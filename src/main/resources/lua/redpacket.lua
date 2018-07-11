local userKey = "728_user_"..KEYS[1] --用户数据userkey
local statusKey = "728_activity_"..KEYS[2]--当前包场次状态的redisKey
local redpacketCountKey = "728_redpacketCount_"..KEYS[3] --当天红包被抢次数包含升级次数
local currRecordKey = "728_redPacket_record_"..KEYS[3]--当天红包记录key
local yesterdayRecordKey = "728_redPacket_record_"..KEYS[4]--前一天红包记录key
local beforeDayRecordKey = "728_redPacket_record_"..KEYS[5]--前两天红包记录key
local redpacketPoolKey = "728_redPacket_pool_"..KEYS[2]--当前红包池key
local secretCodeKey = "728_secret_code"--提现码codekey
if redis.call("HGET",statusKey,"status") == "1" then --抢红包逻辑 红包开抢中
    if redis.call("HGET",userKey,"st") ~= "1" then --当前用户未鉴权
        return "6"
    end
    
    if redis.call("HEXISTS",currRecordKey,KEYS[1]) == 1 then --表示当前用户当日抢过红包直接返回
        return "5"
    end

    if redis.call("HEXISTS",yesterdayRecordKey,KEYS[1]) == 1 and redis.call("HEXISTS",beforeDayRecordKey,KEYS[1]) == 1 then
        return "7" --当前用户已经抢到过红包，不再参与抢红包
    end
    
    if redis.call("HGET",userKey,"su") == "1" then --当前用户抢过超级红包
        return "10"
    end

	if redis.call("llen",redpacketPoolKey) == 0 then--红包已抢完
		redis.call("HSET",statusKey,"status","2")--将当前红包对应场次的状态修改未红包已经抢完
        redis.call("HSET",statusKey,"time",KEYS[6])--红包抢光时间
        return "2,"..KEYS[6]--红包已经抢完
    end

    local redpacketIdAndAmount = redis.call("lpop",redpacketPoolKey)--从红包池中弹出一个红包
    if type(redpacketIdAndAmount) == "boolean" then--获取到的红包为空
        return "8"
    end
    local redpacketIdAndAmountJson = cjson.decode(redpacketIdAndAmount)
    local redpacketId = redpacketIdAndAmountJson["id"]--获取红包id
    local amount = redpacketIdAndAmountJson["sValue"]--截取红包金额
    local actualAmount = math.floor(tonumber(amount)*tonumber(KEYS[7]))
    local redpacketRecordStr = KEYS[2]..","..redpacketId..","..amount..","..actualAmount..","..KEYS[6]..","..KEYS[1]..", "
    redis.call("HSET",currRecordKey,KEYS[1],redpacketRecordStr)
    redis.call("INCR",redpacketCountKey)
    if tonumber(amount) > tonumber(KEYS[8]) then --当前用户抢到的是超级红包
        redis.call("HSET",userKey,"su","1")
    end

    local totalAmount = redis.call("HGET",userKey,"to")
    if type(totalAmount) == "boolean" then
        redis.call("HSET",userKey,"to",actualAmount)--添加用户总金额
    else
        totalAmount = totalAmount+actualAmount
        redis.call("HSET",userKey,"to",totalAmount)
    end
    local medalValue = redis.call("HGET",userKey,"mv")
    if type(medalValue) == "boolean" then --判断当前用户任务值
        medalValue = "0"
    end
    if redis.call("llen",redpacketPoolKey) == 0 then--红包已抢完
		redis.call("HSET",statusKey,"status","2")--将当前红包对应场次的状态修改未红包已经抢完
        redis.call("HSET",statusKey,"time",KEYS[6])--红包抢光时间
        redpacketRecordStr = redpacketRecordStr..","..medalValue..",2"
        return redpacketRecordStr--红包已经抢完
    else
        return redpacketRecordStr..","..medalValue
    end
elseif redis.call("HGET",statusKey,"status") == "2" then
    local costTime = redis.call("HGET",statusKey,"time")
    if type(costTime) == "boolean" then
        redis.call("HSET",statusKey,"time",KEYS[6])--红包抢光时间
        costTime = KEYS[6]    
    end
    return "2,"..costTime--红包已抢完
elseif redis.call("HGET",statusKey,"status") == "0" then
	return "0"--未开抢
else
	return "3"--红包结束
end

function string.split(input, delimiter)
    input = tostring(input)
    delimiter = tostring(delimiter)
    if (delimiter=='') then return false end
    local pos,arr = 0, {}
    -- for each divider found
    for st,sp in function() return string.find(input, delimiter, pos, true) end do
        table.insert(arr, string.sub(input, pos, st - 1))
        pos = sp + 1
    end
    table.insert(arr, string.sub(input, pos))
    return arr
end

local currRecordKey = "728_redPacket_record_"..KEYS[2]--当天红包记录key
local userKey = "728_user_"..KEYS[1] --用户数据userkey
local redpacketCountKey = "728_redpacketCount_"..KEYS[2] --当天红包被抢次数包含升级次数
local medalValue = redis.call("HGET",userKey,"mv") --获取当前用的任务值
if KEYS[5] == "1" then --任务升级红包
    if type(medalValue) == "boolean" or medalValue == "0" then --用户未完成任务
        return "3"
    end
    if bit.band(medalValue,KEYS[4]) ~= tonumber(KEYS[4]) then --当前用户未完成任务
        return "4"
    end
end

if redis.call("HEXISTS",currRecordKey,KEYS[1]) == 0 then--当前用户当天无红包记录
    return "5"
end

local redpacketRecordStr = redis.call("HGET",currRecordKey,KEYS[1])
local index = string.find(redpacketRecordStr," ")
if index == nil then --当前用户已经升级过红包
    return "6"
end
local retTable = string.split(redpacketRecordStr,",")
local amount = retTable[3]
local actualAmount = retTable[4]
local upgradeAmount = amount-actualAmount
local subRecord = string.sub(redpacketRecordStr,0,string.find(redpacketRecordStr," ")-1)
local upgradeRecord = subRecord..upgradeAmount..","..KEYS[5]
redis.call("HSET",currRecordKey,KEYS[1],upgradeRecord)--重置红包记录
redis.call("INCR",redpacketCountKey) --将红包被抢次数加1
local totalAmount = redis.call("HGET",userKey,"to")
totalAmount = totalAmount+upgradeAmount
redis.call("HSET",userKey,"to",totalAmount)
return upgradeRecord


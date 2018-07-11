local currRecordKey = "728_coupon_record_"..KEYS[3]--当优惠券记录key
local userKey = "728_user_"..KEYS[1] --用户数据userkey
if redis.call("HGET",userKey,"st") ~= "1" then --当前用户未鉴权
    return "2"
end
if redis.call("HEXISTS",currRecordKey,KEYS[1]) == 0 then --当前用户当日未抢过优惠券
    local couponId = KEYS[2] -- 优惠券id
    local time = KEYS[4] --抢券时间
    local roundId = KEYS[5] --场次id
    local conponRecordStr = couponId..","..roundId..","..time
    redis.call("HSET",currRecordKey,KEYS[1],conponRecordStr)
    return conponRecordStr
else
    return "2"
end
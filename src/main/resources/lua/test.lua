local userKey = "728_user_"..KEYS[1]
if redis.call("exists",userKey) == "1" then
   return -1
end
local to = redis.call("HGET",userKey,"to")
local wd = redis.call("HGET",userKey,"wd")
local bonus = redis.call("HGET",userKey,"bonus")
if type(to) == "boolean" then
    to = 0
end
if type(wd) == "boolean" then
    wd = 0
end
if type(bonus) == "boolean" then
    bonus = 0
end
local value = tonumber(to)+tonumber(bonus)-tonumber(wd)
local newWd = tonumber(wd) + tonumber(value)
redis.call("HSET",userKey,"wd",newWd)
return value
local array = {}
array[1] = KEYS[1]
array[2] = KEYS[2]
array[3] = KEYS[3]
array[4] = KEYS[4]
array[5] = KEYS[5]
array[6] = KEYS[6]
array[7] = KEYS[7]
array[8] = KEYS[8]
array[9] = KEYS[9]
array[10] = KEYS[10]
array[11] = KEYS[11]
local records = ""
for i=1,11 do
    local couponRecord = redis.call("HGET", array[i], KEYS[12])
    if  type(couponRecord) ~= "boolean" then
        records = records.."|"..couponRecord
    end
end
return records
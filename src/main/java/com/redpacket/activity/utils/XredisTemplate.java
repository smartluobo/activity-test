package com.redpacket.activity.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class XredisTemplate {

	@Resource
    private StringRedisTemplate stringRedisTemplate;

	private String LOCK_VALUE = "1";

	// default time out 3 second
	private int DEFAULT_TIME = 3000;

	public void setKeyValue(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}

	public void setKeyValue(String key, Object value) {
		stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(value));
	}

	public void setKeyValueExpire(String key, String value, long milliseconds) {
		stringRedisTemplate.opsForValue().set(key, value, milliseconds, TimeUnit.MILLISECONDS);
	}

	public void setKeyValueExpire(String key, Object value, long milliseconds) {
		stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(value), milliseconds, TimeUnit.MILLISECONDS);
	}

	public <T> T getValue(String key, Class<T> clazz) {
		String json = stringRedisTemplate.opsForValue().get(key);
		return JSON.toJavaObject(JSON.parseObject(json), clazz);
	}

	public String getValue(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	public boolean lock(String key) {
		return lock(key, LOCK_VALUE, DEFAULT_TIME);
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @param milliseconds
	 *            unit MILLI SECONDS
	 * @return
	 */
	public boolean lock(String key, String value, long milliseconds) {

		boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, value);
		if (flag) {
			flag = stringRedisTemplate.expire(key, milliseconds, TimeUnit.MILLISECONDS);
			if (flag) {
				return true;
			}
			stringRedisTemplate.delete(key);
		}
		return false;
	}

	public void unlock(String key) {
		stringRedisTemplate.delete(key);
	}

	public void delete(String key) {
		stringRedisTemplate.delete(key);
	}

	public void delete(Collection<String> keys) {
		stringRedisTemplate.delete(keys);
	}

	public boolean exist(String key) {
		return stringRedisTemplate.hasKey(key);
	}


    public long listRightPushAll(String key, List<String> values){
        return stringRedisTemplate.opsForList().rightPushAll(key, values);
    }

	public void hashSet(String key, String hashKey, String value) {
		stringRedisTemplate.opsForHash().put(key, hashKey, value);
	}

	public void hashSet(String key, Map<String, String> map) {
		stringRedisTemplate.opsForHash().putAll(key, map);
	}

	public void hashSet(String key, String hashKey, Object value){
		stringRedisTemplate.opsForHash().put(key, hashKey, JSON.toJSONString(value));
	}

	public String hashGet(String key, String hashKey){
		return  (String) stringRedisTemplate.opsForHash().get(key, hashKey);
	}

	public <T> T hashGet(String key, String hashKey, Class<T> clazz){
		String value = (String) stringRedisTemplate.opsForHash().get(key, hashKey);
		return JSON.toJavaObject(JSON.parseObject(value), clazz);
	}

	public long hashDelete(String key, String hashKey) {
		return stringRedisTemplate.opsForHash().delete(key, hashKey);
	}

	public boolean hasHashKey(String key, String hashKey) {
		return stringRedisTemplate.opsForHash().hasKey(key, hashKey);
	}


	// -------------------------zset(sortedSet)-----------------------------
	/**
	 * <pre>
	 * Add specified member with the specified score to the sorted set stored at key.
	 * * If the key exists but does not hold a sorted set, null is returned.
	 * Time complexity: O(log(N))
	 * </pre>
	 * 
	 * @param key
	 * @param member
	 * @param score
	 * @return
	 */
	public Boolean zsetAdd(String key, String member, double score) {
		Boolean result = false;
		try {
			result = stringRedisTemplate.opsForZSet().add(key, member, score);
		} catch (Exception e) {
			result = null;
		}
		return result;
	}

	/**
	 * <pre>
	 * Returns the sorted set cardinality (number of elements) of the sorted set stored at key.
	 * * If key does not exist,return 0。
	 * * If the key exists but does not hold a sorted set, null is returned.
	 * Time complexity: O(1)
	 * </pre>
	 * 
	 * @param key
	 * @return
	 */
	public Long zsetSize(String key) {
		Long result = null;
		try {
			result = stringRedisTemplate.opsForZSet().size(key);
		} catch (Exception e) {
			result = null;
		}
		return result;
	}

	/**
	 * <pre>
	 * Returns the rank of member in the sorted set stored at key, with the
	 * scores ordered from low to high. The rank (or index) is 0-based, which
	 * means that the member with the lowest score has rank 0.
	 * * If key does not exist,null is returned.
	 * * If the key exists but does not hold a sorted set, null is returned.
	 * Time complexity: O(log(N))
	 * </pre>
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public Long zsetRank(String key, String value) {
		Long result = null;
		try {
			result = stringRedisTemplate.opsForZSet().rank(key, value);
		} catch (Exception e) {
			result = null;
		} 
		return result;
	}

	public Set<String> hashKeys(String redisKey){
		HashOperations<String, String, String> opsForHash = stringRedisTemplate.opsForHash();
		Set<String> keys = opsForHash.keys(redisKey);
		return  keys;
	}

	public List<String> hashValues(String redisKey){
		HashOperations<String, String, String> opsForHash = stringRedisTemplate.opsForHash();
		List<String> values = opsForHash.values(redisKey);
		return values;
	}



}
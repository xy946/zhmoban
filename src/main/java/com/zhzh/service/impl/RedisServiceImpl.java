package com.zhzh.service.impl;

import com.zhzh.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisServiceImpl.class);



    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * <写入缓存>
     *
     * @param key   key
     * @param value value
     * @return 写入是否成功
     * @throws
     */
    public boolean set(String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        return result;
    }

    /**
     * <写入缓存设置时效时间>
     *
     * @param key        key
     * @param value      value
     * @param expireTime expireTime
     * @return 写入是否成功
     * @throws
     */
    public boolean set(String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        return result;
    }

    /**
     * <写入缓存>
     *
     * @param key   key
     * @param value value
     * @return 写入是否成功  已经有此key set失败
     * @throws
     */
    public boolean setNx(String key, Object value) {
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            return operations.setIfAbsent(key, value);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        return false;
    }

    /**
     * <写入缓存设置时效时间>
     *
     * @param key        key
     * @param value      value
     * @param expireTime expireTime
     * @return 写入是否成功 已经有此key set失败
     */
    public boolean setNx(String key, Object value, Long expireTime) {
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            return operations.setIfAbsent(key, value, Duration.ofSeconds(expireTime));
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        return false;
    }

    /**
     * <批量删除对应的value>
     *
     * @param keys keys
     * @throws
     */
    public void remove(String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * <批量删除key>
     *
     * @param pattern pattern
     * @throws
     */

    public void removePattern(String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * <删除对应的value>
     *
     * @param key key
     * @throws
     */

    public void remove(String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * <判断缓存中是否有对应的value>
     *
     * @param key key
     * @return 是否存在
     * @throws
     */

    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * <读取缓存>
     *
     * @param key key
     * @return 结果
     * @throws
     */

    public Object get(String key) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    /**
     * <删除缓存>
     *
     * @param key key
     * @return 结果
     * @throws
     */

    public void removeById(String key) {
        String prefix = "*" + key + "-*";
        Set<String> keys = redisTemplate.keys(prefix);
        if (keys.size() > 0) {
            for (String key1 : keys) {
                remove(key1);
            }
        }
    }

    public int size(String key) {

        Set<String> keys = redisTemplate.keys(key);
        return keys.size();
    }

    /**
     * <哈希添加>
     *
     * @param key     key
     * @param hashKey hashKey
     * @param value   value
     * @throws
     */

    public void hmSet(String key, Object hashKey, Object value) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key, hashKey, value);
    }

    /**
     * <哈希获取数据>
     *
     * @param key     key
     * @param hashKey hashKey
     * @return 结果
     * @throws
     */

    public Object hmGet(String key, Object hashKey) {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }

    /**
     * <列表添加>
     *
     * @param k k
     * @param v v
     * @throws
     */

    public void addByList(String k, Object v) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.leftPush(k, v);
    }

    /**
     * <列表获取所有>
     *
     * @param k k
     * @return 结果
     * @throws
     */

    public <T> T getByListAll(String k) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return (T) list.rightPop(k);
    }

    /**
     * <列表获取>
     *
     * @param k k
     * @return 结果
     * @throws
     */

    public List<Object> getByListPage(String k, int page, int pageSize) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        Integer beginIndex = ((page - 1) * pageSize);
        Integer endIndex = (beginIndex + pageSize - 1);
        return list.range(k, beginIndex, endIndex);
    }

    /**
     * <集合添加>
     *
     * @param key   key
     * @param value value
     * @throws
     */

    public void add(String key, Object value) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key, value);
    }

    /**
     * <集合获取>
     *
     * @param key key
     * @return 结果
     * @throws
     */

    public Set<Object> setMembers(String key) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    /**
     * <有序集合添加>
     *
     * @param key    key
     * @param value  value
     * @param scoure scoure
     * @throws
     */

    public void zAdd(String key, Object value, double scoure) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key, value, scoure);
    }

    /**
     * <有序集合获取>
     *
     * @param key     key
     * @param scoure  scoure
     * @param scoure1 scoure1
     * @return 结果
     * @throws
     */

    public Set<Object> rangeByScore(String key, double scoure, double scoure1) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }

    //生成服务编号
    public String createFWOrderNum(){
        StringBuffer sb=new StringBuffer();
        LocalDateTime startTime= LocalDateTime.now(ZoneOffset.of("+8")); //2019-08-05T15:12:22.733
        String date = Constants.df.format(startTime);    //2019-08-05 15:12:22
        Long increment = redisTemplate.opsForValue().increment(date,1L);
        redisTemplate.expire(date,1, TimeUnit.DAYS);
        String num = String.format("%04d", increment);
        return sb.append("ORDER").append(date).append(num).toString();
    }
}

//package com.qiqi.rs.admin.core.cache;
//
//import com.qiqi.core.utils.JsonUtil;
//import com.qiqi.core.utils.RedisUtil;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.TimeUnit;
//
//@Component
//public class CacheService {
//
//    private final static Logger logger = LoggerFactory.getLogger(CacheService.class);
//
//    @Autowired
//    private RedisUtil redisUtil;
//
//    public void cacheToRedis(String key, Object obj, long expireTime, TimeUnit timeUnit) {
//        redisUtil.set(key, obj, expireTime, timeUnit);
//    }
//
//    public <T> T getCacheFromRedis(String key, Class<T> cls) {
////        String str = null;
////        try {
////            str = (String) redisTemplate.opsForValue().get(key);
////            if (str == null) {
////                return null;
////            }
////            return JsonUtils.jsonToBean(str, cls);
////        } catch (Throwable e) {
////            logger.error(e.getMessage(), e);
////
////        }
//
//        Object obj = redisTemplate.opsForValue().get(key);
//        if (obj != null) {
//            return (T)obj;
//        }
//
//        return null;
//    }
//
//    public void removeFromRedis(String key) {
//        redisTemplate.delete(key);
//    }
//
//    public void expire(final String[] keys, final long expireTime, final TimeUnit timeUnit) {
//        if (keys != null && keys.length > 0) {
//            for (String key : keys) {
//                redisTemplate.expire(key, expireTime, timeUnit);
//            }
//        }
//    }
//
//    public void expire(final String key, final long expireTime, final TimeUnit timeUnit) {
//        if (!StringUtils.isBlank(key)) {
//            this.expire(new String[]{key}, expireTime, timeUnit);
//        }
//    }
//}

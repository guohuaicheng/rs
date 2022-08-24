package com.qiqi.rs.admin.core.cache;

import com.qiqi.core.utils.ContextUtil;
import com.qiqi.core.utils.RedisUtil;
import com.qiqi.rs.admin.core.constants.IConstants;
import com.qiqi.rs.admin.platform.user.model.CachedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class TokenCache {
    private final static Logger logger = LoggerFactory.getLogger(TokenCache.class);
    private static RedisUtil redisUtil;

    static {
        redisUtil = ContextUtil.getBean(RedisUtil.class);
        if (redisUtil == null) {
            throw new RuntimeException("Not found bean RedisUtil");
        }
    }

    public static void set(String token, CachedUser cachedUser) {

        redisUtil.set(IConstants.CACHE_TOKEN_ADMIN + token, cachedUser, IConstants.CACHE_TIME_OUT, TimeUnit.MINUTES);
    }

    public static CachedUser get(String token) {
        CachedUser cachedUser = redisUtil.get(IConstants.CACHE_TOKEN_ADMIN + token, CachedUser.class);
        if (cachedUser != null) {
            redisUtil.expire(IConstants.CACHE_TOKEN_ADMIN + token, IConstants.CACHE_TIME_OUT, TimeUnit.MINUTES);
        }
        return cachedUser;
    }

    public static void remove(String token) {
        redisUtil.del(IConstants.CACHE_TOKEN_ADMIN + token);
    }

}

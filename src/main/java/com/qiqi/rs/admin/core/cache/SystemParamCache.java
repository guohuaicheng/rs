package com.qiqi.rs.admin.core.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 系统参数缓存
 */
public class SystemParamCache {

    private static Map<String, String> systemParamCache = new ConcurrentHashMap<String, String>();

    public static void put(String key, String value) {
        systemParamCache.put(key, value);
    }

    public static String get(String key) {
        return systemParamCache.get(key);
    }

    public static void clear() {
        systemParamCache.clear();
    }

    public static Map<String, String> getAll() {
        return systemParamCache;
    }

}

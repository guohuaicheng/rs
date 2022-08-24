package com.qiqi.rs.admin.core.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DictCache {

    private static Map<String, Map<String, String>> dictCacheMap = new ConcurrentHashMap<String, Map<String, String>>();
    private static DictCache instance = new DictCache();

    private DictCache() {
    }


    public static DictCache getInstance() {
        return instance;
    }

    public void put(String type, Map<String, String> map) {
        this.dictCacheMap.put(type, map);
    }

    public Map<String, String> get(String type) {
        return dictCacheMap.get(type);
    }

    public void clear() {
        this.dictCacheMap.clear();
    }
}

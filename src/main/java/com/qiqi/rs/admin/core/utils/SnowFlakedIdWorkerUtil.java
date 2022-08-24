package com.qiqi.rs.admin.core.utils;

import com.qiqi.core.utils.ContextUtil;

public class SnowFlakedIdWorkerUtil {

    private static SnowflakeIdWorker snowFlakedIdWorker;

    static {
        snowFlakedIdWorker = ContextUtil.getBean(SnowflakeIdWorker.class);
        if (snowFlakedIdWorker == null) {
            throw new RuntimeException("Not found bean SnowflakeIdWorker");
        }
    }

    public static long generateId() {
        return snowFlakedIdWorker.nextId();
    }
}

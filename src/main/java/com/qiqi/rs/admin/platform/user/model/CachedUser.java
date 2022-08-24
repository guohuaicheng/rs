package com.qiqi.rs.admin.platform.user.model;

public class CachedUser extends UserAuth {
    private long lastUsingTimestamp = System.currentTimeMillis();

    public long getLastUsingTimestamp() {
        return lastUsingTimestamp;
    }

    public void setLastUsingTimestamp(long lastUsingTimestamp) {
        this.lastUsingTimestamp = lastUsingTimestamp;
    }
}

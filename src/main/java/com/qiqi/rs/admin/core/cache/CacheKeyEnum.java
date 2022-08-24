package com.qiqi.rs.admin.core.cache;

public enum CacheKeyEnum {
    IP("Cache::Ip::"),
    ONLINE_SHB_TENANT_CONFIG("Online:tenant:shb:config:"),
    ONLINE_CZB_TENANT_CONFIG("Online:tenant:czb:config:"),
    ONLINE_DXB_TENANT_CONFIG("Online:tenant:dxb:config:");
    private String prefix;

    CacheKeyEnum(String prefix) {
        this.prefix = prefix;
    }

    public String generateKey(String key) {
        return this.prefix + key;
    }
}

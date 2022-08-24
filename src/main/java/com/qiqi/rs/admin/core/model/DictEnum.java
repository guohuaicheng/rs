package com.qiqi.rs.admin.core.model;

public enum DictEnum {
    XTJF("xtjf", "系统缴费"), DXCZ("dxcz", "短信充值"), INDUSTRY("industry", "所属行业");
    private String code;
    private String desc;

    private DictEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String getCode() {
        return code;
    }
}

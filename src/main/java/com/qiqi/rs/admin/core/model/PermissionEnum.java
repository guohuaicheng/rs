package com.qiqi.rs.admin.core.model;

/**
 * 员工角色-枚举类
 */
public enum PermissionEnum {
    ADMIN(""),
    EDIT("修改"),
    PAY("缴费"),
    SEARCH("查看");

    private String desc;


    private PermissionEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}

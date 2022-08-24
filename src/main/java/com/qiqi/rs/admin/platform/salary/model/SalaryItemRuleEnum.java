package com.qiqi.rs.admin.platform.salary.model;

public enum SalaryItemRuleEnum {
    DIRECT(1, "直接"), PACKAGE(2, "计件");

    private int code;
    private String desc;

    SalaryItemRuleEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int code() {
        return this.code;
    }

    public static SalaryItemRuleEnum fromCode(int code) {
        if (code == 1) return DIRECT;
        else if (code == 2) return PACKAGE;
        else return null;
    }
}

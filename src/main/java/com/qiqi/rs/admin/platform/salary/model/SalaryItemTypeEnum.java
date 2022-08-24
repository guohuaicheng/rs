package com.qiqi.rs.admin.platform.salary.model;

public enum SalaryItemTypeEnum {
    ADD(1, "增加"), MINUS(2, "减少");

    private int code;
    private String desc;

    SalaryItemTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int code() {
        return this.code;
    }

    public static SalaryItemTypeEnum fromCode(int code) {
        if (code == 1) return ADD;
        else if (code == 2) return MINUS;
        else return null;
    }
}

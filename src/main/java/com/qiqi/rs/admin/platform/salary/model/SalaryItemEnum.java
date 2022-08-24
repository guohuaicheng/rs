package com.qiqi.rs.admin.platform.salary.model;

public enum SalaryItemEnum {
    BASE(1, "基本工资"), YLBX(2, "养老保险"), YLBX3(3, "医疗保险"), SYBX(4, "生育保险"), SYBX5(5, "失业保险"), GSBX(6, "工伤保险"), GJJ(7, "公积金");

    private int id;
    private String desc;
    SalaryItemEnum(int id, String desc) {
        this.id = id;
        this.desc = desc;
    }
}

package com.qiqi.rs.admin.platform.employee.model;

public enum EmployeeStateEnum {
    NOT_JOIN(1), JOIN(2), LEAVE(3);

    private int state;
    EmployeeStateEnum(int state) {
        this.state = state;
    }


    public int state() {
        return this.state;
    }
}

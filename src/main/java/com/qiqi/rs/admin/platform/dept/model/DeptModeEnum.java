package com.qiqi.rs.admin.platform.dept.model;

public enum DeptModeEnum {
    BM(1), GW(2);

    private int mode;

    DeptModeEnum(int mode) {
        this.mode = mode;
    }

    public int mode() {
        return mode;
    }

    public static DeptModeEnum valueFrom(int mode) {
        switch (mode) {
            case 1:
                return BM;
            case 2:
                return GW;
            default:
                return null;
        }
    }
}

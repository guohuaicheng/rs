package com.qiqi.rs.admin.platform.user.model;

public enum RoleEnum {
    ADMIN("0", "管理员"),
    SALE("1", "销售");

    private String code;
    private String roleName;

    private RoleEnum(String code, String roleName) {
        this.code = code;
        this.roleName = roleName;
    }

    public String code() {
        return code;
    }

    public static RoleEnum fromCode(String code) {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.code.equals(code)) {
                return roleEnum;
            }
        }
        return null;
    }

}

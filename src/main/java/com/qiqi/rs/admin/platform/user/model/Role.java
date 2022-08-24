package com.qiqi.rs.admin.platform.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.qiqi.rs.admin.platform.base.PlatformBase;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Role extends PlatformBase {
    private String roleId;
    private String roleCode;
    private String roleName;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}

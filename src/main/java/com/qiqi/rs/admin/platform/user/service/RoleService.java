package com.qiqi.rs.admin.platform.user.service;

import com.qiqi.rs.admin.platform.user.model.Role;

import java.util.List;

public interface RoleService {

    int createRole(Role role);

    List<Role> queryRoles(Role pojo);

    int updateRole(Role pojo);
}

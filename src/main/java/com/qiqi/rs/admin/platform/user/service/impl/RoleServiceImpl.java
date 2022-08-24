package com.qiqi.rs.admin.platform.user.service.impl;

import com.qiqi.rs.admin.platform.user.dao.RoleDao;
import com.qiqi.rs.admin.platform.user.model.Role;
import com.qiqi.rs.admin.platform.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public int createRole(Role role) {
        return roleDao.insert(role);
    }

    @Override
    public List<Role> queryRoles(Role role) {
        return roleDao.select(role);
    }

    @Override
    public int updateRole(Role role) {
        return roleDao.update(role);
    }

}

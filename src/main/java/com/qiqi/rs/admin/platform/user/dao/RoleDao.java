package com.qiqi.rs.admin.platform.user.dao;

import com.qiqi.rs.admin.platform.user.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleDao {

    int insert(@Param("role") Role role);

    List<Role> select(@Param("role") Role role);

    int update(@Param("role") Role role);

    int delete(@Param("roleId") String roleId);
}

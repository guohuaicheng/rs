package com.qiqi.rs.admin.platform.user.dao;

import com.qiqi.rs.admin.platform.user.model.UserAuth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserAuthDao {

    int insert(UserAuth userAuth);

    List<UserAuth> select(UserAuth userAuth);

    UserAuth selectByUsername(@Param("username") String username);

    int update(UserAuth userAuth);

}

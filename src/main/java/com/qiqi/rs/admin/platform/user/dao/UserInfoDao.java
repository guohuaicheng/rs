package com.qiqi.rs.admin.platform.user.dao;

import com.qiqi.rs.admin.platform.user.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInfoDao {

    int insert(UserInfo userInfo);

    int update(UserInfo userInfo);

    int delete(@Param("userId") String userId);

    String selectLastCode();

    List<UserInfo> select(UserInfo userInfo);

    List<UserInfo> selectBasicInfo(UserInfo userInfo);

    UserInfo selectByCodeForCheckIfExists(@Param("userCode") String userCode);

    UserInfo selectByNameForCheckIfExists(@Param("nickname") String nickname);

    UserInfo selectByMobileForCheckIfExists(@Param("mobilePhone") String mobilePhone);

    UserInfo selectById(@Param("userId") String userId);
    // 用于编辑密码
    UserInfo selectByIdForOpt(@Param("userId") String userId);

    UserInfo selectByCode(@Param("userCode") String userCode);


}

package com.qiqi.rs.admin.platform.user.service;

import com.qiqi.core.utils.PageCondition;
import com.qiqi.rs.admin.platform.user.model.UserInfo;

import java.util.List;

public interface UserInfoService {

    int createUser(UserInfo userInfo);

    int updateUser(UserInfo userInfo);

    int deleteUser(String userId);

    String queryNextCode();

    List<UserInfo> queryUsers(UserInfo userInfo, PageCondition pageCondition);

    List<UserInfo> queryUsersBasicInfo(UserInfo userInfo, PageCondition pageCondition);

    UserInfo queryUserById(String userId);
}

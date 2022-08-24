package com.qiqi.rs.admin.platform.user.service;

import com.qiqi.rs.admin.platform.user.model.UserAuth;

import java.util.List;

public interface UserAuthService {

    int createUserAuth(UserAuth userAuth);

    List<UserAuth> queryUserAuths(UserAuth userAuth);

    UserAuth queryByUsername(String username);

    int update(UserAuth userAuth);

}

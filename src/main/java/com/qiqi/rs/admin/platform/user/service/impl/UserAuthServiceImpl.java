package com.qiqi.rs.admin.platform.user.service.impl;

import com.qiqi.rs.admin.platform.user.dao.UserAuthDao;
import com.qiqi.rs.admin.platform.user.model.UserAuth;
import com.qiqi.rs.admin.platform.user.service.UserAuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserAuthDao userAuthDao;

    @Override
    public int createUserAuth(UserAuth userAuth) {
        return 0;
    }

    @Override
    public List<UserAuth> queryUserAuths(UserAuth userAuth) {
        return null;
    }

    @Override
    public UserAuth queryByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        return this.userAuthDao.selectByUsername(username);
    }

    @Override
    public int update(UserAuth userAuth) {
        return 0;
    }
}

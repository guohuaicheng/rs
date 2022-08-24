package com.qiqi.rs.admin.platform.login.controller;

import com.qiqi.core.model.Result;
import com.qiqi.rs.admin.core.cache.TokenCache;
import com.qiqi.rs.admin.core.constants.IConstants;
import com.qiqi.rs.admin.core.exception.ExceptionDefinition;
import com.qiqi.rs.admin.platform.user.model.CachedUser;
import com.qiqi.rs.admin.platform.user.model.UserAuth;
import com.qiqi.rs.admin.platform.user.model.UserInfo;
import com.qiqi.rs.admin.platform.user.service.UserAuthService;
import com.qiqi.rs.admin.platform.user.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author G
 */
@RestController
public class LoginController {

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/platform/login", method = RequestMethod.POST)
    public Result<Integer> login(@RequestBody UserAuth userAuth, HttpServletResponse response) {

        // 判断用户登录信息是否完整
        if (userAuth == null || StringUtils.isBlank(userAuth.getUsername()) || StringUtils.isBlank(userAuth.getPassword())) {
            throw ExceptionDefinition.LOGIN_FAILED_ERROR.exception();
        }

        String username = userAuth.getUsername();
        String password = userAuth.getPassword();
        String device = userAuth.getDevice();

        userAuth = this.userAuthService.queryByUsername(username);
        if (userAuth == null || !StringUtils.equals(password, userAuth.getPassword())) {
            throw ExceptionDefinition.LOGIN_FAILED_ERROR.exception();
        }

        UserInfo userInfo = userInfoService.queryUserById(userAuth.getUserId());
        if (userInfo == null) {
            throw ExceptionDefinition.LOGIN_FAILED_ERROR.exception();
        }

        // 将token缓存下来
        String token = UUID.randomUUID().toString();
        response.setHeader(IConstants.SESSION_TOKEN, token);

        CachedUser cachedUser = new CachedUser();
        BeanUtils.copyProperties(userAuth, cachedUser);
        cachedUser.setDevice(device);
        cachedUser.setPassword(null);
        TokenCache.set(token, cachedUser);

        // 记录操作日志
        return new Result<Integer>(1);
    }

    @RequestMapping(value = "/platform/logout")
    public Result<Integer> logout(ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader(IConstants.SESSION_TOKEN);
//        TokenCache.remove(token);
        TokenCache.remove(token);
        return new Result<Integer>(1);
    }
}

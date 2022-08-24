package com.qiqi.rs.admin.core.interceptor;

import com.qiqi.core.exception.ServiceRuntimeException;
import com.qiqi.core.model.Result;
import com.qiqi.core.utils.JsonUtil;
import com.qiqi.rs.admin.core.cache.TokenCache;
import com.qiqi.rs.admin.core.constants.IConstants;
import com.qiqi.rs.admin.core.exception.ExceptionDefinition;
import com.qiqi.rs.admin.core.runtime.threadlocal.UserContextThreadLocalHolder;
import com.qiqi.rs.admin.platform.user.model.CachedUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

//    private final static String TENANT_URL_PREFIX = "/tenant";
//    private final static String EXPORT = "/tenant/export";
//    private final static String UPLOAD_IE9 = "/tenant/upload/ie9";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader(IConstants.SESSION_TOKEN);

        CachedUser cachedUser = null;
        if (!StringUtils.isEmpty(token)) {
            cachedUser = TokenCache.get(token);
            if (cachedUser != null) {
                UserContextThreadLocalHolder.set(cachedUser);
            } else {
                handleUnloginException(request, response);
                return false;
            }
        } else {
            handleUnloginException(request, response);
            return false;
        }

        return super.preHandle(request, response, handler);
    }

    private void handleUnloginException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServiceRuntimeException serviceRuntimeException = ExceptionDefinition.LOGIN_NOT_LOGIN_ERROR.exception();
        Result result = new Result();
        result.setCode(serviceRuntimeException.getErrorCode());
        result.setMessage(serviceRuntimeException.getMessage());
        logger.error(serviceRuntimeException.getErrorCode() + " -> " + serviceRuntimeException.getMessage(), serviceRuntimeException);
        response.setHeader("Content-Type", request.getHeader("Content-Type"));
        response.getWriter().write(JsonUtil.beanToJson(result));
    }
}

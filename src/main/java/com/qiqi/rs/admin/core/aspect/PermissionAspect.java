package com.qiqi.rs.admin.core.aspect;

import com.qiqi.rs.admin.core.annotation.Permission;
import com.qiqi.rs.admin.core.constants.IConstants;
import com.qiqi.rs.admin.core.exception.ExceptionDefinition;
import com.qiqi.rs.admin.core.model.PermissionEnum;
import com.qiqi.rs.admin.core.runtime.threadlocal.UserContextThreadLocalHolder;
import com.qiqi.rs.admin.platform.user.model.CachedUser;
import com.qiqi.rs.admin.platform.user.model.UserInfo;
import com.qiqi.rs.admin.platform.user.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class PermissionAspect {

    @Autowired
    private UserInfoService userInfoService;

    //定义切入点
	/*1、execution 表达式主体
	  2、第1个* 表示返回值类型  *表示所有类型
	  3、包名  com.*.*.controller下
	  4、第4个* 类名，com.*.*.controller包下所有类
	  5、第5个* 方法名，com.*.*.controller包下所有类所有方法
	  6、(..) 表示方法参数，..表示任何参数
	  */
    @Pointcut("@annotation(com.qiqi.rs.admin.core.annotation.Permission)")
    public void validate() {

    }

    @Before("validate()")
    public void doBefore(JoinPoint joinPoint) {        //方法里面注入连接点
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Permission permission = methodSignature.getMethod().getAnnotation(Permission.class);
        if (permission == null) {
            return;
        }

        CachedUser cachedUser = UserContextThreadLocalHolder.get();
        if (cachedUser == null) {
            throw ExceptionDefinition.PERMISSION_ERROR.exception();
        }
        if (StringUtils.equals(cachedUser.getUserCode(), IConstants.DEFAULT_CODE)) { // 如果是超级管理员，则不需验证
            return;
        }

        List<PermissionEnum> permissionEnumList = Arrays.asList(permission.permission());
        if (permissionEnumList.contains(PermissionEnum.ADMIN)) {
            throw ExceptionDefinition.PERMISSION_ERROR.exception();
        }

        UserInfo userInfo = this.userInfoService.queryUserById(cachedUser.getUserId());
        if (userInfo == null) {
            throw ExceptionDefinition.PERMISSION_ERROR.exception();
        }


        String userPermission = userInfo.getPermission();
        List<String> ps = null;
        if (StringUtils.isBlank(userPermission)) {
            throw ExceptionDefinition.PERMISSION_ERROR.exception();
        } else {
            ps = Arrays.asList(userPermission.split(","));
        }

        for (PermissionEnum p : permissionEnumList) {
            if (!ps.contains(p.name().toLowerCase())) {
                throw ExceptionDefinition.PERMISSION_ERROR.exception();
            }
        }

    }

}

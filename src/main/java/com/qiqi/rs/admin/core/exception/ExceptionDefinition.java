package com.qiqi.rs.admin.core.exception;

import com.qiqi.core.exception.ServiceRuntimeException;
import com.qiqi.core.utils.ContextUtil;

import java.text.MessageFormat;
import java.util.Locale;

public enum ExceptionDefinition {

    INNER_ERROR("inner.000000"),
    /**
     * 通用 开始
     */
    COMMON_LACK_INFO_ERROR("common.000000"),

    /**
     * 权限 开始
     */
    PERMISSION_ERROR("permission.000000"),

    LOGIN_NOT_LOGIN_ERROR("login.000000"),
    LOGIN_LACK_INFO_ERROR("login.000001"),
    LOGIN_FAILED_ERROR("login.000002"), // 用户名密码错误


    USER_LACK_INFO_ERROR("user.000000"),
    USER_CREATE_NAME_EXIST_ERROR("user.000001"),
    USER_UPDATE_NULL_ERROR("user.000002"),
    USER_CREATE_MOBILE_PHONE_EXIST_ERROR("user.000003"),
    USER_CREATE_CODE_EXIST_ERROR("user.000004"),

    /**
     * 工单 开始
     */
    WORK_ORDER_NOT_FOUND("workorder.000000"),
    WORK_ORDER_HAS_BEEN_RESOLVED("workorder.000001"),
    WORK_ORDER_HAS_BEEN_CLOSED("workorder.000002"),
    WORK_ORDER_HAS_BEEN_CANCELED("workorder.000003"),
    /**
     * 工单 结束
     */

    /**
     * 版本历史
     */
    APP_HISTORY_LACK_INFO_ERROR("app_history.000000"),
    APP_HISTORY_UPDATE_NULL_ERROR("app_history.000001"),


    /**
     * 通知
     */
    ANNOUNCEMENT_UPDATE_NULL_ERROR("announcement.000001");

    private String errorCode;

    ExceptionDefinition(String errorCode) {
        this.errorCode = errorCode;
    }

    private String getMessage(Object... args) {
        String message = ContextUtil.getMessage(this.errorCode, null, this.name(), Locale.getDefault());
        return MessageFormat.format(message, args);
    }

    public ServiceRuntimeException exception(Object... args) {
        return new ServiceRuntimeException(this.errorCode, getMessage(args));
    }

    public ServiceRuntimeException exception(Throwable cause, Object... args) {
        return new ServiceRuntimeException(this.errorCode, getMessage(args), cause);
    }
}

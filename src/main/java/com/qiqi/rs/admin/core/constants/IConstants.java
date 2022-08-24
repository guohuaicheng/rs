package com.qiqi.rs.admin.core.constants;

/**
 * @author G
 */
public interface IConstants {

    String CACHE_TOKEN_ADMIN = "Token:platform:admin:";
    int CACHE_TIME_OUT = 30;

    String SESSION_TOKEN = "token";
    String NON_VIP_NAME_PY = "FHY";
    String DEFAULT_CODE = "0000";
    String DEFAULT_START_CODE = "0001";

    String QUERY_SORT_CREATED_TIME = "createdTime";



    String ERROR_DEFAULT_CODE = "0";
    String ERROR_INNER_MSG = "内部错误";
    String ERROR_DATA_MSG = "数据错误";
    String ERROR_NO_DATA_MSG = "数据不存在";

    String ERROR_USED_MSG = "已使用";

    String ERROR_NO_DEPT_MSG = "部门不存在";
    String ERROR_NO_PARENT_DEPT_MSG = "上级部门不存在";
    String ERROR_DEPT_EXIST_EMP_MSG = "部门下存在员工";

    String RESPONSE_COUNT = "result_count";
}
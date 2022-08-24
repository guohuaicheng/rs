package com.qiqi.rs.admin.core.model;

/**
 * 系统参数
 */
public enum SystemParamEnum {

    APP_NAME("app_name", "系统名"),
    VERSION("version", "系统版本号"),
    ADDRESS("address", "公司地址"),
    EMAIL("email", "公司邮箱"),
    TELEPHONE("telephone", "公司电话"),
    URL("url", "网站首页"),
    FILE_URL("file_url", "文件服务器地址"),
    IMAGE_URL("image_url", "图片服务器地址"),
    MQTT_URL("mqtt_url", "消息推送服务器地址"),
    FREE_TIME("free_time", "新注册用户默认试用时长（单位月）"),
    MSG_COUNT("msg_count", "新注册用户默认赠送短信条数"),
    REJECT_TIME("reject_time", "允许作废的时间范围（单位天）"),
    QQ("qq", "客服QQ");

    private String code;
    private String desc;

    private SystemParamEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String code() {
        return this.code;
    }
}

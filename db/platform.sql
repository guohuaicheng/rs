--平台管理员
DROP TABLE IF EXISTS `p_user_auth`;
CREATE TABLE p_user_auth (
  `user_id`  VARCHAR(64) NOT NULL COMMENT 'user_id',
  `username` VARCHAR(64) NOT NULL COMMENT '账号',
  `user_code` VARCHAR(64) COMMENT '用户编号',
  `mobile_phone` VARCHAR(20) NOT NULL COMMENT '手机号',
  `password` VARCHAR(128) NOT NULL COMMENT '密码',
  PRIMARY KEY (`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'p_user_auth';

INSERT INTO p_user_auth VALUES ('1', '0000', '0000', '15853991666', '1df84c75e41452d62e4cacb333115da0');

DROP TABLE IF EXISTS `p_user_info`;
CREATE TABLE p_user_info(
    `user_id` VARCHAR(128) NOT NULL COMMENT '用户ID',
    `user_code` VARCHAR(64) COMMENT '用户编号',
    `username` VARCHAR(64) NOT NULL COMMENT '账号',
    `nickname` VARCHAR(64) COMMENT '昵称',
    `mobile_phone` VARCHAR(20) NOT NULL COMMENT '手机号',
    `role` VARCHAR(128) NOT NULL COMMENT '角色',
    `permission` VARCHAR(256) COMMENT '权限',
    `description` VARCHAR(256) COMMENT '描述',
    `enable_flag` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '是否启用',
    `del_flag` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
    `created_time` DATETIME NOT NULL COMMENT '创建时间',
    `created_user` VARCHAR(64) NOT NULL COMMENT '创建人',
    `updated_time` DATETIME COMMENT '更新时间',
    `updated_user` VARCHAR(64) COMMENT '更新人',
    `created_device` VARCHAR(128) NOT NULL COMMENT '创建设备',
    `created_device_ip` VARCHAR(64) NOT NULL COMMENT '创建设备IP',
    `updated_device` VARCHAR(128) COMMENT '更新设备',
    `updated_device_ip` VARCHAR(64) COMMENT '更新设备IP',
    PRIMARY KEY (`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '系统用户表';

INSERT INTO p_user_info VALUES ('1', '0000', '0000', '管理员', '15853991666', '0', '','', 1, 0, '2018-10-01 00:00:00', '0000', NULL, NULL, 'W@pc', '127.0.0.1', NULL, NULL);
-- DROP TABLE IF EXISTS `p_role`;
-- CREATE TABLE p_role(
--     `role_id` VARCHAR(128) NOT NULL COMMENT '角色ID',
--     `role_code` VARCHAR(128) NOT NULL COMMENT '角色编码',
--     `role_name` VARCHAR(64) NOT NULL COMMENT '角色名',
--     `description` VARCHAR(256) COMMENT '描述',
--     `enable_flag` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '是否启用',
--     `del_flag` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
--     `created_time` DATETIME NOT NULL COMMENT '创建时间',
--     `created_user` VARCHAR(64) NOT NULL COMMENT '创建人',
--     `updated_time` DATETIME COMMENT '更新时间',
--     `updated_user` VARCHAR(64) COMMENT '更新人',
--     `created_device` VARCHAR(128) NOT NULL COMMENT '创建设备',
--     `created_device_ip` VARCHAR(64) NOT NULL COMMENT '创建设备IP',
--     `updated_device` VARCHAR(128) COMMENT '更新设备',
--     `updated_device_ip` VARCHAR(64) COMMENT '更新设备IP',
--     PRIMARY KEY (`role_id`)
-- )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '系统角色表';

DROP TABLE IF EXISTS `p_app_history`;
CREATE TABLE p_app_history(
    `id` VARCHAR(64) NOT NULL COMMENT '主键ID',
    `version_code` INTEGER UNSIGNED NOT NULL COMMENT '版本号',
    `version_name` VARCHAR(50) NOT NULL COMMENT '版本名称',
    `muv` INTEGER UNSIGNED NOT NULL DEFAULT 0 COMMENT '强制升级版本号',
    `type` TINYINT(1) NOT NULL COMMENT '版本类型（0:非标件，1:标准件）',
    `description` VARCHAR(1024) NOT NULL COMMENT '描述',
    `app_path` VARCHAR(200) COMMENT 'appPath', -- 版本+PC+标准件
    `release_date` DATETIME COMMENT '发布日期',
    `app_type` VARCHAR(2) NOT NULL COMMENT 'A:安卓,P:PC前端',
    `stable_flag` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否稳定版本（0:否，1:是）',
    `created_time` DATETIME NOT NULL COMMENT '创建时间',
    `created_user` VARCHAR(64) NOT NULL COMMENT '创建人',
    `updated_time` DATETIME COMMENT '更新时间',
    `updated_user` VARCHAR(64) COMMENT '更新人',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '系统版本表';

DROP TABLE IF EXISTS `p_app_error`;
CREATE TABLE p_app_error(
    `id` VARCHAR(64) NOT NULL COMMENT 'id',
    `error_msg` VARCHAR(1024) COMMENT '错误信息',
    `created_time` DATETIME NOT NULL COMMENT '创建时间',
    `created_device` VARCHAR(128) NOT NULL COMMENT '创建设备',
    `created_device_ip` VARCHAR(64) NOT NULL COMMENT '创建设备IP',
    `tenant_id` BIGINT(20) NOT NULL COMMENT '租户ID',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'p_app_error';


DROP TABLE IF EXISTS `p_announcement`;
CREATE TABLE p_announcement(
    `id` VARCHAR(64) NOT NULL COMMENT 'id',
    `content` VARCHAR(256) NOT NULL DEFAULT '' COMMENT 'content',
    `app_type` TINYINT NOT NULL COMMENT '版本类型（1标准件,2非标件,3通用版,4零售版,5代销A）',
    `state` TINYINT NOT NULL DEFAULT 1 COMMENT '发布状态 0未发布 ， 1已发布',
    `created_user` VARCHAR(64) NOT NULL COMMENT '创建人',
    `created_time` DATETIME NOT NULL COMMENT '创建时间',
    `updated_time` DATETIME COMMENT '更新时间',
    `updated_user` VARCHAR(64) COMMENT '更新人',
    `tenant_id` BIGINT(20) UNSIGNED DEFAULT 0 COMMENT '店铺',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '通知';

/*DROP TABLE IF EXISTS `p_tenant_referrer`;
CREATE TABLE p_tenant_referrer(
    `user_code` VARCHAR(64) DEFAULT '' COMMENT '推荐人工号',
    `tenant_username` VARCHAR(50) NOT NULL COMMENT '店铺账号',
    `tenant_id` BIGINT(20) NOT NULL COMMENT '店铺id',
    `tenant_type` TINYINT(1) COMMENT '用户类型：1-标准件， 2-非标件',
    `referrer_type` TINYINT(1) NOT NULL COMMENT '软件获取方式  1 推荐人 2 网络媒体 3 朋友介绍',
    `created_time` DATETIME NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`tenant_username`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '店铺推荐人表';*/

DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE t_employee(
    `id` BIGINT(20) UNSIGNED NOT NULL COMMENT 'id',
    `employee_code` char(6) NOT NULL COMMENT '工号',
    `employee_name` VARCHAR(64) NOT NULL COMMENT '姓名',
    `id_no` VARCHAR(18) COMMENT '身份证号',
    `dept_id` BIGINT(20) COMMENT '部门',
    `gw_id` BIGINT(20) COMMENT '岗位',
    `join_date` DATETIME DEFAULT NULL COMMENT '入职时间',
    `leave_date` DATETIME DEFAULT NULL COMMENT '离职时间',
    `state` tinyint(1) NOT NULL DEFAULT '1' COMMENT '员工状态',
    `others` JSON NOT NULL COMMENT '员工信息',
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '员工信息表';


DROP TABLE IF EXISTS `t_employee_config`;
CREATE TABLE `t_employee_config` (
  `id` bigint(20) unsigned NOT NULL COMMENT 'id',
  `field` VARCHAR(20) NOT NULL COMMENT '字段',
  `field_name` VARCHAR(20) NOT NULL COMMENT '字段名称',
  `field_type` tinyint(4) NOT NULL COMMENT '字段类型',
  `state` tinyint(4) NOT NULL COMMENT '字段状态',
  `candidate_val` VARCHAR(1024) DEFAULT NULL COMMENT '字段值',
  `format` VARCHAR(64) DEFAULT NULL COMMENT '格式',
  PRIMARY KEY (`id`),
  UNIQUE KEY `field` (`field`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工信息字段表';


DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `dept_name` VARCHAR(32) NOT NULL COMMENT '部门名称',
  `parent_id` int(11) NOT NULL,
  `mode` tinyint(1) NOT NULL COMMENT '部门或岗位',
  `dept_path` VARCHAR(255) NOT NULL,
  `parent_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否父节点',
  `enable_flag` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
--   `created_time` datetime NOT NULL COMMENT '创建时间',
--   `created_user` VARCHAR(64) NOT NULL COMMENT '创建人',
--   `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
--   `updated_user` VARCHAR(64) DEFAULT NULL COMMENT '更新人',
--   `created_device` VARCHAR(128) NOT NULL COMMENT '创建设备',
--   `created_device_ip` VARCHAR(64) NOT NULL COMMENT '创建设备IP',
--   `updated_device` VARCHAR(128) DEFAULT NULL COMMENT '更新设备',
--   `updated_device_ip` VARCHAR(64) DEFAULT NULL COMMENT '更新设备IP',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_salary_item`;
CREATE TABLE `t_salary_item` (
  `id` BIGINT(20) NOT NULL,
  `name` VARCHAR(32) NOT NULL COMMENT '薪资名称',
  `type` tinyint(1) NOT NULL,
  `rule` tinyint(1) NOT NULL,
  `default_price` DECIMAL(14,2) COMMENT '薪资',
  `default_money` DECIMAL(14,2) COMMENT '薪资',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_r_employee_salary_item`;
CREATE TABLE `t_r_employee_salary_item` (
  `employee_id` BIGINT(20) NOT NULL,
  `salary_item_id` BIGINT(20) NOT NULL,
  `count` INT(10) COMMENT '进件数量',
  `price` DECIMAL(14,2) COMMENT '计件单价',
  `money` DECIMAL(14,2)  COMMENT '金额',
  UNIQUE KEY `r` (`employee_id`, `salary_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_salary`;
CREATE TABLE `t_salary` (
  `id` BIGINT(20) NOT NULL,
  `month` VARCHAR(7) NOT NULL,
  `dept_id` BIGINT(20) NOT NULL COMMENT '部门',
  `employee_id` BIGINT(20) NOT NULL COMMENT '进件数量',
  `salary` DECIMAL(14,2)  NOT NULL COMMENT '薪水',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `t_employee_salary_item`;
CREATE TABLE `t_employee_salary_item` (
  `id` BIGINT(20) NOT NULL,
  `salary_id` BIGINT(20) NOT NULL,
  `employee_id` BIGINT(20) NOT NULL,
  `salary_item_id` BIGINT(20) NOT NULL,
  `month` VARCHAR(7) NOT NULL,
  `count` INT(10) COMMENT '进件数量',
  `price` DECIMAL(14,2) COMMENT '计件单价',
  `money` DECIMAL(14,2)  NOT NULL COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `p_dict`;
CREATE TABLE `p_dict` (
  `id` BIGINT NOT NULL COMMENT '主键',
  `dict_code` VARCHAR(64) NOT NULL COMMENT '字典编号',
  `dict_name` VARCHAR(64) NOT NULL COMMENT '字典名称',
  `description` VARCHAR(256) DEFAULT NULL COMMENT '描述',
  `enable_flag` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据字典';

-- ----------------------------
-- Table structure for p_dict_info
-- ----------------------------
DROP TABLE IF EXISTS `p_dict_info`;
CREATE TABLE `p_dict_info` (
  `id` BIGINT NOT NULL COMMENT '主键',
  `dict_id` BIGINT NOT NULL COMMENT '主表主键ID',
--   `parent_id` BIGINT DEFAULT NULL COMMENT 'parentId',
  `dict_name` VARCHAR(64) DEFAULT NULL COMMENT '字典名称',
  `dict_value` BIGINT NOT NULL COMMENT '字典值',
  `px` int(2) NOT NULL DEFAULT '-1' COMMENT '序号',
  `description` VARCHAR(256) DEFAULT NULL COMMENT '描述',
  `enable_flag` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='数据字典-子表';
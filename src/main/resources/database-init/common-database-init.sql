CREATE DATABASE IF NOT EXISTS ylkj_common DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE ams_common;

-- 一、表结构创建语句
-- 用户表
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `login_name` varchar(100) DEFAULT NULL COMMENT '登录名,唯一',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '昵称',
  `admin` tinyint(1) DEFAULT '0' COMMENT '是否超管,1是,0否',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile_phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `status` int(11) DEFAULT NULL COMMENT '状态,-1-删除、 0-禁用， 1-正常',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人，如果是同步的数据，则默认为-1L',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

CREATE TABLE `sys_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键,自增',
  `identity` varchar(100) DEFAULT NULL COMMENT '资源身份标识',
  `name` varchar(100) DEFAULT NULL COMMENT '资源名称',
  `url` varchar(200) DEFAULT NULL COMMENT '资源请求地址',
  `parent_id` int(11) DEFAULT NULL COMMENT '父资源主键ID,若顶级则为0',
  `parent_ids` varchar(200) DEFAULT NULL COMMENT '资源树,形如:0/7,若顶级为0',
  `icon` varchar(200) DEFAULT NULL COMMENT '资源图标',
  `weight` int(11) DEFAULT NULL COMMENT '权重',
  `resource_type` int(11) DEFAULT NULL COMMENT '资源类型(暂无使用)',
  `is_resource` tinyint(1) DEFAULT NULL COMMENT '是否是资源(是否可点击触发请求)：0否；1是',
  `is_show` tinyint(1) DEFAULT NULL COMMENT '是否显示：0不显示；1显示',
  `status` int(11) DEFAULT NULL COMMENT '状态,-1-删除、 0-禁用， 1-正常',
  `creator_id` int(11) DEFAULT NULL COMMENT '创建人，如果是同步的数据，则默认为-1L',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_id` int(11) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(200) DEFAULT NULL COMMENT '资源描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';


-- 国家行政区划表（进行初始化<取高德地图数据>）
CREATE TABLE `zone`  (
  `code` int(11) NOT NULL COMMENT '标识',
  `parent_code` int(11) NOT NULL COMMENT '父级行政区划,第一级为:0',
  `level` int(11) NOT NULL COMMENT '级别',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `shortname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简称'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '国家行政区划表';


-- 二、数据初始化
-- 测试数据
INSERT INTO `sys_user` (`id`, `login_name`, `password`, `nick_name`, `admin`, `email`, `mobile_phone`, `status`, `creator_id`, `create_time`, `update_id`, `update_time`, `remarks`) VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '1', NULL, NULL, '1', '-1', '2019-06-16 22:35:26', NULL, NULL, NULL);
INSERT INTO `sys_user` (`id`, `login_name`, `password`, `nick_name`, `admin`, `email`, `mobile_phone`, `status`, `creator_id`, `create_time`, `update_id`, `update_time`, `remarks`) VALUES ('2', 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', '张三', '0', NULL, NULL, '1', '-1', '2019-06-16 22:35:26', NULL, NULL, NULL);
INSERT INTO `sys_user` (`id`, `login_name`, `password`, `nick_name`, `admin`, `email`, `mobile_phone`, `status`, `creator_id`, `create_time`, `update_id`, `update_time`, `remarks`) VALUES ('3', 'lisi', 'e10adc3949ba59abbe56e057f20f883e', '李四', '0', NULL, NULL, '1', '-1', '2019-06-16 22:35:26', NULL, NULL, NULL);
INSERT INTO `sys_user` (`id`, `login_name`, `password`, `nick_name`, `admin`, `email`, `mobile_phone`, `status`, `creator_id`, `create_time`, `update_id`, `update_time`, `remarks`) VALUES ('4', 'wangwu', 'e10adc3949ba59abbe56e057f20f883e', '王五', '0', NULL, NULL, '1', '-1', '2019-06-16 22:35:26', NULL, NULL, NULL);

-- 正式数据



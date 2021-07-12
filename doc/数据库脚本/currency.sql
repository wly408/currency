/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : currency

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2021-07-03 17:02:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` varchar(36) NOT NULL COMMENT '主键',
  `menu_name` varchar(100) NOT NULL COMMENT '菜单名称',
  `parent_menu_id` varchar(36) NOT NULL COMMENT '父节点ID',
  `menu_sort` int(4) DEFAULT NULL COMMENT '排序',
  `menu_sn` varchar(32) DEFAULT NULL COMMENT '菜单编码',
  `menu_url` varchar(500) NOT NULL COMMENT '菜单地址',
  `menu_icon` varchar(500) DEFAULT NULL COMMENT '菜单图标',
  `menu_type` varchar(2) NOT NULL COMMENT '菜单类型：1菜单，2：按钮',
  `tenant_id` varchar(32) NOT NULL COMMENT '租户ID',
  `status_cd` varchar(2) DEFAULT NULL COMMENT ' 状态',
  `create_user` varchar(32) NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` varchar(64) NOT NULL COMMENT '角色ID',
  `role_name` varchar(100) NOT NULL COMMENT '角色名称',
  `role_code` varchar(100) NOT NULL COMMENT '角色编码',
  `tenant_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '租户ID',
  `status_cd` varchar(2) NOT NULL COMMENT '状态',
  `create_user` varchar(36) NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(36) DEFAULT NULL COMMENT '修改人',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` varchar(36) DEFAULT NULL COMMENT '主键',
  `role_id` varchar(36) DEFAULT NULL COMMENT '角色ID',
  `menu_id` varchar(36) DEFAULT NULL COMMENT '菜单ID',
  `tenant_id` varchar(36) NOT NULL COMMENT '租户ID',
  `status_cd` varchar(2) DEFAULT NULL COMMENT ' 状态',
  `create_user` varchar(36) NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(36) DEFAULT NULL COMMENT '修改人',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

-- ----------------------------
-- Table structure for sys_tenant
-- ----------------------------
DROP TABLE IF EXISTS `sys_tenant`;
CREATE TABLE `sys_tenant` (
  `tenant_id` varchar(36) NOT NULL COMMENT '租户ID',
  `tenant_code` varchar(100) NOT NULL COMMENT '租户编码',
  `tenant_name` varchar(200) NOT NULL COMMENT '租户名称',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `create_user` varchar(36) NOT NULL COMMENT '创建人',
  `status_cd` varchar(2) NOT NULL COMMENT '状态',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(36) DEFAULT NULL COMMENT '修改人',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='租户表';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` varchar(36) NOT NULL COMMENT '主键',
  `user_code` varchar(32) NOT NULL COMMENT '用户编码',
  `user_name` varchar(100) NOT NULL COMMENT '用户名称',
  `user_photo` varchar(500) DEFAULT NULL COMMENT '用户头像',
  `realname` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `tenant_id` varchar(36) NOT NULL COMMENT '所属租户',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `id_num` varchar(100) DEFAULT NULL COMMENT '证件号码',
  `id_type` varchar(10) DEFAULT NULL COMMENT '证件类型',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `password` varchar(200) NOT NULL COMMENT '密码',
  `salt` varchar(32) DEFAULT NULL COMMENT '盐值',
  `user_type` varchar(2) NOT NULL COMMENT '用户类型：0：通用1：管理员2：用户',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `status_cd` varchar(2) NOT NULL COMMENT '状态',
  `create_user` varchar(36) NOT NULL COMMENT '创建人',
  `update_user` varchar(36) DEFAULT NULL COMMENT '修改人',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `role_id` varchar(36) NOT NULL COMMENT '角色ID',
  `user_id` varchar(36) NOT NULL COMMENT '用户ID',
  `tenant_id` varchar(36) NOT NULL COMMENT '租户ID',
  `status_cd` varchar(2) DEFAULT NULL COMMENT ' 状态',
  `create_user` varchar(36) NOT NULL COMMENT '创建人',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_user` varchar(36) DEFAULT NULL COMMENT '修改人',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

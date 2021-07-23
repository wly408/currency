/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50627
Source Host           : 127.0.0.1:3306
Source Database       : currency

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2021-07-23 17:03:54
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
-- Records of sys_menu
-- ----------------------------

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
-- Records of sys_role
-- ----------------------------

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
-- Records of sys_role_menu
-- ----------------------------

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
-- Records of sys_tenant
-- ----------------------------
INSERT INTO `sys_tenant` VALUES ('-1', 'sys', 'sys', '2021-07-14 19:47:47', '', '1', '2021-07-13 19:47:44', '1', null);

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
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('0054dc723288402a853415bc587ede13', 'admin5', 'ly', null, null, '-1', null, null, null, null, '$2a$10$i8UgAaN1f4CnKZzFEZo6i.erkuAxY0CYzm.qjoyEkpfKLRKzutg/W', null, '1', '2021-07-12 15:12:27', '1', '-1', null, null, null);
INSERT INTO `sys_user` VALUES ('15d2f567a6cd4e47ae4911c3acb9b89d', 'admin3', 'ly', null, null, '-1', null, null, null, null, '$2a$10$cnIIw.mrf9hftNROvikt6.X0p.ppws0rivXVhex0u3o7bFmtYx2Y.', null, '1', '2021-07-12 15:12:21', '1', '-1', null, null, null);
INSERT INTO `sys_user` VALUES ('24387560d7344f65b9535f10ee140841', 'admin10', 'ly', null, null, '-1', null, null, null, null, '$2a$10$3KS1RxCvLuFKHGSz9jps1u9deOPJKK32DmrxZQFYFKD2f9BmrjOyq', null, '1', '2021-07-12 15:12:46', '1', '-1', null, null, null);
INSERT INTO `sys_user` VALUES ('32c45d199e1b45388f18613fd57374f0', 'admin13', 'ly', null, null, '-1', null, null, null, null, '$2a$10$pA1k0mv7KXHDZXhOD4hAn.sdjZ0xIavt9m3tzQoS2xHBOT28IZ3zm', null, '1', '2021-07-12 17:28:33', '1', '-1', null, null, null);
INSERT INTO `sys_user` VALUES ('4fb47ecac81b421c8eafd0a6a0b67cff', 'admin', 'ly', null, null, '-1', null, null, null, null, '$2a$10$regTLSlk2u.feDcI6LvfPuBzHXn6SaWigiCe0NAIGKi4Wqf/o5HUe', null, '1', '2021-07-09 15:53:35', '1', '1', null, null, null);
INSERT INTO `sys_user` VALUES ('792dc882a2384a3cb8bd6ceeafcde582', 'admin9', 'ly', null, null, '-1', null, null, null, null, '$2a$10$922EOFH2dhZrtftV9As.LeF1Dme6wEdAWpuzagbxrjhCNLx0WHN0W', null, '1', '2021-07-12 15:12:42', '1', '-1', null, null, null);
INSERT INTO `sys_user` VALUES ('9bdc01ffb78f4aee97ed7b9c1337ec40', 'admin7', 'ly', null, null, '-1', null, null, null, null, '$2a$10$WEP6eCd/pRrg5KfHeW0ohelz7jmO27MWCsBWQY3lI4xL6xGpYG82a', null, '1', '2021-07-12 15:12:35', '1', '-1', null, null, null);
INSERT INTO `sys_user` VALUES ('bb2ec74340fd43ddb0ed23812a7ff1e3', 'admin4', 'ly', null, null, '-1', null, null, null, null, '$2a$10$MkBpIKR0olieStTbsa6Zuul3XD6CJwHuoY9m2fr.wEVtfe8OCX2Ym', null, '1', '2021-07-12 15:12:24', '1', '-1', null, null, null);
INSERT INTO `sys_user` VALUES ('bf04a255958c408fa99c7ebdd6780403', 'admin6', 'ly', null, null, '-1', null, null, null, null, '$2a$10$y5Vh7DidNix1xxQ6PNTefenNOEsCaAYXMJ9HT8EU1eRYdrhXHS.wC', null, '1', '2021-07-12 15:12:31', '1', '-1', null, null, null);
INSERT INTO `sys_user` VALUES ('c4fa666e700242048f6f7c4330f192a4', 'admin2', 'ly', null, null, '-1', null, null, null, null, '$2a$10$qEuOprAH0Tj12MsLgq2nHOJ4dMKcSMAKOLiF46RHdhtWmhefBkPc6', null, '1', '2021-07-09 17:32:14', '1', '-1', null, null, null);
INSERT INTO `sys_user` VALUES ('c7df580376c4406cbd4fd37b62338820', 'admin11', 'ly', null, null, '-1', null, null, null, null, '$2a$10$fXGWTad5m02VisudouK/Gu1nDr0U26xXkjn2gPWPcFv3H7Q7eMGOW', null, '1', '2021-07-12 17:25:56', '1', '-1', null, null, null);
INSERT INTO `sys_user` VALUES ('d86f84ce80454f4d8460613d668e7a6a', 'admin8', 'ly', null, null, '-1', null, null, null, null, '$2a$10$ZNU4hTEDI.Iar2MZBMFx/ukMApiwCdrpGoVukX5sgQfNiby9dO6P2', null, '1', '2021-07-12 15:12:38', '1', '-1', null, null, null);
INSERT INTO `sys_user` VALUES ('e702dad726784f888379a99e2e72c5d0', 'admin14', 'ly', null, null, '-1', null, null, null, null, '$2a$10$kwLCVftZCEObT0y3By/NE.GCGbHRlHz2777tKGJd5zVx8xt5Fo0Ky', null, '1', '2021-07-12 17:43:22', '1', '-1', null, null, null);

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

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

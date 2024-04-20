/*
Navicat MySQL Data Transfer

Source Server         : 120.48.30.252_12345
Source Server Version : 50735
Source Host           : 120.48.30.252:12345
Source Database       : hospital

Target Server Type    : MYSQL
Target Server Version : 50735
File Encoding         : 65001

Date: 2024-04-20 22:04:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_banner
-- ----------------------------
DROP TABLE IF EXISTS `sys_banner`;
CREATE TABLE `sys_banner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'banner编号',
  `name` varchar(255) NOT NULL COMMENT 'banner标题',
  `picture` text NOT NULL COMMENT '分类简介',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '修改者',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='资讯表';

-- ----------------------------
-- Records of sys_banner
-- ----------------------------
INSERT INTO `sys_banner` VALUES ('1', 'banner1', '/628c0c46-af30-4d23-90a2-cdcc95eb8a4b_1.png', '2024-04-20 20:42:16', null, null, null, null);
INSERT INTO `sys_banner` VALUES ('2', 'banner2', '/8a9a6482-b9b3-4e3e-8b8a-8bd44294a42c_2.png', '2024-04-20 20:45:05', null, null, null, null);

-- ----------------------------
-- Table structure for sys_category
-- ----------------------------
DROP TABLE IF EXISTS `sys_category`;
CREATE TABLE `sys_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `name` varchar(255) NOT NULL COMMENT '分类名',
  `info` varchar(255) NOT NULL COMMENT '分类简介',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '修改者',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='分类表';

-- ----------------------------
-- Records of sys_category
-- ----------------------------
INSERT INTO `sys_category` VALUES ('28', '入职体检', '针对职场相关人员入职', '2024-04-17 11:52:19', 'admin', null, null, null);
INSERT INTO `sys_category` VALUES ('29', '常规体检B套餐', '日常常规体检', '2024-04-17 14:06:43', 'admin', null, null, null);

-- ----------------------------
-- Table structure for sys_institution
-- ----------------------------
DROP TABLE IF EXISTS `sys_institution`;
CREATE TABLE `sys_institution` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) NOT NULL COMMENT '机构名称',
  `address` varchar(255) NOT NULL COMMENT '机构地址',
  `phone` varchar(20) DEFAULT NULL COMMENT '机构电话',
  `picture` varchar(255) DEFAULT NULL COMMENT '机构封面',
  `status` char(1) CHARACTER SET utf8 DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '修改者',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COMMENT='机构表';

-- ----------------------------
-- Records of sys_institution
-- ----------------------------
INSERT INTO `sys_institution` VALUES ('1', '好未来机构', '好未来xxx号', '13111111111', '/2bd4ca8f-5b71-4dfa-985a-64009a513e54_体检B.png', '0', null, null, '2024-04-19 15:04:53', null, '蜂箱内容');

-- ----------------------------
-- Table structure for sys_order
-- ----------------------------
DROP TABLE IF EXISTS `sys_order`;
CREATE TABLE `sys_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_id` varchar(255) NOT NULL COMMENT '订单id',
  `institution_id` bigint(20) NOT NULL COMMENT '预约机构id',
  `subscribe_id` bigint(20) DEFAULT NULL COMMENT '预约id',
  `package_id` bigint(20) DEFAULT NULL COMMENT '套餐id',
  `user_id` bigint(20) NOT NULL COMMENT '订单用户id',
  `report_url` text COMMENT '生成报告地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '修改者',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Records of sys_order
-- ----------------------------
INSERT INTO `sys_order` VALUES ('1', 'bfb1b315-c85b-4b70-abe4-7db1ebd05def', '1', '3', '31', '27', '/613ff841-d3d1-4f51-8d51-d31fc3478949_template.docx', '2024-04-20 16:28:21', null, '2024-04-20 20:00:06', null, null);

-- ----------------------------
-- Table structure for sys_package
-- ----------------------------
DROP TABLE IF EXISTS `sys_package`;
CREATE TABLE `sys_package` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '套餐ID',
  `name` varchar(100) NOT NULL COMMENT '套餐名称',
  `price` varchar(255) NOT NULL COMMENT '套餐价格',
  `checked` varchar(255) NOT NULL COMMENT '检查项',
  `category_id` varchar(255) NOT NULL COMMENT '所属分类',
  `picture` varchar(255) NOT NULL COMMENT '描述图片',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '修改者',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='分类表';

-- ----------------------------
-- Records of sys_package
-- ----------------------------
INSERT INTO `sys_package` VALUES ('31', '测试体检套餐', '222', '404,335,662,366,18445,1084,372,14345,1204,681,675,1', '28', '/355f4aae-f2a2-411d-9964-739ce58b1c2d_封面.png', '2024-04-19 14:01:45', null, '2024-04-19 14:12:28', null, '上传');

-- ----------------------------
-- Table structure for sys_promotion
-- ----------------------------
DROP TABLE IF EXISTS `sys_promotion`;
CREATE TABLE `sys_promotion` (
  `id` int(11) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` varbinary(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_promotion
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '后台管理员');
INSERT INTO `sys_role` VALUES ('2', '小程序用户');

-- ----------------------------
-- Table structure for sys_subscribe
-- ----------------------------
DROP TABLE IF EXISTS `sys_subscribe`;
CREATE TABLE `sys_subscribe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `phone` varchar(255) NOT NULL COMMENT '预约手机号',
  `address` varchar(255) NOT NULL COMMENT '预约机构地址',
  `institution_phone` varchar(20) DEFAULT NULL COMMENT '预约机构电话',
  `package_id` varchar(255) DEFAULT NULL COMMENT '套餐id',
  `package_name` varchar(255) DEFAULT NULL COMMENT '套餐名称',
  `user_id` bigint(20) NOT NULL COMMENT '预约用户id',
  `user_name` varchar(20) DEFAULT NULL COMMENT '预约支付人',
  `order_status` char(1) CHARACTER SET utf8 DEFAULT '0' COMMENT '是否支付',
  `report_url` varchar(255) DEFAULT NULL COMMENT '报告下载地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(100) DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '修改者',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='预约表';

-- ----------------------------
-- Records of sys_subscribe
-- ----------------------------
INSERT INTO `sys_subscribe` VALUES ('2', '13333333333', '好未来xxx号', '13111111111', '31', '测试体检套餐', '27', '小程序用户', '2', null, '2024-04-20 10:11:23', null, '2024-04-20 11:46:25', null, null);
INSERT INTO `sys_subscribe` VALUES ('3', '13333333333', '好未来xxx号', '13111111111', '31', '测试体检套餐', '27', '小程序用户', '1', null, '2024-04-20 11:18:32', null, null, null, null);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户昵称',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '盐',
  `status` char(1) CHARACTER SET utf8 DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改者',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('26', '昵称', 'admin', '123456', null, '0', '2024-04-17 17:47:01', null, null, null, null);
INSERT INTO `sys_user` VALUES ('27', '小程序用户', '13333333333', '123456', null, '0', '2024-04-17 17:51:21', null, null, null, null);

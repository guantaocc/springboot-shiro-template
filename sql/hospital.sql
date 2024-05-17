/*
Navicat MySQL Data Transfer

Source Server         : 测试
Source Server Version : 50735
Source Host           : 120.48.30.252:12345
Source Database       : hospital

Target Server Type    : MYSQL
Target Server Version : 50735
File Encoding         : 65001

Date: 2024-04-24 18:29:50
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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='分类表';

-- ----------------------------
-- Records of sys_category
-- ----------------------------
INSERT INTO `sys_category` VALUES ('31', '入职体检', '职工入职体检', '2024-04-24 17:28:11', null, null, null, null);
INSERT INTO `sys_category` VALUES ('32', '专项体检', '专项体检', '2024-04-24 17:29:25', null, null, null, null);
INSERT INTO `sys_category` VALUES ('33', '父母体检', '体检', '2024-04-24 17:29:39', null, null, null, null);
INSERT INTO `sys_category` VALUES ('34', '婚孕体检', '婚孕', '2024-04-24 17:29:52', null, null, null, null);

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
INSERT INTO `sys_institution` VALUES ('1', '好未来机构', '江苏省未来街道好未来xxx号100', '13311231311', '/fd65b119-0b86-423f-bd8e-5cfffd6f44d9_3x.png', '0', null, null, '2024-04-24 18:25:55', null, '每间医疗中心耗资数千万，配置国际主流品牌的医疗设备。凭借集团多年累积的企业健康管理经验和管理团队，自研健康管理信息化平台，引进行业专业人才，提供深度体检、早癌筛查、慢病管理、一站式全科门诊（全科、口腔科、医疗美容科、康复医学科、成人预防接种门诊……）、绿色就医通道等一站式全方位健康管理解决方案。');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Records of sys_order
-- ----------------------------
INSERT INTO `sys_order` VALUES ('2', '9061fbe5-727e-4885-9dbf-0ca2e996ff91', '1', '5', '34', '27', null, '2024-04-24 17:52:51', null, null, null, null);
INSERT INTO `sys_order` VALUES ('3', 'a572bc5e-99e3-446a-baa3-591e6ea1e3ec', '1', '7', '35', '28', '/37d46927-ba99-49aa-aecf-de1c3e44ea5b_template.docx', '2024-04-24 17:59:20', null, '2024-04-24 18:16:57', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='套餐表';

-- ----------------------------
-- Records of sys_package
-- ----------------------------
INSERT INTO `sys_package` VALUES ('32', '职工A套餐', '120', '2066,366,1084,450,611,681,360,407,358,465,1543,404,335,2067,1,867,371,675', '31', '/306da36b-4967-49f2-9a80-9ddef2255962_封面.png', '2024-04-24 17:30:46', null, null, null, 'xx');
INSERT INTO `sys_package` VALUES ('33', '职工B套餐', '222.33', '404,335,371,407,1084,366,1543,2067', '31', '/d185c2ec-974a-46ae-8cc0-ad5733ffb1c1_x.png', '2024-04-24 17:32:59', null, null, null, 'xx');
INSERT INTO `sys_package` VALUES ('34', '职工C套餐', '567.00', '404,1543,1084,366,611,675,371', '31', '/bd5f46d0-ff29-4813-9ecc-dd79565a9c81_2x.png', '2024-04-24 17:33:35', null, null, null, null);
INSERT INTO `sys_package` VALUES ('35', '婚A套餐', '123.00', '404,335,591,1903,450,14345,465,360,976,441,14286,358,341', '34', '/d1db2853-6f11-4e17-ac89-0138638285d1_3x.png', '2024-04-24 17:34:48', null, null, null, null);
INSERT INTO `sys_package` VALUES ('36', '老人体检A', '1000', '404,662,1903,335,2066,366,1084,450,14345,465,360,675,1,867', '33', '/3b2b18db-effd-4b2a-aaf9-5f584cb7e269_老人.jpg', '2024-04-24 17:41:09', null, null, null, 'xx');
INSERT INTO `sys_package` VALUES ('37', '老人体检B', '333.00', '404,1543,366,18445,611,1084,14345,675,681,360,372,976,14286,371,1204', '33', '/cd603df7-836f-4807-8283-ed8433c879f4_封面.png', '2024-04-24 17:42:36', null, null, null, null);
INSERT INTO `sys_package` VALUES ('38', '职工E', '444', '404,450,14345,372,1,591,3,1903,662,2066,1084,681,976,360,358,1204', '31', '/b8a64808-d562-4508-ba5d-67a893495dbf_3x.png', '2024-04-24 17:43:14', null, null, null, 'x');
INSERT INTO `sys_package` VALUES ('39', '专项A', '111', '404,804,663,450,372,1084,366,2067,3,1903,867,1,371,14286,360,976,407,441', '32', '/7b062f12-6621-4a2d-80e4-5dc2d65e59de_2x.png', '2024-04-24 17:49:21', null, null, null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='预约表';

-- ----------------------------
-- Records of sys_subscribe
-- ----------------------------
INSERT INTO `sys_subscribe` VALUES ('5', '13333333333', '好未来xxx号', '13111111111', '34', '职工C套餐', '27', '小程序用户', '1', null, '2024-04-24 17:52:24', null, null, null, null);
INSERT INTO `sys_subscribe` VALUES ('6', '13333333333', '好未来xxx号', '13111111111', '33', '职工B套餐', '27', '小程序用户', '0', null, '2024-04-24 17:53:35', null, null, null, null);
INSERT INTO `sys_subscribe` VALUES ('7', '14444444444', '好未来xxx号', '13111111111', '35', '婚A套餐', '28', '小程序用户', '1', null, '2024-04-24 17:59:00', null, null, null, null);
INSERT INTO `sys_subscribe` VALUES ('8', '14444444444', '好未来xxx号', '13111111111', '36', '老人体检A', '28', '小程序用户', '2', null, '2024-04-24 18:00:15', null, '2024-04-24 18:00:25', null, null);
INSERT INTO `sys_subscribe` VALUES ('9', '14444444444', '好未来xxx号', '13111111111', '32', '职工A套餐', '28', '大猫', '0', null, '2024-04-24 18:14:16', null, null, null, null);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户昵称',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '随机数',
  `role_id` bigint(20) DEFAULT '1' COMMENT '角色ID',
  `status` char(1) CHARACTER SET utf8 DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改者',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('26', '超级管理员', 'admin', '123456', null, '1', '0', '2024-04-17 17:47:01', null, null, null, null);
INSERT INTO `sys_user` VALUES ('27', '小程序用户', '13333333333', '123456', null, '2', '0', '2024-04-17 17:51:21', null, null, null, null);
INSERT INTO `sys_user` VALUES ('28', '大猫', '14444444444', '123456', null, '2', '0', '2024-04-24 16:07:07', null, '2024-04-24 18:02:39', null, null);

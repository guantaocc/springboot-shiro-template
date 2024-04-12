/*
 Navicat Premium Data Transfer

 Source Server         : 47.94.101.118
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : 47.94.101.118:3306
 Source Schema         : shiro-redis-base

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 12/04/2024 09:23:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '盐',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改者',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '管理员', 'admin', 'baa38bf9dc3ed7699c56253998bdd22d', '123456', '0', '2024-03-27 15:40:30', 'admin', '2024-03-27 15:40:30', '1', '管理员');

-- ----------------------------
-- View structure for VT
-- ----------------------------
DROP VIEW IF EXISTS `VT`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `VT` AS select `sys_user`.`user_id` AS `user_id`,`sys_user`.`nick_name` AS `nick_name`,`sys_user`.`user_name` AS `user_name`,`sys_user`.`password` AS `password`,`sys_user`.`salt` AS `salt`,`sys_user`.`status` AS `status`,`sys_user`.`create_time` AS `create_time`,`sys_user`.`create_by` AS `create_by`,`sys_user`.`update_time` AS `update_time`,`sys_user`.`update_by` AS `update_by`,`sys_user`.`remark` AS `remark` from `sys_user` limit 1;

-- ----------------------------
-- View structure for VT2
-- ----------------------------
DROP VIEW IF EXISTS `VT2`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `VT2` AS select `sys_user`.`user_id` AS `user_id`,`sys_user`.`nick_name` AS `nick_name`,`sys_user`.`user_name` AS `user_name`,`sys_user`.`password` AS `password`,`sys_user`.`salt` AS `salt`,`sys_user`.`status` AS `status`,`sys_user`.`create_time` AS `create_time`,`sys_user`.`create_by` AS `create_by`,`sys_user`.`update_time` AS `update_time`,`sys_user`.`update_by` AS `update_by`,`sys_user`.`remark` AS `remark` from `sys_user` limit 2;

-- ----------------------------
-- Function structure for ftest
-- ----------------------------
DROP FUNCTION IF EXISTS `ftest`;
delimiter ;;
CREATE FUNCTION `ftest`(id INT)
 RETURNS varchar(100) CHARSET utf8mb4 COLLATE utf8mb4_general_ci
  READS SQL DATA 
BEGIN
    DECLARE result VARCHAR(100);

    -- 检查是否存在匹配的用户记录
    SELECT user_name INTO result FROM sys_user WHERE user_id = id LIMIT 1;

    -- 如果没有找到匹配的用户记录，返回适当的错误信息
    IF result IS NULL THEN
        SET result = 'User not found';
    END IF;

    RETURN result;
END
;;
delimiter ;

-- ----------------------------
-- Function structure for ftest2
-- ----------------------------
DROP FUNCTION IF EXISTS `ftest2`;
delimiter ;;
CREATE FUNCTION `ftest2`(id int)
 RETURNS varchar(100) CHARSET utf8mb4 COLLATE utf8mb4_general_ci
  READS SQL DATA 
begin
	declare result varchar(100);
	select user_name into result from sys_user where user_id = id;
	return result;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for ptest
-- ----------------------------
DROP PROCEDURE IF EXISTS `ptest`;
delimiter ;;
CREATE PROCEDURE `ptest`()
BEGIN
	#Routine body goes here...
	declare de_name varchar(10) default '';
	set de_name = 'test';
	select de_name;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for ptest2
-- ----------------------------
DROP PROCEDURE IF EXISTS `ptest2`;
delimiter ;;
CREATE PROCEDURE `ptest2`(in s_status char(1), out s_count int)
begin
select count(*) into s_count from sys_user where `status` = s_status;
select s_count;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for ptest3
-- ----------------------------
DROP PROCEDURE IF EXISTS `ptest3`;
delimiter ;;
CREATE PROCEDURE `ptest3`(in `day` int)
begin
	if `day` = 0 then
	select '星期天';
	elseif `day` = 1 then
	select '星期一';
	else
	select '哈哈哈不告诉你';
	end if;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for ptest4
-- ----------------------------
DROP PROCEDURE IF EXISTS `ptest4`;
delimiter ;;
CREATE PROCEDURE `ptest4`(inout number int)
begin
	select number;
end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;

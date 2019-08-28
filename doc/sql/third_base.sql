/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50626
 Source Host           : 47.100.33.198:3306
 Source Schema         : third_base

 Target Server Type    : MySQL
 Target Server Version : 50626
 File Encoding         : 65001

 Date: 07/05/2019 16:23:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for th_bank_config
-- ----------------------------
DROP TABLE IF EXISTS `th_bank_config`;
CREATE TABLE `th_bank_config`  (
  `bank_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '银行id',
  `appid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `card_type` int(4) NOT NULL COMMENT '卡类型(1借记卡2信用卡)',
  `bank_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '银行名称',
  `bank_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '银行代码',
  `bg_color` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行卡背景颜色代码',
  `logo_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '银行logo图标url',
  `bg_logo_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '银行logo背景图标url',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `if_enable` tinyint(1) NOT NULL COMMENT '0无效1有效',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `single_limit` decimal(10, 2) NOT NULL COMMENT '银行单笔限额',
  `daily_limit` decimal(10, 2) NULL DEFAULT NULL COMMENT '银行每日限额',
  `monthly_limit` decimal(10, 2) NULL DEFAULT NULL COMMENT '银行每月限额',
  PRIMARY KEY (`bank_id`) USING BTREE,
  UNIQUE INDEX `idx_b_code`(`bank_code`) USING BTREE,
  INDEX `idx_appid`(`appid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '银行卡配置表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for th_bank_match
-- ----------------------------
DROP TABLE IF EXISTS `th_bank_match`;
CREATE TABLE `th_bank_match`  (
  `match_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '银行编号',
  `appid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `card_no_prefix` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '银行号',
  `bank_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '银行名称',
  `bank_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '使用编号',
  PRIMARY KEY (`match_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '银行字典表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for th_center_config
-- ----------------------------
DROP TABLE IF EXISTS `th_center_config`;
CREATE TABLE `th_center_config`  (
  `center_config_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '信贷中心配置id',
  `appid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `appsecret` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用secret',
  `base_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用服务器地址',
  `environment` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用环境参数',
  `token_key` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用令牌缓存key前缀',
  `sub_token_key` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用子令牌缓存key前缀',
  PRIMARY KEY (`center_config_id`) USING BTREE,
  UNIQUE INDEX `idx_appid`(`appid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '信贷中心相关配置' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for th_param_config
-- ----------------------------
DROP TABLE IF EXISTS `th_param_config`;
CREATE TABLE `th_param_config`  (
  `param_config_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '参数配置id',
  `appid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '来源应用id',
  `param_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '参数key',
  `param_value` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '参数value',
  `param_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数描述',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`param_config_id`) USING BTREE,
  INDEX `idx_appid`(`appid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参数配置表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for th_product_config
-- ----------------------------
DROP TABLE IF EXISTS `th_product_config`;
CREATE TABLE `th_product_config`  (
  `product_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品id',
  `appid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用id',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品编号',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品名称',
  `price` decimal(10, 2) NOT NULL COMMENT '产品价格',
  `day_rate` decimal(10, 4) NOT NULL COMMENT '日利率',
  `pre_price` decimal(10, 2) NOT NULL COMMENT '前置产品价格',
  `periods` int(2) NULL DEFAULT NULL COMMENT '期数',
  `cycle` int(4) NOT NULL COMMENT '每期天数',
  `app_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用标识',
  `application_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用名称',
  `ext` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `ext2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  `type` int(2) NOT NULL COMMENT '产品类型(0正常1免息)',
  PRIMARY KEY (`product_id`) USING BTREE,
  INDEX `idx_p_code`(`code`) USING BTREE,
  INDEX `idx_appid`(`appid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '应用产品配置表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for th_user_info
-- ----------------------------
DROP TABLE IF EXISTS `th_user_info`;
CREATE TABLE `th_user_info`  (
  `user_info_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `identity_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证号码',
  `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `mobile` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭住址',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '来源订单id',
  `other_order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '来源第三方订单id',
  `app_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '来源应用标识',
  `appid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '来源应用id',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `ext` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `ext2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`user_info_id`) USING BTREE,
  UNIQUE INDEX `idx_cn_id`(`identity_no`, `mobile`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户来源信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for th_user_quarantine
-- ----------------------------
DROP TABLE IF EXISTS `th_user_quarantine`;
CREATE TABLE `th_user_quarantine`  (
  `user_quarantine_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '隔离用户id',
  `mobile` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号码',
  `identity_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '身份证号码',
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名称',
  `phone_id_number_md5` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机身份证md5',
  `appid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '来源应用id',
  `app_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '来源应用标识',
  `order_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '来源订单id',
  `other_order_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '来源第三方订单id',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '原因',
  `quarantine_end_time` datetime(0) NOT NULL COMMENT '隔离结束日期',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_quarantine_id`) USING BTREE,
  INDEX `idx_appid`(`appid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '隔离用户表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;

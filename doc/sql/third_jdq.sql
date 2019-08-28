/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50626
 Source Host           : 47.100.33.198:3306
 Source Schema         : third_jdq

 Target Server Type    : MySQL
 Target Server Version : 50626
 File Encoding         : 65001

 Date: 07/05/2019 16:23:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for jdq_device_info
-- ----------------------------
DROP TABLE IF EXISTS `jdq_device_info`;
CREATE TABLE `jdq_device_info`  (
  `device_info_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备信息主键id',
  `user_info_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '关联用户id',
  `jdq_order_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '借点钱订单id',
  `device_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备号',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `device_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备类型如：IOS或Android',
  `device_model` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机设备型号',
  `device_os` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设备操作系统版本',
  `openudid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'iOS的openudid',
  `jailbreak_flag` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IOS 是否越狱 (0 : 没有越狱；1 ：已经越狱)',
  `root_flag` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Android是否root (0 : 没有root；1 ：已经root)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`device_info_id`) USING BTREE,
  INDEX `Index_user_info_id`(`user_info_id`) USING BTREE,
  INDEX `Index_jdq_order_id`(`jdq_order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设备信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for jdq_operator_bills
-- ----------------------------
DROP TABLE IF EXISTS `jdq_operator_bills`;
CREATE TABLE `jdq_operator_bills`  (
  `operator_bills_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `operator_data_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '运营商原始数据关联id',
  `user_info_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户基础信息关联id',
  `bill_month` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账单月，格式：yyyy-MM',
  `bill_start_date` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账期起始日期，格式：yyyy-MM-dd',
  `bill_end_date` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账期结束日期，格式：yyyy-MM-dd',
  `base_fee` int(11) NULL DEFAULT NULL COMMENT '本机号码套餐及固定费',
  `extra_service_fee` int(11) NULL DEFAULT NULL COMMENT '增值业务费',
  `voice_fee` int(11) NULL DEFAULT NULL COMMENT '语音费',
  `sms_fee` int(11) NULL DEFAULT NULL COMMENT '短彩信费',
  `web_fee` int(11) NULL DEFAULT NULL COMMENT '网络流量费',
  `extra_fee` int(11) NULL DEFAULT NULL COMMENT '其它费用',
  `total_fee` int(11) NULL DEFAULT NULL COMMENT '本月总费用',
  `discount` int(11) NULL DEFAULT NULL COMMENT '优惠费',
  `extra_discount` int(11) NULL DEFAULT NULL COMMENT '其它优惠',
  `actual_fee` int(11) NULL DEFAULT NULL COMMENT '个人实际费用',
  `paid_fee` int(11) NULL DEFAULT NULL COMMENT '本期已付费用',
  `unpaid_fee` int(11) NULL DEFAULT NULL COMMENT '本期未付费用',
  `point` int(11) NULL DEFAULT NULL COMMENT '本期可用积分',
  `last_point` int(11) NULL DEFAULT NULL COMMENT '上期可用积分',
  `related_mobiles` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '本手机关联号码, 多个手机号以逗号分隔',
  `notes` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`operator_bills_id`) USING BTREE,
  INDEX `Index_user_info_id`(`user_info_id`) USING BTREE,
  INDEX `Index_operator_data_id`(`operator_data_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '运营商-账单信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for jdq_operator_calls
-- ----------------------------
DROP TABLE IF EXISTS `jdq_operator_calls`;
CREATE TABLE `jdq_operator_calls`  (
  `operator_calls_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `operator_data_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '运营商原始数据关联id',
  `user_info_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户基础信息关联id',
  `bill_month` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '语音详情月份，格式 yyyy-MM',
  `total_size` int(11) NULL DEFAULT NULL COMMENT '记录总数',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`operator_calls_id`) USING BTREE,
  INDEX `Index_user_info_id`(`user_info_id`) USING BTREE,
  INDEX `Index_operator_data_id`(`operator_data_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '运营商-语音详情' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for jdq_operator_calls_items
-- ----------------------------
DROP TABLE IF EXISTS `jdq_operator_calls_items`;
CREATE TABLE `jdq_operator_calls_items`  (
  `operator_calls_items_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `operator_calls_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '运营商-语音详情关联id',
  `details_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详情唯一标识',
  `call_time` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通话时间，格式：yyyy-MM-dd HH:mm:ss',
  `peer_number` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '对方通话号码',
  `location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通话地(自己的)',
  `location_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通话地类型. e.g.省内漫游',
  `duration` int(11) NULL DEFAULT NULL COMMENT '通话时长(单位:秒)',
  `dial_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '呼叫类型. DIAL-主叫; DIALED-被叫',
  `fee` int(11) NULL DEFAULT NULL COMMENT '通话费(单位:分)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`operator_calls_items_id`) USING BTREE,
  INDEX `Index_operator_calls_id`(`operator_calls_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '运营商-语音详情明细' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for jdq_operator_data
-- ----------------------------
DROP TABLE IF EXISTS `jdq_operator_data`;
CREATE TABLE `jdq_operator_data`  (
  `operator_data_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `user_info_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户基础关联id',
  `jdq_order_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '借点钱订单id',
  `mobile` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '本机号码',
  `name` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机主姓名',
  `idcard` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机主身份证',
  `carrier` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '本机号码归属运营商标识',
  `province` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '本机号码归属省份',
  `city` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '本机号码归属城市',
  `open_time` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '入网时间，格式：yyyy-MM-dd',
  `level` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户星级',
  `package_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '本机号码当前套餐名称',
  `state` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '本机号码状态',
  `reliability` int(11) NULL DEFAULT NULL COMMENT '本机实名状态',
  `available_balance` int(11) NULL DEFAULT NULL COMMENT '本机号码当前可用余额（单位: 分）',
  `last_modify_time` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据获取时间，格式: yyyy-MM-dd HH:mm:ss',
  `code` int(11) NULL DEFAULT NULL COMMENT '状态码',
  `message_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`operator_data_id`) USING BTREE,
  INDEX `Index_user_info_id`(`user_info_id`) USING BTREE,
  INDEX `Index_jdq_order_id`(`jdq_order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '运营商原始数据' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for jdq_operator_families
-- ----------------------------
DROP TABLE IF EXISTS `jdq_operator_families`;
CREATE TABLE `jdq_operator_families`  (
  `operator_families_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `operator_data_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '运营商原始数据关联id',
  `user_info_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户基础信息关联id',
  `family_num` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '亲情网编号',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`operator_families_id`) USING BTREE,
  INDEX `Index_user_info_id`(`user_info_id`) USING BTREE,
  INDEX `Index_operator_data_id`(`operator_data_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '运营商-亲情网' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for jdq_operator_families_items
-- ----------------------------
DROP TABLE IF EXISTS `jdq_operator_families_items`;
CREATE TABLE `jdq_operator_families_items`  (
  `operator_families_items_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `operator_families_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '运营商-亲情网关联id',
  `long_number` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '亲情网手机号码',
  `short_number` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '短号',
  `member_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '成员类型. MASTER-家长, MEMBER-成员',
  `join_date` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '加入日期, 格式yyyy-MM-dd	',
  `expire_date` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '失效日期, 格式yyyy-MM-dd',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`operator_families_items_id`) USING BTREE,
  INDEX `Index_operator_families_id`(`operator_families_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '运营商-亲情网成员信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for jdq_operator_month_info
-- ----------------------------
DROP TABLE IF EXISTS `jdq_operator_month_info`;
CREATE TABLE `jdq_operator_month_info`  (
  `operator_month_info_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `operator_data_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '运营商原始数据关联id',
  `user_info_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户基础信息关联id',
  `phone_no` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户手机号码',
  `month_count` int(11) NULL DEFAULT NULL COMMENT '有通话记录月份数',
  `miss_month_count` int(11) NULL DEFAULT NULL COMMENT '通话记录获取失败月份数',
  `no_call_month` int(11) NULL DEFAULT NULL COMMENT '无通话记录月份数',
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '调用接口传入user_id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`operator_month_info_id`) USING BTREE,
  INDEX `Index_user_info_id`(`user_info_id`) USING BTREE,
  INDEX `Index_operator_data_id`(`operator_data_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '运营商-语言月份信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for jdq_operator_month_list
-- ----------------------------
DROP TABLE IF EXISTS `jdq_operator_month_list`;
CREATE TABLE `jdq_operator_month_list`  (
  `operator_month_list_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `operator_month_info_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '运营商语音月份信息关联id',
  `month` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通话记录月份',
  `count` int(11) NULL DEFAULT NULL COMMENT '记录数',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`operator_month_list_id`) USING BTREE,
  INDEX `Index_operator_month_info_id`(`operator_month_info_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '运营商-通话记录月份' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for jdq_operator_nets
-- ----------------------------
DROP TABLE IF EXISTS `jdq_operator_nets`;
CREATE TABLE `jdq_operator_nets`  (
  `operator_nets_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `operator_data_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '运营商原始数据关联id',
  `user_info_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户基础信息关联id',
  `total_size` int(11) NULL DEFAULT NULL COMMENT '记录总数',
  `bill_month` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详情月份，格式 yyyy-MM',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`operator_nets_id`) USING BTREE,
  INDEX `Index_user_info_id`(`user_info_id`) USING BTREE,
  INDEX `Index_operator_data_id`(`operator_data_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '运营商-流量详情' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for jdq_operator_nets_items
-- ----------------------------
DROP TABLE IF EXISTS `jdq_operator_nets_items`;
CREATE TABLE `jdq_operator_nets_items`  (
  `operator_nets_items_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `operator_nets_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '运营商-流量详情关联id',
  `details_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详情标识',
  `used_time` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流量使用时间',
  `duration` int(11) NULL DEFAULT NULL COMMENT '流量使用时长(单位:秒)',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流量使用地点',
  `subflow` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流量使用量，单位:KB',
  `net_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '网络类型',
  `service_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务名称',
  `fee` int(11) NULL DEFAULT NULL COMMENT '通信费(单位:分)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`operator_nets_items_id`) USING BTREE,
  INDEX `Index_operator_nets_id`(`operator_nets_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '运营商-流量详情明细' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for jdq_operator_packages
-- ----------------------------
DROP TABLE IF EXISTS `jdq_operator_packages`;
CREATE TABLE `jdq_operator_packages`  (
  `operator_packages_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `operator_data_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '运营商原始数据关联id',
  `user_info_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户基础信息关联id',
  `bill_start_date` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账单起始日, 格式为yyyy-MM-dd',
  `bill_end_date` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账单结束日, 格式为yyyy-MM-dd',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`operator_packages_id`) USING BTREE,
  INDEX `Index_user_info_id`(`user_info_id`) USING BTREE,
  INDEX `Index_operator_data_id`(`operator_data_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '运营商-套餐' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for jdq_operator_packages_items
-- ----------------------------
DROP TABLE IF EXISTS `jdq_operator_packages_items`;
CREATE TABLE `jdq_operator_packages_items`  (
  `operator_packages_items_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `operator_packages_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '运营商-套餐关联id',
  `item` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '套餐项目名称',
  `total` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '套餐项目总量',
  `used` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '套餐项目已使用量',
  `unit` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '套餐项目单位：语音-分; 流量-KB; 短/彩信-条',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`operator_packages_items_id`) USING BTREE,
  INDEX `Index_operator_packages_id`(`operator_packages_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '运营商-套餐明细' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for jdq_operator_recharges
-- ----------------------------
DROP TABLE IF EXISTS `jdq_operator_recharges`;
CREATE TABLE `jdq_operator_recharges`  (
  `operator_recharges_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `operator_data_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '运营商原始数据关联id',
  `user_info_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户基础信息关联id',
  `details_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详情标识，标识唯一一条记录',
  `recharge_time` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '充值时间，格式：yyyy-MM-dd HH:mm:ss',
  `amount` int(11) NULL DEFAULT NULL COMMENT '充值金额(单位: 分)',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '充值方式. e.g. 现金',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`operator_recharges_id`) USING BTREE,
  INDEX `Index_user_info_id`(`user_info_id`) USING BTREE,
  INDEX `Index_operator_data_id`(`operator_data_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '运营商-充值记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for jdq_operator_smses
-- ----------------------------
DROP TABLE IF EXISTS `jdq_operator_smses`;
CREATE TABLE `jdq_operator_smses`  (
  `operator_smses_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `operator_data_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '运营商原始数据关联id',
  `user_info_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户基础信息关联id',
  `bill_month` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详情月份，格式 yyyy-MM',
  `total_size` int(11) NULL DEFAULT NULL COMMENT '记录总数',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`operator_smses_id`) USING BTREE,
  INDEX `Index_user_info_id`(`user_info_id`) USING BTREE,
  INDEX `Index_operator_data_id`(`operator_data_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '运营商-短信详情' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for jdq_operator_smses_items
-- ----------------------------
DROP TABLE IF EXISTS `jdq_operator_smses_items`;
CREATE TABLE `jdq_operator_smses_items`  (
  `operator_smses_items_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `operator_smses_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '短信详情关联id',
  `details_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详情唯一标识',
  `msg_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收/发短信时间',
  `peer_number` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '对方号码',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '通话地(自己的)',
  `send_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'SEND-发送; RECEIVE-收取',
  `msg_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'SMS-短信; MMS-彩信',
  `service_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务名称. e.g. 点对点(网内)',
  `fee` int(11) NULL DEFAULT NULL COMMENT '通话费(单位:分)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`operator_smses_items_id`) USING BTREE,
  INDEX `Index_operator_smses_id`(`operator_smses_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '运营商-短信详情明细' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for jdq_user
-- ----------------------------
DROP TABLE IF EXISTS `jdq_user`;
CREATE TABLE `jdq_user`  (
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户来源id',
  `id_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '身份证',
  `mask_id_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '掩码身份证',
  `mask_phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '掩码手机号码',
  `mask_user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '掩码姓名',
  `user_state` int(11) NULL DEFAULT NULL COMMENT '用户状态 0:新用户;1:老用户',
  `channel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '来源渠道',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `Index_id_number`(`id_number`) USING BTREE,
  INDEX `Index_mask_id_number`(`mask_id_number`) USING BTREE,
  INDEX `Index_mask_user_name`(`mask_user_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户来源' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for jdq_user_address_book
-- ----------------------------
DROP TABLE IF EXISTS `jdq_user_address_book`;
CREATE TABLE `jdq_user_address_book`  (
  `address_book_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `user_info_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户基础信息关联id',
  `jdq_order_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '借点钱订单id',
  `name` varchar(510) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `mobile` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码,多个手机号以英文逗号分隔',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`address_book_id`) USING BTREE,
  INDEX `Index_user_info_id`(`user_info_id`) USING BTREE,
  INDEX `Index_jdq_order_id`(`jdq_order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户通讯录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for jdq_user_company_info
-- ----------------------------
DROP TABLE IF EXISTS `jdq_user_company_info`;
CREATE TABLE `jdq_user_company_info`  (
  `user_company_info_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `user_info_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '关联用户id',
  `jdq_order_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '借点钱订单id',
  `company_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司详细地址，格式\"上海,上海市,杨浦区|政府路\"',
  `hiredate` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '当前公司入职日期，格式\"2017年7月12日\"',
  `company_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工作单位全称',
  `company_tel` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工作单位电话',
  `industry` int(11) NULL DEFAULT NULL COMMENT '公司所属行业code码（1=批发/零售 2=制造业 3=金融/保险/证券 4=住宿/餐饮/旅游 5=商业服务/娱乐/艺术/体育 6=计算机/互联网 7=通讯电子 8=建筑/房地产 9=法律/咨询 10=卫生/教育/社会服务 11=公共事业/社会团体 12=生物/制药 13=广告/媒体 14=能源 15=贸易 16=交通运输/仓储/物流 17=农/林/牧/渔 18=其他）',
  `company_work_year` int(11) NULL DEFAULT NULL COMMENT '当前公司工作年限code码（1=1-5个月 2=6-11个月 3=1-3年 4=4-7年 5=7年以上）',
  `company_type` int(11) NULL DEFAULT NULL COMMENT '公司类型code码（1=政府或企事业单位 2=国企央企 3=外资企业 4=上市公司 5=普通民营企业 6=个体工商户 7=其他）',
  `work_profession` int(11) NULL DEFAULT NULL COMMENT '工作职业code码（1=农牧业 2=木材/森林业 3=矿业/采石业 4=交通运输业 5=餐旅业 6=建筑工程业 7=制造业 8=娱乐业 9=文教 10=金融业 11=服务业 12=治安人员 13=军人 14=其他）',
  `company_city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司所在城市（如：苏州市）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_company_info_id`) USING BTREE,
  INDEX `Index_user_info_id`(`user_info_id`) USING BTREE,
  INDEX `Index_jdq_order_id`(`jdq_order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户公司信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for jdq_user_contact
-- ----------------------------
DROP TABLE IF EXISTS `jdq_user_contact`;
CREATE TABLE `jdq_user_contact`  (
  `user_contact_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `user_info_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '关联用户id',
  `jdq_order_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '借点钱订单id',
  `relation` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第一联系人关系',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第一联系人姓名',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第一联系人手机号码',
  `name_spare` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第二联系人姓名',
  `mobile_spare` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第二联系人手机号码',
  `relation_spare` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '第二联系人关系',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_contact_id`) USING BTREE,
  INDEX `Index_user_info_id`(`user_info_id`) USING BTREE,
  INDEX `Index_jdq_order_id`(`jdq_order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '紧急联系人' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for jdq_user_info
-- ----------------------------
DROP TABLE IF EXISTS `jdq_user_info`;
CREATE TABLE `jdq_user_info`  (
  `user_info_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `jdq_order_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '借点钱订单id',
  `local_order_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '本地订单号',
  `mobile` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `id_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证',
  `phone_id_number_md5` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机身份证MD5',
  `role` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证类型 （1=工薪族、2=企业主、3=自由职业者）',
  `birth` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '出生年月 yyyy-MM-dd',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `nation` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名族',
  `id_positive` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证正面图片url',
  `id_negative` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证反面图片url',
  `face` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '人脸照片url',
  `hand_id_photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手持身份证照片URL',
  `marry` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '婚姻状况',
  `educate` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文化程度',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `living_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '居住详细地址',
  `id_card_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '户籍地址',
  `push_phase_state` int(11) NULL DEFAULT NULL COMMENT '一推二推消息状态 0:初始化;1:一推中;2;一推成功;3:一推失败4:二推中:5:二推成功;6:二推失败',
  `push_risk_state` int(11) NULL DEFAULT NULL COMMENT '推送风控状态 0:初始化;1:推送中;2:审核失败;3:审核成功',
  `push_operator_state` int(11) NULL DEFAULT NULL COMMENT '运营商数据推送状态 0:初始化:1:推送中;2:审核失败:3审核成功',
  `last_reject_date` datetime(0) NULL DEFAULT NULL COMMENT '上一次审批被拒时间',
  `id_signing_authority` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证签发机构',
  `id_start_date` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证有效起始日期(格式：2016-09-01',
  `id_expiry_date` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证有效截止日期(格式：2036-09-01或长期',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_info_id`) USING BTREE,
  INDEX `Index_jdq_order_id`(`jdq_order_id`) USING BTREE,
  INDEX `Index_local_order_id`(`local_order_id`) USING BTREE,
  INDEX `Index_mobile`(`mobile`) USING BTREE,
  INDEX `Index_id_number`(`id_number`) USING BTREE,
  INDEX `Index_push_phase_state`(`push_phase_state`) USING BTREE,
  INDEX `Index_push_risk_state`(`push_risk_state`) USING BTREE,
  INDEX `Index_push_operator_state`(`push_operator_state`) USING BTREE,
  INDEX `Index_user_info_id`(`user_info_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for jdq_user_loan_info
-- ----------------------------
DROP TABLE IF EXISTS `jdq_user_loan_info`;
CREATE TABLE `jdq_user_loan_info`  (
  `loan_info_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `user_info_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户基础信息关联id',
  `jdq_order_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '借点钱订单号',
  `loan_amount` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '贷款金额',
  `loan_term` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '贷款期限，若为单期按天还款产品即传天数，多期产品即传期数',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`loan_info_id`) USING BTREE,
  INDEX `Index_user_info_id`(`user_info_id`) USING BTREE,
  INDEX `Index_jdq_order_id`(`jdq_order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '贷款信息' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;

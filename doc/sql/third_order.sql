/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50626
 Source Host           : 47.100.33.198:3306
 Source Schema         : third_order

 Target Server Type    : MySQL
 Target Server Version : 50626
 File Encoding         : 65001

 Date: 07/05/2019 16:24:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_actual_loan
-- ----------------------------
DROP TABLE IF EXISTS `order_actual_loan`;
CREATE TABLE `order_actual_loan`  (
  `loan_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '借款记录id',
  `credit_use_uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授信uuid',
  `loan_amount` decimal(10, 2) NOT NULL COMMENT '放款金额',
  `ext` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `ext2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  `appid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `finance_order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账务系统订单id',
  `apply_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '借款订单id',
  PRIMARY KEY (`loan_id`) USING BTREE,
  INDEX `idx_ao_id`(`appid`, `order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单放款表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for order_actual_loan_r
-- ----------------------------
DROP TABLE IF EXISTS `order_actual_loan_r`;
CREATE TABLE `order_actual_loan_r`  (
  `loan_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录id',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `appid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `if_success` tinyint(1) NULL DEFAULT NULL COMMENT '是否成功',
  `code` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态码',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原因',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ext` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `ext2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`loan_id`) USING BTREE,
  INDEX `idx_ao_id`(`appid`, `order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单放款记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for order_audit_r
-- ----------------------------
DROP TABLE IF EXISTS `order_audit_r`;
CREATE TABLE `order_audit_r`  (
  `audit_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录id',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `appid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `if_success` tinyint(1) NULL DEFAULT NULL COMMENT '是否成功',
  `code` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态码',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原因',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ext` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `ext2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`audit_id`) USING BTREE,
  INDEX `idx_ao_id`(`appid`, `order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单审核记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for order_bank_bind
-- ----------------------------
DROP TABLE IF EXISTS `order_bank_bind`;
CREATE TABLE `order_bank_bind`  (
  `bind_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '绑定记录id',
  `identity_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证号码',
  `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `bank_card_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '银行卡号码',
  `bank_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '银行代码',
  `bank_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '银行名称',
  `bank_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行卡开户地区',
  `reserved_mobile` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行预留手机号码',
  `sign_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '支付标记',
  `card_type` int(4) NOT NULL COMMENT '卡类型(1借记卡2信用卡)',
  `ext` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `ext2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  `appid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `if_valid` tinyint(1) NOT NULL COMMENT '是否有效',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`bind_id`) USING BTREE,
  INDEX `idx_ao_id`(`appid`, `order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单绑卡表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for order_bank_bind_r
-- ----------------------------
DROP TABLE IF EXISTS `order_bank_bind_r`;
CREATE TABLE `order_bank_bind_r`  (
  `bind_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录id',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `appid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `if_success` tinyint(1) NULL DEFAULT NULL COMMENT '是否成功',
  `code` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态码',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原因',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ext` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `ext2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`bind_id`) USING BTREE,
  INDEX `idx_ao_id`(`appid`, `order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单绑卡记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for order_borrow_apply
-- ----------------------------
DROP TABLE IF EXISTS `order_borrow_apply`;
CREATE TABLE `order_borrow_apply`  (
  `apply_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '记录id',
  `finance_order_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账务系统订单id',
  `periods` int(2) NULL DEFAULT NULL COMMENT '期数',
  `cycle` int(4) NOT NULL COMMENT '每期天数',
  `product_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '产品编号',
  `pre_borrow_amount` decimal(10, 2) NOT NULL COMMENT '前置借款金额',
  `borrow_amount` decimal(10, 2) NOT NULL COMMENT '借款金额',
  `credit_use_uuid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授信uuid',
  `ext` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `ext2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  `appid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用id',
  `order_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单id',
  `if_valid` tinyint(1) NOT NULL COMMENT '是否有效',
  `repayment_plan_time` datetime(0) NOT NULL COMMENT '预计还款时间',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`apply_id`) USING BTREE,
  INDEX `idx_ao_id`(`appid`, `order_id`) USING BTREE,
  INDEX `idx_r_p_time`(`repayment_plan_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单借款申请记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for order_credit
-- ----------------------------
DROP TABLE IF EXISTS `order_credit`;
CREATE TABLE `order_credit`  (
  `credit_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '授信记录id',
  `credit_uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '授信申请订单uuid',
  `credit_use_uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授信uuid',
  `product_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品编号',
  `contract_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '合同编号',
  `credit_amount` decimal(10, 2) NOT NULL COMMENT '授信金额',
  `ext` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `ext2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  `appid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `if_valid` tinyint(1) NOT NULL COMMENT '是否有效',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`credit_id`) USING BTREE,
  INDEX `idx_ao_id`(`appid`, `order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单授信表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for order_credit_r
-- ----------------------------
DROP TABLE IF EXISTS `order_credit_r`;
CREATE TABLE `order_credit_r`  (
  `credit_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录id',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `appid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `if_success` tinyint(1) NULL DEFAULT NULL COMMENT '是否成功',
  `code` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态码',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原因',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ext` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `ext2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`credit_id`) USING BTREE,
  INDEX `idx_ao_id`(`appid`, `order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单授信记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for order_credit_rule
-- ----------------------------
DROP TABLE IF EXISTS `order_credit_rule`;
CREATE TABLE `order_credit_rule`  (
  `rule_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规则主键id',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `appid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `credit_line` decimal(10, 2) NOT NULL COMMENT '选择金额',
  `rule` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '放款规则参数',
  `scene_rule` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '场景规则参数',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `ext` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `ext2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`rule_id`) USING BTREE,
  UNIQUE INDEX `idx_ao_id`(`appid`, `order_id`, `credit_line`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单授信规则表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for order_loan
-- ----------------------------
DROP TABLE IF EXISTS `order_loan`;
CREATE TABLE `order_loan`  (
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `other_order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '第三方订单id',
  `status` int(4) NOT NULL COMMENT '订单状态（0初始化10待审核20审核中30审核失败40待绑卡50绑卡中60待前置签约70待后置签约80待放款90放款中100放款失败110待还款120还款中130已结清140逾期1000订单取消）',
  `repayment_status` int(4) NOT NULL COMMENT '还款状态（0初始化10待还款20已结清30已逾期40逾期结清）',
  `if_finish` tinyint(1) NOT NULL COMMENT '订单是否完结',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ext` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `ext2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  `appid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  PRIMARY KEY (`order_id`) USING BTREE,
  UNIQUE INDEX `idx_o_o`(`other_order_id`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE,
  INDEX `idx_r_status`(`repayment_status`) USING BTREE,
  INDEX `idx_c_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '借还款订单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for order_pre_credit
-- ----------------------------
DROP TABLE IF EXISTS `order_pre_credit`;
CREATE TABLE `order_pre_credit`  (
  `pre_credit_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '前置授信id',
  `credit_uuid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '授信申请订单uuid',
  `product_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品编号',
  `contract_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '合同编号',
  `credit_amount` decimal(10, 2) NOT NULL COMMENT '授信金额',
  `ext` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `ext2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  `appid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `if_valid` tinyint(1) NOT NULL COMMENT '是否有效',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`pre_credit_id`) USING BTREE,
  INDEX `idx_ao_id`(`appid`, `order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单前置授信表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for order_pre_credit_r
-- ----------------------------
DROP TABLE IF EXISTS `order_pre_credit_r`;
CREATE TABLE `order_pre_credit_r`  (
  `pre_credit_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录id',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `appid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `if_success` tinyint(1) NULL DEFAULT NULL COMMENT '是否成功',
  `code` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态码',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原因',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ext` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `ext2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`pre_credit_id`) USING BTREE,
  INDEX `idx_ao_id`(`appid`, `order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单前置授信记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for order_repayment
-- ----------------------------
DROP TABLE IF EXISTS `order_repayment`;
CREATE TABLE `order_repayment`  (
  `repayment_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '还款记录id',
  `if_overdue` tinyint(1) NOT NULL COMMENT '是不是逾期还款',
  `ext` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `ext2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  `appid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '应用id',
  `order_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单id',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `pre_repayment_amount` decimal(10, 2) NOT NULL COMMENT '前置还款金额',
  `repayment_amount` decimal(10, 2) NOT NULL COMMENT '正常还款金额',
  `total_repayment_amount` decimal(10, 2) NOT NULL COMMENT '还款总金额',
  `credit_use_uuid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授信uuid',
  `finance_order_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账务系统订单id',
  `apply_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '借款订单id',
  PRIMARY KEY (`repayment_id`) USING BTREE,
  INDEX `idx_ao_id`(`appid`, `order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单还款表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for order_repayment_r
-- ----------------------------
DROP TABLE IF EXISTS `order_repayment_r`;
CREATE TABLE `order_repayment_r`  (
  `repayment_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录id',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `appid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `if_success` tinyint(1) NULL DEFAULT NULL COMMENT '是否成功',
  `code` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态码',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原因',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `ext` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `ext2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`repayment_id`) USING BTREE,
  INDEX `idx_ao_id`(`appid`, `order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单还款记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for order_user
-- ----------------------------
DROP TABLE IF EXISTS `order_user`;
CREATE TABLE `order_user`  (
  `audit_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '记录id',
  `identity_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证号码',
  `mobile` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
  `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭住址',
  `ext` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `ext2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  `appid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `if_valid` tinyint(1) NOT NULL COMMENT '是否有效',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`audit_id`) USING BTREE,
  INDEX `idx_ao_id`(`appid`, `order_id`) USING BTREE,
  INDEX `idx_identity_no`(`identity_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单用户表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;

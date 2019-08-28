-- **************助力钱包系统相关配置***********************
-- 先建库

CREATE DATABASE `third_zlqb` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';

-- third_zlqb
-- ----------------------------
-- Table structure for zlqb_user_info
-- ----------------------------
DROP TABLE IF EXISTS `zlqb_user_info`;
CREATE TABLE `zlqb_user_info`  (
  `order_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '订单id',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名称',
  `age` int(4) NOT NULL DEFAULT 0 COMMENT '用户年龄',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '邮箱',
  `id_card` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '身份证号码',
  `address` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '身份证地址',
  `permanent_address` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '居住证地址',
  `loan_terms` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '借款期限',
  `degree` int(4) NOT NULL DEFAULT 0 COMMENT '学历 1 高中 2 大专 3 本科 4 硕士以上',
  `profesion` int(4) NOT NULL DEFAULT 0 COMMENT '学历 1 工薪 2 企业业主 3 学生 4 自由职业',
  `nature` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '民族',
  `issued_by` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '签发机关',
  `valid_date` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '有效期',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机号',
  `zm_score` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '芝麻分',
  `loan_amount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '预借款金额 ',
  `apply_date` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '借款期限',
  `house_address` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '居住地址',
  `live_city` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '居住城市',
  `debt` int(4) NOT NULL DEFAULT 0 COMMENT '负债情况 1 为负债 0  不负债',
  `debt_amount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '负债金额 ',
  `pay_back_source_option` int(4) NOT NULL DEFAULT 0 COMMENT '还款来源 0 工资 1经营收入 2 投资收入 3 房租收入 4 其它',
  `loan_purpose` int(4) NOT NULL DEFAULT 0 COMMENT '1.购物, 2.装修,3.旅游, 4.买车, 5.教育, 6.结婚, 7.医疗, 8.其他',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `front_file` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '身份证正面',
  `back_file` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '身份证另一面',
  `nature_file` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手持身份证图片',
  `face_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '人脸图片',
  `ext1` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '扩展字段1',
  `ext2` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '扩展字段2',
  PRIMARY KEY (`order_no`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '助力钱包用户信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for zlqb_order_review
-- ----------------------------
DROP TABLE IF EXISTS `zlqb_order_review`;
CREATE TABLE `zlqb_order_review`  (
  `order_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '平台订单号',
  `approve_amount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '审批金额,单位元',
  `total_principal` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '总本金,单位元',
  `loan_period` int(10) NOT NULL DEFAULT 0 COMMENT '借款期数',
  `loan_terms` int(10) NOT NULL DEFAULT 0 COMMENT '借款期限',
  `approve_date` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '审批时间',
  `status` int(2) NOT NULL DEFAULT 0 COMMENT '订单状态 0初始化10待审核20审核中30拒绝 40 无 50审核通过 待签约 60 审核通过 已签约 70 待开户 80 无 90 审核通过 待放款 100 放款失败 110 无 120 已放款 还款中 130 结清 140 逾期中  1000 签约失败 ）',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `modify_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `ext1` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`order_no`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '助力钱包订单审核表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for zlqb_order_link
-- ----------------------------
DROP TABLE IF EXISTS `zlqb_order_link`;
CREATE TABLE `zlqb_order_link`  (
  `link_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单环节id',
  `order_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单id',
  `old_status` int(2) NOT NULL COMMENT '旧状态',
  `new_status` int(2) NOT NULL COMMENT '新状态',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `ext1` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`link_id`) USING BTREE,
  INDEX `idx_order_no`(`order_no`) USING BTREE,
  INDEX `idx_no_status`(`new_status`, `old_status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '助力钱包订单环节记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for zlqb_order
-- ----------------------------
DROP TABLE IF EXISTS `zlqb_order`;
CREATE TABLE `zlqb_order`  (
  `order_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单id',
  `order_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '交互系统订单id',
  `status` int(2) NOT NULL COMMENT '订单状态（0初始化10数据时限去除20基本数据落库30推送审核40审核通过50审核不通过100放款失败130正常结算1000订单取消）',
  `if_finish` tinyint(1) NOT NULL COMMENT '是否处理结束',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `ext1` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  `is_sign` int(4) NOT NULL DEFAULT 0 COMMENT '0 代表 未签约  1 代表 已签约',
  PRIMARY KEY (`order_no`) USING BTREE,
  UNIQUE INDEX `idx_order_id`(`order_id`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '助力钱包订单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for zlqb_linkman
-- ----------------------------
DROP TABLE IF EXISTS `zlqb_linkman`;
CREATE TABLE `zlqb_linkman`  (
  `link_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '联系人id',
  `order_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '订单id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '姓名',
  `mobile` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机号',
  `type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '联系人类型 1（配偶、兄弟\r\n姐妹、父母） 2亲戚、同事、朋友、同学',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `ext1` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`link_id`) USING BTREE,
  INDEX `idx_order_no`(`order_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '助力钱包紧急联系人表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for zlqb_check_r
-- ----------------------------
DROP TABLE IF EXISTS `zlqb_check_r`;
CREATE TABLE `zlqb_check_r`  (
  `check_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '校验id',
  `id_card` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '身份证号',
  `can_loan` int(2) NOT NULL COMMENT '是否可以借款0 否 1 是',
  `stock` int(2) NOT NULL COMMENT '是否存量用户0 否 1 是',
  `reject_reason` int(2) NULL DEFAULT NULL COMMENT 'null,1黑名单2在贷3被拒记录4其它',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述信息',
  `ext1` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`check_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '助力钱包撞库记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for zlqb_bandcard_info
-- ----------------------------
DROP TABLE IF EXISTS `zlqb_bandcard_info`;
CREATE TABLE `zlqb_bandcard_info`  (
  `bind_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '绑卡id',
  `order_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '订单id',
  `id_card` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '身份证号码',
  `bank_card_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '银行卡号',
  `create_time` bigint(20) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `ext1` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`bind_id`) USING BTREE,
  INDEX `idx_id_card`(`id_card`) USING BTREE,
  INDEX `idx_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '助力钱包绑卡成功记录' ROW_FORMAT = Compact;

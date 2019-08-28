
-- 新增三方融易推库
CREATE DATABASE `third_ryt` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';

-- 在third_ryt 新增
CREATE TABLE `ryt_user_info`  (
  `order_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单id',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名称',
  `gender` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别',
  `id_card` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '身份证号码',
  `id_card_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '身份证地址',
  `race` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '民族',
  `issued_by` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '签发机关',
  `valid_date` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '有效期',
  `user_mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `province` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '省',
  `city` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '城市',
  `district` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '区',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '详细地址',
  `money_usage` int(4) NOT NULL COMMENT '借款用途 1、短期周转 2、购物 3、装修 4、买房 5、买车 6、旅游 7、医疗 8、教育 9、还款 10、为他人借款 11、结婚 12、投资经营 13、信用卡还款 14、购买家电 15、其他耐用消费品 16、垫付保费 17、付房租 18、日常生活消费 19、支付员工工资 20、购买货物/原材料/设备 21、其他',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `id_card_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '身份证正面',
  `id_card_pic_the` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '身份证另一面',
  `id_card_pic_hand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手持身份证图片',
  `face_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '人脸图片',
  `face_pic_other` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '人脸图片扩展',
  `os_version` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'os版本',
  `ext1` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`order_no`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '融易推用户信息表' ROW_FORMAT = Compact;


CREATE TABLE `ryt_order_link`  (
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '融易推订单环节记录表' ROW_FORMAT = Compact;


CREATE TABLE `ryt_order`  (
  `order_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单id',
  `order_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '交互系统订单id',
  `status` int(2) NOT NULL COMMENT '订单状态（0初始化10数据时限去除20基本数据落库30待审核40审核通过50审核不通过60绑卡成功1000订单取消）',
  `if_finish` tinyint(1) NOT NULL COMMENT '是否处理结束',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `loan_amount` int(11) NULL DEFAULT NULL COMMENT '意向借款金额（单位是分）',
  `term_unit` int(2) NULL DEFAULT NULL COMMENT '期限单位 （1：日 2：月）',
  `loan_term` int(2) NULL DEFAULT NULL COMMENT '借款期限',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `ext1` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`order_no`) USING BTREE,
  INDEX `idx_status`(`status`) USING BTREE,
  UNIQUE INDEX `idx_order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '融易推订单表' ROW_FORMAT = Compact;

CREATE TABLE `ryt_linkman`  (
  `link_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '联系人id',
  `order_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `mobile` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '联系人类型 1、父亲2、母亲3、兄弟姐妹4、子女5、配偶6、其它亲属7、朋友8、同学9、同事',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `ext1` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`link_id`) USING BTREE,
  INDEX `idx_order_no`(`order_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '融易推紧急联系人表' ROW_FORMAT = Compact;



-- 修改 third_base
ALTER TABLE `th_bank_config` 
DROP INDEX `idx_b_code`,
ADD INDEX `idx_b_code`(`bank_code`) USING BTREE;

ALTER TABLE `th_bank_match` 
ADD INDEX `idx_appid`(`appid`),
ADD INDEX `idx_prefix`(`card_no_prefix`);

ALTER TABLE `th_product_config` 
MODIFY COLUMN `ext` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段(现已改为渠道信息)' AFTER `application_name`;

ALTER TABLE `th_product_config` 
MODIFY COLUMN `ext2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '扩展字段2(现已改为审批有效期期限天)' AFTER `ext`;




-- ******************************数据相关**********************************
UPDATE th_product_config SET ext='JDQ',ext2='15',application_name='贷款大师-蚂蚁闪电借',name='借点钱' WHERE appid='o6NAAJqaxgAsBqYK';

INSERT INTO `th_product_config` VALUES ('2', 'AtofAdgKRX6o8mth', 'PRODUCT_SDJ2', '2019-06-10 14:31:00', NULL, '融易推', 1000.00, 0.2400, 324.00, 1, 15, '40001', '融易帮帮-蚂蚁闪电借', 'RYT', '15', 0);

INSERT INTO `th_center_config` VALUES ('2', 'AtofAdgKRX6o8mth', 'pPcQreJBdvmM6v6fkH9GAbChq9l3CbUy', 'https://gt.symy666.vip', 'ryt', 'COMMON:CACHE:TOKEN:AtofAdgKRX6o8mth', 'COMMON:CACHE:TOKEN:SUB:AtofAdgKRX6o8mth');

INSERT INTO `th_bank_config` VALUES ('AtofAdgKRX6o8mth-1', 'AtofAdgKRX6o8mth', 1, '工商银行', 'ICBC', NULL, 'https://pinganedai.vip/htmls/appv3/banklogo/40x40/00dcfba270294767a0534f7c2261db67.png', 'https://pinganedai.vip/htmls/appv3/banklogo/80x80/00dcfba270294767a0534f7c2261db67.png', '单笔限额5万元，单日限额5万元，单月限额100万元', 1, '2019-03-15 16:40:25', 50000.00, 50000.00, 1000000.00);
INSERT INTO `th_bank_config` VALUES ('AtofAdgKRX6o8mth-10', 'AtofAdgKRX6o8mth', 1, '招商银行', 'CMB', NULL, 'https://pinganedai.vip/htmls/appv3/banklogo/40x40/bb53dac12a3a4641a9a7341b14a8ddfb.png', 'https://pinganedai.vip/htmls/appv3/banklogo/80x80/1d72d0c9dcf84e91aa38afe2d2969347.png', '单笔限额5万元，单日限额5万元，单月限额100万元', 1, '2019-03-15 16:40:25', 50000.00, 50000.00, 1000000.00);
INSERT INTO `th_bank_config` VALUES ('AtofAdgKRX6o8mth-11', 'AtofAdgKRX6o8mth', 1, '农业银行', 'ABC', NULL, 'https://pinganedai.vip/htmls/appv3/banklogo/40x40/c78f7b550e344b05b66ecdc230dcdb9e.png', 'https://pinganedai.vip/htmls/appv3/banklogo/80x80/29707e2e5ecf4f53adaa12e0c400c422.png', '单笔限额5万元，单日限额20万元（仅限6笔交易成功），单月限额100万元', 1, '2019-03-15 16:40:25', 50000.00, 200000.00, 1000000.00);
INSERT INTO `th_bank_config` VALUES ('AtofAdgKRX6o8mth-12', 'AtofAdgKRX6o8mth', 1, '广发银行', 'CGB', NULL, 'https://pinganedai.vip/htmls/appv3/banklogo/40x40/cc544ff74f3046f8aab755ba6d25376e.png', 'https://pinganedai.vip/htmls/appv3/banklogo/80x80/3af8c56e5eb741a69e732f893d959a42.png', '单笔限10万元，单日限额20万元，单月限额100万元', 1, '2019-03-15 16:40:25', 100000.00, 200000.00, 1000000.00);
INSERT INTO `th_bank_config` VALUES ('AtofAdgKRX6o8mth-13', 'AtofAdgKRX6o8mth', 1, '平安银行', 'PINGANBK', NULL, 'https://pinganedai.vip/htmls/appv3/banklogo/40x40/d01d752574014e7393913d9cbcd6208e.png', 'https://pinganedai.vip/htmls/appv3/banklogo/80x80/3dd63eea2c7a42adb04f6418fcb3bb2a.png', '单笔限额10万元，单日限额20万元，单月限额100万元', 1, '2019-03-15 16:40:25', 100000.00, 200000.00, 1000000.00);
INSERT INTO `th_bank_config` VALUES ('AtofAdgKRX6o8mth-14', 'AtofAdgKRX6o8mth', 1, '邮政储蓄银行', 'PSBC', NULL, 'https://pinganedai.vip/htmls/appv3/banklogo/40x40/d5ad7dc3054b4c8a90c625733f1bd952.png', 'https://pinganedai.vip/htmls/appv3/banklogo/80x80/429f844431f246859c1e720aff773041.png', '单笔限额10万元，单日限额20万元，单月限额100万元', 1, '2019-03-15 16:40:25', 100000.00, 200000.00, 1000000.00);
INSERT INTO `th_bank_config` VALUES ('AtofAdgKRX6o8mth-15', 'AtofAdgKRX6o8mth', 1, '浦发银行', 'SPDB', NULL, 'https://pinganedai.vip/htmls/appv3/banklogo/40x40/f79b118d447a4a5da9838ff07655bde1.png', 'https://pinganedai.vip/htmls/appv3/banklogo/80x80/5ad954683b1b450b93f73efb1768f792.png', '单笔限额10万元，单日限额20万元，单月限额100万元', 1, '2019-03-15 16:40:25', 100000.00, 200000.00, 1000000.00);
INSERT INTO `th_bank_config` VALUES ('AtofAdgKRX6o8mth-16', 'AtofAdgKRX6o8mth', 1, '北京银行', 'BOBJ', NULL, 'http://sy-test-oss.oss-cn-shanghai.aliyuncs.com/images/2018/9/12/5bd285b2febc4a06a8bdbc1700d47268.png', 'http://sy-test-oss.oss-cn-shanghai.aliyuncs.com/images/2018/9/12/5bd285b2febc4a06a8bdbc1700d47268.png', '单笔限额5千元，单日限额5千元，单月限额100万元', 1, '2019-03-29 10:33:07', 5000.00, 5000.00, 1000000.00);
INSERT INTO `th_bank_config` VALUES ('AtofAdgKRX6o8mth-2', 'AtofAdgKRX6o8mth', 1, '民生银行', 'CMBC', NULL, 'https://pinganedai.vip/htmls/appv3/banklogo/40x40/1d72d0c9dcf84e91aa38afe2d2969347.png', 'https://pinganedai.vip/htmls/appv3/banklogo/80x80/74ee67a4b7a149ad98079bcb2e14152b.png', '单笔限额5万元，单日限额5万元，单月限额100万元', 1, '2019-03-15 16:40:25', 50000.00, 50000.00, 1000000.00);
INSERT INTO `th_bank_config` VALUES ('AtofAdgKRX6o8mth-3', 'AtofAdgKRX6o8mth', 1, '建设银行', 'CCB', NULL, 'https://pinganedai.vip/htmls/appv3/banklogo/40x40/29707e2e5ecf4f53adaa12e0c400c422.png', 'https://pinganedai.vip/htmls/appv3/banklogo/80x80/92d529651a864939b9d92e24819270ef.png', '单笔限额5万元，单日限额10万元，单月限额100万元', 1, '2019-03-15 16:40:25', 50000.00, 100000.00, 1000000.00);
INSERT INTO `th_bank_config` VALUES ('AtofAdgKRX6o8mth-4', 'AtofAdgKRX6o8mth', 1, '中国银行', 'BOC', NULL, 'https://pinganedai.vip/htmls/appv3/banklogo/40x40/3af8c56e5eb741a69e732f893d959a42.png', 'https://pinganedai.vip/htmls/appv3/banklogo/80x80/bb53dac12a3a4641a9a7341b14a8ddfb.png', '单笔限额5万元，单日限额20万元，单月限额100万元', 1, '2019-03-15 16:40:25', 50000.00, 200000.00, 1000000.00);
INSERT INTO `th_bank_config` VALUES ('AtofAdgKRX6o8mth-5', 'AtofAdgKRX6o8mth', 1, '兴业银行', 'CIB', NULL, 'https://pinganedai.vip/htmls/appv3/banklogo/40x40/3dd63eea2c7a42adb04f6418fcb3bb2a.png', 'https://pinganedai.vip/htmls/appv3/banklogo/80x80/c78f7b550e344b05b66ecdc230dcdb9e.png', '单笔限额5万元，单日限额5万元，单月限额100万元', 1, '2019-03-15 16:40:25', 50000.00, 50000.00, 1000000.00);
INSERT INTO `th_bank_config` VALUES ('AtofAdgKRX6o8mth-6', 'AtofAdgKRX6o8mth', 1, '中信银行', 'CITIC', NULL, 'https://pinganedai.vip/htmls/appv3/banklogo/40x40/429f844431f246859c1e720aff773041.png', 'https://pinganedai.vip/htmls/appv3/banklogo/80x80/cc544ff74f3046f8aab755ba6d25376e.png', '单笔限额1万元，单日限额1万元，单月限额2万元', 1, '2019-03-15 16:40:25', 10000.00, 10000.00, 20000.00);
INSERT INTO `th_bank_config` VALUES ('AtofAdgKRX6o8mth-7', 'AtofAdgKRX6o8mth', 1, '重庆农商行', 'CQRCB', NULL, 'https://pinganedai.vip/htmls/appv3/banklogo/40x40/5ad954683b1b450b93f73efb1768f792.png', 'https://pinganedai.vip/htmls/appv3/banklogo/80x80/d01d752574014e7393913d9cbcd6208e.png', '单笔限额5万元，单日限额20万元，单月限额100万元', 1, '2019-03-15 16:40:25', 50000.00, 200000.00, 1000000.00);
INSERT INTO `th_bank_config` VALUES ('AtofAdgKRX6o8mth-8', 'AtofAdgKRX6o8mth', 1, '光大银行', 'CEB', NULL, 'https://pinganedai.vip/htmls/appv3/banklogo/40x40/74ee67a4b7a149ad98079bcb2e14152b.png', 'https://pinganedai.vip/htmls/appv3/banklogo/80x80/d5ad7dc3054b4c8a90c625733f1bd952.png', '单笔限额10万元，单日限额20万元，单月限额100万元', 1, '2019-03-15 16:40:25', 100000.00, 200000.00, 1000000.00);
INSERT INTO `th_bank_config` VALUES ('AtofAdgKRX6o8mth-9', 'AtofAdgKRX6o8mth', 1, '交通银行', 'COMM', NULL, 'https://pinganedai.vip/htmls/appv3/banklogo/40x40/92d529651a864939b9d92e24819270ef.png', 'https://pinganedai.vip/htmls/appv3/banklogo/80x80/f79b118d447a4a5da9838ff07655bde1.png', '单笔限额5万元，单日限额5万元，单月限额100万元', 1, '2019-03-15 16:40:25', 50000.00, 50000.00, 1000000.00);



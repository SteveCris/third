
-- third_order

-- 新建表 order_equity

CREATE TABLE `order_equity`  (
  `eq_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权益卡id',
  `appid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `state` int(2) NOT NULL COMMENT '状态（0初始化1获取成功）',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `cardno` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '卡号',
  `pass` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '套餐单价',
  `pgname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '套餐名称',
  `ext1` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`eq_id`) USING BTREE,
  UNIQUE INDEX `idx_eq`(`appid`, `order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单权益卡表' ROW_FORMAT = Compact;


-- 修改字段描述
ALTER TABLE `order_credit_rule` 
MODIFY COLUMN `ext` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段(现已用为规则大类字段1保险服务2法律服务3小说半月卡4权益卡)' AFTER `create_time`;

-- 设置扩展字段默认值为1保险大类
UPDATE order_credit_rule SET ext='1' WHERE ext IS NULL;


-- 规则调整后需要删除留存数据，删除所有留存保险规则，以后都走权益卡
DELETE FROM order_credit_rule WHERE scene_rule='3,1' AND order_id IN
(SELECT order_id FROM order_loan WHERE `status`=60);

DELETE FROM order_credit_rule WHERE scene_rule='2,1' AND order_id IN
(SELECT order_id FROM order_loan WHERE `status`=60);

DELETE FROM order_credit_rule WHERE scene_rule='90,1' AND order_id IN
(SELECT order_id FROM order_loan WHERE `status`=60);


-- ***********************************我是分隔符***********************************
-- center_contract

-- 修改字段描述
ALTER TABLE `center_scene_super` 
MODIFY COLUMN `ext1` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段1（现已改为权重字段，不为空）' AFTER `create_time`;

-- 设置默认权重值
-- 保险服务
UPDATE center_scene_super SET ext1=0 WHERE uuid='DEFAULT-1';
-- 法律服务
UPDATE center_scene_super SET ext1=0 WHERE uuid='DEFAULT-2';
-- 小说半月卡
UPDATE center_scene_super SET ext1=0 WHERE uuid='DEFAULT-3';
-- 权益卡
UPDATE center_scene_super SET ext1=100 WHERE uuid='DEFAULT-4';



-- 添加权益卡场景
INSERT INTO `center_scene` VALUES ('o6NAAJqaxgAsBqYK-EC1', 'o6NAAJqaxgAsBqYK', 4, 1000.00, 324.00, 'EC1', '权益卡', NULL, NULL, '限时特惠 可享多种特权', '125905342810101', '招商银行南京分行奥体支行', '无锡兰前物资有限公司', 15, '2019-04-22 16:04:33', '1011-1', NULL, 4);
INSERT INTO `center_scene` VALUES ('T3GBJf2GWO4YGu6t-EC1', 'T3GBJf2GWO4YGu6t', 4, 1000.00, 324.00, 'EC1', '权益卡', NULL, NULL, '限时特惠 可享多种特权', '125905342810101', '招商银行南京分行奥体支行', '无锡兰前物资有限公司', 15, '2019-04-22 16:04:33', '1011-1', NULL, 4);



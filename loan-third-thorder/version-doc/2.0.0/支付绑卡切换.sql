-- third_order

CREATE TABLE `order_bind_rule`  (
  `bind_r_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `appid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用id',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `rule` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '绑定场景规则参数',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `ext` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段',
  `ext2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`bind_r_id`) USING BTREE,
  UNIQUE INDEX `idx_ao`(`appid`, `order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '绑卡规则表' ROW_FORMAT = Compact;



-- center_contract

INSERT INTO `contract_rule` VALUES ('DEFAULT.BIND-0-1-2', 'DEFAULT.BIND', 1, '唯一组', 2, 0, '易极付', 30, '2019-07-23 10:21:16', NULL, NULL, '绑卡场景规则', 0);
INSERT INTO `contract_rule` VALUES ('DEFAULT.BIND-0-1-4', 'DEFAULT.BIND', 1, '唯一组', 4, 0, '中金支付', 0, '2019-07-23 10:21:16', NULL, NULL, '绑卡场景规则', 0);
INSERT INTO `contract_rule` VALUES ('DEFAULT.BIND-0-1-5', 'DEFAULT.BIND', 1, '唯一组', 8, 0, '现代支付', 0, '2019-07-23 10:21:16', NULL, NULL, '绑卡场景规则', 0);

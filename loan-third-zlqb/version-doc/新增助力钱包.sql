-- ****确认支付系统、账务系统、风控系统 是否已经添加相关配置
-- **************信贷中心相关配置***********************

-- center_contract

-- 新增应用配置
INSERT INTO `center_app` VALUES ('3hTtD4gbxhZFYEkJ', '3hTtD4gbxhZFYEkJ', 'THbBC7rWVA7dahQeQB05idm17ib0Pme4', '尚咏科技', '000001', 0, 1, NULL, '2019-07-24 17:18:08', NULL, NULL, '三方接入平台-助力钱包', 1);

-- 新增应用产品配置
INSERT INTO `center_product` VALUES ('3hTtD4gbxhZFYEkJ-PRODUCT_EIGHT', '3hTtD4gbxhZFYEkJ', '无场景（三方接入平台-助力钱包）', 'PRODUCT_EIGHT', 700.00, 700.00, 8, 1, '2019-06-27 15:12:30', NULL, 3500.00, 0.2400, 0.3600, 0.0000, 0.00, NULL, NULL);

-- 新增无场景大类
INSERT INTO `center_scene_super` VALUES ('3hTtD4gbxhZFYEkJ-8', '3hTtD4gbxhZFYEkJ', 8, '无场景', '', 1, 0, NULL, 1, 0, 0, '2019-06-17 13:47:01', '0', NULL, NULL, NULL);

-- 新增场景
INSERT INTO `center_scene` VALUES ('3hTtD4gbxhZFYEkJ-WUE1', '3hTtD4gbxhZFYEkJ', 801, 700.00, 300.00, 'WUE1', '无场景', NULL, NULL, '借款加速，提现百分百', '125905342810101', '招商银行南京分行奥体支行', '无锡兰前物资有限公司', 8, '2019-08-01 16:05:19', NULL, NULL, 8);

-- 删除、新增规则
delete from contract_rule where appid='WYXSD1FoVlZDTeo6.SUB';
-- 备份语句（不执行） bak
-- INSERT INTO `center_contract`.`contract_rule`(`uuid`, `appid`, `group_sort`, `group_name`, `type`, `if_unique`, `name`, `weight`, `create_time`, `ext1`, `ext2`, `remark`, `ss_type`) VALUES ('WYXSD1FoVlZDTeo6.SUB-1-2-1', 'WYXSD1FoVlZDTeo6.SUB', 2, '后签组', 1, 0, '个人账户', 100, '2019-04-19 18:06:57', NULL, NULL, '默认保险场景规则', 8);
-- INSERT INTO `center_contract`.`contract_rule`(`uuid`, `appid`, `group_sort`, `group_name`, `type`, `if_unique`, `name`, `weight`, `create_time`, `ext1`, `ext2`, `remark`, `ss_type`) VALUES ('WYXSD1FoVlZDTeo6.SUB-8-1-801', 'WYXSD1FoVlZDTeo6.SUB', 1, '先签组', 801, 0, '无场景', 100, '2019-04-19 18:06:57', NULL, NULL, '默认保险场景规则', 8);


INSERT INTO `contract_rule` VALUES ('DEFAULT.SUB-8-1-801', 'DEFAULT.SUB', 1, '先签组', 801, 0, '无场景', 100, '2019-04-19 18:06:57', NULL, NULL, '默认保险场景规则', 8);
INSERT INTO `contract_rule` VALUES ('DEFAULT.SUB-8-2-1', 'DEFAULT.SUB', 2, '后签组', 1, 0, '个人账户', 100, '2019-04-19 18:06:57', NULL, NULL, '默认保险场景规则', 8);


-- **************核心订单系统相关配置***********************
-- third_base

-- 新增产品配置
INSERT INTO `th_product_config` VALUES ('3', '3hTtD4gbxhZFYEkJ', 'PRODUCT_EIGHT', '2019-07-17 16:14:38', NULL, '助力钱包', 700.00, 0.2400, 300.00, 1, 8, '30010', '助力钱包-蚂蚁闪电借', 'ZLQB', '8', 0);

-- 新增信贷中心调用配置
INSERT INTO `th_center_config` VALUES ('3', '3hTtD4gbxhZFYEkJ', 'THbBC7rWVA7dahQeQB05idm17ib0Pme4', 'https://gt.symy666.vip', 'zlqb', 'COMMON:CACHE:TOKEN:3hTtD4gbxhZFYEkJ', 'COMMON:CACHE:TOKEN:SUB:3hTtD4gbxhZFYEkJ');

-- 新增隔离参数
INSERT INTO `th_param_config` VALUES ('3', '3hTtD4gbxhZFYEkJ', 'USER_QUARANTINE', '30', '审核失败隔离日期', '2019-07-24 18:00:03');

-- 修改logo
UPDATE th_bank_config SET bg_logo_url=logo_url WHERE bank_id='4-29-GZYH';












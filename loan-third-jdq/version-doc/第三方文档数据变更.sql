
-- third_jdq
-- 新增字段
ALTER TABLE `jdq_user_info` 
ADD COLUMN `app_name` varchar(64) NULL COMMENT '应用名称' AFTER `face_result_active`,
ADD COLUMN `ext1` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段' AFTER `app_name`,
ADD COLUMN `ext2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '扩展字段2' AFTER `ext1`;


-- third_base
-- 修改应用名称 合同签订用
UPDATE th_product_config SET application_name='蚂蚁闪电借' WHERE product_id='1';
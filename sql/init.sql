-- =============================================
-- 智能法院审理平台数据库初始化脚本
-- =============================================

CREATE DATABASE IF NOT EXISTS legal_platform DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE legal_platform;

-- ---------------------------------------------
-- 用户表
-- ---------------------------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `username` VARCHAR(50) NOT NULL COMMENT '用户名',
    `password` VARCHAR(255) NOT NULL COMMENT '密码（BCrypt加密）',
    `role` VARCHAR(20) NOT NULL DEFAULT 'USER' COMMENT '角色：ADMIN/USER/JUDGE',
    `real_name` VARCHAR(50) DEFAULT NULL COMMENT '真实姓名',
    `phone` VARCHAR(20) DEFAULT NULL COMMENT '手机号',
    `email` VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态：1-正常 0-禁用',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 初始化管理员账号（密码：admin123，BCrypt加密）
INSERT INTO `user` (`username`, `password`, `role`, `real_name`, `status`) VALUES
('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'ADMIN', '系统管理员', 1),
('judge01', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'JUDGE', '张法官', 1),
('user01', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'USER', '李用户', 1);

-- ---------------------------------------------
-- 案件信息表
-- ---------------------------------------------
DROP TABLE IF EXISTS `case_info`;
CREATE TABLE `case_info` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `case_no` VARCHAR(50) DEFAULT NULL COMMENT '案号',
    `case_name` VARCHAR(200) NOT NULL COMMENT '案件名称',
    `party_name` VARCHAR(200) DEFAULT NULL COMMENT '当事人',
    `evidence_desc` TEXT COMMENT '证据描述',
    `status` VARCHAR(20) NOT NULL DEFAULT 'PENDING' COMMENT '状态：PENDING-待受理 HEARING-审理中 CLOSED-已结案',
    `user_id` BIGINT NOT NULL COMMENT '用户ID（原告/申请人）',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='案件信息表';

-- 示例数据
INSERT INTO `case_info` (`case_no`, `case_name`, `party_name`, `evidence_desc`, `status`, `user_id`) VALUES
('（2024）民初字第001号', '张三诉李四民间借贷纠纷案', '张三', '有借条一张，转账记录两份', 'HEARING', 3),
('（2024）民初字第002号', '王五与赵六合同纠纷案', '王五', '合同原件，履行记录', 'PENDING', 3),
('（2024）民初字第003号', '某公司与某某公司货款纠纷案', '某公司', '购销合同、送货单、对账单', 'CLOSED', 3);

-- ---------------------------------------------
-- 庭审表
-- ---------------------------------------------
DROP TABLE IF EXISTS `trial`;
CREATE TABLE `trial` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `case_id` BIGINT NOT NULL COMMENT '案件ID',
    `court` VARCHAR(100) DEFAULT NULL COMMENT '法院/法庭',
    `trial_time` DATETIME DEFAULT NULL COMMENT '开庭时间',
    `judge` VARCHAR(50) DEFAULT NULL COMMENT '主审法官',
    `status` VARCHAR(20) NOT NULL DEFAULT 'SCHEDULED' COMMENT '状态：SCHEDULED-已排期 ONGOING-进行中 FINISHED-已结束',
    `result` TEXT COMMENT '审理结果',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_case_id` (`case_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='庭审表';

-- 示例数据
INSERT INTO `trial` (`case_id`, `court`, `trial_time`, `judge`, `status`, `result`) VALUES
(1, '第一人民法院第一法庭', '2024-06-15 09:00:00', '张法官', 'ONGOING', NULL),
(2, '第一人民法院第二法庭', '2024-06-20 14:00:00', '王法官', 'SCHEDULED', NULL),
(3, '第一人民法院第一法庭', '2024-05-20 09:00:00', '张法官', 'FINISHED', '被告于判决生效后十日内支付原告货款人民币50000元');

-- ---------------------------------------------
-- 文书表
-- ---------------------------------------------
DROP TABLE IF EXISTS `document`;
CREATE TABLE `document` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `case_id` BIGINT NOT NULL COMMENT '案件ID',
    `title` VARCHAR(200) NOT NULL COMMENT '文书标题',
    `type` VARCHAR(50) DEFAULT NULL COMMENT '文书类型：起诉状/答辩状/判决书/调解书等',
    `file_name` VARCHAR(255) DEFAULT NULL COMMENT '原始文件名',
    `file_path` VARCHAR(255) DEFAULT NULL COMMENT '存储路径',
    `file_type` VARCHAR(50) DEFAULT NULL COMMENT '文件类型',
    `file_size` BIGINT DEFAULT NULL COMMENT '文件大小（字节）',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_case_id` (`case_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文书表';

-- ---------------------------------------------
-- 电子卷宗表
-- ---------------------------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `case_id` BIGINT DEFAULT NULL COMMENT '案件ID',
    `case_name` VARCHAR(200) DEFAULT NULL COMMENT '案件名称',
    `file_name` VARCHAR(255) NOT NULL COMMENT '原始文件名',
    `file_path` VARCHAR(255) NOT NULL COMMENT '存储路径',
    `file_type` VARCHAR(50) DEFAULT NULL COMMENT '文件类型',
    `file_size` BIGINT DEFAULT NULL COMMENT '文件大小（字节）',
    `upload_user_id` BIGINT DEFAULT NULL COMMENT '上传用户ID',
    `upload_user` VARCHAR(50) DEFAULT NULL COMMENT '上传人',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
    PRIMARY KEY (`id`),
    KEY `idx_case_id` (`case_id`),
    KEY `idx_upload_user_id` (`upload_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='电子卷宗表';

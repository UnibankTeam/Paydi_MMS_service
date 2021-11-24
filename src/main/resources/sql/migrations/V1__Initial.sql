
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for mms_api_key_access
-- ----------------------------
CREATE TABLE `mms_api_key_access` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `api_key` char(255) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `app_id` bigint(20)  NOT NULL ,
  `desc` varchar(500) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' ,
  `status` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `valid_from_date` datetime DEFAULT NULL,
  `valid_to_date` datetime DEFAULT NULL,
  `created_by` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `updated_by` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- ----------------------------
-- Table structure for mms_app_access
-- ----------------------------
CREATE TABLE `mms_app_access` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` char(255) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `hierarchy` char(255) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `desc` varchar(500) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `external_id` varchar(25) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' ,
  `status` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `created_by` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `updated_by` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- ----------------------------
-- Table structure for mms_params
-- ----------------------------
CREATE TABLE `mms_params` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_code` char(50) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `param_value` varchar(500) CHARACTER SET utf8mb4 NOT NULL DEFAULT '' ,
  `param_description` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `status` char(1) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `created_by` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_by` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- ----------------------------
-- Table structure for mms_service_logs
-- ----------------------------
CREATE TABLE `mms_service_logs` (
  `refid` bigint(20) NOT NULL AUTO_INCREMENT,
  `channel` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `request_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `request_url` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `service_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `request_content` longtext COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `is_success` tinyint(1)  NULL DEFAULT NULL ,
  `response_content` longtext COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `updated_by` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`refid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



-- ----------------------------
-- Table structure for mms_msg_log
-- ----------------------------
CREATE TABLE `mms_msg_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `queue` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `msg` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `execute` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


CREATE TABLE `mms_currency_rate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `source_currency` varchar(4) NOT NULL,
  `target_currency` varchar(4) NOT NULL,
  `exchange_rate` double NOT NULL DEFAULT 0,
  `valid_from_date` datetime  NULL DEFAULT NULL,
  `valid_to_date` datetime  NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `mms_audit_logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `entity_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `entity_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `request_url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `action_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `request_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `response_content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `updated_date` datetime(0) NULL DEFAULT NULL,
  `updated_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;


SET FOREIGN_KEY_CHECKS = 1;

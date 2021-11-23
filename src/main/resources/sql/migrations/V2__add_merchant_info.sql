
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for mms_partner
-- ----------------------------

CREATE TABLE IF NOT EXISTS `mms_partner`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `odoo_contact_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `owner_external_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '',
  `created_by` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `updated_by` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `updated_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `code` (`code`),
  KEY `odoo_contact_id` (`odoo_contact_id`)
) ENGINE = InnoDB  CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for mms_mid_master
-- ----------------------------

CREATE TABLE IF NOT EXISTS `mms_mid_master`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB  CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- master data Napas mid code
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (1, '5139', 'Giày dép');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (2, '5192', 'Sách, ấn phẩm định kỳ và báo chí');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (3, '5231', 'Kính, sơn và giấy dán tường');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (4, '5411', 'Siêu thị và Tạp hóa');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (5, '5611', 'Quần áo và và phụ kiện cho Nam');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (6, '5621', 'Hàng hiệu trang phục cho Nữ');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (7, '5631', 'Phụ kiện cho Nữ');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (8, '5641', 'Quần áo trẻ em và trẻ sơ sinh');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (9, '5651', 'Quần áo gia đình');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (10, '5661', 'Giày');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (11, '5691', 'Quần áo nam và nữ');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (12, '5734', 'Cửa hàng kinh doanh phần mềm máy tính');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (13, '5812', 'Địa điểm ăn uống và nhà hàng');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (14, '5813', 'Địa điểm uống (đồ uống có cồn), quán bar, quán rượu, câu lạc bộ đêm và vũ trường');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (15, '5814', 'Cửa hàng bán đồ ăn nhanh');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (16, '5912', 'Cửa hàng thuốc và nhà thuốc');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (17, '5921', 'Cửa hàng bia, rượu và đồ uống có cồn');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (18, '5832', 'Đồ cổ, dịch vụ sửa chữa và phục hồi');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (19, '5940', 'Cửa hàng xe đạp');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (20, '5941', 'Đồ thể thao');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (21, '5942', 'Cửa hàng sách');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (22, '5943', 'Văn phòng phẩm và đồ đùng học tập');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (23, '5944', 'Đồng hồ, trang sức và đá quý');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (24, '5945', 'Đồ chơi và trò chơi điện tử');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (25, '5946', 'Máy ảnh và phục kiện nhiếp ảnh');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (26, '5947', 'Quà tặng và đồ lưu niệm');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (27, '5977', 'Mỹ phẩm');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (28, '5992', 'Cửa hàng Hoa');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (29, '5995', 'Thú cưng và thực phẩm thú cưng');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (30, '6011', 'Tổ chức tài chính – rút tiền mặt');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (31, '7011', 'Nhà nghỉ - khách sạn, khu nghỉ mát');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (32, '7211', 'Dịch vụ giặt ủi');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (33, '7216', 'Giặt khô');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (34, '7298', 'Cửa hàng chăm sóc sức khỏe và làm đẹp');
INSERT INTO `mms_mid_master` (`id`, `code`, `desc`) VALUES (35, '8062', 'Bệnh viện');

-- ----------------------------
-- Table structure for mms_merchant
-- ----------------------------
CREATE TABLE IF NOT EXISTS `mms_merchant`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `mid_master_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `partner_id` bigint(20) NOT NULL ,
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '',
  `created_by` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `updated_by` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `updated_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB  CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;



-- ----------------------------
-- Table structure for mms_merchant
-- ----------------------------

CREATE TABLE IF NOT EXISTS `mms_tid`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) NOT NULL ,
  `tid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `serial_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `terminal_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `min_rev_default` bigint(20)  NULL DEFAULT NULL ,
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '',
  `created_by` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `updated_by` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `updated_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `code` (`tid`)
  ) ENGINE = InnoDB  CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for mms_terminal
-- ----------------------------

CREATE TABLE IF NOT EXISTS `mms_terminal`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `factory` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `serial_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `merchant_id` bigint(20) NOT NULL ,
  `assets_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `setup_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `contract_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `contract_date` datetime(0) NOT NULL ,
  `bank_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '',
  `created_by` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `updated_by` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `updated_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `code` (`serial_number`)
  ) ENGINE = InnoDB  CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mms_terminal_tid_mapping
-- ----------------------------

CREATE TABLE IF NOT EXISTS `mms_terminal_tid_mapping`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `serial_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `tid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '',
  `active_by` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `active_date` datetime(0) NULL DEFAULT NULL,
  `inactive_by` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `Inactive_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
  ) ENGINE = InnoDB  CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;



-- ----------------------------
-- Table structure for mms_pos_rate_config
-- ----------------------------

CREATE TABLE IF NOT EXISTS `mms_pos_rate_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) NOT NULL ,
  `tid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `card_type` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `cost_rate` double  NOT NULL ,
  `cogs_rate` double  NOT NULL ,
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '',
  `created_by` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `updated_by` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `updated_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `code` (`tid`, `merchant_id`, `card_type`)
  ) ENGINE = InnoDB  CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;



-- ----------------------------
-- Table structure for mms_card_type
-- ----------------------------

CREATE TABLE IF NOT EXISTS `mms_card_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `digit` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
  `code` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `code` (`code`)
) ENGINE = InnoDB  CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

INSERT INTO `mms_card_type` (`id`, `digit`, `code`, `desc`) VALUES (1, '4', 'VC', 'Visa');
INSERT INTO `mms_card_type` (`id`, `digit`, `code`, `desc`) VALUES (2, '5', 'MC', 'Master');
INSERT INTO `mms_card_type` (`id`, `digit`, `code`, `desc`) VALUES (3, '3', 'JC', 'JCB');
INSERT INTO `mms_card_type` (`id`, `digit`, `code`, `desc`) VALUES (4, '9', 'ATM', 'ATM Napas');



-- ----------------------------
-- Table structure for mms_bank
-- ----------------------------


CREATE TABLE IF NOT EXISTS `mms_bank`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `digit` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL  DEFAULT '',
  `code` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `code` (`code`)
) ENGINE = InnoDB  CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (1, 'MB', 'Ngân hàng Quân Đội - MB Bank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (2, 'TCB', 'Ngân Hàng Kỹ thương - Techcombank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (3, 'VCB', 'Ngân Hàng Ngoại Thương - Vietcombank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (4, 'AGR', 'Ngân Hàng NN & PT NT - Agribank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (5, 'CITI', 'Ngân hàng Citibank Việt Nam  - Citibank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (6, 'EXIM', 'Ngân Hàng Xuất Nhập Khẩu - Eximbank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (7, 'HSBC', 'Ngân hàng HSBC Việt Nam - HSBC');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (8, 'ACB', 'Ngân hàng Á Châu - ACB');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (9, 'SH', 'Ngân hàng Shinhan - Shinhanbank ');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (10, 'VPB', 'Ngân hàng Việt Nam Thịnh Vượng - VPBank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (11, 'VIB', 'Ngân hàng Quốc Tế - VIB');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (12, 'SC', 'Ngân hàng Standard Chartered - Stanchart');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (13, 'PVC', 'Ngân hàng Đại chúng - PVCombank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (14, 'SEA', 'Ngân hàng Đông Nam Á - SeaBank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (15, 'FE', 'Công ty tài chính FE - FE Credit');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (16, 'VCCB', 'Ngân hàng Bản Việt - VietCapitalBank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (17, 'SCB', 'Ngân hàng TMCP Sài Gòn - SCB');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (18, 'BIDV', 'Ngân hàng Đầu tư & Phát triển VN  - BIDV');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (19, 'HOM', 'Công ty tài chính Home Credit');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (20, 'IDV', 'Ngân hàng Indovina Việt Nam');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (21, 'DAB', 'Ngân hàng Đông Á - DongABank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (22, 'VTB', 'Ngân hàng Công Thương VN - Vietinbank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (23, 'NCB', 'Ngân hàng Quốc Dân - NCB');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (24, 'MSB', 'Ngân hàng Hàng Hải - MaritimeBank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (25, 'NAB', 'Ngân hàng Nam Á - NamA Bank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (26, 'BAB', 'Ngân hàng Bắc Á - BacA Bank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (28, 'OCB', 'Ngân hàng Phương Đông - OCB');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (29, 'PGB', 'Ngân hàng Xăng Dầu Petrolimex - PGBank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (30, 'JAC', 'Công ty tài chính JACCS');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (31, 'VIE', 'Ngân hàng Việt Nam Thương tín - Vietbank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (32, 'SGB', 'Ngân hàng Sài Gòn Công Thương - SaigonBank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (33, 'STB', 'Ngân hàng Sài Gòn thương tín - Sacombank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (34, 'LOT', 'Công ty tài chính Lotte');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (35, 'GPB', 'Ngân hàng dầu khí Toàn cầu - GPBank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (36, 'OCE', 'Ngân hàng Đại Dương -Oceanbank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (37, 'CB', 'Ngân hàng xây dựng - CB');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (38, 'ABB', 'Ngân hàng An Bình - ABBank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (39, 'BVB', 'Ngân hàng Bảo Việt - BaoVietBank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (40, 'LVB', 'Ngân hàng Bưu điện Liên Việt - LienvietPostbank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (41, 'KLB', 'Ngân hàng Kiên Long - KienLongBank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (42, 'SHB', 'Ngân hàng Sài Gòn - Hà Nội SHB');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (43, 'TPB', 'Ngân hàng Tiên Phong - TPBank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (44, 'VAB', 'Ngân hàng Việt Á - VietABank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (45, 'HDB', 'Ngân hàng Phát triển nhà TP HCM - HDBank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (46, 'WRB', 'Ngân hàng Woori  - Wooribank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (48, 'ANZ', 'Ngân hàng ANZ');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (49, 'UOB', 'Ngân hàng UOB');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (50, 'HLB', 'Hongleong Bank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (52, 'IVB', 'Indovina bank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (54, 'CIMB', 'Ngân hàng MTV CIMB');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (55, 'CO-OPBANK', 'Ngân hàng Co-op bank');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (57, 'VIETMAS', 'VIETTRAVEL - MASTER - BIDV');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (58, 'VIETVISA', 'VIETTRAVEL - VISA - VCB');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (59, 'MCR', 'Công ty tài chính TNHH MB Shinsei');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (60, 'HSS', 'Công ty tài chính HD SaiSon');
INSERT INTO `mms_bank` (`id`, `code`, `desc`) VALUES (61, 'VC', 'Công ty tài chính VietCredit - Việt Credit');


SET FOREIGN_KEY_CHECKS = 1;

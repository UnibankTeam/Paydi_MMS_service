
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


SET FOREIGN_KEY_CHECKS = 1;

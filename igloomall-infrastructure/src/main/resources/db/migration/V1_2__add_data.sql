-- truncate tables
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE tbl_coupon;
TRUNCATE TABLE tbl_user;
TRUNCATE TABLE tbl_coupon_history;

-- insert value into coupon table
INSERT INTO tbl_coupon VALUES (0, 50000, 'C0001', '의류 할인 쿠폰', 1000, 0);
INSERT INTO tbl_coupon VALUES (0, 100000, 'E0001', '전자제품 할인 쿠폰', 300, 0);

-- insert value into user table
INSERT INTO tbl_user VALUES (0);

SET FOREIGN_KEY_CHECKS = 1;
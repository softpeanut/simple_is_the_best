-- create coupon table
CREATE TABLE IF NOT EXISTS tbl_coupon
(
    id              BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    amount          BIGINT      NOT NULL,
    code            CHAR(5)     NOT NULL,
    name            VARCHAR(10) NOT NULL,
    total_quantity  BIGINT      NOT NULL,
    issued_quantity BIGINT      NOT NULL,
    CONSTRAINT code_unique
        UNIQUE (code)
);

-- create user table
CREATE TABLE IF NOT EXISTS tbl_user
(
    id BIGINT AUTO_INCREMENT
        PRIMARY KEY
);

-- create coupon_history table
CREATE TABLE IF NOT EXISTS tbl_coupon_history
(
    coupon_id BIGINT   NOT NULL,
    user_id   BIGINT   NOT NULL,
    issued_at datetime NOT NULL,
    used_at   datetime NULL,
    PRIMARY KEY (coupon_id, user_id),
    CONSTRAINT coupon_foreign_key
        FOREIGN KEY (coupon_id) REFERENCES tbl_coupon (id),
    CONSTRAINT user_foreign_key
        FOREIGN KEY (user_id) REFERENCES tbl_user (id)
);
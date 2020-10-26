SET FOREIGN_KEY_CHECKS =0;

DROP TABLE IF EXISTS `tb_coin_deal`;
CREATE TABLE `tb_coin_deal` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`from_user_id` INT(11) NOT NULL COMMENT '交易发起方用户ID',
	`from_address` VARCHAR(255) NOT NULL COMMENT '交易发起方地址',
	`coin_balance` DOUBLE NOT NULL COMMENT '交易前账户余额',
	`to_user_id` INT(11) NOT NULL COMMENT '交易发接收方用户ID',
	`to_address` VARCHAR(255) NOT NULL COMMENT '交易接收方地址',
	`coin_balance_dealed` DOUBLE NOT NULL COMMENT '交易后账户余额',
	`create_time` DATETIME NOT NULL COMMENT '交易创建时间',
	PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;
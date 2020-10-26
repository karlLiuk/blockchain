SET FOREIGN_KEY_CHECKS =0;

DROP TABLE IF EXISTS `tb_user_wallet`;
CREATE TABLE `tb_user_wallet` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`user_id` INT(11) NOT NULL COMMENT '联盟节点ID',
	`coin_address` VARCHAR(255) NOT NULL COMMENT '币地址',
	`coin_balance` DOUBLE NOT NULL COMMENT '币地址对应的余额',
	`create_time` DATETIME NOT NULL COMMENT '交易创建时间',
	`update_time` TIMESTAMP NOT NULL
		DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '交易更新时间',
	PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;
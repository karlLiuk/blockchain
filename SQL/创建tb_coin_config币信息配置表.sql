CREATE DATABASE blockchain;

SET FOREIGN_KEY_CHECKS =0;

DROP TABLE IF EXISTS `tb_coin_config`;
CREATE TABLE `tb_coin_config` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`biz_type` VARCHAR(255) NOT NULL COMMENT '业务类型',
	`coin_total` INT(11) NOT NULL COMMENT '币的总量',
	`coin_reserved` INT(11) NOT NULL COMMENT '机构预留币的数量',
	`coin_per_deal` DOUBLE NOT NULL COMMENT '每笔交易最小的币支付金额',
	`coin_block_create` DOUBLE NOT NULL COMMENT '生成一个区块的奖励币数量',
	`create_time` DATETIME NOT NULL COMMENT '规则配置时间',
	`update_time` TIMESTAMP NOT NULL
		DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '规则修改时间',
	PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;
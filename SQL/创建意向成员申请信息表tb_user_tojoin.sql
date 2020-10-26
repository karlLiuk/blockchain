SET FOREIGN_KEY_CHECKS =0;

DROP TABLE IF EXISTS `tb_user_tojoin`;
CREATE TABLE `tb_user_tojoin` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`org_name` VARCHAR(255) NOT NULL COMMENT '申请加入的组织名称',
	`org_phone` VARCHAR(255) NOT NULL COMMENT '申请加入的组织联系人电话号码',
	`org_represent` VARCHAR(255) NOT NULL COMMENT '申请加入的组织联系人',
	`result` TINYINT NOT NULL COMMENT '申请结果(0-未处理，1-已处理)',
	`create_time` DATETIME NOT NULL COMMENT '申请加入时间',
	PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=2 CHARSET=utf8mb4;
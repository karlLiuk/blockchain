SET FOREIGN_KEY_CHECKS =0;

DROP TABLE IF EXISTS `tb_alliance`;
CREATE TABLE `tb_alliance` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`alliance_id` VARCHAR(255) DEFAULT NULL COMMENT '联盟成员的ID号码',
	`alliance_ip` VARCHAR(255) DEFAULT NULL COMMENT '联盟成员部署服务器的公网IP地址',
	`alliance_name` VARCHAR(255) DEFAULT NULL COMMENT '联盟成员部署时配置的节点名称',
	`create_time` DATETIME DEFAULT NULL COMMENT '联盟白名单信息表创建的时间',
	`update_time` DATETIME DEFAULT NULL COMMENT '联盟白名单信息表更新的时间',
	PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=2 CHARSET=utf8mb4;
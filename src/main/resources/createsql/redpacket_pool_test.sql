CREATE TABLE redpacket_pool_test (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(2) DEFAULT NULL,
  `amount` INT(11) DEFAULT NULL,
  `round_id` INT(11) DEFAULT NULL,
  `c_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `u_time` TIMESTAMP NOT NULL DEFAULT null,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8
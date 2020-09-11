CREATE TABLE `tb_user` (
   `id` bigint NOT NULL AUTO_INCREMENT,
   `username` varchar(40) NOT NULL,
   `password` varchar(40) NOT NULL,
   `phone` varchar(15) DEFAULT NULL,
   `create_time` date DEFAULT NULL,
   `last_update_time` date DEFAULT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

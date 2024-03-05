# Host: 127.0.0.1  (Version 5.7.23-log)
# Date: 2024-03-05 16:43:04
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "offmessage_record"
#

DROP TABLE IF EXISTS `offmessage_record`;
CREATE TABLE `offmessage_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_user` varchar(50) NOT NULL DEFAULT '' COMMENT '发送者',
  `to_user` varchar(50) NOT NULL DEFAULT '' COMMENT '接收者',
  `date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '日期',
  `state` int(2) NOT NULL DEFAULT '0' COMMENT '状态',
  `content` varchar(255) NOT NULL DEFAULT '' COMMENT '内容',
  `content_type` varchar(30) NOT NULL DEFAULT '' COMMENT '信息类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

#
# Data for table "offmessage_record"
#

INSERT INTO `offmessage_record` VALUES (1,'zhangr132','ff','2024-03-01 17:13:08',1,'1','text'),(8,'zhangr132','admin','2024-03-05 16:42:27',1,'发送','text');

#
# Structure for table "user"
#

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `picture` varchar(50) DEFAULT NULL COMMENT '头像',
  `signature` varchar(100) DEFAULT '这个人很赖，什么都没写~' COMMENT '签名',
  `addr` varchar(100) DEFAULT NULL COMMENT '地址',
  `tel` varchar(30) NOT NULL DEFAULT '' COMMENT '电话',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1,'zwj','81DC9BDB52D04DC20036DBD8313ED055','张无忌','fe39d57b-5a23-4066-8a22-55f7a8c08de8.png','我感到难过，不是因为你欺骗了我，而是因为我再也不能相信你了',NULL,''),(3,'zbf','81DC9BDB52D04DC20036DBD8313ED055','剑神卓不凡','82979bce-e874-4ed5-a9fa-7a415d7b92eb.png','我于杀戮之中盛放，一如黎明中的花朵',NULL,''),(4,'mm','81DC9BDB52D04DC20036DBD8313ED055','敏敏','82979bce-e874-4ed5-a9fa-7a415d7b92eb.png','比比，爱姆sheep',NULL,''),(5,'adh','81DC9BDB52D04DC20036DBD8313ED055','爱德华','82979bce-e874-4ed5-a9fa-7a415d7b92eb.png','一个人知道自己为什么而活，就可以忍受任何一种生活',NULL,''),(6,'ff','81dc9bdb52d04dc20036dbd8313ed055','菲菲','d2b8537e-581b-48f1-8001-35d75fa0da64.png','睡一会罢，——便好了',NULL,'15233667819'),(7,'car','81dc9bdb52d04dc20036dbd8313ed055','car','6efe0c1c-4573-4b70-88d6-3e25ef268644.png','无边落木萧萧下，不尽长江滚滚来',NULL,'15233667819'),(8,'bgz','81dc9bdb52d04dc20036dbd8313ed055','白胡子','c09e50ad-2c7c-4a87-86b9-419d04619c55.png','死亡如风，常伴吾身',NULL,'15233667819'),(9,'qf','81dc9bdb52d04dc20036dbd8313ed055','乔峰','5edfeaee-200f-4aa1-99ba-dadcdc3114f4.png','一曲肝肠断，天涯何处觅知音',NULL,'15233667819'),(11,'sun','81dc9bdb52d04dc20036dbd8313ed055','孙悟空','8397ed5d-22f6-48fa-92f5-2e7d58dc17a1.png','不多不多!多乎哉?不多也。',NULL,'15517986633'),(12,'zhangr132','81dc9bdb52d04dc20036dbd8313ed055','zhangr132','64b69867-5375-4ae9-8615-4c92446546be.png','这个人很赖，什么都没写~',NULL,'13254455685'),(13,'admin','81dc9bdb52d04dc20036dbd8313ed055','admin',NULL,'这个人很赖，什么都没写~',NULL,''),(15,'1234','81dc9bdb52d04dc20036dbd8313ed055','1234',NULL,NULL,NULL,'');

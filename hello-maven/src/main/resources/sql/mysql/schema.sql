-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.27


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema authority
--

CREATE DATABASE IF NOT EXISTS photograph;
USE photograph;

--
-- Definition of table `base_modules`
--

DROP TABLE IF EXISTS `base_modules`;
CREATE TABLE `base_modules` (
  `module_id` int(9) unsigned NOT NULL AUTO_INCREMENT,
  `module_name` varchar(64) NOT NULL,
  `module_url` varchar(64) DEFAULT NULL,
  `parent_id` int(9) unsigned DEFAULT NULL,
  `leaf` int(1) unsigned DEFAULT NULL,
  `expanded` int(1) unsigned DEFAULT NULL,
  `display_index` int(2) unsigned DEFAULT NULL,
  `is_display` int(1) unsigned DEFAULT NULL,
  `en_module_name` varchar(64) DEFAULT NULL,
  `icon_css` varchar(128) DEFAULT NULL,
  `information` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`module_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='系统模块表';

--
-- Dumping data for table `base_modules`
--

/*!40000 ALTER TABLE `base_modules` DISABLE KEYS */;
INSERT INTO `base_modules` (`module_id`,`module_name`,`module_url`,`parent_id`,`leaf`,`expanded`,`display_index`,`is_display`,`en_module_name`,`icon_css`,`information`) VALUES 
 (1,'系统设置',NULL,0,0,1,1,1,'System Settings','system_settings',NULL),
 (2,'供应商管理',NULL,0,0,1,2,1,'Operator','abc',NULL),
 (11,'角色管理','/role',1,1,0,3,1,'Role Management','role',NULL),
 (12,'用户管理','/user',1,1,0,2,1,'User Management','user',NULL),
 (13,'模块管理','/module',1,1,0,1,1,'Module Management','module',NULL),
 (14,'系统字段管理','/field',1,1,1,4,1,'field','field',NULL),
 (21,'供应商信息','/oprator',2,1,0,1,1,'oprator','cde',NULL);
/*!40000 ALTER TABLE `base_modules` ENABLE KEYS */;


--
-- Definition of table `base_role_module`
--

DROP TABLE IF EXISTS `base_role_module`;
CREATE TABLE `base_role_module` (
  `role_module_id` int(9) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色模块ID',
  `role_id` int(9) unsigned DEFAULT NULL COMMENT '角色ID',
  `module_id` int(9) unsigned DEFAULT NULL COMMENT '模块ID',
  PRIMARY KEY (`role_module_id`),
  KEY `FK_base_role_module_1` (`role_id`),
  KEY `FK_base_role_module_2` (`module_id`),
  CONSTRAINT `FK_base_role_module_2` FOREIGN KEY (`module_id`) REFERENCES `base_modules` (`module_id`),
  CONSTRAINT `FK_base_role_module_1` FOREIGN KEY (`role_id`) REFERENCES `base_roles` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='角色模块表';

--
-- Dumping data for table `base_role_module`
--

/*!40000 ALTER TABLE `base_role_module` DISABLE KEYS */;
INSERT INTO `base_role_module` (`role_module_id`,`role_id`,`module_id`) VALUES 
 (1,2,2),
 (2,2,21),
 (3,1,1),
 (4,1,2),
 (5,1,13),
 (6,1,12),
 (7,1,11),
 (8,1,14),
 (9,1,21);
/*!40000 ALTER TABLE `base_role_module` ENABLE KEYS */;


--
-- Definition of table `base_roles`
--

DROP TABLE IF EXISTS `base_roles`;
CREATE TABLE `base_roles` (
  `role_id` int(9) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(128) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';

--
-- Dumping data for table `base_roles`
--

/*!40000 ALTER TABLE `base_roles` DISABLE KEYS */;
INSERT INTO `base_roles` (`role_id`,`role_name`,`role_desc`) VALUES 
 (1,'管理员','管理员'),
 (2,'测试角色','测试角色');
/*!40000 ALTER TABLE `base_roles` ENABLE KEYS */;


--
-- Definition of table `base_user_role`
--

DROP TABLE IF EXISTS `base_user_role`;
CREATE TABLE `base_user_role` (
  `user_role_id` int(9) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户角色ID',
  `user_id` int(9) unsigned DEFAULT NULL COMMENT '用户ID',
  `role_id` int(9) unsigned DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_role_id`),
  KEY `FK_base_user_role_1` (`user_id`),
  KEY `FK_base_user_role_2` (`role_id`),
  CONSTRAINT `FK_base_user_role_1` FOREIGN KEY (`user_id`) REFERENCES `base_users` (`user_id`),
  CONSTRAINT `FK_base_user_role_2` FOREIGN KEY (`role_id`) REFERENCES `base_roles` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户角色表';

--
-- Dumping data for table `base_user_role`
--

/*!40000 ALTER TABLE `base_user_role` DISABLE KEYS */;
INSERT INTO `base_user_role` (`user_role_id`,`user_id`,`role_id`) VALUES 
 (1,2,1),
 (2,1,2);
/*!40000 ALTER TABLE `base_user_role` ENABLE KEYS */;


--
-- Definition of table `base_users`
--

DROP TABLE IF EXISTS `base_users`;
CREATE TABLE `base_users` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `account` varchar(64) NOT NULL COMMENT '账号',
  `password` varchar(128) NOT NULL COMMENT '密码\r\norg.springframework.security.crypto.password.StandardPasswordEncoder.StandardPasswordEncoder(CharSequence secret)',
  `real_name` varchar(64) DEFAULT NULL COMMENT '用户真实姓名',
  `sex` int(1) unsigned DEFAULT NULL COMMENT '性别 0:男 1:女',
  `email` varchar(64) DEFAULT NULL COMMENT '电子邮件地址',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机',
  `office_phone` varchar(32) DEFAULT NULL COMMENT '办公电话',
  `error_count` int(2) unsigned DEFAULT '0' COMMENT '密码错误次数',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `last_login_ip` varchar(32) DEFAULT NULL COMMENT '上次登录IP地址',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';

--
-- Dumping data for table `base_users`
--

/*!40000 ALTER TABLE `base_users` DISABLE KEYS */;
INSERT INTO `base_users` (`user_id`,`account`,`password`,`real_name`,`sex`,`email`,`mobile`,`office_phone`,`error_count`,`last_login_time`,`last_login_ip`,`remark`) VALUES 
 (1,'test','ddee6e95fae5bb5f8890a6f9ef7d0d1db744ca4417e94c05595ef280046a49021eba3291ee9c9cf8','测试用户',0,'test@qq.com','119','110',0,NULL,NULL,NULL),
 (2,'admin','6043ae1095884cf9663d140ee6450b49b8489b3aa073a8eec024492b976ee2a24aee0c272369121b','超级管理员',0,'admin@whty.com.cn','119','110',0,NULL,'127.0.0.1','用户信息');
/*!40000 ALTER TABLE `base_users` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

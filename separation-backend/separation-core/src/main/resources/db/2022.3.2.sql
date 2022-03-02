/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.5.40 : Database - commontemplate
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`commontemplate` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `commontemplate`;

/*Table structure for table `sr_permission` */

DROP TABLE IF EXISTS `sr_permission`;

CREATE TABLE `sr_permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `permission_unique_id` bigint(20) DEFAULT NULL COMMENT '唯一id',
  `permission_url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '前端对应菜单导航栏router_url(可以为空)根据前端设计来',
  `permission_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '后端shiro权限命名',
  `permission_description` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `permission_fid` int(20) DEFAULT NULL COMMENT '权限的fid',
  `permission_enable` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除(删除就为1)',
  PRIMARY KEY (`permission_id`,`permission_enable`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sr_permission` */

insert  into `sr_permission`(`permission_id`,`permission_unique_id`,`permission_url`,`permission_name`,`permission_description`,`permission_fid`,`permission_enable`) values (1,NULL,NULL,'test:manage','测试管理',0,0),(2,NULL,NULL,'test:list','查看测试',1,0),(3,NULL,NULL,'test:update','更新测试',1,0),(4,NULL,NULL,'test:insert','插入测试',1,0),(5,NULL,NULL,'test:delete','删除测试',1,0);

/*Table structure for table `sr_role` */

DROP TABLE IF EXISTS `sr_role`;

CREATE TABLE `sr_role` (
  `role_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '角色id(主键自增)',
  `role_unique_id` bigint(20) DEFAULT NULL COMMENT '角色唯一id',
  `role_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '角色名字',
  `role_description` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '角色描述',
  `role_enable` int(1) DEFAULT '0' COMMENT '是否删除(1就是删除)',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sr_role` */

insert  into `sr_role`(`role_id`,`role_unique_id`,`role_name`,`role_description`,`role_enable`) values (1,NULL,'superAdmin','超级管理员',0),(2,NULL,'admin','管理员',0),(3,NULL,'test','测试',0);

/*Table structure for table `sr_role_permission` */

DROP TABLE IF EXISTS `sr_role_permission`;

CREATE TABLE `sr_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录的id',
  `rid` int(11) DEFAULT NULL COMMENT 'roleId',
  `pid` int(11) DEFAULT NULL COMMENT 'permissionId',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sr_role_permission` */

insert  into `sr_role_permission`(`id`,`rid`,`pid`) values (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,2,1),(7,2,2),(8,2,3),(9,3,1),(10,3,2);

/*Table structure for table `sr_user` */

DROP TABLE IF EXISTS `sr_user`;

CREATE TABLE `sr_user` (
  `user_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '用户id(主键自增)',
  `user_unique_id` bigint(20) DEFAULT NULL COMMENT '雪花算法唯一id',
  `user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户登录名(唯一)',
  `user_nick` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户密码',
  `status` char(1) COLLATE utf8_bin DEFAULT '1' COMMENT '用户状态',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `update_time` date DEFAULT NULL COMMENT '更新时间',
  `user_email` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户邮箱',
  `user_avatar` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户头像图片地址',
  `user_enable` int(1) DEFAULT '0' COMMENT '是否被删除(1就是被删)',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sr_user` */

insert  into `sr_user`(`user_id`,`user_unique_id`,`user_name`,`user_nick`,`password`,`status`,`create_time`,`update_time`,`user_email`,`user_avatar`,`user_enable`) values (1,NULL,'superAdmin','superAdmin','968bfde255b8fe2ff27f442e811073b5','1',NULL,NULL,NULL,NULL,0),(2,NULL,'admin','admin','bc8712b62f4e50fa3c54d70796fb806a','1',NULL,NULL,NULL,NULL,0),(3,NULL,'test','test','d74211014d043d25928252d691b78209','1',NULL,NULL,NULL,NULL,0);

/*Table structure for table `sr_user_role` */

DROP TABLE IF EXISTS `sr_user_role`;

CREATE TABLE `sr_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT 'userId',
  `rid` int(11) DEFAULT NULL COMMENT 'roleId',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sr_user_role` */

insert  into `sr_user_role`(`id`,`uid`,`rid`) values (1,1,1),(2,2,2),(3,3,3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

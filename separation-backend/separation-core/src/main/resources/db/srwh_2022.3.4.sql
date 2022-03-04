/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.5.40 : Database - srworkhoursmanage
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`srworkhoursmanage` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `srworkhoursmanage`;

/*Table structure for table `sr_audit` */

DROP TABLE IF EXISTS `sr_audit`;

CREATE TABLE `sr_audit` (
  `audit_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '审核id',
  `audit_unique_id` bigint(20) unsigned zerofill DEFAULT NULL COMMENT '全局唯一id',
  `user_job_id` int(11) DEFAULT NULL COMMENT '审核任务id',
  `audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  `audit_person_id` int(11) DEFAULT NULL COMMENT '审核人id',
  `audit_enable` int(11) DEFAULT NULL COMMENT '是否删除(1删除)',
  PRIMARY KEY (`audit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sr_audit` */

/*Table structure for table `sr_department` */

DROP TABLE IF EXISTS `sr_department`;

CREATE TABLE `sr_department` (
  `department_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `department_unique_id` bigint(20) DEFAULT NULL COMMENT '全局唯一id',
  `department_name` varchar(255) COLLATE utf8_bin DEFAULT '255' COMMENT '部门名字',
  `department_description` varchar(255) COLLATE utf8_bin DEFAULT '255' COMMENT '部门描述',
  `department_fid` int(11) DEFAULT NULL COMMENT '部门所属fid',
  `department_enable` int(11) unsigned DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sr_department` */

/*Table structure for table `sr_job` */

DROP TABLE IF EXISTS `sr_job`;

CREATE TABLE `sr_job` (
  `job_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '实验id',
  `job_unique_id` bigint(20) DEFAULT NULL COMMENT '全局唯一id',
  `job_name` varchar(255) COLLATE utf8_bin DEFAULT '255' COMMENT '实验名字',
  `job_description` varchar(255) COLLATE utf8_bin DEFAULT '255' COMMENT '实验描述',
  `job_fid` int(11) DEFAULT NULL COMMENT '实验所属id',
  `job_enable` int(11) DEFAULT NULL COMMENT '是否删除(1就是删除)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sr_job` */

/*Table structure for table `sr_permission` */

DROP TABLE IF EXISTS `sr_permission`;

CREATE TABLE `sr_permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `permission_unique_id` bigint(20) DEFAULT NULL COMMENT '唯一id',
  `permission_url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '前端对应菜单导航栏router_url(可以为空)根据前端设计来',
  `permission_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '后端shiro权限命名',
  `permission_description` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `permission_fid` int(20) DEFAULT NULL COMMENT '权限的fid',
  `permission_enable` int(1) DEFAULT '0' COMMENT '是否删除(删除就为1)',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sr_permission` */

insert  into `sr_permission`(`permission_id`,`permission_unique_id`,`permission_url`,`permission_name`,`permission_description`,`permission_fid`,`permission_enable`) values (1,NULL,'/test','test:manage','测试管理',0,0),(2,NULL,'/test/list','test:list','查看测试',1,0),(3,NULL,'/test/update','test:update','更新测试',1,0),(4,NULL,'/test/insert','test:insert','插入测试',1,0),(5,NULL,'/test/delete','test:delete','删除测试',1,0);

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
  `status` int(1) DEFAULT '1' COMMENT '用户状态',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `update_time` date DEFAULT NULL COMMENT '更新时间',
  `user_email` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户邮箱',
  `user_avatar` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户头像图片地址',
  `user_enable` int(1) DEFAULT '0' COMMENT '是否被删除(1就是被删)',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sr_user` */

insert  into `sr_user`(`user_id`,`user_unique_id`,`user_name`,`user_nick`,`password`,`status`,`create_time`,`update_time`,`user_email`,`user_avatar`,`user_enable`) values (1,NULL,'superAdmin','superAdmin','968bfde255b8fe2ff27f442e811073b5',1,NULL,NULL,NULL,NULL,0),(2,NULL,'admin','admin','bc8712b62f4e50fa3c54d70796fb806a',1,NULL,NULL,NULL,NULL,0),(3,NULL,'test','test','d74211014d043d25928252d691b78209',1,NULL,NULL,NULL,NULL,0);

/*Table structure for table `sr_user_job` */

DROP TABLE IF EXISTS `sr_user_job`;

CREATE TABLE `sr_user_job` (
  `user_job_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户任务id',
  `user_job_unique_id` bigint(20) DEFAULT NULL COMMENT '全局唯一id',
  `u_id` int(11) DEFAULT NULL COMMENT '用户id',
  `j_id` int(11) DEFAULT NULL COMMENT '项目id',
  `first_time` datetime DEFAULT NULL COMMENT '任务起始时间',
  `last_time` datetime DEFAULT NULL COMMENT '任务结束时间',
  `total_time` datetime DEFAULT NULL COMMENT '任务持续时间',
  `push_time` datetime DEFAULT NULL COMMENT '提交表单时间',
  `user_job_enable` int(11) DEFAULT NULL COMMENT '是否删除(1删除)',
  `is_audit` int(11) DEFAULT '0' COMMENT '是否审核',
  `audit_id` int(11) DEFAULT '0' COMMENT '审核id(默认为0)',
  `update_time` datetime DEFAULT NULL COMMENT '更新表单时间',
  PRIMARY KEY (`user_job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sr_user_job` */

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

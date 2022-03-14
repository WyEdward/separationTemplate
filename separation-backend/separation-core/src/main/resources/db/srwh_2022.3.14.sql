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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sr_department` */

insert  into `sr_department`(`department_id`,`department_unique_id`,`department_name`,`department_description`,`department_fid`,`department_enable`,`create_time`,`update_time`) values (1,0,'部门1','内容1',NULL,0,'2022-03-07 18:14:23','2022-03-07 18:25:38'),(2,0,'部门2','内容2',NULL,0,'2022-03-07 18:14:33',NULL),(3,0,'部门3','内容3',NULL,0,'2022-03-07 18:14:41',NULL),(4,0,'部门4','内容4',NULL,0,'2022-03-07 18:16:40',NULL),(11,0,'sdfsdf','sdfsd',NULL,0,'2022-03-07 18:30:03',NULL),(12,0,'sdfds','fsdf ',NULL,0,'2022-03-07 18:31:12',NULL),(13,0,'255','255',NULL,0,'2022-03-07 18:43:39',NULL),(14,0,'sdfsd','dsfsd',NULL,0,'2022-03-07 18:44:06',NULL),(15,0,'255','sfdsd',NULL,0,'2022-03-07 18:44:13',NULL),(16,0,'255','dsff',NULL,0,'2022-03-07 18:44:36',NULL),(17,0,'sdf','sdf',NULL,0,'2022-03-07 19:06:23',NULL),(18,0,'sdfsd','sdfsd',NULL,0,'2022-03-07 19:19:54',NULL),(19,0,'sdfsd','sdfsd',NULL,0,'2022-03-07 19:22:50',NULL),(20,0,'sdfsd','sdfsd',NULL,0,'2022-03-07 19:23:58',NULL),(24,0,'dsfsdfsdf','sdfsdf',NULL,0,'2022-03-07 19:59:05',NULL),(25,950783177729904640,'测试部门4','dsfsd',NULL,0,'2022-03-08 15:52:54',NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sr_job` */

insert  into `sr_job`(`job_id`,`job_unique_id`,`job_name`,`job_description`,`job_fid`,`job_enable`,`create_time`,`update_time`) values (1,0,'测试项目1','测试内容1',0,0,NULL,'2022-03-07 14:52:29'),(2,0,'测试项目2','测试描述内容2',0,0,NULL,'2022-03-07 16:24:52'),(3,0,'测试项目3','测试描述3',0,0,NULL,NULL),(6,0,'测试6','内容6',0,0,NULL,NULL),(13,0,'sdfsd','sdfds',NULL,NULL,'2022-03-07 15:56:16',NULL),(14,0,'sdf','dsfsd',NULL,NULL,'2022-03-07 15:56:20',NULL),(15,0,'sdfsd','fdsfsd',NULL,NULL,'2022-03-07 15:56:27',NULL),(23,0,'sdfsdfsdfsd','sdfsdf',NULL,NULL,'2022-03-07 19:58:56',NULL),(24,0,'项目1','hsdjkf',NULL,NULL,'2022-03-08 13:37:39',NULL);

/*Table structure for table `sr_permission` */

DROP TABLE IF EXISTS `sr_permission`;

CREATE TABLE `sr_permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `permission_unique_id` bigint(20) DEFAULT NULL COMMENT '唯一id',
  `permission_url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '前端对应菜单导航栏router_url(可以为空)根据前端设计来',
  `permission_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '后端shiro权限命名',
  `permission_description` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `permission_fid` int(20) DEFAULT '0' COMMENT '权限的fid',
  `permission_enable` int(1) DEFAULT '0' COMMENT '是否删除(删除就为1)',
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sr_permission` */

insert  into `sr_permission`(`permission_id`,`permission_unique_id`,`permission_url`,`permission_name`,`permission_description`,`permission_fid`,`permission_enable`) values (1,NULL,'/test','test:manage','测试管理',0,0),(2,NULL,'/test/list','test:list','查看测试',1,0),(3,NULL,'/test/update','test:update','更新测试',1,0),(4,NULL,'/test/insert','test:insert','插入测试',1,0),(5,NULL,'/test/delete','test:delete','删除测试',1,0),(6,NULL,'/hour','hour:manage','工时管理',0,0),(7,NULL,'/hour/job','job:manage','项目管理',6,0),(8,NULL,'/hour/job/create','job:insert','创建项目',7,0),(9,NULL,NULL,'job:update','更新项目',7,0),(10,NULL,'/hour/job/list','job:list','查看项目',7,0),(11,NULL,NULL,'job:delete','删除项目',7,0),(12,NULL,'/hour/department','department:manage','部门管理',6,0),(13,NULL,'/hour/department/create','department:insert','创建部门',12,0),(14,NULL,'','department:update','更新部门',12,0),(15,NULL,'/hour/department/list','department:list','查看部门',12,0),(16,NULL,'','department:delete','删除部门',12,0),(17,NULL,'/sys','sys:manage','系统管理',0,0),(18,NULL,'/sys/user','user:manage','用户管理',17,0),(19,NULL,'/sys/user/list','user:list','用户列表',18,0),(20,NULL,'/sys/user/create','user:create','用户创建',18,0),(21,NULL,NULL,'user:delete','用户删除',18,0),(22,NULL,NULL,'user:manage','用户更新',18,0),(23,NULL,'/sys/permission','permission:manage','权限管理',17,0),(24,NULL,'/sys/permission/list','permission:list','权限列表',23,0),(25,NULL,'/sys/permission/create','permission:create','权限创建',23,0),(26,NULL,'','permission:update','权限更新',23,0),(27,NULL,NULL,'permission:delete','权限删除',23,0),(28,NULL,'/sys/role','role:manage','角色管理',17,0),(29,NULL,'/sys/role/list','role:list','角色列表',28,0),(30,NULL,'/sys/role/create','role:create','角色创建',28,0),(31,NULL,'','role:update','角色更新',28,0),(32,NULL,NULL,'role:delete','角色删除',28,0),(39,951050320937619456,NULL,'测试添加','测试添加',0,0),(40,951050511409352704,NULL,'测试添加2','测试添加2',0,0),(41,951052128871387136,NULL,'测试添加3','测试添加3',39,0),(42,951052184630464512,NULL,'测试添加4','测试添加4',41,0),(43,951052227215233024,NULL,'测试添加5','测试添加5',42,0),(44,951052278100529152,NULL,'测试添加6','测试添加6',42,0),(45,951052350661988352,NULL,'测试添加7','测试添加7',41,0),(46,951052622511607808,NULL,'测试添加8','测试添加8',45,0),(47,951052667772342272,NULL,'测试添加9','测试添加9',46,0);

/*Table structure for table `sr_role` */

DROP TABLE IF EXISTS `sr_role`;

CREATE TABLE `sr_role` (
  `role_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '角色id(主键自增)',
  `role_unique_id` bigint(20) DEFAULT NULL COMMENT '角色唯一id',
  `role_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '角色名字',
  `role_description` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '角色描述',
  `role_enable` int(1) DEFAULT '0' COMMENT '是否删除(1就是删除)',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sr_role` */

insert  into `sr_role`(`role_id`,`role_unique_id`,`role_name`,`role_description`,`role_enable`) values (1,NULL,'superAdmin','超级管理员',0),(2,NULL,'admin','管理员',0),(3,NULL,'test','测试',0),(7,951639539833769984,'测试4','测试4',0);

/*Table structure for table `sr_role_permission` */

DROP TABLE IF EXISTS `sr_role_permission`;

CREATE TABLE `sr_role_permission` (
  `rp_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录的id',
  `rid` int(11) DEFAULT NULL COMMENT 'roleId',
  `pid` int(11) DEFAULT NULL COMMENT 'permissionId',
  PRIMARY KEY (`rp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=162 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sr_role_permission` */

insert  into `sr_role_permission`(`rp_id`,`rid`,`pid`) values (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(11,1,6),(12,1,7),(13,1,8),(14,1,9),(15,1,10),(16,1,11),(17,1,12),(18,1,13),(19,1,14),(20,1,15),(21,1,16),(22,1,17),(23,1,18),(24,1,19),(25,1,20),(26,1,21),(27,1,22),(28,1,23),(29,1,24),(30,1,25),(31,1,26),(32,1,27),(33,1,28),(34,1,29),(35,1,30),(36,1,31),(37,1,32),(41,3,2),(42,3,40),(43,3,1),(124,7,1),(125,7,2),(126,7,3),(127,7,4),(128,7,5),(129,7,6),(130,7,7),(131,7,8),(132,7,9),(133,7,10),(134,7,11),(135,7,12),(136,7,13),(137,7,14),(138,7,15),(139,7,16),(140,7,39),(141,7,41),(142,7,42),(143,7,43),(144,7,44),(145,7,45),(146,7,46),(147,7,47),(148,2,2),(149,2,3),(150,2,6),(151,2,7),(152,2,8),(153,2,9),(154,2,10),(155,2,11),(156,2,12),(157,2,13),(158,2,14),(159,2,15),(160,2,16),(161,2,1);

/*Table structure for table `sr_user` */

DROP TABLE IF EXISTS `sr_user`;

CREATE TABLE `sr_user` (
  `user_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '用户id(主键自增)',
  `user_unique_id` bigint(20) DEFAULT NULL COMMENT '雪花算法唯一id',
  `user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户登录名(唯一)',
  `user_nick` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户密码',
  `status` int(1) DEFAULT '0' COMMENT '用户状态',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `update_time` date DEFAULT NULL COMMENT '更新时间',
  `user_email` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户邮箱',
  `user_avatar` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户头像图片地址',
  `user_enable` int(1) DEFAULT '0' COMMENT '是否被删除(1就是被删)',
  `user_department` int(11) DEFAULT NULL COMMENT '用户部门',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sr_user` */

insert  into `sr_user`(`user_id`,`user_unique_id`,`user_name`,`user_nick`,`password`,`status`,`create_time`,`update_time`,`user_email`,`user_avatar`,`user_enable`,`user_department`) values (1,NULL,'superAdmin','superAdmin','ff6fd6593f362775bca116f9d2cc8e1c',0,NULL,NULL,'12312',NULL,0,1),(2,NULL,'admin','admin','f086a112004a6e0c9b9984855c971c7d',0,NULL,NULL,NULL,NULL,0,2),(3,NULL,'test','test','e5b33dd67c6e8d6d0b5d4cab11d1aeff',0,NULL,NULL,NULL,NULL,0,1);

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
  `ur_id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT 'userId',
  `rid` int(11) DEFAULT NULL COMMENT 'roleId',
  PRIMARY KEY (`ur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sr_user_role` */

insert  into `sr_user_role`(`ur_id`,`uid`,`rid`) values (1,1,1),(2,2,2),(3,3,3),(4,1,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

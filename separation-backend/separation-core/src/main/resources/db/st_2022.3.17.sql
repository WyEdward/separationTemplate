/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.5.40 : Database - separationtemplate
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`separationtemplate` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `separationtemplate`;

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sr_department` */

insert  into `sr_department`(`department_id`,`department_unique_id`,`department_name`,`department_description`,`department_fid`,`department_enable`,`create_time`,`update_time`) values (1,0,'部门1','内容1',NULL,0,'2022-03-07 18:14:23','2022-03-07 18:25:38'),(2,0,'部门2','内容2',NULL,0,'2022-03-07 18:14:33',NULL),(3,0,'部门3','内容3',NULL,0,'2022-03-07 18:14:41',NULL),(4,0,'部门4','内容4',NULL,0,'2022-03-07 18:16:40',NULL),(11,0,'sdfsdf','sdfsd',NULL,0,'2022-03-07 18:30:03',NULL),(12,0,'sdfds','fsdf ',NULL,0,'2022-03-07 18:31:12',NULL),(13,0,'255','255',NULL,0,'2022-03-07 18:43:39',NULL),(14,0,'sdfsd','dsfsd',NULL,0,'2022-03-07 18:44:06',NULL),(15,0,'255','sfdsd',NULL,0,'2022-03-07 18:44:13',NULL),(16,0,'255','dsff',NULL,0,'2022-03-07 18:44:36',NULL),(17,0,'sdf','sdf',NULL,0,'2022-03-07 19:06:23',NULL),(18,0,'sdfsd','sdfsd',NULL,0,'2022-03-07 19:19:54',NULL),(19,0,'sdfsd','sdfsd',NULL,0,'2022-03-07 19:22:50',NULL),(20,0,'sdfsd','sdfsd1',NULL,0,'2022-03-07 19:23:58','2022-03-16 15:06:53');

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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sr_job` */

insert  into `sr_job`(`job_id`,`job_unique_id`,`job_name`,`job_description`,`job_fid`,`job_enable`,`create_time`,`update_time`) values (1,0,'测试项目1','测试内容1',0,0,NULL,'2022-03-07 14:52:29'),(2,0,'测试项目2','测试描述内容2',0,0,NULL,'2022-03-07 16:24:52'),(3,0,'测试项目3','测试描述3',0,0,NULL,NULL),(6,0,'测试6','内容6',0,0,NULL,NULL),(13,0,'sdfsd','sdfds',NULL,NULL,'2022-03-07 15:56:16',NULL),(14,0,'sdf','dsfsd',NULL,NULL,'2022-03-07 15:56:20',NULL),(15,0,'sdfsd','fdsfsd',NULL,NULL,'2022-03-07 15:56:27',NULL),(23,0,'sdfsdfsdfsd','sdfsdf',NULL,NULL,'2022-03-07 19:58:56',NULL),(24,0,'项目1','hsdjkf',NULL,NULL,'2022-03-08 13:37:39',NULL),(25,952927744314970112,'测试21','测试21',NULL,NULL,'2022-03-14 13:54:38',NULL),(26,952927773138227200,'测试22','测试22',NULL,NULL,'2022-03-14 13:54:45',NULL),(27,952927804134133760,'测试23','测试23',NULL,NULL,'2022-03-14 13:54:53',NULL),(28,952927865207394304,'测试23','测试23',NULL,NULL,'2022-03-14 13:55:07',NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sr_permission` */

insert  into `sr_permission`(`permission_id`,`permission_unique_id`,`permission_url`,`permission_name`,`permission_description`,`permission_fid`,`permission_enable`) values (1,NULL,'/test','test:manage','测试管理',0,0),(2,NULL,'/test/list','test:list','查看测试',1,0),(3,NULL,'/test/update','test:update','更新测试',1,0),(4,NULL,'/test/insert','test:insert','插入测试',1,0),(5,NULL,'/test/delete','test:delete','删除测试',1,0),(6,NULL,'/hour','hour:manage','工时管理',0,0),(7,NULL,'/hour/job','job:manage','项目管理',6,0),(8,NULL,'/hour/job/create','job:insert','创建项目',7,0),(9,NULL,NULL,'job:update','更新项目',7,0),(10,NULL,'/hour/job/list','job:list','查看项目',7,0),(11,NULL,NULL,'job:delete','删除项目',7,0),(12,NULL,'/hour/department','department:manage','部门管理',6,0),(13,NULL,'/hour/department/create','department:insert','创建部门',12,0),(14,NULL,'','department:update','更新部门',12,0),(15,NULL,'/hour/department/list','department:list','查看部门',12,0),(16,NULL,'','department:delete','删除部门',12,0),(17,NULL,'/sys','sys:manage','系统管理',0,0),(18,NULL,'/sys/user','user:manage','用户管理',17,0),(19,NULL,'/sys/user/list','user:list','用户列表',18,0),(20,NULL,'/sys/user/create','user:create','用户创建',18,0),(21,NULL,NULL,'user:delete','用户删除',18,0),(22,NULL,NULL,'user:manage','用户更新',18,0),(23,NULL,'/sys/permission','permission:manage','权限管理',17,0),(24,NULL,'/sys/permission/list','permission:list','权限列表',23,0),(25,NULL,'/sys/permission/create','permission:create','权限创建',23,0),(26,NULL,'','permission:update','权限更新',23,0),(27,NULL,NULL,'permission:delete','权限删除',23,0),(28,NULL,'/sys/role','role:manage','角色管理',17,0),(29,NULL,'/sys/role/list','role:list','角色列表',28,0),(30,NULL,'/sys/role/create','role:create','角色创建',28,0),(31,NULL,'','role:update','角色更新',28,0),(32,NULL,NULL,'role:delete','角色删除',28,0),(33,953231166427762688,'/sys/password','password:manage','密码管理',17,0),(34,953231367511085056,'/sys/password/reset','password:reset','修改密码',33,0),(35,953282358751137792,'/sys/person','person:manage','个人管理',17,0),(36,953282510421364736,'/sys/person/view','person:list','资料查看',35,0),(37,953282814495821824,'/sys/person/update','person:update','资料更新',35,0);

/*Table structure for table `sr_role` */

DROP TABLE IF EXISTS `sr_role`;

CREATE TABLE `sr_role` (
  `role_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '角色id(主键自增)',
  `role_unique_id` bigint(20) DEFAULT NULL COMMENT '角色唯一id',
  `role_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '角色名字',
  `role_description` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '角色描述',
  `role_enable` int(1) DEFAULT '0' COMMENT '是否删除(1就是删除)',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sr_role` */

insert  into `sr_role`(`role_id`,`role_unique_id`,`role_name`,`role_description`,`role_enable`) values (1,NULL,'superAdmin','超级管理员',0),(2,NULL,'admin','管理员',0),(3,NULL,'test','测试',0),(7,951639539833769984,'测试4','测试4',0),(8,952944207444709376,'测试5','测试5',0);

/*Table structure for table `sr_role_permission` */

DROP TABLE IF EXISTS `sr_role_permission`;

CREATE TABLE `sr_role_permission` (
  `rp_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录的id',
  `rid` int(11) DEFAULT NULL COMMENT 'roleId',
  `pid` int(11) DEFAULT NULL COMMENT 'permissionId',
  PRIMARY KEY (`rp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=268 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sr_role_permission` */

insert  into `sr_role_permission`(`rp_id`,`rid`,`pid`) values (196,1,1),(197,1,2),(198,1,3),(199,1,4),(200,1,5),(201,1,6),(202,1,7),(203,1,8),(204,1,9),(205,1,10),(206,1,11),(207,1,12),(208,1,13),(209,1,14),(210,1,15),(211,1,16),(212,1,17),(213,1,18),(214,1,19),(215,1,20),(216,1,21),(217,1,22),(218,1,23),(219,1,24),(220,1,25),(221,1,26),(222,1,27),(223,1,28),(224,1,29),(225,1,30),(226,1,31),(227,1,32),(228,1,33),(229,1,34),(230,1,35),(231,1,36),(232,1,37),(233,2,1),(234,2,2),(235,2,3),(236,2,4),(237,2,5),(238,2,6),(239,2,7),(240,2,8),(241,2,9),(242,2,10),(243,2,11),(244,2,12),(245,2,13),(246,2,14),(247,2,15),(248,2,16),(249,2,33),(250,2,34),(251,2,35),(252,2,36),(253,2,37),(254,2,17),(255,3,2),(256,3,6),(257,3,7),(258,3,8),(259,3,9),(260,3,10),(261,3,11),(262,3,12),(263,3,13),(264,3,14),(265,3,15),(266,3,16),(267,3,1);

/*Table structure for table `sr_sys_log` */

DROP TABLE IF EXISTS `sr_sys_log`;

CREATE TABLE `sr_sys_log` (
  `sys_log_id` int(11) NOT NULL AUTO_INCREMENT,
  `sys_log_unique_id` bigint(255) DEFAULT NULL,
  `user_name` varchar(765) COLLATE utf8_bin DEFAULT NULL,
  `operation` varchar(765) COLLATE utf8_bin DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `method` varchar(765) COLLATE utf8_bin DEFAULT NULL,
  `params` varchar(765) COLLATE utf8_bin DEFAULT NULL,
  `ip` varchar(765) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `sys_log_enable` int(1) DEFAULT '0',
  PRIMARY KEY (`sys_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sr_sys_log` */

insert  into `sr_sys_log`(`sys_log_id`,`sys_log_unique_id`,`user_name`,`operation`,`time`,`method`,`params`,`ip`,`create_time`,`sys_log_enable`) values (1,NULL,'superAdmin','删除部门',161,'cn.wyedward.manage.hour.controller.DepartmentController.remove()','  departmentId: 25','0:0:0:0:0:0:0:1','2022-03-16 14:20:14',NULL),(2,NULL,'admin','删除部门',4,'cn.wyedward.manage.hour.controller.DepartmentController.remove()','  departmentId: 24','0:0:0:0:0:0:0:1','2022-03-16 14:21:04',NULL),(3,NULL,'superAdmin','创建更新部门',8,'cn.wyedward.manage.hour.controller.DepartmentController.insertOrUpdate()','  department: Department(departmentId=20, departmentUniqueId=null, departmentName=sdfsd, departmentDescription=sdfsd1, departmentFid=null, departmentEnable=null, createTime=null, updateTime=Wed Mar 16 15:06:53 CST 2022)','0:0:0:0:0:0:0:1','2022-03-16 15:06:53',NULL);

/*Table structure for table `sr_user` */

DROP TABLE IF EXISTS `sr_user`;

CREATE TABLE `sr_user` (
  `user_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '用户id(主键自增)',
  `user_unique_id` bigint(20) DEFAULT NULL COMMENT '雪花算法唯一id',
  `user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户登录名(唯一)',
  `user_nick` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户密码',
  `status` int(1) DEFAULT '0' COMMENT '用户状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `user_email` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户邮箱',
  `user_avatar` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户头像图片地址',
  `user_enable` int(1) DEFAULT '0' COMMENT '是否被删除(1就是被删)',
  `user_department` int(11) DEFAULT NULL COMMENT '用户部门',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sr_user` */

insert  into `sr_user`(`user_id`,`user_unique_id`,`user_name`,`user_nick`,`password`,`status`,`create_time`,`update_time`,`user_email`,`user_avatar`,`user_enable`,`user_department`) values (1,NULL,'superAdmin','superAdmin','ff6fd6593f362775bca116f9d2cc8e1c',0,NULL,NULL,'12312',NULL,0,1),(2,NULL,'admin','admin','f086a112004a6e0c9b9984855c971c7d',0,NULL,'2022-03-15 14:32:15','123214',NULL,0,2),(3,NULL,'test','test','e5b33dd67c6e8d6d0b5d4cab11d1aeff',0,NULL,'2022-03-14 13:29:25','sdfsdf',NULL,0,1),(4,952920989778448384,'test4','test4','f3376247f190a2ae2cec92d708faee11',0,'2022-03-14 00:00:00',NULL,'4444',NULL,0,NULL);

/*Table structure for table `sr_user_role` */

DROP TABLE IF EXISTS `sr_user_role`;

CREATE TABLE `sr_user_role` (
  `ur_id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT 'userId',
  `rid` int(11) DEFAULT NULL COMMENT 'roleId',
  PRIMARY KEY (`ur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sr_user_role` */

insert  into `sr_user_role`(`ur_id`,`uid`,`rid`) values (1,1,1),(4,1,2),(13,4,7),(14,3,7),(15,2,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

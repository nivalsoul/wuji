/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.9 : Database - wuji
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`wuji` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;

USE `wuji`;

/*Table structure for table `advice` */

DROP TABLE IF EXISTS `advice`;

CREATE TABLE `advice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
  `description` varchar(10000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
  `contact` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系方式',
  `subtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '提交时间',
  `answer` text COLLATE utf8mb4_unicode_ci COMMENT '回复',
  `category` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '内容分类：建议、问题等',
  `dealtime` timestamp NULL DEFAULT NULL COMMENT '处理时间',
  `hits` int(11) DEFAULT NULL COMMENT '阅读次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_title` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `article_content` longtext COLLATE utf8mb4_unicode_ci,
  `author` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `author_name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `pub_time` datetime DEFAULT NULL,
  `comment_num` int(11) DEFAULT NULL,
  `article_type` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `quotation` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '引用',
  `original` tinyint(1) DEFAULT '1',
  `article_link` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文章链接',
  `source` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `reference_id` bigint(20) DEFAULT NULL COMMENT '引用文章id',
  `read_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18021 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(20) DEFAULT NULL,
  `author` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `author_name` varchar(65) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `content` text COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `pub_time` datetime DEFAULT NULL,
  `praise_num` int(11) DEFAULT NULL COMMENT '点赞数',
  `source_id` bigint(20) DEFAULT NULL COMMENT '回复的评论id',
  `ref_comment_id` bigint(20) DEFAULT NULL COMMENT '引用的评论id',
  `quotation` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '引用内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Table structure for table `userinfo` */

DROP TABLE IF EXISTS `userinfo`;

CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `useraccount` varchar(64) NOT NULL COMMENT '用户账号',
  `username` varchar(64) DEFAULT NULL COMMENT '用户名称',
  `thirdaccount` varchar(64) DEFAULT NULL COMMENT '第三方账号',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `photo` varchar(128) DEFAULT NULL COMMENT '头像',
  `gender` char(4) DEFAULT NULL COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `address` varchar(256) DEFAULT NULL COMMENT '地址',
  `regtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `signature` varchar(128) DEFAULT NULL COMMENT '个人签名',
  `lastlogintime` timestamp NULL DEFAULT NULL COMMENT '上次登录时间',
  `treasure` int(11) DEFAULT '0' COMMENT '财富值',
  `level` int(11) DEFAULT '1' COMMENT '等级',
  `honor` varchar(32) DEFAULT NULL COMMENT '头衔',
  `exp` int(11) DEFAULT '10' COMMENT '经验值',
  `ifpush` tinyint(1) DEFAULT '1' COMMENT '是否推送消息',
  `contribution_num` int(11) DEFAULT '0' COMMENT '贡献数',
  `visit_num` int(11) DEFAULT '0' COMMENT '访问数',
  `follow_num` int(11) DEFAULT '0' COMMENT '关注数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

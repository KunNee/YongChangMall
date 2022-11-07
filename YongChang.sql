/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.17 : Database - yongchnag
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yongchnag` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `yongchnag`;

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标题',
  `vedio` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '视频播放地址',
  `summary` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '摘要',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '内容',
  `child_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '分类ID',
  `shop_ids` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `customer_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '客户ID',
  `time` datetime DEFAULT NULL COMMENT '发布时间',
  `status` int(11) DEFAULT NULL COMMENT '0 带审核 1 审核通过   2审核拒绝',
  `check_result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '检查结果',
  `top` tinyint(1) DEFAULT NULL COMMENT '是否置顶',
  `cover` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '封面也有可能是封面视频',
  `hot` int(11) DEFAULT NULL COMMENT '热度',
  `label` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标签',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='文章表';

/*Data for the table `article` */

/*Table structure for table `child_type` */

DROP TABLE IF EXISTS `child_type`;

CREATE TABLE `child_type` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='分类表';


DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'password',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
  `header` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `type` int(11) DEFAULT NULL COMMENT '1 普通客户  2认证客户',
  `auth_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '认证标签',
  `auth_status` int(11) DEFAULT NULL COMMENT '认证申请状态  0 未申请  1申请中  2已通过  3已拒绝',
  `auth_mark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '认证描述',
  `integral` int(100) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='客户表';

/*Data for the table `customer` */

insert  into `customer`(`id`,`name`,`phone`,`password`,`header`,`type`,`auth_name`,`auth_status`,`auth_mark`,`integral`) values ('wsy1236477136451903489','潇洒哥','13888888888','123456','https://s-y-1304244043.cos.ap-beijing.myqcloud.com/ToyMallImg/7afe7cc8f18545de8f50d8a7f560423f.jpg',2,'程序员',2,'程序员',9),('wsy1584051652319576065','kunnee','18888888888','123456','/img/a5.jpg',NULL,NULL,NULL,NULL,0);

/*Table structure for table `customer_address` */

DROP TABLE IF EXISTS `customer_address`;

CREATE TABLE `customer_address` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '地址',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '收货人',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '电话',
  `customer_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '客户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='客户收货地址';

/*Data for the table `customer_address` */

insert  into `customer_address`(`id`,`address`,`name`,`phone`,`customer_id`) values ('wsy1236567279410081794','北京','海绵宝宝','13888888888','wsy1236477136451903489');

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单名',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求地址',
  `type` int(11) DEFAULT NULL COMMENT '1级菜单  2级菜单',
  `p_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '父级ID',
  `time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='菜单表';

/*Data for the table `menu` */

insert  into `menu`(`id`,`name`,`url`,`type`,`p_id`,`time`) values ('1','系统管理',NULL,1,NULL,'2021-04-22 04:04:15'),('11','用户管理','/user/list.htm',2,'1','2021-04-22 04:04:16'),('12','角色管理','/role/list.htm',2,'1','2021-04-22 04:04:17'),('13','客户管理','/customer/list.htm',2,'1','2021-04-22 04:04:18'),('2','商品管理','',1,NULL,'2021-04-22 04:04:19'),('21','类型管理','/shoptype/list.htm',2,'2','2021-04-22 04:04:20'),('23','商品管理','/shop/list.htm',2,'2','2021-04-22 04:04:21'),('3','订单管理','',1,NULL,'2021-04-22 04:04:22'),('31','订单管理','/order/page.htm',2,'3','2021-04-22 04:04:23'),('4','文章管理',NULL,1,NULL,'2021-04-22 04:04:24'),('41','文章类型管理','/childType/indexPage.htm',2,'4','2021-04-22 04:04:26'),('42','文章管理','/article/list.htm',2,'4','2021-04-22 04:04:27');

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `price` decimal(11,2) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '1 已购买 2 已发货  3已收货  4 已评价',
  `time` datetime DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '地址',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '收货人',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '电话',
  `customer_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '客户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='订单表';

/*Data for the table `order` */

/*Table structure for table `order_shop` */

DROP TABLE IF EXISTS `order_shop`;

CREATE TABLE `order_shop` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `customer_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '客户iD',
  `shop_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `price` decimal(12,2) DEFAULT NULL COMMENT '价格',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `order_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `cover` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '封面',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '评价内容',
  `score` int(11) DEFAULT NULL COMMENT '评分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='订单购物详情';

/*Data for the table `order_shop` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色名',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态 1启用  0禁用',
  `time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='角色表';

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`status`,`time`) values ('1231','超级管理员',1,'2021-04-22 04:05:10'),('55d59e7a9ba34d898109d28d209f5bbc','管理员',1,'2021-04-22 04:05:12');

/*Table structure for table `role_menu` */

DROP TABLE IF EXISTS `role_menu`;

CREATE TABLE `role_menu` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色ID',
  `menu_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='角色菜单表';

/*Data for the table `role_menu` */

insert  into `role_menu`(`id`,`role_id`,`menu_id`) values ('wsy1386671197957177345','1231','1'),('wsy1386671198548574209','1231','2'),('wsy1386671199207079938','1231','3'),('wsy1386671199790088194','1231','4'),('wsy1386671200641531906','1231','11'),('wsy1386671201232928770','1231','12'),('wsy1386671201887240194','1231','13'),('wsy1386671202470248450','1231','21'),('wsy1386671203065839617','1231','23'),('wsy1386671203657236481','1231','31'),('wsy1386671204252827649','1231','41'),('wsy1386671204848418817','1231','42'),('wsy1387339082429706241','55d59e7a9ba34d898109d28d209f5bbc','2'),('wsy1387339083075629057','55d59e7a9ba34d898109d28d209f5bbc','4'),('wsy1387339083696386050','55d59e7a9ba34d898109d28d209f5bbc','21'),('wsy1387339085025980417','wsy1387339085025980417','wsy1387339085025980417'),('wsy1387339085755789314','55d59e7a9ba34d898109d28d209f5bbc','41'),('wsy1387339086376546305','55d59e7a9ba34d898109d28d209f5bbc','42');

/*Table structure for table `royalty` */

DROP TABLE IF EXISTS `royalty`;

CREATE TABLE `royalty` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `customer` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '客户ID',
  `mark` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `time` datetime DEFAULT NULL,
  `money` decimal(10,2) DEFAULT NULL COMMENT '钱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户提成表';

/*Data for the table `royalty` */

insert  into `royalty`(`id`,`customer`,`mark`,`time`,`money`) values ('wsy1387644814006173697','wsy1236477136451903489','订单下单抽取提成','2021-04-29 13:48:00','200.00');

/*Table structure for table `shop` */

DROP TABLE IF EXISTS `shop`;

CREATE TABLE `shop` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '商品名称',
  `summery` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '简单描述',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '内容',
  `type_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类型ID',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '封面',
  `price` decimal(8,2) DEFAULT NULL,
  `discount_price` decimal(8,2) DEFAULT NULL COMMENT '折扣价格',
  `score` double(11,2) DEFAULT NULL COMMENT '评分',
  `stock` int(11) DEFAULT NULL COMMENT '库存剩余',
  `hot` tinyint(1) DEFAULT NULL COMMENT ' 是否热卖 ',
  `status` tinyint(1) DEFAULT NULL COMMENT '是否上架',
  `label` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标签',
  `royalty` decimal(8,2) DEFAULT NULL COMMENT '提成占比',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='商品表';

/*Data for the table `shop` */

/*Table structure for table `shop_type` */

DROP TABLE IF EXISTS `shop_type`;

CREATE TABLE `shop_type` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='商品类型';

/*Data for the table `shop_type` */


/*Table structure for table `shopping_gat` */

DROP TABLE IF EXISTS `shopping_gat`;

CREATE TABLE `shopping_gat` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `customer_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `shop_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `cid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='购物车';

/*Data for the table `shopping_gat` */

insert  into `shopping_gat`(`id`,`customer_id`,`shop_id`,`num`,`cid`) values ('741300a82f9b4638932be085d9184d4a','wsy1236477136451903489','wsy1385088069769662466',1,NULL),('a10778ee1b3a41bd9803762809bb88c0','wsy1584051652319576065','wsy1385084170156802049',1,NULL),('fe5af58417f449868c5eebe758e0e773','wsy1584051652319576065','wsy1385088069769662466',1,NULL);


DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名  昵称 ',
  `login_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录名',
  `password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '登录密码',
  `sex` tinyint(1) DEFAULT NULL COMMENT ' 性别  1男  2女',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号码',
  `mark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '个人介绍',
  `head_img` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像地址',
  `status` tinyint(1) DEFAULT NULL COMMENT '1 启用  0 禁用',
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色ID',
  `time` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='用户表';

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`login_name`,`password`,`sex`,`phone`,`mark`,`head_img`,`status`,`role_id`,`time`) values ('0c10fbd2177e44ff9e4c3a05784657b5','理管员','kunnee','123456',1,'13888888888',NULL,NULL,1,'55d59e7a9ba34d898109d28d209f5bbc','2022-10-21 15:53:52'),('1','管理员','admin','123456',1,'13888888888','1','1',1,'1231','2022-10-20 17:37:15');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

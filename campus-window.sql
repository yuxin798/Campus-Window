-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: campus-window
-- ------------------------------------------------------
-- Server version	5.6.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chat_list`
--

DROP TABLE IF EXISTS `chat_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_list` (
  `list_id` varchar(32) NOT NULL COMMENT '聊天列表主键',
  `link_id` varchar(255) NOT NULL COMMENT '聊天主表id',
  `from_user_id` varchar(30) NOT NULL COMMENT '发送者',
  `to_user_id` varchar(30) NOT NULL COMMENT '接收者',
  `to_user_name` varchar(100) DEFAULT NULL,
  `to_user_avatar` varchar(100) DEFAULT NULL,
  `last_msg` varchar(100) DEFAULT NULL,
  `unread` int(11) DEFAULT NULL COMMENT '未读数',
  `status` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  `group_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`list_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_list`
--

LOCK TABLES `chat_list` WRITE;
/*!40000 ALTER TABLE `chat_list` DISABLE KEYS */;
INSERT INTO `chat_list` VALUES ('1','1','1','2','2','2','2',0,0,NULL);
/*!40000 ALTER TABLE `chat_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_message`
--

DROP TABLE IF EXISTS `chat_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_message` (
  `message_id` varchar(32) NOT NULL COMMENT '聊天内容id',
  `link_id` varchar(255) DEFAULT NULL COMMENT '聊天主表id',
  `from_user_Id` varchar(30) NOT NULL COMMENT '发送者',
  `to_user_id` varchar(30) DEFAULT NULL COMMENT '接收者',
  `content` varchar(255) NOT NULL COMMENT '聊天内容',
  `send_time` datetime NOT NULL COMMENT '发送时间',
  `type` int(11) NOT NULL COMMENT '消息类型',
  `is_latest` tinyint(4) DEFAULT NULL COMMENT '是否为最后一条信息',
  PRIMARY KEY (`message_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_message`
--

LOCK TABLES `chat_message` WRITE;
/*!40000 ALTER TABLE `chat_message` DISABLE KEYS */;
INSERT INTO `chat_message` VALUES ('2ab1fd1f83204dada26cb5ddfb509fcd','1','1','2','hello','2023-12-01 13:04:57',0,NULL),('68ed09af3d994120883ee27a536e101c','1','1','2','hello','2023-12-01 13:00:52',0,0),('7792c8445eea4f76a6b45bbf852b2c68','1','1','2','hello','2023-12-01 13:03:15',0,0),('b463bc363bab427c8c2e2a05017f1e5c','1','1','2','hello','2023-12-01 13:03:00',0,0);
/*!40000 ALTER TABLE `chat_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_user_link`
--

DROP TABLE IF EXISTS `chat_user_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_user_link` (
  `link_id` varchar(255) NOT NULL COMMENT '聊天主表id',
  `from_user_id` varchar(30) NOT NULL COMMENT '发送者',
  `to_user_id` varchar(30) DEFAULT NULL COMMENT '接收者',
  `create_time` datetime DEFAULT NULL COMMENT '创建关联时间',
  PRIMARY KEY (`link_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_user_link`
--

LOCK TABLES `chat_user_link` WRITE;
/*!40000 ALTER TABLE `chat_user_link` DISABLE KEYS */;
INSERT INTO `chat_user_link` VALUES ('1','1','2','2023-11-29 19:40:23');
/*!40000 ALTER TABLE `chat_user_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_user_link_group`
--

DROP TABLE IF EXISTS `chat_user_link_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_user_link_group` (
  `public_id` varchar(100) DEFAULT NULL,
  `group_id` varchar(100) DEFAULT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_user_link_group`
--

LOCK TABLES `chat_user_link_group` WRITE;
/*!40000 ALTER TABLE `chat_user_link_group` DISABLE KEYS */;
INSERT INTO `chat_user_link_group` VALUES ('a64ecb07dd8f4bacba54fe8e732d44f1','8b7048a2ba2c4cce883e8fb67098b6f9','1','2023-12-01 12:35:15'),('7c476f456183466f8bd3c77626e02753','8b7048a2ba2c4cce883e8fb67098b6f9','2','2023-12-01 12:35:15'),('ae6f2ae966504c769b6d43f0445c89a7','3d73234892734a86b249ae15eb882e14','1','2023-12-01 12:35:34'),('fb2c6a95d07c432c9139ef7cd55e06ab','3d73234892734a86b249ae15eb882e14','2','2023-12-01 12:35:34');
/*!40000 ALTER TABLE `chat_user_link_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_activity_entertainment`
--

DROP TABLE IF EXISTS `tbl_activity_entertainment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_activity_entertainment` (
  `activity_id` varchar(32) NOT NULL,
  `activity_title` varchar(32) DEFAULT NULL,
  `activity_content` varchar(255) DEFAULT NULL,
  `date` datetime NOT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `avatar` varchar(100) DEFAULT NULL,
  `school` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`activity_id`)
) ENGINE=MEMORY DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_activity_entertainment`
--

LOCK TABLES `tbl_activity_entertainment` WRITE;
/*!40000 ALTER TABLE `tbl_activity_entertainment` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_activity_entertainment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_activity_learning`
--

DROP TABLE IF EXISTS `tbl_activity_learning`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_activity_learning` (
  `activity_id` varchar(100) NOT NULL,
  `activity_title` varchar(100) DEFAULT NULL,
  `activity_content` varchar(100) DEFAULT NULL,
  `date` datetime NOT NULL,
  `user_id` varchar(100) NOT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `avatar` varchar(100) DEFAULT NULL,
  `school` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_activity_learning`
--

LOCK TABLES `tbl_activity_learning` WRITE;
/*!40000 ALTER TABLE `tbl_activity_learning` DISABLE KEYS */;
INSERT INTO `tbl_activity_learning` VALUES ('92b030446b6049a793afef6c927c9008','朕亲临','吾皇万岁万岁万岁','2023-11-30 23:01:19','1','1','http://192.168.144.132:9000/campus-bucket/entertainments/3395e117e9604001b1a4e14310926199.jpg','河北师范大学');
/*!40000 ALTER TABLE `tbl_activity_learning` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_activity_mate`
--

DROP TABLE IF EXISTS `tbl_activity_mate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_activity_mate` (
  `activity_id` varchar(100) NOT NULL,
  `activity_title` varchar(100) DEFAULT NULL,
  `activity_content` varchar(100) DEFAULT NULL,
  `date` datetime NOT NULL,
  `user_id` varchar(100) NOT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `avatar` varchar(100) DEFAULT NULL,
  `school` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_activity_mate`
--

LOCK TABLES `tbl_activity_mate` WRITE;
/*!40000 ALTER TABLE `tbl_activity_mate` DISABLE KEYS */;
INSERT INTO `tbl_activity_mate` VALUES ('e1441cf6a87c411394b0789174900e06','朕亲临','吾皇万岁万岁万岁','2023-11-30 23:02:46','1','1','http://192.168.144.132:9000/campus-bucket/entertainments/3395e117e9604001b1a4e14310926199.jpg','河北师范大学');
/*!40000 ALTER TABLE `tbl_activity_mate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_msg`
--

DROP TABLE IF EXISTS `tbl_msg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_msg` (
  `msg_id` varchar(32) NOT NULL,
  `msg_content` varchar(100) DEFAULT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `userName` varchar(100) DEFAULT NULL,
  `avatar` varchar(100) DEFAULT NULL,
  `isSend` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`msg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_msg`
--

LOCK TABLES `tbl_msg` WRITE;
/*!40000 ALTER TABLE `tbl_msg` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_msg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_school`
--

DROP TABLE IF EXISTS `tbl_school`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_school` (
  `school_id` int(4) NOT NULL AUTO_INCREMENT,
  `school_name` varchar(16) NOT NULL,
  `school_logo` varchar(128) NOT NULL,
  PRIMARY KEY (`school_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_school`
--

LOCK TABLES `tbl_school` WRITE;
/*!40000 ALTER TABLE `tbl_school` DISABLE KEYS */;
INSERT INTO `tbl_school` VALUES (1,'河北师范大学','D:\\images\\schools\\河北师范大学.jpg'),(2,'河北科技大学','D:\\images\\schools\\河北科技大学.jpg'),(3,'清华大学','D:\\images\\schools\\清华大学.jpg');
/*!40000 ALTER TABLE `tbl_school` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_user` (
  `user_id` varchar(32) NOT NULL,
  `user_name` varchar(16) NOT NULL,
  `email` varchar(32) NOT NULL,
  `password` varchar(16) NOT NULL,
  `school` varchar(16) NOT NULL,
  `avatar` varchar(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user`
--

LOCK TABLES `tbl_user` WRITE;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` VALUES ('1','1','1@qq.com','1','河北师范大学','http://192.168.144.132:9000/campus-bucket/entertainments/3395e117e9604001b1a4e14310926199.jpg','2023-11-22 16:42:31'),('2','欲心','789@qq.com','123456','123','http://192.168.144.132:9000/campus-bucket/avatar/20231126/57accd755a0340c6b39e6e05569c494b.png','2023-11-22 17:15:10'),('f88d909b2fb9494abc3fcdb313bb87f0','zhjm','728753465@qq.com','521125','school','http://192.168.144.132:9000/campus-bucket/avatar/20231126/57accd755a0340c6b39e6e05569c494b.png','2023-11-22 16:42:31');
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'campus-window'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-01 14:22:22

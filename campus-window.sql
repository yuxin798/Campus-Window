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
  `from_window` tinyint(4) DEFAULT NULL,
  `to_window` tinyint(4) DEFAULT NULL,
  `last_msg` varchar(100) DEFAULT NULL,
  `unread` int(11) DEFAULT NULL COMMENT '未读数',
  `status` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`list_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_list`
--

LOCK TABLES `chat_list` WRITE;
/*!40000 ALTER TABLE `chat_list` DISABLE KEYS */;
INSERT INTO `chat_list` VALUES ('1','3ed9aafaeaad46d1a89f8735a4563486','1','2',0,0,'广告词',0,1),('2','3ed9aafaeaad46d1a89f8735a4563486','2','1',0,0,'广告词',0,1);
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
  PRIMARY KEY (`message_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_message`
--

LOCK TABLES `chat_message` WRITE;
/*!40000 ALTER TABLE `chat_message` DISABLE KEYS */;
INSERT INTO `chat_message` VALUES ('114894428cde4e6db762aedd5750c4fb','3ed9aafaeaad46d1a89f8735a4563486','1','2','313131','2023-12-05 21:59:04',0),('14a0169524c5451eaebc430a77b67df4','3ed9aafaeaad46d1a89f8735a4563486','1','2','2424242','2023-12-05 22:36:21',0),('1e72cae6c7de4fb79713306a5c25fb2a','3ed9aafaeaad46d1a89f8735a4563486','2','1','2222','2023-12-05 22:36:19',0),('1fb81f4afe4040608d963081f8bee9a1','3ed9aafaeaad46d1a89f8735a4563486','1','2','13131','2023-12-05 22:00:44',0),('3ead4613138e333341313563486','3ed9aafaeaad46d1a89f8735a4563486','2','1','bbb1','1970-01-21 00:43:06',0),('3f0462c79ed7468b84b1e0f40be23050','3ed9aafaeaad46d1a89f8735a4563486','1','2','213131','2023-12-05 22:35:13',0),('402eb0e57cc64b8bb64cb97c8e2b087b','3ed9aafaeaad46d1a89f8735a4563486','2','1','2424','2023-12-05 22:50:45',0),('40c54640d7dc4c4993aaa189cf7a2423','3ed9aafaeaad46d1a89f8735a4563486','2','1','广告词','2023-12-05 22:51:21',0),('41893ac4a59d43879437aaf71fa02e2c','3ed9aafaeaad46d1a89f8735a4563486','2','1','111111111111111111','2023-12-05 22:35:12',0),('427829126c2d4642871b5cfb7f136d32','3ed9aafaeaad46d1a89f8735a4563486','1','2','13131','2023-12-05 21:41:56',0),('4b93e828ef21414cade4dec22863f9c5','3ed9aafaeaad46d1a89f8735a4563486','1','2','aaaaaaaaaaaa','2023-12-05 22:35:21',0),('4cba5177e931407687e621fae3b28905','3ed9aafaeaad46d1a89f8735a4563486','1','2','13131','2023-12-05 21:44:45',0),('81d98397d7a547f6a6c4ead5b572942f','3ed9aafaeaad46d1a89f8735a4563486','1','2','1321','2023-12-05 21:42:13',0),('847ac969c55f4a80ad66a61d6472fdf2','3ed9aafaeaad46d1a89f8735a4563486','1','2','23131','2023-12-05 22:33:34',0),('847f4311dd784ad18f60e182ab8f1001','3ed9aafaeaad46d1a89f8735a4563486','1','2','3131','2023-12-05 22:51:05',0),('899439f5fb334096a6c4f4c90dbfab3b','3ed9aafaeaad46d1a89f8735a4563486','1','2','`2`2','2023-12-05 22:39:08',0),('90786c3f016144609077e89a2c41dbad','3ed9aafaeaad46d1a89f8735a4563486','2','1','13131','2023-12-05 22:35:19',0),('91f59a4ca48044a48b68e26bacf49d49','3ed9aafaeaad46d1a89f8735a4563486','2','1','313131','2023-12-05 22:51:06',0),('9d13a4e36bde4640b8b7524fa03043b4','3ed9aafaeaad46d1a89f8735a4563486','2','1','131313','2023-12-05 22:47:27',0),('9e39c27d971740d78f04b0ef7672049c','3ed9aafaeaad46d1a89f8735a4563486','1','2','w ao\n','2023-12-05 22:51:21',0),('da393642fd1845c5bc3cbe537d23d86e','3ed9aafaeaad46d1a89f8735a4563486','1','2','13131','2023-12-05 22:33:27',0);
/*!40000 ALTER TABLE `chat_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_user_link`
--

DROP TABLE IF EXISTS `chat_user_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_user_link` (
  `id` varchar(100) NOT NULL,
  `link_id` varchar(255) NOT NULL COMMENT '聊天主表id',
  `from_user_id` varchar(30) NOT NULL COMMENT '发送者',
  `to_user_id` varchar(30) DEFAULT NULL COMMENT '接收者',
  `create_time` datetime DEFAULT NULL COMMENT '创建关联时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_user_link`
--

LOCK TABLES `chat_user_link` WRITE;
/*!40000 ALTER TABLE `chat_user_link` DISABLE KEYS */;
INSERT INTO `chat_user_link` VALUES ('72e420d0fcc94803a2772c3161d09cf1','3ed9aafaeaad46d1a89f8735a4563486','1','2','2023-12-03 21:33:44'),('be6553b1bc694fc38f907827db8bfe53','3ed9aafaeaad46d1a89f8735a4563486','2','1','2023-12-03 21:33:44');
/*!40000 ALTER TABLE `chat_user_link` ENABLE KEYS */;
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
  `user_id` varchar(32) NOT NULL,
  `love` int(11) DEFAULT NULL,
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
-- Table structure for table `tbl_activity_image`
--

DROP TABLE IF EXISTS `tbl_activity_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_activity_image` (
  `image_id` varchar(100) NOT NULL,
  `activity_id` varchar(100) DEFAULT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_activity_image`
--

LOCK TABLES `tbl_activity_image` WRITE;
/*!40000 ALTER TABLE `tbl_activity_image` DISABLE KEYS */;
INSERT INTO `tbl_activity_image` VALUES ('10c7a6d377fa41daaa64818434cc7e4b',NULL,'1','http://192.168.144.132:9000/campus-bucket/activity/842997f973904776a064309dfc2c3553.jpeg',0),('843075dfc97843588c90c2959761937f',NULL,'1','http://192.168.144.132:9000/campus-bucket/activity/3f612a1f83d54298b9e5a1ab8f29c2f8.mp4',1);
/*!40000 ALTER TABLE `tbl_activity_image` ENABLE KEYS */;
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
  `love` int(11) DEFAULT NULL,
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_activity_learning`
--

LOCK TABLES `tbl_activity_learning` WRITE;
/*!40000 ALTER TABLE `tbl_activity_learning` DISABLE KEYS */;
INSERT INTO `tbl_activity_learning` VALUES ('c7654f7d823342418666e92f3a5d7b85','朕亲临','吾皇万岁万岁万岁','2023-12-06 21:26:37','1',0);
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
  `love` int(11) DEFAULT NULL,
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_activity_mate`
--

LOCK TABLES `tbl_activity_mate` WRITE;
/*!40000 ALTER TABLE `tbl_activity_mate` DISABLE KEYS */;
INSERT INTO `tbl_activity_mate` VALUES ('2178ee983d794566a7d04dbf2eafcff0','朕亲临','吾皇万岁万岁万岁','2023-12-06 21:36:11','2',0),('66b5211e9ab844fab34064a9edcac845','朕亲临','吾皇万岁万岁万岁','2023-12-06 21:36:09','1',0),('6dac7b6153a94eb9bef87719ebe97733','朕亲临','吾皇万岁万岁万岁','2023-12-06 21:35:16','1',0);
/*!40000 ALTER TABLE `tbl_activity_mate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_comment`
--

DROP TABLE IF EXISTS `tbl_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_comment` (
  `comment_id` varchar(100) NOT NULL,
  `activity_id` varchar(100) DEFAULT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `love` int(11) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_comment`
--

LOCK TABLES `tbl_comment` WRITE;
/*!40000 ALTER TABLE `tbl_comment` DISABLE KEYS */;
INSERT INTO `tbl_comment` VALUES ('7234735d5e3d450f890633879109c7be','248772f1f42140c6a058cd774de0a17e','1','真tm帅',1,'2023-12-06 20:35:04');
/*!40000 ALTER TABLE `tbl_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_comment_image`
--

DROP TABLE IF EXISTS `tbl_comment_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_comment_image` (
  `image_id` varchar(100) NOT NULL,
  `comment_id` varchar(100) DEFAULT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_comment_image`
--

LOCK TABLES `tbl_comment_image` WRITE;
/*!40000 ALTER TABLE `tbl_comment_image` DISABLE KEYS */;
INSERT INTO `tbl_comment_image` VALUES ('93a3cc4c2d884f84a5df3ad37a07b03c','7234735d5e3d450f890633879109c7be','1','http://192.168.144.132:9000/campus-bucket/activity//eed26945f9fb4a09b6d3f6ea0788e4e6.jpeg',0),('a8014daaf4cc48e098fabe622f49829f','7234735d5e3d450f890633879109c7be','1','http://192.168.144.132:9000/campus-bucket/activity//d94d059500ae4725aa6f66ee28324699.jpeg',0);
/*!40000 ALTER TABLE `tbl_comment_image` ENABLE KEYS */;
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
INSERT INTO `tbl_user` VALUES ('1','1','1@qq.com','1','河北师范大学','http://192.168.144.132:9000/campus-bucket/activity/d53f823074fd48119944ef59613e7bca.jpeg','2023-11-22 16:42:31'),('2','欲心','789@qq.com','123456','123','http://192.168.144.132:9000/campus-bucket/activity/d53f823074fd48119944ef59613e7bca.jpeg','2023-11-22 17:15:10'),('f88d909b2fb9494abc3fcdb313bb87f0','zhjm','728753465@qq.com','521125','school','http://192.168.144.132:9000/campus-bucket/activity/d53f823074fd48119944ef59613e7bca.jpeg','2023-11-22 16:42:31');
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

-- Dump completed on 2023-12-07 17:38:40

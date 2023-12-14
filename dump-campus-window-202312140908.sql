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
  `last_msg_time` datetime DEFAULT NULL,
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
INSERT INTO `chat_list` VALUES ('1','3ed9aafaeaad46d1a89f8735a4563486','1','2',0,0,'3333','2023-12-12 10:25:31',0,1),('2','3ed9aafaeaad46d1a89f8735a4563486','2','1',0,0,'3333','2023-12-12 10:25:31',4,1);
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
INSERT INTO `chat_message` VALUES ('0eecca356a5b40059d260a1d99c8ccb3','3ed9aafaeaad46d1a89f8735a4563486','2','1','hello','2023-12-12 10:10:23',0),('114894428cde4e6db762aedd5750c4fb','3ed9aafaeaad46d1a89f8735a4563486','1','2','313131','2023-12-05 21:59:04',0),('14a0169524c5451eaebc430a77b67df4','3ed9aafaeaad46d1a89f8735a4563486','1','2','2424242','2023-12-05 22:36:21',0),('1b31bff2d0af429a87d2307b307bf59c','3ed9aafaeaad46d1a89f8735a4563486','1','2','woaini','2023-12-12 10:13:32',0),('1e72cae6c7de4fb79713306a5c25fb2a','3ed9aafaeaad46d1a89f8735a4563486','2','1','2222','2023-12-05 22:36:19',0),('1f4deb1c158342d083451fbdf120be8e','3ed9aafaeaad46d1a89f8735a4563486','2','1','1313','2023-12-12 10:07:45',0),('1fb81f4afe4040608d963081f8bee9a1','3ed9aafaeaad46d1a89f8735a4563486','1','2','13131','2023-12-05 22:00:44',0),('34234e2be2004e7a8ac0f8c4a7757a8d','3ed9aafaeaad46d1a89f8735a4563486','1','2','6666','2023-12-12 10:13:54',0),('3ead4613138e333341313563486','3ed9aafaeaad46d1a89f8735a4563486','2','1','bbb1','1970-01-21 00:43:06',0),('3f0462c79ed7468b84b1e0f40be23050','3ed9aafaeaad46d1a89f8735a4563486','1','2','213131','2023-12-05 22:35:13',0),('402eb0e57cc64b8bb64cb97c8e2b087b','3ed9aafaeaad46d1a89f8735a4563486','2','1','2424','2023-12-05 22:50:45',0),('40c54640d7dc4c4993aaa189cf7a2423','3ed9aafaeaad46d1a89f8735a4563486','2','1','广告词','2023-12-05 22:51:21',0),('41893ac4a59d43879437aaf71fa02e2c','3ed9aafaeaad46d1a89f8735a4563486','2','1','111111111111111111','2023-12-05 22:35:12',0),('427829126c2d4642871b5cfb7f136d32','3ed9aafaeaad46d1a89f8735a4563486','1','2','13131','2023-12-05 21:41:56',0),('4b93e828ef21414cade4dec22863f9c5','3ed9aafaeaad46d1a89f8735a4563486','1','2','aaaaaaaaaaaa','2023-12-05 22:35:21',0),('4cba5177e931407687e621fae3b28905','3ed9aafaeaad46d1a89f8735a4563486','1','2','13131','2023-12-05 21:44:45',0),('5f03f0c47cbd49e7adfc7910eb90223c','3ed9aafaeaad46d1a89f8735a4563486','2','1','wo yu chengfo ','2023-12-12 10:08:04',0),('81d98397d7a547f6a6c4ead5b572942f','3ed9aafaeaad46d1a89f8735a4563486','1','2','1321','2023-12-05 21:42:13',0),('847ac969c55f4a80ad66a61d6472fdf2','3ed9aafaeaad46d1a89f8735a4563486','1','2','23131','2023-12-05 22:33:34',0),('847f4311dd784ad18f60e182ab8f1001','3ed9aafaeaad46d1a89f8735a4563486','1','2','3131','2023-12-05 22:51:05',0),('899439f5fb334096a6c4f4c90dbfab3b','3ed9aafaeaad46d1a89f8735a4563486','1','2','`2`2','2023-12-05 22:39:08',0),('90786c3f016144609077e89a2c41dbad','3ed9aafaeaad46d1a89f8735a4563486','2','1','13131','2023-12-05 22:35:19',0),('91f59a4ca48044a48b68e26bacf49d49','3ed9aafaeaad46d1a89f8735a4563486','2','1','313131','2023-12-05 22:51:06',0),('9d13a4e36bde4640b8b7524fa03043b4','3ed9aafaeaad46d1a89f8735a4563486','2','1','131313','2023-12-05 22:47:27',0),('9e39c27d971740d78f04b0ef7672049c','3ed9aafaeaad46d1a89f8735a4563486','1','2','w ao\n','2023-12-05 22:51:21',0),('b28c0ebcec0c4f9ea6c49e06322398cd','3ed9aafaeaad46d1a89f8735a4563486','1','2','3333','2023-12-12 10:25:31',0),('c86377ea2fad458f90d51602e21dd4f8','3ed9aafaeaad46d1a89f8735a4563486','1','2','131313','2023-12-12 10:25:14',0),('ca8e910709a1407a90ec55e3b78ee4b0','3ed9aafaeaad46d1a89f8735a4563486','2','1','1111','2023-12-12 10:10:15',0),('da393642fd1845c5bc3cbe537d23d86e','3ed9aafaeaad46d1a89f8735a4563486','1','2','13131','2023-12-05 22:33:27',0),('ed6a67ae94d2412f801a40f786ccb89c','3ed9aafaeaad46d1a89f8735a4563486','2','1','rfcc','2023-12-08 23:21:10',0);
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
-- Table structure for table `tbl_activity`
--

DROP TABLE IF EXISTS `tbl_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_activity` (
  `activity_id` varchar(32) NOT NULL,
  `activity_title` varchar(32) DEFAULT NULL,
  `activity_content` varchar(1024) DEFAULT NULL,
  `date` datetime NOT NULL,
  `user_id` varchar(32) NOT NULL,
  `school` varchar(100) DEFAULT NULL,
  `love` int(11) DEFAULT NULL,
  `comment` int(11) DEFAULT NULL,
  `collect` int(11) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`activity_id`)
) ENGINE=MEMORY DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_activity`
--

LOCK TABLES `tbl_activity` WRITE;
/*!40000 ALTER TABLE `tbl_activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_activity_collect`
--

DROP TABLE IF EXISTS `tbl_activity_collect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_activity_collect` (
  `collect_id` varchar(100) DEFAULT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  `activity_id` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_activity_collect`
--

LOCK TABLES `tbl_activity_collect` WRITE;
/*!40000 ALTER TABLE `tbl_activity_collect` DISABLE KEYS */;
INSERT INTO `tbl_activity_collect` VALUES ('78293ef91dc04b49a1b30856c2e9037f','2','f19e7ea5ed52469a92fea5ca2207df6b'),('1f21e728e39c41e9a269d2d0df659f3f','1','0d6f0381e84e424d897c7c305b2e8e52');
/*!40000 ALTER TABLE `tbl_activity_collect` ENABLE KEYS */;
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
INSERT INTO `tbl_activity_image` VALUES ('2b2cc9232b184c6db566044e68db93af','92cef87ba09947849cd4906659569031','1','http://192.168.144.132:9000/campus-bucket/activity/34c69f9085f644f69e184ec79c8b69ea.mp4#t=0.01',1),('346c7029577842dd857b30b65bc4a591','3ddea1c5480f4365bb793cb7e5ddac0c','1','http://192.168.144.132:9000/campus-bucket/activity/68f33964370644afa8c1465a8a94e494.jpeg',0),('5a00ae56e83f420086462bfa7b8e9313','4728e065f8be4114b122115e86cb4f31','1','http://192.168.144.132:9000/campus-bucket/activity/ad47e186a8b34c00b60eff74513b5c56.mp4#t=0.01',1),('6f95e702069f409a9a0dbe8753634876','ef7b213768e6481f860f6487eed0fa69','1','http://192.168.144.132:9000/campus-bucket/activity/ad47e186a8b34c00b60eff74513b5c56.mp4#t=0.01',1),('9f4fde26aa5a4817b88a0d440ca137cc','92cef87ba09947849cd4906659569031','1','http://192.168.144.132:9000/campus-bucket/activity/1d360598752544f59e6636ba650cbfa9.jpeg',0),('9fa0e98801db4fb4a1cc6d83fa50fb0b','3ddea1c5480f4365bb793cb7e5ddac0c','1','http://192.168.144.132:9000/campus-bucket/activity/81bfca22d831402d9edc07a3bd74c529.mp4#t=0.01',1),('d843a879e58f4e398d6f277aed6dfbe5','4728e065f8be4114b122115e86cb4f31','1','http://192.168.144.132:9000/campus-bucket/activity/38859a533d7f4cebb6c9a333bdeeb85d.jpeg',0),('f9fbaca6f21e410dbdf21fa97d1315f9','ef7b213768e6481f860f6487eed0fa69','1','http://192.168.144.132:9000/campus-bucket/activity/38859a533d7f4cebb6c9a333bdeeb85d.jpeg',0);
/*!40000 ALTER TABLE `tbl_activity_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_activity_love`
--

DROP TABLE IF EXISTS `tbl_activity_love`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_activity_love` (
  `love_id` varchar(100) NOT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  `activity_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`love_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_activity_love`
--

LOCK TABLES `tbl_activity_love` WRITE;
/*!40000 ALTER TABLE `tbl_activity_love` DISABLE KEYS */;
INSERT INTO `tbl_activity_love` VALUES ('386fbecc97734a70bf5610300a6543db','2','79ae19f6954c4535ba83e2eb081ea82f'),('876910aa4cba4d9ba3f521b20e10d79c','2','05b9e0a1e1bc4f2eb0619ecd1d07355f'),('899e331a882a4fb89f9df81a91554a33','2','f19e7ea5ed52469a92fea5ca2207df6b'),('9dea65faef294dc395abde9741f29c4e','1','0d6f0381e84e424d897c7c305b2e8e52'),('a7962756de8b4090b57a9c2a725c59e2','1','58fea0298b05448285c0e6bd721b524e'),('d8a620cacd3244ee97a27b31702cd804','1','05b9e0a1e1bc4f2eb0619ecd1d07355f');
/*!40000 ALTER TABLE `tbl_activity_love` ENABLE KEYS */;
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
  `parent_id` varchar(100) DEFAULT NULL,
  `to_user_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_comment`
--

LOCK TABLES `tbl_comment` WRITE;
/*!40000 ALTER TABLE `tbl_comment` DISABLE KEYS */;
INSERT INTO `tbl_comment` VALUES ('2305cb58e2ba49ef89aa16b5f4d9c924','c6b1d040aed14ef6a914897b8e0e9212','3','真tm帅',0,'2023-12-12 18:20:18',NULL,NULL),('64f6a3a761c444d28026b479e89c3115','c6b1d040aed14ef6a914897b8e0e9212','1','888',0,'2023-12-14 08:33:28','2305cb58e2ba49ef89aa16b5f4d9c924','3'),('ceb598ef668b4e8195a6bc0be680822c','c6b1d040aed14ef6a914897b8e0e9212','2','999',0,'2023-12-14 08:31:32','2305cb58e2ba49ef89aa16b5f4d9c924','3'),('eeb9e9eaabab419f88dc834e5cf3db08','c6b1d040aed14ef6a914897b8e0e9212','1','真tm帅',2,'2023-12-12 18:14:42',NULL,NULL),('f5183badafdf4d6eb1c273027ba6d47b','c6b1d040aed14ef6a914897b8e0e9212','3','我爱你',0,'2023-12-14 08:43:54','ceb598ef668b4e8195a6bc0be680822c','2');
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
-- Table structure for table `tbl_comment_love`
--

DROP TABLE IF EXISTS `tbl_comment_love`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_comment_love` (
  `love_id` varchar(100) NOT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  `comment_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`love_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_comment_love`
--

LOCK TABLES `tbl_comment_love` WRITE;
/*!40000 ALTER TABLE `tbl_comment_love` DISABLE KEYS */;
INSERT INTO `tbl_comment_love` VALUES ('0e4bf99c95614a11af7f3c43fa769262','3','eeb9e9eaabab419f88dc834e5cf3db08'),('f5061b1b8580454b9ecccd568868fa3d','2','eeb9e9eaabab419f88dc834e5cf3db08');
/*!40000 ALTER TABLE `tbl_comment_love` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_school`
--

LOCK TABLES `tbl_school` WRITE;
/*!40000 ALTER TABLE `tbl_school` DISABLE KEYS */;
INSERT INTO `tbl_school` VALUES (1,'河北师范大学','http://192.168.144.132:9000/campus-bucket/schools/a7df4c4f347841aab8afb15f4e515545.jpeg'),(2,'河北科技大学','http://192.168.144.132:9000/campus-bucket/schools/a7df4c4f347841aab8afb15f4e515545.jpeg'),(4,'999','http://192.168.144.132:9000/campus-bucket/schools/a7df4c4f347841aab8afb15f4e515545.jpeg'),(8,'清华大学','http://192.168.144.132:9000/campus-bucket/schools/a7df4c4f347841aab8afb15f4e515545.jpeg');
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
  `gender` tinyint(4) DEFAULT NULL,
  `signature` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user`
--

LOCK TABLES `tbl_user` WRITE;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` VALUES ('1','9','1@qq.com','1','河北师范大学','http://192.168.144.132:9000/campus-bucket/users/428d21d0bc1b4e28b608afbb55aa6856.jpeg','2023-11-22 16:42:31',1,'9'),('2','欲心','789@qq.com','123456','123','http://192.168.144.132:9000/campus-bucket/activity/04375f22b0134ccfafade858c7621d02.jpeg','2023-11-22 17:15:10',1,NULL),('3','zhjm','728753465@qq.com','521125','河北师范大学','http://192.168.144.132:9000/campus-bucket/activity/04375f22b0134ccfafade858c7621d02.jpeg','2023-11-22 16:42:31',0,NULL),('8ad4fc5b81c54cb0a6ad3ad26bbd1ae1','7771','789777@qq.com','27773432','河北师范大学','http://192.168.144.132:9000/campus-bucket/users/default.jpg','2023-12-12 09:42:30',0,NULL);
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

-- Dump completed on 2023-12-14  9:08:25

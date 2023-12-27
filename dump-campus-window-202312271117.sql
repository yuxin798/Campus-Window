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
-- Table structure for table `chat_channel`
--

DROP TABLE IF EXISTS `chat_channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_channel` (
  `link_id` varchar(100) DEFAULT NULL,
  `channel_name` varchar(100) DEFAULT NULL,
  `channel_avatar` varchar(100) DEFAULT NULL,
  `channel_signature` varchar(100) DEFAULT NULL,
  `parent_id` varchar(100) DEFAULT NULL,
  `channel_master` varchar(100) DEFAULT NULL,
  `channel_background` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_channel`
--

LOCK TABLES `chat_channel` WRITE;
/*!40000 ALTER TABLE `chat_channel` DISABLE KEYS */;
INSERT INTO `chat_channel` VALUES ('de6f0904760c4ba9a0ea0b32d1475317','八格牙路大学','http://10.7.88.195:9000/campus-bucket/activity/04375f22b0134ccfafade858c7621d02.jpeg',NULL,NULL,'1',NULL),('5a42b74ef3d143a983464e255c66b6a0','王子学院','http://192.168.3.36:9000/campus-bucket/users/0ae46b4ee1cb44b1b0ed8117045bc3ce.jpeg',NULL,'de6f0904760c4ba9a0ea0b32d1475317','1',NULL),('333c7f5ede604974acae350b95c6a513','公主学院','http://192.168.3.36:9000/campus-bucket/users/0ae46b4ee1cb44b1b0ed8117045bc3ce.jpeg',NULL,'de6f0904760c4ba9a0ea0b32d1475317','1',NULL),('953aa1c9227a4d318fda37c274f1d755','米西米西大学','http://10.7.88.195:9000/campus-bucket/activity/04375f22b0134ccfafade858c7621d02.jpeg',NULL,NULL,'3',NULL),('0864387f062a4edabd62c4e004337d57','瓜埠垃圾学院','http://192.168.3.36:9000/campus-bucket/users/0ae46b4ee1cb44b1b0ed8117045bc3ce.jpeg',NULL,'953aa1c9227a4d318fda37c274f1d755','3',NULL),('8c40eda8787e45c886202f06f614ac16','你就拉稀学院','http://192.168.3.36:9000/campus-bucket/users/0ae46b4ee1cb44b1b0ed8117045bc3ce.jpeg',NULL,'953aa1c9227a4d318fda37c274f1d755','3',NULL);
/*!40000 ALTER TABLE `chat_channel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_group`
--

DROP TABLE IF EXISTS `chat_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_group` (
  `link_id` varchar(100) NOT NULL DEFAULT '',
  `group_name` varchar(100) DEFAULT NULL,
  `group_avatar` varchar(100) DEFAULT NULL,
  `group_number` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`link_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_group`
--

LOCK TABLES `chat_group` WRITE;
/*!40000 ALTER TABLE `chat_group` DISABLE KEYS */;
INSERT INTO `chat_group` VALUES ('0429232e120c4d9c9e061219ea34d667','9欲心','http://192.168.144.132:9000/campus-bucket/users/default.jpg','2'),('dda27352a3f74e2aaec7d4f2d17cb062','hhh','http://10.7.88.195:9000/campus-bucket/activity/5c67359d7a2d4bc0ac217e67c2af0c98.jpeg','3');
/*!40000 ALTER TABLE `chat_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_link`
--

DROP TABLE IF EXISTS `chat_link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_link` (
  `id` varchar(100) NOT NULL,
  `link_id` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_link`
--

LOCK TABLES `chat_link` WRITE;
/*!40000 ALTER TABLE `chat_link` DISABLE KEYS */;
INSERT INTO `chat_link` VALUES ('3b5aab2649db49e4b189aa829d8aa1ca','de6f0904760c4ba9a0ea0b32d1475317','2023-12-27 11:11:57',2),('566256c49f6c4224961c2f79e8dfe6c0','8c40eda8787e45c886202f06f614ac16','2023-12-27 11:12:46',3),('5b3d1b9d572b4b9fbc7fbc9cc2913a3f','0864387f062a4edabd62c4e004337d57','2023-12-27 11:12:38',3),('6e18f72012374e1da8266f61031cde07','333c7f5ede604974acae350b95c6a513','2023-12-27 11:12:13',3),('7ec4422f927341f9843feb3e0f6e73d7','953aa1c9227a4d318fda37c274f1d755','2023-12-27 11:12:25',2),('8e7c22d8f9924b728ae8fd833d4cb7c6','5a42b74ef3d143a983464e255c66b6a0','2023-12-27 11:12:06',3);
/*!40000 ALTER TABLE `chat_link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_list`
--

DROP TABLE IF EXISTS `chat_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_list` (
  `list_id` varchar(100) NOT NULL DEFAULT '',
  `link_id` varchar(100) DEFAULT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  `window` tinyint(4) DEFAULT NULL,
  `last_msg` varchar(100) DEFAULT NULL,
  `last_msg_time` datetime DEFAULT NULL,
  `unread` int(11) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`list_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_list`
--

LOCK TABLES `chat_list` WRITE;
/*!40000 ALTER TABLE `chat_list` DISABLE KEYS */;
INSERT INTO `chat_list` VALUES ('3281cc3b4ab345ee8a0fd6885277f81d','5a42b74ef3d143a983464e255c66b6a0','1',0,'欢迎来到 王子学院','2023-12-27 11:12:06',0,1),('473060b6339743bfb8a3846f2a9b4420','de6f0904760c4ba9a0ea0b32d1475317','3',0,NULL,'2023-12-27 11:14:48',0,1),('49abf9cb1b7e48658420abba2297586b','8c40eda8787e45c886202f06f614ac16','2',0,'欢迎来到 你就拉稀学院','2023-12-27 11:12:46',0,1),('5dd02b09c4fe48fb9eac28a19b2d86c7','953aa1c9227a4d318fda37c274f1d755','2',0,NULL,'2023-12-27 11:15:42',0,1),('663f262fe4f248d1a1c63fe303d2ea5e','333c7f5ede604974acae350b95c6a513','3',0,'欢迎来到 公主学院','2023-12-27 11:12:13',0,1),('6eb57b7453584b878cf2d95683990f36','953aa1c9227a4d318fda37c274f1d755','3',0,NULL,'2023-12-27 11:12:25',0,1),('907526c44a744d13a79c95bb7e4faea3','333c7f5ede604974acae350b95c6a513','1',0,'欢迎来到 公主学院','2023-12-27 11:12:13',0,1),('a0493ee819574885908d8e0dc9dc91f6','8c40eda8787e45c886202f06f614ac16','3',0,'欢迎来到 你就拉稀学院','2023-12-27 11:12:46',0,1),('ad7d6170366f4d1cb9182f6cd7dd9d85','0864387f062a4edabd62c4e004337d57','3',0,'欢迎来到 瓜埠垃圾学院','2023-12-27 11:12:38',0,1),('b1d7cefc382645dfbb28e5788e86432e','5a42b74ef3d143a983464e255c66b6a0','3',0,'欢迎来到 王子学院','2023-12-27 11:12:06',0,1),('c057fa5e7dad46ed97aa8ff1f286baa5','de6f0904760c4ba9a0ea0b32d1475317','1',0,NULL,'2023-12-27 11:11:57',0,1),('d3cfb47e9b66442f9485bfcb9981a47f','0864387f062a4edabd62c4e004337d57','2',0,'欢迎来到 瓜埠垃圾学院','2023-12-27 11:12:38',0,1);
/*!40000 ALTER TABLE `chat_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_message`
--

DROP TABLE IF EXISTS `chat_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_message` (
  `message_id` varchar(100) NOT NULL DEFAULT '',
  `link_id` varchar(100) DEFAULT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_message`
--

LOCK TABLES `chat_message` WRITE;
/*!40000 ALTER TABLE `chat_message` DISABLE KEYS */;
INSERT INTO `chat_message` VALUES ('04cc31a7898748848cc36e4b993314ee','6a21a61de3d7476683a1ac7b1bb701ca','3','999','2023-12-19 11:12:07',0),('05a89cbe0b6249f6b1a1fbf9b13d7cee','6a21a61de3d7476683a1ac7b1bb701ca','1','33322222222232','2023-12-19 10:55:01',0),('083f12e46376477cafbb40fdd4db638e','6a21a61de3d7476683a1ac7b1bb701ca','1','2222','2023-12-22 18:53:33',0),('09153680e0094b4b9a2f76d2aa53bb90','dda27352a3f74e2aaec7d4f2d17cb062','1','13131','2023-12-22 20:30:58',0),('0b14f18d1100437e8630a57887a474e6','98b806b15f414b2c83d3e9c432969492','3','1313','2023-12-22 20:33:55',0),('0bf1b08ce18045068f4ec86f7e930aa3','98b806b15f414b2c83d3e9c432969492','1','111111','2023-12-22 18:53:45',0),('0e15b2ab83b64a489d9f6b73950c983b','dda27352a3f74e2aaec7d4f2d17cb062','1','23131','2023-12-19 18:24:15',0),('0e55bd7371804364a513199c7d3463b1','adac782c81e140fa9f984ae75acaca73','3','777777\n','2023-12-19 11:02:04',0),('1117b958fc534aa3abc98b4a9e8c3ed2','adac782c81e140fa9f984ae75acaca73','1','33332','2023-12-19 10:35:45',0),('1446a0c5c1c245839fa481de974da3ee','adac782c81e140fa9f984ae75acaca73','3','wo ye ai ni\n','2023-12-19 18:23:11',0),('14e1b96b89d1402aa67775d65a0e1464','dda27352a3f74e2aaec7d4f2d17cb062','1','777','2023-12-22 19:01:20',0),('18cbbf4bba5d4fefbf2ccca5ae880339','dda27352a3f74e2aaec7d4f2d17cb062','3','54188','2023-12-22 19:01:04',0),('1931fd617597499185a3d2640effacac','adac782c81e140fa9f984ae75acaca73','1','55555','2023-12-19 11:03:45',0),('1e4b4de1c3ae4199bd2f24cbab9a2d1a','adac782c81e140fa9f984ae75acaca73','1','3223223','2023-12-19 10:59:53',0),('1e8f67156b1c4108b33fdf4abbfe10c2','adac782c81e140fa9f984ae75acaca73','1','我爱你','2023-12-19 18:22:53',0),('1ea6936d583c4c1a971b4866b52b4968','adac782c81e140fa9f984ae75acaca73','3','777777777','2023-12-19 11:12:25',0),('22ae7c83931d4dc398cd24c42abb1265','dda27352a3f74e2aaec7d4f2d17cb062','1','213131','2023-12-22 20:30:49',0),('30fd9efbf7b64a849df286fdcbccef85','adac782c81e140fa9f984ae75acaca73','1','999\n','2023-12-19 11:12:11',0),('31bc308b69184dc89370026a6b906994','dda27352a3f74e2aaec7d4f2d17cb062','1','999','2023-12-19 18:09:17',0),('3376aa95ba7a4a8face38a27ce2adac1','adac782c81e140fa9f984ae75acaca73','3','3222424','2023-12-19 10:59:44',0),('344b6bf50cae4beb893256a94a66a664','dda27352a3f74e2aaec7d4f2d17cb062','3','58888','2023-12-19 17:36:18',0),('358eaa5c80fb4ada9d76b656b70674de','adac782c81e140fa9f984ae75acaca73','3','555555555','2023-12-19 11:10:37',0),('36d1ff4624cf4ba9943ed7a6fb3cad14','adac782c81e140fa9f984ae75acaca73','3','222','2023-12-19 10:53:32',0),('377ce381026f41bfaca820dcc9889eb9','adac782c81e140fa9f984ae75acaca73','3','54188','2023-12-19 11:12:32',0),('37906fd655a34b25b4865c9477f0c794','adac782c81e140fa9f984ae75acaca73','3','8888888888','2023-12-19 17:32:03',0),('39d55de7f6e544dea9e013e45f2808a8','adac782c81e140fa9f984ae75acaca73','3','333','2023-12-19 10:38:25',0),('3a9493370d4e4345b60b110b4b45801e','dda27352a3f74e2aaec7d4f2d17cb062','1','666','2023-12-22 19:01:26',0),('3aa611bcc70d4a1da82f514e1db50f0b','adac782c81e140fa9f984ae75acaca73','3','18845','2023-12-19 17:35:55',0),('3edc18bbe1314ded813a040152377779','dda27352a3f74e2aaec7d4f2d17cb062','1','55445','2023-12-22 18:45:53',0),('4347d8e1b2d346458413ccab5e9a2a6d','dda27352a3f74e2aaec7d4f2d17cb062','1','8888','2023-12-22 19:01:23',0),('482aea13b5354b59a728c67f8a40fee9','dda27352a3f74e2aaec7d4f2d17cb062','1','13131','2023-12-22 20:32:35',0),('5075e1a8d9ee43f392e5473875ab532f','adac782c81e140fa9f984ae75acaca73','1','54188','2023-12-19 10:57:13',0),('51d3466b360d4c99bcd4154d2fca38fd','dda27352a3f74e2aaec7d4f2d17cb062','1','54188','2023-12-19 18:08:57',0),('536e8f7706db4697928e829e4dc8ba6b','dda27352a3f74e2aaec7d4f2d17cb062','1','34242','2023-12-22 18:46:42',0),('550b215b3e2c485c92e09a09f7c5b599','dda27352a3f74e2aaec7d4f2d17cb062','2','888888','2023-12-19 17:38:14',0),('568d2deb8ae64ccfa990320f7cedf5c4','dda27352a3f74e2aaec7d4f2d17cb062','1','666','2023-12-22 19:07:22',0),('5acbff764d7b4ce28f5edbf9bf97a776','dda27352a3f74e2aaec7d4f2d17cb062','1','55','2023-12-22 19:02:44',0),('5c345d66d84f4999928f2dbaa0971412','adac782c81e140fa9f984ae75acaca73','1','9999999999','2023-12-19 11:01:23',0),('5d72c85efdea46619f120f1cd642b310','adac782c81e140fa9f984ae75acaca73','3','33333333','2023-12-19 11:04:25',0),('5ebb41f82b0b48a7bc6b9351d4bae76b','dda27352a3f74e2aaec7d4f2d17cb062','3','7777777','2023-12-19 17:38:08',0),('63009b6448c64b51a195f41db72e1866','dda27352a3f74e2aaec7d4f2d17cb062','1','6666\n','2023-12-19 18:23:59',0),('64f9a9ce36be40f99d3a68cd4a826b78','adac782c81e140fa9f984ae75acaca73','3','2222','2023-12-19 10:57:59',0),('6782bf4c1f114c7796bc95ded7a261dd','dda27352a3f74e2aaec7d4f2d17cb062','3','13131','2023-12-22 20:33:45',0),('6ad8a65f97ae48458683dbfa7a164efa','dda27352a3f74e2aaec7d4f2d17cb062','3','4242','2023-12-22 20:33:47',0),('6bd7d13ac92c4f3198fd5a80b2768a2c','adac782c81e140fa9f984ae75acaca73','3','999\n','2023-12-19 17:31:00',0),('6bffa8202a5542b1a39556f2c97b8a2d','adac782c81e140fa9f984ae75acaca73','1','33332','2023-12-19 10:53:35',0),('76f06bc929da416bbd070d97d80fb592','98b806b15f414b2c83d3e9c432969492','3','54188\n','2023-12-22 20:33:58',0),('76fa3647f4eb49db9bedaeeb77267eef','dda27352a3f74e2aaec7d4f2d17cb062','3','2313131','2023-12-19 18:24:17',0),('7893fd77567343f39edef00277b86d8a','adac782c81e140fa9f984ae75acaca73','3','jia d  ba \n','2023-12-19 18:23:43',0),('78e4b2c0629f41f1bd4bebc53c5d84d3','dda27352a3f74e2aaec7d4f2d17cb062','1','111','2023-12-22 19:01:55',0),('7bd876c26e184301bd3261605555a26c','dda27352a3f74e2aaec7d4f2d17cb062','1','7777','2023-12-19 17:36:26',0),('817574cd6b834c5bb2c4622c1af37c5c','dda27352a3f74e2aaec7d4f2d17cb062','2','7777','2023-12-19 17:37:19',0),('84dc8fc4e7274fa49012b5fb7c3b1b3e','dda27352a3f74e2aaec7d4f2d17cb062','3','23424','2023-12-22 20:33:51',0),('863dc7db1bd846afa789246bb38dd903','98b806b15f414b2c83d3e9c432969492','1','111','2023-12-22 18:53:53',0),('8cfc2536e2744629ba45a566b39385bd','98b806b15f414b2c83d3e9c432969492','1','4','2023-12-22 18:53:58',0),('9016046778134819bff668a66a386a69','adac782c81e140fa9f984ae75acaca73','1','54188','2023-12-19 10:58:03',0),('93b2b9191f574612b9180ec1cb31b8cb','adac782c81e140fa9f984ae75acaca73','3','zhen de jiade ','2023-12-19 18:23:37',0),('94c682acfe3745329459213d50c450be','98b806b15f414b2c83d3e9c432969492','1','3131e13131','2023-12-22 18:53:29',0),('95b36f8d13f6421c94717b14e4a0b8d2','adac782c81e140fa9f984ae75acaca73','1','54188','2023-12-19 17:35:44',0),('962268f171224afd9c4fdc07414a1957','adac782c81e140fa9f984ae75acaca73','3','3333333333333333','2023-12-19 11:10:47',0),('9822593a717e4a12a900ad765bcf6534','adac782c81e140fa9f984ae75acaca73','3','88875','2023-12-19 10:57:19',0),('9cafd4df6c2e4d4da8814e0f8ad06231','dda27352a3f74e2aaec7d4f2d17cb062','3','13131','2023-12-22 19:00:58',0),('9fd1a135c70947acbc1e02a4aa485c85','dda27352a3f74e2aaec7d4f2d17cb062','1','9999','2023-12-19 17:36:24',0),('a0a036f7ba2a41b68fac5149cdd83879','dda27352a3f74e2aaec7d4f2d17cb062','1','22222','2023-12-22 19:02:42',0),('a0fd0f4c788f49febfb499d5a8e879a1','adac782c81e140fa9f984ae75acaca73','1','3333','2023-12-19 11:10:24',0),('a231401660884fefb4e4757b9e03923c','adac782c81e140fa9f984ae75acaca73','3','6666666','2023-12-19 17:31:59',0),('a2e577c43c9f4a90ab3342a2b614a124','dda27352a3f74e2aaec7d4f2d17cb062','1','222','2023-12-22 19:07:19',0),('a3fe87b1f69c48a28c3c174dee4ca82e','adac782c81e140fa9f984ae75acaca73','1','33332','2023-12-19 10:54:52',0),('aa69d75cbc1e4664a3514db00a681e30','dda27352a3f74e2aaec7d4f2d17cb062','1','333','2023-12-22 19:07:25',0),('ab567f54c2f74cb0886c26d54e8a936e','dda27352a3f74e2aaec7d4f2d17cb062','3','44\n','2023-12-22 19:00:11',0),('ab6b0f0e1a794fe9a22a102388c87639','dda27352a3f74e2aaec7d4f2d17cb062','3','222222222','2023-12-22 19:00:00',0),('b5bbb0d60c4a44778811db2598b78491','dda27352a3f74e2aaec7d4f2d17cb062','2','trrrr','2023-12-19 17:37:37',0),('ba737b8f5a9b434098283335d791e9e0','dda27352a3f74e2aaec7d4f2d17cb062','3','5115\n','2023-12-19 17:36:48',0),('bd36d1b4bfa6499582eb407c79fbf324','dda27352a3f74e2aaec7d4f2d17cb062','2','woaini','2023-12-19 17:37:28',0),('bfb3e274607e4b7c9407e602051a3bf2','98b806b15f414b2c83d3e9c432969492','1','2322','2023-12-22 18:46:47',0),('c06031f714cb426ca9ca79e30e0921bf','dda27352a3f74e2aaec7d4f2d17cb062','3','999','2023-12-19 17:37:34',0),('c20df6441fda4555b2988e301584d0e1','adac782c81e140fa9f984ae75acaca73','1','我真的好爱你呀\n','2023-12-19 18:23:02',0),('c56585eb5a5941be8460f49becaa593e','98b806b15f414b2c83d3e9c432969492','3','2222','2023-12-22 18:53:42',0),('c6959c431c9c4705b7a195a2998bb3f5','adac782c81e140fa9f984ae75acaca73','1','```','2023-12-19 11:03:13',0),('c81b56c769db4f3cb6a2c490811e3f8d','dda27352a3f74e2aaec7d4f2d17cb062','1','9999','2023-12-22 19:01:17',0),('c81fee15d07b49448b787a4a1c7b7f10','adac782c81e140fa9f984ae75acaca73','1','33332','2023-12-19 10:35:45',0),('c825ad80b31349af837c4588e50c58f7','dda27352a3f74e2aaec7d4f2d17cb062','1','11','2023-12-22 19:07:28',0),('c9c80b9573f44c208bc7a588f8168be3','dda27352a3f74e2aaec7d4f2d17cb062','3','242','2023-12-22 20:33:48',0),('cad826eb02b74aa192bade541474b696','dda27352a3f74e2aaec7d4f2d17cb062','3','111111111','2023-12-22 18:59:55',0),('cbe2179bb430452ab4055b884c66c4e0','adac782c81e140fa9f984ae75acaca73','3','333','2023-12-19 10:58:01',0),('d9516ed1bb4b4dceb632b9cec5fb3b2b','dda27352a3f74e2aaec7d4f2d17cb062','1','....','2023-12-19 17:36:30',0),('dca9b58f9dc04c54b713fde14a93cba5','98b806b15f414b2c83d3e9c432969492','1','123131','2023-12-22 18:45:37',0),('df9e5f5492b54e8789597dc8c04cc6a1','dda27352a3f74e2aaec7d4f2d17cb062','1','9999','2023-12-19 18:24:06',0),('e14f9c0c3b1f4db0820993c96dac30e0','adac782c81e140fa9f984ae75acaca73','1','33322222222232','2023-12-19 10:57:06',0),('e1aa2881944b47288300d87d0ceb9e7b','adac782c81e140fa9f984ae75acaca73','1','88888\n','2023-12-19 11:12:20',0),('e1d0a5c61fa7422888c454b416170917','98b806b15f414b2c83d3e9c432969492','3','65\n65','2023-12-22 18:37:26',0),('e9a7513090734e719e0fcee3f99625a9','adac782c81e140fa9f984ae75acaca73','1','1111','2023-12-19 11:01:16',0),('e9c367e924734417838482b59f77c948','adac782c81e140fa9f984ae75acaca73','1','18845','2023-12-19 11:12:37',0),('eb76ffe085d84daa80290a7164644c29','adac782c81e140fa9f984ae75acaca73','3','w hao ai ni \n','2023-12-19 18:23:22',0),('ee6f80efae704e4ea1995900c79547a4','adac782c81e140fa9f984ae75acaca73','1','444444','2023-12-19 11:10:32',0),('f2f0d8e1ea0047988534d67460566734','adac782c81e140fa9f984ae75acaca73','1','33322222222232','2023-12-19 10:55:46',0),('f392df8cc0bd4e2783a930d73961ea01','dda27352a3f74e2aaec7d4f2d17cb062','3','ba ','2023-12-19 18:24:10',0),('f4263e0ea3e04fbda51e4f06187f69f7','adac782c81e140fa9f984ae75acaca73','3','3333','2023-12-19 10:36:38',0),('f8416da943254d12a9abf834e1fe10ce','adac782c81e140fa9f984ae75acaca73','1','54188','2023-12-19 10:58:03',0),('f8914de88c46449ca231131ef6f475f5','adac782c81e140fa9f984ae75acaca73','1','55555555','2023-12-19 11:10:51',0),('feb3a87b302943908c15356caefb2c71','dda27352a3f74e2aaec7d4f2d17cb062','3','9999','2023-12-19 17:32:18',0),('feee17c512014d8bbe2e99385659d73b','adac782c81e140fa9f984ae75acaca73','1','33332','2023-12-19 10:35:45',0);
/*!40000 ALTER TABLE `chat_message` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_activity`
--

LOCK TABLES `tbl_activity` WRITE;
/*!40000 ALTER TABLE `tbl_activity` DISABLE KEYS */;
INSERT INTO `tbl_activity` VALUES ('47c2b59933d2466caa128246afd0f294','joker','<img src=\"http://192.168.3.36:9000/campus-bucket/activity/f5c3d7b4899a430b90c2365ae5eba30b.jpeg\" style=\"max-width:100%\"></img><br><br>','2023-12-26 21:02:47','3','河北师范大学',1,0,0,0),('76a58f31fcff42b8b8bc1f64b881c7dd','可爱❤️心','<img src=\"http://192.168.3.36:9000/campus-bucket/activity/dc9fba6d70e04903add7b7a21aa58067.jpeg\" style=\"max-width:100%\"></img><br><br><img src=\"http://192.168.3.36:9000/campus-bucket/activity/c2756ea6ca084a16825b0b4606d05253.jpeg\" style=\"max-width:100%\"></img><br><br>','2023-12-26 21:05:12','3','河北师范大学',0,0,0,2),('987e241f7bf643afac35649eea420174','66','<img src=\"http://192.168.3.36:9000/campus-bucket/activity/4b9d2bf614f046df8b661b2d21be25f1.jpeg\" style=\"max-width:100%\"></img><br><br><img src=\"http://192.168.3.36:9000/campus-bucket/activity/7c5f496bf3cc485786958b008022d9ee.jpeg\" style=\"max-width:100%\"></img><br><br><img src=\"http://192.168.3.36:9000/campus-bucket/activity/e71614d43fc44d4dab6ebdcddadd2ad0.jpeg\" style=\"max-width:100%\"></img><br><br>','2023-12-26 21:05:33','3','河北师范大学',0,0,0,1),('add7ab111941453f9cd7ba195ee8ff75','535353543','<img src=\"http://10.7.88.195:9000/campus-bucket/activity/6406014ac59b46ec87a27eb586d8bdb3.jpeg\" style=\"max-width:100%\"><br>555555555','2023-12-26 09:36:33','3','河北师范大学',1,0,0,0),('c3cec816b33e4d90b5badf7ecaeb6ca8','刘铭洋原来是joker666666','<img src=\"http://192.168.3.36:9000/campus-bucket/activity/c1ae924ece0c47fbbbc13e8fe20ad20e.jpeg\" style=\"max-width:100%\"></img><br><br>','2023-12-26 21:19:59','9ee35a3b11e748789fbf22ecf3c94cb8','河北师范大学',0,1,0,0),('e8f44d4f399f434abd997dfe6e5f4323','cpdd','185男大<img src=\"http://192.168.3.36:9000/campus-bucket/activity/a26a79a2ef29471bbfceb15e0d3c5eff.jpeg\" style=\"max-width:100%\"></img><br><br>','2023-12-26 21:20:21','53d5a04bb18e449ab6b08c483d39a265','河北师范大学',0,0,0,2);
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
INSERT INTO `tbl_activity_image` VALUES ('0e55698c08664e55a8529fbd9f2b2d94','987e241f7bf643afac35649eea420174','3','http://192.168.3.36:9000/campus-bucket/activity/e71614d43fc44d4dab6ebdcddadd2ad0.jpeg',0),('25b96e07870744a4a5d0f1d526a27099','987e241f7bf643afac35649eea420174','3','http://192.168.3.36:9000/campus-bucket/activity/7c5f496bf3cc485786958b008022d9ee.jpeg',0),('416e518a18a044b18dd58ec0d2f734fa','e8f44d4f399f434abd997dfe6e5f4323','53d5a04bb18e449ab6b08c483d39a265','http://192.168.3.36:9000/campus-bucket/activity/a26a79a2ef29471bbfceb15e0d3c5eff.jpeg',0),('4863f4695a434ec392e440aaa749b5ef','add7ab111941453f9cd7ba195ee8ff75','3','http://10.7.88.195:9000/campus-bucket/activity/6406014ac59b46ec87a27eb586d8bdb3.jpeg',0),('5969dbff99094c03b0a7eb56cf17dd9c','c3cec816b33e4d90b5badf7ecaeb6ca8','9ee35a3b11e748789fbf22ecf3c94cb8','http://192.168.3.36:9000/campus-bucket/activity/c1ae924ece0c47fbbbc13e8fe20ad20e.jpeg',0),('6ed51d701e15488e8a6720bc077207bd','987e241f7bf643afac35649eea420174','3','http://192.168.3.36:9000/campus-bucket/activity/4b9d2bf614f046df8b661b2d21be25f1.jpeg',0),('88de8cc7e31a49ac9525ddd03ffec531','76a58f31fcff42b8b8bc1f64b881c7dd','3','http://192.168.3.36:9000/campus-bucket/activity/c2756ea6ca084a16825b0b4606d05253.jpeg',0),('d0f52d509714497da39ec9988791e29f','47c2b59933d2466caa128246afd0f294','3','http://192.168.3.36:9000/campus-bucket/activity/f5c3d7b4899a430b90c2365ae5eba30b.jpeg',0),('de4716fbc6344521a4978fd9a8fb48a2','76a58f31fcff42b8b8bc1f64b881c7dd','3','http://192.168.3.36:9000/campus-bucket/activity/dc9fba6d70e04903add7b7a21aa58067.jpeg',0);
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
  `content` varchar(1024) DEFAULT NULL,
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
INSERT INTO `tbl_comment` VALUES ('20ab3554f0e14cf9b9a679ccac03b394','3afcb9d4a9f94b659e88dd2247460cef','3','5545445',0,'2023-12-25 20:59:33','bf5923dcf8784ea9906864017c673afc','3'),('29d832f99ba746a88175c5c77fd728ff','add7ab111941453f9cd7ba195ee8ff75','3','',0,'2023-12-26 10:35:39',NULL,NULL),('2a07d17e63304c7c805aa5e64f0103ba','c3cec816b33e4d90b5badf7ecaeb6ca8','3','8888',0,'2023-12-27 10:59:51','c4a1f2a16cf241eaa1fc197e0897a11c','3'),('3658c5e211cd46baaf7c4c6ab98cb51c','3afcb9d4a9f94b659e88dd2247460cef','1','我就是你爹',3,'2023-12-25 20:25:54','bf5923dcf8784ea9906864017c673afc','1'),('403664f9b9c840d7ba33edeb74cb3acc','e8f44d4f399f434abd997dfe6e5f4323','9ee35a3b11e748789fbf22ecf3c94cb8','d你大爷',0,'2023-12-26 21:23:00',NULL,NULL),('47598bd1989f4fa1baac4af8a753f829','add7ab111941453f9cd7ba195ee8ff75','3','45654654',0,'2023-12-26 09:47:29','2c27510b61694b34b97fad67f68912d4','3'),('4bc7266d83e045dfb8fb1da1cd99a0a4','c3cec816b33e4d90b5badf7ecaeb6ca8','3','8888',0,'2023-12-27 10:59:46','c4a1f2a16cf241eaa1fc197e0897a11c','3'),('59d73b328dde450ca199984b1301928c','47c2b59933d2466caa128246afd0f294','3','又菜又爱装',0,'2023-12-26 21:06:28',NULL,NULL),('7b104eabda9644969ec6aa9ab953d401','add7ab111941453f9cd7ba195ee8ff75','3','52\n524\n2\n42\n42\n\n',0,'2023-12-26 09:40:44','2c27510b61694b34b97fad67f68912d4','3'),('7e63610487ef4fa09b136889f916b570','add7ab111941453f9cd7ba195ee8ff75','3','',0,'2023-12-26 20:27:24',NULL,NULL),('85a2d318d9504ce1ae40eae863cc6058','add7ab111941453f9cd7ba195ee8ff75','3','5655',1,'2023-12-26 10:33:42',NULL,NULL),('93bcfcde5a3b4c2d8e85fe6bae776f88','add7ab111941453f9cd7ba195ee8ff75','3','4\n2\n42\n4',0,'2023-12-26 09:45:11','2c27510b61694b34b97fad67f68912d4','3'),('9f1b28ff848646c1a84f6bc213dac36c','3afcb9d4a9f94b659e88dd2247460cef','3','德助',0,'2023-12-25 21:20:44',NULL,NULL),('bf5923dcf8784ea9906864017c673afc','3afcb9d4a9f94b659e88dd2247460cef','3','德助',1,'2023-12-25 20:25:10',NULL,NULL),('c4a1f2a16cf241eaa1fc197e0897a11c','c3cec816b33e4d90b5badf7ecaeb6ca8','3','444448888',1,'2023-12-27 10:59:39',NULL,NULL),('ee170e233a8b4089bb52e10ae8ae7c19','add7ab111941453f9cd7ba195ee8ff75','3','',0,'2023-12-26 10:35:33',NULL,NULL);
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
INSERT INTO `tbl_comment_image` VALUES ('2bdd18dd85d049b7b9f93ae5eea41cb4','a41bac988bf544a68fa22609f79f84ef','3','http://192.168.3.36:9000/campus-bucket/activity/2db19c8ee7264a65a03384333448733a.jpeg',0),('671d9fd41ea0441a8200c20e8e27779e','6bc3d8fde5554fa6b78dc09f865632b2','1','http://192.168.3.36:9000/campus-bucket/activity/2db19c8ee7264a65a03384333448733a.jpeg',0),('79cfbbef826f4f49907c68ecba1ccb9f','ee170e233a8b4089bb52e10ae8ae7c19','3','http://10.7.88.195:9000/campus-bucket/activity/1a7b2f059e114fc4bdaa0698ba82a81d.jpeg',0),('934f8575858f41afa15176f599ed5f88','20ab3554f0e14cf9b9a679ccac03b394','3','http://192.168.3.36:9000/campus-bucket/activity/2db19c8ee7264a65a03384333448733a.jpeg',0),('9d4d0db897c3431abcee0612dd92ebe4','29d832f99ba746a88175c5c77fd728ff','3','http://10.7.88.195:9000/campus-bucket/activity/c4c7f0e769c146cda50afa79669b9afb.jpeg',0),('9f250b5fe10c463c88ee9af070dbd9b8','9f1b28ff848646c1a84f6bc213dac36c','3','http://192.168.3.36:9000/campus-bucket/activity/2db19c8ee7264a65a03384333448733a.jpeg',0),('bfc624e2643f4a23a65376063a0c7122','883a359956b941f796b15fee6dec7aed','3','http://192.168.3.36:9000/campus-bucket/activity/2db19c8ee7264a65a03384333448733a.jpeg',0),('e7de34b6e5704df2a387d31d20f3844e','85a2d318d9504ce1ae40eae863cc6058','3','http://10.7.88.195:9000/campus-bucket/activity/199959c6cb2b4bd1aa780d3881e78d30.jpeg',0),('ebda59deaa914303934450ae424888aa','47598bd1989f4fa1baac4af8a753f829','3','http://10.7.88.195:9000/campus-bucket/activity/6182d215bea7435f90bf80595c00983b.jpeg',0),('fdef0dffd0284184a2e4b8bf14d9eafe','bf5923dcf8784ea9906864017c673afc','3','http://192.168.3.36:9000/campus-bucket/activity/2db19c8ee7264a65a03384333448733a.jpeg',0);
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
INSERT INTO `tbl_comment_love` VALUES ('638d3526aa544414979b671d78815ade','3','3658c5e211cd46baaf7c4c6ab98cb51c'),('6521e51cf8b84feb8285c4eee6ba6174','3','c9046f575edf470cbd5f6c57b0f34916'),('78d613067ff04b6da8f6698c8e5cb56e','3','c4a1f2a16cf241eaa1fc197e0897a11c'),('8cfb87af710b4b8ca69623aa99c4bbeb','3','bf5923dcf8784ea9906864017c673afc'),('d6877d1d4fea4d41b3b2901c403e9ed9','3','85a2d318d9504ce1ae40eae863cc6058');
/*!40000 ALTER TABLE `tbl_comment_love` ENABLE KEYS */;
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
  `loves` int(11) DEFAULT '0',
  `friends` int(11) DEFAULT '0',
  `followers` int(11) DEFAULT '0',
  `fans` int(11) DEFAULT '0',
  `background` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user`
--

LOCK TABLES `tbl_user` WRITE;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` VALUES ('1','9','1@qq.com','1','河北师范大学','http://10.7.88.195:9000/campus-bucket/users/428d21d0bc1b4e28b608afbb55aa6856.jpeg','2023-11-22 16:42:31',1,'9',2,6,16,5,'http://192.168.144.132:9000/campus-bucket/users/1edac165b5b74558b62bd2d672d30dc4.jpeg'),('2','欲心','789@qq.com','123456','123','http://10.7.88.195:9000/campus-bucket/activity/04375f22b0134ccfafade858c7621d02.jpeg','2023-11-22 17:15:10',1,NULL,0,1,1,1,'http://192.168.144.132:9000/campus-bucket/users/5f76d3e783544b55b9f63586e8dc46ca.jpeg'),('3','zhjm','728753465@qq.com','521125','河北师范大学','http://10.7.88.195:9000/campus-bucket/activity/04375f22b0134ccfafade858c7621d02.jpeg','2023-11-22 16:42:31',0,NULL,13,5,4,15,'http://192.168.144.132:9000/campus-bucket/users/0ae46b4ee1cb44b1b0ed8117045bc3ce.jpeg'),('53d5a04bb18e449ab6b08c483d39a265','无敌暴龙','1783034566@qq.com','725626','河北师范大学','http://192.168.3.36:9000/campus-bucket/users/b07fbc1a890b4162a37d9389fbde43a8.null','2023-12-26 21:17:45',0,NULL,0,0,0,0,'http://192.168.144.132:9000/campus-bucket/activity/04375f22b0134ccfafade858c7621d02.jpeg'),('8ad4fc5b81c54cb0a6ad3ad26bbd1ae1','7771','789777@qq.com','27773432','河北师范大学','http://10.7.88.195:9000/campus-bucket/users/default.jpg','2023-12-12 09:42:30',0,NULL,0,0,0,0,'http://192.168.144.132:9000/campus-bucket/users/ca4bfd49a4d544ad9504f47d9adcf1d1.jpeg'),('9ee35a3b11e748789fbf22ecf3c94cb8','joker','1241250055@qq.com','123','河北师范大学','http://192.168.3.36:9000/campus-bucket/users/599a9556f79b4df6b09e7516b502497c.null','2023-12-26 21:17:56',0,'我嫩爹',4,0,0,0,'http://192.168.144.132:9000/campus-bucket/activity/04375f22b0134ccfafade858c7621d02.jpeg');
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_user_follow`
--

DROP TABLE IF EXISTS `tbl_user_follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_user_follow` (
  `follow_id` varchar(100) NOT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  `to_user_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`follow_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user_follow`
--

LOCK TABLES `tbl_user_follow` WRITE;
/*!40000 ALTER TABLE `tbl_user_follow` DISABLE KEYS */;
INSERT INTO `tbl_user_follow` VALUES ('0e84ce8a2e854fbda10916be228e3f6c','3','1'),('6d33a0e942134f93b97e3d13c32d240e','1','3'),('b2fb6cc306194db89a97ad53f1b9fd71','3','1'),('c9f736c09c9843fe9eea548a843c31b4','1','3');
/*!40000 ALTER TABLE `tbl_user_follow` ENABLE KEYS */;
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

-- Dump completed on 2023-12-27 11:17:57

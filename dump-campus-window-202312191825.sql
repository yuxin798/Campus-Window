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
  `name` varchar(100) DEFAULT NULL,
  `avatar` varchar(100) DEFAULT NULL,
  `num` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_link`
--

LOCK TABLES `chat_link` WRITE;
/*!40000 ALTER TABLE `chat_link` DISABLE KEYS */;
INSERT INTO `chat_link` VALUES ('0773b8785ca748dfb7d81545441efd15','0773b8785ca748dfb7d81545441efd15','2023-12-19 17:47:23',0,NULL,NULL,'2'),('adac782c81e140fa9f984ae75acaca73','adac782c81e140fa9f984ae75acaca73','2023-12-18 22:42:12',0,NULL,NULL,'2'),('bc520a9cb12a4ecfbcd6cdbca9b82c8a','bc520a9cb12a4ecfbcd6cdbca9b82c8a','2023-12-18 22:42:05',0,NULL,NULL,'2'),('dda27352a3f74e2aaec7d4f2d17cb062','dda27352a3f74e2aaec7d4f2d17cb062','2023-12-18 22:43:08',1,'hello','http://192.168.144.132:9000/campus-bucket/users/1edac165b5b74558b62bd2d672d30dc4.jpeg','3');
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
INSERT INTO `chat_list` VALUES ('34a69b1602b24f7998d52ef2515d8cfc','adac782c81e140fa9f984ae75acaca73','3',0,'jia d  ba \n','2023-12-19 18:23:43',0,1),('4c8de97c5bde4c0ba191c18f1e4f4932','0773b8785ca748dfb7d81545441efd15','1',0,'请开始聊天吧','2023-12-19 17:47:23',0,1),('6eab6d5e83e64106be2c75757d4747f7','adac782c81e140fa9f984ae75acaca73','1',0,'jia d  ba \n','2023-12-19 18:23:43',3,1),('a024ffff048e4e5f97613dfe6e821a1a','0773b8785ca748dfb7d81545441efd15','2',0,'请开始聊天吧','2023-12-19 17:47:23',0,1),('b1d7f94647ca4b3fb5db66a1102c7a52','dda27352a3f74e2aaec7d4f2d17cb062','2',0,'2313131','2023-12-19 18:24:17',7,1),('dbcf73dcb4514c8f8f65e54f4087def2','dda27352a3f74e2aaec7d4f2d17cb062','1',0,'2313131','2023-12-19 18:24:17',0,1),('ea125c39456f4ab1b173421c022c30ba','dda27352a3f74e2aaec7d4f2d17cb062','3',0,'2313131','2023-12-19 18:24:17',0,1);
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
INSERT INTO `chat_message` VALUES ('013485cc9efb4c928a6054105b2e14b8','dda27352a3f74e2aaec7d4f2d17cb062','1','woaini','2023-12-19 17:36:38',0),('04cc31a7898748848cc36e4b993314ee','adac782c81e140fa9f984ae75acaca73','3','999','2023-12-19 11:12:07',0),('05a89cbe0b6249f6b1a1fbf9b13d7cee','adac782c81e140fa9f984ae75acaca73','1','33322222222232','2023-12-19 10:55:01',0),('0e15b2ab83b64a489d9f6b73950c983b','dda27352a3f74e2aaec7d4f2d17cb062','1','23131','2023-12-19 18:24:15',0),('0e55bd7371804364a513199c7d3463b1','adac782c81e140fa9f984ae75acaca73','3','777777\n','2023-12-19 11:02:04',0),('1117b958fc534aa3abc98b4a9e8c3ed2','adac782c81e140fa9f984ae75acaca73','1','33332','2023-12-19 10:35:45',0),('1446a0c5c1c245839fa481de974da3ee','adac782c81e140fa9f984ae75acaca73','3','wo ye ai ni\n','2023-12-19 18:23:11',0),('1931fd617597499185a3d2640effacac','adac782c81e140fa9f984ae75acaca73','1','55555','2023-12-19 11:03:45',0),('1e4b4de1c3ae4199bd2f24cbab9a2d1a','adac782c81e140fa9f984ae75acaca73','1','3223223','2023-12-19 10:59:53',0),('1e8f67156b1c4108b33fdf4abbfe10c2','adac782c81e140fa9f984ae75acaca73','1','我爱你','2023-12-19 18:22:53',0),('1ea6936d583c4c1a971b4866b52b4968','adac782c81e140fa9f984ae75acaca73','3','777777777','2023-12-19 11:12:25',0),('30fd9efbf7b64a849df286fdcbccef85','adac782c81e140fa9f984ae75acaca73','1','999\n','2023-12-19 11:12:11',0),('31bc308b69184dc89370026a6b906994','dda27352a3f74e2aaec7d4f2d17cb062','1','999','2023-12-19 18:09:17',0),('3376aa95ba7a4a8face38a27ce2adac1','adac782c81e140fa9f984ae75acaca73','3','3222424','2023-12-19 10:59:44',0),('344b6bf50cae4beb893256a94a66a664','dda27352a3f74e2aaec7d4f2d17cb062','3','58888','2023-12-19 17:36:18',0),('358eaa5c80fb4ada9d76b656b70674de','adac782c81e140fa9f984ae75acaca73','3','555555555','2023-12-19 11:10:37',0),('36d1ff4624cf4ba9943ed7a6fb3cad14','adac782c81e140fa9f984ae75acaca73','3','222','2023-12-19 10:53:32',0),('377ce381026f41bfaca820dcc9889eb9','adac782c81e140fa9f984ae75acaca73','3','54188','2023-12-19 11:12:32',0),('37906fd655a34b25b4865c9477f0c794','adac782c81e140fa9f984ae75acaca73','3','8888888888','2023-12-19 17:32:03',0),('39d55de7f6e544dea9e013e45f2808a8','adac782c81e140fa9f984ae75acaca73','3','333','2023-12-19 10:38:25',0),('3aa611bcc70d4a1da82f514e1db50f0b','adac782c81e140fa9f984ae75acaca73','3','18845','2023-12-19 17:35:55',0),('5075e1a8d9ee43f392e5473875ab532f','adac782c81e140fa9f984ae75acaca73','1','54188','2023-12-19 10:57:13',0),('51d3466b360d4c99bcd4154d2fca38fd','dda27352a3f74e2aaec7d4f2d17cb062','1','54188','2023-12-19 18:08:57',0),('550b215b3e2c485c92e09a09f7c5b599','dda27352a3f74e2aaec7d4f2d17cb062','2','888888','2023-12-19 17:38:14',0),('5c345d66d84f4999928f2dbaa0971412','adac782c81e140fa9f984ae75acaca73','1','9999999999','2023-12-19 11:01:23',0),('5d72c85efdea46619f120f1cd642b310','adac782c81e140fa9f984ae75acaca73','3','33333333','2023-12-19 11:04:25',0),('5ebb41f82b0b48a7bc6b9351d4bae76b','dda27352a3f74e2aaec7d4f2d17cb062','3','7777777','2023-12-19 17:38:08',0),('63009b6448c64b51a195f41db72e1866','dda27352a3f74e2aaec7d4f2d17cb062','1','6666\n','2023-12-19 18:23:59',0),('64f9a9ce36be40f99d3a68cd4a826b78','adac782c81e140fa9f984ae75acaca73','3','2222','2023-12-19 10:57:59',0),('6bd7d13ac92c4f3198fd5a80b2768a2c','adac782c81e140fa9f984ae75acaca73','3','999\n','2023-12-19 17:31:00',0),('6bffa8202a5542b1a39556f2c97b8a2d','adac782c81e140fa9f984ae75acaca73','1','33332','2023-12-19 10:53:35',0),('76fa3647f4eb49db9bedaeeb77267eef','dda27352a3f74e2aaec7d4f2d17cb062','3','2313131','2023-12-19 18:24:17',0),('7893fd77567343f39edef00277b86d8a','adac782c81e140fa9f984ae75acaca73','3','jia d  ba \n','2023-12-19 18:23:43',0),('7bd876c26e184301bd3261605555a26c','dda27352a3f74e2aaec7d4f2d17cb062','1','7777','2023-12-19 17:36:26',0),('817574cd6b834c5bb2c4622c1af37c5c','dda27352a3f74e2aaec7d4f2d17cb062','2','7777','2023-12-19 17:37:19',0),('9016046778134819bff668a66a386a69','adac782c81e140fa9f984ae75acaca73','1','54188','2023-12-19 10:58:03',0),('93b2b9191f574612b9180ec1cb31b8cb','adac782c81e140fa9f984ae75acaca73','3','zhen de jiade ','2023-12-19 18:23:37',0),('95b36f8d13f6421c94717b14e4a0b8d2','adac782c81e140fa9f984ae75acaca73','1','54188','2023-12-19 17:35:44',0),('962268f171224afd9c4fdc07414a1957','adac782c81e140fa9f984ae75acaca73','3','3333333333333333','2023-12-19 11:10:47',0),('9822593a717e4a12a900ad765bcf6534','adac782c81e140fa9f984ae75acaca73','3','88875','2023-12-19 10:57:19',0),('9fd1a135c70947acbc1e02a4aa485c85','dda27352a3f74e2aaec7d4f2d17cb062','1','9999','2023-12-19 17:36:24',0),('a0fd0f4c788f49febfb499d5a8e879a1','adac782c81e140fa9f984ae75acaca73','1','3333','2023-12-19 11:10:24',0),('a231401660884fefb4e4757b9e03923c','adac782c81e140fa9f984ae75acaca73','3','6666666','2023-12-19 17:31:59',0),('a3fe87b1f69c48a28c3c174dee4ca82e','adac782c81e140fa9f984ae75acaca73','1','33332','2023-12-19 10:54:52',0),('b5bbb0d60c4a44778811db2598b78491','dda27352a3f74e2aaec7d4f2d17cb062','2','trrrr','2023-12-19 17:37:37',0),('ba737b8f5a9b434098283335d791e9e0','dda27352a3f74e2aaec7d4f2d17cb062','3','5115\n','2023-12-19 17:36:48',0),('bd36d1b4bfa6499582eb407c79fbf324','dda27352a3f74e2aaec7d4f2d17cb062','2','woaini','2023-12-19 17:37:28',0),('c06031f714cb426ca9ca79e30e0921bf','dda27352a3f74e2aaec7d4f2d17cb062','3','999','2023-12-19 17:37:34',0),('c20df6441fda4555b2988e301584d0e1','adac782c81e140fa9f984ae75acaca73','1','我真的好爱你呀\n','2023-12-19 18:23:02',0),('c6959c431c9c4705b7a195a2998bb3f5','adac782c81e140fa9f984ae75acaca73','1','```','2023-12-19 11:03:13',0),('c81fee15d07b49448b787a4a1c7b7f10','adac782c81e140fa9f984ae75acaca73','1','33332','2023-12-19 10:35:45',0),('cbe2179bb430452ab4055b884c66c4e0','adac782c81e140fa9f984ae75acaca73','3','333','2023-12-19 10:58:01',0),('d9516ed1bb4b4dceb632b9cec5fb3b2b','dda27352a3f74e2aaec7d4f2d17cb062','1','....','2023-12-19 17:36:30',0),('df9e5f5492b54e8789597dc8c04cc6a1','dda27352a3f74e2aaec7d4f2d17cb062','1','9999','2023-12-19 18:24:06',0),('e14f9c0c3b1f4db0820993c96dac30e0','adac782c81e140fa9f984ae75acaca73','1','33322222222232','2023-12-19 10:57:06',0),('e1aa2881944b47288300d87d0ceb9e7b','adac782c81e140fa9f984ae75acaca73','1','88888\n','2023-12-19 11:12:20',0),('e9a7513090734e719e0fcee3f99625a9','adac782c81e140fa9f984ae75acaca73','1','1111','2023-12-19 11:01:16',0),('e9c367e924734417838482b59f77c948','adac782c81e140fa9f984ae75acaca73','1','18845','2023-12-19 11:12:37',0),('eb76ffe085d84daa80290a7164644c29','adac782c81e140fa9f984ae75acaca73','3','w hao ai ni \n','2023-12-19 18:23:22',0),('ee6f80efae704e4ea1995900c79547a4','adac782c81e140fa9f984ae75acaca73','1','444444','2023-12-19 11:10:32',0),('f2f0d8e1ea0047988534d67460566734','adac782c81e140fa9f984ae75acaca73','1','33322222222232','2023-12-19 10:55:46',0),('f392df8cc0bd4e2783a930d73961ea01','dda27352a3f74e2aaec7d4f2d17cb062','3','ba ','2023-12-19 18:24:10',0),('f4263e0ea3e04fbda51e4f06187f69f7','adac782c81e140fa9f984ae75acaca73','3','3333','2023-12-19 10:36:38',0),('f8416da943254d12a9abf834e1fe10ce','adac782c81e140fa9f984ae75acaca73','1','54188','2023-12-19 10:58:03',0),('f8914de88c46449ca231131ef6f475f5','adac782c81e140fa9f984ae75acaca73','1','55555555','2023-12-19 11:10:51',0),('feb3a87b302943908c15356caefb2c71','dda27352a3f74e2aaec7d4f2d17cb062','3','9999','2023-12-19 17:32:18',0),('feee17c512014d8bbe2e99385659d73b','adac782c81e140fa9f984ae75acaca73','1','33332','2023-12-19 10:35:45',0);
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
INSERT INTO `tbl_activity` VALUES ('2c91f4ecce1e4c08a36cf83a80e47b39','2133331','3131','2023-12-16 14:02:50','3','河北师范大学',0,2,0,0);
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
INSERT INTO `tbl_activity_love` VALUES ('386fbecc97734a70bf5610300a6543db','2','79ae19f6954c4535ba83e2eb081ea82f'),('76857343cbd24f2bbc02083edff72ba7','2','9ffe85f8aea44bb08305d880f5f1f8d7'),('7afdcc51ac10482190a062405586f6ed','1','9ffe85f8aea44bb08305d880f5f1f8d7'),('876910aa4cba4d9ba3f521b20e10d79c','2','05b9e0a1e1bc4f2eb0619ecd1d07355f'),('899e331a882a4fb89f9df81a91554a33','2','f19e7ea5ed52469a92fea5ca2207df6b'),('9dea65faef294dc395abde9741f29c4e','1','0d6f0381e84e424d897c7c305b2e8e52'),('a7962756de8b4090b57a9c2a725c59e2','1','58fea0298b05448285c0e6bd721b524e'),('d8a620cacd3244ee97a27b31702cd804','1','05b9e0a1e1bc4f2eb0619ecd1d07355f');
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
INSERT INTO `tbl_comment` VALUES ('05cd781b4d9b407081b17230f4060b4d','2c91f4ecce1e4c08a36cf83a80e47b39','3','我乃刘德助',0,'2023-12-18 10:28:34',NULL,NULL),('229dd370fb3048a0ba381e497daf9b6b','2c91f4ecce1e4c08a36cf83a80e47b39','2','德助',0,'2023-12-18 10:28:38',NULL,NULL),('87ecebf8513a4b76bc9ca89866f83794','2c91f4ecce1e4c08a36cf83a80e47b39','1','你能拿我咋滴',0,'2023-12-18 10:29:12','05cd781b4d9b407081b17230f4060b4d','3'),('a234113471374ef1bc63642b38695c39','2c91f4ecce1e4c08a36cf83a80e47b39','3','你能拿我咋滴',0,'2023-12-18 10:29:16','05cd781b4d9b407081b17230f4060b4d','2');
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
INSERT INTO `tbl_comment_love` VALUES ('0e4bf99c95614a11af7f3c43fa769262','3','eeb9e9eaabab419f88dc834e5cf3db08'),('3edee291757d4f939c5827874b12c93d','1','64948d79743b432ebc7786c8223a8f58'),('69cdd047c9ac4e1780695353d73d1630','1','2305cb58e2ba49ef89aa16b5f4d9c924'),('e2fe5be79a55418bbedd0ea7057451ed','1','f5183badafdf4d6eb1c273027ba6d47b'),('f5061b1b8580454b9ecccd568868fa3d','2','eeb9e9eaabab419f88dc834e5cf3db08');
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
INSERT INTO `tbl_user` VALUES ('1','9','1@qq.com','1','河北师范大学','http://192.168.144.132:9000/campus-bucket/users/428d21d0bc1b4e28b608afbb55aa6856.jpeg','2023-11-22 16:42:31',1,'9',2,1,1,1,'http://192.168.144.132:9000/campus-bucket/users/1edac165b5b74558b62bd2d672d30dc4.jpeg'),('2','欲心','789@qq.com','123456','123','http://192.168.144.132:9000/campus-bucket/activity/04375f22b0134ccfafade858c7621d02.jpeg','2023-11-22 17:15:10',1,NULL,0,1,1,1,'http://192.168.144.132:9000/campus-bucket/users/5f76d3e783544b55b9f63586e8dc46ca.jpeg'),('3','zhjm','728753465@qq.com','521125','河北师范大学','http://192.168.144.132:9000/campus-bucket/activity/04375f22b0134ccfafade858c7621d02.jpeg','2023-11-22 16:42:31',0,NULL,0,0,0,0,'http://192.168.144.132:9000/campus-bucket/users/0ae46b4ee1cb44b1b0ed8117045bc3ce.jpeg'),('8ad4fc5b81c54cb0a6ad3ad26bbd1ae1','7771','789777@qq.com','27773432','河北师范大学','http://192.168.144.132:9000/campus-bucket/users/default.jpg','2023-12-12 09:42:30',0,NULL,0,0,0,0,'http://192.168.144.132:9000/campus-bucket/users/ca4bfd49a4d544ad9504f47d9adcf1d1.jpeg');
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
INSERT INTO `tbl_user_follow` VALUES ('4f185af5aab149179d92af1cbc47e86b','2','1'),('91e6f916c85b4ad2bfa0e57357dbae9b','1','2');
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

-- Dump completed on 2023-12-19 18:25:57

-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: yst_vblog
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article` (
  `arti_id` int(4) NOT NULL AUTO_INCREMENT,
  `user_id` int(4) NOT NULL,
  `arti_title` varchar(20) NOT NULL,
  `arti_cont` mediumtext NOT NULL,
  `watched_times` int(11) DEFAULT NULL,
  `create_time` bigint(20) DEFAULT NULL,
  `sign_city` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`arti_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `article_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (1,1,'tom_first_text','tom5',33,NULL,NULL),(2,10,'岳赛涛的博客','岳赛涛\r\n123\r\nabc',97,NULL,NULL),(3,11,'cat\'diary','cat say today i catch a fish!\r\nno\r\nit\'s 2 !!',26,NULL,NULL),(4,10,'岳赛涛的博客2','2333klkl',10,NULL,NULL),(8,10,'yst_5','又是难过的一天，哎，嘤嘤嘤！',16,NULL,NULL),(9,10,'test in longhsine','llllllongshine',5,NULL,NULL),(10,10,'岳小桃','yyy,QAQ',NULL,NULL,'辽宁省沈阳'),(11,10,'岳小桃1','TAT',1,NULL,'广西壮族自治区南宁'),(12,10,'岳小桃1','TAT',NULL,NULL,'广西壮族自治区南宁'),(13,10,'岳小桃1','TAT',NULL,NULL,'广西壮族自治区南宁'),(14,10,'岳小桃555','wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !wo tai nan le !',3,NULL,'湖北省武汉'),(15,10,'岳小桃dd','dd',NULL,NULL,'河南省郑州'),(16,10,'岳小桃2333','1111111111111111111122222222222222222222222\r\nso sos soos sosos sososos soooooos soooos sosososososos sooos ssosososossososo so sos soos sosos sososos soooooos soooos sosososososos sooos ssosososossososo so sos soos sosos sososos soooooos soooos sosososososos sooos ssosososossososo so sos soos sosos sososos soooooos soooos sosososososos sooos ssosososossososo so sos soos sosos sososos soooooos soooos sosososososos sooos ssosososossososo so sos soos sosos sososos soooooos soooos sosososososos sooos ssosososossososo',2,1567151882678,'广东省广州');
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comm_arti`
--

DROP TABLE IF EXISTS `comm_arti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comm_arti` (
  `a_id` int(11) DEFAULT NULL,
  `c_id` int(11) DEFAULT NULL,
  KEY `a_id` (`a_id`),
  KEY `c_id` (`c_id`),
  CONSTRAINT `comm_arti_ibfk_1` FOREIGN KEY (`a_id`) REFERENCES `article` (`arti_id`),
  CONSTRAINT `comm_arti_ibfk_2` FOREIGN KEY (`c_id`) REFERENCES `comment` (`comm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comm_arti`
--

LOCK TABLES `comm_arti` WRITE;
/*!40000 ALTER TABLE `comm_arti` DISABLE KEYS */;
INSERT INTO `comm_arti` VALUES (1,5),(2,7),(2,8),(2,9),(4,10),(9,11),(8,12);
/*!40000 ALTER TABLE `comm_arti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comm_comm`
--

DROP TABLE IF EXISTS `comm_comm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comm_comm` (
  `c_id` int(11) DEFAULT NULL,
  `second_c_id` int(11) DEFAULT NULL,
  KEY `c_id` (`c_id`),
  KEY `second_c_id` (`second_c_id`),
  CONSTRAINT `comm_comm_ibfk_1` FOREIGN KEY (`c_id`) REFERENCES `comment` (`comm_id`),
  CONSTRAINT `comm_comm_ibfk_2` FOREIGN KEY (`second_c_id`) REFERENCES `comment` (`comm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comm_comm`
--

LOCK TABLES `comm_comm` WRITE;
/*!40000 ALTER TABLE `comm_comm` DISABLE KEYS */;
INSERT INTO `comm_comm` VALUES (2,6);
/*!40000 ALTER TABLE `comm_comm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comm_user`
--

DROP TABLE IF EXISTS `comm_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comm_user` (
  `u_id` int(11) DEFAULT NULL,
  `c_id` int(11) DEFAULT NULL,
  KEY `u_id` (`u_id`),
  KEY `c_id` (`c_id`),
  CONSTRAINT `comm_user_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `comm_user_ibfk_2` FOREIGN KEY (`c_id`) REFERENCES `comment` (`comm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comm_user`
--

LOCK TABLES `comm_user` WRITE;
/*!40000 ALTER TABLE `comm_user` DISABLE KEYS */;
INSERT INTO `comm_user` VALUES (1,4),(1,5),(1,2),(10,7),(10,8),(10,9),(10,10),(10,11),(10,12);
/*!40000 ALTER TABLE `comm_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `comm_id` int(11) NOT NULL AUTO_INCREMENT,
  `comm_cont` varchar(255) DEFAULT NULL,
  `comm_time` date DEFAULT NULL,
  PRIMARY KEY (`comm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (2,'vvvvv','2019-08-22'),(3,'vvvvv','2019-08-22'),(4,'vvvvv','2019-08-22'),(5,'vvvvv','2019-08-22'),(6,'nice','2019-08-22'),(7,'very good','2019-08-23'),(8,'very good','2019-08-23'),(9,'very good','2019-08-23'),(10,'very good','2019-08-23'),(11,'very good2222','2019-08-23'),(12,'aaa','2019-08-23');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `follow_id` int(11) NOT NULL AUTO_INCREMENT,
  `follow_user_id` int(11) DEFAULT NULL,
  `followed_user_id` int(11) DEFAULT NULL,
  `follow_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`follow_id`),
  KEY `follow_user_id` (`follow_user_id`),
  KEY `followed_user_id` (`followed_user_id`),
  CONSTRAINT `follow_ibfk_1` FOREIGN KEY (`follow_user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `follow_ibfk_2` FOREIGN KEY (`followed_user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (17,10,2,'2019-08-11 16:00:00'),(20,10,11,'2019-08-11 16:00:00'),(23,10,1,'2019-08-20 16:00:00');
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `followed_arti`
--

DROP TABLE IF EXISTS `followed_arti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `followed_arti` (
  `f_arti_id` int(11) NOT NULL AUTO_INCREMENT,
  `arti_id` int(11) NOT NULL,
  `follow_user_id` int(11) NOT NULL,
  PRIMARY KEY (`f_arti_id`),
  KEY `arti_id` (`arti_id`),
  KEY `follow_user_id` (`follow_user_id`),
  CONSTRAINT `followed_arti_ibfk_1` FOREIGN KEY (`arti_id`) REFERENCES `article` (`arti_id`),
  CONSTRAINT `followed_arti_ibfk_2` FOREIGN KEY (`follow_user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `followed_arti`
--

LOCK TABLES `followed_arti` WRITE;
/*!40000 ALTER TABLE `followed_arti` DISABLE KEYS */;
INSERT INTO `followed_arti` VALUES (76,3,10),(78,1,10);
/*!40000 ALTER TABLE `followed_arti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rep_comm`
--

DROP TABLE IF EXISTS `rep_comm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rep_comm` (
  `r_id` int(11) NOT NULL,
  `c_id` int(11) NOT NULL,
  KEY `r_id` (`r_id`),
  KEY `c_id` (`c_id`),
  CONSTRAINT `rep_comm_ibfk_1` FOREIGN KEY (`r_id`) REFERENCES `repost` (`id`),
  CONSTRAINT `rep_comm_ibfk_2` FOREIGN KEY (`c_id`) REFERENCES `comment` (`comm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rep_comm`
--

LOCK TABLES `rep_comm` WRITE;
/*!40000 ALTER TABLE `rep_comm` DISABLE KEYS */;
/*!40000 ALTER TABLE `rep_comm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rep_user`
--

DROP TABLE IF EXISTS `rep_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rep_user` (
  `r_id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  KEY `r_id` (`r_id`),
  KEY `u_id` (`u_id`),
  CONSTRAINT `rep_user_ibfk_1` FOREIGN KEY (`r_id`) REFERENCES `repost` (`id`),
  CONSTRAINT `rep_user_ibfk_2` FOREIGN KEY (`u_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rep_user`
--

LOCK TABLES `rep_user` WRITE;
/*!40000 ALTER TABLE `rep_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `rep_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repost`
--

DROP TABLE IF EXISTS `repost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `repost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rep_cont` varchar(255) DEFAULT '转发YST博客',
  `rep_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repost`
--

LOCK TABLES `repost` WRITE;
/*!40000 ALTER TABLE `repost` DISABLE KEYS */;
INSERT INTO `repost` VALUES (1,'test_repost',1566809792462),(2,'test_repost',1566809860243),(3,NULL,1566811348318),(4,NULL,1566811435494),(5,'test_repost222',1566811450471),(6,'test_repost222',1566811468329),(7,'test_repost222',1566811569811),(8,'test_repost222',1566811607573),(9,'test_repost222',1566811986222),(10,'test_repost222',1566812718913),(11,'test_repost222',1566812814098);
/*!40000 ALTER TABLE `repost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int(3) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(10) NOT NULL,
  `user_pw` varchar(10) NOT NULL,
  `user_addr` varchar(10) NOT NULL,
  `user_img` varchar(45) DEFAULT NULL,
  `user_nickname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'tom','123456','fushun',NULL,NULL),(2,'kate','1234','shenyang',NULL,NULL),(3,'lily','12345','beijing',NULL,NULL),(5,'a','1234','大连',NULL,NULL),(6,'yst','123','hangzhou',NULL,NULL),(7,'yst2','1234','11',NULL,NULL),(8,'yst3','1','1',NULL,NULL),(9,'ystl','12','12',NULL,NULL),(10,'岳赛涛','1234','杭州',NULL,NULL),(11,'cat','1234','cat',NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-30 18:05:12

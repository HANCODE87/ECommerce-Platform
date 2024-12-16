-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: ecommerce_db
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用戶id',
  `username` varchar(255) NOT NULL COMMENT '用戶名稱',
  `password` varchar(255) NOT NULL COMMENT '用戶密碼',
  `email` varchar(255) NOT NULL COMMENT '用戶email',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '用戶創建時間',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '用戶更新時間',
  `role` enum('admin','user') NOT NULL DEFAULT 'user' COMMENT '用戶身分',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `user_pk` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'老大','string','d1@gmail.com','2024-10-17 19:34:55','2024-10-17 19:34:55','user'),(2,'老二','string','e4@gmail.com','2024-10-17 19:37:04','2024-10-17 19:37:04','user'),(4,'c','string','2@gma','2024-10-17 20:02:28','2024-10-17 20:02:28','user'),(5,'string2','string','22','2024-10-26 17:14:22','2024-10-26 17:14:22','user'),(6,'string3','string','33','2024-10-26 17:19:49','2024-10-26 17:19:49','user'),(7,'admin','123456','1','2024-11-06 16:53:15','2024-11-06 16:53:18','user'),(9,'adminr','123456','11@gf','2024-11-06 20:02:39','2024-11-06 20:02:39','user'),(10,'adminA','123456A','11@gfA','2024-11-06 20:36:43','2024-11-06 20:36:43','user'),(11,'adminㄇ','123456ㄎ','1ㄎ1','2024-11-06 20:37:06','2024-11-06 20:37:06','user'),(15,'admins','123456','4@4','2024-11-08 20:16:37','2024-11-08 20:16:37','user'),(16,'adminss','123456','4@4s','2024-11-08 20:19:41','2024-11-08 20:19:41','user');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-17  0:47:44

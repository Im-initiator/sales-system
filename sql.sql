CREATE DATABASE  IF NOT EXISTS `springsale` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `springsale`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: springsale
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `cart_shop`
--

DROP TABLE IF EXISTS `cart_shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_shop` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_by` varchar(255) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `size_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_nwe0h0c2a3jy1e33ov3hbmiv6` (`product_id`),
  KEY `FK_iebyqcwpuf43wihx8a9lbwhkb` (`size_id`),
  KEY `FK_ivkekkkmk04nqau3gmykbkse` (`user_id`),
  CONSTRAINT `FK_iebyqcwpuf43wihx8a9lbwhkb` FOREIGN KEY (`size_id`) REFERENCES `size` (`id`),
  CONSTRAINT `FK_ivkekkkmk04nqau3gmykbkse` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_nwe0h0c2a3jy1e33ov3hbmiv6` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_shop`
--

LOCK TABLES `cart_shop` WRITE;
/*!40000 ALTER TABLE `cart_shop` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_by` varchar(255) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'sleeveless shirt','SLEEVELESS','manager','2024-02-27 00:25:23','manager','2024-02-27 00:25:23');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_gender`
--

DROP TABLE IF EXISTS `category_gender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_gender` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_by` varchar(255) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_gender`
--

LOCK TABLES `category_gender` WRITE;
/*!40000 ALTER TABLE `category_gender` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_gender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_shop`
--

DROP TABLE IF EXISTS `category_shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_shop` (
  `shop_id` bigint NOT NULL,
  `category_id` bigint NOT NULL,
  KEY `FK_olvhphjhmle9tvgiegbwtrtsr` (`shop_id`),
  KEY `FK_it1b7yxn0drigosrwoefse78n` (`category_id`),
  CONSTRAINT `FK_olvhphjhmle9tvgiegbwtrtsr` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_shop`
--

LOCK TABLES `category_shop` WRITE;
/*!40000 ALTER TABLE `category_shop` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite`
--

DROP TABLE IF EXISTS `favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorite` (
  `user_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  KEY `FK_h558axqsc0s736d8fddv6uphs` (`product_id`),
  KEY `FK_8f4j36u3ealttx057oeppnphm` (`user_id`),
  CONSTRAINT `FK_8f4j36u3ealttx057oeppnphm` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_h558axqsc0s736d8fddv6uphs` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite`
--

LOCK TABLES `favorite` WRITE;
/*!40000 ALTER TABLE `favorite` DISABLE KEYS */;
/*!40000 ALTER TABLE `favorite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gender_shop`
--

DROP TABLE IF EXISTS `gender_shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gender_shop` (
  `category_id` bigint NOT NULL,
  `gender_id` bigint NOT NULL,
  KEY `FK_qx11ph7nr34mlyrqxw75am4bt` (`gender_id`),
  KEY `FK_gi1n1ewm5hb3p7552a5eqp1gg` (`category_id`),
  CONSTRAINT `FK_gi1n1ewm5hb3p7552a5eqp1gg` FOREIGN KEY (`category_id`) REFERENCES `category_gender` (`id`),
  CONSTRAINT `FK_qx11ph7nr34mlyrqxw75am4bt` FOREIGN KEY (`gender_id`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gender_shop`
--

LOCK TABLES `gender_shop` WRITE;
/*!40000 ALTER TABLE `gender_shop` DISABLE KEYS */;
/*!40000 ALTER TABLE `gender_shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_by` varchar(255) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `content` text,
  `img` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `prize` float DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `sell_number` int DEFAULT NULL,
  `short_description` varchar(255) DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  `category_gender` bigint DEFAULT NULL,
  `shop_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_q85w9uabk8x6ui8ttphoc0r2s` (`category_gender`),
  KEY `FK_cslu4pketkpf6w88medleeymj` (`shop_id`),
  KEY `FK_rlaghtegr0yx2c1q1s6nkqjlh` (`category_id`),
  CONSTRAINT `FK_cslu4pketkpf6w88medleeymj` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`),
  CONSTRAINT `FK_q85w9uabk8x6ui8ttphoc0r2s` FOREIGN KEY (`category_gender`) REFERENCES `category_gender` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_size`
--

DROP TABLE IF EXISTS `product_size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_size` (
  `product_id` bigint NOT NULL,
  `size_id` bigint NOT NULL,
  KEY `FK_2qboh3u92xbmc3rih64xtboup` (`size_id`),
  KEY `FK_knvo23wgm5dxpwum9lppevnga` (`product_id`),
  CONSTRAINT `FK_2qboh3u92xbmc3rih64xtboup` FOREIGN KEY (`size_id`) REFERENCES `size` (`id`),
  CONSTRAINT `FK_knvo23wgm5dxpwum9lppevnga` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_size`
--

LOCK TABLES `product_size` WRITE;
/*!40000 ALTER TABLE `product_size` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_size` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_order`
--

DROP TABLE IF EXISTS `purchase_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_by` varchar(255) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `note` text,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `quantity` int NOT NULL,
  `receAddress` text NOT NULL,
  `recipient_name` varchar(255) DEFAULT NULL,
  `sendAddress` text NOT NULL,
  `shipper_id` bigint DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `status` tinyint NOT NULL,
  `product_id` bigint NOT NULL,
  `transport_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_mh283nd624s5s3emomtkar5wa` (`product_id`),
  KEY `FK_36gvsw6fi78goca8y0pouj6dg` (`transport_id`),
  KEY `FK_1lvg6ax0rvgmxdnbnniidup5s` (`user_id`),
  CONSTRAINT `FK_1lvg6ax0rvgmxdnbnniidup5s` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_36gvsw6fi78goca8y0pouj6dg` FOREIGN KEY (`transport_id`) REFERENCES `transport` (`id`),
  CONSTRAINT `FK_mh283nd624s5s3emomtkar5wa` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_order`
--

LOCK TABLES `purchase_order` WRITE;
/*!40000 ALTER TABLE `purchase_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchase_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_by` varchar(255) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,NULL,NULL,NULL,NULL,'ADMIN','admin'),(2,NULL,NULL,NULL,NULL,'USER','user'),(3,'admin','2024-02-25 23:55:03','admin','2024-02-25 23:55:03','SALER','salesman'),(4,'admin','2024-02-25 23:55:19','admin','2024-02-25 23:55:19','SHIPPER','shipper'),(5,'admin','2024-02-25 23:56:15','admin','2024-02-25 23:56:15','MANAGER','manager'),(6,'admin','2024-02-25 23:56:29','admin','2024-02-25 23:56:29','CENSOR','censor');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_by` varchar(255) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `reviews` float DEFAULT NULL,
  `short_description` text,
  `thumbnail` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES (1,'user','2024-02-26 13:25:40','user','2024-02-26 16:35:52','Hà Nội',NULL,'Women’s fashion',0,'<p>Women&rsquo;s fashion in Ha Nois</p>\r\n','/template/image/category-1tm3620240226_163552.jpg','Women’sfashion@gmail.com','028432328'),(2,'user1','2024-02-26 23:39:43','user1','2024-02-26 23:39:43','Hà Nội',NULL,'Men’s fashion',0,'<p>Men&rsquo;s fashion</p>\r\n','/template/image/category-2tm3620240226_233943.jpg','Men’s fashion@gmail.com','0559919290234'),(3,'user2','2024-02-26 23:47:04','user2','2024-02-26 23:47:04','Hà Nội',NULL,'Cosmetics',0,'<h4>Cosmetics</h4>\r\n','/template/image/category-4tm3620240226_234704.jpg','Cosmetics@gmail.com','0559919290'),(4,'user3','2024-02-26 23:47:54','user3','2024-02-26 23:47:54','Hà Nội',NULL,'Kid’s fashion',0,'<h4>Kid&rsquo;s fashion</h4>\r\n','/template/image/category-3tm3620240226_234753.jpg','Kid’s fashion@gmail.com','0559919290'),(5,'user4','2024-02-26 23:49:34','user4','2024-02-26 23:49:34','Hà Nội',NULL,'Accessories',0,'<h4>Accessories</h4>\r\n','/template/image/category-5tm3620240226_234933.jpg','Accessories@gmail.com','0559919290');
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `size`
--

DROP TABLE IF EXISTS `size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `size` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_by` varchar(255) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `size`
--

LOCK TABLES `size` WRITE;
/*!40000 ALTER TABLE `size` DISABLE KEYS */;
/*!40000 ALTER TABLE `size` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `size_shop`
--

DROP TABLE IF EXISTS `size_shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `size_shop` (
  `shop_id` bigint NOT NULL,
  `size_id` bigint NOT NULL,
  KEY `FK_gy7oxjd3k3q4ybnr364njm6ey` (`size_id`),
  KEY `FK_7468fwwrhiu7wm247qupy7ehl` (`shop_id`),
  CONSTRAINT `FK_7468fwwrhiu7wm247qupy7ehl` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`),
  CONSTRAINT `FK_gy7oxjd3k3q4ybnr364njm6ey` FOREIGN KEY (`size_id`) REFERENCES `size` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `size_shop`
--

LOCK TABLES `size_shop` WRITE;
/*!40000 ALTER TABLE `size_shop` DISABLE KEYS */;
/*!40000 ALTER TABLE `size_shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transport`
--

DROP TABLE IF EXISTS `transport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transport` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_by` varchar(255) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transport`
--

LOCK TABLES `transport` WRITE;
/*!40000 ALTER TABLE `transport` DISABLE KEYS */;
/*!40000 ALTER TABLE `transport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_by` varchar(255) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `money` double DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  `shop_id` bigint DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `detail` text,
  PRIMARY KEY (`id`),
  KEY `FK_esehgck2vxe3t6t4qrby70y47` (`shop_id`),
  CONSTRAINT `FK_esehgck2vxe3t6t4qrby70y47` FOREIGN KEY (`shop_id`) REFERENCES `shop` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'anonymousUser','2024-02-16 01:14:04','user','2024-02-26 13:25:40','Thanh Hóa','leminhtien@gmail.com','Lê Minh Tiến',0,'$2a$10$PKUexTjMK70kYixB/sGpcOTPwkEWcAibVq3EQ901e9pw/A.f8Je9G','482984562986',1,'user',1,'/template/image/OIPtm3620240225_233917.jfif','Detailsss'),(3,NULL,NULL,'admin','2024-02-26 00:17:57','Thanh Hóa','admin@gmail.com','Admin',NULL,'$2a$10$PKUexTjMK70kYixB/sGpcOTPwkEWcAibVq3EQ901e9pw/A.f8Je9G','',1,'admin',NULL,'/template/image/img usertm3620240226_001756.jpg','I am admin'),(4,NULL,NULL,'admin','2024-02-26 00:10:11','Hà Nội',NULL,'Your Shipper',NULL,'$2a$10$KoUvy9rKRpJOQCpPjR7fcuKLcqgFxmEPB2.am6GDsS4/P0dZFyvku','234242223',1,'shipper',NULL,NULL,NULL),(5,'admin','2024-02-26 00:09:53','admin','2024-02-26 00:09:53','Hà Nội','manager@gmail.com','Your Manager',0,'$2a$10$04Z7ZH63XHe9DKbbUImSr.cz2G2Ql34oZ416Y0cITD7SfGqZUNu16','05448934',1,'manager',NULL,'/template/web/user/images/user.jpg',NULL),(6,'admin','2024-02-26 00:10:59','admin','2024-02-26 00:10:59','Hà Nội','censo@gmail.com','Your Censor',0,'$2a$10$ygVusIjKttXvt6Hh.YUBGOoUwqe7GbCv0hmy.Xiu/rvI/XlB61nGO','0435344353',1,'censor',NULL,'/template/web/user/images/user.jpg',NULL),(7,'anonymousUser','2024-02-26 23:38:40','user1','2024-02-26 23:39:43','Hà Nội','user1@gmail.com','minhtien le',0,'$2a$10$.om1LcLbbVGPr13ZtWsi4.PwSBTSwbj57cfZ0GL.FFQ3TktDEiN6i','0559919290232',1,'user1',2,'/template/web/user/images/user.jpg',NULL),(8,'anonymousUser','2024-02-26 23:42:29','user3','2024-02-26 23:47:54','Hà Nội','user3@gmail.com','minhtien le',0,'$2a$10$Zk6BL9btn3oZamgbjMwXUeL4KMZ3d5acz5RYmcbEmw9Vkkep.9taO','0559919290',1,'user3',4,'/template/web/user/images/user.jpg',NULL),(9,'anonymousUser','2024-02-26 23:46:22','user2','2024-02-26 23:47:04','Hà Nội','user2@gmail.com','Lê Minh Tiến',0,'$2a$10$MMuM0YTo3IMHoUor2638kuUgsyBRpjMkFgZroC2holMTwXuFUq7xW','0559919290',1,'user2',3,'/template/web/user/images/user.jpg',NULL),(10,'anonymousUser','2024-02-26 23:49:03','user4','2024-02-26 23:49:34','Hà Nội','thanh@gmail.com','Lê Minh Thành',0,'$2a$10$JKuVq0Wzk8Dw2dM.jRZU8O4YZcQQDFVxb6ypWkNWIPhDxBGe52c5m','0559919290',1,'user4',5,'/template/web/user/images/user.jpg',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  KEY `FK_it77eq964jhfqtu54081ebtio` (`role_id`),
  KEY `FK_apcc8lxk2xnug8377fatvbn04` (`user_id`),
  CONSTRAINT `FK_apcc8lxk2xnug8377fatvbn04` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_it77eq964jhfqtu54081ebtio` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (3,1),(5,2),(5,5),(6,2),(6,6),(2,2),(2,3),(7,2),(7,3),(9,2),(9,3),(8,2),(8,3),(10,2),(10,3);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'springsale'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-26 14:46:46

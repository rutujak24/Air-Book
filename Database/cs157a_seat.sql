CREATE DATABASE  IF NOT EXISTS `cs157a` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cs157a`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: cs157a
-- ------------------------------------------------------
-- Server version	5.6.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seat` (
  `sID` char(3) NOT NULL,
  `row` char(1) DEFAULT NULL,
  `seatNo` int(11) DEFAULT NULL,
  `taken` tinyint(1) DEFAULT NULL,
  `planeID` int(11) DEFAULT NULL,
  PRIMARY KEY (`sID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat`
--

LOCK TABLES `seat` WRITE;
/*!40000 ALTER TABLE `seat` DISABLE KEYS */;
INSERT INTO `seat` VALUES ('A01','A',1,1,1234),('A02','A',2,1,1234),('A03','B',1,1,1234),('A04','B',2,1,1234),('A05','C',1,1,1234),('A06','C',2,1,1234),('A07','F',1,1,1234),('A08','F',2,1,1234),('B01','A',1,1,1867),('B02','A',2,1,1867),('B03','B',1,0,1867),('B04','B',2,0,1867),('B05','C',1,0,1867),('B06','C',2,0,1867),('B07','F',1,0,1867),('B08','F',2,0,1867),('C01','A',1,0,2685),('C02','A',2,0,2685),('C03','B',1,0,2685),('C04','B',2,0,2685),('C05','C',1,0,2685),('C06','C',2,0,2685),('C07','F',1,0,2685),('C08','F',2,0,2685),('D01','A',1,0,3212),('D02','A',2,0,3212),('D03','B',1,0,3212),('D04','B',2,0,3212),('D05','C',1,0,3212),('D06','C',2,0,3212),('D07','F',1,0,3212),('D08','F',2,0,3212),('E01','A',1,0,4325),('E02','A',2,0,4325),('E03','B',1,0,4325),('E04','B',2,0,4325),('E05','C',1,0,4325),('E06','C',2,0,4325),('E07','F',1,0,4325),('E08','F',2,0,4325),('F01','A',1,0,5432),('F02','A',2,0,5432),('F03','B',1,0,5432),('F04','B',2,0,5432),('F05','C',1,0,5432),('F06','C',2,0,5432),('F07','F',1,0,5432),('F08','F',2,0,5432),('G01','A',1,0,6987),('G02','A',2,0,6987),('G03','B',1,0,6987),('G04','B',2,0,6987),('G05','C',1,0,6987),('G06','C',2,0,6987),('G07','F',1,0,6987),('G08','F',2,0,6987),('H01','A',1,0,7234),('H02','A',2,0,7234),('H03','B',1,0,7234),('H04','B',2,0,7234),('H05','C',1,0,7234),('H06','C',2,0,7234),('H07','F',1,0,7234),('H08','F',2,0,7234);
/*!40000 ALTER TABLE `seat` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-11-26 23:39:27

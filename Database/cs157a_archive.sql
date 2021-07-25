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
-- Table structure for table `archive`
--

DROP TABLE IF EXISTS `archive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `archive` (
  `flightID` int(11) NOT NULL,
  `destination` varchar(255) DEFAULT NULL,
  `depDate` date DEFAULT NULL,
  `depTime` time DEFAULT NULL,
  `planeID` int(11) DEFAULT NULL,
  `pilotID` int(11) DEFAULT NULL,
  `gateID` char(4) DEFAULT NULL,
  PRIMARY KEY (`flightID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `archive`
--

LOCK TABLES `archive` WRITE;
/*!40000 ALTER TABLE `archive` DISABLE KEYS */;
INSERT INTO `archive` VALUES (101,'Tokyo, Japan','2013-12-25','01:20:00',1234,10,'A001'),(102,'Manilla, Philippines','2013-11-02','13:00:00',1867,11,'A002'),(103,'New Dehli, India','2013-11-25','13:30:00',2685,1,'A003'),(104,'Zurich, Switzerland','2013-11-06','01:45:00',3212,2,'A004'),(105,'Taipei, Taiwan','2013-12-07','06:50:00',4325,3,'A002'),(106,'Seoul, Korea','2014-01-02','09:15:00',5432,4,'A001'),(107,'Taipei, Taiwan','2014-01-19','16:10:00',6987,5,'A004'),(108,'New Delhi, India','2013-12-20','15:00:00',7234,6,'A003');
/*!40000 ALTER TABLE `archive` ENABLE KEYS */;
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

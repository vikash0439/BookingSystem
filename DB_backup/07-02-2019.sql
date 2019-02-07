-- MySQL dump 10.13  Distrib 5.5.50, for Win32 (x86)
--
-- Host: localhost    Database: srcpa
-- ------------------------------------------------------
-- Server version	5.5.50

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
-- Current Database: `srcpa`
--

/*!40000 DROP DATABASE IF EXISTS `srcpa`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `srcpa` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `srcpa`;

--
-- Table structure for table `allocation`
--

DROP TABLE IF EXISTS `allocation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `allocation` (
  `allocationid` bigint(20) NOT NULL AUTO_INCREMENT,
  `allocatedamount` bigint(20) NOT NULL,
  `contractid` bigint(20) NOT NULL,
  `receiptno` bigint(20) NOT NULL,
  PRIMARY KEY (`allocationid`),
  KEY `FKsgkd3e7x4ftn2hoo0u0sry0nr` (`contractid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allocation`
--

LOCK TABLES `allocation` WRITE;
/*!40000 ALTER TABLE `allocation` DISABLE KEYS */;
/*!40000 ALTER TABLE `allocation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking` (
  `serviceid` bigint(20) NOT NULL AUTO_INCREMENT,
  `slot` varchar(255) DEFAULT NULL,
  `servicecost` varchar(255) DEFAULT NULL,
  `servicedate` varchar(255) DEFAULT NULL,
  `servicetime` varchar(255) DEFAULT NULL,
  `serviceused` varchar(255) DEFAULT NULL,
  `contractid` bigint(20) DEFAULT NULL,
  `servicename` varchar(255) DEFAULT NULL,
  `contract_contractid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`serviceid`),
  KEY `FKpoixtm1h4gb7p8ft9txg44ub9` (`contract_contractid`),
  KEY `FKs727j3fbu88a7fedfl13i5rwn` (`contractid`)
) ENGINE=MyISAM AUTO_INCREMENT=61 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,'Morning','33000','24/01/2019','10:00 - 14:00hrs',NULL,1,'Mor. Show',NULL),(2,'Evening','37500','24/01/2019','14:00 - 22:00hrs',NULL,1,'Eve. Show',NULL),(3,'Morning','33000','25/01/2019','10:00 - 14:00hrs',NULL,1,'Mor. Show',NULL),(4,'Evening','37500','25/01/2019','14:00 - 22:00hrs',NULL,1,'Eve. Show',NULL),(5,'Morning','33000','26/01/2019','10:00 - 14:00hrs',NULL,1,'Mor. Show',NULL),(6,'Evening','37500','26/01/2019','14:00 - 22:00hrs',NULL,1,'Eve. Show',NULL),(7,'Evening','37500','01/01/2019','14:00 - 22:00hrs',NULL,2,'Eve. Show',NULL),(8,'Morning','18500','01/01/2019','10:00 - 14:00hrs',NULL,2,'Mor. Rehersal',NULL),(9,'Morning','33000','01/01/2019','10:00 - 14:00hrs',NULL,2,'Mor. Show',NULL),(10,'Evening','37500','06/01/2019','14:00 - 22:00hrs',NULL,3,'Eve. Show',NULL),(11,'Morning','33000','06/01/2019','10:00 - 14:00hrs',NULL,3,'Mor. Show',NULL),(12,'Morning','33000','20/01/2019','10:00 - 14:00hrs',NULL,4,'Mor. Show',NULL),(13,'Morning','33000','22/01/2019','10:00 - 14:00hrs',NULL,4,'Mor. Show',NULL),(14,'Morning','33000','18/01/2019','10:00 - 14:00hrs',NULL,4,'Mor. Show',NULL),(15,'Morning','33000','07/01/2019','10:00 - 14:00hrs',NULL,5,'Mor. Show',NULL),(16,'Evening','37500','07/01/2019','14:00 - 22:00hrs',NULL,5,'Eve. Show',NULL),(17,'Morning','33000','15/01/2019','10:00 - 14:00hrs',NULL,5,'Mor. Show',NULL),(18,'Evening','37500','15/01/2019','14:00 - 22:00hrs',NULL,5,'Eve. Show',NULL),(19,'Morning','33000','30/01/2019','10:00 - 14:00hrs',NULL,5,'Mor. Show',NULL),(20,'Evening','37500','30/01/2019','14:00 - 22:00hrs',NULL,5,'Eve. Show',NULL),(21,'Morning','33000','08/03/2019','10:00 - 14:00hrs',NULL,6,'Mor. Show',NULL),(22,'Morning','33000','15/03/2019','10:00 - 14:00hrs',NULL,6,'Mor. Show',NULL),(23,'Morning','33000','22/03/2019','10:00 - 14:00hrs',NULL,6,'Mor. Show',NULL),(24,'Morning','18500','10/12/2018','10:00 - 14:00hrs',NULL,7,'Mor. Rehersal',NULL),(25,'Evening','37500','10/12/2018','14:00 - 22:00hrs',NULL,7,'Eve. Show',NULL),(26,'Morning','18500','11/12/2018','10:00 - 14:00hrs',NULL,7,'Mor. Rehersal',NULL),(27,'Evening','37500','11/12/2018','14:00 - 22:00hrs',NULL,7,'Eve. Show',NULL),(28,'Morning','18500','12/12/2018','10:00 - 14:00hrs',NULL,7,'Mor. Rehersal',NULL),(29,'Evening','37500','12/12/2018','14:00 - 22:00hrs',NULL,7,'Eve. Show',NULL),(30,'Morning','18500','13/12/2018','10:00 - 14:00hrs',NULL,7,'Mor. Rehersal',NULL),(31,'Evening','37500','13/12/2018','14:00 - 22:00hrs',NULL,7,'Eve. Show',NULL),(32,'Morning','18500','14/12/2018','10:00 - 14:00hrs',NULL,7,'Mor. Rehersal',NULL),(33,'Evening','37500','14/12/2018','14:00 - 22:00hrs',NULL,7,'Eve. Show',NULL),(34,'Morning','33000','08/01/2019','10:00 - 14:00hrs',NULL,8,'Mor. Show',NULL),(35,'Morning','33000','10/01/2019','10:00 - 14:00hrs',NULL,8,'Mor. Show',NULL),(36,'Morning','33000','10/02/2019','10:00 - 14:00hrs',NULL,9,'Mor. Show',NULL),(37,'Evening','37500','12/02/2019','14:00 - 22:00hrs',NULL,9,'Eve. Show',NULL),(42,'Morning','18500','14/02/2019','10:00 - 14:00hrs',NULL,10,'Mor. Rehersal',NULL),(51,'Morning','33000','10/03/2019','10:00 - 14:00hrs',NULL,14,'Mor. Show',NULL),(44,'Morning','33000','16/02/2019','10:00 - 14:00hrs',NULL,10,'Mor. Show',NULL),(45,'Evening','33000','18/02/2019','14:00 - 22:00hrs',NULL,11,'Mor. Show',NULL),(47,'Morning','33000','31/12/2018','10:00 - 14:00hrs',NULL,12,'Mor. Show',NULL),(48,'Morning','33000','24/02/2019','10:00 - 14:00hrs',NULL,13,'Mor. Show',NULL),(49,'Evening','33000','26/02/2019','14:00 - 22:00hrs',NULL,13,'Mor. Show',NULL);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER bookingtrigger 
    AFTER DELETE ON booking
    FOR EACH ROW 
BEGIN

    INSERT INTO bookingold( slot, serviceid, servicecost, servicedate, contractid, servicename, changedat)
    values ( old.slot, old.serviceid, old.servicecost, old.servicedate, old.contractid, old.servicename, NOW()); 
    
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `bookingold`
--

DROP TABLE IF EXISTS `bookingold`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookingold` (
  `bookingoldid` int(11) NOT NULL AUTO_INCREMENT,
  `serviceid` int(11) DEFAULT NULL,
  `slot` varchar(255) DEFAULT NULL,
  `servicecost` varchar(255) DEFAULT NULL,
  `servicedate` varchar(255) DEFAULT NULL,
  `contractid` varchar(255) DEFAULT NULL,
  `servicename` varchar(255) DEFAULT NULL,
  `changedat` datetime DEFAULT NULL,
  PRIMARY KEY (`bookingoldid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookingold`
--

LOCK TABLES `bookingold` WRITE;
/*!40000 ALTER TABLE `bookingold` DISABLE KEYS */;
INSERT INTO `bookingold` VALUES (1,59,'Morning','33000','13/02/2019','14','Mor. Show','2019-02-04 00:00:00'),(2,38,'Afternoon','2000','14/02/2019','9','Food','2019-02-04 00:00:00'),(3,41,'Evening','2000','12/02/2019','10','Food','2019-02-04 14:37:32'),(4,40,'Morning','2000','10/02/2019','10','Food','2019-02-04 14:49:44'),(5,46,'Morning','33000','19/02/2019','11','Mor. Show','2019-02-07 10:57:54');
/*!40000 ALTER TABLE `bookingold` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookingrequest`
--

DROP TABLE IF EXISTS `bookingrequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookingrequest` (
  `bookingrequestid` bigint(20) NOT NULL AUTO_INCREMENT,
  `bookingpurpose` varchar(255) DEFAULT NULL,
  `customername` varchar(255) DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `entryby` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `preferreddate` varchar(255) DEFAULT NULL,
  `secondtable` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bookingrequestid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookingrequest`
--

LOCK TABLES `bookingrequest` WRITE;
/*!40000 ALTER TABLE `bookingrequest` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookingrequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cancelcriteria`
--

DROP TABLE IF EXISTS `cancelcriteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cancelcriteria` (
  `cancelcriteriaid` bigint(20) NOT NULL AUTO_INCREMENT,
  `cancelcharges` varchar(255) DEFAULT NULL,
  `canceltimemax` varchar(255) DEFAULT NULL,
  `canceltimemin` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cancelcriteriaid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cancelcriteria`
--

LOCK TABLES `cancelcriteria` WRITE;
/*!40000 ALTER TABLE `cancelcriteria` DISABLE KEYS */;
/*!40000 ALTER TABLE `cancelcriteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contract` (
  `contractid` bigint(20) NOT NULL AUTO_INCREMENT,
  `baseprice` varchar(255) DEFAULT NULL,
  `bookingdate` varchar(255) DEFAULT NULL,
  `override` varchar(255) DEFAULT NULL,
  `pact` varchar(255) DEFAULT NULL,
  `paymentstatus` varchar(255) DEFAULT NULL,
  `purpose` varchar(255) DEFAULT NULL,
  `slabno` varchar(255) DEFAULT NULL,
  `taxamount` varchar(255) DEFAULT NULL,
  `customerid` bigint(20) DEFAULT NULL,
  `repname` varchar(255) DEFAULT NULL,
  `noc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`contractid`),
  KEY `FK6y15vdr97pc7huhqp1j6vpxn1` (`customerid`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
INSERT INTO `contract` VALUES (1,'211500','24/01/2019',NULL,'249570.0',NULL,'Rehersal',NULL,'38070.0',1,'ok','Yes'),(2,'89000','24/01/2019',NULL,'105020.0',NULL,'Rehersal',NULL,'16020.0',3,'ABCDE','Yes'),(3,'70500','24/01/2019',NULL,'83190.0',NULL,'Meetings',NULL,'12690.0',4,'Vikash Kumar','Yes'),(4,'99000','25/01/2019',NULL,'116820.0','Advanced','Meetings',NULL,'17820.0',5,'AK Sharma','Yes'),(5,'211500','28/01/2019',NULL,'249570.0','Advanced','Rehersal',NULL,'38070.0',1,'ok','Yes'),(6,'99000','28/01/2019',NULL,'116820.0',NULL,'Theatre',NULL,'17820.0',6,'Ram Prakash','Yes'),(7,'280000','28/01/2019',NULL,'330400.0',NULL,'Meetings',NULL,'50400.0',4,'B Gupta','Yes'),(8,'202500','28/01/2019',NULL,'238950.0','Advanced','Meetings',NULL,'36450.0',4,'B Gupta','Yes'),(9,'263500','29/01/2019','cancelled','310930.0','Advanced','Rehersal',NULL,'47430.0',1,'ok','Yes'),(10,'90000','29/01/2019',NULL,'106200.0',NULL,'Meetings',NULL,'16200.0',5,'AK Sharma','Yes'),(11,'132000','29/01/2019',NULL,'155760.0',NULL,'Meetings',NULL,'23760.0',3,'ABCDE','Yes'),(12,'33000','29/01/2019',NULL,'38940.0','Advanced','Rehersal',NULL,'5940.0',7,'Ram','Yes'),(13,'167000','30/01/2019',NULL,'197060.0',NULL,'Play',NULL,'30060.0',6,'Ram Prakash','Yes'),(14,'238650','01/02/2019',NULL,'281607.0','Advanced','Theatre',NULL,'42957.0',1,'ok','Yes');
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customerid` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `customername` varchar(255) DEFAULT NULL,
  `gstno` varchar(255) DEFAULT NULL,
  `landline` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `statecode` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customerid`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Mandi House, Delhi','A','ABC Drama ','AASDGGH12345JKL','0123456789','Active','DELHI (07)','www.abc.com'),(2,'Delhi','A','SRCPA','07AAATI1040E1ZB','0123456789','','DELHI (07)','www.srcpa.in'),(3,'Mumbai','A','Street Play Co','ASDFGHJ5678hjiO','32465798','Best','05','www.street.com'),(4,'Delhi','','DCM Ltd','N/A','0123145678','A','DELHI (07)','www.dcm.in'),(5,'Govindpuri, Kalkaji, New Delhi- 110019','A','Acharya Narendra Dev College','N/A','0123456789','Good','DELHI (07)','www.acha.com'),(6,'24-24, nd Floor, Haryana','A','Drama & Co','ASDFGH4567GHJ','0123456789','Good','HARYANA (06)','www.dd.com'),(7,'Delhi','A','All Ltd','ASDFGHJ34567HJKI','011111','','MANIPUR (14)','www.all.com'),(8,'bbb','bbb','bbb','bbb','bbb','bbb','DELHI (07)','bbb');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `holiday`
--

DROP TABLE IF EXISTS `holiday`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `holiday` (
  `holidayid` bigint(20) NOT NULL AUTO_INCREMENT,
  `hdate` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`holidayid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `holiday`
--

LOCK TABLES `holiday` WRITE;
/*!40000 ALTER TABLE `holiday` DISABLE KEYS */;
/*!40000 ALTER TABLE `holiday` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice` (
  `invoiceid` bigint(20) NOT NULL AUTO_INCREMENT,
  `cancelled` varchar(255) DEFAULT NULL,
  `invoicedate` varchar(255) DEFAULT NULL,
  `contract_contractid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`invoiceid`),
  KEY `FKiyuvljmhb4igyaraexqtwmswi` (`contract_contractid`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (1,'','28/01/2019',6);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymentdetails`
--

DROP TABLE IF EXISTS `paymentdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paymentdetails` (
  `paymentdetailsid` bigint(20) NOT NULL AUTO_INCREMENT,
  `credit` varchar(255) DEFAULT NULL,
  `modebank` varchar(255) DEFAULT NULL,
  `modedate` varchar(255) DEFAULT NULL,
  `paidby` varchar(255) DEFAULT NULL,
  `receipt_receiptid` bigint(20) DEFAULT NULL,
  `modeid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`paymentdetailsid`),
  KEY `FK8xlp9cbbdrqjjh2gsk5kfhr5o` (`receipt_receiptid`)
) ENGINE=MyISAM AUTO_INCREMENT=169 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymentdetails`
--

LOCK TABLES `paymentdetails` WRITE;
/*!40000 ALTER TABLE `paymentdetails` DISABLE KEYS */;
INSERT INTO `paymentdetails` VALUES (1,'Yes','','','',NULL,''),(2,'No','','','',NULL,''),(3,'No','','','',NULL,''),(4,'Yes','','','',NULL,''),(5,'Yes','abc bank','10/10/2018','abc',NULL,'abc'),(6,'No','','','',NULL,''),(7,'No','','','',NULL,''),(8,'Yes','','','',NULL,''),(9,'Yes','','','',NULL,''),(10,'Yes','','','',NULL,''),(11,'No','','','',NULL,''),(12,'No','','','',NULL,''),(13,'No','','','',NULL,''),(14,'No','','','',NULL,''),(15,'No','','','',NULL,''),(16,'No','','','',NULL,''),(17,'No','','','',NULL,''),(18,'No','','','',NULL,''),(19,'No','','','',NULL,''),(20,'No','','','',NULL,''),(21,'No','','','',NULL,''),(22,'No','','','',NULL,''),(23,'No','','','',NULL,''),(24,'No','','','',NULL,''),(25,'No','','','',NULL,''),(26,'No','','','',NULL,''),(27,'No','','','',NULL,''),(28,'No','','','',NULL,''),(29,'No','','','',NULL,''),(30,'No','','','',NULL,''),(31,'No','','','',NULL,''),(32,'No','','','',NULL,''),(33,'No','','','',NULL,''),(34,'No','','','',NULL,''),(35,'No','','','',NULL,''),(36,'No','','','',NULL,''),(37,'No','','','',NULL,''),(38,'No','','','',NULL,''),(39,'No','','','',NULL,''),(40,'No','','','',NULL,''),(41,'No','','','',NULL,''),(42,'No','','','',NULL,''),(43,'No','','','',NULL,''),(44,'No','','','',NULL,''),(45,'No','','','',NULL,''),(46,'No','','','',NULL,''),(47,'No','','','',NULL,''),(48,'Yes','','','',NULL,''),(49,'No','','','',NULL,''),(50,'No','','','',NULL,''),(51,'No','','','',NULL,''),(52,'No','','','',NULL,''),(53,'No','','','',NULL,''),(54,'No','','','',NULL,''),(55,'No','','','',NULL,''),(56,'No','','','',NULL,''),(57,'No','','','',NULL,''),(58,'No','','','',NULL,''),(59,'No','','','',NULL,''),(60,'No','','','',NULL,''),(61,'No','','','',NULL,''),(62,'No','','','',NULL,''),(63,'No','hdfc','10/10/2018','vk',NULL,'sdfgh'),(64,'Yes','','','',NULL,''),(65,'No','','','',NULL,''),(66,'Yes','','','',NULL,''),(67,'Yes','','','',NULL,''),(68,'Yes','','','',NULL,''),(69,'No','','','',NULL,''),(70,'No','','','',NULL,''),(71,'Yes','','','',NULL,''),(72,'No','','','',NULL,''),(73,'No','','','',NULL,''),(74,'No','','','',NULL,''),(75,'No','','','',NULL,''),(76,'No','','','',NULL,''),(77,'No','SBI','2/11/2018','VK',NULL,'asdfg2345fgh'),(78,'No','','','',NULL,''),(79,'No','','','',NULL,''),(80,'Yes','','','',NULL,''),(81,'Yes','','','',NULL,''),(82,'No','','','',NULL,''),(83,'No','','','',NULL,''),(84,'No','','','',NULL,''),(85,'No','','','',NULL,''),(86,'Yes','','','',NULL,''),(87,'Yes','','','',NULL,''),(88,'No','','','',NULL,''),(89,'No','','','',NULL,''),(90,'Yes','','','',NULL,''),(91,'Yes','','','',NULL,''),(92,'Yes','','','',NULL,''),(93,'Yes','','','',NULL,''),(94,'Yes','','','',NULL,''),(95,'No','','','',NULL,''),(96,'No','','','',NULL,''),(97,'No','','','',NULL,''),(98,'Yes','','','',NULL,''),(99,'Yes','','','',NULL,''),(100,'Yes','','','',NULL,''),(101,'Yes','','','',NULL,''),(102,'No','','','',NULL,''),(103,'Yes','','','',NULL,''),(104,'Yes','','','',NULL,''),(105,'Yes','','1/11/2018','',NULL,'asdfghjkl'),(106,'Yes','','','',NULL,''),(107,'Yes','','','',NULL,''),(108,'Yes','','','',NULL,''),(109,'Yes','','6/11/2018','',NULL,'asdfgh34567'),(110,'Yes','','','',NULL,''),(111,'Yes','','','',NULL,''),(112,'No','','','',NULL,''),(113,'Yes','','','',NULL,''),(114,'Yes','','','',NULL,''),(115,'Yes','','','',NULL,''),(116,'Yes','','','',NULL,''),(117,'Yes','','','',NULL,''),(118,'Yes','','','',NULL,''),(119,'Yes','','','',NULL,''),(120,'Yes','','','',NULL,''),(121,'Yes','','','',NULL,''),(122,'Yes','','','',NULL,''),(123,'Yes','','','',NULL,''),(124,'Yes','','','',NULL,''),(125,'Yes','','','',NULL,''),(126,'Yes','','','',NULL,''),(127,'Yes','','','',NULL,''),(128,'Yes','','','',NULL,''),(129,'Yes','','','',NULL,''),(130,'Yes','','','',NULL,''),(131,'Yes','','','',NULL,''),(132,'Yes','','15/11/2018','',NULL,'DD NO: 12345'),(133,'Yes','','','',NULL,''),(134,'Yes','','','',NULL,''),(135,'No','SBI','18/11/2018','V Kumar',NULL,'DD NO:12345DFGHJ'),(136,'No','','5/11/2018','',NULL,'df34fgh:jkl'),(137,'Yes','','','',NULL,''),(138,'Yes','','','',NULL,''),(139,'No','','','',NULL,''),(140,'Yes','','','',NULL,''),(141,'Yes','','','',NULL,''),(142,'Yes','','','',NULL,''),(143,'Yes','','','',NULL,''),(144,'No','','','',NULL,''),(145,'Yes','','','',NULL,''),(146,'Yes','','','',NULL,''),(147,'No','','','',NULL,''),(148,'Yes','','','',NULL,''),(149,'Yes','SBI, Delhi','22/11/2018','',NULL,'23456789dfgh'),(150,'No','','22/11/2018','',NULL,'fgh456fg'),(151,'No','','14/11/2018','',NULL,''),(152,'No','SBI, CP Delhi','22/11/2018','',NULL,'bhiu5676ghnbb'),(153,'No','PNB, Hisar','15/11/2018','',NULL,'njihj7788bnjknkn0jj'),(154,'No','HDFC, Mandi House','22/11/2018','',NULL,'SR908765677GHG'),(155,'No','','','',NULL,''),(156,'Yes','','','',NULL,''),(157,'Yes','','','',NULL,''),(158,'Yes','','','',NULL,''),(159,'Yes','','','',NULL,''),(160,'Yes','','','',NULL,''),(161,'Yes','','','',NULL,''),(162,'Yes','SBI','12/12/2018','Vk',NULL,'nkdsfhkjdsnfkbdsjcbkjdb'),(163,'Yes','','','',NULL,''),(164,'No','SBI','12/12/2018','Vk',NULL,'nkdsfhkjdsnfkbdsjcbkjdb'),(165,'Yes','','','',NULL,''),(166,'No','','','',NULL,''),(167,'No','','','',NULL,''),(168,'Yes','','','',NULL,'');
/*!40000 ALTER TABLE `paymentdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `performance`
--

DROP TABLE IF EXISTS `performance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `performance` (
  `performanceid` bigint(20) NOT NULL AUTO_INCREMENT,
  `showdetails` varchar(255) DEFAULT NULL,
  `showname` varchar(255) DEFAULT NULL,
  `showtime` varchar(255) DEFAULT NULL,
  `contractid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`performanceid`),
  KEY `FKm7163a0bhbti60jaxiqqw3vs8` (`contractid`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performance`
--

LOCK TABLES `performance` WRITE;
/*!40000 ALTER TABLE `performance` DISABLE KEYS */;
INSERT INTO `performance` VALUES (1,'aaa','aa','aa',9),(2,'asdfg','asdfghj','asdf',10),(3,'Ticket from BookMyShow','Gang of Girls','All day',14);
/*!40000 ALTER TABLE `performance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purpose`
--

DROP TABLE IF EXISTS `purpose`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purpose` (
  `purposeid` bigint(20) NOT NULL AUTO_INCREMENT,
  `purpose` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`purposeid`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purpose`
--

LOCK TABLES `purpose` WRITE;
/*!40000 ALTER TABLE `purpose` DISABLE KEYS */;
INSERT INTO `purpose` VALUES (1,'Rehersal'),(2,'Play'),(3,'Meetings'),(4,'Theatre');
/*!40000 ALTER TABLE `purpose` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receipt`
--

DROP TABLE IF EXISTS `receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receipt` (
  `receiptid` bigint(20) NOT NULL AUTO_INCREMENT,
  `finalpayment` varchar(255) DEFAULT NULL,
  `paidamount` varchar(255) DEFAULT NULL,
  `paymentmode` varchar(255) DEFAULT NULL,
  `receiptdate` varchar(255) DEFAULT NULL,
  `taxamount` varchar(255) DEFAULT NULL,
  `contractid` bigint(20) DEFAULT NULL,
  `pdetails_paymentdetailsid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`receiptid`),
  KEY `FKchjc2on4rp8q7ngctc5us059u` (`contractid`),
  KEY `FK23uumkbgeijhbbjkplqxb6cus` (`pdetails_paymentdetailsid`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receipt`
--

LOCK TABLES `receipt` WRITE;
/*!40000 ALTER TABLE `receipt` DISABLE KEYS */;
INSERT INTO `receipt` VALUES (1,NULL,'100000','Cash','22/01/2019','15254',6,168);
/*!40000 ALTER TABLE `receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rep`
--

DROP TABLE IF EXISTS `rep`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rep` (
  `repid` bigint(20) NOT NULL AUTO_INCREMENT,
  `repemail` varchar(255) DEFAULT NULL,
  `repmobile` varchar(255) DEFAULT NULL,
  `repname` varchar(255) DEFAULT NULL,
  `customerid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`repid`),
  KEY `FK4g55imc2gjmpodywysdq2jpn6` (`customerid`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rep`
--

LOCK TABLES `rep` WRITE;
/*!40000 ALTER TABLE `rep` DISABLE KEYS */;
INSERT INTO `rep` VALUES (1,'pl','pl','ok',1),(2,'a','a','a',1),(3,'aaa','aa','AA',2),(4,'s','s','s',2),(7,'B@B.com','987654321','B Gupta',4),(8,'vikash@dcmtech.com','095461323789','Vikash Kumar',4),(9,'abs@abc.com','987645321','ABCDE',3),(10,'aaa@gmail.com','9876543210','AK Sharma',5),(11,'ram@gmail.com','9876543210','Ram Prakash',6),(12,'','0132546798','Ram',7),(13,'','','',3),(14,'','0321456987','Suman',3);
/*!40000 ALTER TABLE `rep` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserve`
--

DROP TABLE IF EXISTS `reserve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reserve` (
  `reserveid` bigint(20) NOT NULL AUTO_INCREMENT,
  `bookingdate` date DEFAULT NULL,
  `customerid` varchar(255) DEFAULT NULL,
  `internalusage` varchar(255) DEFAULT NULL,
  `reservetitle` varchar(255) DEFAULT NULL,
  `servicedate` varchar(255) DEFAULT NULL,
  `serviceid` varchar(255) DEFAULT NULL,
  `servicetime` varchar(255) DEFAULT NULL,
  `slot` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`reserveid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserve`
--

LOCK TABLES `reserve` WRITE;
/*!40000 ALTER TABLE `reserve` DISABLE KEYS */;
/*!40000 ALTER TABLE `reserve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `service` (
  `serviceid` bigint(20) NOT NULL AUTO_INCREMENT,
  `cancelcharges` varchar(255) DEFAULT NULL,
  `servicecharges` varchar(255) DEFAULT NULL,
  `serviceinuse` varchar(255) DEFAULT NULL,
  `servicename` varchar(255) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`serviceid`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,'','33000','Yes','Mor. Show','Day'),(2,'','37500','Yes','Eve. Show','Day'),(3,'','18500','Yes','Mor. Rehersal','Day'),(4,'','3000','Yes','Additional AC','Hour'),(5,'','150','Yes','abc','Unit'),(6,'','15000','Yes','Additional Show','Day'),(8,'','2000','Yes','Food','Plate'),(9,'','20000','','Goods',''),(10,'','15000','','Play','');
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slab`
--

DROP TABLE IF EXISTS `slab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `slab` (
  `slabid` bigint(20) NOT NULL AUTO_INCREMENT,
  `serviceid` bigint(20) NOT NULL,
  `slabno` bigint(20) NOT NULL,
  `taxid` bigint(20) NOT NULL,
  PRIMARY KEY (`slabid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slab`
--

LOCK TABLES `slab` WRITE;
/*!40000 ALTER TABLE `slab` DISABLE KEYS */;
/*!40000 ALTER TABLE `slab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slot`
--

DROP TABLE IF EXISTS `slot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `slot` (
  `slotid` bigint(20) NOT NULL AUTO_INCREMENT,
  `slot` varchar(255) DEFAULT NULL,
  `timings` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`slotid`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slot`
--

LOCK TABLES `slot` WRITE;
/*!40000 ALTER TABLE `slot` DISABLE KEYS */;
INSERT INTO `slot` VALUES (1,'Morning','10:00 - 14:00hrs'),(2,'Evening','14:00 - 22:00hrs'),(4,'Afternoon',''),(3,'Night','');
/*!40000 ALTER TABLE `slot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statecode`
--

DROP TABLE IF EXISTS `statecode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `statecode` (
  `statecodeid` bigint(20) NOT NULL AUTO_INCREMENT,
  `statecode` varchar(255) DEFAULT NULL,
  `statecodeno` varchar(255) DEFAULT NULL,
  `statename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`statecodeid`)
) ENGINE=MyISAM AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statecode`
--

LOCK TABLES `statecode` WRITE;
/*!40000 ALTER TABLE `statecode` DISABLE KEYS */;
INSERT INTO `statecode` VALUES (1,'01',NULL,'JAMMU AND KASHMIR'),(2,'02',NULL,'HIMACHAL PRADESH'),(3,'03',NULL,'PUNJAB'),(4,'04',NULL,'CHANDIGARH'),(5,'05',NULL,'UTTARAKHAND'),(6,'06',NULL,'HARYANA'),(7,'07',NULL,'DELHI'),(8,'08',NULL,'RAJASTHAN'),(9,'09',NULL,'UTTAR  PRADESH'),(10,'10',NULL,'BIHAR'),(11,'11',NULL,'SIKKIM'),(12,'12',NULL,'ARUNACHAL PRADESH'),(13,'13',NULL,'NAGALAND'),(14,'14',NULL,'MANIPUR'),(15,'15',NULL,'MIZORAM'),(16,'16',NULL,'TRIPURA'),(17,'17',NULL,'MEGHLAYA'),(18,'18',NULL,'ASSAM'),(19,'19',NULL,'WEST BENGAL'),(20,'20',NULL,'JHARKHAND'),(21,'21',NULL,'ODISHA'),(22,'22',NULL,'CHATTISGARH'),(23,'23',NULL,'MADHYA PRADESH'),(24,'24',NULL,'GUJARAT'),(25,'25',NULL,'DAMAN AND DIU'),(26,'26',NULL,'DADRA AND NAGAR HAVELI'),(27,'27',NULL,'MAHARASHTRA'),(28,'28',NULL,'ANDHRA PRADESH(BEFORE DIVISION)'),(29,'29',NULL,'KARNATAKA'),(30,'30',NULL,'GOA'),(31,'31',NULL,'LAKSHWADEEP'),(32,'32',NULL,'KERALA'),(33,'33',NULL,'TAMIL NADU'),(34,'34',NULL,'PUDUCHERRY'),(35,'35',NULL,'ANDAMAN AND NICOBAR ISLANDS'),(36,'36',NULL,'TELANGANA'),(37,'37',NULL,'ANDHRA PRADESH (NEW)');
/*!40000 ALTER TABLE `statecode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tax`
--

DROP TABLE IF EXISTS `tax`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tax` (
  `taxid` bigint(20) NOT NULL AUTO_INCREMENT,
  `applied` varchar(255) DEFAULT NULL,
  `taxcharges` varchar(255) DEFAULT NULL,
  `taxform` varchar(255) DEFAULT NULL,
  `taxinuse` varchar(255) DEFAULT NULL,
  `taxname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`taxid`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tax`
--

LOCK TABLES `tax` WRITE;
/*!40000 ALTER TABLE `tax` DISABLE KEYS */;
INSERT INTO `tax` VALUES (1,'Directly','9','Percentage','Yes','CGST (@9%)'),(2,'Directly','9','Percentage','Yes','SGST (@9%)'),(3,'On Tax','1000','Amount','No','Education'),(4,'Directly','','Percentage','Yes','IGST'),(5,'Directly','','Percentage','Yes','Child');
/*!40000 ALTER TABLE `tax` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tds`
--

DROP TABLE IF EXISTS `tds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tds` (
  `tdsid` bigint(20) NOT NULL AUTO_INCREMENT,
  `receiptno` bigint(20) NOT NULL,
  `tdsamount` bigint(20) NOT NULL,
  `tdscertificateno` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tdsid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tds`
--

LOCK TABLES `tds` WRITE;
/*!40000 ALTER TABLE `tds` DISABLE KEYS */;
/*!40000 ALTER TABLE `tds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dob` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2018-11-13','admin','Admin','Female','admin','admin','Admin'),(2,'2018-09-12','vikash.k@dcmtech.com','Vikash','Male','Kumar','vikash','Admin'),(3,'2012-08-22','bhavishya.g@dcmtech.com','Bhavishya','Female','Gupta','bhavishya','Admin');
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

-- Dump completed on 2019-02-07 12:18:05

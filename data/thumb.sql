-- MySQL dump 10.13  Distrib 5.5.12, for Win32 (x86)
--
-- Host: localhost    Database: thumb
-- ------------------------------------------------------
-- Server version	5.5.12

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
-- Table structure for table `t_record`
--

DROP TABLE IF EXISTS `t_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_record` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `start_address` varchar(255) DEFAULT NULL,
  `end_address` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `start_lat` double DEFAULT NULL,
  `start_lon` double DEFAULT NULL,
  `end_lat` double DEFAULT NULL,
  `end_lon` double DEFAULT NULL,
  `other` varchar(255) DEFAULT NULL,
  `c_type` int(11) DEFAULT NULL,
  `category` int(11) DEFAULT NULL,
  PRIMARY KEY (`record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_record`
--

LOCK TABLES `t_record` WRITE;
/*!40000 ALTER TABLE `t_record` DISABLE KEYS */;
INSERT INTO `t_record` VALUES (2,2,'Dora','Harvard University\n12 Holyoke Street, Cambridge, MA 02138-5014','Wall St\nMiddleboro, MA 02346','2011-05-26 08:00:00','2011-05-26 18:00:00',42.3793435424587,-71.11664772033691,41.903619,-70.82551,'It is a long way to go. Dame help me!',3,2),(3,3,'Lory','Harvard University\n12 Holyoke Street, Cambridge, MA 02138-5014','Wall St\nMiddleboro, MA 02346','2011-05-26 08:00:00','2011-05-26 18:00:00',42.3793435424587,-71.11664772033691,41.903619,-70.82551,'It is a long way to go. Dame help me!',3,2),(21,3,'Lory','NYU 44 W 4th St New York, NY 10012-1106 USA','Goldman Sachs Inner Ring Rd, Domlur Bengaluru, Karnataka India','2011-06-05 16:00:00','2011-06-05 16:00:00',40.7288951,-73.9962688,12.951024,77.6412338,'',2,3),(22,3,'Lory','Massachusetts Institute of Technology Cambridge, MA USA','Yellow Stone San Antonio, TX 78210 USA','2011-06-06 14:00:00','2011-06-06 14:00:00',42.3599263,-71.0958314,29.397646,-98.485193,'',3,2);
/*!40000 ALTER TABLE `t_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'yankai','15850736725','kaiyannju@gmail.com'),(2,'Dora','13975182238','dora@gmail.com'),(3,'Lory','91191191191','lory@gmail.com'),(4,'iratao','15951899234','iratao@gmail.com');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-06-06 22:41:22

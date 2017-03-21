-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: db_movie
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `directors`
--

DROP TABLE IF EXISTS `directors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `directors` (
  `director_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `director_firstNames` varchar(100) DEFAULT NULL,
  `director_lastName` varchar(100) NOT NULL,
  `imdb_id` varchar(7) NOT NULL,
  PRIMARY KEY (`director_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `directors`
--

LOCK TABLES `directors` WRITE;
/*!40000 ALTER TABLE `directors` DISABLE KEYS */;
INSERT INTO `directors` VALUES (1,'Sam','Raimi','0000600'),(2,'Zack','Snyder','0811583'),(3,'Mark Steven','Johnson','0425756'),(4,'Bryan','Singer','0001741'),(5,'Christopher','Nolan','0634240'),(6,'Matthew','Vaughn','0891216'),(7,'Tim','Miller','1783265'),(8,'David','Ayer','0043742'),(9,'Joss','Whedon','0923736'),(10,'Joe','Johnston','0002653'),(11,'Anthony','Russo','0751577'),(12,'Marc','Webb','1989536'),(13,'Jon','Favreau','0269463'),(14,'Shane','Black','0000948'),(15,'James','Gunn','0348181'),(16,'Brett','Ratner','0711840'),(17,'Alan','Taylor','0851930'),(18,'Kenneth','Branagh','0000110'),(19,'Martin','Campball','0132709'),(20,'Jonathan','Liebesman','0509448'),(21,'Josh','Trank','2503633'),(22,'Peyton','Reed','0715636'),(23,'test','fsdf','1111111');
/*!40000 ALTER TABLE `directors` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-21 15:34:32

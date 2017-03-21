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
-- Table structure for table `lookup_film_directors`
--

DROP TABLE IF EXISTS `lookup_film_directors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lookup_film_directors` (
  `film_id` int(10) unsigned NOT NULL,
  `director_id` int(10) unsigned NOT NULL,
  KEY `film_id` (`film_id`),
  KEY `director_id` (`director_id`),
  CONSTRAINT `lookup_film_directors_ibfk_1` FOREIGN KEY (`film_id`) REFERENCES `films` (`film_id`),
  CONSTRAINT `lookup_film_directors_ibfk_2` FOREIGN KEY (`director_id`) REFERENCES `directors` (`director_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lookup_film_directors`
--

LOCK TABLES `lookup_film_directors` WRITE;
/*!40000 ALTER TABLE `lookup_film_directors` DISABLE KEYS */;
INSERT INTO `lookup_film_directors` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,5),(7,2),(8,4),(9,6),(10,7),(11,8),(12,5),(13,9),(14,9),(15,10),(16,11),(17,11),(18,12),(19,13),(20,13),(21,14),(22,15),(23,16),(24,17),(25,18),(26,19),(27,12),(28,20),(29,21),(30,22),(48,23);
/*!40000 ALTER TABLE `lookup_film_directors` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-21 15:34:31

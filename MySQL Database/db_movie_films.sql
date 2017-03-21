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
-- Table structure for table `films`
--

DROP TABLE IF EXISTS `films`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `films` (
  `film_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `film_name` varchar(100) CHARACTER SET utf8 NOT NULL,
  `film_year` smallint(4) unsigned NOT NULL,
  `imdb_id` varchar(7) DEFAULT NULL,
  `imdb_rating` float(3,1) unsigned NOT NULL,
  `is_archived` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`film_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `films`
--

LOCK TABLES `films` WRITE;
/*!40000 ALTER TABLE `films` DISABLE KEYS */;
INSERT INTO `films` VALUES (1,'Spider-Man',2002,'0145487',7.3,0),(2,'Batman v Superman: Dawn of Justice',2016,'2975590',6.7,0),(3,'Daredevil',2003,'3322312',5.3,0),(4,'X-Men Apocalypse',2016,'3385516',7.1,0),(5,'Batman: The Dark Knight',2008,'0468569',9.0,0),(6,'Batman: The Dark Knight Rises',2012,'1345836',8.5,0),(7,'Man Of Steel',2013,'0770828',7.1,0),(8,'X-Men: Days of Future Past',2014,'1877832',8.0,0),(9,'X-Men: First Class',2011,'1270798',7.8,0),(10,'Deadpool',2016,'1431045',8.1,0),(11,'Suicide Squad',2016,'1386697',6.3,0),(12,'Batman Begins',2005,'0372784',8.3,0),(13,'Avengers Assemble',2012,'0848228',8.1,0),(14,'Avengers: Age of Ultron',2015,'2395427',7.4,0),(15,'Captain America: The First Avenger',2011,'0458339',6.9,0),(16,'Captain America: The Winter Soilder',2014,'1843866',7.8,0),(17,'Captain America: Civil War',2016,'3498820',7.9,0),(18,'The Amazing Spider-Man',2012,'0948470',7.0,0),(19,'Iron Man',2008,'0371746',7.9,0),(20,'Iron Man 2',2010,'1228705',7.0,0),(21,'Iron Man 3',2013,'1300854',7.2,0),(22,'Guardians Of The Galaxy',2014,'2015381',8.1,0),(23,'X-Men: The Last Stand',2006,'0376994',6.7,0),(24,'Thor: The Dark World',2013,'1981115',7.1,0),(25,'Thor',2011,'0800369',7.0,0),(26,'The Green Lantern',2011,'1133985',5.6,0),(27,'The Amazing Spider-Man 2',2014,'1872181',6.7,0),(28,'Teenage Mutant Ninja Turtles',2014,'1291150',5.9,0),(29,'Fantastic Four',2015,'1502712',4.3,0),(30,'Ant-Man',2015,'0478970',7.3,0),(31,'test',1234,'1234567',1.5,0),(32,'saji',1967,'1235467',3.4,0),(33,'saji',1967,'1235467',3.4,0),(34,'test',1234,'1234567',1.5,0),(35,'fish',2004,'1235789',2.5,0),(36,'fish',2004,'1235789',2.5,0),(37,'fish',2004,'1235789',2.5,0),(38,'fish',2004,'1235789',2.5,0),(39,'test',1234,'1234567',1.5,0),(40,'test',1234,'1234567',1.5,0),(41,'test',1234,'1233567',1.5,0),(42,'tfd',1221,'1233567',1.5,0),(43,'tfd',1221,'1233567',1.5,0),(44,'tfd',1221,'1234569',1.5,0),(45,'saji',2015,'7654321',5.5,0),(46,'Saji',2015,'7654322',5.5,0),(47,'Saji',2015,'7654325',5.5,0),(48,'dss',2014,'7777777',5.9,0),(49,'test',2012,'1111111',7.5,0);
/*!40000 ALTER TABLE `films` ENABLE KEYS */;
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

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
-- Dumping events for database 'db_movie'
--

--
-- Dumping routines for database 'db_movie'
--
/*!50003 DROP PROCEDURE IF EXISTS `addActor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `addActor`(
	IN fid VARCHAR(7),
    IN aid VARCHAR(7),
    IN fname VARCHAR(100),
    IN lname VARCHAR(100),
    OUT message SMALLINT(2))
BEGIN
	DECLARE actorid VARCHAR(7);
    DECLARE filmid VARCHAR(7);
	DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
	BEGIN
		SET message = 0;
		ROLLBACK;
	END;
    
    START TRANSACTION;
    IF (SELECT COUNT(*) FROM films WHERE imdb_id = fid) > 0 THEN
		BEGIN
			IF (SELECT COUNT(*) FROM actors WHERE imdb_id = aid) > 0 THEN
				BEGIN
					SELECT actor_id INTO actorid FROM actors WHERE imdb_id = aid;
					SELECT film_id INTO filmid FROM films WHERE imdb_id = fid;
					IF (SELECT COUNT(*) FROM lookup_film_actos WHERE film_id = filmid AND actor_id = actorid) > 0 THEN
						BEGIN
							SET message = 3;
                            ROLLBACK;
						END;
					ELSE
						BEGIN
							INSERT INTO lookup_film_actors SET
								film_id = filmid,
								actor_id = actorid;
							SET message = 4;
							COMMIT;
                        END;
					END IF;
				END;
			ELSE
				BEGIN
					INSERT INTO actors SET
						actor_firstNames = fname, 
						actor_lastName = lname,
						imdb_id = aid;
					SELECT actor_id INTO actorid FROM actors WHERE imdb_id = aid;
					SELECT film_id INTO filmid FROM films WHERE imdb_id = fid;
					INSERT INTO lookup_film_actors SET
						film_id = filmid,
                        actor_id = actorid;
                    SET message = 5;
					COMMIT;
				END;
			END IF;
		END;
	ELSE
		BEGIN
			SET message = 6;
			ROLLBACK;
        END;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `addDirector` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `addDirector`(
	IN fid VARCHAR(7),
    IN did VARCHAR(7),
    IN fname VARCHAR(100),
    IN lname VARCHAR(100),
    OUT message SMALLINT(2))
BEGIN
	DECLARE directorid VARCHAR(7);
    DECLARE filmid VARCHAR(7);
	DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
	BEGIN
		SET message = 0;
		ROLLBACK;
	END;
    
    START TRANSACTION;
    IF (SELECT COUNT(*) FROM films WHERE imdb_id = fid) > 0 THEN
		BEGIN
			IF (SELECT COUNT(*) FROM directors WHERE imdb_id = did) > 0 THEN
				BEGIN
					SELECT director_id INTO directorid FROM directors WHERE imdb_id = did;
					SELECT film_id INTO filmid FROM films WHERE imdb_id = fid;
					IF (SELECT COUNT(*) FROM lookup_film_directors WHERE film_id = filmid AND director_id = directorid) > 0 THEN
						BEGIN
							SET message = 7;
                            ROLLBACK;
						END;
					ELSE
						BEGIN
							INSERT INTO lookup_film_directors SET
								film_id = filmid,
								director_id = directorid;
							SET message = 8;
							COMMIT;
                        END;
					END IF;
				END;
			ELSE
				BEGIN
					INSERT INTO directors SET
						director_firstNames = fname, 
						director_lastName = lname,
						imdb_id = did;
					SELECT director_id INTO directorid FROM directors WHERE imdb_id = did;
					SELECT film_id INTO filmid FROM films WHERE imdb_id = fid;
					INSERT INTO lookup_film_directors SET
						film_id = filmid,
                        director_id = directorid;
                    SET message = 9;
					COMMIT;
				END;
			END IF;
		END;
	ELSE
		BEGIN
			SET message = 6;
			ROLLBACK;
        END;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `addFilm` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `addFilm`(
	IN fid VARCHAR(7),
    IN fname VARCHAR(100),
    IN fyear SMALLINT(4),
    IN frating FLOAT(3,1),
    OUT message SMALLINT(2))
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION, SQLWARNING
	BEGIN
		SET message = 0;
		ROLLBACK;
	END;

	START TRANSACTION;
	IF (SELECT COUNT(*) FROM films WHERE imdb_id = fid) > 0 THEN
		BEGIN
			SET message = 1;
			ROLLBACK;
		END;
    ELSE
		BEGIN
			INSERT INTO films SET 
				film_name = fname, 
				film_year = fyear,
				imdb_id = fid,
				imdb_rating = frating;
			SET message = 2;
			COMMIT;
		END;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getFilmDetails` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getFilmDetails`()
BEGIN
	SELECT fs.imdb_id AS 'Film ID'
		, fs.film_name AS 'Film Name'
		, fs.imdb_rating AS 'IMDB Rating'
		, d.imdb_id AS 'Director ID'
		, CONCAT(
			d.director_firstNames
			, ' '
            , d.director_lastName
		) AS 'Director'
		, a.imdb_id AS 'Actor ID'
		, CONCAT(
			a.actor_firstNames
            , ' '  
            , a.actor_lastName
		) AS 'Actor'
		, fs.film_year AS 'Year'
	FROM Films fs
  #----------------------------------- ACTORS
	JOIN lookup_film_actors lfa 
		ON lfa.film_id = fs.film_id
	JOIN actors a 
		ON a.actor_id = lfa.actor_id
  #----------------------------------- DIRECTORS
	JOIN lookup_film_directors lfd
		ON lfd.film_id = fs.film_id
	JOIN directors d
		ON d.director_id = lfd.director_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-03-21 15:34:33

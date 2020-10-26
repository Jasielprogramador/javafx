-- MySQL dump 10.13  Distrib 5.7.31, for Linux (x86_64)
--
-- Host: localhost    Database: liburuak
-- ------------------------------------------------------
-- Server version	5.7.31-0ubuntu0.18.04.1

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
-- Table structure for table `liburua`
--

DROP TABLE IF EXISTS `liburua`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `liburua` (
  `isbn` varchar(45) NOT NULL,
  `title` varchar(45) NOT NULL,
  `orriKop` int(11) DEFAULT NULL,
  `argitaletxea` varchar(45) DEFAULT NULL,
  `irudia` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `liburua`
--

LOCK TABLES `liburua` WRITE;
/*!40000 ALTER TABLE `liburua` DISABLE KEYS */;
INSERT INTO `liburua` VALUES ('1491910399','R for Data Science',522,'O\'Reilly Media','/home/asiertxu/Descargas/1491910399.jpg'),('1491946008','Fluent Python',792,'O\'Reilly Media','/home/asiertxu/Descargas/1491946008.jpg'),('1491978236','Natural Language Processing with PyTorch',256,'O\'Reilly Media','/home/asiertxu/Descargas/1491978236.jpg'),('9781491906187','Data Algorithms',778,'O\'Reilly Media','/home/asiertxu/Descargas/9781491906187.jpg'),('9781491920497','Blockchain: Blueprint for a New Economy',152,'O\'Reilly Media','/home/asiertxu/Descargas/9781491920497.jpg');
/*!40000 ALTER TABLE `liburua` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-26 10:13:44

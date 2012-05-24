-- MySQL dump 10.13  Distrib 5.1.34, for apple-darwin9.5.0 (i386)
--
-- Host: localhost    Database: zaetoon
-- ------------------------------------------------------
-- Server version	5.5.13

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
-- Current Database: `zaetoon`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `zaetoon` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `zaetoon`;

--
-- Table structure for table `REVINFO`
--

DROP TABLE IF EXISTS `REVINFO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REVINFO` (
  `REV` int(11) NOT NULL AUTO_INCREMENT,
  `REVTSTMP` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`REV`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REVINFO`
--

LOCK TABLES `REVINFO` WRITE;
/*!40000 ALTER TABLE `REVINFO` DISABLE KEYS */;
INSERT INTO `REVINFO` VALUES (1,1309504997368);
/*!40000 ALTER TABLE `REVINFO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES ('admin','ROLE_ADMIN'),('admin','ROLE_USER');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authorities_AUD`
--

DROP TABLE IF EXISTS `authorities_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authorities_AUD` (
  `username` varchar(50) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `authority` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`username`,`REV`),
  KEY `FK194AC872DF74E053` (`REV`),
  CONSTRAINT `FK194AC872DF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities_AUD`
--

LOCK TABLES `authorities_AUD` WRITE;
/*!40000 ALTER TABLE `authorities_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `authorities_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `program`
--

DROP TABLE IF EXISTS `program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `program` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `program_structure_id` int(11) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `program_structure_id` (`program_structure_id`),
  CONSTRAINT `program_ibfk_1` FOREIGN KEY (`program_structure_id`) REFERENCES `Program_Structure` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `program`
--

LOCK TABLES `program` WRITE;
/*!40000 ALTER TABLE `program` DISABLE KEYS */;
/*!40000 ALTER TABLE `program` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `program_AUD`
--

DROP TABLE IF EXISTS `program_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `program_AUD` (
  `id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `program_structure_id` int(11) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK3C406A55DF74E053` (`REV`),
  CONSTRAINT `FK3C406A55DF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `program_AUD`
--

LOCK TABLES `program_AUD` WRITE;
/*!40000 ALTER TABLE `program_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `program_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `program_structure`
--

DROP TABLE IF EXISTS `program_structure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `program_structure` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `days` int(11) NOT NULL,
  `description` varchar(200) NOT NULL,
  `prerequisites` int(11) DEFAULT NULL,
  `category` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `program_structure`
--

LOCK TABLES `program_structure` WRITE;
/*!40000 ALTER TABLE `program_structure` DISABLE KEYS */;
/*!40000 ALTER TABLE `program_structure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `program_structure_AUD`
--

DROP TABLE IF EXISTS `program_structure_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `program_structure_AUD` (
  `id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `category` varchar(200) DEFAULT NULL,
  `days` int(11) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `prerequisites` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK56F2A349DF74E053` (`REV`),
  CONSTRAINT `FK56F2A349DF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `program_structure_AUD`
--

LOCK TABLES `program_structure_AUD` WRITE;
/*!40000 ALTER TABLE `program_structure_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `program_structure_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_enrollment`
--

DROP TABLE IF EXISTS `user_enrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_enrollment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `program_id` int(11) DEFAULT NULL,
  `result` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `program_id` (`program_id`),
  KEY `user_enrollment_fk2` (`user_id`),
  KEY `user_enrollment_ibfk_2` (`user_id`),
  CONSTRAINT `user_enrollment_ibfk_1` FOREIGN KEY (`program_id`) REFERENCES `Program` (`id`) ON DELETE CASCADE,
  CONSTRAINT `user_enrollment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user_profile` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_enrollment`
--

LOCK TABLES `user_enrollment` WRITE;
/*!40000 ALTER TABLE `user_enrollment` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_enrollment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_enrollment_AUD`
--

DROP TABLE IF EXISTS `user_enrollment_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_enrollment_AUD` (
  `id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `program_id` int(11) DEFAULT NULL,
  `result` varchar(50) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FKF7B381C9DF74E053` (`REV`),
  CONSTRAINT `FKF7B381C9DF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_enrollment_AUD`
--

LOCK TABLES `user_enrollment_AUD` WRITE;
/*!40000 ALTER TABLE `user_enrollment_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_enrollment_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_graduation`
--

DROP TABLE IF EXISTS `user_graduation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_graduation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `program_structure_id` int(11) NOT NULL,
  `graduation_date` date NOT NULL,
  `title` varchar(150) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_graduation_ibfk_1` (`program_structure_id`),
  KEY `user_graduation_ibfk_2` (`user_id`),
  CONSTRAINT `user_graduation_ibfk_1` FOREIGN KEY (`program_structure_id`) REFERENCES `Program_Structure` (`id`) ON DELETE CASCADE,
  CONSTRAINT `user_graduation_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user_profile` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_graduation`
--

LOCK TABLES `user_graduation` WRITE;
/*!40000 ALTER TABLE `user_graduation` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_graduation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_graduation_AUD`
--

DROP TABLE IF EXISTS `user_graduation_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_graduation_AUD` (
  `id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `graduation_date` date DEFAULT NULL,
  `program_structure_id` int(11) DEFAULT NULL,
  `title` varchar(150) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FK70A12D33DF74E053` (`REV`),
  CONSTRAINT `FK70A12D33DF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_graduation_AUD`
--

LOCK TABLES `user_graduation_AUD` WRITE;
/*!40000 ALTER TABLE `user_graduation_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_graduation_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profile`
--

DROP TABLE IF EXISTS `user_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(80) DEFAULT NULL,
  `country` varchar(80) DEFAULT NULL,
  `title` varchar(150) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile`
--

LOCK TABLES `user_profile` WRITE;
/*!40000 ALTER TABLE `user_profile` DISABLE KEYS */;
INSERT INTO `user_profile` VALUES (9,'santa clara','usa','user','intesar','mohammed');
/*!40000 ALTER TABLE `user_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_profile_AUD`
--

DROP TABLE IF EXISTS `user_profile_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_profile_AUD` (
  `id` int(11) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `city` varchar(80) DEFAULT NULL,
  `country` varchar(80) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `title` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`,`REV`),
  KEY `FKAD9CAC86DF74E053` (`REV`),
  CONSTRAINT `FKAD9CAC86DF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_profile_AUD`
--

LOCK TABLES `user_profile_AUD` WRITE;
/*!40000 ALTER TABLE `user_profile_AUD` DISABLE KEYS */;
INSERT INTO `user_profile_AUD` VALUES (9,1,0,'santa clara','usa','intesar','mohammed','user');
/*!40000 ALTER TABLE `user_profile_AUD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin','admin',1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_AUD`
--

DROP TABLE IF EXISTS `users_AUD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_AUD` (
  `username` varchar(50) NOT NULL,
  `REV` int(11) NOT NULL,
  `REVTYPE` tinyint(4) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`username`,`REV`),
  KEY `FK154C77D9DF74E053` (`REV`),
  CONSTRAINT `FK154C77D9DF74E053` FOREIGN KEY (`REV`) REFERENCES `REVINFO` (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_AUD`
--

LOCK TABLES `users_AUD` WRITE;
/*!40000 ALTER TABLE `users_AUD` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_AUD` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-07-11  1:56:20

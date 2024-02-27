-- MySQL dump 10.13  Distrib 8.0.19, for osx10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: world
-- ------------------------------------------------------
-- Server version	8.0.19-debug

SET @old_autocommit=@@autocommit;

--
-- Current Database: `world`
--

/*!40000 DROP DATABASE IF EXISTS `world`*/;

CREATE DATABASE `world` DEFAULT CHARACTER SET utf8mb4;

USE `world`;

/*!50503 set default_storage_engine = InnoDB */;
/*!50503 select CONCAT('storage engine: ', @@default_storage_engine) as INFO */;
--
-- Table structure for table `city`
--
SELECT 'CREATING DATABASE STRUCTURE' as 'INFO';
DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
                        `ID` int NOT NULL AUTO_INCREMENT,
                        `Name` char(35) NOT NULL DEFAULT '',
                        `CountryCode` char(3) NOT NULL DEFAULT '',
                        `District` char(20) NOT NULL DEFAULT '',
                        `Population` int NOT NULL DEFAULT '0',
                        PRIMARY KEY (`ID`),
                        KEY `CountryCode` (`CountryCode`),
                        CONSTRAINT `city_ibfk_1` FOREIGN KEY (`CountryCode`) REFERENCES `country` (`Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
                           `Code` char(3) NOT NULL DEFAULT '',
                           `Name` char(52) NOT NULL DEFAULT '',
                           `Continent` enum('Asia','Europe','North America','Africa','Oceania','Antarctica','South America') NOT NULL DEFAULT 'Asia',
                           `Region` char(26) NOT NULL DEFAULT '',
                           `SurfaceArea` decimal(10,2) NOT NULL DEFAULT '0.00',
                           `IndepYear` smallint DEFAULT NULL,
                           `Population` int NOT NULL DEFAULT '0',
                           `LifeExpectancy` decimal(3,1) DEFAULT NULL,
                           `GNP` decimal(10,2) DEFAULT NULL,
                           `GNPOld` decimal(10,2) DEFAULT NULL,
                           `LocalName` char(45) NOT NULL DEFAULT '',
                           `GovernmentForm` char(45) NOT NULL DEFAULT '',
                           `HeadOfState` char(60) DEFAULT NULL,
                           `Capital` int DEFAULT NULL,
                           `Code2` char(2) NOT NULL DEFAULT '',
                           PRIMARY KEY (`Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `countrylanguage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `countrylanguage` (
                                   `CountryCode` char(3) NOT NULL DEFAULT '',
                                   `Language` char(30) NOT NULL DEFAULT '',
                                   `IsOfficial` enum('T','F') NOT NULL DEFAULT 'F',
                                   `Percentage` decimal(4,1) NOT NULL DEFAULT '0.0',
                                   PRIMARY KEY (`CountryCode`,`Language`),
                                   KEY `CountryCode` (`CountryCode`),
                                   CONSTRAINT `countryLanguage_ibfk_1` FOREIGN KEY (`CountryCode`) REFERENCES `country` (`Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

flush /*!50503 binary */ logs;

SELECT 'LOADING city' as 'INFO';
source load_city.dump ;
SELECT 'LOADING country' as 'INFO';
source load_country.dump ;
SELECT 'LOADING countrylanguage' as 'INFO';
source load_countrylanguage.dump ;
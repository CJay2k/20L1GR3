-- MariaDB dump 10.17  Distrib 10.4.12-MariaDB, for Linux (x86_64)
--
-- Host: 54.38.143.86    Database: dziennik_elektroniczny
-- ------------------------------------------------------
-- Server version	10.1.44-MariaDB-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `autoryzacja`
--

DROP TABLE IF EXISTS `autoryzacja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `autoryzacja` (
  `pesel` bigint(20) NOT NULL,
  `login` varchar(30) NOT NULL,
  `haslo` varchar(30) NOT NULL,
  `kto` varchar(1) NOT NULL,
  PRIMARY KEY (`pesel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autoryzacja`
--

LOCK TABLES `autoryzacja` WRITE;
/*!40000 ALTER TABLE `autoryzacja` DISABLE KEYS */;
/*!40000 ALTER TABLE `autoryzacja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `klasa`
--

DROP TABLE IF EXISTS `klasa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `klasa` (
  `nazwa_klasy` varchar(2) NOT NULL,
  `rok_szkolny` year(4) DEFAULT NULL,
  `wychowawca` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`nazwa_klasy`),
  KEY `FKmxovwiw2qfv2shhm5dbbgaphp` (`wychowawca`),
  CONSTRAINT `FKmxovwiw2qfv2shhm5dbbgaphp` FOREIGN KEY (`wychowawca`) REFERENCES `nauczyciel` (`pesel`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `klasa`
--

LOCK TABLES `klasa` WRITE;
/*!40000 ALTER TABLE `klasa` DISABLE KEYS */;
INSERT INTO `klasa` VALUES ('1a',2020,22222222220),('1b',2020,22222222221);
/*!40000 ALTER TABLE `klasa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nauczyciel`
--

DROP TABLE IF EXISTS `nauczyciel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nauczyciel` (
  `pesel` bigint(11) NOT NULL,
  `imie` varchar(30) DEFAULT NULL,
  `nazwisko` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`pesel`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nauczyciel`
--

LOCK TABLES `nauczyciel` WRITE;
/*!40000 ALTER TABLE `nauczyciel` DISABLE KEYS */;
INSERT INTO `nauczyciel` VALUES (22222222220,'Benton','Wildsmit'),(22222222221,'Darnall','Musson'),(22222222222,'Roderic','Spensley'),(22222222223,'Der','Houndsom'),(22222222224,'Fairfax','Mewton'),(22222222225,'Bengt','Kubis'),(22222222226,'Serge','Steagall'),(22222222227,'Yanaton','Britnell'),(22222222228,'Kayne','Halpen'),(22222222229,'Zebadiah','Drake');
/*!40000 ALTER TABLE `nauczyciel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `obecnosc`
--

DROP TABLE IF EXISTS `obecnosc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `obecnosc` (
  `pesel` bigint(11) NOT NULL,
  `data` date DEFAULT NULL,
  `wartosc` tinyint(1) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `przedmiot` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`pesel`),
  KEY `FKtop2awa3er6m12xd5t13jdosn` (`przedmiot`),
  CONSTRAINT `FKolviqa2btnk0e71agi851swb7` FOREIGN KEY (`pesel`) REFERENCES `uczen` (`pesel`),
  CONSTRAINT `FKtop2awa3er6m12xd5t13jdosn` FOREIGN KEY (`przedmiot`) REFERENCES `przedmiot` (`nazwa_przedmiotu`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obecnosc`
--

LOCK TABLES `obecnosc` WRITE;
/*!40000 ALTER TABLE `obecnosc` DISABLE KEYS */;
/*!40000 ALTER TABLE `obecnosc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ocena`
--

DROP TABLE IF EXISTS `ocena`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ocena` (
  `pesel` bigint(11) NOT NULL,
  `przedmiot` varchar(30) DEFAULT NULL,
  `stopien` varchar(2) DEFAULT NULL,
  `data` date DEFAULT NULL,
  `opis` varchar(50) DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`pesel`),
  KEY `FKg593sxr86xar3vy2c79h33duy` (`przedmiot`),
  CONSTRAINT `FK4u4plwm0ilmqvcakhrqcy4dh` FOREIGN KEY (`pesel`) REFERENCES `uczen` (`pesel`),
  CONSTRAINT `FKg593sxr86xar3vy2c79h33duy` FOREIGN KEY (`przedmiot`) REFERENCES `przedmiot` (`nazwa_przedmiotu`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ocena`
--

LOCK TABLES `ocena` WRITE;
/*!40000 ALTER TABLE `ocena` DISABLE KEYS */;
/*!40000 ALTER TABLE `ocena` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `przedmiot`
--

DROP TABLE IF EXISTS `przedmiot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `przedmiot` (
  `nazwa_przedmiotu` varchar(30) NOT NULL,
  PRIMARY KEY (`nazwa_przedmiotu`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `przedmiot`
--

LOCK TABLES `przedmiot` WRITE;
/*!40000 ALTER TABLE `przedmiot` DISABLE KEYS */;
INSERT INTO `przedmiot` VALUES ('algebra_liniowa'),('geometria_analityczna'),('informatyka_ogolna'),('jezyk_angielski'),('jezyk_polski'),('programowanie_java'),('programowanie_zespolowe');
/*!40000 ALTER TABLE `przedmiot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `przedmiot_pomocnicza`
--

DROP TABLE IF EXISTS `przedmiot_pomocnicza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `przedmiot_pomocnicza` (
  `id` bigint(11) NOT NULL,
  `nazwa_przedmiotu` varchar(30) NOT NULL,
  `prowadzacy` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `przedmiot_pomocnicza`
--

LOCK TABLES `przedmiot_pomocnicza` WRITE;
/*!40000 ALTER TABLE `przedmiot_pomocnicza` DISABLE KEYS */;
INSERT INTO `przedmiot_pomocnicza` VALUES (1,'algebra_liniowa','22222222221'),(2,'programowanie_java','22222222222'),(3,'programowanie_java','22222222223'),(4,'programowanie_java','22222222224'),(5,'informatyka_ogolna','22222222225'),(6,'informatyka_ogolna','22222222226'),(7,'programowanie_zespolowe','22222222226'),(8,'algebra_liniowa','22222222227'),(9,'geometria_analityczna','22222222228'),(10,'algebra_liniowa','22222222228'),(11,'jezyk_polski','22222222229'),(12,'jezyk_angielski','22222222220');
/*!40000 ALTER TABLE `przedmiot_pomocnicza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rodzaj_oceny`
--

DROP TABLE IF EXISTS `rodzaj_oceny`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rodzaj_oceny` (
  `rodzajOceny` varchar(4) NOT NULL,
  PRIMARY KEY (`rodzajOceny`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rodzaj_oceny`
--

LOCK TABLES `rodzaj_oceny` WRITE;
/*!40000 ALTER TABLE `rodzaj_oceny` DISABLE KEYS */;
/*!40000 ALTER TABLE `rodzaj_oceny` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rodzic`
--

DROP TABLE IF EXISTS `rodzic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rodzic` (
  `pesel` bigint(11) NOT NULL,
  `dziecko` bigint(11) DEFAULT NULL,
  `imie_ojca` varchar(30) DEFAULT NULL,
  `nazwisko_ojca` varchar(30) DEFAULT NULL,
  `imie_matki` varchar(30) DEFAULT NULL,
  `nazwisko_matki` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`pesel`),
  KEY `FKhdrr4fj5055xgeu53lqo1kacs` (`dziecko`),
  CONSTRAINT `FKhdrr4fj5055xgeu53lqo1kacs` FOREIGN KEY (`dziecko`) REFERENCES `uczen` (`pesel`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rodzic`
--

LOCK TABLES `rodzic` WRITE;
/*!40000 ALTER TABLE `rodzic` DISABLE KEYS */;
INSERT INTO `rodzic` VALUES (11111111110,32222222220,'Orin','Titmuss','Ines','Disman'),(11111111111,32222222221,'Chancey','Feakins','Stacee','Strickland'),(11111111112,32222222222,'Harp','Daugherty','Hedy','Pennicard'),(11111111113,32222222223,'Sigfried','Tomkies','Regine','Cirlos'),(11111111114,32222222224,'Pablo','Maletratt','Sharity','Ashfold'),(11111111115,32222222225,'Northrup','Morin','Robbin','Bissill'),(11111111116,32222222226,'Frazier','Heller','Kristina','Raine'),(11111111117,32222222227,'Matthiew','Scargle','Moyna','Frankham'),(11111111118,32222222228,'Rudie','Birrel','Libbey','Gammett'),(11111111119,32222222229,'Aldous','Nowakowski','Thomasin','Moule');
/*!40000 ALTER TABLE `rodzic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sklad_klasy`
--

DROP TABLE IF EXISTS `sklad_klasy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sklad_klasy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `klasa` varchar(2) NOT NULL,
  `uczen` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjqlj5m6gvajecg3094h4icisd` (`uczen`),
  CONSTRAINT `FKjqlj5m6gvajecg3094h4icisd` FOREIGN KEY (`uczen`) REFERENCES `uczen` (`pesel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sklad_klasy`
--

LOCK TABLES `sklad_klasy` WRITE;
/*!40000 ALTER TABLE `sklad_klasy` DISABLE KEYS */;
/*!40000 ALTER TABLE `sklad_klasy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uczen`
--

DROP TABLE IF EXISTS `uczen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uczen` (
  `pesel` bigint(11) NOT NULL,
  `nazwa_klasy` varchar(2) DEFAULT NULL,
  `imie` varchar(30) DEFAULT NULL,
  `nazwisko` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`pesel`),
  KEY `FKhhbcs24yp2d32ecuphf8sit2b` (`nazwa_klasy`),
  CONSTRAINT `FKhhbcs24yp2d32ecuphf8sit2b` FOREIGN KEY (`nazwa_klasy`) REFERENCES `klasa` (`nazwa_klasy`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uczen`
--

LOCK TABLES `uczen` WRITE;
/*!40000 ALTER TABLE `uczen` DISABLE KEYS */;
INSERT INTO `uczen` VALUES (32222222220,'1b','Zenon','Szmit'),(32222222221,'1a','Gniewomir','Iryd'),(32222222222,'1a','Aleksander','Jewula'),(32222222223,'1a','Mateusz','Jedziniak'),(32222222224,'1a','Jakub','Galuszka'),(32222222225,'1a','Pawel','Kolano'),(32222222226,'1b','Arkadiusz','Seagall'),(32222222227,'1b','Bartosz','Belz'),(32222222228,'1b','Kain','Harpel'),(32222222229,'1b','Zbigniew','Smok');
/*!40000 ALTER TABLE `uczen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zajecia`
--

DROP TABLE IF EXISTS `zajecia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zajecia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `klasa` varchar(2) NOT NULL,
  `prowadzacy` bigint(20) NOT NULL,
  `przedmiot` varchar(30) NOT NULL,
  `godzina` time NOT NULL,
  `dzien` varchar(3) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsynq5s17yph36b2bm9bym6yh6` (`prowadzacy`),
  CONSTRAINT `FKsynq5s17yph36b2bm9bym6yh6` FOREIGN KEY (`prowadzacy`) REFERENCES `nauczyciel` (`pesel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zajecia`
--

LOCK TABLES `zajecia` WRITE;
/*!40000 ALTER TABLE `zajecia` DISABLE KEYS */;
/*!40000 ALTER TABLE `zajecia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'dziennik_elektroniczny'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-19 22:15:22

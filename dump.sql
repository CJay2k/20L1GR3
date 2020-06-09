-- MariaDB dump 10.17  Distrib 10.4.13-MariaDB, for Linux (x86_64)
--
-- Host: 54.38.143.86    Database: edziennik
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
-- Table structure for table `Klasy`
--

DROP TABLE IF EXISTS `Klasy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Klasy` (
  `klasa_id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa_klasy` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  `rok_szkolny` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`klasa_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Klasy`
--

LOCK TABLES `Klasy` WRITE;
/*!40000 ALTER TABLE `Klasy` DISABLE KEYS */;
INSERT INTO `Klasy` VALUES (1,'1a','2020/2021'),(2,'2a','2020/2021'),(3,'3b','2020/2021'),(4,'2b','2020/2021'),(5,'1b','2020/2021'),(6,'3a','2020/2021');
/*!40000 ALTER TABLE `Klasy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Klasy_Przedmioty`
--

DROP TABLE IF EXISTS `Klasy_Przedmioty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Klasy_Przedmioty` (
  `klasy_przedmioty_id` int(11) NOT NULL AUTO_INCREMENT,
  `klasa_id` int(11) NOT NULL,
  `przedmiot_id` int(11) NOT NULL,
  PRIMARY KEY (`klasy_przedmioty_id`),
  KEY `FKcwp5v4q84vdgk7ipug5cdq1h4` (`klasa_id`),
  KEY `FKnjabprqp8epf047775tqdceke` (`przedmiot_id`),
  CONSTRAINT `FKcwp5v4q84vdgk7ipug5cdq1h4` FOREIGN KEY (`klasa_id`) REFERENCES `Klasy` (`klasa_id`),
  CONSTRAINT `FKnjabprqp8epf047775tqdceke` FOREIGN KEY (`przedmiot_id`) REFERENCES `Przedmioty` (`przedmiot_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Klasy_Przedmioty`
--

LOCK TABLES `Klasy_Przedmioty` WRITE;
/*!40000 ALTER TABLE `Klasy_Przedmioty` DISABLE KEYS */;
INSERT INTO `Klasy_Przedmioty` VALUES (1,1,1),(2,6,2),(3,3,3),(4,4,4),(5,3,5),(6,1,2),(7,2,5),(8,5,3),(9,5,1),(11,5,5);
/*!40000 ALTER TABLE `Klasy_Przedmioty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Konta`
--

DROP TABLE IF EXISTS `Konta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Konta` (
  `konto_id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  `haslo` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  `rola_id` int(11) NOT NULL,
  PRIMARY KEY (`konto_id`),
  UNIQUE KEY `Konta_UN` (`login`),
  KEY `FKdjhxdf2jent29f3h7jihv8ego` (`rola_id`),
  CONSTRAINT `FKdjhxdf2jent29f3h7jihv8ego` FOREIGN KEY (`rola_id`) REFERENCES `Role` (`rola_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Konta`
--

LOCK TABLES `Konta` WRITE;
/*!40000 ALTER TABLE `Konta` DISABLE KEYS */;
INSERT INTO `Konta` VALUES (1,'d','d',1),(2,'n','n',2),(3,'r','r',3),(4,'u','u',4),(5,'u2','u2',4),(6,'u3','u3',4),(7,'r2','r2',3),(8,'n2','n2',2),(9,'n4','n4',2),(10,'r6','r6',3),(11,'u4','u4',4),(12,'u5','u5',4),(13,'r3','r3',3),(14,'u6','u6',4),(16,'r4','r4',3),(18,'r5','r5',3);
/*!40000 ALTER TABLE `Konta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Nauczyciele`
--

DROP TABLE IF EXISTS `Nauczyciele`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Nauczyciele` (
  `nauczyciel_id` int(11) NOT NULL AUTO_INCREMENT,
  `imie` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  `nazwisko` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  `konto_id` int(11) NOT NULL,
  PRIMARY KEY (`nauczyciel_id`),
  UNIQUE KEY `Nauczyciele_UN` (`konto_id`),
  CONSTRAINT `FKgh4o3c1yd5o1579scg8c8ec8o` FOREIGN KEY (`konto_id`) REFERENCES `Konta` (`konto_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Nauczyciele`
--

LOCK TABLES `Nauczyciele` WRITE;
/*!40000 ALTER TABLE `Nauczyciele` DISABLE KEYS */;
INSERT INTO `Nauczyciele` VALUES (1,'Zbigniew','Stonoga',2),(2,'Mariusz','Pudzianowski',8),(3,'Maria','Konopnicka',9),(4,'Adrian','Hrycaj',18);
/*!40000 ALTER TABLE `Nauczyciele` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Nieobecnosci`
--

DROP TABLE IF EXISTS `Nieobecnosci`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Nieobecnosci` (
  `nieobecnosc_id` int(11) NOT NULL AUTO_INCREMENT,
  `uczen_id` int(11) NOT NULL,
  `przedmiot_id` int(11) NOT NULL,
  `wartosc` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  `data` date NOT NULL,
  `tresc_usprawiedliwienia` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  PRIMARY KEY (`nieobecnosc_id`),
  KEY `FKmhlhxviit1h32ufxsyxxn8so8` (`uczen_id`),
  KEY `FKmnlx8x7ct2ubbvmuiadmkt20d` (`przedmiot_id`),
  CONSTRAINT `FKmhlhxviit1h32ufxsyxxn8so8` FOREIGN KEY (`uczen_id`) REFERENCES `Uczniowie` (`uczen_id`),
  CONSTRAINT `FKmnlx8x7ct2ubbvmuiadmkt20d` FOREIGN KEY (`przedmiot_id`) REFERENCES `Przedmioty` (`przedmiot_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Nieobecnosci`
--

LOCK TABLES `Nieobecnosci` WRITE;
/*!40000 ALTER TABLE `Nieobecnosci` DISABLE KEYS */;
INSERT INTO `Nieobecnosci` VALUES (1,1,1,'nieusprawiedliwiona','2020-08-05','lo lll 3'),(2,2,2,'nieobecny','2020-08-08','lo lll 3'),(3,1,1,'usprawiedliwiona','2020-05-15','u1 test 2'),(4,1,1,'nieobecny','2020-05-19',''),(5,1,1,'nieobecny','2020-05-20','');
/*!40000 ALTER TABLE `Nieobecnosci` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Oceny`
--

DROP TABLE IF EXISTS `Oceny`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Oceny` (
  `ocena_id` int(11) NOT NULL AUTO_INCREMENT,
  `uczen_id` int(11) NOT NULL,
  `przedmiot_id` int(11) NOT NULL,
  `wartosc` varchar(2) COLLATE utf8_polish_ci NOT NULL,
  `opis` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  `data` date NOT NULL,
  PRIMARY KEY (`ocena_id`),
  KEY `FKjvhqqbpojlejaqo6rqcv8fvyv` (`uczen_id`),
  KEY `FKt2lf19edct88su2x9dalc75j9` (`przedmiot_id`),
  CONSTRAINT `FKjvhqqbpojlejaqo6rqcv8fvyv` FOREIGN KEY (`uczen_id`) REFERENCES `Uczniowie` (`uczen_id`),
  CONSTRAINT `FKt2lf19edct88su2x9dalc75j9` FOREIGN KEY (`przedmiot_id`) REFERENCES `Przedmioty` (`przedmiot_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Oceny`
--

LOCK TABLES `Oceny` WRITE;
/*!40000 ALTER TABLE `Oceny` DISABLE KEYS */;
INSERT INTO `Oceny` VALUES (1,1,1,'5','odpowiedz ustna','2020-11-05'),(2,2,2,'4','odpowiedz ustna','2020-11-07'),(3,3,2,'4','odpowiedz ustna','2020-11-07'),(4,2,2,'4','za kartkowke','2020-05-15'),(5,1,1,'5','za kartkowke','2020-05-19'),(6,3,5,'3','za kartkowke','2020-10-05'),(7,3,5,'3','za sprawdzian','2020-05-12'),(8,1,2,'4','za kartkowke','2020-05-06'),(9,4,3,'5','odpowiedz ustna','2020-03-15'),(10,4,3,'2','za sprawdzian','2020-06-06'),(11,5,5,'3','odpowiedz ustna','2020-07-06'),(12,6,1,'4','za kartkowke','2020-08-06'),(13,2,2,'5','odpowiedz ustna','2020-09-06'),(14,1,1,'6','za sprawdzian','2020-10-06'),(15,4,5,'2','za sprawdzian','2020-11-06'),(16,3,5,'3','za kartkowke','2020-12-06'),(17,5,3,'4','odpowiedz ustna','2020-10-06'),(18,6,2,'5','za sprawdzian','2020-11-06'),(19,5,5,'6','za sprawdzian','2020-11-07'),(20,2,2,'2','za kartkowke','2020-12-09');
/*!40000 ALTER TABLE `Oceny` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Przedmioty`
--

DROP TABLE IF EXISTS `Przedmioty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Przedmioty` (
  `przedmiot_id` int(11) NOT NULL AUTO_INCREMENT,
  `nauczyciel_id` int(11) NOT NULL,
  `nazwa_przedmiotu` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`przedmiot_id`),
  KEY `FK47tmo8pbewd30emhbv92qv1gi` (`nauczyciel_id`),
  CONSTRAINT `FK47tmo8pbewd30emhbv92qv1gi` FOREIGN KEY (`nauczyciel_id`) REFERENCES `Nauczyciele` (`nauczyciel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Przedmioty`
--

LOCK TABLES `Przedmioty` WRITE;
/*!40000 ALTER TABLE `Przedmioty` DISABLE KEYS */;
INSERT INTO `Przedmioty` VALUES (1,1,'Prawo'),(2,2,'WF'),(3,3,'Fizyka'),(4,4,'Informatyka'),(5,3,'Chemia');
/*!40000 ALTER TABLE `Przedmioty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Rodzice`
--

DROP TABLE IF EXISTS `Rodzice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Rodzice` (
  `rodzic_id` int(11) NOT NULL AUTO_INCREMENT,
  `imie_ojca` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `nazwisko_ojca` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `imie_matki` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `nazwisko_matki` varchar(100) COLLATE utf8_polish_ci DEFAULT NULL,
  `konto_id` int(11) NOT NULL,
  PRIMARY KEY (`rodzic_id`),
  UNIQUE KEY `Rodzice_UN` (`konto_id`),
  CONSTRAINT `FK4lp4lkc6jsi314ycf0tyu369v` FOREIGN KEY (`konto_id`) REFERENCES `Konta` (`konto_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Rodzice`
--

LOCK TABLES `Rodzice` WRITE;
/*!40000 ALTER TABLE `Rodzice` DISABLE KEYS */;
INSERT INTO `Rodzice` VALUES (1,'Janusz','Kowalski','Maryla','Rodowicz',3),(2,'Karol','Krawczyk','Alina','Krawczyk',7),(3,'Marian','Kaczor','Aneta','Kaczor',13),(4,'Zbigniew','Nieboszczyk','Ola','Nieboszczyk',16),(6,'Marcin','Malina','Marta','Malina',18);
/*!40000 ALTER TABLE `Rodzice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Role`
--

DROP TABLE IF EXISTS `Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Role` (
  `rola_id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa_roli` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (`rola_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
INSERT INTO `Role` VALUES (1,'dyrektor'),(2,'nauczyciel'),(3,'rodzic'),(4,'uczen');
/*!40000 ALTER TABLE `Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Uczniowie`
--

DROP TABLE IF EXISTS `Uczniowie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Uczniowie` (
  `uczen_id` int(11) NOT NULL AUTO_INCREMENT,
  `imie` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  `nazwisko` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  `klasa_id` int(11) NOT NULL,
  `rodzic_id` int(11) NOT NULL,
  `konto_id` int(11) NOT NULL,
  PRIMARY KEY (`uczen_id`),
  UNIQUE KEY `Uczniowie_UN` (`konto_id`),
  KEY `FK6rurayuhkokphq8et0mdj3tsw` (`klasa_id`),
  KEY `FKdbw1x6uypyhr2nk43xk76n2qc` (`rodzic_id`),
  CONSTRAINT `FK3b237irsg1fpmm69yxljo4xv8` FOREIGN KEY (`konto_id`) REFERENCES `Konta` (`konto_id`),
  CONSTRAINT `FK6rurayuhkokphq8et0mdj3tsw` FOREIGN KEY (`klasa_id`) REFERENCES `Klasy` (`klasa_id`),
  CONSTRAINT `FKdbw1x6uypyhr2nk43xk76n2qc` FOREIGN KEY (`rodzic_id`) REFERENCES `Rodzice` (`rodzic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Uczniowie`
--

LOCK TABLES `Uczniowie` WRITE;
/*!40000 ALTER TABLE `Uczniowie` DISABLE KEYS */;
INSERT INTO `Uczniowie` VALUES (1,'Kamil','Kowalski',1,1,4),(2,'Witek','Krawczyk',2,2,5),(3,'Brian','Kowalski',2,1,6),(4,'Jakub','Kaczor',3,3,11),(5,'Radek','Nieboszczyk',3,4,12),(6,'Piotr','Malina',1,6,14);
/*!40000 ALTER TABLE `Uczniowie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Uwagi`
--

DROP TABLE IF EXISTS `Uwagi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Uwagi` (
  `obecnosc_id` int(11) NOT NULL AUTO_INCREMENT,
  `uczen_id` int(11) NOT NULL,
  `przedmiot_id` int(11) NOT NULL,
  `wartosc` varchar(100) COLLATE utf8_polish_ci NOT NULL,
  `data` date NOT NULL,
  PRIMARY KEY (`obecnosc_id`),
  KEY `FKb93i5psg8kpwjnw94eytmapo8` (`uczen_id`),
  KEY `FK1509p8yw6jfg28jgd9n5hh58l` (`przedmiot_id`),
  CONSTRAINT `FK1509p8yw6jfg28jgd9n5hh58l` FOREIGN KEY (`przedmiot_id`) REFERENCES `Przedmioty` (`przedmiot_id`),
  CONSTRAINT `FKb93i5psg8kpwjnw94eytmapo8` FOREIGN KEY (`uczen_id`) REFERENCES `Uczniowie` (`uczen_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Uwagi`
--

LOCK TABLES `Uwagi` WRITE;
/*!40000 ALTER TABLE `Uwagi` DISABLE KEYS */;
INSERT INTO `Uwagi` VALUES (1,1,1,'Glosno zachowywal sie na lekcji','2020-05-05'),(2,3,2,'Jadl na zajeciach','2020-05-10'),(3,3,2,'Obrazal rowiesników podczas zajec','2020-05-09'),(4,2,2,'Przeszkadzal w prowadzeniu zajec','2020-05-04'),(5,2,2,'Rzucal kreda po sali','2020-05-04'),(6,1,1,'Brak zeszytu ','2020-05-13'),(34,1,1,'nie uważał na lekcji','2020-06-08');
/*!40000 ALTER TABLE `Uwagi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'edziennik'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-09  2:30:56

CREATE DATABASE  IF NOT EXISTS `qtxtestarq` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci */;
USE `qtxtestarq`;
-- MySQL dump 10.13  Distrib 5.7.22, for Win64 (x86_64)
--
-- Host: localhost    Database: qtxtestarq
-- ------------------------------------------------------
-- Server version	5.7.22-log

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
-- Table structure for table `catvalorsimple`
--

DROP TABLE IF EXISTS `catvalorsimple`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catvalorsimple` (
  `cat_id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `cat_tipo_valor` varchar(30) COLLATE utf8_spanish2_ci NOT NULL,
  `cat_valor_alfa` varchar(45) COLLATE utf8_spanish2_ci NOT NULL,
  `cat_valor_corto_alfa` varchar(12) COLLATE utf8_spanish2_ci DEFAULT NULL,
  PRIMARY KEY (`cat_id`),
  UNIQUE KEY `idx_tipoValor` (`cat_tipo_valor`,`cat_valor_alfa`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catvalorsimple`
--

LOCK TABLES `catvalorsimple` WRITE;
/*!40000 ALTER TABLE `catvalorsimple` DISABLE KEYS */;
INSERT INTO `catvalorsimple` VALUES (1,'apellido','Abaroa',NULL),(2,'apellido','Almazo',NULL),(3,'apellido','Acosta',NULL),(4,'apellido','Barreto',NULL),(5,'apellido','Becerra',NULL),(6,'apellido','Bonilla',NULL),(7,'apellido','Bueno',NULL),(8,'apellido','Camargo',NULL),(9,'apellido','Caballero',NULL),(10,'apellido','Centeno',NULL),(11,'apellido','Cerezo',NULL),(12,'apellido','Cedillo',NULL),(13,'apellido','Costa',NULL),(14,'nombre','Alejo',NULL),(15,'nombre','Alejandro',NULL),(16,'nombre','Adalberto',NULL),(17,'nombre','Alan',NULL),(18,'nombre','Alejandra',NULL),(19,'nombre','Arturo',NULL),(20,'nombre','Aldo',NULL),(21,'nombre','Amelia',NULL),(22,'nombre','América',NULL),(23,'nombre','Bianca',NULL),(24,'apellido','Barrientos',NULL),(25,'apellido','Vera',NULL),(27,'apellido','Verástegui',NULL);
/*!40000 ALTER TABLE `catvalorsimple` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persona` (
  `per_id` bigint(19) unsigned NOT NULL AUTO_INCREMENT,
  `per_ap_paterno` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `per_ap_materno` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `per_nombres` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `per_fec_nac` datetime NOT NULL,
  PRIMARY KEY (`per_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (1,'Cruz','Rojas','Alejandro','1968-01-11 00:00:00'),(2,'Aguirre','Mora','Mariela Silvina','2001-02-02 00:00:00'),(3,'Bautista','Sánchez','Nora Isela','1977-03-01 00:00:00'),(4,'Díaz','López','Martín','1985-09-22 00:00:00'),(5,'Chávez','Fernández','Miguel Ángel','1991-04-05 00:00:00'),(6,'Estrada','García','Beatriz','1995-05-06 00:00:00'),(7,'Fierro','Menéndez','Gilberto','1992-06-17 00:00:00'),(8,'García','Villavicencio','Rafael','2000-07-18 00:00:00'),(9,'Higuera','Jara','Víctor Manuel','1973-08-19 00:00:00'),(10,'Jiménez','Maya','Carmen Gisela','1988-10-03 00:00:00');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-21 21:34:29

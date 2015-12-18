-- MySQL dump 10.13  Distrib 5.5.45, for Linux (x86_64)
--
-- Host: localhost    Database: CulturaApp
-- ------------------------------------------------------
-- Server version	5.5.45

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
-- Table structure for table `Evento`
--

DROP TABLE IF EXISTS `Evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Evento` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id del evento',
  `NOMBRE` varchar(50) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Nombre del evento',
  `TIPO` varchar(30) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Tipo del evento',
  `LUGAR` varchar(11) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Lugar del evento',
  `CUPOS` int(11) NOT NULL COMMENT 'Cupos del evento',
  `DESCRIPCION` varchar(70) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Descripcion del evento',
  `CALIFICACION` int(11) NOT NULL COMMENT 'Calicacion del evento',
  `NUMEROBUSQUEDAS` bigint(255) NOT NULL COMMENT 'Numero de busquedas',
  `FOTO` varchar(150) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Foto del evento',
  `FECHAEVENTO` date NOT NULL COMMENT 'Fecha del evento',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Evento`
--

LOCK TABLES `Evento` WRITE;
/*!40000 ALTER TABLE `Evento` DISABLE KEYS */;
INSERT INTO `Evento` VALUES (2,'Flauta acustica','Musica','Metropoli',100,'Concierto acustico',5,70,'/flauta.jpg','2016-05-31');
/*!40000 ALTER TABLE `Evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Historial`
--

DROP TABLE IF EXISTS `Historial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Historial` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id del historial',
  `NOMBRE` varchar(50) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Nombre en el historial',
  `FECHA` date NOT NULL COMMENT 'Fecha del historial',
  `ESTADO` varchar(30) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Estado del historial',
  `TIPO` varchar(20) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Tipo de historial',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Historial`
--

LOCK TABLES `Historial` WRITE;
/*!40000 ALTER TABLE `Historial` DISABLE KEYS */;
/*!40000 ALTER TABLE `Historial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Lugar`
--

DROP TABLE IF EXISTS `Lugar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Lugar` (
  `IDLUGAR` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id lugar',
  `NOMBRE` varchar(50) COLLATE utf8_spanish_ci NOT NULL COMMENT 'nombre del lugar',
  `DIRECCION` varchar(70) COLLATE utf8_spanish_ci NOT NULL COMMENT 'direccion del lugar',
  `COORDENADAS` varchar(100) COLLATE utf8_spanish_ci NOT NULL COMMENT 'coordenadas del lugar',
  `FOTO` varchar(150) COLLATE utf8_spanish_ci NOT NULL COMMENT 'foto del lugar',
  `CALIFICACION` int(11) NOT NULL COMMENT 'calificacion del lugar',
  `CUPOS` int(11) NOT NULL COMMENT 'cupos del lugar',
  PRIMARY KEY (`IDLUGAR`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Lugar`
--

LOCK TABLES `Lugar` WRITE;
/*!40000 ALTER TABLE `Lugar` DISABLE KEYS */;
INSERT INTO `Lugar` VALUES (1,'Metropolitano de Medellin','Calle 32 sur # 47 -46','0,08','/fotos/foto.jpgpp',5,123),(2,'Pablo Tobon Uribe','Calle 40 # 58','[0,06][0,03]','/fotos/foto1.jpg',4,250),(3,'Pablo Tobon Uribe','Calle 40 # 58','[0,06][0,03]','/fotos/foto1.jpg',4,250),(4,'Nombre','Direccion','[0,08][0,07]','FOTO',3,5000),(5,'portal','calle 40','norte','foticos',2,150),(6,'pablo tobon','calle 52 sur','34','/foto/pablo.jpg',4,200),(9,'Concordia','calle 70','43','concordia',4,120),(10,'portal','direccion','coorde','Foticos',2,400);
/*!40000 ALTER TABLE `Lugar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reservas`
--

DROP TABLE IF EXISTS `Reservas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reservas` (
  `ID` int(20) NOT NULL COMMENT 'Id de la reserva',
  `LUGAR` varchar(70) COLLATE utf8_spanish_ci NOT NULL COMMENT 'lugar de la reserva',
  `FECHA` date NOT NULL COMMENT 'fecha que se realizo la reserva',
  `EVENTO` varchar(50) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Evento de la reserva la reserva',
  `USUARIO` varchar(50) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Usuario que realizo la reserva',
  `ESTADO` varchar(30) COLLATE utf8_spanish_ci NOT NULL COMMENT 'Estado de la reserva',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reservas`
--

LOCK TABLES `Reservas` WRITE;
/*!40000 ALTER TABLE `Reservas` DISABLE KEYS */;
/*!40000 ALTER TABLE `Reservas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Usuario` (
  `IDUSUARIO` int(20) NOT NULL  COMMENT 'Id del usuario',
  `TTPO_DOCUMENTO` varchar(10) NOT NULL COMMENT 'TIPO DE DOCUMENTO NIT,CC',
  `PRIMER_NOMBRE` varchar(50) COLLATE utf8_spanish_ci COMMENT 'PRIMER Nombre usuario',
  `SEGUNDO_NOMBRE` varchar(50) COLLATE utf8_spanish_ci COMMENT 'SEGUNDO Nombre usuario',
  `PRIMER_APELLIDO` varchar(50) COLLATE utf8_spanish_ci COMMENT 'Primer apellido usuario',
  `SEGUNDO_APELLIDO` varchar(50) COLLATE utf8_spanish_ci COMMENT 'Segundo apellido usuario',
  `FOTO` varchar(150) COLLATE utf8_spanish_ci COMMENT 'Foto perfil usuario',
  `EMAIL` varchar(70) COLLATE utf8_spanish_ci COMMENT 'Email del usuario',
  `CELULAR` varchar(11) COMMENT 'Celular del usuario',
  PRIMARY KEY (`IDUSUARIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-17 19:02:44

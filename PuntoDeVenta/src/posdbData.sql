-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: posdb
-- ------------------------------------------------------
-- Server version	5.6.27

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
-- Table structure for table `ciudades`
--

DROP TABLE IF EXISTS `ciudades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ciudades` (
  `idciudad` int(11) NOT NULL AUTO_INCREMENT,
  `idprovincia` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`idciudad`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudades`
--

LOCK TABLES `ciudades` WRITE;
/*!40000 ALTER TABLE `ciudades` DISABLE KEYS */;
INSERT INTO `ciudades` VALUES (1,1,'San Francisco de Macorís'),(3,100,'Narnia');
/*!40000 ALTER TABLE `ciudades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `idcliente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `celular` varchar(15) DEFAULT NULL,
  `identificacion` varchar(15) DEFAULT NULL,
  `sexo` bit(1) NOT NULL,
  `tasaDescuento` float NOT NULL DEFAULT '0',
  `clienteDesde` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idcliente`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'King','Enum','C/Castillo','809-852-8748','809-852-8745','056-2546824-8','\0',90,'2016-12-13 13:10:56'),(2,'Lambda','Enum','C/Padrea Brea #126','809-852-8792','809-852-8745','258-9524698-5','\0',6,'2016-12-13 13:11:23'),(6,'Jose Manuel','Remigio Trinidad ','C/Papi Olivier #165 APT #4','888-888-2222','777-666-6262','888-9999999-5','\0',30,'2016-12-13 20:40:59'),(7,'Isidro','de Jesús','Cercado','809-874-9898','809-874-9898','402-8745632-1','\0',0,'2016-12-14 06:05:13'),(8,'Florisa','Romero','C/Papi Olivier','809-854-5215','809-854-5215','056-0248356-8','\0',30,'2016-12-14 19:41:30'),(9,'Nuevo Cliente','Hola Como estas','Vamos','','','402-2463259-2','',5,'2016-12-21 19:02:24'),(10,'Editar un Cliente','Es sencillo','Lejos','','','402-262-5456','',0,'2016-12-21 19:33:10'),(11,'Nombre de prueba','Apellido de prueba','Yo vivo lejos',NULL,'809-111-1111','402-2463259-2','',1,'2017-05-19 12:31:06'),(12,'Nombre de prueba','Apellido de prueba','Yo vivo lejos',NULL,'809-111-1111','402-2463259-2','',1,'2017-05-19 12:31:13'),(13,'Nombre de prueba','Apellido de prueba','Yo vivo lejos',NULL,'809-111-1111','402-2463259-2','',1,'2017-05-19 12:31:42'),(14,'Nombre de prueba','Apellido de prueba','Yo vivo lejos',NULL,'809-111-1111','402-2463259-2','',1,'2017-05-19 12:47:30'),(15,'Nombre de prueba','Apellido de prueba','Yo vivo lejos',NULL,'809-111-1111','402-2463259-2','',1,'2017-05-19 13:00:23'),(16,'Nombre de prueba','Apellido de prueba','Yo vivo lejos',NULL,'809-111-1111','402-2463259-2','',1,'2017-05-19 13:00:47'),(17,'Nombre de prueba','Apellido de prueba','Yo vivo lejos',NULL,'809-111-1111','402-2463259-2','',1,'2017-05-19 13:05:37'),(18,'Nombre de prueba','Apellido de prueba','Yo vivo lejos',NULL,'809-111-1111','402-2463259-2','',1,'2017-05-19 13:06:19'),(19,'Nombre de prueba','Apellido de prueba','Yo vivo lejos',NULL,'809-111-1111','402-2463259-2','',1,'2017-05-19 13:06:37'),(20,'Nombre de prueba','Apellido de prueba','Yo vivo lejos',NULL,'809-111-1111','402-2463259-2','',1,'2017-05-19 14:58:18'),(22,'Nombre de prueba','Apellido de prueba','Yo vivo lejos',NULL,'809-111-1111','402-2463259-2','',1,'2017-05-19 15:24:48'),(24,'Nombre de prueba','Apellido de prueba','Yo vivo lejos',NULL,'809-111-1111','402-2463259-2','',1,'2017-05-19 15:28:52'),(26,'Nombre de prueba','Apellido de prueba','Yo vivo lejos',NULL,'809-111-1111','402-2463259-2','',1,'2017-05-25 00:31:55'),(28,'Nombre de prueba','Apellido de prueba','Yo vivo lejos',NULL,'809-111-1111','402-2463259-2','',1,'2017-05-26 16:14:59');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comprasdetalle`
--

DROP TABLE IF EXISTS `comprasdetalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comprasdetalle` (
  `idcompra` int(11) NOT NULL,
  `idproducto` int(11) NOT NULL,
  `valor` float NOT NULL,
  `impuestos` float NOT NULL,
  `subtotal` float NOT NULL,
  `cantidad` float DEFAULT NULL,
  `tasaImpuestos` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`idcompra`,`idproducto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comprasdetalle`
--

LOCK TABLES `comprasdetalle` WRITE;
/*!40000 ALTER TABLE `comprasdetalle` DISABLE KEYS */;
INSERT INTO `comprasdetalle` VALUES (1,1,100,0.18,50,2,0),(2,2,50,0.18,100,5,0),(4,4,35,0.25,35,2,0),(9,1,10,0,10,1,0),(9,2,10,0,10,1,0),(9,3,10,0,10,1,0),(9,4,10,0,10,1,0),(9,5,10,0,10,1,0),(9,6,10,0,10,1,0),(10,1,10,0,10,1,0),(10,2,10,0,10,1,0),(10,3,10,0,10,1,0),(10,4,10,0,10,1,0),(10,5,10,0,10,1,0),(10,6,10,0,10,1,0),(11,1,10,0,10,1,0),(11,2,10,0,10,1,0),(11,3,10,0,10,1,0),(11,4,10,0,10,1,0),(11,5,10,0,10,1,0),(11,6,10,0,10,1,0),(12,1,10,0,10,1,0),(13,1,10,0,10,1,0),(14,1,10,0,10,1,0),(15,1,10,0,10,1,0),(16,1,10,0,10,1,0),(17,1,10,0,10,1,0),(18,1,10,0,10,1,0),(19,1,10,0,10,1,0),(20,1,10,0,10,1,0),(21,1,10,0,10,1,0),(22,1,10,0,10,1,0),(23,1,10,0,10,1,0),(24,1,10,0,10,1,0),(25,1,10,0,10,1,0),(26,1,10,0,10,1,0),(27,1,10,0,10,1,0),(28,1,10,0,10,1,0),(29,1,10,0,10,1,0),(30,1,10,0,10,1,0),(31,1,10,0,10,1,0),(32,1,10,0,10,1,0),(33,1,10,0,10,1,0),(34,1,10,0,10,1,0);
/*!40000 ALTER TABLE `comprasdetalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comprasencabezado`
--

DROP TABLE IF EXISTS `comprasencabezado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comprasencabezado` (
  `idcompra` int(11) NOT NULL AUTO_INCREMENT,
  `idsuplidor` int(11) NOT NULL,
  `idtienda` int(11) NOT NULL,
  `idsupervisor` int(11) DEFAULT NULL,
  `subtotal` float NOT NULL,
  `impuestos` float NOT NULL,
  `descuentos` float NOT NULL,
  `total` float NOT NULL,
  `efectuado` bit(1) NOT NULL,
  `fecha` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idcompra`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comprasencabezado`
--

LOCK TABLES `comprasencabezado` WRITE;
/*!40000 ALTER TABLE `comprasencabezado` DISABLE KEYS */;
INSERT INTO `comprasencabezado` VALUES (1,2,1,5,0,0.498,0.16,289.502,'','2016-12-20 17:00:19'),(2,2,1,5,0,0.498,0.16,289.502,'','2016-12-20 17:23:14'),(3,2,1,5,0,0.498,0.16,289.502,'','2016-12-21 11:09:47'),(4,2,1,5,0,0.498,0.16,289.502,'','2016-12-21 18:45:07'),(5,2,1,5,0,0.498,0.16,289.502,'','2016-12-21 19:45:55'),(6,2,1,5,0,0.498,0.16,289.502,'','2017-05-25 22:55:33'),(7,2,1,5,0,0.498,0.16,289.502,'','2017-05-25 23:07:39'),(8,2,1,5,0,0.498,0.16,289.502,'','2017-05-25 23:08:33'),(9,2,1,5,0,0.498,0.16,289.502,'','2017-05-26 11:49:51'),(10,2,1,5,0,0.498,0.16,289.502,'','2017-05-26 11:51:00'),(11,2,1,5,0,0.498,0.16,289.502,'','2017-05-26 11:51:38'),(12,2,1,5,0,0.498,0.16,289.502,'','2017-05-26 12:02:25'),(13,2,1,5,0,0.498,0.16,289.502,'','2017-05-26 12:05:39'),(14,2,1,5,0,0.498,0.16,289.502,'','2017-05-26 12:16:26'),(15,2,1,5,0,0.498,0.16,289.502,'','2017-05-26 12:19:23'),(16,2,1,5,0,0.498,0.16,289.502,'','2017-05-26 12:22:20'),(17,2,1,5,0,0.498,0.16,289.502,'','2017-05-26 12:24:49'),(18,2,1,5,0,0.498,0.16,289.502,'','2017-05-26 12:26:02'),(19,2,1,5,0,0.498,0.16,289.502,'','2017-05-26 12:26:59'),(20,2,1,5,0,0.498,0.16,289.502,'','2017-05-26 12:28:52'),(21,2,1,5,0,0.498,0.16,289.502,'','2017-05-26 12:32:46'),(22,2,1,5,0,0.498,0.16,289.502,'','2017-05-26 12:33:08'),(23,2,1,5,0,0.498,0.16,289.502,'','2017-05-26 12:33:22'),(25,2,1,5,0,0.498,0.16,289.502,'','2017-05-26 13:01:56'),(26,2,1,5,0,0.498,0.16,289.502,'','2017-05-26 17:00:49'),(27,2,1,5,0,0.498,0.16,289.502,'','2017-05-26 17:00:50'),(30,2,1,5,0,0.498,0.16,289.502,'','2017-05-26 17:01:17'),(33,1,1,1,0,0,0,70,'','2017-05-26 17:29:56');
/*!40000 ALTER TABLE `comprasencabezado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `devolucionescomprasdetalle`
--

DROP TABLE IF EXISTS `devolucionescomprasdetalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `devolucionescomprasdetalle` (
  `iddevolucioncompra` int(11) NOT NULL,
  `idproducto` int(11) NOT NULL,
  `subtotal` float NOT NULL,
  `cantidad` float DEFAULT NULL,
  PRIMARY KEY (`iddevolucioncompra`,`idproducto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devolucionescomprasdetalle`
--

LOCK TABLES `devolucionescomprasdetalle` WRITE;
/*!40000 ALTER TABLE `devolucionescomprasdetalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `devolucionescomprasdetalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `devolucionescomprasencabezado`
--

DROP TABLE IF EXISTS `devolucionescomprasencabezado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `devolucionescomprasencabezado` (
  `iddevolucioncompra` int(11) NOT NULL AUTO_INCREMENT,
  `idcompra` int(11) NOT NULL,
  `idsupervisor` int(11) NOT NULL,
  `idnotadebito` int(11) NOT NULL,
  PRIMARY KEY (`iddevolucioncompra`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devolucionescomprasencabezado`
--

LOCK TABLES `devolucionescomprasencabezado` WRITE;
/*!40000 ALTER TABLE `devolucionescomprasencabezado` DISABLE KEYS */;
/*!40000 ALTER TABLE `devolucionescomprasencabezado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `devolucionesventasdetalle`
--

DROP TABLE IF EXISTS `devolucionesventasdetalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `devolucionesventasdetalle` (
  `iddevolucionventa` int(11) NOT NULL,
  `idproducto` int(11) NOT NULL,
  `subtotal` float NOT NULL,
  `cantidad` float DEFAULT NULL,
  PRIMARY KEY (`iddevolucionventa`,`idproducto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devolucionesventasdetalle`
--

LOCK TABLES `devolucionesventasdetalle` WRITE;
/*!40000 ALTER TABLE `devolucionesventasdetalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `devolucionesventasdetalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `devolucionesventasencabezado`
--

DROP TABLE IF EXISTS `devolucionesventasencabezado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `devolucionesventasencabezado` (
  `iddevolucionventa` int(11) NOT NULL AUTO_INCREMENT,
  `idventa` int(11) NOT NULL,
  `idsupervisor` int(11) NOT NULL,
  `idnotacredito` int(11) NOT NULL,
  PRIMARY KEY (`iddevolucionventa`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devolucionesventasencabezado`
--

LOCK TABLES `devolucionesventasencabezado` WRITE;
/*!40000 ALTER TABLE `devolucionesventasencabezado` DISABLE KEYS */;
INSERT INTO `devolucionesventasencabezado` VALUES (1,12,1,1);
/*!40000 ALTER TABLE `devolucionesventasencabezado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventarioproductos`
--

DROP TABLE IF EXISTS `inventarioproductos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventarioproductos` (
  `idtienda` int(11) NOT NULL,
  `idproducto` int(11) NOT NULL,
  `inventario` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`idtienda`,`idproducto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventarioproductos`
--

LOCK TABLES `inventarioproductos` WRITE;
/*!40000 ALTER TABLE `inventarioproductos` DISABLE KEYS */;
INSERT INTO `inventarioproductos` VALUES (1,1,33),(1,2,28),(1,3,40),(1,4,28),(1,5,28),(1,6,28),(1,7,0),(1,8,3),(1,9,0),(2,1,0),(2,2,0),(2,3,0),(2,4,0),(2,5,0),(2,6,0),(2,7,0),(2,8,0),(2,9,0),(3,1,0),(3,2,0),(3,3,0),(3,4,0),(3,5,0),(3,6,0),(3,7,0),(3,8,0),(3,9,0),(5,1,0),(5,2,0),(5,3,0),(5,4,0),(5,5,0),(5,6,0),(5,7,0),(5,8,0),(5,9,0),(6,1,0),(6,2,0),(6,3,0),(6,4,0),(6,5,0),(7,1,0),(7,2,0),(7,3,0),(7,4,0),(7,5,0),(7,6,0),(7,7,0),(7,8,0),(7,9,0),(8,1,0),(8,2,0),(8,3,0),(8,4,0),(8,5,0),(8,6,0),(8,8,0),(9,1,0),(9,2,0),(9,3,0),(9,4,0),(9,5,0),(9,6,0),(9,8,0),(10,1,0),(10,2,0),(10,3,0),(10,4,0),(10,5,0),(10,6,0),(10,8,0),(11,1,0),(11,2,0),(11,3,0),(11,4,0),(11,5,0),(11,6,0),(11,8,0);
/*!40000 ALTER TABLE `inventarioproductos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notascredito`
--

DROP TABLE IF EXISTS `notascredito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notascredito` (
  `idnotacredito` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` datetime DEFAULT CURRENT_TIMESTAMP,
  `total` float DEFAULT NULL,
  `efectuado` float DEFAULT NULL,
  `idcliente` int(11) DEFAULT NULL,
  `concepto` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idnotacredito`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notascredito`
--

LOCK TABLES `notascredito` WRITE;
/*!40000 ALTER TABLE `notascredito` DISABLE KEYS */;
INSERT INTO `notascredito` VALUES (1,'2016-12-21 21:32:05',99.82,0,10,'Nota de Crédito por devolución de Venta #12');
/*!40000 ALTER TABLE `notascredito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notasdebito`
--

DROP TABLE IF EXISTS `notasdebito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notasdebito` (
  `idnotadebito` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` datetime DEFAULT CURRENT_TIMESTAMP,
  `total` float DEFAULT NULL,
  `efectuado` float DEFAULT NULL,
  `idsuplidor` int(11) DEFAULT NULL,
  `concepto` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idnotadebito`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notasdebito`
--

LOCK TABLES `notasdebito` WRITE;
/*!40000 ALTER TABLE `notasdebito` DISABLE KEYS */;
/*!40000 ALTER TABLE `notasdebito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paises`
--

DROP TABLE IF EXISTS `paises`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paises` (
  `idpais` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`idpais`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paises`
--

LOCK TABLES `paises` WRITE;
/*!40000 ALTER TABLE `paises` DISABLE KEYS */;
INSERT INTO `paises` VALUES (56,'Republica Dominicana'),(58,'Egipto'),(59,'Cuba'),(60,'Nicaragua'),(61,'Venezuela'),(62,'Colombia'),(63,'Argentina'),(70,'Republica Dominicana1'),(71,'Republica Dominicana1'),(72,'Egipto 1'),(73,'Egipto1'),(74,'Egipto 4'),(75,'Egipto232'),(77,'Narnia');
/*!40000 ALTER TABLE `paises` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `idproducto` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(10) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `costo` float NOT NULL,
  `precio` float NOT NULL,
  `tasaImpuesto` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`idproducto`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'9897512236','Berenjena Juice DS',80,100,0.18),(2,'9658456399','ARROZ LA GARZA',200,250,0.18),(3,'365484787','ACEITE CRISOL 64MG ',350,375,0.18),(4,'326984521','AGUACATE MADURO',55,40,0.12),(5,'COD-858','Coca Cola',70,85,16),(6,'855595598','Arroz con Piña',25,5062,0.09),(8,'ASD123','Producto de prueba',10,10,0.16);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provincias`
--

DROP TABLE IF EXISTS `provincias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provincias` (
  `idprovincia` int(11) NOT NULL AUTO_INCREMENT,
  `idpais` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  PRIMARY KEY (`idprovincia`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincias`
--

LOCK TABLES `provincias` WRITE;
/*!40000 ALTER TABLE `provincias` DISABLE KEYS */;
INSERT INTO `provincias` VALUES (1,56,'Duarte'),(3,100,'Narnia state');
/*!40000 ALTER TABLE `provincias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suplidores`
--

DROP TABLE IF EXISTS `suplidores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suplidores` (
  `idsuplidor` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `telefono` varchar(20) NOT NULL,
  `celular` varchar(20) DEFAULT NULL,
  `sexo` bit(1) NOT NULL,
  `empresa` varchar(100) DEFAULT NULL,
  `rnc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idsuplidor`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suplidores`
--

LOCK TABLES `suplidores` WRITE;
/*!40000 ALTER TABLE `suplidores` DISABLE KEYS */;
INSERT INTO `suplidores` VALUES (1,'Jose Miguel','Trinidad R.','C/Billini #52','829-471-8527','829-324-8252','\0','Empresa','852696945265'),(2,'Florisa ','Romero P.','C/Trompezon ','809-725-0000','829-875-2415','\0','Compumisce','124-5854-52'),(4,'Luego','No Digas','Que no pasa nada','809','849','','Lejos','8988498784'),(5,'Este es un Suplidor','Veamos 2','Lejos Lejos','809-852','849','','HotPhix','40256565'),(7,'Nombre de prueba','Apellido de prueba','Direccion de prueba','Telefono de prueba','Celular de prueba','','Empresa de pruebas','RNC de prueba');
/*!40000 ALTER TABLE `suplidores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tiendas`
--

DROP TABLE IF EXISTS `tiendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tiendas` (
  `idtienda` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `slogan` varchar(150) DEFAULT NULL,
  `idciudad` int(11) DEFAULT NULL,
  PRIMARY KEY (`idtienda`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tiendas`
--

LOCK TABLES `tiendas` WRITE;
/*!40000 ALTER TABLE `tiendas` DISABLE KEYS */;
INSERT INTO `tiendas` VALUES (1,'asd1','asd1','asd1',1),(2,'asd1','asd1','asd1',1),(3,'asd1','asd1','asd1',1),(5,'Nueva Tienda','','',1),(7,'Tienda Nueva de Prueba','Kimchonwan Veamos','De Lejos te vendemos joyería',1),(8,'Empresa Ink.','Drieccion','Slogan',100),(10,'Empresa Ink.','Drieccion','Slogan',100);
/*!40000 ALTER TABLE `tiendas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombreusuario` varchar(20) DEFAULT NULL,
  `hashClave` varchar(256) DEFAULT NULL,
  `salesClave` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `nombreCompleto` varchar(100) DEFAULT NULL,
  `tipo` tinyint(4) DEFAULT NULL,
  `idTienda` int(11) DEFAULT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'emt38','$5$f$vTQxGFkFt/jXHrTjMgLupEzRppB.mMVTSae2W/.cSw0','$5$f\\_j?x^??4??','Ezequiel Moneró',2,1),(4,'jmtrinidad','$5$fo$hiG7HqygMtoLAuUdIwcS0KQ2P6s8McG3PzxFIPjeGu5','$5$fod�~��3���ڪ','José Miguel Trinidad Remigio',2,1),(5,'thefran','$5$f$C4RowGEDKEFXksVgIp53nFw4h1wQuZQnebeIzIty8t8','$5$f�x�m5e�e�ѩ','Francisco Alberto Frías Alvarado',2,1),(6,'elprincipee','$5$f.K$S4vLNedeT//T2rrVAo2i9lDxzEOGHBfBsGFHI8BAjO.','$5$f.K�fR���()�','Franklyn de Jesús Mejía',2,1),(7,'dragneel','$5$f$C4RowGEDKEFXksVgIp53nFw4h1wQuZQnebeIzIty8t8','$5$f���&�\\���#�%','Erickberto Pérez',2,1),(8,'wild','$5$f47$HBNDMKJYgbqYNpLCsnWZjfcSw8U9ltHCbE0fuB9ANa0','$5$f47�^\0�I!?�','Isidro de Jesús',2,1),(9,'admin','$5$f$C4RowGEDKEFXksVgIp53nFw4h1wQuZQnebeIzIty8t8','$5$f�#�si�^�Uh�O','Administrador de Prueba',0,1),(11,'elnuevo','$5$f$qOofaw4sPvUBXm0ibb/aEzJCQmiZdeg6JEakYjSNdc1','$5$fyfEC*','Este es un Cajerito',1,1),(13,'PruebaNombreUsuario','pruebaHashClave','pruebaSalesClave','PruebaNombreCompleto',1,100);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventasdetalle`
--

DROP TABLE IF EXISTS `ventasdetalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ventasdetalle` (
  `idventa` int(11) NOT NULL,
  `idproducto` int(11) NOT NULL,
  `valor` float NOT NULL,
  `impuestos` float NOT NULL,
  `subtotal` float NOT NULL,
  `cantidad` float NOT NULL,
  `tasaImpuestos` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`idventa`,`idproducto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventasdetalle`
--

LOCK TABLES `ventasdetalle` WRITE;
/*!40000 ALTER TABLE `ventasdetalle` DISABLE KEYS */;
INSERT INTO `ventasdetalle` VALUES (1,2,5,0.6,251,2,0),(2,1,100,0.18,99.82,1,0),(2,3,375,0.675,374.3,1,0),(11,1,100,0.18,99.82,1,0),(12,1,100,0.18,99.82,1,0),(13,1,10,0,10,1,0),(14,1,10,0,10,1,0),(15,1,10,0,10,1,0),(16,1,10,0,10,1,0),(17,1,10,0,10,1,0),(18,1,10,0,10,1,0),(19,1,10,0,10,1,0),(20,1,10,0,10,1,0),(21,1,10,0,10,1,0),(22,1,10,0,10,1,0),(23,1,10,0,10,1,0),(24,1,10,0,10,1,0),(25,1,10,0,10,1,0),(26,1,10,0,10,1,0),(27,1,10,0,10,1,0),(28,1,10,0,10,1,0),(29,1,10,0,10,1,0),(30,1,10,0,10,1,0),(31,1,10,0,10,1,0),(32,1,10,0,10,1,0),(33,1,10,0,10,1,0),(34,1,10,0,10,1,0),(35,1,10,0,10,1,0),(36,1,10,0,10,1,0),(37,1,10,0,10,1,0);
/*!40000 ALTER TABLE `ventasdetalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventasencabezado`
--

DROP TABLE IF EXISTS `ventasencabezado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ventasencabezado` (
  `idventa` int(11) NOT NULL AUTO_INCREMENT,
  `idcliente` int(11) NOT NULL,
  `idtienda` int(11) NOT NULL,
  `idcajero` int(11) DEFAULT NULL,
  `terminalVentas` int(11) DEFAULT NULL,
  `efectivoRecibido` int(11) NOT NULL,
  `cambioDevuelto` int(11) NOT NULL,
  `subtotal` float NOT NULL,
  `impuestos` float NOT NULL,
  `descuentos` float NOT NULL,
  `total` float NOT NULL,
  `efectuado` bit(1) NOT NULL,
  `fecha` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idventa`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventasencabezado`
--

LOCK TABLES `ventasencabezado` WRITE;
/*!40000 ALTER TABLE `ventasencabezado` DISABLE KEYS */;
INSERT INTO `ventasencabezado` VALUES (1,8,1,5,0,2000,253,0,3.15,0.16,1746.85,'','2016-01-01 00:00:00'),(12,10,1,11,0,200,100,0,0.18,0,99.82,'','2016-12-21 19:45:02'),(13,1,1,1,0,0,0,0,0,0,60,'','2017-05-26 13:19:12'),(14,1,1,1,0,0,0,0,0,0,60,'','2017-05-26 13:19:12'),(15,1,1,1,0,0,0,0,0,0,60,'','2017-05-26 13:19:13'),(16,1,1,1,0,0,0,0,0,0,60,'','2017-05-26 13:21:26'),(17,1,1,1,0,0,0,0,0,0,60,'','2017-05-26 13:21:26'),(18,1,1,1,0,0,0,0,0,0,60,'','2017-05-26 13:21:27'),(20,1,1,1,0,0,0,0,0,0,60,'','2017-05-26 13:23:18'),(23,1,1,1,0,0,0,0,0,0,60,'','2017-05-26 13:27:43'),(26,1,1,1,0,0,0,0,0,0,60,'','2017-05-26 13:29:21'),(27,1,1,1,0,0,0,0,0,0,60,'','2017-05-26 14:29:40'),(28,1,1,1,0,0,0,0,0,0,60,'','2017-05-26 14:29:41'),(31,1,1,1,0,0,0,0,0,0,60,'','2017-05-26 14:30:08'),(32,1,1,1,0,0,0,0,0,0,70,'','2017-05-26 17:05:00'),(33,1,1,1,0,0,0,0,0,0,70,'','2017-05-26 17:05:00'),(36,1,1,1,0,0,0,0,0,0,70,'','2017-05-26 17:05:17');
/*!40000 ALTER TABLE `ventasencabezado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `viewcompras`
--

DROP TABLE IF EXISTS `viewcompras`;
/*!50001 DROP VIEW IF EXISTS `viewcompras`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `viewcompras` AS SELECT 
 1 AS `descripcion`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `viewventas`
--

DROP TABLE IF EXISTS `viewventas`;
/*!50001 DROP VIEW IF EXISTS `viewventas`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `viewventas` AS SELECT 
 1 AS `descripcion`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping routines for database 'posdb'
--
/*!50003 DROP PROCEDURE IF EXISTS `AgregarCiudad` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AgregarCiudad`(in Idprov int(11), in Nom varchar(100))
begin
	insert into ciudades(idprovincia, nombre) values (Idprov,Nom);
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AgregarCliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AgregarCliente`(
 in nom varchar(100),
 in ape varchar(100),
 in dir varchar(200),
 in tel varchar(15), 
 in cel varchar(15),
 in ident varchar(15),
 in sex bit(1),
 in tasadesc float
 )
begin
	insert into clientes (nombre, apellido, direccion, telefono, celular, identificacion, sexo, tasadescuento)
    values(nom, ape, dir, tel, cel, ident, sex, tasadesc);
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AgregarComprasDetalle` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AgregarComprasDetalle`(
in idcomp int(11),
in idprod int(11),
in _valor float,
in imp float,
in subt float,
in cant float
)
begin
	insert into comprasdetalle(idcompra, idProducto, valor, impuestos, subtotal, cantidad) values (idcomp, idprod, _valor, imp, subt, cant);

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AgregarComprasEncabezado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AgregarComprasEncabezado`(in _idsuplidor int(11), in _idtienda int(11), in _idsupervisor int(11), in _subtotal float, in _impuestos float, in _descuentos float, in _total float, in _efectuado float)
begin
	insert into comprasencabezado(idsuplidor, idtienda, idsupervisor, subtotal, impuestos, descuentos, total, efectuado) values (_idsuplidor, _idtienda, _idsupervisor, _subtotal, _impuestos, _descuentos, _total, _efectuado);
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AgregarDevolucionesComprasDetalle` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AgregarDevolucionesComprasDetalle`(
in _iddevolucioncompra int(11),
in _idProducto int(11),
in _subTotal float,
in _cantidad float
)
begin
	insert into devolucionescomprasdetalle (iddevolucioncompra, idproducto, subtotal, cantidad) values(_iddevolucioncompra, _idProducto, _subTotal, _cantidad);
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AgregarDevolucionesComprasEncabezado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AgregarDevolucionesComprasEncabezado`(
in _idCompra int(11),
in _idSupervisor int(11),
in _idNotaDebito int(11)
)
begin
	insert into devolucionescomprasencabezado(idcompra, idsupervisor, idnotadebito) values (_idCompra, _idSupervisor, _idNotaDebito);
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AgregarDevolucionesVentasDetalle` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AgregarDevolucionesVentasDetalle`(
in _idDevolucionVenta int(11),
in _idProducto int(11),
in _subTotal float,
in _cantidad float
)
begin
	insert into devolucionesventasdetalle(iddevolucionventa, idproducto, subtotal, cantidad) values (_idDevolucionVenta, _idproducto, _subtotal, _cantidad);

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AgregarDevolucionesVentasEncabezado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AgregarDevolucionesVentasEncabezado`(
in _idVenta int(11),
in _idSupervisor int(11),
in _idNotaCredito int(11)
)
begin
	insert into devolucionesventasencabezado(idVenta, idSupervisor, idNotaCredito) values (_idVenta, _idSupervisor, _idNotaCredito);

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AgregarInventarioProductos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AgregarInventarioProductos`(
in _idTienda int(11),
in _idProducto int(11),
in _inventario float
)
begin 
	insert into inventarioproductos(idTienda,  idProducto, inventario) values(_idTienda,  _idProducto, _inventario);

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AgregarNotasCredito` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AgregarNotasCredito`(
in _total float,
in _efectuado float,
in _idcliente int(11),
in _concepto varchar(100)
)
begin
insert into notascredito (total, efectuado, idcliente, concepto) values(_total, _efectuado, _idcliente, _concepto);

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AgregarNotasDebito` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AgregarNotasDebito`(
in _total float,
in _efectuado float,
in _idSuplidor int(11),
in _concepto varchar(100)
)
begin
insert into notasDebito(total, efectuado, idSuplidor, concepto) values(total, _efectuado, _idSuplidor, _concepto);

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AgregarPais` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AgregarPais`(
in _Nombre varchar(100)
)
begin 
	insert into paises (nombre) values(_Nombre);
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AgregarProducto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AgregarProducto`(
in _codigo varchar(10),
in _descripcion varchar(100),
in _costo float,
in _precio float,
in _tasaImpuesto float
)
begin
	DECLARE idprod INT;
    
	insert into productos(codigo, descripcion, costo, precio, tasaImpuesto) values(_codigo, _descripcion, _costo, _precio, _tasaImpuesto);
	SET idprod = LAST_INSERT_ID();
    
    INSERT INTO inventarioproductos(idproducto, idtienda, inventario)
    SELECT idprod, idtienda, 0 FROM tiendas;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AgregarProvincia` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AgregarProvincia`(
in _idpais int(11),
in _nombre varchar(100)
)
begin
	insert into provincias (idpais, nombre) values(_idpais, _nombre);
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AgregarSuplidor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AgregarSuplidor`(
in _nombre varchar(100),
in _apellido varchar(100),
in _direccion varchar(200),
in _telefono varchar(20),
in _celular varchar(20),
in _sexo bit(1),
in _empresa varchar(100),
in _rnc varchar(45)
)
begin 
	insert into suplidores(nombre, apellido, direccion, telefono, celular,  sexo, empresa, rnc ) values(_nombre, _apellido, _direccion, _telefono, _celular,  _sexo, _empresa, _rnc );
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AgregarTienda` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AgregarTienda`(
in _nombre varchar(100),
in _direccion varchar(200),
in _slogan varchar(150),
in _idciudad int(11)
)
begin
	DECLARE idtien INT;
	insert into tiendas(nombre, direccion, slogan, idciudad) values (_nombre, _direccion, _slogan, _idciudad);   
	
    
	SET idtien = LAST_INSERT_ID();
    
    INSERT INTO inventarioproductos(idproducto, idtienda, inventario)
    SELECT idproducto, idtien, 0 FROM productos;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AgregarUsuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AgregarUsuario`(
in _nombreUsuario varchar(20),
in _hashClave varchar(256),
in _salesClave varchar(45),
in _nombreCompleto varchar(100),
in _tipo tinyint(4),
in _idTienda int(11)
)
begin 
	insert into usuarios(nombreUsuario, hashClave, salesClave, nombreCompleto, tipo, idTienda ) values(_nombreUsuario, _hashClave, _salesClave, _nombreCompleto, _tipo, _idTienda );
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AgregarVentasDetalle` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AgregarVentasDetalle`(
in _idVenta int(11),
in _idProducto int(11),
in  _valor float,
in _impuestos float,
in _subtotal float,
in _cantidad float
)
begin
	insert into ventasdetalle(idVenta, idProducto, valor, impuestos, subtotal, cantidad ) values (_idVenta, _idProducto, _valor, _impuestos, _subtotal, _cantidad );
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AgregarVentasEncabezado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AgregarVentasEncabezado`(
in _idCliente int(11),
in _idTienda int(11),
in _idCajero int(11),
in _terminalVentas int(11),
in _efectivoRecibido int(11),
in _cambioDevuelto int(11),
in _subtotal float,
in _impuestos float,
in _descuentos float,
in _total float,
in _efectuado float
)
begin
	insert into ventasencabezado(idCliente, idTienda, idCajero, terminalVentas, efectivoRecibido, cambioDevuelto, subtotal, impuestos, descuentos, total, efectuado) values(_idCliente, _idTienda, _idCajero, _terminalVentas, _efectivoRecibido, _cambioDevuelto, _subtotal, _impuestos, _descuentos, _total, _efectuado    );

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `AumentarInventario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `AumentarInventario`(
in _cantidad float, 
in _idproducto int, 
in _idtienda int
)
BEGIN
	UPDATE inventarioproductos
    SET inventario = inventario + _cantidad
    WHERE idproducto=_idproducto AND idtienda=_idtienda;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EliminarCiudad` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `EliminarCiudad`(in id int(11))
begin
		delete from ciudades where idciudad = id;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EliminarCliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `EliminarCliente`(
in id int(11)
)
begin
	delete from clientes where idcliente = id;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EliminarComprasDetalle` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `EliminarComprasDetalle`(
in idcomp int(11),
in idprod int(11)
)
begin
	delete from comprasdetalle where idcompra = idcomp and idproducto = idprod;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EliminarComprasEncabezado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `EliminarComprasEncabezado`(
in id int(11)
)
begin

delete from comprasencabezado where idcompra = id;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EliminarDevolucionesComprasDetalle` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `EliminarDevolucionesComprasDetalle`(
in _iddevolucioncompra int(11),
in _idProducto int(11)
)
begin
	delete from devolucionescomprasdetalle  where iddevolucioncompra = _iddevolucioncompra and idproducto = _idproducto; 
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EliminarDevolucionesComprasEncabezado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `EliminarDevolucionesComprasEncabezado`(
in _idDevolucionCompra int(11)
)
begin
	delete from devolucionescomprasencabezado  where iddevolucioncompra = _iddevolucioncompra;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EliminarDevolucionesVentasDetalle` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `EliminarDevolucionesVentasDetalle`(
in _idDevolucioneCompra int(11),
in _idProducto int(11)
)
begin
	delete from devolucionesventasdetalle where iddevolucioncompra = _iddevolucioncompra and idproducto = _idproducto;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EliminarDevolucionesVentasEncabezado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `EliminarDevolucionesVentasEncabezado`(
in _idDevolucionVenta int(11)
)
begin
	delete from devolucionesventasencabezado where iddevolucionventa = _idDevolucionVenta;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EliminarInventarioProductos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `EliminarInventarioProductos`(
in _idTienda int(11),
in _idProducto int(11),
in _inventario float
)
begin 
	delete from inventarioproductos where idproducto = _idProducto and idtienda = _idTienda;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EliminarNotasCredito` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `EliminarNotasCredito`(
in _idNotaCredito int(11)
)
begin
delete from notascredito  where idnotacredito = _idNotaCredito; 

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EliminarNotasDebito` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `EliminarNotasDebito`(
in _idNotaDebito int(11)
)
begin
delete from notasdebito where idnotadebito = _idNotaDebito;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EliminarPais` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `EliminarPais`(
in _idPais int(11)
)
begin 
	delete from paises  where idpais = _idPais;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EliminarProducto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `EliminarProducto`(
in _idProducto int(11)
)
begin 
	delete from productos where idproducto = _idProducto;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EliminarProvincia` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `EliminarProvincia`(
in _idProvincia int(11)
)
begin
	delete from provincias where idprovincia = _idProvincia;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EliminarSuplidor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `EliminarSuplidor`(
in _idSuplidor int(11)
)
begin 
	delete from suplidores where idsuplidor = _idSuplidor;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EliminarTienda` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `EliminarTienda`(
	in _idTienda int(11)
)
begin
	delete from tiendas where idtienda = _idTIenda;
	
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EliminarUsuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `EliminarUsuario`(
in _idUsuario int(11)
)
begin 
	delete from usuarios where idusuario = _idUsuario;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EliminarVentasDetalle` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `EliminarVentasDetalle`(
in _idVenta int(11),
in _idProducto int(11)

)
begin
	delete from ventasdetalle   where idventa = _idVenta and idproducto = _idProducto;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `EliminarVentasEncabezado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `EliminarVentasEncabezado`(
in _idventa int(11)
)
begin 
	delete from ventasencabezado where idventa = _idventa;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ModificarCiudad` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarCiudad`(in id int(11), in Idprov int(11), in Nom varchar(100))
begin
		update ciudades set idprovincia = Idprov, nombre = nom where idciudad = id;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ModificarCliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarCliente`(
 in id int(11),
 in nom varchar(100),
 in ape varchar(100),
 in dir varchar(200),
 in tel varchar(15), 
 in cel varchar(15),
 in ident varchar(15),
 in sex bit(1),
 in tasadesc float
 )
begin
	update clientes set nombre = nom, apellido = ape,  direccion = dir, telefono = tel, celular = cel, identificacion = ident, sexo = sex, tasaDescuento = tasadesc
	where idcliente = id;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ModificarComprasDetalle` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarComprasDetalle`(
in idcomp int(11),
in idprod int(11),
in _valor float,
in imp float,
in subt float,
in cant float
)
begin
	update comprasdetalle set valor = _valor, impuestos = imp, subtotal = subt, cantidad = cant where idproducto = idprod and idcompra = idcomp;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ModificarComprasEncabezado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarComprasEncabezado`(
in id int(11),
in idsupl int(11),
in idtie int(11),
in idsuperv int(11),
in subt float,
in imp float,
in descuent float,
in tot float,
in _efectuado float
)
begin
	update comprasencabezado set idsuplidor = idsupl, idtienda = idtie, idsupervisor = idsuperv, subtotal = subt, impuestos = imp, descuentos = descuent, total = tot, efectuado = _efectuado;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ModificarDevolucionesComprasDetalle` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarDevolucionesComprasDetalle`(
in _iddevolucioncompra int(11),
in _idProducto int(11),
in _subTotal float
)
begin
	update devolucionescomprasdetalle set subtotal = _subtotal where iddevolucioncompra = _iddevolucioncompra and idproducto = _idproducto; 
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ModificarDevolucionesComprasEncabezado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarDevolucionesComprasEncabezado`(
in _idDevolucionCompra int(11),
in _idCompra int(11),
in _idSupervisor int(11),
in _idNotaDebito int(11)
)
begin
	update devolucionescomprasencabezado set idcompra = _idCompra, idsupervisor = _idSupervisor, idnotadebito = _idNotaDebito where iddevolucioncompra = _iddevolucioncompra;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ModificarDevolucionesVentasDetalle` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarDevolucionesVentasDetalle`(
in _idDevolucioneCompra int(11),
in _idProducto int(11),
in _subTotal float
)
begin
	update devolucionesventasdetalle set subtotal = _subtotal where iddevolucioncompra = _iddevolucioncompra and idproducto = _idproducto;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ModificarDevolucionesVentasEncabezado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarDevolucionesVentasEncabezado`(
in idDevolucionVenta int(11),
in _idVenta int(11),
in _idSupervisor int(11),
in _idNotaCredito int(11)
)
begin
	update devolucionesventasencabezado set idventa = _idVenta, idsupervisor = _idSupervisor, idnotacredito =  _idNotaCredito where iddevolucionventa = _idDevolucionVenta;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ModificarInventarioProductos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarInventarioProductos`(
in _idTienda int(11),
in _idProducto int(11),
in _inventario float
)
begin 
	update inventarioproductos set inventario = _inventario where idproducto = _idProducto and idtienda = _idTienda;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ModificarNotasCredito` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarNotasCredito`(
in _idNotaCredito int(11),
in _total float,
in _efectuado float,
in _idcliente int(11),
in _concepto varchar(100)
)
begin
update notascredito set total = _total, efectuado = _efectuado, idcliente = _idcliente, concepto = _concepto where idnotacredito = _idNotaCredito; 

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ModificarNotasDebito` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarNotasDebito`(
in _idNotaDebito int(11),
in _total float,
in _efectuado float,
in _idSuplidor int(11),
in _concepto varchar(100)
)
begin
update notasDebito set total = _total, efectuado = _efectuado, idsuplidor = _idSuplidor, concepto = _concepto where idnotadebito = _idNotaDebito;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ModificarPais` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarPais`(
in _idPais int(11),
in _Nombre varchar(100)
)
begin 
	update paises set nombre = _Nombre where idpais = _idPais;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ModificarProducto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarProducto`(
in _idProducto int(11),
in _codigo varchar(10),
in _descripcion varchar(100),
in _costo float,
in _precio float,
in _tasaImpuesto float
)
begin
	update productos set  codigo = _codigo, descripcion = _descripcion, costo = _costo, precio = _precio, tasaImpuesto = _tasaImpuesto where idproducto = _idProducto;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ModificarProvincia` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarProvincia`(
in _idProvincia int(11),
in _idpais int(11),
in _nombre varchar(100)
)
begin
	update provincias set  idpais = _idpais, nombre = _nombre where idProvincia = _idProvincia;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ModificarSuplidor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarSuplidor`(
in _idSuplidor int(11),
in _nombre varchar(100),
in _apellido varchar(100),
in _direccion varchar(200),
in _telefono varchar(20),
in _celular varchar(20),
in _sexo bit(1),
in _empresa varchar(100),
in _rnc varchar(45)
)
begin 
	update suplidores set nombre = _nombre, apellido = _apellido, direccion = _direccion, telefono = _telefono, celular = _celular, sexo = _sexo, empresa = _empresa, rnc = _rnc where idsuplidor = _idSuplidor ;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ModificarTienda` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarTienda`(
in _idTienda int(11),
in _nombre varchar(100),
in _direccion varchar(200),
in _slogan varchar(150),
in _idciudad int(11)
)
begin
	update tiendas set nombre = _nombre, direccion = _direccion, slogan = _slogan, idciudad =_idciudad where idtienda = _idTienda;   
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ModificarUsuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarUsuario`(
in _idUsuario int(11),
in _nombreUsuario varchar(20),
in _hashClave varchar(256),
in _salesClave varchar(45),
in _nombreCompleto varchar(100),
in _tipo tinyint(4),
in _idTienda int(11)
)
begin 
	update usuarios set nombreusuario = _nombreUsuario, hashClave = _hashClave, salesClave = _salesClave, nombreCompleto = _nombreCompleto, tipo = _tipo, idTienda = _idTienda  where idusuario = _idUsuario;	
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ModificarVentasDetalle` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarVentasDetalle`(
in _idVenta int(11),
in _idProducto int(11),
in  _valor float,
in _impuestos float,
in _subtotal float
)
begin
	update ventasdetalle set  valor = _valor, impuestos = _impuestos, subtotal =  _subtotal  where idventa = _idVenta and idproducto = _idProducto;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ModificarVentasEncabezado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ModificarVentasEncabezado`(
in _idVenta int(11),
in _idCliente int(11),
in _idTienda int(11),
in _idCajero int(11),
in _terminalVentas int(11),
in _efectivoRecibido int(11),
in _cambioDevuelto int(11),
in _subtotal float,
in _impuestos float,
in _descuentos float,
in _total float,
in _efectuado float
)
begin
	update ventasencabezado set idcliente = _idCliente, idtienda = _idTienda, idcajero = _idCajero, terminalVentas = _terminalVentas, efectivoRecibido = _efectivoRecibido, cambioDevuelto = _cambioDevuelto, subtotal = _subtotal, impuestos = _impuestos, descuentos = _descuentos, total = _total, efectuado = _efectuado  where idventa = _idVenta ;

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ReducirInventario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ReducirInventario`(
in _cantidad float, 
in _idproducto int, 
in _idtienda int
)
BEGIN
	UPDATE inventarioproductos
    SET inventario = IF((inventario - _cantidad) >= 0, (inventario - _cantidad), 0)
    WHERE idproducto=_idproducto AND idtienda=_idtienda;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `viewcompras`
--

/*!50001 DROP VIEW IF EXISTS `viewcompras`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `viewcompras` AS select concat(`c`.`idcompra`,'       ',`p`.`descripcion`,'                	',`c`.`valor`,'                		',`c`.`impuestos`,'               		 ',`c`.`cantidad`,'                    ',`c`.`subtotal`) AS `descripcion` from (`comprasdetalle` `c` join `productos` `p`) where (`c`.`idproducto` = `p`.`idproducto`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `viewventas`
--

/*!50001 DROP VIEW IF EXISTS `viewventas`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `viewventas` AS select concat(`v`.`idventa`,'       ',`p`.`descripcion`,'                	',`v`.`valor`,'                		',`v`.`impuestos`,'               		 ',`v`.`cantidad`,'                    ',`v`.`subtotal`) AS `descripcion` from (`ventasdetalle` `v` join `productos` `p`) where (`v`.`idproducto` = `p`.`idproducto`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-26 21:03:06

-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.15


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema curso
--

CREATE DATABASE IF NOT EXISTS curso;
USE curso;

--
-- Definition of table `departamentos`
--

DROP TABLE IF EXISTS `departamentos`;
CREATE TABLE `departamentos` (
  `numero` int(10) unsigned NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `ciudad` varchar(50) NOT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `departamentos`
--

/*!40000 ALTER TABLE `departamentos` DISABLE KEYS */;
INSERT INTO `departamentos` (`numero`,`nombre`,`ciudad`) VALUES 
 (10,'Contabilidad','Málaga'),
 (20,'Desarrollo','Madrid'),
 (30,'Ventas','Sevilla'),
 (40,'Operaciones','Barcelona');
/*!40000 ALTER TABLE `departamentos` ENABLE KEYS */;


--
-- Definition of table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
CREATE TABLE `empleados` (
  `numero` int(10) unsigned NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `puesto` varchar(50) NOT NULL,
  `jefe` int(10) unsigned DEFAULT NULL,
  `fechaAlta` datetime NOT NULL,
  `salario` int(10) unsigned NOT NULL,
  `comision` int(10) unsigned DEFAULT NULL,
  `dnumero` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`numero`),
  KEY `FK_empleados_jefe` (`jefe`),
  KEY `FK_empleados_departamentos` (`dnumero`),
  CONSTRAINT `FK_empleados_departamentos` FOREIGN KEY (`dnumero`) REFERENCES `departamentos` (`numero`),
  CONSTRAINT `FK_empleados_jefe` FOREIGN KEY (`jefe`) REFERENCES `empleados` (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `empleados`
--

/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` (`numero`,`nombre`,`puesto`,`jefe`,`fechaAlta`,`salario`,`comision`,`dnumero`) VALUES 
 (7934,'Muñoz','Administrativo',8082,'1982-02-23 00:00:00',1300,NULL,10),
 (8002,'Forte','Analista',8066,'1981-03-21 00:00:00',3000,NULL,20),
 (8021,'Santos','Vendedor',8098,'1984-01-15 00:00:00',1250,500,30),
 (8039,'Reyes','Presidente',NULL,'1979-11-01 00:00:00',5000,NULL,10),
 (8066,'Jiménez','Director',8039,'1981-12-14 00:00:00',2975,NULL,20),
 (8069,'Pérez','Administrativo',8002,'1980-12-23 00:00:00',800,NULL,20),
 (8082,'Gutiérrez','Director',8039,'1981-12-01 00:00:00',2450,NULL,10),
 (8098,'Corral','Director',8039,'1980-05-08 00:00:00',2800,NULL,30),
 (8099,'Sancho','Vendedor',8098,'1983-06-08 00:00:00',1600,300,30);
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

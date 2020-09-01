GRANT ALL PRIVILEGES ON *.* TO 'garellano'@'%' IDENTIFIED BY 'garellano' with GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'garellano'@'localhost' IDENTIFIED BY 'garellano' with GRANT OPTION;
DROP DATABASE IF EXISTS plank;
CREATE DATABASE plank;
USE plank;

-- MariaDB dump 10.17  Distrib 10.4.13-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: plank
-- ------------------------------------------------------
-- Server version   10.4.13-MariaDB-1:10.4.13+maria~bionic

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
-- Table structure for table registro
--

DROP TABLE IF EXISTS registro;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE registro (
  correo varchar(128) NOT NULL,
  nombre varchar(64) NOT NULL,
  telefono varchar(32) DEFAULT NULL,
  direccion varchar(256) DEFAULT NULL,
  fecha_nacimiento date DEFAULT NULL,
  clave_hash varchar(128) NOT NULL,
  random_string varchar(16) NOT NULL,
  fecha_registro bigint(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (correo),
  UNIQUE KEY idx_registro_random_string (random_string)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table registro
--

LOCK TABLES registro WRITE;
/*!40000 ALTER TABLE registro DISABLE KEYS */;
/*!40000 ALTER TABLE registro ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table rol
--

DROP TABLE IF EXISTS rol;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE rol (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(128) NOT NULL,
  description varchar(128) NOT NULL,
  active tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table rol
--

LOCK TABLES rol WRITE;
/*!40000 ALTER TABLE rol DISABLE KEYS */;
INSERT INTO rol VALUES (1,'admin','primero',1),(2,'user','segundo',1),(3,'reporter','tercero',1);
/*!40000 ALTER TABLE rol ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table usuario
--

DROP TABLE IF EXISTS usuario;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE usuario (
  id int(11) NOT NULL AUTO_INCREMENT,
  mail varchar(128) NOT NULL,
  pass varchar(128) NOT NULL DEFAULT 'temp',
  created bigint(20) NOT NULL DEFAULT 0,
  active tinyint(1) NOT NULL DEFAULT 0,
  wrong_access_count int(11) NOT NULL DEFAULT 0,
  blocked_time bigint(20) NOT NULL DEFAULT 0,
  request_key varchar(128) NOT NULL DEFAULT 'temp',
  request_type int(11) NOT NULL DEFAULT 0,
  request_time mediumtext NOT NULL DEFAULT 0,
  request_completed tinyint(1) NOT NULL DEFAULT 0,
  last_access_time bigint(20) NOT NULL DEFAULT 0,
  last_password_update_time bigint(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (id),
  UNIQUE KEY idx_usuario_mail (mail)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table usuario
--

LOCK TABLES usuario WRITE;
/*!40000 ALTER TABLE usuario DISABLE KEYS */;
INSERT INTO usuario VALUES 
(1,'gus1@aol.com','temp',0,1,8,1597967094149,'temp',0,'0',0,0,0),
(2,'gus2@aol.com','790c90d92b1fe1a8b96843207791779cc886f2f7f5985e5fde263566531f6871',0,1,0,0,'temp',0,'0',0,1597967920683,0),
(3,'gus3@aol.com','temp',0,1,0,0,'temp',0,'0',0,0,0),
(25,'arellano.gustavo@gmail.com','8736ac646a05a76eff0bc42ecd466aa908c985640a7201ba6d3d8a2de121cc4d',1597977435925,1,0,0,'',1,'0',1,1597977538764,0);
/*!40000 ALTER TABLE usuario ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table usuario_detalles
--

DROP TABLE IF EXISTS usuario_detalles;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE usuario_detalles (
  id_usuario int(11) NOT NULL,
  nombre varchar(64) NOT NULL,
  telefono varchar(32) DEFAULT NULL,
  direccion varchar(128) DEFAULT NULL,
  fecha_nacimiento date DEFAULT NULL,
  PRIMARY KEY (id_usuario),
  CONSTRAINT usuario_detalles_ibfk_1 FOREIGN KEY (id_usuario) REFERENCES usuario (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table usuario_detalles
--

LOCK TABLES usuario_detalles WRITE;
/*!40000 ALTER TABLE usuario_detalles DISABLE KEYS */;
INSERT INTO usuario_detalles VALUES (25,'Gustavo Arellano Sandoval','+52 (55) 1691 3070','Kopoma 395','2020-08-20');
/*!40000 ALTER TABLE usuario_detalles ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table usuario_rol
--

DROP TABLE IF EXISTS usuario_rol;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE usuario_rol (
  id_usuario int(11) NOT NULL,
  id_rol int(11) NOT NULL,
  PRIMARY KEY (id_usuario,id_rol),
  KEY id_rol (id_rol),
  CONSTRAINT usuario_rol_ibfk_1 FOREIGN KEY (id_usuario) REFERENCES usuario (id),
  CONSTRAINT usuario_rol_ibfk_2 FOREIGN KEY (id_rol) REFERENCES rol (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table usuario_rol
--

drop view vw_usuario_all;
create view vw_usuario_all as (
 select 
    usuario.id,
    usuario.mail, 
    usuario_detalles.nombre, 
    usuario_detalles.telefono, 
    usuario_detalles.direccion, 
    usuario_detalles.fecha_nacimiento  
 from 
    usuario left join usuario_detalles  
 on  
    usuario.id=usuario_detalles.id_usuario
 ); 


LOCK TABLES usuario_rol WRITE;
/*!40000 ALTER TABLE usuario_rol DISABLE KEYS */;
INSERT INTO usuario_rol VALUES (1,1),(1,2),(1,3),(2,2),(2,3),(3,1),(3,3),(25,2);
/*!40000 ALTER TABLE usuario_rol ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-21  2:58:16

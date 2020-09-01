GRANT ALL PRIVILEGES ON *.* TO 'garellano'@'%' IDENTIFIED BY 'garellano' with GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'garellano'@'localhost' IDENTIFIED BY 'garellano' with GRANT OPTION;
DROP DATABASE IF EXISTS plank;
CREATE DATABASE plank;
USE plank;

-- MariaDB dump 10.17  Distrib 10.4.13-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: plank
-- ------------------------------------------------------
-- Server version	10.4.13-MariaDB-1:10.4.13+maria~bionic

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
-- Table structure for table `registro`
--

DROP TABLE IF EXISTS `registro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registro` (
  `correo` varchar(128) NOT NULL,
  `nombre` varchar(64) NOT NULL,
  `telefono` varchar(32) DEFAULT NULL,
  `direccion` varchar(256) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `clave_hash` varchar(128) NOT NULL,
  `random_string` varchar(16) NOT NULL,
  `fecha_registro` bigint(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`correo`),
  UNIQUE KEY `idx_registro_random_string` (`random_string`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registro`
--

LOCK TABLES `registro` WRITE;
/*!40000 ALTER TABLE `registro` DISABLE KEYS */;
/*!40000 ALTER TABLE `registro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `description` varchar(128) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'admin','primero',1),(2,'user','segundo',1),(3,'reporter','tercero',1);
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mail` varchar(128) NOT NULL,
  `pass` varchar(128) NOT NULL DEFAULT 'temp',
  `created` bigint(20) NOT NULL DEFAULT 0,
  `active` tinyint(1) NOT NULL DEFAULT 0,
  `wrong_access_count` int(11) NOT NULL DEFAULT 0,
  `blocked_time` bigint(20) NOT NULL DEFAULT 0,
  `request_key` varchar(128) NOT NULL DEFAULT 'temp',
  `request_type` int(11) NOT NULL DEFAULT 0,
  `request_time` mediumtext NOT NULL DEFAULT 0,
  `request_completed` tinyint(1) NOT NULL DEFAULT 0,
  `last_access_time` bigint(20) NOT NULL DEFAULT 0,
  `last_password_update_time` bigint(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_usuario_mail` (`mail`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES 
(1,'gus1@aol.com','temp',0,1,8,1597967094149,'temp',0,'0',0,0,0),
(2,'gus2@aol.com','790c90d92b1fe1a8b96843207791779cc886f2f7f5985e5fde263566531f6871',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),
(3,'gus3@aol.com','temp',0,1,0,0,'temp',0,'0',0,0,0),
(25,'arellano.gustavo@gmail.com','8736ac646a05a76eff0bc42ecd466aa908c985640a7201ba6d3d8a2de121cc4d',1597977435925,1,0,0,'',1,'0',1,1597977538764,0),
(26,'gus100@aol.com','c3bfc751078d04227aefee002d241dcf0c074aa33931c8bdab675cbf668467e6',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),
(27,'gus101@aol.com','77b79b880d6bca4b16a83584e0589447f78d683664eaeac0712eeefb43f93bc1',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),
(28,'gus102@aol.com','c67edcca5e8eef04a26edb3f2d237ce9b206dc78ccb90c4c9f2181bc2104b79b',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),
(29,'gus103@aol.com','74d5f906a2b72a8d1a955c97cf3b655ab66294b7eead02645a19bdf12992b191',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),
(30,'gus104@aol.com','66c42a5f6eaf4689ff4fccd5661af7a6c1af4d77a8ce8ea30848dbacdc9f264d',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),
(31,'gus105@aol.com','f74013b6d330124912a60b4e831e5ad015f606eb631b739d1d5c489fba1271e1',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),
(32,'gus106@aol.com','febb04f83f7976a50f26abaf19662c4a3f048ff97ca67ff057623f4fbf176e88',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),
(33,'gus107@aol.com','5c1d8b06791b10c5bf153c7444ecce4d6a254bf94994871324bd1367889a37cf',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),
(34,'gus108@aol.com','df81ecd33dbc221e53a9ff6b88b20cb63a7dc183b93f97223d45179d307503f7',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),
(35,'gus109@aol.com','596f08b250bc9434e9ae88da1beab7b9f7e03fcadc72fb6aea25eb136a40b777',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(36,'gus110@aol.com','c36d8ff33372a51ba4433e4fba2e7a3ec5debbcb8e78e753a51a2c3109dbc3e6',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(37,'gus111@aol.com','12f0047377eab60984d19a4d9712a16b932a0d2635a1a49f950dd16b2db6acb5',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(38,'gus112@aol.com','12d9e571c0b9194fca4487e88bb8df7dc8647d56bf8b1686083f048fb825d9ce',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(39,'gus113@aol.com','bf420e037b1e013c09ebf1a9c8c8127a2abd8d75e22f8741913187b27c57784d',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(40,'gus114@aol.com','d06bc2a13368d649d68f271ffac1ff3713383fb0619748510861c1395afd498b',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(41,'gus115@aol.com','e70296cff21607b8d6fce4efd5a4e47202671a672f3690698d5d6f5083a20211',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(42,'gus116@aol.com','e1ea39a0c2088548633be4eedd4c30196517646403224690c16ab308ac821f38',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(43,'gus117@aol.com','6a9794ed422a020a7a1e4e5010558c4ef2e702fffff325df25b7cedded322ce3',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(44,'gus118@aol.com','e5cecc0ad2c8de6786f56fd617da422e4e02894423abd905378bf0c6c97106e8',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(45,'gus119@aol.com','d8a921fc158112f32aca61ba3c1a565587e0fcab2fe7a760ecf19ac4b9926753',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(46,'gus120@aol.com','187137a53017ebe2eb9c494c6c70c7012bf95e11c0a82b136b44193d6716b395',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(47,'gus121@aol.com','d6b4fd09fa71ab620416f192eef84c4c231edfb28a9dea0a88b327e321728d8f',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(48,'gus122@aol.com','dd900ae1533ce689770b1eea98ab6f7aded09681cfd0517d2371ca2706b1baaf',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(49,'gus123@aol.com','cd2d5b38ce31dd74a59ff25f25b0e3062468ff14284aafe4b5a1c3390ba30d19',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(50,'gus124@aol.com','9ebe66734f8d7e86e8919a0d7222bb21316497e60d5034984050b423a8be7a01',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(51,'gus125@aol.com','569347b0134086672857a9dd76e8a65e626929a82d242c9bab64bb6c9398a903',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(52,'gus126@aol.com','cc666181443b19e881215cd62475bf125436dafb92d0ec8d9cd8c8325b3ea52b',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(53,'gus127@aol.com','fcf6455fbeb5906a2f6aeac477b80ed9135f6d088ebe0f64a639207c7913a427',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(54,'gus128@aol.com','24a3e99f59fc610b7928a029bed47674d5dd3771b17e313db1a9777043e21f34',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(55,'gus129@aol.com','8596bf1f612bc2e5c7b1e409078cf9a2ba373a37c485eed11054e6366ca9b0c7',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(56,'gus130@aol.com','1e13ebeef3829a792b86117aa471084d4fdc82b041f914f54fdb0ccabf5d1142',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(57,'gus131@aol.com','31f4f6b9ed1445ec5557d786298875049163f16a3982e2a08a67bdbaae869cde',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(58,'gus132@aol.com','fbca001edcad5b5a93c45ceaa620965dfcb86860ae39c707ac6429525d068138',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(59,'gus133@aol.com','ae071d4da603a6dd30921d136850f73c586dcefbef09db6fd0b930315516c8db',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(60,'gus134@aol.com','c345d5546f79f3f490dd4b7f27b6dedb88ce98d8fb10ef0085bd7c704536a6',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(61,'gus135@aol.com','bde8e29850b1eece18a39250ebd859baa9e782be23ca9e8ce0b4cd604b72484',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(62,'gus136@aol.com','9cdabc53001cfd1c6a10fe4cc4ff907f28b3af1611b64a719cc968d1d75d30de',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(63,'gus137@aol.com','6f53441ca3faaa9f25a5fd8e432e2763896c441765756cdf3a636b4c712bc75c',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(64,'gus138@aol.com','e99777e905f91e5e38a2d6354c4e43bcbd5fe4f83ac1ba9f3cd19b9f732a4fb3',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(65,'gus139@aol.com','885f17b9c8de638007767df82def236188aa09d9f3118812f57b2f613387614d',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(66,'gus140@aol.com','cb3af3ae2c39df9f7ca5d02127e80ddf06ef82c25dca61ffa5b5c040af95266',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(67,'gus141@aol.com','29a8462960b81ef473d60bccb4a5dc9c699dcb212cf09fb3e34f4d8cd66574e6',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(68,'gus142@aol.com','cb29657b85cad4ae54f29a2d8f748aa32ccfd9c552510e51ac1c2e9c2c32078b',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(69,'gus143@aol.com','73a7352c246484e3b7e7734298d7a00c696867176fb9a2366e8039635c4b24d3',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(70,'gus144@aol.com','170e996fb031c66d05bd48519d3626aa7f03b280c953c8164b1d3c4fdfa5a665',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(71,'gus145@aol.com','6520c6fcc74f2ce4d967af0f73d78519410e385c88e354c4d4b39eb15ebef06c',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(72,'gus146@aol.com','b6dd525124afc1407ee2ff3bb7bd7ff9868720dc2995274e3fa6b004641686aa',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(73,'gus147@aol.com','ead24a26cabf2a2dd4bb546d2b6a7fadee95ae3433aefcac11a868484f769be5',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(74,'gus148@aol.com','3ce41cd6dbf3d2d95e3e00b4d8c66c12507107d3dd506807c42ad20fbb242e96',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(75,'gus149@aol.com','ba521137ad8da945ce9a3c8ff1ea66874ed42ebeaac980f2150d5f0b73a8944f',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(76,'gus150@aol.com','8af52c00c4e4bf531ed455dc2dd6d380482729274281464a40529ce864b7734a',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(77,'gus151@aol.com','43bacfacbfe894b994da1b9fe75c1a11de6bfc6451c282a7050fe7c1ad0d4ffc',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(78,'gus152@aol.com','ca962ab2ee48b6cb1e41930c58548c72398986923719da31eceb327f96d1d987',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(79,'gus153@aol.com','bc87d9d865a77fccd0c5c39e0963b1aa6188d619b9a462c75bea0f08ac15c921',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(80,'gus154@aol.com','dd5ec495982a6a9301dc84cfe1afd226f6f20cee7bef3b148c2b48e21c08380b',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(81,'gus155@aol.com','5d3e69090bff635d38af248eb80b6b277c71e2c5da9d88b2a33cd3864cf2433f',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(82,'gus156@aol.com','83dbb0b52f1e1883ade380897dcbce51a66871c6220b85cd4e4f26d1070c799d',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(83,'gus157@aol.com','d4bb1bf86d0672077a141ba73ceb300c460fcd5f5ee61a02c4faaa0a64095536',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(84,'gus158@aol.com','317e8eea21401f369c9d9a2c9dabf6d3b9cacb391eaf69c5fb14192170d3796c',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(85,'gus159@aol.com','752b6b68a929136d93ca5ae252e0551696cfd73700a7c623f0e07d427ebae74a',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(86,'gus160@aol.com','a1a5a0cd4e6560f776e6a0d0571469b1bcef2e44b8e0d31c1e5786c67c65c9e3',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(87,'gus161@aol.com','a506e5097fbac12fa8ce3c1fa6e8d771283aa5cdd980a99dee085d30f8d27806',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(88,'gus162@aol.com','ce232f3b67d0b6f50acca008d2d6b983c40904f621fa25dec0b012fbdcba242f',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(89,'gus163@aol.com','5a08e1387aa55292422a7e00b717b5d8fb1031daa19b39452747aab524963b5c',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(90,'gus164@aol.com','6b9933a5e21b2ea31fb4831afc73aa176151365e28bc4d1032de3db4569914dd',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(91,'gus165@aol.com','d1f34a9e75a36ea9d5f83502b803dbc30b986446a46d434293aca90a019bac0e',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(92,'gus166@aol.com','90d5c211c6a6ff48e40ace2e717428d099f7cf8f58e2a75eeea04a5ed74062f1',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(93,'gus167@aol.com','c3daf3f1fff6e56da4e16fc53f473d77fed28c5c93a4ea079f9f1dd11b81872e',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(94,'gus168@aol.com','e460fb640628f127d86685ce6a1038ffed4f1131f0771b4ff79f149197d059d2',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(95,'gus169@aol.com','46cd21427e5646e0cfb932782c87173542bd31e62b9052de4563b32a00469f2b',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(96,'gus170@aol.com','d8aa4117a84db58b3f5ac2df27aab610a233ce4c3b5e40226f083b2bc7763fc5',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(97,'gus171@aol.com','569b0939ce244c74a8e526589924a7cdc06c4b11b512a4aa995946ce0c8e70b7',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(98,'gus172@aol.com','f4b8f039bdbcc4d4a3e25d0e77883a25f9dbb1c5524e155bb7a93282b282087c',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(99,'gus173@aol.com','a53da78a353cb2a9b5166c1318d17cefa9fa4ed28dd173f578214e9d65da8428',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(100,'gus174@aol.com','44b7f7f09d2e139c1aa59a63c190259542fe6935607282cf1d6818372a918d1b',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(101,'gus175@aol.com','e90b44bc5b414f74ea08bbd3ff550aa3ae6065566cc865c688bbf66970d37a16',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(102,'gus176@aol.com','526b1843943c435b7aa301ad785ddec57aa40261a51f0bf731bd4e53345142c5',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(103,'gus177@aol.com','78ceb2958d899a3d2678c23a455f497793d642b0c7b271c9b25eb3549ec62124',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(104,'gus178@aol.com','43aed078585b77daef2b4f9ac72709fae4db5c482b10c13976035d3281f7cf79',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(105,'gus179@aol.com','ffcfe9c784464565a31213399e76ec7448a123a9314c053af2fb2ffec04b2005',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(106,'gus180@aol.com','1ae1847556c79a616c8e139b47dee7c9f579cc6b8de852686b96ebd93fa2f2bc',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(107,'gus181@aol.com','6ce540c70c0dd0a623065e7ccd4376f5dbb65bc6aa0c12067eac33bbab83cd6d',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(108,'gus182@aol.com','64b434c8308dbc9586ede531046c3c811d126408031fb87b263276c3a5158b16',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(109,'gus183@aol.com','8c82b871d285e22dde45f4f421c06f23ee723de2e98ad3c1a301da46b0af4353',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(110,'gus184@aol.com','aec5a26cea03ecc5664738b98af63ade6c38bb175454cb289c55f7e654a1e2c6',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(111,'gus185@aol.com','7b3f88513640f3ba2eb6fdfdff4d36b193b5a5b407288c483259b4355caf0680',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(112,'gus186@aol.com','d1e8aaa8956dcb5aabb8515c2ef908fa33d472815b520496c0837dd1eba76e76',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(113,'gus187@aol.com','d76b918495820946216f6d3e5c1b2914f17fd3dc00b709fffaf07d472756d503',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(114,'gus188@aol.com','cb5102fae6768202d8936273c1b89d2ce718ee14a5f6536cd45eb371a3c802c4',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(115,'gus189@aol.com','c90860f3a50567bb6eca83106cf6de0f0cdc8a37974e3e519b3570d265bccb34',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(116,'gus190@aol.com','ef90cd62f62c2f446c6413c871b3af66c9abe4ed290c20768652a7ce6efbd520',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(117,'gus191@aol.com','161ee3da748bd692c1f780165a7cd50909f9ede60147247bd397c59020e9174f',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(118,'gus192@aol.com','206c7bf5a437c81932e6e5a3c21d0181158652551e7828e2b27045c04372b5bf',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(119,'gus193@aol.com','aa361799bfc4f273e877c8ccb68ce4049bba031fa65a2ec75d2486cb40fe27cf',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(120,'gus194@aol.com','b9432e377920ab601d647451001dec8a77bc2c20c6ea80688492c548f7695e59',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(121,'gus195@aol.com','203b01678ccb8b323496dab5718ab84856fe5354b60fb2af895cfc06bd599161',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(122,'gus196@aol.com','53c4ee8d2744ae6969cf8b1f055cd9477d7a687c1271a4374613ff6b521a5d1e',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(123,'gus197@aol.com','444feae97b6f59d84a883f2ca2012bf997bb5a41f215ad577f208ee53b9c6040',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0),(124,'gus198@aol.com','a029c9b734f5d4127ae9e1608473a54d8a9e5eb4cee5d141d83f1e7eeb27abd1',0,1,0,0,'USJ2Rc',1,'25',0,1598334147863,0);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_detalles`
--

DROP TABLE IF EXISTS `usuario_detalles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_detalles` (
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(64) NOT NULL,
  `telefono` varchar(32) DEFAULT NULL,
  `direccion` varchar(128) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  CONSTRAINT `usuario_detalles_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_detalles`
--

LOCK TABLES `usuario_detalles` WRITE;
/*!40000 ALTER TABLE `usuario_detalles` DISABLE KEYS */;
INSERT INTO `usuario_detalles` VALUES (2,'Gustavo Arellano Sandoval','+52 (55) 1691 3070','Kopoma 395','2020-08-20');
/*!40000 ALTER TABLE `usuario_detalles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_rol`
--

DROP TABLE IF EXISTS `usuario_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_rol` (
  `id_usuario` int(11) NOT NULL,
  `id_rol` int(11) NOT NULL,
  PRIMARY KEY (`id_usuario`,`id_rol`),
  KEY `id_rol` (`id_rol`),
  CONSTRAINT `usuario_rol_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `usuario_rol_ibfk_2` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_rol`
--

LOCK TABLES `usuario_rol` WRITE;
/*!40000 ALTER TABLE `usuario_rol` DISABLE KEYS */;
INSERT INTO `usuario_rol` VALUES (1,1),(1,2),(1,3),(2,2),(2,3),(3,1),(3,3),(25,2);
/*!40000 ALTER TABLE `usuario_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `vw_usuario_all`
--

DROP TABLE IF EXISTS `vw_usuario_all`;
/*!50001 DROP VIEW IF EXISTS `vw_usuario_all`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `vw_usuario_all` (
  `id` tinyint NOT NULL,
  `mail` tinyint NOT NULL,
  `nombre` tinyint NOT NULL,
  `telefono` tinyint NOT NULL,
  `direccion` tinyint NOT NULL,
  `fecha_nacimiento` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `vw_usuario_all`
--

/*!50001 DROP TABLE IF EXISTS `vw_usuario_all`*/;
/*!50001 DROP VIEW IF EXISTS `vw_usuario_all`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = latin1 */;
/*!50001 SET character_set_results     = latin1 */;
/*!50001 SET collation_connection      = latin1_swedish_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`garellano`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vw_usuario_all` AS (select `usuario`.`id` AS `id`,`usuario`.`mail` AS `mail`,`usuario_detalles`.`nombre` AS `nombre`,`usuario_detalles`.`telefono` AS `telefono`,`usuario_detalles`.`direccion` AS `direccion`,`usuario_detalles`.`fecha_nacimiento` AS `fecha_nacimiento` from (`usuario` left join `usuario_detalles` on(`usuario`.`id` = `usuario_detalles`.`id_usuario`))) */;
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

-- Dump completed on 2020-09-01 21:52:50

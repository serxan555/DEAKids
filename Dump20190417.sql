CREATE DATABASE  IF NOT EXISTS `ss.555` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ss.555`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: ss.555
-- ------------------------------------------------------
-- Server version	5.5.37

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
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `groupName` varchar(45) NOT NULL,
  PRIMARY KEY (`id`,`groupName`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES (1,'A'),(2,'B'),(3,'C'),(4,'D'),(5,'E'),(6,'F'),(7,'G');
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sgt`
--

DROP TABLE IF EXISTS `sgt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sgt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `studentId` int(11) DEFAULT NULL,
  `payment` double DEFAULT NULL,
  `groupId` int(11) DEFAULT NULL,
  `lessonDate` varchar(45) DEFAULT NULL,
  `lessonTime` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `student_user_ibfk_1_idx` (`studentId`,`userId`),
  KEY `student_user_ibfk_2_idx` (`userId`),
  KEY `sgt_ibfk_4_idx` (`groupId`),
  CONSTRAINT `sgt_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `users` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sgt_ibfk_3` FOREIGN KEY (`studentId`) REFERENCES `students` (`id`),
  CONSTRAINT `sgt_ibfk_4` FOREIGN KEY (`groupId`) REFERENCES `groups` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sgt`
--

LOCK TABLES `sgt` WRITE;
/*!40000 ALTER TABLE `sgt` DISABLE KEYS */;
INSERT INTO `sgt` VALUES (36,1,2,60,1,'1-6','19:00-19:00','Active'),(54,1,141,70,1,'1-5','19:00-19:00','Passive'),(55,1,142,80,1,'1-6','19:00-19:00','Active'),(57,1,144,135,2,'6-7','13:00-11:00','Active'),(58,1,145,135,2,'6-7','13:00-11:00','Passive'),(59,1,146,88,2,'6-7','13:00-11:00','Passive'),(61,1,148,128,2,'6-7','13:00-11:00','Active'),(62,1,149,64,2,'6-7','13:00-11:00','Active'),(63,1,150,68,2,'6-7','13:00-11:00','Active'),(64,1,151,68,2,'6-7','13:00-11:00','Active'),(65,1,152,100,2,'6-7','13:00-11:00','Passive'),(66,1,153,120,1,'1-5','19:00-19:00','Active'),(67,1,154,60,1,'1-6','19:00-19:00','Active'),(68,1,155,60,1,'1-6','19:00-19:00','Passive'),(69,1,156,135,4,'2-7','17:00-14:00','Active'),(70,1,157,128,4,'2-7','17:00-14:00','Active'),(71,1,158,160,4,'2-7','17:00-14:00','Passive'),(72,1,159,120,4,'2-7','17:00-14:00','Active'),(73,1,160,135,4,'2-7','17:00-14:00','Active'),(74,1,161,160,4,'2-7','17:00-14:00','Active'),(75,1,162,160,4,'2-7','17:00-14:00','Passive'),(76,1,163,112,5,'6-7','16:00','Active'),(77,1,164,160,5,'6-7','16:00','Active'),(78,1,165,100,5,'6-7','16:00','Active'),(79,1,166,100,5,'6-7','16:00','Active'),(80,1,167,128,5,'6-7','16:00','Active'),(81,1,168,160,5,'6-7','16:00','Active'),(82,1,169,112,5,'6-7','16:00','Active'),(83,1,170,96,5,'6-7','16:00','Active'),(84,1,171,160,5,'6-7','16:00','Active'),(85,1,172,125,5,'6-7','16:00','Active'),(86,1,173,125,5,'6-7','16:00','Active'),(87,1,174,160,5,'6-7','16:00','Active'),(88,1,175,100,1,'1-6','19:00-19:00','Active'),(89,1,176,70,3,'5-6','19:00-10:00','Active'),(90,1,177,120,3,'5-6','19:00-10:00','Active'),(91,1,178,60,3,'5-6','19:00-10:00','Active'),(92,1,179,100,3,'5-6','19:00-10:00','Active'),(93,1,180,60,3,'5-6','19:00-10:00','Active'),(94,1,181,120,6,'1-4','16:00','Active'),(95,1,182,112,6,'1-4','16:00','Active'),(96,1,183,128,6,'1-4','16:00','Active'),(97,1,184,84,7,'2-4','19:00','Active'),(98,1,185,60,7,'2-4','19:00','Active'),(99,1,186,100,7,'2-4','19:00','Active'),(100,1,187,120,7,'2-4','19:00','Active'),(101,1,188,102,7,'2-4','19:00','Active');
/*!40000 ALTER TABLE `sgt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `parent` varchar(45) DEFAULT NULL,
  `birthDate` date DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `gmail` varchar(45) DEFAULT NULL,
  `gmailCode` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=189 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (2,'Hüseyn','Hüseynli','Tural','2007-10-23','050-220-86-23','huseyn00h1843@gmail.com','Dea783,.D'),(141,'Əfruz','Mirzəzadə','Əbülfəz','2007-06-17','050-356-68-17','efruz00m2009@gmail.com','Dea783,.D'),(142,'Hüseyn','Nemətli','Gülnarə','2007-04-22','070-550-93-02','huseyn00n2209@gmail.com','Dea783,.D'),(144,'Gülbəniz','Əliyeva','Gülmirə','2008-07-07','051-809-33-99','gulbeniz000e1831@gmail.com','Dea783,.D'),(145,'Cansu','Əliyeva','Mərcanə','2007-12-17','051-809-33-99','cansu00e2671@gmail.com','Dea783,.D'),(146,'Vüqar','Abbasov','Gülmirə','2007-03-25','051-686-53-73','vuqar000a2697@gmail.com','Dea783,.D'),(148,'Fərid','İsmayılzadə','Ramin','2007-01-01','050-213-59-29','ferid.ismayilzade.07@mail.ru',NULL),(149,'Uğur','İbadzadə','Natiqə','2008-07-05','070-681-16-36','ayan.ibadova.07@gmail.com',NULL),(150,'Ayan','İbadzadə','Natiqə','2007-05-18','070-681-16-36','ayan.ibadova.07@gmail.com',NULL),(151,'Adil','İbadov','Natiqə','2002-12-27','070-681-16-36','ayan.ibadova.07@gmail.com',NULL),(152,'Nurdan','Nəsirli','Günel','2009-11-14','055-800-39-15','nurdan00n3297@gmail.com',NULL),(153,'Tərgül','Həsən','Eldəniz-Günay','2007-05-27','051-702-47-49','tergul000h2135@gmail.com','Dea783,.D'),(154,'Cavidan','Cavadov','Kənan','2007-10-29','055-250-04-15','cavidanc2742@gmail.com','developia555'),(155,'Önər','Yusifov','Səma-Tural','2007-09-07','051-750-90-77','onery2711@gmail.com','developia555'),(156,'Zəhranur','Fərəzli','Aynur','2006-01-01','070-381-95-96','zehranur000f@gmail.com','developia555'),(157,'Tofiq','Fərəcli','Aynur','2009-01-01','070-381-95-96','zehranur000f@gmail.com','developia555'),(158,'Ayxan ','Mövsümov',NULL,'2006-01-01',NULL,'movsumovayxan49@gmail.com','developia555'),(159,'Ləman','Məmmədova','Almaz','2005-01-01','055-990-33-25','leman000m@gmail.com','developia555'),(160,'İsmayıl','Məmmədov','Aysel','2010-01-01','077-572-21-96','mismayil2010@gmail.com',NULL),(161,'Vaqif','Heybətzadə','Nüşabə','2004-01-01','055-338-02-20','vaqif000h@gmail.com','developia555'),(162,'Fuad','Abdullazadə','Arzu','2003-01-01','050-254-56-76','fuad000abdul@gmail.com','Avdu77azadeFuad'),(163,'Şahsənəm','Quliyeva','Ayna-Yaşar','2007-10-05','050-722-44-44','sahsenem000q@gmail.com','developia'),(164,'Səmra','Yusibova','Leyla-Eldəniz','2008-12-31','055-832-62-89','semra000y@gmail.com','developia'),(165,'Seyidhəşim','Cabbarlı','Nəzrin-Seyidhəşim','2007-06-10','077-733-51-01','seyidhesim00c@gmail.com','developia'),(166,'Ruslan','Cabbarlı','Nəzrin-Seyidhəşim','2008-12-11','050-731-51-01','seyidhesim00c@gmail.com','developia'),(167,'Kənan','Cəfərli','Anzulə-Xaqani','2007-09-17','070-715-02-71','kenan00c@gmail.com','developia'),(168,'Arif','Babayev','Vefa-Asif','2007-02-22','050-581-70-63','arif00babayev@gmail.com','developia'),(169,'Pərvin','Həsənov','Sədaqət-Perviz','2006-01-07','070-379-91-95','pervin00h@gmail.com','developia'),(170,'Məhəmməd','Kərimli','Qumru-Mübariz','2004-03-24','050-597-17-89','mehemmedkeri.04@gmail.com',NULL),(171,'Xədicə','Yusifli','Samirə-Ramiz','2006-01-01','055-471-10-99','xedice00y@gmail.com','developia'),(172,'Hüseyn','Quliyev','Vuqar','2004-11-11','050-302-22-68','huseyn2268@gmail.com',NULL),(173,'Məsim','Quliyev','Vüqar','2007-05-01','050-302-22-68','mesimq4477@gmail.com',''),(174,'Sənan','Ömərli','Fariz-Nigar','2007-03-10','055-871-01-80','o.senan2007@gmail.com','developia'),(175,'Nəzrin','Cavadova','Kənan','2009-11-11','055-250-04-15','nezrinc2743@gmail.com','developia555'),(176,'Aydın','Mirzəyev','Əbülfəz','2019-01-01','050-356-68-17','aydin00m2126@gmail.com',NULL),(177,'Fərid','Salmanov','Nuriyyə','2019-01-01','055-333-72-03','ferid00s2028@gmail.com',NULL),(178,'Mahmud','Əliyev','Namiq','2019-01-01','055-600-72-29','mahmud00e2220@gmail.com',NULL),(179,'Əli','Məmmədzadə','Elçin','2019-01-01','070-354-17-10','eli00m1936@gmail.com',NULL),(180,'Ünsal','Qasımlı','Mövsüm-Çinarə','2019-01-01','055-202-37-95','Unsal00q2646@gmail.com',NULL),(181,'Üzeyir','Hacılı','Azər','2019-01-01','050-537-94-84','uzeyir00h2974@gmail.com',NULL),(182,'Raul','Süleymanzadə','Aynur','2019-01-01','050-224-04-34','raulsuleymanzada@gmail.com',NULL),(183,'Şahin','Rzazadə','Şahlar','2019-01-01','050-220-96-46','sahinrzazada2019@gmail.com',NULL),(184,'Fərid','Allahverdili','Almaz','2019-01-01','050-731-96-64','feridallahverdili2804@gmail.com',NULL),(185,'Hüseyn','Zöhrablı','Ofelya','2019-01-01','051-870-07-17','huseyn00z3385@gmail.com',NULL),(186,'Süleyman','Məmmədov','Sevda','2019-01-01','055-560-49-26','suleyman00m3443@gmail.com',NULL),(187,'Üzeyir','Bayramlı','Elşad','2019-01-01','050-537-42-95','uzeyir00b3379@gmail.com',NULL),(188,'Əli','Məmmədli','Anar','2019-01-01','050-332-54-58','Eli07m1649@gmail.com',NULL);
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(225) NOT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `user_surname` varchar(45) DEFAULT NULL,
  `user_email` varchar(45) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `password_UNIQUE` (`password`),
  UNIQUE KEY `user_email_UNIQUE` (`user_email`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'serxan555','c3d90dc1f2b4b1091c7855b434b5181f79ee124ea99770f5f8b29666f23cb251','Sərxan','Səmədbəyli','ssmdbyli@gmail.com'),(7,'A','559aead08264d5795d3909718cdd05abd49572e84fe55590eef31a88a08fdffd','A','A','serxan555@mail.ru'),(42,'w','454349e422f05297191ead13e21d3db520e5abef52055e4964b82fb213f593a1','w','w','serxansemedbeyli555@mail.ru'),(47,'t','18ac3e7343f016890c510e93f935261169d9e3f565436429830faf0934f4f8e4','d','d','d'),(48,'elsad555','571dbde51e212d2965747dacef3d4f9f10874766a892be69d2f8a82b9568ac06','Elsad','Kerimov','elsadkerimov@gmail.com'),(50,'elsad','45dc246ad7de6519fd1c3dcd4699df6200f222b3b426e5fe4f36c3d46e241714','Elşad','Kərimov','elsadkerimov100@gmail.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-17 10:26:16

/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 4.1.22-community-nt : Database - trabalhothread
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`trabalhothread` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `trabalhothread`;

/*Table structure for table `clientes` */

DROP TABLE IF EXISTS `clientes`;

CREATE TABLE `clientes` (
  `id` int(11) NOT NULL auto_increment,
  `nome` varchar(60) default NULL,
  `dt_nasc` date default NULL,
  `rg` varchar(15) default NULL,
  `cpf` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `clientes` */

insert  into `clientes`(`id`,`nome`,`dt_nasc`,`rg`,`cpf`) values (1,'lucas medeiros','1996-03-14','2323','57567'),(3,'lucas da silva','1998-09-12','73623','347364');

/*Table structure for table `profissionais` */

DROP TABLE IF EXISTS `profissionais`;

CREATE TABLE `profissionais` (
  `id` int(11) NOT NULL auto_increment,
  `nome` varchar(60) default NULL,
  `dt_nasc` date default NULL,
  `login` varchar(30) default NULL,
  `senha` varchar(64) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `profissionais` */

insert  into `profissionais`(`id`,`nome`,`dt_nasc`,`login`,`senha`) values (5,'teste profissional','2012-12-12','teste','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3'),(6,'mais um prof','2009-03-13','profi','6b51d431df5d7f141cbececcf79edf3dd861c3b4069f0b11661a3eefacbba918'),(7,'profissional','2000-01-01','profissional','a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

/*
SQLyog Community v13.2.0 (64 bit)
MySQL - 10.4.28-MariaDB : Database - sbs_proj
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sbs_proj` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `sbs_proj`;

/*Table structure for table `bmicategory` */

DROP TABLE IF EXISTS `bmicategory`;

CREATE TABLE `bmicategory` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `section` char(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `bmicategory` */

insert  into `bmicategory`(`id`,`section`) values 
(1,'저체중'),
(2,'정상'),
(3,'과체중, 비만'),
(4,'기타');

/*Table structure for table `exercise` */

DROP TABLE IF EXISTS `exercise`;

CREATE TABLE `exercise` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `bmiId` int(10) unsigned NOT NULL,
  `like` int(10) unsigned NOT NULL,
  `name` char(100) NOT NULL,
  `location` char(100) NOT NULL,
  `kind` char(100) NOT NULL,
  `link` char(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `exercise` */

insert  into `exercise`(`id`,`bmiId`,`like`,`name`,`location`,`kind`,`link`) values 
(1,2,22,' 초보자를 위한 가슴,등,복근 운동','헬스장','무산소','https://www.youtube.com/watch?v=iv8reqGucYE'),
(2,3,65,'               슬로우 버피테스트','홈트','유산소','https://www.youtube.com/watch?v=Uly8jUuscOw'),
(3,2,23,'                     초급 유산소','홈트','유산소','https://www.youtube.com/watch?v=OoytN1a8Klc'),
(4,2,24,'               왕초보달리기 루틴','홈트','유산소','https://www.youtube.com/watch?v=rOoYrqdCVc0'),
(5,3,56,'              체지방 태우는 루틴','홈트','유산소','https://www.youtube.com/watch?v=CYcLODSeC-c'),
(6,2,42,'                     홈트 스쿼트','홈트','무산소','https://www.youtube.com/watch?v=KXYi6bI-UPE'),
(7,2,21,'                푸쉬업 자세 설명','홈트','무산소','https://www.youtube.com/watch?v=DMU6bat46SM'),
(8,1,32,'                     푸쉬업 루틴','홈트','무산소','https://www.youtube.com/watch?v=c_5ENJWekbQ'),
(9,1,41,'            초보자를 위한 푸쉬업','홈트','무산소','https://www.youtube.com/watch?v=8oYB62z3sVs'),
(10,2,14,'                       런지 자세','홈트','무산소','https://www.youtube.com/watch?v=oYiBDWhmrX8'),
(11,2,15,'                  홈트 하체 루틴','홈트','무산소','https://www.youtube.com/watch?v=KXYi6bI-UPE'),
(12,2,4,'             홈트 초보 코어 운동','홈트','무산소','https://www.youtube.com/watch?v=C7gPeAgeBAk'),
(13,2,34,'                 헬스장 유산소팁','헬스장','유산소','https://www.youtube.com/watch?v=RDIZLrVVA-Q'),
(14,3,13,'     체지방 태우는 런닝머신 루틴','헬스장','유산소','https://www.youtube.com/watch?v=IJZvF8XITWo'),
(15,3,25,'       살빠지는 실내 자전거 루틴','헬스장','유산소','https://www.youtube.com/watch?v=weOd8r9JHdU'),
(16,2,36,'                 벤치프레스 자세','헬스장','무산소','https://www.youtube.com/watch?v=0DsXTSHo3lU'),
(17,2,43,'           오버 헤드 프레스 자세','헬스장','무산소','https://www.youtube.com/watch?v=EoGMVSORHtM'),
(18,2,52,'                바벨 스쿼트 자세','헬스장','무산소','https://www.youtube.com/watch?v=kz84Fc6HGu4'),
(19,2,62,'                 데드리프트 자세','헬스장','무산소','https://www.youtube.com/watch?v=EBjYQeeBI-0'),
(20,2,71,'                     턱걸이 자세','헬스장','무산소','https://www.youtube.com/watch?v=nWhS28U6bCY'),
(21,2,8,'                   바벨로우 자세','헬스장','무산소','https://www.youtube.com/watch?v=EEqGCoTuYfQ'),
(22,1,9,'       초보자를 위한 벌크업 루틴','헬스장','무산소','https://www.youtube.com/watch?v=KCAwey51gUc'),
(23,2,33,'            기본적인 머신 사용법','헬스장','무산소','https://www.youtube.com/watch?v=ztGFHUsjFQo'),
(24,2,22,'초보자를 위한 어깨,팔, 하체 운동','헬스장','무산소','https://www.youtube.com/watch?v=D7iZMIy0C5E');

/*Table structure for table `food` */

DROP TABLE IF EXISTS `food`;

CREATE TABLE `food` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `bmiId` int(10) unsigned NOT NULL,
  `like` int(10) unsigned NOT NULL,
  `name` char(100) NOT NULL,
  `kal` int(100) NOT NULL,
  `pro` int(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `food` */

insert  into `food`(`id`,`bmiId`,`like`,`name`,`kal`,`pro`) values 
(1,1,4,'  통밀빵',247,13),
(2,1,99,'치즈버거',244,14),
(3,1,5,'    쌀밥',130,3),
(4,2,62,'    사과',52,0),
(5,3,54,'  요거트',58,10),
(6,3,54,'    당근',41,1),
(7,2,67,'  바나나',88,1),
(8,3,22,'  토마토',16,1),
(9,3,54,'    두부',76,8),
(10,2,45,'    우유',42,3),
(11,3,42,'  고구마',85,2),
(12,1,23,'  오트밀',67,2),
(13,3,13,'브로콜리',33,3),
(14,3,42,'블루베리',57,1),
(15,2,23,'닭가슴살',164,31),
(16,2,54,'    참치',131,28),
(17,2,42,'    달걀',155,13),
(18,2,97,'    연어',120,20),
(19,1,12,'  아몬드',575,21);

/*Table structure for table `likeLog` */

DROP TABLE IF EXISTS `likeLog`;

CREATE TABLE `likeLog` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userName` text DEFAULT NULL,
  `likeName` text DEFAULT NULL,
  `likeWhat` text DEFAULT NULL,
  `link` char(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `likeLog` */

insert  into `likeLog`(`id`,`userName`,`likeName`,`likeWhat`,`link`) values 
(1,'admin','벤치프레스 자세','exercise','https://www.youtube.com/watch?v=0DsXTSHo3lU'),
(2,'철수','벤치프레스 자세','exercise','https://www.youtube.com/watch?v=0DsXTSHo3lU'),
(3,'철수','초보자를 위한 벌크업 루틴','exercise','https://www.youtube.com/watch?v=KCAwey51gUc');

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `bmiId` int(10) NOT NULL,
  `loginId` char(100) NOT NULL,
  `loginPw` char(100) NOT NULL,
  `name` char(100) NOT NULL,
  `gender` char(100) NOT NULL,
  `height` int(200) NOT NULL,
  `weight` int(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginId` (`loginId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `member` */

insert  into `member`(`id`,`bmiId`,`loginId`,`loginPw`,`name`,`gender`,`height`,`weight`) values 
(1,2,'admin','admin','admin','여자',160,60),
(2,3,'c','d','철수','남자',170,70);

/*Table structure for table `noticeboard` */

DROP TABLE IF EXISTS `noticeboard`;

CREATE TABLE `noticeboard` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `updateDate` datetime NOT NULL,
  `title` char(100) NOT NULL,
  `detail` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `noticeboard` */

insert  into `noticeboard`(`id`,`updateDate`,`title`,`detail`) values 
(1,'2023-07-27 09:37:24','공지사항 Test1','공지사항 Test용 기본 생성 공지 입니다.'),
(2,'2023-07-27 09:37:24','공지사항 Test2','공지사항 Test용 기본 생성 공지 입니다. 이 내용을 볼 수 있어야 합니다.');

/*Table structure for table `qna` */

DROP TABLE IF EXISTS `qna`;

CREATE TABLE `qna` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userName` text DEFAULT NULL,
  `userQuestionName` text DEFAULT NULL,
  `userQuestionText` text DEFAULT NULL,
  `adminAnswerName` text DEFAULT NULL,
  `adminAnswerText` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `qna` */

insert  into `qna`(`id`,`userName`,`userQuestionName`,`userQuestionText`,`adminAnswerName`,`adminAnswerText`) values 
(1,'철수','QnA Test용 글 입니까?','abc','네. 이 글은 QnA Test입니다.','123');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

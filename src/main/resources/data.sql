LOCK TABLES `hall` WRITE;
INSERT INTO `hall` VALUES
(1,'Large Hall',500,'img/hall_large.jpg','LARGE'),
(2,'Medium Hall',200,'img/hall_medium.jpg','MEDIUM'),
(3,'Small Hall',100,'img/hall_small.jpg','SMALL');
UNLOCK TABLES;

LOCK TABLES `exposition` WRITE;
INSERT INTO `exposition` VALUES
(1,'Italian Renaissance','was a period in Italian history that covered the 15th and 16th centuries,',99.99,'img/italian_renaissance.jpg','2020-02-15','2020-02-22',1),
(2,'Baroque','European paintings from the early 17th century until the 1740s',89.99,'img/baroque.jpg','2020-02-23','2020-03-02',1),
(3,'Dutch Golden Age','Was a period in the history of the Netherlands, roughly spanning the 17th century',79.99,'img/dutch_golden_age.jpg','2020-03-03','2020-03-10',1),
(4,'Impressionism','19th-century art movement characterized by relatively small, yet visible brush strokes',69.99,'img/impressionism.jpg','2020-02-16','2020-02-23',2),
(5,'Post-Impressionism','French art movement that developed roughly between 1886 and 1905',59.99,'img/post_impressionism.jpg','2020-02-24','2020-03-03',2),
(6,'Neo-Impressionism','Art movement founded by Georges Seurat in 1886 in France',49.99,'img/neo_impressionism.jpg','2020-03-04','2020-03-11',2),
(7,'Medieval Art','Covers a vast scope of time and place, over 1000 years of art in Europe',39.99,'img/medieval.jpg','2020-02-17','2020-02-24',3),
(8,'Ancient Roman Art','Refers to the visual arts made in Ancient Rome and in the territories of the Roman Empire',29.99,'img/ancient_rome.jpg','2020-02-25','2020-03-04',3),
(9,'Ancient Greek Art','Ancient Greek art proper emerged during the 8th century BCE',19.99,'img/ancient_greek.jpg','2020-03-05','2020-03-12',3);
UNLOCK TABLES;

LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES (3,'Anton','Shevchenko','123-456-78-90','ssa.ssa.ssa11@gmail.com','2020-01-20 18:38:25',1234567890,'anton','$2a$11$avyZ9vqFQCaEufoT2npVku2uoAX3ibp/37d7H1DHu9460mG04TSMW','CUSTOMER'),
(4,'John','Smith','987-654-32-10','admin@example.com','2020-02-17 22:57:05',1000000000,'admin','$2a$11$bQ5msOfDUFjuFzGCw/A8WOOJEQiUBuRZu5Dscd4lX/QMx0FtyZu1.','ADMIN');
UNLOCK TABLES;

LOCK TABLES `payment` WRITE;
INSERT INTO `payment` VALUES
(2,279.95,'2020-01-22 19:14:33',3),
(3,179.96,'2020-01-22 19:19:21',3),
(4,69.99,'2020-01-22 19:25:47',3),
(5,59.99,'2020-01-22 19:34:49',3),
(6,129.97,'2020-01-28 22:43:27',3),
(7,669.90,'2020-01-29 02:31:01',3),
(8,669.90,'2020-01-29 02:38:55',3),
(9,289.95,'2020-02-01 14:05:06',3),
(10,289.95,'2020-02-01 14:06:46',3),
(11,289.95,'2020-02-01 14:09:20',3),
(12,289.95,'2020-02-01 14:11:11',3),
(13,379.95,'2020-02-07 00:51:11',3),
(15,489.95,'2020-02-07 00:55:21',3),
(16,209.97,'2020-02-12 20:22:22',3),
(17,459.94,'2020-02-12 20:24:06',3),
(18,59.99,'2020-02-12 20:25:21',3),
(19,69.99,'2020-02-12 20:29:17',3),
(20,99.99,'2020-02-12 20:35:25',3),
(21,29.99,'2020-02-12 20:39:21',3),
(22,59.99,'2020-02-12 20:42:32',3),
(23,89.99,'2020-02-12 20:45:46',3),
(24,39.99,'2020-02-12 20:46:44',3),
(25,439.95,'2020-02-16 15:10:35',3),
(26,389.94,'2020-02-17 23:34:29',3),
(27,819.90,'2020-02-17 23:36:14',3);
UNLOCK TABLES;

LOCK TABLES `ticket` WRITE;
INSERT INTO `ticket` VALUES
(2,'2020-02-03',3,3,2),
(3,'2020-01-22',2,7,2),
(4,'2020-01-24',2,2,3),
(5,'2020-01-24',2,5,3),
(6,'2020-02-03',1,6,4),
(7,'2020-01-24',1,5,5),
(8,'2020-01-29',2,2,6),
(9,'2020-02-01',1,6,6),
(10,'2020-01-29',1,2,7),
(11,'2020-01-31',3,5,7),
(12,'2020-02-04',4,6,7),
(13,'2020-02-01',2,8,7),
(14,'2020-01-29',1,2,8),
(15,'2020-01-31',3,5,8),
(16,'2020-02-05',4,6,8),
(17,'2020-02-01',2,8,8),
(18,'2020-02-02',3,4,9),
(19,'2020-02-18',2,6,9),
(20,'2020-02-08',3,4,10),
(21,'2020-02-21',2,6,10),
(22,'2020-02-08',3,4,11),
(23,'2020-02-21',2,6,11),
(24,'2020-02-08',3,4,12),
(25,'2020-02-21',2,6,12),
(26,'2020-02-18',2,6,13),
(27,'2020-02-10',3,7,13),
(28,'2020-02-18',1,8,15),
(29,'2020-02-19',4,9,15),
(30,'2020-02-21',3,6,16),
(31,'2020-02-14',2,2,17),
(32,'2020-02-25',4,9,17),
(33,'2020-02-12',1,5,18),
(34,'2020-02-18',1,6,19),
(35,'2020-02-19',1,9,20),
(36,'2020-02-12',1,2,21),
(37,'2020-02-12',1,5,22),
(38,'2020-02-12',1,8,23),
(39,'2020-02-17',1,3,24),
(40,'2020-02-21',2,6,25),
(41,'2020-02-24',3,9,25),
(42,'2020-03-05',3,3,26),
(43,'2020-02-26',2,5,26),
(44,'2020-02-29',1,8,26),
(45,'2020-02-20',3,1,27),
(46,'2020-03-07',4,3,27),
(47,'2020-02-22',2,4,27),
(48,'2020-02-28',1,5,27);
UNLOCK TABLES;

-- CREATE USER 'anton'@'localhost' IDENTIFIED BY 'anton';
-- GRANT ALL PRIVILEGES ON * . * TO 'anton'@'localhost';
-- FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS expo_center DEFAULT CHARACTER SET utf8;

USE expo_center;

DROP TABLE IF EXISTS `hall`;
CREATE TABLE `hall` (
  `hall_id` tinyint(4) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `area` decimal(10,0) DEFAULT NULL,
  `image_path` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`hall_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `exposition`;
CREATE TABLE `exposition` (
  `exposition_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `image_path` varchar(100) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `hall_id` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`exposition_id`),
  KEY `hall_id_idx` (`hall_id`),
  CONSTRAINT `hall_id` FOREIGN KEY (`hall_id`) REFERENCES `hall` (`hall_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `date_joined` datetime DEFAULT NULL,
  `card_number` bigint(20) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `account_status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `total` decimal(10,2) DEFAULT NULL,
  `payment_date` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `user_id_idx` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket` (
  `ticket_id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `quantity` tinyint(4) DEFAULT NULL,
  `exposition_id` int(11) DEFAULT NULL,
  `payment_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ticket_id`),
  KEY `fk_ticket_1_idx` (`payment_id`),
  KEY `exposition_id_idx` (`exposition_id`),
  CONSTRAINT `exposition_id` FOREIGN KEY (`exposition_id`) REFERENCES `exposition` (`exposition_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `payment_id` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`payment_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

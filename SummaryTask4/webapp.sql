DROP DATABASE IF EXISTS webapp;

CREATE DATABASE webapp
CHARACTER SET utf8
COLLATE utf8_general_ci;

USE webapp;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login` char(15) NOT NULL UNIQUE,
  `password` char(15) NOT NULL,
  `email` char(25) NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `id` int NOT NULL,
  `name` char(15) NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `locale`;

CREATE TABLE `locale` (
  `id` int NOT NULL,
  `lang` char(15) NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `subjects`;

CREATE TABLE `subjects` (
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `subject_info`;

CREATE TABLE `subject_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `subject_id` int NOT NULL,
  `name` char(15) NOT NULL,
  `locale_id` int NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `grades`;

CREATE TABLE `grades` (
  `id` int NOT NULL AUTO_INCREMENT,
  `entrant_id` int NOT NULL,
  `subject_id` int NOT NULL,
  `grade` int NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `facultys`;

CREATE TABLE `facultys` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ms_id` int NOT NULL,
  `ss_id` int NOT NULL,
  `ts_id` int NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `faculty_info`;

CREATE TABLE `faculty_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `faculty_id` int NOT NULL,
  `name` char(15) NOT NULL,
  `description` LONGTEXT NOT NULL,
  `locale_id` int NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `entrants`;

CREATE TABLE `entrants` (
  `id` int NOT NULL AUTO_INCREMENT,
  `is_blocked` boolean NOT NULL,
  `user_id` int NOT NULL,
  `email` char(25) NOT NULL,
  `cetificate_url` char(25) NOT NULL,
  `tel` char(15) NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `entrants_info`;

CREATE TABLE `entrants_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `entrant_id` int NOT NULL,
  `first_name` char(15) NOT NULL,
  `middle_name` char(15) NOT NULL,
  `last_name` char(15) NOT NULL,
  `adress` char(15) NOT NULL,
  `oblast` char(15) NOT NULL,
  `school` char(15) NOT NULL,
  `locale_id` int NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `registration`;

CREATE TABLE `registration` (
  `id` int NOT NULL AUTO_INCREMENT,
  `entrant_id` int NOT NULL,
  `faculty_id` int NOT NULL,
  `mg_id` int NOT NULL,
  `sg_id` int NOT NULL,
  `tg_id` int NOT NULL,
  `is_blocked` boolean NOT NULL,
  `is_checked` boolean NOT NULL,
  `is_budget` boolean NOT NULL, 
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `positions`;

CREATE TABLE `positions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `faculty_id` int NOT NULL,
  `pos_quantity` int NOT NULL,
  `bud_pos_quantity` int NOT NULL,
  `pos_filled` int NOT NULL,
  `bud_pos_filled` int NOT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `locale` VALUES (1,'uk');
INSERT INTO `locale` VALUES (2,'en');
INSERT INTO `locale` VALUES (3,'ru');

INSERT INTO `users` VALUES (1,'admin', 'admin', 'rockarolla6666@gmail.com', 1);
INSERT INTO `users` VALUES (2,'user', 'user', 'rockarolla6666@gmail.com', 2);

INSERT INTO `roles` VALUES (1,'admin');
INSERT INTO `roles` VALUES (2,'user');

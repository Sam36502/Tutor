#########################
# TUTOR DATABASE SCRIPT #
#########################

# Create Database
DROP DATABASE IF EXISTS `tutor_db`;
CREATE DATABASE `tutor_db`;
USE `tutor_db`;

# Create Access Users
DROP USER 'tutor_user'@'%';
CREATE USER 'tutor_user'@'%' IDENTIFIED BY 'password';
DROP USER 'tutor_user'@'localhost';
CREATE USER 'tutor_user'@'localhost' IDENTIFIED BY 'password';

# Create Tables
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
	title VARCHAR(100) NOT NULL UNIQUE,
	description TEXT,
	author VARCHAR(50)
);

DROP TABLE IF EXISTS `step`;
CREATE TABLE `step` (
	id_step INT NOT NULL UNIQUE AUTO_INCREMENT,
	title VARCHAR(100) NOT NULL,
	description TEXT NOT NULL,
	image_path CHAR(25),
	course_title VARCHAR(100) NOT NULL,

	FOREIGN KEY (course_title)
	REFERENCES `course`(title)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

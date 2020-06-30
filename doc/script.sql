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
GRANT INSERT, SELECT, UPDATE, DELETE ON `tutor_db`.* TO 'tutor_user'@'%';

DROP USER 'tutor_user'@'localhost';
CREATE USER 'tutor_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON `tutor_db`.* TO 'tutor_user'@'localhost';

# Create Tables
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
	id INT NOT NULL UNIQUE AUTO_INCREMENT,
	title VARCHAR(100) NOT NULL,
	description TEXT,
	author VARCHAR(50)
);

DROP TABLE IF EXISTS `step`;
CREATE TABLE `step` (
	id_step INT NOT NULL UNIQUE AUTO_INCREMENT,
	title VARCHAR(100) NOT NULL,
	description TEXT NOT NULL,
	image_path CHAR(25),
	step_num INT NOT NULL,
	course_id INT NOT NULL,

	FOREIGN KEY (course_id)
	REFERENCES `course`(id)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

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
	id_course INT NOT NULL UNIQUE AUTO_INCREMENT,
	title VARCHAR(100) NOT NULL,
	description TEXT,
	author VARCHAR(50),
	
	PRIMARY KEY (id_course)
);

DROP TABLE IF EXISTS `step`;
CREATE TABLE `step` (
	id_step INT NOT NULL UNIQUE AUTO_INCREMENT,
	title VARCHAR(100) NOT NULL,
	description TEXT NOT NULL,
	image_path CHAR(25),
	step_num INT NOT NULL,
	course_id INT NOT NULL,
	
	PRIMARY KEY (id_step),

	FOREIGN KEY (course_id)
	REFERENCES `course`(id_course)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	
	UNIQUE KEY course_step (course_id, step_num)
);

# Test Data
# Remove/Comment out before release
DELETE FROM `course` WHERE 1;
INSERT INTO `course`(title, description, author)
VALUES ('Test Course', 'This is a course to test the database', 'Samuel Pearce');
INSERT INTO `course`(title, description, author)
VALUES ('Another Course', 'This course only to test having multiple courses.', 'Amin Haidar');

DELETE FROM `step` WHERE 1;
INSERT INTO `step`(title, description, step_num, course_id)
VALUES ('First Step', 'This is the first step. It should be shown first.', 1, 1);
INSERT INTO `step`(title, description, step_num, course_id)
VALUES ('Second Step', 'This is the next step. It should be shown after the first.', 2, 1);
INSERT INTO `step`(title, description, step_num, course_id)
VALUES ('First Step', 'This is the first step. It should be shown first.', 1, 2);

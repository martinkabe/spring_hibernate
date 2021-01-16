CREATE DATABASE  IF NOT EXISTS `hb_student_tracker`;
USE `hb_student_tracker`;

-- Table structure for table `employee`

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

insert into employee(first_name, last_name, email) values
('Jachime', 'Merrit', 'jmerrit14784@gmail.cz'),
('John', 'Doe', 'jdoe14784@gmail.cz'),
('Jack', 'Smith', 'jsmith14784@gmail.cz');

CREATE TABLE `student` (
  student_id int NOT NULL AUTO_INCREMENT,
  first_name varchar(45) DEFAULT NULL,
  last_name varchar(45) DEFAULT NULL,
  age INT,
  postal_code varchar(10),
  course_code varchar(10),
  country_name varchar(50),
  language_name varchar(50),
  os_name varchar(50),
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
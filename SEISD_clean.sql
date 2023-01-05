-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: bgs7tzjpys5zfnmraodc-mysql.services.clever-cloud.com:3306
-- Generation Time: Dec 14, 2022 at 04:43 PM
-- Server version: 8.0.22-13
-- PHP Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bgs7tzjpys5zfnmraodc`
--

-- --------------------------------------------------------

--
-- Table structure for table `courseinfo`
--

CREATE TABLE `courseinfo` (
  `CourseCode` varchar(20) NOT NULL,
  `CourseName` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Credit` int NOT NULL,
  `Prerequisite` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `courseinfo`
--

INSERT INTO `courseinfo` (`CourseCode`, `CourseName`, `Credit`, `Prerequisite`) VALUES
('CHM123', 'Chemistry', 3, '0'),
('CHM136', 'Chemistry Lab', 1, '0'),
('CSE132', 'Data Structure', 3, '0'),
('CSE133', 'Data Structure Lab', 1, '0'),
('CSE134', 'Structured Programming Language', 3, 'GED115,GED116'),
('CSE135', 'Structured Programming Language', 1, 'GED115,GED116'),
('CSE213', 'Object Oriented Programming', 3, 'CSE134,CSE135'),
('CSE214', 'Object Oriented Programming Lab', 1, 'CSE134,CSE135'),
('CSE223', 'Digital Logic Design', 3, '0'),
('CSE224', 'Digital Logic Design Lab', 1, '0'),
('CSE225', 'Visual and Net-Based Programming', 3, 'CSE213'),
('CSE226', 'Visual and Net-Based Programming Lab', 1, 'CSE214'),
('CSE232', 'Microprocessor and assembly language programming', 3, '0'),
('CSE233', 'Microprocessor and assembly language programming lab', 1, '0'),
('CSE234', 'Algorithms', 3, 'CSE132,MAT231'),
('CSE235', 'Algorithms Lab', 1, 'CSE135'),
('CSE236', 'Database Management Systems', 3, 'CSE132'),
('CSE237', 'Database Management Systems Lab', 1, ''),
('CSE311', 'Communication Engineering', 3, '0'),
('CSE312', 'Numerical Methods', 3, '0'),
('CSE313', 'Computer Network', 3, '0'),
('CSE314', 'Computer Network Lab', 1, '0'),
('CSE315', 'Operating System', 3, '0'),
('CSE316', 'Operating System Lab', 1, '0'),
('CSE322', 'Theory of Computation', 3, '0'),
('CSE323', 'Computer Architecture', 3, '0'),
('CSE324', 'Software Engineering and Information Design', 3, '0'),
('CSE325', 'Software Engineering and Information Design lab', 1, '0'),
('CSE331', 'Peripherals and Interfacing', 3, 'CSE223'),
('CSE332', 'Peripherals and Interfacing Lab', 1, 'CSE224'),
('CSE333', 'Compiler Design', 3, 'CSE322'),
('CSE334', 'Compiler Design Lab', 1, '0'),
('CSE335', 'Mathematical Analysis for Computer Science', 3, '0'),
('CSE411', 'Artificial Intelligence', 3, '0'),
('CSE412', 'Artificial Intelligence Lab', 1, '0'),
('CSE413', 'IT Organization and Management', 3, '0'),
('CSE414', 'Neural Networks', 3, '0'),
('CSE415', 'Neural Networks Lab', 1, '0'),
('CSE416', 'UNIX Programming', 3, '0'),
('CSE417', 'UNIX Programming Lab', 1, '0'),
('CSE418', 'Multimedia', 3, '0'),
('CSE419', 'Multimedia Lab', 1, '0'),
('CSE421', 'Computer Graphics', 3, '0'),
('CSE422', 'Computer Graphics Lab', 1, '0'),
('CSE423', 'Wireless Networks', 3, '0'),
('CSE424', 'Wireless Networks Lab', 1, '0'),
('CSE425', 'Advanced Algorithm', 3, '0'),
('CSE426', 'Advanced Algorithm Lab', 1, '0'),
('CSE427', 'Pattern Recognition', 3, '0'),
('CSE428', 'Pattern Recognition Lab', 1, '0'),
('CSE431', 'Internship with Seminar', 6, '0'),
('CSE432', 'Project with Seminar', 6, '0'),
('CSE433', 'E-Commerce and Web Design', 3, '0'),
('CSE434', 'E-Commerce and Web Design Lab', 1, '0'),
('CSE435', 'Advanced topic', 3, '0'),
('CSE436', 'Advanced topic Lab', 1, '0'),
('CSE437', 'Simulation and Modelling', 3, '0'),
('CSE438', 'Basic Graph Theory', 3, '0'),
('CSE439', 'Digital Image Processing', 3, '0'),
('CSE440', 'Sensing and Planning in Robotics', 3, '0'),
('CSE441', 'Parallel and Distributed Processing', 3, '0'),
('CSE442', 'Software Design Tools & Techniques', 3, '0'),
('CSE444', 'VLSI Design', 3, '0'),
('CSE481', 'Optical Fibre Communication', 3, '0'),
('EEE124', 'Electric Circuits', 3, '0'),
('EEE125', 'Electric Circuits Lab', 3, '0'),
('EEE215', 'Electronic Devices and Circuit', 3, 'EEE124'),
('EEE216', 'Electronic Devices and Circuit', 1, 'EEE125'),
('ENG 121', 'English Foundation 2', 3, 'ENG111'),
('ENG111', 'English Foundation 1', 3, '0'),
('GED115', 'Computer Fundamental', 2, '0'),
('GED116', 'Computer Application Lab', 1, '0'),
('GED212', 'Bangladesh Culture & Heritage Studies', 3, '0'),
('GED222', 'Financial and Managerial Accounting', 3, '0'),
('GED321', 'Economics', 3, '0'),
('MAT112', 'Differential and Integral Calculus', 3, '0'),
('MAT122', 'Coordinate Geometry & Vector Analysis', 3, 'MAT112'),
('MAT131', 'Linear Algebra and Complex Var', 3, 'MAT122'),
('MAT211', 'Differential Equations and Special Functions', 3, 'MAT131'),
('MAT221', 'Basic Statistics and Probabili', 3, '0'),
('MAT231', 'Discrete Mathematics', 3, '0'),
('PHY113', 'Physics', 3, '0'),
('PHY114', 'Physics Lab', 1, '0');

-- --------------------------------------------------------

--
-- Table structure for table `information`
--

CREATE TABLE `information` (
  `attribute` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `value` varchar(16000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `information`
--

INSERT INTO `information` (`attribute`, `value`) VALUES
('thisSem', 'Fall2018'),
('courseOffer', '{\"14\":[\"CHM123\",\"CHM136\",\"CSE132\",\"CSE133\"]}'),
('completedCourse', '{\"14\":[]}'),
('runningCourseData', '{\"CHM136\":[14],\"CHM123\":[14],\"CSE133\":[14],\"CSE132\":[14]}');

-- --------------------------------------------------------

--
-- Table structure for table `Routine`
--

CREATE TABLE `Routine` (
  `Date` varchar(20) NOT NULL,
  `courseName` varchar(50) NOT NULL,
  `times` varchar(40) NOT NULL,
  `room` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` int NOT NULL,
  `batch` int DEFAULT NULL,
  `admission_sem` varchar(20) DEFAULT NULL,
  `info` mediumtext
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `batch`, `admission_sem`, `info`) VALUES
(111222, 14, 'Fall1999', '{\"name\":\"null\",\"Fall2022\":[{\"GED212\":4.0},{\"GED222\":2.0},{\"GED321\":3.6}]}'),
(18300011, 14, 'Fall2018', '{\"Fall2018\":[{\"CHM123\":0.0},{\"CHM136\":0.0},{\"CSE132\":0.0},{\"CSE133\":0.0}],\"name\":\"Shakib Hossain\",\"information\":[{\"dateOfBirth\":\"2022-12-01\"},{\"religion\":\"Islam\"},{\"gender\":\"Male\"},{\"address\":\"Chasara,Narayanganj\"}]}');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `courseinfo`
--
ALTER TABLE `courseinfo`
  ADD PRIMARY KEY (`CourseCode`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

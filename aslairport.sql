-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost

-- Server version: 5.5.24-log
-- PHP Version: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `aslairport`
--

-- --------------------------------------------------------

--
-- Table structure for table `flight`
--

CREATE TABLE IF NOT EXISTS `flight` (
  `flightID` int(11) NOT NULL,
  `destination` varchar(60) NOT NULL,
  `depDate` date NOT NULL,
  `depTime` time NOT NULL,
  `planeID` int(11) NOT NULL,
  `pilotID` int(11) NOT NULL,
  `gateID` varchar(4) NOT NULL,
  PRIMARY KEY (`flightID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `flight`
--

INSERT INTO `flight` (`flightID`, `destination`, `depDate`, `depTime`, `planeID`, `pilotID`, `gateID`) VALUES
(101, 'Tokyo, Japan', '2013-12-25', '01:20:00', 234, 10, 'A001'),
(102, 'Manila, Philippines', '2013-11-02', '13:00:00', 1867, 11, 'A002'),
(103, 'New Delhi, India', '2013-11-25', '13:30:00', 2685, 1, 'A003'),
(104, 'Zurich, Switzerland', '2013-11-06', '01:45:00', 3212, 2, 'A004'),
(105, 'Taipei, Taiwan', '2013-12-07', '06:50:00', 4325, 3, 'A002'),
(106, 'Seoul, Korea', '2014-01-02', '09:15:00', 5432, 4, 'A001'),
(107, 'Taipei, Taiwan', '2014-01-19', '16:10:00', 6987, 5, 'A004'),
(108, 'New Delhi, India', '2013-12-20', '15:00:00', 7234, 6, 'A003');

-- --------------------------------------------------------

--
-- Table structure for table `passenger`
--

CREATE TABLE IF NOT EXISTS `passenger` (
  `name` varchar(50) NOT NULL,
  `age` int(11) NOT NULL,
  `flightID` int(11) NOT NULL,
  `firstClass` tinyint(1) NOT NULL,
  `seatID` varchar(3) NOT NULL,
  `pasID` varchar(4) NOT NULL,
  PRIMARY KEY (`pasID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `passenger`
--

INSERT INTO `passenger` (`name`, `age`, `flightID`, `firstClass`, `seatID`, `pasID`) VALUES
('Arjay Nguyen', 22, 101, 1, '', '101C'),
('Jim Nelson', 54, 101, 0, '', '102C'),
('Billy Norton', 19, 101, 0, '', '103C'),
('Allison Bingham', 32, 101, 1, '', '104C'),
('Lucy Chen', 25, 101, 1, '', '105C'),
('Shivalik Narad', 22, 101, 1, '', '106C'),
('Andrew Jones', 25, 101, 1, '', '107C'),
('Tyler Stennette', 23, 101, 3, '', '108C'),
('Joseph Coorey', 23, 101, 3, '', '109C'),
('Joshua Filstrup', 24, 101, 1, '', '110C');

-- --------------------------------------------------------

--
-- Table structure for table `pilot`
--

CREATE TABLE IF NOT EXISTS `pilot` (
  `name` varchar(40) NOT NULL,
  `pilotID` int(11) NOT NULL,
  `yearExp` int(11) NOT NULL,
  PRIMARY KEY (`pilotID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pilot`
--

INSERT INTO `pilot` (`name`, `pilotID`, `yearExp`) VALUES
('Shannon Moody', 1, 5),
('Rita King', 2, 15),
('Jan Morris', 3, 1),
('James Doolittle', 4, 7),
('Noel Wein', 5, 14),
('Robert Hoover', 6, 9),
('Charles Lindbergh', 7, 13),
('Charles Yeager', 8, 17),
('Fox McCould', 10, 10),
('Pete Mitchell', 11, 6);

-- --------------------------------------------------------

--
-- Table structure for table `plane`
--

CREATE TABLE IF NOT EXISTS `plane` (
  `planeID` int(11) NOT NULL,
  `age` int(11) NOT NULL,
  PRIMARY KEY (`planeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `plane`
--

INSERT INTO `plane` (`planeID`, `age`) VALUES
(234, 7),
(1867, 13),
(2685, 1),
(3212, 20),
(4325, 5),
(5432, 15),
(6987, 9),
(7234, 16);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

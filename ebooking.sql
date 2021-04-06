-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 06, 2021 at 02:24 PM
-- Server version: 5.7.31-0ubuntu0.18.04.1
-- PHP Version: 7.2.24-0ubuntu0.18.04.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ebooking`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking_details`
--

CREATE TABLE `booking_details` (
  `id` int(11) NOT NULL,
  `bookingId` varchar(255) DEFAULT NULL,
  `fromDate` varchar(255) DEFAULT NULL,
  `roomId` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `toDate` varchar(255) DEFAULT NULL,
  `userId` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `booking_details`
--

INSERT INTO `booking_details` (`id`, `bookingId`, `fromDate`, `roomId`, `status`, `toDate`, `userId`) VALUES
(1, 'NKPMG', '2021-04-06', '113', 0, '2021-04-07', '0x5024e09bf41b4fe5812ac1587f45d5b7'),
(2, 'MJ1IT', '2021-04-06', '113', 0, '2021-04-07', '0x5024e09bf41b4fe5812ac1587f45d5b7');

-- --------------------------------------------------------

--
-- Table structure for table `ebooking_registration`
--

CREATE TABLE `ebooking_registration` (
  `userId` binary(16) NOT NULL,
  `emailId` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ebooking_registration`
--

INSERT INTO `ebooking_registration` (`userId`, `emailId`, `name`, `password`, `phoneNumber`) VALUES
(0x5024e09bf41b4fe5812ac1587f45d5b7, 'keerthanatorcher@gmail.com', 'keerthana', 'dd9ffe003fb10cdfd3c25731a6082255', '1313131313'),
(0xf156a79d165648cb8aa7bfd231b26a6b, 'rockpenhar@gmail.com', 'penhar', '38466ba79ad04d00152563b80d1f8c1c', '8248106512');

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `id` int(11) NOT NULL,
  `availability` bit(1) DEFAULT NULL,
  `roomId` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`id`, `availability`, `roomId`) VALUES
(1, b'1', '113');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking_details`
--
ALTER TABLE `booking_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ebooking_registration`
--
ALTER TABLE `ebooking_registration`
  ADD PRIMARY KEY (`userId`);

--
-- Indexes for table `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking_details`
--
ALTER TABLE `booking_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

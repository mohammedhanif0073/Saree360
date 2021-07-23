-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 03, 2020 at 03:01 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `saree360`
--

-- --------------------------------------------------------

--
-- Table structure for table `addcart`
--

CREATE TABLE `addcart` (
  `id` int(100) NOT NULL,
  `image` varchar(500) NOT NULL,
  `sareename` varchar(500) NOT NULL,
  `brand` varchar(500) NOT NULL,
  `quantity` int(100) NOT NULL,
  `price` varchar(500) NOT NULL,
  `total` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `addcart`
--

INSERT INTO `addcart` (`id`, `image`, `sareename`, `brand`, `quantity`, `price`, `total`) VALUES
(1, 'http://192.168.43.189/saree360php/images/sareenew12.jpeg', 'Black Woven Banarasi Silk Blend Saree', 'Cotton Silk Sarees,Silk Sarees', 2, '1111', '2222'),
(2, 'http://192.168.43.189/saree360php/images/sareenew13.jpeg', 'Black Woven Banarasi Silk Blend Saree', 'Cotton Silk Sarees,Silk Sarees', 2, '2222', '4444');

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id` int(100) NOT NULL,
  `image` varchar(500) NOT NULL,
  `sareename` varchar(500) NOT NULL,
  `brand` varchar(100) NOT NULL,
  `quantity` int(100) NOT NULL,
  `price` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`id`, `image`, `sareename`, `brand`, `quantity`, `price`) VALUES
(1, 'http://192.168.43.189/saree360php/images/sareenew12.jpeg', 'Black Woven Banarasi Silk Blend Saree', 'Cotton Silk Sarees,Silk Sarees', 0, '1111'),
(2, 'http://192.168.43.189/saree360php/images/sareenew13.jpeg', 'Black Woven Banarasi Silk Blend Saree', 'Cotton Silk Sarees,Silk Sarees', 0, '2222');

-- --------------------------------------------------------

--
-- Table structure for table `userdata`
--

CREATE TABLE `userdata` (
  `id` int(255) NOT NULL,
  `username` varchar(500) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `phoneno` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `userdata`
--

INSERT INTO `userdata` (`id`, `username`, `email`, `password`, `phoneno`) VALUES
(5, 'hanif', 'hanifhoney12345@gmail.com', 'Honey12345@', '9901889198'),
(6, 'aaa', 'aa@gmail.com', 'Aaaa1221@', '1111111111'),
(7, 'hanif', 'hahsjnssb@gmail.com', 'Honry12345@', '8746926979'),
(8, 'hjjvgv', 'hhjj@gmail.com', 'bnnnHon123@', '9999999912'),
(9, 'aaaaa', 'hajsnsnsnd@gmail.com', 'Honey12345@', '9901889198'),
(10, 'bbbb', 'hanifhoney12345@gmail.com', 'Honey12345@', '9901889198');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `addcart`
--
ALTER TABLE `addcart`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `userdata`
--
ALTER TABLE `userdata`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `addcart`
--
ALTER TABLE `addcart`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `userdata`
--
ALTER TABLE `userdata`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

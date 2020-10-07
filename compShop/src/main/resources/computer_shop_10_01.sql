-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 01, 2020 at 10:17 AM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `computer_shop`
--
CREATE DATABASE IF NOT EXISTS `computer_shop` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `computer_shop`;

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `category_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `category_name`) VALUES
(1, 'Desktop'),
(2, 'Laptop'),
(3, 'Server');

-- --------------------------------------------------------

--
-- Table structure for table `producers`
--

DROP TABLE IF EXISTS `producers`;
CREATE TABLE `producers` (
  `id` smallint(6) NOT NULL,
  `producer_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `producers`
--

INSERT INTO `producers` (`id`, `producer_name`) VALUES
(1, 'Asus'),
(2, 'Apple'),
(3, 'Dell'),
(4, 'HP');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `producer_id` smallint(6) NOT NULL,
  `name` varchar(255) NOT NULL,
  `short_description` text NOT NULL,
  `price` decimal(20,2) NOT NULL,
  `image` varchar(255) NOT NULL,
  `meta_title` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `category_id`, `producer_id`, `name`, `short_description`, `price`, `image`, `meta_title`) VALUES
(1, 2, 1, 'Asus ZenBook S (UX391FA-AH010T)\r\n', '13,3\" / 1920x1080 / IPS / Intel Core i7-8565U, 1,8-4,6 ГГц / Intel UHD Graphics 620/ DDR3 / 16 ГБ / SSD - 512 GB/\r\n\r\n', '29999.00', 'img/products/ua_noutbuk_asus_zenbook_13_ux334fac_ux334fac-a3047t_noutbuk_asus_zenbook_13_ux334fac_ux334fac-a3047t_2500_1673.jpg', 'qq'),
(2, 2, 1, 'Asus ZenBook UX325JA Grey (UX325JA-AH040T)', '13,3\" / 1920x1080 / Intel Core i7-1065G7, 1,3-3,9 ГГц / Intel Iris Plus Graphics / ОС - Windows 10 Home / DDR4 / 16 ГБ / SSD - 512 ГБ / Wi-Fi 802.11ax, Bluetooth', '39999.00', 'img/products/ua_noutbuk_asus_zenbook_s_ux391fa-ah010t_noutbuk_asus_zenbook_s_ux391fa-ah010t.jpg', 'qq'),
(3, 2, 1, 'Asus ZenBook 13 UX333FLC (UX333FLC-A3153T)\r\n', '13,3\" / 1920x1080 / Intel Core i7-10510U, 1,8-4,9 ГГц / NVIDIA GeForce MX 250, 2 GB / DDR3 / 16 ГБ / SSD - 512 GB / Wi-Fi 802.11ax, Bluetooth / HDMI ', '38699.00', 'img/products/ua_noutbuk_asus_zenbook_ux325ja_grey_ux325ja-ah040t_noutbuk_asus_zenbook_ux325ja_grey_ux325ja-ah040t_1100_764.jpg', 'qq');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `login` varchar(200) NOT NULL,
  `first_name` varchar(200) NOT NULL,
  `last_name` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `login`, `first_name`, `last_name`, `email`, `password`) VALUES
(1, 'FromRegisr1', 'Anatoli', 'Asdf', 'anatol1812@ukr.net', 'Qazwsx23edc'),
(2, 'test', 'test', 'test', 'test', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `producers`
--
ALTER TABLE `producers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_login_uindex` (`login`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `producers`
--
ALTER TABLE `producers`
  MODIFY `id` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

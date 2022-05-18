-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: 23 ต.ค. 2020 เมื่อ 08:43 PM
-- เวอร์ชันของเซิร์ฟเวอร์: 10.4.10-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `prog2`
--

-- --------------------------------------------------------

--
-- โครงสร้างตาราง `customer`
--

CREATE TABLE `customer` (
  `id` int(13) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `username` varchar(8) NOT NULL,
  `password` varchar(8) NOT NULL,
  `address` int(9) NOT NULL,
  `postcode` int(5) NOT NULL,
  `bank_acct` int(10) NOT NULL,
  `balance` double(9,2) NOT NULL,
  `verified` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- dump ตาราง `customer`
--

INSERT INTO `customer` (`id`, `name`, `surname`, `username`, `password`, `address`, `postcode`, `bank_acct`, `balance`, `verified`) VALUES
(783, 'admin', 'admin', 'admin', '1234', 12, 999, 2343234, 99999.00, 1),
(786, 'nichapat', 'daragorn', 'eing', '43232', 489, 2322, 630023, 90000.00, 0),
(787, 'natcha', 'singhara', 'natcha', '43212', 4891, 2322, 630023, 44000.00, 0),
(788, 'natthanan', 'sihawong', 'bobinz', '43212', 4891, 2322, 334211, 44000.00, 0),
(789, 'kiattiyot', 'shawong', 'oakker', '6666', 12, 999, 63453, 3399.00, 0);

-- --------------------------------------------------------

--
-- โครงสร้างตาราง `good`
--

CREATE TABLE `good` (
  `id` int(9) NOT NULL,
  `name` varchar(45) NOT NULL,
  `unitcost` double(9,2) NOT NULL,
  `unitprice` double(9,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- dump ตาราง `good`
--

INSERT INTO `good` (`id`, `name`, `unitcost`, `unitprice`) VALUES
(123458, 'pepsi', 10.00, 12.00),
(123459, 'coke', 11.00, 14.00),
(123460, 'bigCola', 10.00, 10.00),
(123511, 'oishi', 13.00, 15.00),
(123516, 'nescafe', 10.00, 13.00),
(123519, 'nubwo', 233.00, 250.00),
(123520, 'yumyum', 10.00, 11.00);

-- --------------------------------------------------------

--
-- โครงสร้างตาราง `invoice`
--

CREATE TABLE `invoice` (
  `ID` int(9) NOT NULL,
  `customer_id` int(13) NOT NULL,
  `invoice_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `total_amount` double(9,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- โครงสร้างตาราง `invoice_det`
--

CREATE TABLE `invoice_det` (
  `ID` int(9) NOT NULL,
  `invoice_id` int(9) NOT NULL,
  `good_id` int(9) NOT NULL,
  `quantity` double(9,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- โครงสร้างตาราง `purchase`
--

CREATE TABLE `purchase` (
  `id` int(9) NOT NULL,
  `supplier_id` int(9) NOT NULL,
  `purchase_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `receive_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `status` int(1) NOT NULL,
  `total_amount` double(9,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- dump ตาราง `purchase`
--

INSERT INTO `purchase` (`id`, `supplier_id`, `purchase_date`, `receive_date`, `status`, `total_amount`) VALUES
(432451, 10, '2020-10-21 17:52:40', '0000-00-00 00:00:00', 0, 3500.00),
(432452, 26, '2020-10-21 17:53:42', '0000-00-00 00:00:00', 1, 2000.00),
(432453, 9, '2020-10-23 18:13:22', '0000-00-00 00:00:00', 1, 3250.00),
(432454, 36, '2020-10-23 17:46:16', '0000-00-00 00:00:00', 1, 12500.00),
(432455, 9, '2020-10-23 18:14:43', '0000-00-00 00:00:00', 1, 1800.00),
(432456, 26, '2020-10-23 18:16:11', '0000-00-00 00:00:00', 1, 3750.00),
(432457, 26, '2020-10-23 18:21:14', '0000-00-00 00:00:00', 1, 1400.00),
(432458, 36, '2020-10-23 18:17:51', '0000-00-00 00:00:00', 1, 2000.00);

-- --------------------------------------------------------

--
-- โครงสร้างตาราง `purchase_det`
--

CREATE TABLE `purchase_det` (
  `ID` int(9) NOT NULL,
  `purchase_id` int(15) DEFAULT NULL,
  `good_id` int(15) DEFAULT NULL,
  `quantity` double(9,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- dump ตาราง `purchase_det`
--

INSERT INTO `purchase_det` (`ID`, `purchase_id`, `good_id`, `quantity`) VALUES
(63, 432452, 123460, 200.00),
(64, 432453, 123516, 250.00),
(65, 432454, 123519, 50.00),
(66, 432455, 123458, 150.00),
(67, 432456, 123511, 250.00),
(68, 432457, 123459, 100.00),
(69, 432458, 123460, 200.00);

-- --------------------------------------------------------

--
-- โครงสร้างตาราง `stock`
--

CREATE TABLE `stock` (
  `id` int(11) NOT NULL,
  `good_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `replenish` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- dump ตาราง `stock`
--

INSERT INTO `stock` (`id`, `good_id`, `quantity`, `replenish`) VALUES
(1, 123459, 100, 100);

-- --------------------------------------------------------

--
-- โครงสร้างตาราง `supplier`
--

CREATE TABLE `supplier` (
  `id` int(9) NOT NULL,
  `name` varchar(45) NOT NULL,
  `address` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- dump ตาราง `supplier`
--

INSERT INTO `supplier` (`id`, `name`, `address`) VALUES
(9, 'sombat', '1211'),
(10, 'sofia', '1432'),
(26, 'somcai', '1288'),
(36, 'mala', '1433');

-- --------------------------------------------------------

--
-- โครงสร้างตาราง `supplier_good`
--

CREATE TABLE `supplier_good` (
  `id` int(9) NOT NULL,
  `supplier_id` int(9) NOT NULL,
  `good_id` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- dump ตาราง `supplier_good`
--

INSERT INTO `supplier_good` (`id`, `supplier_id`, `good_id`) VALUES
(556, 9, 123459),
(557, 9, 123458),
(558, 10, 123460),
(562, 36, 123516),
(566, 36, 123520);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `good`
--
ALTER TABLE `good`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `invoice_det`
--
ALTER TABLE `invoice_det`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `good_id` (`good_id`),
  ADD KEY `invoice_id` (`invoice_id`);

--
-- Indexes for table `purchase`
--
ALTER TABLE `purchase`
  ADD PRIMARY KEY (`id`),
  ADD KEY `supplier_id` (`supplier_id`);

--
-- Indexes for table `purchase_det`
--
ALTER TABLE `purchase_det`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `purchase_id` (`purchase_id`);

--
-- Indexes for table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`id`),
  ADD KEY `good_id` (`good_id`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `supplier_good`
--
ALTER TABLE `supplier_good`
  ADD PRIMARY KEY (`id`),
  ADD KEY `supplier_id` (`supplier_id`),
  ADD KEY `good_id` (`good_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(13) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=790;

--
-- AUTO_INCREMENT for table `good`
--
ALTER TABLE `good`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=123521;

--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `ID` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73;

--
-- AUTO_INCREMENT for table `invoice_det`
--
ALTER TABLE `invoice_det`
  MODIFY `ID` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;

--
-- AUTO_INCREMENT for table `purchase`
--
ALTER TABLE `purchase`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=432460;

--
-- AUTO_INCREMENT for table `purchase_det`
--
ALTER TABLE `purchase_det`
  MODIFY `ID` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- AUTO_INCREMENT for table `stock`
--
ALTER TABLE `stock`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `supplier`
--
ALTER TABLE `supplier`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `supplier_good`
--
ALTER TABLE `supplier_good`
  MODIFY `id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=567;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`ID`);

--
-- Constraints for table `invoice_det`
--
ALTER TABLE `invoice_det`
  ADD CONSTRAINT `invoice_det_ibfk_1` FOREIGN KEY (`good_id`) REFERENCES `good` (`id`),
  ADD CONSTRAINT `invoice_det_ibfk_2` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`ID`);

--
-- Constraints for table `purchase`
--
ALTER TABLE `purchase`
  ADD CONSTRAINT `purchase_ibfk_1` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`ID`);

--
-- Constraints for table `purchase_det`
--
ALTER TABLE `purchase_det`
  ADD CONSTRAINT `purchase_det_ibfk_1` FOREIGN KEY (`purchase_id`) REFERENCES `purchase` (`ID`);

--
-- Constraints for table `stock`
--
ALTER TABLE `stock`
  ADD CONSTRAINT `stock_ibfk_1` FOREIGN KEY (`good_id`) REFERENCES `good` (`id`);

--
-- Constraints for table `supplier_good`
--
ALTER TABLE `supplier_good`
  ADD CONSTRAINT `supplier_good_ibfk_1` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`ID`),
  ADD CONSTRAINT `supplier_good_ibfk_2` FOREIGN KEY (`good_id`) REFERENCES `good` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

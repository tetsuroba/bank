-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 24, 2018 at 08:30 AM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bank`
--

-- --------------------------------------------------------

--
-- Table structure for table `bankfiok`
--

CREATE TABLE `bankfiok` (
  `fioknev` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `tartalek` int(11) DEFAULT NULL,
  `varos` varchar(255) COLLATE utf8_hungarian_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Dumping data for table `bankfiok`
--

INSERT INTO `bankfiok` (`fioknev`, `tartalek`, `varos`) VALUES
('Budapesti bank', 13174385, 'Budapest'),
('Debreceni bank', 5658777, 'Debrecen'),
('Szegedi bank', 7869910, 'Szeged'),
('Teszt bank', 87554, 'Budapest');

-- --------------------------------------------------------

--
-- Table structure for table `bankszamla`
--

CREATE TABLE `bankszamla` (
  `szamla_ID` int(255) NOT NULL,
  `tipus` int(11) NOT NULL DEFAULT '1',
  `egyenleg` int(255) NOT NULL,
  `tulajdonos_ID` varchar(255) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Dumping data for table `bankszamla`
--

INSERT INTO `bankszamla` (`szamla_ID`, `tipus`, `egyenleg`, `tulajdonos_ID`) VALUES
(1, 1, 2062054, 'elso'),
(3, 1, 13937, 'masodik'),
(4, 1, 41050, 'ujfelh'),
(5, 2, 1155, 'ujfelh'),
(6, 2, 0, 'k'),
(7, 1, 0, 'forint'),
(8, 2, 0, 'euro'),
(9, 3, 0, 'dollar');

-- --------------------------------------------------------

--
-- Table structure for table `kolcson`
--

CREATE TABLE `kolcson` (
  `kolcsonzesi_ID` int(11) NOT NULL,
  `osszeg` int(11) NOT NULL,
  `felvevo_neve` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `ado_fiok` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `rendezve` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Dumping data for table `kolcson`
--

INSERT INTO `kolcson` (`kolcsonzesi_ID`, `osszeg`, `felvevo_neve`, `ado_fiok`, `rendezve`) VALUES
(1, 10000, 'elso', 'Budapesti bank', 1),
(2, 10000, 'elso', 'Budapesti bank', 1),
(3, 10000, 'elso', 'Budapesti bank', 1),
(4, 10000, 'elso', 'Budapesti bank', 1),
(5, 10000, 'elso', 'Budapesti bank', 1),
(6, 10000, 'elso', 'Budapesti bank', 1),
(7, 10000, 'elso', 'Budapesti bank', 1),
(8, 10000, 'elso', 'Budapesti bank', 1),
(9, 10000, 'elso', 'Budapesti bank', 1),
(10, 10000, 'elso', 'Budapesti bank', 1),
(11, 10000, 'elso', 'Budapesti bank', 1),
(12, 10000, 'elso', 'Budapesti bank', 1),
(13, 10000, 'elso', 'Budapesti bank', 1),
(14, 10000, 'elso', 'Budapesti bank', 1),
(15, 10000, 'elso', 'Budapesti bank', 1),
(16, 10000, 'elso', 'Budapesti bank', 1),
(17, 10000, 'elso', 'Budapesti bank', 1),
(18, 10000, 'elso', 'Budapesti bank', 1),
(19, 10000, 'elso', 'Budapesti bank', 1),
(20, 10000, 'elso', 'Budapesti bank', 1),
(21, 10000, 'elso', 'Budapesti bank', 1),
(22, 10000, 'elso', 'Budapesti bank', 1),
(23, 10000, 'elso', 'Budapesti bank', 1),
(24, 10000, 'elso', 'Budapesti bank', 1),
(25, 10000, 'elso', 'Budapesti bank', 1),
(26, 10000, 'elso', 'Budapesti bank', 1),
(27, 10000, 'elso', 'Budapesti bank', 1),
(28, 10000, 'elso', 'Budapesti bank', 1),
(29, 10000, 'elso', 'Budapesti bank', 1),
(30, 10000, 'elso', 'Budapesti bank', 1),
(31, 10000, 'elso', 'Budapesti bank', 1),
(32, 10000, 'elso', 'Budapesti bank', 1),
(33, 10000, 'elso', 'Szegedi bank', 1),
(34, 10000, 'elso', 'Szegedi bank', 1),
(35, 10000, 'elso', 'Szegedi bank', 1),
(36, 10000, 'elso', 'Szegedi bank', 1),
(37, 31223, 'elso', 'Teszt bank', 1),
(38, 31223, 'elso', 'Teszt bank', 1),
(39, 31223, 'elso', 'Debreceni bank', 1),
(40, 1000, 'elso', 'Budapesti bank', 1),
(41, 100000, 'elso', 'Debreceni bank', 1),
(42, 100000, 'elso', 'Debreceni bank', 1),
(43, 10000, 'elso', 'Budapesti bank', 1),
(44, 123, 'elso', 'Budapesti bank', 1),
(45, 123, 'elso', 'Budapesti bank', 1),
(46, 123, 'elso', 'Budapesti bank', 1),
(47, 123, 'elso', 'Budapesti bank', 1),
(48, 123, 'elso', 'Budapesti bank', 1),
(49, 10000, 'elso', 'Budapesti bank', 1),
(50, 10000, 'elso', 'Budapesti bank', 1),
(51, 10000, 'elso', 'Budapesti bank', 1),
(52, 10000, 'elso', 'Budapesti bank', 1),
(53, 10000, 'elso', 'Budapesti bank', 1),
(54, 110000, 'elso', 'Budapesti bank', 1),
(55, 11, 'elso', 'Szegedi bank', 1),
(56, 11, 'elso', 'Szegedi bank', 1),
(57, 11, 'elso', 'Szegedi bank', 1),
(58, 11, 'elso', 'Szegedi bank', 1),
(59, 11, 'elso', 'Szegedi bank', 1),
(60, 11, 'elso', 'Szegedi bank', 1),
(61, 11, 'elso', 'Szegedi bank', 1),
(62, 11, 'elso', 'Szegedi bank', 1),
(63, 11, 'elso', 'Szegedi bank', 1),
(64, 11000, 'elso', 'Budapesti bank', 1),
(65, 110000, 'elso', 'Debreceni bank', 1),
(66, 110000, 'elso', 'Debreceni bank', 1),
(67, 11000, 'elso', 'Debreceni bank', 1),
(68, 110000, 'elso', 'Budapesti bank', 1),
(69, 110000, 'elso', 'Budapesti bank', 1),
(70, 110000, 'elso', 'Budapesti bank', 1),
(71, 110000, 'elso', 'Budapesti bank', 1),
(72, 11000, 'elso', 'Budapesti bank', 1),
(73, 110000, 'elso', 'Debreceni bank', 0),
(74, 110000, 'elso', 'Debreceni bank', 0),
(75, 110000, 'elso', 'Debreceni bank', 0),
(76, 110000, 'elso', 'Debreceni bank', 0),
(77, 110000, 'elso', 'Debreceni bank', 0),
(78, 110000, 'elso', 'Debreceni bank', 0),
(79, 110000, 'elso', 'Debreceni bank', 0),
(80, 110000, 'elso', 'Debreceni bank', 0),
(81, 110000, 'elso', 'Debreceni bank', 0),
(82, 110000, 'elso', 'Szegedi bank', 1),
(83, 110000, 'elso', 'Szegedi bank', 0);

-- --------------------------------------------------------

--
-- Table structure for table `ugyfel`
--

CREATE TABLE `ugyfel` (
  `ugyfel_ID` int(11) NOT NULL,
  `felhasznalonev` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `jelszo` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `ugyfelnev` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `ugyfelutca` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `ugyfelvaros` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
  `tartozas` int(11) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Dumping data for table `ugyfel`
--

INSERT INTO `ugyfel` (`ugyfel_ID`, `felhasznalonev`, `jelszo`, `ugyfelnev`, `ugyfelutca`, `ugyfelvaros`, `tartozas`) VALUES
(1, 'elso', 'uft{u234', 'Kutya123', 'Teszt utca 8', 'szeged', 2425826),
(3, 'masodik', 'uft{u234', 'teszt123', 'teszt utca 5', 'szeged', 0),
(5, 'ujfelh', 'bte234', 'uj felh', 'teszt utca 8', 'Szeged', 0),
(7, 'uj2', 'bteghi', 'res', 'res', 'res', 0),
(8, 'ujreg', 'bte234', 'uj regisztralo', 'utca utca 6', 'Szeged', 0),
(12, 'kkkkk', 'bcd234', 'dddd', 'erwq', 'daaa', 0),
(13, 'k', 'bte234', 'nev', 'utca', 'varos', 0),
(14, 'forint', 'gpsjou', 'forint', 'forint', 'forint', 0),
(15, 'euro', 'fvsp', 'euro', 'euro', 'euro', 0),
(16, 'dollar', 'epmmbs', 'dollar', 'dollar', 'dollar', 0);

-- --------------------------------------------------------

--
-- Table structure for table `valuta`
--

CREATE TABLE `valuta` (
  `valutaKod` int(11) NOT NULL,
  `valuta` varchar(255) COLLATE utf8_hungarian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Dumping data for table `valuta`
--

INSERT INTO `valuta` (`valutaKod`, `valuta`) VALUES
(1, 'Forint'),
(2, 'Euro'),
(3, 'USD');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bankfiok`
--
ALTER TABLE `bankfiok`
  ADD PRIMARY KEY (`fioknev`);

--
-- Indexes for table `bankszamla`
--
ALTER TABLE `bankszamla`
  ADD PRIMARY KEY (`szamla_ID`),
  ADD UNIQUE KEY `szamla_ID` (`szamla_ID`),
  ADD KEY `tipus` (`tipus`),
  ADD KEY `tulajdonos_ID` (`tulajdonos_ID`);

--
-- Indexes for table `kolcson`
--
ALTER TABLE `kolcson`
  ADD PRIMARY KEY (`kolcsonzesi_ID`),
  ADD KEY `felvevo_neve` (`felvevo_neve`),
  ADD KEY `ado_fiok` (`ado_fiok`);

--
-- Indexes for table `ugyfel`
--
ALTER TABLE `ugyfel`
  ADD PRIMARY KEY (`ugyfel_ID`),
  ADD UNIQUE KEY `felhasznalonev` (`felhasznalonev`);

--
-- Indexes for table `valuta`
--
ALTER TABLE `valuta`
  ADD PRIMARY KEY (`valutaKod`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bankszamla`
--
ALTER TABLE `bankszamla`
  MODIFY `szamla_ID` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `kolcson`
--
ALTER TABLE `kolcson`
  MODIFY `kolcsonzesi_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=84;

--
-- AUTO_INCREMENT for table `ugyfel`
--
ALTER TABLE `ugyfel`
  MODIFY `ugyfel_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bankszamla`
--
ALTER TABLE `bankszamla`
  ADD CONSTRAINT `bankszamla_ibfk_1` FOREIGN KEY (`tipus`) REFERENCES `valuta` (`valutaKod`),
  ADD CONSTRAINT `bankszamla_ibfk_2` FOREIGN KEY (`tulajdonos_ID`) REFERENCES `ugyfel` (`felhasznalonev`);

--
-- Constraints for table `kolcson`
--
ALTER TABLE `kolcson`
  ADD CONSTRAINT `kolcson_ibfk_1` FOREIGN KEY (`felvevo_neve`) REFERENCES `ugyfel` (`felhasznalonev`),
  ADD CONSTRAINT `kolcson_ibfk_2` FOREIGN KEY (`ado_fiok`) REFERENCES `bankfiok` (`fioknev`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

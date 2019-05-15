-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 27, 2016 at 04:23 AM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `koperasi`
--

-- --------------------------------------------------------

--
-- Table structure for table `anggota`
--

CREATE TABLE IF NOT EXISTS `anggota` (
  `id` int(11) NOT NULL,
  `nama` varchar(35) DEFAULT NULL,
  `alamat` varchar(35) DEFAULT NULL,
  `jk` varchar(15) DEFAULT NULL,
  `hp` varchar(25) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `anggota`
--

INSERT INTO `anggota` (`id`, `nama`, `alamat`, `jk`, `hp`) VALUES
(3, 'ekos', 'Yogyakarta', 'laki-laki', '0899099'),
(4, 'febi as', 'surabaya', 'perempuan', '887878787'),
(5, 'afaris arsalan daimung', 'kekalik swadaya', 'Laki-laki', '08776689'),
(8, 'farhan', 'sape', 'Laki-Laki', '083739002112');

-- --------------------------------------------------------

--
-- Table structure for table `bunga`
--

CREATE TABLE IF NOT EXISTS `bunga` (
  `id` int(11) NOT NULL,
  `idpinjam` int(11) NOT NULL,
  `jumlahBunga` bigint(20) NOT NULL,
  `tanggalBunga` date NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bunga`
--

INSERT INTO `bunga` (`id`, `idpinjam`, `jumlahBunga`, `tanggalBunga`) VALUES
(1, 7, 130000, '2016-10-24'),
(2, 8, 250000, '2016-10-24'),
(3, 8, 248800, '2016-10-24'),
(4, 7, 118801, '2016-11-10'),
(10, 11, 102, '2016-11-17');

-- --------------------------------------------------------

--
-- Table structure for table `pengurus`
--

CREATE TABLE IF NOT EXISTS `pengurus` (
  `idp` int(11) NOT NULL,
  `nama` varchar(35) DEFAULT NULL,
  `alamat` varchar(35) DEFAULT NULL,
  `jabatan` varchar(35) DEFAULT NULL,
  `hp` varchar(25) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengurus`
--

INSERT INTO `pengurus` (`idp`, `nama`, `alamat`, `jabatan`, `hp`) VALUES
(2, 'jokowi jk', 'jakarta', 'presiden', '087766504967'),
(5, 'arif rahman', 'Mataram', 'Ketua', '08888888');

-- --------------------------------------------------------

--
-- Table structure for table `pinjam`
--

CREATE TABLE IF NOT EXISTS `pinjam` (
  `id` int(11) NOT NULL,
  `ida` int(11) DEFAULT NULL,
  `jumlahp` int(11) DEFAULT NULL,
  `tanggalp` date DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pinjam`
--

INSERT INTO `pinjam` (`id`, `ida`, `jumlahp`, `tanggalp`) VALUES
(7, 3, 989999, '2016-10-24'),
(8, 4, 1980000, '2016-10-24'),
(10, 8, 831188, '2016-11-16'),
(11, 5, 100, '2016-11-17'),
(13, 8, 69000, '2016-11-19');

-- --------------------------------------------------------

--
-- Table structure for table `tabungan`
--

CREATE TABLE IF NOT EXISTS `tabungan` (
  `id` int(11) NOT NULL,
  `ida` int(11) DEFAULT NULL,
  `jumlah` int(11) DEFAULT NULL,
  `tanggal` date DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tabungan`
--

INSERT INTO `tabungan` (`id`, `ida`, `jumlah`, `tanggal`) VALUES
(8, 5, 56000, '2016-10-21'),
(10, 3, 60000, '2016-10-21'),
(11, 8, 50000, '2016-10-24'),
(12, 4, 5000, '2016-10-24'),
(13, 5, 900000, '2016-11-17'),
(14, 5, 200, '2016-11-17');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `anggota`
--
ALTER TABLE `anggota`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `bunga`
--
ALTER TABLE `bunga`
  ADD PRIMARY KEY (`id`), ADD KEY `idpinjam` (`idpinjam`);

--
-- Indexes for table `pengurus`
--
ALTER TABLE `pengurus`
  ADD PRIMARY KEY (`idp`);

--
-- Indexes for table `pinjam`
--
ALTER TABLE `pinjam`
  ADD PRIMARY KEY (`id`), ADD KEY `ida` (`ida`);

--
-- Indexes for table `tabungan`
--
ALTER TABLE `tabungan`
  ADD PRIMARY KEY (`id`), ADD KEY `ida` (`ida`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `anggota`
--
ALTER TABLE `anggota`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `bunga`
--
ALTER TABLE `bunga`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `pengurus`
--
ALTER TABLE `pengurus`
  MODIFY `idp` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `pinjam`
--
ALTER TABLE `pinjam`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `tabungan`
--
ALTER TABLE `tabungan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `bunga`
--
ALTER TABLE `bunga`
ADD CONSTRAINT `bunga_ibfk_1` FOREIGN KEY (`idpinjam`) REFERENCES `pinjam` (`id`);

--
-- Constraints for table `pinjam`
--
ALTER TABLE `pinjam`
ADD CONSTRAINT `pinjam_ibfk_1` FOREIGN KEY (`ida`) REFERENCES `anggota` (`id`);

--
-- Constraints for table `tabungan`
--
ALTER TABLE `tabungan`
ADD CONSTRAINT `tabungan_ibfk_1` FOREIGN KEY (`ida`) REFERENCES `anggota` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

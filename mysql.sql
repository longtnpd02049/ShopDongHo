-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: duan2
-- ------------------------------------------------------
-- Server version	5.6.40-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `binhluan`
--

DROP TABLE IF EXISTS `binhluan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `binhluan` (
  `IdBinhLuan` int(11) NOT NULL AUTO_INCREMENT,
  `IdChiTietSP` int(11) NOT NULL,
  `GioiTinh` int(11) NOT NULL,
  `HoTen` varchar(255) CHARACTER SET utf8 NOT NULL,
  `DanhGia` int(11) NOT NULL,
  `Email` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `Phone` double NOT NULL,
  `NoiDungBinhLuan` varchar(255) CHARACTER SET utf8 NOT NULL,
  `ThoiGianBinhLuan` datetime NOT NULL,
  `ThoiGianXacNhan` datetime DEFAULT NULL,
  `ThoiGianSua` datetime DEFAULT NULL,
  `Status` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`IdBinhLuan`),
  KEY `BinhLuan_ChiTietSP_IdChiTietSP` (`IdChiTietSP`),
  CONSTRAINT `BinhLuan_ChiTietSP_IdChiTietSP` FOREIGN KEY (`IdChiTietSP`) REFERENCES `chitietsp` (`IdChiTietSP`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `binhluan`
--

LOCK TABLES `binhluan` WRITE;
/*!40000 ALTER TABLE `binhluan` DISABLE KEYS */;
INSERT INTO `binhluan` VALUES (1,57,0,'Trương Ngọc Long',5,'Longtnpd02049@fpt.edu.vn',929638859,'Sản phẩm tuyệt vời demo edit cũng ok đấy','2018-10-02 23:06:09','2018-11-09 03:00:32','2018-11-09 03:00:13','ok'),(2,57,0,'Nguyễn Thúy Kiều',5,'Kieukuteahihi@gmail.com',16685412,'Đánh giá sản phẩm này 5 sao','2018-05-03 11:31:20','2018-11-09 03:00:37','2018-11-09 03:00:10','ok'),(3,64,0,'Nguyễn Quốc Tuấn',4,'Quoctuoan@gmail.com',929612351,'Sản phẩm không tồi','2018-08-22 15:06:59','2018-11-09 03:00:40','2018-11-09 03:00:07','ok'),(4,64,0,'Trương Thị Tú Linh',5,'Tulinh@gmail.com',9298450421,'Đánh Giá 5 Sao','2018-10-21 10:12:21','2018-11-09 03:00:41','2018-11-09 03:00:02','ok'),(5,68,0,'trương ngọc tuấn anh',5,'longkuteno6qb@gmail.com',984504989,'sản phẩm này tuyệt vời quá','2018-11-02 13:53:24','2018-11-09 13:38:32','2018-11-09 13:38:01','ok'),(6,64,0,'Nguyễn Hồng Thúy',1,'thuykute@gmail.com',1665712607,'Sản phẩm này quá tệ :((','2018-11-09 13:45:50','2018-11-26 13:22:42','2018-12-04 03:54:52','wait');
/*!40000 ALTER TABLE `binhluan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitietdathang`
--

DROP TABLE IF EXISTS `chitietdathang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chitietdathang` (
  `IdChiTietDatHang` int(11) NOT NULL AUTO_INCREMENT,
  `IdDatHang` int(11) NOT NULL,
  `IdChiTietSP` int(11) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  PRIMARY KEY (`IdChiTietDatHang`),
  KEY `ChiTietDatHang_ChiTietSP_IdChiTietSP` (`IdChiTietSP`),
  KEY `ChiTietDatHang_DatHang_IdDatHang` (`IdDatHang`),
  CONSTRAINT `ChiTietDatHang_ChiTietSP_IdChiTietSP` FOREIGN KEY (`IdChiTietSP`) REFERENCES `chitietsp` (`IdChiTietSP`),
  CONSTRAINT `ChiTietDatHang_DatHang_IdDatHang` FOREIGN KEY (`IdDatHang`) REFERENCES `dathang` (`IdDatHang`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitietdathang`
--

LOCK TABLES `chitietdathang` WRITE;
/*!40000 ALTER TABLE `chitietdathang` DISABLE KEYS */;
INSERT INTO `chitietdathang` VALUES (22,17,68,2),(23,17,64,1),(24,18,65,10),(25,18,61,9),(26,19,65,1),(27,20,68,1),(28,21,63,4),(29,22,63,1),(30,22,66,1),(31,23,78,2),(32,23,73,1),(33,23,72,3),(34,23,71,1),(35,23,59,1),(36,23,58,1),(37,23,60,1),(38,23,77,1),(39,23,76,1),(40,23,75,1),(41,23,74,1),(42,23,64,1),(43,24,77,2),(44,24,73,1),(45,24,71,1);
/*!40000 ALTER TABLE `chitietdathang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitietsp`
--

DROP TABLE IF EXISTS `chitietsp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chitietsp` (
  `IdChiTietSP` int(11) NOT NULL AUTO_INCREMENT,
  `IdDanhMuc` int(11) NOT NULL,
  `IdDanhMuc2` int(11) DEFAULT NULL,
  `IdHieuSP` int(11) NOT NULL,
  `IdUser` int(11) NOT NULL,
  `TenSP` varchar(255) CHARACTER SET utf8 NOT NULL,
  `GiaSP` double NOT NULL,
  `SoLuongDat` int(11) NOT NULL DEFAULT '1',
  `SoLuongSP` int(11) NOT NULL,
  `ThoiGianTaoSP` datetime NOT NULL,
  `ThoiGianSuaSP` datetime DEFAULT NULL,
  `DacDiemNoiBat` varchar(255) CHARACTER SET utf8 NOT NULL,
  `LoaiMay` varchar(255) CHARACTER SET utf8 NOT NULL,
  `KieuDang` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `MatKinh` varchar(255) CHARACTER SET utf8 NOT NULL,
  `Vo` varchar(255) CHARACTER SET utf8 NOT NULL,
  `DuongKinh` varchar(255) CHARACTER SET utf8 NOT NULL,
  `DoDay` varchar(255) CHARACTER SET utf8 NOT NULL,
  `DoChiuNuoc` varchar(255) CHARACTER SET utf8 NOT NULL,
  `BaoHanh` varchar(255) CHARACTER SET utf8 NOT NULL,
  `Status` varchar(255) CHARACTER SET utf8 NOT NULL,
  `Image` varchar(255) CHARACTER SET utf8 NOT NULL,
  `MoTaThem` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`IdChiTietSP`),
  KEY `ChiTietSP_DanhMucSP_IdDanhMuc` (`IdDanhMuc`),
  KEY `ChiTietSP_HieuSP_IdHieuSP` (`IdHieuSP`),
  KEY `ChiTietSP_Users_IdUser` (`IdUser`),
  CONSTRAINT `ChiTietSP_DanhMucSP_IdDanhMuc` FOREIGN KEY (`IdDanhMuc`) REFERENCES `danhmucsp` (`IdDanhMucSP`),
  CONSTRAINT `ChiTietSP_HieuSP_IdHieuSP` FOREIGN KEY (`IdHieuSP`) REFERENCES `hieusp` (`IdHieuSP`),
  CONSTRAINT `ChiTietSP_Users_IdUser` FOREIGN KEY (`IdUser`) REFERENCES `users` (`IdUsername`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitietsp`
--

LOCK TABLES `chitietsp` WRITE;
/*!40000 ALTER TABLE `chitietsp` DISABLE KEYS */;
INSERT INTO `chitietsp` VALUES (52,1,7,1,1,'Đồng hồ nam Carnival G78901.103.313',3840000,1,100,'2018-10-21 23:36:09','2018-11-17 01:15:48','Đặc điểm nổi bật','Automatic (máy cơ)','Nam','Hardlex','Thép không ghỉ','39mm x 39mm','9mm','3 ATM','2 năm','ok','images/1.jpeg','Mô tả thêm'),(53,1,7,1,2,'Đồng hồ nam Carnival G78901.103.616',1760000,1,100,'2018-10-22 23:36:09','2018-11-17 01:15:44','Đặc điểm nổi bật','Quartz (chạy pin)','Nam','Sapphire chống xước','Thép không ghỉ','38mm x 38mm','6mm','3 ATM','2 năm','ok','images/3.jpeg','Mô tả thêm'),(54,1,7,1,3,'Đồng hồ nam Carnival G78901.101.616',2400000,1,100,'2018-10-26 23:35:00','2018-11-17 01:15:40','Đặc điểm nổi bật','Quartz (chạy pin)','Nam','Sapphire chống xước','Thép không ghỉ','38mm x 38mm','7mm','3 ATM','2 năm','ok','images/2.jpeg','Mô tả thêm'),(55,1,7,1,2,'Đồng hồ nam Carnival G72301.101.033',1760000,1,100,'2018-10-26 23:37:19','2018-11-17 01:15:36','Đặc điểm nổi bật','Automatic (máy cơ)','Nam','Hardlex','Thép không ghỉ','39mm x 39mm','9mm','3 ATM','2 năm','ok','images/4.jpeg','Mô tả thêm'),(56,1,7,1,1,'Đồng hồ nam Carnival G72301.101.733',3840000,1,100,'2018-10-26 22:37:32','2018-11-17 01:15:31','Đặc điểm nổi bật','Quartz (chạy pin)','Nam','Sapphire chống xước','Thép không ghỉ','38mm x 38mm','6mm','3 ATM','2 năm','ok','images/2.jpeg','Mô tả thêm'),(57,1,7,1,3,'Đồng hồ nam Carnival G72301.102.032',2400000,1,100,'2018-09-21 12:36:09','2018-11-17 01:15:26','Đặc điểm nổi bật','Automatic (máy cơ)','Nam','Sapphire chống xước','Thép không ghỉ','39mm x 39mm','6mm','3 ATM','2 năm','ok','images/5.jpeg','Mô tả thêm'),(58,1,7,1,3,'Đồng hồ nam Carnival G72301.102.732',3840000,1,100,'2018-08-15 15:30:32','2018-11-17 01:15:22','Đặc điểm nổi bật','Quartz (chạy pin)','Nam','Sapphire chống xước','Thép không ghỉ','38mm x 38mm','6mm','3 ATM','2 năm','ok','images/2.jpeg','Mô tả thêm'),(59,1,7,1,2,'Đồng hồ nam Carnival G72301.104.032',4160000,1,100,'2018-08-15 15:30:32','2018-11-17 01:15:18','Đặc điểm nổi bật','Quartz (chạy pin)','Nam','Hardlex','Thép không ghỉ','39mm x 39mm','9mm','3 ATM','2 năm','ok','images/3.jpeg','Mô tả thêm'),(60,1,7,1,1,'Đồng hồ nam Carnival G70802.202.032',3840000,1,100,'2018-09-21 12:36:09','2018-11-17 01:15:13','Đặc điểm nổi bật','Automatic (máy cơ)','Nam','Sapphire chống xước','Thép không ghỉ','38mm x 38mm','7mm','3 ATM','2 năm','ok','images/5.jpeg','Mô tả thêm'),(61,1,7,1,2,'Đồng hồ nam Carnival G70802.201.432',1760000,1,100,'2018-08-15 15:30:32','2018-11-17 01:15:09','Đặc điểm nổi bật','Automatic (máy cơ)','Nam','Sapphire chống xước','Thép không ghỉ','39mm x 39mm','9mm','3 ATM','2 năm','ok','images/4.jpeg','Mô tả thêm'),(62,1,7,1,1,'Đồng hồ nam Carnival G70802.202.432',4160000,1,100,'2018-08-15 15:30:32','2018-11-17 01:15:03','Đặc điểm nổi bật','Quartz (chạy pin)','Nam','Sapphire chống xước','Thép không ghỉ','39mm x 39mm','9mm','3 ATM','2 năm','ok','images/8.jpeg','Mô tả thêm'),(63,1,7,1,1,'Đồng hồ nam Carnival G70802.204.212',3840000,1,100,'2018-10-26 23:35:00','2018-11-17 01:14:07','Đặc điểm nổi bật','Quartz (chạy pin)','Nam','Hardlex','Thép không ghỉ','39mm x 39mm','7mm','3 ATM','2 năm','ok','images/2.jpeg','Mô tả thêm'),(64,1,7,1,3,'Đồng hồ nam Carnival G70802.201.011',4160000,1,100,'2018-10-26 23:35:00','2018-11-17 01:14:00','Đặc điểm nổi bật','Automatic (máy cơ)','Nam','Hardlex','Thép không ghỉ','39mm x 39mm','9mm','3 ATM','2 năm','ok','images/7.jpeg','Mô tả thêm'),(65,2,9,1,1,'Đồng hồ nữ Carnival G70802.200.010',980000,1,100,'2018-10-26 23:35:00','2018-11-17 01:24:10','Đặc điểm nổi bật','Automatic (máy cơ)','Nữ','Sapphire chống xước','Thép không ghỉ','39mm x 39mm','9mm','3 ATM','2 năm','ok','images/1.jpeg','Mô tả thêm'),(66,3,NULL,1,1,'Đồng hồ cơ Carnival G70802.182.100',1500000,1,100,'2018-10-26 23:35:00','2018-11-17 01:24:00','Đặc điểm nổi bật','Automatic (máy cơ)','Nữ','Sapphire chống xước','Thép không ghỉ','39mm x 39mm','9mm','3 ATM','2 năm','ok','images/6.jpeg','Mô tả thêm'),(67,1,8,2,1,'đồng hồ IW G70801.123.321',23000000,1,100,'2018-10-26 23:35:00','2018-11-17 01:13:32','Đặc điểm nổi bật','Automatic (máy cơ)','Nữ','Sapphire chống xước','Thép không ghỉ','39mm x 39mm','9mm','3 ATM','2 năm','ok','images/5.jpeg','Mô tả thêm'),(68,1,8,2,2,'đồng hồ IW G10801.123.321',1852000,1,100,'2018-08-15 15:30:32','2018-11-17 01:11:57','Đặc điểm nổi bật','Automatic (máy cơ)','Nữ','Sapphire chống xước','Thép không ghỉ','39mm x 39mm','9mm','3 ATM','2 năm','ok','images/1.jpeg','Mô tả thêm'),(69,3,NULL,2,1,'đồng hồ cơ x1',2500000,1,100,'2018-11-19 05:09:34',NULL,'không','Automatic (máy cơ)','Nam','Hardlex','kim loại','35mm x 35mm','10mm','3 aTM','2 năm','ok','images/3.jpeg',''),(70,3,NULL,2,1,'đồng hồ cơ x2',2500000,1,100,'2018-11-19 05:09:58',NULL,'không','Automatic (máy cơ)','Nam','Hardlex','kim loại','35mm x 35mm','10mm','3 aTM','2 năm','ok','images/2.jpeg',''),(71,3,NULL,2,1,'đồng hồ cơ x3',2500000,1,100,'2018-11-19 05:10:44',NULL,'không','Automatic (máy cơ)','Nam','Hardlex','kim loại','35mm x 35mm','10mm','3 aTM','2 năm','ok','images/3.jpeg',''),(72,3,NULL,2,1,'đồng hồ cơ x4',2500000,1,100,'2018-11-19 05:11:17',NULL,'không','Automatic (máy cơ)','Nam','Hardlex','kim loại','35mm x 35mm','10mm','3 aTM','2 năm','ok','images/1.jpeg','không'),(73,3,NULL,2,1,'đồng hồ cơ x5',2500000,1,100,'2018-11-19 05:11:43',NULL,'không','Automatic (máy cơ)','Nam','Hardlex','kim loại','35mm x 35mm','10mm','3 aTM','2 năm','ok','images/1.jpeg',''),(74,1,7,1,1,'ĐỒNG HỒ NAM CARNIVAL x1',2500000,1,100,'2018-11-19 06:01:41',NULL,'không','Automatic (máy cơ)','Nam','Hardlex','kim loại','35mm x 35mm','10mm','3 aTM','2 năm','ok','images/5.jpeg',''),(75,1,7,1,1,'ĐỒNG HỒ NAM CARNIVAL x2',2500000,1,100,'2018-11-19 06:02:05',NULL,'không','Automatic (máy cơ)','Nam','Hardlex','kim loại','35mm x 35mm','10mm','3 aTM','2 năm','ok','images/3.jpeg',''),(76,1,7,1,1,'ĐỒNG HỒ NAM CARNIVAL x3',2500000,1,100,'2018-11-19 06:02:56',NULL,'không','Automatic (máy cơ)','Nam','Sapphire chống xước','kim cương','35mm x 35mm','10mm','3 aTM','2 năm','ok','images/2.jpeg',''),(77,1,7,1,1,'ĐỒNG HỒ NAM CARNIVAL x4',2500000,1,100,'2018-11-19 06:04:27','2018-11-19 08:29:34','không','Automatic (máy cơ)','Nam','Sapphire chống xước','kim loại','35mm x 35mm','10mm','3 aTM','2 năm','giamgia','images/1.jpeg',''),(78,1,7,1,1,'ĐỒNG HỒ NAM CARNIVAL x5',3200000,1,100,'2018-11-19 06:04:58','2018-11-25 19:10:54','không','Quartz (chạy pin)','Nam','Sapphire chống xước','kim loại','35mm x 35mm','10mm','3 aTM','2 năm','hethang','images/2.jpeg','');
/*!40000 ALTER TABLE `chitietsp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `danhmucsp`
--

DROP TABLE IF EXISTS `danhmucsp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `danhmucsp` (
  `IdDanhMucSP` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `ThoiGianTao` datetime NOT NULL,
  `ThoiGianSua` datetime DEFAULT NULL,
  `sortId` int(11) NOT NULL,
  PRIMARY KEY (`IdDanhMucSP`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `danhmucsp`
--

LOCK TABLES `danhmucsp` WRITE;
/*!40000 ALTER TABLE `danhmucsp` DISABLE KEYS */;
INSERT INTO `danhmucsp` VALUES (1,'Đồng hồ nam','0000-00-00 00:00:00',NULL,1),(2,'Đồng hồ nữ','0000-00-00 00:00:00',NULL,2),(3,'Đồng hồ cơ','0000-00-00 00:00:00',NULL,3),(4,'Phụ kiện đồng hồ','0000-00-00 00:00:00',NULL,4),(5,'Dịch vụ thay dây da','0000-00-00 00:00:00',NULL,5);
/*!40000 ALTER TABLE `danhmucsp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `danhmucsp2`
--

DROP TABLE IF EXISTS `danhmucsp2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `danhmucsp2` (
  `IdDanhMucSP2` int(11) NOT NULL AUTO_INCREMENT,
  `IdDanhMucSP` int(11) NOT NULL,
  `Name` varchar(255) CHARACTER SET utf8 NOT NULL,
  `ThoiGianTao` datetime NOT NULL,
  `ThoiGianSua` datetime DEFAULT NULL,
  `sortId` int(11) NOT NULL,
  PRIMARY KEY (`IdDanhMucSP2`),
  KEY `DanhMucSP2_DanhMucSP_IdDanhMucSP` (`IdDanhMucSP`),
  CONSTRAINT `DanhMucSP2_DanhMucSP_IdDanhMucSP` FOREIGN KEY (`IdDanhMucSP`) REFERENCES `danhmucsp` (`IdDanhMucSP`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `danhmucsp2`
--

LOCK TABLES `danhmucsp2` WRITE;
/*!40000 ALTER TABLE `danhmucsp2` DISABLE KEYS */;
INSERT INTO `danhmucsp2` VALUES (7,1,'Đồng hồ nam Carnival','0000-00-00 00:00:00',NULL,1),(8,1,'Đồng hồ IW','0000-00-00 00:00:00',NULL,2),(9,2,'Đồng hồ nữ Carnival','0000-00-00 00:00:00',NULL,1),(10,5,'Dây da thường','0000-00-00 00:00:00',NULL,1),(11,5,'Dây da xịn','0000-00-00 00:00:00',NULL,2),(12,5,'Dây da cá sấu','0000-00-00 00:00:00',NULL,3);
/*!40000 ALTER TABLE `danhmucsp2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dathang`
--

DROP TABLE IF EXISTS `dathang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dathang` (
  `IdDatHang` int(11) NOT NULL AUTO_INCREMENT,
  `IdUser` int(11) DEFAULT NULL,
  `TenKhachHang` varchar(255) CHARACTER SET utf8 NOT NULL,
  `Email` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `Sdt1` double NOT NULL,
  `Sdt2` double DEFAULT NULL,
  `DiaChi` varchar(255) CHARACTER SET utf8 NOT NULL,
  `ThoiGianDatHang` datetime NOT NULL,
  `ThoiGianXacNhanDonHang` datetime DEFAULT NULL,
  `ThoiGianHoanTatDonHang` datetime DEFAULT NULL,
  `GhiChu` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `TongTien` double NOT NULL,
  `Status` varchar(255) CHARACTER SET utf8 NOT NULL,
  `MoTaThem` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`IdDatHang`),
  KEY `DatHang_Users_IdUser` (`IdUser`),
  CONSTRAINT `DatHang_Users_IdUser` FOREIGN KEY (`IdUser`) REFERENCES `users` (`IdUsername`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dathang`
--

LOCK TABLES `dathang` WRITE;
/*!40000 ALTER TABLE `dathang` DISABLE KEYS */;
INSERT INTO `dathang` VALUES (17,1,'demo mua hàng','email@gmail.com',123123,0,'địa chỉ','2018-11-17 23:02:39','2018-11-17 23:57:04','2018-11-18 00:06:37','gửi tại abc @@',7864000,'success',NULL),(18,1,'hồ văn lang','langkute@gmail.com',147258369,NULL,'hồ chí minh','2018-11-17 23:53:25',NULL,NULL,'gửi lúc 2h sáng',25640000,'deleted',NULL),(19,NULL,'test demo mua sản phẩm','demo1@gmail.com',3451521,NULL,'địa chỉ abc','2018-11-18 00:23:27',NULL,NULL,'',980000,'wait',NULL),(20,1,'Hồ Quý Ly','',154326666,NULL,'Hà Nội','2018-11-19 02:32:14','2018-11-19 02:34:18','2018-11-19 02:34:34','Giao lúc 8h sáng đến 16h chiều',1852000,'success',NULL),(21,1,'Hồ Việt Cường','cuongkute@gmail.com',9444555632,162352156,'Hồ Chí Minh','2018-11-19 02:35:24','2018-11-19 02:35:41',NULL,'',15360000,'ok',NULL),(22,1,'Vũ Văn Việt','vietabc123@gmail.com',145236135,NULL,'138 Ngô Thị Nhậm - Hà Nội','2018-11-19 03:20:38','2018-11-19 03:20:58','2018-11-19 03:21:00','',5340000,'success',NULL),(23,1,'Vũ Quế Hải','haiabc@gmail.com',154235235,NULL,'Đà Nẵng city','2018-11-19 09:42:17','2018-11-19 09:43:34','2018-11-19 09:43:37','giao hàng trước 12h',44900000,'success',NULL),(24,1,'trương ngọc long','longkute123@gmail.com',15232521,NULL,'hồ chí minh','2018-11-20 13:38:00','2018-11-20 13:38:07','2018-11-20 13:38:08','giao hàng 8h sáng',10000000,'success',NULL);
/*!40000 ALTER TABLE `dathang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gallery`
--

DROP TABLE IF EXISTS `gallery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gallery` (
  `IdGallery` int(11) NOT NULL AUTO_INCREMENT,
  `IdChiTietSP` int(11) NOT NULL,
  `ThoiGianTao` datetime NOT NULL,
  `Images` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`IdGallery`),
  KEY `Gallery_ChiTietSP_IdChiTietSP` (`IdChiTietSP`),
  CONSTRAINT `Gallery_ChiTietSP_IdChiTietSP` FOREIGN KEY (`IdChiTietSP`) REFERENCES `chitietsp` (`IdChiTietSP`)
) ENGINE=InnoDB AUTO_INCREMENT=204 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gallery`
--

LOCK TABLES `gallery` WRITE;
/*!40000 ALTER TABLE `gallery` DISABLE KEYS */;
INSERT INTO `gallery` VALUES (159,68,'2018-11-17 01:17:38','images/1.jpeg'),(160,68,'2018-11-17 01:17:38','images/2.jpeg'),(161,67,'2018-11-17 01:17:52','images/3.jpeg'),(162,67,'2018-11-17 01:17:52','images/4.jpeg'),(163,66,'2018-11-17 01:18:04','images/5.jpeg'),(164,66,'2018-11-17 01:18:04','images/6.jpeg'),(165,65,'2018-11-17 01:18:15','images/7.jpeg'),(166,65,'2018-11-17 01:18:15','images/8.jpeg'),(167,64,'2018-11-17 01:18:26','images/1.jpeg'),(168,64,'2018-11-17 01:18:26','images/2.jpeg'),(169,63,'2018-11-17 01:18:35','images/3.jpeg'),(170,63,'2018-11-17 01:18:35','images/4.jpeg'),(171,62,'2018-11-17 01:18:44','images/5.jpeg'),(172,62,'2018-11-17 01:18:44','images/6.jpeg'),(173,61,'2018-11-17 01:18:58','images/7.jpeg'),(174,61,'2018-11-17 01:18:58','images/8.jpeg'),(175,60,'2018-11-17 01:19:05','images/1.jpeg'),(176,60,'2018-11-17 01:19:05','images/2.jpeg'),(177,59,'2018-11-17 01:19:11','images/3.jpeg'),(178,59,'2018-11-17 01:19:11','images/4.jpeg'),(179,58,'2018-11-17 01:19:36','images/5.jpeg'),(180,58,'2018-11-17 01:19:36','images/6.jpeg'),(181,58,'2018-11-17 01:19:36','images/7.jpeg'),(182,57,'2018-11-17 01:19:42','images/8.jpeg'),(183,57,'2018-11-17 01:19:42','images/1.jpeg'),(184,57,'2018-11-17 01:19:42','images/2.jpeg'),(185,56,'2018-11-17 01:19:51','images/3.jpeg'),(186,56,'2018-11-17 01:19:51','images/4.jpeg'),(187,56,'2018-11-17 01:19:51','images/5.jpeg'),(188,55,'2018-11-17 01:19:58','images/6.jpeg'),(189,55,'2018-11-17 01:19:58','images/7.jpeg'),(190,55,'2018-11-17 01:19:58','images/8.jpeg'),(191,54,'2018-11-17 01:20:05','images/1.jpeg'),(192,54,'2018-11-17 01:20:05','images/2.jpeg'),(193,54,'2018-11-17 01:20:05','images/3.jpeg'),(194,53,'2018-11-17 01:20:11','images/4.jpeg'),(195,53,'2018-11-17 01:20:11','images/5.jpeg'),(196,53,'2018-11-17 01:20:11','images/6.jpeg'),(197,52,'2018-11-17 01:20:19','images/7.jpeg'),(198,52,'2018-11-17 01:20:19','images/8.jpeg'),(199,52,'2018-11-17 01:20:19','images/1.jpeg'),(202,53,'2018-11-28 17:15:54','images/2.jpeg'),(203,53,'2018-11-28 17:15:54','images/3.jpeg');
/*!40000 ALTER TABLE `gallery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hieusp`
--

DROP TABLE IF EXISTS `hieusp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hieusp` (
  `IdHieuSP` int(11) NOT NULL AUTO_INCREMENT,
  `TenHieuSP` varchar(255) CHARACTER SET utf8 NOT NULL,
  `ThoiGianTao` datetime NOT NULL,
  `ThoiGianSua` datetime DEFAULT NULL,
  `sortId` int(11) NOT NULL,
  `MoTaThem` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`IdHieuSP`),
  UNIQUE KEY `sortId_UNIQUE` (`sortId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hieusp`
--

LOCK TABLES `hieusp` WRITE;
/*!40000 ALTER TABLE `hieusp` DISABLE KEYS */;
INSERT INTO `hieusp` VALUES (1,'Hãng Carnival','2018-10-25 15:08:09','2018-11-09 14:26:31',1,''),(2,'Hãng IW','2018-10-25 15:08:09','2018-10-25 15:08:09',2,NULL);
/*!40000 ALTER TABLE `hieusp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `IdUsername` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Password2` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HoTen` varchar(255) CHARACTER SET utf8 NOT NULL,
  `GioiTinh` int(11) NOT NULL,
  `SdtUser1` double NOT NULL,
  `SdtUser2` double DEFAULT NULL,
  `DiaChi` varchar(255) CHARACTER SET utf8 NOT NULL,
  `ThoiGianTao` datetime NOT NULL,
  `ThoiGianSua` datetime DEFAULT NULL,
  `ThoiGianXoa` datetime DEFAULT NULL,
  `Role` varchar(255) CHARACTER SET utf8 NOT NULL,
  `Status` varchar(255) CHARACTER SET utf8 NOT NULL,
  `MoTaThem` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`IdUsername`),
  UNIQUE KEY `Username_UNIQUE` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Admin','4297f44b13955235245b2497399d7a93','e10adc3949ba59abbe56e057f20f883e','ngoclongxno1@gmail.com','Trương Ngọc Long',0,929638859,984504989,'Đồng Hới - Quảng Bình','2018-10-21 23:08:09','2018-11-26 15:29:27','2018-11-09 16:19:01','Administrator','ok',''),(2,'Member1','4297f44b13955235245b2497399d7a93','e10adc3949ba59abbe56e057f20f883e','member1@gmail.com','Quản lý 1',0,984504989,0,'Đà Nẵng','2018-10-27 10:08:09','2018-11-26 15:29:26','2018-11-09 16:19:01','Moderator','ok',NULL),(3,'Member2','d41d8cd98f00b204e9800998ecf8427e','d41d8cd98f00b204e9800998ecf8427e','member2@gmail.com','Quản lý 2',1,523842066,0,'Hà Nội edit 3','2018-10-25 15:08:09','2018-11-27 18:31:39','2018-11-09 16:19:01','Moderator','ok','');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-08 23:33:24

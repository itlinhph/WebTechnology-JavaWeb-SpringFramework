-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 10, 2017 at 06:53 AM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `webHust`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `UserName` char(30) COLLATE utf8_vietnamese_ci NOT NULL,
  `Password` varchar(50) COLLATE utf8_vietnamese_ci NOT NULL,
  `Name` varchar(30) COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`UserName`, `Password`, `Name`) VALUES
('admin', 'admin', 'Linh Phan');

-- --------------------------------------------------------

--
-- Table structure for table `Bill`
--

CREATE TABLE `Bill` (
  `BillId` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  `TotalMoney` int(11) NOT NULL,
  `ReceiverName` varchar(40) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `Address` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `PayOption` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `Date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `Bill`
--

INSERT INTO `Bill` (`BillId`, `UserID`, `TotalMoney`, `ReceiverName`, `Address`, `PayOption`, `Date`) VALUES
(42, 10, 1280000, 'Đào Thu Trang', 'Vĩnh Yên - Vĩnh Phúc', 'Tiền mặt', '2017-05-09 19:41:42'),
(43, 10, 49600000, 'Lĩnh Phan', 'Bách Khoa Hà Nội', 'Chuyển khoản', '2017-05-09 19:42:58'),
(44, 1, 47030000, 'Trần Lưu Ly', 'Thanh Xuân - Hà Nội', 'Tiền mặt', '2017-05-09 19:45:08'),
(45, 1, 18100000, 'Nguyễn Bình Minh', 'B1 - Bách Khoa Hà Nội', 'Chuyển khoản', '2017-05-09 19:45:54'),
(46, 24, 41820000, 'Nguyễn Bình Minh', 'Bách Khoa Hà Nội', 'Chuyển khoản', '2017-05-09 20:00:53'),
(47, 24, 36790000, 'Ngô Văn Linh', 'Bách Khoa Hà Nội', 'Chuyển khoản', '2017-05-09 20:02:23'),
(48, 25, 15660000, 'Phan Thúy Hằng', 'Nam Đàn Nghệ An', 'Tiền mặt', '2017-05-09 20:06:26'),
(49, 25, 25900000, 'Trần Thu Hồng', 'Lê Hồng Phong', 'Tiền mặt', '2017-05-09 20:07:48'),
(50, 25, 6340000, 'Đỗ Thị Thúy Trang', 'Nam Trực - Nam Định', 'Chuyển khoản', '2017-05-09 20:08:42'),
(51, 14, 29120000, 'Đặng Nguyễn Ánh', 'Thái Nguyên', 'Tiền mặt', '2017-05-09 20:10:15'),
(52, 14, 640000, 'Phan Văn Quảng', 'Nghệ An', 'Chuyển khoản', '2017-05-09 20:11:16'),
(53, 7, 19190000, 'Đỗ Thị Thu', 'Nam Trực - Nam Định', 'Tiền mặt', '2017-05-09 20:13:21'),
(54, 7, 23000000, 'Lan Hương', 'Polyme K57', 'Chuyển khoản', '2017-05-09 20:14:38'),
(55, 26, 640000, 'Nguyễn Bình Minh', 'Thanh Xuân - Hà Nội', 'Tiền mặt', '2017-05-09 21:45:51');

-- --------------------------------------------------------

--
-- Table structure for table `BillDetail`
--

CREATE TABLE `BillDetail` (
  `BillDetailId` int(11) NOT NULL,
  `BillId` int(11) NOT NULL,
  `PID` char(15) COLLATE utf8_unicode_ci NOT NULL,
  `Price` int(11) NOT NULL,
  `Quantify` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `BillDetail`
--

INSERT INTO `BillDetail` (`BillDetailId`, `BillId`, `PID`, `Price`, `Quantify`) VALUES
(59, 42, 'as-keyboard', 320000, 2),
(60, 42, 'as-headphone', 640000, 1),
(61, 43, 'laptop-dell', 14500000, 1),
(62, 43, 'laptop-apple', 18100000, 1),
(63, 43, 'tablet-ipad', 8500000, 2),
(64, 44, 'sp-galaxys7', 15340000, 1),
(65, 44, 'tablet-sony', 9700000, 1),
(66, 44, 'sp-iphone7', 21990000, 1),
(67, 45, 'laptop-apple', 18100000, 1),
(68, 46, 'as-sacduphong', 450000, 1),
(69, 46, 'as-headphone', 640000, 2),
(70, 46, 'laptop-apple', 18100000, 1),
(71, 46, 'sp-iphone7', 21990000, 1),
(72, 47, 'as-mouse', 250000, 1),
(73, 47, 'op-lung-galaxy', 50000, 1),
(74, 47, 'laptop-dell', 14500000, 1),
(75, 47, 'sp-iphone7', 21990000, 1),
(76, 48, 'sp-galaxys7', 15340000, 1),
(77, 48, 'op-lung-galaxy', 50000, 2),
(78, 48, 'speaker-pc', 220000, 1),
(79, 49, 'laptop-asus', 16200000, 1),
(80, 49, 'tablet-sony', 9700000, 1),
(81, 50, 'sp-oppoF1', 6340000, 1),
(82, 51, 'laptop-asus', 16200000, 1),
(83, 51, 'sp-sony', 12920000, 1),
(84, 52, 'as-keyboard', 320000, 2),
(85, 53, 'as-sacduphong', 450000, 1),
(86, 53, 'as-headphone', 640000, 1),
(87, 53, 'laptop-apple', 18100000, 1),
(88, 54, 'laptop-dell', 14500000, 1),
(89, 54, 'tablet-ipad', 8500000, 1),
(90, 55, 'as-headphone', 640000, 1);

-- --------------------------------------------------------

--
-- Table structure for table `Category`
--

CREATE TABLE `Category` (
  `CategoryId` char(20) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `CategoryName` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `Category`
--

INSERT INTO `Category` (`CategoryId`, `CategoryName`) VALUES
('accessories', 'Phụ Kiện'),
('laptop', 'LapTop'),
('smartphone', 'Smart Phone'),
('tablet', 'Máy tính bảng');

-- --------------------------------------------------------

--
-- Table structure for table `Products`
--

CREATE TABLE `Products` (
  `PID` char(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `PName` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `InputPrice` int(11) NOT NULL,
  `PPrice` int(11) NOT NULL,
  `Img` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `SID` int(11) NOT NULL,
  `CategoryId` char(20) COLLATE utf8_vietnamese_ci NOT NULL,
  `PDescription` varchar(500) CHARACTER SET utf32 COLLATE utf32_vietnamese_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `Products`
--

INSERT INTO `Products` (`PID`, `PName`, `InputPrice`, `PPrice`, `Img`, `SID`, `CategoryId`, `PDescription`) VALUES
('as-headphone', 'Head phone cao cấp', 520000, 640000, 'as-headphone.jpg', 1, 'accessories', '<p><br></p><ul><li> Jack cắm: 3.5 mm </li><li> Độ dài dây: 1.5 m </li><li> Phím tính năng: Micro, Nút tăng giảm âm lượng, nút nhận/kết thúc cuộc gọi </li><li> Tính năng khác: Thiết kế thời trang,chất lượng âm thanh sống động </li><li> Có khả năng gập gọn khi bạn di chuyển. </li><li> Lớp đệm tai mềm cho cảm giác thoải mái khi đeo. </li><li> Tích hợp phím chức năng tiện lợi cho người dùng</li></ul><p><br></p>'),
('as-keyboard', 'Bàn phím Newmen E835P ', 300000, 320000, 'as-keyboard.jpg', 5, 'accessories', '<ul><li> Model: E835P </li><li> Kiểu kết nối: Có dây </li><li> Chuẩn kết nối: PS/2 </li><li> Phím chức năng: Standard </li> <li> Màu: Đen </li><li> Tính năng khác: Thiết kế gia cường, không thể tháo rời. Chữ khắc Laser chống mài mòn, phím mũi tên thiết kế công năng giúp sử dụng lâu dài. </li></ul>'),
('as-mouse', 'Chuột xịn NewMen', 190000, 250000, 'as-mouse.jpeg', 5, 'accessories', '<ul><li> Model: E835P </li><li> Kiểu kết nối: Có dây </li><li> Chuẩn kết nối: PS/2 </li><li> Phím chức năng: Standard </li> <li> Màu: Đen </li><li> Tính năng khác: Thiết kế gia cường, không thể tháo rời. Chữ khắc Laser chống mài mòn, phím mũi tên thiết kế công năng giúp sử dụng lâu dài. </li></ul>'),
('as-sacduphong', 'Sạc dự phòng', 370000, 450000, 'as-sacduphong.jpg', 6, 'accessories', '<ul><li> Hiệu suất sạc: 65% </li><li> Đèn LED báo hiệu: Có<li> Thời gian sạc: 3-4 giờ </li><li> Nguồn vào: 5V-2.1A </li><li> Cổng ra USB 1: 5V-2.1A </li><li> Cổng ra USB 2: Không. </li><li> Kích thước: Dài 9cm x ngang 5cm x 2.5cm </li><li> Tự ngắt khi sạc đầy điện thoại: Không </li><li> Trọng lượng: 200gr </li></ul>'),
('laptop-acer', 'Laptop Acer ES1 431', 9300000, 12000000, 'laptop-acer.jpeg', 1, 'laptop', '<ul><li>CPU: Intel, Celeron, N3050, 1.60 GHz</li><li>RAM: DDR3 (on board), 2 GB, 1600 MHz</li><li>Đĩa cứng: HDD, 500 GB</li><li>Màn hình rộng: 14 inch, HD (1366 x 768 pixels)</li><li>Cảm ứng: Không</li><li>Đồ họa: Integrated Graphics, Share (Dùng chung bộ nhớ với RAM<li>Đĩa quang: Không</li><li>Webcam: 0.3 MP</li><li>Chất liệu vỏ: Vỏ nhựa</li><li>Cổng giao tiếp: HDMI, LAN (RJ45), USB 2.0, USB 3.0, VGA (D-Sub)</li></ul>'),
('laptop-apple', 'Laptop Apple Macbook MMGL2', 15900000, 18100000, 'laptop-banner.jpg', 5, 'laptop', '<ul><li> CPU: Intel, Pentium, N3710, 1.60 GHz</li><li> RAM: DDR3L (1 khe RAM), 4 GB, 1600 MHz</li>\r\n<li> Đĩa cứng: eMMC, 32 GB</li><li> Màn hình rộng: 11.6 inch, HD (1366 x 768 pixels)</li><li> Cảm ứng: Có </li>\r\n<li> Đồ họa: Intel® HD Graphics 405 </li><li> Đĩa quang: Không</li><li> Webcam: 0.9 MP(16:9)</li><li> Chất liệu vỏ: Vỏ nhựa</li> </ul>'),
('laptop-asus', 'Laptop Asus E402SA', 12450000, 16200000, 'laptop-asus.jpg', 2, 'laptop', '<ul><li>CPU: Intel, Celeron, N3050, 1.60 GHz</li><li>RAM: DDR3 (on board), 2 GB, 1600 MHz</li><li>Đĩa cứng: HDD, 500 GB</li><li>Màn hình rộng: 14 inch, HD (1366 x 768 pixels)</li><li>Cảm ứng: Không</li><li>Đồ họa: Integrated Graphics, Share (Dùng chung bộ nhớ với RAM)<li>Đĩa quang: Không</li<li>Webcam: 0.3 MP</li><li>Chất liệu vỏ: Vỏ nhựa</li><li>Cổng giao tiếp: HDMI, LAN (RJ45), USB 2.0, USB 3.0, VGA (D-Sub)</li></ul>'),
('laptop-dell', 'Laptop Dell Inspiron 3552', 8000000, 14500000, 'laptop-dell.jpg', 3, 'laptop', '<ul><li> CPU: Intel, Core i7, N3060, 1.60 GHz</li><li> RAM: DDR3L (1 khe RAM), 4 GB, 1600 MHz</li><li> Đĩa cứng: HDD, 500 GB</li><li> Màn hình rộng:15.6 inch, HD (1366 x 768 pixels)</li><li> Cảm ứng: Không</li><li> Đồ họa: Intel® HD Graphics, Share (Dùng chung bộ nhớ với RAM)</li><li> Đĩa quang: DVD Super Multi Double Layer</li><li> Webcam: 0.3 MP</li></ul>'),
('op-lung-galaxy', 'Ốp lưng Galaxy J5', 30000, 50000, 'op-lung.jpg', 4, 'accessories', '<ul class="policy"><li>Chất liệu nhựa dẻo dày, kiểu dáng thời trang và đẹp mắt.</li><li>Thiết kế vừa vặn và ôm sát thân máy.</li><li> Dễ dàng tháo/lắp ốp vào máy. </li></ul>'),
('sp-galaxys7', 'SamSung Galaxy S7', 10700000, 15340000, 'sp-galaxys7.jpg', 6, 'smartphone', '<ul><li> Màn hình: Super AMOLED, 5.5", Quad HD </li><li> Hệ điều hành: Android 6.0 (Marshmallow) </li><li> Camera sau: 12 MP </li><li> Camera trước: 5 MP </li><li> CPU: Exynos 8890 8 nhân 64-bit </li><li> RAM: 4 GB </li><li> Bộ nhớ trong: 32 GB </li><li> Hỗ trợ thẻ nhớ: 200 GB </li><li> Thẻ SIM: 2 SIM, SIM 2 chung khe thẻ nhớ, Nano SIM </li><li> Dung lượng pin: 3600 mAh </li></ul>'),
('sp-iphone7', 'Iphone 7Plus', 18500000, 21990000, 'sp-iphone7.jpg', 5, 'smartphone', '<ul><li>Màn hình: LED-backlit ÍP LCD, 5.5", Retina HD </li><li>Hệ điều hành: iOS 10 </li><li> Camera sau: Hai camera 12 MP </li><li>Camera trước: 7 MP </li><li>CPU: Apple A10 Fusion 4 nhân 64-bit </li><li> RAM: 3 GB </li><li>Bộ nhớ trong: 256 GB </li><li>Hỗ trợ thẻ nhớ: Không </li><li>Thẻ SIM: 1 SIM, Nano SIM </li><li>Dung lượng pin: 2900 mAh </li></ul>'),
('sp-oppoF1', 'Oppo F1 Plus', 5300000, 6340000, 'sp-oppoF1.png', 1, 'smartphone', '<ul><li> Màn hình: AMOLED, 5.5", Full HD </li><li> Hệ điều hành: Android 5.1 (Lollipop) </li><li> Camera sau: 13 MP </li><li> Camera trước: 16 MP </li><li> CPU: Helio P10 8 nhân 64-bit </li><li> RAM: 4 GB </li><li> Bộ nhớ trong: 64 GB </li><li> Hỗ trợ thẻ nhớ:128 GB </li><li> Thẻ SIM: 2 SIM, SIM 2 chung khe thẻ nhớ, Nano SIM </li><li> Dung lượng pin: 2850mAh </li></ul>'),
('sp-sony', 'Sony Xperia ZenFone4', 9560000, 12920000, 'sp-sony.jpg', 8, 'smartphone', '<ul><li> Màn hình: Super AMOLED, 5.5", Quad HD </li><li> Hệ điều hành: Android 6.0 (Marshmallow) </li><li> Camera sau: 12 MP </li><li> Camera trước: 5 MP </li><li> CPU: Exynos 8890 8 nhân 64-bit </li><li> RAM: 4 GB </li><li> Bộ nhớ trong: 32 GB </li><li> Hỗ trợ thẻ nhớ: 200 GB </li><li> Thẻ SIM: 2 SIM, SIM 2 chung khe thẻ nhớ, Nano SIM </li><li> Dung lượng pin: 3600 mAh </li></ul>'),
('speaker-pc', 'Loa vi tính Fenda U213A - 2.0', 120000, 220000, 'loamaytinh.jpg', 4, 'accessories', '<ul><li>Thiết kế đơn giản với màu đen trơn nhưng rất tinh tế và đẹp mắt.</li><li>Kích thước nhỏ gọn thuận tiện cho việc di chuyển. </li><li>Với góc nghiêng 11 độ giúp Loa phát âm thanh rộng hơn. </li><li>Kết nối dễ dàng và nhanh chóng với PC, laptop,...thông qua Jack 3.5mm. </li></ul>'),
('tablet-asus', 'Asus Tablet V5', 8600000, 11000000, 'tablet-asus.jpg', 2, 'tablet', '<ul><li> Màn hình: LED backlit LCD, 12.9" </li><li> Hệ điều hành: iOS 9 </li><li> CPU: Apple A9X 2 nhân 64-bit, 2.2 GHz </li><li> RAM: 4GB </li><li> Bộ nhớ trong: 32 GB </li><li> Camera sau: 8 MP </li><li> Camera trước: 1.2 MP </li><li> Kết nối mạng: WiFi, Không hỗ trợ 3G, Không hỗ trợ 4G </li><li> Hỗ trợ SIM: Không </li></ul>'),
('tablet-ipad', 'Ipad Pro Tablet', 7300000, 8500000, 'tablet-ipad.jpg', 5, 'tablet', '<ul><li> Màn hình: LED backlit LCD, 12.9" </li><li> Hệ điều hành: iOS 9 </li><li> CPU: Apple A9X 2 nhân 64-bit, 2.2 GHz </li><li> RAM: 4GB </li><li> Bộ nhớ trong: 32 GB </li><li> Camera sau: 8 MP </li><li> Camera trước: 1.2 MP </li><li> Kết nối mạng: WiFi, Không hỗ trợ 3G, Không hỗ trợ 4G </li><li> Hỗ trợ SIM: Không </li></ul>'),
('tablet-samsung', 'Samsung Galaxy Tablet', 6700000, 7500000, 'samsungglx-tab.png', 4, 'tablet', '<p><br></p><ul><li>Màn hình: PLS LCD, 10.1" </li><li>Hệ điều hành: Android 6.0 (Marshmallow) </li><li>CPU: Exynos 7870 8 nhân, 1.6 GHz </li><li>RAM: 3 GB </li><li>Bộ nhớ trong: 16 GB </li><li>Camera sau: 8 MP </li><li>Camera trước: 2 MP </li><li>Kết nối mạng: WiFi, 3G, 4G LTE </li><li>Hỗ trợ SIM: Nano Sim </li><li>Đàm thoại: Có </li></ul><p><br></p>'),
('tablet-sony', 'Máy tính bảng Sony', 7200000, 9700000, 'tablet-sony.jpg', 3, 'tablet', '<ul><li>Màn hình: PLS LCD, 10.1" </li><li>Hệ điều hành: Android 6.0 (Marshmallow) </li><li>CPU: Exynos 7870 8 nhân, 1.6 GHz </li><li>RAM: 3 GB </li><li>Bộ nhớ trong: 16 GB</li><li>Camera sau: 8 MP </li><li>Camera trước: 2 MP </li><li>Kết nối mạng: WiFi, 3G, 4G LTE </li><li>Hỗ trợ SIM: Nano Sim</li<li>Đàm thoại: Có </li></ul>');

-- --------------------------------------------------------

--
-- Table structure for table `Suppliers`
--

CREATE TABLE `Suppliers` (
  `SID` int(11) NOT NULL,
  `SName` varchar(30) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `SAddress` varchar(50) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `Suppliers`
--

INSERT INTO `Suppliers` (`SID`, `SName`, `SAddress`) VALUES
(1, 'ACER', 'Hoa Kỳ'),
(2, 'ASUS', 'Trung Quốc'),
(3, 'DELL', 'Hoa Kỳ'),
(4, 'SAMSUNG', 'Hàn Quốc'),
(5, 'APPLE', 'Hoa Kỳ'),
(6, 'NEWMEN', 'Việt Nam'),
(8, 'SONY', 'Nhật Bản');

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE `User` (
  `UserID` int(11) NOT NULL,
  `UserName` varchar(30) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `PassWord` varchar(50) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `FullName` varchar(40) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `Email` varchar(40) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `Address` varchar(50) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `Phone` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`UserID`, `UserName`, `PassWord`, `FullName`, `Email`, `Address`, `Phone`) VALUES
(1, 'tranluuly', '202cb962ac59075b964b07152d234b70', 'Trần Lưu Ly', 'linhphan@gmail.com', 'Nghệ An', 964976895),
(3, 'linhphanhust', '4297f44b13955235245b2497399d7a93', 'Lĩnh Phan', 'user@gmail.com', 'Hà Nội', 974345095),
(4, 'daothutrang', '202cb962ac59075b964b07152d234b70', 'Đào Thu Trang', 'trangmit28@gmail.com', 'Nam Định', 912799546),
(5, 'doanvietdung', '7c880b352b74b3ddd49f67891ad717c9', 'Đoàn VIệt Dũng', 'aido@gmail.com', 'Vĩnh Phúc', 912334332),
(6, 'u19vietnam', '900150983cd24fb0d6963f7d28e17f72', 'Trần Thành U19', 'u19vietnam@gmail.com', 'Viet Nam', 979497460),
(7, 'test123', '202cb962ac59075b964b07152d234b70', 'Đỗ Thị Thúy Trang', 'trangtrang@yahoo.com', 'United State', 931313232),
(9, 'test1233', 'e034fb6b66aacc1d48f445ddfb08da98', 'Sơn Tùng ATM', 'asd@mail', 'Viet Nam', 123456789),
(10, 'LinhPhan', '81dc9bdb52d04dc20036dbd8313ed055', 'Lĩnh Phan', 'linhphan@gmail.com', 'Nam Đàn', 1636764157),
(14, 'dangnguyenanh', '202cb962ac59075b964b07152d234b70', 'Đặng Nguyễn Ánh', '123@gmail.com', 'Đống Đa - Hà Nội', 123445552),
(17, 'admin', 'c561ed8f4cf2d3f2e19c4f1c898cfbcb', 'Lĩnh Phan', 'admin@yahoo.com', 'Bách Khoa', 123435323),
(20, 'nguyenhongquan', '4297f44b13955235245b2497399d7a93', 'Nguyễn Hồng Quân', 'nguyenhongquan@gmail.com', 'TP. HCM', 989213322),
(24, 'nguyenbinhminh', '797e5a982eadcdc4772ccb4293d7e2a6', 'Nguyễn Bình Minh', 'minhnb@soict.hust.edu.vn', 'Bách Khoa Hà Nội', 98323234),
(25, 'linhphanabc', 'da446032ceaef184624aa8b64834c1d0', 'Lĩnh Phan', 'linhphanhust@gmail.com', 'Nghệ An', 93283244),
(26, 'linhphanacc', '220466675e31b9d20c051d5e57974150', 'Linh Phan', 'hotgatsonglam@gmail.com', 'CNTT BK', 964976895);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`UserName`);

--
-- Indexes for table `Bill`
--
ALTER TABLE `Bill`
  ADD PRIMARY KEY (`BillId`),
  ADD KEY `Key2_bill` (`UserID`);

--
-- Indexes for table `BillDetail`
--
ALTER TABLE `BillDetail`
  ADD PRIMARY KEY (`BillDetailId`),
  ADD KEY `Key3_BillDetail` (`PID`),
  ADD KEY `KEY2_BillDetail` (`BillId`);

--
-- Indexes for table `Category`
--
ALTER TABLE `Category`
  ADD PRIMARY KEY (`CategoryId`);

--
-- Indexes for table `Products`
--
ALTER TABLE `Products`
  ADD PRIMARY KEY (`PID`),
  ADD KEY `KEY2_Product` (`SID`),
  ADD KEY `KEY3_Product` (`CategoryId`);

--
-- Indexes for table `Suppliers`
--
ALTER TABLE `Suppliers`
  ADD PRIMARY KEY (`SID`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`UserID`),
  ADD UNIQUE KEY `UserName` (`UserName`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Bill`
--
ALTER TABLE `Bill`
  MODIFY `BillId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;
--
-- AUTO_INCREMENT for table `BillDetail`
--
ALTER TABLE `BillDetail`
  MODIFY `BillDetailId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=91;
--
-- AUTO_INCREMENT for table `Suppliers`
--
ALTER TABLE `Suppliers`
  MODIFY `SID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `User`
--
ALTER TABLE `User`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `Bill`
--
ALTER TABLE `Bill`
  ADD CONSTRAINT `Key2_bill` FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `BillDetail`
--
ALTER TABLE `BillDetail`
  ADD CONSTRAINT `KEY2_BillDetail` FOREIGN KEY (`BillId`) REFERENCES `Bill` (`BillId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Key3_BillDetail` FOREIGN KEY (`PID`) REFERENCES `Products` (`PID`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `Products`
--
ALTER TABLE `Products`
  ADD CONSTRAINT `KEY2_Product` FOREIGN KEY (`SID`) REFERENCES `Suppliers` (`SID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `Key3_Product` FOREIGN KEY (`CategoryId`) REFERENCES `Category` (`CategoryId`) ON DELETE NO ACTION ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

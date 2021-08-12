-- --------------------------------------------------------
-- Host:                         resourcesharing.cmi5x0wj5lza.ap-southeast-1.rds.amazonaws.com
-- Server version:               Microsoft SQL Server 2014 (SP3-CU4) (KB4500181) - 12.0.6329.1
-- Server OS:                    Windows NT 6.3 <X64> (Build 14393: ) (Hypervisor)
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES  */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table ResourceSharing.Account
CREATE TABLE IF NOT EXISTS "Account" (
	"Email" VARCHAR(50) NOT NULL COLLATE 'SQL_Latin1_General_CP1_CI_AS',
	"Password" VARCHAR(50) NOT NULL COLLATE 'SQL_Latin1_General_CP1_CI_AS',
	"Name" VARCHAR(50) NOT NULL COLLATE 'SQL_Latin1_General_CP1_CI_AS',
	"Address" VARCHAR(50) NOT NULL COLLATE 'SQL_Latin1_General_CP1_CI_AS',
	"Phone" INT NOT NULL,
	"CreateDate" DATETIME NOT NULL,
	"RoleId" INT NOT NULL,
	"StatusId" INT NOT NULL,
	PRIMARY KEY ("Email"),
	FOREIGN KEY INDEX "FK_Account_Role" ("RoleId"),
	FOREIGN KEY INDEX "FK_Account_Status2" ("StatusId"),
	CONSTRAINT "FK_Account_Role" FOREIGN KEY ("RoleId") REFERENCES "Role" ("RoleId") ON UPDATE NO_ACTION ON DELETE NO_ACTION,
	CONSTRAINT "FK_Account_Status2" FOREIGN KEY ("StatusId") REFERENCES "Status" ("StatusId") ON UPDATE NO_ACTION ON DELETE NO_ACTION
);

-- Dumping data for table ResourceSharing.Account: -1 rows
/*!40000 ALTER TABLE "Account" DISABLE KEYS */;
INSERT INTO "Account" ("Email", "Password", "Name", "Address", "Phone", "CreateDate", "RoleId", "StatusId") VALUES
	('employee@gmail.com', '1', 'Employee', 'HCM', 1, '2021-08-09 00:00:00.000', 3, 2),
	('employee1@gmail.com', '1', 'Thanh Loc', 'Ho Chi Minh', 1231231, '2021-08-10 20:31:54.000', 3, 2),
	('employee2@gmail.com', '123', 'Tien Linh', 'Ha Long', 374442123, '2021-08-10 00:00:00.000', 3, 1),
	('kusmile15@gmail.com', '1', 'Ngo Thanh Loc Manager', 'Ho Chi Minh', 374412894, '2021-08-09 00:00:00.000', 3, 2),
	('kusmile1501@gmail.com', '123123', '037123123', 'HCM', 1, '2021-08-10 01:20:55.580', 1, 1),
	('leader@gmail.com', '1', 'Leader', 'Ho Chi Minh', 374412894, '2021-08-09 00:00:00.000', 2, 2),
	('locntse130721@fpt.edu.vn', '123456', 'locntse130721@fpt.edu.vn', 'Ho Chi Minh', 0, '2021-08-10 00:00:00.000', 3, 2),
	('manager@gmail.com', '1', 'Manager', 'HCM', 1, '2021-08-09 00:00:00.000', 1, 2);
/*!40000 ALTER TABLE "Account" ENABLE KEYS */;

-- Dumping structure for table ResourceSharing.Booking
CREATE TABLE IF NOT EXISTS "Booking" (
	"BookingId" INT NOT NULL,
	"DateCreate" DATETIME NOT NULL,
	"DateBookingFrom" DATETIME NOT NULL,
	"DateBookingTo" DATETIME NOT NULL,
	"StatusId" INT NOT NULL,
	"Email" VARCHAR(50) NOT NULL COLLATE 'SQL_Latin1_General_CP1_CI_AS',
	PRIMARY KEY ("BookingId"),
	FOREIGN KEY INDEX "FK_Booking_Account" ("Email"),
	CONSTRAINT "FK_Booking_Account" FOREIGN KEY ("Email") REFERENCES "Account" ("Email") ON UPDATE NO_ACTION ON DELETE NO_ACTION
);

-- Dumping data for table ResourceSharing.Booking: -1 rows
/*!40000 ALTER TABLE "Booking" DISABLE KEYS */;
INSERT INTO "Booking" ("BookingId", "DateCreate", "DateBookingFrom", "DateBookingTo", "StatusId", "Email") VALUES
	(1, '2021-08-10 00:00:00.000', '2021-08-10 00:00:00.000', '2021-08-15 00:00:00.000', 2, 'kusmile15@gmail.com'),
	(2, '2021-08-09 00:00:00.000', '2021-08-09 00:00:00.000', '2021-08-09 00:00:00.000', 2, 'kusmile15@gmail.com'),
	(3, '2021-08-09 00:00:00.000', '2021-08-09 00:00:00.000', '2021-08-09 00:00:00.000', 2, 'kusmile15@gmail.com'),
	(4, '2021-08-09 00:00:00.000', '2021-08-09 00:00:00.000', '2021-08-09 00:00:00.000', 2, 'kusmile15@gmail.com'),
	(5, '2021-08-09 00:00:00.000', '2021-08-09 00:00:00.000', '2021-08-09 00:00:00.000', 2, 'kusmile15@gmail.com'),
	(6, '2021-08-09 00:00:00.000', '2021-08-09 00:00:00.000', '2021-08-09 00:00:00.000', 4, 'kusmile15@gmail.com'),
	(7, '2021-08-09 00:00:00.000', '2021-08-09 00:00:00.000', '2021-08-09 00:00:00.000', 4, 'kusmile15@gmail.com'),
	(8, '2021-08-09 00:00:00.000', '2021-08-09 00:00:00.000', '2021-08-09 00:00:00.000', 3, 'kusmile15@gmail.com'),
	(9, '2021-08-09 00:00:00.000', '2021-08-09 00:00:00.000', '2021-08-09 00:00:00.000', 3, 'kusmile15@gmail.com'),
	(10, '2021-08-09 00:00:00.000', '2021-08-09 00:00:00.000', '2021-08-09 00:00:00.000', 3, 'kusmile15@gmail.com'),
	(11, '2021-08-09 00:00:00.000', '2021-08-09 00:00:00.000', '2021-08-09 00:00:00.000', 4, 'kusmile15@gmail.com'),
	(12, '2021-08-09 00:00:00.000', '2021-08-09 00:00:00.000', '2021-08-09 00:00:00.000', 2, 'kusmile15@gmail.com'),
	(13, '2021-08-09 00:00:00.000', '2021-08-09 00:00:00.000', '2021-08-09 00:00:00.000', 3, 'kusmile15@gmail.com'),
	(14, '2021-08-10 00:00:00.000', '2021-08-10 00:00:00.000', '2021-08-10 00:00:00.000', 4, 'employee@gmail.com'),
	(15, '2021-08-10 00:00:00.000', '2021-08-11 00:00:00.000', '2021-08-16 00:00:00.000', 4, 'employee@gmail.com'),
	(16, '2021-08-10 00:00:00.000', '2021-08-12 00:00:00.000', '2021-08-18 00:00:00.000', 4, 'employee@gmail.com'),
	(17, '2021-08-10 00:00:00.000', '2021-08-11 00:00:00.000', '2021-08-13 00:00:00.000', 4, 'employee@gmail.com'),
	(18, '2021-08-10 00:00:00.000', '2021-08-12 00:00:00.000', '2021-08-18 00:00:00.000', 4, 'employee@gmail.com'),
	(19, '2021-08-10 00:00:00.000', '2021-08-12 00:00:00.000', '2021-08-19 00:00:00.000', 4, 'employee@gmail.com'),
	(22, '2021-08-10 00:00:00.000', '2021-08-12 00:00:00.000', '2021-08-18 00:00:00.000', 4, 'employee@gmail.com'),
	(23, '2021-08-10 00:00:00.000', '2021-08-12 00:00:00.000', '2021-08-22 00:00:00.000', 2, 'employee@gmail.com'),
	(24, '2021-08-10 00:00:00.000', '2021-08-12 00:00:00.000', '2021-08-20 00:00:00.000', 2, 'leader@gmail.com'),
	(25, '2021-08-10 00:00:00.000', '2021-08-12 00:00:00.000', '2021-08-18 00:00:00.000', 2, 'locntse130721@fpt.edu.vn'),
	(26, '2021-08-10 00:00:00.000', '2021-08-12 00:00:00.000', '2021-08-19 00:00:00.000', 4, 'employee@gmail.com'),
	(27, '2021-08-10 00:00:00.000', '2021-08-12 00:00:00.000', '2021-08-18 00:00:00.000', 4, 'employee@gmail.com'),
	(28, '2021-08-11 00:00:00.000', '2021-08-13 00:00:00.000', '2021-08-19 00:00:00.000', 1, 'employee@gmail.com'),
	(29, '2021-08-11 00:00:00.000', '2021-08-13 00:00:00.000', '2021-08-20 00:00:00.000', 2, 'leader@gmail.com');
/*!40000 ALTER TABLE "Booking" ENABLE KEYS */;

-- Dumping structure for table ResourceSharing.BookingDetail
CREATE TABLE IF NOT EXISTS "BookingDetail" (
	"BookingDetailId" INT NOT NULL,
	"Amount" INT NOT NULL,
	"BookingId" INT NOT NULL,
	"ResourceId" INT NOT NULL,
	PRIMARY KEY ("BookingDetailId"),
	FOREIGN KEY INDEX "FK_BookingDetail_Booking" ("BookingId"),
	FOREIGN KEY INDEX "FK_BookingDetail_Resource1" ("ResourceId"),
	CONSTRAINT "FK_BookingDetail_Booking" FOREIGN KEY ("BookingId") REFERENCES "Booking" ("BookingId") ON UPDATE NO_ACTION ON DELETE NO_ACTION,
	CONSTRAINT "FK_BookingDetail_Resource1" FOREIGN KEY ("ResourceId") REFERENCES "Resource" ("ResourceId") ON UPDATE NO_ACTION ON DELETE NO_ACTION
);

-- Dumping data for table ResourceSharing.BookingDetail: -1 rows
/*!40000 ALTER TABLE "BookingDetail" DISABLE KEYS */;
INSERT INTO "BookingDetail" ("BookingDetailId", "Amount", "BookingId", "ResourceId") VALUES
	(2, 1, 6, 24),
	(3, 2, 7, 24),
	(4, 5, 8, 24),
	(5, 3, 9, 24),
	(6, 4, 10, 24),
	(7, 2, 10, 25),
	(8, 7, 11, 24),
	(9, 2, 11, 25),
	(10, 6, 12, 24),
	(11, 2, 13, 24),
	(12, 3, 14, 33),
	(13, 3, 14, 27),
	(14, 1, 15, 36),
	(15, 1, 15, 24),
	(16, 1, 16, 32),
	(17, 1, 17, 32),
	(18, 2, 18, 28),
	(19, 2, 18, 29),
	(20, 2, 19, 26),
	(21, 2, 22, 28),
	(22, 2, 23, 32),
	(23, 1, 24, 33),
	(24, 2, 24, 36),
	(25, 1, 25, 36),
	(26, 2, 25, 24),
	(27, 2, 26, 27),
	(28, 2, 26, 29),
	(29, 2, 27, 32),
	(30, 2, 27, 33),
	(31, 3, 28, 24),
	(32, 1, 29, 36),
	(33, 1, 29, 43);
/*!40000 ALTER TABLE "BookingDetail" ENABLE KEYS */;

-- Dumping structure for table ResourceSharing.Resource
CREATE TABLE IF NOT EXISTS "Resource" (
	"ResourceId" INT NOT NULL,
	"ItemName" NVARCHAR(50) NOT NULL COLLATE 'SQL_Latin1_General_CP1_CI_AS',
	"Category" NVARCHAR(50) NOT NULL COLLATE 'SQL_Latin1_General_CP1_CI_AS',
	"Quantity" INT NOT NULL,
	"Color" NVARCHAR(50) NOT NULL COLLATE 'SQL_Latin1_General_CP1_CI_AS',
	"HighestOfRole" NVARCHAR(50) NOT NULL COLLATE 'SQL_Latin1_General_CP1_CI_AS',
	"StatusId" INT NOT NULL,
	"DateFrom" DATETIME NULL DEFAULT NULL,
	"DateTo" DATETIME NULL DEFAULT NULL,
	PRIMARY KEY ("ResourceId")
);

-- Dumping data for table ResourceSharing.Resource: -1 rows
/*!40000 ALTER TABLE "Resource" DISABLE KEYS */;
INSERT INTO "Resource" ("ResourceId", "ItemName", "Category", "Quantity", "Color", "HighestOfRole", "StatusId", "DateFrom", "DateTo") VALUES
	(24, 'laptop 1', 'laptop', 20, 'black', '1', 2, '2021-08-15 00:00:00.000', '2021-08-20 00:00:00.000'),
	(25, 'laptop 2', 'laptop', 12, 'black', '1', 2, '2021-08-15 00:00:00.000', '2021-08-20 00:00:00.000'),
	(26, 'laptop 3', 'laptop', 10, 'black', '1', 2, '2021-08-15 00:00:00.000', '2021-08-20 00:00:00.000'),
	(27, 'laptop 4', 'laptop', 7, 'black', '1', 2, '2021-08-15 00:00:00.000', '2021-08-20 00:00:00.000'),
	(28, 'laptop 5', 'laptop', 8, 'black', '1', 2, '2021-08-15 00:00:00.000', '2021-08-20 00:00:00.000'),
	(29, 'laptop 6', 'laptop', 8, 'black', '1', 2, '2021-08-15 00:00:00.000', '2021-08-20 00:00:00.000'),
	(30, 'laptop 7', 'laptop', 12, 'black', '1', 2, '2021-08-15 00:00:00.000', '2021-08-20 00:00:00.000'),
	(31, 'laptop 8', 'laptop', 12, 'black', '1', 2, '2021-08-15 00:00:00.000', '2021-08-20 00:00:00.000'),
	(32, 'laptop 9', 'laptop', 6, 'black', '1', 2, '2021-08-15 00:00:00.000', '2021-08-20 00:00:00.000'),
	(33, 'laptop 10', 'laptop', 6, 'black', '1', 2, '2021-08-15 00:00:00.000', '2021-08-20 00:00:00.000'),
	(36, 'laptop 1', 'laptop', 7, 'blue', '1', 2, '2021-08-13 07:38:24.000', '2021-08-20 07:38:29.000'),
	(43, 'laptop 1', 'laptop', 9, 'red', '1', 2, '2021-08-14 07:51:42.000', '2021-08-20 07:51:43.000'),
	(49, 'laptop 1', 'laptop', 20, 'white', '1', 2, '2021-08-12 07:50:08.000', '2021-08-21 09:50:09.000'),
	(50, 'laptop 1', 'laptop', 20, 'pink', '1', 2, '2021-08-12 07:50:08.000', '2021-08-21 09:50:09.000'),
	(51, 'laptop 1', 'laptop', 20, 'IndianRed', '1', 2, '2021-08-12 07:50:08.000', '2021-08-21 09:50:09.000'),
	(52, 'laptop 1', 'laptop', 20, 'LightCoral', '1', 2, '2021-08-12 07:50:08.000', '2021-08-21 09:50:09.000'),
	(53, 'laptop 1', 'laptop', 20, 'Salmon', '1', 2, '2021-08-12 07:50:08.000', '2021-08-21 09:50:09.000'),
	(54, 'laptop 1', 'laptop', 20, 'DarkSalmon', '1', 2, '2021-08-12 07:50:08.000', '2021-08-21 09:50:09.000'),
	(55, 'laptop 1', 'laptop', 20, 'Crimson', '1', 2, '2021-08-12 07:50:08.000', '2021-08-21 09:50:09.000'),
	(56, 'laptop 1', 'laptop', 20, 'FireBrick', '1', 2, '2021-08-12 07:50:08.000', '2021-08-21 09:50:09.000'),
	(57, 'laptop 1', 'laptop', 20, 'DarkRed', '1', 2, '2021-08-12 07:50:08.000', '2021-08-21 09:50:09.000'),
	(58, 'laptop 1', 'laptop', 20, 'LightPink', '1', 2, '2021-08-12 07:50:08.000', '2021-08-21 09:50:09.000'),
	(59, 'laptop 1', 'laptop', 20, 'HotPink', '1', 2, '2021-08-12 07:50:08.000', '2021-08-21 09:50:09.000'),
	(60, 'laptop 1', 'laptop', 20, 'DeepPink', '1', 2, '2021-08-12 07:50:08.000', '2021-08-21 09:50:09.000'),
	(61, 'laptop 1', 'laptop', 20, 'MediumVioletRed', '1', 2, '2021-08-12 07:50:08.000', '2021-08-21 09:50:09.000'),
	(62, 'laptop 1', 'laptop', 20, 'PaleVioletRed', '1', 2, '2021-08-12 07:50:08.000', '2021-08-21 09:50:09.000'),
	(63, 'laptop 1', 'laptop', 20, 'Tomato', '1', 2, '2021-08-12 07:50:08.000', '2021-08-21 09:50:09.000'),
	(64, 'laptop 1', 'laptop', 20, 'DarkOrange', '1', 2, '2021-08-12 07:50:08.000', '2021-08-21 09:50:09.000'),
	(65, 'laptop 1', 'laptop', 20, 'OrangeRed', '1', 2, '2021-08-12 07:50:08.000', '2021-08-21 09:50:09.000'),
	(66, 'laptop 1', 'laptop', 20, 'Orange', '1', 2, '2021-08-12 07:50:08.000', '2021-08-21 09:50:09.000'),
	(67, 'laptop 1', 'laptop', 20, 'Gold', '1', 2, '2021-08-12 07:50:08.000', '2021-08-21 09:50:09.000'),
	(68, 'laptop 1', 'laptop', 20, 'Yellow', '1', 2, '2021-08-12 07:50:08.000', '2021-08-21 09:50:09.000'),
	(69, 'laptop 1', 'laptop', 20, 'Moccasin', '1', 2, '2021-08-12 07:50:08.000', '2021-08-21 09:50:09.000');
/*!40000 ALTER TABLE "Resource" ENABLE KEYS */;

-- Dumping structure for table ResourceSharing.Role
CREATE TABLE IF NOT EXISTS "Role" (
	"RoleId" INT NOT NULL,
	"Name" NVARCHAR(50) NOT NULL COLLATE 'SQL_Latin1_General_CP1_CI_AS',
	PRIMARY KEY ("RoleId")
);

-- Dumping data for table ResourceSharing.Role: -1 rows
/*!40000 ALTER TABLE "Role" DISABLE KEYS */;
INSERT INTO "Role" ("RoleId", "Name") VALUES
	(1, 'manager   '),
	(2, 'leader'),
	(3, 'employee');
/*!40000 ALTER TABLE "Role" ENABLE KEYS */;

-- Dumping structure for table ResourceSharing.Status
CREATE TABLE IF NOT EXISTS "Status" (
	"StatusId" INT NOT NULL,
	"Name" VARCHAR(50) NULL DEFAULT NULL COLLATE 'SQL_Latin1_General_CP1_CI_AS',
	PRIMARY KEY ("StatusId")
);

-- Dumping data for table ResourceSharing.Status: -1 rows
/*!40000 ALTER TABLE "Status" DISABLE KEYS */;
INSERT INTO "Status" ("StatusId", "Name") VALUES
	(1, 'New'),
	(2, 'Accept'),
	(3, 'Delete'),
	(4, 'inactive');
/*!40000 ALTER TABLE "Status" ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

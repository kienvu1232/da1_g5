create database QLBS_GG
go
use QLBS_GG
go
create table TacGia(
	Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
	Ten NVARCHAR(50) DEFAULT NULL,
);
create table NSX(
	Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
	Ten NVARCHAR(50) DEFAULT NULL,
);
create table TheLoai(
	Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
	Ten NVARCHAR(50) DEFAULT NULL,
);
create table SanPham(
	Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
	IdTG UNIQUEIDENTIFIER,
	IdNSX UNIQUEIDENTIFIER,
	IdTheLoai UNIQUEIDENTIFIER,
	Ten NVARCHAR(50) DEFAULT NULL,
	GiaNhap DECIMAL(20,0) DEFAULT 0,
	GiaBan DECIMAL(20,0) DEFAULT 0,
	SoLuong INT DEFAULT 0,
	MoTa NVARCHAR(50) DEFAULT NULL,
	TrangThai INT DEFAULT 1,
	FOREIGN KEY(IdTG) REFERENCES TacGia(Id),
	FOREIGN KEY(IdNSX) REFERENCES NSX(Id),
	FOREIGN KEY(IdTheLoai) REFERENCES TheLoai(Id),
);
create table CuaHang(
	Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
	Ten NVARCHAR(50) DEFAULT NULL,
	DiaChi NVARCHAR(50) DEFAULT NULL,
	ThanhPho NVARCHAR(50) DEFAULT NULL,
	QuocGia NVARCHAR(50) DEFAULT NULL,
)

create table NhanVien(
	Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
	IdCH UNIQUEIDENTIFIER,
	Ten NVARCHAR(50) DEFAULT NULL,
	GioiTinh INT DEFAULT NULL,
	NgaySinh DATE DEFAULT NULL,
	Sdt NVARCHAR(30) DEFAULT NULL,
	DiaChi NVARCHAR(50) DEFAULT NULL,
	Email NVARCHAR(50) DEFAULT NULL,
	NgayTao DATETIME2 DEFAULT GETDATE(),
	MatKhau NVARCHAR(50) DEFAULT NULL,
	VaiTro BIT DEFAULT 1,
	TrangThai INT DEFAULT 1,
	FOREIGN KEY(IdCH) REFERENCES CuaHang(Id),
)
create table KhachHang(
	Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
	Ten NVARCHAR(50) DEFAULT NULL,
	Sdt NVARCHAR(50) DEFAULT NULL,
	DiaChi NVARCHAR(50) DEFAULT NULL,
	GioiTinh INT DEFAULT NULL,
	NgayTao DATETIME2 DEFAULT GETDATE(),
	TrangThai INT DEFAULT 1
)
create table HoaDon(
	Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
	IdNV UNIQUEIDENTIFIER,
	IdKH UNIQUEIDENTIFIER DEFAULT NULL,
	MaHD varchar(20) UNIQUE,
	NgayTao DATETIME2 DEFAULT GETDATE(),
	NgayThanhToan DATETIME2 DEFAULT GETDATE(),
	TongTien DECIMAL(20,0) DEFAULT NULL,
	GhiChu NVARCHAR(50) DEFAULT NULL,
	HinhThucThanhToan BIT DEFAULT 1,
	TrangThai INT DEFAULT 1,
	FOREIGN KEY(IdNV) REFERENCES NhanVien(Id),
	FOREIGN KEY(IdKH) REFERENCES KhachHang(Id),
)
create table HoaDonChiTiet(
	Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
	IdHD UNIQUEIDENTIFIER,
	IdSP UNIQUEIDENTIFIER,
	SoLuong INT DEFAULT 0,
	DonGia DECIMAL(20,0) DEFAULT 0,
	FOREIGN KEY(IdHD) REFERENCES HoaDon(Id),
	FOREIGN KEY(IdSP) REFERENCES SanPham(Id),
)
create table KhuyenMai(
	Id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
	TenKM NVARCHAR(50) DEFAULT NULL,
	MoTa NVARCHAR(50) DEFAULT NULL,
	NgayBatDau DATE DEFAULT NULL,
	NgayKetThuc DATE DEFAULT NULL,
	GiamGia INT DEFAULT 0,
	TrangThai INT DEFAULT 1
)

INSERT INTO TacGia(Ten) values('Kien'),('Dang'),('Tien'), ('Du')
INSERT INTO NSX(Ten) values('AA'),('BB'),('CC'), ('DD')
INSERT INTO TheLoai(Ten) values('XX'),('YY'),('KK'), ('GG')

INSERT INTO SanPham(IdTG, IdNSX, IdTheLoai, Ten, GiaNhap, GiaBan, SoLuong, MoTa) values 
('F749C3BB-1970-4CC0-B4D2-5C1BC624E6B2', '99201972-35FA-4627-9BB4-25DB3D312BFC','F69CA66A-7BFD-48B4-AAB3-29BCA8CC9332', 'Tinh tam', 100, 1000, 100, 'Khong co gi' )

INSERT INTO CuaHang(Ten, DiaChi, ThanhPho, QuocGia) values ('GG1', 'Gia Binh, Bac Ninh', 'Bac Ninh', 'Viet Nam'),('GG2', 'BTL, HN', 'HN', 'Viet Nam')

INSERT INTO NhanVien(IdCH, Ten, GioiTinh, NgaySinh, Sdt, DiaChi, Email, MatKhau, VaiTro) values 
('136663CC-428E-4C32-BE23-065B25BE3A60', 'Son', 1, '2003-03-09', '0961155488', 'Bac Ninh', 'sonsang@gmail.com', '123', 1)

select * from TacGia

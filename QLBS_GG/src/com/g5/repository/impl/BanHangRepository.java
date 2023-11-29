/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.g5.repository.impl;

import com.g5.domainmodel.SanPham;
import com.g5.repository.IBanHangRepository;
import com.g5.util.DBConnection;
import com.g5.util.JdbcHelper;
import com.g5.viewModel.HoaDonViewModel;
import com.g5.viewModel.SanPhamViewModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class BanHangRepository implements IBanHangRepository {

    @Override
    public List<SanPhamViewModel> getAllSP() {
        List<SanPhamViewModel> dsSPBH = new ArrayList<>();
        String sql = "SELECT dbo.SanPham.Id, dbo.SanPham.Ten, dbo.NSX.Ten AS TenNSX, dbo.TacGia.Ten AS TenTacGia, dbo.TheLoai.Ten AS TenTheLoai, dbo.SanPham.SoLuong, dbo.SanPham.GiaBan\n"
                + "FROM     dbo.NSX INNER JOIN\n"
                + "                  dbo.SanPham ON dbo.NSX.Id = dbo.SanPham.IdNSX INNER JOIN\n"
                + "                  dbo.TacGia ON dbo.SanPham.IdTG = dbo.TacGia.Id INNER JOIN\n"
                + "                  dbo.TheLoai ON dbo.SanPham.IdTheLoai = dbo.TheLoai.Id";
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql);
            while (rs.next()) {
                SanPhamViewModel sp = new SanPhamViewModel();
                sp.setId(rs.getString(1));
                sp.setTenSP(rs.getString(2));
                sp.setIdNSX(rs.getString(3));
                sp.setIdTG(rs.getString(4));
                sp.setIdTheLoai(rs.getString(5));
                sp.setSoLuong(rs.getInt(6));
                sp.setGiaBan(rs.getDouble(7));
                dsSPBH.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsSPBH;
    }

    @Override
    public String insertHD(HoaDonViewModel hd) {
        String sql = "INSERT INTO HoaDon(IdKH,IdNV,MaHD) VALUES (?,?,?)";
//        DBConnection.ExcuteDungna(sql,hd.getIdKH(),hd.getIdNV(),hd.getMaHD());
        try ( Connection con = JdbcHelper.openDbConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, hd.getIdKH());
            ps.setObject(2, hd.getIdNV());
            ps.setObject(3, hd.getMaHD());

            if (ps.executeUpdate() > 0) {
                return "Thêm hóa đơn thành công";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thêm hóa đơn thất bại";
    }

    @Override
    public List<HoaDonViewModel> findHD() {
        String sql = "SELECT dbo.HoaDon.Id, dbo.HoaDon.MaHD, dbo.KhachHang.Ten, dbo.NhanVien.Ten AS Expr1, dbo.HoaDon.NgayTao, dbo.HoaDon.TrangThai\n"
                + "FROM     dbo.HoaDon INNER JOIN\n"
                + "                  dbo.KhachHang ON dbo.HoaDon.IdKH = dbo.KhachHang.Id INNER JOIN\n"
                + "                  dbo.NhanVien ON dbo.HoaDon.IdNV = dbo.NhanVien.Id";
        List<HoaDonViewModel> dsHD = new ArrayList<>();
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql);
            while (rs.next()) {
                HoaDonViewModel hd = new HoaDonViewModel();
                hd.setId(rs.getString(1));
                hd.setMaHD(rs.getString(2));
                hd.setIdKH(rs.getString(3));
                hd.setIdNV(rs.getString(4));
                hd.setNgayTao(rs.getDate(5));
                hd.setTrangThai(rs.getInt(6));
                dsHD.add(hd);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsHD;
    }

    @Override
    public String findByIdKH(String idKH) {
        String sql = "select id from KhachHang where maKH = ?";
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, idKH);
            if (rs.next()) {
                String id = rs.getString("id");
                return id;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String findByIdNV(String idNV){
         String sql = "select id from NhanVien where idNV = ?";
          try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, idNV);
            if (rs.next()) {
                String id = rs.getString("id");
                return id;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BanHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

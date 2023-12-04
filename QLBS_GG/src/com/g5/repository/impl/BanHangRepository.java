/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.g5.repository.impl;

import com.g5.domainmodel.HoaDonChiTiet;
import com.g5.domainmodel.SanPham;
import com.g5.repository.IBanHangRepository;
import com.g5.util.DBConnection;
import com.g5.util.JdbcHelper;
import com.g5.viewModel.GioHangViewModel;
import com.g5.viewModel.HoaDonViewModel;
import com.g5.viewModel.SanPhamViewModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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

    public String findByIdNV(String idNV) {
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

    @Override
    public String addHDCT(HoaDonChiTiet hdct) {
        String sql = "INSERT INTO HoaDonChiTiet(IdHD,IdSP,SoLuong, DonGia) VALUES (?,?,?,?)";
        try ( Connection con = JdbcHelper.openDbConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, hdct.getIdHD());
            ps.setObject(2, hdct.getIdSP());
            ps.setObject(3, hdct.getSoLuong());
            ps.setObject(4, hdct.getDonGia());

            if (ps.executeUpdate() > 0) {
                return "Thêm hóa đơn ct thành công";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thêm hóa đơn ct thất bại";
    }

//    @Override
//    public List<GioHangViewModel> selecttBySqlGH(String sql, Object... argsObjects) {
//        List<GioHangViewModel> list = new ArrayList<>();
//        try {
//            ResultSet rs = JdbcHelper.query(sql, argsObjects);
//            while (rs.next()) {
//                GioHangViewModel entity = new GioHangViewModel();
//                entity.setId(rs.getString(1));
//                entity.setIdSP(rs.getString(2));
////                entity.setMaSP(rs.getString(3));
//                entity.setTenSP(rs.getString(3));
//                entity.setSoLuong(rs.getInt(4));
//                entity.setDonGia(rs.getDouble(5));
//                list.add(entity);
//
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(BanHangRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }
//    
    

//      public List<DauDiemMon> getAllDauDiem(String id) {
//        String sql = "select * from DauDiem_Mon where idMon = ?";
//        List<DauDiemMon> dsDauDiem = new ArrayList<>();
//        try {
//            ResultSet rs = DBConnection.getDataFromQuery(sql, id);
//            while (rs.next()) {
//                String idDauDiem = rs.getString("IdDauDiem");
//                String idMon = rs.getString("IdMon");
//                int heSo = rs.getInt("HeSo");
//                DauDiemMon dd = new DauDiemMon(idDauDiem, idMon, heSo);
//                dsDauDiem.add(dd);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DauDiemMonRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return dsDauDiem;
//    }
    
//        @Override
//    public List<GioHangViewModel> selecttBySqlGH(String sql, Object... argsObjects) {
//        List<GioHangViewModel> list = new ArrayList<>();
//        try {
//            ResultSet rs = JdbcHelper.query(sql, argsObjects);
//            while (rs.next()) {
//                GioHangViewModel entity = new GioHangViewModel();
//                entity.setId(rs.getString(1));
//                entity.setIdSP(rs.getString(2));
////                entity.setMaSP(rs.getString(3));
//                entity.setTenSP(rs.getString(3));
//                entity.setSoLuong(rs.getInt(4));
//                entity.setDonGia(rs.getDouble(5));
//                list.add(entity);
//
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(BanHangRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return list;
//    }
    
    @Override
    public List<GioHangViewModel> getGioHang(String id) {
        List<GioHangViewModel> list = new ArrayList<>();
        String sql = "select sp.Id, sp.Ten, hdct.SoLuong, sp.GiaBan from HoaDonChiTiet hdct \n"
                + "inner join HoaDon hd on hd.Id = hdct.IdHD \n"
                + "inner join SanPham sp on sp.Id = hdct.IdSP\n"
                + "where hd.MaHD = ?";
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, id);
            while (rs.next()) {
                GioHangViewModel gh = new GioHangViewModel();
                gh.setId(rs.getString(1));
                gh.setTenSP(rs.getString(2));
                gh.setSoLuong(rs.getInt(3));
                gh.setDonGia(rs.getDouble(4));
                list.add(gh);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
        
    }
    
//        @Override
//    public List<GioHangViewModel> getGioHang(String id) {
//        String sql = "select sp.Id, sp.Ten, hdct.SoLuong, sp.GiaBan from HoaDonChiTiet hdct \n"
//                + "inner join HoaDon hd on hd.Id = hdct.IdHD \n"
//                + "inner join SanPham sp on sp.Id = hdct.IdSP\n"
//                + "where hd.Id = ?";
//        return this.selecttBySqlGH(sql, "%" + id + "%");
//    }

    @Override
    public List<SanPhamViewModel> getSanPhamBH(String id) {
        List<SanPhamViewModel> list = new ArrayList<>();
        String sql = "select * from sanPham where id = ?";
        try {
            ResultSet rs = DBConnection.getDataFromQuery(sql, id);
            if (rs.next()) {
                SanPhamViewModel gh = new SanPhamViewModel();
                gh.setId(rs.getString(1));
                gh.setTenSP(rs.getString(5));
                gh.setGiaBan(rs.getDouble(7));
                gh.setSoLuong(rs.getInt(8));
                list.add(gh);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateSoLuong(String id, int soluong) {
        String sql = "update SanPham set SoLuong = ? where Id = ?";
        DBConnection.ExcuteDungna(sql, soluong, id);

    }

    @Override
    public void deleteHD(String id) {
        String sql = "delete HoaDon where id =?";
        DBConnection.ExcuteDungna(sql,id);
    }

    @Override
    public void deleteHDCT(String id) {
        String sql = "delete HoaDonChiTiet where idSP = ?";
        DBConnection.ExcuteDungna(sql,id);        
    }

}

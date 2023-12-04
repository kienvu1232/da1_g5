/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.g5.repository.impl;

import com.g5.domainmodel.SanPham;
import com.g5.repository.ISanPhamRepository;
import com.g5.util.DBConnection;
import com.g5.util.JdbcHelper;
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
public class SanPhamRepository implements ISanPhamRepository {

    @Override
    public List<SanPham> getAllSP() {
        List<SanPham> dsSP = new ArrayList<>();
        String sql = "SELECT dbo.SanPham.Id, dbo.SanPham.Ten, dbo.TacGia.Ten AS Expr1, dbo.NSX.Ten AS Expr2, dbo.TheLoai.Ten AS Expr3, dbo.SanPham.SoLuong, dbo.SanPham.GiaNhap, dbo.SanPham.GiaBan, dbo.SanPham.MoTa\n"
                + "FROM     dbo.NSX INNER JOIN\n"
                + "                  dbo.SanPham ON dbo.NSX.Id = dbo.SanPham.IdNSX INNER JOIN\n"
                + "                  dbo.TacGia ON dbo.SanPham.IdTG = dbo.TacGia.Id INNER JOIN\n"
                + "                  dbo.TheLoai ON dbo.SanPham.IdTheLoai = dbo.TheLoai.Id";
     try {
            ResultSet rs = DBConnection.getDataFromQuery(sql);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getString(1));
                sp.setTenSP(rs.getString(2));
                sp.setIdTG(rs.getString(3));
                sp.setIdNSX(rs.getString(4));
                sp.setIdTheLoai(rs.getString(5));
                sp.setSoLuong(rs.getInt(6));
                sp.setGiaNhap(rs.getDouble(7));
                sp.setGiaBan(rs.getDouble(8));
                sp.setMoTa(rs.getString(9));
                dsSP.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsSP;
    }

    @Override
    public String insertSP(SanPham sp) {
        String sql = "INSERT INTO SanPham(IdTG, IdNSX,IdTheLoai, Ten, GiaNhap, GiaBan,SoLuong, MoTa ) values (?,?,?,?,?,?,?,?)";
        try ( Connection con = JdbcHelper.openDbConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, sp.getIdTG());
            ps.setObject(2, sp.getIdNSX());
            ps.setObject(3, sp.getIdTheLoai());
            ps.setObject(4, sp.getTenSP());
            ps.setObject(5, sp.getGiaNhap());
            ps.setObject(6, sp.getGiaBan());
            ps.setObject(7, sp.getSoLuong());
            ps.setObject(8, sp.getMoTa());

            if (ps.executeUpdate() > 0) {
                return "Thêm san pham thành công";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thêm san pham thất bại";
    
    }

    @Override
    public String findByIdTG(String idTG) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String findByIdNSX(String idNSX) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String findByIdTl(String idTL) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        String sql = "delete SanPham where id =?";
        DBConnection.ExcuteDungna(sql,id);
    }

}

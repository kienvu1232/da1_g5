/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.g5.repository.impl;

import com.g5.domainmodel.NhanVien;
import com.g5.repository.INhanVienRepository;
import com.g5.util.DBConnection;
import com.g5.util.JdbcHelper;
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
public class NhanVienRepository implements INhanVienRepository {

    @Override
    public String insert(NhanVien nhanVien) {
        String sql = "INSERT INTO NhanVien(Ten, IdCH, GioiTinh, NgaySinh, Sdt, DiaChi, Email, MatKhau) values(?,?,?,?,?,?,?,?)";
        try ( Connection con = JdbcHelper.openDbConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, nhanVien.getTenNV());
            ps.setObject(2, nhanVien.getIdCH());
            ps.setObject(3, nhanVien.getGioiTinh());
            ps.setObject(4, nhanVien.getNgaySinh());
            ps.setObject(5, nhanVien.getSdt());
            ps.setObject(6, nhanVien.getDiaChi());
            ps.setObject(7, nhanVien.getEmail());
            ps.setObject(8, nhanVien.getMatKhau());

            if (ps.executeUpdate() > 0) {
                return "Thêm nhan vien thành công";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thêm nhan vien thất bại";
    
    }

    @Override
    public void delete(String id) {
        String sql ="delete NhanVien where id = ?";
        DBConnection.ExcuteDungna(sql,id);
    }

    @Override
    public void update(NhanVien nhanVien) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<NhanVien> findAll() {
        List<NhanVien> dsNhanVien = new ArrayList<>();
        String select_sql = "SELECT dbo.NhanVien.Id, dbo.NhanVien.Ten, dbo.CuaHang.Ten AS Expr1, dbo.NhanVien.GioiTinh, dbo.NhanVien.NgaySinh, dbo.NhanVien.Sdt, dbo.NhanVien.DiaChi, dbo.NhanVien.Email, dbo.NhanVien.NgayTao, dbo.NhanVien.MatKhau, \n"
                + "                  dbo.NhanVien.VaiTro\n"
                + "FROM     dbo.CuaHang INNER JOIN\n"
                + "                  dbo.NhanVien ON dbo.CuaHang.Id = dbo.NhanVien.IdCH";

        try {
            ResultSet rs = DBConnection.getDataFromQuery(select_sql);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setId(rs.getString(1));
                nv.setTenNV(rs.getString(2));
                nv.setIdCH(rs.getString(3));
                nv.setGioiTinh(rs.getInt(4));
                nv.setNgaySinh(rs.getDate(5));
                nv.setSdt(rs.getString(6));
                nv.setDiaChi(rs.getString(7));
                nv.setEmail(rs.getString(8));
                nv.setNgayTao(rs.getDate(9));
                nv.setMatKhau(rs.getString(10));
                nv.setVaiTro(rs.getBoolean(11));
                dsNhanVien.add(nv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NhanVienRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsNhanVien;
    }

}

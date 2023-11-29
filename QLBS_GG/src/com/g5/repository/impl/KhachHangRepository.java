/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.g5.repository.impl;

import com.g5.domainmodel.KhachHang;
import com.g5.repository.IKhachHangRepository;
import com.g5.util.DBConnection;
import com.g5.viewModel.SanPhamViewModel;
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
public class KhachHangRepository implements IKhachHangRepository{

    @Override
    public List<KhachHang> getAllKH() {
        List<KhachHang>dsKH = new ArrayList<>();
        String sql = "Select * from KhachHang";
         try {
            ResultSet rs = DBConnection.getDataFromQuery(sql);
            while (rs.next()) {
                KhachHang x = new KhachHang();
                x.setId(rs.getString(1));
                x.setMaKH(rs.getString(2));
                x.setTenKH(rs.getString(3));
                x.setSdt(rs.getString(4));
                x.setDiaChi(rs.getString(5));
                x.setGioiTinh(rs.getInt(6));
                x.setNgayTao(rs.getDate(7));
                dsKH.add(x);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsKH;
    }
    
}

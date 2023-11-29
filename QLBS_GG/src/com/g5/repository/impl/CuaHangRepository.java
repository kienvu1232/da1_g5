/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.g5.repository.impl;

import com.g5.domainmodel.CuaHang;
import com.g5.domainmodel.TheLoai;
import com.g5.repository.ICuaHangReposioty;
import com.g5.util.DBConnection;
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
public class CuaHangRepository implements ICuaHangReposioty{

    @Override
    public String insert(CuaHang cuaHang) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(CuaHang cuaHang) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CuaHang> findAll() {
          String select_sql = "select * from CuaHang";
        List<CuaHang> dsCuaHang = new ArrayList<>();
        try {
            ResultSet rs = DBConnection.getDataFromQuery(select_sql);
            while (rs.next()) {
                String id = rs.getString("Id");
                String tenCuaHang = rs.getString("ten");
                String diaChi = rs.getString("DiaChi");
                String thanhPho = rs.getString("ThanhPho");
                String quocGia = rs.getString("QuocGia");
                CuaHang cuaHang = new CuaHang(id, tenCuaHang, diaChi, thanhPho, quocGia);
                dsCuaHang.add(cuaHang);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CuaHangRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsCuaHang;
    }
    
}

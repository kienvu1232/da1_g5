/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.g5.repository.impl;

import com.g5.domainmodel.TheLoai;
import com.g5.repository.ITheLoaiRepository;
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
public class TheLoaiRepository implements ITheLoaiRepository{

    @Override
    public void insert(TheLoai theLoai) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String tenTL) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(TheLoai theLoai) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<TheLoai> findAll() {
         String select_sql = "select * from TheLoai";
        List<TheLoai> dsTheLoai = new ArrayList<>();
        try {
            ResultSet rs = DBConnection.getDataFromQuery(select_sql);
            while (rs.next()) {
                String id = rs.getString("Id");
                String tenTL = rs.getString("ten");
                TheLoai theLoai = new TheLoai(id, tenTL);
                dsTheLoai.add(theLoai);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TheLoaiRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsTheLoai; 
    }
    
}

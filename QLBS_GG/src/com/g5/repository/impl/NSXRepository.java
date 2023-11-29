/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.g5.repository.impl;

import com.g5.domainmodel.NSX;
import com.g5.repository.INSXRepository;
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
public class NSXRepository implements INSXRepository{

    @Override
    public void insert(NSX nsx) {
         String sql = "Insert into NSX(Ten) values (?)";
        DBConnection.ExcuteDungna(sql, nsx.getTenNSX());
    }

    @Override
    public void delete(String id) {
        String delete_sql = "delete from NSX where id = ?";
       DBConnection.ExcuteDungna(delete_sql,id);
    }

    @Override
    public void update(NSX nsx) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<NSX> findAll() {
       String select_sql = "select * from NSX";
        List<NSX> dsNSX = new ArrayList<>();
        try {
            ResultSet rs = DBConnection.getDataFromQuery(select_sql);
            while (rs.next()) {
                String id = rs.getString("Id");
                String tenTG = rs.getString("ten");
                NSX nsx = new NSX(id, tenTG);
                dsNSX.add(nsx);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NSXRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsNSX; 
    }
    
}

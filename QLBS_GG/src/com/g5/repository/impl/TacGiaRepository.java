/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.g5.repository.impl;

import com.g5.domainmodel.TacGia;
import com.g5.repository.ITacGiaRepository;
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
public class TacGiaRepository implements ITacGiaRepository{

    @Override
    public void insert(TacGia tacGia) {
        String sql = "Insert into TacGia values (?,?)";
        DBConnection.ExcuteDungna(sql, tacGia.getId(), tacGia.getTenTG());
    }

    @Override
    public void delete(String tenTG) {
       
    }

    @Override
    public void update(TacGia tacGia) {
        
    }

    @Override
    public List<TacGia> findAll() {
        String select_sql = "select * from TacGia";
        List<TacGia> dsTacGia = new ArrayList<>();
        try {
            ResultSet rs = DBConnection.getDataFromQuery(select_sql);
            while (rs.next()) {
                int idMon = rs.getInt("Id");
                String tenTG = rs.getString("tenTG");
                TacGia tacGia = new TacGia(idMon, tenTG);
                dsTacGia.add(tacGia);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TacGiaRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dsTacGia; 
    }

    @Override
    public List<TacGia> getAllTacGia(String id) {
        
        return null;
        
    }

    @Override
    public String getIdTacGia(String ten) {
        
        return null;
        
    }
    
}
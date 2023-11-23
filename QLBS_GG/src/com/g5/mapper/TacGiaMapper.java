/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.g5.mapper;

import com.g5.domainmodel.TacGia;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class TacGiaMapper {
    public TacGia mapRow(ResultSet rs){
        if(rs != null){
            try {
                int id = rs.getInt("Id");
                String ten = rs.getString("TenTG");
                TacGia tacGia = new TacGia(id, ten);
                tacGia.setId(id);
                return tacGia;
            } catch (SQLException ex) {
              ex.printStackTrace();
            }
        }
        return null;
    }
    public TacGia toDomainModel(TacGia tacGiaView){
        if(tacGiaView != null){
            TacGia result = new TacGia();
            result.setId(tacGiaView.getId());
            result.setTenTG(tacGiaView.getTenTG());
            return result;
        }
        return null;
    }
}

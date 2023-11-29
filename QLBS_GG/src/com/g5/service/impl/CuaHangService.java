/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.g5.service.impl;

import com.g5.domainmodel.CuaHang;
import com.g5.repository.impl.CuaHangRepository;
import com.g5.service.ICuaHangService;
import java.util.List;

/**
 *
 * @author admin
 */
public class CuaHangService implements ICuaHangService{
    private CuaHangRepository cuaHangRepository = new CuaHangRepository();
    
    public CuaHangService(){
        this.cuaHangRepository = new CuaHangRepository();
    }
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
       return this.cuaHangRepository.findAll();
    }
    
}

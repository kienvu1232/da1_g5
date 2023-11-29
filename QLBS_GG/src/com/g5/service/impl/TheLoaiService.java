/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.g5.service.impl;

import com.g5.domainmodel.TheLoai;
import com.g5.repository.impl.TheLoaiRepository;
import com.g5.service.ITheLoaiService;
import java.util.List;

/**
 *
 * @author admin
 */
public class TheLoaiService implements ITheLoaiService{
    private TheLoaiRepository theLoaiRepository = new TheLoaiRepository();
    
    public TheLoaiService(){
        this.theLoaiRepository = new TheLoaiRepository();
    }
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
        return theLoaiRepository.findAll();
    }
    
}

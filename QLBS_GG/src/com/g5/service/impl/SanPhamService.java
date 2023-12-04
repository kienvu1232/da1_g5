/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.g5.service.impl;

import com.g5.domainmodel.SanPham;
import com.g5.repository.impl.SanPhamRepository;
import com.g5.service.ISanPhamService;
import java.util.List;

/**
 *
 * @author admin
 */
public class SanPhamService implements ISanPhamService{
    private SanPhamRepository sanPhamRepository = new SanPhamRepository();
    
    public SanPhamService(){
        this.sanPhamRepository = new SanPhamRepository();
    }    
    @Override
    public List<SanPham> getAllSP() {
        return this.sanPhamRepository.getAllSP();
    }

    @Override
    public String insertSP(SanPham sp) {
        return this.sanPhamRepository.insertSP(sp);
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
        this.sanPhamRepository.delete(id);
    }
    
}

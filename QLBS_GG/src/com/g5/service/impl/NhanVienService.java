/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.g5.service.impl;

import com.g5.domainmodel.NhanVien;
import com.g5.repository.impl.NhanVienRepository;
import com.g5.service.INhanVienService;
import java.util.List;

/**
 *
 * @author admin
 */
public class NhanVienService implements INhanVienService{
    private NhanVienRepository nhanVienRepository = new NhanVienRepository();
    
    public NhanVienService(){
        this.nhanVienRepository = new NhanVienRepository();
    }
    @Override
    public String insert(NhanVien nhanVien) {
        return this.nhanVienRepository.insert(nhanVien);
    }

    @Override
    public void delete(String tenNV) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(NhanVien nhanVien) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<NhanVien> findAll() {
        return this.nhanVienRepository.findAll();
    }
    
}

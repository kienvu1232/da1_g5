/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.g5.service.impl;

import com.g5.domainmodel.KhachHang;
import com.g5.repository.impl.KhachHangRepository;
import com.g5.service.IKhachHangService;
import java.util.List;

/**
 *
 * @author admin
 */
public class KhachHangService implements IKhachHangService{
    private KhachHangRepository khachHangRepository = new KhachHangRepository();
    
    public KhachHangService(){
        this.khachHangRepository = new KhachHangRepository();
    }
    @Override
    public List<KhachHang> getAllKH() {
        return this.khachHangRepository.getAllKH();
    }
    
}

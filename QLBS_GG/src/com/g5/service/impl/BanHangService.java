/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.g5.service.impl;

import com.g5.domainmodel.HoaDonChiTiet;
import com.g5.domainmodel.SanPham;
import com.g5.repository.impl.BanHangRepository;
import com.g5.service.IBanHangServie;
import com.g5.util.DBConnection;
import com.g5.viewModel.GioHangViewModel;
import com.g5.viewModel.HoaDonViewModel;
import com.g5.viewModel.SanPhamViewModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author admin
 */
public class BanHangService implements IBanHangServie{
     private BanHangRepository banHangRepository = new BanHangRepository();
     
     public BanHangService(){
         this.banHangRepository = new BanHangRepository();
     }

    @Override
    public List<SanPhamViewModel> getAllSP() {
        return banHangRepository.getAllSP();
    }

    @Override
    public List<HoaDonViewModel> findHD() {
        return banHangRepository.findHD();
    }

    @Override
    public void insertHD(HoaDonViewModel hd) {
        
            this.banHangRepository.insertHD(hd);
        
    }

    @Override
    public String findByIdKH(String idKH) {
        return this.banHangRepository.findByIdKH(idKH);
    }

    @Override
    public String findByIdNV(String idNV) {
        return this.banHangRepository.findByIdNV(idNV);
    }

    @Override
    public String addHDCT(HoaDonChiTiet hdct) {
        return this.banHangRepository.addHDCT(hdct);
    }

    @Override
    public List<GioHangViewModel> getGioHang(String id) {
        return this.banHangRepository.getGioHang(id);
    }

    @Override
    public List<SanPhamViewModel> getSanPhamBH(String id) {
        return this.banHangRepository.getSanPhamBH(id);
    }

    @Override
    public void deleteHD(String id) {
        this.banHangRepository.deleteHD(id);
    }

    @Override
    public void deleteHDCT(String id) {
        this.banHangRepository.deleteHDCT(id);
    }
     
}

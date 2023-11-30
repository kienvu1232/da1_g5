/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.g5.service;

import com.g5.domainmodel.HoaDonChiTiet;
import com.g5.domainmodel.SanPham;
import com.g5.viewModel.GioHangViewModel;
import com.g5.viewModel.HoaDonViewModel;
import com.g5.viewModel.SanPhamViewModel;
import java.util.List;

/**
 *
 * @author admin
 */
public interface IBanHangServie {
    List<SanPhamViewModel>getAllSP(); 
    List<HoaDonViewModel>findHD();
    void insertHD(HoaDonViewModel hd);
    String findByIdKH(String idKH);
    String findByIdNV(String idNV);
    String addHDCT(HoaDonChiTiet hdct);
    List<GioHangViewModel> getGioHang(String id);
    
    List<SanPhamViewModel>getSanPhamBH(String id);
}

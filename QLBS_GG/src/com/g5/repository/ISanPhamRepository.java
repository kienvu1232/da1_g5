/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.g5.repository;

import com.g5.domainmodel.SanPham;
import java.util.List;

/**
 *
 * @author admin
 */
public interface ISanPhamRepository {
    List<SanPham>getAllSP();
    String insertSP(SanPham sp);
    String findByIdTG(String idTG);
    String findByIdNSX(String idNSX);
    String findByIdTl(String idTL);
}

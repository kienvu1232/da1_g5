/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.g5.service;

import com.g5.domainmodel.NhanVien;
import java.util.List;

/**
 *
 * @author admin
 */
public interface INhanVienService {
    String insert (NhanVien nhanVien);
    void delete (String tenNV);
    void update(NhanVien nhanVien);
    List<NhanVien>findAll();
}

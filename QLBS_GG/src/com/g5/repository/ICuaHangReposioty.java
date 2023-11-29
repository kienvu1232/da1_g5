/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.g5.repository;

import com.g5.domainmodel.CuaHang;
import java.util.List;

/**
 *
 * @author admin
 */
public interface ICuaHangReposioty {
    String insert (CuaHang cuaHang);
    void delete (String id);
    void update (CuaHang cuaHang);
    List<CuaHang> findAll();
}

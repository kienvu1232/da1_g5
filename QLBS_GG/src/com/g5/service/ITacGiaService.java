/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.g5.service;

import com.g5.domainmodel.TacGia;
import java.util.List;

/**
 *
 * @author admin
 */
public interface ITacGiaService {
    List<TacGia> findAll();
    List<TacGia> findAllView();
    String getTenTacGia(String id);
    String getIdTacGia(String tenTG);
    void insert(TacGia tacGia);
    void update(TacGia tacGia);
    void delete(String ten);
}

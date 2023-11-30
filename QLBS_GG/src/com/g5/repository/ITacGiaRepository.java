/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.g5.repository;

import com.g5.domainmodel.TacGia;
import java.util.List;

/**
 *
 * @author admin
 */
public interface ITacGiaRepository {
    void insert (TacGia tacGia);
    void delete (String tenTG);
    void update (TacGia tacGia);
    List<TacGia> findAll();
    TacGia findOneByName(String name);
}

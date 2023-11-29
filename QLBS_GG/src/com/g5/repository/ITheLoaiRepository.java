/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.g5.repository;

import com.g5.domainmodel.TheLoai;
import java.util.List;

/**
 *
 * @author admin
 */
public interface ITheLoaiRepository {
    void insert (TheLoai theLoai);
    void delete (String tenTL);
    void update (TheLoai theLoai);
    List<TheLoai> findAll();
}

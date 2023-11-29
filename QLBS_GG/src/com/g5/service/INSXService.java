/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.g5.service;

import com.g5.domainmodel.NSX;
import java.util.List;

/**
 *
 * @author admin
 */
public interface INSXService {
    void insert (NSX nsx);
    void delete (String id);
    void update (NSX nsx);
    List<NSX> findAll();
}

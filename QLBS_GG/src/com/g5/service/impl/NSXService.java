/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.g5.service.impl;

import com.g5.domainmodel.NSX;
import com.g5.repository.impl.NSXRepository;
import com.g5.service.INSXService;
import java.util.List;

/**
 *
 * @author admin
 */
public class NSXService implements INSXService{
    private NSXRepository nsxRepository = new NSXRepository();
    
    public NSXService(){
        this.nsxRepository = new NSXRepository();
    }
    @Override
    public void insert(NSX nsx) {
        if(nsx != null){
            NSX n = new NSX();
            n.setTenNSX(nsx.getTenNSX());
            this.nsxRepository.insert(nsx);
        }
    }

    @Override
    public void delete(String id) {
        this.nsxRepository.delete(id);
    }

    @Override
    public void update(NSX nsx) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<NSX> findAll() {
       return nsxRepository.findAll();
    }
    
}

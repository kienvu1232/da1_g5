/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.g5.service.impl;

import com.g5.domainmodel.TacGia;
import com.g5.repository.ITacGiaRepository;
import com.g5.repository.impl.TacGiaRepository;
import com.g5.service.ITacGiaService;
import java.util.List;

/**
 *
 * @author admin
 */
public class TacGiaService implements ITacGiaService{
     private ITacGiaRepository tacGiaRepository;
     public TacGiaService(){
         this.tacGiaRepository = new TacGiaRepository();
     }
    @Override
    public List<TacGia> findAll() {
        return tacGiaRepository.findAll();
    }

    @Override
    public List<TacGia> findAllView() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getTenTacGia(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getIdTacGia(String tenTG) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(TacGia tacGia) {
         if(tacGia != null){
        TacGia tg = new TacGia();
        tg.setId(tacGia.getId());
        tg.setTenTG(tacGia.getTenTG());
        this.tacGiaRepository.insert(tg);
        }
    }

    @Override
    public void update(TacGia tacGia) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String ten) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.g5.domainmodel;

/**
 *
 * @author admin
 */
public class TacGia {
    private int id;
    private String tenTG;

    public TacGia() {
    }
    
    
    public TacGia(int id, String tenTG) {
        this.id = id;
        this.tenTG = tenTG;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenTG() {
        return tenTG;
    }

    public void setTenTG(String tenTG) {
        this.tenTG = tenTG;
    }
    
    
}

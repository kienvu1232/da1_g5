/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.g5.domainmodel;

/**
 *
 * @author admin
 */
public class TheLoai {
    private String id;
    private String tenTL;

    public TheLoai() {
    }

    public TheLoai(String id, String tenTL) {
        this.id = id;
        this.tenTL = tenTL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenTL() {
        return tenTL;
    }

    public void setTenTL(String tenTL) {
        this.tenTL = tenTL;
    }
    
    @Override
    public String toString() {
        return tenTL;
    }
}

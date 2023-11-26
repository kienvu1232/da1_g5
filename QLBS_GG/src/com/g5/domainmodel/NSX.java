/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.g5.domainmodel;

/**
 *
 * @author admin
 */
public class NSX {
    private String id;
    private String tenNSX;

    public NSX() {
    }

    public NSX(String id, String tenNSX) {
        this.id = id;
        this.tenNSX = tenNSX;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenNSX() {
        return tenNSX;
    }

    public void setTenNSX(String tenNSX) {
        this.tenNSX = tenNSX;
    }
    
    
}

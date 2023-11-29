/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.g5.viewModel;

/**
 *
 * @author admin
 */
public class SanPhamViewModel {

    private String id;
    private String tenSP;
    private String idTG;
    private String idNSX;
    private String idTheLoai;  
    private double giaBan;
    private int soLuong;

    public SanPhamViewModel() {
    }

    public SanPhamViewModel(String id, String tenSP, String idTG, String idNSX, String idTheLoai, double giaBan, int soLuong) {
        this.id = id;
        this.tenSP = tenSP;
        this.idTG = idTG;
        this.idNSX = idNSX;
        this.idTheLoai = idTheLoai;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getIdTG() {
        return idTG;
    }

    public void setIdTG(String idTG) {
        this.idTG = idTG;
    }

    public String getIdNSX() {
        return idNSX;
    }

    public void setIdNSX(String idNSX) {
        this.idNSX = idNSX;
    }

    public String getIdTheLoai() {
        return idTheLoai;
    }

    public void setIdTheLoai(String idTheLoai) {
        this.idTheLoai = idTheLoai;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
   

    public Object[] todataRowSanPham() {
        return new Object[]{id, tenSP, idNSX, idTG, idTheLoai, giaBan, soLuong};
    }
}

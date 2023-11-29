/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import com.g5.domainmodel.NSX;
import com.g5.domainmodel.SanPham;
import com.g5.domainmodel.TacGia;
import com.g5.domainmodel.TheLoai;
import com.g5.service.impl.NSXService;
import com.g5.service.impl.SanPhamService;
import com.g5.service.impl.TacGiaService;
import com.g5.service.impl.TheLoaiService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author RAVEN
 */
public class SanPhamJPanel extends javax.swing.JPanel {

    /**
     * Creates new form Form_1
     */
    SanPhamService sanPhamService = new SanPhamService();
    TacGiaService tacGiaService = new TacGiaService();
    NSXService nsxService = new NSXService();
    TheLoaiService tlService = new TheLoaiService();
    List<SanPham> listSP;
    List<NSX> listNSX;
    List<TheLoai> listTL;
    List<TacGia> listTacGia;
    DefaultTableModel model;
    private DefaultComboBoxModel cbxmodel;
    
    public SanPhamJPanel() {
        initComponents();
        loadTableSP();
        loadTableTacGia();
        loadTableNSX();
//        loadCbxTg();
//        loadCbxNSX();
//        loadCbxTL();
        loadNSX((ArrayList<NSX>) nsxService.findAll());
        loadTG((ArrayList<TacGia>) tacGiaService.findAll());
        loadTL((ArrayList<TheLoai>) tlService.findAll());
    }

//    public void loadCbxTg(){
//        DefaultComboBoxModel<TacGia> modelCbx = new DefaultComboBoxModel<>();
//        listTacGia = tacGiaService.findAll();
//        for (TacGia tg : listTacGia){
//            modelCbx.addElement(tg);
//        }
//        cbxTG.setModel(modelCbx);
//    }
//    public void loadCbxNSX(){
//        DefaultComboBoxModel<NSX> modelCbx = new DefaultComboBoxModel<>();
//        listNSX = nsxService.findAll();
//        for (NSX nsx : listNSX) {
//            modelCbx.addElement(nsx);
//        }
//        cbxNSX.setModel(modelCbx);
//    }
//     public void loadCbxTL(){
//        DefaultComboBoxModel<TheLoai> modelCbx = new DefaultComboBoxModel<>();
//        listTL = tlService.findAll();
//        for (TheLoai t : listTL) {
//            modelCbx.addElement(t);
//        }
//        cbxTL.setModel(modelCbx);
//    }
    public void loadNSX(ArrayList<NSX> list) {
        cbxmodel = (DefaultComboBoxModel) cbxNSX.getModel();
        for (NSX nsx : list) {
            cbxmodel.addElement(nsx);
        }
        
    }
    public void loadTG(ArrayList<TacGia> list) {
        cbxmodel = (DefaultComboBoxModel) cbxTG.getModel();
        for (TacGia tacGia : list) {
            cbxmodel.addElement(tacGia);
        }
        
    }
    public void loadTL(ArrayList<TheLoai> list) {
        cbxmodel = (DefaultComboBoxModel) cbxTL.getModel();
        for (TheLoai theLoai : list) {
            cbxmodel.addElement(theLoai);
        }
        
    }

    public void loadTableSP() {
        listSP = sanPhamService.getAllSP();
        model = (DefaultTableModel) tableSP.getModel();
        model.setRowCount(0);
        for (SanPham x : listSP) {
            model.addRow(new Object[]{x.getId(), x.getTenSP(), x.getIdTG(), x.getIdNSX(), x.getIdTheLoai(), x.getSoLuong(), x.getGiaNhap(), x.getGiaBan(), x.getMoTa()});
        }
    }
    
    public void loadTableTacGia() {
        listTacGia = tacGiaService.findAll();
        model = (DefaultTableModel) tableTacGia.getModel();
        model.setRowCount(0);
        for (TacGia tg : listTacGia) {
            model.addRow(new Object[]{tg.getId(), tg.getTenTG()});
        }
    }
    
    public void loadTableNSX() {
        listNSX = nsxService.findAll();
        model = (DefaultTableModel) tableNSX.getModel();
        model.setRowCount(0);
        for (NSX nsx : listNSX) {
            model.addRow(new Object[]{nsx.getId(), nsx.getTenNSX()});
        }
    }

    public SanPham inputSanPham() {
        SanPham sp = new SanPham();
        sp.setTenSP(txtTenSP.getText());
        TacGia tg = (TacGia) cbxTG.getSelectedItem();
        String idTG = tg.getId();
        NSX nsx = (NSX) cbxNSX.getSelectedItem();
        String idNSX = nsx.getId();
        TheLoai tl = (TheLoai) cbxTL.getSelectedItem();
        String idTL = tl.getId();
        sp.setIdTG(idTG);
        sp.setIdNSX(idNSX);
        sp.setIdTheLoai(idTL);
        sp.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        sp.setGiaNhap(Double.parseDouble(txtGiaNhap.getText()));
        sp.setGiaBan(Double.parseDouble(txtGiaBan.getText()));
        sp.setMoTa(txtMoTa.getText());
        return sp;
    }

    public void saveSP() {
        SanPham sp = inputSanPham();
        sanPhamService.insertSP(sp);
        JOptionPane.showMessageDialog(this, "Thanh Cong");
        loadTableSP();
        
    }

    public TacGia inputTacGia() {
        TacGia tg = new TacGia();
        tg.setTenTG(txtTenTacGia.getText());
        return tg;
    }

    public NSX inputNSX() {
        NSX nsx = new NSX();
        nsx.setTenNSX(txtTenNSX.getText());
        return nsx;
    }

    public void saveNSX() {
        NSX nsx = inputNSX();
        nsxService.insert(nsx);
        JOptionPane.showMessageDialog(this, "Thanh Cong");
        loadTableNSX();
    }

    public void saveTacGia() {
        TacGia tg = inputTacGia();
        tacGiaService.insert(tg);
        JOptionPane.showMessageDialog(this, "Thanh Cong");
        loadTableTacGia();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSP = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        lblIDSP = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbxTG = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cbxNSX = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        cbxTL = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        txtGiaBan = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        btnThemSP = new javax.swing.JButton();
        btnSuaSP = new javax.swing.JButton();
        btnXoaSP = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTacGia = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTimKiemTacGia = new javax.swing.JTextField();
        btnThemTacGia = new javax.swing.JButton();
        btnSuaTacGia = new javax.swing.JButton();
        btnXoaTacGia = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtTenTacGia = new javax.swing.JTextField();
        btnTimTacGia = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lblid = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableNSX = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTimKiemNSX = new javax.swing.JTextField();
        btnThemNSX = new javax.swing.JButton();
        btnSuaNSX = new javax.swing.JButton();
        btnXoaNSX = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtTenNSX = new javax.swing.JTextField();
        btnTimNSX = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        lblidNSX = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenu3.setText("jMenu3");

        jMenu4.setText("File");
        jMenuBar1.add(jMenu4);

        jMenu5.setText("Edit");
        jMenuBar1.add(jMenu5);

        tableSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Tên sản phẩm", "Tác giả", "NSX", "Thể loại", "Số lượng", "Giá nhập", "Giá bán", "Mô tả"
            }
        ));
        tableSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableSP);

        jLabel6.setText("Tên sản phẩm");

        jLabel10.setText("ID");

        lblIDSP.setText("?");

        jLabel12.setText("Tác giả");

        cbxTG.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxTG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTGActionPerformed(evt);
            }
        });

        jLabel13.setText("NSX");

        cbxNSX.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel14.setText("Thể loại");

        cbxTL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel15.setText("Số lượng");

        jLabel16.setText("Giá nhập");

        jLabel17.setText("Giá bán");

        jLabel18.setText("Mô tả");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane4.setViewportView(txtMoTa);

        btnThemSP.setText("Thêm");
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        btnSuaSP.setText("Sửa");

        btnXoaSP.setText("Xóa");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                        .addComponent(lblIDSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cbxTL, javax.swing.GroupLayout.Alignment.LEADING, 0, 144, Short.MAX_VALUE)
                        .addComponent(cbxNSX, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbxTG, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(88, 88, 88)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGiaNhap)
                            .addComponent(txtGiaBan)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnThemSP)
                .addGap(18, 18, 18)
                .addComponent(btnSuaSP)
                .addGap(18, 18, 18)
                .addComponent(btnXoaSP)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblIDSP)
                    .addComponent(jLabel15)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel16)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbxTG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbxNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(cbxTL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemSP)
                    .addComponent(btnSuaSP)
                    .addComponent(btnXoaSP))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sản phẩm", jPanel1);

        tableTacGia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id", "Tên tác giả"
            }
        ));
        tableTacGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableTacGiaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableTacGia);

        jLabel1.setText("Id");

        jLabel3.setText("Tên tác giả");

        txtTimKiemTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemTacGiaActionPerformed(evt);
            }
        });

        btnThemTacGia.setText("Thêm");
        btnThemTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTacGiaActionPerformed(evt);
            }
        });

        btnSuaTacGia.setText("Sửa");
        btnSuaTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaTacGiaActionPerformed(evt);
            }
        });

        btnXoaTacGia.setText("Xóa");
        btnXoaTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTacGiaActionPerformed(evt);
            }
        });

        jLabel4.setText("Tìm kiếm");

        txtTenTacGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenTacGiaActionPerformed(evt);
            }
        });

        btnTimTacGia.setText("Tìm");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Quản lý tác giả");

        lblid.setText("?");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtTimKiemTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(btnTimTacGia))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(48, 48, 48)
                                        .addComponent(jLabel1)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnThemTacGia)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnSuaTacGia)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnXoaTacGia))
                                    .addComponent(txtTenTacGia, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                                    .addComponent(lblid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(50, 50, 50)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblid))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTenTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemTacGia)
                    .addComponent(btnSuaTacGia)
                    .addComponent(btnXoaTacGia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTimKiemTacGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimTacGia))
                .addGap(205, 205, 205))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tác giả", jPanel2);

        tableNSX.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id", "Tên NSX"
            }
        ));
        tableNSX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNSXMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableNSX);

        jLabel2.setText("Id");

        jLabel7.setText("Tên tác giả");

        txtTimKiemNSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemNSXActionPerformed(evt);
            }
        });

        btnThemNSX.setText("Thêm");
        btnThemNSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNSXActionPerformed(evt);
            }
        });

        btnSuaNSX.setText("Sửa");

        btnXoaNSX.setText("Xóa");
        btnXoaNSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNSXActionPerformed(evt);
            }
        });

        jLabel8.setText("Tìm kiếm");

        txtTenNSX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenNSXActionPerformed(evt);
            }
        });

        btnTimNSX.setText("Tìm");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Quản lý NSX");

        lblidNSX.setText("?");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txtTimKiemNSX, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(btnTimNSX))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(48, 48, 48)
                                        .addComponent(jLabel2)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(btnThemNSX)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnSuaNSX)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnXoaNSX))
                                    .addComponent(txtTenNSX, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                                    .addComponent(lblidNSX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(50, 50, 50)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblidNSX))
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTenNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemNSX)
                    .addComponent(btnSuaNSX)
                    .addComponent(btnXoaNSX))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTimKiemNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimNSX))
                .addGap(205, 205, 205))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("NSX", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 829, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 439, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Thể loại", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemTacGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemTacGiaActionPerformed

    private void btnThemTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTacGiaActionPerformed
        // TODO add your handling code here:
        saveTacGia();
    }//GEN-LAST:event_btnThemTacGiaActionPerformed

    private void txtTenTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenTacGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenTacGiaActionPerformed

    private void tableSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSPMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tableSPMouseClicked

    private void tableTacGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTacGiaMouseClicked
        // TODO add your handling code here:
        int selectedRow = tableTacGia.getSelectedRow();
        String ma = (String) tableTacGia.getValueAt(selectedRow, 0);
        String ten = (String) tableTacGia.getValueAt(selectedRow, 1);
        lblid.setText(ma);
        txtTenTacGia.setText(ten);
    }//GEN-LAST:event_tableTacGiaMouseClicked

    private void btnXoaTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTacGiaActionPerformed
        // TODO add your handling code here:int selectedRow = tblDauDiem.getSelectedRow();
        int selectedRow = tableTacGia.getSelectedRow();
        if (selectedRow != -1) {
            String id = (String) tableTacGia.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Ban co muon xoa khong?");
            if (confirm == JOptionPane.YES_OPTION) {
                this.tacGiaService.delete(id);
                loadTableTacGia();
            }
        }
    }//GEN-LAST:event_btnXoaTacGiaActionPerformed

    private void tableNSXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNSXMouseClicked
        // TODO add your handling code here:
        int selectedRow = tableNSX.getSelectedRow();
        String ma = (String) tableNSX.getValueAt(selectedRow, 0);
        String ten = (String) tableNSX.getValueAt(selectedRow, 1);
        lblidNSX.setText(ma);
        txtTenNSX.setText(ten);
    }//GEN-LAST:event_tableNSXMouseClicked

    private void txtTimKiemNSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemNSXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemNSXActionPerformed

    private void btnThemNSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNSXActionPerformed
        // TODO add your handling code here:
        saveNSX();
    }//GEN-LAST:event_btnThemNSXActionPerformed

    private void btnXoaNSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNSXActionPerformed
        // TODO add your handling code here:
        int selectedRow = tableNSX.getSelectedRow();
        if (selectedRow != -1) {
            String id = (String) tableNSX.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Ban co muon xoa khong?");
            if (confirm == JOptionPane.YES_OPTION) {
                this.nsxService.delete(id);
                loadTableNSX();
            }
        }
    }//GEN-LAST:event_btnXoaNSXActionPerformed

    private void txtTenNSXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenNSXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenNSXActionPerformed

    private void btnSuaTacGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaTacGiaActionPerformed
        // TODO add your handling code here:
        int selectedRow = tableTacGia.getSelectedRow();
        if (selectedRow != -1) {
            TacGia tacGia = new TacGia();
            
            if (tacGia != null) {
                int confirm = JOptionPane.showConfirmDialog(this, "Ban co muon sua khong?");
                if (confirm == JOptionPane.YES_OPTION) {
                    this.tacGiaService.update(tacGia);
                    loadTableTacGia();
                }
            }
        }
    }//GEN-LAST:event_btnSuaTacGiaActionPerformed

    private void cbxTGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTGActionPerformed

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
        // TODO add your handling code here:
        saveSP();
    }//GEN-LAST:event_btnThemSPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSuaNSX;
    private javax.swing.JButton btnSuaSP;
    private javax.swing.JButton btnSuaTacGia;
    private javax.swing.JButton btnThemNSX;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnThemTacGia;
    private javax.swing.JButton btnTimNSX;
    private javax.swing.JButton btnTimTacGia;
    private javax.swing.JButton btnXoaNSX;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.JButton btnXoaTacGia;
    private javax.swing.JComboBox<String> cbxNSX;
    private javax.swing.JComboBox<String> cbxTG;
    private javax.swing.JComboBox<String> cbxTL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblIDSP;
    private javax.swing.JLabel lblid;
    private javax.swing.JLabel lblidNSX;
    private javax.swing.JTable tableNSX;
    private javax.swing.JTable tableSP;
    private javax.swing.JTable tableTacGia;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenNSX;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTenTacGia;
    private javax.swing.JTextField txtTimKiemNSX;
    private javax.swing.JTextField txtTimKiemTacGia;
    // End of variables declaration//GEN-END:variables
}

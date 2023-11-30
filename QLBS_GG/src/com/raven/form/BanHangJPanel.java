package com.raven.form;

import com.g5.domainmodel.HoaDonChiTiet;
import com.g5.repository.impl.BanHangRepository;
import com.g5.service.impl.BanHangService;
import com.g5.viewModel.GioHangViewModel;
import com.g5.viewModel.HoaDonViewModel;
import com.g5.viewModel.SanPhamViewModel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BanHangJPanel extends javax.swing.JPanel {
    
    BanHangRepository banHangRepository = new BanHangRepository();
    BanHangService banHangService = new BanHangService();
    DecimalFormat fomat = new DecimalFormat("###,###,###");
    private DefaultTableModel model = new DefaultTableModel();
    private List<SanPhamViewModel> listSP = new ArrayList<>();
    private List<HoaDonViewModel> listHD = new ArrayList<>();
    private List<GioHangViewModel> listGH = new ArrayList<>();
    
    public BanHangJPanel() {
        initComponents();
        loadSP();
        loadHD();
        
    }
    
    public void loadSP() {
        List<SanPhamViewModel> listspvm = banHangRepository.getAllSP();
        model = (DefaultTableModel) tableSPBH.getModel();
        model.setRowCount(0);
        for (SanPhamViewModel s : listspvm) {
            model.addRow(new Object[]{
                s.getId(), s.getTenSP(), s.getIdNSX(), s.getIdTG(), s.getIdTheLoai(), s.getSoLuong(), s.getGiaBan()
            });
        }
        
    }
    
    public void loadHD() {
        List<HoaDonViewModel> listspvm = banHangRepository.findHD();
        model = (DefaultTableModel) tableHD.getModel();
        model.setRowCount(0);
        for (HoaDonViewModel s : listspvm) {
            model.addRow(new Object[]{
                s.getId(), s.getMaHD(), s.getNgayTao(), s.getIdNV(), s.getIdKH(), s.getTrangThai()
            });
        }
        
    }
    
    public void loadGH2() {
        model = (DefaultTableModel) tableHDCT.getModel();
        model.setRowCount(0);
        for (GioHangViewModel x : listGH) {
            model.addRow(new Object[]{
                x.getId(), x.getTenSP(), x.getSoLuong(), x.getDonGia() * x.getSoLuong()
            });
            
        }
    }
    
    public void loadGH(List<GioHangViewModel> listGioHang) {
        model = (DefaultTableModel) tableHDCT.getModel();
        model.setRowCount(0);
        for (SanPhamViewModel x : listSP) {
            model.addRow(new Object[]{x.getId(), x.getTenSP(), x.getSoLuong(), x.getGiaBan()});
        }
    }
    
    public void insertHD() {
        Random r = new Random();
        int x = r.nextInt(10);
        int i = listHD.size();
        i++;
        String maHD = "HDD" + x + i;
        HoaDonViewModel hd = new HoaDonViewModel();
        
        hd.setIdNV("F074E744-384E-49A2-8806-790C61B72479");
//        hd.setIdKH(banHangRepository.findByIdKH(maHD));

        hd.setIdKH("F3499789-8243-4778-A2F2-57DB7A484B84");
        hd.setMaHD(maHD);
        hd.setTrangThai(1);
        banHangService.insertHD(hd);
        listHD = banHangService.findHD();
        loadHD();
    }
//    private void fillDataHD(int index){
//        HoaDonViewModel hd = listHD.get(index);
//        lblIdHD.setText(hd.getMaHD());
//    }

    private void fillHoaDonCho() {
        int index = tableHD.getSelectedRow();
        String idHD = tableHD.getValueAt(index, 1).toString();
//        lblIdHD.setText(idHD);
        listGH = banHangRepository.getGioHang(idHD);
        loadGH2();
//        loadGH(listGH);
//        fillDataHD(index);
    }

    private void fillInsertSPGH() {
        List<SanPhamViewModel> listsp = banHangRepository.getAllSP();
        
        GioHangViewModel gh = new GioHangViewModel();
        Integer rowHD = tableHD.getSelectedRow();
        Integer row = tableSPBH.getSelectedRow();
        String idHDB = tableHD.getValueAt(rowHD, 0).toString();
        String idSPDB = tableSPBH.getValueAt(row, 0).toString();
        int soLuongSP = Integer.parseInt(tableSPBH.getValueAt(row, 5).toString());
//        banHangRepository.
//        SanPhamViewModel sp = (SanPhamViewModel) banHangRepository.getSanPhamBH(idSPDB);
        if (rowHD < 0) {
            JOptionPane.showMessageDialog(this, "Vui long chon hoa don");
        } else {
            String soLuong = JOptionPane.showInputDialog("Moi ban nhap so luong");
            if (soLuong != null) {
                if (!soLuong.matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(this, "Nhap dung dinh dang");
                } else if (Integer.parseInt(soLuong) > soLuongSP) {
                    JOptionPane.showMessageDialog(this, "So luong vuot qua");
                } else {
//                    HoaDonViewModel hd = listHD.get(rowHD);
                    String tensp = tableSPBH.getValueAt(row, 1).toString();
//                    String tensp = tableSPBH.getValueAt(row, 1).toString();
                    Double donGia = Double.parseDouble(tableSPBH.getValueAt(row, 6).toString());
                    
                    gh.setSoLuong(Integer.parseInt(soLuong));
                    gh.setIdSP(idSPDB);
                    gh.setTenSP(tensp);
                    gh.setDonGia(donGia);
                    boolean trung = false;
                    for (GioHangViewModel x : listGH) {
                        if (x.getId().contains(idSPDB)) {
                            trung = true;
                            System.out.println("thanh cong");
                        }
                    }
                    if (trung) {
                        JOptionPane.showMessageDialog(this, "San pham da co trong gio hang");
                    } else {
                        listGH.add(gh);
                        SanPhamViewModel sp = new SanPhamViewModel();
                        sp.setSoLuong(sp.getSoLuong() - Integer.parseInt(soLuong));
                        loadSP();
                        String idHD = idHDB;
                        String idSP = idSPDB;
                        int SoLuong1 = Integer.parseInt(soLuong);
//                        Double donGia = sp.getGiaBan();

                        // add gio hang vao HDCT
                        HoaDonChiTiet hdct = new HoaDonChiTiet(idSP, idHD, idSP, SoLuong1, 0);
                        JOptionPane.showMessageDialog(this, banHangService.addHDCT(hdct));
                        listGH = banHangRepository.getGioHang(idHD);
//                        loadGH(listGH);
                        listsp.clear();
                        loadSP();
                        loadGH2();
                        
                    }
                }
            }            
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHD = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSPBH = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableHDCT = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblIdHD = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblNgayTao = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lblThanhToan = new javax.swing.JLabel();
        lblTienThua = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        cbxHTTT = new javax.swing.JComboBox<>();
        cbxGiamGia = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        btnThanhToan = new javax.swing.JButton();
        btnHuyHD = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnXoaSP = new javax.swing.JButton();
        btnCapNhap = new javax.swing.JButton();

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        tableHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Mã HĐ", "Ngày tạo", "Nhân viên tạo", "Khách hàng", "Tình trạng"
            }
        ));
        tableHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableHDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableHD);

        tableSPBH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "NSX", "Tác giả", "Thể loại", "Số lượng", "Giá"
            }
        ));
        tableSPBH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSPBHMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableSPBH);

        tableHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Số lượng", "Đơn giá"
            }
        ));
        jScrollPane3.setViewportView(tableHDCT);

        jLabel4.setText("Tên khách hàng");

        jLabel5.setText("Mã hóa đơn");

        jLabel6.setText("Ngày tạo");

        jLabel7.setText("Tổng tiền");

        jLabel8.setText("Giảm giá");

        jLabel9.setText("Thanh toán");

        jLabel10.setText("Hình thức thanh toán");

        jLabel11.setText("Tiền khách đưa");

        jLabel12.setText("Tiền thừa");

        jLabel13.setText("Ghi chú");

        jLabel14.setText("?");

        lblIdHD.setText("?");

        jLabel16.setText("?");

        jLabel17.setText("Tên khách hàng");

        lblNgayTao.setText("?");

        lblTongTien.setText("?");

        lblThanhToan.setText("?");

        lblTienThua.setText("?");

        cbxHTTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxGiamGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane4.setViewportView(txtGhiChu);

        btnThanhToan.setText("Thanh toán");

        btnHuyHD.setText("Hủy hóa đơn");
        btnHuyHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHDActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");

        jButton1.setText("Tao");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel17)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel2)
                                            .addGap(86, 86, 86))
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel8))
                                        .addGap(63, 63, 63)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cbxGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbxHTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblTienThua)
                                            .addComponent(lblThanhToan)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel16)
                                            .addComponent(txtTienKhachDua, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                            .addComponent(lblNgayTao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(lblIdHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThanhToan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHuyHD, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLamMoi)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(jLabel17))
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblIdHD))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblNgayTao))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblTongTien))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbxGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblThanhToan))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbxHTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lblTienThua))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan)
                    .addComponent(btnHuyHD)
                    .addComponent(btnLamMoi)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnXoaSP.setText("Xóa SP");
        btnXoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPActionPerformed(evt);
            }
        });

        btnCapNhap.setText("Cập nhật");
        btnCapNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhapActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnXoaSP)
                                    .addComponent(btnCapNhap)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(btnXoaSP)
                                .addGap(24, 24, 24)
                                .addComponent(btnCapNhap))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaSPActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        insertHD();
        loadHD();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnHuyHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyHDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHuyHDActionPerformed

    private void tableSPBHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSPBHMouseClicked
        // TODO add your handling code here:
        fillInsertSPGH();
    }//GEN-LAST:event_tableSPBHMouseClicked

    private void tableHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableHDMouseClicked
        // TODO add your handling code here:
        fillHoaDonCho();
        int selectedRow = tableHD.getSelectedRow();
        String maHD = (String) tableHD.getValueAt(selectedRow, 1);
        String ngayTao = tableHD.getValueAt(selectedRow, 2).toString();
        lblIdHD.setText(maHD);
        lblNgayTao.setText(ngayTao);
    }//GEN-LAST:event_tableHDMouseClicked

    private void btnCapNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhapActionPerformed
        loadGH2();
        loadHD();
        loadSP();
        double thanhTien = 0;
        double thanhToan = 0;
        double giamGia = 0;
        for (GioHangViewModel gha : listGH) {
            thanhTien += gha.getSoLuong() * gha.getDonGia();
        }
        lblTongTien.setText(String.valueOf(fomat.format(thanhTien)));
        lblThanhToan.setText(String.valueOf(fomat.format(thanhToan = thanhTien)));
    }//GEN-LAST:event_btnCapNhapActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhap;
    private javax.swing.JButton btnHuyHD;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.JComboBox<String> cbxGiamGia;
    private javax.swing.JComboBox<String> cbxHTTT;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblIdHD;
    private javax.swing.JLabel lblNgayTao;
    private javax.swing.JLabel lblThanhToan;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLayeredPane panel;
    private javax.swing.JTable tableHD;
    private javax.swing.JTable tableHDCT;
    private javax.swing.JTable tableSPBH;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtTienKhachDua;
    // End of variables declaration//GEN-END:variables
}

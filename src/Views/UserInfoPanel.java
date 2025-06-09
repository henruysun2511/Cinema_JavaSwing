package Views;

import javax.swing.*;


import java.text.DecimalFormat;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import java.awt.*;
import java.security.cert.CertPathValidator;
import java.util.*;
import java.util.List;

import Utilzs.*;
import Models.*;
import Controllers.GheController;
import Controllers.LichChieuController;
import Controllers.PhimController;
import Controllers.PhongChieuController;
import Controllers.VeController;
import Controllers.VoucherController;
import Daos.VeDao;

public class UserInfoPanel extends JPanel {
    private JTextField txtMaKH, txtTenKH, txtSDT, txtEmail, txtDiaChi;
    private JButton btnLuu, btnXoa;
    JPanel mainContentPanel;
    CardLayout cardLayout;
    JButton btnThongTinKhachHang, btnVeDaMua, btnHoaDon;

    public UserInfoPanel() {
        addControls();
        addEvents();
    }

    private void addControls() {
        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout);

        // Thêm các panel con vào mainContentPanel
        mainContentPanel.add(thongTinKhachHang(), "THONG_TIN_KHACH_HANG");
        mainContentPanel.add(veDaMua(), "VE_DA_MUA");
        mainContentPanel.add(hoaDon(), "HOA_DON");

        // Panel chứa các nút điều hướng
        JPanel pnButton = new GradientPanel(new Color(10, 10, 30), new Color(60, 30, 180)); 
        pnButton.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15)); 
        pnButton.setBorder(new EmptyBorder(10, 0, 10, 0)); 

        btnThongTinKhachHang = createNavigationButton("Thông tin khách hàng");
        btnVeDaMua = createNavigationButton("Vé của tôi");
        btnHoaDon = createNavigationButton("Hóa đơn");
        
        pnButton.add(btnThongTinKhachHang);
        pnButton.add(btnVeDaMua);
        pnButton.add(btnHoaDon);

        this.setLayout(new BorderLayout());
        this.add(pnButton, BorderLayout.NORTH);
        this.add(mainContentPanel, BorderLayout.CENTER);
    }

    // Phương thức tạo và tùy chỉnh các nút điều hướng
    private JButton createNavigationButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 123, 255)); // Màu xanh dương đậm hơn
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(0, 100, 200), 1), // Viền màu đậm hơn
            new EmptyBorder(10, 20, 10, 20) // Padding bên trong nút
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Biểu tượng con trỏ khi di chuột qua
        button.setOpaque(true); // Đảm bảo màu nền hiển thị
        return button;
    }

    public JPanel thongTinKhachHang() {
        JPanel pnMain = new GradientPanel(new Color(10, 10, 30), new Color(60, 30, 180));
        pnMain.setLayout(new GridBagLayout());

        // ======== Form Panel ========
        JPanel pnForm = new JPanel(new GridBagLayout());
        pnForm.setOpaque(false);
        pnForm.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 123, 255), 2),
                "Thông Tin Khách Hàng",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 18),
                new Color(0, 123, 255)
            ),
            new EmptyBorder(30, 50, 30, 50)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblMaKH = new JLabel("Mã Khách Hàng:");
        JLabel lblTenKH = new JLabel("Tên Khách Hàng:");
        JLabel lblSDT = new JLabel("Số Điện Thoại:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblDiaChi = new JLabel("Địa Chỉ:");

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        JLabel[] labels = { lblMaKH, lblTenKH, lblSDT, lblEmail, lblDiaChi };
        for (JLabel lbl : labels) {
            lbl.setFont(labelFont);
            lbl.setForeground(Color.white);
        }

        txtMaKH = createStyledTextField();
        txtTenKH = createStyledTextField();
        txtSDT = createStyledTextField();
        txtEmail = createStyledTextField();
        txtDiaChi = createStyledTextField();

        JTextField[] fields = { txtMaKH, txtTenKH, txtSDT, txtEmail, txtDiaChi };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0;
            pnForm.add(labels[i], gbc);

            gbc.gridx = 1;
            gbc.weightx = 1.0;
            pnForm.add(fields[i], gbc);
        }

        // ======== Tổng Chi Tiêu Panel ========
        JPanel pnChiTieu = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnChiTieu.setOpaque(false);
        pnChiTieu.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 123, 255), 2),
                "Tổng chi tiêu",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 18),
                new Color(0, 123, 255)
            ),
            new EmptyBorder(30, 50, 30, 50)
        ));

        double tongChiTieu = tinhTongChiTieuTheoMaKH("US001"); // Hàm xử lý
        DecimalFormat df = new DecimalFormat("#,### VND");
        JLabel lblChiTieu = new JLabel("Tổng chi tiêu: " + df.format(tongChiTieu));
        lblChiTieu.setFont(new Font("Arial", Font.BOLD, 16));
        lblChiTieu.setForeground(Color.WHITE);
        pnChiTieu.add(lblChiTieu);

        // ======== Buttons Panel ========
        JPanel pnButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        pnButtons.setOpaque(false);

        btnLuu = createActionButton("Lưu", new Color(40, 167, 69));
        btnXoa = createActionButton("Xóa", new Color(220, 53, 69));
      
        pnButtons.add(btnLuu);
        pnButtons.add(btnXoa);
      

        // ======== Add to Main Panel ========
        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.insets = new Insets(20, 20, 10, 20);
        gbcMain.fill = GridBagConstraints.BOTH;
        gbcMain.weighty = 1.0;

        // pnForm (trái)
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.weightx = 0.7;
        pnMain.add(pnForm, gbcMain);

        // pnChiTieu (phải)
        gbcMain.gridx = 1;
        gbcMain.gridy = 0;
        gbcMain.weightx = 0.3;
        pnMain.add(pnChiTieu, gbcMain);

        // pnButtons (hàng dưới)
        gbcMain.gridx = 0;
        gbcMain.gridy = 1;
        gbcMain.gridwidth = 2;
        gbcMain.weightx = 1.0;
        gbcMain.weighty = 0;
        gbcMain.insets = new Insets(10, 0, 10, 0);
        pnMain.add(pnButtons, gbcMain);

        return pnMain;
    }
    
    ArrayList<HoaDonVe> danhSachHoaDonVe = VeController.layDanhSachHoaDonVeTheoMaNguoiDung("US001");
    public double tinhTongChiTieuTheoMaKH(String maKH) {
        double tong = 0;
        
        for (HoaDonVe hdv : danhSachHoaDonVe) {
            if (hdv.getMaNguoiDung().equals(maKH)) {
                tong += hdv.getTongThanhToan();
            }
        }
        return tong;
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1), // Viền xám nhạt
            new EmptyBorder(8, 10, 8, 10) // Padding bên trong
        ));
        textField.setPreferredSize(new Dimension(250, 35)); // Kích thước gợi ý
        return textField;
    }

    // Phương thức tạo và tùy chỉnh các nút hành động
    private JButton createActionButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(bgColor.darker(), 1), // Viền đậm hơn màu nền
            new EmptyBorder(10, 20, 10, 20) // Padding bên trong
        ));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        return button;
    }

    public JPanel veDaMua (){
        JPanel pnMain = new GradientPanel(new Color(10, 10, 30), new Color(60, 30, 180));
        pnMain.setLayout(new BorderLayout()); 

        ArrayList<Ve> dsVeDaMua = VeController.layDanhSachVeTheoMaNguoiDung("US001");

        String[] columnNames = { "Mã Vé", "Phim", "Phòng", "Ghế", "Suất", "Ngày", "Xuất vé" };
        Object[][] data = new Object[dsVeDaMua.size()][7];

        for (int i = 0; i < dsVeDaMua.size(); i++) {
            Ve ve = dsVeDaMua.get(i);
            Ghe ghe = GheController.layGheTheoMaGhe(ve.getMaGhe());
            LichChieu lc = LichChieuController.layLichChieuTheoMaLichChieu(ve.getMaSuatChieu());
            data[i][0] = ve.getMaVe();
            data[i][1] = PhimController.layPhimTheoMaPhim(lc.getMaPhim());
            data[i][2] = PhongChieuController.layPhongChieuTheoMaPhongChieu(lc.getMaPhong());
            data[i][3] = ghe.getTenGhe();
            data[i][4] = lc.getKhungGioChieuString();
            data[i][5] = lc.getNgayChieu();
            data[i][6] = "";
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                return Object.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
            	return column == 6; 
            }
        };

        JTable table = new JTable(model);
        table.getColumn("Xuất vé").setCellRenderer(new ButtonRenderer());
        table.getColumn("Xuất vé").setCellEditor(new ButtonEditor(new JCheckBox()));
        table.setRowHeight(90);
        table.setForeground(Color.WHITE);
        table.setBackground(new Color(0, 0, 0, 0));
        table.setOpaque(false);
        ((DefaultTableCellRenderer) table.getDefaultRenderer(Object.class)).setOpaque(false);

        // Căn giữa
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        

        // Header
        JTableHeader header = table.getTableHeader();
        header.setForeground(Color.WHITE);
        header.setBackground(new Color(30, 30, 60));
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // Font
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        // ScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


        pnMain.setOpaque(false);
        pnMain.add(scrollPane, BorderLayout.CENTER);

        return pnMain;
    }

    public JScrollPane hoaDon() {
        JPanel pnMain = new GradientPanel(new Color(10, 10, 30), new Color(60, 30, 180));
        pnMain.setLayout(new GridLayout(0, 3, 20, 20));  // mỗi hàng 3 hóa đơn

        for (HoaDonVe hdv : danhSachHoaDonVe) {
            JPanel pnHoaDonVe = new JPanel();
            pnHoaDonVe.setLayout(new BoxLayout(pnHoaDonVe, BoxLayout.Y_AXIS));
            pnHoaDonVe.setBackground(Color.WHITE);
            pnHoaDonVe.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
            ));

            // Tiêu đề hóa đơn
            JLabel lblTitle = new JLabel("HÓA ĐƠN VÉ", SwingConstants.CENTER);
            lblTitle.setFont(new Font("Serif", Font.BOLD, 22));
            lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
            lblTitle.setForeground(new Color(30, 30, 130));
            pnHoaDonVe.add(lblTitle);

            pnHoaDonVe.add(Box.createVerticalStrut(10));
            pnHoaDonVe.add(new JSeparator());

            // Thông tin hóa đơn
            JLabel lblMaHoaDon = new JLabel("Mã hóa đơn: " + hdv.getMaThanhToan());
            JLabel lblThoiGianGiaoDich = new JLabel("Thời gian giao dịch: " + hdv.getLichSuGiaoDich());
            JLabel lblTenKhachHang = new JLabel("Khách hàng: " + hdv.getMaNguoiDung());

            for (JLabel label : List.of(lblMaHoaDon, lblThoiGianGiaoDich, lblTenKhachHang)) {
                label.setFont(new Font("Arial", Font.PLAIN, 13));
                pnHoaDonVe.add(label);
            }

            pnHoaDonVe.add(Box.createVerticalStrut(10));
            pnHoaDonVe.add(new JSeparator());

            List<Ve> danhSachVe = VeDao.layDanhSachVeTheoMaHoaDon(hdv.getMaThanhToan());
            if (!danhSachVe.isEmpty()) {
                Ve veDauTien = danhSachVe.get(0);
                LichChieu lc = LichChieuController.layLichChieuTheoMaLichChieu(veDauTien.getMaSuatChieu());
                PhongChieu ph = PhongChieuController.layPhongChieuTheoMaPhongChieu(lc.getMaPhong());
                DinhDang dd = PhongChieuController.layDinhDangTheoMaDinhDang(ph.getMaDinhDang());
                Phim p = PhimController.layPhimTheoMaPhim(lc.getMaPhim());

                JLabel lblTenPhim = new JLabel("Phim: " + p.getTenPhim());
                lblTenPhim.setFont(new Font("Arial", Font.BOLD, 17));
                JLabel lblThoiLuong = new JLabel(p.getThoiLuong() + " phút - " + p.getDoTuoiChoPhep());
                JLabel lblPhong = new JLabel("Phòng chiếu: " + ph.getTenPhong() + " (" + dd.getTenDinhDang() + ")");
                JLabel lblSuatChieu = new JLabel("Suất chiếu: " + lc.getKhungGioChieuString() + " - Ngày: " + lc.getNgayChieu());

                for (JLabel label : List.of(lblTenPhim, lblThoiLuong, lblPhong, lblSuatChieu)) {
                    label.setFont(new Font("Arial", Font.PLAIN, 13));
                    pnHoaDonVe.add(label);
                }

                pnHoaDonVe.add(Box.createVerticalStrut(10));
            }

            // Danh sách vé
            pnHoaDonVe.add(new JLabel("Danh sách vé:"));
            for (Ve ve : danhSachVe) {
                Ghe ghe = GheController.layGheTheoMaGhe(ve.getMaGhe());
                LoaiGhe loaiGhe = GheController.layLoaiGheTheoMa(ghe.getLoaiGhe());
                JLabel lblVe = new JLabel(" - Ghế " + ghe.getTenGhe() + " (" + loaiGhe.getLoaiGhe() + "): " + loaiGhe.getGiaGhe() + " VNĐ");
                lblVe.setFont(new Font("Arial", Font.PLAIN, 13));
                pnHoaDonVe.add(lblVe);
            }

            pnHoaDonVe.add(Box.createVerticalStrut(10));

            // Khuyến mãi
            Voucher vc = VoucherController.layVoucherTheoMaVoucher(hdv.getMaVoucher());
            if (vc != null) {
                JLabel lblKhuyenMai = new JLabel("Khuyến mãi: " + vc.getMoTa() + " (" + vc.getTenVoucher() + ")");
                lblKhuyenMai.setFont(new Font("Arial", Font.ITALIC, 13));
                lblKhuyenMai.setForeground(Color.RED);
                pnHoaDonVe.add(lblKhuyenMai);
            }

            pnHoaDonVe.add(Box.createVerticalStrut(10));
            pnHoaDonVe.add(new JSeparator());

            // Tổng tiền
            JLabel lblTongTien = new JLabel("Tổng cộng: " + String.format("%,.0f", hdv.getTongThanhToan()) + " VNĐ");
            lblTongTien.setFont(new Font("Arial", Font.BOLD, 15));
            lblTongTien.setAlignmentX(Component.RIGHT_ALIGNMENT);
            pnHoaDonVe.add(lblTongTien);

            int tongTienInt = (int) hdv.getTongThanhToan();
            JLabel lblBangChu = new JLabel("Bằng chữ: " + NumberToVietnameseWords.convertNumberToVietnameseWords(tongTienInt));
            lblBangChu.setFont(new Font("Arial", Font.ITALIC, 12));
            lblBangChu.setForeground(Color.DARK_GRAY);
            pnHoaDonVe.add(lblBangChu);



            pnMain.add(pnHoaDonVe);
        }

        JScrollPane scrollPane = new JScrollPane(pnMain);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(900, 600));

        return scrollPane;
    }

    private void addEvents() {
        btnThongTinKhachHang.addActionListener(e -> {
            cardLayout.show(mainContentPanel, "THONG_TIN_KHACH_HANG"); 
        });

        btnVeDaMua.addActionListener(e -> {
            cardLayout.show(mainContentPanel, "VE_DA_MUA"); 
        });

        btnHoaDon.addActionListener(e -> {
            cardLayout.show(mainContentPanel, "HOA_DON"); 
        });

        btnLuu.addActionListener(e -> {
            String thongTin = "Mã: " + txtMaKH.getText()
                    + "\nTên: " + txtTenKH.getText()
                    + "\nSĐT: " + txtSDT.getText()
                    + "\nEmail: " + txtEmail.getText()
                    + "\nĐịa chỉ: " + txtDiaChi.getText();

            JOptionPane.showMessageDialog(null, "Đã lưu thông tin:\n\n" + thongTin, "Lưu thông tin", JOptionPane.INFORMATION_MESSAGE);
        });

        btnXoa.addActionListener(e -> {
            txtMaKH.setText("");
            txtTenKH.setText("");
            txtSDT.setText("");
            txtEmail.setText("");
            txtDiaChi.setText("");
            JOptionPane.showMessageDialog(null, "Đã xóa trắng thông tin.", "Xóa thông tin", JOptionPane.INFORMATION_MESSAGE);
        });
        
    }
    
    
        
        
}
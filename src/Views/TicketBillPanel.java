package Views;

import java.awt.*;

import java.util.List;
import javax.swing.*;
import javax.swing.table.*;

import Controllers.GheController;
import Controllers.LichChieuController;
import Controllers.PhimController;
import Controllers.PhongChieuController;
import Controllers.VeController;
import Controllers.VoucherController;
import Daos.VeDao;
import Models.*;
import Utilzs.*;

public class TicketBillPanel extends JPanel {
    private String maHoaDonVe;

    public TicketBillPanel(String maHoaDonVe) {
        this.maHoaDonVe = maHoaDonVe;
        setLayout(new BorderLayout());
        addControls();
    }

    public void addControls() {
        JPanel pnMain = new GradientPanel(new Color(10, 10, 30), new Color(60, 30, 180));
        pnMain.setLayout(new BorderLayout());
        pnMain.setOpaque(false);

        JPanel tablePanel = hienThiThongTinVe(maHoaDonVe);
        pnMain.add(tablePanel, BorderLayout.CENTER);
        JPanel hoaDonPanel = hienThiThongTinHoaDon(maHoaDonVe);
        hoaDonPanel.setPreferredSize(new Dimension(300, 0));
        pnMain.add(hoaDonPanel, BorderLayout.EAST);

        this.add(pnMain, BorderLayout.CENTER);
    }

    public JPanel hienThiThongTinVe(String maHoaDonVe) {
        List<Ve> danhSachVe = VeDao.layDanhSachVeTheoMaHoaDon(maHoaDonVe);

        String[] columnNames = { "Mã Vé", "Phim", "Phòng", "Ghế", "Suất", "Ngày", "Xuất vé (EXCEL)" };
        Object[][] data = new Object[danhSachVe.size()][7];

        for (int i = 0; i < danhSachVe.size(); i++) {
            Ve ve = danhSachVe.get(i);
            Ghe ghe = GheController.layGheTheoMaGhe(ve.getMaGhe());
            LichChieu lc = LichChieuController.layLichChieuTheoMaLichChieu(ve.getMaSuatChieu());
            data[i][0] = ve.getMaVe();
            data[i][1] = PhimController.layPhimTheoMaPhim(lc.getMaPhim());
            data[i][2] = PhongChieuController.layPhongChieuTheoMaPhongChieu(lc.getMaPhong());
            data[i][3] = ghe.getTenGhe();
            data[i][4] = lc.getKhungGioChieuString();
            data[i][5] = lc.getNgayChieu();
            data[i][6] = "Xuất vé";
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 6)
                    return ImageIcon.class;
                return Object.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6;
            }
        };
        JTable table = new JTable(model);
        
//        table.getColumn("Xuất vé").setCellRenderer(new ButtonRenderer());
//        table.getColumn("Xuất vé").setCellEditor(new ButtonEditor(new JCheckBox()));

       
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

        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    public JPanel hienThiThongTinHoaDon(String maHoaDonVe) {
        List<Ve> danhSachVe = VeDao.layDanhSachVeTheoMaHoaDon(maHoaDonVe);
        HoaDonVe hdv = VeController.layHoaDonVeTheoMaHoaDonVe(maHoaDonVe);

        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
        pnMain.setBackground(Color.white);

        // Thông tin chung hóa đơn
        JLabel lblTitle = new JLabel("HÓA ĐƠN VÉ");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblThoiGianGiaoDich = new JLabel("Thời gian giao dịch: " + hdv.getLichSuGiaoDich());
        JLabel lblTenKhachHang = new JLabel("Khách hàng: ");

        pnMain.add(lblTitle);
        pnMain.add(lblThoiGianGiaoDich);
        pnMain.add(lblTenKhachHang);

        // Lấy thông tin từ 1 vé đại diện
        if (!danhSachVe.isEmpty()) {
            Ve veDauTien = danhSachVe.get(0);
            LichChieu lc = LichChieuController.layLichChieuTheoMaLichChieu(veDauTien.getMaSuatChieu());
            PhongChieu ph = PhongChieuController.layPhongChieuTheoMaPhongChieu(lc.getMaPhong());
            DinhDang dd = PhongChieuController.layDinhDangTheoMaDinhDang(ph.getMaDinhDang());
            Phim p = PhimController.layPhimTheoMaPhim(lc.getMaPhim());

            JLabel lblTenPhim = new JLabel("Phim: " + p.getTenPhim());
            lblTenPhim.setFont(new Font("Arial", Font.BOLD, 18));

            JLabel lblThoiLuong = new JLabel(p.getThoiLuong() + " phút - " + p.getDoTuoiChoPhep());
            JLabel lblPhong = new JLabel("Phòng: " + ph.getTenPhong() + " - Định dạng: " + dd.getTenDinhDang());
            JLabel lblSuatChieu = new JLabel("Suất: " + lc.getKhungGioChieuString() + " - Ngày: " + lc.getNgayChieu());

            pnMain.add(lblTenPhim);
            pnMain.add(lblThoiLuong);
            pnMain.add(lblPhong);
            pnMain.add(lblSuatChieu);
        }

        // Danh sách ghế
        for (Ve ve : danhSachVe) {
            Ghe ghe = GheController.layGheTheoMaGhe(ve.getMaGhe());
            LoaiGhe loaiGhe = GheController.layLoaiGheTheoMa(ghe.getLoaiGhe());
            JLabel lblVe = new JLabel(
                    "Ghế: " + ghe.getTenGhe() + " (" + loaiGhe.getLoaiGhe() + ") - " + loaiGhe.getGiaGhe() + " VNĐ");
            pnMain.add(lblVe);
        }

        // Khuyến mãi
        Voucher vc = VoucherController.layVoucherTheoMaVoucher(hdv.getMaVoucher());
        if (vc != null) {
            JLabel lblKhuyenMai = new JLabel("Giảm giá: " + vc.getMoTa() + " - " + vc.getTenVoucher());
            lblKhuyenMai.setFont(new Font("", Font.ITALIC, 14));
            pnMain.add(lblKhuyenMai);
        } else {
            System.out.println("Voucher không tồn tại!");
        }

        // Tổng tiền
        JLabel lblTongTien = new JLabel("Tổng tiền: " + hdv.getTongThanhToan());
        lblTongTien.setFont(new Font("", Font.BOLD, 16));

        return pnMain;
    }
    
//    public class ButtonEditor extends DefaultCellEditor {
//        private JButton button;
//        private String maVe;
//        private boolean clicked;
//
//        public ButtonEditor(JCheckBox checkBox) {
//            super(checkBox);
//            button = new JButton("Xuất vé");
//            button.setOpaque(true);
//
//            button.addActionListener(e -> {
//                if (clicked) {
//                    ExportExcel.xuatVeExcel(maVe); 
//                }
//            });
//        }
//
//        @Override
//        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
//                int row, int column) {
//            maVe = table.getValueAt(row, 0).toString();
//            clicked = true;
//            return button;
//        }
//
//        @Override
//        public Object getCellEditorValue() {
//            clicked = false;
//            return "Xuất vé";
//        }
//
//        @Override
//        public boolean stopCellEditing() {
//            clicked = false;
//            return super.stopCellEditing();
//        }
//
//        @Override
//        protected void fireEditingStopped() {
//            super.fireEditingStopped();
//        }
//    }
//        
//        public class ButtonRenderer extends JButton implements TableCellRenderer {
//
//            public ButtonRenderer() {
//                setOpaque(true);
//                setText("Xuất vé");
//            }
//
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
//                    boolean hasFocus, int row, int column) {
//                return this;
//            }
//        }

}
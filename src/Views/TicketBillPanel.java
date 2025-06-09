package Views;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        hoaDonPanel.setPreferredSize(new Dimension(300, 300));
        pnMain.add(hoaDonPanel, BorderLayout.SOUTH);

        this.add(pnMain, BorderLayout.CENTER);
    }

    public JPanel hienThiThongTinVe(String maHoaDonVe) {
        List<Ve> danhSachVe = VeDao.layDanhSachVeTheoMaHoaDon(maHoaDonVe);

        String[] columnNames = { "Mã Vé", "Phim", "Phòng", "Ghế", "Suất", "Ngày", "Xuất vé" };
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
        pnMain.setBackground(Color.WHITE);
        pnMain.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30)); // padding quanh hóa đơn

        // Tiêu đề
        JLabel lblTitle = new JLabel("HÓA ĐƠN THANH TOÁN", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Serif", Font.BOLD, 22));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setForeground(new Color(30, 30, 130));
        pnMain.add(lblTitle);
        pnMain.add(Box.createVerticalStrut(10));
        pnMain.add(new JSeparator());

        // Thông tin giao dịch
        JLabel lblThoiGianGiaoDich = new JLabel("Thời gian giao dịch: " + hdv.getLichSuGiaoDich());
        lblThoiGianGiaoDich.setFont(new Font("Arial", Font.PLAIN, 13));

        JLabel lblTenKhachHang = new JLabel("Khách hàng: " + hdv.getMaNguoiDung());
        lblTenKhachHang.setFont(new Font("Arial", Font.PLAIN, 13));

        pnMain.add(lblThoiGianGiaoDich);
        pnMain.add(lblTenKhachHang);

        pnMain.add(Box.createVerticalStrut(10));
        pnMain.add(new JSeparator());

        // Thông tin phim
        if (!danhSachVe.isEmpty()) {
            Ve veDauTien = danhSachVe.get(0);
            LichChieu lc = LichChieuController.layLichChieuTheoMaLichChieu(veDauTien.getMaSuatChieu());
            PhongChieu ph = PhongChieuController.layPhongChieuTheoMaPhongChieu(lc.getMaPhong());
            DinhDang dd = PhongChieuController.layDinhDangTheoMaDinhDang(ph.getMaDinhDang());
            Phim p = PhimController.layPhimTheoMaPhim(lc.getMaPhim());

            JLabel lblTenPhim = new JLabel("Phim: " + p.getTenPhim());
            lblTenPhim.setFont(new Font("Arial", Font.BOLD, 15));
            JLabel lblThoiLuong = new JLabel(p.getThoiLuong() + " phút - " + p.getDoTuoiChoPhep());
            JLabel lblPhong = new JLabel("Phòng chiếu: " + ph.getTenPhong() + " (" + dd.getTenDinhDang() + ")");
            JLabel lblSuatChieu = new JLabel("Suất chiếu: " + lc.getKhungGioChieuString() + " - Ngày: " + lc.getNgayChieu());

            for (JLabel lbl : List.of(lblTenPhim, lblThoiLuong, lblPhong, lblSuatChieu)) {
                lbl.setFont(new Font("Arial", Font.PLAIN, 13));
                pnMain.add(lbl);
            }

            pnMain.add(Box.createVerticalStrut(10));
        }

        // Danh sách vé
        pnMain.add(new JLabel("Danh sách ghế:"));
        for (Ve ve : danhSachVe) {
            Ghe ghe = GheController.layGheTheoMaGhe(ve.getMaGhe());
            LoaiGhe loaiGhe = GheController.layLoaiGheTheoMa(ghe.getLoaiGhe());

            JLabel lblVe = new JLabel(" - Ghế " + ghe.getTenGhe() + " (" + loaiGhe.getLoaiGhe() + "): " + loaiGhe.getGiaGhe() + " VNĐ");
            lblVe.setFont(new Font("Arial", Font.PLAIN, 13));
            pnMain.add(lblVe);
        }

        pnMain.add(Box.createVerticalStrut(10));

        // Khuyến mãi
        Voucher vc = VoucherController.layVoucherTheoMaVoucher(hdv.getMaVoucher());
        if (vc != null) {
            JLabel lblKhuyenMai = new JLabel("Khuyến mãi: " + vc.getMoTa() + " (" + vc.getTenVoucher() + ")");
            lblKhuyenMai.setFont(new Font("Arial", Font.ITALIC, 13));
            lblKhuyenMai.setForeground(Color.RED);
            pnMain.add(lblKhuyenMai);
        }

        pnMain.add(Box.createVerticalStrut(10));
        pnMain.add(new JSeparator());

        // Tổng tiền
        JLabel lblTongTien = new JLabel("Tổng tiền: " + String.format("%,.0f", hdv.getTongThanhToan()) + " VNĐ");
        lblTongTien.setFont(new Font("Arial", Font.BOLD, 15));
        lblTongTien.setAlignmentX(Component.RIGHT_ALIGNMENT);
        pnMain.add(lblTongTien);

        // Bằng chữ
        int tongTienInt = (int) hdv.getTongThanhToan();
        JLabel lblBangChu = new JLabel("Bằng chữ: " + NumberToVietnameseWords.convertNumberToVietnameseWords(tongTienInt));
        lblBangChu.setFont(new Font("Arial", Font.ITALIC, 12));
        lblBangChu.setForeground(Color.DARK_GRAY);
        pnMain.add(lblBangChu);
        
        JButton btnXuatHoaDon = new JButton("Xuất hóa đơn");
        btnXuatHoaDon.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	ExportExcel.xuatHoaDonExcel(hdv.getMaThanhToan(), "hoa_don.xlsx");
            }
        });
        pnMain.add(btnXuatHoaDon);
        return pnMain;
    }
    
    
    
   

}
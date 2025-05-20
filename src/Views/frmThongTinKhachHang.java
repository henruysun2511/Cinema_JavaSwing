package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.TitledBorder;

public class frmThongTinKhachHang extends JFrame {
    private JMenuBar menuBar;
    private JMenu mnuTrangChu, mnuPhim, mnuLichChieu, mnuGiaVe, mnuThoat;
    private JTextField txtMaKH, txtTenKH, txtSDT, txtEmail, txtDiaChi;
    private JButton btnLuu, btnXoa, btnThoat;

    public frmThongTinKhachHang(String title) {
        super(title);
        addControls();
        addEvents();
        showWindow();
    }

    private void showWindow() {
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void addControls() {
        // Menu bar
        menuBar = new JMenuBar();
        mnuTrangChu = new JMenu("Trang Chủ");
        mnuPhim = new JMenu("Phim");
        mnuLichChieu = new JMenu("Lịch Chiếu");
        mnuGiaVe = new JMenu("Giá Vé");
        mnuThoat = new JMenu("Thoát");

        menuBar.add(mnuTrangChu);
        menuBar.add(mnuPhim);
        menuBar.add(mnuLichChieu);
        menuBar.add(mnuGiaVe);
        menuBar.add(mnuThoat);

        setJMenuBar(menuBar);

        // Panel chính
        JPanel pnMain = new JPanel(new BorderLayout());

        // Panel thông tin khách hàng
        JPanel pnForm = new JPanel(new GridLayout(5, 2, 10, 10));
        pnForm.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Thông Tin Khách Hàng"));
        pnForm.setPreferredSize(new Dimension(500, 200));
        pnForm.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));

        txtMaKH = new JTextField();
        txtTenKH = new JTextField();
        txtSDT = new JTextField();
        txtEmail = new JTextField();
        txtDiaChi = new JTextField();

        pnForm.add(new JLabel("Mã Khách Hàng:"));
        pnForm.add(txtMaKH);
        pnForm.add(new JLabel("Tên Khách Hàng:"));
        pnForm.add(txtTenKH);
        pnForm.add(new JLabel("Số Điện Thoại:"));
        pnForm.add(txtSDT);
        pnForm.add(new JLabel("Email:"));
        pnForm.add(txtEmail);
        pnForm.add(new JLabel("Địa Chỉ:"));
        pnForm.add(txtDiaChi);

        // Panel nút
        JPanel pnButtons = new JPanel(new FlowLayout());
        btnLuu = new JButton("Lưu");
        btnXoa = new JButton("Xóa");
        btnThoat = new JButton("Đóng");

        pnButtons.add(btnLuu);
        pnButtons.add(btnXoa);
        pnButtons.add(btnThoat);

        // Thêm vào main
        pnMain.add(pnForm, BorderLayout.CENTER);
        pnMain.add(pnButtons, BorderLayout.SOUTH);

        this.setLayout(new BorderLayout());
        this.add(pnMain, BorderLayout.CENTER);
    }

    private void addEvents() {
        // Menu điều hướng
        mnuTrangChu.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new frmTrangChu("Trang Chủ").setVisible(true);
                dispose();
            }
        });

        mnuThoat.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });

        // Nút chức năng
        btnLuu.addActionListener(e -> {
            String thongTin = "Mã: " + txtMaKH.getText()
                    + "\nTên: " + txtTenKH.getText()
                    + "\nSĐT: " + txtSDT.getText()
                    + "\nEmail: " + txtEmail.getText()
                    + "\nĐịa chỉ: " + txtDiaChi.getText();

            JOptionPane.showMessageDialog(null, "Đã lưu thông tin:\n\n" + thongTin);
        });

        btnXoa.addActionListener(e -> {
            txtMaKH.setText("");
            txtTenKH.setText("");
            txtSDT.setText("");
            txtEmail.setText("");
            txtDiaChi.setText("");
        });

        btnThoat.addActionListener(e -> dispose());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new frmThongTinKhachHang("Thông Tin Khách Hàng"));
    }
}

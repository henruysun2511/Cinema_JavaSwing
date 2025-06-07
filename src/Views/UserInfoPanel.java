package Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.util.*;
import Utilzs.*;
import Models.*;
import Controllers.GheController;
import Controllers.LichChieuController;
import Controllers.PhimController;
import Controllers.PhongChieuController;
import Controllers.VeController;

public class UserInfoPanel extends JPanel {
    private JTextField txtMaKH, txtTenKH, txtSDT, txtEmail, txtDiaChi;
    private JButton btnLuu, btnXoa, btnThoat;
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

    public JPanel thongTinKhachHang(){
        JPanel pnMain = new GradientPanel(new Color(10, 10, 30), new Color(60, 30, 180)); 
        pnMain.setLayout(new GridBagLayout()); // Sử dụng GridBagLayout để căn giữa form

        JPanel pnForm = new JPanel(new GridBagLayout()); 
        pnForm.setOpaque(false);
        pnForm.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 123, 255), 2), // Viền xanh
                "Thông Tin Khách Hàng", // Tiêu đề
                TitledBorder.CENTER, // Căn giữa tiêu đề
                TitledBorder.TOP, // Vị trí tiêu đề
                new Font("Arial", Font.BOLD, 18), // Font tiêu đề
                new Color(0, 123, 255) // Màu tiêu đề
            ),
            new EmptyBorder(30, 50, 30, 50) // Padding bên trong form
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.fill = GridBagConstraints.HORIZONTAL; 

        // Labels
        JLabel lblMaKH = new JLabel("Mã Khách Hàng:");
        JLabel lblTenKH = new JLabel("Tên Khách Hàng:");
        JLabel lblSDT = new JLabel("Số Điện Thoại:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblDiaChi = new JLabel("Địa Chỉ:");

        // Style Labels
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Color labelColor = new Color(51, 51, 51); // Màu xám đậm
        lblMaKH.setFont(labelFont); lblMaKH.setForeground(labelColor);
        lblTenKH.setFont(labelFont); lblTenKH.setForeground(labelColor);
        lblSDT.setFont(labelFont); lblSDT.setForeground(labelColor);
        lblEmail.setFont(labelFont); lblEmail.setForeground(labelColor);
        lblDiaChi.setFont(labelFont); lblDiaChi.setForeground(labelColor);

        // TextFields
        txtMaKH = createStyledTextField();
        txtTenKH = createStyledTextField();
        txtSDT = createStyledTextField();
        txtEmail = createStyledTextField();
        txtDiaChi = createStyledTextField();

        // Thêm Labels và TextFields vào pnForm
        gbc.gridx = 0; gbc.gridy = 0; pnForm.add(lblMaKH, gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0; pnForm.add(txtMaKH, gbc); // weightx để txt điền đầy
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0; pnForm.add(lblTenKH, gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0; pnForm.add(txtTenKH, gbc);       
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0; pnForm.add(lblSDT, gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 1.0; pnForm.add(txtSDT, gbc);        
        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0; pnForm.add(lblEmail, gbc);
        gbc.gridx = 1; gbc.gridy = 3; gbc.weightx = 1.0; pnForm.add(txtEmail, gbc);       
        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0; pnForm.add(lblDiaChi, gbc);
        gbc.gridx = 1; gbc.gridy = 4; gbc.weightx = 1.0; pnForm.add(txtDiaChi, gbc);


        JPanel pnButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0)); 
        pnButtons.setOpaque(false); 

        // Style các nút hành động
        btnLuu = createActionButton("Lưu", new Color(40, 167, 69)); // Màu xanh lá
        btnXoa = createActionButton("Xóa", new Color(220, 53, 69)); // Màu đỏ
        btnThoat = createActionButton("Đóng", new Color(108, 117, 125)); // Màu xám

        pnButtons.add(btnLuu);
        pnButtons.add(btnXoa);
        pnButtons.add(btnThoat);

        // Thêm pnForm và pnButtons vào pnMain
        // Căn giữa pnForm
        GridBagConstraints gbcMain = new GridBagConstraints();
        gbcMain.gridx = 0;
        gbcMain.gridy = 0;
        gbcMain.anchor = GridBagConstraints.CENTER; // Căn giữa theo chiều ngang và dọc
        gbcMain.weightx = 1.0; // Cho phép mở rộng theo chiều ngang
        gbcMain.weighty = 0.8; // Cho phép mở rộng theo chiều dọc (để pnForm chiếm phần lớn không gian)
        pnMain.add(pnForm, gbcMain);

        // Đặt pnButtons ở dưới pnForm, căn giữa
        gbcMain.gridy = 1;
        gbcMain.weighty = 0.2; // Chiếm phần còn lại của không gian
        gbcMain.insets = new Insets(20, 0, 0, 0); // Khoảng cách từ form
        pnMain.add(pnButtons, gbcMain);

        return pnMain;
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

        String[] columnNames = { "Mã Vé", "Phim", "Phòng", "Ghế", "Suất", "Ngày", "QR" };
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
            data[i][6] = ""; // Giả sử trả về ImageIcon QR
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
                return false;
            }
        };

        JTable table = new JTable(model);
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

    public JPanel hoaDon (){
        JPanel pnMain = new GradientPanel(new Color(10, 10, 30), new Color(60, 30, 180));
        pnMain.setLayout(new GridBagLayout()); 

        JLabel lblPlaceholder = new JLabel("Nội dung Hóa đơn sẽ hiển thị ở đây...");
        lblPlaceholder.setFont(new Font("Arial", Font.ITALIC, 20));
        lblPlaceholder.setForeground(new Color(220, 220, 220)); 
        pnMain.add(lblPlaceholder); 

        return pnMain;
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
        
        btnThoat.addActionListener(e -> {
            // Đóng cửa sổ chứa panel này, hoặc quay lại màn hình trước đó
            // Ví dụ: Lấy cửa sổ cha và đóng nó
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window instanceof JFrame) {
                ((JFrame) window).dispose(); // Đóng JFrame
            }
            // Hoặc nếu là JDialog, dùng ((JDialog)window).dispose();
        });
    }
}
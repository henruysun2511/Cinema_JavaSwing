package Views;

import javax.swing.*;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import Controllers.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import Models.*;

public class PromotionPanel extends JPanel {

    public PromotionPanel() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Tiêu đề của panel
        JLabel titleLabel = new JLabel("Ưu Đãi Đặc Biệt", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(51, 51, 51));
        add(titleLabel, BorderLayout.NORTH);

        // Panel chứa các ô khuyến mãi

        // Thêm các khuyến mãi vào grid

        // Cuộn nếu nội dung quá lớn
        JScrollPane scrollPane = new JScrollPane(hienThiDanhSachKhuyenMai());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Không cuộn ngang
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Cuộn dọc khi cần
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Xóa viền scrollpane
        add(scrollPane, BorderLayout.CENTER);

        // Đặt màu nền cho panel
        setBackground(new Color(249, 249, 249));
    }

    public JPanel hienThiDanhSachKhuyenMai() {
        ArrayList<Voucher> dsVoucher = VoucherController.layDanhSachVoucher();
        JPanel promotionGridPanel = new JPanel();

        promotionGridPanel.setLayout(new GridLayout(0, 3, 25, 25));
        promotionGridPanel.setBorder(new EmptyBorder(30, 0, 0, 0));
        for (Voucher vc : dsVoucher) {
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BorderLayout(0, 10)); 
            itemPanel.setBackground(Color.WHITE);
            itemPanel.setBorder(BorderFactory.createLineBorder(new Color(221, 221, 221), 1));
            itemPanel.setPreferredSize(new Dimension(250, 300)); 
            itemPanel.setMaximumSize(new Dimension(250, 300)); 

            // Ảnh khuyến mãi
            JLabel imageLabel = new JLabel();
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            try {
                // Tải ảnh từ resources folder
                URL imageUrl = getClass().getClassLoader().getResource(vc.getAnhVoucher());
                if (imageUrl != null) {
                    ImageIcon originalIcon = new ImageIcon(imageUrl);
                    // Điều chỉnh kích thước ảnh cho phù hợp
                    Image image = originalIcon.getImage();
                    Image scaledImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Kích thước ô vuông
                    imageLabel.setIcon(new ImageIcon(scaledImage));
                } else {
                    // Nếu không tìm thấy ảnh, hiển thị placeholder text
                    imageLabel.setText("No Image");
                    imageLabel.setPreferredSize(new Dimension(200, 200));
                    imageLabel.setBackground(Color.LIGHT_GRAY);
                    imageLabel.setOpaque(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
                imageLabel.setText("Error loading image");
                imageLabel.setPreferredSize(new Dimension(200, 200));
                imageLabel.setBackground(Color.LIGHT_GRAY);
                imageLabel.setOpaque(true);
            }
            itemPanel.add(imageLabel, BorderLayout.CENTER); 

            // Tiêu đề khuyến mãi
            JLabel titleLabel = new JLabel(vc.getTenVoucher(), SwingConstants.CENTER);
            titleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            titleLabel.setForeground(new Color(85, 85, 85));
            titleLabel.setBorder(new EmptyBorder(0, 10, 0, 10)); 
            itemPanel.add(titleLabel, BorderLayout.SOUTH); 

            // Nút "Đọc chi tiết"
            JButton detailButton = new JButton("Đọc chi tiết");
            detailButton.setBackground(new Color(0, 123, 255));
            detailButton.setForeground(Color.WHITE);
            detailButton.setFocusPainted(false); 
            detailButton.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
            detailButton.setFont(new Font("Arial", Font.BOLD, 14));
            detailButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
            detailButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Bạn đã nhấp vào: " + vc.getMoTa(), "Chi tiết khuyến mãi",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            });

            // Tạo một panel nhỏ để chứa nút và căn giữa nó
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.setBackground(Color.WHITE);
            buttonPanel.add(detailButton);
            itemPanel.add(buttonPanel, BorderLayout.PAGE_END); // Đặt nút ở cuối cùng của item

            promotionGridPanel.add(itemPanel); // Thêm item vào panel chứa grid
        }
        return promotionGridPanel;
    }
}

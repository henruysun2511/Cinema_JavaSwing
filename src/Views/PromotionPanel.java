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
import Utilzs.GradientPanel;

public class PromotionPanel extends JPanel {
	CardLayout cardLayout;
    JPanel mainContentPanel;

	public PromotionPanel(CardLayout cardLayout, JPanel mainContentPanel) {
		this.cardLayout = cardLayout;
        this.mainContentPanel = mainContentPanel;
        addControls();
    }
	public void addControls() {
         setLayout(new BorderLayout());
        
        JPanel pnMain = new GradientPanel(new Color(10, 10, 30), new Color(60, 30, 180));
        pnMain.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Ưu Đãi Đặc Biệt", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.white);
        titleLabel.setOpaque(false);
        pnMain.add(titleLabel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(hienThiDanhSachKhuyenMai());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        pnMain.add(scrollPane, BorderLayout.CENTER);

        add(pnMain,BorderLayout.CENTER);
	}

    public JPanel hienThiDanhSachKhuyenMai() {
        ArrayList<Voucher> dsVoucher = VoucherController.layDanhSachVoucher();
        JPanel promotionGridPanel = new GradientPanel(new Color(10, 10, 30), new Color(60, 30, 180));
        promotionGridPanel.setLayout(new GridLayout(0, 3, 25, 25));
        promotionGridPanel.setBorder(new EmptyBorder(30, 0, 0, 0));
        promotionGridPanel.setBackground(new Color(249, 249, 249));

        for (Voucher vc : dsVoucher) {
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
            itemPanel.setBackground(Color.decode("#004aad"));
            itemPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(221, 221, 221), 1),
                new EmptyBorder(10, 10, 10, 10)
            ));
            itemPanel.setPreferredSize(new Dimension(250, 320));

            // Ảnh
            JLabel imageLabel = new JLabel();
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            try {
                ImageIcon originalIcon = new ImageIcon(vc.getAnhVoucher());
                Image scaledImage = originalIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
            } catch (Exception e) {
                imageLabel.setText("No Image");
                imageLabel.setPreferredSize(new Dimension(200, 150));
                imageLabel.setOpaque(true);
                imageLabel.setBackground(Color.LIGHT_GRAY);
            }
            itemPanel.add(imageLabel);
            itemPanel.add(Box.createVerticalStrut(10));

            // Tiêu đề khuyến mãi
            JLabel titleLabel = new JLabel(vc.getTenVoucher(), SwingConstants.CENTER);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            itemPanel.add(titleLabel);
            itemPanel.add(Box.createVerticalStrut(15));

            // Nút chi tiết
            JButton detailButton = new JButton("Đọc chi tiết");
            detailButton.setBackground(Color.decode("#efb146"));
            detailButton.setForeground(Color.WHITE);
            detailButton.setFocusPainted(false);
            detailButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
            detailButton.setFont(new Font("Arial", Font.BOLD, 14));
            detailButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            detailButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            detailButton.addActionListener(e -> {
            	PromotionDetailPanel promotionDetailPanel = new PromotionDetailPanel(vc);
                mainContentPanel.add(promotionDetailPanel, "CHI_TIET_KHUYEN_MAI");
                cardLayout.show(mainContentPanel, "CHI_TIET_KHUYEN_MAI");
            });
            itemPanel.add(detailButton);

            promotionGridPanel.add(itemPanel);
        }

        return promotionGridPanel;
    }
}

package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.*;
import java.awt.*;

import Controllers.VoucherController;
import Models.*;
import Utilzs.GradientPanel;

public class PromotionDetailPanel extends JPanel {
	public PromotionDetailPanel(Voucher vc) {
		addControls(vc);
	}
	
	public void addControls(Voucher vc) {
		JPanel pnMain = new GradientPanel(new Color(10, 10, 30), new Color(60, 30, 180));
	    pnMain.setLayout(new BorderLayout(10, 10));  // giãn cách giữa các khu vực

	    // Tiêu đề lớn, đậm, màu trắng, căn giữa
	    JLabel lblTitle = new JLabel(vc.getTenVoucher());
	    lblTitle.setFont(new Font("Serif", Font.BOLD, 26));
	    lblTitle.setForeground(Color.WHITE);
	    lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTitle.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10)); // padding

	    // Ảnh lớn nằm giữa
	    JLabel imageLabel = new JLabel();
	    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    ImageIcon originalIcon = new ImageIcon(vc.getAnhVoucher());
	    Image scaledImage = originalIcon.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
	    imageLabel.setIcon(new ImageIcon(scaledImage));
	    imageLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

	    // Thời gian áp dụng - font nhỏ hơn, nghiêng, màu xám nhạt, căn giữa
	    JLabel lblThoiGianApDung = new JLabel("Thời gian áp dụng: " + vc.getNgayBatDau() + " - " + vc.getNgayKetThuc());
	    lblThoiGianApDung.setFont(new Font("Serif", Font.ITALIC, 14));
	    lblThoiGianApDung.setForeground(Color.LIGHT_GRAY);
	    lblThoiGianApDung.setHorizontalAlignment(SwingConstants.CENTER);
	    lblThoiGianApDung.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

	    // Nội dung mô tả - dùng JTextArea để hiển thị nhiều dòng, có scrollbar nếu dài
	    JTextArea txtNoiDung = new JTextArea(vc.getMoTa());
	    txtNoiDung.setLineWrap(true);
	    txtNoiDung.setWrapStyleWord(true);
	    txtNoiDung.setEditable(false);
	    txtNoiDung.setFont(new Font("Serif", Font.PLAIN, 16));
	    txtNoiDung.setForeground(Color.WHITE);
	    txtNoiDung.setOpaque(false);
	    txtNoiDung.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

	    JScrollPane scrollPane = new JScrollPane(txtNoiDung);
	    scrollPane.setBorder(null);
	    scrollPane.setPreferredSize(new Dimension(450, 200));
	    scrollPane.setOpaque(false);
	    scrollPane.getViewport().setOpaque(false);

	    // Tạo panel trung tâm để chứa ảnh, thời gian, nội dung
	    JPanel centerPanel = new JPanel();
	    centerPanel.setOpaque(false);
	    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
	    centerPanel.setBackground(new Color(10, 10, 30));
	    centerPanel.add(imageLabel);
	    centerPanel.add(lblThoiGianApDung);
	    centerPanel.add(scrollPane);

	    // Thêm các thành phần vào pnMain
	    pnMain.add(lblTitle, BorderLayout.NORTH);
	    pnMain.add(centerPanel, BorderLayout.CENTER);

	    this.setLayout(new BorderLayout());
	    this.add(pnMain, BorderLayout.CENTER);
		
	}

}

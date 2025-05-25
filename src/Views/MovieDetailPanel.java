package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.TitledBorder;

import Daos.PhimDao;
import Models.Phim;
import Controllers.PhimController;


public class MovieDetailPanel extends JPanel {
	JButton btnThoat, btnDatVe;
	
	public MovieDetailPanel(Phim p) {
		addControls(p);
		addEvents();
	}
	
	public void addControls(Phim p) {
		 this.setLayout(new BorderLayout());

		    JPanel pnMain = new JPanel();
		    pnMain.setLayout(new BorderLayout(20, 20));
		    pnMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // padding

		    // ======= TOP: Poster và thông tin cơ bản =======
		    JPanel pnTop = new JPanel(new BorderLayout(20, 0));

		    // Poster phim
		    ImageIcon img = new ImageIcon(p.getAnhPhim());
		    Image scaledImage = img.getImage().getScaledInstance(250, 370, Image.SCALE_SMOOTH);
		    JLabel lblAnhPhim = new JLabel(new ImageIcon(scaledImage));
		    lblAnhPhim.setPreferredSize(new Dimension(250, 370));

		    // Thông tin phim
		    JPanel pnInfo = new JPanel();
		    pnInfo.setLayout(new GridLayout(5, 1, 10, 10));
		    pnInfo.setBorder(BorderFactory.createTitledBorder("Thông tin phim"));
		    

		    JLabel lblTenPhim = new JLabel("Tên phim: " + p.getTenPhim());
		    lblTenPhim.setFont(new Font("Arial", Font.BOLD, 22));
		    JLabel lblNgayPhatHanh = new JLabel("Khởi chiếu: " + p.getNgayPhatHanh());
		    JLabel lblThoiLuong = new JLabel("Thời lượng: " + p.getThoiLuong() + " phút");
		    JLabel lblDaoDien = new JLabel("Đạo diễn: " + p.getDaoDien());
		    JLabel lblDoTuoiChoPhep = new JLabel("Độ tuổi: " + p.getDoTuoiChoPhep());

		    Font infoFont = new Font("Arial", Font.PLAIN, 18);
		    lblNgayPhatHanh.setFont(infoFont);
		    lblThoiLuong.setFont(infoFont);
		    lblDaoDien.setFont(infoFont);
		    lblDoTuoiChoPhep.setFont(infoFont);

		    pnInfo.add(lblTenPhim);
		    pnInfo.add(lblNgayPhatHanh);
		    pnInfo.add(lblThoiLuong);
		    pnInfo.add(lblDaoDien);
		    pnInfo.add(lblDoTuoiChoPhep);

		    pnTop.add(lblAnhPhim, BorderLayout.WEST);
		    pnTop.add(pnInfo, BorderLayout.CENTER);

		    // ======= CENTER: Nội dung phim =======
		    JPanel pnCenter = new JPanel();
		    pnCenter.setLayout(new GridLayout(2, 1, 10, 10));

		    // Nội dung phim
		    JTextArea txtNoiDung = new JTextArea(p.getNoiDung());
		    txtNoiDung.setFont(new Font("Arial", Font.PLAIN, 18));
		    txtNoiDung.setLineWrap(true);
		    txtNoiDung.setWrapStyleWord(true);
		    txtNoiDung.setEditable(false);
		    txtNoiDung.setOpaque(false);

		    JScrollPane scrollNoiDung = new JScrollPane(txtNoiDung);
		    scrollNoiDung.setBorder(BorderFactory.createTitledBorder("Nội dung phim"));
		    scrollNoiDung.setPreferredSize(new Dimension(1150, 150));
		    scrollNoiDung.setOpaque(false);
		    scrollNoiDung.getViewport().setOpaque(false);
		    scrollNoiDung.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		    // Diễn viên
		    JTextArea txtDienVien = new JTextArea("Diễn viên ở đây");
		    txtDienVien.setFont(new Font("Arial", Font.PLAIN, 18));
		    txtDienVien.setLineWrap(true);
		    txtDienVien.setWrapStyleWord(true);
		    txtDienVien.setEditable(false);
		    txtDienVien.setOpaque(false);

		    JScrollPane scrollDienVien = new JScrollPane(txtDienVien);
		    scrollDienVien.setBorder(BorderFactory.createTitledBorder("Diễn viên"));
		    scrollDienVien.setOpaque(false);
		    scrollDienVien.getViewport().setOpaque(false);
		    scrollDienVien.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		    pnCenter.add(scrollNoiDung);
		    pnCenter.add(scrollDienVien);

		    // ======= BOTTOM: Nút =======
		    JPanel pnButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));
		    btnThoat = new JButton("Thoát");
		    btnDatVe = new JButton("Đặt vé ngay");

		    Font buttonFont = new Font("Arial", Font.BOLD, 16);
		    btnThoat.setFont(buttonFont);
		    btnDatVe.setFont(buttonFont);
		    btnThoat.setBackground(new Color(108, 117, 125)); // Màu xám đẹp
		    btnDatVe.setBackground(new Color(220, 53, 69));   // Màu đỏ nổi bật
		    btnThoat.setForeground(Color.WHITE);
		    btnDatVe.setForeground(Color.WHITE);
		    btnThoat.setFocusPainted(false);
		    btnDatVe.setFocusPainted(false);

		    pnButton.add(btnThoat);
		    pnButton.add(btnDatVe);

		    // ======= Thêm vào giao diện chính =======
		    pnMain.add(pnTop, BorderLayout.NORTH);
		    pnMain.add(pnCenter, BorderLayout.CENTER);
		    pnMain.add(pnButton, BorderLayout.SOUTH);
		    
		    JScrollPane scp = new JScrollPane(pnMain,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		    this.add(scp, BorderLayout.CENTER);
	}
	
	public void addEvents() {
//		btnThoat.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				dispose();
//			}
//		});
		
		btnDatVe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
	}
	
	

}

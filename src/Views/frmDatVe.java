package Views;
import javax.swing.*;



import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import java.sql.*;
import java.sql.Date;

import Controllers.GheController;
import Controllers.LichChieuController;
import Models.Phim;
import Models.Ghe;
import Models.LichChieu;
import Controllers.*;

public class frmDatVe extends JFrame {
	JButton btnPrev, btnNext;
	CardLayout carLayout;
	JPanel pnRight;
	JPanel pnChonGhe, pnThanhToan;
	
	public frmDatVe(LichChieu l) {
		super("Đặt vé");
		showWindow();
		addControls(l);
		addEvents();
	}
	public void showWindow() {
		this.setSize(1300, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
	}
	public void addControls(LichChieu l) {
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());
		
		//Bên trái chứa thông tin vé
		JPanel pnLeft = new JPanel();
		pnLeft.setBackground(Color.black);
		pnLeft.setPreferredSize(new Dimension(270,600));
		
		//Bên phải chọn ghế
		carLayout = new CardLayout();
		pnRight = new JPanel();
		pnRight.setLayout(carLayout); //Right mang kiểu cardlayout chứa các cardlayout con
		
		//Button ở dưới
		JPanel pnButton = new JPanel();
		pnButton.setLayout(new BorderLayout());
		pnButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		btnPrev = new JButton("<<< Quay lại");
		btnNext = new JButton(">>> Tiếp theo");
		btnPrev.setVisible(false);
		pnButton.add(btnPrev, BorderLayout.WEST);
		pnButton.add(btnNext, BorderLayout.EAST);
		

		
		pnRight.add(pnChonGhe(l), "Chọn ghế");
		pnRight.add(pnThanhToan(), "Thanh toán");
		
		pnMain.add(pnLeft, BorderLayout.EAST);
		pnMain.add(pnRight, BorderLayout.CENTER);
		pnMain.add(pnButton,BorderLayout.SOUTH);
		
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		con.add(pnMain, BorderLayout.CENTER);
		
	}
	
	//Trang chọn ghế
	public JPanel pnChonGhe(LichChieu l) {
		pnChonGhe = new JPanel();
		pnChonGhe.setLayout(new BoxLayout(pnChonGhe, BoxLayout.Y_AXIS));
		
		JLabel lblChonGhe = new JLabel("CHỌN GHẾ");
		lblChonGhe.setFont(new Font("Arial", Font.BOLD, 28));
		lblChonGhe.setAlignmentX(Component.CENTER_ALIGNMENT); //Căn giữa
		
		pnChonGhe.add(lblChonGhe);
		
		//Danh sách ghế
		pnChonGhe.add(hienThiDanhSachGhe(l));
		
		//Chú thích
		JPanel pnChuThich = new JPanel();
		pnChuThich.setLayout(new FlowLayout());
		
		JPanel pnChuThich1 = new JPanel();
		pnChuThich1.setLayout(new FlowLayout());
		JButton btnThuong = new JButton();
		JLabel lblThuong=new JLabel("Ghế thường");
		pnChuThich1.add(btnThuong);
		pnChuThich1.add(lblThuong);
		
		JPanel pnChuThich2 = new JPanel();
		pnChuThich2.setLayout(new FlowLayout());
		JButton btnVip = new JButton();
		btnVip.setBackground(Color.orange);
		JLabel lblVip=new JLabel("Ghế vip");
		pnChuThich2.add(btnVip);
		pnChuThich2.add(lblVip);
		
		JPanel pnChuThich3 = new JPanel();
		pnChuThich3.setLayout(new FlowLayout());
		JButton btnDoi = new JButton();
		btnDoi.setBackground(Color.PINK);
		JLabel lblDoi=new JLabel("Ghế đôi");
		pnChuThich3.add(btnDoi);
		pnChuThich3.add(lblDoi);
		
		JPanel pnChuThich4 = new JPanel();
		pnChuThich4.setLayout(new FlowLayout());
		JButton btnDangChon = new JButton();
		JLabel lblDangChon=new JLabel("Đang chọn");
		btnDangChon.setBackground(Color.GREEN);
		pnChuThich4.add(btnDangChon);
		pnChuThich4.add(lblDangChon);
		
		JPanel pnChuThich5 = new JPanel();
		pnChuThich5.setLayout(new FlowLayout());
		JButton btnDaChon = new JButton();
		btnDaChon.setBackground(Color.gray);
		JLabel lblDaChon=new JLabel("Đã chọn");
		pnChuThich5.add(btnDaChon);
		pnChuThich5.add(lblDaChon);
		
		pnChuThich.add(pnChuThich1);
		pnChuThich.add(pnChuThich2);
		pnChuThich.add(pnChuThich3);
		pnChuThich.add(pnChuThich4);
		pnChuThich.add(pnChuThich5);
		pnChonGhe.add(pnChuThich);
		
		return pnChonGhe;
	}
	
	public JPanel hienThiDanhSachGhe(LichChieu l) {
		ArrayList<Ghe> dsGhe = GheController.layDanhSachGhe(l.getMaLichChieu());
		JPanel pnGhe = new JPanel();
		String maPhong = dsGhe.get(0).getMaPhong(); 
		if (maPhong.equals("RM007")) {
		    pnGhe.setLayout(new GridLayout(32, 28, 1, 1));
		} else {
		    pnGhe.setLayout(new GridLayout(10, 14, 2, 2)); // mặc định
		}

		
		pnGhe.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // padding
		pnGhe.setPreferredSize(new Dimension(600, 600)); 

		
		for(Ghe gh : dsGhe) {
			JButton btnGhe = new JButton(gh.getTenGhe());
			btnGhe.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
			pnGhe.add(btnGhe);
			if(gh.getLoaiGhe().equals("ST002")) {
				btnGhe.setBackground(Color.ORANGE);
			}
			if(gh.getLoaiGhe().equals("ST003")){
				btnGhe.setBackground(Color.pink);
			}
			btnGhe.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if (btnGhe.isSelected()) {
				        btnGhe.setBackground(Color.RED); // Đang được chọn
				    } else {
				        btnGhe.setBackground(null); // Trả lại màu gốc
				    }	
				}
			});
		}
		return pnGhe;
	}
	
	public JPanel pnThanhToan() {
		pnThanhToan = new JPanel();
		//pnThanhToan.setLayout(new BoxLayout(btnNext, BoxLayout.Y_AXIS));
		
		JLabel lblThanhToan = new JLabel("CHỌN GHẾ");
		lblThanhToan.setFont(new Font("Arial", Font.BOLD, 28));
		lblThanhToan.setAlignmentX(Component.CENTER_ALIGNMENT); //Căn giữa
		
		pnThanhToan.add(lblThanhToan);
		return pnThanhToan;
	}

	public void addEvents() {
		btnNext.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        carLayout.show(pnRight, "Thanh toán");
		        btnPrev.setVisible(true);
		    }
		});

		btnPrev.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        carLayout.show(pnRight, "Chọn ghế");
		        btnPrev.setVisible(false);
		    }
		});
		
	}

}

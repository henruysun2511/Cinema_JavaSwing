package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.TitledBorder;

import Daos.PhimDao;
import Models.Phim;
import Controllers.PhimController;


public class frmChiTietPhim extends JFrame {
	JButton btnThoat, btnDatVe;
	
	public frmChiTietPhim(Phim p) {
		this.setTitle("Chi tiết phim "+ p.getTenPhim());
		showWindow();
		addControls(p);
		addEvents();
		
	}
	
	public void showWindow() {
		this.setSize(600, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
	}
	
	public void addControls(Phim p) {
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		
		//pnTop chứa ảnh và các thông tin cơ bản
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new FlowLayout());
		
		//Chứa poster bên phải
		ImageIcon img = new ImageIcon(p.getAnhPhim());
		JLabel lblAnhPhim = new JLabel();
		lblAnhPhim.setIcon(img);
		
		//Chứa các thông tin cơ bản bên trái
		JPanel pnLeftofTop = new JPanel();
		pnLeftofTop.setLayout(new GridLayout(0, 1, 5, 5));
		JLabel lblTenPhim = new JLabel("Tên phim: " +p.getTenPhim());
		lblTenPhim.setFont(new Font("Arial", Font.PLAIN, 20));
		JLabel lblNgayPhatHanh = new JLabel("Khởi chiếu: "+ p.getNgayPhatHanh());
		JLabel lblThoiLuong = new JLabel("Thời lượng: "+ p.getThoiLuong()+" phút");
		JLabel lblDaoDien = new JLabel("Đạo diễn "+ p.getDaoDien());
		JLabel lblDoTuoiChoPhep = new JLabel("Thời luộng "+ p.getDoTuoiChoPhep());
		
		pnLeftofTop.add(lblTenPhim);
		pnLeftofTop.add(lblNgayPhatHanh);
		pnLeftofTop.add(lblThoiLuong);
		pnLeftofTop.add(lblDaoDien);
		pnLeftofTop.add(lblDoTuoiChoPhep);
		
		pnTop.add(lblAnhPhim);
		pnTop.add(pnLeftofTop);
		
		//pnCenter chứa nội dung phim và diễn viên và các nút
		JPanel pnCenter = new JPanel();
		pnCenter.setLayout(new BorderLayout());
		
		JTextArea txtNoiDung = new JTextArea(p.getNoiDung());
		txtNoiDung.setLineWrap(true);
		txtNoiDung.setWrapStyleWord(true);
		txtNoiDung.setEditable(false); // Không cho người dùng sửa
		txtNoiDung.setOpaque(false);   // Trong suốt như JLabel
		TitledBorder borderLeft = new TitledBorder(BorderFactory.createLineBorder(Color.RED),"Nội dung phim");
        txtNoiDung.setBorder(borderLeft);
        txtNoiDung.setPreferredSize(new Dimension(50,150));
        txtNoiDung.setFont(new Font("Arial", Font.PLAIN, 16));
        
        //pnButton chứa các nút
        JPanel pnButton = new JPanel();
        pnButton.setLayout(new FlowLayout());
        btnThoat = new JButton("Thoát");
        btnDatVe = new JButton("Đặt vé ngay");
        btnThoat.setFont(new Font("Arial", Font.BOLD, 16));
        btnDatVe.setFont(new Font("Arial", Font.BOLD, 16));
        //Màu nền nút
        btnThoat.setBackground(new Color(40, 167, 69));
        btnDatVe.setBackground(new Color(220, 53, 69));
        //Màu chữ
        btnThoat.setForeground(Color.WHITE);
        btnDatVe.setForeground(Color.WHITE);
        
      
        pnButton.add(btnThoat);
        pnButton.add(btnDatVe);
        
        pnCenter.add(txtNoiDung, BorderLayout.NORTH);
        pnCenter.add(pnButton, BorderLayout.SOUTH);
       

				
		
		
		
		pnMain.add(pnTop);
		pnMain.add(pnCenter);
		Container con = getContentPane();
		con.add(pnMain);
		
		
	}
	public void addEvents() {
		btnThoat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnDatVe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
	}
	
	

}

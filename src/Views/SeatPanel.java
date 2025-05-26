package Views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import Controllers.*;
import Models.*;


public class SeatPanel extends JPanel {
	JButton btnPrev, btnNext;
	CardLayout carLayout;
	JPanel pnRight;
	JPanel pnChonGhe, pnThanhToan;
	
	public SeatPanel(LichChieu l) {
		addControls(l);
	}
	
	public void addControls(LichChieu l) {
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());
		
		//Bên trái chứa thông tin vé
		JPanel pnLeft = new JPanel();
		pnLeft.setLayout(new BorderLayout());
		pnLeft.setBackground(Color.black);
		pnLeft.setPreferredSize(new Dimension(270,600));
		pnLeft.add(HienThiThongTinVe(l), BorderLayout.CENTER);
		
		
		//Bên phải chọn ghế
		pnRight = new JPanel();
		pnRight.setLayout(new BorderLayout()); 		
		pnRight.add(pnChonGhe(l), BorderLayout.CENTER);

		
		pnMain.add(pnLeft, BorderLayout.EAST);
		pnMain.add(pnRight, BorderLayout.CENTER);
		

		this.setLayout(new BorderLayout());
		this.add(pnMain, BorderLayout.CENTER);
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

			if (gh.getLoaiGhe().equals("ST002")) {
		        btnGhe.setBackground(Color.ORANGE); // VIP
		    } else if (gh.getLoaiGhe().equals("ST003")) {
		        btnGhe.setBackground(Color.PINK);   // Ghế đôi
		    } else {
		        btnGhe.setBackground(null);         // Ghế thường
		    }
			
			//Lưu màu vào ghế
			btnGhe.putClientProperty("originalColor", btnGhe.getBackground());
			btnGhe.addActionListener(new ActionListener() {
			        private boolean isSelected = false;
			        @Override
			        public void actionPerformed(ActionEvent e) {
			            isSelected = !isSelected;

			            if (isSelected) {
			                btnGhe.setBackground(Color.GREEN); // Chọn
			            } else {
			                Color originalColor = (Color) btnGhe.getClientProperty("originalColor");
			                btnGhe.setBackground(originalColor); // Trả lại màu gốc
			            }
			        }
			    });
		}
		return pnGhe;
	}
	
	public JPanel HienThiThongTinVe(LichChieu l) {
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		
		JPanel pnThongTinPhim = new JPanel();
		pnThongTinPhim.setLayout(new BoxLayout(pnThongTinPhim, BoxLayout.Y_AXIS));
		
		//Lấy thông tin phim từ suất chiếu
		Phim p = PhimController.layPhimTheoMaPhim(l.getMaPhim());
		ImageIcon imageIcon = new ImageIcon(p.getAnhPhim());
		Image image = imageIcon.getImage(); 
		Image scaledImage = image.getScaledInstance(170, 230, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		JLabel lblPoster = new JLabel(scaledIcon);
		JLabel lblTenPhim = new JLabel(p.getTenPhim());
		lblTenPhim.setFont(new Font("", Font.BOLD, 20));
		JLabel lblThoiLuong = new JLabel(String.valueOf(p.getThoiLuong()) + " phút - "+ p.getDoTuoiChoPhep());
		lblThoiLuong.setFont(new Font("", Font.PLAIN, 15));
		pnThongTinPhim.add(lblPoster);
		pnThongTinPhim.add(lblTenPhim);
		pnThongTinPhim.add(lblThoiLuong);
		
		//Lấy thông tin phòng từ suất chiếu
		PhongChieu ph = PhongChieuController.layPhongChieuTheoMaPhongChieu(l.getMaPhong());
		JLabel lblPhong = new JLabel("Phòng: "+ph +" - Định dạng: "+ph.getMaDinhDang());
		lblPhong.setFont(new Font("", Font.PLAIN, 15));
		
		//Thông tin suất chiếu
		JLabel lblSuatChieu = new JLabel("Suất: "+ l.getKhungGioChieuString() + " - Ngày: " + l.getNgayChieu());
		lblSuatChieu.setFont(new Font("", Font.PLAIN, 15));
		
		//Ghế
		JLabel lblGheChon = new JLabel("Ghế: "); 
		
				
		pnMain.add(pnThongTinPhim);
		pnMain.add(lblPhong);
		pnMain.add(lblSuatChieu);

		
		return pnMain;
	}
}

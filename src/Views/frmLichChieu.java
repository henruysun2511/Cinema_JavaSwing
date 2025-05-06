package Views;
import javax.swing.*;

import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import java.sql.*;
import java.sql.Date;
import Controllers.LichChieuController;
import Models.Phim;
import Models.LichChieu;


public class frmLichChieu extends JFrame {
	JPanel pnMain;
	JPanel pnCenter;
	JPanel pnPhim;

	
    public frmLichChieu(String tieude) {
        super(tieude);
        addControls();
        showWindow();
    }

    public void showWindow() {
        this.setSize(1200, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void addControls() {	
    	pnMain = new JPanel();
    	pnMain.setLayout(new BorderLayout());
    	//Thêm thanh cuộn cho pnMain
    	JScrollPane scp = new JScrollPane(pnMain,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    	
    	//Phần bên trên chưa danh sách các button
        pnMain.add(hienThiNgayChieu(),BorderLayout.NORTH);
    	
    	//Phần bên giữa chứa lịch chiếu
        //Mặc định luôn hiển thị Ngày chiếu thứ nhất
        ArrayList<Date> dsNgayChieu = LichChieuController.layDanhSachNgayChieu();
        if (!dsNgayChieu.isEmpty()) {
        	pnMain.add(hienThiPhimTheoNgayChieu(dsNgayChieu.get(0)), BorderLayout.CENTER);
        }
        
        // Panel chứa nút
        JPanel pnThoat = new JPanel();
        pnThoat.setLayout(new FlowLayout(FlowLayout.CENTER)); // căn giữa nút
       
        JButton btnThoat = new JButton("Thoát");
        btnThoat.setPreferredSize(new Dimension(150,30));
        btnThoat.setBackground(Color.red);
        btnThoat.setForeground(Color.white);
        btnThoat.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        btnThoat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
        pnThoat.add(btnThoat);
        
    	pnMain.add(pnThoat,BorderLayout.SOUTH);
    	
    	Container con = getContentPane();
    	con.setLayout(new BorderLayout());
    	con.add(scp, BorderLayout.CENTER);

    }
    public JPanel hienThiNgayChieu() {
    	JPanel pnButton = new JPanel();
    	pnButton.setLayout(new FlowLayout());
    	
    	ArrayList<Date> dsNgayChieu = LichChieuController.layDanhSachNgayChieu();
    	for(Date ngayChieu : dsNgayChieu) {
    		JButton btnNgayChieu= new JButton(ngayChieu.toString());
    		pnButton.add(btnNgayChieu);
    		
    		//Ấn vào lịch chiếu sẽ ra ngày tương ứng
    		btnNgayChieu.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	pnMain.removeAll(); // Xóa hết mọi thứ
                    pnMain.add(pnButton, BorderLayout.NORTH);
                    
                	Date selectedDate = Date.valueOf(btnNgayChieu.getText()); 
                  
                    pnMain.add(hienThiPhimTheoNgayChieu(selectedDate), BorderLayout.CENTER);
                    pnMain.revalidate();
                    pnMain.repaint();
                }
            });
    	}  	
    	return pnButton;
    } 
    
    
    public JPanel hienThiPhimTheoNgayChieu(Date ngayChieu) {
    	pnPhim = new JPanel();
    	pnPhim.setLayout(new GridLayout(0,2));
    	ArrayList<Phim> dsPhimTheoNgayChieu = LichChieuController.layDanhSachPhimTheoNgayChieu(ngayChieu) ;
    	for(Phim p : dsPhimTheoNgayChieu) {
    		JPanel pnPhimItem = new JPanel();
    		pnPhimItem.setLayout(new BorderLayout());
    		pnPhimItem.setPreferredSize(new Dimension(200,300));
    		
    		//Bên trái chứa ảnh
    		ImageIcon icon = new ImageIcon(p.getAnhPhim());
    		JLabel lblPoster = new JLabel();
    		lblPoster.setIcon(icon);
    		lblPoster.setPreferredSize(new Dimension(185,273));
    		
    		//Bên phải chứa thông tin phim chung và lịch chiếu
    		JPanel pnRight = new JPanel();
    		pnRight.setLayout(new BoxLayout(pnRight, BoxLayout.Y_AXIS));
    		pnRight.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10)); //padding
    		
    		//Tên phim
    		JLabel lblTenPhim = new JLabel(p.getTenPhim());
    		lblTenPhim.setFont(new Font("Arial", Font.PLAIN, 20));
    		lblTenPhim.setAlignmentX(Component.LEFT_ALIGNMENT); // Căn trái
    		//Thông tin chung
    		JLabel lblInfo = new JLabel(p.getThoiLuong() + " phút - " + p.getDoTuoiChoPhep());
    		lblInfo.setAlignmentX(Component.LEFT_ALIGNMENT); // Căn trái

    		//Bên dưới chưa danh sách lịch chiếu
    		JPanel pnLichChieu = new JPanel();
    		pnLichChieu.setLayout(new FlowLayout(FlowLayout.LEFT));
    		pnLichChieu.setAlignmentX(Component.LEFT_ALIGNMENT);
    		//Gán lịch chiếu cho từng phim
    		ArrayList<LichChieu> dsLichChieuTheoPhimVaNgay = LichChieuController.layDanhSachLichChieuTheoPhimVaNgay(p.getMaPhim(), ngayChieu);
    		for(LichChieu l : dsLichChieuTheoPhimVaNgay) {
    			JButton btnLichChieu = new JButton(l.getKhungGioChieuString().toString());
    			btnLichChieu.setCursor(new Cursor(Cursor.HAND_CURSOR)); 

    			pnLichChieu.add(btnLichChieu);
    			
    			//Khi ấn vào suất chiếu điều hướng sang trang Đặt vé theo mã suất chiếu
    			btnLichChieu.addActionListener(new ActionListener() {				
					@Override
					public void actionPerformed(ActionEvent e) {
						new frmDatVe(l);
						
					}
				});
    		}
	
    		pnRight.add(lblTenPhim);
    		pnRight.add(lblInfo);
    		pnRight.add(pnLichChieu);
   		
    		pnPhimItem.add(lblPoster,BorderLayout.WEST);
    		pnPhimItem.add(pnRight,BorderLayout.CENTER);
    		pnPhim.add(pnPhimItem);
    	}
    	return pnPhim;
    }

   

}

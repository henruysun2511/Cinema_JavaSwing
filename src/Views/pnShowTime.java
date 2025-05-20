package Views;

import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import java.sql.*;
import java.sql.Date;
import Models.*;
import Controllers.*;
import Utilzs.*;

public class pnShowTime extends JPanel {
	JButton btnThem, btnLuu, btnXoa, btnHuy, btnThoat, btnSua;
	JTextField txtMaSuatChieu, txtPhong, txtNgay, txtSuatChieu;
	JComboBox<String> cboPhim;
	
	
	public pnShowTime() {
		this.setBounds(100, 100, 693, 559);
		this.setLayout(new BorderLayout());
		
		//<===================pnTop chứa danh sách phòng=====================>
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new BoxLayout(pnTop, BoxLayout.Y_AXIS));
		
		//Tiêu đề
		JLabel lblTiTle = new JLabel("QUẢN LÝ SUẤT CHIẾU");
		lblTiTle.setFont(new Font("Arial", Font.BOLD, 20));
		lblTiTle.setAlignmentX(Component.CENTER_ALIGNMENT); //Căn giữa
		
		// Khởi tạo model cho DatePicker
        UtilDateModel model = new UtilDateModel();
        model.setSelected(true);
        // Cấu hình Properties cho DatePicker
        Properties p = new Properties();
        p.put("text.today", "Hôm nay");
        p.put("text.month", "Tháng");
        p.put("text.year", "Năm");
        // Tạo panel và DatePicker
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        
        JPanel pnDatePicker = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnDatePicker.add(datePicker);
		
        //Danh sách các phòng
        hienThiPhongChieu();

               
        pnTop.add(lblTiTle);
        pnTop.add(pnDatePicker);
        pnTop.add(hienThiPhongChieu());
		
       //<===================pnTop chứa thông tin và các nút=====================>
		JPanel pnBottom = new JPanel();
		//pnBottom.setBackground(Color.gray);
		pnBottom.setPreferredSize(new Dimension(693,120));
		pnBottom.setLayout(new FlowLayout());
		
		//PnLeftOfBottom chứa thông tin chung
		JPanel pnLeftOfBottom = new JPanel();
		pnLeftOfBottom.setLayout(new GridLayout(3,3,5,5));
		pnLeftOfBottom.setOpaque(false);
		
		
		JPanel pnMaSuatChieu = new JPanel();
		pnMaSuatChieu.setLayout(new FlowLayout());
		pnMaSuatChieu.setOpaque(false);
		JLabel lblMaSuatChieu = new JLabel("Mã suất chiếu:");
		lblMaSuatChieu.setForeground(Color.white);
		txtMaSuatChieu = new JTextField(10);
		pnMaSuatChieu.add(lblMaSuatChieu);
		pnMaSuatChieu.add(txtMaSuatChieu);
		
		JPanel pnPhim = new JPanel();
		pnPhim.setLayout(new FlowLayout());
		pnPhim.setOpaque(false);
		JLabel lblPhim = new JLabel("Phim:");
		lblPhim.setForeground(Color.white);
		lblPhim.setPreferredSize(lblMaSuatChieu.getPreferredSize());
		cboPhim = new JComboBox<String>();
		txtMaSuatChieu.setForeground(Color.white);
		pnPhim.add(lblPhim);
		pnPhim.add(cboPhim);
		
		JPanel pnSuatChieu = new JPanel();
		pnSuatChieu.setLayout(new FlowLayout());
		pnSuatChieu.setOpaque(false);
		JLabel lblSuatChieu = new JLabel("Suất chiếu:");
		lblSuatChieu.setForeground(Color.white);
		txtSuatChieu = new JTextField(10);
		pnSuatChieu.add(lblSuatChieu);
		pnSuatChieu.add(txtSuatChieu);
		
		JPanel pnPhong = new JPanel();
		pnPhong.setLayout(new FlowLayout());
		pnPhong.setOpaque(false);
		JLabel lblPhong = new JLabel("Phòng:");
		lblPhong.setForeground(Color.white);
		
		txtPhong = new JTextField(10);
		pnPhong.add(lblPhong);
		pnPhong.add(txtPhong);
		
		JPanel pnNgay = new JPanel();
		pnNgay.setLayout(new FlowLayout());
		pnNgay.setOpaque(false);
		JLabel lblNgay = new JLabel("Ngày:");
		lblNgay.setForeground(Color.white);
		lblNgay.setPreferredSize(lblPhong.getPreferredSize());
		txtNgay = new JTextField(10);
		pnNgay.add(lblNgay);
		pnNgay.add(txtNgay);
			
		pnLeftOfBottom.add(pnMaSuatChieu);
		pnLeftOfBottom.add(pnPhong);
		pnLeftOfBottom.add(pnPhim);
		pnLeftOfBottom.add(pnNgay);
		pnLeftOfBottom.add(pnSuatChieu);
		
		
		//PnRightOfBottom chứa các nút
		JPanel pnRightOfBottom = new JPanel();
		pnRightOfBottom.setLayout(new GridLayout(3, 3, 5, 5));
		pnRightOfBottom.setOpaque(false);
		pnRightOfBottom.setPreferredSize(new Dimension(150,100));
		
		btnThem = new JButton("Thêm");
		btnSua = new JButton("Sửa");
		btnXoa = new JButton("Xóa");
		btnLuu = new JButton("Lưu");
		btnHuy = new JButton("Hủy");
		btnThoat = new JButton("Thoát");
		
		btnThem.setBackground(Color.decode("#FFBD59"));
		btnSua.setBackground(Color.decode("#FFBD59"));
		btnXoa.setBackground(Color.decode("#FFBD59"));
		btnLuu.setBackground(Color.decode("#FFBD59"));
		btnHuy.setBackground(Color.decode("#FFBD59"));
		btnThoat.setBackground(Color.decode("#FFBD59"));
		
		pnRightOfBottom.add(btnThem);
		pnRightOfBottom.add(btnLuu);
		pnRightOfBottom.add(btnSua);
		pnRightOfBottom.add(btnXoa);
		pnRightOfBottom.add(btnHuy);
		pnRightOfBottom.add(btnThoat);
		
		
		pnBottom.add(pnLeftOfBottom);
		pnBottom.add(pnRightOfBottom);
		
		this.add(pnTop,BorderLayout.CENTER);
		this.add(pnBottom, BorderLayout.SOUTH);
}
	
	ArrayList<PhongChieu> dsPhongChieu = PhongChieuController.layDanhSachPhongChieu();
	public JPanel hienThiPhongChieu() {
		JPanel pnPhong = new JPanel();
		pnPhong.setLayout(new GridLayout(2,4,10,10));
		pnPhong.setPreferredSize(new Dimension(693, 400));
		
		for(PhongChieu phong : dsPhongChieu) {
			JPanel pnPhongItem = new JPanel(new BorderLayout());
	        pnPhongItem.setPreferredSize(new Dimension(300, 100)); // Chiều cao lớn để dễ scroll
	        pnPhongItem.setBackground(Color.decode("#004aad"));

	        String tenPhong = String.valueOf(phong.getTenPhong());
	        JButton btnPhong = new JButton(tenPhong);

	        // Tạo nội dung mô phỏng bên trong phòng chiếu (ví dụ ghế ngồi, thông tin, v.v...)
	        JPanel pnNoiDung = new JPanel();
	        pnNoiDung.setLayout(new GridLayout(10, 1)); // Giả sử có 10 mục
	        for (int i = 1; i <= 10; i++) {
	            pnNoiDung.add(new JLabel("Thông tin " + i));
	        }

	        // Tạo JScrollPane cho từng phòng
	        JScrollPane scrollPhongItem = new JScrollPane(pnNoiDung, 
	                                                      JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
	                                                      JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	        // Thêm các thành phần vào pnPhongItem
	        pnPhongItem.add(btnPhong, BorderLayout.NORTH);
	        pnPhongItem.add(scrollPhongItem, BorderLayout.CENTER);

	        // Cuối cùng thêm vào pnPhong
	        pnPhong.add(pnPhongItem);
		}
		return pnPhong;
	}
	

	
	
	
	public void addEvents() {
		
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Lịch Chiếu");
		frame.setBounds(100, 100, 693, 559);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		// Thêm panel vào frame
		pnShowTime panel = new pnShowTime();
		frame.add(panel);

		// Hiển thị frame
		frame.setVisible(true);
	}

}

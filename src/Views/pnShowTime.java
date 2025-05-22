package Views;

import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.ModuleLayer.Controller;
import java.text.*;
import java.util.*;
import java.sql.*;
import java.sql.Date;
import Models.*;
import Controllers.*;
import Utilzs.*;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class pnShowTime extends JPanel {
	JPanel pnTop;
	JDatePickerImpl datePicker;
	JButton btnThem, btnLuu, btnXoa, btnHuy, btnThoat, btnSua;
	JTextField txtMaSuatChieu, txtPhong, txtNgay, txtSuatChieu;
	JComboBox<Phim> cboPhim;
	JTable currentTable;
	boolean isAdding = false;
	
	public pnShowTime() {		
		this.setBounds(100, 100, 693, 559);
		this.setLayout(new BorderLayout());
		
		//<===================pnTop chứa danh sách phòng=====================>
		pnTop = new JPanel();
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
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        
        JPanel pnDatePicker = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnDatePicker.add(datePicker);
        
        //Ngày được chọn
        java.util.Date utilDate = (java.util.Date) datePicker.getModel().getValue();
        java.sql.Date selectedDate = new java.sql.Date(utilDate.getTime());
        
        //Mỗi khi click render lại
        datePicker.getModel().addChangeListener(e -> {
        	java.util.Date Date = (java.util.Date) datePicker.getModel().getValue();
            java.sql.Date newDate = new java.sql.Date(Date.getTime());
		    pnTop.remove(2); 
		    pnTop.add(hienThiPhongChieu(newDate));
		    pnTop.revalidate();
		    pnTop.repaint();
		    txtNgay.setText(String.valueOf(newDate));
		});
        		
        pnTop.add(lblTiTle);
        pnTop.add(pnDatePicker);
        pnTop.add(hienThiPhongChieu(selectedDate));
		
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
		//lblMaSuatChieu.setForeground(Color.white);
		txtMaSuatChieu = new JTextField(10);
		pnMaSuatChieu.add(lblMaSuatChieu);
		pnMaSuatChieu.add(txtMaSuatChieu);
		
		JPanel pnPhim = new JPanel();
		pnPhim.setLayout(new FlowLayout());
		pnPhim.setOpaque(false);
		JLabel lblPhim = new JLabel("Phim:");
		//lblPhim.setForeground(Color.white);
		cboPhim = new JComboBox<Phim>();
		cboPhim.setPreferredSize(new Dimension(150, 25)); 
        doDuLieuVaoComboBoxPhim(cboPhim);
		txtMaSuatChieu.setForeground(Color.white);
		pnPhim.add(lblPhim);
		pnPhim.add(cboPhim);
		
		JPanel pnSuatChieu = new JPanel();
		pnSuatChieu.setLayout(new FlowLayout());
		pnSuatChieu.setOpaque(false);
		JLabel lblSuatChieu = new JLabel("Suất chiếu:");
		//lblSuatChieu.setForeground(Color.white);
		txtSuatChieu = new JTextField(10);
		pnSuatChieu.add(lblSuatChieu);
		pnSuatChieu.add(txtSuatChieu);
		
		JPanel pnPhong = new JPanel();
		pnPhong.setLayout(new FlowLayout());
		pnPhong.setOpaque(false);
		JLabel lblPhong = new JLabel("Phòng:");
		//lblPhong.setForeground(Color.white);
		txtPhong = new JTextField(10);
		pnPhong.add(lblPhong);
		pnPhong.add(txtPhong);
		
		JPanel pnNgay = new JPanel();
		pnNgay.setLayout(new FlowLayout());
		pnNgay.setOpaque(false);
		JLabel lblNgay = new JLabel("Ngày:");
		//lblNgay.setForeground(Color.white);
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
		
		txtMaSuatChieu.setEnabled(false);
		txtNgay.setEnabled(false);
		btnLuu.setEnabled(false);
		
		addEvents();
}
	
	//Hiển thị danh sách phòng chiếu
	public JPanel hienThiPhongChieu(Date selectedDate) {
		ArrayList<PhongChieu> dsPhongChieu = PhongChieuController.layDanhSachPhongChieu();
		JPanel pnPhong = new JPanel();
		pnPhong.setLayout(new GridLayout(2,4,10,10));
		pnPhong.setPreferredSize(new Dimension(693, 400));
		
		for(PhongChieu phong : dsPhongChieu) {
			JPanel pnPhongItem = new JPanel(new BorderLayout());
	        pnPhongItem.setPreferredSize(new Dimension(300, 100)); 
	        pnPhongItem.setBackground(Color.decode("#004aad"));

	        String tenPhong = String.valueOf(phong.getTenPhong());
	    	JButton btnPhong = new JButton("Phòng " + tenPhong);
	        btnPhong.setBackground(Color.decode("#004aad"));
	        btnPhong.setForeground(Color.white);
	        
	        //Click vào phòng thì hiển thị lên txtPhong
	        btnPhong.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                txtPhong.setText(String.valueOf(tenPhong));
	            }
	        });

	        // Tạo bảng cho từng phòng
	        String[] columnNames = {"Mã lịch chiếu","Khung giờ","Mã phim", "Mã Phòng", "Ngày chiếu"};
	        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	        JTable table = new JTable(model);
	        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	        TableColumnModel columnModel = table.getColumnModel();
	        columnModel.getColumn(0).setPreferredWidth(80);
	        columnModel.getColumn(1).setPreferredWidth(80);
	        columnModel.getColumn(2).setPreferredWidth(50);
	        columnModel.getColumn(3).setPreferredWidth(70);
	        columnModel.getColumn(4).setPreferredWidth(100);
	        
	        
	        //Đổ dữ liệu lịch chiếu vào bảng
	        ArrayList<LichChieu> dsLichChieu = LichChieuController.layDanhSachLichChieuTheoPhongVaNgay(phong.getMaPhong(), selectedDate);
	        LichChieuController.doDuLieuLichChieuVaoBang(table, dsLichChieu);
	        addTableRowClickListener(table, txtMaSuatChieu, txtSuatChieu, cboPhim, txtPhong, txtNgay);
	        
	        table.getSelectionModel().addListSelectionListener(e -> {
	            if (!e.getValueIsAdjusting()) {
	                currentTable = table; // Lưu bảng đang thao tác
	            }
	        });
	        
	        JScrollPane scrollPhongItem = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	        // Thêm các thành phần vào pnPhongItem
	        pnPhongItem.add(btnPhong, BorderLayout.NORTH);
	        pnPhongItem.add(scrollPhongItem, BorderLayout.CENTER);

	        //Thêm vào pnPhong
	        pnPhong.add(pnPhongItem);
		}
		return pnPhong;
	}
	
	//Đổ dữ liệu tên phim vào ComboBox
	public static void doDuLieuVaoComboBoxPhim(JComboBox<Phim> comboBox) {
	    ArrayList<Phim> dsPhim = PhimController.layDanhSachPhim();
	    comboBox.removeAllItems(); 

	    for (Phim phim : dsPhim) {
	        comboBox.addItem(phim);  
	    }
	}
	
	//Viết sự kiện click vào hàng hiển thị thông tin lên textbox
	public void addTableRowClickListener(JTable table, JTextField txtMaSuatChieu,  JTextField txtKhungGio,  JComboBox<Phim> cboPhim,  JTextField txtMaPhong, JTextField txtNgayChieu) {   
	    table.getSelectionModel().addListSelectionListener(e -> {
	        if (!e.getValueIsAdjusting()) {
	        	if (isAdding) {
	                JOptionPane.showMessageDialog(null, "Đang ở chế độ thêm mới. Không thể chọn dòng!");
	                table.clearSelection();
	                return;
	            }
	        	
	            int selectedRow = table.getSelectedRow();
	            if (selectedRow != -1) {
	                txtMaSuatChieu.setText(table.getValueAt(selectedRow, 0).toString());
	                txtKhungGio.setText(table.getValueAt(selectedRow, 1).toString());
	                String maPhim = table.getValueAt(selectedRow, 2).toString();
	                for (int i = 0; i < cboPhim.getItemCount(); i++) {
	                    Phim phim = cboPhim.getItemAt(i);
	                    if (phim.getMaPhim().equals(maPhim)) {
	                        cboPhim.setSelectedIndex(i);
	                        break;
	                    }
	                }
	                PhongChieu pc = PhongChieuController.layPhongChieuTheoMaPhongChieu(table.getValueAt(selectedRow, 3).toString());
	                txtMaPhong.setText(String.valueOf(pc.getTenPhong()));
	                txtNgayChieu.setText(table.getValueAt(selectedRow, 4).toString());
	            }
	            System.out.println("Clicked row: " + selectedRow);
	            System.out.println("Value at column 0: " + table.getValueAt(selectedRow, 0));
	        }
	    });
	}
	
	public void addEvents() {
	    btnThem.addActionListener(e -> xuLyThem());
	    btnLuu.addActionListener(e -> xuLyLuu());
	    btnSua.addActionListener(e -> xuLySua());
	    btnXoa.addActionListener(e -> xuLyXoa());
	}
	
	
	public void reset() {
		txtMaSuatChieu.setText(""); 
	    txtSuatChieu.setText("");
	    txtPhong.setText("");
	    txtNgay.setText(String.valueOf(new java.sql.Date(((java.util.Date) datePicker.getModel().getValue()).getTime())));
	    cboPhim.setSelectedIndex(0);
	}
	
	public void xuLyThem() {
	    isAdding = true;
	    reset();
	    String maCuoi = generateIDInOrder.layMaCuoiCung("tblShowTime", "showtime_id"); // =>ST132
        String maMoi = generateIDInOrder.sinhMaTuDong(maCuoi, "ST"); //=>ST133
        txtMaSuatChieu.setText(maMoi);	    
	    btnThem.setEnabled(false);
	    btnSua.setEnabled(false);
	    btnXoa.setEnabled(false);
	    btnLuu.setEnabled(true);
	}
	
	public void xuLyLuu() {
	    if (!isAdding) return;

	    String maSuatChieu = txtMaSuatChieu.getText().trim();
	    String tenPhong = txtPhong.getText();
	    String suatChieu = txtSuatChieu.getText().trim();
	    String ngay = txtNgay.getText().trim();
	    Phim phim = (Phim) cboPhim.getSelectedItem();

	    InputValidate.showTimeValidate(maSuatChieu, suatChieu, tenPhong, ngay);

	    LichChieu lc = new LichChieu();
	    lc.setMaLichChieu(maSuatChieu);
	    lc.setMaPhong(PhongChieuController.layMaPhongTheoTenPhong(Integer.parseInt(tenPhong)));
	    lc.setKhungGioChieuString(ParseDate.parseToTime(suatChieu));
	    lc.setMaPhim(phim.getMaPhim());
	    lc.setNgayChieu(ParseDate.parseToDate(ngay));

	    boolean kq = LichChieuController.themLichChieu(lc);
	    if (kq) {
	        JOptionPane.showMessageDialog(this, "Thêm lịch chiếu thành công.");
	        isAdding = false;
	        btnThem.setEnabled(true);
	        btnSua.setEnabled(true);
	        btnXoa.setEnabled(true);
	        btnLuu.setEnabled(false);
	        
	        capNhatGiaoDienLichChieu();
	    } else {
	        JOptionPane.showMessageDialog(this, "Thêm thất bại!");
	    }
	}
	
	public void xuLySua() {
		if (currentTable == null) {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn một lịch chiếu để sửa.");
	        return;
	    }
	    int selectedRow = currentTable.getSelectedRow();
	    if (selectedRow == -1) {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn một lịch chiếu để sửa.");
	        return;
	    }

	    String maSuatChieu = txtMaSuatChieu.getText().trim();
	    String tenPhong = txtPhong.getText();
	    String suatChieu = txtSuatChieu.getText().trim();
	    String ngay = txtNgay.getText().trim();
	    Phim phim = (Phim) cboPhim.getSelectedItem();
	    
	    InputValidate.showTimeValidate(maSuatChieu, suatChieu, tenPhong, ngay);

	    LichChieu lc = new LichChieu();
	    lc.setMaLichChieu(maSuatChieu);
	    lc.setMaPhong(PhongChieuController.layMaPhongTheoTenPhong(Integer.parseInt(tenPhong)));
	    lc.setKhungGioChieuString(ParseDate.parseToTime(suatChieu));
	    lc.setMaPhim(phim.getMaPhim());
	    lc.setNgayChieu(ParseDate.parseToDate(ngay));

	    boolean kq = LichChieuController.suaLichChieu(lc);
	    if (kq) {
	        JOptionPane.showMessageDialog(this, "Cập nhật thành công.");
	        capNhatGiaoDienLichChieu();
	    } else {
	        JOptionPane.showMessageDialog(this, "Cập nhật thất bại.");
	    }
	}
	
	public void xuLyXoa() {
		if (currentTable == null) {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn một lịch chiếu để xóa.");
	        return;
	    }
	    int selectedRow = currentTable.getSelectedRow();
	    if (selectedRow == -1) {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn một lịch chiếu để xóa.");
	        return;
	    }

	    String maLichChieu = txtMaSuatChieu.getText();
	    int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
	    if (confirm == JOptionPane.YES_OPTION) {
	        boolean kq = LichChieuController.xoaLichChieu(maLichChieu);
	        if (kq) {
	            JOptionPane.showMessageDialog(this, "Xóa thành công.");
	            capNhatGiaoDienLichChieu();
	        } else {
	            JOptionPane.showMessageDialog(this, "Xóa thất bại.");
	        }
	    }
	}
	
	private void capNhatGiaoDienLichChieu() {
		java.util.Date Date = (java.util.Date) datePicker.getModel().getValue();
        java.sql.Date newDate = new java.sql.Date(Date.getTime());
	    pnTop.remove(2); 
	    pnTop.add(hienThiPhongChieu(newDate));
	    pnTop.revalidate();
	    pnTop.repaint();
	}
	
//	public void setLuuListener(ActionListener listener) {
//	    btnLuu.addActionListener(listener);
//	}
//
//	public void setXoaListener(ActionListener listener) {
//	    btnXoa.addActionListener(listener);
//	}
//
//	public void setSuaListener(ActionListener listener) {
//	    btnSua.addActionListener(listener);
//	}
//	
//	public String getMaSuatChieu() {
//		return txtMaSuatChieu.getText();
//	}
//	
//	public java.sql.Time getSuatChieu() {
//		return ParseDate.parseToTime(txtSuatChieu.getText());
//	}
//	
//	public String getMaPhim(){
//		Phim selectedPhim = (Phim) cboPhim.getSelectedItem();
//		String maPhim = selectedPhim.getMaPhim();
//		return maPhim;
//	}
//	
//	public String getMaPhong() {
//		String phongText = txtPhong.getText().trim();
//	    if (phongText.isEmpty()) {
//	        return ""; 
//	    }
//	    String maPhong = PhongChieuController.layMaPhongTheoTenPhong(Integer.parseInt(phongText));
//	    return maPhong;
//	}
//	
//	public java.sql.Date getNgay() {
//		 java.sql.Date ngay = ParseDate.parseToDate(txtNgay.getText());
//		    if (ngay == null) {
//		        System.err.println("Lỗi: Ngày nhập vào không hợp lệ hoặc trống.");
//		    }
//		   return ngay;
//	}
//	
//	public JTable getTable() { 
//		return table;
//	}
	

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

package Views;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.sql.Date;
import Controllers.LichChieuController;
import Models.Phim;
import Utilzs.GradientPanel;
import Models.LichChieu;

public class ShowTimePanel extends JPanel {
	JPanel pnMain;
	JPanel pnCenter;
	JPanel pnPhim;
	JPanel mainContentPanel;
	CardLayout cardLayout;

	public ShowTimePanel(CardLayout cardLayout, JPanel mainContentPanel) {
		this.cardLayout = cardLayout;
		this.mainContentPanel = mainContentPanel;
		addControls();
	}

	public void addControls() {
		pnMain = new GradientPanel(new Color(10, 10, 30), new Color(60, 30, 180));
		pnMain.setLayout(new BorderLayout());
		// Thêm thanh cuộn cho pnMain
		JScrollPane scp = new JScrollPane(pnMain, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		// Phần bên trên chưa danh sách các button
		pnMain.add(hienThiNgayChieu(), BorderLayout.NORTH);

		// Phần bên giữa chứa lịch chiếu
		// Mặc định luôn hiển thị Ngày chiếu thứ nhất
		ArrayList<Date> dsNgayChieu = LichChieuController.layDanhSachNgayChieu();
		if (!dsNgayChieu.isEmpty()) {
			pnMain.add(hienThiPhimTheoNgayChieu(dsNgayChieu.get(0)), BorderLayout.CENTER);
		}

		this.setLayout(new BorderLayout());
		this.add(scp, BorderLayout.CENTER);
	}

	public JPanel hienThiNgayChieu() {
		JPanel pnButton = new JPanel();
		pnButton.setLayout(new FlowLayout());
		pnButton.setOpaque(false);

		ArrayList<Date> dsNgayChieu = LichChieuController.layDanhSachNgayChieu();
		for (Date ngayChieu : dsNgayChieu) {
			JButton btnNgayChieu = new JButton(ngayChieu.toString());
			btnNgayChieu.setBackground(Color.decode("#ECF0F1"));
			pnButton.add(btnNgayChieu);

			// Ấn vào lịch chiếu sẽ ra ngày tương ứng
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
		pnPhim.setOpaque(false);
		pnPhim.setLayout(new GridLayout(0, 2));
		ArrayList<Phim> dsPhimTheoNgayChieu = LichChieuController.layDanhSachPhimTheoNgayChieu(ngayChieu);
		for (Phim p : dsPhimTheoNgayChieu) {
			JPanel pnPhimItem = new JPanel();
			pnPhimItem.setLayout(new BorderLayout());
			pnPhimItem.setPreferredSize(new Dimension(200, 300));
			pnPhimItem.setOpaque(false);

			// Bên trái chứa ảnh
			ImageIcon icon = new ImageIcon(p.getAnhPhim());
			JLabel lblPoster = new JLabel();
			lblPoster.setIcon(icon);
			lblPoster.setPreferredSize(new Dimension(185, 273));

			// Bên phải chứa thông tin phim chung và lịch chiếu
			JPanel pnRight = new JPanel();
			pnRight.setLayout(new BoxLayout(pnRight, BoxLayout.Y_AXIS));
			pnRight.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10)); // padding

			// Tên phim
			JLabel lblTenPhim = new JLabel(p.getTenPhim());
			lblTenPhim.setFont(new Font("Arial", Font.PLAIN, 20));
			lblTenPhim.setAlignmentX(Component.LEFT_ALIGNMENT); // Căn trái
			lblTenPhim.setForeground(Color.white);
			// Thông tin chung
			JLabel lblInfo = new JLabel(p.getThoiLuong() + " phút - " + p.getDoTuoiChoPhep());
			lblInfo.setAlignmentX(Component.LEFT_ALIGNMENT); // Căn trái
			lblInfo.setForeground(Color.white);

			// Bên dưới chưa danh sách lịch chiếu
			JPanel pnLichChieu = new JPanel();
			pnLichChieu.setLayout(new FlowLayout(FlowLayout.LEFT));
			pnLichChieu.setAlignmentX(Component.LEFT_ALIGNMENT);
			pnLichChieu.setOpaque(false);
			// Gán lịch chiếu cho từng phim
			ArrayList<LichChieu> dsLichChieuTheoPhimVaNgay = LichChieuController
					.layDanhSachLichChieuTheoPhimVaNgay(p.getMaPhim(), ngayChieu);
			for (LichChieu l : dsLichChieuTheoPhimVaNgay) {
				JButton btnLichChieu = new JButton(l.getKhungGioChieuString().toString());
				btnLichChieu.setCursor(new Cursor(Cursor.HAND_CURSOR));
				btnLichChieu.setBackground(Color.decode("#ECF0F1"));

				pnLichChieu.add(btnLichChieu);

				// Khi ấn vào suất chiếu điều hướng sang trang Đặt vé theo mã suất chiếu
				btnLichChieu.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// Điều hướng sang trang đặt vé
						BookingPanel bookingControllPanel = new BookingPanel(l);
						mainContentPanel.add(bookingControllPanel, "DAT_VE");
						cardLayout.show(mainContentPanel, "DAT_VE");
					}
				});
			}

			pnRight.add(lblTenPhim);
			pnRight.add(lblInfo);
			pnRight.add(pnLichChieu);
			pnRight.setOpaque(false);

			pnPhimItem.add(lblPoster, BorderLayout.WEST);
			pnPhimItem.add(pnRight, BorderLayout.CENTER);
			pnPhim.add(pnPhimItem);
		}
		return pnPhim;
	}

}

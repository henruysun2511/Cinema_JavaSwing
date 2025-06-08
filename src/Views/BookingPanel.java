package Views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import Controllers.*;
import Utilzs.*;
import Models.*;

public class BookingPanel extends JPanel {
	JPanel mainContentPanel;
	CardLayout cardLayout;
	JButton btnPrev, btnNext;
	int[] currentPage = { 1 };
	private LichChieu currentLichChieu;

	JPanel panelThongTinVe;
	public Set<Ghe> gheDangChon;
	private Map<Integer, JButton> seatButtonsMap = new HashMap<>();

	String maVoucher;
	private String voucherDangChon = null;
	private float phanTramGiam = 0;

	private int tongTienThanhToan = 0;

	JPanel pnLeft, pnButton;

	String maHoaDonVe;

	public BookingPanel(LichChieu l) {
		this.currentLichChieu = l;
		gheDangChon = new LinkedHashSet<>(); // Khởi tạo ghế đang chọn
		addControls(l);
		addEvents();
	}

	public void addControls(LichChieu l) {
		cardLayout = new CardLayout();
		mainContentPanel = new JPanel(cardLayout);

		// Khởi tạo các panel cho CardLayout
		JPanel seatSelectionPanel = pnChonGhe(l); // Đây là nội dung chính của trang 1
		JPanel paymentVoucherPanel = pnThanhToanVaVoucher(l); // Đây là nội dung chính của trang 2

		mainContentPanel.add(seatSelectionPanel, "1"); // Trang chọn ghế
		mainContentPanel.add(paymentVoucherPanel, "2"); // Trang thanh toán (bao gồm chọn voucher)

		// Cột bên trái chứa thông tin vé
		pnLeft = new JPanel();
		pnLeft.setLayout(new BorderLayout());
		pnLeft.setBackground(Color.black);
		pnLeft.setPreferredSize(new Dimension(270, 600));
		pnLeft.add(hienThiThongTinVe(l), BorderLayout.CENTER);

		// Phần điều hướng dưới cùng
		pnButton = new JPanel();
		pnButton.setOpaque(false);
		pnButton.setLayout(new BorderLayout());
		pnButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		btnPrev = new JButton("<<< Quay lại");
		btnNext = new JButton(">>> Tiếp theo");
		pnButton.add(btnPrev, BorderLayout.WEST);
		pnButton.add(btnNext, BorderLayout.EAST);

		// Sắp xếp tổng thể BookingControlPanel
		JPanel pnMain = new JPanel();
		pnMain = new GradientPanel(new Color(10, 10, 30), new Color(60, 30, 180));
		pnMain.setLayout(new BorderLayout());
		pnMain.add(pnLeft, BorderLayout.EAST); // Cột thông tin vé bên phải
		pnMain.add(mainContentPanel, BorderLayout.CENTER); // Nội dung chính giữa (chọn ghế/thanh toán)
		pnMain.add(pnButton, BorderLayout.SOUTH); // Nút điều hướng dưới cùng

		this.setLayout(new BorderLayout());
		this.add(pnMain, BorderLayout.CENTER);
	}

	public void addEvents() {
		btnPrev.addActionListener(e -> {
			if (currentPage[0] > 1) {
				currentPage[0]--;
				cardLayout.show(mainContentPanel, String.valueOf(currentPage[0]));
				updateButtonStates();
			}
		});

		btnNext.addActionListener(e -> {
			if (currentPage[0] == 1) {
				capNhatThongTinVe(panelThongTinVe, currentLichChieu, gheDangChon);
			}
			if (currentPage[0] < 2) {
				currentPage[0]++;
				cardLayout.show(mainContentPanel, String.valueOf(currentPage[0]));
				updateButtonStates();
			} else {
				if (gheDangChon.isEmpty()) {
					JOptionPane.showMessageDialog(this, "Vui lòng chọn ghế trước khi thanh toán.", "Lỗi",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				int result = JOptionPane.showConfirmDialog(
						this,
						"Bạn có chắc chắn muốn thanh toán không?",
						"Xác nhận thanh toán",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);

				if (result == JOptionPane.YES_OPTION) {
					boolean success = themTinhTrangGhe();
					if (success) {
						boolean ticketCreationSuccess = themVe();
						if (ticketCreationSuccess) {
							// Điều hướng sang trang Hóa đơn vé
							mainContentPanel.removeAll(); // Xóa toàn bộ nội dung cũ
							mainContentPanel.add(new TicketBillPanel(maHoaDonVe), "HOA_DON_VE");
							cardLayout.show(mainContentPanel, "HOA_DON_VE");
							mainContentPanel.revalidate();
							mainContentPanel.repaint();

							pnLeft.setVisible(false);
                            pnButton.setVisible(false);

							this.revalidate();
                            this.repaint();
						}
					}
				}
			}
		});

		updateButtonStates();
	}

	private void updateButtonStates() {
		btnPrev.setEnabled(currentPage[0] > 1);

		if (currentPage[0] == 2) {
			btnNext.setText("Thanh toán");
		} else {
			btnNext.setText(">>> Tiếp theo");
		}
	}

	public boolean themTinhTrangGhe() {
		boolean updateSuccess = GheController.datGhe(gheDangChon, currentLichChieu.getMaLichChieu());
		if (updateSuccess) {
			JOptionPane.showMessageDialog(this, "Thanh toán thành công! Ghế đã được đặt.", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
			return true;
		} else {
			JOptionPane.showMessageDialog(this, "Thanh toán thất bại", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
	}

	public boolean themVe() {
		// Tạo mã hóa đơn vé mới
		String maCuoi = generateIDInOrder.layMaCuoiCung("tblTicketBill", "payment_id");
		String maMoi = generateIDInOrder.sinhMaTuDong(maCuoi, "TB");
		maHoaDonVe = maMoi;
		int soLuongVe = gheDangChon.size();
		double tongTienVe = (double) tongTienThanhToan;
		String maVoucherApDung = maVoucher;
		String maNguoiDung = "US001";
		String pttt = "online";
		String maQr = "chua co";
		java.sql.Timestamp lichSuGiaoDich = new java.sql.Timestamp(System.currentTimeMillis());

		HoaDonVe hoaDonVe = new HoaDonVe(maHoaDonVe, soLuongVe, tongTienVe, maVoucherApDung, maNguoiDung, pttt, maQr,
				lichSuGiaoDich);

		// Thêm hóa đơn vé
		boolean themThanhCong = VeController.themHoaDonVe(hoaDonVe);

		if (!themThanhCong) {
			JOptionPane.showMessageDialog(this, "Thêm hóa đơn vé thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// Thêm từng vé
		for (Ghe ghe : gheDangChon) {
			String maVeCuoi = generateIDInOrder.layMaCuoiCung("tblTicket", "ticket_id");
			String maVeMoi = generateIDInOrder.sinhMaTuDong(maVeCuoi, "TK");
			String maVe = maVeMoi;
			LoaiGhe lg = GheController.layLoaiGheTheoMa(ghe.getLoaiGhe());
			double giaVe = (double) lg.getGiaGhe();

			Ve ve = new Ve(maVe, ghe.getMaGhe(), currentLichChieu.getMaLichChieu(), giaVe, maHoaDonVe);
			boolean themChiTiet = VeController.themVe(ve);

			if (!themChiTiet) {
				JOptionPane.showMessageDialog(this, "Thêm chi tiết vé thất bại cho ghế: " + ghe.getTenGhe(), "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		JOptionPane.showMessageDialog(this, "Đặt vé thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		return true;
	}

	public JPanel pnChonGhe(LichChieu l) {
		JPanel pnChonGhe = new GradientPanel(new Color(10, 10, 30), new Color(60, 30, 180));
		pnChonGhe.setLayout(new BoxLayout(pnChonGhe, BoxLayout.Y_AXIS));

		JLabel lblChonGhe = new JLabel("CHỌN GHẾ");
		lblChonGhe.setFont(new Font("Arial", Font.BOLD, 28));
		lblChonGhe.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblChonGhe.setForeground(Color.WHITE);

		pnChonGhe.add(lblChonGhe);
		pnChonGhe.add(hienThiDanhSachGhe(l)); 

		// Chú thích (giữ nguyên)
		JPanel pnChuThich = new JPanel();
		pnChuThich.setLayout(new FlowLayout());
		pnChuThich.setOpaque(false);

		JPanel pnChuThich1 = new JPanel();
	    pnChuThich1.setOpaque(false);
		pnChuThich1.setLayout(new FlowLayout());
		JButton btnThuong = new JButton();
		JLabel lblThuong = new JLabel("Ghế thường");
		lblThuong.setForeground(Color.WHITE);
		pnChuThich1.add(btnThuong);
		pnChuThich1.add(lblThuong);

		JPanel pnChuThich2 = new JPanel();
		pnChuThich2.setOpaque(false);
		pnChuThich2.setLayout(new FlowLayout());
		JButton btnVip = new JButton();
		btnVip.setBackground(Color.orange);
		JLabel lblVip = new JLabel("Ghế vip");
		lblVip.setForeground(Color.WHITE);
		pnChuThich2.add(btnVip);
		pnChuThich2.add(lblVip);

		JPanel pnChuThich3 = new JPanel();
		pnChuThich3.setLayout(new FlowLayout());
		pnChuThich3.setOpaque(false);
		JButton btnDoi = new JButton();
		btnDoi.setBackground(Color.PINK);
		JLabel lblDoi = new JLabel("Ghế đôi");
		lblDoi.setForeground(Color.WHITE);
		pnChuThich3.add(btnDoi);
		pnChuThich3.add(lblDoi);

		JPanel pnChuThich4 = new JPanel();
		pnChuThich4.setOpaque(false);
		pnChuThich4.setLayout(new FlowLayout());
		JButton btnDangChon = new JButton();
		JLabel lblDangChon = new JLabel("Đang chọn");
		lblDangChon.setForeground(Color.WHITE);
		btnDangChon.setBackground(Color.GREEN);
		pnChuThich4.add(btnDangChon);
		pnChuThich4.add(lblDangChon);

		JPanel pnChuThich5 = new JPanel();
		pnChuThich5.setOpaque(false);
		pnChuThich5.setLayout(new FlowLayout());
		JButton btnDaChon = new JButton();
		btnDaChon.setBackground(Color.gray);
		JLabel lblDaChon = new JLabel("Đã chọn");
		lblDaChon.setForeground(Color.WHITE);
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
		pnGhe = new GradientPanel(new Color(10, 10, 30), new Color(60, 30, 180));
		String maPhong = dsGhe.get(0).getMaPhong();

		if (maPhong.equals("RM005")) {
			pnGhe.setLayout(new GridLayout(5, 8, 1, 1));
			pnGhe.setPreferredSize(new Dimension(1200, 700));
		} else if (maPhong.equals("RM006")) {
			pnGhe.setLayout(new GridLayout(6, 4, 1, 1));
			pnGhe.setPreferredSize(new Dimension(1200, 700));
		} else if (maPhong.equals("RM007")) {
			pnGhe.setLayout(new GridLayout(14, 20, 1, 1));
			pnGhe.setPreferredSize(new Dimension(1200, 700));
		} else {
			pnGhe.setLayout(new GridLayout(10, 14, 2, 2));
			pnGhe.setPreferredSize(new Dimension(700, 500));
		}

		seatButtonsMap.clear(); // Xóa các nút cũ nếu có
		Map<Integer, Ghe> indexToGhe = new HashMap<>();
		Set<String> gheDaDat = GheController.layDanhSachGheDaDat(l.getMaLichChieu());

		for (int i = 0; i < dsGhe.size(); i++) {
			Ghe gh = dsGhe.get(i);
			JButton btnGhe = new JButton(gh.getTenGhe());
			btnGhe.setCursor(new Cursor(Cursor.HAND_CURSOR));

			if (maPhong.equals("RM007")) {
				btnGhe.setFont(new Font("Arial", Font.BOLD, 9));
			}

			if (gheDaDat.contains(gh.getMaGhe())) { // Ghế đã bán
				btnGhe.setBackground(Color.GRAY);
				btnGhe.setEnabled(false);
			} else if (gh.getLoaiGhe().equals("ST002")) {
				btnGhe.setBackground(Color.ORANGE); // VIP
			} else if (gh.getLoaiGhe().equals("ST003")) {
				btnGhe.setBackground(Color.PINK); // Ghế đôi
			} else {
				btnGhe.setBackground(null); // Ghế thường
			}

			btnGhe.putClientProperty("originalColor", btnGhe.getBackground());
			seatButtonsMap.put(i, btnGhe); // Lưu trữ nút
			indexToGhe.put(i, gh);
			pnGhe.add(btnGhe);
		}

		for (int i = 0; i < dsGhe.size(); i++) {
			final int index = i;
			JButton btnGhe = seatButtonsMap.get(index); // Lấy nút từ map
			Ghe ghe = indexToGhe.get(index);

			btnGhe.addActionListener(e -> {
				boolean dangChon = gheDangChon.contains(ghe);

				if (ghe.getLoaiGhe().equals("ST003")) {
					int pairIndex = (index % 2 == 0) ? index + 1 : index - 1;

					if (seatButtonsMap.containsKey(pairIndex)) { // Kiểm tra trong map
						JButton pairBtn = seatButtonsMap.get(pairIndex);
						Ghe pairGhe = indexToGhe.get(pairIndex);

						if (pairGhe.getLoaiGhe().equals("ST003")) {
							if (!dangChon) {
								gheDangChon.add(ghe);
								gheDangChon.add(pairGhe);
								btnGhe.setBackground(Color.GREEN);
								pairBtn.setBackground(Color.GREEN);
							} else {
								gheDangChon.remove(ghe);
								gheDangChon.remove(pairGhe);
								btnGhe.setBackground((Color) btnGhe.getClientProperty("originalColor"));
								pairBtn.setBackground((Color) pairBtn.getClientProperty("originalColor"));
							}
						}
					}
				} else {
					if (!dangChon) {
						gheDangChon.add(ghe);
						btnGhe.setBackground(Color.GREEN);
					} else {
						gheDangChon.remove(ghe);
						btnGhe.setBackground((Color) btnGhe.getClientProperty("originalColor"));
					}
				}
				// Sau khi chọn/bỏ chọn ghế, cập nhật thông tin vé ở cột trái
				capNhatThongTinVe(panelThongTinVe, currentLichChieu, gheDangChon);
			});
		}

		return pnGhe;
	}

	public JPanel hienThiThongTinVe(LichChieu l) {
		panelThongTinVe = new JPanel(); 
		panelThongTinVe.setLayout(new BoxLayout(panelThongTinVe, BoxLayout.Y_AXIS));
		panelThongTinVe= new GradientPanel(new Color(10, 10, 30), new Color(60, 30, 180));

		// Thông tin phim
		Phim p = PhimController.layPhimTheoMaPhim(l.getMaPhim());
		ImageIcon scaledIcon = new ImageIcon(p.getAnhPhim());
		JLabel lblPoster = new JLabel(
				new ImageIcon(scaledIcon.getImage().getScaledInstance(170, 230, Image.SCALE_SMOOTH)));

		JLabel lblTenPhim = new JLabel(p.getTenPhim());
		lblTenPhim.setFont(new Font("", Font.BOLD, 20));
		lblTenPhim.setForeground(Color.WHITE);

		JLabel lblThoiLuong = new JLabel(p.getThoiLuong() + " phút - " + p.getDoTuoiChoPhep());
		lblThoiLuong.setFont(new Font("", Font.PLAIN, 15));
		lblThoiLuong.setForeground(Color.WHITE);

		JPanel pnThongTinPhim = new JPanel();
		pnThongTinPhim.setOpaque(false);
		pnThongTinPhim.setLayout(new BoxLayout(pnThongTinPhim, BoxLayout.Y_AXIS));
		pnThongTinPhim.add(lblPoster);
		pnThongTinPhim.add(lblTenPhim);
		pnThongTinPhim.add(lblThoiLuong);

		// Phòng chiếu
		PhongChieu ph = PhongChieuController.layPhongChieuTheoMaPhongChieu(l.getMaPhong());
		DinhDang dd = PhongChieuController.layDinhDangTheoMaDinhDang(ph.getMaDinhDang());
		System.out.println(dd.getTenDinhDang());
		JLabel lblPhong = new JLabel("Phòng: " + ph + " - Định dạng: " + dd.getTenDinhDang());
		lblPhong.setForeground(Color.WHITE);
		JLabel lblSuatChieu = new JLabel("Suất: " + l.getKhungGioChieuString() + " - Ngày: " + l.getNgayChieu());
        lblSuatChieu.setForeground(Color.WHITE);
		lblPhong.setFont(new Font("", Font.PLAIN, 15));
		lblSuatChieu.setFont(new Font("", Font.PLAIN, 15));

		panelThongTinVe.add(pnThongTinPhim);
		panelThongTinVe.add(lblPhong);
		panelThongTinVe.add(lblSuatChieu);

		// Cập nhật thông tin vé ban đầu với ghế đang chọn hiện tại (nếu có)
		capNhatThongTinVe(panelThongTinVe, l, gheDangChon);

		return panelThongTinVe;
	}

	
	public JPanel pnThanhToanVaVoucher(LichChieu l) {
		JPanel pnPayment = new JPanel();
		pnPayment.setLayout(new BorderLayout());

		pnPayment.add(hienThiDanhSachVoucher(l), BorderLayout.CENTER);

		// Thêm các thành phần thanh toán khác vào đây (ví dụ: phương thức thanh toán,
		// tổng tiền cuối cùng...)
		JPanel paymentDetailsPanel = new JPanel();
		paymentDetailsPanel.setLayout(new FlowLayout()); // Hoặc một layout phù hợp hơn
		JLabel lblPaymentDetails = new JLabel("Thông tin thanh toán sẽ ở đây");
		paymentDetailsPanel.add(lblPaymentDetails);
		// pnPayment.add(paymentDetailsPanel, BorderLayout.CENTER);

		return pnPayment;
	}

	public JPanel hienThiDanhSachVoucher(LichChieu l) {
		JPanel pnPromo = new JPanel(new GridLayout(3, 4, 10, 10));
		pnPromo.setBackground(new Color(5, 0, 56));
		pnPromo.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.WHITE),
				"Khuyến mãi",
				0, 0, null, Color.WHITE));

		ButtonGroup voucherButtonGroup = new ButtonGroup(); // Tạo ButtonGroup nếu chưa có

		ArrayList<Voucher> dsVoucher = VoucherController.layDanhSachVoucher();
		for (Voucher vc : dsVoucher) {
			JPanel voucherItem = new JPanel();
			voucherItem.setLayout(new BoxLayout(voucherItem, BoxLayout.Y_AXIS));
			voucherItem.setBackground(Color.WHITE);
			voucherItem.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

			JRadioButton radio = new JRadioButton(vc.getTenVoucher());
			radio.setBackground(Color.WHITE);
			radio.putClientProperty("discount", vc.getPhanTramGiam()); // Gắn thuộc tính giảm giá

			// Gắn sự kiện khi chọn radio
			radio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					maVoucher = vc.getMaVoucher();
					voucherDangChon = vc.getTenVoucher();
					phanTramGiam = vc.getPhanTramGiam();

					capNhatThongTinVe(panelThongTinVe, l, gheDangChon);
				}
			});

			JLabel label = new JLabel(vc.getMoTa());

			voucherItem.add(radio);
			voucherItem.add(label);

			voucherButtonGroup.add(radio);
			pnPromo.add(voucherItem);
		}

		return pnPromo;
	}

	public void capNhatThongTinVe(JPanel panel, LichChieu l, Set<Ghe> gheDaChon) {
		// Xoá tất cả các component từ ghế trở đi
		while (panel.getComponentCount() > 3) {
			panel.remove(3);
		}

		int tongTien = 0;

		for (Ghe ghe : gheDaChon) {
			LoaiGhe lg = GheController.layLoaiGheTheoMa(ghe.getLoaiGhe());
			int gia = (int) lg.getGiaGhe(); // làm tròn nếu cần
			tongTien += gia;

			String tenLoai = switch (ghe.getLoaiGhe()) {
				case "ST002" -> "Ghế VIP";
				case "ST003" -> "Ghế đôi";
				case "ST004" -> "Ghế IMAX";
				default -> "Ghế thường";
			};

			JLabel lbl = new JLabel(ghe.getTenGhe() + " (" + tenLoai + ") - " + gia + "đ");
			lbl.setForeground(Color.decode("#efb146"));
			lbl.setFont(new Font("", Font.PLAIN, 14));
			panel.add(lbl);
		}

		float tienGiam = tongTien * phanTramGiam;
		tongTienThanhToan = Math.round(tongTien - tienGiam);

		if (voucherDangChon != null) {
			JLabel lblVoucher = new JLabel("Áp dụng voucher: " + voucherDangChon + " (-" + phanTramGiam + "%)");
			lblVoucher.setFont(new Font("", Font.ITALIC, 14));
			lblVoucher.setForeground(Color.decode("#C94D3D"));
			panel.add(lblVoucher);
		}

		JLabel lblTong = new JLabel("Tổng tiền: " + String.format("%,d", tongTienThanhToan) + "đ");
		lblTong.setFont(new Font("", Font.BOLD, 16));
		lblTong.setForeground(Color.RED);
		panel.add(lblTong);

		panel.revalidate();
		panel.repaint();
	}
}
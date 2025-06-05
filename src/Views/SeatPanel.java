package Views;

import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import Controllers.*;
import Models.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


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
		pnChonGhe.add(hienThiDanhSachGhe(l,panelThongTinVe));
		
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
	
	public JPanel hienThiDanhSachGhe(LichChieu l, JPanel panelThongTinVe) {
	    ArrayList<Ghe> dsGhe = GheController.layDanhSachGhe(l.getMaLichChieu());
	    JPanel pnGhe = new JPanel();
	    String maPhong = dsGhe.get(0).getMaPhong();

	    if (maPhong.equals("RM007")) {
	        pnGhe.setLayout(new GridLayout(14, 20, 1, 1));
	        pnGhe.setPreferredSize(new Dimension(1200, 700));
	    } else {
	        pnGhe.setLayout(new GridLayout(10, 14, 2, 2));
	        pnGhe.setPreferredSize(new Dimension(700, 500));
	    }

	    Map<Integer, JButton> indexToButton = new HashMap<>();
	    Map<Integer, Ghe> indexToGhe = new HashMap<>();
	    Set<Ghe> gheDangChon = new LinkedHashSet<>();

	    for (int i = 0; i < dsGhe.size(); i++) {
	        Ghe gh = dsGhe.get(i);
	        JButton btnGhe = new JButton(gh.getTenGhe());
	        btnGhe.setCursor(new Cursor(Cursor.HAND_CURSOR));

	        if (maPhong.equals("RM007")) {
	            btnGhe.setFont(new Font("Arial", Font.BOLD, 9));
	        }

	        if (gh.getLoaiGhe().equals("ST002")) {
	            btnGhe.setBackground(Color.ORANGE); // VIP
	        } else if (gh.getLoaiGhe().equals("ST003")) {
	            btnGhe.setBackground(Color.PINK);   // Ghế đôi
	        } else {
	            btnGhe.setBackground(null);         // Ghế thường
	        }

	        btnGhe.putClientProperty("originalColor", btnGhe.getBackground());

	        indexToButton.put(i, btnGhe);
	        indexToGhe.put(i, gh);
	        pnGhe.add(btnGhe);
	    }

	    for (int i = 0; i < dsGhe.size(); i++) {
	        final int index = i;
	        JButton btnGhe = indexToButton.get(index);
	        Ghe ghe = indexToGhe.get(index);

	        btnGhe.addActionListener(e -> {
	            boolean dangChon = gheDangChon.contains(ghe);

	            if (ghe.getLoaiGhe().equals("ST003")) {
	                int pairIndex = (index % 2 == 0) ? index + 1 : index - 1;

	                if (indexToButton.containsKey(pairIndex)) {
	                    JButton pairBtn = indexToButton.get(pairIndex);
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

	            capNhatThongTinVe(panelThongTinVe, l, gheDangChon);
	        });
	    }

	    return pnGhe;
	}
	
	JPanel panelThongTinVe;
	public JPanel HienThiThongTinVe(LichChieu l) {
	    panelThongTinVe = new JPanel();
	    panelThongTinVe.setLayout(new BoxLayout(panelThongTinVe, BoxLayout.Y_AXIS));
	    panelThongTinVe.putClientProperty("gheDaChon", new LinkedHashSet<Ghe>());

	    // Thông tin phim
	    Phim p = PhimController.layPhimTheoMaPhim(l.getMaPhim());
	    ImageIcon scaledIcon = new ImageIcon(p.getAnhPhim());
	    JLabel lblPoster = new JLabel(new ImageIcon(scaledIcon.getImage().getScaledInstance(170, 230, Image.SCALE_SMOOTH)));

	    JLabel lblTenPhim = new JLabel(p.getTenPhim());
	    lblTenPhim.setFont(new Font("", Font.BOLD, 20));

	    JLabel lblThoiLuong = new JLabel(p.getThoiLuong() + " phút - " + p.getDoTuoiChoPhep());
	    lblThoiLuong.setFont(new Font("", Font.PLAIN, 15));

	    JPanel pnThongTinPhim = new JPanel();
	    pnThongTinPhim.setLayout(new BoxLayout(pnThongTinPhim, BoxLayout.Y_AXIS));
	    pnThongTinPhim.add(lblPoster);
	    pnThongTinPhim.add(lblTenPhim);
	    pnThongTinPhim.add(lblThoiLuong);

	    // Phòng chiếu
	    PhongChieu ph = PhongChieuController.layPhongChieuTheoMaPhongChieu(l.getMaPhong());
	    JLabel lblPhong = new JLabel("Phòng: " + ph + " - Định dạng: " + ph.getMaDinhDang());
	    JLabel lblSuatChieu = new JLabel("Suất: " + l.getKhungGioChieuString() + " - Ngày: " + l.getNgayChieu());

	    lblPhong.setFont(new Font("", Font.PLAIN, 15));
	    lblSuatChieu.setFont(new Font("", Font.PLAIN, 15));

	    panelThongTinVe.add(pnThongTinPhim);
	    panelThongTinVe.add(lblPhong);
	    panelThongTinVe.add(lblSuatChieu);

	    return panelThongTinVe;
	}

	public void capNhatThongTinVe(JPanel panel, LichChieu l, Set<Ghe> gheDaChon) {
	    // Xoá tất cả các component từ ghế trở đi
	    while (panel.getComponentCount() > 3) {
	        panel.remove(3);
	    }

	    Map<String, List<Ghe>> gheTheoLoai = new LinkedHashMap<>();
	    for (Ghe g : gheDaChon) {
	        gheTheoLoai.computeIfAbsent(g.getLoaiGhe(), k -> new ArrayList<>()).add(g);
	    }

	    int tongTien = 0;
	    for (Map.Entry<String, List<Ghe>> entry : gheTheoLoai.entrySet()) {
	        String maLoai = entry.getKey();
	        List<Ghe> gheList = entry.getValue();

	        LoaiGhe lg = GheController.layLoaiGheTheoMa(maLoai);
	        double gia = lg.getGiaGhe();
	        int soLuong = gheList.size();
	        double tien = gia * soLuong;
	        tongTien += tien;

	        String tenLoai = switch (maLoai) {
	            case "ST002" -> "Ghế VIP";
	            case "ST003" -> "Ghế đôi";
	            default -> "Ghế thường";
	        };

	        String tenGhe = gheList.stream().map(Ghe::getTenGhe).collect(Collectors.joining(", "));
	        JLabel lbl = new JLabel(tenLoai + ": " + tenGhe + "x" + soLuong+ "    " + tien +"đ");
	        lbl.setFont(new Font("", Font.PLAIN, 14));
	        panel.add(lbl);
	    }

	    JLabel lblTong = new JLabel("Tổng tiền: " + tongTien + "đ");
	    lblTong.setFont(new Font("", Font.BOLD, 16));
	    lblTong.setForeground(Color.RED);
	    panel.add(lblTong);

	    panel.revalidate();
	    panel.repaint();
	}
}

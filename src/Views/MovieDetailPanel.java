package Views;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.border.TitledBorder;

import Models.Actor;
import Models.Phim;
import Utilzs.GradientPanel;
import java.util.List;
import Controllers.*;


public class MovieDetailPanel extends JPanel {

	public MovieDetailPanel(Phim p) {
		addControls(p);
		addEvents();
	}

	public void addControls(Phim p) {
		this.setLayout(new BorderLayout());

		JPanel pnMain = new GradientPanel(new Color(10, 10, 30), new Color(60, 30, 180));
		pnMain.setLayout(new BorderLayout(20, 20));
		pnMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // padding

		// ======= TOP: Poster và thông tin cơ bản =======
		JPanel pnTop = new JPanel(new BorderLayout(20, 0));
		pnTop.setOpaque(false);

		// Poster phim
		ImageIcon img = new ImageIcon(p.getAnhPhim());
		Image scaledImage = img.getImage().getScaledInstance(250, 370, Image.SCALE_SMOOTH);
		JLabel lblAnhPhim = new JLabel(new ImageIcon(scaledImage));
		lblAnhPhim.setPreferredSize(new Dimension(250, 370));

		// Thông tin phim
		JPanel pnInfo = new JPanel();
		pnInfo.setLayout(new GridLayout(5, 1, 10, 10));
		TitledBorder borderInfo = BorderFactory.createTitledBorder("Thông tin phim");
		borderInfo.setTitleColor(Color.WHITE);
		pnInfo.setBorder(borderInfo);
		pnInfo.setOpaque(false);

		JLabel lblTenPhim = new JLabel("Tên phim: " + p.getTenPhim());
		lblTenPhim.setFont(new Font("Arial", Font.BOLD, 22));
		lblTenPhim.setForeground(Color.WHITE);

		JLabel lblNgayPhatHanh = new JLabel("Khởi chiếu: " + p.getNgayPhatHanh());
		JLabel lblThoiLuong = new JLabel("Thời lượng: " + p.getThoiLuong() + " phút");
		JLabel lblDaoDien = new JLabel("Đạo diễn: " + p.getDaoDien());
		JLabel lblDoTuoiChoPhep = new JLabel("Độ tuổi: " + p.getDoTuoiChoPhep());

		Font infoFont = new Font("Arial", Font.PLAIN, 18);
		for (JLabel lbl : new JLabel[] { lblNgayPhatHanh, lblThoiLuong, lblDaoDien, lblDoTuoiChoPhep }) {
			lbl.setFont(infoFont);
			lbl.setForeground(Color.WHITE);
		}

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
		pnCenter.setOpaque(false);

		JTextArea txtNoiDung = new JTextArea(p.getNoiDung());
		txtNoiDung.setFont(new Font("Arial", Font.PLAIN, 18));
		txtNoiDung.setLineWrap(true);
		txtNoiDung.setWrapStyleWord(true);
		txtNoiDung.setEditable(false);
		txtNoiDung.setOpaque(false);
		txtNoiDung.setForeground(Color.WHITE); // màu chữ

		TitledBorder borderNoiDung = BorderFactory.createTitledBorder("Nội dung phim");
		borderNoiDung.setTitleColor(Color.WHITE);

		JScrollPane scrollNoiDung = new JScrollPane(txtNoiDung);
		scrollNoiDung.setBorder(borderNoiDung);
		scrollNoiDung.setPreferredSize(new Dimension(1150, 150));
		scrollNoiDung.setOpaque(false);
		scrollNoiDung.getViewport().setOpaque(false);
		scrollNoiDung.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		// Diễn viên
		JPanel pnDienVien = new JPanel();
		pnDienVien.setLayout(new FlowLayout());
		pnDienVien.setOpaque(false);
		List<Actor> dsDienVien = ActorController.findActorsByMovieId(p.getMaPhim());
		for(Actor actor : dsDienVien) {
			ImageIcon img2 = new ImageIcon(actor.getActor_image());
			Image scaledImage2 = img2.getImage().getScaledInstance(250, 370, Image.SCALE_SMOOTH);
			JLabel lblAnhDienVien= new JLabel(new ImageIcon(scaledImage2));
			lblAnhDienVien.setPreferredSize(new Dimension(100, 150));
			pnDienVien.add(lblAnhDienVien);
			System.out.println("Path ảnh diễn viên: " + actor.getActor_image());
		}
		

		TitledBorder borderDienVien = BorderFactory.createTitledBorder("Diễn viên");
		borderDienVien.setTitleColor(Color.WHITE);

		JScrollPane scrollDienVien = new JScrollPane(pnDienVien);
		scrollDienVien.setBorder(borderDienVien);
		scrollDienVien.setOpaque(false);
		scrollDienVien.getViewport().setOpaque(false);
		scrollDienVien.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		pnCenter.add(scrollNoiDung);
		pnCenter.add(scrollDienVien);



		// ======= Thêm vào giao diện chính =======
		pnMain.add(pnTop, BorderLayout.NORTH);
		pnMain.add(pnCenter, BorderLayout.CENTER);

		JScrollPane scp = new JScrollPane(pnMain, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scp.setOpaque(false);
		scp.getViewport().setOpaque(false);

		this.add(scp, BorderLayout.CENTER);
	}

	public void addEvents() {}

}

package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import Models.LichChieu;

public class PaymentPanel extends JPanel {
	JPanel pnRight;

	public PaymentPanel(LichChieu l) {
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
		
		//JPanel thongTinVePanel = SeatPanel.HienThiThongTinVe(l);
		//pnLeft.add(thongTinVePanel, BorderLayout.CENTER);
		
		
		//Bên phải chọn ghế
		pnRight = new JPanel();
		pnRight.setLayout(new BorderLayout()); 		
		//pnRight.add(pnChonGhe(l), BorderLayout.CENTER);

		
		pnMain.add(pnLeft, BorderLayout.EAST);
		pnMain.add(pnRight, BorderLayout.CENTER);
		

		this.setLayout(new BorderLayout());
		this.add(pnMain, BorderLayout.CENTER);
	}
}

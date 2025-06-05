package Views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import Models.LichChieu;

public class BookingControlPanel extends JPanel {
	JPanel mainContentPanel;
	CardLayout cardLayout;
	JButton btnPrev, btnNext;
	
	public BookingControlPanel(LichChieu l) {
		addControls(l);
		addEvents();
	}
	
	public void addControls(LichChieu l) {
		cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout);
        
        mainContentPanel.add(new SeatPanel(l), "1");
        mainContentPanel.add(new PaymentPanel(l), "2"); 
//        mainContentPanel.add(new TicketBillPanel(), "3");
        
        
        
		//bên dưới chứa button điều hướng
        JPanel pnButton = new JPanel();
		pnButton.setLayout(new BorderLayout());
		pnButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		btnPrev = new JButton("<<< Quay lại");
		btnNext = new JButton(">>> Tiếp theo");
		//btnPrev.setVisible(false);
		pnButton.add(btnPrev, BorderLayout.WEST);
		pnButton.add(btnNext, BorderLayout.EAST);
		
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());
		pnMain.add(mainContentPanel, BorderLayout.CENTER);
		pnMain.add(pnButton,BorderLayout.SOUTH);
		
		this.setLayout(new BorderLayout());
		this.add(pnMain, BorderLayout.CENTER);
	}
	
	int[] currentPage = {1};
	public void addEvents() {
		btnPrev.addActionListener(e -> {
		    if (currentPage[0] > 1) {
		        currentPage[0]--;
		        cardLayout.show(mainContentPanel, String.valueOf(currentPage[0]));
		    }
		});

		btnNext.addActionListener(e -> {
		    if (currentPage[0] < 4) {
		        currentPage[0]++;
		        cardLayout.show(mainContentPanel, String.valueOf(currentPage[0]));
		    }
		});
	}
	
}

package Views;

import javax.swing.*;


import java.awt.*;
import java.awt.event.*;

public class ClientFrame extends JFrame {
	JMenuBar menuBar;
	JMenu mnuHomePage, mnuShowTime, mnuPromotion, mnuUserInfo;
	JPanel mainContentPanel;
	CardLayout cardLayout;
	
	public ClientFrame(String title) {
        super(title);
        addControls();
        addEvents();
        showWindow();
    }

    private void addControls() {
        // Menu
        menuBar = new JMenuBar();
        mnuHomePage = new JMenu("Trang chủ");
        mnuShowTime = new JMenu("Lịch chiếu");
        mnuPromotion = new JMenu("Khuyến mãi");
        mnuUserInfo = new JMenu("Thông tin cá nhân");
        
        menuBar.add(mnuHomePage);
        menuBar.add(mnuShowTime);
        menuBar.add(mnuPromotion);
        menuBar.add(mnuUserInfo);
        
        setJMenuBar(menuBar);

        // CardLayout
        cardLayout = new CardLayout();
        mainContentPanel = new JPanel(cardLayout);

        mainContentPanel.add(new HomePagePanel(cardLayout, mainContentPanel), "TRANG_CHU");
        mainContentPanel.add(new ShowTimePanel(cardLayout, mainContentPanel), "LICH_CHIEU");
         mainContentPanel.add(new PromotionPanel(cardLayout, mainContentPanel), "KHUYEN_MAI");
//        mainContentPanel.add(new GioiThieuPanel(), "GIOI_THIEU");
        mainContentPanel.add(new UserInfoPanel(), "THONG_TIN_CA_NHAN");

        getContentPane().add(mainContentPanel, BorderLayout.CENTER);
    }

    private void addEvents() {
    	mnuHomePage.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                switchTo("TRANG_CHU");
            }
        });
        mnuShowTime.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                switchTo("LICH_CHIEU");
            }
        });

        mnuPromotion.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                switchTo("KHUYEN_MAI");
            }
        });

        mnuUserInfo.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                switchTo("THONG_TIN_CA_NHAN");
            }
        });
    }

    private void switchTo(String panelName) {
        cardLayout.show(mainContentPanel, panelName);
    }

    private void showWindow() {
    	this.setSize(1300, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        new ClientFrame("Trang Chủ Rạp Chiếu Phim");
    }

}

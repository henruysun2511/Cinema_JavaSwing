package Views;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.TitledBorder;

import Models.Phim;
import Controllers.PhimController;
import Utilzs.GradientPanel;

public class HomePagePanel extends JPanel {
    private JButton btnPrev, btnNext;
    private JLabel lblImage;
    private String[] images = {
        "F:/Cinema_JavaSwing/images/carousel/minecraft-2048_1743651882260.jpg",
            "F:/Cinema_JavaSwing/images/carousel/0018450.jpg"
            
    };
    JPanel pnPhim;
    CardLayout cardLayout;
    JPanel mainContentPanel;
    private int currentImageIndex = 0;

    public HomePagePanel(CardLayout cardLayout, JPanel mainContentPanel) {
        this.cardLayout = cardLayout;
        this.mainContentPanel = mainContentPanel;
        addControls();
        addEvents();
    }

    public void addControls() {
        // Tạo các nút điều khiển
        btnPrev = new JButton("<");
        btnNext = new JButton(">");

        // Tạo hình ảnh ban đầu
        lblImage = new JLabel();
        updateImage();

        // Tạo panel chứa nút và ảnh
        JPanel pnTop = new JPanel();
        pnTop.setLayout(new FlowLayout());
        pnTop.add(btnPrev);
        pnTop.add(lblImage);
        pnTop.add(btnNext);

        JPanel pnCenter = new JPanel();
        pnCenter.setLayout(new FlowLayout());

        // Bên trái của center chứa phim đang chiếu
        JPanel pnLeftofCenter = new JPanel();
        pnLeftofCenter.setLayout(new BorderLayout());
        TitledBorder borderLeft = new TitledBorder(BorderFactory.createLineBorder(Color.white), "Phim đang chiếu");
        borderLeft.setTitleColor(Color.white); 
        borderLeft.setTitleFont(new Font("Arial", Font.BOLD, 16));
        pnLeftofCenter.setBorder(borderLeft); // Thêm viền cho phim đang chiếu
        pnLeftofCenter.setPreferredSize(new Dimension(900, 1000));
        pnLeftofCenter.add(hienThiDanhSachPhim(), BorderLayout.CENTER); // hiển thị danh sách phim vào bên trái của
                                                                        // center

        // Bên phải của center chứa khuyến mãi
        JPanel pnRightofCenter = new JPanel();
        TitledBorder borderRight = new TitledBorder(BorderFactory.createLineBorder(Color.white), "Khuyến mãi");
        borderRight.setTitleColor(Color.white); 
        borderRight.setTitleFont(new Font("Arial", Font.BOLD, 16));
        pnRightofCenter.setBorder(borderRight); // Thêm viền khuyến mãi
        pnRightofCenter.setPreferredSize(new Dimension(200, 1000));

        pnCenter.add(pnLeftofCenter);
        pnCenter.add(pnRightofCenter);

        //
        JPanel pnCenter2 = new JPanel();
        pnCenter2.setLayout(new FlowLayout());
        JPanel pnLeftofCenter2 = new JPanel();
        pnLeftofCenter2.setLayout(new BorderLayout());
        TitledBorder borderLeft2 = new TitledBorder(BorderFactory.createLineBorder(Color.white), "Phim sắp chiếu");
        borderLeft2.setTitleColor(Color.white); 
        borderLeft2.setTitleFont(new Font("Arial", Font.BOLD, 16));
        pnLeftofCenter2.setBorder(borderLeft2); // Thêm viền cho phim đang chiếu
        pnLeftofCenter2.setPreferredSize(new Dimension(900, 300));

        JPanel pnRightofCenter2 = new JPanel();
        TitledBorder borderRight2 = new TitledBorder(BorderFactory.createLineBorder(Color.white), "Magic Box");
        borderRight.setTitleColor(Color.white); 
        borderRight.setTitleFont(new Font("Arial", Font.BOLD, 16));
        pnRightofCenter2.setBorder(borderRight2); // Thêm viền khuyến mãi
        pnRightofCenter2.setPreferredSize(new Dimension(200, 300));

        pnCenter2.add(pnLeftofCenter2);
        pnCenter2.add(pnRightofCenter2);

        // Main chứa tất pn con
        JPanel pnMain = new GradientPanel(new Color(10, 10, 30), new Color(60, 30, 180));
        pnMain.setLayout(new BorderLayout());
        pnMain.add(pnTop, BorderLayout.NORTH);
        pnMain.add(pnCenter, BorderLayout.CENTER);
        pnMain.add(pnCenter2, BorderLayout.SOUTH);

        JScrollPane scp = new JScrollPane(pnMain, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        this.setLayout(new BorderLayout());
        this.add(scp, BorderLayout.CENTER);

        pnTop.setOpaque(false);
        pnCenter.setOpaque(false);
        pnLeftofCenter.setOpaque(false);
        pnRightofCenter.setOpaque(false);
        pnCenter2.setOpaque(false);
        pnLeftofCenter2.setOpaque(false);
        pnRightofCenter2.setOpaque(false);
    }

    public void addEvents() {
        btnPrev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showPrevImage();
            }
        });

        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showNextImage();
            }
        });
    }

    public void updateImage() {
        ImageIcon icon = new ImageIcon(images[currentImageIndex]);
        lblImage.setIcon(icon);
        lblImage.setPreferredSize(new Dimension(1100, 570));
    }

    public void showPrevImage() {
        if (currentImageIndex > 0) {
            currentImageIndex--;
        } else {
            currentImageIndex = images.length - 1; // Chuyển tới ảnh cuối
        }
        updateImage();
    }

    public void showNextImage() {
        if (currentImageIndex < images.length - 1) {
            currentImageIndex++;
        } else {
            currentImageIndex = 0; // Chuyển tới ảnh đầu tiên
        }
        updateImage();
    }

    // Hiển thị danh sách phim đang chiếu
    ArrayList<Phim> dsPhim = PhimController.layDanhSachPhim();
    JLabel lblPoster;

    public JPanel hienThiDanhSachPhim() {
        pnPhim = new JPanel();
        pnPhim.setLayout(new GridLayout(0, 3, 10, 10));
        pnPhim.setOpaque(false);

        for (Phim p : dsPhim) {
            JPanel pnPhimItem = new JPanel();
            pnPhimItem.setLayout(new BoxLayout(pnPhimItem, BoxLayout.Y_AXIS));
            pnPhimItem.setOpaque(false);

            // Poster
            ImageIcon icon = new ImageIcon(p.getAnhPhim());
            lblPoster = new JLabel();
            lblPoster.setIcon(icon);
            lblPoster.setPreferredSize(new Dimension(185, 273));
            lblPoster.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Tên phim
            JLabel lblTenPhim = new JLabel(p.getTenPhim());
            lblTenPhim.setForeground(Color.white);
            lblTenPhim.setFont(new Font("Arial", Font.BOLD, 16));

            pnPhimItem.add(lblPoster);
            pnPhimItem.add(lblTenPhim);

            pnPhim.add(pnPhimItem);

            // Thêm sự kiện khi ấn vô poster sẽ hiện frame chi tiết phim của phim đấy
            lblPoster.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    // new frmChiTietPhim(p);
                    MovieDetailPanel pnlChiTiet = new MovieDetailPanel(p);
                    mainContentPanel.add(pnlChiTiet, "CHI_TIET");
                    cardLayout.show(mainContentPanel, "CHI_TIET");
                }

                public void mouseEntered(MouseEvent e) {

                }
            });

        }
        return pnPhim;
    }
}
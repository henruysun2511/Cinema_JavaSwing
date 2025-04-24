package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;

public class frmTrangChu extends JFrame {
    private JMenuBar menuBar;
    private JMenu mnuPhim, mnuLichChieu, mnuGioiThieu, mnuGiaVe;
    private JButton btnPrev, btnNext;
    private JLabel lblImage;
    private String[] images = {
        "F:/Cinema_JavaSwing/images/carousel/0018450.jpg", 
        "F:/Cinema_JavaSwing/images/carousel/minecraft-2048_1743651882260.jpg",
    };
    private int currentImageIndex = 0;

    public frmTrangChu(String tieude) {
        super(tieude);
        addControls();
        showWindow();
        addEvents();
    }

    public void showWindow() {
        this.setSize(1200, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void addControls() {
        // Tạo menu bar
        menuBar = new JMenuBar();
        mnuPhim = new JMenu("Phim");
        mnuLichChieu = new JMenu("Lịch Chiếu");
        mnuGioiThieu = new JMenu("Giới Thiệu");
        mnuGiaVe = new JMenu("Giá Vé");

        menuBar.add(mnuPhim);
        menuBar.add(mnuLichChieu);
        menuBar.add(mnuGioiThieu);
        menuBar.add(mnuGiaVe);
        setJMenuBar(menuBar);

        // Tạo các nút điều khiển
        btnPrev = new JButton("<");
        btnNext = new JButton(">");

        // Tạo hình ảnh ban đầu
        lblImage = new JLabel();
        updateImage();

        // Tạo panel chứa nút và ảnh
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new FlowLayout());
        panelTop.add(btnPrev);
        panelTop.add(lblImage);
        panelTop.add(btnNext);

        // Thêm panel vào frame
        Container con = getContentPane();
        con.setLayout(new BorderLayout());
        con.add(panelTop, BorderLayout.NORTH);

       
        
    }
    public void addEvents(){
        //Điều hướng sang các form khác trong menu
        mnuLichChieu.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frmLichChieu LichChieu = new frmLichChieu("Lịch chiếu");
                LichChieu.setVisible(true);
            }
        
            public void mouseEntered(MouseEvent e) {
                
            }
        });

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
        lblImage.setPreferredSize(new Dimension(1000, 600)); 
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


    public static void main(String[] args) {
        new frmTrangChu("Chào mừng đến với rạp chiếu phim BingeBox Cinema");
    }
}
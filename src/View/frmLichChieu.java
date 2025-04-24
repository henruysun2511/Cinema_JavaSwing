package View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;

public class frmLichChieu extends JFrame {
    public frmLichChieu(String tieude) {
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

    }

    public void addEvents() {
       
    }

}

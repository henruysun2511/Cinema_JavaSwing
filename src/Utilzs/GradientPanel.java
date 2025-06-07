package Utilzs;
import java.awt.*;
import javax.swing.*;

public class GradientPanel extends JPanel {
    private final Color colorStart;
    private final Color colorEnd;

    public GradientPanel(Color colorStart, Color colorEnd) {
        this.colorStart = colorStart;
        this.colorEnd = colorEnd;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(
            0, 0, colorStart,
            getWidth(), getHeight(), colorEnd
        );
        g2.setPaint(gp);
        g2.fillRect(0, 0, getWidth(), getHeight());
    }
}

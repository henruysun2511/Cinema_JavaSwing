package Statics;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.axis.NumberAxis;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ThongKePanel extends JPanel {

    public ThongKePanel() {
        setLayout(new BorderLayout());

        List<ThongKePhim> ds = ThongKeDao.layDanhSachThongKe();
        List<ThongKeNgay> dsTheoNgayChieu = ThongKeDao.layThongKeTheoNgayChieu();
        List<ThongKeLoaiGhe> dsGhe = ThongKeDao.laySoVeTheoLoaiGhe();

    

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Doanh thu theo phim", taoBieuDoDoanhThu(ds));
        tabs.addTab("Số vé bán ra", taoBieuDoSoVe(ds));
        tabs.addTab("Doanh thu theo ngày chiếu", taoBieuDoDoanhThuTheoNgay(dsTheoNgayChieu));
        tabs.addTab("Số vé theo loại ghế", taoBieuDoTiLeVeTheoLoaiGhe(dsGhe));

        add(tabs, BorderLayout.CENTER);
    }

    private ChartPanel taoBieuDoDoanhThu(List<ThongKePhim> ds) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (ThongKePhim tk : ds) {
            dataset.addValue(tk.getTongDoanhThu(), "Doanh thu", tk.getTenPhim());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Doanh thu theo phim",
                "Tên phim",
                "Doanh thu (VNĐ)",
                dataset
        );

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        chart.setBackgroundPaint(new Color(245, 245, 245));

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(79, 129, 189)); // Xanh dương nhạt

        return new ChartPanel(chart);
    }

    private ChartPanel taoBieuDoSoVe(List<ThongKePhim> ds) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (ThongKePhim tk : ds) {
            dataset.addValue(tk.getSoLuongVe(), "Số vé", tk.getTenPhim());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Số vé bán theo phim",
                "Tên phim",
                "Số vé",
                dataset
        );

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        chart.setBackgroundPaint(new Color(245, 245, 245));

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(252, 180, 65)); 

        return new ChartPanel(chart);
    }

    private ChartPanel taoBieuDoDoanhThuTheoNgay(List<ThongKeNgay> ds) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (ThongKeNgay tk : ds) {
            dataset.addValue(tk.getTongDoanhThu(), "Doanh thu", tk.getNgayGiaoDich());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Doanh thu theo ngày chiếu",
                "Ngày chiếu",
                "Doanh thu (VNĐ)",
                dataset,
                PlotOrientation.HORIZONTAL,
                true, true, false
        );

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        chart.setBackgroundPaint(new Color(245, 245, 245));

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(155, 187, 89)); // Xanh lá

        return new ChartPanel(chart);
    }
    
    public ChartPanel taoBieuDoTiLeVeTheoLoaiGhe(List<ThongKeLoaiGhe> ds) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        for (ThongKeLoaiGhe tk : ds) {
            dataset.setValue(tk.getLoaiGhe(), tk.getSoLuongVe());
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Tỷ lệ vé bán theo loại ghế",
                dataset,
                true,  // legend
                true,  // tooltips
                false  // URLs
        );

        chart.getPlot().setBackgroundPaint(Color.WHITE);

        return new ChartPanel(chart);
    }


    // Main test
    public static void main(String[] args) {
        JFrame frame = new JFrame("Thống kê phim");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new ThongKePanel());
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

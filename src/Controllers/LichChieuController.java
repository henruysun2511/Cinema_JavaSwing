package Controllers;

import java.util.ArrayList;
import java.sql.*;
import java.sql.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Daos.*;
import Models.*;
import Views.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import java.util.List; 
import javax.swing.JOptionPane; 

public class LichChieuController {

    public static ArrayList<Date> layDanhSachNgayChieu() {
        return LichChieuDao.layDanhSachNgayChieu();
    }

    public static ArrayList<Phim> layDanhSachPhimTheoNgayChieu(Date ngayChieu) {
        return LichChieuDao.layDanhSachPhimTheoNgay(ngayChieu);
    }

    public static ArrayList<LichChieu> layDanhSachLichChieuTheoPhimVaNgay(String maPhim, Date ngayChieu) {
        return LichChieuDao.layDanhSachLichChieuTheoPhimVaNgay(maPhim, ngayChieu);
    }

    public static ArrayList<LichChieu> layDanhSachLichChieuTheoPhongVaNgay(String maPhong, Date ngay) {
        return LichChieuDao.layDanhSachLichChieuTheoPhongVaNgay(maPhong, ngay);
    }

    public static boolean themLichChieu(LichChieu lichChieu) {
        return LichChieuDao.themLichChieu(lichChieu);
    }

    public static boolean suaLichChieu(LichChieu lichChieu) {
        return LichChieuDao.suaLichChieu(lichChieu);
    }

    public static boolean xoaLichChieu(String maLichChieu) {
        return LichChieuDao.xoaNgayChieu(maLichChieu);
    }

//    private void attachEvents() {
//        view.setLuuListener(e -> themLichChieu());
//        view.setXoaListener(e -> xoaLichChieu());
//        view.setSuaListener(e -> suaLichChieu());
//    }
//
//    private void capNhatBangLichChieu() {
//        JTable table = view.getTable();
//        String maPhong = view.getMaPhong();
//        Date ngay = view.getNgay();
//        doDuLieuLichChieuVaoBang(table, layDanhSachLichChieuTheoPhongVaNgay(maPhong, ngay));
//    }
//
//    public void themLichChieu() {
//        LichChieu l = layThongTinTuView();
//        if (themLichChieu(l)) {
//            JOptionPane.showMessageDialog(null, "Thêm thành công!");
//            capNhatBangLichChieu();
//        } else {
//            JOptionPane.showMessageDialog(null, "Thêm thất bại!");
//        }
//    }
//
//    public void suaLichChieu() {
//        LichChieu l = layThongTinTuView();
//        if (suaLichChieu(l)) {
//            JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
//            capNhatBangLichChieu();
//        } else {
//            JOptionPane.showMessageDialog(null, "Cập nhật thất bại!");
//        }
//    }
//
//    public void xoaLichChieu() {
//        int row = view.getTable().getSelectedRow();
//        if (row == -1) {
//            JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng cần xóa.");
//            return;
//        }
//
//        String maLichChieu = view.getTable().getValueAt(row, 0).toString();
//        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
//        if (confirm == JOptionPane.YES_OPTION && xoaLichChieu(maLichChieu)) {
//            JOptionPane.showMessageDialog(null, "Xóa thành công!");
//            capNhatBangLichChieu();
//        } else {
//            JOptionPane.showMessageDialog(null, "Xóa thất bại!");
//        }
//    }
//
//    private LichChieu layThongTinTuView() {
//        return new LichChieu(
//            view.getMaSuatChieu(),
//            view.getSuatChieu(),
//            view.getMaPhim(),
//            view.getMaPhong(),
//            view.getNgay()
//        );
//    }

    public static void doDuLieuLichChieuVaoBang(JTable table, ArrayList<LichChieu> dsLichChieu) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (LichChieu l : dsLichChieu) {
            model.addRow(new Object[]{
                l.getMaLichChieu(),
                l.getKhungGioChieuString(),
                l.getMaPhim(),
                l.getMaPhong(),
                l.getNgayChieu()
            });
        }
    }
}
package Controllers;

import java.util.ArrayList;
import java.sql.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Daos.*;
import Models.*;


public class LichChieuController {
	public static ArrayList<LichChieu> layDanhSachLichChieu(){
		return LichChieuDao.layDanhSachLichChieu();
	}
    public static LichChieu layLichChieuTheoMaLichChieu(String maLichChieu){
        return LichChieuDao.layLichChieuTheoMaLichChieu(maLichChieu);
    }

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
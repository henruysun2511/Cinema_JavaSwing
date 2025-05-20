package Controllers;

import java.util.ArrayList;
import java.sql.*;
import java.sql.Date;
import Daos.*;
import Models.*;

public class LichChieuController {
	public static ArrayList<Date> layDanhSachNgayChieu(){
		return LichChieuDao.layDanhSachNgayChieu();
	}
	public static ArrayList<Phim> layDanhSachPhimTheoNgayChieu(Date ngayChieu){
		return LichChieuDao.layDanhSachPhimTheoNgay(ngayChieu);
	}
	public static ArrayList<LichChieu> layDanhSachLichChieuTheoPhimVaNgay(String maPhim, Date ngayChieu){
		return LichChieuDao.layDanhSachLichChieuTheoPhimVaNgay(maPhim, ngayChieu);
	}

}

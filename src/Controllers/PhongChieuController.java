package Controllers;

import java.util.ArrayList;
import Daos.*;
import Models.*;

public class PhongChieuController {
	public static ArrayList<PhongChieu> layDanhSachPhongChieu() {
		return PhongChieuDao.layDanhSachPhongChieu();
	}

	public static PhongChieu layPhongChieuTheoMaPhongChieu(String maPhong) {
		return PhongChieuDao.layPhongChieuTheoMaPhong(maPhong);
	}

	public static String layMaPhongTheoTenPhong(int maPhong) {
		return PhongChieuDao.layMaPhongTheoTenPhong(maPhong);
	}

	public static DinhDang layDinhDangTheoMaDinhDang(String maDinhDang) {
		return DinhDangDao.layDinhDangTheoMaDinhDang(maDinhDang);
	}

}

package Controllers;
import Daos.PhimDao;
import Models.Phim;
import java.util.ArrayList;

public class PhimController {
	public static ArrayList<Phim> layDanhSachPhimDangChieu(){
		return PhimDao.layPhimDangChieu();
	}
	
	public static ArrayList<Phim> layDanhSachPhimSapChieu(){
		return PhimDao.layPhimSapChieu();
	}
	
	public static Phim layPhimTheoMaPhim(String maPhim) {
		return PhimDao.layPhimTheoMaPhim(maPhim);
	}

}

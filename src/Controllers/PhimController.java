package Controllers;
import Daos.PhimDao;
import Models.Phim;
import java.util.ArrayList;

public class PhimController {
	public static ArrayList<Phim> layDanhSachPhim(){
		return PhimDao.layPhimDangChieu();
	}
	
	public static Phim layPhimTheoMaPhim(String maPhim) {
		return PhimDao.layPhimTheoMaPhim(maPhim);
	}

}

package Controllers;
import java.util.ArrayList;

import Daos.*;
import Models.*;

public class GheController {
	public static ArrayList<Ghe> layDanhSachGhe(String maSuatChieu){
		return GheDao.layDanhSachGheTheoMaSuatChieu(maSuatChieu);
	}

}

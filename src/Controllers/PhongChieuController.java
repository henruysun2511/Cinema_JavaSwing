package Controllers;

import java.util.ArrayList;
import java.sql.*;
import java.sql.Date;
import Daos.*;
import Models.*;

public class PhongChieuController {
	public static ArrayList<PhongChieu> layDanhSachPhongChieu(){
		return PhongChieuDao.layDanhSachPhongChieuu();
	}

}

package Controllers;

import Daos.VeDao;
import Models.HoaDonVe;
import Models.Ve;
import java.util.ArrayList;

public class VeController {
    public static ArrayList<Ve> layDanhSachVe() {
        return VeDao.layDanhSachVe();
    }

    public static ArrayList<Ve> layDanhSachVeTheoMaNguoiDung(String maNguoiDung) {
        return VeDao.layDanhSachVeTheoMaNguoiDung(maNguoiDung);
    }

    public static ArrayList<Ve> layDanhSachVeTheoMaHoaDon(String maHoaDon) {
        return VeDao.layDanhSachVeTheoMaHoaDon(maHoaDon);
    }
    
    public static Ve layVeTheoMaVe(String maVe) {
    	return VeDao.layVeTheoMaVe(maVe);
    }

    public static boolean themVe(Ve ve) {
        return VeDao.themVe(ve);
    }
    

    public static boolean themHoaDonVe(HoaDonVe hoaDonVe) {
        return VeDao.themHoaDonVe(hoaDonVe);
    }

    public static HoaDonVe layHoaDonVeTheoMaHoaDonVe(String maHoaDonVe) {
        return VeDao.layHoaDonVeTheoMaHoaDonVe(maHoaDonVe);
    }
}

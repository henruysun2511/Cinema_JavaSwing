package Controllers;

import java.util.ArrayList;
import java.util.Set;

import Daos.*;
import Models.*;

public class GheController {
    public static ArrayList<Ghe> layDanhSachGhe(String maSuatChieu) {
        return GheDao.layDanhSachGheTheoMaSuatChieu(maSuatChieu);
    }
    
    public static Set<String> layDanhSachGheDaDat(String maSuatChieu){
    	return GheDao.layDanhSachGheDaDat(maSuatChieu);
    }

    public static Ghe layGheTheoMaGhe(String maGhe) {
        return GheDao.layGheTheoMaGhe(maGhe);
    }

    public static LoaiGhe layLoaiGheTheoMa(String maLoaiGhe) {
        return LoaiGheDao.layLoaiGheTheoMa(maLoaiGhe);
    }

    public static boolean datGhe(Set<Ghe> danhSachGheDaChon, String maLichChieu) {
        return GheDao.themTinhTrangGhe(danhSachGheDaChon, maLichChieu, 1);
    }

}

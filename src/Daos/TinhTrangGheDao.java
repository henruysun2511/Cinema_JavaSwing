package Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ConnectToDB.connectToQuanLyRapChieuPhimDB;
import Models.TinhTrangGhe;

public class TinhTrangGheDao {
    public static ArrayList<TinhTrangGhe> layDanhSachTinhTrangGhe() {
        ArrayList<TinhTrangGhe> dsTinhTrangGhe = new ArrayList<>();
        try {
            Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
            String sql = "SELECT seat_id, seat_status FROM tblSeatStatus";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TinhTrangGhe ttg = new TinhTrangGhe();
                ttg.setMaTinhTrangGhe(rs.getString("maTinhTrangGhe"));
                ttg.setMoTa(rs.getString("moTa"));
                dsTinhTrangGhe.add(ttg);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsTinhTrangGhe;
    }

    public static TinhTrangGhe layTinhTrangGheTheoMa(String ma) {
        TinhTrangGhe ttg = null;
        try {
            Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
            String sql = "SELECT seat_id, seat_status FROM tblSeatStatus WHERE seat_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ma);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ttg = new TinhTrangGhe();
                ttg.setMaTinhTrangGhe(rs.getString("maTinhTrangGhe"));
                ttg.setMoTa(rs.getString("moTa"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ttg;
    }
}

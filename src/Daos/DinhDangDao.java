package Daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ConnectToDB.connectToQuanLyRapChieuPhimDB;
import Models.DinhDang;

public class DinhDangDao {
    public static ArrayList<DinhDang> layDanhSachDinhDang() {
        ArrayList<DinhDang> dsDinhDang = new ArrayList<>();
        try {
            Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
            String sql = "SELECT format_id, format_name FROM tblFormatRoom";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DinhDang dd = new DinhDang();
                dd.setMaDinhDang(rs.getString("format_id"));
                dd.setTenDinhDang(rs.getString("format_name"));
                dsDinhDang.add(dd);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsDinhDang;
    }

    public static DinhDang layDinhDangTheoMaDinhDang(String maDinhDang) {
        DinhDang dd = null;
        try {
            Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
            String sql = "SELECT format_id, format_name FROM tblFormatRoom WHERE format_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, maDinhDang);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                dd = new DinhDang();
                dd.setMaDinhDang(rs.getString("format_id"));
                dd.setTenDinhDang(rs.getString("format_name"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dd;
    }
}

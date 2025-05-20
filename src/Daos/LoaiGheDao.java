package Daos;

import java.sql.*;

import java.util.ArrayList;

import ConnectToDB.connectToQuanLyRapChieuPhimDB;
import Models.*;

public class LoaiGheDao {

    public static ArrayList<LoaiGhe> layDanhSachLoaiGhe() {
        ArrayList<LoaiGhe> dsLoaiGhe = new ArrayList<LoaiGhe>();
        try {
            Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
            String sql = "SELECT * FROM tblSeatType";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                LoaiGhe loaiGhe = new LoaiGhe();
                loaiGhe.setMaLoaiGhe(rs.getString("seat_type_id"));
                loaiGhe.setLoaiGhe(rs.getString("seat_type"));

                dsLoaiGhe.add(loaiGhe);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsLoaiGhe;
    }
}
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
    
    public static LoaiGhe layLoaiGheTheoMa(String maLoaiGhe) {
        LoaiGhe loaiGhe = null;
        try {
            Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
            String sql = "SELECT * FROM tblSeatType WHERE seat_type_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, maLoaiGhe);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                loaiGhe = new LoaiGhe();
                loaiGhe.setMaLoaiGhe(rs.getString("seat_type_id"));
                loaiGhe.setLoaiGhe(rs.getString("seat_type"));
                loaiGhe.setGiaGhe(rs.getFloat("seat_price"));
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loaiGhe;
    }
}
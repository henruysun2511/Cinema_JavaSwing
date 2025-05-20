package Daos;

import java.sql.*;
import java.util.ArrayList;

import ConnectToDB.connectToQuanLyRapChieuPhimDB;
import Models.Ve;

public class VeDao {

    // select 
    public static ArrayList<Ve> layDanhSachVe() {
        ArrayList<Ve> dsVe = new ArrayList<Ve>();
        try {
            Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
            String sql = "SELECT * FROM tblTicket";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Ve ve = new Ve();
                ve.setMaVe(rs.getString("ticket_id"));
                ve.setMaGhe(rs.getString("seat_id"));
                ve.setMaSuatChieu(rs.getString("showtime_id"));
                ve.setGiaVe(rs.getDouble("ticket_price"));
                ve.setMaHoaDonVe(rs.getString("ticket_bill_id"));

                dsVe.add(ve);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsVe;
    }

    // insert
    public static boolean themVe(Ve ve) {
        try {
            Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
            String sql = "INSERT INTO tblTicket (ticket_id, seat_id, showtime_id, ticket_price, ticket_bill_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ve.getMaVe());
            stmt.setString(2, ve.getMaGhe());
            stmt.setString(3, ve.getMaSuatChieu());
            stmt.setDouble(4, ve.getGiaVe());
            stmt.setString(5, ve.getMaHoaDonVe());

            int rowsInserted = stmt.executeUpdate();
            conn.close();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // update
    public static boolean capNhatVe(Ve ve) {
        try {
            Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
            String sql = "UPDATE tblTicket SET seat_id = ?, showtime_id = ?, ticket_price = ?,  ticket_bill_id = ? WHERE ticket_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ve.getMaGhe());
            stmt.setString(2, ve.getMaSuatChieu());
            stmt.setDouble(3, ve.getGiaVe());
            stmt.setString(4, ve.getMaHoaDonVe());
            stmt.setString(5, ve.getMaVe());

            int rowsUpdated = stmt.executeUpdate();
            conn.close();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
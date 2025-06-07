package Daos;

import java.sql.*;
import java.util.ArrayList;
import ConnectToDB.connectToQuanLyRapChieuPhimDB;
import Models.HoaDonVe;
import Models.Ve;

public class VeDao {

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
                ve.setMaHoaDonVe(rs.getString("payment_id"));

                dsVe.add(ve);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsVe;
    }

    public static ArrayList<Ve> layDanhSachVeTheoMaNguoiDung(String maNguoiDung) {
        ArrayList<Ve> dsVe = new ArrayList<>();
        try {
            Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
            String sql = "SELECT t.ticket_id, t.seat_id, t.showtime_id, t.qrcode, t.ticket_price, t.payment_id " +
                    "FROM tblTicket t " +
                    "JOIN tblTicketBill tb ON t.payment_id = tb.payment_id " +
                    "WHERE tb.user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, maNguoiDung);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Ve ve = new Ve();
                ve.setMaVe(rs.getString("ticket_id"));
                ve.setMaGhe(rs.getString("seat_id"));
                ve.setMaSuatChieu(rs.getString("showtime_id"));
                ve.setGiaVe(rs.getDouble("ticket_price"));
                ve.setMaHoaDonVe(rs.getString("payment_id"));
                dsVe.add(ve);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsVe;
    }

    public static ArrayList<Ve> layDanhSachVeTheoMaHoaDon(String maHoaDonVe) {
        ArrayList<Ve> dsVe = new ArrayList<Ve>();
        try {
            Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
            String sql = "SELECT * FROM tblTicket WHERE payment_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, maHoaDonVe);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Ve ve = new Ve();
                ve.setMaVe(rs.getString("ticket_id"));
                ve.setMaGhe(rs.getString("seat_id"));
                ve.setMaSuatChieu(rs.getString("showtime_id"));
                ve.setGiaVe(rs.getDouble("ticket_price"));
                ve.setMaHoaDonVe(rs.getString("payment_id"));
                dsVe.add(ve);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsVe;
    }
    
    public static Ve layVeTheoMaVe(String maVe){
        Ve ve = null;
        try {
            Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
            String sql = "SELECT * FROM tblTicket WHERE ticket_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, maVe);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ve = new Ve();
                ve.setMaVe(rs.getString("ticket_id"));
                ve.setMaGhe(rs.getString("seat_id"));
                ve.setMaHoaDonVe(rs.getString("payment_id"));
                ve.setMaSuatChieu(rs.getString("showtime_id"));
                ve.setGiaVe(rs.getFloat("ticket_price"));
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ve;
    }

    public static HoaDonVe layHoaDonVeTheoMaHoaDonVe(String maHoaDonVe){
        HoaDonVe hdv = null;
        try {
            Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
            String sql = "SELECT * FROM tblTicketBill WHERE payment_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, maHoaDonVe);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                hdv = new HoaDonVe();
                hdv.setMaThanhToan(rs.getString("payment_id"));
                hdv.setLichSuGiaoDich(rs.getTimestamp("transaction_history"));
                hdv.setSoLuongVe(rs.getInt("ticket_quantity"));
                hdv.setTongThanhToan(rs.getFloat("price_total"));
                hdv.setMaNguoiDung(rs.getString("user_id"));
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hdv;
    }

    public static boolean themVe(Ve ve) {
        try {
            Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
            String sql = "INSERT INTO tblTicket (ticket_id, seat_id, showtime_id, ticket_price, payment_id) VALUES (?, ?, ?, ?, ?)";
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

    public static boolean themHoaDonVe(HoaDonVe hoaDonVe) {
        try {
            Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
            String sql = "Insert into tblTicketBill (payment_id, ticket_quantity, price_total, voucher_id, user_id, payment_method, payment_qr, transaction_history) values (?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, hoaDonVe.getMaThanhToan());
            stmt.setInt(2, hoaDonVe.getSoLuongVe());
            stmt.setDouble(3, hoaDonVe.getTongThanhToan());
            stmt.setString(4, hoaDonVe.getMaVoucher());
            stmt.setString(5, hoaDonVe.getMaNguoiDung());
            stmt.setString(6, hoaDonVe.getHinhThucThanhToan());
            stmt.setString(7, hoaDonVe.getMaQR());
            stmt.setTimestamp(8, hoaDonVe.getLichSuGiaoDich());

            int rowsUpdated = stmt.executeUpdate();
            conn.close();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
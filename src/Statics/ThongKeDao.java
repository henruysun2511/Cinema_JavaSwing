package Statics;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ConnectToDB.connectToQuanLyRapChieuPhimDB;

public class ThongKeDao {
	   public static List<ThongKePhim> layDanhSachThongKe() {
	        List<ThongKePhim> ds = new ArrayList<>();

	        String sql = """
	            SELECT 
	                m.movie_name,
	                COUNT(t.ticket_id) AS so_luong_ve,
	                SUM(t.ticket_price) AS tong_doanh_thu
	            FROM 
	                tblTicket t
	            JOIN 
	                tblTicketBill b ON t.payment_id = b.payment_id
	            JOIN 
	                tblShowtime s ON t.showtime_id = s.showtime_id
	            JOIN 
	                tblMovie m ON s.movie_id = m.movie_id
	            GROUP BY 
	                m.movie_name
	        """;

	        try (Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {

	            while (rs.next()) {
	                String tenPhim = rs.getString("movie_name");
	                int soVe = rs.getInt("so_luong_ve");
	                double doanhThu = rs.getDouble("tong_doanh_thu");

	                ds.add(new ThongKePhim(tenPhim, soVe, doanhThu));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return ds;
	    }
	   
	   public static List<ThongKeNgay> layThongKeTheoNgayChieu() {
		    List<ThongKeNgay> ds = new ArrayList<>();

		    String sql = """
		        SELECT 
		            s.showtime_day AS ngay_chieu,
		            COUNT(t.ticket_id) AS so_luong_ve,
		            SUM(t.ticket_price) AS tong_doanh_thu
		        FROM 
		            tblTicket t
		        JOIN 
		            tblShowtime s ON t.showtime_id = s.showtime_id
		        GROUP BY 
		            s.showtime_day
		        ORDER BY 
		            s.showtime_day
		    """;

		    try (Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql);
		         ResultSet rs = stmt.executeQuery()) {

		        while (rs.next()) {
		            String ngay = rs.getString("ngay_chieu");
		            int soVe = rs.getInt("so_luong_ve");
		            double doanhThu = rs.getDouble("tong_doanh_thu");

		            ds.add(new ThongKeNgay(ngay, soVe, doanhThu));
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return ds;
		}
	   
	   public static List<ThongKeLoaiGhe> laySoVeTheoLoaiGhe() {
	        List<ThongKeLoaiGhe> list = new ArrayList<>();
	        String sql = "SELECT lg.seat_type, COUNT(*) as so_luong \r\n"
	        		+ "                     FROM tblTicket t \r\n"
	        		+ "                     JOIN tblSeat s ON t.seat_id = s.seat_id \r\n"
	        		+ "                     JOIN tblSeatType lg ON s.seat_type_id= lg.seat_type_id\r\n"
	        		+ "                     GROUP BY lg.seat_type";

	        try (Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {
	                String loaiGhe = rs.getString("seat_type");
	                int soLuong = rs.getInt("so_luong");
	                list.add(new ThongKeLoaiGhe(loaiGhe, soLuong));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
	    }
	}


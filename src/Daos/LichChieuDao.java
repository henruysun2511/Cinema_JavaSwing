package Daos;

import java.util.ArrayList;


import ConnectToDB.connectToQuanLyRapChieuPhimDB;
import java.beans.Statement;
import java.lang.foreign.PaddingLayout;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;


import Models.*;


public class LichChieuDao {
	public static ArrayList<Date> layDanhSachNgayChieu(){
		ArrayList<Date> dsNgayChieu = new ArrayList<Date>();
		try {
			Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
			String sql = "Select distinct showtime_day from tblShowTime";
			PreparedStatement stmt = conn.prepareStatement(sql); 
			ResultSet rs = stmt.executeQuery(); 
			
			while(rs.next()) {
				dsNgayChieu.add(rs.getDate("showtime_day"));
			}
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dsNgayChieu;
	}
	
	public static ArrayList<Phim> layDanhSachPhimTheoNgay(Date ngayChieu){
		ArrayList<Phim> dsPhimTheoNgay = new ArrayList<Phim>();
		try {
			Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
			String sql = "select distinct m.movie_id, movie_name, duration, age_permisson, poster from tblMovie m join tblShowTime s on m.movie_id =s.movie_id where showtime_day= ?";
			PreparedStatement stmt = conn.prepareStatement(sql); 
			stmt.setDate(1, new java.sql.Date(ngayChieu.getTime()));
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				Phim p = new Phim();
				p.setMaPhim(rs.getString("movie_id"));
				p.setTenPhim(rs.getString("movie_name"));
				p.setThoiLuong(rs.getInt("duration"));
				p.setAnhPhim(rs.getString("age_permisson"));
				p.setAnhPhim(rs.getString("poster"));
		
				dsPhimTheoNgay.add(p);
			}
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dsPhimTheoNgay;
	}
	
	public static ArrayList<LichChieu> layDanhSachLichChieuTheoPhimVaNgay(String maPhim,Date ngayChieu){
		ArrayList<LichChieu> dsLichChieuTheoPhimVaNgay = new ArrayList<LichChieu>();
		try {
			Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
			String sql = "select showtime_id, showtime, movie_id, room_id, showtime_day from tblShowTime where movie_id = ? and showtime_day = ? order by showtime asc";
			PreparedStatement stmt = conn.prepareStatement(sql); 
			//truyền điều kiên
			stmt.setString(1, maPhim); 
			stmt.setDate(2, new java.sql.Date(ngayChieu.getTime()));
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				LichChieu l = new LichChieu();
				l.setMaLichChieu(rs.getString("showtime_id"));
				l.setKhungGioChieuString(rs.getTime("showtime"));
				l.setMaPhim(rs.getString("movie_id"));
				l.setMaPhong(rs.getString("room_id"));
				l.setNgayChieu(rs.getDate("showtime_day"));
				
				dsLichChieuTheoPhimVaNgay.add(l);				
			}
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dsLichChieuTheoPhimVaNgay;
	}
	
	//Lấy danh sách lịch chiếu theo phòng và ngày
	public static ArrayList<LichChieu> layDanhSachLichChieuTheoPhongVaNgay(String maPhong, Date ngayChieu){
		ArrayList<LichChieu> dsLichChieuTheoPhongVaNgay = new ArrayList<LichChieu>();
		try {
			Connection conn= connectToQuanLyRapChieuPhimDB.getConnection();
			String sql ="select * from tblShowTime where room_id = ? and showtime_day = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, maPhong);
			stmt.setDate(2, new Date(ngayChieu.getTime()));
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				LichChieu l = new LichChieu(
						rs.getString("showtime_id"),
						rs.getTime("showtime"),
						rs.getString("movie_id"),
						rs.getString("room_id"),
						rs.getDate("showtime_day")
						);
				dsLichChieuTheoPhongVaNgay.add(l);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsLichChieuTheoPhongVaNgay;
	}
	
	public static boolean themLichChieu(LichChieu l) {
		try {
			Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
			String sql = "insert into tblShowTime values(?,?,?,?,?) ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, l.getMaLichChieu());
			stmt.setTime(2, l.getKhungGioChieuString());
			stmt.setString(3, l.getMaPhim());
			stmt.setString(4, l.getMaPhong());
			stmt.setDate(5, l.getNgayChieu());
			
			int rows = stmt.executeUpdate();
            conn.close();
            return rows > 0;
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean suaLichChieu(LichChieu l) {
		try {
			Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
			String sql = "update tblShowTime set SET movie_id = ?, showtime_day = ?, showtime = ?, room_id = ? WHERE showtime_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
	        
	        // Set giá trị cho các tham số
	        stmt.setString(1, l.getMaPhim());
	        stmt.setDate(2, l.getNgayChieu());
	        stmt.setTime(3, l.getKhungGioChieuString());
	        stmt.setString(4, l.getMaPhong());
	        stmt.setString(5, l.getMaLichChieu());
			
	        int rows = stmt.executeUpdate();
            conn.close();
            return rows > 0;
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean xoaNgayChieu(String maLichChieu) {
	    try (Connection conn = connectToQuanLyRapChieuPhimDB.getConnection()) {
	        String sql = "DELETE FROM tblShowTime WHERE showtime_id = ?";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setString(1, maLichChieu);

	        int rows = stmt.executeUpdate();
            conn.close();
            return rows > 0;
            
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	   
	}
}

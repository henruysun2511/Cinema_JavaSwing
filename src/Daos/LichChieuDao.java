package Daos;

import java.util.ArrayList;



import ConnectToDB.connectToQuanLyRapChieuPhimDB;

import java.beans.Statement;
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
			ResultSet rs = stmt.executeQuery(); //thực hiện truy vấn từng dòng
			
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
			String sql = "select showtime_id, showtime from tblShowTime where movie_id = ? and showtime_day = ? order by showtime asc";
			PreparedStatement stmt = conn.prepareStatement(sql); 
			//truyền điều kiên
			stmt.setString(1, maPhim); 
			stmt.setDate(2, new java.sql.Date(ngayChieu.getTime()));
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				LichChieu l = new LichChieu();
				l.setMaLichChieu(rs.getString("showtime_id"));
				l.setKhungGioChieuString(rs.getTime("showtime"));
				
				dsLichChieuTheoPhimVaNgay.add(l);				
			}
			conn.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dsLichChieuTheoPhimVaNgay;
	}
	
	

}

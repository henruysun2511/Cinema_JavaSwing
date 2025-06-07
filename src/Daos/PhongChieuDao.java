package Daos;
import java.sql.*;


import java.util.ArrayList;
import ConnectToDB.connectToQuanLyRapChieuPhimDB;
import Models.*;

public class PhongChieuDao {
	//Lấy tất phòng chiếu
	public static ArrayList<PhongChieu> layDanhSachPhongChieu(){
		ArrayList<PhongChieu> dsPhong = new ArrayList<>(); 
		try {
			Connection conn = connectToQuanLyRapChieuPhimDB.getConnection(); 
			String sql = "Select * from tblShowRoom"; 
			PreparedStatement stmt = conn.prepareStatement(sql); 
			ResultSet rs = stmt.executeQuery(); 
			
			while(rs.next()) { 
				 PhongChieu p = new PhongChieu(
		                    rs.getString("room_id"),
		                    rs.getInt("room_number"),
		                    rs.getInt("seat_total"),
		                    rs.getString("format_id")
		                );
				 dsPhong.add(p); 
			}
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dsPhong; 			
	}
	
	public static PhongChieu layPhongChieuTheoMaPhong(String maPhong){
		PhongChieu pc = null;
		try {
			Connection conn = connectToQuanLyRapChieuPhimDB.getConnection(); 
			String sql = "Select * from tblShowRoom where room_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql); 
			stmt.setString(1, maPhong);
			ResultSet rs = stmt.executeQuery(); 
			
			while(rs.next()) { 
				 pc = new PhongChieu(
		                    rs.getString("room_id"),
		                    rs.getInt("room_number"),
		                    rs.getInt("seat_total"),
		                    rs.getString("format_id")
		                );
			}
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}

		return pc; 	
	}
	
	public static String layMaPhongTheoTenPhong(int tenPhong) {
	    String maPhong = null;
	    try {
	        Connection conn = connectToQuanLyRapChieuPhimDB.getConnection(); 
	        String sql = "SELECT room_id FROM tblShowRoom WHERE room_number = ?";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, tenPhong);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            maPhong = rs.getString("room_id");
	        }
	        rs.close();
	        stmt.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return maPhong;
	}
	
}
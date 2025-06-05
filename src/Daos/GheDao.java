package Daos;

import java.util.ArrayList;
import java.sql.*;

import ConnectToDB.connectToQuanLyRapChieuPhimDB;
import Models.Ghe;
import Models.LoaiGhe;


public class GheDao {
	public static ArrayList<Ghe> layDanhSachGheTheoMaSuatChieu(String maSuatChieu){
		ArrayList<Ghe> dsGhe = new ArrayList<Ghe>();
		try {
			Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
			String sql = "SELECT s.seat_id, s.seat_name, s.seat_type_id, s.room_id\r\n"
					+ "FROM tblSeat s JOIN tblShowRoom r ON s.room_id = r.room_id\r\n"
					+ "JOIN tblShowTime st ON r.room_id = st.room_id\r\n"
					+ "LEFT JOIN tblSeatStatus ss ON s.seat_id = ss.seat_id \r\n"
					+ "WHERE st.showtime_id = ?" ;
			PreparedStatement stmt = conn.prepareStatement(sql); 
			stmt.setString(1, maSuatChieu); 
			ResultSet rs = stmt.executeQuery(); 
			while(rs.next()) {
				Ghe gh = new Ghe();
				gh.setMaGhe(rs.getString("seat_id"));
				gh.setTenGhe(rs.getString("seat_name"));
				gh.setLoaiGhe(rs.getString("seat_type_id"));
				gh.setMaPhong(rs.getString("room_id"));
				
				dsGhe.add(gh);
			}
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return dsGhe;
	}
	

}

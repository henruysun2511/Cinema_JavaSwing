package Daos;

import java.util.ArrayList;
import java.util.Set;
import java.sql.*;

import ConnectToDB.connectToQuanLyRapChieuPhimDB;
import Models.Ghe;

public class GheDao {
	public static ArrayList<Ghe> layDanhSachGheTheoMaSuatChieu(String maSuatChieu) {
		ArrayList<Ghe> dsGhe = new ArrayList<Ghe>();
		try {
			Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
			String sql = "SELECT s.seat_id, s.seat_name, s.seat_type_id, s.room_id, ss.seat_status\r\n" + //
					"FROM tblSeat s JOIN tblShowRoom r ON s.room_id = r.room_id\r\n" + //
					"JOIN tblShowTime st ON r.room_id = st.room_id\r\n" + //
					"LEFT JOIN tblSeatStatus ss ON s.seat_id = ss.seat_id\r\n" + //
					"WHERE st.showtime_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, maSuatChieu);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Ghe gh = new Ghe();
				gh.setMaGhe(rs.getString("seat_id"));
				gh.setTenGhe(rs.getString("seat_name"));
				gh.setLoaiGhe(rs.getString("seat_type_id"));
				gh.setMaPhong(rs.getString("room_id"));
				gh.setTinhTrangGhe(rs.getInt("seat_status"));

				dsGhe.add(gh);
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsGhe;
	}

	public static Ghe layGheTheoMaGhe(String maGhe) {
		Ghe gh = null;
		try {
			Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
			String sql = "Select * from tblSeat where seat_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, maGhe);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				gh = new Ghe();
				gh.setMaGhe(rs.getString("seat_id"));
				gh.setTenGhe(rs.getString("seat_name"));
				gh.setLoaiGhe(rs.getString("seat_type_id"));
				gh.setMaPhong(rs.getString("room_id"));
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gh;
	}

	public static boolean themTinhTrangGhe(Set<Ghe> danhSachGheDaChon, String maLichChieu, int newStatus) {
		try {
			Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
			String sql = "INSERT INTO tblSeatStatus (seat_id, showtime_id, seat_status) VALUES (?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);

			int rowsInserted = 0;

			for (Ghe ghe : danhSachGheDaChon) {
				stmt.setString(1, ghe.getMaGhe());
				stmt.setString(2, maLichChieu);
				stmt.setInt(3, newStatus);
				rowsInserted += stmt.executeUpdate();
			}

			conn.close();
			return rowsInserted == danhSachGheDaChon.size();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}

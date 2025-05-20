package Daos;
import java.sql.*;


import java.util.ArrayList;
import ConnectToDB.connectToQuanLyRapChieuPhimDB;
import Models.Phim;
import Models.PhongChieu;

public class PhongChieuDao {
	//Lấy tất phòng chiếu
	public static ArrayList<PhongChieu> layDanhSachPhongChieuu(){
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
	
	// Hàm lấy phòng chiếu theo mã suất chiếu
//    public static PhongChieu layPhongChieuTheoMaSuatChieu(String showtime_id) {
//        PhongChieu phong = null;
//        try (Connection conn = connectToQuanLyRapChieuPhimDB.getConnection()) {
//            String sql = "SELECT * FROM tblShowTime st " +
//                         "JOIN tblShowRoom sr ON st.room_id = sr.room_id " +
//                         "WHERE st.showtime_id = ?";
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            stmt.setString(1, showtime_id);
//
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                phong = new PhongChieu(
//                    rs.getString("room_id"),
//                    rs.getString("room_number"),
//                    rs.getInt("seat_total"),
//                    rs.getInt("format_id")
//                );
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return phong;
//    }

}

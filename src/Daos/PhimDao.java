package Daos;
import java.sql.*;


import java.util.ArrayList;
import ConnectToDB.connectToQuanLyRapChieuPhimDB;
import Models.Phim;

//Ý tưởng của bài toán là dùng một list mang kiểu Phim để lưu trữ thông tin được lấy từ sql, các thuộc tính của sql sẽ được gán vào đối tương phim
//List sẽ chứa các đối tượng ấy

public class PhimDao {
	public static ArrayList<Phim> layPhimDangChieu(){
		ArrayList<Phim> dsPhim = new ArrayList<>(); //khởi tạo một list thuộc kiểu Phim rỗng
		
		try {
			Connection conn = connectToQuanLyRapChieuPhimDB.getConnection(); //kết nối sql
			String sql = "Select * from tblMovie"; //câu lệnh sql
			PreparedStatement stmt = conn.prepareStatement(sql); 
			//stmt.setString(1,movie_id) nếu có điều kiện
			ResultSet rs = stmt.executeQuery(); //chạy câu lệnh sql
			
			while(rs.next()) { //lấy dữ liệu từ ResultSet gán vào đối tượng Phim
				 Phim p = new Phim(
		                    rs.getString("movie_id"),
		                    rs.getString("movie_name"),
		                    rs.getString("release_date"),
		                    rs.getString("director"),
		                    rs.getInt("duration"),
		                    rs.getString("script"),	
		                    rs.getString("age_permisson"),	
		                    rs.getString("poster"),
		                    rs.getInt("status")
		                );
		         dsPhim.add(p); //lưu thông tin lấy được vào list
			}
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return dsPhim; //trả về list chứa mảng các đối tượng
				
	}
	
	public static Phim layPhimTheoMaPhim(String maPhim) {
		Phim phim = null;
		try {
			Connection conn = connectToQuanLyRapChieuPhimDB.getConnection(); //kết nối sql
			String sql = "Select * from tblMovie where movie_id = ?"; //câu lệnh sql
			PreparedStatement stmt = conn.prepareStatement(sql); 
			stmt.setString(1,maPhim);
			ResultSet rs = stmt.executeQuery(); //chạy câu lệnh sql
			
			while(rs.next()) { 
				 phim = new Phim(
		                    rs.getString("movie_id"),
		                    rs.getString("movie_name"),
		                    rs.getString("release_date"),
		                    rs.getString("director"),
		                    rs.getInt("duration"),
		                    rs.getString("script"),	
		                    rs.getString("age_permisson"),	
		                    rs.getString("poster"),
		                    rs.getInt("status")
		                );
			}
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return phim;
	}
	
	public static ArrayList<Phim> getPosterandNameOfMovieComingSoon(){
		ArrayList<Phim> dsTenVaPosterPhim = new ArrayList<>(); //khởi tạo một list thuộc kiểu Phim rỗng
		
		try {
			Connection conn = connectToQuanLyRapChieuPhimDB.getConnection(); //kết nối sql
			String sql = "Select movie_name, poster from tblMovie where status = 1"; //câu lệnh sql
			PreparedStatement stmt = conn.prepareStatement(sql); 
			//stmt.setString(1,movie_id) nếu có điều kiện
			ResultSet rs = stmt.executeQuery(); //chạy câu lệnh sql
			
			while(rs.next()) { //lấy dữ liệu từ ResultSet gán vào đối tượng Phim
				Phim p = new Phim();
				p.setTenPhim(rs.getString("movie_name"));
				p.setAnhPhim(rs.getString("poster"));
				dsTenVaPosterPhim.add(p); //lưu thông tin lấy được vào list
			}
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return dsTenVaPosterPhim; //trả về list chứa mảng các đối tượng: [(tên-poster)],[(tên-poster)]
				
	}

}

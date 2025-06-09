package Daos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ConnectToDB.connectToQuanLyRapChieuPhimDB;
import Models.Actor;

public class ActorDao {
	public static List<Actor> getAllActors() {
		String sql = "Select * from tblActor";
		try (Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			List<Actor> list = new ArrayList<>();
			while (rs.next()) {
				Actor actor = new Actor(rs.getString("actor_id"), rs.getString("actor_name"),
						rs.getString("actor_image"), rs.getString("nationality"), rs.getDate("birth"),
						rs.getString("biography"));
				list.add(actor);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	public static List<Actor> findActorsByMovieId(String movie_id) {
	    List<Actor> actors = new ArrayList<>();
	    String query = "SELECT * " +
	                   "FROM tblActor a " +
	                   "JOIN tblMovie_Actor ma ON a.actor_id = ma.actor_id " +
	                   "WHERE ma.movie_id = ?";
	    try (Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
	         PreparedStatement ps = conn.prepareStatement(query)) {

	        ps.setString(1, movie_id);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            Actor actor = new Actor(
	                rs.getString("actor_id"),
	                rs.getString("actor_name"),
	                rs.getString("actor_image"),
	                rs.getString("nationality"),
	                rs.getDate("birth"),
	                rs.getString("biography")
	               
	            );
	            actors.add(actor);
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException("Lỗi khi tìm kiếm diễn viên thất bại: " + e.getMessage(), e);
	    }
	    return actors;
	}
}

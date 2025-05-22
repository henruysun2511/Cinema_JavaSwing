package Utilzs;
import java.util.ArrayList;


import ConnectToDB.connectToQuanLyRapChieuPhimDB;
import java.beans.Statement;
import java.lang.foreign.PaddingLayout;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;

public class generateIDInOrder {
	public static String layMaCuoiCung(String tenBang, String tenCotMa) {
	    String maCuoi = null;
	    String sql = "SELECT TOP 1 " + tenCotMa + " FROM " + tenBang + " ORDER BY " + tenCotMa + " DESC";

	    try (
	    	 Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        if (rs.next()) {
	            maCuoi = rs.getString(tenCotMa);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return maCuoi;
	}
	
	public static String sinhMaTuDong(String maCuoi, String prefix) {
	    if (maCuoi == null) {
	        return prefix + "001";
	    }
	    String so = maCuoi.substring(prefix.length());
	    int soMoi = Integer.parseInt(so) + 1;
	    return String.format("%s%03d", prefix, soMoi);
	}
}

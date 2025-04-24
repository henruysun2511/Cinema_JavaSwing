package ConnectToDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class connectToQuanLyRapChieuPhimDB {
    private static String DB_URL = "jdbc:sqlserver://LAPTOP-198J6NK2\\SQLEXPRESS:1433;databaseName=QuanLyRapChieuPhim;encrypt=true;trustServerCertificate=true";
    private static String USER_NAME = "sa";
    private static String PASSWORD = "alittlesun100";

    public static Connection getConnection(String dbURL, String userName, String password) {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) {
        Connection conn = getConnection(
                "jdbc:sqlserver://LAPTOP-198J6NK2\\SQLEXPRESS:1433;databaseName=QuanLyRapChieuPhim;encrypt=true;trustServerCertificate=true",
                "sa", "alittlesun100");
        if (conn != null) {
            System.out.println("Kết nối SQL Server thành công");
        } else {
            System.out.println("Kết nối SQL Server thất bại");
        }
    }
}

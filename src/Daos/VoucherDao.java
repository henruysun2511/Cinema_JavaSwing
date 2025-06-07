package Daos;

import java.sql.*;
import java.util.ArrayList;
import ConnectToDB.connectToQuanLyRapChieuPhimDB;
import Models.Voucher;

public class VoucherDao {
    public static ArrayList<Voucher> getAllVouchers() {
        ArrayList<Voucher> voucherList = new ArrayList<>();
        try {
            Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
            String sql = "SELECT * FROM tblVoucher";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Voucher v = new Voucher(
                        rs.getString("voucher_id"),
                        rs.getString("voucher_name"),
                        rs.getFloat("voucher_discount"),
                        rs.getDate("voucher_start"),
                        rs.getDate("voucher_end"),
                        rs.getString("voucher_script"),
                        rs.getString("voucher_image"));
                voucherList.add(v);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return voucherList;
    }

    public static Voucher getVoucherById(String voucherId) {
        Voucher v = null;
        try {
            Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
            String sql = "SELECT * FROM tblVoucher WHERE voucher_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, voucherId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                v = new Voucher(
                        rs.getString("voucher_id"),
                        rs.getString("voucher_name"),
                        rs.getFloat("voucher_discount"),
                        rs.getDate("voucher_start"),
                        rs.getDate("voucher_end"),
                        rs.getString("voucher_script"),
                        rs.getString("voucher_image"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }

    public static boolean insertVoucher(Voucher v) {
        try {
            Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
            String sql = "INSERT INTO tblVoucher (voucher_id, voucher_name, voucher_discount, voucher_start, voucher_end, voucher_script) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, v.getMaVoucher());
            stmt.setString(2, v.getTenVoucher());
            stmt.setFloat(3, v.getPhanTramGiam());
            stmt.setDate(4, v.getNgayBatDau());
            stmt.setDate(5, v.getNgayKetThuc());
            stmt.setString(6, v.getMoTa()); // sửa vị trí tham số thành 6
            int rows = stmt.executeUpdate();
            conn.close();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateVoucher(Voucher v) {
        try {
            Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
            String sql = "UPDATE tblVoucher SET voucher_name = ?, voucher_discount = ?, voucher_start = ?, voucher_end = ?, voucher_script = ? WHERE voucher_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, v.getTenVoucher());
            stmt.setFloat(2, v.getPhanTramGiam());
            stmt.setDate(3, v.getNgayBatDau());
            stmt.setDate(4, v.getNgayKetThuc());
            stmt.setString(5, v.getMoTa());
            stmt.setString(6, v.getMaVoucher());

            int rows = stmt.executeUpdate();
            conn.close();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteVoucher(String voucherId) {
        try {
            Connection conn = connectToQuanLyRapChieuPhimDB.getConnection();
            String sql = "DELETE FROM tblVoucher WHERE voucher_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, voucherId);
            int rows = stmt.executeUpdate();
            conn.close();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}

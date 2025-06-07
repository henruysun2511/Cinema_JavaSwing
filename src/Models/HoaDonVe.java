package Models;
import java.sql.*;

public class HoaDonVe {
	private String maThanhToan;
	private int soLuongVe;
	private double tongThanhToan;
	private String maVoucher;
	private String maNguoiDung;
	private String hinhThucThanhToan;
	private String maQR;
	private Timestamp lichSuGiaoDich;

	public HoaDonVe() {
	}

	public HoaDonVe(String maThanhToan, int soLuongVe, double tongThanhToan, String maVoucher,
			String maNguoiDung, String hinhThucThanhToan, String maQR, Timestamp lichSuGiaoDich) {
		this.maThanhToan = maThanhToan;
		this.soLuongVe = soLuongVe;
		this.tongThanhToan = tongThanhToan;
		this.maVoucher = maVoucher;
		this.maNguoiDung = maNguoiDung;
		this.hinhThucThanhToan = hinhThucThanhToan;
		this.maQR = maQR;
		this.lichSuGiaoDich = lichSuGiaoDich;
	}



	public String getMaThanhToan() {
		return maThanhToan;
	}

	public void setMaThanhToan(String maThanhToan) {
		this.maThanhToan = maThanhToan;
	}

	public int getSoLuongVe() {
		return soLuongVe;
	}

	public void setSoLuongVe(int soLuongVe) {
		this.soLuongVe = soLuongVe;
	}

	public double getTongThanhToan() {
		return tongThanhToan;
	}

	public void setTongThanhToan(double tongThanhToan) {
		this.tongThanhToan = tongThanhToan;
	}

	public String getMaVoucher() {
		return maVoucher;
	}

	public void setMaVoucher(String maVoucher) {
		this.maVoucher = maVoucher;
	}

	public String getMaNguoiDung() {
		return maNguoiDung;
	}

	public void setMaNguoiDung(String maNguoiDung) {
		this.maNguoiDung = maNguoiDung;
	}

	public String getHinhThucThanhToan() {
		return hinhThucThanhToan;
	}

	public void setHinhThucThanhToan(String hinhThucThanhToan) {
		this.hinhThucThanhToan = hinhThucThanhToan;
	}

	public String getMaQR() {
		return maQR;
	}

	public void setMaQR(String maQR) {
		this.maQR = maQR;
	}

	public Timestamp getLichSuGiaoDich() {
		return lichSuGiaoDich;
	}

	public void setLichSuGiaoDich(Timestamp lichSuGiaoDich) {
		this.lichSuGiaoDich = lichSuGiaoDich;
	}

	
}

package Models;

public class ThanhToan {
	private String maThanhToan;
	private String maHoaDon;
	private String maVoucher;
	private double tongThanhToan;
	private String hinhThucThanhToan;
	private String trangThai;
	private String maNguoiDung;
	private String maQR;
	
	public ThanhToan() {
		this.maThanhToan = "";
		this.maHoaDon = "";
		this.maVoucher = "";
		this.tongThanhToan = 0;
		this.hinhThucThanhToan = "";
		this.trangThai = "";
		this.maNguoiDung = "";
		this.maQR = "";
	}
	public ThanhToan(String maThanhToan, String maHoaDon, String maVoucher, int tongThanhToan, String hinhThucThanhToan, String trangThai, String maNguoiDung, String maQR ) {
		this.maThanhToan = maThanhToan;
		this.maHoaDon = maHoaDon;
		this.maVoucher = maVoucher;
		this.tongThanhToan = tongThanhToan;
		this.hinhThucThanhToan = hinhThucThanhToan;
		this.trangThai = trangThai;
		this.maNguoiDung = maNguoiDung;
		this.maQR = maQR;
		
	}
	public String getMaThanhToan() {
		return maThanhToan;
	}
	public String getMaHoaDon() {
		return maHoaDon;
		
	}
	public String getmaVoucher() {
		return maVoucher;
	}
	public double getTongThanhToan() {
		return tongThanhToan;
		
	}
	public String getHinhThucThanhToan() {
		return hinhThucThanhToan;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public String getMaNguoiDung() {
		return maNguoiDung;
		
	}
	public String getMaQR() {
		return maQR;
	}
	public void setMaThanhToan(String maThanhToan) {
		this.maThanhToan = maThanhToan;
	}
	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public void setMaVoucher(String maVoucher) {
		this.maVoucher = maVoucher;
	}
	public void setTongThanhToan(double tongThanhToan) {
		this.tongThanhToan = tongThanhToan;
	}
	public void setHinhThucThanhToan(String hinhThucThanhToan) {
		this.hinhThucThanhToan = hinhThucThanhToan;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public void setMaNguoiDung(String maNguoiDung) {
		this.maNguoiDung = maNguoiDung;
	}
	public void setMaQR(String maQR) {
		this.maQR = maQR;
	}

}


package Models;
import java.sql.Date;
import java.sql.Time;

public class LichChieu {
	private String maLichChieu;
	private Time khungGioChieuString;
	private String maPhim;
    private String maPhong;
    private Date ngayChieu; 
    
    public LichChieu() {
    	
    }

	public LichChieu(String maLichChieu, Time khungGioChieuString, String maPhim, String maPhong, Date ngayChieu) {
		super();
		this.maLichChieu = maLichChieu;
		this.khungGioChieuString = khungGioChieuString;
		this.maPhim = maPhim;
		this.maPhong = maPhong;
		this.ngayChieu = ngayChieu;
	}

	public String getMaLichChieu() {
		return maLichChieu;
	}

	public void setMaLichChieu(String maLichChieu) {
		this.maLichChieu = maLichChieu;
	}

	public Time getKhungGioChieuString() {
		return khungGioChieuString;
	}

	public void setKhungGioChieuString(Time khungGioChieuString) {
		this.khungGioChieuString = khungGioChieuString;
	}

	public String getMaPhim() {
		return maPhim;
	}

	public void setMaPhim(String maPhim) {
		this.maPhim = maPhim;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public Date getNgayChieu() {
		return ngayChieu;
	}

	public void setNgayChieu(Date ngayChieu) {
		this.ngayChieu = ngayChieu;
	}
    
    
}

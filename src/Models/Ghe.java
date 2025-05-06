package Models;

public class Ghe {
	private String maGhe;
	private String tenGhe;
	private String loaiGhe;
	private String maPhong;
	
	public Ghe() {
		
	}

	public Ghe(String maGhe, String tenGhe, String loaiGhe, String maPhong) {
		super();
		this.maGhe = maGhe;
		this.tenGhe = tenGhe;
		this.loaiGhe = loaiGhe;
		this.maPhong = maPhong;
	}

	public String getMaGhe() {
		return maGhe;
	}

	public void setMaGhe(String maGhe) {
		this.maGhe = maGhe;
	}

	public String getTenGhe() {
		return tenGhe;
	}

	public void setTenGhe(String tenGhe) {
		this.tenGhe = tenGhe;
	}

	public String getLoaiGhe() {
		return loaiGhe;
	}

	public void setLoaiGhe(String loaiGhe) {
		this.loaiGhe = loaiGhe;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	
	

}

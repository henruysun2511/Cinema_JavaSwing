package Models;

public class LoaiGhe {
	private String maLoaiGhe;
	private String loaiGhe;
	private double giaGhe;
	
	public LoaiGhe() {
		this.maLoaiGhe = "";
		this.loaiGhe = "";
		this.giaGhe = 0;
	}
	
	public LoaiGhe(String maLoaiGhe, String loaiGhe, double giaGhe) {
		super();
		this.maLoaiGhe = maLoaiGhe;
		this.loaiGhe = loaiGhe;
		this.giaGhe = giaGhe;
	}

	public String getMaLoaiGhe() {
		return maLoaiGhe;
	}

	public void setMaLoaiGhe(String maLoaiGhe) {
		this.maLoaiGhe = maLoaiGhe;
	}

	public String getLoaiGhe() {
		return loaiGhe;
	}

	public void setLoaiGhe(String loaiGhe) {
		this.loaiGhe = loaiGhe;
	}

	public double getGiaGhe() {
		return giaGhe;
	}

	public void setGiaGhe(double giaGhe) {
		this.giaGhe = giaGhe;
	}
	
	
	
	

}

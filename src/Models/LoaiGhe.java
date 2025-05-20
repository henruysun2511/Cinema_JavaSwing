package Models;

public class LoaiGhe {
	private String maLoaiGhe;
	private String loaiGhe;
	
	public LoaiGhe() {
		this.maLoaiGhe = "";
		this.loaiGhe = "";
	}
	
	public LoaiGhe(String maLoaiGhe, String loaiGhe) {
		super();
		this.maLoaiGhe = maLoaiGhe;
		this.loaiGhe = loaiGhe;
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
	
	

}

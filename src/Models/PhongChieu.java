package Models;

public class PhongChieu {
    private String maPhong;
    private int tenPhong;
    private int soLuongGhe;
    private String maDinhDang;

    public PhongChieu() {
        this.maPhong = "";
        this.tenPhong = 0;
        this.soLuongGhe = 0;
        this.maDinhDang = "";
    }

	public PhongChieu(String maPhong, int tenPhong, int soLuongGhe, String maDinhDang) {
		super();
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		this.soLuongGhe = soLuongGhe;
		this.maDinhDang = maDinhDang;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public int getTenPhong() {
		return tenPhong;
	}

	public void setTenPhong(int tenPhong) {
		this.tenPhong = tenPhong;
	}

	public int getSoLuongGhe() {
		return soLuongGhe;
	}

	public void setSoLuongGhe(int soLuongGhe) {
		this.soLuongGhe = soLuongGhe;
	}

	public String getMaDinhDang() {
		return maDinhDang;
	}

	public void setMaDinhDang(String maDinhDang) {
		this.maDinhDang = maDinhDang;
	}
	
	@Override
    public String toString() {
        return String.valueOf(this.tenPhong); 
    }

    
}

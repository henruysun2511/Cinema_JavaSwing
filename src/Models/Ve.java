package Models;

public class Ve {
    private String maVe;
    private String maGhe;
    private String maSuatChieu;
    private double giaVe;
    private String maHoaDonVe;
    
    public Ve() {
        this.maVe = "";
        this.maGhe = "";
        this.maSuatChieu = "";
        this.giaVe = 0.0;
        this.maHoaDonVe = "";
    }

    public Ve(String maVe, String maGhe, String maSuatChieu, double giaVe, String maHoaDonVe) {
		super();
		this.maVe = maVe;
		this.maGhe = maGhe;
		this.maSuatChieu = maSuatChieu;
		this.giaVe = giaVe;
		this.maHoaDonVe = maHoaDonVe;
	}

	public String getMaVe() {
		return maVe;
	}

	public void setMaVe(String maVe) {
		this.maVe = maVe;
	}

	public String getMaGhe() {
		return maGhe;
	}

	public void setMaGhe(String maGhe) {
		this.maGhe = maGhe;
	}

	public String getMaSuatChieu() {
		return maSuatChieu;
	}

	public void setMaSuatChieu(String maSuatChieu) {
		this.maSuatChieu = maSuatChieu;
	}

	public double getGiaVe() {
		return giaVe;
	}

	public void setGiaVe(double giaVe) {
		this.giaVe = giaVe;
	}

	public String getMaHoaDonVe() {
		return maHoaDonVe;
	}

	public void setMaHoaDonVe(String maHoaDonVe) {
		this.maHoaDonVe = maHoaDonVe;
	}

	
}

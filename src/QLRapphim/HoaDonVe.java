package QLRapphim;

public class HoaDonVe {
	private String maThanhToan;
    private int soLuongVe;
    private float tongTien;
    private String maGiamGia;
    private String maNguoiDung;
    private String phuongThucThanhToan;
    private String maQr;
   
    public HoaDonVe() {
    	this.maThanhToan="";
    	this.soLuongVe=0;
    	this.tongTien=0;
    	this.maGiamGia="";
    	this.maNguoiDung="";
    	this.phuongThucThanhToan="";
    	this.maQr="";
   	
    }

    public HoaDonVe(String maThanhToan, int soLuongVe, float tongTien, String maGiamGia, String maNguoiDung, String phuongThucThanhToan, String maQr) {
        this.maThanhToan = maThanhToan;
        this.soLuongVe = soLuongVe;
        this.tongTien = tongTien;
        this.maGiamGia = maGiamGia;
        this.maNguoiDung = maNguoiDung;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.maQr = maQr;
    }

    public String getMaThanhToan() {
        return maThanhToan;
    }

    public int getSoLuongVe() {
        return soLuongVe;
    }

    public float getTongTien() {
        return tongTien;
    }

    public String getMaGiamGia() {
        return maGiamGia;
    }

    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }
    
    public String getMaQr() {
		return maQr;
	}

	
    public void setMaThanhToan(String maThanhToan) {
        this.maThanhToan = maThanhToan;
    }

    public void setSoLuongVe(int soLuongVe) {
        this.soLuongVe = soLuongVe;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public void setMaGiamGia(String maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public void setMaQr(String maQr) {
        this.maQr = maQr;
    }
       
}

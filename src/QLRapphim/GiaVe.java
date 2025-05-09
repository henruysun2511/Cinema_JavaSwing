package QLRapphim;

public class GiaVe {
		private String maGiaVe;
		private String loaiGhe;
	    private String thoiGianTrongNgay;
	    private String ngayTrongTuan;
	    private float giaVe;
	    
	    public GiaVe() {
	    	this.maGiaVe="";
	    	this.loaiGhe="";
	    	this.thoiGianTrongNgay="";
	    	this.ngayTrongTuan="";
	    	this.giaVe=0;
	    	
	    }

	    public GiaVe(String maGiaVe,String loaiGhe, String thoiGianTrongNgay, String ngayTrongTuan, float giaVe) {
	        this.maGiaVe = maGiaVe;
	    	this.loaiGhe = loaiGhe;
	        this.thoiGianTrongNgay = thoiGianTrongNgay;
	        this.ngayTrongTuan = ngayTrongTuan;
	        this.giaVe = giaVe;
	    }
	    
	    public String getMaGiaVe() {
	    	return maGiaVe;
	    }

	    public String getLoaiGhe() {
	        return loaiGhe;
	    }

	    public String getThoiGianTrongNgay() {
	        return thoiGianTrongNgay;
	    }

	    public String getNgayTrongTuan() {
	        return ngayTrongTuan;
	    }

	    public float getGiaVe() {
	        return giaVe;
	    }
	    
	    public void setMaGiaVe(String maGiaVe) {
	    	this.maGiaVe = maGiaVe;
	    }
	    
	    public void setLoaiGhe(String loaiGhe) {
	        this.loaiGhe = loaiGhe;
	    }

	    public void setThoiGianTrongNgay(String thoiGianTrongNgay) {
	        this.thoiGianTrongNgay = thoiGianTrongNgay;
	   
	    public void setNgayTrongTuan(String ngayTrongTuan) {
	        this.ngayTrongTuan = ngayTrongTuan;
	    }

	    public void setGiaVe(float giaVe) {
	        this.giaVe = giaVe;
	    }

	}



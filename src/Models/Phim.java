package Models;


public class Phim {
    private String maPhim;
    private String tenPhim;
    private String ngayPhatHanh;
    private String daoDien;
    private int thoiLuong;
    private String noiDung;
    private String doTuoiChoPhep;
    private String anhPhim;
    private int trangThai;
    
    public Phim() {
    	this.maPhim="";
    	this.tenPhim="";
    	this.ngayPhatHanh= null;
    	this.daoDien="";
    	this.thoiLuong=0;
    	this.noiDung="";
    	this.doTuoiChoPhep="";
    	this.anhPhim="";
    	this.trangThai=0;
    	
    }

    public Phim(String maPhim, String tenPhim, String ngayPhatHanh, String daoDien, int thoiLuong, String noiDung, String doTuoiChoPhep, String anhPhim, int trangThai) {
        this.maPhim = maPhim;
        this.tenPhim = tenPhim;
        this.ngayPhatHanh = ngayPhatHanh;
        this.daoDien = daoDien;
        this.thoiLuong = thoiLuong;
        this.noiDung = noiDung;
        this.doTuoiChoPhep = doTuoiChoPhep;
        this.anhPhim = anhPhim;
        this.trangThai = trangThai;
    }

    public String getMaPhim() {
        return maPhim;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public String getNgayPhatHanh() {
        return ngayPhatHanh;
    }

    public String getDaoDien() {
        return daoDien;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public String getNoiDung() {
        return noiDung;
    }
    
    public String getDoTuoiChoPhep() {
		return doTuoiChoPhep;
	}

	public String getAnhPhim() {
        return anhPhim;
    }
    
    public int getTrangThai() {
        return trangThai;
    }
    

    public void setMaPhim(String maPhim) {
        this.maPhim = maPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public void setNgayPhatHanh(String ngayPhatHanh) {
        this.ngayPhatHanh = ngayPhatHanh;
    }

    public void setDaoDien(String daoDien) {
        this.daoDien = daoDien;
    }

    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public void setAnhPhim(String anhPhim) {
        this.anhPhim = anhPhim;
    }
    
    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    public void setDoTuoiChoPhep(String doTuoiChoPhep) {
		this.doTuoiChoPhep = doTuoiChoPhep;
	}
}





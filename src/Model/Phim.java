package Model;


public class Phim {
    private String maPhim;
    private String tenPhim;
    private String ngayPhatHanh;
    private String daoDien;
    private String thoiLuong;
    private String noiDung;
    private String anhPhim;

    public Phim(String maPhim, String tenPhim, String ngayPhatHanh, String daoDien, String thoiLuong, String noiDung, String anhPhim) {
        this.maPhim = maPhim;
        this.tenPhim = tenPhim;
        this.ngayPhatHanh = ngayPhatHanh;
        this.daoDien = daoDien;
        this.thoiLuong = thoiLuong;
        this.noiDung = noiDung;
        this.anhPhim = anhPhim;
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

    public String getThoiLuong() {
        return thoiLuong;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public String getAnhPhim() {
        return anhPhim;
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

    public void setThoiLuong(String thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public void setAnhPhim(String anhPhim) {
        this.anhPhim = anhPhim;
    }
    
}





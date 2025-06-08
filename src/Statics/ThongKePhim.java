package Statics;

public class ThongKePhim {
    private String tenPhim;
    private int soLuongVe;
    private double tongDoanhThu;

    public ThongKePhim(String tenPhim, int soLuongVe, double tongDoanhThu) {
        this.tenPhim = tenPhim;
        this.soLuongVe = soLuongVe;
        this.tongDoanhThu = tongDoanhThu;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public int getSoLuongVe() {
        return soLuongVe;
    }

    public double getTongDoanhThu() {
        return tongDoanhThu;
    }
}
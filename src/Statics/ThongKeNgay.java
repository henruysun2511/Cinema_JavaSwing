package Statics;

public class ThongKeNgay {
    private String ngayGiaoDich;
    private int soLuongVe;
    private double tongDoanhThu;

    public ThongKeNgay(String ngayGiaoDich, int soLuongVe, double tongDoanhThu) {
        this.ngayGiaoDich = ngayGiaoDich;
        this.soLuongVe = soLuongVe;
        this.tongDoanhThu = tongDoanhThu;
    }

    public String getNgayGiaoDich() {
        return ngayGiaoDich;
    }

    public int getSoLuongVe() {
        return soLuongVe;
    }

    public double getTongDoanhThu() {
        return tongDoanhThu;
    }
}
package Models;

public class TinhTrangGhe {
    private String maTinhTrangGhe;
    private String moTa;

    public TinhTrangGhe() {
        this.maTinhTrangGhe = "";
        this.moTa = "";
    }

    public TinhTrangGhe(String maTinhTrangGhe, String moTa) {
        this.maTinhTrangGhe = maTinhTrangGhe;
        this.moTa = moTa;
    }

    public String getMaTinhTrangGhe() {
        return maTinhTrangGhe;
    }

    public void setMaTinhTrangGhe(String maTinhTrangGhe) {
        this.maTinhTrangGhe = maTinhTrangGhe;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
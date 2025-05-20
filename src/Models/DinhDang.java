package Models;

public class DinhDang {
    private String maDinhDang;
    private String tenDinhDang;

    public DinhDang() {
        this.maDinhDang = "";
        this.tenDinhDang = "";
    }

    public DinhDang(String maDinhDang, String tenDinhDang) {
        this.maDinhDang = maDinhDang;
        this.tenDinhDang = tenDinhDang;
    }

    public String getMaDinhDang() {
        return maDinhDang;
    }

    public void setMaDinhDang(String maDinhDang) {
        this.maDinhDang = maDinhDang;
    }

    public String getTenDinhDang() {
        return tenDinhDang;
    }

    public void setTenDinhDang(String tenDinhDang) {
        this.tenDinhDang = tenDinhDang;
    }
}

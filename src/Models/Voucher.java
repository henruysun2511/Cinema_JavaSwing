package Models;
import java.sql.Date;

public class Voucher {
	private String maVoucher;
	private String tenVoucher;
	private float phanTramGiam;
	private Date ngayBatDau;
	private Date ngayKetThuc;
	private String moTa;
	
	public Voucher() {
		this.maVoucher="";
		this.tenVoucher="";
		this.phanTramGiam = 0;
		this.ngayBatDau = null;
		this.ngayKetThuc = null;
		this.moTa ="";
		
	}
	
	public Voucher(String maVoucher, String tenVoucher, float phanTramGiam, Date ngayBatDau, Date ngayKetThuc,
			String moTa) {
		super();
		this.maVoucher = maVoucher;
		this.tenVoucher = tenVoucher;
		this.phanTramGiam = phanTramGiam;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.moTa = moTa;
	}


	public String getMaVoucher() {
		return maVoucher;
	}

	public void setMaVoucher(String maVoucher) {
		this.maVoucher = maVoucher;
	}

	public String getTenVoucher() {
		return tenVoucher;
	}

	public void setTenVoucher(String tenVoucher) {
		this.tenVoucher = tenVoucher;
	}

	public float getPhanTramGiam() {
		return phanTramGiam;
	}

	public void setPhanTramGiam(float phanTramGiam) {
		this.phanTramGiam = phanTramGiam;
	}

	public Date getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

}

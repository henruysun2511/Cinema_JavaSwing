package Models;

public class ListPhim_TheLoai {
	private String maPhim;
	private String maTheLoai;
	
	public ListPhim_TheLoai() {
		this.maPhim = "";
		this.maTheLoai = "";
	}
	public ListPhim_TheLoai(String maPhim, String maTheLoai){
		this.maPhim = maPhim;
		this.maTheLoai = maTheLoai;
	}
	public String getMaphim() {
		return maPhim;
	}
	public String getMaTheLoai() {
		return maTheLoai;
	}
	public void setMaPhim(String maPhim) {
		this.maPhim = maPhim;
		
	}
	public void setMaTheLoai(String maTheLoai) {
		this.maTheLoai = maTheLoai;
	}
		

}

package Utilzs;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import Models.*;
import Controllers.*;
import javax.swing.JOptionPane;
import java.util.List;

public class ExportPDF {
    public static void xuatVeRaPDF(Ve ve, LichChieu lc, Ghe ghe, String tenPhim, String tenPhong) {
        Document document = new Document();
        try {
            String tenFile = "Ve_" + ve.getMaVe() + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(tenFile));
            document.open();

            // Tiêu đề
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("VÉ XEM PHIM", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" ")); // dòng trống

            Font normalFont = new Font(Font.FontFamily.HELVETICA, 12);

            document.add(new Paragraph("Mã vé: " + ve.getMaVe(), normalFont));
            document.add(new Paragraph("Phim: " + tenPhim, normalFont));
            document.add(new Paragraph("Phòng chiếu: " + tenPhong, normalFont));
            document.add(new Paragraph("Ghế: " + ghe.getTenGhe(), normalFont));
            document.add(new Paragraph("Suất chiếu: " + lc.getKhungGioChieuString(), normalFont));
            document.add(new Paragraph("Ngày chiếu: " + lc.getNgayChieu(), normalFont));

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Chúc bạn xem phim vui vẻ!", normalFont));

            document.close();

            javax.swing.JOptionPane.showMessageDialog(null, "Xuất vé thành công: " + tenFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void xuatHoaDonRaPDF(HoaDonVe hdv, List<Ve> danhSachVe, String tenPhim, String tenPhong, String suatChieu, String ngayChieu, Voucher vc) {
        Document document = new Document();
        try {
            String tenFile = "HoaDon_" + hdv.getMaThanhToan() + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(tenFile));
            document.open();

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("HÓA ĐƠN THANH TOÁN", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 12);

            document.add(new Paragraph("Mã hóa đơn: " + hdv.getMaThanhToan(), normalFont));
            document.add(new Paragraph("Thời gian giao dịch: " + hdv.getLichSuGiaoDich(), normalFont));
            document.add(new Paragraph("Khách hàng: " + hdv.getMaNguoiDung(), normalFont));

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Phim: " + tenPhim, normalFont));
            document.add(new Paragraph("Phòng chiếu: " + tenPhong, normalFont));
            document.add(new Paragraph("Suất chiếu: " + suatChieu, normalFont));
            document.add(new Paragraph("Ngày chiếu: " + ngayChieu, normalFont));

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Danh sách vé:", normalFont));
            for (Ve ve : danhSachVe) {
                Ghe ghe = GheController.layGheTheoMaGhe(ve.getMaGhe());
                LoaiGhe loaiGhe = GheController.layLoaiGheTheoMa(ghe.getLoaiGhe());
                document.add(new Paragraph(" - Ghế " + ghe.getTenGhe() + " (" + loaiGhe.getLoaiGhe() + "): " + String.format("%,.0f", loaiGhe.getGiaGhe()) + " VNĐ", normalFont));
            }

            document.add(new Paragraph(" "));

            if (vc != null) {
                document.add(new Paragraph("Khuyến mãi: " + vc.getMoTa() + " (" + vc.getTenVoucher() + ")", normalFont));
            }

            document.add(new Paragraph("Tổng tiền: " + String.format("%,.0f", hdv.getTongThanhToan()) + " VNĐ", normalFont));
            int tongTienInt = (int) hdv.getTongThanhToan();
            document.add(new Paragraph("Bằng chữ: " + NumberToVietnameseWords.convertNumberToVietnameseWords(tongTienInt), normalFont));

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Cảm ơn quý khách!", normalFont));

            document.close();
            JOptionPane.showMessageDialog(null, "Xuất hóa đơn thành công: " + tenFile);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xuất hóa đơn: " + e.getMessage());
        }
    }
}

package Utilzs;

import Controllers.*;

import Daos.VeDao;
import Models.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;

public class ExportExcel {
	public static void xuatVeExcel(String maVe) {
        Ve ve = VeController.layVeTheoMaVe(maVe);
        Ghe ghe = GheController.layGheTheoMaGhe(ve.getMaGhe());
        LichChieu lc = LichChieuController.layLichChieuTheoMaLichChieu(ve.getMaSuatChieu());
        Phim phim = PhimController.layPhimTheoMaPhim(lc.getMaPhim());
        PhongChieu phong = PhongChieuController.layPhongChieuTheoMaPhongChieu(lc.getMaPhong());

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Vé");

            sheet.createRow(0).createCell(0).setCellValue("Mã vé");
            sheet.getRow(0).createCell(1).setCellValue(ve.getMaVe());

            sheet.createRow(1).createCell(0).setCellValue("Phim");
            sheet.getRow(1).createCell(1).setCellValue(phim.getTenPhim());

            sheet.createRow(2).createCell(0).setCellValue("Phòng");
            sheet.getRow(2).createCell(1).setCellValue(phong.getTenPhong());

            sheet.createRow(3).createCell(0).setCellValue("Ghế");
            sheet.getRow(3).createCell(1).setCellValue(ghe.getTenGhe());

            sheet.createRow(4).createCell(0).setCellValue("Suất chiếu");
            sheet.getRow(4).createCell(1).setCellValue(lc.getKhungGioChieuString());

            sheet.createRow(5).createCell(0).setCellValue("Ngày chiếu");
            sheet.getRow(5).createCell(1).setCellValue(lc.getNgayChieu().toString());

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setSelectedFile(new File("Ve_" + maVe + ".xlsx"));
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                try (FileOutputStream out = new FileOutputStream(fileChooser.getSelectedFile())) {
                    workbook.write(out);
                    JOptionPane.showMessageDialog(null, "Xuất vé thành công!");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xuất vé!");
        }
    }
	
	 public static void xuatHoaDonExcel(String maHoaDonVe, String filePath) {
	        List<Ve> danhSachVe = VeDao.layDanhSachVeTheoMaHoaDon(maHoaDonVe);
	        HoaDonVe hdv = VeController.layHoaDonVeTheoMaHoaDonVe(maHoaDonVe);

	        try (Workbook workbook = new XSSFWorkbook()) {
	            Sheet sheet = workbook.createSheet("Hóa đơn");

	            int rowNum = 0;

	            // Tiêu đề
	            Row titleRow = sheet.createRow(rowNum++);
	            Cell titleCell = titleRow.createCell(0);
	            titleCell.setCellValue("HÓA ĐƠN THANH TOÁN");

	            CellStyle boldStyle = workbook.createCellStyle();
	            Font boldFont = workbook.createFont();
	            boldFont.setBold(true);
	            boldFont.setFontHeightInPoints((short) 14);
	            boldStyle.setFont(boldFont);
	            titleCell.setCellStyle(boldStyle);

	            // Giao dịch
	            sheet.createRow(rowNum++).createCell(0).setCellValue("Thời gian giao dịch: " + hdv.getLichSuGiaoDich());
	            sheet.createRow(rowNum++).createCell(0).setCellValue("Khách hàng: " + hdv.getMaNguoiDung());

	            rowNum++; // dòng trống

	            // Thông tin phim
	            if (!danhSachVe.isEmpty()) {
	                Ve veDauTien = danhSachVe.get(0);
	                LichChieu lc = LichChieuController.layLichChieuTheoMaLichChieu(veDauTien.getMaSuatChieu());
	                PhongChieu ph = PhongChieuController.layPhongChieuTheoMaPhongChieu(lc.getMaPhong());
	                DinhDang dd = PhongChieuController.layDinhDangTheoMaDinhDang(ph.getMaDinhDang());
	                Phim p = PhimController.layPhimTheoMaPhim(lc.getMaPhim());

	                sheet.createRow(rowNum++).createCell(0).setCellValue("Phim: " + p.getTenPhim());
	                sheet.createRow(rowNum++).createCell(0).setCellValue(p.getThoiLuong() + " phút - " + p.getDoTuoiChoPhep());
	                sheet.createRow(rowNum++).createCell(0).setCellValue("Phòng chiếu: " + ph.getTenPhong() + " (" + dd.getTenDinhDang() + ")");
	                sheet.createRow(rowNum++).createCell(0).setCellValue("Suất chiếu: " + lc.getKhungGioChieuString() + " - Ngày: " + lc.getNgayChieu());
	            }

	            rowNum++;

	            // Danh sách ghế
	            sheet.createRow(rowNum++).createCell(0).setCellValue("Danh sách ghế:");
	            for (Ve ve : danhSachVe) {
	                Ghe ghe = GheController.layGheTheoMaGhe(ve.getMaGhe());
	                LoaiGhe loaiGhe = GheController.layLoaiGheTheoMa(ghe.getLoaiGhe());

	                String line = " - Ghế " + ghe.getTenGhe() + " (" + loaiGhe.getLoaiGhe() + "): " + loaiGhe.getGiaGhe() + " VNĐ";
	                sheet.createRow(rowNum++).createCell(0).setCellValue(line);
	            }

	            rowNum++;

	            // Khuyến mãi
	            Voucher vc = VoucherController.layVoucherTheoMaVoucher(hdv.getMaVoucher());
	            if (vc != null) {
	                sheet.createRow(rowNum++).createCell(0).setCellValue("Khuyến mãi: " + vc.getMoTa() + " (" + vc.getTenVoucher() + ")");
	            }

	            rowNum++;

	            // Tổng tiền
	            sheet.createRow(rowNum++).createCell(0).setCellValue("Tổng tiền: " + String.format("%,.0f", hdv.getTongThanhToan()) + " VNĐ");

	            int tongTienInt = (int) hdv.getTongThanhToan();
	            sheet.createRow(rowNum++).createCell(0).setCellValue("Bằng chữ: " + NumberToVietnameseWords.convertNumberToVietnameseWords(tongTienInt));

	            // Auto size
	            sheet.autoSizeColumn(0);

	            // Ghi file
	            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
	                workbook.write(fileOut);
	                System.out.println("Xuất Excel thành công tại: " + filePath);
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}

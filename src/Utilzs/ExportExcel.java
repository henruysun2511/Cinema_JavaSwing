//package Utilzs;
//
//import Controllers.*;
//import Daos.VeDao;
//import Models.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//
//import javax.swing.*;
//import java.io.File;
//import java.io.FileOutputStream;
//
//public class ExportExcel {
//	public static void xuatVeExcel(String maVe) {
//        Ve ve = VeController.layVeTheoMaVe(maVe);
//        Ghe ghe = GheController.layGheTheoMaGhe(ve.getMaGhe());
//        LichChieu lc = LichChieuController.layLichChieuTheoMaLichChieu(ve.getMaSuatChieu());
//        Phim phim = PhimController.layPhimTheoMaPhim(lc.getMaPhim());
//        PhongChieu phong = PhongChieuController.layPhongChieuTheoMaPhongChieu(lc.getMaPhong());
//
//        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
//            XSSFSheet sheet = workbook.createSheet("Vé");
//
//            sheet.createRow(0).createCell(0).setCellValue("Mã vé");
//            sheet.getRow(0).createCell(1).setCellValue(ve.getMaVe());
//
//            sheet.createRow(1).createCell(0).setCellValue("Phim");
//            sheet.getRow(1).createCell(1).setCellValue(phim.getTenPhim());
//
//            sheet.createRow(2).createCell(0).setCellValue("Phòng");
//            sheet.getRow(2).createCell(1).setCellValue(phong.getTenPhong());
//
//            sheet.createRow(3).createCell(0).setCellValue("Ghế");
//            sheet.getRow(3).createCell(1).setCellValue(ghe.getTenGhe());
//
//            sheet.createRow(4).createCell(0).setCellValue("Suất chiếu");
//            sheet.getRow(4).createCell(1).setCellValue(lc.getKhungGioChieuString());
//
//            sheet.createRow(5).createCell(0).setCellValue("Ngày chiếu");
//            sheet.getRow(5).createCell(1).setCellValue(lc.getNgayChieu().toString());
//
//            JFileChooser fileChooser = new JFileChooser();
//            fileChooser.setSelectedFile(new File("Ve_" + maVe + ".xlsx"));
//            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
//                try (FileOutputStream out = new FileOutputStream(fileChooser.getSelectedFile())) {
//                    workbook.write(out);
//                    JOptionPane.showMessageDialog(null, "Xuất vé thành công!");
//                }
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Lỗi khi xuất vé!");
//        }
//    }
//}

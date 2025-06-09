package Utilzs;

import java.awt.Component;


import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import Models.*;
import Controllers.*;

public class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean isPushed;
    private JTable table;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton("Xuất vé");
        button.setOpaque(true);
        button.addActionListener(e -> fireEditingStopped());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        this.table = table;
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String maVe = table.getValueAt(selectedRow, 0).toString();
                Ve ve = VeController.layVeTheoMaVe(maVe); 
                LichChieu lc = LichChieuController.layLichChieuTheoMaLichChieu(ve.getMaSuatChieu());
                Ghe ghe =  GheController.layGheTheoMaGhe(ve.getMaGhe());
                Phim phim = PhimController.layPhimTheoMaPhim(lc.getMaPhim());
                String tenPhim = phim.getTenPhim();
                PhongChieu phongChieu = PhongChieuController.layPhongChieuTheoMaPhongChieu(lc.getMaPhong());
                String tenPhong = String.valueOf(phongChieu.getTenPhong()); 

                ExportPDF.xuatVeRaPDF(ve, lc, ghe, tenPhim, tenPhong);
            }
        }
        isPushed = false;
        return new String("Xuất vé");
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}

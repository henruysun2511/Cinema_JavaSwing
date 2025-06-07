package Controllers;
import java.util.ArrayList;

import Daos.*;
import Models.Voucher;

public class VoucherController {
    public static ArrayList<Voucher> layDanhSachVoucher(){
        return VoucherDao.getAllVouchers();
    }

    public static Voucher layVoucherTheoMaVoucher(String maVoucher){
        return VoucherDao.getVoucherById(maVoucher);
    }
    
}

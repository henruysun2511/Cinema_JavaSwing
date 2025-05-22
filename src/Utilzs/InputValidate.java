package Utilzs;
import javax.swing.*;
import java.util.regex.Pattern;
import Models.*;


public class InputValidate {
	public static boolean showTimeValidate(String showtime_id, String showtime, String room_id, String showtime_day) {
		if (showtime_id == null || showtime_id.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mã suất chiếu không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

		String regexTime = "^([01]\\d|2[0-3]):[0-5]\\d(:[0-5]\\d)?$";
		if (!Pattern.matches(regexTime, showtime)) {
            JOptionPane.showMessageDialog(null, "Suất chiếu phải đúng định dạng HH:mm hoặc HH:mm:ss (VD: 14:30 hoặc 14:30:00)", "Lỗi định dạng", JOptionPane.ERROR_MESSAGE);
            return false;
        }
		
        if (showtime == null) {
            JOptionPane.showMessageDialog(null, "Giờ chiếu không được để chống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (room_id == null || room_id.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Phòng chiếu không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!room_id.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Phòng chiếu phải là số nguyên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (showtime_day == null) {
            JOptionPane.showMessageDialog(null, "Ngày chiếu không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true; 
    }

}

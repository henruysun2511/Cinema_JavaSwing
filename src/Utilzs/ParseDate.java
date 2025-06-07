package Utilzs;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class ParseDate {
	public static java.sql.Date parseToDate(String dateStr) {
	    try {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date utilDate = sdf.parse(dateStr);
	        return new java.sql.Date(utilDate.getTime());
	    } catch (ParseException e) {
	        System.out.println("Sai định dạng ngày. Định dạng đúng là yyyy-MM-dd");
	        return null;
	    }
	}
	
	public static java.sql.Time parseToTime(String timeStr) {
	    try {
	        if (timeStr.matches("\\d{2}:\\d{2}")) {
	            timeStr += ":00";
	        }
	        return java.sql.Time.valueOf(timeStr);
	    } catch (IllegalArgumentException e) {
	        System.out.println("Sai định dạng giờ. Định dạng đúng là HH:mm hoặc HH:mm:ss");
	        return null;
	    }
	}

}

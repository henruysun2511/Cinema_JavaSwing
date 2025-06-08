package Utilzs;

public class NumberToVietnameseWords {

    private static final String[] ONES = {
            "", "một", "hai", "ba", "bốn", "năm", "sáu", "bảy", "tám", "chín"
    };

    private static final String[] TEENS = {
            "mười", "mười một", "mười hai", "mười ba", "mười bốn", "mười lăm", "mười sáu", "mười bảy", "mười tám", "mười chín"
    };

    private static final String[] TENS = {
            "", "", "hai mươi", "ba mươi", "bốn mươi", "năm mươi", "sáu mươi", "bảy mươi", "tám mươi", "chín mươi"
    };

    private static final String[] UNITS = {
            "", "ngàn", "triệu", "tỷ"
    };

    /**
     * Chuyển đổi một số có 3 chữ số thành chữ (ví dụ: 123 -> "một trăm hai mươi ba").
     *
     * @param number Số có 3 chữ số.
     * @param hasHundreds true nếu có hàng trăm (để đọc "không trăm..." nếu hàng trăm là 0 và không phải là số đầu tiên).
     * @return Chuỗi tiền chữ của số có 3 chữ số.
     */
    private static String convertThreeDigits(int number, boolean hasHundreds) {
        if (number == 0) {
            return hasHundreds ? "không" : ""; // Trả về "không" nếu là 0 và có hàng trăm, ngược lại trả về rỗng
        }

        StringBuilder sb = new StringBuilder();
        int hundreds = number / 100;
        int remainder = number % 100;

        if (hundreds > 0) {
            sb.append(ONES[hundreds]).append(" trăm");
        } else if (hasHundreds) {
            // Nếu hàng trăm là 0 nhưng có yêu cầu đọc "không trăm" (ví dụ: 089)
            sb.append("không trăm");
        }


        if (remainder > 0) {
            if (hundreds > 0) { // Nếu có hàng trăm, thêm khoảng trắng trước khi đọc hàng chục/đơn vị
                sb.append(" ");
            }

            if (remainder < 10) { // 0-9
                if (hundreds > 0 && remainder == 0) { // Ví dụ: 100
                    // Không làm gì nếu là số tròn trăm
                } else if (hundreds > 0 && remainder < 10 && remainder != 0) { // Ví dụ: 101, 105 (lẻ)
                    sb.append("lẻ ").append(ONES[remainder]);
                } else { // 0-9 và không có hàng trăm trước đó (ví dụ: 5, 9)
                    sb.append(ONES[remainder]);
                }
            } else if (remainder >= 10 && remainder < 20) { // 10-19
                sb.append(TEENS[remainder - 10]);
            } else { // 20-99
                int tens = remainder / 10;
                int ones = remainder % 10;
                sb.append(TENS[tens]);
                if (ones > 0) {
                    if (ones == 5 && tens >= 2) { // Ví dụ: 25 -> "hai mươi lăm"
                        sb.append(" lăm");
                    } else if (ones == 1 && tens >= 2) { // Ví dụ: 21 -> "hai mươi mốt"
                        sb.append(" mốt");
                    } else { // Các trường hợp còn lại
                        sb.append(" ").append(ONES[ones]);
                    }
                }
            }
        }
        return sb.toString().trim();
    }

    public static String convertNumberToVietnameseWords(long number) {
        if (number == 0) {
            return "không đồng";
        }

        StringBuilder sb = new StringBuilder();
        String sign = "";
        if (number < 0) {
            sign = "âm ";
            number = Math.abs(number);
        }

        long tempNumber = number;
        int unitIndex = 0; // đơn vị: 0 = "", 1 = "ngàn", 2 = "triệu", 3 = "tỷ"

        while (tempNumber > 0) {
            int threeDigits = (int) (tempNumber % 1000);
            String threeDigitsWords = "";

            if (threeDigits != 0 || (unitIndex == 0 && number > 0) || (unitIndex > 0 && tempNumber > 0)) {
                boolean hasHundreds = (unitIndex > 0 && (tempNumber / 1000) > 0) || (unitIndex == 0 && number >= 100);
                threeDigitsWords = convertThreeDigits(threeDigits, hasHundreds);

                if (!threeDigitsWords.isEmpty()) {
                    if (unitIndex > 0) {
                        sb.insert(0, " " + UNITS[unitIndex]);
                    }
                    sb.insert(0, " " + threeDigitsWords);
                }
            }
            tempNumber /= 1000;
            unitIndex++;
        }

        return sign + sb.toString().trim() + " đồng";
    }

}
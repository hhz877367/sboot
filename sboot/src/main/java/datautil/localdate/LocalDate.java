package datautil.localdate;

import java.time.format.DateTimeFormatter;

public class LocalDate {

    public static DateTimeFormatter formatyyyy_mm_dd = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static DateTimeFormatter formatyyyymmdd = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static DateTimeFormatter formatYYYY_MM_DD_HH_MM_SS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public static void main(String[] args) {

    }
}

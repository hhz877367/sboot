package com.baizhi.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    public static final String YYYY = "yyyy";

    public static final String YYYY_MM = "yyyy-MM";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String HH_MM_SS = "HH:mm:ss";
    private static String[] parsePatterns = { "yyyy-MM-dd",
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd " + "HH:mm", "yyyy-MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss",
            "yyyy/MM/dd "
                    + "HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss",
            "yyyy.MM.dd HH:mm",
            "yyyy" + ".MM" };

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow() {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format) {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date) {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format,
                                              final Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts) {
        try {
            return new SimpleDateFormat(format).parse(ts);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = (diff % nd) / nh;
        // 计算差多少分钟
        long min = (diff % nd % nh) / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 获取间隔时间，包含start,不包含end
     * */
    public static List<String> getMinuteBetweenTwoDate(Date start, Date end) {
        List<String> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();
        calendar.setTime(start);
        calendarEnd.setTime(end);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        while (calendar.before(calendarEnd)) {
            list.add(formatter.format(calendar.getTime()));
            calendar.add(Calendar.MINUTE, 1);
        }
        list.add(formatter.format(calendarEnd.getTime()));
        return list;
    }

    /**
     * 获取间隔时间，包含start,不包含end
     * */
    public static List<String> getMinuteBetweenTwoDate(Date start) {
        List<String> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Calendar calendarEnd = Calendar.getInstance();
        calendar.setTime(start);
        calendarEnd.setTime(new Date());
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        while (calendar.before(calendarEnd)) {
            list.add(formatter.format(calendar.getTime()));
            calendar.add(Calendar.MINUTE, 1);
        }
        return list;
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime 当前时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     * @author wyy
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime,
                                          Date endTime) {
        if ((nowTime.getTime() == startTime.getTime()) || (nowTime.getTime()
                == endTime.getTime())) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 给指定日期加1天
     *
     * @param date 时间
     * @return
     * @author wyy
     */
    public static Date plusDate(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, 1); //把日期往后增加一天,整数  往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        return date;
    }

    /**
     * 获取今天开始时间
     */
    public static Date getStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    /**
     * 获取今天结束时间
     */
    public static Date getEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    /**
     * 获取一个月的开始或者结束日期
     *
     * @param year      年
     * @param month     月
     * @param day       日
     */
    public static String getMonthDay(int year, int month, int day){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(YYYY_MM_DD);
        LocalDate localDate = LocalDate.of(year, month, day);
        int monthDay = 0;
        if(localDate.isLeapYear()){//闰年
            monthDay = localDate.getMonth().maxLength();
        }else {
            monthDay = localDate.getMonth().minLength();
        }

        LocalDate newDate = LocalDate.of(year, month, monthDay);

        return newDate.format(dtf);
    }

    /**
     * 按天数增加，返回训练的开始，结束等信息
     *
     * @param day   增加的天数
     *
     * @return  增加后的天数
     */
    public static LocalDateTime getAddDay(int day, LocalDateTime dateTime){
        if(dateTime == null){
            dateTime = LocalDateTime.now();
        }
        LocalDateTime localDate = LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(), 0 ,0 ,0);

        return localDate.plusDays(day);
    }

}
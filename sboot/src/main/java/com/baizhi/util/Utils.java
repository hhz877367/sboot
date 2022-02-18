package com.baizhi.util;

import com.github.pagehelper.util.StringUtil;
import com.google.common.collect.Maps;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

/** @author OWolfe */
public class Utils {
  private Utils() {
    // 私有构造
  }

  public static final int MINUTE = 60;
  public static final int HOUR = MINUTE * 60;
  private static final Random rand = new Random();

  public static String uuid() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  public static int random(int min, int max) {
    return (rand.nextInt(max) % ((max - min) + 1)) + min;
  }

  /**
   * 根据手机号生成用户名
   *
   * @param phone 手机号
   * @return 用户名
   */
  public static String generateNameByPhone(String phone) {
    return phone.substring(0, 3) + "****" + phone.substring(7);
  }

  /**
   * 生成短信验证码
   *
   * @return 短信验证码
   */
  public static String generateSmsCode() {
    return String.valueOf(rand.nextInt(9000) + 1000);
  }

  public static Class<?> getSuperClassGenericType(final Class<?> clazz, final int index) {
    // 返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
    Type genType = clazz.getGenericSuperclass();
    if (!(genType instanceof ParameterizedType)) {
      return Object.class;
    }
    // 返回表示此类型实际类型参数的 Type 对象的数组。
    Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
    if ((index >= params.length) || (index < 0)) {
      return Object.class;
    }
    if (!(params[index] instanceof Class)) {
      return Object.class;
    }
    return (Class<?>) params[index];
  }

  /**
   * 生成md5
   *
   * @param str 要加密的内容
   * @return 加密后内容
   */
  public static String md5(String str) {
    return DigestUtils.md5DigestAsHex(str.getBytes());
  }

  /**
   * bean转map
   *
   * @param bean 原始bean
   * @param <T> 泛型
   * @return map
   */
  public static <T> Map<String, Object> beanToMap(T bean) {
    Map<String, Object> map = Maps.newHashMap();
    if (bean != null) {
      BeanMap beanMap = BeanMap.create(bean);
      for (Object key : beanMap.keySet()) {
        map.put(key + "", beanMap.get(key));
      }
    }

    return map;
  }

  /**
   * 将map装换为javabean对象
   *
   * @param map 原始map
   * @param bean 实例化空bean
   * @param <T> 泛型
   * @return 填充后的bean
   */
  public static <T> T mapToBean(Map<String, Object> map, T bean) {
    BeanMap beanMap = BeanMap.create(bean);
    beanMap.putAll(map);
    return bean;
  }

  /**
   * 排序签名
   *
   * @param o 要签名的对象
   * @param <T> 泛型
   * @param secret 秘钥
   * @return 加密后字符串
   */
  public static <T> String signWithSort(T o, String secret) {
    Map<String, Object> map = beanToMap(o);
    String signStr =
        map.keySet().stream()
            .sorted()
            .map(
                key -> {
                  Object value = map.get(key);
                  if (value == null) {
                    return null;
                  }
                  return key.concat("=").concat(value + "");
                })
            .filter(Objects::nonNull)
            .collect(Collectors.joining("&"));
    return md5(signStr + secret);
  }

  /**
   * 获取文件名后缀，携带小数点
   *
   * @param fileName 文件名称
   * @return 后缀名称
   */
  public static String fileSuffixWithPoint(String fileName) {
    if (StringUtils.isEmpty(fileName)) {
      return "";
    }
    int suffixIndex = fileName.lastIndexOf('.');
    if (suffixIndex > -1) {
      return fileName.substring(suffixIndex);
    }
    return "";
  }

  /**
   * 获取文件名后缀，不携带小数点
   *
   * @param fileName 文件名称
   * @return 后缀名称
   */
  public static String fileSuffix(String fileName) {
    if (StringUtils.isEmpty(fileName)) {
      return "";
    }
    int suffixIndex = fileName.lastIndexOf('.');
    if (suffixIndex > -1) {
      return fileName.substring(suffixIndex + 1);
    }
    return "";
  }

  /**
   * 安全返回数组某个下标的元素
   *
   * @param array 数组
   * @param index 下标
   * @param <T> 泛型
   * @return 某个元素
   */
  public static <T> T safeElement(T[] array, int index) {
    if (array != null) {
      return (array.length - 1) < index ? null : array[index];
    }

    return null;
  }

  /**
   * 判断方法中是否包含某个类型的参数
   *
   * @param method 方法
   * @param paramClass 参数class
   * @return true/false
   */
  public static boolean checkMethodHasParamClass(Method method, Class<?> paramClass) {
    Type[] types = method.getGenericParameterTypes();
    if ((types != null) && (types.length > 0)) {
      for (Type type : types) {
        if (type instanceof ParameterizedType) {
          ParameterizedType parameterizedType = (ParameterizedType) type;
          if (parameterizedType.getRawType().getTypeName().equals(paramClass.getName())) {
            return true;
          }
        } else {
          if (type.getTypeName().equals(paramClass.getName())) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * 通过反射获取方法某个参数的泛型
   *
   * @param method 方法
   * @param paramClass 参数class
   * @return 泛型
   */
  public static Type[] reflectMethodParameterTypes(Method method, Class<?> paramClass) {
    if (!checkMethodHasParamClass(method, paramClass)) {
      return new Type[0];
    }

    Type[] types = method.getGenericParameterTypes();
    if ((types != null) && (types.length > 0)) {
      for (Type type : types) {
        if (type instanceof ParameterizedType) {
          ParameterizedType parameterizedType = (ParameterizedType) type;
          if (parameterizedType.getRawType().getTypeName().equals(paramClass.getName())) {
            return parameterizedType.getActualTypeArguments();
          }
        }
      }
    }
    return new Type[] {Object.class};
  }

  /**
   * 通过反射获取方法的返回参数的泛型
   *
   * @param method 方法
   * @return 泛型
   */
  public static Type[] reflectMethodReturnTypes(Method method) {
    Type type = method.getGenericReturnType();
    if (type instanceof ParameterizedType) {
      return ((ParameterizedType) type).getActualTypeArguments();
    }
    return new Type[] {Object.class};
  }

  /** 由过去的某一时间,计算距离当前的时间 */
  public static String getTimeDescByString(String time) {
    long nowTime = System.currentTimeMillis(); // 获取当前时间的毫秒数
    String msg = null;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 指定时间格式
    Date setTime = null; // 指定时间
    try {
      setTime = sdf.parse(time); // 将字符串转换为指定的时间格式
    } catch (ParseException e) {

      e.printStackTrace();
    }

    long reset = setTime.getTime(); // 获取指定时间的毫秒数
    long dateDiff = nowTime - reset;

    if (dateDiff < 0) {
      msg = "输入的时间不对";
    } else {

      long dateTemp1 = dateDiff / 1000; // 秒
      long dateTemp2 = dateTemp1 / 60; // 分钟
      long dateTemp3 = dateTemp2 / 60; // 小时
      long dateTemp4 = dateTemp3 / 24; // 天数
      long dateTemp5 = dateTemp4 / 30; // 月数
      long dateTemp6 = dateTemp5 / 12; // 年数

      if (dateTemp6 > 0) {
        msg = dateTemp6 + "年前";

      } else if (dateTemp5 > 0) {
        msg = dateTemp5 + "个月前";

      } else if (dateTemp4 > 0) {
        msg = dateTemp4 + "天前";

      } else if (dateTemp3 > 0) {
        msg = dateTemp3 + "小时前";

      } else if (dateTemp2 > 0) {
        msg = dateTemp2 + "分钟前";

      } else if (dateTemp1 > 0) {
        msg = "刚刚";
      }
    }
    return msg;
  }

  /** str 格式 计算两个时间字符串(yyyy-MM-dd HH:mm:ss)的时间差，返回 毫秒数 */
  public static long getTimeDiffByStr(String dateTime1, String dateTime2, String str) {
    SimpleDateFormat sdf = new SimpleDateFormat(str); // 指定时间格式
    Date setTime1 = null;
    Date setTime2 = null;
    try {
      setTime1 = sdf.parse(dateTime1); // 将字符串转换为指定的时间格式
      setTime2 = sdf.parse(dateTime2); // 将字符串转换为指定的时间格式
    } catch (ParseException e) {
      e.printStackTrace();
    }
    long reset1 = setTime1.getTime();
    long reset2 = setTime2.getTime();
    long dateDiff = 0;
    if (reset1 > reset2) {
      dateDiff = reset1 - reset2;
    } else {
      dateDiff = reset2 - reset1;
    }
    return dateDiff;
  }

  /** str 格式 计算两个时间字符串(yyyy-MM-dd HH:mm:ss)的时间差，返回 月份 */
  public static Integer getMothTimeDiffByStr(String dateTime1, String dateTime2, String str) {
    SimpleDateFormat sdf = new SimpleDateFormat(str); // 指定时间格式
    Date setTime1 = null;
    Date setTime2 = null;
    try {
      setTime1 = sdf.parse(dateTime1); // 将字符串转换为指定的时间格式
      setTime2 = sdf.parse(dateTime2); // 将字符串转换为指定的时间格式
    } catch (ParseException e) {
      e.printStackTrace();
    }
    long reset1 = setTime1.getTime();
    long reset2 = setTime2.getTime();
    long dateDiff = 0;
    if (reset1 > reset2) {
      dateDiff = reset1 - reset2;
    } else {
      dateDiff = reset2 - reset1;
    }
    long dateTemp1 = dateDiff / 1000; // 秒
    long dateTemp2 = dateTemp1 / 60; // 分钟
    long dateTemp3 = dateTemp2 / 60; // 小时
    long dateTemp4 = dateTemp3 / 24; // 天数
    long dateTemp5 = dateTemp4 / 30; // 月数
    DecimalFormat df = new DecimalFormat("####0");
    String mothRoud = df.format(dateTemp5);
    Integer result = Integer.parseInt(mothRoud);
    return result;
  }

  /** 计算两个时间Date的时间差，返回 毫秒数 */
  public static long getTimeDiffByDate(Date dateTime1, Date dateTime2) {
    long reset1 = dateTime1.getTime();
    long reset2 = dateTime2.getTime();
    long dateDiff = 0;
    if (reset1 > reset2) {
      dateDiff = reset1 - reset2;
    } else {
      dateDiff = reset2 - reset1;
    }
    return dateDiff;
  }

  /** 根据时间搓计算小时 */
  public static String getHourByTimeDiff(long dateDiff) {
    long dateTemp1 = dateDiff / 1000; // 秒
    long dateTemp2 = dateTemp1 / 60; // 分钟
    long dateTemp3 = dateTemp2 / 60; // 小时
    return dateTemp3 + "";
  }

  /** 转换日期类型 */
  public static String getStringDateByForm(Date date) {
    if (date == null) {
      return "日期为空";
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String string = sdf.format(date);
    return string;
  }

  /** 转换日期类型 */
  public static String getStringDateByFormDefin(Date date, String fomat) {

    if (date == null) {
      return "日期为空";
    }
    SimpleDateFormat sdf = new SimpleDateFormat(fomat);
    String string = sdf.format(date);
    return string;
  }

  /** 转换日期类型 String date String str */
  public static Date getDateByStringYYYYMMDD(String date, String str) {

    if (date == null) {
      return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat(str);
    Date parse = null;
    try {
      parse = sdf.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return parse;
  }

  /**
   * 根据时间戳返回周数
   *
   * @param startTime 毫秒级时间戳
   * @param yearModel 如 GMT+8:00
   * @return
   */
  public static Long getYearStartTime(Long startTime, Long yearModel) {
    long timeDifference1 = startTime - yearModel;
    long day = timeDifference1 / 1000 / 60 / 60 / 24;
    Long startWeeek = 1L;
    if (day % 7 == 0) {
      startWeeek = day / 7;
    } else {
      startWeeek = day / 7;
      startWeeek = startWeeek + 1;
    }
    return startWeeek;
  }

  // 根据时间戳计算周数,纬度只考虑一年内，跨两年不能用
  public static List<String> getWeekByTime(String startDateStr, String endDateStr) {

    if (StringUtil.isEmpty(startDateStr) || StringUtil.isEmpty(endDateStr)) {
      return null;
    }
    long startTime = getDateByStringYYYYMMDD(startDateStr, "yyyy-MM-dd").getTime(); // 获取毫秒数
    long endTime = getDateByStringYYYYMMDD(endDateStr, "yyyy-MM-dd").getTime();
    // 判断是否跨年
    String str1 = startDateStr.substring(0, 4);
    String str2 = endDateStr.substring(0, 4);

    // 上下一年的时间戳当不跨年，则yearModel和yearModelOlder相等
    long yearModel = getDateByStringYYYYMMDD(str1 + "-01-01", "yyyy-MM-dd").getTime();
    long yearModelOlder = getDateByStringYYYYMMDD(str2 + "-01-01", "yyyy-MM-dd").getTime();

    // 不跨年情况
    if (str1.equals(str2)) {
      Long startWeeek =
          getYearStartTime(
              startTime, getDateByStringYYYYMMDD(str1 + "-01-01", "yyyy-MM-dd").getTime());
      Long endweeek = getYearStartTime(endTime, yearModel);
      // 返回结果值
      List<String> listResult = new ArrayList<>();
      for (Long i = startWeeek; i <= endweeek; i++) {
        if (i == startWeeek) {
          listResult.add(str1 + "年第" + i + "周");
        } else {
          listResult.add("第" + i + "周");
        }
      }
      return listResult;
    } else {
      // 跨年情况
      Long startWeeek = getYearStartTime(startTime, yearModel);
      Long endweeek = getYearStartTime(endTime, yearModelOlder);
      ;
      // 返回结果值
      List<String> listResult = new ArrayList<>();
      for (Long i = startWeeek; i <= 52; i++) {
        if (i == startWeeek) {
          listResult.add(str1 + "年第" + i + "周");
        } else {
          listResult.add("第" + i + "周");
        }
      }
      for (Long i = 1L; i <= endweeek; i++) {
        if (i == 1L) {
          listResult.add(str2 + "年第" + i + "周");
        } else {
          listResult.add("第" + i + "周");
        }
      }
      return listResult;
    }
  }

  // 根据毫秒数，判定返回是按日，周，月，统计
  public static String convert(Date startDate, Date endDate) {
    long startTime = startDate.getTime(); // 获取毫秒数
    long endTime = endDate.getTime(); // 获取毫秒数
    long timeDifference = endTime - startTime;
    long second = timeDifference / 1000; // 计算秒

    if (second < 60) {
      return second + "秒前";
    } else {
      long minute = second / 60;
      if (minute < 60) {
        return minute + "分钟前";
      } else {
        long hour = minute / 60;
        if (hour < 24) {
          return "day";
        } else {
          long day = hour / 24;
          if (day <= 32) {
            return "day";
          } else if (day <= 93) {
            return "week";
          } else {
            return "month";
          }
        }
      }
    }
  }

  // 计算日期时间段包含几个月
  public static Integer getMonthCount(Date startDate, Date endDate) {
    long startTime = startDate.getTime(); // 获取毫秒数
    long endTime = endDate.getTime(); // 获取毫秒数
    long timeDifference = endTime - startTime;
    long day = timeDifference / 1000 / 60 / 60 / 24; // 计算秒
    int motnhCount = getIntByObject(new Double(day) / 30);
    return motnhCount;
  }

  // 计算日期时间段包含多少天数
  public static Integer getDayCount(Date startDate, Date endDate) {
    if (startDate == null || endDate == null) {
      return null;
    }
    long startTime = startDate.getTime(); // 获取毫秒数
    long endTime = endDate.getTime(); // 获取毫秒数
    long timeDifference = endTime - startTime;
    long h = timeDifference / 1000 / 60 / 60; // 计算秒
    int day = getIntByObject(new Double(h) / 24);
    return day;
  }

  public static String yesterDayStart() {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, -1);
    String yesterdayEnd = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(cal.getTime());
    return yesterdayEnd;
  }

  public static String yesterDayEnd() {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, -1);
    String yesterdayEnd = new SimpleDateFormat("yyyy-MM-dd 24:59:59").format(cal.getTime());
    return yesterdayEnd;
  }

  public static String newDayStart() {
    Calendar cal = Calendar.getInstance();
    String yesterdayEnd = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(cal.getTime());
    return yesterdayEnd;
  }

  public static String newDayEnd() {
    Calendar cal = Calendar.getInstance();
    String yesterdayEnd = new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(cal.getTime());
    return yesterdayEnd;
  }



  /**
   * （1）四舍五入把double转化int整型，0.5进一，小于0.5不进一
   *
   * @param number
   * @return
   */
  public static int getIntByObject(double number) {
    BigDecimal bd = new BigDecimal(number).setScale(0, BigDecimal.ROUND_HALF_UP);
    return Integer.parseInt(bd.toString());
  }
  // 四舍五入保留小数点1位
  public static Double getIntByObjectOne(double number) {
    BigDecimal bd = new BigDecimal(number).setScale(1, BigDecimal.ROUND_HALF_UP);
    return new Double(bd.toString());
  }

  // 四舍五入保留小数点2位
  public static Double getIntByObjectTow(double number) {
    BigDecimal bd = new BigDecimal(number).setScale(2, BigDecimal.ROUND_HALF_UP);
    return new Double(bd.toString());
  }

  // 四舍五入保留小数点5位
  public static Double getIntByObjectFive(double number) {
    BigDecimal bd = new BigDecimal(number).setScale(5, BigDecimal.ROUND_HALF_UP);
    return new Double(bd.toString());
  }

  // 四舍五入保留小数点6位
  public static Double getIntByObjectTen(double number) {
    DecimalFormat df=new DecimalFormat("#0.000000");
    String format = df.format(number);
    Double result = new Double(format);
    return  result;
  }

  /**
   * 舍去小数位
   *
   * @param number
   * @return
   */
  public static int getIntByObjectDown(double number) {
    BigDecimal bd = new BigDecimal(number).setScale(0, BigDecimal.ROUND_FLOOR);
    return Integer.parseInt(bd.toString());
  }

  // 把时间往前推一天

  public static String getStrTimeYesterDay(String date) {
    Date dateTime = getDateByStringYYYYMMDD(date, "yyyy-MM-dd");
    long time = dateTime.getTime();
    time = time - 1000 * 60 * 60 * 24;
    Date date1 = new Date(time);
    String yesterDay = getStringDateByFormDefin(date1, "yyyy-MM-dd");
    return yesterDay;
  }

  public static void main(String[] args) {
    Double intByObjectTow = getIntByObjectFive(new Double(100) / 3);
    System.out.println(intByObjectTow);
  }

  // 训练表中 自定义靶心率转换
  public static String getHearByList(String tarheat, Integer age) {
    String result = "";
    if (StringUtil.isEmpty(tarheat)) {
      return result;
    }

    if (tarheat.equals("warm_up")) {
      result = "[80,90]";
    } else if (tarheat.equals("aerobic_burn_fat")) {
      result = "[96,139]";
    } else if (tarheat.equals("aerobic_endurance")) {
      result = "[112,159]";
    } else if (tarheat.equals("anaerobic_endurance")) {
      result = "[128,179]";
    } else if (tarheat.equals("anaerobic_explosion")) {
      result = "[144,+∞]";
    } else {
      String[] split = tarheat.split(",");
      result = "[(220-年龄)*" + split[0] + ",(220-年龄)*" + split[1] + "]";
    }
    return result;
  }

  // 训练表中 自定义运动量转换
  // 0 低运动量 1中低运动量 2 中高运动量 3高运动量
  public static String getMotionSizeByDesc(String motionSize) {
    String result = "";
    if (StringUtil.isEmpty(motionSize)) {
      return result;
    }

    if (motionSize.equals("0")) {
      result = "低运动量";
    } else if (motionSize.equals("1")) {
      result = "中低运动量";
    } else if (motionSize.equals("2")) {
      result = "中高运动量";
    } else if (motionSize.equals("3")) {
      result = "高运动量";
    }
    return result;
  }

  // 分钟转换成秒 5分32秒 格式
  public static String getMinuteBysecond(Double second) {
    String result = "";
    if (second != null) {
      if (second > 1) {
        Integer minute = getIntByObjectDown(second);
        Double remainder = second % 1;
        Integer intRemainder = getIntByObject(remainder * 60);
        result = minute + "分" + intRemainder + "秒";
      } else {
        return second + "秒";
      }
    }
    return result;
  }

  public static boolean getFile(String filePath) {
    // 传入文件路径
    if (StringUtil.isNotEmpty(filePath)) {
      File f = new File(filePath); // 创建File对象
      System.out.println(f.exists() + "判断文件是否存在");
      if (f.exists()) { // 判断f 如果不存在,就创建
        System.out.println(f.exists() + "文件不存在");
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  //
  public static boolean getRource(String source) {
    try {
      URL url = new URL(source);
      URLConnection uc = url.openConnection();
      InputStream in = uc.getInputStream();
      if (source.equalsIgnoreCase(uc.getURL().toString())) in.close();
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}

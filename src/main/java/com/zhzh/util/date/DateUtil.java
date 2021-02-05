package com.zhzh.util.date;

import com.zhzh.constants.Constants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : zhang sq
 * @date : 2020/8/17 17:17
 **/
public class DateUtil {


    /**
     * 根据year年的第week周，查询本周的起止时间 data格式 202025   2020年第25周
     * @param data  202034
     * @param format Constants.MM月dd日
     * @return {monday=08月17日, tuesday=08月18日, wednesday=08月19日, thursday=08月20日, firday=08月21日, saturday=08月22日, sunday=08月23日}
     * @throws Exception ""
     */
    public static Map<String, String> weekToDayFormate(String data, String format) throws Exception {
        Integer year = Integer.valueOf(data.substring(0, 4));
        Integer week = Integer.valueOf(data.substring(4));
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Map<String, String> map = new LinkedHashMap<>();
        Calendar calendar = Calendar.getInstance();
        // ①.设置该年份的开始日期：第一个月的第一天
        calendar.set(year, 0, 1);
        // ②.计算出第一周还剩几天：+1是因为1号是1天
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        int dayOfWeek = 7 - i + 2;
        // ③.周数减去第一周再减去要得到的周
        week = week - 2;
        // ④.计算起止日期
        calendar.add(Calendar.DAY_OF_YEAR, week * 7 + dayOfWeek);
        String monday = sdf.format(calendar.getTime());
        map.put("monday", monday);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        String tuesday = sdf.format(calendar.getTime());
        map.put("tuesday", tuesday);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        String wednesday = sdf.format(calendar.getTime());
        map.put("wednesday", wednesday);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        String thursday = sdf.format(calendar.getTime());
        map.put("thursday", thursday);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        String firday = sdf.format(calendar.getTime());
        map.put("firday", firday);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        String saturday = sdf.format(calendar.getTime());
        map.put("saturday", saturday);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        String sunday = sdf.format(calendar.getTime());
        map.put("sunday", sunday);
        return map;
    }

    /**
     * 根据当前时间计算今年周次  返回示例 : 202034   2020年34周
     * @return 202036
     */
    public static String selectYearWeek() {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.YYYY);
        Date date = new Date();
        String year = sdf.format(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, -1); //减一天,每周从周日开始算,所以减一天
        int i = cal.get(Calendar.WEEK_OF_YEAR);
        return new StringBuffer().append(year).append(i).toString();
    }

    /**
     * 根据当前时间计算今年下周的周次  返回示例 : 202037   2020年37周
     * @return 202037
     */
    public static String selectYearNextWeek() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, 6); //每周从周日开始算,所以只加6天
        return findWeek(cal.getTime());
    }

    /**
     * 根据当前时间返回本周的日期
     * @return  {monday=08月31日, tuesday=09月01日, wednesday=09月02日, thursday=09月03日, firday=09月04日, saturday=09月05日, sunday=09月06日}
     * @throws Exception ""
     */
    public static Map<String, String> selectWeekDate() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.YYYY);
        Date date = new Date();
        String year = sdf.format(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, -1); //减一天,每周从周日开始算,所以减一天
        int i = cal.get(Calendar.WEEK_OF_YEAR);
        String s = new StringBuffer().append(year).append(i).toString();
        return weekToDayFormate(s, Constants.MM月dd日);
    }


    /**
     * 根据当前时间返回下周的日期
     * @return {monday=09月07日, tuesday=09月08日, wednesday=09月09日, thursday=09月10日, firday=09月11日, saturday=09月12日, sunday=09月13日}
     * @throws Exception ""
     */
    public static Map<String, String> selectNextWeekDate() throws Exception {
        String s = selectYearNextWeek();
        return weekToDayFormate(s, Constants.MM月dd日);
    }

    /**
     * 根据时间返回周次信息
     * @param date 时间
     * @return 202036
     */
    public static String findWeek(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.YYYY);
        String yea = sdf.format(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int one = cal.get(Calendar.WEEK_OF_YEAR);
        if(one==1){
            cal.add(Calendar.DAY_OF_YEAR, 6); //每周从周日开始算,所以只加6天
            Date time = cal.getTime();
            String format = sdf.format(time);
            if(!yea.equals(format)){
                yea= String.valueOf(Integer.valueOf(yea)+1);
            }
        }
        return new StringBuffer().append(yea).append(one).toString();
    }



    /**
     * 根据当前时间计算本周202036 和之后的4周的周次
     * @return [202036, 202037, 202038, 202039, 202040]
     */
    public static List<String> findWeek() {
        List<String> list=new ArrayList<>();
        Date date=new Date();
        String one = findWeek(date);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -1); //减一天,每周从周日开始算,所以减一天
        cal.add(Calendar.DAY_OF_YEAR, 7);
        String two = findWeek(cal.getTime());
        cal.add(Calendar.DAY_OF_YEAR, 7);
        String three = findWeek(cal.getTime());
        cal.add(Calendar.DAY_OF_YEAR, 7);
        String four = findWeek(cal.getTime());
        cal.add(Calendar.DAY_OF_YEAR, 7);
        String five = findWeek(cal.getTime());
        list.add(one);
        list.add(two);
        list.add(three);
        list.add(four);
        list.add(five);
        return list;
    }

    /**
     * 根据当前时间计算上下两周周次和本周的日期
     * @return {monday=08月31日, tuesday=09月01日, wednesday=09月02日, thursday=09月03日,
     * firday=09月04日, saturday=09月05日, oldWeek=202035, oldOldWeek=202034, nextWeek=202037,
     * nextNextWeek=202038, sunday=09月06日, weekNum=202036}
     */
    public static Map<String ,String> findOldAndNextWeek(String weekNum,String format){
        Integer year = Integer.valueOf(weekNum.substring(0, 4));
        Integer week = Integer.valueOf(weekNum.substring(4));
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Map<String, String> map = new LinkedHashMap<>();
        Calendar calendar = Calendar.getInstance();
        // ①.设置该年份的开始日期：第一个月的第一天
        calendar.set(year, 0, 1);
        // ②.计算出第一周还剩几天：+1是因为1号是1天
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        int dayOfWeek = 7 - i + 2;
        // ③.周数减去第一周再减去要得到的周
        week = week - 2;
        // ④.计算起止日期
        calendar.add(Calendar.DAY_OF_YEAR, week * 7 + dayOfWeek);
        String monday = sdf.format(calendar.getTime());
        map.put("monday", monday);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        String tuesday = sdf.format(calendar.getTime());
        map.put("tuesday", tuesday);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        String wednesday = sdf.format(calendar.getTime());
        map.put("wednesday", wednesday);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        String thursday = sdf.format(calendar.getTime());
        map.put("thursday", thursday);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        String firday = sdf.format(calendar.getTime());
        map.put("firday", firday);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        String saturday = sdf.format(calendar.getTime());
        map.put("saturday", saturday);
        calendar.add(Calendar.DAY_OF_YEAR, -7); //前一周
        String oldWeek = findWeek(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, -7); //前2周
        String oldOldWeek = findWeek(calendar.getTime());
        map.put("oldWeek",oldWeek);
        map.put("oldOldWeek",oldOldWeek);
        calendar.add(Calendar.DAY_OF_YEAR, 14);
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        String nextWeek = findWeek(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        String nextNextWeek = findWeek(calendar.getTime());
        map.put("nextWeek",nextWeek);
        map.put("nextNextWeek",nextNextWeek);
        calendar.add(Calendar.DAY_OF_YEAR, -14);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        String sunday = sdf.format(calendar.getTime());
        map.put("sunday", sunday);
        map.put("weekNum",weekNum);
        return map;
    }


    /**
     * 根据生日计算年龄
     * @param birthday  出生年月日字符串 示例:1991-04-12
     * @return 年龄
     */
    public static int getAgeByBirth(String birthday) {
        SimpleDateFormat sdf = new SimpleDateFormat(Constants.YYYY_MM_DD);
        Date date;
        try {
            date = sdf.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("时间格式不对,时间解析错误! 格式示例:1991-04-12");
        }
        return getAgeByBirth(date);
    }

    /**
     * 根据生日计算年龄
     * @param birthday 生日
     * @return 年龄  1
     */
    public static int getAgeByBirth(Date birthday) {
        //Calendar：日历
        /*从Calendar对象中或得一个Date对象*/
        Calendar cal = Calendar.getInstance();
        /*把出生日期放入Calendar类型的bir对象中，进行Calendar和Date类型进行转换*/
        Calendar bir = Calendar.getInstance();
        bir.setTime(birthday);
        /*如果生日大于当前日期，则抛出异常：出生日期不能大于当前日期*/
        if (cal.before(birthday)) {
            throw new IllegalArgumentException("出生日期大于当前日期,你还没出生呢");
        }
        /*取出当前年月日*/
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayNow = cal.get(Calendar.DAY_OF_MONTH);
        /*取出出生年月日*/
        int yearBirth = bir.get(Calendar.YEAR);
        int monthBirth = bir.get(Calendar.MONTH);
        int dayBirth = bir.get(Calendar.DAY_OF_MONTH);
        /*大概年龄是当前年减去出生年*/
        int age = yearNow - yearBirth;
        /*如果出当前月小与出生月，或者当前月等于出生月但是当前日小于出生日，那么年龄age就减一岁*/
        if (monthNow < monthBirth || (monthNow == monthBirth && dayNow < dayBirth)) {
            age--;
        }
        return age;
    }


    /**
     * 时间大小比较
     * @param beginDate
     * @param endDate
     * @return -1 大于 1 小于 0 相等
     */
    public static int compare_date(String beginDate, String endDate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date dt1 = df.parse(beginDate);
            Date dt2 = df.parse(endDate);
            if (dt1.getTime() > dt2.getTime()) {
                return -1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            return -2;
        }
    }

    /**
     * 计算两个时间差
     */
    public static long getDifference(Date endDate, Date nowDate) {
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        return diff;
    }

    /**
     * 获取当前时间的周几
     * @param n n为推迟的周数，0本周，-1向前推迟一周，1下周，依次类推
     * @param calendar 想周几，这里就传几Calendar.MONDAY（TUESDAY...）
     * @return
     */
    public static String getWeekTime(int n,int calendar){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        String monday;
        if(cal.get(Calendar.DAY_OF_WEEK)==1){
            cal.add(Calendar.DATE, -1);
        }else{
            cal.add(Calendar.DATE, n*7);
        }
        cal.set(Calendar.DAY_OF_WEEK,calendar);
        monday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        return monday;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1
     *            时间参数 1 格式：1990-01-01 12:00:00
     * @param str2
     *            时间参数 2 格式：2009-01-01 12:00:00
     * @param ymdhms
     *            时间参数 2 格式：y m d h f s 返回相差的时间单位 年、月、日、时、分、秒
     * @return String[] 返回值为：{天, 时, 分, 秒}
     */
    public static long getDistanceTimes(String str1, String str2, String ymdhms) {
        long res = 0;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();long time2 = two.getTime();long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            if (ymdhms.equalsIgnoreCase("y")) {
                res = diff / (12 * 30 * 24 * 60 * 60 * 1000);
            } else if (ymdhms.equalsIgnoreCase("m")) {
                res = diff / (30 * 24 * 60 * 60 * 1000);
            } else if (ymdhms.equalsIgnoreCase("d")) {
                res = diff / (24 * 60 * 60 * 1000);
            } else if (ymdhms.equalsIgnoreCase("h")) {
                res = (diff / (60 * 60 * 1000));
            } else if (ymdhms.equalsIgnoreCase("f")) {
                res = ((diff / (60 * 1000)));
            } else {
                res = (diff / 1000);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *
     * @param str1
     *            时间参数 1 格式：1990-01-01 12:00:00
     * @param str2
     *            时间参数 2 格式：2009-01-01 12:00:00
     * @return String[] 返回值为：{天, 时, 分, 秒}
     */
    public static String[] getDistanceTimes(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;Date two;
        long day = 0;long hour = 0;long min = 0;long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();long time2 = two.getTime();long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String[] times = { day + "", hour + "", min + "", sec + "" };
        return times;
    }

    /**
     * 在系统时间上增加一段时间
     *
     * @param format
     *        格式 "yyyy-MM-dd HH:mm:ss" ymd 格式 年 月 日 Y M D h m s;
     *        count 加的数量 加的数量 负数为减
     * @return
     */
    public static String addNYMD(String format, String ymd, int count) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar curr = Calendar.getInstance();
        if (ymd.equalsIgnoreCase("Y")) {
            curr.set(Calendar.YEAR, curr.get(Calendar.YEAR) + count);
        } else if (ymd.equalsIgnoreCase("M")) {
            curr.set(Calendar.MONTH, curr.get(Calendar.MONTH) + count);
        } else if (ymd.equalsIgnoreCase("D")) {
            curr.set(Calendar.DAY_OF_MONTH, curr.get(Calendar.DAY_OF_MONTH) + count);
        } else if (ymd.equalsIgnoreCase("h")) {
            curr.set(Calendar.HOUR_OF_DAY, curr.get(Calendar.HOUR_OF_DAY) + count);
        } else if (ymd.equalsIgnoreCase("m")) {
            curr.set(Calendar.MINUTE, curr.get(Calendar.MINUTE) + count);
        } else if(ymd.equalsIgnoreCase("S")) {
            curr.set(Calendar.SECOND, curr.get(Calendar.SECOND) + count);
        }
        Date date = curr.getTime();
        return sdf.format(date);
    }

    /**
     * 在指定时间上增加一段时间
     *
     * @param format
     *            格式 "yyyy-MM-dd HH:mm:ss" time 格式 "2018-01-01 20:05:03" 要高度一致
     * @param ymd
     *            格式 年 月 日 Y M D h m s;count 加的数量 负数为减
     * @return
     * @throws ParseException
     */
    public static String addNYMD(String format, String time, String ymd,
                                 int count) throws ParseException {
        if (format.length() == time.length()) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date t = sdf.parse(time);
            Calendar curr = Calendar.getInstance();
            curr.setTime(t);
            if (ymd.equalsIgnoreCase("Y")) {
                curr.set(Calendar.YEAR, curr.get(Calendar.YEAR) + count);
            } else if (ymd.equalsIgnoreCase("M")) {
                curr.set(Calendar.MONTH, curr.get(Calendar.MONTH) + count);
            } else if (ymd.equalsIgnoreCase("D")) {
                curr.set(Calendar.DAY_OF_MONTH, curr.get(Calendar.DAY_OF_MONTH) + count);
            } else if (ymd.equalsIgnoreCase("h")) {
                curr.set(Calendar.HOUR_OF_DAY, curr.get(Calendar.HOUR_OF_DAY) + count);
            } else if (ymd.equalsIgnoreCase("m")) {
                curr.set(Calendar.MINUTE, curr.get(Calendar.MINUTE) + count);
            } else if(ymd.equalsIgnoreCase("S")) {
                curr.set(Calendar.SECOND, curr.get(Calendar.SECOND) + count);
            }
            Date date = curr.getTime();
            return sdf.format(date);
        } else {
            return "参数format和time类型不匹配";
        }
    }

    /**
     * 将时间格式YYYY-mm-dd字符串转换为时间 yyyy/m/d
     *
     * @param strDate
     * @return
     */
    public static Date strToDatePos(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/M/d");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     *  yyyy/m/d日期格式正则验证
     *
     * @param strDate
     * @return
     */
    public static boolean strDateReg(String strDate) {
        String reg = "\\d{4}/\\d{1,2}/\\d{1,2}";
        Pattern pt = Pattern.compile(reg);
        Matcher mt =pt.matcher(strDate);
        if(mt.matches()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 判断时间是否在时间段内
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        if (date.after(begin) && date.before(end)) {
            return true;
        } else if (nowTime.compareTo(beginTime) == 0 || nowTime.compareTo(endTime) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取星期几数字
     * @param date
     * @return
     */
    public static int dateToWeek(Date date) {
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK)-1; // 指示一个星期中的某天。
        if(w == 0){
            w = 7;
        }
        return w;
    }

    /**
     * 获取星期几
     * @param date
     * @return
     */
    public static String getYmdWeek(Date date){
        String week = "";
        int weekNum = dateToWeek(date);
        switch (weekNum) {
            case 1:
                week = "星期一";
                break;
            case 2:
                week = "星期二";
                break;
            case 3:
                week = "星期三";
                break;
            case 4:
                week = "星期四";
                break;
            case 5:
                week = "星期五";
                break;
            case 6:
                week = "星期六";
                break;
            case 7:
                week = "星期日";
                break;
            default:
                break;
        }
        return week;
    }
}

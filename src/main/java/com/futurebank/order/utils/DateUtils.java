package com.futurebank.order.utils;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
public class DateUtils {
    public static final String DATE_PATTEN_YYMMDD = "yyyyMMdd";
    public static final String DATE_PATTEN_YY_MM_DD = "yyyy-MM-dd";
    public static final String DATE_FILE_PATTEN = "yyyy/MM/dd";
    public static final String DATE_PATTEN_YY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTEN_HHMM = "HH:mm";
    public static final String DATE_PATTEN_YYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static void main(String[] args) {
        System.out.println(formatDate("20210426174303", DateUtils.DATE_PATTEN_YYMMDDHHMMSS, DATE_PATTEN_YY_MM_DD_HHMMSS));
    }

    public static String formatDate(String sourceDate, String inputPatten, String outputPatten) {

        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPatten);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPatten);

        try {
            Date date = inputFormat.parse(sourceDate);
            String formattedDate = outputFormat.format(date);
            return formattedDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getPreviousDateStr() {

        Calendar calendar = Calendar.getInstance();

        calendar.add(5, -1);

        return (new SimpleDateFormat("yyyyMMdd")).format(calendar.getTime());

    }


    public static Date getPreviousDate() {

        String yestoday = getPreviousDateStr();

        return stringToDate(yestoday, "yyyyMMdd");

    }


    public static String getCurrentDateStr() {

        Calendar calendar = Calendar.getInstance();

        return (new SimpleDateFormat("yyyyMMdd")).format(calendar.getTime());

    }


    public static String getCurrentDateFilePath() {

        Calendar calendar = Calendar.getInstance();

        return (new SimpleDateFormat("yyyy/MM/dd")).format(calendar.getTime());

    }


    public static Date getCurrentDate() {

        String date = getCurrentDateStr();

        return stringToDate(date, "yyyyMMdd");

    }


    public static Date stringToDate(String date, String patten) {

        SimpleDateFormat sf = new SimpleDateFormat(patten);

        Date d = null;

        try {

            d = sf.parse(date);

        } catch (ParseException e) {

            e.printStackTrace();

        }

        return d;

    }


    public static Date getDate(String dateStr) {

        try {

            return (new SimpleDateFormat("yyyy-MM-dd")).parse(dateStr);

        } catch (Exception e) {

            e.printStackTrace();


            return null;

        }

    }


    public static String getDateStr() {

        return transferDateToString(new Date(), "yyyyMMddHHmmss");

    }


//    public static void main(String[] args) {
//
//        System.out.println(getDateStr());
//
//    }


    public static String transferDateToString(Date date, String pattern) {

        if (date == null) {

            return null;

        }

        SimpleDateFormat dateFormate = new SimpleDateFormat(pattern);

        return dateFormate.format(date);

    }


    public static String getCurrentDate(Date date, String formart) {

        SimpleDateFormat sd = new SimpleDateFormat(formart);

        return sd.format(date);

    }


    public static String getSpecifiedDayBefore(String specifiedDay, String format) {

        Calendar c = Calendar.getInstance();

        Date date = null;

        try {

            date = (new SimpleDateFormat(format)).parse(specifiedDay);

        } catch (ParseException e) {

            e.printStackTrace();

        }

        c.setTime(date);

        int day = c.get(5);

        c.set(5, day - 1);


        String dayBefore = (new SimpleDateFormat(format)).format(c.getTime());

        return dayBefore;

    }


    public static List<Date> getRecentlyDays(Integer length, String endTime) {

        List<Date> theDays = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {

            String currentDate = getCurrentDate(sdf.parse(endTime), "yyyy-MM-dd");

            String specDay = currentDate;

            String specBeforeDay = currentDate;

            for (int i = 0; i < length.intValue() + 1; i++) {

                theDays.add(i, sdf.parse(specBeforeDay));

                specDay = specBeforeDay;

                specBeforeDay = getSpecifiedDayBefore(specDay, "yyyy-MM-dd");

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return theDays;

    }


    public static String getSpecifiedHourBefore(String specifiedHour, String format) {

        Calendar c = Calendar.getInstance();

        Date date = null;

        try {

            date = (new SimpleDateFormat(format)).parse(specifiedHour);

        } catch (ParseException e) {

            e.printStackTrace();

        }

        c.setTime(date);

        int hour = c.get(11);

        c.set(11, hour - 1);


        String dayBefore = (new SimpleDateFormat(format)).format(c.getTime());

        return dayBefore;

    }


    public static List<Date> getHourTime(String endTime) {

        List<Date> theHourTime = new ArrayList<>();

        String todayTime = getCurrentDate(new Date(), "yyyy-MM-dd");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
        try {

            int length;

            String currentDateTime;

            if (endTime.equals(todayTime)) {

                currentDateTime = getCurrentDate(new Date(), "yyyy-MM-dd HH");

                SimpleDateFormat sdf = new SimpleDateFormat("HH");

                String specifiedStr = sdf.format(new Date());

                length = Integer.parseInt(specifiedStr);

            } else {

                String tempTime = new String(endTime);

                tempTime = tempTime + " 23";

                currentDateTime = getCurrentDate(format.parse(tempTime), "yyyy-MM-dd HH");

                length = 23;

            }


            String temp = currentDateTime;

            String specifiedBeforeStr = temp;


            for (int i = 0; i <= length; i++) {

                theHourTime.add(i, format.parse(specifiedBeforeStr));

                specifiedBeforeStr = getSpecifiedHourBefore(temp, "yyyy-MM-dd HH");

                temp = specifiedBeforeStr;

            }

        } catch (Exception e) {

            e.printStackTrace();

        }


        return theHourTime;

    }


    public static String updateDate() {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return df.format(new Date()).toString();

    }


    public static String Week(String d, Integer i) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();

        Date date = sdf.parse(d);

        calendar.setTime(date);

        calendar.add(3, i.intValue());

        date = calendar.getTime();

        int year = Integer.parseInt((new SimpleDateFormat("yyyy")).format(date));

        int month = Integer.parseInt((new SimpleDateFormat("MM")).format(date));

        int day = Integer.parseInt((new SimpleDateFormat("dd")).format(date));

        String dd = "";

        if (day < 10) {

            dd = "0" + day;

        } else {

            dd = dd + day;

        }

        return year + "-" + month + "-" + dd;

    }


    public static String Yestoday(String d, Integer i) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar calendar = Calendar.getInstance();

        Date date = sdf.parse(d);

        calendar.setTime(date);

        calendar.add(6, i.intValue());

        date = calendar.getTime();


        return (new SimpleDateFormat("yyyy-MM-dd")).format(date);

    }


    public static String today() {

        Date date = new Date(System.currentTimeMillis());

        return (new SimpleDateFormat("yyyy-MM-dd")).format(date);

    }


    /**
     * 获取当前日期,指定格式
     * 描述:<描述函数实现的功能>.
     *
     * @return
     */
    public static String nowDate() {
        return nowDate(Constants.DATE_FORMAT_NUM);
    }

    /**
     * 获取当前年,指定格式
     * 描述:<描述函数实现的功能>.
     *
     * @return
     */
    public static String nowYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR) + "";
    }

    /**
     * 获取上一年,指定格式
     * 描述:<描述函数实现的功能>.
     *
     * @return
     */
    public static String lastYear() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        return cal.get(Calendar.YEAR) + "";
    }

    /**
     * 获取当前日期,指定格式
     * 描述:<描述函数实现的功能>.
     *
     * @return
     */
    public static Date nowDateTime() {
        return strToDate(nowDateTimeStr(), Constants.DATE_FORMAT);
    }

    /**
     * 获取当前日期,指定格式
     * 描述:<描述函数实现的功能>.
     *
     * @return
     */
    public static String nowDateTimeStr() {
        return nowDate(Constants.DATE_FORMAT);
    }

    /**
     * 获取当前日期,指定格式
     * 描述:<描述函数实现的功能>.
     *
     * @return
     */
    public static String nowDate(String DATE_FORMAT) {
        SimpleDateFormat dft = new SimpleDateFormat(DATE_FORMAT);
        return dft.format(new Date());
    }

    /**
     * 获取当前日期,指定格式
     * 描述:<描述函数实现的功能>.
     *
     * @return
     */
    public static String nowDateTime(String DATE_FORMAT) {
        SimpleDateFormat dft = new SimpleDateFormat(DATE_FORMAT);
        return dft.format(new Date());
    }

    /**
     * 获取当前日期,指定格式
     * 描述:<描述函数实现的功能>.
     *
     * @return
     */
    public static Integer getNowTime() {
        long t = (System.currentTimeMillis() / 1000L);
        return Integer.parseInt(String.valueOf(t));
    }

    /**
     * 获取当前时间戳（秒级）
     *
     * @return
     */
    public static Long getTime() {
        return (System.currentTimeMillis() / 1000L);
    }

    /**
     * 获取当前日期,指定格式
     * 描述:<描述函数实现的功能>.
     *
     * @return
     */
    public static Date nowDateTimeReturnDate(String DATE_FORMAT) {
        SimpleDateFormat dft = new SimpleDateFormat(DATE_FORMAT);
        return strToDate(dft.format(new Date()), DATE_FORMAT);
    }

    /**
     * convert a date to string in a specifies fromat.
     *
     * @param date
     * @param DATE_FORMAT
     * @return
     */
    public static String dateToStr(Date date, String DATE_FORMAT) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat myFormat = new SimpleDateFormat(DATE_FORMAT);
        return myFormat.format(date);
    }

    /**
     * parse a String to Date in a specifies fromat.
     *
     * @param dateStr
     * @param DATE_FORMAT
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String dateStr, String DATE_FORMAT) {
        SimpleDateFormat myFormat = new SimpleDateFormat(DATE_FORMAT);
        try {
            return myFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }


    /**
     * date add Second
     *
     * @param date
     * @param num
     * @return
     */
    public static Date addSecond(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, num);
        return calendar.getTime();
    }

    /**
     * date add Second return String
     *
     * @param date
     * @param num
     * @return
     */
    public static String addSecond(Date date, int num, String DATE_FORMAT) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, num);
        return dateToStr(calendar.getTime(), DATE_FORMAT);
    }

    /**
     * 指定日期加上天数后的日期
     *
     * @param num     为增加的天数
     * @param newDate 创建时间
     * @return
     */
    public static final String addDay(String newDate, int num, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            Date currdate = format.parse(newDate);
            Calendar ca = Calendar.getInstance();
            ca.setTime(currdate);
            ca.add(Calendar.DATE, num);
            return format.format(ca.getTime());
        } catch (ParseException e) {
            log.error("转化时间出错,", e);
            return null;
        }
    }

    /**
     * 指定日期加上天数后的日期
     *
     * @param num     为增加的天数
     * @param newDate 创建时间
     * @return
     */
    public static final String addDay(Date newDate, int num, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Calendar ca = Calendar.getInstance();
        ca.setTime(newDate);
        ca.add(Calendar.DATE, num);
        return format.format(ca.getTime());
    }

    /**
     * convert long to date
     *
     * @param date 待转换时间戳
     * @return 转换后时间
     */
    public static Date timeStamp11ToDate(Integer date) {
        return new Date(date);
    }

    /**
     * convert long to date string
     *
     * @param date        待转换时间戳
     * @param DATE_FORMAT 格式化时间
     * @return 格式化后的时间
     */
    public static String timeStamp11ToDate(Integer date, String DATE_FORMAT) {
        return dateToStr(new Date(date), DATE_FORMAT);
    }

    /**
     * compare two date String with a pattern
     *
     * @param date1
     * @param date2
     * @param pattern
     * @return
     */
    public static int compareDate(String date1, String date2, String pattern) {
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(pattern);
        try {
            Date dt1 = DATE_FORMAT.parse(date1);
            Date dt2 = DATE_FORMAT.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }

        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 检查日期格式是否合法
     *
     * @param date
     * @param style
     * @return
     */
    public static boolean checkDateFormat(String date, String style) {
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(style);
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            DATE_FORMAT.setLenient(false);
            DATE_FORMAT.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 计算两个时间之间的天数差
     *
     * @param beforeDay 开始时间
     * @param afterDay  结束时间
     * @return 相差天数
     */
    public static long getTwoDateDays(Date beforeDay, Date afterDay) {
        SimpleDateFormat sm = new SimpleDateFormat(Constants.DATE_FORMAT_NUM);
        long days = -1;
        try {
            days = (sm.parse(sm.format(afterDay)).getTime() - sm.parse(sm.format(beforeDay)).getTime()) / (1000 * 3600 * 24);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return days;
    }


    //获取时间戳11位
    public static int getSecondTimestamp(Date date) {
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime() / 1000);
        return Integer.parseInt(timestamp);
    }

    //获取时间戳11位
    public static int getSecondTimestamp(String date) {
        if (null == date) {
            return 0;
        }
        Date date1 = strToDate(date, Constants.DATE_FORMAT);
        if (date1 == null) {
            return 0;
        }
        String timestamp = String.valueOf(date1.getTime() / 1000);
        return Integer.parseInt(timestamp);
    }

    //获取时间戳10位
    public static int getSecondTimestamp(Long timeMillis) {
        if (null == timeMillis) {
            return 0;
        }
        String timestamp = String.valueOf(timeMillis / 1000);
        return Integer.parseInt(timestamp);
    }

    //获取时间戳11位
    public static int getSecondTimestamp() {
        Date date = strToDate(nowDateTime(Constants.DATE_FORMAT), Constants.DATE_FORMAT);
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime() / 1000);
        return Integer.parseInt(timestamp);
    }

    /**
     * 获得昨天日期:yyyy-MM-dd  HH:mm:ss
     */
    public static String getYesterdayStr() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        SimpleDateFormat startSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return startSdf.format(c.getTime());
    }

    /**
     * 获得本周第一天:yyyy-MM-dd HH:mm:ss
     */
    public static String getWeekStartDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.WEEK_OF_MONTH, 0);
        c.set(Calendar.DAY_OF_WEEK, 2);
        SimpleDateFormat startSdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        return startSdf.format(c.getTime());
    }

    /**
     * 获得本周最后一天:yyyy-MM-dd HH:mm:ss
     */
    public static String getWeekEndDay() {
        return addDay(getWeekStartDay(), 7, Constants.DATE_FORMAT);
    }

    /**
     * 获得上周第一天:yyyy-MM-dd HH:mm:ss
     */
    public static String getLastWeekStartDay() {
        return addDay(getWeekStartDay(), -7, Constants.DATE_FORMAT);
    }

    /**
     * 获得上周最后一天:yyyy-MM-dd HH:mm:ss
     */
    public static String getLastWeekEndDay() {
        return addDay(getLastWeekStartDay(), 7, Constants.DATE_FORMAT);
    }

    /**
     * 获得本月最后一天:yyyy-MM-dd HH:mm:ss
     */
    public static String getMonthEndDay() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat endSdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        return endSdf.format(c.getTime());
    }

    /**
     * 获得上月第一天:yyyy-MM-dd HH:mm:ss
     */
    public static String getLastMonthStartDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat startSdf = new SimpleDateFormat("yyyy-MM-01 00:00:00");
        return startSdf.format(c.getTime());
    }

    /**
     * 获得上月最后一天:yyyy-MM-dd HH:mm:ss
     */
    public static String getLastMonthEndDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat endSdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        return endSdf.format(c.getTime());
    }

    /**
     * 获得上年第一天:yyyy-MM-dd HH:mm:ss
     */
    public static String getLastYearStartDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -1);
        SimpleDateFormat startSdf = new SimpleDateFormat("yyyy-01-01 00:00:00");
        return startSdf.format(c.getTime());
    }

    /**
     * 获得上年最后一天:yyyy-MM-dd HH:mm:ss
     */
    public static String getLastYearEndDay() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -1);
        SimpleDateFormat endSdf = new SimpleDateFormat("yyyy-12-31 23:59:59");
        return endSdf.format(c.getTime());
    }


    /**
     * 两个日期之前的相差天数
     *
     * @param starDate 开始日期
     * @param endDate  结束日期
     * @return 相差天数
     */
    public static int daysBetween(Date starDate, Date endDate) {

        Calendar cal = Calendar.getInstance();

        cal.setTime(starDate);

        long time1 = cal.getTimeInMillis();

        cal.setTime(endDate);

        long time2 = cal.getTimeInMillis();

        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));

    }

    /**
     * 获取指定日期指定格式字符串
     *
     * @param dateStr
     * @param DATE_FORMAT
     * @return
     * @throws ParseException
     */
    public static String appointedDayStrToFormatStr(String dateStr, String STR_DATE_FORMAT, String DATE_FORMAT) {
        Date date = DateUtil.strToDate(dateStr, STR_DATE_FORMAT);
        return DateUtil.dateToStr(date, DATE_FORMAT);
    }

    /**
     * 获取当前时间小时
     *
     * @return 当前时间小时 默认24小时
     */
    public static int getCurrentHour() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        return hour;
    }

    /**
     * 13位时间戳转字符串
     *
     * @param timestamp
     * @param dateFormat
     * @return
     */
    public static String timestamp2DateStr(Long timestamp, String dateFormat) {
        if (Objects.isNull(timestamp)) {
            return "";
        }
        if (StrUtil.isBlank(dateFormat)) {
            dateFormat = Constants.DATE_FORMAT;
        }
        Date date = new Date(timestamp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(date);
    }

    /**
     * 字符串转13位时间戳
     */
    public static Long dateStr2Timestamp(String dateStr, String type) {
        DateTime parse = cn.hutool.core.date.DateUtil.parse(dateStr);
        if (StrUtil.isNotBlank(type)) {
            if (type.equals(Constants.DATE_TIME_TYPE_BEGIN)) {
                parse = cn.hutool.core.date.DateUtil.beginOfDay(parse);
            }
            if (type.equals(Constants.DATE_TIME_TYPE_END)) {
                parse = cn.hutool.core.date.DateUtil.endOfDay(parse);
            }
        }
        return parse.getTime();
    }


    public static Date parseDate(String dateString) {
        // 定义不同日期格式
        String[] patterns = {"yyyy年M月d日", "yyyy-MM-dd", "MM/dd/yyyy"};

        // 遍历不同格式，尝试解析日期字符串
        for (String pattern : patterns) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                // 解析日期
                Date date = sdf.parse(dateString);
                // 返回解析成功的日期对象
                return date;
            } catch (ParseException e) {
                // 如果解析失败，继续尝试下一个格式
            }
        }

        // 如果所有格式都无法解析，返回null
        return null;
    }

    public static Date parseDateUTC(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//        formatter.setTimeZone(TimeZone.getTimeZone("UTC")); // 设置时区为UTC
        try {
            Date date = formatter.parse(dateString);
            return date;
        } catch (Exception e) {
            return null;
        }
    }


}



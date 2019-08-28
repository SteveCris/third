package com.shangyong.thzlqb.utils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * 日期工具类
 * @author tongjingji01@gmail.com on 2019
 */
public class DateUtil {

    public static final String YY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static String getSimpleFormatDate(Long date) {
        return getFormatDate("yyyy/MM/dd HH:mm:ss", date);
    }

    public static String getFormatDate(String formatStr, Long date) {
        if (date == null || date < 1){
            return "";
        }
        FastDateFormat format = FastDateFormat.getInstance(formatStr);
        return format.format(new Date(date));
    }

    public static String getEsSearchDate(Long date){
        // 减去8小时换成标准时间
        date = date - 8*60*60*1000L;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(date)).replace(" ", "T");
    }
    public static String getDateToStr(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
    /**
     * 描述转时间
     * @param timeStr
     * @param formatStr
     * @return
     */
    public static Long getParseTime(String timeStr, String formatStr) {
        if (StringUtils.isEmpty(timeStr) ){
            return 0L;
        }
        if(StringUtils.isEmpty(formatStr)){
            formatStr = "yyyy-MM-dd HH:mm:ss";// yyyy-MM-ddTHH:mm:ss
        }
        SimpleDateFormat sdf=new SimpleDateFormat(formatStr);
        try {
            return sdf.parse(timeStr).getTime();
        } catch (ParseException e) {
           e.printStackTrace();
        }
        return 0L;
    }

    /**
     * 获取零点时间
     * @return
     */
    public static Long getZeroTimeInMillis(Long time){
        if(time==null || time == 0L){
            return 0L;
        }
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTimeInMillis();
    }

    /**
     * 获取23:59:59时间
     * @return
     */
    public static Long getLastTimeInMillis(Long time){
        if(time==null || time == 0L){
            return 0L;
        }
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 59);
        return c.getTimeInMillis();
    }
    
	public static Date getDateByCalculateMonth(Long time, int addTime) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		c.add(Calendar.MONTH, addTime);
		return c.getTime();
	}
    
	public static long getDateOf2016() {

		String lastTime = "2016-12-12";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			return sdf.parse(lastTime).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}

	}

    public static Long date2Long(Date date) {
        if (Objects.isNull(date)){
            return null;
        }
        return date.getTime();
    }

    public static Long null2Zero(Date date) {
        if (Objects.isNull(date)){
            return 0L;
        }
        return date.getTime();
    }

    /**
     * 计算向前或向后推移天数后的日期
     * @param time 需要计算的时间
     * @param day 需要计算的天数
     * @return 推移后的时间
     */
    public static Long addDate(Long time,int day) {
        if (Objects.isNull(time)) {
            return 0L;
        }
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        c.add(Calendar.DATE,day);
        return c.getTimeInMillis();
    }
	public static long getFormDayTimeMills(int formatDay){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, - formatDay);
        long timeInMillis = c.getTimeInMillis();
        return  timeInMillis;
    }
	public static Date formatLongToDate(Long time){
        if (time == null) {
            return null;
        }
        Date date = new Date();
        date.setTime(time);
        return  date;
    }
    public static Date StrToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String DateToStr(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }
    // 获得某天最大时间 2017-10-15 23:59:59
    public static String getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        Date date1 = Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
        DateToStr(date1);
        return DateToStr(date1);
    }

    public  static String  plusDaysDate(String value,int day){
        Validate.isTrue(value != null, "The value must not be null");
        Date date = StrToDate(value);
        Date startDate = DateUtils.addDays(date, day);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return  format.format(startDate);
    }



}

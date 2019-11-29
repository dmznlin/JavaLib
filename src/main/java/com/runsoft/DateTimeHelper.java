package com.runsoft;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

interface DateTimeFormatConfig {
    /**
     * 格式化日期
     * @return
     */
    default String forDate(){
        return "yyyy-MM-dd";
    }

    /**
     * 格式化日期不带分隔符
     * @return
     */
    default String forDateNoSeparator(){
        return "yyyyMMdd";
    }

    /**
     * 中文格式日期
     * @return
     */
    default String forCHDate(){ return "yyyy年MM月dd日"; }

    /**
     * 格式化时间
     * @return
     */
    default String forTime(){
        return "HH:mm:ss";
    }

    /**
     * 格式化时间不带分隔符
     * @return
     */
    default String forTimeNoSeparator(){
        return "HHmmss";
    }

    /**
     * 中文格式的时间
     * @return
     */
    default String forCHTime(){ return "HH时mm分ss秒"; };

    /**
     * 格式化日期 + 时间
     * @return
     */
    default String forDateTime(){
        return "yyyy-MM-dd HH:mm:ss";
    }

    /**
     * 星期前缀,如:周日
     * @return
     */
    default String forWeek(){
        return "周";
    }
}

public class DateTimeHelper {
    private static DateTimeHelper FHelper = null;
    private DateTimeFormatConfig FConfig = null;

    /**
     * 初始化格式化方式
     * @param nConfig
     */
    public static final void ConfigHelper(DateTimeFormatConfig nConfig){
        if (FHelper == null) {
            FHelper = new DateTimeHelper(nConfig);
        }
    }

    public DateTimeHelper(DateTimeFormatConfig nConfig){
        FConfig = nConfig;
    }

    /**
     * 使用字符串构建日期
     * @param nStr 日期字符串,需符合FConfig.forDate格式
     * @return
     */
    public Date StrToDate(String nStr) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(FConfig.forDate());
        LocalDate date = LocalDate.parse(nStr, df);
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date Str2Date(String nStr) {
        return FHelper.StrToDate(nStr);
    }

    /**
     * 使用字符串构建时间
     * @param nStr 时间字符串,需符合FConfig.forTime格式
     * @return
     */
    public Date StrToTime(String nStr) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(FConfig.forTime());
        LocalDateTime dt = LocalDateTime.of(LocalDate.now(), LocalTime.parse(nStr, df));
        return Date.from(dt.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date Str2Time(String nStr) {
        return FHelper.StrToTime(nStr);
    }

    /**
     * 将日期转为字符串
     * @param nDate 日期
     * @param nSeparator 是否使用分隔符
     * @return
     */
    public String DateToStr(Date nDate, boolean nSeparator) {
        DateTimeFormatter df;
        if (nSeparator){
            df = DateTimeFormatter.ofPattern(FConfig.forDate());
        } else {
            df = DateTimeFormatter.ofPattern(FConfig.forDateNoSeparator());
        }

        LocalDateTime dt = LocalDateTime.ofInstant(nDate.toInstant(), ZoneId.systemDefault());
        return df.format(dt.toLocalDate());
    }

    public static String Date2Str(Date nDate, boolean nSeparator) {
        return FHelper.DateToStr(nDate, nSeparator);
    }

    /**
     * 将时间转为字符串
     * @param nTime 时间
     * @param nSeparator 是否使用分隔符
     * @return
     */
    public String TimeToStr(Date nTime, boolean nSeparator) {
        DateTimeFormatter df;
        if (nSeparator){
            df = DateTimeFormatter.ofPattern(FConfig.forTime());
        } else {
            df = DateTimeFormatter.ofPattern(FConfig.forTimeNoSeparator());
        }

        LocalDateTime dt = LocalDateTime.ofInstant(nTime.toInstant(), ZoneId.systemDefault());
        return df.format(dt.toLocalTime());
    }

    public static String Time2Str(Date nTime, boolean nSeparator) {
        return FHelper.TimeToStr(nTime, nSeparator);
    }

    /**
     * 将字符串转为日期 + 时间
     * @param nStr 待转换字符串
     * @return
     */
    public Date StrToDateTime(String nStr){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(FConfig.forDateTime());
        LocalDateTime dt = LocalDateTime.parse(nStr, df);
        return Date.from(dt.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date Str2DateTime(String nStr){
        return FHelper.StrToDateTime(nStr);
    }

    /**
     * 将日期 + 时间转为字符串
     * @param nDateTime 日期+时间
     * @return
     */
    public String DateTimeToStr(Date nDateTime){
        LocalDateTime dt = LocalDateTime.ofInstant(nDateTime.toInstant(), ZoneId.systemDefault());
        DateTimeFormatter df = DateTimeFormatter.ofPattern(FConfig.forDateTime());
        return df.format(dt);
    }

    public static String DateTime2Str(Date nDateTime){
        return FHelper.DateTimeToStr(nDateTime);
    }

    /**
     * 获取指定日期为星期几
     * @param nDate 待计算的日期
     * @param nEnglish 是否使用英文星期
     * @return
     */
    public String DateToWeek(Date nDate, boolean nEnglish){
        String[][] nWeeks = {{"MONDAY", "一"}, {"TUESDAY", "二"}, {"WEDNESDAY", "三"},
                {"THURSDAY", "四"}, {"FRIDAY", "五"}, {"SATURDAY", "六"}, {"SUNDAY", "日"}};
        //星期常量定义

        LocalDateTime dt = LocalDateTime.ofInstant(nDate.toInstant(), ZoneId.systemDefault());
        String nDay = String.valueOf(dt.toLocalDate().getDayOfWeek());
        if (nEnglish) { return nDay; }

        for (int i = 0; i < nWeeks.length; i++) {
            if (nDay.equals(nWeeks[i][0])) {
                nDay = nWeeks[i][1];
                break;
            }
        }

        return FConfig.forWeek() + nDay;
    }

    public static String Date2Week(Date nDate, boolean nEnglish){
        return  FHelper.DateToWeek(nDate, nEnglish);
    }

    /**
     * 将日期转为中文描述
     * @param nDate
     * @return
     */
    public String DateToCH(Date nDate){
        LocalDateTime dt = LocalDateTime.ofInstant(nDate.toInstant(), ZoneId.systemDefault());
        DateTimeFormatter df = DateTimeFormatter.ofPattern(FConfig.forCHDate());
        return df.format(dt.toLocalDate());
    }

    public static String Date2CH(Date nDate){
        return FHelper.DateToCH(nDate);
    }

    /**
     * 将时间转为中文描述
     * @param nTime
     * @return
     */
    public String TimeToCH(Date nTime){
        LocalDateTime dt = LocalDateTime.ofInstant(nTime.toInstant(), ZoneId.systemDefault());
        DateTimeFormatter df = DateTimeFormatter.ofPattern(FConfig.forCHTime());
        return df.format(dt.toLocalTime());
    }

    public static String Time2CH(Date nTime){
        return FHelper.TimeToCH(nTime);
    }
}

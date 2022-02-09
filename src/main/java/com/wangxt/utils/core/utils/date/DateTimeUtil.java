package com.wangxt.utils.core.utils.date;

import org.apache.commons.lang3.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author wangxt
 * @description 时间转换工具类
 * @date 2022/2/9 13:52
 **/
public class DateTimeUtil {

    /**
     * 格式枚举
     */
    public enum FormatEnum {
        yyyyMMdd("yyyyMMdd"),
        d("yyyy-MM-dd"),
        yyyyMM01("yyyy-MM-01"),
        yyyyMM("yyyy-MM"),
        yyyyMMdd000000("yyyy-MM-dd 00:00:00"),
        yyyyMMdd235959("yyyy-MM-dd 23:59:59"),
        MMdd("MMdd"),
        yyyyMMddHHmmss("yyyyMMddHHmmss"),
        yyMMddHHmmss("yyMMddHHmmss"),
        yyMMddHHmmssSSS("yyMMddHHmmssSSS"),
        yyyyMMddHHmmssSSS("yyyyMMddHHmmssSSS"),
        cn_G("yyyy/MM/dd HH:mm:ss"),
        u("yyyy-MM-dd HH:mm:ss"),
        T("HH:mm:ss"),
        t("HHmmss");

        private String value;

        FormatEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static Date localDateToUtilDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date localDateTimeToUtilDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate utilDateToLocalDate(Date utilDate) {
        return LocalDateTime.ofInstant(utilDate.toInstant(), ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime utilDateToLocalDateTime(Date utilDate) {
        return LocalDateTime.ofInstant(utilDate.toInstant(), ZoneId.systemDefault());
    }

    public static long daysBetween(Temporal temporal1Inclusive, Temporal temporal2Exclusive) {
        return ChronoUnit.DAYS.between(temporal1Inclusive, temporal2Exclusive);
    }

    public static long hoursBetween(Temporal temporal1Inclusive, Temporal temporal2Exclusive) {
        return ChronoUnit.HOURS.between(temporal1Inclusive, temporal2Exclusive);
    }

    public static long minutesBetween(Temporal temporal1Inclusive, Temporal temporal2Exclusive) {
        return ChronoUnit.MINUTES.between(temporal1Inclusive, temporal2Exclusive);
    }

    public static long secondsBetween(Temporal temporal1Inclusive, Temporal temporal2Exclusive) {
        return ChronoUnit.SECONDS.between(temporal1Inclusive, temporal2Exclusive);
    }

    public static String dateTimeFormat(LocalDateTime dateTime, FormatEnum format) {
        return dateTime.format(DateTimeFormatter.ofPattern(format.getValue()));
    }

    public static String dateFormat(LocalDate date, FormatEnum format) {
        return date.format(DateTimeFormatter.ofPattern(format.getValue()));
    }

    public static String timeFormat(LocalTime time, FormatEnum format) {
        return time.format(DateTimeFormatter.ofPattern(format.getValue()));
    }

    public static LocalDateTime parseToLocalDateTime(String text, FormatEnum format) {
        return LocalDateTime.parse(text, DateTimeFormatter.ofPattern(format.getValue()));
    }

    public static LocalDate parseToLocalDate(String text, FormatEnum format) {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern(format.getValue()));
    }

    public static LocalTime parseToLocalTime(String text, FormatEnum format) {
        return LocalTime.parse(text, DateTimeFormatter.ofPattern(format.getValue()));
    }

    public static List<String> getDayRegions(String start, String end){
        List<String> times = new ArrayList<>();
        try {
            LocalDate startDate = LocalDate.parse(start);
            LocalDate endDate = LocalDate.parse(end);
            long distance = ChronoUnit.DAYS.between(startDate, endDate);
            if (distance < 1) {
                times.add(startDate.toString());
                return times;
            }
            Stream.iterate(startDate, d -> {
                return d.plusDays(1);
            }).limit(distance + 1).forEach(f -> {
                times.add(f.toString());
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        return times;
    }

    public static String getCurrWeekMonday(String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        try {
            //将日期格式化
            LocalDate beginDateTime = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            //获取当前日期所在周的星期一
            LocalDate monday = beginDateTime.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).plusDays(1);
            return monday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCurrWeekSunDay(String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        try {
            //将日期格式化
            LocalDate beginDateTime = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            //获取当前周的星期日
            LocalDate monday = beginDateTime.with(TemporalAdjusters.next(DayOfWeek.MONDAY)).minusDays(1);
            return monday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long localDateTimeToMillisecond(LocalDateTime datetime){
        return datetime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    public static long localDateToMillisecond(LocalDate date){
        return date.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();
    }

    public static LocalDateTime millisecondToLocalDateTime(Long timestamp){
        return Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
    }

    public static LocalDate millisecondToLocalDate(Long timestamp){
        return Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDate();
    }
}

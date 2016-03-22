package com.ualr.emoweat.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import com.ualr.emoweat.core.utils.XMLObject;
/**
* @author - Tolgahan CAKALOGLU "Jackalhan"
*/

public class DateFactory extends java.util.GregorianCalendar implements XMLObject {
    public final static String ERA_G = "G"; // AD/BC
    public final static String YEAR_y = "y"; // 1996
    public final static String MONTH_M = "M"; // July & 07
    public final static String DAY_d = "d"; // 10
    public final static String HOUR12_h = "h"; // 12 (1~12)
    public final static String HOUR24_H = "H"; // 0 (0~23)
    public final static String MINUTE_m = "m"; // 30
    public final static String SECOND_s = "s"; // 55
    public final static String MILLISECOND_S = "S"; // 978
    public final static String DAY_IN_WEEK_E = "E"; //Tuesday
    public final static String DAY_IN_YEAR_D = "D"; // 189
    public final static String DAY_OF_WEEK_IN_MONTH_F = "F"; // 2 (2nd Wed in July)
    public final static String WEEK_IN_YEAR_w = "w"; // 27
    public final static String WEEK_IN_MONTH_W = "W"; // 2
    public final static String AM_PM_a = "a"; // PM
    public final static String HOUR_IN_DAY_k = "k"; // 24 (1~24)
    public final static String HOUR_IN_AM_PM_K = "K"; // 0 (1~11)
    public final static String TIMEZONE_z = "z"; // Pacific Standard Time


    private DateFactory() {
        super();
    }

    public static Date getDate(int hours, int minutes) {
        return new DateFactory(hours, minutes).getTime();
    }

    public static Date getDate(int year, int month, int day) {
        return new DateFactory(year, month, day).getTime();
    }

    public static Date getDate(int year, int month, int day, int hour, int minutes) {
        return new DateFactory(year, month, day, hour, minutes).getTime();
    }

    public static Date getDate(int year, int month, int day, int hour, int minutes, int seconds) {
        return new DateFactory(year, month, day, hour, minutes, seconds).getTime();
    }

    public static Date getDate(java.util.TimeZone timezone) {
        return new DateFactory(timezone).getTime();
    }

    public static DateFactory getDateFactory(long milis) {
        return new DateFactory(milis);
    }

    public static DateFactory getDateFactory() {
        return new DateFactory();
    }

    private DateFactory(String date, String format) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        this.setTimeInMillis(formatter.parse(date).getTime());
    }

    private DateFactory(String date, String format, String language) throws ParseException {
        Locale locale = new Locale(language, "");
        SimpleDateFormat formatter = new SimpleDateFormat(format, locale);
        this.setTimeInMillis(formatter.parse(date).getTime());
    }

    private DateFactory(int hours, int minutes) {
        super();
        setHours(hours);
        setMinutes(minutes);
        setSeconds(0);
    }

    private DateFactory(int year, int month, int day) {
        super(year, month - 1, day);
    }

    private DateFactory(DateFactory date) {
        setTimeInMillis(date.getTimeInMillis());
    }

    private DateFactory(int year, int month, int day, int hour, int minutes) {
        super(year, month - 1, day, hour, minutes);
    }


    private DateFactory(int year, int month, int day, int hour, int minutes, int seconds) {
        super(year, month - 1, day, hour, minutes, seconds);
    }


    private DateFactory(long milis) {
        super();
        setTime(new java.util.Date(milis));
    }


    private DateFactory(String hostDate) {
        super();
        setYear(Integer.parseInt(hostDate.substring(0, 4)));
        setMonth(Integer.parseInt(hostDate.substring(4, 6)));
        setDay(Integer.parseInt(hostDate.substring(6, 8)));
        setHours(0);
        setMinutes(0);
        setSeconds(0);
    }


    private DateFactory(java.util.TimeZone timezone) {
        super(timezone);
    }


    public boolean after(DateFactory when) {
        return getTimeInMillis() > when.getTimeInMillis();
    }


    public boolean afterIgnoreTime(DateFactory when) {
        DateFactory thisTime = (DateFactory) clone();
        DateFactory otherTime = (DateFactory) when.clone();
        thisTime.setHours(0);
        thisTime.setMinutes(0);
        thisTime.setSeconds(0);
        otherTime.setHours(0);
        otherTime.setMinutes(0);
        otherTime.setSeconds(0);
        if ((thisTime.getTimeMillis() / 1000) > (otherTime.getTimeMillis() / 1000))
            return true;
        return false;
    }

    public boolean before(DateFactory when) {
        return getTimeInMillis() < when.getTimeInMillis();
    }


    public boolean beforeIgnoreTime(DateFactory when) {
        DateFactory thisTime = (DateFactory) clone();
        DateFactory otherTime = (DateFactory) when.clone();
        thisTime.setHours(0);
        thisTime.setMinutes(0);
        thisTime.setSeconds(0);
        otherTime.setHours(0);
        otherTime.setMinutes(0);
        otherTime.setSeconds(0);
        if ((thisTime.getTimeMillis() / 1000) < (otherTime.getTimeMillis() / 1000))
            return true;
        return false;
    }

    public static void main(String[] args) throws ParseException {
        DateFactory the = new DateFactory();
        DateFactory min = the.getDaysPrior(1);
        DateFactory max = the.getDaysLater(1);
        System.out.println("min " + min);
        System.out.println("the " + the);
        System.out.println("max " + max);
        System.out.println(the.betweenIgnoreTime(min, max));
    }

    private int findMonthDiff(DateFactory secondDate) {
        int diff = 0;
        diff += (secondDate.getYear() - getYear()) * 12;
        diff += secondDate.getMonth() - getMonth();
        return diff;
    }


    private int findYearDiff(DateFactory secondDate) {
        return secondDate.getYear() - getYear();
    }

    public String getDateString() {
        return getDateString("dd/MM/yyyy", "en");
    }

    public String format() {
        return format("dd/MM/yyyy", "en");
    }


    public String getDateString(String pattern) {
        return getDateString(pattern, "en");
    }

    public String format(String pattern) {
        return format(pattern, "en");
    }


    public String getDateString(String pattern, String language) {
        java.util.Locale l = new java.util.Locale(language, "");
        java.text.SimpleDateFormat s = new java.text.SimpleDateFormat(pattern, l);
        return s.format(getTime());
    }

    public String format(String pattern, String language) {
        java.util.Locale l = new java.util.Locale(language, "");
        java.text.SimpleDateFormat s = new java.text.SimpleDateFormat(pattern, l);
        return s.format(getTime());
    }


    public String getDateString(String pattern, String language, int timeZoneOffset) {
        java.util.Locale l = new java.util.Locale(language, "");
        java.text.SimpleDateFormat s = new java.text.SimpleDateFormat(pattern, l);
        return s.format(getTime());
    }

    public String format(String pattern, String language, int timeZoneOffset) {
        java.util.Locale l = new java.util.Locale(language, "");
        java.text.SimpleDateFormat s = new java.text.SimpleDateFormat(pattern, l);
        return s.format(getTime());
    }


    public String getDateString(String pattern, String language, java.util.TimeZone tz) {
        java.util.Locale l = new java.util.Locale(language, "");
        java.text.SimpleDateFormat s = new java.text.SimpleDateFormat(pattern, l);
        return s.format(getTime());
    }

    public String format(String pattern, String language, java.util.TimeZone tz) {
        java.util.Locale l = new java.util.Locale(language, "");
        java.text.SimpleDateFormat s = new java.text.SimpleDateFormat(pattern, l);
        return s.format(getTime());
    }


    public int getDateYMD() {
        return new Integer(getDateYMDString()).intValue();
    }


    public String getDateYMDString() {
        return getYearString() + getMonthString() + getDayString();
    }


    public int getDay() {
        return get(5);
    }


    public int getDayOfWeek() {
        if (get(7) == 1)
            return 7;
        return get(7) - 1;
    }


    public String getDayOfWeekLiteral() {
        return format("EEE");
    }

    public String getDayOfWeekLongEn() {
        return format("EEEE");
    }


    public String getDayOfWeekLongTr() {
        return format("EEEE", "tr");
    }


    public DateFactory getDaysLater(int forward) {
        DateFactory newDate = new DateFactory(getYear(), getMonth(), getDay(), getHours(), getMinutes());
        newDate.setDay(getDay() + forward);
        return newDate;
    }


    public DateFactory getDaysPrior(int backward) {
        DateFactory newDate = new DateFactory(getYear(), getMonth(), getDay(), getHours(), getMinutes());
        newDate.setDay(getDay() - backward);
        return newDate;
    }


    public String getDayString() {
        String day = String.valueOf(getDay());
        if (day.length() == 1)
            day = "0" + day;
        return day;
    }


    public int getHours() {
        return get(11);
    }


    public DateFactory getHoursLater(int hour) {
        int newHour = getHours() + hour;
        return new DateFactory(getYear(), getMonth(), getDay(), newHour, getMinutes());
    }

    public int getLastDayOfMonth(int month) {
        if (month == 2)
            if (isLeapYear(getYear()))
                return 29;
            else
                return 28;
        else if (month == 4 || month == 6 || month == 9 || month == 11)
            return 30;
        else
            return 31;
    }


    public int getMinutes() {
        return get(12);
    }


    public DateFactory getMinutesLater(int minute) {
        int newMinute = getMinutes() + minute;
        return new DateFactory(getYear(), getMonth(), getDay(), getHours(), newMinute);
    }


    public int getMonth() {
        return get(2) + 1;
    }


    public String getMonthLiteral() {
        return format("MMM");
    }


    public static String getMonthLiteral(int month) {
        return getMonthLiteral(month, "en");
    }


    public static String getMonthLiteral(int month, String language) {
        java.util.Locale l = new java.util.Locale(language, "");
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMM", l);
        return sdf.format(new DateFactory(2001, month, 1).getTime());
    }

    public static String getMonthLong(int month, String language) {
        java.util.Locale l = new java.util.Locale(language, "");
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMMM", l);
        return sdf.format(new DateFactory(2001, month, 1).getTime());
    }


    public String getMonthLongEn() {
        return format("MMMM");
    }


    public String getMonthLongTr() {
        return format("MMMM", "tr");
    }


    public static int getMonthNumeric(String month) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("MMM");
        return new DateFactory(format.parse(month).getTime()).getMonth();
    }


    public String getMonthString() {
        return format("MM");
    }


    public String getOracleDate() {
        return "TO_DATE('" + format("yyyy-MM-dd HH:mm") + "','YYYY-MM-dd HH24:MI')";
    }


    public String toOracleDate() {
        return "TO_DATE('" + format("yyyy-MM-dd HH:mm") + "','YYYY-MM-dd HH24:MI')";
    }


    public int getSeconds() {
        return get(13);
    }


    public long getTimeMillis() {
        return getTimeInMillis();
    }


    public String getTimeString() {
        return format("HH:mm");
    }


    public int getYear() {
        return get(1);
    }

    public String getYearString() {
        return format("yyyy");
    }


    public boolean isDateEqualTo(DateFactory anotherDate) {
        if (getYear() == anotherDate.getYear() && getMonth() == anotherDate.getMonth() && getDay() == anotherDate.getDay())
            return true;
        else
            return false;
    }

    public static boolean isValid(int year, int month, int day) {
        if (month == 2 && !((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) && day > 28)
            return false;
        else if (month == 2 && day > 29)
            return false;
        else if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30)
            return false;
        else if (day > 31)
            return false;
        if (month < 1 || month > 12)
            return false;
        return true;
    }


    public static boolean isValid(String year, String month, String day) {
        try {
            if (month.length() < 3)
                return isValid(Integer.valueOf(year.trim()).intValue(), Integer.valueOf(month.trim()).intValue(), Integer.valueOf(day.trim()).intValue());
            else
                return isValid(Integer.valueOf(year.trim()).intValue(), DateFactory.getMonthNumeric(month.trim()), Integer.valueOf(day.trim()).intValue());
        } catch (Exception e) {
            return false;
        }
    }


    public static boolean isValid(String dateString, String format) {
        try {
            parse(dateString, format);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void parseOracleDateString(String oracleDateString) throws NullPointerException {
        setDay(Integer.valueOf(oracleDateString.substring(8, 10).trim()).intValue());
        setMonth(Integer.valueOf(oracleDateString.substring(5, 7).trim()).intValue());
        setYear(Integer.valueOf(oracleDateString.substring(0, 4).trim()).intValue());
        setHours(Integer.valueOf(oracleDateString.substring(11, 13).trim()).intValue());
        setMinutes(Integer.valueOf(oracleDateString.substring(14, 16).trim()).intValue());
        setSeconds(Integer.valueOf(oracleDateString.substring(17, 19).trim()).intValue());
    }

    public void setDay(int day) {
        super.set(5, day);
    }

    public void setDaysBackward(int backward) {
        setDay(getDay() - backward);
    }


    public void setDaysForward(int forward) {
        setDay(getDay() + forward);
    }


    public void setHours(int hour) {
        set(11, hour);
    }


    public void setMinutes(int minutes) {
        set(12, minutes);
    }

    public void setMonth(int month) {
        super.set(2, month - 1);
    }

    public void setSeconds(int second) {
        set(13, second);
    }


    public void setYear(int year) {
        super.set(1, year);
    }

    public int timeDifference(String differenceTimeUnit, DateFactory otherDate) throws IllegalArgumentException {
        if (differenceTimeUnit == null)
            differenceTimeUnit = "";
        long dif = otherDate.getTimeInMillis() - getTimeInMillis();
        if (differenceTimeUnit.trim().equals(""))
            return (int) (dif);
        else if (differenceTimeUnit.toUpperCase().trim().startsWith("s"))
            return (int) (dif / 1000);
        else if (differenceTimeUnit.trim().startsWith("m"))
            return (int) (dif / 60000);
        else if (differenceTimeUnit.toUpperCase().trim().startsWith("H"))
            return (int) (dif / 3600000);
        else if (differenceTimeUnit.toUpperCase().trim().startsWith("D"))
            return (int) (dif / 86400000);
        else if (differenceTimeUnit.trim().startsWith("M"))
            return findMonthDiff(otherDate);
        else if (differenceTimeUnit.toUpperCase().trim().startsWith("Y"))
            return findYearDiff(otherDate);
        else {
            IllegalArgumentException ar = new IllegalArgumentException("TimeUnit not recognized");
            throw ar;
        }
    }

    public int timeDifferenceAbs(String differenceTimeUnit, DateFactory otherDate) throws IllegalArgumentException {
        return Math.abs(timeDifference(differenceTimeUnit, otherDate));
    }


    public boolean between(DateFactory d1, DateFactory d2) {
        if (d2.before(d1)) {
            DateFactory temp = d1;
            d1 = new DateFactory(d2.getTimeInMillis());
            d2 = temp;
        }
        if (before(d2) && after(d1))
            return true;
        else
            return false;
    }

    public boolean betweenIgnoreTime(DateFactory d1, DateFactory d2) {
        if (d2.before(d1)) {
            DateFactory temp = d1;
            d1 = new DateFactory(d2.getTimeInMillis());
            d2 = temp;
        }
        DateFactory thisTime = (DateFactory) clone();
        DateFactory time1 = (DateFactory) d1.clone();
        DateFactory time2 = (DateFactory) d2.clone();
        time1.setHours(0);
        time1.setMinutes(0);
        time1.setSeconds(0);
        time2.setHours(23);
        time2.setMinutes(59);
        time2.setSeconds(59);
        if ((thisTime.getTimeMillis() / 1000) > (time1.getTimeMillis() / 1000) && (thisTime.getTimeMillis() / 1000) < (time2.getTimeMillis() / 1000))
            return true;
        return false;
    }

    public boolean betweenIgnoreTime(DateFactory d1, DateFactory d2, boolean inclusive) {
        if (d2.before(d1)) {
            DateFactory temp = d1;
            d1 = new DateFactory(d2.getTimeInMillis());
            d2 = temp;
        }
        DateFactory thisTime = (DateFactory) clone();
        DateFactory time1 = (DateFactory) d1.clone();
        DateFactory time2 = (DateFactory) d2.clone();
        time1.setHours(0);
        time1.setMinutes(0);
        time1.setSeconds(0);
        time2.setHours(23);
        time2.setMinutes(59);
        time2.setSeconds(59);
        if (inclusive) {
            time1.setDaysBackward(1);
            time2.setDaysForward(1);
        }
        if ((thisTime.getTimeMillis() / 1000) > (time1.getTimeMillis() / 1000) && (thisTime.getTimeMillis() / 1000) < (time2.getTimeMillis() / 1000))
            return true;
        return false;
    }

    public static DateFactory parse(String dateString, String pattern) throws ParseException {
        return parse(dateString, pattern, "en");
    }

    public static DateFactory parse(String dateString, String pattern, String language) throws ParseException {
        SimpleDateFormat s = new SimpleDateFormat(pattern, new Locale(language, ""));
        return new DateFactory(s.parse(dateString).getTime());
    }


    public String toXML() {
        return String.valueOf(getTimeMillis());
    }


}

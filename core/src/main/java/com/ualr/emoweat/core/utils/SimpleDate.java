package  com.ualr.emoweat.core.utils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
* @author - Tolgahan CAKALOGLU "Jackalhan"
*/


public class SimpleDate implements XMLObject, Comparable, Serializable
{

    private Date date;
    /**
     * Insert the method's description here.
     * Creation date: (3/28/01 8:54:50 AM)
     * @return java.util.Date
     */
    public java.util.Date getDate()
    {
        return date;
    }
    /**
     * SimpleDate constructor comment.
     */
    public SimpleDate(int year, int month, int day)
    {
        setDate(DateFactory.getDate(year, month, day));
    }
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
    /**
     * Insert the method's description here.
     * Creation date: (12/3/00 1:32:13 PM)
     */
    public SimpleDate()
    {
        setDate(new Date());
    }
    public SimpleDate(String date, String format) throws ParseException
    {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        setDate(formatter.parse(date));
    }
    public SimpleDate(String date, String format, String language) throws ParseException
    {
        Locale locale = new Locale(language, "");
        SimpleDateFormat formatter = new SimpleDateFormat(format, locale);
        setDate(formatter.parse(date));
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/3/00 1:32:13 PM)
     */
    public SimpleDate(int hours, int minutes)
    {
        setDate(DateFactory.getDate(hours, minutes));
    }
    public SimpleDate(SimpleDate date)
    {
        setDate(new Date(date.getTimeMillis()));
    }
    /**
     * SimpleDate constructor comment.
     */
    public SimpleDate(int year, int month, int day, int hour, int minutes)
    {
        setDate(DateFactory.getDate(year, month, day, hour, minutes));
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/3/00 1:32:13 PM)
     */
    public SimpleDate(long milis)
    {
        setDate(new Date(milis));
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/3/00 1:32:13 PM)
     */
    public SimpleDate(String hostDate)
    {
        setDate(DateFactory.getDate(Integer.parseInt(hostDate.substring(0, 4)), Integer.parseInt(hostDate.substring(4, 6)), Integer.parseInt(hostDate.substring(6, 8)), 0, 0, 0));
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/3/00 1:32:13 PM)
     */
    public SimpleDate(java.util.TimeZone timezone)
    {
        setDate(DateFactory.getDate(timezone));
    }
    /**
     * Tests if this date is after the specified date.
     *
     * @param   when   a date.
     * @return  <code>true</code> if this date is after the argument date;
     *          <code>false</code> otherwise.
     * @since   JDK1.0
     */
    public boolean after(SimpleDate when)
    {
        return getTimeMillis() > when.getTimeMillis();
    }
    public long getTimeInMillis()
    {
        return getTimeMillis();
    }
    /**
     * Tests if this date is after the specified date.
     *
     * @param   when   a date.
     * @return  <code>true</code> if this date is after the argument date;
     *          <code>false</code> otherwise.
     * @since   JDK1.0
     */
    public boolean afterIgnoreTime(SimpleDate when)
    {
        SimpleDate thisTime = new SimpleDate(this);
        SimpleDate otherTime = new SimpleDate(when);
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
    /**
     * Tests if this date is before the specified date.
     *
     * @param   when   a date.
     * @return  <code>true</code> if this date is before the argument date;
     *          <code>false</code> otherwise.
     * @since   JDK1.0
     */
    public boolean before(SimpleDate when)
    {
        return getTimeMillis() < when.getTimeMillis();
    }
    /**
     * Tests if this date is before the specified date.
     *
     * @param   when   a date.
     * @return  <code>true</code> if this date is before the argument date;
     *          <code>false</code> otherwise.
     * @since   JDK1.0
     */
    public boolean beforeIgnoreTime(SimpleDate when)
    {
        SimpleDate thisTime = new SimpleDate(this);
        SimpleDate otherTime = new SimpleDate(when);
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

    private int findMonthDiff(SimpleDate secondDate)
    {
        int diff = 0;
        diff += (secondDate.getYear() - getYear()) * 12;
        diff += secondDate.getMonth() - getMonth();
        return diff;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/30/01 2:41:24 PM)
     * @return int
     */
    private int findYearDiff(SimpleDate secondDate)
    {
        return secondDate.getYear() - getYear();
    }
    /**
     * Insert the method's description here.
     * Creation date: (7/19/00 10:07:59 AM)
     * @return java.lang.String
     * @deprecated use format() instead
     */
    public String getDateString()
    {
        return getDateString("dd/MM/yyyy", "en");
    }
    public String format()
    {
        return format("dd/MM/yyyy", "en");
    }
    /**
     * Insert the method's description here.
     * Creation date: (7/19/00 10:07:59 AM)
     * @return java.lang.String
     * @deprecated use format() instead
     */
    public String getDateString(String pattern)
    {
        return getDateString(pattern, "en");
    }
    public String format(String pattern)
    {
        return format(pattern, "en");
    }
    /**
     * Insert the method's description here.
     * Creation date: (7/19/00 10:07:59 AM)
     * @return java.lang.String
     * @deprecated use format() instead
     */
    public String getDateString(String pattern, String language)
    {
        java.util.Locale l = new java.util.Locale(language, "");
        java.text.SimpleDateFormat s = new java.text.SimpleDateFormat(pattern, l);
        return s.format(getDate());
    }
    public String format(String pattern, String language)
    {
        java.util.Locale l = new java.util.Locale(language, "");
        java.text.SimpleDateFormat s = new java.text.SimpleDateFormat(pattern, l);
        return s.format(getDate());
    }
    /**
     * Insert the method's description here.
     * Creation date: (7/19/00 10:07:59 AM)
     * @return java.lang.String
     * @deprecated use format() instead
     */
    public String getDateString(String pattern, String language, int timeZoneOffset)
    {
        java.util.Locale l = new java.util.Locale(language, "");
        java.text.SimpleDateFormat s = new java.text.SimpleDateFormat(pattern, l);
        return s.format(getDate());
    }
    public String format(String pattern, String language, int timeZoneOffset)
    {
        java.util.Locale l = new java.util.Locale(language, "");
        java.text.SimpleDateFormat s = new java.text.SimpleDateFormat(pattern, l);
        return s.format(getDate());
    }
    /**
     * Insert the method's description here.
     * Creation date: (7/19/00 10:07:59 AM)
     * @return java.lang.String
     * @deprecated use format() instead
     */
    public String getDateString(String pattern, String language, java.util.TimeZone tz)
    {
        java.util.Locale l = new java.util.Locale(language, "");
        java.text.SimpleDateFormat s = new java.text.SimpleDateFormat(pattern, l);
        return s.format(getDate());
    }
    public String format(String pattern, String language, java.util.TimeZone tz)
    {
        java.util.Locale l = new java.util.Locale(language, "");
        java.text.SimpleDateFormat s = new java.text.SimpleDateFormat(pattern, l);
        return s.format(getDate());
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/20/00 11:23:17 AM)
     * @return int
     * @deprecated
     */
    public int getDateYMD()
    {
        return new Integer(getDateYMDString()).intValue();
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/20/00 11:23:17 AM)
     * @return int
     * @deprecated
     */
    public String getDateYMDString()
    {
        return getYearString() + getMonthString() + getDayString();
    }
    /**
     * Insert the method's description here.
     * Creation date: (7/19/00 4:02:30 PM)
     * @return int
     */
    public int getDay()
    {
        return DateFactory.getDateFactory(getTimeMillis()).getDay();
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/15/00 2:17:42 PM)
     * @return int
     */
    public int getDayOfWeek()
    {
        return DateFactory.getDateFactory(getTimeMillis()).getDayOfWeek();
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/15/00 2:17:42 PM)
     * @return int
     * @deprecate
     */
    public String getDayOfWeekLiteral()
    {
        return format("EEE");
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/15/00 2:17:42 PM)
     * @return int
     * @deprecate
     */
    public String getDayOfWeekLongEn()
    {
        return format("EEEE");
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/15/00 2:17:42 PM)
     * @return int
     * @deprecate
     */
    public String getDayOfWeekLongTr()
    {
        return format("EEEE", "tr");
    }
    /**
     * Insert the method's description here.
     * Creation date: (9/20/00 1:16:58 PM)
     * @return int
     */
    public SimpleDate getDaysLater(int forward)
    {
        SimpleDate newDate = new SimpleDate(this);
        newDate.setDay(getDay() + forward);
        return newDate;
    }
    /**
     * Insert the method's description here.
     * Creation date: (9/20/00 1:16:58 PM)
     * @return int
     */
    public SimpleDate getDaysPrior(int backward)
    {
        SimpleDate newDate = new SimpleDate(this);
        newDate.setDay(getDay() - backward);
        return newDate;
    }
    /**
     * Insert the method's description here.
     * Creation date: (7/19/00 4:02:30 PM)
     * @return int
     */
    public String getDayString()
    {
        return format("dd");
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/27/01 2:08:34 PM)
     * @return int
     */
    public int getHours()
    {
        return DateFactory.getDateFactory(getTimeMillis()).getHours();
    }
    /**
     * Insert the method's description here.
     * Creation date: (7/18/00 2:14:48 PM)
     * @return int
     */
    public SimpleDate getHoursLater(int hour)
    {
        SimpleDate newDate = new SimpleDate(this);
        newDate.setHours(getHours() + hour);
        return newDate;
    }
    /**
     * monthnsert the method's descrmonthptmonthon here.
     * Creatmonthon date: (9/23/01 8:30:15 PM)
     * @return monthnt
     * @param month monthnt
     */
    public int getLastDayOfMonth(int month)
    {
        if (month == 2)
            if (DateFactory.getDateFactory(getTimeMillis()).isLeapYear(getYear()))
                return 29;
            else
                return 28;
        else if (month == 4 || month == 6 || month == 9 || month == 11)
            return 30;
        else
            return 31;
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/27/01 2:09:09 PM)
     * @return int
     */
    public int getMinutes()
    {
        return DateFactory.getDateFactory(getTimeMillis()).getMinutes();
    }
    /**
     * Insert the method's description here.
     * Creation date: (7/18/00 2:14:48 PM)
     * @return int
     */
    public SimpleDate getMinutesLater(int minute)
    {
        int newMinute = getMinutes() + minute;
        return new SimpleDate(getYear(), getMonth(), getDay(), getHours(), newMinute);
    }
    /**
     * Insert the method's description here.
     * Creation date: (7/18/00 2:14:48 PM)
     * @return int
     */
    public int getMonth()
    {
        return DateFactory.getDateFactory(getTimeMillis()).getMonth();
    }
    /**
     * Insert the method's description here.
     * Creation date: (7/18/00 2:14:48 PM)
     * @return int
     * @deprecated
     */
    public String getMonthLiteral()
    {
        return format("MMM");
    }
    /**
     * Insert the method's description here.
     * Creation date: (7/18/00 2:14:48 PM)
     * @return int
     * @deprecated
     */
    public static String getMonthLiteral(int month)
    {
        return getMonthLiteral(month, "en");
    }
    /**
     * Insert the method's description here.
     * Creation date: (7/18/00 2:14:48 PM)
     * @return int
     * @deprecated
     */
    public static String getMonthLiteral(int month, String language)
    {
        java.util.Locale l = new java.util.Locale(language, "");
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMM", l);
        return sdf.format(DateFactory.getDate(2001, month, 1));
    }
    /**
     * Insert the method's description here.
     * Creation date: (7/18/00 2:14:48 PM)
     * @return int
     * @deprecated
     */
    public static String getMonthLong(int month, String language)
    {
        java.util.Locale l = new java.util.Locale(language, "");
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("MMMM", l);
        return sdf.format(DateFactory.getDate(2001, month, 1));
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/00 8:44:45 AM)
     * @deprecate
     */
    public String getMonthLongEn()
    {
        return format("MMMM");
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/2/00 8:44:45 AM)
     */
    public String getMonthLongTr()
    {
        return format("MMMM", "tr");
    }
    /**
     * Insert the method's description here.
     * Creation date: (7/18/00 2:14:48 PM)
     * @return int
     */
    public static int getMonthNumeric(String month) throws ParseException
    {
        SimpleDateFormat format = new SimpleDateFormat("MMM");
        return new SimpleDate(format.parse(month).getTime()).getMonth();
    }
    /**
     * Insert the method's description here.
     * Creation date: (7/18/00 2:14:48 PM)
     * @return int
     * @deprecated use format instead
     */
    public String getMonthString()
    {
        return format("MM");
    }
    /**
     * @deprecated use toOracleDate() instead
     * Insert the method's description here.
     * Creation date: (7/19/00 10:59:50 AM)
     * @return java.lang.String
     *
     */
    public String getOracleDate()
    {
        return "TO_DATE('" + format("yyyy-MM-dd HH:mm") + "','YYYY-MM-dd HH24:MI')";
    }
    /**
     * Insert the method's description here.
     * Creation date: (7/19/00 10:59:50 AM)
     * @return java.lang.String
     *
     */
    public String toOracleDate()
    {
        return "TO_DATE('" + format("yyyy-MM-dd HH:mm") + "','YYYY-MM-dd HH24:MI')";
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/27/01 2:10:47 PM)
     * @return int
     */
    public int getSeconds()
    {
        return DateFactory.getDateFactory(getTimeMillis()).getSeconds();
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/28/01 3:27:07 PM)
     * @return long
     */
    public long getTimeMillis()
    {
        return getDate().getTime();
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/4/00 10:39:15 AM)
     * @return java.lang.String
     * @deprecated
     */
    public String getTimeString()
    {
        return format("HH:mm");
    }
    /**
     * Insert the method's description here.
     * Creation date: (7/18/00 2:15:45 PM)
     * @return int
     */
    public int getYear()
    {
        return DateFactory.getDateFactory(getTimeMillis()).getYear();
    }
    /**
     * Insert the method's description here.
     * Creation date: (7/18/00 2:15:45 PM)
     * @return int
     */
    public String getYearString()
    {
        return format("yyyy");
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/9/00 11:19:39 AM)
     * @return boolean
     */
    public boolean isDateEqualTo(SimpleDate anotherDate)
    {
        if (getYear() == anotherDate.getYear() && getMonth() == anotherDate.getMonth() && getDay() == anotherDate.getDay())
            return true;
        else
            return false;
    }
    /**
     * Insert the method's description here.
     * Creation date: (11/1/00 2:21:24 PM)
     * @param year int
     * @param month int
     * @param day int
     */
    public static boolean isValid(int year, int month, int day)
    {
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
    /**
     * Insert the method's description here.
     * Creation date: (11/1/00 2:21:24 PM)
     * @param year int
     * @param month int
     * @param day int
     */
    public static boolean isValid(String year, String month, String day)
    {
        try
        {
            if (month.length() < 3)
                return isValid(Integer.valueOf(year.trim()).intValue(), Integer.valueOf(month.trim()).intValue(), Integer.valueOf(day.trim()).intValue());
            else
                return isValid(Integer.valueOf(year.trim()).intValue(), SimpleDate.getMonthNumeric(month.trim()), Integer.valueOf(day.trim()).intValue());
        }
        catch (Exception e)
        {
            return false;
        }
    }
    public static boolean isValid(String dateString, String format)
    {
        try
        {
            parse(dateString, format);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    /**
     * Insert the method's description here.
     * Creation date: (7/19/00 8:44:13 AM)
     * @param oracleDateString java.lang.String
     * deprecated
     */
    public void parseOracleDateString(String oracleDateString) throws NullPointerException
    {
        setDay(Integer.valueOf(oracleDateString.substring(8, 10).trim()).intValue());
        setMonth(Integer.valueOf(oracleDateString.substring(5, 7).trim()).intValue());
        setYear(Integer.valueOf(oracleDateString.substring(0, 4).trim()).intValue());
        setHours(Integer.valueOf(oracleDateString.substring(11, 13).trim()).intValue());
        setMinutes(Integer.valueOf(oracleDateString.substring(14, 16).trim()).intValue());
        setSeconds(Integer.valueOf(oracleDateString.substring(17, 19).trim()).intValue());
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/3/00 1:40:51 PM)
     * @param day int
     */
    public void setDay(int day)
    {
        DateFactory df = DateFactory.getDateFactory(getTimeMillis());
        df.setDay(day);
        setDate(df.getTime());
    }
    /**
     * Insert the method's description here.
     * Creation date: (9/20/00 1:16:58 PM)
     * @return int
     */
    public void setDaysBackward(int backward)
    {
        setDay(getDay() - backward);
    }
    /**
     * Insert the method's description here.
     * Creation date: (9/20/00 1:16:58 PM)
     * @return int
     */
    public void setDaysForward(int forward)
    {
        setDay(getDay() + forward);
    }
    /**
     * Insert the method's description here.
     * Creation date: (3/27/01 1:49:58 PM)
     * @param hour int
     */
    public void setHours(int hour)
    {
        DateFactory df = DateFactory.getDateFactory(getTimeMillis());
        df.setHours(hour);
        setDate(df.getTime());
    }

    public void setMinutes(int minutes)
    {
        DateFactory df = DateFactory.getDateFactory(getTimeMillis());
        df.setMinutes(minutes);
        setDate(df.getTime());
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/3/00 1:39:55 PM)
     * @param month int
     */
    public void setMonth(int month)
    {
        DateFactory df = DateFactory.getDateFactory(getTimeMillis());
        df.setMonth(month);
        setDate(df.getTime());
    }

    public void setSeconds(int second)
    {
        DateFactory df = DateFactory.getDateFactory(getTimeMillis());
        df.setSeconds(second);
        setDate(df.getTime());
    }
    /**
     * Insert the method's description here.
     * Creation date: (12/3/00 1:38:34 PM)
     * @param year int
     */
    public void setYear(int year)
    {
        DateFactory df = DateFactory.getDateFactory(getTimeMillis());
        df.setYear(year);
        setDate(df.getTime());
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/11/01 2:51:09 PM)
     * @return int
     * @param differenceTimeUnit java.lang.String
     * @param otherDate tools.SimpleDate
     */
    public int timeDifference(String differenceTimeUnit, SimpleDate otherDate) throws IllegalArgumentException
    {
        if (differenceTimeUnit == null)
            differenceTimeUnit = "";
        long dif = otherDate.getTimeMillis() - getTimeMillis();
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
        else
        {
            IllegalArgumentException ar = new IllegalArgumentException("TimeUnit not recognized");
            throw ar;
        }
    }
    /**
     * Insert the method's description here.
     * Creation date: (1/11/01 2:51:09 PM)
     * @return int
     * @param differenceTimeUnit java.lang.String
     * @param otherDate tools.SimpleDate
     */
    public int timeDifferenceAbs(String differenceTimeUnit, SimpleDate otherDate) throws IllegalArgumentException
    {
        return Math.abs(timeDifference(differenceTimeUnit, otherDate));
    }
    /**
     * Insert the method's description here.
     * Creation date: (4/5/02 9:23:01 AM)
     * @return boolean
     * @param d1 com.jforce.tools.SimpleDate
     * @param d2 com.jforce.tools.SimpleDate
     *
     */
    public boolean between(SimpleDate d1, SimpleDate d2)
    {
        if (d2.before(d1))
        {
            SimpleDate temp = d1;
            d1 = new SimpleDate(d2.getTimeMillis());
            d2 = temp;
        }
        if (before(d2) && after(d1))
            return true;
        else
            return false;
    }
    /**
     * Insert the method's description here.
     * Creation date: (4/5/02 9:23:01 AM)
     * @return boolean
     * @param d1 com.jforce.tools.SimpleDate
     * @param d2 com.jforce.tools.SimpleDate
     * @deprecated
     */
    public boolean betweenIgnoreTime(SimpleDate d1, SimpleDate d2)
    {
        if (d2.before(d1))
        {
            SimpleDate temp = d1;
            d1 = new SimpleDate(d2.getTimeMillis());
            d2 = temp;
        }
        SimpleDate thisTime = new SimpleDate(this);
        SimpleDate time1 = new SimpleDate(d1);
        SimpleDate time2 = new SimpleDate(d2);
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
    public boolean betweenIgnoreTime(SimpleDate d1, SimpleDate d2, boolean inclusive)
    {
        if (d2.before(d1))
        {
            SimpleDate temp = d1;
            d1 = new SimpleDate(d2.getTimeMillis());
            d2 = temp;
        }
        SimpleDate thisTime = new SimpleDate(this);
        SimpleDate time1 = new SimpleDate(d1);
        SimpleDate time2 = new SimpleDate(d2);
        time1.setHours(0);
        time1.setMinutes(0);
        time1.setSeconds(0);
        time2.setHours(23);
        time2.setMinutes(59);
        time2.setSeconds(59);
        if (inclusive)
        {
            time1.setDaysBackward(1);
            time2.setDaysForward(1);
        }
        if ((thisTime.getTimeMillis() / 1000) > (time1.getTimeMillis() / 1000) && (thisTime.getTimeMillis() / 1000) < (time2.getTimeMillis() / 1000))
            return true;
        return false;
    }
    public static SimpleDate parse(String dateString, String pattern) throws ParseException
    {
        return parse(dateString, pattern, "en");
    }
    public static SimpleDate parse(String dateString, String pattern, String language) throws ParseException
    {
        SimpleDateFormat s = new SimpleDateFormat(pattern, new Locale(language, ""));
        return new SimpleDate(s.parse(dateString).getTime());
    }

    public String toXML()
    {
        return String.valueOf(getTimeMillis());
    }
    /**
     * @see java.lang.Comparable#compareTo(Object)
     */
    public int compareTo(Object otherDate)
    {
        if (getTimeMillis() > ((SimpleDate) otherDate).getTimeMillis())
            return 1;
        if (getTimeMillis() < ((SimpleDate) otherDate).getTimeMillis())
            return -1;
        return 0;
    }
    /**
     * Sets the date.
     * @param date The date to set
     */
    public void setDate(Date date)
    {
        this.date = date;
    }
    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return getDate().toString();
    }

}

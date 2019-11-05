package com.manuelrojas.fixture.data.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class DateTimeUtil {

    public static String getFullDateTime(String strDate) {
        DateTime dt = getDateTime(strDate);
        return dt.monthOfYear().getAsShortText() + " " + dt.dayOfMonth().getAsShortText()
                + ", " + dt.year().getAsText() + " at "
                + formatTimeDigits(dt.getHourOfDay())  + ":" + formatTimeDigits(dt.getMinuteOfHour());
    }

    public static String getMonth(String strDate) {
        return getDateTime(strDate).monthOfYear().getAsText();
    }

    public static String getYear(String strDate) {
        return getDateTime(strDate).year().getAsText();
    }

    public static String getDayOfWeekShort(String strDate) {
        return getDateTime(strDate).dayOfWeek().getAsShortText();
    }

    public static String getDayOfMonth(String strDate) {
        return getDateTime(strDate).dayOfMonth().getAsText();
    }

    private static String formatTimeDigits(int val) {
        return val<10 ? "0"+val : ""+val;
    }

    private static DateTime getDateTime(String strDate) {
        DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
        return fmt.parseDateTime(strDate);
//        return dt.withZone(DateTimeZone.forID("Europe/London"));
    }

    public static String convertSecondsToMin(String seconds) {
        int min = Integer.parseInt(seconds)/60;
        int sec = Integer.parseInt(seconds)%60;
        String pad = sec<10 ? "0" : "";
        return min + ":" + pad + sec;
    }

}

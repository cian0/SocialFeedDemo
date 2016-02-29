package user.com.sg.socialfeeddemo.utils;

import android.content.Context;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ianicasiano on 2/28/16.
 */
public class DateUtils {
    public static final String FORMAT_SERVER_DATE = "yyyy-MM-dd";
    public static final String FORMAT_SERVER_DATE_MILLIS = "yyyy-MM-dd HH:mm";
    private static DateFormat df = new SimpleDateFormat(FORMAT_SERVER_DATE);
    private static DateFormat dfMillis = new SimpleDateFormat(FORMAT_SERVER_DATE_MILLIS);

    public static Date toJavaDate(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(date);
    }

    public static String javaDateToString(Date date, String format) {
        DateFormat customFormat = new SimpleDateFormat(format);
        return customFormat.format(date);
    }


    public static String toServerDateFormat(Date date) {
        return df.format(date);
    }

    public static String toServerDateFormatMillis(Date date) {
        return dfMillis.format(date);
    }

    public static Date toServerDate(String date) throws ParseException {
        return df.parse(date);
    }

    public static String relativeTimeOfDate(Date date) {
        long now = System.currentTimeMillis();
        return android.text.format.DateUtils.getRelativeTimeSpanString(date.getTime(), now, android.text.format.DateUtils.DAY_IN_MILLIS).toString();
    }

    public static Date toServerDateHoursMillis(String date) throws ParseException {
        return dfMillis.parse(date);
    }

    private static int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) ||
                (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
            diff--;
        }
        Log.d("test", "age diff: " + diff);
        return diff;
    }

    private static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }

}

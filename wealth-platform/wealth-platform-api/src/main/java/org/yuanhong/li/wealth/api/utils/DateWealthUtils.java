package org.yuanhong.li.wealth.api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateWealthUtils {
	
	private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
	
	public static final String PATTERN_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
	
	/**
     * Formats the given date according to the RFC 1123 pattern.
     * 
     * @param date The date to format.
     * @return An RFC 1123 formatted date string.
     * 
     * @see #PATTERN_RFC1123
     */
    public static String formatRFC1123(Date date) {
        return formatDate(date, PATTERN_RFC1123);
    }

	public static String formatDate(Date date, String pattern) {
        if (date == null) throw new IllegalArgumentException("date is null");
        if (pattern == null) throw new IllegalArgumentException("pattern is null");
        
        SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.US);
        formatter.setTimeZone(GMT);
        return formatter.format(date);
    }
}

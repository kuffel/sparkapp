package eu.kuffel.sparkapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Helper functions belong here.
 *
 * @author kuffel
 * @version 06.01.2017
 */
public class AppCommons {

    /**
     * Convert a java.util.Calendar object to an ISO8601 UTC string.
     * @param calendar Calendar object or null if we want to convert now.
     * @return ISO8601 UTC string
     */
    public static String convertCalendarToISO8601( Calendar calendar ){
        if(calendar == null){
            calendar = GregorianCalendar.getInstance();
        }
        Date date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        String formatted = formatter.format(date);
        return formatted.substring(0, 22) + ":" + formatted.substring(22);
    }

    /**
     * Convert an ISO8601 UTC string to a Calendar object.
     * @param iso8601string ISO8601 UTC string
     * @return Calendar object
     */
    public static Calendar convertISO8610ToCalendar(String iso8601string ){
        Calendar calendar = GregorianCalendar.getInstance();
        String s = iso8601string.replace("Z", "+00:00");
        try {
            s = s.substring(0, 22) + s.substring(23);  // to get rid of the ":"
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = formatter.parse(s);
            calendar.setTime(date);
        }catch(IndexOutOfBoundsException e){
            //e.printStackTrace();
        } catch (ParseException e) {
            //e.printStackTrace();
        } finally {
            return calendar;
        }
    }


    /**
     * Convert an ISO8601 UTC string to an UNIX timestamp.
     * @param iso8601string ISO8601 UTC string
     * @return long UNIX timestamp
     */
    public static long convertISO8610ToTimestamp( String iso8601string ){
        if(iso8601string != null){
            Calendar calendar = convertISO8610ToCalendar(iso8601string);
            return calendar.getTimeInMillis()/1000;
        }
        return 0;
    }


}

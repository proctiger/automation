package uol.collective.offer.commons.test.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

public abstract class DateTimeUtil
{

    private static final long DAY_IN_MILLIS = ( 1000 * 60 * 60 * 24 );

    private static final String FORMAT_DAY_MONTH_YEAR = "dd/MM/yyyy";

    private static final String FORMAT_HIBERNATE = "dd-MMM-yy";

    private static final String FORMAT_XML = "yyyy-MM-dd'T'HH:mm:ss";

    public static String formatDateToString(final Date date, final String PATTERN)
    {
        DateFormat formatter = new SimpleDateFormat( PATTERN );
        return formatter.format( date );
    }

    public static String formatDateToStringXML(Date date)
    {
        DateFormat formatter = new SimpleDateFormat( FORMAT_XML );
        return formatter.format( date );
    }

    public static String formatDateToStringHibernate(Date date)
    {
        DateFormat formatter = new SimpleDateFormat( FORMAT_HIBERNATE );
        return formatter.format( date );
    }

    public static String formatDateToStringDayMonthYear(Date date)
    {
        DateFormat formatter = new SimpleDateFormat( FORMAT_DAY_MONTH_YEAR );
        return formatter.format( date );
    }

    public static String formatDateToSolr(Date date)
    {
        String dateTime;
        SimpleDateFormat formatter = new SimpleDateFormat();
        formatter.applyPattern( "yyyy-MM-dd" );
        String formattedDate = formatter.format( date );

        formatter.applyPattern( "HH:mm:ss" );
        String formattedTime = formatter.format( date );

        dateTime = formattedDate + "T" + formattedTime + "Z";
        return dateTime;
    }

    public static Date addDays(Date date, int i)
    {
        if ( date != null )
        {
            Calendar dateAux = Calendar.getInstance();
            dateAux.setTime( date );
            dateAux.add( Calendar.DATE, i );
            return dateAux.getTime();
        }
        return date;
    }

    /**
     * Returns de number of days between two dates excluding the start and end. Example: 2012-12-01 and 2012-12-31 the return will be 29.
     * 
     * @param Date
     *            start
     * @param Date
     *            end
     * 
     * */
    public static int getDifferenceBetweenTwoDatesExclusive(Date start, Date end)
    {
        if ( start != null && end != null )
        {
            return (int) ( ( ( end.getTime() - start.getTime() ) - DAY_IN_MILLIS ) / DAY_IN_MILLIS );
        }
        return -1;
    }

    /**
     * Returns de number of days between two dates including the start and end. Example: 2012-12-01 and 2012-12-31 the return will be 31.
     * 
     * @param Date
     *            start
     * @param Date
     *            end
     * 
     * */
    public static int getDifferenceBetweenTwoDatesInclusive(Date start, Date end)
    {
        if ( start != null && end != null )
        {
            start = DateUtils.truncate( start, Calendar.DATE );
            end = DateUtils.truncate( end, Calendar.DATE );
            return (int) ( ( ( end.getTime() - start.getTime() ) + DAY_IN_MILLIS ) / DAY_IN_MILLIS );
        }
        return -1;
    }

    public static Date parse(final String DATE, final String PATTERN) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat( PATTERN );
        return sdf.parse( DATE );
    }

    public static Date parseFromXmlFormat(final String DATE) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat( FORMAT_XML );
        return sdf.parse( DATE );
    }
    
    public static Date parseFromHibernateFormat(final String DATE) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat( FORMAT_HIBERNATE );
        return sdf.parse( DATE );
    }
    
    public static Date parseFromDayMonthYearFormat(final String DATE) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat( FORMAT_DAY_MONTH_YEAR );
        return sdf.parse( DATE );
    }
    
    /**
     * Methods for Hibernate and XML-Services purposes.
     * 
     * */

    public static Date getAFarDateFromNow()
    {
        Calendar farDate = Calendar.getInstance();
        farDate.add( Calendar.YEAR, +5 );
        return farDate.getTime();
    }

    public static String getAFarDateFromNowInHibernateFormat()
    {
        return formatDateToStringHibernate( getAFarDateFromNow() );
    }

    public static String getAFarDateFromNowInXmlFormat()
    {
        return formatDateToStringXML( getAFarDateFromNow() );
    }

    public static String getCurrentDateInHibernateFormat()
    {
        return formatDateToStringHibernate( new Date() );
    }

    public static String getCurrentDateInXmlFormat()
    {
        return formatDateToStringXML( new Date() );
    }

    public static Date getAnOldDateFromNow()
    {
        Calendar oldDate = Calendar.getInstance();
        oldDate.add( Calendar.YEAR, -5 );
        return oldDate.getTime();
    }

    public static String getAnOldDateFromNowInHibernateFormat()
    {
        return formatDateToStringHibernate( getAnOldDateFromNow() );
    }

    public static String getAnOldDateFromNowInXmlFormat()
    {
        return formatDateToStringXML( getAnOldDateFromNow() );
    }

    public static Date getADateFromNow(int numberOfDaysFromNow)
    {
        Calendar date = Calendar.getInstance();
        date.add( Calendar.DATE, numberOfDaysFromNow - 1 );
        return date.getTime();
    }

    public static String getADateFromNowInXmlFormat(int numberOfDaysFromNow)
    {
        return formatDateToStringXML( getADateFromNow( numberOfDaysFromNow ) );
    }

}

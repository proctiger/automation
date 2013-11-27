package uol.test.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author rsilva
 * 
 */
public class DateTimeAdapter extends XmlAdapter<String, Date> {

	private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

    @Override
    public String marshal(Date arg) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.format(arg);
    }

    @Override
    public Date unmarshal(String arg) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        return sdf.parse(arg);
    }
}
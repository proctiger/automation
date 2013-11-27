package uol.adserversap.ws.test.http;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;

import uol.adserversap.ws.test.config.RemoteConfig;
import uol.adserversap.ws.test.prepare.DfpServiceFactory;
import uol.remote.admin.config.client.proxy.ProxyFactory;

import com.google.api.ads.dfp.axis.v201302.Date;
import com.google.api.ads.dfp.axis.v201302.DateTime;

public class DfpHttpRequest {

    protected static final String[] PAYMENT_CONDITIONS =
        {null, null, null, "DFI0", "DFIP", "DCU0", "DPB0", "DPR0", "DFII", "DFI0", "DFIP", "DCU0", "DPB0", "DPR0", "DFII", null};

    protected static final RemoteConfig remoteConfig = ProxyFactory.newInstance(RemoteConfig.class);
    protected static final Random random = new Random();
    protected static final DfpServiceFactory dfpServiceFactory;
    protected static final Properties dfpProperties;
    static {
        try {
            dfpProperties = new Properties();
            dfpProperties.load(new FileInputStream("config.properties"));

            dfpServiceFactory = new DfpServiceFactory(dfpProperties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected static java.util.Date toDate(DateTime dateTime) {
        if (dateTime == null) {
            return null;
        }

        try {
            final TimeZone tz = TimeZone.getTimeZone(dateTime.getTimeZoneID());
            final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            sdf.setTimeZone(tz);

            return sdf.parse(StringUtils.leftPad(dateTime.getDate().getDay().toString(), 2, '0')
                    + "/" + StringUtils.leftPad(dateTime.getDate().getMonth().toString(), 2, '0')
                    + "/" + dateTime.getDate().getYear());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected static DateTime toDateTime(Calendar cal) {
        return new DateTime(new Date(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH)),
                cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND), cal.getTimeZone().getID());
    }
}

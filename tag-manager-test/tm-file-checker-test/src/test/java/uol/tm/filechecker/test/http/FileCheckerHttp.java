package uol.tm.filechecker.test.http;

import java.util.concurrent.TimeUnit;
import uol.simple.httpclient.SimpleHttpClient;
import uol.simple.httpclient.SimpleHttpRequest;
import uol.simple.httpclient.SimpleHttpResponse;
import uol.tm.filechecker.test.utils.Constants;

/**
 *
 * @author dvrocha
 */
public class FileCheckerHttp {

    private static final String FILE_CHECKER_DOMAIN = "http://" + Constants.FILE_CHECKER_INSTANCE + ":42444/tm-file-checker";
    private static final int REQUEST_TIMEOUT = 5;

    public static SimpleHttpResponse getProbe(String probeType) throws Exception {
        SimpleHttpRequest request = SimpleHttpClient.newGetRequest(FILE_CHECKER_DOMAIN, probeType);
        return SimpleHttpClient.executeAvoidingSSL(request, REQUEST_TIMEOUT, TimeUnit.SECONDS);
    }
}

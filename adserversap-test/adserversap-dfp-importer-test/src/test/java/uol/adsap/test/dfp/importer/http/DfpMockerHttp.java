package uol.adsap.test.dfp.importer.http;

import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import uol.simple.httpclient.SimpleHttpClient;
import uol.simple.httpclient.SimpleHttpRequest;
import uol.simple.httpclient.SimpleHttpResponse;

/**
 *
 * @author dvrocha
 *
 */
public class DfpMockerHttp {

    private static final String DFP_MOCKER_DOMAIN = "http://a1-lagarta-q-prt1:48080";
    private static final String DFP_MOCKER_REPORT_SERVICE = "apis/ads/publisher/v201302/ReportService";
    private static final String DFP_MOCKER_REPORT_DOWNLOAD = "apis/ads/publisher/ReportDownload";
    private static final String DFP_MOCKER_REPORT_DOWNLOAD_CONTENT = DFP_MOCKER_REPORT_DOWNLOAD + "/content";

    private static final Integer REMOTE_TEST_TIMEOUT_SEC = 20;

    public static SimpleHttpResponse putReportService(String file) throws Exception {
        SimpleHttpRequest request = null;
        if(file != null) {
            request = SimpleHttpClient.newPutRequest(DFP_MOCKER_DOMAIN, DFP_MOCKER_REPORT_SERVICE, "getReportJob", "file", file);
        } else {
            request = SimpleHttpClient.newPutRequest(DFP_MOCKER_DOMAIN, DFP_MOCKER_REPORT_SERVICE, "getReportJob");
        }

        return SimpleHttpClient.execute(request, REMOTE_TEST_TIMEOUT_SEC, TimeUnit.SECONDS);
    }

    public static SimpleHttpResponse putReportServiceComplete() throws Exception {
        return putReportService("getReportJob");
    }

    public static SimpleHttpResponse putSlowReportDownload(int timeToAnswer) throws Exception {
        SimpleHttpRequest request = SimpleHttpClient.newPutRequest(DFP_MOCKER_DOMAIN, DFP_MOCKER_REPORT_DOWNLOAD, "sleep", String.valueOf(timeToAnswer));
        return SimpleHttpClient.execute(request, REMOTE_TEST_TIMEOUT_SEC, TimeUnit.SECONDS);
    }

    public static SimpleHttpResponse putReportServiceInProgress() throws Exception {
        return putReportService("getReportJob_inProgress");
    }

    public static SimpleHttpResponse putReportServiceFailed() throws Exception {
        return putReportService("getReportJob_failed");
    }

    public static SimpleHttpResponse deleteGetReportService() throws Exception {
        final SimpleHttpRequest request = SimpleHttpClient.newDeleteRequest(
                        DFP_MOCKER_DOMAIN, DFP_MOCKER_REPORT_SERVICE, "getReportJob");

        return SimpleHttpClient.execute(request, REMOTE_TEST_TIMEOUT_SEC,
                        TimeUnit.SECONDS);
    }

    public static SimpleHttpResponse deleteRunReportService() throws Exception {
        final SimpleHttpRequest request = SimpleHttpClient.newDeleteRequest(
                        DFP_MOCKER_DOMAIN, DFP_MOCKER_REPORT_SERVICE, "runReportJob");

        return SimpleHttpClient.execute(request, REMOTE_TEST_TIMEOUT_SEC,
                        TimeUnit.SECONDS);
    }

    public static SimpleHttpResponse putReportDownload(String content) throws Exception {
        final SimpleHttpRequest request = SimpleHttpClient.newPutRequest(
                        DFP_MOCKER_DOMAIN, DFP_MOCKER_REPORT_DOWNLOAD_CONTENT);
        HttpEntity entity = new StringEntity(content, ContentType.TEXT_PLAIN);
        request.setEntity(entity);

        return SimpleHttpClient.execute(request, REMOTE_TEST_TIMEOUT_SEC,
                        TimeUnit.SECONDS);
    }

    public static SimpleHttpResponse deleteReportDownload() throws Exception {
        final SimpleHttpRequest request = SimpleHttpClient.newDeleteRequest(
                        DFP_MOCKER_DOMAIN, DFP_MOCKER_REPORT_DOWNLOAD_CONTENT);

        return SimpleHttpClient.execute(request, REMOTE_TEST_TIMEOUT_SEC,
                        TimeUnit.SECONDS);
    }

}

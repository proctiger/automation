package uol.bt.commons.test.helper;

import java.util.concurrent.TimeUnit;

import uol.simple.httpclient.SimpleHttpClient;
import uol.simple.httpclient.SimpleHttpRequest;
import uol.simple.httpclient.SimpleHttpResponse;

public class RemoteTestHelper {

    private static final Integer REMOTE_TEST_TIMEOUT_SEC = 60;

    public static SimpleHttpResponse cat(String remoteTestDomain, String filePath) throws Exception {
        final SimpleHttpRequest catRequest = SimpleHttpClient
                .newGetRequest(remoteTestDomain, "cat").
                addQueryParam("file", filePath);

        return SimpleHttpClient.execute(catRequest, REMOTE_TEST_TIMEOUT_SEC, TimeUnit.SECONDS);
    }

    public static SimpleHttpResponse shell(String remoteTestDomain, String command) throws Exception {
        final SimpleHttpRequest shellRequest = SimpleHttpClient
                .newGetRequest(remoteTestDomain, "shell")
                .addQueryParam("command", command)
                .addQueryParam("timeout", REMOTE_TEST_TIMEOUT_SEC.toString());

        return SimpleHttpClient.execute(shellRequest, REMOTE_TEST_TIMEOUT_SEC, TimeUnit.SECONDS);
    }

    public static SimpleHttpResponse telnet(String remoteTestDomain, String server, String port, String command, String timeout) throws Exception {
        final SimpleHttpRequest telnetRequest = SimpleHttpClient
                .newGetRequest(remoteTestDomain, "telnet")
                .addQueryParam("server", server)
                .addQueryParam("port", port)
                .addQueryParam("command", command)
                .addQueryParam("timeout", timeout);

        return SimpleHttpClient.execute(telnetRequest, REMOTE_TEST_TIMEOUT_SEC, TimeUnit.SECONDS);
    }

}

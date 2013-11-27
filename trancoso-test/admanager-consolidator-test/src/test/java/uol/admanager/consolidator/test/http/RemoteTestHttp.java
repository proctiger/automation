package uol.admanager.consolidator.test.http;

import java.util.concurrent.TimeUnit;

import uol.admanager.consolidator.test.reconf.AdmanagerConsolidatorTestConfig;
import uol.admanager.consolidator.test.reconf.RemoteConfig;
import uol.simple.httpclient.SimpleHttpClient;
import uol.simple.httpclient.SimpleHttpRequest;
import uol.simple.httpclient.SimpleHttpResponse;

public class RemoteTestHttp {

    private static final AdmanagerConsolidatorTestConfig config = RemoteConfig.getInstance();

    private static final String REMOTE_ADMIN_TEST_DOMAIN = config.getRemoteTestDomain();
    private static final Integer REMOTE_ADMIN_TEST_DEFAULT_PORT = 1910;
    private static final Integer REQUEST_TIMEOUT = 20;


    public static SimpleHttpResponse cat(String filePath) throws Exception {
        final SimpleHttpRequest catRequest = SimpleHttpClient
                .newGetRequest(REMOTE_ADMIN_TEST_DOMAIN, "cat")
                .addQueryParam("file", filePath);

        return SimpleHttpClient.execute(catRequest, REQUEST_TIMEOUT, TimeUnit.SECONDS);
    }

    public static SimpleHttpResponse shell(String host, String command) throws Exception {
        final String domain = String.format("http://%s:%s/remote-admin-test", host, REMOTE_ADMIN_TEST_DEFAULT_PORT);

        return executeShell(domain, command);
    }

    public static SimpleHttpResponse shell(String command) throws Exception {
        return executeShell(REMOTE_ADMIN_TEST_DOMAIN, command);
    }

    private static SimpleHttpResponse executeShell(String domain, String command) throws Exception {
        final SimpleHttpRequest shellRequest = SimpleHttpClient
                .newGetRequest(domain, "shell")
                .addQueryParam("command", command)
                .addQueryParam("timeout", REQUEST_TIMEOUT.toString());

        return SimpleHttpClient.execute(shellRequest, REQUEST_TIMEOUT, TimeUnit.SECONDS);
    }

    public static SimpleHttpResponse telnet(String server, String port, String command, String timeout) throws Exception {
        final SimpleHttpRequest telnetRequest = SimpleHttpClient.
                newGetRequest(REMOTE_ADMIN_TEST_DOMAIN, "telnet")
                .addQueryParam("server", server)
                .addQueryParam("port", port)
                .addQueryParam("command", command)
                .addQueryParam("timeout", timeout);

        return SimpleHttpClient.execute(telnetRequest, REQUEST_TIMEOUT, TimeUnit.SECONDS);
    }
}

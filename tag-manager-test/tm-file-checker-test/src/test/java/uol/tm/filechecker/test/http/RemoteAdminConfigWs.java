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
public class RemoteAdminConfigWs {

    private static final String REMOTE_ADMIN_CONFIG_WS_DOMAIN = "http://config-ws.remote-admin.intranet/product/tag-manager/component/tm-file-checker/";
    private static final String REMOTE_ADMIN_CONFIG_WS_INSTANCE = "instance/" + Constants.FILE_CHECKER_INSTANCE;
    private static final String REMOTE_ADMIN_CONFIG_WS_USER = "tm-file-checker-test";
    private static final int REQUEST_TIMEOUT = 5;

    public static SimpleHttpResponse enableSvnCommit() throws Exception {
        return setProperty("commit.to.svn", "{\"value\":\"'true'\"}");
    }

    public static SimpleHttpResponse disableSvnCommit() throws Exception {
        return setProperty("commit.to.svn", "{\"value\":\"'false'\"}");
    }

    private static SimpleHttpResponse setProperty(String propertyName, String propertyValue) throws Exception {
        SimpleHttpRequest request = SimpleHttpClient.newPutRequest(
                REMOTE_ADMIN_CONFIG_WS_DOMAIN,
                "poperty" + propertyName,
                REMOTE_ADMIN_CONFIG_WS_INSTANCE);
        request.addQueryParam("application", REMOTE_ADMIN_CONFIG_WS_USER);
        request.setEntityAsJson(propertyValue);
        return SimpleHttpClient.execute(request, REQUEST_TIMEOUT, TimeUnit.SECONDS);
    }
}

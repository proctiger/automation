package uol.tm.stc.test.http;

import java.util.concurrent.TimeUnit;

import uol.simple.httpclient.SimpleHttpClient;
import uol.simple.httpclient.SimpleHttpRequest;
import uol.simple.httpclient.SimpleHttpResponse;

/**
 * 
 * @author dvrocha
 */
public class RemoteAdminTesterHttp {

	private static final String REMOTE_ADMIN_TEST_DOMAIN = "http://a1-tagmanager-s-prt2:1910/remote-admin-test";
	private static final Integer REMOTE_TEST_TIMEOUT_SEC = 20;

	public static SimpleHttpResponse cat(String filePath) throws Exception {
		final SimpleHttpRequest catRequest = SimpleHttpClient.newGetRequest(REMOTE_ADMIN_TEST_DOMAIN, "cat")
		        .addQueryParam("file", filePath);

		return SimpleHttpClient.execute(catRequest, REMOTE_TEST_TIMEOUT_SEC, TimeUnit.SECONDS);
	}

	public static SimpleHttpResponse shell(String command) throws Exception {
		final SimpleHttpRequest shellRequest = SimpleHttpClient.newGetRequest(REMOTE_ADMIN_TEST_DOMAIN, "shell")
		        .addQueryParam("command", command).addQueryParam("timeout", REMOTE_TEST_TIMEOUT_SEC.toString());

		return SimpleHttpClient.execute(shellRequest, REMOTE_TEST_TIMEOUT_SEC, TimeUnit.SECONDS);
	}
}

package uol.bt.tracker.test.http;

import java.util.concurrent.TimeUnit;

import reconf.client.proxy.ConfigurationRepositoryFactory;
import uol.bt.tracker.test.reconf.ConfigBtTrackerTest;
import uol.simple.httpclient.SimpleHttpClient;
import uol.simple.httpclient.SimpleHttpRequest;
import uol.simple.httpclient.SimpleHttpResponse;

public class RemoteAdminTestHttp {

	private static final ConfigBtTrackerTest config = ConfigurationRepositoryFactory.get(ConfigBtTrackerTest.class);

	private static final String REMOTE_ADMIN_TEST_DOMAIN = config.getRemoteAdminTestDomain();
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

	public static SimpleHttpResponse telnet(String server, String port, String command, String timeout)
	        throws Exception {
		final SimpleHttpRequest telnetRequest = SimpleHttpClient.newGetRequest(REMOTE_ADMIN_TEST_DOMAIN, "telnet")
		        .addQueryParam("server", server).addQueryParam("port", port).addQueryParam("command", command)
		        .addQueryParam("timeout", timeout);

		return SimpleHttpClient.execute(telnetRequest, REMOTE_TEST_TIMEOUT_SEC, TimeUnit.SECONDS);
	}
}

package uol.adsap.test.dfp.importer.http;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uol.simple.httpclient.SimpleHttpClient;
import uol.simple.httpclient.SimpleHttpRequest;
import uol.simple.httpclient.SimpleHttpResponse;

/**
 *
 * @author dvrocha
 *
 */
public class RemoteTestHttp {
	private static final Logger logger = LoggerFactory.getLogger(RemoteTestHttp.class);

	private static final String REMOTE_ADMIN_TEST_DOMAIN = "http://a1-lagarta-q-prt1:1910/remote-admin-test";
	private static final Integer REMOTE_TEST_TIMEOUT_SEC = 20;

	public static SimpleHttpResponse cat(String filePath) throws Exception {
		logger.debug("Efetuando cat do arqivo: {}", filePath);

		final SimpleHttpRequest catRequest = SimpleHttpClient.newGetRequest(
				REMOTE_ADMIN_TEST_DOMAIN, "cat")
				.addQueryParam("file", filePath);

		return SimpleHttpClient.execute(catRequest, REMOTE_TEST_TIMEOUT_SEC,
				TimeUnit.SECONDS);
	}

	public static SimpleHttpResponse shell(String command) throws Exception {
		logger.debug("Efetuando o seguinte commando: {}", command);

		final SimpleHttpRequest shellRequest = SimpleHttpClient
				.newGetRequest(REMOTE_ADMIN_TEST_DOMAIN, "shell")
				.addQueryParam("command", command)
				.addQueryParam("timeout", REMOTE_TEST_TIMEOUT_SEC.toString());

		return SimpleHttpClient.execute(shellRequest, REMOTE_TEST_TIMEOUT_SEC,
				TimeUnit.SECONDS);
	}
}

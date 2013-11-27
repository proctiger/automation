package uol.adsap.test.dfp.importer.http;

import java.util.concurrent.TimeUnit;

import uol.simple.httpclient.SimpleHttpClient;
import uol.simple.httpclient.SimpleHttpRequest;
import uol.simple.httpclient.SimpleHttpResponse;

/**
 *
 * @author dvrocha
 *
 */
public class ConfigWsHttp {

	private static final String CONFIG_WS_DOMAIN = "http://config-ws.remote-admin.intranet";
	private static final Integer REMOTE_TEST_TIMEOUT_SEC = 20;

	private static final String REMOTE_CONFIG_PRODUCT = "adserversap";
	private static final String REMOTE_CONFIG_COMPONENT = "adserversap-dfp-importer";
	private static final String REMOTE_CONFIG_APPLICATION = "ploc-tester";
	private static final String REMOTE_CONFIG_INSTANCE = "a1-lagarta-q-prt1";

	public static SimpleHttpResponse putProperty(String property, String value)
			throws Exception {
		final SimpleHttpRequest request = SimpleHttpClient.newPutRequest(
				CONFIG_WS_DOMAIN, "product", REMOTE_CONFIG_PRODUCT,
				"component", REMOTE_CONFIG_COMPONENT, "property", property,
				"instance", REMOTE_CONFIG_INSTANCE);
		request.addQueryParam("application", REMOTE_CONFIG_APPLICATION);
		request.setEntityAsJson("{\"value\":\"" + value + "\"}");

		return SimpleHttpClient.execute(request, REMOTE_TEST_TIMEOUT_SEC,
				TimeUnit.SECONDS);
	}

	public static SimpleHttpResponse removeProperty(String property)
			throws Exception {
		final SimpleHttpRequest request = SimpleHttpClient.newDeleteRequest(
				CONFIG_WS_DOMAIN, "product", REMOTE_CONFIG_PRODUCT,
				"component", REMOTE_CONFIG_COMPONENT, "property", property,
				"instance", REMOTE_CONFIG_INSTANCE);
		request.addQueryParam("application", REMOTE_CONFIG_APPLICATION);

		return SimpleHttpClient.execute(request, REMOTE_TEST_TIMEOUT_SEC,
				TimeUnit.SECONDS);
	}

}

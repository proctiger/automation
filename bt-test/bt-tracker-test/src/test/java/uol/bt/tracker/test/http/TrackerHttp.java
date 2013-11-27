package uol.bt.tracker.test.http;

import java.util.concurrent.TimeUnit;

import reconf.client.proxy.ConfigurationRepositoryFactory;

import uol.bt.tracker.test.domain.RequestParams;
import uol.bt.tracker.test.reconf.ConfigBtTrackerTest;
import uol.simple.httpclient.SimpleHttpClient;
import uol.simple.httpclient.SimpleHttpRequest;
import uol.simple.httpclient.SimpleHttpResponse;

/**
 * 
 * @author dvrocha
 */
public class TrackerHttp {

	private static final ConfigBtTrackerTest config = ConfigurationRepositoryFactory.get(ConfigBtTrackerTest.class);
	
	private static final String TRACKER_HOST = "http://" + config.getTrackerDomain();
	private static final String TRACKER_HOST_SSL = "https://" + config.getTrackerDomain();
	private static final int REQUEST_TIMEOUT = 20;

	public static SimpleHttpResponse probe(String probeType) throws Exception {
		return probe(probeType, false);
	}

	public static SimpleHttpResponse probe(String probeType, boolean ssl) throws Exception {
		return sendRequest("bt-tracker/" + probeType, ssl);
	}

	public static SimpleHttpResponse optin(RequestParams params) throws Exception {
		return optin(params, false);
	}

	public static SimpleHttpResponse optin(RequestParams params, boolean ssl) throws Exception {
		return sendRequest("optin", params, ssl);
	}

	public static SimpleHttpResponse optstatus(RequestParams params) throws Exception {
		return optstatus(params, false);
	}

	public static SimpleHttpResponse optstatus(RequestParams params, boolean ssl) throws Exception {
		return sendRequest("optstatus", params, ssl);
	}

	public static SimpleHttpResponse optout(RequestParams params) throws Exception {
		return optout(params, false);
	}

	public static SimpleHttpResponse optout(RequestParams params, boolean ssl) throws Exception {
		return sendRequest("optout", params, ssl);
	}

	public static SimpleHttpResponse track(RequestParams params) throws Exception {
		return track(params, false);
	}

	public static SimpleHttpResponse track(RequestParams params, boolean ssl) throws Exception {
		return sendRequest("track", params, ssl);
	}

	public static SimpleHttpResponse partner(RequestParams params) throws Exception {
		return partner(params, false);
	}

	public static SimpleHttpResponse partner(RequestParams params, boolean ssl) throws Exception {
		return sendRequest("partner", params, ssl);
	}

	private static SimpleHttpResponse sendRequest(String request, boolean ssl) throws Exception {
		return sendRequest(request, null, ssl);
	}

	private static SimpleHttpResponse sendRequest(String req, RequestParams params, boolean ssl) throws Exception {
		String host = ssl ? TRACKER_HOST_SSL : TRACKER_HOST;
		SimpleHttpRequest request = SimpleHttpClient.newGetRequest(host, req);
		if (req != null) {
			addParamsToGETRequest(params, request);
		}
		return SimpleHttpClient.execute(request, REQUEST_TIMEOUT, TimeUnit.SECONDS);
	}

	private static void addParamsToGETRequest(RequestParams params, SimpleHttpRequest trackRequest) {
		if (params != null) {
			if (params.getUserAgent() != null) {
				trackRequest.addHeaderField("User-Agent", params.getUserAgent());
			}

			if (params.getReferer() != null) {
				trackRequest.addHeaderField("Referer", params.getReferer());
			}

			if (params.getBttrk() != null) {
				trackRequest.addHeaderField("Cookie", "BTTRK=" + params.getBttrk().getValue());
			}

			if (params.getDnt() != null) {
				trackRequest.addHeaderField("DNT", params.getDnt());
			}
		}
	}
}

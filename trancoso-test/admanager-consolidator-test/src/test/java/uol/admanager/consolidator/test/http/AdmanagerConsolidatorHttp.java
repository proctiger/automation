package uol.admanager.consolidator.test.http;

import java.util.concurrent.TimeUnit;

import uol.admanager.consolidator.test.reconf.AdmanagerConsolidatorTestConfig;
import uol.admanager.consolidator.test.reconf.RemoteConfig;
import uol.simple.httpclient.SimpleHttpClient;
import uol.simple.httpclient.SimpleHttpRequest;
import uol.simple.httpclient.SimpleHttpResponse;

public class AdmanagerConsolidatorHttp {

    private static final AdmanagerConsolidatorTestConfig config = RemoteConfig.getInstance();

    private static final String ADMANAGER_CONSOLIDATOR_DOMAIN = config.getConsolidatorDomain();
    private static final Integer REQUEST_TIMEOUT = 60;

    public static SimpleHttpResponse probe(String probeType) throws Exception {
        final SimpleHttpRequest catRequest = SimpleHttpClient.newGetRequest(ADMANAGER_CONSOLIDATOR_DOMAIN, probeType);
        return SimpleHttpClient.execute(catRequest, REQUEST_TIMEOUT, TimeUnit.SECONDS);
    }
}

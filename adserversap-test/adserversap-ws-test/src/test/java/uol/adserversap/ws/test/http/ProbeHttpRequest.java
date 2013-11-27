/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uol.adserversap.ws.test.http;

import java.util.concurrent.TimeUnit;

import uol.adserversap.ws.test.config.ConfigTest;
import uol.simple.httpclient.SimpleHttpClient;
import uol.simple.httpclient.SimpleHttpRequest;
import uol.simple.httpclient.SimpleHttpResponse;

/**
 *
 * @author dvrocha
 */
public class ProbeHttpRequest {

    public static SimpleHttpResponse getProbe(String probeType) throws Exception {
        SimpleHttpRequest request = SimpleHttpClient.newGetRequest(ConfigTest.ADSERVERSAP_WS_DOMAIN, "adserversap-ws", probeType);
        return SimpleHttpClient.executeAvoidingSSL(request, ConfigTest.REQUEST_TIMEOUT, TimeUnit.SECONDS);
    }
}

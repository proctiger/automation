/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uol.adserversap.ws.test.http;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;


import uol.adserversap.ws.test.config.ConfigTest;
import uol.simple.httpclient.SimpleHttpClient;
import uol.simple.httpclient.SimpleHttpRequest;
import uol.simple.httpclient.SimpleHttpResponse;

/**
 *
 * @author wrodrigues@uolinc.com
 */
public class OrderHttpRequest {

    private static final String USER_LOGIN = "test_user";
    private static final String USER_IP = ipToString();

    private static final String ipToString() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "unknown";
        }
    }

    public static SimpleHttpResponse getOrderForApprovalById(String orderId) throws Exception {
        final SimpleHttpRequest request = SimpleHttpClient
                .newGetRequest(ConfigTest.ADSERVERSAP_WS_DOMAIN, "order/approval/" + orderId);

        return SimpleHttpClient
                .executeAvoidingSSL(request, ConfigTest.REQUEST_TIMEOUT, TimeUnit.SECONDS);
    }

    public static SimpleHttpResponse getOrderForApprovalByIdAndPoNumber(String orderId, String poNumber) throws Exception {
        final SimpleHttpRequest request = SimpleHttpClient
                .newGetRequest(ConfigTest.ADSERVERSAP_WS_DOMAIN, "order/approval/" + orderId)
                .addQueryParam("ponumber", poNumber);

        return SimpleHttpClient
                .executeAvoidingSSL(request, ConfigTest.REQUEST_TIMEOUT, TimeUnit.SECONDS);
    }

    public static SimpleHttpResponse getOrderForReprogrammingByIdAndPoNumber(String orderId, String poNumber) throws Exception {
        final SimpleHttpRequest request = SimpleHttpClient
                .newGetRequest(ConfigTest.ADSERVERSAP_WS_DOMAIN, "order/reprogramming/" + orderId + "/ponumber/" + poNumber)
        		.addQueryParam("login", USER_LOGIN)
        		.addQueryParam("ip", USER_IP);
        
        return SimpleHttpClient
                .executeAvoidingSSL(request, ConfigTest.REQUEST_TIMEOUT, TimeUnit.SECONDS);
    }

    public static SimpleHttpResponse approveOrderById(String orderId) throws Exception {
        final SimpleHttpRequest request = SimpleHttpClient
                .newPostRequest(ConfigTest.ADSERVERSAP_WS_DOMAIN, "order/approval/" + orderId)
                .addQueryParam("login", USER_LOGIN)
                .addQueryParam("ip", USER_IP);

        return SimpleHttpClient
                .executeAvoidingSSL(request, ConfigTest.REQUEST_TIMEOUT, TimeUnit.SECONDS);
    }

    public static SimpleHttpResponse approveOrderByIdAndPoNumber(String orderId, String poNumber) throws Exception {
        final SimpleHttpRequest request = SimpleHttpClient
                .newPostRequest(ConfigTest.ADSERVERSAP_WS_DOMAIN, "order/approval/" + orderId)
                .addQueryParam("ponumber", poNumber)
                .addQueryParam("login", USER_LOGIN)
                .addQueryParam("ip", USER_IP);

        return SimpleHttpClient
                .executeAvoidingSSL(request, ConfigTest.REQUEST_TIMEOUT, TimeUnit.SECONDS);
    }

    public static SimpleHttpResponse reprogramOrderByIdAndPoNumber(String orderId, String poNumber) throws Exception {
        final SimpleHttpRequest request = SimpleHttpClient
                .newPostRequest(ConfigTest.ADSERVERSAP_WS_DOMAIN, "order/reprogramming/" + orderId + "/ponumber/" + poNumber)
                .addQueryParam("login", USER_LOGIN)
                .addQueryParam("ip", USER_IP);

        return SimpleHttpClient
                .executeAvoidingSSL(request, ConfigTest.REQUEST_TIMEOUT, TimeUnit.SECONDS);
    }
}




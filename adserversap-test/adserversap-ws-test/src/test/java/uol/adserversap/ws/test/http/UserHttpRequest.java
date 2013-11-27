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
public class UserHttpRequest {

    public static SimpleHttpResponse getUser(String userLogin) throws Exception {
        SimpleHttpRequest request = SimpleHttpClient.newGetRequest(ConfigTest.ADSERVERSAP_WS_DOMAIN, "user", userLogin);
        return SimpleHttpClient.executeAvoidingSSL(request, ConfigTest.REQUEST_TIMEOUT, TimeUnit.SECONDS);
    }

    public static SimpleHttpResponse getUserList() throws Exception {
        SimpleHttpRequest request = SimpleHttpClient.newGetRequest(ConfigTest.ADSERVERSAP_WS_DOMAIN, "user");
        return SimpleHttpClient.executeAvoidingSSL(request, ConfigTest.REQUEST_TIMEOUT, TimeUnit.SECONDS);
    }

    public static SimpleHttpResponse deleteUser(String userLogin) throws Exception {
        SimpleHttpRequest request = SimpleHttpClient.newDeleteRequest(ConfigTest.ADSERVERSAP_WS_DOMAIN, "user", userLogin);
        return SimpleHttpClient.executeAvoidingSSL(request, ConfigTest.REQUEST_TIMEOUT, TimeUnit.SECONDS);
    }

    public static SimpleHttpResponse putUser(String login, String profile) throws Exception {
        SimpleHttpRequest request = SimpleHttpClient.newPutRequest(ConfigTest.ADSERVERSAP_WS_DOMAIN, "user");
        request.setEntityAsJson(getUserJson(login, profile));
        return SimpleHttpClient.executeAvoidingSSL(request, ConfigTest.REQUEST_TIMEOUT, TimeUnit.SECONDS);
    }
    
    private static String getUserJson(String login, String profile) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if(login != null){
            sb.append("\"login\":\"").append(login).append("\"");
        }
        if(profile != null){
            if(login != null){
                sb.append(",");
            }
            sb.append("\"profile\":\"").append(profile).append("\"");
        }
        sb.append("}");
        return sb.toString();
    }
}

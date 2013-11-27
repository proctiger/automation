package test.services;

import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.springframework.stereotype.Service;

import test.utils.ClickUriUtil;

@Service
class RestGetCookieService {

    public String getCookieUOLAFByRequest(ClickUriUtil clickUriUtil) throws Exception {
        ClientRequest req = new ClientRequest(clickUriUtil.getEventClickFullUri());
        ClientResponse<Response> resp = req.get(Response.class);
        for (String cookie : resp.getHeaders().get("Set-Cookie")) {
            if (StringUtils.contains(cookie, "UOLAF.CAD")) {
                return extractCookie(cookie);
            }
        }
        return StringUtils.EMPTY;
    }
    
    private String extractCookie(String cookie) {
        return cookie.split(";")[0].split("=")[1];
    }
}

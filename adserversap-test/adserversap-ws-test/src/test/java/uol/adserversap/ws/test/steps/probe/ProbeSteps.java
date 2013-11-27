package uol.adserversap.ws.test.steps.probe;

import uol.adserversap.ws.test.http.ProbeHttpRequest;
import uol.adserversap.ws.test.steps.commons.BaseCommonsSteps;
import uol.simple.httpclient.SimpleHttpResponse;
import cucumber.api.java.pt.Quando;

/**
 *
 * @author dvrocha
 */
public class ProbeSteps extends BaseCommonsSteps {

    @Quando("^solicitar url do probe <(.+)>$")
    public static SimpleHttpResponse getProbeServiceResult(String probeType) throws Exception {
        return serviceResponse = ProbeHttpRequest.getProbe(probeType);
    }
}

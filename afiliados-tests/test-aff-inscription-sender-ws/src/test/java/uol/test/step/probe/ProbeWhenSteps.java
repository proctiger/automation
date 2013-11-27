package uol.test.step.probe;

import org.junit.Assert;
import uol.test.util.ServiceRequest;
import cucumber.api.java.pt.Quando;

public class ProbeWhenSteps extends AbstractProbeSteps {

    @Quando("^efetuar a validacao no <(.+)> probe$")
    public void validateProbe(String tipoProbe) throws Exception {
        Assert.assertFalse("O tipo do probe nao pode ser nulo.", tipoProbe == null);
        ServiceRequest request = new ServiceRequest();
        String url;
        code = request.doGet(url = String.format("http://aff-inscription-sender-ws.sys.srv.intranet/%sprobe", tipoProbe.trim())).getStatusCode();
        LOGGER.info(String.format("URL = %s, CODE: %d", url, code));
    }
}
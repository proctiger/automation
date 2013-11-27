package uol.test.step.service;

import java.util.HashMap;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uol.test.util.ServiceRequest;

import com.uolinc.das.ws.client.service.SignupWS;

public abstract class AbstractLocalServiceSteps{
    
    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractLocalServiceSteps.class);

    protected static final String AFFILIATED_INFO = "affiliatedInfo";

    protected static final int AFFILIATED_INFO_CODE = 8;

    protected static final SignupWS SUBSCRIPTION_SERVICE_IMPL;

    protected static final String INFO_TYPE = "namInscAdditInfoType";

    protected static int code;

    protected static boolean fakeRC;
    static {
        SUBSCRIPTION_SERVICE_IMPL = SignupWS.getInstance("43", "43", "http://das-webservice.sys.srv.intranet:8090/das-ws.html");
    }

    protected void notifyEvent(final String dadosComplementares) {
        ServiceRequest request = new ServiceRequest();
        request.setBody(new HashMap<String, String>(1) {

            private static final long serialVersionUID = -940490002331971553L;
            {
                put("payload", dadosComplementares);
            }
        });
        try {
            code = request.doPost("http://aff-inscription-sender-ws.sys.srv.intranet/ebroker/subscription/1234567890/event/1357924680",
                                  MediaType.APPLICATION_FORM_URLENCODED_TYPE).getStatusCode();
        } catch (Exception e) {
            Assert.fail(String.format("Nao foi possivel realizar o POST para a notificacao no servico, devido a: %s", e.getLocalizedMessage()));
        }
    }
}
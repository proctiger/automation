package uol.test.step.service;

import java.util.HashMap;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;
import org.junit.Assert;

import uol.test.step.AbstractTestStep;
import uol.test.util.ServiceRequest;

import com.uolinc.das.ws.client.service.SignupWS;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public abstract class AbstractLocalServiceSteps extends AbstractTestStep {


    protected static int code;
    protected static final String DEFAULT_APPLICATIONID = "141";

    
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
            System.out.println(e);
        }
    }


    protected void realRC() {
    	System.out.println("Aplicando as configurações originais do remote config para applicationid " + String.valueOf(DEFAULT_APPLICATIONID));
    	setRConfigPrepaidProperty(DEFAULT_APPLICATIONID);
    	System.out.println("Voltando as configurações originais do remote config para applicationid " + String.valueOf(DEFAULT_APPLICATIONID));

    } 

    public void setRConfigPrepaidProperty(final String value) {
        ServiceRequest request = new ServiceRequest();
        JSONObject json = new JSONObject(new HashMap<String, String>(2) {
            private static final long serialVersionUID = 1L;
            {
                put("value", "['"+value+"':['13':'Compra Cursos Online']]");
            }
        });
        request.setBody(json.toString());
        try {
            Status status = request.doPut("http://config-ws.remote-admin.intranet/product/afiliados/component/aff-prepaid-importer/property/aff.prepaid.importer.product.source.from.platform.map?application=test-aff-prepaid-importer",
            				MediaType.APPLICATION_JSON_TYPE);
            Assert.assertTrue("A resposta para a chamada ao Remote Config nao foi positiva.", status.getStatusCode() < 300);
        } catch (Exception e) {
            Assert.fail(String.format("Erro ao tentar realizar a chamada ao remote config: %s", e.getLocalizedMessage()));
        }
    }
}
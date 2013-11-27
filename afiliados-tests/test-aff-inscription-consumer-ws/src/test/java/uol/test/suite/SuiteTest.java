package uol.test.suite;

import java.util.HashMap;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cucumber.api.java.After;
import uol.test.feature.DASWebserviceTest;
import uol.test.feature.LocalServiceTest;
import uol.test.util.BaseFactory;
import uol.test.util.ServiceRequest;

@RunWith(Suite.class)
@SuiteClasses({ DASWebserviceTest.class, LocalServiceTest.class })
public class SuiteTest {
    private static void setDelaySenderRC(final String value) {
        ServiceRequest request = new ServiceRequest();
        JSONObject json = new JSONObject(new HashMap<String, String>(2) {

            private static final long serialVersionUID = 1L;
            {
                put("value", value);
            }
        });
        request.setBody(json.toString());
        try {
            Status status = request.doPut("http://config-ws.remote-admin.intranet/product/afiliados/component/aff-inscription-sender-ws/property/queue.scheduled.delivery.millis?application=test-aff-inscription-consumer-ws",
                                          MediaType.APPLICATION_JSON_TYPE);
            Assert.assertTrue("A resposta para a chamada ao Remote Config nao foi positiva.", status.getStatusCode() < 30000);
        } catch (Exception e) {
            Assert.fail(String.format("Erro ao tentar realizar a chamada ao remote config: %s", e.getLocalizedMessage()));
        }
    }

    private static void wantQueueDelayConfig() {
        try {
            System.out.println("Esperando 1 min para que o delay de envio para a fila seja alterado via Remote Config.");
            Thread.sleep(1000 * 65); // 65s
        } catch (InterruptedException e) {
            Assert.fail("O Sleep falhou quando tentava esperar por um minuto.");
        }
    }
    
    @BeforeClass
    public static void setDelay() {
        setDelaySenderRC("'0'");
        wantQueueDelayConfig();
    }

    @AfterClass
    public static void restoreDelay() {
        setDelaySenderRC("'6000'");
        wantQueueDelayConfig();
        closeConnection();
    }

    @After
	private static void closeConnection() {
    	System.out.println("---------------------- FECHANDO AS CONEXÕES COM O BANCO ---------------------- ");
    	BaseFactory.closeConnectionUol3();
    	BaseFactory.closeConnectionUol7();
	}
	
}
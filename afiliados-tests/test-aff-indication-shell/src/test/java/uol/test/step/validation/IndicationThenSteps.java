package uol.test.step.validation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.hamcrest.core.Is;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import uol.affiliated.commons.rest.client.RestServiceCaller;
import uol.affiliated.commons.rest.client.factory.RestServiceCallerFactory;
import uol.affiliated.commons.test.CucumberTestUtil;
import uol.affiliated.commons.test.TestStep;
import uol.affiliated.commons.test.DAO.AeEventLogDAOImpl;
import uol.affiliated.commons.test.bean.AeEventLog;
import uol.affiliated.commons.test.exception.StepException;
import br.com.uol.cms.redis.driver.RedisService;
import cucumber.api.java.pt.Entao;

public class IndicationThenSteps extends AbstractIndicationSteps {
	private static final String NULL = null;
    private @Autowired RedisService redisService;

	@Entao("^verificar se o servico esta retornando uma mensagem <(.+)>$")
    public void verifyServiceResult(final String json) {
        CucumberTestUtil.then(new TestStep() {

            public void execute(PrintWriter printer) throws StepException {
                try {
                    Assert.assertThat(IndicationThenSteps.json, IsJSONObject.equalTo(new JSONObject(json)));
                } catch (JSONException e) {
                    Assert.fail(String.format("Cannot convert string [%s] to JSONObject. Invalid argument for testing.", json));
                }
            }
        });
    }

    @Entao("^verificar se o servico esta retornando status <(.+)>$")
    public void verifyServiceStatus(final int status) {
        CucumberTestUtil.then(new TestStep() {

            public void execute(PrintWriter printer) throws StepException {
                Assert.assertThat(String.format("ESPERADO: [%d], RECEBIDO: [%d]", status, IndicationThenSteps.status), IndicationThenSteps.status, Is.is(status));
            }
        });
    }

    @Entao("^ocorrera o redirecionamento para o registrador de clicks de afiliados$")
    public void occurRedirect() throws StepException{
        CucumberTestUtil.then(new TestStep() {
            public void execute(PrintWriter printer) throws StepException {
                Assert.assertTrue("Serviço não gerou a URL de Redir" , redirect());
                printer.println(String.format("Cookie guardado - " + IndicationThenSteps.cookie));
            }
        });
    }
    
    @Entao("^nao ocorrera o redirecionamento para o registrador de clicks de afiliados$")
    public void notOccurRedirect() throws StepException{
        CucumberTestUtil.then(new TestStep() {
            public void execute(PrintWriter printer) throws StepException {
                Assert.assertFalse("Serviço gerou a URL de Redir" , redirect());
            }
        });
    }    
    
    @Entao("^na tabela de eventos existira um evento de indicacao com codigo do produto <(.+)> para o <(.+)>$")
    public void verifyIndicationEvent(final int idtProductSource,
                                      final String namLogin) {
        CucumberTestUtil.then(new TestStep() {
            public void execute(PrintWriter printer) throws StepException {
                List<AeEventLog> aeEventLogList = new ArrayList<AeEventLog>();
                AeEventLogDAOImpl aeeventlogdaoimpl = new AeEventLogDAOImpl(dbUtil);
                sleep();
                aeEventLogList = aeeventlogdaoimpl.selectEventByNamLoginDesNameAndIdtProductSource(namLogin, idtProductSource, "Indicacao");
                Assert.assertFalse(aeEventLogList.isEmpty());
            }
        });
    }
    

    private void sleep() {
        try {
            Thread.sleep(1000 * 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Entao("^na tabela de eventos nao existira um evento de indicacao com codigo do produto <(.+)> para o <(.+)>$")
    public void verifyForNoIndicationEvent(final int idtProductSource, final String namLogin) {
        CucumberTestUtil.then(new TestStep() {

            public void execute(PrintWriter printer) throws StepException {
                List<AeEventLog> aeEventLogList = new ArrayList<AeEventLog>();
                AeEventLogDAOImpl aeeventlogdaoimpl = new AeEventLogDAOImpl(dbUtil);
                aeEventLogList = aeeventlogdaoimpl.selectEventByNamLoginDesNameAndIdtProductSource(namLogin, idtProductSource, "Indicacao");
                Assert.assertTrue(aeEventLogList.isEmpty());
            }
        });
    }
    
    private boolean redirect() {
        if (IndicationWhenSteps.redir == null || IndicationWhenSteps.redir.isEmpty()) {
            return false;
        }
        RestServiceCaller caller = RestServiceCallerFactory.createRestServiceCaller(IndicationWhenSteps.redir, 1000);
        caller.doGet(IndicationWhenSteps.redir, String.class);
        try {
            caller.close();
        } catch (IOException e) {
            Assert.fail("Erro ao fechar conexão " + e.getLocalizedMessage());
        }
        IndicationThenSteps.cookie = getCookie(caller.getHeaderValue("Set-Cookie"));
        if (IndicationThenSteps.cookie == null || IndicationWhenSteps.cookie.isEmpty()) {
            return false;
        }
        return true;
    }

    private String getCookie(String header) {
        return header.split(";")[0].split("=")[1];
    }
    
    @Entao("^o conteudo da chave no servidor de sessao com codigo do produto <(.+)> contera o valor <(.+)>$")
    public void foundKey(String productSource , String redisValue)throws StepException {
        sleep();
    	Assert.assertEquals(redisValue, searchRedis(AbstractIndicationSteps.cookie , productSource));
    }
    
    @Entao("^nao havera chave do produto  <(.+)> no servidor de sessao$")
    public void notFoundKey(String productSource) throws StepException {
        Assert.assertEquals(NULL, searchRedis(AbstractIndicationSteps.cookie , productSource));
    }
    
    private String searchRedis(String cookie , String productSource) {
        String key = String.format("%s:%s",AbstractIndicationSteps.cookie, productSource);
    	String value = redisService.get(key, String.class);
    	 if(value == null || value.isEmpty()) {
             return null;
         }
    	return StringUtils.newStringUtf8(Base64.decodeBase64(value));
    }
    
    

}
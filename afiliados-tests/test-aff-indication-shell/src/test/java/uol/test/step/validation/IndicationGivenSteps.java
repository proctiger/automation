package uol.test.step.validation;

import java.io.PrintWriter;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import uol.affiliated.commons.rest.client.factory.RestServiceCallerFactory;
import uol.affiliated.commons.test.CucumberTestUtil;
import uol.affiliated.commons.test.TerminalUtil;
import uol.affiliated.commons.test.TestStep;
import uol.affiliated.commons.test.DAO.AeEventLogDAOImpl;
import uol.affiliated.commons.test.exception.StepException;
import br.com.uol.cms.redis.driver.RedisService;
import cucumber.api.java.pt.Dado;

public class IndicationGivenSteps  extends AbstractIndicationSteps {	
	
        private @Autowired RedisService redisService;
    
	@Dado("^o dominio <(.+)> utilizando o protocolo <(.+)>$")
	public void configureService(final String domain, final String protocol) {
		CucumberTestUtil.given(new TestStep() {
			public void execute(PrintWriter printer) throws StepException{
				try {
					System.out.print("Protocolo :" + protocol);
					IndicationGivenSteps.request = RestServiceCallerFactory.createRestServiceCaller(IndicationGivenSteps.requestURI=String.format("%s://%s", protocol, domain),  5000);
				} catch(Exception e) {
					throw new StepException(e);
				}
				printer.println(String.format(">>> REQUEST: %s", request));
			}

			});
	}
	
	@Dado("^que nao exista no servidor de sessao as chaves do produto <(.+)>$")
	public void cleanRedis(String keyRedis) {
	    Assert.assertTrue("Não foi possivel remover as chaves do servidor de sessao",redisService.removeAllKeys());
	}
	
	@Dado("^que nao exista registros de indicacao do afiliado <(.+)> na tabela de eventos$")
	public void removeFromAeEventLog(final String namLogin) {
		CucumberTestUtil.given(new TestStep() {
			public void execute(PrintWriter printer) throws StepException {
				AeEventLogDAOImpl aeeventlogdaoimpl = new AeEventLogDAOImpl(dbUtil);
				try {
					aeeventlogdaoimpl.deleteEventByNamLoginAndDesName(namLogin,"Indicacao");
				} catch(Exception e) {
					throw new StepException(e);
				}
				printer.println(String.format(">>> REQUEST: %s", request));
			}
		});
	}
	
	@Dado("^que o servico <(.+)> esteja indisponivel no pool <(.+)>$")
	public void shutServiceDown(final String component, final String pool) {
		CucumberTestUtil.given(new TestStep() {
			public void execute(PrintWriter printer) throws StepException {
				try {
					TerminalUtil.getInstance(printer).appStop(component, "jetty" /*since it's a service */, pool, "a");
				} catch(Exception e) {
					throw new StepException(e);
				}
			}
		});
	}
	
	@Dado("^que os servicos <(.+)> estejam disponiveis nos pools <(.+)>$")
	public void startServicesUp(final String components, final String pools) {
		CucumberTestUtil.given(new TestStep() {
			public void execute(PrintWriter printer) throws StepException {
				try {
					String[] services = components.split(";");
					String[] ps = pools.split(";");
					printer.println(String.format("*** Tentando iniciar os servicos [%s] nos pools [%s]", components, pools));
					for(int i = 0; i < services.length; i++) {
						try{
							printer.println(String.format("*** Tentando iniciar o servico [%s] no pool [%s]", services[i], ps[i]));
							TerminalUtil.getInstance(printer).appStart(services[i], "jetty" /*since it's a service */, ps[i], "a");
						} catch(Exception e) {
							printer.println(String.format("*** Problemas ao tentar iniciar o servico [%s] no pool [%s]: [%s]", services[i], ps[i], e.getLocalizedMessage()));
						}
					}
				} catch(Exception e) {
					throw new StepException(e);
				}
			}
		});
	}
	
	@Dado("^que o servico <(.+)> esteja disponivel no pool <(.+)>$")
	public void startServiceUp(final String component, final String pool) {
		CucumberTestUtil.given(new TestStep() {
			public void execute(PrintWriter printer) throws StepException {
				try {
					TerminalUtil.getInstance(printer).appStartAndWait(component, "jetty" /*since it's a service */, pool, "a");
				} catch(Exception e) {
					throw new StepException(e);
				}
			}
		});
	}
	
	@Dado("^que sejam esperados <(\\d+)> milissegundos$")
	public void sleep(final int ms) throws InterruptedException {
		CucumberTestUtil.given(new TestStep() {
			public void execute(PrintWriter printer) throws StepException {
				try {
					printer.write(String.format("*** ESPERANDO [%d] MILISSEGUNDOS", ms));
					Thread.sleep(ms);
				} catch(Exception e) {
					throw new StepException(e);
				}
			}
		});
	}
}
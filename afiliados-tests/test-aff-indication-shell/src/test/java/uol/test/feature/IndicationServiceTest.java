package uol.test.feature;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import uol.test.step.validation.IndicationGivenSteps;
import cucumber.api.junit.Cucumber;

// reponsavel por fazer a chamada do feature

@RunWith(Cucumber.class)
@Cucumber.Options(glue = "uol.test.step",
    features = "src/test/resources/feature"
//    ,name = {"Validacao da chamada de indicacao", "Servico de Validacao", "Verificacao da monitoracao do probe"}
)
public class IndicationServiceTest {
	
	@BeforeClass
	@AfterClass
	public static void resetUpServices() throws InterruptedException {
		IndicationGivenSteps steps = new IndicationGivenSteps();
		steps.startServicesUp("affiliated-event-click;affiliated-ws-service", "a1-helique-s-prt1;a1-iziafl-s-prt1");
		steps.sleep(15000);
	}
}
package uol.admanager.consolidator.test.steps.probe;

import org.junit.Assert;

import uol.admanager.consolidator.test.steps.view.TestStep;
import cucumber.api.java.pt.Entao;

public class ThenTestStep extends TestStep {

    @Entao("^o sistema retorna o status HTTP (\\d+)$")
    public void validateHttpStatus(int expectedStatus) throws Exception {
        final int actualStatus = probeResponse.getStatusCode();
        final String errorMessage = String.format("Status HTTP retornado está incorreto (%s)", probeResponse.getBodyAsString());

        Assert.assertEquals(errorMessage, expectedStatus, actualStatus);
    }
}

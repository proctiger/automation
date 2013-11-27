package uol.tm.filechecker.test.step;

import cucumber.api.java.pt.Então;
import org.junit.Assert;

/**
 *
 * @author dvrocha
 */
public class ThenStep extends BaseStep {

    @Então("^a requisição retorna o status <(\\d+)>$")
    public void checkRequestStatus(int status) {
        Assert.assertEquals("Status retornado na requisição está incorreto", status, serviceResponse.getStatusCode());
    }
}

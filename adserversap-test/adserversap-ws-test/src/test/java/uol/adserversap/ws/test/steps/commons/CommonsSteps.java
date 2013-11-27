package uol.adserversap.ws.test.steps.commons;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;

import uol.adserversap.ws.test.domain.ErrorMessageDomain;
import cucumber.api.java.pt.Entao;

/**
 *
 * @author dvrocha
 */
public class CommonsSteps extends BaseCommonsSteps {

    @Entao("^retorna status HTTP igual a <(\\d+)>$")
    public void checkServiceStatus(int httpCode) throws Exception {
        Assert.assertEquals(serviceResponse.getBodyAsString(), httpCode, serviceResponse.getStatusCode());
    }

    @Entao("^retorna erro com o código <(.+)> e a mensagem <(.+)>$")
    public void checkErrorMessage(String code, String message) throws Exception {
        ErrorMessageDomain[] errorMessageList = new ObjectMapper().readValue(serviceResponse.getBodyAsString(), ErrorMessageDomain[].class);
        Assert.assertTrue(errorMessageList.length > 0);
        for (int i = 0; i < errorMessageList.length; i++) {
            if (errorMessageList[i].getCode().equals(code)) {
                Assert.assertEquals(message, errorMessageList[i].getMessage());
                break;
            }
            if (i == errorMessageList.length - 1) {
                Assert.fail("Mensagem de erro não encontrada");
            }
        }
    }
}

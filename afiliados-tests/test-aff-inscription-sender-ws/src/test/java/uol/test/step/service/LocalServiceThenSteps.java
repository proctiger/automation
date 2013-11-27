package uol.test.step.service;

import org.junit.Assert;
import cucumber.api.java.pt.Entao;

public class LocalServiceThenSteps extends AbstractLocalServiceSteps {

    @Entao("^verificar se retorna o HTTP <(.+)> para o Notificador Cadastral apos o envio para a fila$")
    public void checkHttpReturn(Long httpCode) {
        LOGGER.info("HTTP CODE: " + httpCode);
        Assert.assertEquals(new Long(code), httpCode);
    }
}
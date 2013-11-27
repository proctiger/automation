package uol.test.step.probe;

import org.junit.Assert;

import cucumber.api.java.pt.Entao;

/**
 * @author mis_dmarcos Nesta classe são criados os passos que são esperados como
 *         resultados dos testes.
 **/
public class ProbeThenSteps extends AbstractProbeSteps {

    @Entao("^verifique que o resultado obtido do slb probe e igual ao codigo (\\d+)$")
    public void checkProbeCodeResult(int code) throws Exception {
        String error = String.format("Codigo <%s> não foi encontrado.", code);
        Assert.assertTrue(error, code == ProbeThenSteps.code);
    }
}
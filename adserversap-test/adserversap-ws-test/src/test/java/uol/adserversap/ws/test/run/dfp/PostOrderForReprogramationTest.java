package uol.adserversap.ws.test.run.dfp;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

/**
 *
 * @author wrodrigues
 */
@RunWith(Cucumber.class)
@Cucumber.Options(
        glue = {"uol.adserversap.ws.test.steps.commons", "uol.adserversap.ws.test.steps.dfp"},
        features = {"src/test/resources/features/dfp/OrdemReprogramacao/DFPPOSTOrderReprogramation.feature"},
        name = "Reprogramacao de Ordem com Sucesso")
public class PostOrderForReprogramationTest {
}
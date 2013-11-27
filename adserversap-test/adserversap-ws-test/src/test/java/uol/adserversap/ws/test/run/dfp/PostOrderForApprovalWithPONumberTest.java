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
        features = {"src/test/resources/features/dfp/OrdemAprovacao/DFPPOSTOrderApprovalWithPONumber.feature"},
        name = "Aprovacao de Ordem com Sucesso com Id e PONumber")
public class PostOrderForApprovalWithPONumberTest {
}


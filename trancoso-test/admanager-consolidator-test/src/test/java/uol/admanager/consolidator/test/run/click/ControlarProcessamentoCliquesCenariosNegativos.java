package uol.admanager.consolidator.test.run.click;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

/**
 *
 * @author wrodrigues
 */
@RunWith(Cucumber.class)
@Cucumber.Options(
        glue = {"uol.admanager.consolidator.test.steps.commons", "uol.admanager.consolidator.test.steps.click"},
        features = {"src/test/resources/features/click/Controlar Processamento de Cliques Cenarios Negativos.feature"},
        name = "Controlar Processamento de Cliques com Problema")
public class ControlarProcessamentoCliquesCenariosNegativos{
}
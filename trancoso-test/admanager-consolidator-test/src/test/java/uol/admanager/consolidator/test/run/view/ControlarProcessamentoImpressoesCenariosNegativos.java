package uol.admanager.consolidator.test.run.view;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

/**
 *
 * @author wrodrigues
 */
@RunWith(Cucumber.class)
@Cucumber.Options(
        glue = {"uol.admanager.consolidator.test.steps.commons", "uol.admanager.consolidator.test.steps.view"},
        features = {"src/test/resources/features/view/Controlar Processamento de Impressões Cenarios Negativos.feature"},
        name = "Controlar Processamento de Impressões com Problema")
public class ControlarProcessamentoImpressoesCenariosNegativos{
}
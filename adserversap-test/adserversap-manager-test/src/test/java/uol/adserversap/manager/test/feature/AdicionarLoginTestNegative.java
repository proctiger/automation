package uol.adserversap.manager.test.feature;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

/**
 * 
 * @author wrodrigues
 * 
 */
@RunWith(Cucumber.class)
@Cucumber.Options(
		glue = "uol.adserversap.manager.test.step.user",
		features = "src/test/resources/feature", 
		format = { "html:target/cucumber", "json:target/cucumber.json" }, 
		name = "Adicionar Login Cenarios Negativos")
public class AdicionarLoginTestNegative {
}

package uol.adserversap.manager.test.feature;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

/**
 * 
 * @author dvrocha
 * 
 */
@RunWith(Cucumber.class)
@Cucumber.Options(
		glue = "uol.adserversap.manager.test.step.login", 
		features = "src/test/resources/feature", 
		format = { "html:target/cucumber", "json:target/cucumber.json" }, 
		name = "Efetuar Login")
public class LoginTest {
}

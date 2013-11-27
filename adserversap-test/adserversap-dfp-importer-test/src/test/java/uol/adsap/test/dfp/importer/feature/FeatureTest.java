package uol.adsap.test.dfp.importer.feature;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

/**
 * 
 * @author dvrocha
 * 
 */
@RunWith(Cucumber.class)
@Cucumber.Options(
		glue = "uol.adsap.test.dfp.importer.step", 
		features = "src/test/resources/feature", 
		format = { "html:target/cucumber", "json:target/cucumber.json" })
public class FeatureTest {}
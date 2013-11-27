package uol.bt.tracker.test.feature;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

/**
 * @author dvrocha
 */
@RunWith(Cucumber.class)
@Cucumber.Options(
		glue = "uol.bt.tracker.test.step",
		tags = "@Self", 
		features = "src/test/resources/feature_files/", 
		format = "json:/export/bt/self-test/self-test.json")
public class SelfFeature {}

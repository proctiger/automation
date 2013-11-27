package uol.bt.tracker.test.feature;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

/**
 * @author dvrocha
 */
@RunWith(Cucumber.class)
@Cucumber.Options(tags = "@Manual", features = "src/test/resources/feature_files/", format = "json:target/cucumber-manual.json")
public class ManualFeature {}

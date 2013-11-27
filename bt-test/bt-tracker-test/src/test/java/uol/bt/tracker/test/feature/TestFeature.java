package uol.bt.tracker.test.feature;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

/**
 * 
 * @author dvrocha
 *
 */
@RunWith(Cucumber.class)
@Cucumber.Options(
    glue = {"uol.bt.tracker.test.step"},
    tags = {"~@Manual", "~@Ignore", "~@Web"},
    		features = "src/test/resources/feature_files/",
    format = "json:target/cucumber.json")
public class TestFeature {

}

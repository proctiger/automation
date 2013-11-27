package uol.tm.filechecker.test.feature;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 *
 * @author dvrocha
 */
@RunWith(Cucumber.class)
@Cucumber.Options(
    tags = {"@MANUAL"},
    features = "src/test/resources/feature",
    format = "json:target/cucumber-manual.json")
public class ManualFeatureTest {}

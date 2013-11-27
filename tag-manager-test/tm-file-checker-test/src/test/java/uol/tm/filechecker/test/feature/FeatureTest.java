package uol.tm.filechecker.test.feature;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 *
 * @author dvrocha
 */
@RunWith(Cucumber.class)
@Cucumber.Options(
    glue = "uol.tm.filechecker.test.step",
    tags = {"~@MANUAL","~@WIP"},
    features = "src/test/resources/feature",
    format = "json:target/cucumber.json")
public class FeatureTest {}

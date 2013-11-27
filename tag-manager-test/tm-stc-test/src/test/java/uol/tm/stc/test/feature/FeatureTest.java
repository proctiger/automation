package uol.tm.stc.test.feature;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

/**
 *
 * @author dvrocha
 */
@RunWith(Cucumber.class)
@Cucumber.Options(glue = "uol.tm.stc.test.step",
tags = {"~@Ignore"},
features = "src/test/java/feature_files",
format = "json:target/cucumber.json")
public class FeatureTest {
}

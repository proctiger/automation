package uol.bt.cruncher.test.feature;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

/**
 *
 * @author dvrocha
 *
 */
@RunWith(Cucumber.class)
@Cucumber.Options(glue = "uol.bt.cruncher.test.step", features = "src/test/resources/feature",tags = "~@Ignore")
public class FeatureTest {

}

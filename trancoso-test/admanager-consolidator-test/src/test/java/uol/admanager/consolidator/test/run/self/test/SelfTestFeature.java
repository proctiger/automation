package uol.admanager.consolidator.test.run.self.test;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

/**
 *
 * @author dvrocha
 *
 */
@RunWith(Cucumber.class)
@Cucumber.Options(
	glue = {"uol.admanager.consolidator.test.steps"},
    tags = "@Self",
    features = "classpath:features",
    format = "html:/export/admanager-consolidator/self-test")
public class SelfTestFeature {

}

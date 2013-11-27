package uol.bt.mongokeeper.test.run;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

/**
 * @author cin_wrodrigues
 */
@RunWith(Cucumber.class)
@Cucumber.Options(
		glue="uol.bt.mongokeeper.test.step",
        features = {"src/test/resources/features/purgecollections.feature"},
        name = "Apagar collections")
public class PurgaCollectionsTest {
}

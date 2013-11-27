package uol.admanager.consolidator.test.suite;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import uol.admanager.consolidator.test.run.self.test.SelfTestFeature;
import uol.admanager.consolidator.test.steps.view.TestStep;

/**
 *
 * @author dvrocha
 *
 */
@RunWith(Suite.class)
@SuiteClasses(SelfTestFeature.class)
public class SelfTestSuite extends TestStep {

    @BeforeClass
    public static void beforeAllTests() throws Exception {
        self = true;
    }

    @AfterClass
    public static void afterAllTests() throws Exception {
        self = false;
    }
}

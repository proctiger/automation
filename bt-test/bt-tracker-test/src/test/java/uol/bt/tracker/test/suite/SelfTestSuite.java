package uol.bt.tracker.test.suite;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import uol.bt.tracker.test.feature.SelfFeature;
import uol.bt.tracker.test.step.BaseStep;

/**
 * 
 * @author dvrocha
 */
@RunWith(Suite.class)
@SuiteClasses(SelfFeature.class)
public class SelfTestSuite extends BaseStep {

	@BeforeClass
	public static void beforeClass() throws Exception {
		self = true;
	}

	@AfterClass
	public static void afterClass() throws Exception {
		self = false;
	}
}

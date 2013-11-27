package uol.bt.tracker.test.suite;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import uol.bt.tracker.test.feature.TestFeature;
import uol.bt.tracker.test.step.TestPrepare;

/**
 * 
 * @author dvrocha
 */
@RunWith(Suite.class)
@SuiteClasses({ TestFeature.class, WebTestSuite.class })
public class TestSuite {

	@BeforeClass
	public static void beforClass() throws Exception {
		TestPrepare.removeEvents();
		TestPrepare.restartTracker();
		TestPrepare.waitTrackerStart();
	}
}

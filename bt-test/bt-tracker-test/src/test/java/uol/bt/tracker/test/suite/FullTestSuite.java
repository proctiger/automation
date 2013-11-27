package uol.bt.tracker.test.suite;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import uol.bt.tracker.test.feature.ManualFeature;
import uol.bt.tracker.test.step.TestPrepare;

/**
 * 
 * @author dvrocha
 */
@RunWith(Suite.class)
@SuiteClasses({
	TestSuite.class,
	ManualFeature.class })
public class FullTestSuite {

	@BeforeClass
	public static void beforClass() throws Exception {
		TestPrepare.removeEvents();
		TestPrepare.restartTracker();
		TestPrepare.waitTrackerStart();
	}
}

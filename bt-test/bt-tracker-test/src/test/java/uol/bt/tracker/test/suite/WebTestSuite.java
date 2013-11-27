package uol.bt.tracker.test.suite;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import uol.bt.tracker.test.feature.WebFeature;
import uol.bt.tracker.test.selenium.BasicWebTest;
import uol.bt.tracker.test.step.TestPrepare;

/**
 * 
 * @author dvrocha
 */
@RunWith(Suite.class)
@SuiteClasses(WebFeature.class)
public class WebTestSuite {

	@BeforeClass
	public static void beforeClass() throws Exception {
		TestPrepare.waitTrackerStart();
		BasicWebTest.startFirefoxBrowser();
	}

	@AfterClass
	public static void afterClass() throws Exception {
		BasicWebTest.stopFirefoxBrowser();
	}
}

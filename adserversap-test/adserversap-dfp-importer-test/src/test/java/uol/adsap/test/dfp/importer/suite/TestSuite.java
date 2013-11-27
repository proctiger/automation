package uol.adsap.test.dfp.importer.suite;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import uol.adsap.test.dfp.importer.feature.FeatureTest;
import uol.adsap.test.dfp.importer.http.ConfigWsHttp;

/**
 *
 * @author dvrocha
 */
@RunWith(Suite.class)
@SuiteClasses({ FeatureTest.class })
public class TestSuite {

	@AfterClass
	public static void afterClass() throws Exception {
		ConfigWsHttp.removeProperty("hours.execution");
		ConfigWsHttp.removeProperty("start.minus.days");
		ConfigWsHttp.removeProperty("end.minus.days");
	}
}

package uol.admanager.editor.test.suite;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import uol.admanager.editor.test.feature.FeatureTest;

/**
 * @author dvrocha
 */
@RunWith(Suite.class)
@SuiteClasses({ FeatureTest.class })
public class TestSuite {

	@BeforeClass
	public static void openBrowser() {
	}

	@AfterClass
	public static void closeBrowser() {
	}
}

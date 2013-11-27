package uol.tm.stc.test.suite;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import uol.tm.stc.test.feature.FeatureTest;
import uol.tm.stc.test.http.RemoteAdminTesterHttp;
import uol.tm.stc.test.utils.TestHelper;
import uol.tm.stc.test.web.BasicWebTest;

/**
 * 
 * @author dvrocha
 */
@RunWith(Suite.class)
@SuiteClasses({ FeatureTest.class })
public class TestSuite {

	@BeforeClass
	public static void startWebDrive() throws Exception {
		writeStyleCss();
		writeConsoleJs();
		BasicWebTest.startFirefoxBrowser();
	}

	@AfterClass
	public static void stopWebDrive() {
		BasicWebTest.stopFirefoxBrowser();
	}

	private static void writeConsoleJs() throws Exception {
		String content = TestHelper.readResourceFile("console.js");
		String command = "sudo echo \"" + content.replace("\"", "\\\"") + "\" > /tmp/console.js;";
		command += "sudo mv /tmp/console.js /export/htdocs/test.tm.uol.com.br/;";
		RemoteAdminTesterHttp.shell(command);
	}
	
	private static void writeStyleCss() throws Exception {
		String content = TestHelper.readResourceFile("style.css");
		String command = "sudo echo \"" + content.replace("\"", "\\\"") + "\" > /tmp/style.css;";
		command += "sudo mv /tmp/style.css /export/htdocs/test.tm.uol.com.br/;";
		RemoteAdminTesterHttp.shell(command);
	}
}

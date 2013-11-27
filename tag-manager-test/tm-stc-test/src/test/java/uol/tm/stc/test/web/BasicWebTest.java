package uol.tm.stc.test.web;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

/**3
 * 
 * @author dvrocha
 *
 */
public class BasicWebTest {

	public static FirefoxDriver firefoxDriver;

	public static void startFirefoxBrowser() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);
		firefoxDriver = new FirefoxDriver(profile);
	}

	public static void stopFirefoxBrowser() {
		if (firefoxDriver != null && firefoxDriver.getSessionId() != null) {
			firefoxDriver.close();
			firefoxDriver.quit();
		}
	}
}

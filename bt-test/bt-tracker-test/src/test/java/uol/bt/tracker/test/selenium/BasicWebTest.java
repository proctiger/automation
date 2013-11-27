package uol.bt.tracker.test.selenium;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 *
 * @author dvrocha
 */
public class BasicWebTest {

    public static FirefoxDriver firefoxDriver;

    /**
     * Inicia o Firefox para execução dos testes com Selenium. Recomenda-se usar
     * este método como BeforeClass do JUnit
     */
    public static void startFirefoxBrowser() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setAcceptUntrustedCertificates(true);
        firefoxDriver = new FirefoxDriver(profile);
    }

    /**
     * Finaliza a exeução do Firefox. Recomenda-se usar este método como
     * AfterClass do JUnit
     */
    public static void stopFirefoxBrowser() {
        if (firefoxDriver != null && firefoxDriver.getSessionId() != null) {
            firefoxDriver.close();
            firefoxDriver.quit();
        }
    }
}

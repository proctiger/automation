package uol.adserversap.manager.test.suite;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import uol.adserversap.dao.ploc.UserDao;
import uol.adserversap.entity.User;
import uol.adserversap.manager.test.feature.RemoverLoginTestNegative;
import uol.adserversap.manager.test.feature.RemoverLoginTestPositive;
import uol.selenium.basics.BasicWebTest;

/**
 * 
 * @author wrodrigues
 * 
 */
@RunWith(Suite.class)
@SuiteClasses({ RemoverLoginTestPositive.class, RemoverLoginTestNegative.class})
public class TestSuiteUserRemove extends BasicWebTest {
	@BeforeClass
	public static void startWebDrive() {
		startFirefoxBrowser();
	}

	@AfterClass
	public static void stopWebDriver() throws Exception {
		UserDao.deleteAllFromUserLogin();
		UserDao.persistUser(new User("admin_1", 'R', 1));
	}
}

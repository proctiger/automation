package uol.adserversap.manager.test.suite;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import uol.adserversap.dao.ploc.UserDao;
import uol.adserversap.entity.User;
import uol.selenium.basics.BasicWebTest;

@RunWith(Suite.class)
@SuiteClasses({ TestSuiteLogin.class, TestSuiteUserAdd.class,
		TestSuiteUserEdit.class, TestSuiteUserRemove.class })
public class AllTests extends BasicWebTest{
	@BeforeClass
	public static void startWebDrive() {
	}

	@AfterClass
	public static void stopWebDriver() throws Exception {
		stopFirefoxBrowser();
		UserDao.deleteAllFromUserLogin();
		UserDao.persistUser(new User("admin_1", 'R', 1));
	}
}

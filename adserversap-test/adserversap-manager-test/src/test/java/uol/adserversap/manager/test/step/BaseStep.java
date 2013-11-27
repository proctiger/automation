package uol.adserversap.manager.test.step;

import uol.adserversap.manager.test.domain.UserDomain;
import uol.adserversap.manager.test.page.BasePage;
import uol.adserversap.manager.test.page.LoginPage;
import uol.adserversap.manager.test.page.UserPage;
import uol.selenium.basics.BasicWebTest;

/**
 * 
 * @author dvrocha
 * 
 */
public class BaseStep extends BasicWebTest {
	protected static UserDomain 	user;
	protected static BasePage 		page 			= 	new BasePage(firefoxDriver);
	protected static LoginPage 		loginPage 		= 	new LoginPage(firefoxDriver);
	protected static UserPage 		userPage 		= 	new UserPage(firefoxDriver);
	protected static final String 	DEFAULT_USER 	= 	"admin_1";
	protected static final String 	DEFAULT_PASS	= 	"abk2000!";
	protected static final String 	DEFAULT_USER2	= 	"admin_2";
	
	public void logout() throws Exception {
		page.open();
		if (page.isLogged()) {
			page.submitLogout();
		}
	}
	
}

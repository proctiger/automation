package selenium.test.incoming;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;

import selenium.page.object.DashboardObject;
import selenium.service.builder.DashboardService;
import selenium.service.builder.LoginService;
import selenium.test.BaseTest;

public class LoginTest extends BaseTest {

	@After
	public void logout() {
		new DashboardService().action().exitPage();
	}
	
	@Test
	public void loginOk() throws Exception {
		Assert.assertEquals(DashboardObject.DASHBOARD_URL, new LoginService(
				login, password).login());
	}
}

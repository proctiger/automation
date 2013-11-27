package selenium.test.campaign;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import selenium.service.builder.CampaignService;
import selenium.service.builder.DashboardService;
import selenium.service.builder.LoginService;
import selenium.test.BaseTest;

public class CampaignCreateTest extends BaseTest {

	private CampaignService campaignService;

	public CampaignCreateTest() {
		campaignService = new CampaignService();
	}

	@Before
	public void login() throws Exception {
		new LoginService(login, password).login();
	}

	@After
	public void logout() {
		new DashboardService().action().exitPage();
	}

	@Test
	public void createOK() {
		//Assert.assertTrue(campaignService.title("S. Campanha 2").startDate(new Date())
			//	.endDate(new Date()).budget(500).createCampaign());
		//new DashboardService().action().clickControlPanel();
	}
	
	@Test
	public void withBlankTitle() {
		
	}
	
	@Test
	public void withBlankStartDate() {
		
	}
	
	@Test
	public void withBlankEndDate() {
		
	}
	
	@Test
	public void withBlankDailyBudget() {
		
	}
	
	@Test
	public void withRepeatTitle() {
		
	}
	
	@Test
	public void withStartDateAfterEndDate() {
		
	}
	
	@Test
	public void upperCaseTitle() {
		
	}

	@Test
	public void invalidSpecialTitle() {
	
	}
	
	@Test
	public void spaceTitle() {
	
	}
	
	@Test
	public void invalidMinorDailyBudget() {
	
	}
	
	@Test
	public void invalidMaxDailyBudget() {
	
	}
	
	@Test
	public void blankStartDate() {
	
	}
	
	@Test
	public void invalidFebruaryStartDate() {
		
	}
	
	@Test
	public void futureStartDate() {
	
	}
	
}

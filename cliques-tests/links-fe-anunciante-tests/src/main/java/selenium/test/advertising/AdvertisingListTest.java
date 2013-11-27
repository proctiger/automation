package selenium.test.advertising;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import selenium.infra.LinksStatus;
import selenium.service.builder.AdvertisingService;
import selenium.service.builder.CampaignService;
import selenium.service.builder.DashboardService;
import selenium.service.builder.LoginService;
import selenium.test.BaseTest;

public class AdvertisingListTest extends BaseTest {

	private AdvertisingService advertisingService;

	public AdvertisingListTest() {
		advertisingService = new AdvertisingService();
	}

	@Before
	public void login() throws Exception {
		new LoginService(login, password).login();
		new CampaignService().clickInCampaign("campanha 400");
	}

	@After
	public void logout() {
		new DashboardService().action().exitPage();
	}

	@Test
	public void filterAllAdvertisings() throws Exception {
		advertisingService.allFilter();

		Assert.assertTrue(advertisingService.containsStatus(advertisingService
				.getStatus(LinksStatus.ACTIVE)));
		Assert.assertTrue(advertisingService.containsStatus(advertisingService
				.getStatus(LinksStatus.PAUSED)));
		Assert.assertTrue(advertisingService.containsStatus(advertisingService
				.getStatus(LinksStatus.DELETED)));
		Assert.assertTrue(advertisingService.containsStatus(advertisingService
				.getStatus(LinksStatus.INCOMPLETED)));
	}

	@Test
	public void filterAllAdvertisingsButExcluded() throws Exception {
		advertisingService.defaultFilter();

		Assert.assertTrue(advertisingService.containsStatus(advertisingService
				.getStatus(LinksStatus.ACTIVE)));
		Assert.assertTrue(advertisingService.containsStatus(advertisingService
				.getStatus(LinksStatus.PAUSED)));
		Assert.assertFalse(advertisingService.containsStatus(advertisingService
				.getStatus(LinksStatus.DELETED)));
		Assert.assertTrue(advertisingService.containsStatus(advertisingService
				.getStatus(LinksStatus.INCOMPLETED)));
	}

	@Test
	public void filterActivesAdvertisings() throws Exception {
		advertisingService.activeFilter();

		Assert.assertTrue(advertisingService.containsStatus(advertisingService
				.getStatus(LinksStatus.ACTIVE)));
		Assert.assertFalse(advertisingService.containsStatus(advertisingService
				.getStatus(LinksStatus.PAUSED)));
		Assert.assertFalse(advertisingService.containsStatus(advertisingService
				.getStatus(LinksStatus.DELETED)));
		Assert.assertFalse(advertisingService.containsStatus(advertisingService
				.getStatus(LinksStatus.INCOMPLETED)));
	}

	@Test
	public void filterExcludedsAdvertisings() throws Exception {
		advertisingService.excludedFilter();

		Assert.assertFalse(advertisingService.containsStatus(advertisingService
				.getStatus(LinksStatus.ACTIVE)));
		Assert.assertFalse(advertisingService.containsStatus(advertisingService
				.getStatus(LinksStatus.PAUSED)));
		Assert.assertTrue(advertisingService.containsStatus(advertisingService
				.getStatus(LinksStatus.DELETED)));
		Assert.assertFalse(advertisingService.containsStatus(advertisingService
				.getStatus(LinksStatus.INCOMPLETED)));
	}

	@Test
	public void filterPausedAdvertisings() throws Exception {
		advertisingService.pausedFilter();

		Assert.assertFalse(advertisingService.containsStatus(advertisingService
				.getStatus(LinksStatus.ACTIVE)));
		Assert.assertTrue(advertisingService.containsStatus(advertisingService
				.getStatus(LinksStatus.PAUSED)));
		Assert.assertFalse(advertisingService.containsStatus(advertisingService
				.getStatus(LinksStatus.DELETED)));
		Assert.assertFalse(advertisingService.containsStatus(advertisingService
				.getStatus(LinksStatus.INCOMPLETED)));
	}

	@Test
	public void filterIncompletedAdvertisings() throws Exception {
		advertisingService.incompletedFilter();

		Assert.assertFalse(advertisingService.containsStatus(advertisingService
				.getStatus(LinksStatus.ACTIVE)));
		Assert.assertFalse(advertisingService.containsStatus(advertisingService
				.getStatus(LinksStatus.PAUSED)));
		Assert.assertFalse(advertisingService.containsStatus(advertisingService
				.getStatus(LinksStatus.DELETED)));
		Assert.assertTrue(advertisingService.containsStatus(advertisingService
				.getStatus(LinksStatus.INCOMPLETED)));
	}

}

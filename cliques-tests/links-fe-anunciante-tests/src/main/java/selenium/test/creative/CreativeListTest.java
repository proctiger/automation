package selenium.test.creative;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import selenium.infra.LinksStatus;
import selenium.service.builder.AdvertisingService;
import selenium.service.builder.CampaignService;
import selenium.service.builder.CreativeService;
import selenium.service.builder.DashboardService;
import selenium.service.builder.LoginService;
import selenium.test.BaseTest;

public class CreativeListTest extends BaseTest {
	private CreativeService creativeService;

	public CreativeListTest() {
		creativeService = new CreativeService();
	}

	@Before
	public void login() throws Exception {
		new LoginService(login, password).login();
		new CampaignService().clickInCampaign("campanha 400");
		new AdvertisingService().clickInAdvertising("ativo");
	}

	@After
	public void logout() {
		new DashboardService().action().exitPage();
	}

	@Test
	public void filterAllCreatives() throws Exception {
		creativeService.allFilter();

		Assert.assertTrue(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.ACTIVE)));
		Assert.assertTrue(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.PAUSED)));
		Assert.assertTrue(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.DELETED)));
		Assert.assertTrue(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.PENDING)));
		Assert.assertTrue(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.REPROVED)));
	}

	@Test
	public void filterAllCreativesButExcluded() throws Exception {
		creativeService.defaultFilter();

		Assert.assertTrue(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.ACTIVE)));
		Assert.assertTrue(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.PAUSED)));
		Assert.assertFalse(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.DELETED)));
		Assert.assertTrue(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.PENDING)));
		Assert.assertTrue(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.REPROVED)));
	}

	@Test
	public void filterActivesCreatives() throws Exception {
		creativeService.activeFilter();

		Assert.assertTrue(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.ACTIVE)));
		Assert.assertFalse(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.PAUSED)));
		Assert.assertFalse(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.DELETED)));
		Assert.assertFalse(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.PENDING)));
		Assert.assertFalse(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.REPROVED)));
	}

	@Test
	public void filterExcludedsCreatives() throws Exception {
		creativeService.excludedFilter();

		Assert.assertFalse(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.ACTIVE)));
		Assert.assertFalse(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.PAUSED)));
		Assert.assertTrue(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.DELETED)));
		Assert.assertFalse(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.PENDING)));
		Assert.assertFalse(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.REPROVED)));
	}

	@Test
	public void filterPausedCreatives() throws Exception {
		creativeService.pausedFilter();

		Assert.assertFalse(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.ACTIVE)));
		Assert.assertTrue(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.PAUSED)));
		Assert.assertFalse(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.DELETED)));
		Assert.assertFalse(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.PENDING)));
		Assert.assertFalse(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.REPROVED)));
	}

	@Test
	public void filterPendingCreatives() throws Exception {
		creativeService.pendingFilter();

		Assert.assertFalse(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.ACTIVE)));
		Assert.assertFalse(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.PAUSED)));
		Assert.assertFalse(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.DELETED)));
		Assert.assertTrue(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.PENDING)));
		Assert.assertFalse(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.REPROVED)));
	}

	@Test
	public void filterReprovedCreatives() throws Exception {
		creativeService.reprovedFilter();

		Assert.assertFalse(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.ACTIVE)));
		Assert.assertFalse(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.PAUSED)));
		Assert.assertFalse(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.DELETED)));
		Assert.assertFalse(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.PENDING)));
		Assert.assertTrue(creativeService.containsStatus(creativeService
				.getStatus(LinksStatus.REPROVED)));
	}

}

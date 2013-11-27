package selenium.test.campaign;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import selenium.infra.LinksStatus;
import selenium.service.builder.CampaignService;
import selenium.service.builder.DashboardService;
import selenium.service.builder.LoginService;
import selenium.test.BaseTest;

public class CampaignListTest extends BaseTest {

	private CampaignService campaignService;

	public CampaignListTest() {
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
	public void filterAllCampaigns() throws Exception {
		campaignService.allFilter();

		Assert.assertTrue(campaignService.containsStatus(campaignService
				.getStatus(LinksStatus.ACTIVE)));
		Assert.assertTrue(campaignService.containsStatus(campaignService
				.getStatus(LinksStatus.PAUSED)));
		Assert.assertTrue(campaignService.containsStatus(campaignService
				.getStatus(LinksStatus.DELETED)));
		Assert.assertTrue(campaignService.containsStatus(campaignService
				.getStatus(LinksStatus.FINALIZED)));
	}

	@Test
	public void filterAllCampaignsButExcluded() throws Exception {
		campaignService.defaultFilter();

		Assert.assertTrue(campaignService.containsStatus(campaignService
				.getStatus(LinksStatus.ACTIVE)));
		Assert.assertTrue(campaignService.containsStatus(campaignService
				.getStatus(LinksStatus.PAUSED)));
		Assert.assertFalse(campaignService.containsStatus(campaignService
				.getStatus(LinksStatus.DELETED)));
		Assert.assertTrue(campaignService.containsStatus(campaignService
				.getStatus(LinksStatus.FINALIZED)));
	}

	@Test
	public void filterActivesCampaigns() throws Exception {
		campaignService.activeFilter();

		Assert.assertTrue(campaignService.containsStatus(campaignService
				.getStatus(LinksStatus.ACTIVE)));
		Assert.assertFalse(campaignService.containsStatus(campaignService
				.getStatus(LinksStatus.PAUSED)));
		Assert.assertFalse(campaignService.containsStatus(campaignService
				.getStatus(LinksStatus.DELETED)));
		Assert.assertFalse(campaignService.containsStatus(campaignService
				.getStatus(LinksStatus.FINALIZED)));
	}

	@Test
	public void filterExcludedsCampaigns() throws Exception {
		campaignService.excludedFilter();

		Assert.assertFalse(campaignService.containsStatus(campaignService
				.getStatus(LinksStatus.ACTIVE)));
		Assert.assertFalse(campaignService.containsStatus(campaignService
				.getStatus(LinksStatus.PAUSED)));
		Assert.assertTrue(campaignService.containsStatus(campaignService
				.getStatus(LinksStatus.DELETED)));
		Assert.assertFalse(campaignService.containsStatus(campaignService
				.getStatus(LinksStatus.FINALIZED)));
	}

	@Test
	public void filterPausedCampaigns() throws Exception {
		campaignService.pausedFilter();

		Assert.assertFalse(campaignService.containsStatus(campaignService
				.getStatus(LinksStatus.ACTIVE)));
		Assert.assertTrue(campaignService.containsStatus(campaignService
				.getStatus(LinksStatus.PAUSED)));
		Assert.assertFalse(campaignService.containsStatus(campaignService
				.getStatus(LinksStatus.DELETED)));
		Assert.assertFalse(campaignService.containsStatus(campaignService
				.getStatus(LinksStatus.FINALIZED)));
	}

	@Test
	public void filterFinalizedCampaigns() throws Exception {
		campaignService.finalizedFilter();

		Assert.assertFalse(campaignService.containsStatus(campaignService
				.getStatus(LinksStatus.ACTIVE)));
		Assert.assertFalse(campaignService.containsStatus(campaignService
				.getStatus(LinksStatus.PAUSED)));
		Assert.assertFalse(campaignService.containsStatus(campaignService
				.getStatus(LinksStatus.DELETED)));
		Assert.assertTrue(campaignService.containsStatus(campaignService
				.getStatus(LinksStatus.FINALIZED)));
	}

}

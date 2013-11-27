package selenium.test.flight;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import selenium.infra.LinksStatus;
import selenium.service.builder.AdvertisingService;
import selenium.service.builder.DashboardService;
import selenium.service.builder.FlightService;
import selenium.service.builder.CampaignService;
import selenium.service.builder.LoginService;
import selenium.test.BaseTest;

public class FlightListTest extends BaseTest {

	private FlightService flightService;

	public FlightListTest() {
		flightService = new FlightService();
		
	}

	@Before
	public void login() throws Exception {
		new LoginService(login, password).login();
		new CampaignService().clickInCampaign("campanha 400");
		new AdvertisingService().clickInAdvertising("ativo");
		flightService.tabFlight();
	}

	@After
	public void logout() {
		new DashboardService().action().exitPage();
	}

	@Test
	public void filterAllFlights() throws Exception {
		flightService.allFilter();

		Assert.assertTrue(flightService.containsStatus(flightService
				.getStatus(LinksStatus.ACTIVE)));
		Assert.assertTrue(flightService.containsStatus(flightService
				.getStatus(LinksStatus.PAUSED)));
		Assert.assertTrue(flightService.containsStatus(flightService
				.getStatus(LinksStatus.DELETED)));
	}

	@Test
	public void filterAllFlightsButExcluded() throws Exception {
		flightService.defaultFilter();

		Assert.assertTrue(flightService.containsStatus(flightService
				.getStatus(LinksStatus.ACTIVE)));
		Assert.assertTrue(flightService.containsStatus(flightService
				.getStatus(LinksStatus.PAUSED)));
		Assert.assertFalse(flightService.containsStatus(flightService
				.getStatus(LinksStatus.DELETED)));
	}

	@Test
	public void filterActivesFlights() throws Exception {
		flightService.activeFilter();

		Assert.assertTrue(flightService.containsStatus(flightService
				.getStatus(LinksStatus.ACTIVE)));
		Assert.assertFalse(flightService.containsStatus(flightService
				.getStatus(LinksStatus.PAUSED)));
		Assert.assertFalse(flightService.containsStatus(flightService
				.getStatus(LinksStatus.DELETED)));
	}

	@Test
	public void filterExcludedsFlights() throws Exception {
		flightService.excludedFilter();

		Assert.assertFalse(flightService.containsStatus(flightService
				.getStatus(LinksStatus.ACTIVE)));
		Assert.assertFalse(flightService.containsStatus(flightService
				.getStatus(LinksStatus.PAUSED)));
		Assert.assertTrue(flightService.containsStatus(flightService
				.getStatus(LinksStatus.DELETED)));
	}

	@Test
	public void filterPausedFlights() throws Exception {
		flightService.pausedFilter();

		Assert.assertFalse(flightService.containsStatus(flightService
				.getStatus(LinksStatus.ACTIVE)));
		Assert.assertTrue(flightService.containsStatus(flightService
				.getStatus(LinksStatus.PAUSED)));
		Assert.assertFalse(flightService.containsStatus(flightService
				.getStatus(LinksStatus.DELETED)));
	}
}


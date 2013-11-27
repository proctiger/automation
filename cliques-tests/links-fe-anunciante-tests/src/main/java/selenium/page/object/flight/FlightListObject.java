package selenium.page.object.flight;

import java.util.List;

import org.openqa.selenium.WebElement;

import selenium.template.method.WebDriverTemplate;
import selenium.template.method.selenium.Locations;

public class FlightListObject {
	private WebDriverTemplate webPage;

	public FlightListObject(WebDriverTemplate webPage) {
		this.webPage = webPage;
	}

	public WebElement statusList() {
		return webPage.getElement().findElementBy(Locations.XPATH,
				"(//dt[@id='title-status'])[2]");
	}

	public WebElement allFlightsButExcluded() {
		return webPage.getElement().findElementBy(Locations.XPATH,
				"(//li[@id='D'])[2]");
	}

	public WebElement allFlights() {
		return webPage.getElement().findElementBy(Locations.XPATH,
				"(//li[@id='T'])[2]");
	}

	public WebElement activesFlights() {
		return webPage.getElement().findElementBy(Locations.XPATH,
				"(//li[@id='A'])[2]");
	}

	public WebElement pausedFlights() {
		return webPage.getElement().findElementBy(Locations.XPATH,
				"(//li[@id='P'])[2]");
	}

	public WebElement excludedsFlights() {
		return webPage.getElement().findElementBy(Locations.XPATH,
				"(//li[@id='X'])[2]");
	}

	public WebElement btnFilter() {
		return webPage.getElement().findElementBy(Locations.XPATH,
				"(//input[@value='Filtrar'])[2]");
	}

	public List<WebElement> entityStatus(String arg) {
		return webPage.getElement().findElementsBy(Locations.CSS, arg);
	}

	public WebElement tabFlight() {
		return webPage.getElement().findElementBy(Locations.LINKTEXT,
				"Segmentações");
	}
}

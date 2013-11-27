package selenium.page.object.campaign;

import org.openqa.selenium.WebElement;

import selenium.template.method.WebDriverTemplate;
import selenium.template.method.selenium.Locations;

public class CampaignCreateObject {
	
	private WebDriverTemplate webPage;

	public CampaignCreateObject(WebDriverTemplate webPage) {
		this.webPage = webPage;
	}
	
	public WebElement createCampaignLink() {
		return webPage.getElement().findElementBy(Locations.LINKTEXT, "Criar campanha");
	}
	
	public WebElement title() {
		return webPage.getElement().findElementBy(Locations.NAME, "title");
	}
	
	public WebElement startDate() {
		return webPage.getElement().findElementBy(Locations.ID, "durCampStart");
	}

	public WebElement endDate() {
		return webPage.getElement().findElementBy(Locations.ID, "durCampEnd");
	}
	
	public WebElement budget() {
		return webPage.getElement().findElementBy(Locations.NAME, "budget.dailyBudget");
	}
	
	public WebElement btnCreateAndGoAdvetising() {
		return webPage.getElement().findElementBy(Locations.XPATH, "//button[@type='button']");
	}
}
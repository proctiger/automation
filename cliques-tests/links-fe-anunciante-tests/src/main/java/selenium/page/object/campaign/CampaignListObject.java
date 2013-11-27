package selenium.page.object.campaign;

import java.util.List;

import org.openqa.selenium.WebElement;

import selenium.template.method.WebDriverTemplate;
import selenium.template.method.selenium.Locations;

public class CampaignListObject {
	
	private WebDriverTemplate webPage;
	
	public CampaignListObject(WebDriverTemplate webPage) {
		this.webPage = webPage;
	}
	
	public WebElement statusList() {
		return webPage.getElement().findElementBy(Locations.ID, "title-status");
	}
		
	public WebElement allCampaigns() {
		return webPage.getElement().findElementBy(Locations.ID, "T");
	}
	
	public WebElement allButExcluded() {
		return webPage.getElement().findElementBy(Locations.ID, "D");
	}
		
	public WebElement actives() {
		return webPage.getElement().findElementBy(Locations.ID, "A");
	}
	
	public WebElement paused() {
		return webPage.getElement().findElementBy(Locations.ID, "P");
	}
	
	public WebElement excludeds() {
		return webPage.getElement().findElementBy(Locations.ID, "X");
	}
	
	public WebElement finalized() {
		return webPage.getElement().findElementBy(Locations.ID, "F");
	}
	
	public WebElement btnFilter() {
		return webPage.getElement().findElementBy(Locations.XPATH, "//input[@value='Filtrar']");
	}
	
	public List<WebElement> entityStatus(String arg) {
		return webPage.getElement().findElementsBy(Locations.CSS, arg);
	}
	
	public WebElement linkCampaign(String campaignText) {
		return webPage.getElement().findElementBy(Locations.LINKTEXT, campaignText);
	}
}

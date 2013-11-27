package selenium.page.object.Advertising;

import java.util.List;

import org.openqa.selenium.WebElement;

import selenium.template.method.WebDriverTemplate;
import selenium.template.method.selenium.Locations;

public class AdvertisingListObject {

private WebDriverTemplate webPage;
	
	public AdvertisingListObject(WebDriverTemplate webPage) {
		this.webPage = webPage;
	}
	
	public WebElement statusList() {
		return webPage.getElement().findElementBy(Locations.ID, "title-status");
	}
		
	public WebElement allAdvertisingsButExcluded() {
		return webPage.getElement().findElementBy(Locations.ID, "D");
	}
	
	public WebElement allAdvertisings() {
		return webPage.getElement().findElementBy(Locations.ID, "T");
	}
		
	public WebElement activesAdvertisings() {
		return webPage.getElement().findElementBy(Locations.ID, "A");
	}
	
	public WebElement pausedAdvertisings() {
		return webPage.getElement().findElementBy(Locations.ID, "P");
	}
	
	public WebElement excludedsAdvertisings() {
		return webPage.getElement().findElementBy(Locations.ID, "X");
	}
	
	public WebElement incompleteAdvertisings() {
		return webPage.getElement().findElementBy(Locations.ID, "I");
	}
	
	public WebElement btnFilter() {
		return webPage.getElement().findElementBy(Locations.XPATH, "//input[@value='Filtrar']");
	}
	
	public List<WebElement> entityStatus(String arg) {
		return webPage.getElement().findElementsBy(Locations.CSS, arg);
	}
	
	public WebElement linkAdvertising(String text) {
		return webPage.getElement().findElementBy(Locations.LINKTEXT, text);
	}
}

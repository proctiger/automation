package selenium.page.object.creative;

import java.util.List;

import org.openqa.selenium.WebElement;

import selenium.template.method.WebDriverTemplate;
import selenium.template.method.selenium.Locations;

public class CreativeListObject {
	
	private WebDriverTemplate webPage;
	
	public CreativeListObject(WebDriverTemplate webPage) {
		this.webPage = webPage;
	}
	
	public WebElement statusList() {
		return webPage.getElement().findElementBy(Locations.ID, "title-status");
	}
		
	public WebElement allCreativesButExcluded() {
		return webPage.getElement().findElementBy(Locations.ID, "D");
	}
	
	public WebElement allCreatives() {
		return webPage.getElement().findElementBy(Locations.ID, "T");
	}
		
	public WebElement activesCreatives() {
		return webPage.getElement().findElementBy(Locations.ID, "A");
	}
	
	public WebElement pausedCreatives() {
		return webPage.getElement().findElementBy(Locations.ID, "P");
	}
	
	public WebElement excludedsCreatives() {
		return webPage.getElement().findElementBy(Locations.ID, "X");
	}
	
	public WebElement pendingCreatives() {
		return webPage.getElement().findElementBy(Locations.ID, "M");
	}
	
	public WebElement reprovedCreatives() {
		return webPage.getElement().findElementBy(Locations.ID, "R");
	}
	
	public WebElement btnFilter() {
		return webPage.getElement().findElementBy(Locations.XPATH, "//input[@value='Filtrar']");
	}
	
	public List<WebElement> entityStatus(String arg) {
		return webPage.getElement().findElementsBy(Locations.CSS, arg);
	}
}

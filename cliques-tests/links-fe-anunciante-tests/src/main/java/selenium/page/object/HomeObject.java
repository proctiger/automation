package selenium.page.object;

import org.openqa.selenium.WebElement;

import selenium.template.method.WebDriverTemplate;
import selenium.template.method.selenium.Locations;

public class HomeObject {

	public static final String HOME_URL = "http://cliques.uol.com.br";

	private WebDriverTemplate webPage;

	public HomeObject(WebDriverTemplate webPage) {
		this.webPage = webPage;
	}

	public WebElement enter() {
		return webPage.getElement().findElementBy(Locations.ID, "submiter");
	}

	public WebElement email() {
		return webPage.getElement().findElementBy(Locations.ID, "email");
	}

	public WebElement senha() {
		return webPage.getElement().findElementBy(Locations.ID, "pass");
	}
}

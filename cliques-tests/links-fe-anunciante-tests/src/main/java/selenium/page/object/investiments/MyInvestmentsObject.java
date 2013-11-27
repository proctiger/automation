package selenium.page.object.investiments;

import org.openqa.selenium.WebElement;

import selenium.template.method.WebDriverTemplate;
import selenium.template.method.selenium.Locations;

public class MyInvestmentsObject {

	public static final String MYINVESTMENTS_URL = "http://cliques.uol.com.br/links/advertiser/myInvestments.html";

	private WebDriverTemplate webPage;

	public MyInvestmentsObject(WebDriverTemplate webPage) {
		this.webPage = webPage;
	}

	public WebElement manualInvestment() {
		return webPage.getElement().findElementBy(Locations.LINKTEXT,
				"Fazer investimento manual");
	}

	public WebElement automaticInvestment() {
		return webPage.getElement().findElementBy(Locations.CLASSNAME,
				"cliques-button green-theme large-366 ativar-renew");
	}

	public WebElement extract() {
		return webPage.getElement().findElementBy(Locations.LINKTEXT,
				"/links/advertiser/showAccountStatement.html");
	}

	public WebElement showCupom() {
		return webPage.getElement().findElementBy(Locations.ID, "show-coupon");
	}

	public WebElement coupom() {
		return webPage.getElement().findElementBy(Locations.ID, "coupom");
	}

	public WebElement submitBonus() {
		return webPage.getElement().findElementBy(Locations.ID, "enviar-bonus");
	}

}

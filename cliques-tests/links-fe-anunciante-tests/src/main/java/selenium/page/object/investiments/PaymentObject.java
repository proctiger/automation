package selenium.page.object.investiments;

import org.openqa.selenium.WebElement;

import selenium.template.method.WebDriverTemplate;
import selenium.template.method.selenium.Locations;

public class PaymentObject {

	public static final String PAYMENT_URL = "http://cliques.uol.com.br/links/advertiser/payment.html";

	private WebDriverTemplate webPage;

	public PaymentObject(WebDriverTemplate webPage) {
		this.webPage = webPage;
	}

	public WebElement addCredit() {
		return webPage.getElement().findElementBy(Locations.ID,
				"execute_submit");
	}

	public WebElement depositValue() {
		return webPage.getElement().findElementBy(Locations.ID, "depositValue");
	}

	public WebElement cancelCredit() {
		return webPage.getElement().findElementBy(Locations.CLASSNAME,
				"cliques-button gray-theme small-146");
	}

}

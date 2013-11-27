package selenium.page.object.investiments;

import org.openqa.selenium.WebElement;

import selenium.template.method.WebDriverTemplate;
import selenium.template.method.selenium.Locations;

public class AccountStatementObject {

	public static final String ACCOUNT_STATEMENT_URL = "http://cliques.uol.com.br/links/advertiser/showAccountStatement.html";

	private WebDriverTemplate webPage;

	public AccountStatementObject(WebDriverTemplate webPage) {
		this.webPage = webPage;
	}

	public WebElement newInvestmentLink() {
		return webPage.getElement().findElementBy(Locations.LINKTEXT,
				"NOVO INVESTIMENTO");
	}

	public WebElement extractLink() {
		return webPage.getElement()
				.findElementBy(Locations.LINKTEXT, "EXTRATO");
	}

}

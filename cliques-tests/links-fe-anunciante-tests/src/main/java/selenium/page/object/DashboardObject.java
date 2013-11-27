package selenium.page.object;

import org.openqa.selenium.WebElement;

import selenium.template.method.WebDriverTemplate;
import selenium.template.method.selenium.Locations;

public class DashboardObject {

	public static final String DASHBOARD_URL = "http://cliques.uol.com.br/links/advertiser/dashboard.html";

	private WebDriverTemplate webPage;

	public DashboardObject(WebDriverTemplate webPage) {
		this.webPage = webPage;
	}

	public WebElement controlPanelLink() {
		return webPage.getElement().findElementBy(Locations.LINKTEXT,
				"Painel de Controle");
	}

	public WebElement reportLink() {
		return webPage.getElement().findElementBy(Locations.LINKTEXT,
				"RELATÓRIOS");
	}

	public WebElement investmentLink() {
		return webPage.getElement().findElementBy(Locations.XPATH,
				"/html/body/div/div/nav/ul/li[5]/div/dl/dt/a");
	}
	
	public WebElement exitPage() {
		return webPage.getElement().findElementBy(Locations.LINKTEXT, "Sair");
	}
}

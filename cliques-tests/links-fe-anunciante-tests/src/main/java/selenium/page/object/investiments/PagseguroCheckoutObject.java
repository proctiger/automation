package selenium.page.object.investiments;

import org.openqa.selenium.WebElement;

import selenium.template.method.WebDriverTemplate;
import selenium.template.method.selenium.Locations;

public class PagseguroCheckoutObject {

	// public static final String PAGSEGUROCHECKOUT_URL =
	// "https://pagseguro.uol.com.br/checkout/formasPagto.jhtml#rmcl";

	private WebDriverTemplate webPage;

	public PagseguroCheckoutObject(WebDriverTemplate webPage) {
		this.webPage = webPage;
	}

	public WebElement creditCardCVV_wallet() {
		return webPage.getElement().findElementBy(Locations.ID,
				"creditCardCVV_wallet");
	}

	public WebElement confirmPayment() {
		return webPage.getElement().findElementBy(Locations.CSS,
				".pagseguro-button");
	}

	public WebElement showOneWallet() {
		return webPage.getElement()
				.findElementBy(Locations.ID, "showOneWallet");
	}

	public WebElement masterCardCredit() {
		return webPage.getElement().findElementBy(Locations.ID,
				"wcc_1_mastercard");
	}

	public WebElement bradescoTef() {
		return webPage.getElement().findElementBy(Locations.ID,
				"paymentMethod_eft");
	}

	public WebElement boleto() {
		return webPage.getElement().findElementBy(Locations.ID,
				"paymentMethod_booklet");
	}

	public WebElement walletChange() {
		return webPage.getElement().findElementBy(Locations.ID, "walletChange");
	}

	public WebElement backToShop() {
		return webPage.getElement().findElementBy(Locations.LINKTEXT,
				"Voltar para a loja");
	}

}

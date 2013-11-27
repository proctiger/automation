package selenium.page.action.investiments;

import selenium.page.action.AbstractAction;
import selenium.page.object.investiments.PagseguroCheckoutObject;

public class PagseguroCheckoutAction extends AbstractAction {

	public static final String CODE_VERIFY = "876";

	private PagseguroCheckoutObject pagseguroCheckoutObject;

	public PagseguroCheckoutAction() {
		pagseguroCheckoutObject = new PagseguroCheckoutObject(webPage);
	}

	public PagseguroCheckoutAction defaultCodeVerifyWallet(String arg) {
		pagseguroCheckoutObject.creditCardCVV_wallet().clear();
		pagseguroCheckoutObject.creditCardCVV_wallet().sendKeys(arg);
		return this;
	}

	public String ckickConfirmPayment() {
		pagseguroCheckoutObject.confirmPayment().click();
		return webPage.getCurrentUrl();

	}

	public String clickBackToShop() {
		pagseguroCheckoutObject.backToShop().submit();
		return webPage.getCurrentUrl();
	}

}

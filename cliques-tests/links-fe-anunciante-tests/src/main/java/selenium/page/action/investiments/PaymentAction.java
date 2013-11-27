package selenium.page.action.investiments;

import selenium.page.action.AbstractAction;
import selenium.page.object.investiments.PaymentObject;

public class PaymentAction extends AbstractAction {

	private PaymentObject paymentObject;

	public PaymentAction() {
		paymentObject = new PaymentObject(webPage);
	}

	public PaymentAction openPayment() {
		webPage.openPage(PaymentObject.PAYMENT_URL);
		return this;
	}

	public String addCredit() {
		paymentObject.addCredit().submit();
		return webPage.getCurrentUrl();
	}

	public String cancelCredit() {
		paymentObject.cancelCredit().click();
		return webPage.getCurrentUrl();
	}

	public PaymentAction depositValue(String arg) {
		paymentObject.depositValue().clear();
		paymentObject.depositValue().sendKeys(arg);
		return this;
	}

}

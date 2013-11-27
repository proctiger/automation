package selenium.page.action.investiments;

import selenium.page.action.AbstractAction;
import selenium.page.object.investiments.MyInvestmentsObject;

public class MyInvestmentsAction extends AbstractAction {

	private MyInvestmentsObject myInvestmentsObject;

	public MyInvestmentsAction() {
		myInvestmentsObject = new MyInvestmentsObject(webPage);
	}

	public MyInvestmentsAction openMyInvestment() {
		webPage.openPage(MyInvestmentsObject.MYINVESTMENTS_URL);
		return this;
	}

	public String enterManualInvestment() {
		myInvestmentsObject.manualInvestment().click();
		return webPage.getCurrentUrl();
	}

	public String enterAutomaticInvestment() {
		myInvestmentsObject.automaticInvestment().click();
		return webPage.getCurrentUrl();
	}

	public void submitBonus(String bonusMessage) {
		myInvestmentsObject.submitBonus().click();
		webPage.found(bonusMessage);
	}
}

package selenium.page.action.investiments;

import selenium.page.action.AbstractAction;
import selenium.page.object.investiments.AccountStatementObject;

public class AccountStatementAction extends AbstractAction {

	private AccountStatementObject accountStatementObject;

	public AccountStatementAction() {
		accountStatementObject = new AccountStatementObject(webPage);
	}

	public String clickNewInvestment() {
		accountStatementObject.newInvestmentLink().click();
		return webPage.getCurrentUrl();
	}

}

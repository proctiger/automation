package selenium.service.builder;

import selenium.page.action.investiments.AccountStatementAction;
import selenium.page.action.investiments.MyInvestmentsAction;
import selenium.page.action.investiments.PagseguroCheckoutAction;
import selenium.page.action.investiments.PaymentAction;

public class InvestimentsService {

	private AccountStatementAction accountStatement;

	private MyInvestmentsAction myInvestment;
	private PaymentAction payment;
	private PagseguroCheckoutAction pagseguroCheckout;

	public InvestimentsService() {
		this.accountStatement = new AccountStatementAction();

		this.myInvestment = new MyInvestmentsAction();
		this.payment = new PaymentAction();
		this.pagseguroCheckout = new PagseguroCheckoutAction();
	}

	public AccountStatementAction accountStatement() {
		return accountStatement;
	}

	public MyInvestmentsAction myInvestment() {
		return myInvestment;
	}

	public PaymentAction payment() {
		return payment;
	}

	public PagseguroCheckoutAction pagseguroCheckout() {
		return pagseguroCheckout;
	}
}

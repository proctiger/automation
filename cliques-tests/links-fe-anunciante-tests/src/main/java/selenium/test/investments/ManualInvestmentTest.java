package selenium.test.investments;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import selenium.service.builder.DashboardService;
import selenium.service.builder.InvestimentsService;
import selenium.service.builder.LoginService;
import selenium.test.BaseTest;

public class ManualInvestmentTest extends BaseTest {

	private InvestimentsService investiments;

	@Before
	public void login() throws Exception {
		new LoginService(login, password).login();
		investiments = new InvestimentsService();
	}

	@After
	public void logout() {
		new DashboardService().action().exitPage();
	}

	@Test
	public void inputManualInvestment() throws Exception {
		String urlAccount = new DashboardService().action().clickInvestment();
		Assert.assertEquals(
				"http://cliques.uol.com.br/links/advertiser/showAccountStatement.html",
				urlAccount);

		String urlNewInvestment = investiments.accountStatement()
				.clickNewInvestment();
		Assert.assertEquals(
				"http://cliques.uol.com.br/links/advertiser/myInvestments.html",
				urlNewInvestment);

		String urlPayment = investiments.myInvestment().enterManualInvestment();
		Assert.assertEquals(
				"http://cliques.uol.com.br/links/advertiser/payment.html",
				urlPayment);

		investiments.payment().depositValue("101,00");
		investiments.payment().addCredit();
		investiments.pagseguroCheckout().defaultCodeVerifyWallet("541");
		investiments.pagseguroCheckout().ckickConfirmPayment();
		investiments.pagseguroCheckout().clickBackToShop();
	}

}

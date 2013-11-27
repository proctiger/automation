package selenium.page.action;

import selenium.page.object.DashboardObject;

public class DashboardAction extends AbstractAction {

	private DashboardObject dashboardObject;

	public DashboardAction() {
		dashboardObject = new DashboardObject(webPage);
	}

	public String clickControlPanel() {
		dashboardObject.controlPanelLink().click();
		return webPage.getCurrentUrl();
	}

	public String clickReport() {
		dashboardObject.reportLink().click();
		return webPage.getCurrentUrl();
	}

	public String clickInvestment() {
		dashboardObject.investmentLink().click();
		return webPage.getCurrentUrl();
	}
	
	public DashboardAction exitPage() {
		dashboardObject.exitPage().click();
		return this;
	}
}

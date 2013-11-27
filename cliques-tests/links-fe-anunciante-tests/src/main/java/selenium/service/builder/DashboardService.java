package selenium.service.builder;

import selenium.page.action.DashboardAction;


public class DashboardService {
	private DashboardAction action;

	public DashboardService() {
		this.action = new DashboardAction();
	}

	public DashboardAction action() {
		return action;
	}
}

package selenium.service.builder;

import selenium.infra.LinksStatus;
import selenium.page.action.flight.FlightListAction;

public class FlightService {
	private FlightListAction list;

	public FlightService() {
		this.list = new FlightListAction();
	}

	public void defaultFilter() {
		list.clickStatusList().clickAllButExcluded().clickFilter();
	}

	public void allFilter() {
		list.clickStatusList().clickAll().clickFilter();
	}

	public void activeFilter() {
		list.clickStatusList().clickActives().clickFilter();
	}

	public void pausedFilter() {
		list.clickStatusList().clickPaused().clickFilter();
	}

	public void excludedFilter() {
		list.clickStatusList().clickExcluded().clickFilter();
	}

	public String getStatus(LinksStatus status) {
		switch (status) {
		case ACTIVE:
			return "span.A";
		case PAUSED:
			return "span.I";
		case DELETED:
			return "span.X";
		default:
			return null;
		}
	}

	public boolean containsStatus(String status) {
		if (list.countStatus(status) > 0) {
			return true;
		}
		return false;
	}

	public FlightService tabFlight() {
		list.clickTabFlight();
		return this;
	}

	public FlightListAction getList() {
		return list;
	}
	
}

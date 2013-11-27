package selenium.page.action.flight;

import selenium.page.action.AbstractAction;
import selenium.page.object.flight.FlightListObject;

public class FlightListAction extends AbstractAction {

	private FlightListObject flightListObject;

	public FlightListAction() {
		flightListObject = new FlightListObject(webPage);
	}

	public FlightListAction clickStatusList() {
		flightListObject.statusList().click();
		webPage.waitPageLoad();
		return this;
	}

	public FlightListAction clickAll() {
		flightListObject.allFlights().click();
		return this;
	}

	public FlightListAction clickAllButExcluded() {
		flightListObject.allFlightsButExcluded().click();
		return this;
	}

	public FlightListAction clickActives() {
		flightListObject.activesFlights().click();
		return this;
	}

	public FlightListAction clickPaused() {
		flightListObject.pausedFlights().click();
		return this;
	}

	public FlightListAction clickExcluded() {
		flightListObject.excludedsFlights().click();
		return this;
	}

	public FlightListAction clickFilter() {
		flightListObject.btnFilter().click();
		return this;
	}

	public int countStatus(String arg) {
		return flightListObject.entityStatus(arg).size();
	}

	public FlightListAction clickTabFlight() {
		flightListObject.tabFlight().click();
		webPage.waitPageLoad();
		return this;
	}
}

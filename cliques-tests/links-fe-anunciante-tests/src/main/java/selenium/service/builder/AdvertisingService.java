package selenium.service.builder;

import selenium.infra.LinksStatus;
import selenium.page.action.advertising.AdvertisingListAction;

public class AdvertisingService {

	private AdvertisingListAction list;

	public AdvertisingService() {
		this.list = new AdvertisingListAction();
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

	public void incompletedFilter() {
		list.clickStatusList().clickIncomplete().clickFilter();
	}

	public String getStatus(LinksStatus status) {
		switch (status) {
		case ACTIVE:
			return "span.A";
		case PAUSED:
			return "span.I";
		case DELETED:
			return "span.X";
		case INCOMPLETED:
			return "span.IC";
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

	public void clickInAdvertising(String title) {
		list.clickAdvertising(title);
	}

	public AdvertisingListAction getList() {
		return list;
	}
	
}

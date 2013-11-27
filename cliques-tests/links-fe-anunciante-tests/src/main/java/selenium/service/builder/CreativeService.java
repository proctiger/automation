package selenium.service.builder;

import selenium.infra.LinksStatus;
import selenium.page.action.creative.CreativeListAction;

public class CreativeService {
	private CreativeListAction list;

	public CreativeService() {
		this.list = new CreativeListAction();
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

	public void pendingFilter() {
		list.clickStatusList().clickPending().clickFilter();
	}

	public void reprovedFilter() {
		list.clickStatusList().clickReproved().clickFilter();
	}

	public String getStatus(LinksStatus status) {
		switch (status) {
		case ACTIVE:
			return "span.A";
		case PAUSED:
			return "span.I";
		case DELETED:
			return "span.X";
		case PENDING:
			return "span.PENDING";
		case REPROVED:
			return "span.REPROVED";
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

	public CreativeListAction getList() {
		return list;
	}
}

package selenium.page.action.creative;

import selenium.page.action.AbstractAction;
import selenium.page.object.creative.CreativeListObject;

public class CreativeListAction extends AbstractAction {

	private CreativeListObject creativeListObject;

	public CreativeListAction() {
		creativeListObject = new CreativeListObject(webPage);
	}

	public CreativeListAction clickStatusList() {
		creativeListObject.statusList().click();
		webPage.waitPageLoad();
		return this;
	}

	public CreativeListAction clickAll() {
		creativeListObject.allCreatives().click();
		return this;
	}

	public CreativeListAction clickAllButExcluded() {
		creativeListObject.allCreativesButExcluded().click();
		return this;
	}

	public CreativeListAction clickActives() {
		creativeListObject.activesCreatives().click();
		return this;
	}

	public CreativeListAction clickPaused() {
		creativeListObject.pausedCreatives().click();
		return this;
	}

	public CreativeListAction clickExcluded() {
		creativeListObject.excludedsCreatives().click();
		return this;
	}

	public CreativeListAction clickPending() {
		creativeListObject.pendingCreatives().click();
		return this;
	}

	public CreativeListAction clickReproved() {
		creativeListObject.reprovedCreatives().click();
		return this;
	}

	public CreativeListAction clickFilter() {
		creativeListObject.btnFilter().click();
		webPage.waitPageLoad();
		return this;
	}

	public int countStatus(String arg) {
		return creativeListObject.entityStatus(arg).size();
	}
}

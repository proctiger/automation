package selenium.page.action.advertising;

import selenium.page.action.AbstractAction;
import selenium.page.object.Advertising.AdvertisingListObject;

public class AdvertisingListAction extends AbstractAction {
	
	private AdvertisingListObject advertisingListObject;
	
	public AdvertisingListAction() {
		advertisingListObject = new AdvertisingListObject(webPage);
	}
	
	public AdvertisingListAction clickStatusList() {
		advertisingListObject.statusList().click();
		return this;
	}

	public AdvertisingListAction clickAll() {
		advertisingListObject.allAdvertisings().click();
		return this;
	}

	public AdvertisingListAction clickAllButExcluded() {
		advertisingListObject.allAdvertisingsButExcluded().click();
		return this;
	}

	public AdvertisingListAction clickActives() {
		advertisingListObject.activesAdvertisings().click();
		return this;
	}

	public AdvertisingListAction clickPaused() {
		advertisingListObject.pausedAdvertisings().click();
		return this;
	}

	public AdvertisingListAction clickExcluded() {
		advertisingListObject.excludedsAdvertisings().click();
		return this;
	}

	public AdvertisingListAction clickIncomplete() {
		advertisingListObject.incompleteAdvertisings().click();
		return this;
	}

	public AdvertisingListAction clickFilter() {
		advertisingListObject.btnFilter().click();
		webPage.waitPageLoad();
		return this;
	}

	public int countStatus(String arg) {
		return advertisingListObject.entityStatus(arg).size();
	}
	
	public AdvertisingListAction clickAdvertising(String text) {
		advertisingListObject.linkAdvertising(text).click();
		webPage.waitPageLoad();
		return this;
	}
}

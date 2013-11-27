package selenium.page.action.campaign;

import selenium.page.action.AbstractAction;
import selenium.page.object.campaign.CampaignListObject;

public class CampaignListAction extends AbstractAction {

	private CampaignListObject campaignListObject;

	public CampaignListAction() {
		campaignListObject = new CampaignListObject(webPage);
	}

	public CampaignListAction clickStatusList() {
		campaignListObject.statusList().click();
		return this;
	}

	public CampaignListAction clickAll() {
		campaignListObject.allCampaigns().click();
		return this;
	}

	public CampaignListAction clickAllButExcluded() {
		campaignListObject.allButExcluded().click();
		return this;
	}

	public CampaignListAction clickActives() {
		campaignListObject.actives().click();
		return this;
	}

	public CampaignListAction clickPaused() {
		campaignListObject.paused().click();
		return this;
	}

	public CampaignListAction clickExcluded() {
		campaignListObject.excludeds().click();
		return this;
	}

	public CampaignListAction clickFinalized() {
		campaignListObject.finalized().click();
		return this;
	}

	public CampaignListAction clickFilter() {
		campaignListObject.btnFilter().click();
		webPage.waitPageLoad();
		return this;
	}

	public int countStatus(String arg) {
		return campaignListObject.entityStatus(arg).size();
	}
	
	public CampaignListAction clickCampaign(String campaignText) {
		campaignListObject.linkCampaign(campaignText).click();
		webPage.waitPageLoad();
		return this;
	}
}

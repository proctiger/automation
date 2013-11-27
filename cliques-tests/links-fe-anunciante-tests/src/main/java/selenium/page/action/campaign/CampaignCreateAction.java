package selenium.page.action.campaign;

import selenium.page.action.AbstractAction;
import selenium.page.object.campaign.CampaignCreateObject;

public class CampaignCreateAction extends AbstractAction {

	private CampaignCreateObject campaignCreate;

	public CampaignCreateAction() {
		campaignCreate = new CampaignCreateObject(webPage);
	}

	public CampaignCreateAction clickCreateCampaignLink() {
		campaignCreate.createCampaignLink().click();
		webPage.waitPageLoad();
		return this;
	}

	public CampaignCreateAction title(String arg) {
		campaignCreate.title().click();
		campaignCreate.title().sendKeys(arg);
		return this;
	}

	public CampaignCreateAction startDate(String arg) {
		campaignCreate.startDate().click();
		campaignCreate.startDate().sendKeys(arg);
		return this;
	}

	public CampaignCreateAction endDate(String arg) {
		campaignCreate.endDate().click();
		campaignCreate.endDate().sendKeys(arg);
		return this;
	}

	public CampaignCreateAction budget(String arg) {
		campaignCreate.budget().click();
		campaignCreate.budget().sendKeys(arg);
		return this;
	}
	
	public boolean createAndGoAdvertising(){
		campaignCreate.btnCreateAndGoAdvetising().click();
		webPage.waitPageLoad();
		return !campaignCreate.btnCreateAndGoAdvetising().isDisplayed();
	}
}

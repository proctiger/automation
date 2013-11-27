package selenium.service.builder;

import java.math.BigDecimal;
import java.util.Date;

import selenium.infra.LinksStatus;
import selenium.infra.PageUtils;
import selenium.page.action.campaign.CampaignCreateAction;
import selenium.page.action.campaign.CampaignListAction;

public class CampaignService {

	private CampaignCreateAction create;
	private CampaignListAction list;

	private String title;
	private String startDate;
	private String endDate;
	private String budget;

	public CampaignService() {
		this.create = new CampaignCreateAction();
		this.list = new CampaignListAction();
	}

	public boolean createCampaign() {
		return create.clickCreateCampaignLink().title(title).startDate(startDate)
				.endDate(endDate).budget(budget).createAndGoAdvertising();
	}

	/************************
	 ******* FILTER
	 ************************/
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

	public void finalizedFilter() {
		list.clickStatusList().clickFinalized().clickFilter();
	}

	public void clickInCampaign(String title) {
		list.clickCampaign(title);
	}

	public String getStatus(LinksStatus status) {
		switch (status) {
		case ACTIVE:
			return "span.ACTIVE";
		case PAUSED:
			return "span.PAUSED";
		case DELETED:
			return "span.DELETED";
		case FINALIZED:
			return "span.FINALIZED";
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

	public CampaignService title(String title) {
		this.title = title;
		return this;
	}

	public CampaignService startDate(Date startDate) {
		return startDate(PageUtils.toString(startDate));
	}

	public CampaignService startDate(String startDate) {
		this.startDate = startDate;
		return this;
	}

	public CampaignService endDate(Date endDate) {
		return endDate(PageUtils.toString(endDate));
	}

	public CampaignService endDate(String endDate) {
		this.endDate = endDate;
		return this;
	}

	public CampaignService budget(int budget) {
		return budget(BigDecimal.valueOf(budget));
	}

	public CampaignService budget(BigDecimal budget) {
		this.budget = budget.toPlainString();
		return this;
	}

	public CampaignCreateAction getCreate() {
		return create;
	}

	public CampaignListAction getList() {
		return list;
	}
	
}

package selenium.page.action;

import selenium.page.object.HomeObject;

public class HomeAction extends AbstractAction {

	private HomeObject loginObject;

	public HomeAction() {
		loginObject = new HomeObject(webPage);
	}

	public HomeAction openHome() {
		webPage.openPage(HomeObject.HOME_URL);
		return this;
	}

	public HomeAction email(String arg) {
		loginObject.email().clear();
		loginObject.email().sendKeys(arg);
		return this;
	}

	public HomeAction password(String arg) {
		loginObject.senha().clear();
		loginObject.senha().sendKeys(arg);
		return this;
	}

	public String enter() {
		loginObject.enter().click();
		webPage.waitPageLoad();
		return webPage.getCurrentUrl();
	}
}

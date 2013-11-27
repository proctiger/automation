package selenium.page.action;

import selenium.template.method.WebDriverTemplate;

public class AbstractAction {

	protected WebDriverTemplate webPage;

	public AbstractAction() {
		webPage = WebDriverTemplate.getSeleniumPage();
	}
	
	public void closeWebPage() {
		webPage.closeWebPage();
	}
	
	public void quitPage() {
		webPage.quit();
	}
}

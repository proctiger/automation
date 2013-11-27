package selenium.template.method;

import java.util.List;

import selenium.template.method.selenium.SeleniumElement;
import selenium.template.method.selenium.SeleniumWebDriver;

public abstract class WebDriverTemplate {

	private static SeleniumWebDriver seleniumWebDriver;

	public static SeleniumWebDriver getSeleniumPage() {
		if (seleniumWebDriver == null) {
			seleniumWebDriver = new SeleniumWebDriver();
		}
		return seleniumWebDriver;
	}

	public abstract SeleniumElement getElement();

	public abstract void openPage(String url);

	public abstract String getCurrentUrl();

	public abstract void found(String text);

	public abstract void found(List<String> texts);

	public abstract void notFound(String text);

	public abstract String escapeHtml(String text);

	public abstract void closeWebPage();

	public abstract void quit();

	public abstract void waitPageLoad();

}

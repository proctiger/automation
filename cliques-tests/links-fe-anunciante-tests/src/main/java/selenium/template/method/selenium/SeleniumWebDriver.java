package selenium.template.method.selenium;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.template.method.WebDriverTemplate;

public class SeleniumWebDriver extends WebDriverTemplate implements WebDriver {

	private final WebDriver webDriver;
	private final WebDriverWait wait;
	private SeleniumElement element;

	public SeleniumWebDriver() {
		webDriver = new FirefoxDriver();

		wait = new WebDriverWait(webDriver, 10);
		element = new SeleniumElement(webDriver, wait);

		webDriver.manage().timeouts()
				.implicitlyWait(30, java.util.concurrent.TimeUnit.SECONDS);
		webDriver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		webDriver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
	}

	public SeleniumElement getElement() {
		return element;
	}

	public void found(String text) {
		found(getPageSource(), text);
	}

	public void found(List<String> texts) {
		for (String text : texts) {
			found(text);
		}
	}

	public void notFound(String text) {
		notFound(getPageSource(), text);
	}

	public String escapeHtml(String text) {
		return text.replace("<", "&lt;").replace(">", "&gt;");
	}

	private void found(String pageSource, String text) {
		if (!pageSource.contains(escapeHtml(text))) {
			fail("Text: '" + text + "' not found in page '" + pageSource + "'");
		}
	}

	private void notFound(String pageSource, String text) {
		Assert.assertFalse(pageSource.contains(escapeHtml(text)));
	}

	public void openPage(String url) {
		this.get(url);
	}

	public void close() {
		this.closeWebPage();
	}

	@Override
	public String getCurrentUrl() {
		return webDriver.getCurrentUrl();
	}

	@Override
	public String getTitle() {
		return webDriver.getTitle();
	}

	@Override
	public List<WebElement> findElements(By by) {
		return webDriver.findElements(by);
	}

	@Override
	public WebElement findElement(By by) {
		return webDriver.findElement(by);
	}

	@Override
	public String getPageSource() {
		return webDriver.getPageSource();
	}

	@Override
	public Set<String> getWindowHandles() {
		return webDriver.getWindowHandles();
	}

	@Override
	public String getWindowHandle() {
		return webDriver.getWindowHandle();
	}

	@Override
	public TargetLocator switchTo() {
		return webDriver.switchTo();
	}

	@Override
	public Navigation navigate() {
		return webDriver.navigate();
	}

	@Override
	public Options manage() {
		return webDriver.manage();
	}

	@Override
	public void quit() {
		webDriver.quit();
	}

	@Override
	public void get(String url) {
		webDriver.get(url);

	}
	
	public void waitPage() {
		webDriver.manage().timeouts().implicitlyWait(30, java.util.concurrent.TimeUnit.SECONDS);
	}

	public void waitPageLoad() {
		ExpectedCondition<Boolean> expected = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver input) {
				return ((JavascriptExecutor) input).executeScript(
						"return document.readyState").equals("complete");
			}
		};
		wait.until(expected);
	}

	@Override
	public void closeWebPage() {
		webDriver.close();

	}
}

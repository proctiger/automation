package selenium.template.method.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumElement {

	private final WebDriver seleniumDriver;
	private final WebDriverWait wait;

	public SeleniumElement(WebDriver driverTester, WebDriverWait wait) {
		this.seleniumDriver = driverTester;
		this.wait = wait;
	}

	public void waitDisappear(Locations locations, String arg) {
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(getBy(
					locations, arg)));
		} catch (Exception e) {
			throw new RuntimeException("Erro ao esperar a pagina carregar.");
		}
	}

	public void clear(Locations locations, String arg) {
		findElementBy(locations, arg).clear();
	}

	public void click(Locations locations, String arg) {
		findElementBy(locations, arg).click();
	}

	public void sendKeys(Locations locations, String arg, String value) {
		findElementBy(locations, arg).sendKeys(value);
	}

	public void getText(Locations locations, String arg) {
		findElementBy(locations, arg).getText();
	}

	public WebElement findElementBy(Locations locations, String arg) {
		return seleniumDriver.findElement(getBy(locations, arg));
	}

	public List<WebElement> findElementsBy(Locations locations, String arg) {
		return seleniumDriver.findElements(getBy(locations, arg));
	}

	private By getBy(Locations locations, String arg) {
		switch (locations) {
		case ID:
		default:
			return By.id(arg);
		case NAME:
			return By.name(arg);
		case CLASSNAME:
			return By.className(arg);
		case CSS:
			return By.cssSelector(arg);
		case XPATH:
			return By.xpath(arg);
		case LINKTEXT:
			return By.linkText(arg);
		}
	}
}

package uol.selenium.basics;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author dvrocha
 */
public class BasicWebPage {

	public WebDriver driver;

	public BasicWebPage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Verifica se um elemnto HTML existe na página
	 * 
	 * @param searchBy
	 * @return
	 */
	public boolean isElementPresent(By searchBy) {
		List<WebElement> elements = driver.findElements(searchBy);
		if (elements != null && elements.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Retorna um elemento web caso o mesmo exista
	 * 
	 * @param by
	 * @return
	 * @throws Exception
	 */
	public WebElement findElement(By by) throws Exception {
		if (isElementPresent(by)) {
			return driver.findElement(by);
		}
		throw new Exception("WebElement not found: " + by.toString());
	}
}

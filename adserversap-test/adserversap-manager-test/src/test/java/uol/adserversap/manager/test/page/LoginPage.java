package uol.adserversap.manager.test.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	private By userInput = new By.ById("username");
	private By passInput = new By.ById("password");
	private By loginButton = new By.ByXPath("//form/button[text() = 'Entrar']");
	private By errorMessageBox = new By.ByXPath(
			"//form/div[@class='message alert-message error']");

	public void putUserName(String userName) throws Exception {
		findElement(userInput).clear();
		findElement(userInput).sendKeys(userName);
	}

	public void putPassword(String password) throws Exception {
		findElement(passInput).clear();
		findElement(passInput).sendKeys(password);
	}

	public void submitLogin() throws Exception {
		findElement(loginButton).click();
	}

	public String getErrorMessage() throws Exception {
		return findElement(errorMessageBox).getText();
	}
}

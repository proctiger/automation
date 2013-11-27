package uol.bt.tracker.test.selenium;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import reconf.client.proxy.ConfigurationRepositoryFactory;
import uol.bt.tracker.test.reconf.ConfigBtTrackerTest;

public class UserAuthWebPage extends BasicWebPage {

	public UserAuthWebPage(WebDriver driver) {
		super(driver);
	}

	private static final ConfigBtTrackerTest config = ConfigurationRepositoryFactory.get(ConfigBtTrackerTest.class);
	private static final String USER_LOGIN = config.getProfileUser().get("login");
	private static final String USER_PASS = config.getProfileUser().get("password");

	private By userField = By.id("email");
	private By passField = By.id("senha");
	private By submitField = By.id("enviar");
	private By tracked = By.id("tracked");
	private By failed = By.id("failed");

	public void openAuthPage(String action) {
		openAuthPage(action, null);
	}

	public void openAuthPage(String action, String cookieValue) {
		String url = "http://tracker.bt.uol.com.br/user-auth.html?action=" + action;
		if (!StringUtils.isEmpty(cookieValue)) {
			url += "&set-cookie=" + cookieValue;
		}
		driver.get(url);
	}

	public void login() {
		driver.findElement(userField).sendKeys(USER_LOGIN);
		driver.findElement(passField).sendKeys(USER_PASS);
		driver.findElement(submitField).click();
	}

	public void logout() {
		driver.get("https://acesso.uol.com.br/logout.html?dest=REDIR|http://tracker.bt.uol.com.br/user-auth.html");
	}

	public void waitTrack() throws Exception {
		int counter = 0;
		while(true){
			if (isElementPresent(tracked) || isElementPresent(failed)) {
				break;
			}
			if(counter >= 20){
				throw new Exception("Página de track não foi carregada");
			}
			Thread.sleep(100);
			counter++;
		}
	}
}

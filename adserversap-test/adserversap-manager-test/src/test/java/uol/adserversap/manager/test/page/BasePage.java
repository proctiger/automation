package uol.adserversap.manager.test.page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import uol.selenium.basics.BasicWebPage;

public class BasePage extends BasicWebPage {

	public BasePage(WebDriver driver) {
		super(driver);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(1, TimeUnit.SECONDS);
	}

	public final String SYSTEM_LOCATION = "https://manager.adserversap.intranet";
	public final String LOGOUT_LOCATION = "https://manager.adserversap.intranet/login.html";

	private final By loggedUserName = new By.ByXPath(
			"//div[@class='toolbar']/strong");
	private final By userAdminMenu = new By.ByXPath(
			"//ul[@id='menu']/li/dl/dd/ul/li/a[@href='/usuarios/index.html']");
	private final By debugatorMenu = new By.ByXPath(
			"//ul[@id='menu']/li/dl/dd/ul/li/a[@href='/dbg-service.html']");

	private By addLoginButton 						= new By.ById("adicionar");
	private By removeLoginButtonAdmin_1				= new By.ById("admin_1-remover");
	private By removeLoginButtonAdmin_2				= new By.ById("admin_2-remover");
	private By removeLoginButtonConfirmation		= new By.ById("confirm");
	private By cancelRemoveLoginButtonConfirmation	= new By.ById("cancel");
	private By editLoginButtonAdmin_2				= new By.ById("admin_2-editar");
	private By cancelEditLoginButton 				= new By.ById("cancelar");

	public void open() throws Exception {
		driver.get(SYSTEM_LOCATION);
	}

	public void submitLogout() throws Exception {
		driver.get(LOGOUT_LOCATION);
	}

	public String getLoggedUserName() throws Exception {
		return findElement(loggedUserName).getText();
	}

	public boolean isLogged() throws Exception {
		return isElementPresent(loggedUserName);
	}

	public boolean userAdminMenuExists() throws Exception {
		return isElementPresent(userAdminMenu);
	}

	public boolean debugMenuExists() throws Exception {
		return isElementPresent(debugatorMenu);
	}
	
	public void submitAddLogin() throws Exception {
		Thread.sleep(1000);
		findElement(addLoginButton).click();
	}
	
	public void submitRemoveLogin() throws Exception {
		Thread.sleep(1000);
		findElement(removeLoginButtonAdmin_2).click();
	}
		
	public void submitConfirmationRemoveLogin() throws Exception {
		Thread.sleep(1000);
		findElement(removeLoginButtonConfirmation).click();
	}

	public void submitCancelConfirmationRemoveLogin() throws Exception {
		Thread.sleep(1000);
		findElement(cancelRemoveLoginButtonConfirmation).click();
	}

	public boolean checkIfButtonRemoveIsPresentForTheUser(String user) {
		boolean result = false;
		if (user.equals("admin_2")){
			result =  isElementPresent(removeLoginButtonAdmin_2);
		} else if (user.equals("admin_1")){
			result = isElementPresent(removeLoginButtonAdmin_1);
		}
		return result;
	}

	public void submitEditLogin() throws Exception {
		Thread.sleep(1000);
		findElement(editLoginButtonAdmin_2).click();
	}

	public void submitCancelEditLogin() throws Exception {
		Thread.sleep(1000);
		findElement(cancelEditLoginButton).click();
	}
}

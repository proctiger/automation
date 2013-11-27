package uol.adserversap.manager.test.page;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByLinkText;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.ui.Select;

/**
 * 
 * @author wrodrigues
 * 
 */
public class UserPage extends BasePage {

	public UserPage(WebDriver driver) {
		super(driver);
	}

	private By loginInput 			= new ByIdOrName("f-name");
	private By saveButton			= new By.ByCssSelector("button.btn.primary");
	private By cancelButton 		= new ByLinkText("Cancelar");	
	private By profileSelector		= new By.ById("f-roles");
	private By optionRootSelector	= new By.ByCssSelector("option[value=\"root\"]");
	private By messageText			= new By.ByCssSelector("BODY");
	private By errorMessageBox 		= new By.ByXPath("//dl[@class='msg1 error1']/dd");

	public boolean getMessageSuccessAddedUser(String user) throws Exception{
		String regex = "^[\\s\\S]*Usuário \"" + user + "\" criado com sucesso![\\s\\S]*$";
		return findElement(messageText).getText().matches(regex);
	}
	
	public boolean getMessageSuccessRemovedUser(String user) throws Exception{
		String regex = "^[\\s\\S]*Usuário \"" + user + "\" removido com sucesso![\\s\\S]*$";
		return findElement(messageText).getText().matches(regex);
	}
	
	public boolean getMessageSuccessEditedUser(String user) throws Exception{
		String regex = "^[\\s\\S]*Usuário \"" + user + "\" editado com sucesso![\\s\\S]*$";
		return findElement(messageText).getText().matches(regex);
	}
	
	public boolean getErrorMessage(String errorMessage) throws Exception{
		return findElement(messageText).getText().matches(errorMessage);
	}
	
	public void putUserName(String userName) throws Exception {
		findElement(loginInput).clear();
		findElement(loginInput).sendKeys(userName);
	}

	public void selectProfile(String profile) throws Exception {
		Thread.sleep(1000);
		new Select(findElement(profileSelector)).selectByVisibleText(profile);
	}
	
	public void submitSaveButton() throws Exception {
		Thread.sleep(1000);
		findElement(saveButton).click();
	}
	
	public void cancelButton() throws Exception {
		Thread.sleep(1000);
		findElement(cancelButton).click();
	}
	
	public void testCancelarButton() throws Exception {
		Thread.sleep(1000);
	    driver.findElement(By.linkText("Cancelar")).click();	
	}
	
	public String getErrorMessage() throws Exception {
		return findElement(errorMessageBox).getText();
	}

	public boolean isOptionRootPresent() throws Exception {
		return isElementPresent(optionRootSelector);
	}
	
}

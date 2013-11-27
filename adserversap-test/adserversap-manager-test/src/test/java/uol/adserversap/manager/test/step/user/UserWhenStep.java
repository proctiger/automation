package uol.adserversap.manager.test.step.user;

import uol.adserversap.dao.ploc.UserDao;
import uol.adserversap.entity.User;
import uol.adserversap.manager.test.domain.UserDomain;
import uol.adserversap.manager.test.step.BaseStep;
import cucumber.api.java.pt.Quando;

/**
 * 
 * @author wrodrigues
 * 
 */
public class UserWhenStep extends BaseStep {

	@Quando ("efetuar login com sucesso com perfil <(.+)>")
	public void loginUser(String profile) throws Exception {
		user = new UserDomain(DEFAULT_USER, profile);
		UserDao.persistUser(new User(user.getLogin(), user.getDbProfile(), 1));
		logout();
		loginPage.putUserName(user.getLogin());
		loginPage.putPassword(DEFAULT_PASS);
		loginPage.submitLogin();
	}
	
	@Quando ("adicionar login com perfil <(.+)>")
	public void addLoginProfile(String profile) throws Exception {
		user = new UserDomain(DEFAULT_USER2, profile);
		page.submitAddLogin();
		userPage.putUserName(user.getLogin());
		userPage.selectProfile(profile);
		userPage.submitSaveButton();
	}
	
	@Quando ("acessar página para criação de login")
	public void addLoginRootProfileAsAdmin() throws Exception {
		page.submitAddLogin();
	}
	
	@Quando ("adicionar login com perfil <(.+)> com caracter inválido")
	public void addLoginProfileWithInvalidLogin(String profile) throws Exception {
		user = new UserDomain("inválido", profile);
		page.submitAddLogin();
		userPage.putUserName(user.getLogin());
		userPage.selectProfile(profile);
		userPage.submitSaveButton();
	}
	
	@Quando ("adicionar login com perfil <(.+)> com tags html")
	public void addLoginProfileWithInvalidTagHtmlLogin(String profile) throws Exception {
		user = new UserDomain("<body>", profile);
		page.submitAddLogin();
		userPage.putUserName(user.getLogin());
		userPage.selectProfile(profile);
		userPage.submitSaveButton();
	}

	@Quando ("adicionar login com perfil <(.+)> sem preencher campo login")
	public void addLoginProfileWithoutLogin(String profile) throws Exception {
		user = new UserDomain(DEFAULT_USER2, profile);
		Thread.sleep(1000);
		page.submitAddLogin();
		userPage.selectProfile(profile);
		userPage.submitSaveButton();
	}
	
	@Quando ("acionar botão remover login com perfil <(.+)> e confirmar")
	public void submitRemoveButtonOfLoginAndConfirm(String profile) throws Exception {
		user = new UserDomain(DEFAULT_USER2, profile);
		page.submitRemoveLogin();
		page.submitConfirmationRemoveLogin();
	}
	
	@Quando ("acionar botão remover login com perfil <(.+)> e cancelar")
	public void submitRemoveButtonOfLoginAndCancel(String profile) throws Exception {
		user = new UserDomain(DEFAULT_USER2, profile);
		page.submitRemoveLogin();
		page.submitCancelConfirmationRemoveLogin();
	}

	@Quando ("acionar botão editar de login com perfil <(.+)>")
	public void submitEditButtonOfLogin(String profile) throws Exception {
		user = new UserDomain(DEFAULT_USER2, profile);
		page.submitEditLogin();
	}
	
	@Quando ("alterar login para perfil <(.+)> e salvar")
	public void changeProfileLoginAndSave(String profile) throws Exception {
		userPage.selectProfile(profile);
		userPage.submitSaveButton();
	}
	
	@Quando ("alterar login para perfil <(.+)> e cancelar")
	public void changeProfileLoginAndCancel(String profile) throws Exception {
		userPage.selectProfile(profile);
		page.submitCancelEditLogin();
	}
	
}

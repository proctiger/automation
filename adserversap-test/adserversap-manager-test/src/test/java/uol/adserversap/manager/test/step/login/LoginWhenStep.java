package uol.adserversap.manager.test.step.login;

import uol.adserversap.dao.ploc.UserDao;
import uol.adserversap.entity.User;
import uol.adserversap.manager.test.domain.UserDomain;
import uol.adserversap.manager.test.prepare.UserDataUtils;
import uol.adserversap.manager.test.step.BaseStep;
import cucumber.api.java.pt.Quando;

/**
 * 
 * @author dvrocha
 * 
 */
public class LoginWhenStep extends BaseStep {

	@Quando("efetuar login com um usuário de perfil <(.+)>")
	public void setUserProfile(String profile) throws Exception {
		user = new UserDomain(DEFAULT_USER, profile);
		UserDao.persistUser(new User(user.getLogin(), user.getDbProfile(), 1));
		logout();
		loginPage.putUserName(user.getLogin());
		loginPage.putPassword(DEFAULT_PASS);
		loginPage.submitLogin();
	}

	@Quando("efetuar login sem informar login")
	public void loginWithoutUsername() throws Exception {
		logout();
		loginPage.putPassword(DEFAULT_PASS);
		loginPage.submitLogin();
	}

	@Quando("efetuar login sem informar senha")
	public void loginWithoutPassword() throws Exception {
		logout();
		loginPage.putUserName(DEFAULT_USER);
		loginPage.submitLogin();
	}

	@Quando("^efetuar login sem informar login nem senha$")
	public void loginWithoutUsernameAndPassword() throws Exception {
		logout();
		loginPage.submitLogin();
	}

	@Quando("^efetuar login informando senha inválida$")
	public void setUserProfile() throws Exception {
		logout();
		loginPage.putUserName(DEFAULT_USER);
		loginPage.putPassword("invalidpass");
		loginPage.submitLogin();
	}

	@Quando("^efetuar login com um usuário inativo$")
	public void setUserInactive() throws Exception {
		user = new UserDomain(DEFAULT_USER,
				UserDataUtils.getRandomProfileDomain());
		UserDao.persistUser(new User(user.getLogin(), user.getDbProfile(), 0));
		logout();
		loginPage.putUserName(user.getLogin());
		loginPage.putPassword(DEFAULT_PASS);
		loginPage.submitLogin();
	}

	@Quando("^efetuar login com um usuário inexistente no sistema$")
	public void userNotFoundOnSite() throws Exception {
		user = new UserDomain(DEFAULT_USER,
				UserDataUtils.getRandomProfileDomain());
		UserDao.deleteUserLogin(user.getLogin());
		logout();
		loginPage.putUserName(user.getLogin());
		loginPage.putPassword(DEFAULT_PASS);
		loginPage.submitLogin();
	}

	@Quando("^efetuar login com um usuário inexistente no radius$")
	public void userNotFoundOnRadius() throws Exception {
		user = new UserDomain(UserDataUtils.getRandomLogin().toLowerCase(),
				UserDataUtils.getRandomProfileDomain());
		UserDao.persistUser(new User(user.getLogin(), user.getDbProfile(), 1));
		logout();
		loginPage.putUserName(user.getLogin());
		loginPage.putPassword(DEFAULT_PASS);
		loginPage.submitLogin();
	}
 
}

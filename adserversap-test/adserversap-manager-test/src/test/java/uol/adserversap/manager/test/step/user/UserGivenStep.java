package uol.adserversap.manager.test.step.user;

import uol.adserversap.dao.ploc.UserDao;
import uol.adserversap.entity.User;
import uol.adserversap.manager.test.domain.UserDomain;
import uol.adserversap.manager.test.step.BaseStep;
import cucumber.api.java.pt.Dado;

/**
 * 
 * @author wrodrigues
 * 
 */
public class UserGivenStep extends BaseStep {

	@Dado("login não existente com perfil <(.+)>")
	public void deleteLogin(String profile) throws Exception {
		user = new UserDomain(DEFAULT_USER2, profile);
		UserDao.deleteUserLogin(user.getLogin());
	}
	
	@Dado("login de teste existente com perfil <(.+)>")
	public void insertLogin(String profile) throws Exception {
		user = new UserDomain(DEFAULT_USER2, profile);
		UserDao.persistUser(new User(user.getLogin(), user.getDbProfile(), 1));
	}
	
	@Dado("login efetuado com sucesso com perfil <(.+)>")
	public void loginUser(String profile) throws Exception {
		user = new UserDomain(DEFAULT_USER, profile);
		UserDao.persistUser(new User(user.getLogin(), user.getDbProfile(), 1));
		logout();
		loginPage.putUserName(user.getLogin());
		loginPage.putPassword(DEFAULT_PASS);
		loginPage.submitLogin();
	}
}

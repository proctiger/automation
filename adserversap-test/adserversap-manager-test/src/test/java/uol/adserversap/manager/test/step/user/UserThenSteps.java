package uol.adserversap.manager.test.step.user;

import junit.framework.Assert;
import uol.adserversap.dao.ploc.UserDao;
import uol.adserversap.entity.User;
import uol.adserversap.manager.test.step.BaseStep;
import cucumber.api.java.pt.Entao;

/**
 * 
 * @author wrodrigues
 * 
 */
public class UserThenSteps extends BaseStep {

	@Entao("o login <(.+)> criado com sucesso no banco")
	public void checkLoginDatabase(String flag) throws Exception {
		if (flag.equals("é")){
			String errorMessage 		= "Usuário não foi criado no banco de dados";
			Assert.assertTrue(errorMessage,UserDao.selectCountFromUserLoginWhereNamLogin(user.getLogin()) > 0) ;
		} else{
			String errorMessage 		= "Usuário inválido foi criado no banco de dados";
			Assert.assertTrue(errorMessage,UserDao.selectCountFromUserLoginWhereNamLogin(user.getLogin()) == 0) ;
		}
	}
	
	@Entao("o login <(.+)> removido do banco")
	public void checkLoginRemovedDatabase(String flagRemoved) throws Exception {
		if (flagRemoved.equals("é")){
			String errorMessage 		= "Usuário não foi removido do banco de dados";
			User userRemoved = UserDao.selectAllFromUserLoginWhereNamLogin(user.getLogin());
			Assert.assertTrue(errorMessage,userRemoved.getStatus() == 0) ;
		}else{
			String errorMessage 		= "Usuário foi removido do banco de dados";
			User userRemoved = UserDao.selectAllFromUserLoginWhereNamLogin(user.getLogin());
			Assert.assertTrue(errorMessage,userRemoved.getStatus() == 1) ;
		}
	}
	
	@Entao("o login é criado com sucesso no banco com perfil <(.+)>")
	public void checkLoginProfileDatabase(String profile) throws Exception {
		String errorMessageProfile 	= "Usuário não foi criado com o perfil correto no banco de dados";
		User userCreated = UserDao.selectAllFromUserLoginWhereNamLogin(user.getLogin());
		
		if (profile.equals("root")){
			Assert.assertTrue(errorMessageProfile, userCreated.getProfile() == 'R'); 
		} else if (profile.equals("admin")) {
			Assert.assertTrue(errorMessageProfile, userCreated.getProfile() == 'A');
		} else if (profile.equals("user")) {
			Assert.assertTrue(errorMessageProfile, userCreated.getProfile() == 'U');
		}
	}
	
	@Entao("perfil root não é exibido para seleção")
	public void checkProfileRootIsNotPresentWhenLoggedAsAdmin() throws Exception {
			String message 	= "Perfil root é exibido para seleção quando logado como Admin";
			Assert.assertFalse(message, userPage.isOptionRootPresent());
	}
	
	@Entao("mensagem de usuário criado com sucesso é exibida")
	public void checkMessageAddedLogin() throws Exception {
		String errorMessage 		= "Mensagem de usuário criado com sucesso não foi exibida";
		Assert.assertTrue(errorMessage,userPage.getMessageSuccessAddedUser(user.getLogin()));
	}
	
	@Entao("mensagem de erro do campo login não preenchido é exibida")
	public void checkErrorMessageNullLogin() throws Exception {
		String error 		= "Mensagem de erro de campo Login não preenchido não foi exibida";
		String regexErrorLogin = "^[\\s\\S]*preenchimento obrigatório[\\s\\S]*$";
		Assert.assertTrue(error,userPage.getErrorMessage(regexErrorLogin));
	}
	
	@Entao("mensagem de erro do campo login com caracteres inválidos é exibida")
	public void checkErrorMessageInvalidLogin() throws Exception {
		String error 		= "Mensagem de erro de campo Login não foi exibida";
		String regexErrorLogin = "^[\\s\\S]*o valor do campo não pode conter caracteres especiais ou espaços[\\s\\S]*$";
		Assert.assertTrue(error,userPage.getErrorMessage(regexErrorLogin));
	}
	
	@Entao("mensagem de erro do campo login com tags html é exibida")
	public void checkErrorMessageInvalidTagHtmlLogin() throws Exception {
		String error 		= "Mensagem de erro de campo Login não foi exibida";
		String regexErrorLogin = "^[\\s\\S]*o valor do campo não pode ter html[\\s\\S]*$";
		Assert.assertTrue(error,userPage.getErrorMessage(regexErrorLogin));
	}
	
	@Entao("mensagem de usuário removido com sucesso é exibida")
	public void checkMessageRemovedLogin() throws Exception {
		String errorMessage 		= "Mensagem de usuário removido com sucesso não foi exibida";
		Assert.assertTrue(errorMessage,userPage.getMessageSuccessRemovedUser(user.getLogin()));
	}

	@Entao ("verificar que não é possível remover o usuário")
	public void checkRemoveButtonForLogin() throws Exception {
		String message 		= "Botão Remover é exibido para o Login admin_2";
		Assert.assertFalse(message, page.checkIfButtonRemoveIsPresentForTheUser("admin_2"));
	}
	 
	@Entao ("verificar que não é possível remover o próprio usuário")
	public void checkRemoveButtonForOwnLogin() throws Exception {
		String message 		= "Botão Remover é exibido para o Login admin_1";
		Assert.assertFalse(message, page.checkIfButtonRemoveIsPresentForTheUser("admin_1"));
	}
	
	@Entao("mensagem de usuário alterado com sucesso é exibida")
	public void checkMessageEditedLogin() throws Exception {
		String errorMessage 		= "Mensagem de usuário alterado com sucesso não foi exibida";
		Assert.assertTrue(errorMessage,userPage.getMessageSuccessEditedUser(user.getLogin()));
	}
	
	@Entao("o login é alterado com sucesso no banco com perfil <(.+)>")
	public void checkLoginProfileChangedDatabase(String profile) throws Exception {
		String errorMessageProfile 	= "Usuário não foi criado com o perfil correto no banco de dados";
		User userChanged = UserDao.selectAllFromUserLoginWhereNamLogin(user.getLogin());
		
		if (profile.equals("root")){
			Assert.assertTrue(errorMessageProfile, userChanged.getProfile() == 'R'); 
		} else if (profile.equals("admin")) {
			Assert.assertTrue(errorMessageProfile, userChanged.getProfile() == 'A');
		} else if (profile.equals("user")) {
			Assert.assertTrue(errorMessageProfile, userChanged.getProfile() == 'U');
		}
	}
	
}

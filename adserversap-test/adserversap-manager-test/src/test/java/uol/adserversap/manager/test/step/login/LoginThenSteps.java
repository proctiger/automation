package uol.adserversap.manager.test.step.login;

import junit.framework.Assert;
import uol.adserversap.manager.test.step.BaseStep;
import cucumber.api.java.pt.Entao;

public class LoginThenSteps extends BaseStep {

	@Entao("o login é efetuado com sucesso")
	public void checkUserLogged() throws Exception {
		String errorMessage = "Usuário autenticado está incorreto";
		Assert.assertEquals(errorMessage, user.getLogin(),
				page.getLoggedUserName());
	}

	@Entao("o login não é efetuado")
	public void checkUserUnlogged() throws Exception {
		String errorMessage = "Usuário autenticado indevidamente";
		Assert.assertFalse(errorMessage, page.isLogged());
	}

	@Entao("^a opção de gerenciamento de usuários <(é|não é)> exibida no menu$")
	public void checkUserItemInMenu(String choice) throws Exception {
		if (choice.equals("é")) {
			String errorMessage = "Menu de gerenciamento de usuários não foi encontrado";
			Assert.assertTrue(errorMessage, page.userAdminMenuExists());
		} else {
			String errorMessage = "Menu de gerenciamento de usuários foi exibido indevidamente";
			Assert.assertFalse(errorMessage, page.userAdminMenuExists());
		}
	}

	@Entao("^a opção de debug de serviços <(é|não é)> exibida no menu$")
	public void checkDebugItemInMenu(String choice) throws Exception {
		if (choice.equals("é")) {
			String errorMessage = "Menu de debug de serviços não foi encontrado";
			Assert.assertTrue(errorMessage, page.debugMenuExists());
		} else {
			String errorMessage = "Menu de debug de serviços foi exibido indevidamente";
			Assert.assertFalse(errorMessage, page.debugMenuExists());
		}
	}

	@Entao("^é exibido o seguinte erro na área de login: <(.+)>$")
	public void checkLoginErrorMessage(String msg) throws Exception {
		String errorMessage = "Mensagem de erro incorreta";
		Assert.assertEquals(errorMessage, msg, loginPage.getErrorMessage());
	}
}

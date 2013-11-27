package selenium.service.builder;

import selenium.page.action.HomeAction;

public class LoginService {

	private String login;
	private String password;

	private HomeAction home;

	public LoginService(String login, String password) {
		this.home = new HomeAction();

		this.login = login;
		this.password = password;
	}

	public String login() {
		return home.openHome().email(login).password(password).enter();
	}

	public LoginService login(String login) {
		this.login = login;
		return this;
	}

	public LoginService password(String password) {
		this.password = password;
		return this;
	}

	public HomeAction getHome() {
		return home;
	}
}

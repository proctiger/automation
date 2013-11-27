package uol.adserversap.ws.test.steps.user;

import uol.adserversap.dao.ploc.UserDao;
import uol.adserversap.entity.User;
import uol.adserversap.ws.test.domain.UserDomain;
import uol.adserversap.ws.test.prepare.UserDataUtils;
import cucumber.api.java.pt.Dado;

/**
 *
 * @author dvrocha
 */
public class UserGivenSteps extends BaseUserSteps {

    @Dado("^um usuário novo com login <((?!nulo)[a-zA-Z0-9-_]+)>$")
    public void newUserLogin(String login) throws Exception {
        user = new UserDomain(login, UserDataUtils.getRandomProfileDomain());
        UserDao.deleteUserLogin(user.getLogin());
    }

    @Dado("^pretendendo atualizar o perfil para <(?i)(admin|root|user)>$")
    public void setUserToUpdate(String profile) throws Exception {
        user.setProfile(profile);
    }

    @Dado("^um usuário novo com perfil <(?i)(admin|root|user)>$")
    public void newUserProfile(String profile) throws Exception {
        user = new UserDomain(UserDataUtils.getRandomLogin(), profile);
        UserDao.deleteUserLogin(user.getLogin());
    }

    @Dado("^um usuário novo com (login|perfil) nulo$")
    public void nullArg(String arg) throws Exception {
        String login = arg.equals("login") ? null : UserDataUtils.getRandomLogin();
        String profile = arg.equals("perfil") ? null : UserDataUtils.getRandomProfileDomain();
        user = new UserDomain(login, profile);
        if (login != null) {
            UserDao.deleteUserLogin(user.getLogin());
        }
    }

    @Dado("^um usuário novo com (login|perfil) em branco$")
    public void emptyArg(String arg) throws Exception {
        String login = arg.equals("login") ? "" : UserDataUtils.getRandomLogin();
        String profile = arg.equals("perfil") ? "" : UserDataUtils.getRandomProfileDomain();
        user = new UserDomain(login, profile);
        if (!login.equals("")) {
            UserDao.deleteUserLogin(user.getLogin());
        }
    }

    @Dado("^um usuário novo com (login|perfil) inválido$")
    public void invalidArg(String arg) throws Exception {
        String login = arg.equals("login") ? "inválido" : UserDataUtils.getRandomLogin();
        String profile = arg.equals("perfil") ? "inválido" : UserDataUtils.getRandomProfileDomain();
        user = new UserDomain(login, profile);
        if (!login.equals("inválido")) {
            UserDao.deleteUserLogin(user.getLogin());
        }
    }

    @Dado("^um usuário com status <(\\d+)> e perfil <(admin|root|user)>$")
    public void persistUser(int status, String profile) throws Exception {
        String login = UserDataUtils.getRandomLogin();
        user = new UserDomain(login, String.valueOf(status), profile);
        UserDao.persistUser(new User(login, user.getDbProfile(), status));
    }

    @Dado("^um usuário com status <(\\d+)>$")
    public void persistUser(int status) throws Exception {
        String login = UserDataUtils.getRandomLogin();
        user = new UserDomain(login, String.valueOf(status), UserDataUtils.getRandomProfileDomain());
        UserDao.persistUser(new User(login, user.getDbProfile(), status));
    }

    @Dado("^um usuário inexistente$")
    public void setUserAndDelete() throws Exception {
        String notFoundUser = UserDataUtils.getRandomLogin();
        UserDao.deleteUserLogin(notFoundUser);
        user = new UserDomain();
        user.setLogin(notFoundUser);
    }
}

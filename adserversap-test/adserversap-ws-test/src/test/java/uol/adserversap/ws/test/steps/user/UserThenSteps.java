/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uol.adserversap.ws.test.steps.user;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;

import uol.adserversap.dao.ploc.UserDao;
import uol.adserversap.ws.test.domain.UserDomain;
import uol.adserversap.ws.test.prepare.UserDataUtils;
import cucumber.api.java.pt.Entao;

/**
 *
 * @author dvrocha
 */
public class UserThenSteps extends BaseUserSteps {

    @Entao("^persiste os dados do usuário$")
    public void checkUserInDb() throws Exception {
        Assert.assertTrue(
                "Usuário não foi encontrado no banco de dados",
                UserDao.selectCountFromUserLoginWhereNamLogin(user.getLogin().toLowerCase()) > 0);
        UserDomain userFound = new UserDomain(UserDao.selectAllFromUserLoginWhereNamLogin(user.getLogin().toLowerCase()));
        Assert.assertEquals(user.getLogin().toLowerCase(), userFound.getLogin());
        Assert.assertEquals(user.getProfile().toLowerCase(), userFound.getProfile());
        Assert.assertEquals("1", userFound.getStatus());
    }

    @Entao("^não persiste os dados do usuário$")
    public void checkUserNotExists() throws Exception {
        String login = user.getLogin() != null ? user.getLogin() : "";
        Assert.assertTrue(UserDao.selectCountFromUserLoginWhereNamLogin(login) < 1);
    }

    @Entao("^retorna os dados do usuário$")
    public void checkServiceResult() throws Exception {
        UserDomain userResult = new ObjectMapper().readValue(serviceResponse.getBodyAsString(), UserDomain.class);
        Assert.assertEquals(user.getLogin(), userResult.getLogin());
        Assert.assertEquals(user.getProfile(), userResult.getProfile());
    }

    @Entao("^retorna a lista de usuários ordenada por login$")
    public void retorna_a_lista_de_usuários() throws Exception {
        UserDomain[] foundList = new ObjectMapper().readValue(serviceResponse.getBodyAsString(), UserDomain[].class);
        UserDomain[] expectedList = UserDataUtils.getActiveUsers();
        junit.framework.Assert.assertEquals(expectedList.length, foundList.length);
        for (int i = 0; i < expectedList.length; i++) {
            junit.framework.Assert.assertEquals(
                    expectedList[i].getLogin(),
                    foundList[i].getLogin());
            junit.framework.Assert.assertEquals(
                    expectedList[i].getStatus(),
                    "1");
            junit.framework.Assert.assertEquals(
                    expectedList[i].getProfile(),
                    foundList[i].getProfile());
        }
    }

    @Entao("^atualiza o status do usuário para <(\\d+)>$")
    public void checkUserStatus(String expectedStatus) throws Exception {
        UserDomain resultUser = new UserDomain(UserDao.selectAllFromUserLoginWhereNamLogin(user.getLogin()));
        junit.framework.Assert.assertEquals(expectedStatus, resultUser.getStatus());
    }
}

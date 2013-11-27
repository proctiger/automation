/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package uol.adserversap.ws.test.steps.user;

import uol.adserversap.ws.test.http.UserHttpRequest;
import cucumber.api.java.pt.Quando;

/**
 *
 * @author dvrocha
 */
public class UserWhenSteps extends BaseUserSteps {

    @Quando("^solicitar serviço de (inserção|atualização) de usuários$")
    public void getServiceResults(String method) throws Exception {
        serviceResponse = UserHttpRequest.putUser(user.getLogin(), user.getProfile());
    }

    @Quando("^solicitar serviço de consulta de usuário$")
    public void getServiceResult() throws Exception {
        serviceResponse = UserHttpRequest.getUser(user.getLogin().toLowerCase());
    }

    @Quando("^solicitar serviço de lista de usuários$")
    public void solicitar_serviço_de_lista_de_usuarios() throws Exception {
        serviceResponse = UserHttpRequest.getUserList();
    }

    @Quando("^solicitar serviço de remoção de usuário$")
    public void getServiceResults() throws Exception {
        serviceResponse = UserHttpRequest.deleteUser(user.getLogin().toLowerCase());
    }
}

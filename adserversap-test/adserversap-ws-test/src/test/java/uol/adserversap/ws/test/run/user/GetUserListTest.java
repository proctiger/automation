package uol.adserversap.ws.test.run.user;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

/**
 *
 * @author dvrocha
 */
@RunWith(Cucumber.class)
@Cucumber.Options(
    glue = {"uol.adserversap.ws.test.steps.commons", "uol.adserversap.ws.test.steps.user"},
    features = {"src/test/resources/features/user"},
    name = "Consultar lista de usuários")
public class GetUserListTest {
}
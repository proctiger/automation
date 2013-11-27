package uol.test.step.topic;

import javax.ws.rs.core.Response.Status;

import org.junit.Assert;

import uol.test.util.ServiceRequest;
import cucumber.api.java.pt.Dado;

public class TopicSubscriptionGivenSteps extends AbstractTopicSubscriptionSteps {

    @Dado("^a existencia de um registro no notificador cadastral com callback URL <(.+)>$")
    public void validateSubscription(String callback) {
        ServiceRequest request = new ServiceRequest();
        request.setParameters(buildBarams(callback, topicName, keepUndelivered, token));
        try {
            code = genericSubscribe(callback, topicName, keepUndelivered, token);
            if (code == 400) {
                Status result = request.doPut(String.format("http://%s:8080/subscription/token/%s", TOPIC_URL, token));
                Assert.assertTrue("O registro nao foi efetuado no notificador.", (result.getStatusCode() == 202 || result.getStatusCode() == 200));
            }
        } catch (Exception e) {
            Assert.fail(String.format("O teste falhou devido ao seguinte erro: %s", e.getLocalizedMessage()));
        }
    }

    @Dado("^a tentativa de primeiro registro no notificador cadastral com token dinamico e callback URL <(.+)>$")
    public void validateFirstSubscription(String callback) {
        try {
            String token;
            code = genericSubscribe(callback,
                                    topicName,
                                    keepUndelivered,
                                    token = String.format("%s_%d", TopicSubscriptionGivenSteps.token, System.currentTimeMillis()));
            if (code == Status.OK.getStatusCode() || code == Status.ACCEPTED.getStatusCode()) {
                Thread.sleep(500);
                ServiceRequest request = new ServiceRequest();
                returnedJSONString = request.doGet(String.format("http://%s:8080/subscription/token/%s", TOPIC_URL, token), String.class);
                Assert.assertNotNull("Nao foi retornado valor na chamada do servico de registro inicial no topico cadastral.", returnedJSONString);
            } else {
                Assert.fail("Nao foi possivel efetuar o registro no Topico Cadastral");
            }
        } catch (Exception e) {
            Assert.fail(String.format("O teste falhou devido ao seguinte erro: %s", e.getLocalizedMessage()));
        }
    }
}
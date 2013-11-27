package uol.test.step.topic;

import org.json.JSONException;
import org.junit.Assert;

import cucumber.api.java.pt.Entao;

public class TopicSubscriptionThenSteps extends AbstractTopicSubscriptionSteps {

    @Entao("^verificar resultado do registro com o codigo <(.+)>$")
    public void checkSubscription(int code) {
        Assert.assertEquals(code, TopicSubscriptionThenSteps.code);
    }

    @Entao("^validar resultado do registro com subscription-status <(.+)>$")
    public void checkSubscriptionStatus(String subscriptionStatus) {
        try {
            Assert.assertEquals(subscriptionStatus, jsonTobeValidated.getString("subscription-status"));
        } catch (JSONException e) {
            Assert.fail(String.format("Nao foi possivel realizar a validacao do campo subscription-status, " +
            		"devido a: %s", e.getLocalizedMessage()));
        }
    }
    
    @Entao("^verificar resultado do callback com o codigo <(.+)>$")
    public void checkStatus(int code) {
        Assert.assertEquals(code, TopicSubscriptionThenSteps.code);
    }
}
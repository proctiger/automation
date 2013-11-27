package uol.test.step.topic;

import java.util.HashMap;

import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uol.test.util.ServiceRequest;

public abstract class AbstractTopicSubscriptionSteps {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractTopicSubscriptionSteps.class);

    protected static String topicName = "inscription";

    protected static String callbackURL = "http://fake.server:8080/ebroker";

    protected static boolean keepUndelivered = true;

    protected static String token = "jrrToken_2";

    protected static int code = -1;

    protected static final String TOPIC_URL = "ws.notificador.intranet";

    protected static JSONObject jsonTobeValidated;

    protected static String returnedJSONString;

    protected static HashMap<String, String> buildBarams(final String callbackURL,
                                                         final String topicName,
                                                         final boolean keepUndelivered,
                                                         final String token) {
        return new HashMap<String, String>() {

            private static final long serialVersionUID = -1691644414895260935L;
            {
                put("topic-name", topicName);
                put("callback-url", callbackURL);
                put("keep-undelivered", Boolean.toString(keepUndelivered));
                put("subscription-token", token);
            }
        };
    }

    protected int genericSubscribe(final String callbackURL,
                                   final String topicName,
                                   final boolean keepUndelivered,
                                   final String token) {
        LOGGER.info(String.format("TOKEN DA VEZ: %s", token));
        ServiceRequest request = new ServiceRequest();
        try {
            request.setParameters(buildBarams(callbackURL, topicName, keepUndelivered, token));
            String url = String.format("http://%s:8080/subscription", TOPIC_URL);
            LOGGER.info(String.format("CHAMADA DA VEZ: %s", url));
            Status status = request.doPost(url);
            return code = status.getStatusCode();
        } catch (Exception e) {
            Assert.fail(String.format("O teste falhou devido ao seguinte erro: %s", e.getLocalizedMessage()));
        }
        return -1;
    }

    protected <T> T genericSubscribeWithComplexResult(final String callbackURL,
                                                      final String topicName,
                                                      final boolean keepUndelivered,
                                                      final String token,
                                                      Class<T> complex) {
        ServiceRequest request = new ServiceRequest();
        try {
            request.setParameters(buildBarams(callbackURL, topicName, keepUndelivered, token));
            String url = String.format("http://%s:8080/subscription", TOPIC_URL);
            LOGGER.info(String.format("CHAMADA DA VEZ: %s", url));
            T result = request.doPost(url, complex);
            return result;
        } catch (Exception e) {
            Assert.fail(String.format("O teste falhou devido ao seguinte erro: %s", e.getLocalizedMessage()));
        }
        return null;
    }
}
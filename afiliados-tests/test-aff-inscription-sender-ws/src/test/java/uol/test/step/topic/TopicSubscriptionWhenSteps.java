package uol.test.step.topic;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import uol.test.util.ServiceRequest;
import cucumber.api.java.pt.Quando;

public class TopicSubscriptionWhenSteps extends AbstractTopicSubscriptionSteps {

    @Quando("^efetuar registro no Notificador Cadastral$")
    public void subscribe() {
        genericSubscribe(callbackURL, topicName, keepUndelivered, String.format("%s_%d", token, System.currentTimeMillis()));
    }

    @Quando("^efetuar registro no Notificador Cadastral com token <(.+)>$")
    public void subscribe(String token) {
        genericSubscribe(callbackURL, topicName, keepUndelivered, token);
    }

    @Quando("^efetuar registro no Notificador Cadastral com topicname <(.+)>$")
    public void subscribeWithDifferentTopicName(String topicName){
        genericSubscribe(callbackURL, topicName, keepUndelivered, String.format("%s_%d", token, System.currentTimeMillis()));
    }
    
    @Quando("^efetuar registro no Notificador Cadastral com callbackUrl <(.+)>$")
    public void subscribeWithDifferentCallbackURL(String callbackURL){
        genericSubscribe(callbackURL, topicName, keepUndelivered, String.format("%s_%d", token, System.currentTimeMillis()));
    }
    
    @Quando("^efetuar registro no Notificador Cadastral com keepUndelivered <(.+)>$")
    public void subscribeWithDifferentKeepUndelivered(boolean keepUndelivered){
        genericSubscribe(callbackURL, topicName, keepUndelivered, String.format("%s_%d", token, System.currentTimeMillis()));
    }
    
    @Quando("^validar registro no Notificador Cadastral$")
    public void validate(){
        ServiceRequest request = new ServiceRequest();
        try {
            String result = request.doGet(String.format("http://%s:8080/subscription/token/%s", TOPIC_URL, token), String.class);
            Assert.assertNotNull("A chamada ao servico get do notificador cadastral nao retornou resultado.", result);
            jsonTobeValidated = new JSONObject(result);
         } catch (Exception e) {
            Assert.fail(String.format("O teste falhou devido ao seguinte erro: %s", e.getLocalizedMessage()));
         }
    }
    
    @Quando("^Mapear o valor retornado$")
    public void mapResult(){
        try {
            jsonTobeValidated = new JSONObject(returnedJSONString);
        } catch (JSONException e) {
            Assert.fail(String.format("Nao foi possivel mapear o objeto JSON, devido a: %s", e.getLocalizedMessage()));
        }
    }
    
    @Quando("^efetuar callback de status simulando o notificador cadastral$")
    public void callbackStatus(){
        ServiceRequest request = new ServiceRequest();
        try {
            code = request.doGet("http://aff-inscription-consumer-ws.sys.srv.intranet/ebroker/subscription/1234567890/status").getStatusCode();
        } catch (Exception e) {
            Assert.fail(String.format("Nao foi possivel realizar a chamada para obter o status do servico, devido a: %s", e.getLocalizedMessage()));
        }
    }
}
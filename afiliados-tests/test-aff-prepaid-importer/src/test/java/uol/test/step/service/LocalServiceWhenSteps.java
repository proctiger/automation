package uol.test.step.service;

import javax.ws.rs.core.Response.Status;

import junit.framework.Assert;

import org.json.JSONObject;

import uol.test.util.ServiceRequest;
import cucumber.api.java.pt.Quando;

public class LocalServiceWhenSteps extends AbstractLocalServiceSteps {

    private static int ERRO404 = 404;

    @Quando("^efetuar uma chamada ao servico simulando a notificacao de um evento de pagamento com os dados <(.+)> com data e horario atual$")
    public void performPaymentEvent(String dadosDePagamento) {
    }

    @Quando("^que seja simulado o envio da hash <(.+)> pelo servico do billing com os dados <(.+)>$")
    public void performPaymentEventAffiliatedInfo(String hash,
                                                  String dados) throws Exception {
        System.out.println(" -> Quando que seja simulado o envio da hash " + hash + " pelo servico do billing com os dados " + dados);
        ServiceRequest request = new ServiceRequest();
        JSONObject json = new JSONObject(dados);
        String url = "http://account-collector.srv.intranet:8080/Billing/transaction/" + json.getString("idtTransaction") + "/additionalInfo";
        System.out.println("Chamando o Servi�o : " + url);
        Status status = request.doGet(url);
        if (status.getStatusCode() == ERRO404) {
            Assert.fail("Teste Falhou , O servi�o retornou erro !");
        }
        System.out.println("O servi�o retornou : " + status.getStatusCode() + " " + status.getReasonPhrase());
        System.out.println(" <- Quando que seja simulado o envio da hash " + hash + " pelo servico do billing com os dados " + dados);
    }
}
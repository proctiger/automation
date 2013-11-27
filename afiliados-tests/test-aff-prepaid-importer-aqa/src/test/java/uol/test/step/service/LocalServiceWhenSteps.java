package uol.test.step.service;


import java.math.BigDecimal;
import java.util.Date;

import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import static org.junit.Assert.*;

import uol.aff.prepaid.importer.business.PaymentProcessor;
import uol.aff.prepaid.importer.model.PaymentEvent;
import uol.test.util.LookupUtil;
import uol.test.util.ServiceRequest;
import cucumber.api.java.pt.Quando;

public class LocalServiceWhenSteps extends AbstractLocalServiceSteps {

    private static int ERRO404 = 404;

    @Quando("^efetuar uma chamada ao servico simulando a notificacao de um evento de pagamento com os dados <(.+)> com data e horario atual$")
    public void performPaymentEvent(String dadosDePagamento) throws Exception {
        System.out.println("\n ---> Quando efetuar uma chamada ao servico simulando a notificacao de um evento de pagamento com os dados " + dadosDePagamento + " com data e horario atual---\n");
        
        PaymentProcessor processor = new LookupUtil().comNome("aff-prepaid-importer/PaymentProcessor")
                                                     .paraClasse(PaymentProcessor.class)
                                                     .paraPorta("1099")
                                                     .paraHost("aff-prepaid-importer.host")
                                                     .lookup();
        
        JSONObject json = new JSONObject(dadosDePagamento);
        
        PaymentEvent event = new PaymentEvent(json.getLong("idtTransaction"),
                                              json.getLong("idtInscriptionAccount"),
                                              json.getInt("numPaymentReceipt"),
                                              json.getInt("idtApplication"),
                                              new BigDecimal(json.getString("numPaymentValue")),
                                              new Date());// now
        processor.process(event);
    }

    @Quando("^que seja simulado o envio da hash <(.+)> pelo servico do billing com os dados <(.+)>$")
    public void performPaymentEventAffiliatedInfo(String hash,
                                                  String dados) throws Exception {
        System.out.println(" -> Quando que seja simulado o envio da hash " + hash + " pelo servico do billing com os dados " + dados);
        ServiceRequest request = new ServiceRequest();
        JSONObject json = new JSONObject(dados);
        String url = "http://account-collector.srv.intranet:8080/Billing/transaction/" + json.getString("idtTransaction") + "/additionalInfo";
        System.out.println("Chamando o Servico : " + url);
        Status status = request.doGet(url);
        if (status.getStatusCode() == ERRO404) {
            fail("Teste Falhou , O servico retornou erro !");
        }
        System.out.println("O servico retornou : " + status.getStatusCode() + " " + status.getReasonPhrase());
        System.out.println(" <- Quando que seja simulado o envio da hash " + hash + " pelo servico do billing com os dados " + dados);
    }
}
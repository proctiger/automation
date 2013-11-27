package uol.test.step.service;

import java.io.UnsupportedEncodingException;

import javax.ws.rs.core.Response.Status;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.uol.cms.redis.driver.RedisService;

import static org.junit.Assert.*;

import uol.crypt.CryptographerException;
import uol.crypt.base64.Base64;
import uol.test.util.ServiceRequest;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;

public class LocalServiceGivenSteps extends AbstractLocalServiceSteps {
    
    private @Autowired RedisService redisService;
    
    @Before
	public static void inicioTeste() {
		System.out.println("\n\n --------------------------------- Inicio - Cenario --------------------------------- \n\n");
	}
           
    @Dado("^que o id da aplicacao no servico de configuracao remota seja alterado para <(.+)>$")
    public void setRcApplicationId(String applicationId) {
    	System.out.println("Alterando as configurações no remote config para applicationId " + applicationId);
    	setRConfigPrepaidProperty(applicationId);
    	System.out.println("Configurações no remote config para applicationId " + applicationId + " alterada");
    }
    
    @Dado("^que no servidor de sessao exista as informacoes de afiliados <(.+)> de hash <(.+)> e codigo de produto <(.+)>$")
    public void insertRedisKey(String info, String hash, int prd_source) throws UnsupportedEncodingException, CryptographerException{
    	System.out.println("\n -> Dado que no servidor de sessao exista as informacoes de afiliados <"+ info +"> de hash <"+ hash +"> e codigo de produto <"+ prd_source +"> \n");
    	String key = hash + ":" + prd_source;
    	System.out.println("Inserindo no Redis > KEY:" +key  +" VALUE: " + info);
    	byte[] bytes = info.getBytes("UTF-8");
    	byte[] encoded = Base64.encode(bytes);
    	String infoBase64 = new String(encoded);
    	System.out.println("Passando value para base 64 > "+  infoBase64);
        if (redisService.hasKey(key)) {
            System.out.println("Removendo do redis: " + key + " para insercao de novos valores.");
            redisService.remove(key);
        }
    	redisService.put(key,infoBase64.toString() ,2592000);
    	System.out.println("Inserido no Redis > KEY:" +key  +" VALUE: " + infoBase64);
    	System.out.println("\n <- Dado que no servidor de sessao exista as informacoes de afiliados <"+ info +"> de hash <"+ hash +"> e codigo de produto <"+ prd_source +">\n");
    }
    
     //Caso seja necessario usar o mock para testar, essa instrucao insere dados no dados no mock do Billing.
    @Dado("^que no billing exista as informacoes de <(.+)>, <(.+)> e <(.+)> para os dados <(.+)>$")
    public void insertBillingMockInfo(String hash, String applicationId, String source, String dadosPagamento) throws Exception {
        System.out.println("\n -----------------> E que o billing retorne " + hash + " para os dados " + dadosPagamento + "-------------------------- \n");
        
        ServiceRequest request = new ServiceRequest();
        JSONObject dadosPagamentoJ = new JSONObject(dadosPagamento);
        
        // Insert in mock
        String url = String.format("http://account-collector.srv.intranet:8080/Billing/insert?cookie=%s&transactionId=%s&products=%s",
                                   hash,
                                   dadosPagamentoJ.getString("idtTransaction"),
                                   String.format("%s:%s", applicationId, source));
        
        System.out.println(" ----> Efetuando requisicao para insercao de dados no Mock do Billing: " + url);
        Status status = request.doGet(url);
        if (status != Status.OK) {
            fail("Falha ao salvar dados no mock do Billing!");
        }
        System.out.println("\n -> --------------------- Dados salvos no Mock do Billing com sucesso! --------------------------- \n");
    }
    
}
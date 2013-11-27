package uol.test.step.service;

import java.io.UnsupportedEncodingException;

import junit.framework.Assert;

import org.springframework.beans.factory.annotation.Autowired;

import uol.crypt.CryptographerException;
import uol.crypt.base64.Base64;
import br.com.uol.cms.redis.driver.RedisService;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;

public class LocalServiceGivenSteps extends AbstractLocalServiceSteps {
 //   private @Autowired RedisService redisService;
    
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
    //	redisService.put(key,infoBase64.toString() ,2592000);
    	System.out.println("Inserido no Redis > KEY:" +key  +" VALUE: " + infoBase64);
    	System.out.println("\n <- Dado que no servidor de sessao exista as informacoes de afiliados <"+ info +"> de hash <"+ hash +"> e codigo de produto <"+ prd_source +">\n");
    }
    	
   
    

}
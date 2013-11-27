package sandbox;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


import uol.adserversap.ws.test.config.ConfigTest;
import uol.adserversap.ws.test.domain.SapAgencyDomain;
import uol.simple.httpclient.SimpleHttpClient;
import uol.simple.httpclient.SimpleHttpRequest;
import uol.simple.httpclient.SimpleHttpResponse;


public class teste {

	/**
	 * @param args
	 * @throws URISyntaxException 
	 * @throws GeneralSecurityException 
	 * @throws TimeoutException 
	 * @throws InterruptedException 
	 * @throws ExecutionException 
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static void main(String[] args) throws Exception {
	    
//	        final AgencyDomain ag = new AgencyDomain();
	        final SimpleHttpRequest request = SimpleHttpClient
	                .newGetRequest(ConfigTest.ADSERVERSAP_WS_DOMAIN, "sap/advertiser/");
	        SimpleHttpResponse serviceResponse = 
	        		SimpleHttpClient
	        		.executeAvoidingSSL(request, ConfigTest.REQUEST_TIMEOUT, TimeUnit.SECONDS);
	        
	        
	        SapAgencyDomain[] agencyList = new ObjectMapper().readValue(serviceResponse.getBodyAsString(), SapAgencyDomain[].class);
	        
	        for (SapAgencyDomain sapAgencyDomain : agencyList) {
	        	if (sapAgencyDomain.getEnterprise().equals("UOLB")){
	        		System.out.println("UOLB--------------------------------------");
	        		System.out.println(sapAgencyDomain);
	        	}else{
	        		System.out.println("BOLB--------------------------------------");
	        		System.out.println(sapAgencyDomain);
	        		break;
	        	}
	        	
	        }
	}

}

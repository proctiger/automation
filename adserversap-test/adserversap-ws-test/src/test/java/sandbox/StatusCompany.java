package sandbox;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.google.api.ads.common.lib.auth.ClientLoginTokens;
import com.google.api.ads.dfp.axis.factory.DfpServices;
import com.google.api.ads.dfp.axis.v201203.Company;
import com.google.api.ads.dfp.axis.v201203.CompanyCreditStatus;
import com.google.api.ads.dfp.axis.v201203.CompanyServiceInterface;
import com.google.api.ads.dfp.axis.v201203.CompanyType;
import com.google.api.ads.dfp.lib.client.DfpSession;


public class StatusCompany {

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
	try{
	
		 // Get a ClientLogin AuthToken.
	      String clientLoginToken = new ClientLoginTokens.Builder()
	          .forApi(ClientLoginTokens.Api.DFP)
	          .fromFile("config.properties")
	          .build()
	          .requestToken();
	      
	      // Construct a DfpSession.
	      DfpSession session = new DfpSession.Builder()
	          .fromFile("config.properties")
	          .withClientLoginToken(clientLoginToken)
	          .build();
	      
	      // Construct a DFP service factory, which can only be used once per thread,
	      // but should be reused as much as possible.
	      DfpServices dfpServices = new DfpServices();
	      
	      // Get the InventoryService.
	      CompanyServiceInterface companyService =
	          dfpServices.get(session, CompanyServiceInterface.class);
        
	      Company[] companies = new Company[1];
	      Company compani = new Company();
	      compani.setName("WtesteAdvertiserBlocked #" + 1);
	      compani.setType(CompanyType.ADVERTISER);
	      
	      CompanyCreditStatus companyCreditStatus = CompanyCreditStatus.fromValue("BLOCKED");
	      compani.setCreditStatus(companyCreditStatus);
	      
	      
	      companies[0] = compani;

	      companies = companyService.createCompanies(companies);
	   
	      if (companies != null) {
	          for (Company company : companies) {
	            System.out.println("A company with ID \"" + company.getId()
	                + "\", name \"" + company.getName()
	                + "\", and type \"" + company.getType() + "\" was created.");
	          }
	        } else {
	          System.out.println("No companies created.");
	        }

	
	}catch (Exception e) {
	      e.printStackTrace();
	}


		
	}
}
	    
		

	

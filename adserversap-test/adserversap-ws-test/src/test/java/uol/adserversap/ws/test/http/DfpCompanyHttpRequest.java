package uol.adserversap.ws.test.http;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.ArrayUtils;
import org.codehaus.jackson.map.ObjectMapper;

import uol.adserversap.dao.sap.SapAdvertiserDao;
import uol.adserversap.dao.sap.SapAgencyDao;
import uol.adserversap.entity.SapAdvertiser;
import uol.adserversap.entity.SapAgency;
import uol.adserversap.ws.test.config.ConfigTest;
import uol.adserversap.ws.test.domain.AdvertiserDomain;
import uol.adserversap.ws.test.domain.AgencyDomain;
import uol.adserversap.ws.test.domain.DfpAdvertiserDomain;
import uol.adserversap.ws.test.domain.DfpAgencyDomain;
import uol.adserversap.ws.test.domain.OrderDomain;
import uol.adserversap.ws.test.domain.SapAdvertiserDomain;
import uol.adserversap.ws.test.domain.SapAgencyDomain;
import uol.simple.httpclient.SimpleHttpClient;
import uol.simple.httpclient.SimpleHttpRequest;
import uol.simple.httpclient.SimpleHttpResponse;

import com.google.api.ads.dfp.axis.utils.v201302.StatementBuilder;
import com.google.api.ads.dfp.axis.v201302.ApiException;
import com.google.api.ads.dfp.axis.v201302.Company;
import com.google.api.ads.dfp.axis.v201302.CompanyPage;
import com.google.api.ads.dfp.axis.v201302.CompanyServiceInterface;
import com.google.api.ads.dfp.axis.v201302.CompanyType;
import com.google.api.ads.dfp.axis.v201302.Statement;

public class DfpCompanyHttpRequest extends DfpHttpRequest {

    public static AdvertiserDomain getValidAdvertiser() throws Exception {
        final AdvertiserDomain adv = new AdvertiserDomain();
        final SapAdvertiser sapAdv = new SapAdvertiser();
        String enterprise = "";
        
    	final SimpleHttpRequest request = SimpleHttpClient
                .newGetRequest(ConfigTest.ADSERVERSAP_WS_DOMAIN, "sap/advertiser/");
        SimpleHttpResponse serviceResponse = 
        		SimpleHttpClient
        		.executeAvoidingSSL(request, ConfigTest.REQUEST_TIMEOUT, TimeUnit.SECONDS);
        SapAdvertiserDomain[] advList = new ObjectMapper().readValue(serviceResponse.getBodyAsString(), SapAdvertiserDomain[].class);
       
        for (SapAdvertiserDomain sapAdvertiserDomain : advList) {
        	if (sapAdvertiserDomain.getEnterprise().equals("UOLB")){
        		sapAdv.setId(sapAdvertiserDomain.getId());
            	sapAdv.setName(sapAdvertiserDomain.getName());
            	sapAdv.setCnpj(sapAdvertiserDomain.getCnpj());
            	enterprise = "UOLB";
            	break;
        	}
		}

        final CompanyServiceInterface companyService = dfpServiceFactory.newDfpService(CompanyServiceInterface.class);
        final Statement stmtFilterByExternalId = new StatementBuilder().where("type = :adv AND externalId = :sapId")
                .withBindVariableValue("sapId", sapAdv.getId())
                .withBindVariableValue("adv", CompanyType.ADVERTISER.toString())
                .toStatement();
        final CompanyPage page = companyService.getCompaniesByStatement(stmtFilterByExternalId);

        Company dfpAdv;

        if (ArrayUtils.isEmpty(page.getResults())) {
            dfpAdv = new Company();
            dfpAdv.setName(sapAdv.getName());
            dfpAdv.setExternalId(sapAdv.getId());
            dfpAdv.setType(CompanyType.ADVERTISER);
            dfpAdv = companyService.createCompany(dfpAdv);
        } else {
            dfpAdv = page.getResults(0);
        }

        final SapAdvertiserDomain sapAdvertiserDomain = new SapAdvertiserDomain();
        sapAdvertiserDomain.setId(sapAdv.getId());
        sapAdvertiserDomain.setName(sapAdv.getName());
        sapAdvertiserDomain.setCnpj(sapAdv.getCnpj());
        sapAdvertiserDomain.setEnterprise(enterprise);

        final DfpAdvertiserDomain dfpAdvertiserDomain = new DfpAdvertiserDomain();
        dfpAdvertiserDomain.setId(dfpAdv.getId());
        dfpAdvertiserDomain.setName(dfpAdv.getName());
        dfpAdvertiserDomain.setNotes(dfpAdv.getComment());
        dfpAdvertiserDomain.setSapId(sapAdv.getId());

        adv.setSapAdvertiser(sapAdvertiserDomain);
        adv.setDfpAdvertiser(dfpAdvertiserDomain);

        return adv;
    }
    
    public static SapAdvertiserDomain getCompanyFromSap(String enterprise) throws Exception{
    	ArrayList<SapAdvertiserDomain> advListEnterprise = new ArrayList<>(); 
    	Random rand = new Random(); 
        int intRandom = rand.nextInt(5);
        
    	final SimpleHttpRequest request 	= SimpleHttpClient
    											.newGetRequest(ConfigTest.ADSERVERSAP_WS_DOMAIN, "sap/advertiser/");
        SimpleHttpResponse serviceResponse 	= SimpleHttpClient
        										.executeAvoidingSSL(request, ConfigTest.REQUEST_TIMEOUT, TimeUnit.SECONDS);
        
        SapAdvertiserDomain[] advList = new ObjectMapper().readValue(serviceResponse.getBodyAsString(), SapAdvertiserDomain[].class);
        
        if (enterprise.equals("UOLB")){ 
	        for (SapAdvertiserDomain sapAdvertiserDomain : advList) {
	        	if (sapAdvertiserDomain.getEnterprise().equals("UOLB")){
	    			advListEnterprise.add(sapAdvertiserDomain);
	    		}
			}
        }else{
        	for (SapAdvertiserDomain sapAdvertiserDomain : advList) {
				if (sapAdvertiserDomain.getEnterprise().equals("BOLB")){
					advListEnterprise.add(sapAdvertiserDomain);
	    		}
			}
        }
        return advListEnterprise.get(intRandom);
    }
    
    private static Company createAdvertiserDFP(final SapAdvertiserDomain sapAdvAg)
			throws RemoteException, ApiException {
    	
    	String type = "adv";
   		CompanyType	companyType = CompanyType.ADVERTISER;
			
		final CompanyServiceInterface companyService = dfpServiceFactory.newDfpService(CompanyServiceInterface.class);
		final Statement stmtFilterByExternalId = new StatementBuilder().where("type = :"+type+" AND externalId = :sapId")
            .withBindVariableValue("sapId", sapAdvAg.getId())
            .withBindVariableValue(type, companyType.toString())
            .toStatement();
    
		final Statement stmtFilterByName = new StatementBuilder().where("type = :"+type+" AND name = :sapName")
            .withBindVariableValue("sapName", sapAdvAg.getName())
            .withBindVariableValue(type, companyType.toString())
            .toStatement();
    
		final CompanyPage pageExternalId = companyService.getCompaniesByStatement(stmtFilterByExternalId);
		final CompanyPage pageName = companyService.getCompaniesByStatement(stmtFilterByName);
    
		Company company = new Company();
		company.setType(companyType);
        company.setName(sapAdvAg.getName());
        company.setExternalId(sapAdvAg.getId());
    
        if (ArrayUtils.isEmpty(pageExternalId.getResults())) {
    		if (ArrayUtils.isEmpty(pageName.getResults())) {
	            company = companyService.createCompany(company);
    		}else{
    			company.setId(pageName.getResults(0).getId());
    			companyService.updateCompany(company);
            }
        } else {
        	company = pageExternalId.getResults(0);
        }
        
        return company;
	}
    
    
    private static Company createAgencyDFP(final SapAgencyDomain sapAdvAg)
 			throws RemoteException, ApiException {
     	
     	String type = "ag";
    		CompanyType	companyType = CompanyType.AGENCY;
 			
 		final CompanyServiceInterface companyService = dfpServiceFactory.newDfpService(CompanyServiceInterface.class);
 		final Statement stmtFilterByExternalId = new StatementBuilder().where("type = :"+type+" AND externalId = :sapId")
             .withBindVariableValue("sapId", sapAdvAg.getId())
             .withBindVariableValue(type, companyType.toString())
             .toStatement();
     
 		final Statement stmtFilterByName = new StatementBuilder().where("type = :"+type+" AND name = :sapName")
             .withBindVariableValue("sapName", sapAdvAg.getName())
             .withBindVariableValue(type, companyType.toString())
             .toStatement();
     
 		final CompanyPage pageExternalId = companyService.getCompaniesByStatement(stmtFilterByExternalId);
 		final CompanyPage pageName = companyService.getCompaniesByStatement(stmtFilterByName);
     
 		Company company = new Company();
 		company.setType(companyType);
        company.setName(sapAdvAg.getName());
        company.setExternalId(sapAdvAg.getId());
     
         if (ArrayUtils.isEmpty(pageExternalId.getResults())) {
     		if (ArrayUtils.isEmpty(pageName.getResults())) {
 	            company = companyService.createCompany(company);
     		}else{
     			company.setId(pageName.getResults(0).getId());
     			companyService.updateCompany(company);
             }
         } else {
         	company = pageExternalId.getResults(0);
         }
         return company;
 	}
    
    
    
    public static AdvertiserDomain getValidAdvertiser(String enterprise) throws Exception {
        SapAdvertiserDomain sapAdvertiserDomainRandom = getCompanyFromSap(enterprise);

        SapAdvertiserDomain sapAdv = new SapAdvertiserDomain();
		sapAdv.setId(sapAdvertiserDomainRandom.getId());
    	sapAdv.setName(sapAdvertiserDomainRandom.getName());
    	sapAdv.setCnpj(sapAdvertiserDomainRandom.getCnpj());
    	sapAdv.setAddress(sapAdvertiserDomainRandom.getAddress());

        Company dfpAdv = createAdvertiserDFP(sapAdv);

        final SapAdvertiserDomain sapAdvertiserDomain = new SapAdvertiserDomain();
        sapAdvertiserDomain.setId(sapAdv.getId());
        sapAdvertiserDomain.setName(sapAdv.getName());
        sapAdvertiserDomain.setCnpj(sapAdv.getCnpj());
        sapAdvertiserDomain.setEnterprise(enterprise);
        sapAdvertiserDomain.setAddress(sapAdv.getAddress());

        final DfpAdvertiserDomain dfpAdvertiserDomain = new DfpAdvertiserDomain();
        dfpAdvertiserDomain.setId(dfpAdv.getId());
        dfpAdvertiserDomain.setName(dfpAdv.getName());
        dfpAdvertiserDomain.setNotes(dfpAdv.getComment());
        dfpAdvertiserDomain.setSapId(dfpAdv.getExternalId());

        final AdvertiserDomain adv = new AdvertiserDomain();
        adv.setSapAdvertiser(sapAdvertiserDomain);
        adv.setDfpAdvertiser(dfpAdvertiserDomain);

        return adv;
    }


    public static AgencyDomain getValidAgency(String enterprise) throws Exception {
        SapAdvertiserDomain sapAdvertiserDomainRandom = getCompanyFromSap(enterprise);

        SapAgencyDomain sapAg = new SapAgencyDomain();
        sapAg.setId(sapAdvertiserDomainRandom.getId());
        sapAg.setName(sapAdvertiserDomainRandom.getName());
        sapAg.setCnpj(sapAdvertiserDomainRandom.getCnpj());
        sapAg.setAddress(sapAdvertiserDomainRandom.getAddress());

        Company dfpAg = createAgencyDFP(sapAg);

        final SapAgencyDomain sapAgencyDomain = new SapAgencyDomain();
        sapAgencyDomain.setId(sapAg.getId());
        sapAgencyDomain.setName(sapAg.getName());
        sapAgencyDomain.setCnpj(sapAg.getCnpj());
        sapAgencyDomain.setEnterprise(enterprise);
        sapAgencyDomain.setAddress(sapAg.getAddress());

        final DfpAgencyDomain dfpAgencyDomain = new DfpAgencyDomain();
        dfpAgencyDomain.setId(dfpAg.getId());
        dfpAgencyDomain.setName(dfpAg.getName());
        dfpAgencyDomain.setNotes(dfpAg.getComment());
        dfpAgencyDomain.setSapId(dfpAg.getExternalId());

        final AgencyDomain ag = new AgencyDomain();
        ag.setSapAgency(sapAgencyDomain);
        ag.setDfpAgency(dfpAgencyDomain);

        return ag;
    }
	

    
    
    public static AdvertiserDomain getInvalidAdvertiser() throws Exception {
        final StringBuilder idBuilder = new StringBuilder("xxx");
        String sapId;
        while (SapAdvertiserDao.selectAdvertiserById((sapId = idBuilder.toString())) != null) {
            idBuilder.append("x");
        }

        final CompanyServiceInterface companyService = dfpServiceFactory.newDfpService(CompanyServiceInterface.class);
        final Statement stmtFilterByExternalId = new StatementBuilder().where("type = :adv AND externalId = :sapId")
                .withBindVariableValue("sapId", sapId)
                .withBindVariableValue("adv", CompanyType.ADVERTISER.toString())
                .toStatement();
        final CompanyPage page = companyService.getCompaniesByStatement(stmtFilterByExternalId);

        Company dfpAdv;

        if (ArrayUtils.isEmpty(page.getResults())) {
            dfpAdv = new Company();
            dfpAdv.setName("Anunciante para testes nao existente no SAP");
            dfpAdv.setExternalId(sapId);
            dfpAdv.setType(CompanyType.ADVERTISER);
            dfpAdv = companyService.createCompany(dfpAdv);
        } else {
            dfpAdv = page.getResults(0);
        }

        final DfpAdvertiserDomain dfpAdvertiserDomain = new DfpAdvertiserDomain();
        dfpAdvertiserDomain.setId(dfpAdv.getId());
        dfpAdvertiserDomain.setName(dfpAdv.getName());
        dfpAdvertiserDomain.setNotes(dfpAdv.getComment());
        dfpAdvertiserDomain.setSapId(sapId);

        final AdvertiserDomain advertiserDomain = new AdvertiserDomain();
        advertiserDomain.setDfpAdvertiser(dfpAdvertiserDomain);

        return advertiserDomain;
    }

    public static SimpleHttpResponse getValidCompany() throws Exception {
        final SimpleHttpRequest request = SimpleHttpClient
                .newGetRequest(ConfigTest.ADSERVERSAP_WS_DOMAIN, "sap/advertiser/");
        return SimpleHttpClient
                .executeAvoidingSSL(request, ConfigTest.REQUEST_TIMEOUT, TimeUnit.SECONDS);
    }
    
    public static AgencyDomain getValidAgencyService(String enterprise) throws Exception {
    	final AgencyDomain ag = new AgencyDomain();
        SimpleHttpResponse serviceResponse = getValidCompany();
        SapAgencyDomain[] agencyList = new ObjectMapper().readValue(serviceResponse.getBodyAsString(), SapAgencyDomain[].class);

        SapAgencyDomain sapAg = new SapAgencyDomain();
        for (SapAgencyDomain sapAgencyDomainList : agencyList) {
        	if (enterprise.equals("UOLB")){
        		if(sapAgencyDomainList.getEnterprise().equals("UOLB")){
        			sapAg = sapAgencyDomainList;
        			break;
        		}
        	}else{
        		if(sapAgencyDomainList.getEnterprise().equals("BOLB")){
        			sapAg = sapAgencyDomainList;
        			break;
        		}
        	}
        }

        final CompanyServiceInterface companyService = dfpServiceFactory.newDfpService(CompanyServiceInterface.class);
        final Statement stmtFilterByExternalId = new StatementBuilder().where("type = :ag AND externalId = :sapId")
                .withBindVariableValue("sapId", sapAg.getId())
                .withBindVariableValue("ag", CompanyType.AGENCY.toString())
                .toStatement();
        final CompanyPage page = companyService.getCompaniesByStatement(stmtFilterByExternalId);
        Company dfpAg;
        if (ArrayUtils.isEmpty(page.getResults())) {
            dfpAg = new Company();
            dfpAg.setName(sapAg.getName());
            dfpAg.setExternalId(sapAg.getId());
            dfpAg.setType(CompanyType.AGENCY);
            dfpAg = companyService.createCompany(dfpAg);
        } else {
            dfpAg = page.getResults(0);
        }
        
        final SapAgencyDomain sapAgencyDomain = new SapAgencyDomain();
        sapAgencyDomain.setId(sapAg.getId());
        sapAgencyDomain.setName(sapAg.getName());
        sapAgencyDomain.setCnpj(sapAg.getCnpj());
        sapAgencyDomain.setEnterprise(enterprise);
        sapAgencyDomain.setNotes(sapAg.getNotes());
        sapAgencyDomain.setAddress(sapAg.getAddress());

        final DfpAgencyDomain dfpAgencyDomain = new DfpAgencyDomain();
        dfpAgencyDomain.setId(dfpAg.getId());
        dfpAgencyDomain.setName(dfpAg.getName());
        dfpAgencyDomain.setNotes(dfpAg.getComment());
        dfpAgencyDomain.setSapId(sapAg.getId());
        
        
        ag.setSapAgency(sapAgencyDomain);
        ag.setDfpAgency(dfpAgencyDomain);
        
        return ag;
        
    }

    
    
    
    public static AgencyDomain getValidAgency() throws Exception {
        final AgencyDomain ag = new AgencyDomain();
        final SapAgency sapAg = SapAgencyDao.selectOneAgency();

        final CompanyServiceInterface companyService = dfpServiceFactory.newDfpService(CompanyServiceInterface.class);
        final Statement stmtFilterByExternalId = new StatementBuilder().where("type = :ag AND externalId = :sapId")
                .withBindVariableValue("sapId", sapAg.getId())
                .withBindVariableValue("ag", CompanyType.AGENCY.toString())
                .toStatement();
        final CompanyPage page = companyService.getCompaniesByStatement(stmtFilterByExternalId);

        Company dfpAg;

        if (ArrayUtils.isEmpty(page.getResults())) {
            dfpAg = new Company();
            dfpAg.setName(sapAg.getName());
            dfpAg.setExternalId(sapAg.getId());
            dfpAg.setType(CompanyType.AGENCY);
            dfpAg = companyService.createCompany(dfpAg);
        } else {
            dfpAg = page.getResults(0);
        }

        final SapAgencyDomain sapAgencyDomain = new SapAgencyDomain();
        sapAgencyDomain.setId(sapAg.getId());
        sapAgencyDomain.setName(sapAg.getName());
        sapAgencyDomain.setCnpj(sapAg.getCnpj());

        final DfpAgencyDomain dfpAgencyDomain = new DfpAgencyDomain();
        dfpAgencyDomain.setId(dfpAg.getId());
        dfpAgencyDomain.setName(dfpAg.getName());
        dfpAgencyDomain.setNotes(dfpAg.getComment());
        dfpAgencyDomain.setSapId(sapAg.getId());

        ag.setSapAgency(sapAgencyDomain);
        ag.setDfpAgency(dfpAgencyDomain);

        return ag;
    }

    public static AgencyDomain getInvalidAgency() throws Exception {
        final StringBuilder idBuilder = new StringBuilder("xxx");
        String sapId;
        while (SapAgencyDao.selectAgencyById((sapId = idBuilder.toString())) != null) {
            idBuilder.append("x");
        }

        final CompanyServiceInterface companyService = dfpServiceFactory.newDfpService(CompanyServiceInterface.class);
        final Statement stmtFilterByExternalId = new StatementBuilder().where("type = :ag AND externalId = :sapId")
                .withBindVariableValue("sapId", sapId)
                .withBindVariableValue("ag", CompanyType.AGENCY.toString())
                .toStatement();
        final CompanyPage page = companyService.getCompaniesByStatement(stmtFilterByExternalId);

        Company dfpAg;

        if (ArrayUtils.isEmpty(page.getResults())) {
            dfpAg = new Company();
            dfpAg.setName("Agencia para testes nao existente no SAP");
            dfpAg.setExternalId(sapId);
            dfpAg.setType(CompanyType.AGENCY);
            dfpAg = companyService.createCompany(dfpAg);
        } else {
            dfpAg = page.getResults(0);
        }

        final DfpAgencyDomain dfpAgencyDomain = new DfpAgencyDomain();
        dfpAgencyDomain.setId(dfpAg.getId());
        dfpAgencyDomain.setName(dfpAg.getName());
        dfpAgencyDomain.setNotes(dfpAg.getComment());
        dfpAgencyDomain.setSapId(sapId);

        final AgencyDomain agencyDomain = new AgencyDomain();
        agencyDomain.setDfpAgency(dfpAgencyDomain);

        return agencyDomain;
    }

    public static void updateAvertiserExternalId(OrderDomain order, String newExternalId) throws Exception {
        final CompanyServiceInterface companyService = dfpServiceFactory.newDfpService(CompanyServiceInterface.class);

        Company dfpAdvertiser = companyService.getCompany(order.getAdvertiserDFP().getId());
        dfpAdvertiser.setExternalId(newExternalId);
        dfpAdvertiser = companyService.updateCompany(dfpAdvertiser);

        order.getAdvertiserDFP().setSapId(dfpAdvertiser.getExternalId());
    }
}

package uol.test.step.service;

import java.util.Map;

import org.junit.Assert;

import com.google.common.collect.Maps;

import cucumber.api.java.After;
import cucumber.api.java.pt.Quando;

import uol.affiliated.client.UserSupplierServiceResourceStub;
import uol.affiliated.client.util.ClientHelper;
import uol.affiliated.common.exception.ServiceException;
import uol.affiliated.domain.enumeration.DefaultResponseStatusEnum;
import uol.affiliated.domain.request.UserSupplierRequest;
import uol.affiliated.domain.response.DefaultResponse;
import uol.affiliated.domain.response.UserSupplierResponse;
import uol.test.step.AbstractTestStep;

public class LocalServiceWhenSteps extends AbstractTestStep {
	
	private static DefaultResponseStatusEnum code;
	
    @Quando("^efetuar uma chamada ao servico default para inserir um usuario cujo idt_person seja igual a <(.+)>$")
    public void insertUserByDefaultService(long idtPerson) {
    	UserSupplierRequest request = buildUserSupplierRequest();
    	UserSupplierServiceResourceStub userSupplierServiceResourceStub = new UserSupplierServiceResourceStub();
        userSupplierServiceResourceStub.setBaseUri("http://affiliated-ws.sys.srv.intranet/service");
        System.out.println("Efetuando chamada ao servico default");
        try {
            DefaultResponse response = userSupplierServiceResourceStub.insertUserSupplier(String.valueOf(idtPerson), request);     
            code = response.getServiceStatus();
        } 
        catch (ServiceException e) {
        	Assert.fail(String.format("Erro ao tentar efetuar uma chamada ao servico default, devido a: %s", e.getLocalizedMessage()));
        }
    }
    
    @Quando("^efetuar uma chamada ao servico assincrono para inserir um usuario cujo idt_person seja igual a <(.+)>$")
    public void insertUserByAsyncService(long idtPerson) {
    	ClientHelper clientHelper = new ClientHelper();
    	UserSupplierRequest request = buildUserSupplierRequest();
    	String uri = "http://affiliated-ws.sys.srv.intranet/service/affiliated/insert/" + idtPerson;
        System.out.println("Efetuando chamada ao servico assincrono");
        try {
        	DefaultResponse response = clientHelper.post(UserSupplierResponse.class,uri,request);     
            code = response.getServiceStatus();
        } 
        catch (ServiceException e) {
        	Assert.fail(String.format("Erro ao tentar efetuar uma chamada ao servico default, devido a: %s", e.getLocalizedMessage()));
        }
    }

    private UserSupplierRequest buildUserSupplierRequest() {
        UserSupplierRequest request = new UserSupplierRequest();
        request.setIndStatus("A");
        request.setNumBalanceValue("0");
        request.setGroups(buildCommissionEventGroups());
        request.setCodDisplaySupplierParent("8352d113273d41a6b866f0d446e188a9"); // CAF INDICADOR
        return request;
    }

    private Map<String, String> buildCommissionEventGroups() {
        Map<String, String> commGroupsMap = Maps.newHashMap();
        commGroupsMap.put("1", "A");
        commGroupsMap.put("13", "A");
        commGroupsMap.put("14", "A");
        commGroupsMap.put("9", "A");
        return commGroupsMap;
    }
    
    public static String getCode() {
    	String formattedCode = null;
    	if(code == DefaultResponseStatusEnum.SUCCESS) {
    		formattedCode = "success";
    	}
    	else if(code == DefaultResponseStatusEnum.WARN) {
    		formattedCode = "warn";
    	}
    	else {
    		formattedCode = "error";
    	}
    	return formattedCode;
    }
    
}
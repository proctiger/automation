package uol.adserversap.ws.test.steps.dfp;

import java.util.UUID;

import org.codehaus.jackson.map.ObjectMapper;

import uol.adserversap.dao.ploc.AssociationDao;
import uol.adserversap.dao.ploc.OrderAssociationDao;
import uol.adserversap.ws.test.domain.InconsistenceDomain;
import uol.adserversap.ws.test.domain.OrderDomain;
import uol.adserversap.ws.test.http.OrderHttpRequest;
import cucumber.api.java.pt.Quando;

public class DfpWhenSteps extends BaseDfpSteps{
    @Quando("^solicitar servico de consulta de ordem para aprovacao$")
    public void getOrderForApprovalById() throws Exception {
        serviceResponse = OrderHttpRequest.getOrderForApprovalById(order.getId().toString());
        actualOrder  = new ObjectMapper().readValue(serviceResponse.getBodyAsString(), OrderDomain.class);
    }

    @Quando("^solicitar servico de consulta de ordem para aprovacao pelo PONumber <(EXISTENTE|INEXISTENTE)>$")
    public void getOrderForApprovalByIdAndPoNumber(String poNumberExistence) throws Exception {
        String poNumber = "";
        if ("INEXISTENTE".equals(poNumberExistence)) {
            poNumber = UUID.randomUUID().toString();
        } else {
        	if (order.getEnterprise() != null){
        		poNumber = AssociationDao.selectOrderAssociationsByEnterprise(order.getEnterprise()).get(0).getPoNumber();
        	}else{
        		OrderAssociationDao orderAssociationDao = new OrderAssociationDao();
            	poNumber = orderAssociationDao.getPoNumber("BOLB");
        	}
        }

        serviceResponse = OrderHttpRequest.getOrderForApprovalByIdAndPoNumber(order.getId().toString(), poNumber);
        actualOrder  = new ObjectMapper().readValue(serviceResponse.getBodyAsString(), OrderDomain.class);
    }

    @Quando("^solicitar servico de aprovacao de ordem$")
    public void approveOrder() throws Exception {
        serviceResponse = OrderHttpRequest.approveOrderById(order.getId().toString());
        actualOrder  = new ObjectMapper().readValue(serviceResponse.getBodyAsString(), OrderDomain.class);
    }

    @Quando("^solicitar servico de aprovacao de ordem Post com inconsistencia$")
    public void approveOrderPost() throws Exception {
        serviceResponse = OrderHttpRequest.approveOrderById(order.getId().toString());
        inconsistenceList = new ObjectMapper().readValue(serviceResponse.getBodyAsString(), InconsistenceDomain[].class);
    }
      
    @Quando("^solicitar servico de aprovacao de ordem pelo PONUmber com inconsistencia: PONUmber <(EXISTENTE|INEXISTENTE)>$")
    public void approveOrdersByIdAndPoNumberWithInconsistence(String poNumberExistence) throws Exception {
    	final String poNumber;
    	if ("INEXISTENTE".equals(poNumberExistence)) {
    		poNumber = UUID.randomUUID().toString();
    	}else{
    		poNumber = actualOrder.getPoNumber();
    	}
    	serviceResponse = OrderHttpRequest.approveOrderByIdAndPoNumber(order.getId().toString(), poNumber);
		inconsistenceList = new ObjectMapper().readValue(serviceResponse.getBodyAsString(), InconsistenceDomain[].class);
    }
    
    @Quando("^solicitar servico de aprovacao de ordem pelo PONUmber com sucesso$")
    public void approveOrdersByIdAndPoNumber() throws Exception {
            String poNumber = actualOrder.getPoNumber();
            serviceResponse = OrderHttpRequest.approveOrderByIdAndPoNumber(order.getId().toString(), poNumber);
            actualOrder  = new ObjectMapper().readValue(serviceResponse.getBodyAsString(), OrderDomain.class);
    }
 
    @Quando("^solicitar servico de consulta de ordem para reprogramacao$")
    public void getOrderForReprogrammingByIdAndPoNumber() throws Exception {
    	
    	String enterprise = "UOLB";
    	
    	if (order.getEnterprise() != null){
    		enterprise = order.getEnterprise();
    	}
    	
    	OrderAssociationDao orderAssociationDao = new OrderAssociationDao();
    	String poNumber = orderAssociationDao.getPoNumber(enterprise);
    	
        serviceResponse = OrderHttpRequest.getOrderForReprogrammingByIdAndPoNumber(order.getId().toString(), poNumber);
        actualOrder  = new ObjectMapper().readValue(serviceResponse.getBodyAsString(), OrderDomain.class);
    }
   
    @Quando("^solicitar servico de consulta de ordem para reprogramacao informando PoNumber nao existente$")
    public void reprogrammingByIdAndPoNumberInexistent() throws Exception {
    	String poNumber = UUID.randomUUID().toString();
        serviceResponse = OrderHttpRequest.getOrderForReprogrammingByIdAndPoNumber(order.getId().toString(), poNumber);
        actualOrder  = new ObjectMapper().readValue(serviceResponse.getBodyAsString(), OrderDomain.class);
    }
    
    @Quando("^solicitar servico de reprogramacao de ordem com inconsistencia$")
    public void reprogramOrderByIdAndPoNumberWithInconsistence() throws Exception {
        serviceResponse = OrderHttpRequest.reprogramOrderByIdAndPoNumber(order.getId().toString(), order.getPoNumber());
        inconsistenceList = new ObjectMapper().readValue(serviceResponse.getBodyAsString(), InconsistenceDomain[].class);
    }
    
    @Quando("^solicitar servico de reprogramacao de ordem$")
    public void reprogramOrderByIdAndPoNumber() throws Exception {
    	String poNumber = actualOrder.getPoNumber();
        serviceResponse = OrderHttpRequest.reprogramOrderByIdAndPoNumber(order.getId().toString(), poNumber);
        if (serviceResponse.getStatusCode() == 200){
        	actualOrder  = new ObjectMapper().readValue(serviceResponse.getBodyAsString(), OrderDomain.class);
        }
    }
}
package uol.adserversap.ws.test.steps.dfp;


import java.util.UUID;

import org.codehaus.jackson.map.ObjectMapper;

import uol.adserversap.dao.ploc.AssociationDao;
import uol.adserversap.entity.OrderAssociation;
import uol.adserversap.ws.test.domain.AdvertiserDomain;
import uol.adserversap.ws.test.domain.AgencyDomain;
import uol.adserversap.ws.test.domain.OrderDomain;
import uol.adserversap.ws.test.http.DfpCompanyHttpRequest;
import uol.adserversap.ws.test.http.DfpLineItemHttpRequest;
import uol.adserversap.ws.test.http.DfpOrderHttpRequest;
import uol.adserversap.ws.test.http.OrderHttpRequest;

import com.google.api.ads.dfp.axis.v201302.LineItemDiscountType;

import cucumber.api.java.pt.Dado;

/**
 * @author wrodrigues@uolinc.com
 */
public class DfpGivenSteps extends BaseDfpSteps {

    @Dado("^conexao ao DFP estabelecida$")
    public void connectToDFP() {
    }

    @Dado("^criar ordem$")
    public void createOrderDef() throws Exception {
        order = DfpOrderHttpRequest.createDefaultOrder();
    }
    
    @Dado("^criar ordem sem billingTypeId$")
    public void createOrderDefWithoutBillingType() throws Exception {
        order = DfpOrderHttpRequest.createDefaultOrderWithoutBillingType();
    }
    
    @Dado("^criar ordem para empresa <(UOLB|BOLB)>$")
    public void createOrderWithEnterprise(String enterprise) throws Exception {
        order = DfpOrderHttpRequest.createDefaultOrder(enterprise);
    }
    
    @Dado("^criar ordem sem agencia$")
    public void createOrderWithoutAgency() throws Exception {
        order = DfpOrderHttpRequest.createDefaultOrderWithoutAgency();
    }
    
    @Dado("^aprovar ordem$")
    public void approveOrder() throws Exception {
        serviceResponse = OrderHttpRequest.approveOrderById(order.getId().toString());
        actualOrder  = new ObjectMapper().readValue(serviceResponse.getBodyAsString(), OrderDomain.class);
        DfpLineItemHttpRequest.removeLineItems(order);
        DfpOrderHttpRequest.deleteOrder(order);
    }
    
    @Dado("^aprovar ordem para reprogramacao$")
    public void approveOrderForReprogramming() throws Exception {
        serviceResponse = OrderHttpRequest.approveOrderById(order.getId().toString());
        actualOrder  = new ObjectMapper().readValue(serviceResponse.getBodyAsString(), OrderDomain.class);
    }
    
    @Dado("^criar ordem default$")
    public void createOrderDefault() throws Exception {
        final AdvertiserDomain advertiser = DfpCompanyHttpRequest.getValidAdvertiser();
        order = DfpOrderHttpRequest.createDefaultOrder(advertiser);
    }

    @Dado("^criar ordem com anunciante invalido$")
    public void createOrderWithInvalidAdvertiser() throws Exception {
        final AdvertiserDomain invalidAdvertiser = DfpCompanyHttpRequest.getInvalidAdvertiser();
        order = DfpOrderHttpRequest.createDefaultOrder(invalidAdvertiser);
    }

    @Dado("^criar ordem sem item de linha$")
    public void createOrderWithoutLineItem() throws Exception {
        final AdvertiserDomain advertiser = DfpCompanyHttpRequest.getValidAdvertiser();
        order = DfpOrderHttpRequest.createDefaultOrder(advertiser);
        DfpLineItemHttpRequest.removeLineItems(order);
        lastLineItem = null;
    }

    @Dado("^criar ordem de reprogramacao$")
    public void createReprogrammingOrder() throws Exception {
        order = DfpOrderHttpRequest.createDefaultOrder();
        DfpOrderHttpRequest.updateBillingTypeId(order, 15);
    }
    
    @Dado("^criar ordem de reprogramacao para empresa <(UOLB|BOLB)>$")
    public void createReprogrammingOrder(String enterprise) throws Exception {
        order = DfpOrderHttpRequest.createDefaultOrder(enterprise);
        DfpOrderHttpRequest.updateBillingTypeId(order, 15);
    }

    @Dado("^criar ordem de reprogramacao sem item de linha$")
    public void creatReprogramminOrderWithouLineItem() throws Exception {
        final AdvertiserDomain advertiser = DfpCompanyHttpRequest.getValidAdvertiser();
        order = DfpOrderHttpRequest.createDefaultOrder(advertiser);
        DfpOrderHttpRequest.updateBillingTypeId(order, 15);
        DfpLineItemHttpRequest.removeLineItems(order);
        lastLineItem = null;
    }

    @Dado("^ordem empresa <(UOLB|BOLB)> aprovada$")
    public void getOrderApproved(String enterprise) throws Exception {
        if (AssociationDao.selectOrderAssociationsByEnterprise(enterprise).isEmpty()) {
            final AdvertiserDomain advertiser = DfpCompanyHttpRequest.getValidAdvertiser();
            final OrderDomain orderForApproval = DfpOrderHttpRequest.createDefaultOrder(advertiser);
            DfpOrderHttpRequest.updateEnterprise(orderForApproval, enterprise);
            DfpOrderHttpRequest.approveOrder(orderForApproval);
        }
    }

    @Dado("^uma ordem nao existente$")
    public void invalidOrder() throws Exception {
        final AdvertiserDomain advertiser = DfpCompanyHttpRequest.getValidAdvertiser();
        order = DfpOrderHttpRequest.createDefaultOrder(advertiser);
        DfpLineItemHttpRequest.removeLineItems(order);
        DfpOrderHttpRequest.deleteOrder(order);
    }

    @Dado("^configurar ordem com empresa <(UOLB|BOLB|VAZIA)>$")
    public void setOrderEnterprise(String enterprise) throws Exception {
        final String newEnterprise = ("VAZIA".equals(enterprise)) ? null : enterprise;
        DfpOrderHttpRequest.updateEnterprise(order, newEnterprise);
    }

    @Dado("^configurar ordem com billingTypeId <([0-9]+|VAZIO)>$")
    public void setOrderBillingTypeId(String billingTypeId) throws Exception {
        final Integer newBillingTypeId = ("VAZIO".equals(billingTypeId)) ? null : Integer.valueOf(billingTypeId);
        DfpOrderHttpRequest.updateBillingTypeId(order, newBillingTypeId);
    }

    @Dado("^configurar ordem com Ponumber$")
    public void setOrderPoNumber() throws Exception {
        DfpOrderHttpRequest.updatePoNumber(order, UUID.randomUUID().toString());
    }

    @Dado("^configurar ordem com Ponumber existente <(UOLB|BOLB)>$")
    public void setOrderWithPoNumberExistent(String enterprise) throws Exception {
        String existentPoNumber = null;

        for (OrderAssociation association : AssociationDao.selectOrderAssociationsByEnterprise(enterprise)) {
            existentPoNumber = association.getPoNumber();
            break;
        }

        if (existentPoNumber == null) {
            final AdvertiserDomain advertiser = DfpCompanyHttpRequest.getValidAdvertiser();
            final OrderDomain newOrder = DfpOrderHttpRequest.createDefaultOrder(advertiser);
            DfpOrderHttpRequest.updateEnterprise(newOrder, enterprise);
            DfpOrderHttpRequest.approveOrder(newOrder);
            existentPoNumber = newOrder.getPoNumber();
        }

        DfpOrderHttpRequest.updatePoNumber(order, existentPoNumber);
    }

    @Dado("^configurar ordem com startDate <(?i)(menor|igual|maior)> que a data atual$")
    public void setOrderStartDate(String comparison) throws Exception {
    }

    @Dado("^configurar ordem com endDate <(?i)(menor|igual|maior)> que a data atual$")
    public void setOrderEndDate(String comparison) throws Exception {
    }

    @Dado("^configurar ordem como arquivada$")
    public void setOrderArchieved() throws Exception {
        DfpOrderHttpRequest.archiveOrder(order);
    }

    @Dado("^configurar ordem com status APPROVED$")
    public void setOrderapproved() throws Exception {
    	DfpOrderHttpRequest.approveOrder(order);
    }

    @Dado("^configurar ordem com status PENDING_APPROVAL$")
    public void setOrderPendingForApproval() throws Exception {
    	Thread.sleep(5000);
        DfpOrderHttpRequest.submitForApproval(order);
        Thread.sleep(5000);
    }
 
    @Dado("^configurar advertiser com external id <(.+)>$")
    public void setAdvertiserExternalId(String externalId) throws Exception {
        DfpCompanyHttpRequest.updateAvertiserExternalId(order, externalId);
    }

    @Dado("^criar agencia <(UOLB|BOLB)> existente no SAP$")
    public void createValidAgency(String enterprise) throws Exception { 
    	final AgencyDomain validAgency = DfpCompanyHttpRequest.getValidAgencyService(enterprise);
    	DfpOrderHttpRequest.associateAgency(order, validAgency);
	}

    @Dado("^criar agencia nao existente no SAP$")
    public void createInvalidAgency() throws Exception {
        final AgencyDomain invalidAgency = DfpCompanyHttpRequest.getInvalidAgency();
        DfpOrderHttpRequest.associateAgency(order, invalidAgency);
    }

    @Dado("^criar item de linha$")
    public void createLineItemDefault() throws Exception {
        lastLineItem = DfpLineItemHttpRequest.createDefaultLineItem(order);
    }

    @Dado("^configurar item de linha com custo <(CPC|CPM|CPD)>$")
    public void setLineItemCostType(String costTypeName) throws Exception {
        DfpLineItemHttpRequest.updateCostValues(lastLineItem, order, costTypeName);
    }

    @Dado("^configurar item de linha com material <(DECOMM|DLINKP|DPUBLI|INVALIDO)>$")
    public void setLineItemMaterial(String material) throws Exception {
        final String newMaterial = ("INVALIDO".equals(material)) ? null : material;
        DfpLineItemHttpRequest.updateSapProduct(lastLineItem, newMaterial);
    }

    @Dado("^configurar item de linha com desconto <(?i)(PERCENTUAL|ABSOLUTO)>$")
    public void setLineItemDiscount(String discountType) throws Exception {
        final String dfpDiscountType;
        if (discountType.equalsIgnoreCase("PERCENTUAL")) {
            dfpDiscountType = LineItemDiscountType.PERCENTAGE.toString();
        } else {
            dfpDiscountType = LineItemDiscountType.ABSOLUTE_VALUE.toString();
        }

        DfpLineItemHttpRequest.updateDiscountType(lastLineItem, order, dfpDiscountType);
    }

    @Dado("^configurar item de linha com desconto total$")
    public void setLineItemDiscountTotal() throws Exception {
        DfpLineItemHttpRequest.updateDiscountValue(lastLineItem, order, lastLineItem.getGrossValue());
    }
    
    @Dado("^configurar item de linha com desconto parcial$")
    public void setLineItemDiscountPartial() throws Exception {
        DfpLineItemHttpRequest.updateDiscountPartialValue(lastLineItem, order, 0.1 * lastLineItem.getGrossValue());
    }

    @Dado("^configurar item de linha com startDate <(?i)(menor|igual|maior)> que a data atual$")
    public void setLineItemStartDate(String startDate) {
    }

    @Dado("^configurar item de linha com endDate <(?i)(menor|igual|maior)> que a data atual$")
    public void setLineItemEndDate(String endDate) {
    }

    @Dado("^configurar item de linha com status ativada$")
    public void setLineItemStatus(String status) throws Exception {
        DfpLineItemHttpRequest.activateLineItem(lastLineItem);
    }

    @Dado("^configurar item de linha como arquivada$")
    public void setLineItemArchieved() throws Exception {
        DfpLineItemHttpRequest.archiveLineItem(order, lastLineItem);
    }

    @Dado("^configurar item de linha com valor <(\\d+)>$")
    public void setLineItemValue(Long value) throws Exception {
        DfpLineItemHttpRequest.updateCostPerUnit(lastLineItem, order, value);
    }
}




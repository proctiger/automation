package uol.adserversap.ws.test.steps.dfp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import junit.framework.Assert;

import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.map.ObjectMapper;

import uol.adserversap.dao.sap.SapOvkDao;
import uol.adserversap.dao.sap.SapOvpDao;
import uol.adserversap.entity.SapOvk;
import uol.adserversap.entity.SapOvp;
import uol.adserversap.ws.test.domain.DfpAdvertiserDomain;
import uol.adserversap.ws.test.domain.DfpAgencyDomain;
import uol.adserversap.ws.test.domain.InconsistenceDomain;
import uol.adserversap.ws.test.domain.LineItemDomain;
import uol.adserversap.ws.test.domain.OrderDomain;
import uol.adserversap.ws.test.domain.SapAdvertiserDomain;
import uol.adserversap.ws.test.domain.SapAgencyDomain;
import uol.adserversap.ws.test.http.DfpLineItemHttpRequest;
import uol.adserversap.ws.test.http.DfpOrderHttpRequest;
import cucumber.api.java.After;
import cucumber.api.java.pt.Entao;


public class DfpThenSteps extends BaseDfpSteps{

    @After
    public void removeOrderFromDfp() throws Exception {
        DfpLineItemHttpRequest.removeLineItems(order);
        DfpOrderHttpRequest.deleteOrder(order);
        
        if (actualOrder != null){
        	DfpLineItemHttpRequest.removeLineItems(actualOrder);
            DfpOrderHttpRequest.deleteOrder(actualOrder);
        }
        
    }

    @Entao("^verifica dados da ordem$")
    public void setActualOrder() throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd/MM/yyyy"));

        actualOrder = mapper.readValue(serviceResponse.getBodyAsString(), OrderDomain.class);
        
        Assert.assertNotNull("O servico nao retornou a ordem", actualOrder);
        Assert.assertTrue("O servico nao retornou a ordem corretamente", order.equals(actualOrder) );
    }

    @Entao("^verifica ordem com campo ponumber$")
    public void checkOrderPoNumber() {
        Assert.assertNotNull("Ordem não recebeu PONumber após aprovação", actualOrder.getPoNumber());
    }
    
    @Entao("^verifica status da ordem no DFP igual a <(.+)>$")
    public void checkOrderStatusApproved(String status) {
    	Assert.assertTrue("No DFP o status da ordem não foi alterado para "+status, actualOrder.getStatus().equalsIgnoreCase(status));
    }

    @Entao("^verifica ordem com campo <(.+)> igual <(.+)>$")
    public void checkOrderField(String field, String expectedValue) throws Exception{
        String actualValue = null;

        switch (field) {
        case "status":
            actualValue = actualOrder.getStatus();
            break;
        case "enterprise":
            actualValue = actualOrder.getEnterprise();
            break;
        case "archived":
            actualValue = String.valueOf(actualOrder.isArchived());
            break;

        default:
            throw new InvalidParameterException("campo desconhecido: " + field);
        }

        Assert.assertEquals(String.format("Campos %s diferem", field), expectedValue, actualValue);
    }

    @Entao("^verifica ordem com campo <(.+)> correto$")
    public void checkOrderField(String fieldName) {
        Object expectedValue = null;
        Object actualValue = null;

        switch (fieldName.toLowerCase()) {
        case "advertiser":
            DfpAdvertiserDomain advertiserDFP = order.getAdvertiserDFP();
            SapAdvertiserDomain advertiserSAP = order.getAdvertiserSAP();
            DfpAdvertiserDomain advertiserDFPOrderActual = actualOrder.getAdvertiserDFP();
            SapAdvertiserDomain advertiserSAPOrderActual = actualOrder.getAdvertiserSAP();
            Assert.assertEquals(String.format("Campos %s diferem", fieldName), advertiserDFP, advertiserDFPOrderActual); 
            Assert.assertEquals(String.format("Campos %s diferem", fieldName), advertiserSAP, advertiserSAPOrderActual);
            break;
        case "agency":
            DfpAgencyDomain agencyDFP = order.getAgencyDFP();
            SapAgencyDomain agencySAP = order.getAgencySAP();
            DfpAgencyDomain agencyDFPOrderActual = actualOrder.getAgencyDFP();
            SapAgencyDomain agencySAPOrderActual = actualOrder.getAgencySAP();
            Assert.assertEquals(String.format("Campos %s diferem", fieldName), agencyDFP, agencyDFPOrderActual);
            Assert.assertEquals(String.format("Campos %s diferem", fieldName), agencySAP, agencySAPOrderActual);
            break;    
        case "paymentcondition":
            expectedValue = order.getPaymentCondition();
            actualValue = actualOrder.getPaymentCondition();
            break;
        case "billingtype":
            expectedValue = order.getBillingType();
            actualValue = actualOrder.getBillingType();
            break;
        case "lineitems":
            List<LineItemDomain> expectedLineItems = order.getLineItems();
            List<LineItemDomain> actualLineItems = actualOrder.getLineItems();
            Assert.assertEquals(String.format("Campos %s diferem", fieldName), expectedLineItems, actualLineItems);           
            break;
        case "material":
            String sapProductExpected = order.getLineItems().get(0).getSapProduct();
            String sapProductActual = actualOrder.getLineItems().get(0).getSapProduct();
            Assert.assertEquals(String.format("Campos %s diferem", fieldName), sapProductExpected, sapProductActual);           
            break;
        default:
            throw new InvalidParameterException("campo desconhecido: " + fieldName);
        }
        
        if (expectedValue != null){
        	Assert.assertEquals(String.format("Campos %s diferem", fieldName), expectedValue, actualValue);
        }
    }

    @Entao("^verifica ordem sem Agencia do SAP$")
    public void checkOrderWithoutSapAgency() {
        Assert.assertNull("Agencia nao deveria existir", actualOrder.getAgencySAP());
    }

    @Entao("^verifica ordem sem Advertiser no SAP$")
    public void checkOrderWithoutSapAdvertiser() {
        Assert.assertNull("Anunciante nao deveria existir", actualOrder.getAdvertiserSAP());
    }

    @Entao("^verifica ordem com advertiser valido$")
    public void checkOrderWithAdvertiser() {
        Assert.assertNotNull("Anunciante no DFP nao deveria ser nulo", actualOrder.getAdvertiserDFP());
        Assert.assertNotNull("Anunciante no SAP nao deveria ser nulo", actualOrder.getAdvertiserSAP());
    }

    @Entao("^verifica ordem com agencia valida$")
    public void checkOrderWithAgency() {
        Assert.assertNotNull("Agencia no DFP nao deveria ser nula", actualOrder.getAgencyDFP());
        Assert.assertNotNull("Agencia no SAP nao deveria ser nula", actualOrder.getAgencySAP());
    }

    @Entao("^verifica ordem sem Line Item$")
    public void checkOrderEmptyLineItems() {
        Assert.assertTrue("Ordem deveria estar sem itens de linha", CollectionUtils.isEmpty(actualOrder.getLineItems()));
    }

    @Entao("^nenhuma inconsistencia na ordem$")
    public void checkOrderWithoutInconsistences() {
        final String inconsistencesString = (actualOrder.getInconsistencies() == null) ? "null" : actualOrder.getInconsistencies().toString();
        Assert.assertTrue("Ordem nao deveria conter inconsistencias: " + inconsistencesString, CollectionUtils.isEmpty(actualOrder.getInconsistencies()));
    }

    @Entao("^verifica inconsistencia <(.+)>$")
    public void checkOrderWithInconsistence(String inconsistenceCode) {
        InconsistenceDomain actualInconsistence = null;
        String inconsistencesString = "[]";

        if (CollectionUtils.isNotEmpty(actualOrder.getInconsistencies())) {
            for (InconsistenceDomain orderInconsistence : actualOrder.getInconsistencies()) {
                if (Objects.equals(orderInconsistence.getCode(), inconsistenceCode)) {
                    actualInconsistence = orderInconsistence;
                    break;
                }
            }

            inconsistencesString = actualOrder.getInconsistencies().toString();
        }

        Assert.assertNotNull(String.format("Inconsistencia %s nao encontrada em %s", inconsistenceCode, inconsistencesString),
                actualInconsistence);
    }

    @Entao("^verifica inconsistencia do post for approval")
    public void checkPostInconsistence() {
    	Assert.assertTrue("Inconsistencia approval.execution.inconsistent nao encontrada",inconsistenceList[0].getCode().equalsIgnoreCase("approval.execution.inconsistent"));
    }
    
    @Entao("^verifica inconsistencia do POST para reprogramacao")
    public void checkPostInconsistenceReprogramming() {
    	Assert.assertTrue("Inconsistencia approval.execution.inconsistent nao encontrada",inconsistenceList[0].getCode().equalsIgnoreCase("approval.execution.inconsistent"));
    }
    
    @Entao("^verifica item de linha com campo <(.+)> correto$")
    public void checkLineItemField(String fieldName) {
        for (int i = 0; i < order.getLineItems().size(); i++) {
            Object expectedValue = null;
            Object actualValue = null;

            switch (fieldName) {
            case "material":
                expectedValue = order.getLineItems().get(i).getSapProduct();
                actualValue = actualOrder.getLineItems().get(i).getSapProduct();
                break;
            case "discountPercent":
                expectedValue = order.getLineItems().get(i).getDiscountPercent();
                actualValue = actualOrder.getLineItems().get(i).getDiscountPercent();
                break;
            case "costType":
            	expectedValue = order.getLineItems().get(i).getCostType();
                actualValue = actualOrder.getLineItems().get(i).getCostType();
                break;
            default:
                throw new InvalidParameterException("campo desconhecido: " + fieldName);
            }

            Assert.assertEquals(String.format("Campos %s diferem", fieldName), expectedValue, actualValue);
        }
    }

    @Entao("^verifica item de linha com campo <(.+)> igual <(.+)>$")
    public void checkLineItemField(String field, String expectedValue) throws Exception{
        String actualValue = null;

        switch (field) {
        case "custo":
            actualValue = lastLineItem.getCostType().toString();
            break;

        default:
            throw new InvalidParameterException("campo desconhecido: " + field);
        }

        Assert.assertEquals(String.format("Campos %s diferem", field), expectedValue, actualValue);
    }

    @Entao("^verifica valores de sumarizacao da ordem$")
    public void checkOrderValues() {
        assertBigDecimalEquals("Valor bruto da ordem difere", order.getGrossValue(), actualOrder.getGrossValue());
        assertBigDecimalEquals("Valor liquido da ordem difere", order.getNetValue(), actualOrder.getNetValue());
        assertBigDecimalEquals("Valor de desconto da ordem difere", order.getGeneralDiscount(), actualOrder.getGeneralDiscount());
    }

    @Entao("^verifica aporte da ordem com sucesso$")
    public void checkAporte() throws Exception {
        final String sapId = actualOrder.getPoNumber() + "-" + actualOrder.getEnterprise();

        final SapOvk ovk = SapOvkDao.selectSapOvkById(sapId);
        Assert.assertNotNull("nao foram encontrados registros de aporte na OVK", ovk);
        Assert.assertEquals("empresa aportada na OVK difere", actualOrder.getEnterprise(), ovk.getEnterprise());
        Assert.assertEquals("anunciante aportado na OVK difere", actualOrder.getAdvertiserSAP().getId(), ovk.getAdvertiser());
        Assert.assertEquals("agencia aportada na OVK difere", (actualOrder.getAgencySAP() == null) ? " " : actualOrder.getAgencySAP().getId(), ovk.getAgency());
        Assert.assertEquals("condicao de pagamento aportada na OVK difere", actualOrder.getPaymentCondition(), ovk.getPaymentCondition());
        Assert.assertEquals("moeda aportada na OVK difere", actualOrder.getCurrency(), ovk.getCurrency());
        assertBigDecimalEquals("valor bruto aportado na OVK difere", actualOrder.getGrossValue(), ovk.getGrossValue());
        assertBigDecimalEquals("valor liquido aportado na OVK difere", actualOrder.getGrossValue(), ovk.getGrossValue());
        assertBigDecimalEquals("valor desconto geral aportado na OVK difere", 0D, ovk.getGeneralDiscount());
        assertBigDecimalEquals("valor desconto anunciante aportado na OVK difere", 0D, ovk.getAdvertiserDiscount());
        assertBigDecimalEquals("valor desconto agencia aportado na OVK difere", actualOrder.getGeneralDiscount(), ovk.getAgencyDiscount());

        final List<SapOvp> actualOvps = SapOvpDao.selectSapOvpById(sapId);
        Assert.assertTrue("nao foram encontrados registros de aporte na OVP", CollectionUtils.isNotEmpty(actualOvps));

        final Map<String, SapOvp> expectedOvps = new TreeMap<>();
        for (LineItemDomain item : actualOrder.getLineItems()) {
            if (item.isArchived()) {
                continue;
            }

            if (expectedOvps.containsKey(item.getSapProduct())) {
                final SapOvp ovp = expectedOvps.get(item.getSapProduct());
                ovp.setGrossValue(ovp.getGrossValue() + item.getGrossValue());
                ovp.setNetValue(ovp.getNetValue() + item.getNetValue());
//              ovp.setGeneralDiscount(ovp.getGeneralDiscount() + item.getGeneralDiscount());
                ovp.setAgencyDiscount(ovp.getAgencyDiscount() + item.getGeneralDiscount());
            } else {
                final SapOvp ovp = new SapOvp();
                ovp.setSapProduct(item.getSapProduct());
                ovp.setGrossValue(item.getGrossValue());
                ovp.setNetValue(item.getNetValue());
                ovp.setGeneralDiscount(0D);
                ovp.setAdvertiserDiscount(0D);
                ovp.setAgencyDiscount(item.getGeneralDiscount());

                expectedOvps.put(item.getSapProduct(), ovp);
            }
        }

        Assert.assertEquals("quantidade de registros aportados na OVP difere", expectedOvps.size(), actualOvps.size());

        final Iterator<SapOvp> expectedIt = expectedOvps.values().iterator();
        final Iterator<SapOvp> actualIt = actualOvps.iterator();

        while (expectedIt.hasNext() && actualIt.hasNext()) {
            final SapOvp expectedOvp = expectedIt.next();
            final SapOvp actualOvp = actualIt.next();

            Assert.assertEquals("material aportado na OVP difere", expectedOvp.getSapProduct(), actualOvp.getSapProduct());
            assertBigDecimalEquals("valor bruto aportado na OVP difere", expectedOvp.getGrossValue(), actualOvp.getGrossValue());
            assertBigDecimalEquals("valor liquido aportado na OVP difere", expectedOvp.getNetValue(), actualOvp.getNetValue());
            
            assertBigDecimalEquals("valor desconto geral aportado na OVP difere", 0D, actualOvp.getGeneralDiscount());
            assertBigDecimalEquals("valor desconto anunciante aportado na OVP difere", 0D, actualOvp.getAdvertiserDiscount());
            assertBigDecimalEquals("valor desconto agencia na OVP difere", expectedOvp.getAgencyDiscount(), actualOvp.getAgencyDiscount());
        }
    }

    private void assertBigDecimalEquals(String failMessage, Double expected, Double actual) {
        Assert.assertEquals(failMessage,
                new BigDecimal(expected).setScale(2, RoundingMode.UP),
                new BigDecimal(actual).setScale(2, RoundingMode.UP));
    }
}
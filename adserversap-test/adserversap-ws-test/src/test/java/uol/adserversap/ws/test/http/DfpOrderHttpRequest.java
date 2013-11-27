package uol.adserversap.ws.test.http;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import uol.adserversap.ws.test.domain.AdvertiserDomain;
import uol.adserversap.ws.test.domain.AgencyDomain;
import uol.adserversap.ws.test.domain.LineItemDomain;
import uol.adserversap.ws.test.domain.OrderDomain;

import com.google.api.ads.dfp.axis.utils.v201302.StatementBuilder;
import com.google.api.ads.dfp.axis.v201302.ApproveAndOverbookOrders;
import com.google.api.ads.dfp.axis.v201302.ArchiveOrders;
import com.google.api.ads.dfp.axis.v201302.BaseCustomFieldValue;
import com.google.api.ads.dfp.axis.v201302.DeleteOrders;
import com.google.api.ads.dfp.axis.v201302.DropDownCustomFieldValue;
import com.google.api.ads.dfp.axis.v201302.Order;
import com.google.api.ads.dfp.axis.v201302.OrderAction;
import com.google.api.ads.dfp.axis.v201302.OrderServiceInterface;
import com.google.api.ads.dfp.axis.v201302.Statement;
import com.google.api.ads.dfp.axis.v201302.SubmitOrdersForApprovalAndOverbook;

public class DfpOrderHttpRequest extends DfpHttpRequest {

    public static OrderDomain createDefaultOrder(AdvertiserDomain advertiser) throws Exception {
        final String name = "Order " + new Timestamp(System.currentTimeMillis()).toString();
        final long traffickerId = Long.valueOf(dfpProperties.get("api.dfp.traffickerId").toString());
        final String enterprise = random.nextBoolean() ? "UOLB" : "BOLB";
        final int billingTypeId = random.nextInt(12) + 3;
        final String notes = "Ordem para testes automatizados";
        final BaseCustomFieldValue enterpriseField = createCustomFieldEnterprise(enterprise);
        final BaseCustomFieldValue billingTypeIdField = createCustomFieldBillingTypeId(billingTypeId);
        final Calendar tomorrow = Calendar.getInstance();
        final Calendar nextWeek = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);
        nextWeek.add(Calendar.DAY_OF_MONTH, 7);

        final OrderServiceInterface orderService = dfpServiceFactory.newDfpService(OrderServiceInterface.class);
        Order dfpOrder = new Order();
        dfpOrder.setName(name);
        dfpOrder.setAdvertiserId(advertiser.getDfpAdvertiser().getId());
        dfpOrder.setTraffickerId(traffickerId);
        dfpOrder.setCustomFieldValues(new BaseCustomFieldValue[]{ enterpriseField, billingTypeIdField });
        dfpOrder.setNotes(notes);
        dfpOrder.setStartDateTime(toDateTime(tomorrow));
        dfpOrder.setEndDateTime(toDateTime(nextWeek));
        dfpOrder = orderService.createOrder(dfpOrder);

        final OrderDomain order = new OrderDomain();
        order.setId(dfpOrder.getId());
        order.setCurrency(dfpOrder.getCurrencyCode());
        order.setName(dfpOrder.getName());
        order.setNotes(dfpOrder.getNotes());
        order.setStatus(dfpOrder.getStatus().toString());
        order.setEnterprise(enterprise);
        order.setPaymentCondition(PAYMENT_CONDITIONS[billingTypeId]);
        order.setArchived(dfpOrder.getIsArchived());
        order.setStartDate(dfpOrder.getStartDateTime().toString());
        order.setEndDate(dfpOrder.getEndDateTime().toString());
        order.setGrossValue(0D);
        order.setNetValue(0D);
        order.setGeneralDiscount(0D);
        order.setAdvertiserDFP(advertiser.getDfpAdvertiser());
        order.setAdvertiserSAP(advertiser.getSapAdvertiser());
        order.setAdvertiserSource("DFP");
        order.setLineItems(new ArrayList<LineItemDomain>());

        return order;
    }

    public static OrderDomain createDefaultOrder() throws Exception {
        final String name = "Order " + new Timestamp(System.currentTimeMillis()).toString();
        final long traffickerId = Long.valueOf(dfpProperties.get("api.dfp.traffickerId").toString());
        final String enterprise = random.nextBoolean() ? "UOLB" : "BOLB";
        final int billingTypeId = random.nextInt(12) + 3;
        final String notes = "Ordem para testes automatizados";
        final BaseCustomFieldValue enterpriseField = createCustomFieldEnterprise(enterprise);
        final BaseCustomFieldValue billingTypeIdField = createCustomFieldBillingTypeId(billingTypeId);
        final Calendar tomorrow = Calendar.getInstance();
        final Calendar nextWeek = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);
        nextWeek.add(Calendar.DAY_OF_MONTH, 7);

        AdvertiserDomain advertiser = DfpCompanyHttpRequest.getValidAdvertiser(enterprise);
        AgencyDomain agency			= DfpCompanyHttpRequest.getValidAgency(enterprise);
        
        final OrderServiceInterface orderService = dfpServiceFactory.newDfpService(OrderServiceInterface.class);
        Order dfpOrder = new Order();
        dfpOrder.setName(name);
        dfpOrder.setAdvertiserId(advertiser.getDfpAdvertiser().getId());
        dfpOrder.setTraffickerId(traffickerId);
        dfpOrder.setCustomFieldValues(new BaseCustomFieldValue[]{ enterpriseField, billingTypeIdField });
        dfpOrder.setNotes(notes);
        dfpOrder.setStartDateTime(toDateTime(tomorrow));
        dfpOrder.setEndDateTime(toDateTime(nextWeek));
        dfpOrder.setAgencyId(agency.getDfpAgency().getId());
        dfpOrder = orderService.createOrder(dfpOrder);

        final OrderDomain order = new OrderDomain();
        order.setId(dfpOrder.getId());
        order.setCurrency(dfpOrder.getCurrencyCode());
        order.setName(dfpOrder.getName());
        order.setNotes(dfpOrder.getNotes());
        order.setStatus(dfpOrder.getStatus().toString());
        order.setEnterprise(enterprise);
        order.setPaymentCondition(PAYMENT_CONDITIONS[billingTypeId]);
        order.setArchived(dfpOrder.getIsArchived());
        order.setStartDate(dfpOrder.getStartDateTime().toString());
        order.setEndDate(dfpOrder.getEndDateTime().toString());
        order.setGrossValue(0D);
        order.setNetValue(0D);
        order.setGeneralDiscount(0D);
        order.setAdvertiserDFP(advertiser.getDfpAdvertiser());
        order.setAdvertiserSAP(advertiser.getSapAdvertiser());
        order.setAdvertiserSource("DFP");
        order.setAgencyDFP(agency.getDfpAgency());
        order.setAgencySAP(agency.getSapAgency());
        order.setLineItems(new ArrayList<LineItemDomain>());
        
        return order;
    }
    
    public static OrderDomain createDefaultOrder(String enterprise) throws Exception {
        final String name = "Order " + new Timestamp(System.currentTimeMillis()).toString();
        final long traffickerId = Long.valueOf(dfpProperties.get("api.dfp.traffickerId").toString());
        final int billingTypeId = random.nextInt(12) + 3;
        final String notes = "Ordem para testes automatizados";
        final BaseCustomFieldValue enterpriseField = createCustomFieldEnterprise(enterprise);
        final BaseCustomFieldValue billingTypeIdField = createCustomFieldBillingTypeId(billingTypeId);
        final Calendar tomorrow = Calendar.getInstance();
        final Calendar nextWeek = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);
        nextWeek.add(Calendar.DAY_OF_MONTH, 7);

        AdvertiserDomain advertiser = DfpCompanyHttpRequest.getValidAdvertiser(enterprise);
        AgencyDomain agency			= DfpCompanyHttpRequest.getValidAgency(enterprise);
        
        final OrderServiceInterface orderService = dfpServiceFactory.newDfpService(OrderServiceInterface.class);
        Order dfpOrder = new Order();
        dfpOrder.setName(name);
        dfpOrder.setAdvertiserId(advertiser.getDfpAdvertiser().getId());
        dfpOrder.setTraffickerId(traffickerId);
        dfpOrder.setCustomFieldValues(new BaseCustomFieldValue[]{ enterpriseField, billingTypeIdField });
        dfpOrder.setNotes(notes);
        dfpOrder.setStartDateTime(toDateTime(tomorrow));
        dfpOrder.setEndDateTime(toDateTime(nextWeek));
        dfpOrder.setAgencyId(agency.getDfpAgency().getId());
        dfpOrder = orderService.createOrder(dfpOrder);

        final OrderDomain order = new OrderDomain();
        order.setId(dfpOrder.getId());
        order.setCurrency(dfpOrder.getCurrencyCode());
        order.setName(dfpOrder.getName());
        order.setNotes(dfpOrder.getNotes());
        order.setStatus(dfpOrder.getStatus().toString());
        order.setEnterprise(enterprise);
        order.setPaymentCondition(PAYMENT_CONDITIONS[billingTypeId]);
        order.setArchived(dfpOrder.getIsArchived());
        order.setStartDate(dfpOrder.getStartDateTime().toString());
        order.setEndDate(dfpOrder.getEndDateTime().toString());
        order.setGrossValue(0D);
        order.setNetValue(0D);
        order.setGeneralDiscount(0D);
        order.setAdvertiserDFP(advertiser.getDfpAdvertiser());
        order.setAdvertiserSAP(advertiser.getSapAdvertiser());
        order.setAdvertiserSource("DFP");
        order.setAgencyDFP(agency.getDfpAgency());
        order.setAgencySAP(agency.getSapAgency());
        order.setLineItems(new ArrayList<LineItemDomain>());
        
        return order;
    }

    public static OrderDomain createDefaultOrderWithoutBillingType() throws Exception {
        final String name = "Order " + new Timestamp(System.currentTimeMillis()).toString();
        final long traffickerId = Long.valueOf(dfpProperties.get("api.dfp.traffickerId").toString());
        final String enterprise = random.nextBoolean() ? "UOLB" : "BOLB";
        final int billingTypeId = random.nextInt(12) + 3;
        final String notes = "Ordem para testes automatizados";
        final BaseCustomFieldValue enterpriseField = createCustomFieldEnterprise(enterprise);
        final BaseCustomFieldValue billingTypeIdField = createCustomFieldBillingTypeId(billingTypeId);
        final Calendar tomorrow = Calendar.getInstance();
        final Calendar nextWeek = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);
        nextWeek.add(Calendar.DAY_OF_MONTH, 7);

        AdvertiserDomain advertiser = DfpCompanyHttpRequest.getValidAdvertiser(enterprise);
        AgencyDomain agency			= DfpCompanyHttpRequest.getValidAgency(enterprise);
        
        final OrderServiceInterface orderService = dfpServiceFactory.newDfpService(OrderServiceInterface.class);
        Order dfpOrder = new Order();
        dfpOrder.setName(name);
        dfpOrder.setAdvertiserId(advertiser.getDfpAdvertiser().getId());
        dfpOrder.setTraffickerId(traffickerId);
        dfpOrder.setCustomFieldValues(new BaseCustomFieldValue[]{ enterpriseField, billingTypeIdField });
        dfpOrder.setNotes(notes);
        dfpOrder.setStartDateTime(toDateTime(tomorrow));
        dfpOrder.setEndDateTime(toDateTime(nextWeek));
        dfpOrder.setAgencyId(agency.getDfpAgency().getId());
        dfpOrder = orderService.createOrder(dfpOrder);

        final OrderDomain order = new OrderDomain();
        order.setId(dfpOrder.getId());
        order.setCurrency(dfpOrder.getCurrencyCode());
        order.setName(dfpOrder.getName());
        order.setNotes(dfpOrder.getNotes());
        order.setStatus(dfpOrder.getStatus().toString());
        order.setEnterprise(enterprise);
//        order.setPaymentCondition(PAYMENT_CONDITIONS[billingTypeId]);
        order.setArchived(dfpOrder.getIsArchived());
        order.setStartDate(dfpOrder.getStartDateTime().toString());
        order.setEndDate(dfpOrder.getEndDateTime().toString());
        order.setGrossValue(0D);
        order.setNetValue(0D);
        order.setGeneralDiscount(0D);
        order.setAdvertiserDFP(advertiser.getDfpAdvertiser());
        order.setAdvertiserSAP(advertiser.getSapAdvertiser());
        order.setAdvertiserSource("DFP");
        order.setAgencyDFP(agency.getDfpAgency());
        order.setAgencySAP(agency.getSapAgency());
        order.setLineItems(new ArrayList<LineItemDomain>());
        
        return order;
    }
    
    public static OrderDomain createDefaultOrderWithoutAgency() throws Exception {
        final String name = "Order " + new Timestamp(System.currentTimeMillis()).toString();
        final long traffickerId = Long.valueOf(dfpProperties.get("api.dfp.traffickerId").toString());
        final String enterprise = random.nextBoolean() ? "UOLB" : "BOLB";
        final int billingTypeId = random.nextInt(12) + 3;
        final String notes = "Ordem para testes automatizados";
        final BaseCustomFieldValue enterpriseField = createCustomFieldEnterprise(enterprise);
        final BaseCustomFieldValue billingTypeIdField = createCustomFieldBillingTypeId(billingTypeId);
        final Calendar tomorrow = Calendar.getInstance();
        final Calendar nextWeek = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);
        nextWeek.add(Calendar.DAY_OF_MONTH, 7);

        AdvertiserDomain advertiser = DfpCompanyHttpRequest.getValidAdvertiser(enterprise);
        
        final OrderServiceInterface orderService = dfpServiceFactory.newDfpService(OrderServiceInterface.class);
        Order dfpOrder = new Order();
        dfpOrder.setName(name);
        dfpOrder.setAdvertiserId(advertiser.getDfpAdvertiser().getId());
        dfpOrder.setTraffickerId(traffickerId);
        dfpOrder.setCustomFieldValues(new BaseCustomFieldValue[]{ enterpriseField, billingTypeIdField });
        dfpOrder.setNotes(notes);
        dfpOrder.setStartDateTime(toDateTime(tomorrow));
        dfpOrder.setEndDateTime(toDateTime(nextWeek));
        dfpOrder = orderService.createOrder(dfpOrder);

        final OrderDomain order = new OrderDomain();
        order.setId(dfpOrder.getId());
        order.setCurrency(dfpOrder.getCurrencyCode());
        order.setName(dfpOrder.getName());
        order.setNotes(dfpOrder.getNotes());
        order.setStatus(dfpOrder.getStatus().toString());
        order.setEnterprise(enterprise);
        order.setPaymentCondition(PAYMENT_CONDITIONS[billingTypeId]);
        order.setArchived(dfpOrder.getIsArchived());
        order.setStartDate(dfpOrder.getStartDateTime().toString());
        order.setEndDate(dfpOrder.getEndDateTime().toString());
        order.setGrossValue(0D);
        order.setNetValue(0D);
        order.setGeneralDiscount(0D);
        order.setAdvertiserDFP(advertiser.getDfpAdvertiser());
        order.setAdvertiserSAP(advertiser.getSapAdvertiser());
        order.setAdvertiserSource("DFP");
        order.setLineItems(new ArrayList<LineItemDomain>());
        
        return order;
    }
    
    
    public static void associateAgency(OrderDomain order, AgencyDomain agency) throws Exception {
        final OrderServiceInterface orderService = dfpServiceFactory.newDfpService(OrderServiceInterface.class);

        Order dfpOrder = orderService.getOrder(order.getId());
        dfpOrder.setAgencyId(agency.getDfpAgency().getId());
        dfpOrder = orderService.updateOrder(dfpOrder);

        order.setAgencyDFP(agency.getDfpAgency());
        order.setAgencySAP(agency.getSapAgency());
    }

    private static BaseCustomFieldValue createCustomFieldEnterprise(String enterprise) {
        if (enterprise == null) {
            return null;
        }

        final DropDownCustomFieldValue customFieldValue = new DropDownCustomFieldValue();
        customFieldValue.setCustomFieldId(remoteConfig.getDfpCustomfieldIdForEnterprise());
        customFieldValue.setCustomFieldOptionId(remoteConfig.getDfpCustomfieldOptionsForEnterprise().get(enterprise));

        return customFieldValue;
    }

    private static BaseCustomFieldValue createCustomFieldBillingTypeId(Integer billingTypeId) {
        if (billingTypeId == null) {
            return null;
        }

        final DropDownCustomFieldValue customFieldValue = new DropDownCustomFieldValue();
        customFieldValue.setCustomFieldId(remoteConfig.getDfpCustomfieldIdForBillingTypeId());
        customFieldValue.setCustomFieldOptionId(remoteConfig.getDfpCustomfieldOptionsForBillingTypeId().get(billingTypeId));

        return customFieldValue;
    }

    public static void approveOrder(OrderDomain order) throws Exception {
        final Order dfpOrder = performOrderAction(order, new ApproveAndOverbookOrders());
        order.setStatus(dfpOrder.getStatus().toString());
    }

    public static void archiveOrder(OrderDomain order) throws Exception {
        final Order dfpOrder = performOrderAction(order, new ArchiveOrders());
        order.setArchived(dfpOrder.getIsArchived());
    }

    public static void submitForApproval(OrderDomain order) throws Exception {
        final Order dfpOrder = performOrderAction(order, new SubmitOrdersForApprovalAndOverbook());
        order.setStatus(dfpOrder.getStatus().toString());
    }

    public static void deleteOrder(OrderDomain order) throws Exception {
        performOrderAction(order, new DeleteOrders());
    }

    private static Order performOrderAction(OrderDomain order, OrderAction action) throws Exception {
        final OrderServiceInterface orderService = dfpServiceFactory.newDfpService(OrderServiceInterface.class);
        final Statement stmtFilterById = new StatementBuilder().where("id = :id")
                .withBindVariableValue("id", order.getId())
                .toStatement();

        orderService.performOrderAction(action, stmtFilterById);

        try {
            return orderService.getOrder(order.getId());
        } catch (Exception notFoundException) {
            return null;
        }
    }

    public static void updatePoNumber(OrderDomain order, String newPoNumber) throws Exception {
        if (Objects.equals(order.getPoNumber(), newPoNumber)) {
            return;
        }

        final OrderServiceInterface orderService = dfpServiceFactory.newDfpService(OrderServiceInterface.class);

        Order dfpOrder = orderService.getOrder(order.getId());
        dfpOrder.setPoNumber(newPoNumber);
        dfpOrder = orderService.updateOrder(dfpOrder);

        order.setPoNumber(dfpOrder.getPoNumber());
    }
    
    public static void updateAdvertiser(OrderDomain order, Long advertiserId) throws Exception {
        final OrderServiceInterface orderService = dfpServiceFactory.newDfpService(OrderServiceInterface.class);

        Order dfpOrder = orderService.getOrder(order.getId());
        dfpOrder.setAdvertiserId(advertiserId);
        dfpOrder = orderService.updateOrder(dfpOrder);

        order.setPoNumber(dfpOrder.getPoNumber());
    }
    

    public static void updateEnterprise(OrderDomain order, String newEnterprise) throws Exception {
        if (Objects.equals(order.getEnterprise(), newEnterprise)) {
            return;
        }

        final OrderServiceInterface orderService = dfpServiceFactory.newDfpService(OrderServiceInterface.class);

        Order dfpOrder = orderService.getOrder(order.getId());

        if (newEnterprise == null) {
            final List<BaseCustomFieldValue> fieldValues = new ArrayList<>();
            for (BaseCustomFieldValue fieldValue : dfpOrder.getCustomFieldValues()) {
                if (!Objects.equals(fieldValue.getCustomFieldId(), remoteConfig.getDfpCustomfieldIdForEnterprise())) {
                    fieldValues.add(fieldValue);
                }
            }
            dfpOrder.setCustomFieldValues(fieldValues.toArray(new BaseCustomFieldValue[0]));
        } else {
            for (BaseCustomFieldValue fieldValue : dfpOrder.getCustomFieldValues()) {
                if (Objects.equals(fieldValue.getCustomFieldId(), remoteConfig.getDfpCustomfieldIdForEnterprise())) {
                    ((DropDownCustomFieldValue) fieldValue).setCustomFieldOptionId(remoteConfig.getDfpCustomfieldOptionsForEnterprise().get(newEnterprise));
                    break;
                }
            }
        }

        dfpOrder = orderService.updateOrder(dfpOrder);

        order.setEnterprise(newEnterprise);
    }

    public static void updateBillingTypeId(OrderDomain order, Integer newBillingTypeId) throws Exception {
        final OrderServiceInterface orderService = dfpServiceFactory.newDfpService(OrderServiceInterface.class);

        Order dfpOrder = orderService.getOrder(order.getId());

        if (newBillingTypeId == null) {
            final List<BaseCustomFieldValue> fieldValues = new ArrayList<>();
            for (BaseCustomFieldValue fieldValue : dfpOrder.getCustomFieldValues()) {
                if (!Objects.equals(fieldValue.getCustomFieldId(), remoteConfig.getDfpCustomfieldIdForBillingTypeId())) {
                    fieldValues.add(fieldValue);
                }
            }
            dfpOrder.setCustomFieldValues(fieldValues.toArray(new BaseCustomFieldValue[0]));
            order.setPaymentCondition(null);
        } else {
            for (BaseCustomFieldValue fieldValue : dfpOrder.getCustomFieldValues()) {
                if (Objects.equals(fieldValue.getCustomFieldId(), remoteConfig.getDfpCustomfieldIdForBillingTypeId())) {
                    ((DropDownCustomFieldValue) fieldValue).setCustomFieldOptionId(remoteConfig.getDfpCustomfieldOptionsForBillingTypeId().get(newBillingTypeId));
                    break;
                }
            }
            order.setPaymentCondition(PAYMENT_CONDITIONS[newBillingTypeId]);
        }

        dfpOrder = orderService.updateOrder(dfpOrder);
    }
}

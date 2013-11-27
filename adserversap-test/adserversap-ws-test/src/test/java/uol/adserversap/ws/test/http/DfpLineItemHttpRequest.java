package uol.adserversap.ws.test.http;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import uol.adserversap.ws.test.domain.LineItemDomain;
import uol.adserversap.ws.test.domain.OrderDomain;

import com.google.api.ads.dfp.axis.utils.v201302.StatementBuilder;
import com.google.api.ads.dfp.axis.v201302.ActivateLineItems;
import com.google.api.ads.dfp.axis.v201302.ApiException;
import com.google.api.ads.dfp.axis.v201302.ArchiveLineItems;
import com.google.api.ads.dfp.axis.v201302.BaseCustomFieldValue;
import com.google.api.ads.dfp.axis.v201302.BrowserTargeting;
import com.google.api.ads.dfp.axis.v201302.CostType;
import com.google.api.ads.dfp.axis.v201302.CreativePlaceholder;
import com.google.api.ads.dfp.axis.v201302.CreativeRotationType;
import com.google.api.ads.dfp.axis.v201302.DayOfWeek;
import com.google.api.ads.dfp.axis.v201302.DayPart;
import com.google.api.ads.dfp.axis.v201302.DayPartTargeting;
import com.google.api.ads.dfp.axis.v201302.DeleteLineItems;
import com.google.api.ads.dfp.axis.v201302.DeliveryTimeZone;
import com.google.api.ads.dfp.axis.v201302.DropDownCustomFieldValue;
import com.google.api.ads.dfp.axis.v201302.GeoTargeting;
import com.google.api.ads.dfp.axis.v201302.InventoryTargeting;
import com.google.api.ads.dfp.axis.v201302.LineItem;
import com.google.api.ads.dfp.axis.v201302.LineItemAction;
import com.google.api.ads.dfp.axis.v201302.LineItemDiscountType;
import com.google.api.ads.dfp.axis.v201302.LineItemServiceInterface;
import com.google.api.ads.dfp.axis.v201302.LineItemSummaryDuration;
import com.google.api.ads.dfp.axis.v201302.LineItemType;
import com.google.api.ads.dfp.axis.v201302.Location;
import com.google.api.ads.dfp.axis.v201302.MinuteOfHour;
import com.google.api.ads.dfp.axis.v201302.Money;
import com.google.api.ads.dfp.axis.v201302.Size;
import com.google.api.ads.dfp.axis.v201302.Statement;
import com.google.api.ads.dfp.axis.v201302.Targeting;
import com.google.api.ads.dfp.axis.v201302.Technology;
import com.google.api.ads.dfp.axis.v201302.TechnologyTargeting;
import com.google.api.ads.dfp.axis.v201302.TimeOfDay;
import com.google.api.ads.dfp.axis.v201302.UnitType;
import com.google.api.ads.dfp.axis.v201302.UserDomainTargeting;

public class DfpLineItemHttpRequest extends DfpHttpRequest {

    public static LineItemDomain createDefaultLineItem(OrderDomain order) throws Exception {
        final String name = "Line item #" + order.getLineItems().size() + 1;
        final String sapProduct = new String[]{ "DECOMM", "DLINKP", "DPUBLI" }[random.nextInt(3)];
        final String costType = new String[]{ "CPC", "CPM", "CPD" }[random.nextInt(3)];

        final CreativePlaceholder creativePlaceholder = new CreativePlaceholder();
        creativePlaceholder.setSize(new Size(320, 480, false));
        final BaseCustomFieldValue sapProductField = createCustomFieldSapProduct(sapProduct);
        final Calendar tomorrow = Calendar.getInstance();
        final Calendar dayAfterTomorrow = Calendar.getInstance();
        tomorrow.add(Calendar.DAY_OF_MONTH, 1);
        dayAfterTomorrow.add(Calendar.DAY_OF_MONTH, 2);

        LineItem dfpLineItem = new LineItem();
        dfpLineItem.setName(name);
        dfpLineItem.setOrderId(order.getId());
        dfpLineItem.setAllowOverbook(true);
        dfpLineItem.setCreativeRotationType(CreativeRotationType.EVEN);
        dfpLineItem.setCreativePlaceholders(new CreativePlaceholder[]{ creativePlaceholder });
        dfpLineItem.setCustomFieldValues(new BaseCustomFieldValue[]{ sapProductField });
        dfpLineItem.setTargeting(createDefaultTargeting());
        dfpLineItem.setStartDateTime(toDateTime(tomorrow));
        dfpLineItem.setEndDateTime(toDateTime(dayAfterTomorrow));

        setLineItemsCostValues(dfpLineItem, order.getCurrency(), costType);

        final LineItemServiceInterface itemService = dfpServiceFactory.newDfpService(LineItemServiceInterface.class);
        try {
            dfpLineItem = itemService.createLineItem(dfpLineItem);
        } catch (ApiException e) {
            if (e.getFaultString().contains("RangeError.TOO_HIGH @ discount")) {
                dfpLineItem.setDiscount(0D);
                dfpLineItem  = itemService.createLineItem(dfpLineItem);
            }
        }

        return createLineItemDomain(dfpLineItem, order, sapProduct);
    }

    private static void setLineItemsCostValues(LineItem dfpLineItem, String currency, String costType) {
        final LineItemDiscountType discountType =
                new LineItemDiscountType[]{ LineItemDiscountType.PERCENTAGE, LineItemDiscountType.ABSOLUTE_VALUE }[random.nextInt(1)];

        final Double discount = (double) (random.nextInt(5) +  1);
        final Long rate;
        final Long goal;
        final LineItemType lineItemType;
        final CostType lineItemCostType;
        final UnitType unityType;
        final Integer priority;
        final LineItemSummaryDuration duration;

        if ("CPC".equals(costType)) {
            goal = Math.min(100, (long) (random.nextInt(1001)));
            rate = Math.min(10, (long) (random.nextInt(101)));
            lineItemType = LineItemType.STANDARD;
            lineItemCostType = CostType.CPC;
            unityType = UnitType.CLICKS;
            priority = 8;
            duration = LineItemSummaryDuration.LIFETIME;
        } else if ("CPM".equals(costType)) {
            goal = Math.min(1000, (long) (random.nextInt(10001)));
            rate = Math.min(100, (long) (random.nextInt(1001)));
            lineItemType = LineItemType.STANDARD;
            lineItemCostType = CostType.CPM;
            unityType = UnitType.IMPRESSIONS;
            priority = 8;
            duration = LineItemSummaryDuration.LIFETIME;
        } else {
            goal = 100L;
            rate = Math.min(1000, (long) (random.nextInt(10001)));
            lineItemType = LineItemType.SPONSORSHIP;
            lineItemCostType = CostType.CPD;
            unityType = UnitType.IMPRESSIONS;
            priority = 4;
            duration = LineItemSummaryDuration.DAILY;
        }

        dfpLineItem.setLineItemType(lineItemType);
        dfpLineItem.setPriority(priority);
        dfpLineItem.setCostType(lineItemCostType);
        dfpLineItem.setUnitType(unityType);
        dfpLineItem.setCostPerUnit(new Money(currency, rate * 1000000));
        dfpLineItem.setUnitsBought(goal);
        dfpLineItem.setDiscountType(discountType);
        dfpLineItem.setDiscount(discount);
        dfpLineItem.setDuration(duration);
    }

    private static LineItemDomain createLineItemDomain(LineItem dfpLineItem, OrderDomain order, String sapProduct) {
        final LineItemDomain lineItem = new LineItemDomain();
        lineItem.setId(dfpLineItem.getId());
        lineItem.setStatus(dfpLineItem.getStatus().toString());
        lineItem.setStartDate(dfpLineItem.getStartDateTime().toString());
        lineItem.setEndDate(dfpLineItem.getEndDateTime().toString());
        lineItem.setEnterprise(order.getEnterprise());
        lineItem.setSapProduct(sapProduct);
        lineItem.setArchived(dfpLineItem.getIsArchived());

        setLineItemDomainCostValues(dfpLineItem, lineItem, order);

        return lineItem;
    }

    private static void setLineItemDomainCostValues(LineItem dfpLineItem, LineItemDomain lineItemDomain, OrderDomain orderDomain) {
        removeLineItemFromOrder(lineItemDomain, orderDomain);

        lineItemDomain.setRate(dfpLineItem.getCostPerUnit().getMicroAmount() / 1000000D);
        lineItemDomain.setCostType(dfpLineItem.getCostType().toString());

        if (Objects.equals(dfpLineItem.getCostType(), CostType.CPC)) {
            lineItemDomain.setGrossValue(dfpLineItem.getUnitsBought() * lineItemDomain.getRate());
        } else if (Objects.equals(dfpLineItem.getCostType(), CostType.CPM)) {
            lineItemDomain.setGrossValue(dfpLineItem.getUnitsBought() * lineItemDomain.getRate() / 1000D);
        } else if (Objects.equals(dfpLineItem.getCostType(), CostType.CPD)) {
            final long startTime = toDate(dfpLineItem.getStartDateTime()).getTime();
            final long endTime = toDate(dfpLineItem.getEndDateTime()).getTime();
            final long days = ((endTime - startTime) / 86400000L) + 1;
            lineItemDomain.setGrossValue(days * lineItemDomain.getRate());
        }

        lineItemDomain.setGoal((double) dfpLineItem.getUnitsBought());
        lineItemDomain.setNetValue(dfpLineItem.getBudget().getMicroAmount() / 1000000D);
        lineItemDomain.setGeneralDiscount(lineItemDomain.getGrossValue() - lineItemDomain.getNetValue());

        if (Objects.equals(dfpLineItem.getDiscountType(), LineItemDiscountType.PERCENTAGE)) {
            lineItemDomain.setDiscountPercent(dfpLineItem.getDiscount());
        } else {
            lineItemDomain.setDiscountPercent(null);
        }

        addLineItemIntoOrder(lineItemDomain, orderDomain);
    }

    private static void addLineItemIntoOrder(LineItemDomain lineItem, OrderDomain order) {
        if (order.getLineItems().add(lineItem)) {
            order.setGrossValue(order.getGrossValue() + lineItem.getGrossValue());
            order.setNetValue(order.getNetValue() + lineItem.getNetValue());
            order.setGeneralDiscount(order.getGeneralDiscount() + lineItem.getGeneralDiscount());
        }
    }

    private static void removeLineItemFromOrder(LineItemDomain lineItem, OrderDomain order) {
        if (order.getLineItems().remove(lineItem)) {
            order.setGrossValue(order.getGrossValue() - lineItem.getGrossValue());
            order.setNetValue(order.getNetValue() - lineItem.getNetValue());
            order.setGeneralDiscount(order.getGeneralDiscount() - lineItem.getGeneralDiscount());
        }
    }

    private static Targeting createDefaultTargeting() {
        // placementId criado manualmente no DFP
        long[] targetedPlacementIds = { Long.valueOf(dfpProperties.get("api.dfp.placementId").toString()) };

        // Create inventory targeting.
        InventoryTargeting inventoryTargeting = new InventoryTargeting();
        inventoryTargeting.setTargetedPlacementIds(targetedPlacementIds);

        // Create geographical targeting.
        GeoTargeting geoTargeting = new GeoTargeting();

        // Include the US, Quebec, Canada, and the B3P Canada postal code.
        Location countryLocation = new Location();
        countryLocation.setId(2840L);

        Location regionLocation = new Location();
        regionLocation.setId(20123L);

        Location postalCodeLocation = new Location();
        postalCodeLocation.setId(9000093L);

        geoTargeting.setTargetedLocations(new Location[] { countryLocation,
                regionLocation, postalCodeLocation });

        // Exclude Chicago and the New York metro area.
        Location cityLocation = new Location();
        cityLocation.setId(1016367L);

        Location metroLocation = new Location();
        metroLocation.setId(200501L);
        geoTargeting.setExcludedLocations(new Location[] { cityLocation,
                metroLocation });

        // Exclude domains that are not under the network's control.
        UserDomainTargeting userDomainTargeting = new UserDomainTargeting();
        userDomainTargeting.setDomains(new String[] { "usa.gov" });
        userDomainTargeting.setTargeted(false);

        // Create day-part targeting.
        DayPartTargeting dayPartTargeting = new DayPartTargeting();
        dayPartTargeting.setTimeZone(DeliveryTimeZone.BROWSER);

        // Target only the weekend in the browser's timezone.
        DayPart saturdayDayPart = new DayPart();
        saturdayDayPart.setDayOfWeek(DayOfWeek.SATURDAY);
        saturdayDayPart.setStartTime(new TimeOfDay(0, MinuteOfHour.ZERO));
        saturdayDayPart.setEndTime(new TimeOfDay(24, MinuteOfHour.ZERO));

        DayPart sundayDayPart = new DayPart();
        sundayDayPart.setDayOfWeek(DayOfWeek.SUNDAY);
        sundayDayPart.setStartTime(new TimeOfDay(0, MinuteOfHour.ZERO));
        sundayDayPart.setEndTime(new TimeOfDay(24, MinuteOfHour.ZERO));
        dayPartTargeting.setDayParts(new DayPart[] { saturdayDayPart,
                sundayDayPart });

        // Create technology targeting.
        TechnologyTargeting technologyTargeting = new TechnologyTargeting();

        // Create browser targeting.
        BrowserTargeting browserTargeting = new BrowserTargeting();
        browserTargeting.setIsTargeted(true);

        // Target just the Chrome browser.
        Technology browserTechnology = new Technology();
        browserTechnology.setId(500072L);
        browserTargeting.setBrowsers(new Technology[] { browserTechnology });
        technologyTargeting.setBrowserTargeting(browserTargeting);

        // Create targeting.
        Targeting targeting = new Targeting();
        targeting.setGeoTargeting(geoTargeting);
        targeting.setInventoryTargeting(inventoryTargeting);
        targeting.setUserDomainTargeting(userDomainTargeting);
        targeting.setDayPartTargeting(dayPartTargeting);
        targeting.setTechnologyTargeting(technologyTargeting);

        return targeting;
    }

    private static BaseCustomFieldValue createCustomFieldSapProduct(String sapProduct) {
        if (sapProduct == null) {
            return null;
        }

        final DropDownCustomFieldValue customFieldValue = new DropDownCustomFieldValue();
        customFieldValue.setCustomFieldId(remoteConfig.getDfpCustomfieldIdForSapProduct());
        customFieldValue.setCustomFieldOptionId(remoteConfig.getDfpCustomfieldOptionsForSapProduct().get(sapProduct));

        return customFieldValue;
    }

    public static void removeLineItems(OrderDomain order) throws Exception {
        if (CollectionUtils.isEmpty(order.getLineItems())) {
            return;
        }

        final List<Long> itemsIds = new ArrayList<>(order.getLineItems().size());
        for (LineItemDomain item : order.getLineItems()) {
            itemsIds.add(item.getId());
        }

        final String itemsIdsString = StringUtils.join(itemsIds, ',');

        final Statement stmtFilterByIds = new StatementBuilder().where(String.format("id IN (%s)", itemsIdsString)).toStatement();

        final LineItemServiceInterface itemService = dfpServiceFactory.newDfpService(LineItemServiceInterface.class);
        itemService.performLineItemAction(new DeleteLineItems(), stmtFilterByIds);

        order.getLineItems().clear();
    }

    public static void archiveLineItem(OrderDomain order, LineItemDomain item) throws Exception {
        final LineItem dfpItem = performLineItemAction(item, new ArchiveLineItems());
        item.setArchived(dfpItem.getIsArchived());

        if (item.isArchived()) {
            order.setGrossValue(order.getGrossValue() - item.getGrossValue());
            order.setNetValue(order.getNetValue() - item.getNetValue());
            order.setGeneralDiscount(order.getGeneralDiscount() - item.getGeneralDiscount());
        }
    }

    public static void activateLineItem(LineItemDomain item) throws Exception {
        final LineItem dfpItem = performLineItemAction(item, new ActivateLineItems());
        item.setStatus(dfpItem.getStatus().toString());
    }

    private static LineItem performLineItemAction(LineItemDomain item, LineItemAction action) throws Exception {
        final LineItemServiceInterface itemService = dfpServiceFactory.newDfpService(LineItemServiceInterface.class);
        final Statement stmtFilterById = new StatementBuilder().where("id = :id")
                .withBindVariableValue("id", item.getId())
                .toStatement();

        itemService.performLineItemAction(action, stmtFilterById);

        try {
            return itemService.getLineItem(item.getId());
        } catch (Exception notFoundException) {
            return null;
        }
    }

    public static void updateSapProduct(LineItemDomain item, String newSapProduct) throws Exception {
        if (Objects.equals(item.getSapProduct(), newSapProduct)) {
            return;
        }

        final LineItemServiceInterface itemService = dfpServiceFactory.newDfpService(LineItemServiceInterface.class);

        LineItem dfpItem = itemService.getLineItem(item.getId());

        if (newSapProduct == null) {
            final List<BaseCustomFieldValue> fieldValues = new ArrayList<>();
            for (BaseCustomFieldValue fieldValue : dfpItem.getCustomFieldValues()) {
                if (!Objects.equals(fieldValue.getCustomFieldId(), remoteConfig.getDfpCustomfieldIdForSapProduct())) {
                    fieldValues.add(fieldValue);
                }
            }
            dfpItem.setCustomFieldValues(fieldValues.toArray(new BaseCustomFieldValue[0]));
        } else {
            for (BaseCustomFieldValue fieldValue : dfpItem.getCustomFieldValues()) {
                if (Objects.equals(fieldValue.getCustomFieldId(), remoteConfig.getDfpCustomfieldIdForSapProduct())) {
                    ((DropDownCustomFieldValue) fieldValue).setCustomFieldOptionId(remoteConfig.getDfpCustomfieldOptionsForSapProduct().get(newSapProduct));
                }
            }
        }

        itemService.updateLineItem(dfpItem);

        item.setSapProduct(newSapProduct);
    }

    public static void updateCostValues(LineItemDomain item, OrderDomain order, String costType) throws Exception {
        final LineItemServiceInterface itemService = dfpServiceFactory.newDfpService(LineItemServiceInterface.class);

        LineItem dfpItem = itemService.getLineItem(item.getId());

        setLineItemsCostValues(dfpItem, dfpItem.getCostPerUnit().getCurrencyCode(), costType);

        dfpItem = itemService.updateLineItem(dfpItem);

        setLineItemDomainCostValues(dfpItem, item, order);
    }

    public static void updateCostPerUnit(LineItemDomain item, OrderDomain order, Long rate) throws Exception {
        final LineItemServiceInterface itemService = dfpServiceFactory.newDfpService(LineItemServiceInterface.class);

        LineItem dfpItem = itemService.getLineItem(item.getId());
        dfpItem.setCostPerUnit(new Money(dfpItem.getCostPerUnit().getCurrencyCode(), rate));
        if (Objects.equals(dfpItem.getDiscountType(), LineItemDiscountType.ABSOLUTE_VALUE) &&
                dfpItem.getDiscount() > rate) {
            dfpItem.setDiscount(Math.max(0, dfpItem.getDiscount() - rate - 1));
        }

        try {
            dfpItem = itemService.updateLineItem(dfpItem);
        } catch (ApiException e) {
            if (e.getFaultString().contains("RangeError.TOO_HIGH @ discount")) {
                dfpItem.setDiscount(0D);
                dfpItem = itemService.updateLineItem(dfpItem);
            }
        }

        setLineItemDomainCostValues(dfpItem, item, order);
    }

    public static void updateDiscountType(LineItemDomain item, OrderDomain order, String discountType) throws Exception {
        final LineItemServiceInterface itemService = dfpServiceFactory.newDfpService(LineItemServiceInterface.class);

        LineItem dfpItem = itemService.getLineItem(item.getId());
        dfpItem.setDiscountType(LineItemDiscountType.fromString(discountType));
        if (Objects.equals(dfpItem.getDiscountType(), LineItemDiscountType.ABSOLUTE_VALUE) &&
                dfpItem.getDiscount() > item.getRate()) {
            dfpItem.setDiscount(Math.max(0, dfpItem.getDiscount() - item.getRate() - 1));
        }
        dfpItem = itemService.updateLineItem(dfpItem);

        setLineItemDomainCostValues(dfpItem, item, order);
    }

    public static void updateDiscountValue(LineItemDomain item, OrderDomain order, Double absoluteDiscountValue) throws Exception {
        final LineItemServiceInterface itemService = dfpServiceFactory.newDfpService(LineItemServiceInterface.class);

        LineItem dfpItem = itemService.getLineItem(item.getId());

        if (Objects.equals(item.getGrossValue(), absoluteDiscountValue) &&
                Objects.equals(dfpItem.getDiscountType(), LineItemDiscountType.PERCENTAGE)) {
            absoluteDiscountValue = 100D;
        }

        dfpItem.setDiscount(absoluteDiscountValue);
        dfpItem = itemService.updateLineItem(dfpItem);

        setLineItemDomainCostValues(dfpItem, item, order);
    }
    
    public static void updateDiscountPartialValue(LineItemDomain item, OrderDomain order, Double absoluteDiscountValue) throws Exception {
        final LineItemServiceInterface itemService = dfpServiceFactory.newDfpService(LineItemServiceInterface.class);

        LineItem dfpItem = itemService.getLineItem(item.getId());
        LineItemDiscountType absoluteDiscount = LineItemDiscountType.ABSOLUTE_VALUE;
        
        dfpItem.setDiscountType(absoluteDiscount);
        dfpItem.setDiscount(absoluteDiscountValue);
        dfpItem = itemService.updateLineItem(dfpItem);

        setLineItemDomainCostValues(dfpItem, item, order);
    }
    
}

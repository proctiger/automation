package uol.adserversap.entity;

public class SapOvk {

    private String mandt;
    private String id;
    private String orderType;
    private String timestamp;
    private String enterprise;
    private String advertiser;
    private String agency;
    private String payer;
    private String paymentCondition;
    private String currency;
    private Double grossValue;
    private Double netValue;
    private Double generalDiscount;
    private Double advertiserDiscount;
    private Double agencyDiscount;

    public String getMandt() {
        return mandt;
    }
    public void setMandt(String mandt) {
        this.mandt = mandt;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getOrderType() {
        return orderType;
    }
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getEnterprise() {
        return enterprise;
    }
    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public String getAdvertiser() {
        return advertiser;
    }
    public void setAdvertiser(String advertiser) {
        this.advertiser = advertiser;
    }

    public String getAgency() {
        return agency;
    }
    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getPayer() {
        return payer;
    }
    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getPaymentCondition() {
        return paymentCondition;
    }
    public void setPaymentCondition(String paymentCondition) {
        this.paymentCondition = paymentCondition;
    }

    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getGrossValue() {
        return grossValue;
    }
    public void setGrossValue(Double grossValue) {
        this.grossValue = grossValue;
    }

    public Double getNetValue() {
        return netValue;
    }
    public void setNetValue(Double netValue) {
        this.netValue = netValue;
    }

    public Double getGeneralDiscount() {
        return generalDiscount;
    }
    public void setGeneralDiscount(Double generalDiscount) {
        this.generalDiscount = generalDiscount;
    }

    public Double getAdvertiserDiscount() {
        return advertiserDiscount;
    }
    public void setAdvertiserDiscount(Double advertiserDiscount) {
        this.advertiserDiscount = advertiserDiscount;
    }

    public Double getAgencyDiscount() {
        return agencyDiscount;
    }
    public void setAgencyDiscount(Double agencyDiscount) {
        this.agencyDiscount = agencyDiscount;
    }
}

package uol.adserversap.entity;

public class SapOvp {

    private String mandt;
    private String id;
    private String orderType;
    private String timestamp;
    private String sapProduct;
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

    public String getSapProduct() {
        return sapProduct;
    }
    public void setSapProduct(String sapProduct) {
        this.sapProduct = sapProduct;
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

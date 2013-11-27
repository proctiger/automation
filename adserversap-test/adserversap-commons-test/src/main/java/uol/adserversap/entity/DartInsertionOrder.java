package uol.adserversap.entity;

import java.util.Date;

public class DartInsertionOrder {

    private long id;
    private Date timestamp;
    private String poNumber;
    private String notes;
    private String name;
    private DartAdvertiser advertiser;
    private DartAgency agency;
    private Integer billingTypeId;
    private Double discountAmount;
    private Double discountPercent;
    private boolean reservation;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getPoNumber() {
        return poNumber;
    }
    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public DartAdvertiser getAdvertiser() {
        return advertiser;
    }
    public void setAdvertiser(DartAdvertiser advertiser) {
        this.advertiser = advertiser;
    }

    public DartAgency getAgency() {
        return agency;
    }
    public void setAgency(DartAgency agency) {
        this.agency = agency;
    }

    public Integer getBillingTypeId() {
        return billingTypeId;
    }
    public void setBillingTypeId(Integer billingTypeId) {
        this.billingTypeId = billingTypeId;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }
    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }
    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public boolean isReservation() {
        return reservation;
    }
    public void setReservation(boolean reservation) {
        this.reservation = reservation;
    }
}

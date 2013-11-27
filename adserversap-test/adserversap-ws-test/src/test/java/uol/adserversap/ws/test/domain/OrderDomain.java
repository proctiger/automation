package uol.adserversap.ws.test.domain;

import java.util.List;

public class OrderDomain {

    private Long id;
    private String poNumber;
    private String currency;
    private String name;
    private String notes;
    private String status;
    private String enterprise;
    private String paymentCondition;
    private String billingType;
    private boolean archived;
    private String startDate;
    private String endDate;
    private String lastModified;
    private Double grossValue;
    private Double netValue;
    private Double generalDiscount;
    private DfpAdvertiserDomain advertiserDFP;
    private SapAdvertiserDomain advertiserSAP;
    private DfpAgencyDomain agencyDFP;
    private SapAgencyDomain agencySAP;
    private String advertiserSource;
    private List<LineItemDomain> lineItems;
    private List<OrderDomain> relatedOrders;
    private List<InconsistenceDomain> inconsistencies;
    private List<OrderWarning> warnings;
    

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPoNumber() {
        return poNumber;
    }
    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getEnterprise() {
        return enterprise;
    }
    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }
    public String getPaymentCondition() {
        return paymentCondition;
    }
    public void setPaymentCondition(String paymentCondition) {
        this.paymentCondition = paymentCondition;
    }
    public boolean isArchived() {
        return archived;
    }
    public void setArchived(boolean archived) {
        this.archived = archived;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
    public DfpAdvertiserDomain getAdvertiserDFP() {
        return advertiserDFP;
    }
    public void setAdvertiserDFP(DfpAdvertiserDomain advertiserDFP) {
        this.advertiserDFP = advertiserDFP;
    }
    public SapAdvertiserDomain getAdvertiserSAP() {
        return advertiserSAP;
    }
    public void setAdvertiserSAP(SapAdvertiserDomain advertiserSAP) {
        this.advertiserSAP = advertiserSAP;
    }
    public DfpAgencyDomain getAgencyDFP() {
        return agencyDFP;
    }
    public void setAgencyDFP(DfpAgencyDomain agencyDFP) {
        this.agencyDFP = agencyDFP;
    }
    public SapAgencyDomain getAgencySAP() {
        return agencySAP;
    }
    public void setAgencySAP(SapAgencyDomain agencySAP) {
        this.agencySAP = agencySAP;
    }
    public List<LineItemDomain> getLineItems() {
        return lineItems;
    }
    public void setLineItems(List<LineItemDomain> lineItems) {
        this.lineItems = lineItems;
    }
    public List<InconsistenceDomain> getInconsistencies() {
        return inconsistencies;
    }
    public void setInconsistencies(List<InconsistenceDomain> inconsistencies) {
        this.inconsistencies = inconsistencies;
    }
    public String getAdvertiserSource() {
        return advertiserSource;
    }
    public void setAdvertiserSource(String advertiserSource) {
        this.advertiserSource = advertiserSource;
    }
    public String getLastModified() {
        return lastModified;
    }
    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }
    public String getBillingType() {
        return billingType;
    }
    public void setBillingType(String billingType) {
        this.billingType = billingType;
    }
    public List<OrderDomain> getRelatedOrders() {
        return relatedOrders;
    }
    public void setRelatedOrders(List<OrderDomain> relatedOrders) {
        this.relatedOrders = relatedOrders;
    }
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDomain other = (OrderDomain) obj;
		if (advertiserDFP == null) {
			if (other.advertiserDFP != null)
				return false;
		} else if (!advertiserDFP.equals(other.advertiserDFP)){
			System.out.println("advertiserDFP");
			return false;
		}
		if (advertiserSAP == null) {
			if (other.advertiserSAP != null)
				return false;
		} else if (!advertiserSAP.equals(other.advertiserSAP)){
			System.out.println("advertiserDFP");
			return false; 
		}
		if (advertiserSource == null) {
			if (other.advertiserSource != null)
				return false;
		} else if (!advertiserSource.equals(other.advertiserSource))
			return false;
		if (agencyDFP == null) {
			if (other.agencyDFP != null)
				return false;
		} else if (!agencyDFP.equals(other.agencyDFP)){
			System.out.println("agencyDFP");
			return false;
		}
		if (agencySAP == null) {
			if (other.agencySAP != null)
				return false;
		} else if (!agencySAP.equals(other.agencySAP)){
			System.out.println("agencySAP");
			return false;}
		if (archived != other.archived)
			return false;
//		if (billingType == null) {
//			if (other.billingType != null)
//				return false;
//		} else if (!billingType.equals(other.billingType))
//			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
//		if (endDate == null) {
//			if (other.endDate != null)
//				return false;
//		} else if (!endDate.equals(other.endDate))
//			return false;
		if (enterprise == null) {
			if (other.enterprise != null)
				return false;
		} else if (!enterprise.equals(other.enterprise))
			return false;
		if (generalDiscount == null) {
			if (other.generalDiscount != null)
				return false;
		} else if (!generalDiscount.equals(other.generalDiscount))
			return false;
		if (grossValue == null) {
			if (other.grossValue != null)
				return false;
		} else if (!grossValue.equals(other.grossValue))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inconsistencies == null) {
			if (other.inconsistencies != null)
				return false;
		} else if (!inconsistencies.equals(other.inconsistencies))
			return false;
//		if (lastModified == null) {
//			if (other.lastModified != null)
//				return false;
//		} else if (!lastModified.equals(other.lastModified))
//			return false;
		if (lineItems == null) {
			if (other.lineItems != null)
				return false;
		} else if (!lineItems.equals(other.lineItems)){
			System.out.println("lineItems");
			return false;
		}
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.trim().equals(other.name.trim()))
			return false;
		if (netValue == null) {
			if (other.netValue != null)
				return false;
		} else if (!netValue.equals(other.netValue))
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (paymentCondition == null) {
			if (other.paymentCondition != null)
				return false;
		} else if (!paymentCondition.equals(other.paymentCondition))
			return false;
		if (poNumber == null) {
			if (other.poNumber != null)
				return false;
		} else if (!poNumber.equals(other.poNumber))
			return false;
		if (relatedOrders == null) {
			if (other.relatedOrders != null)
				return false;
		} else if (!relatedOrders.equals(other.relatedOrders))
			return false;
//		if (startDate == null) {
//			if (other.startDate != null)
//				return false;
//		} else if (!startDate.equals(other.startDate))
//			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	public List<OrderWarning> getWarnings() {
		return warnings;
	}
	public void setWarnings(List<OrderWarning> warnings) {
		this.warnings = warnings;
	}

}

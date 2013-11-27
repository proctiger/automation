package uol.adserversap.ws.test.domain;

import java.util.ArrayList;
import java.util.List;

public class LineItemDomain {
	private Long id;
    private String name;
    private String status;
    private String startDate;
    private String endDate;
    private String enterprise;
    private String sapProduct;
    private String costType;
    private boolean archived;
    private Double goal;
    private Double rate;
    private Double grossValue;
    private Double netValue;
    private Double generalDiscount;
    private Double discountPercent;
    private List<String> sizes = new ArrayList<>();
    private Targeting targetingIncluded;
    private Targeting targetingExcluded;
    private Long clicksDelivered;
    private Long impressionsDelivered;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
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
    public String getEnterprise() {
        return enterprise;
    }
    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }
    public String getSapProduct() {
        return sapProduct;
    }
    public void setSapProduct(String sapProduct) {
        this.sapProduct = sapProduct;
    }
    public String getCostType() {
        return costType;
    }
    public void setCostType(String costType) {
        this.costType = costType;
    }
    public boolean isArchived() {
        return archived;
    }
    public void setArchived(boolean archived) {
        this.archived = archived;
    }
    public Double getGoal() {
        return goal;
    }
    public void setGoal(Double goal) {
        this.goal = goal;
    }
    public Double getRate() {
        return rate;
    }
    public void setRate(Double rate) {
        this.rate = rate;
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
    public List<String> getSizes() {
        return sizes;
    }
    public void setSizes(List<String> sizes) {
        this.sizes = sizes;
    }
    public Double getDiscountPercent() {
        return discountPercent;
    }
    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }
	public Targeting getTargetingIncluded() {
		return targetingIncluded;
	}
	public void setTargetingIncluded(Targeting targetingIncluded) {
		this.targetingIncluded = targetingIncluded;
	}
	public Targeting getTargetingExcluded() {
		return targetingExcluded;
	}
	public void setTargetingExcluded(Targeting targetingExcluded) {
		this.targetingExcluded = targetingExcluded;
	}
	public Long getClicksDelivered() {
		return clicksDelivered;
	}
	public void setClicksDelivered(Long clicksDelivered) {
		this.clicksDelivered = clicksDelivered;
	}
	public Long getImpressionsDelivered() {
		return impressionsDelivered;
	}
	public void setImpressionsDelivered(Long impressionsDelivered) {
		this.impressionsDelivered = impressionsDelivered;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineItemDomain other = (LineItemDomain) obj;
//		if (adPlacementNames == null) {
//			if (other.adPlacementNames != null)
//				return false;
//		} else if (!adPlacementNames.equals(other.adPlacementNames))
//			return false;
		if (archived != other.archived)
			return false;
		if (costType == null) {
			if (other.costType != null)
				return false;
		} else if (!costType.equals(other.costType))
			return false;
		if (discountPercent == null) {
			if (other.discountPercent != null)
				return false;
		} else if (!discountPercent.equals(other.discountPercent))
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
		} else if (!(String.format("%.3f",generalDiscount).equals(String.format("%.3f", other.generalDiscount))))
			return false;
		if (goal == null) {
			if (other.goal != null)
				return false;
		}
		if (!other.costType.equals("CPD")){
			if (!goal.equals(other.goal))
				return false;
		}
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
//		if (name == null) {
//			if (other.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
		if (netValue == null) {
			if (other.netValue != null)
				return false;
		} else if (!netValue.equals(other.netValue))
			return false;
		if (rate == null) {
			if (other.rate != null)
				return false;
		} else if (!rate.equals(other.rate))
			return false;
		if (sapProduct == null) {
			if (other.sapProduct != null)
				return false;
		} else if (!sapProduct.equals(other.sapProduct))
			return false;
//		if (sizes == null) {
//			if (other.sizes != null)
//				return false;
//		} else if (!sizes.equals(other.sizes))
//			return false;
//		if (startDate == null) {
//			if (other.startDate != null)
//				return false;
//		} else if (!startDate.equals(other.startDate))
//			return false;
//		if (status == null) {
//			if (other.status != null)
//				return false;
//		} else if (!status.equals(other.status))
//			return false;
		return true;
	}
	
	   @Override
		public String toString() {
			return "LineItemDomain [id=" + id + ", name=" + name + ", status="
					+ status + ", startDate=" + startDate + ", endDate=" + endDate
					+ ", enterprise=" + enterprise + ", sapProduct=" + sapProduct
					+ ", costType=" + costType + ", archived=" + archived
					+ ", goal=" + goal + ", rate=" + rate + ", grossValue="
					+ grossValue + ", netValue=" + netValue + ", generalDiscount="
					+ generalDiscount + ", discountPercent=" + discountPercent
					+ ", sizes=" + sizes + ", targetingIncluded="
					+ targetingIncluded + ", targetingExcluded="
					+ targetingExcluded + ", clicksDelivered=" + clicksDelivered
					+ ", impressionsDelivered=" + impressionsDelivered + "]";
		}
	
}

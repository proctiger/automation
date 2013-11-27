package uol.adserversap.entity;

/**
 * @author wrodrigues@uolinc.com
 */
public class Association {

    private Long sourceID;
    private Long sapID;

    public Association() {
	}
    
	public Association(Long sourceID, Long sapID) {
		this.sourceID = sourceID;
		this.sapID = sapID;
	}
	public Long getSourceID() {
        return sourceID;
    }
    public void setSourceID(Long sourceID) {
        this.sourceID = sourceID;
    }
    
    public Long getSapID() {
        return sapID;
    }
    public void setSapID(Long sapID) {
        this.sapID = sapID;
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Association other = (Association) obj;
		if (sapID == null) {
			if (other.sapID != null)
				return false;
		} else if (!sapID.equals(other.sapID))
			return false;
		if (sourceID == null) {
			if (other.sourceID != null)
				return false;
		} else if (!sourceID.equals(other.sourceID))
			return false;
		return true;
	}
}
    
    

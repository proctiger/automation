package uol.adserversap.ws.test.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include=Inclusion.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class DfpAdvertiserDomain {

    private Long id;
    private String sapId;
    private String name;
    private String notes;
    private boolean activeCreditStatus;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getSapId() {
        return sapId;
    }
    public void setSapId(String sapId) {
        this.sapId = sapId;
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
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DfpAdvertiserDomain other = (DfpAdvertiserDomain) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.trim().equals(other.name.trim()))
			return false;
		if (notes == null || notes.isEmpty()) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (sapId == null) {
			if (other.sapId != null)
				return false;
		} else if (!sapId.equals(other.sapId))
			return false;
		return true;
	}
	public boolean isActiveCreditStatus() {
		return activeCreditStatus;
	}
	public void setActiveCreditStatus(boolean activeCreditStatus) {
		this.activeCreditStatus = activeCreditStatus;
	}
}

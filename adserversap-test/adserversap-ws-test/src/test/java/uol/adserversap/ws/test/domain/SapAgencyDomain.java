package uol.adserversap.ws.test.domain;

public class SapAgencyDomain {

    private String id;
    private String name;
    private String cnpj;
    private String notes;
    private String enterprise;
    private Address address;
    private boolean activeCreditStatus;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
	public String getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SapAgencyDomain other = (SapAgencyDomain) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (enterprise == null) {
			if (other.enterprise != null)
				return false;
		} else if (!enterprise.equals(other.enterprise))
			return false;
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
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SapAgencyDomain [id=" + id + ", name=" + name + ", cnpj="
				+ cnpj + ", notes=" + notes + ", enterprise=" + enterprise
				+ "]";
	}
	public boolean isActiveCreditStatus() {
		return activeCreditStatus;
	}
	public void setActiveCreditStatus(boolean activeCreditStatus) {
		this.activeCreditStatus = activeCreditStatus;
	}
}

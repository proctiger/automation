package uol.adserversap.entity;

import java.util.Date;

public class OrderAssociation {

    private long id;
    private String poNumber;
    private String source;
    private Date lastUpdate;
    private String enterprise;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getPoNumber() {
        return poNumber;
    }
    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getEnterprise() {
        return enterprise;
    }
    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }
}

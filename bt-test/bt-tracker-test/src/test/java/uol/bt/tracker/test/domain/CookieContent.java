package uol.bt.tracker.test.domain;

import java.util.Date;

/**
 *
 * @author dvrocha
 */
public class CookieContent {

    private String domain;
    private String name;
    private String value;
    private String path;
    private Date expiryDate;

    public CookieContent() {
    }

    public CookieContent(String domain, String name, String value, String path, Date expiryDate) {
        this.domain = domain;
        this.name = name;
        this.value = value;
        this.path = path;
        this.expiryDate = expiryDate;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

package uol.adserversap.ws.test.domain;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include=Inclusion.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    private String street;
    private String district;
    private String city;
    private String state;
    private String zipcode;
    private String phone;

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = StringUtils.trimToNull(street);
    }

    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = StringUtils.trimToNull(district);
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = StringUtils.trimToNull(city);
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = StringUtils.trimToNull(state);
    }

    public String getZipcode() {
        return zipcode;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = StringUtils.trimToNull(zipcode);
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = StringUtils.trimToNull(phone);
    }
}

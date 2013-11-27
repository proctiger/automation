package uol.collective.offer.commons.test.model.impl;

import java.util.HashSet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;




@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ErrorTestResponse {
    
    public ErrorTestResponse() {}
    
    public ErrorTestResponse(String code, String description, HashSet<String> fields) {
        this.code = code;
        this.description = description;
        this.fields = fields;
    }
    
    @XmlElement
    private String code;
    
    @XmlElement
    private String description;
    
    @XmlElement(name = "field")
    @XmlElementWrapper(name = "fields")
    private HashSet<String> fields;

    public HashSet<String> getFields() {
        return fields;
    }

    public void setFields(HashSet<String> fields) {
        this.fields = fields;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ErrorTestResponse other = (ErrorTestResponse) obj;
        if ((this.code == null) ? (other.code != null) : !this.code.equals(other.code)) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        if (this.fields != other.fields && (this.fields == null || !this.fields.equals(other.fields))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.code != null ? this.code.hashCode() : 0);
        hash = 79 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 79 * hash + (this.fields != null ? this.fields.hashCode() : 0);
        return hash;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("ErrorTestResponse [");
        sb.append("code=");
        sb.append(this.code != null ? this.code : "null");
        sb.append(", ");
        sb.append("description=");
        sb.append(this.description != null ? this.description : "null");
        sb.append(", ");
        sb.append("fields [");
        if(this.fields != null){
            int counter = 0;
            for (String field : this.fields) {
                if(counter != 0)
                    sb.append(", ");
                sb.append(field);
                counter++;
            }
        }
        sb.append("]");
        sb.append("]");
        return sb.toString();
    }
    
    
    
}




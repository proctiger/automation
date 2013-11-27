package uol.collective.offer.commons.test.model.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "errors")
@XmlAccessorType(XmlAccessType.FIELD)
public class ErrorsTestResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    @XmlElement(name = "error")
    private Set<ErrorTestResponse> errorList;

    public Set<ErrorTestResponse> getErrorList() {
        return errorList;
    }

    public void setErrorList(Set<ErrorTestResponse> errorList) {
        this.errorList = errorList;
    }

    public void addError(ErrorTestResponse error)
    {
        if( errorList == null ){
            this.errorList = new HashSet<ErrorTestResponse>(); 
        }
        this.errorList.add( error );
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ErrorsTestResponse other = (ErrorsTestResponse) obj;
        if (this.errorList != other.errorList && (this.errorList == null || !this.errorList.equals(other.errorList))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.errorList != null ? this.errorList.hashCode() : 0);
        return hash;
    }
    
    @Override
    public String toString()
    {
        return "ErrorsTestResponse [errorList=" + errorList + "]";
    }


}

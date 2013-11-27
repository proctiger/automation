package uol.collective.offer.commons.test.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import uol.collective.offer.commons.test.annotation.SequenceName;
import uol.collective.offer.commons.test.model.TestResponse;

@Entity
@Table(name = "city")
@XmlRootElement(name = "city")
@XmlAccessorType(XmlAccessType.FIELD)
@SequenceName(name = "sq_city_idt")
public class CityTestResponse implements TestResponse
{

    @Id
    @Column(name = "idt_city")
    private String id;

    @Column(name = "nam_city")
    private String name;

    @Column(name = "ind_status")
    private String status;

    @Column(name = "nam_normalized_city")
    private String normalizedName;

    @Column(name = "nam_federation_unit")
    private String federationUnit;

    public CityTestResponse()
    {
        super();
    }

    public CityTestResponse(String id)
    {
        super();
        this.id = id;
    }

    public CityTestResponse(String name,
            String status,
            String federationUnit)
    {
        super();
        this.name = name;
        this.status = status;
        this.federationUnit = federationUnit;
    }

    @Override
    public String getEntityRootUrl()
    {
        return ENTITY_ROOT_RADAR + "/city";
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getNormalizedName()
    {
        return normalizedName;
    }

    public void setNormalizedName(String normalizedName)
    {
        this.normalizedName = normalizedName;
    }

    public String getFederationUnit()
    {
        return federationUnit;
    }

    public void setFederationUnit(String federationUnit)
    {
        this.federationUnit = federationUnit;
    }

    @Override
    public String toString()
    {
        return "CityTestResponse [id=" + id + ", name=" + name + ", status=" + status + ", normalizedName=" + normalizedName + ", federationUnit=" + federationUnit + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CityTestResponse other = (CityTestResponse) obj;
        if ( id == null )
        {
            if ( other.id != null )
                return false;
        } else if ( !id.equals( other.id ) )
            return false;
        return true;
    }
}

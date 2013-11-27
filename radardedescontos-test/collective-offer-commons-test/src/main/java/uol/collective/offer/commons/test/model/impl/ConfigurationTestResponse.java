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
@Table(name = "configuration")
@XmlRootElement(name = "configuration")
@XmlAccessorType(XmlAccessType.FIELD)
@SequenceName(name = "sq_configuration_idt")
public class ConfigurationTestResponse implements TestResponse
{

    @Id
    @Column(name = "idt_configuration")
    private String id;

    @Column(name = "des_configuration")
    private String key;

    @Column(name = "des_value")
    private String value;

    @Column(name = "flg_active")
    private String flgStatus;

    public ConfigurationTestResponse()
    {
        super();
    }

    public ConfigurationTestResponse(String id,
            String key,
            String value,
            String flgStatus)
    {
        super();
        this.id = id;
        this.key = key;
        this.value = value;
        this.flgStatus = flgStatus;
    }

    public ConfigurationTestResponse(String key,
            String value,
            String flgStatus)
    {
        super();
        this.key = key;
        this.value = value;
        this.flgStatus = flgStatus;
    }

    @Override
    public String getEntityRootUrl()
    {
        return ENTITY_ROOT_RADAR + "/offerLocation";
    }

    @Override
    public String getId()
    {
        return id;
    }

    @Override
    public void setId(String id)
    {
        this.id = id;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getFlgStatus()
    {
        return flgStatus;
    }

    public void setFlgStatus(String flgStatus)
    {
        this.flgStatus = flgStatus;
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
        ConfigurationTestResponse other = (ConfigurationTestResponse) obj;
        if ( id == null )
        {
            if ( other.id != null )
                return false;
        } else if ( !id.equals( other.id ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "ConfigurationTestResponse [id=" + id + ", key=" + key + ", value=" + value + ", flgStatus=" + flgStatus + "]";
    }

}

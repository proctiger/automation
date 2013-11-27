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
@Table(name = "slot")
@XmlRootElement(name = "slot")
@XmlAccessorType(XmlAccessType.FIELD)
@SequenceName(name="sq_slot_idt", schema = "radar_adm")
public class SlotTestResponse implements TestResponse
{
//TODO REVER CDATA
    
    @Id
    @Column(name = "idt_slot")
    private String id;
    
//    @XmlJavaTypeAdapter(value = CDATARadar.class)
    @Column(name = "des_slot")
    private String description;

//    @XmlJavaTypeAdapter(value = CDATARadar.class)
    @Column(name = "des_alias")
    private String alias;

    @Column(name = "num_quantity")
    private String quantity;

    @Column(name = "ind_status")
    private String status;
    

    
    
    public SlotTestResponse() {
        super();
    }

    public SlotTestResponse( String id ) {
        super();
        this.id = id;
    }

    public SlotTestResponse(String id, String description, String alias, String quantity, String status ) {
        super();
        this.id = id;
        this.description = description;
        this.alias = alias;
        this.quantity = quantity;
        this.status = status;
    }
    
    public SlotTestResponse(String description, String alias, String quantity, String status ) {
        this.description = description;
        this.alias = alias;
        this.quantity = quantity;
        this.status = status;
    }    

    @Override
    public String getEntityRootUrl()
    {
        return ENTITY_ROOT_RADAR + "/slot";
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    
    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SlotTestResponse other = (SlotTestResponse) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        result.append( "Slot [" );
        result.append( "id=" );
        result.append( getId() == null ? "null" : getId() );
        result.append( ", " );
        result.append( "description=" );
        result.append( getDescription() == null ? "null" : getDescription() );
        result.append( ", " );
        result.append( "alias=" );
        result.append( getAlias() == null ? "null" : getAlias() );
        result.append( ", " );
        result.append( "quantity=" );
        result.append( getQuantity() == null ? "null" : getQuantity() );
        result.append( ", " );
        result.append( "status=" );
        result.append( getStatus() == null ? "null" : getStatus() );
        result.append( "]" );
        return result.toString();
    }
    
}

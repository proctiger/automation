package uol.collective.offer.commons.test.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import uol.collective.offer.commons.test.annotation.SequenceName;
import uol.collective.offer.commons.test.model.TestResponse;


@Entity
@Table(name = "cost_per_click")
@XmlRootElement(name = "cpc")
@XmlAccessorType(XmlAccessType.FIELD)
@SequenceName(name = "sq_cost_per_click_idt")
public class CPCTestResponse implements TestResponse
{


   
    @Id
    @Column(name = "IDT_COST_PER_CLICK")
    private String id;

    @Column(name = "NUM_CPC_VALUE")
    private String value;

    @XmlElement(name = "category")
    @ManyToOne
    @JoinColumn(name = "IDT_CATEGORY")
    private CategoryTestResponse category;

    @Column(name = "IDT_SITE")
    private String idtSite;


    
    public CPCTestResponse()
    {
        super();
    }

    public CPCTestResponse(String id)
    {
        super();
        this.id = id;
    }

    public CPCTestResponse(String id,
            String name,
            CategoryTestResponse category,
            String idtSite)
    {
        super();
        this.id = id;
        this.value = name;
        this.category = category;
        this.idtSite = idtSite;
    }

    @Override
    public String getEntityRootUrl()
    {
        return ENTITY_ROOT_RADAR + "/cpc";
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public CategoryTestResponse getCategory()
    {
        return category;
    }

    public void setCategory(CategoryTestResponse category)
    {
        this.category = category;
    }

    public String getIdtSite()
    {
        return idtSite;
    }

    public void setIdtSite(String idtSite)
    {
        this.idtSite = idtSite;
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
        CPCTestResponse other = (CPCTestResponse) obj;
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
        return "CPCTestResponse [id=" + id + ", value=" + value + ", category=" + category + ", idtSite=" + idtSite + "]";
    }



}

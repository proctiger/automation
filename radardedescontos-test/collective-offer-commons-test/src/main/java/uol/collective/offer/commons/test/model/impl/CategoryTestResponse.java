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
@Table(name = "category")
@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
@SequenceName(name = "sq_category_idt")
public class CategoryTestResponse implements TestResponse
{



    
    @Id
    @Column(name = "IDT_CATEGORY")
    private String id;

    @Column(name = "NAM_CATEGORY")
    private String name;

    @Column(name = "IND_STATUS")
    private String status;


    
    
    public CategoryTestResponse()
    {
        super();
    }

    public CategoryTestResponse(String id)
    {
        super();
        this.id = id;
    }

    public CategoryTestResponse(String id,
            String name,
            String status,
            String idtSite)
    {
        super();
        this.id = id;
        this.name = name;
        this.status = status;
    }

    @Override
    public String getEntityRootUrl()
    {
        return ENTITY_ROOT_RADAR + "/category";
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
        CategoryTestResponse other = (CategoryTestResponse) obj;
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
        return "CategoryTestResponse [id=" + id + ", name=" + name + ", status=" + status + "]";
    }


    
}

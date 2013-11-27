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
@Table(name = "click_image_roimeter")
@XmlRootElement(name = "click_image_roimeter")
@XmlAccessorType(XmlAccessType.FIELD)
@SequenceName(name = "sq_click_image_roimeter_idt")
public class ClickImageRoimeterTestResponse implements TestResponse
{

    @Id
    @Column(name = "idt_click_event")
    private String id;

    @Column(name = "des_click_image")
    private String clickImage;

    public ClickImageRoimeterTestResponse()
    {
        super();
    }

    public ClickImageRoimeterTestResponse(String id)
    {
        super();
        this.id = id;
    }

    public ClickImageRoimeterTestResponse(String id,
            String clickImage)
    {
        super();
        this.id = id;
        this.clickImage = clickImage;
    }

    @Override
    public String getEntityRootUrl()
    {
        return ENTITY_ROOT_RADAR + "/click_image_roimeter";
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getClickImage()
    {
        return clickImage;
    }

    public void setClickImage(String clickImage)
    {
        this.clickImage = clickImage;
    }

    @Override
    public String toString()
    {
        return "SiteTestResponse [id=" + id + ", clickImage=" + clickImage + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( clickImage == null ) ? 0 : clickImage.hashCode() );
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
        ClickImageRoimeterTestResponse other = (ClickImageRoimeterTestResponse) obj;
        if ( clickImage == null )
        {
            if ( other.clickImage != null )
                return false;
        } else if ( !clickImage.equals( other.clickImage ) )
            return false;
        if ( id == null )
        {
            if ( other.id != null )
                return false;
        } else if ( !id.equals( other.id ) )
            return false;
        return true;
    }

}

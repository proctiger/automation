package uol.collective.offer.commons.test.model.impl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import uol.collective.offer.commons.test.model.TestResponse;

@XmlRootElement(name = "dayAvailability")
@XmlAccessorType(XmlAccessType.FIELD)
public class DayAvailabilityTestResponse implements TestResponse
{

    @XmlElement
    private String day;

    @XmlElement(name = "is-day-free")
    private String isDayFree;

    public DayAvailabilityTestResponse()
    {
        super();
    }

    public DayAvailabilityTestResponse(String day,
            String isDayFree)
    {
        super();
        this.day = day;
        this.isDayFree = isDayFree;
    }

    public String getDay()
    {
        return day;
    }

    public void setDay(String day)
    {
        this.day = day;
    }

    public String getIsDayFree()
    {
        return isDayFree;
    }

    public void setIsDayFree(String isDayFree)
    {
        this.isDayFree = isDayFree;
    }

    @Override
    public String getEntityRootUrl()
    {
        return null;
    }

    @Override
    public String getId()
    {
        return day;
    }

    @Override
    public void setId(String id)
    {
        
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( day == null ) ? 0 : day.hashCode() );
        result = prime * result + ( ( isDayFree == null ) ? 0 : isDayFree.hashCode() );
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
        DayAvailabilityTestResponse other = (DayAvailabilityTestResponse) obj;
        if ( day == null )
        {
            if ( other.day != null )
                return false;
        } else if ( !day.equals( other.day ) )
            return false;
        if ( isDayFree == null )
        {
            if ( other.isDayFree != null )
                return false;
        } else if ( !isDayFree.equals( other.isDayFree ) )
            return false;
        return true;
    }

}

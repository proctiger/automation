package uol.collective.offer.commons.test.model.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "collection")
@XmlAccessorType(XmlAccessType.FIELD)
public class DaysAvailabilityTestResponse implements Serializable
{

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "dayAvailability")
    private Set<DayAvailabilityTestResponse> dayAvailabilityList;

    public Set<DayAvailabilityTestResponse> getDayAvailabilityList()
    {
        return dayAvailabilityList;
    }

    public void setDayAvailabilityList(Set<DayAvailabilityTestResponse> dayAvailabilityList)
    {
        this.dayAvailabilityList = dayAvailabilityList;
    }

    public void addDayAvailability(DayAvailabilityTestResponse dayAvailability)
    {
        if ( dayAvailabilityList == null )
        {
            this.dayAvailabilityList = new HashSet<DayAvailabilityTestResponse>();
        }
        this.dayAvailabilityList.add( dayAvailability );
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( dayAvailabilityList == null ) ? 0 : dayAvailabilityList.hashCode() );
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
        DaysAvailabilityTestResponse other = (DaysAvailabilityTestResponse) obj;
        if ( dayAvailabilityList == null )
        {
            if ( other.dayAvailabilityList != null )
                return false;
        } else if ( !dayAvailabilityList.equals( other.dayAvailabilityList ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "DaysAvailabilityTestResponse [dayAvailabilityList=" + dayAvailabilityList + "]";
    }
}

package uol.collective.offer.commons.test.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import uol.collective.offer.commons.test.model.ListTestResponse;
import uol.collective.offer.commons.test.model.TestResponse;

@SuppressWarnings({
        "rawtypes",
        "unchecked" })
@XmlRootElement(name = "collection")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({
        ErrorsTestResponse.class,
        ErrorTestResponse.class,
        ProposalTestResponse.class,
        SaleTestResponse.class,
        ScheduleTestResponse.class,
        SiteTestResponse.class,
        SlotTestResponse.class,
        DayAvailabilityTestResponse.class,
        ConfigurationTestResponse.class,
        OptOutHistoryTestResponse.class,
        CPCTestResponse.class })
public class ListTestResponseImpl<T> implements ListTestResponse<T>
{

    @XmlTransient
    private static final long serialVersionUID = 1L;

    @XmlElement
    private List<T> entities;

    @XmlTransient
    private Class<T> persistentClass;

    public ListTestResponseImpl()
    {
        super();
    }

    public ListTestResponseImpl(Class<T> persistentClass)
    {
        super();
        this.persistentClass = persistentClass;
    }

    public ListTestResponseImpl(List<T> entities,
            Class<T> persistentClass)
    {
        super();
        this.entities = entities;
        this.persistentClass = persistentClass;
    }

    @Override
    public List<T> getEntities()
    {
        this.entities = ( entities == null ? new ArrayList<T>() : this.entities );
        return this.entities;
    }

    public void addEntities(List<T> entities)
    {
        getEntities().addAll( entities );
    }

    public void addEntity(T entity)
    {
        getEntities().add( entity );
    }

    public int size()
    {
        return ( this.entities == null ? 0 : this.entities.size() );
    }

    public boolean isEmpty()
    {
        return ( this.entities == null ? true : this.entities.isEmpty() );
    }

    public Class<T> getPersistentClass()
    {
        return this.persistentClass;
    }

    @Override
    public String toString()
    {
        return "ListTestResponse [entities=" + entities + "]";
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( entities == null ) ? 0 : entities.hashCode() );
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
        ListTestResponseImpl other = (ListTestResponseImpl) obj;
        if ( entities == null )
        {
            if ( other.entities != null )
                return false;
        } else if ( other.entities == null )
        {
            return false;
        } else
        {
            Collections.sort( entities, ID_COMPARATOR );
            Collections.sort( other.entities, ID_COMPARATOR );
            return entities.equals( other.entities );
        }

        return true;
    }

    public static final Comparator ID_COMPARATOR = new Comparator()
    {

        @Override
        public int compare(Object a, Object b)
        {
            return ( (TestResponse) a ).getId()
                .compareTo( ( (TestResponse) b ).getId() );
        }
    };

}

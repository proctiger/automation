package uol.collective.offer.commons.test.model.impl;

import java.util.Date;

import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import uol.collective.offer.commons.test.annotation.XmlTypeDateTime;
import uol.collective.offer.commons.test.model.TestResponse;
import uol.collective.offer.commons.test.util.DateTimeUtil;

@XmlRootElement(name = "transaction")
@XmlAccessorType(XmlAccessType.FIELD)
public class TransactionTestResponse implements TestResponse
{

    @XmlElement
    private String codUniqueClick;

    @XmlTypeDateTime
    private String date;

    @XmlElement
    private String code;

    @XmlElement
    private String status;

    @XmlElement
    private String grossAmount;

    public TransactionTestResponse()
    {
        super();
    }

    public TransactionTestResponse(String codUniqueClick)
    {
        super();
        this.codUniqueClick = codUniqueClick;
    }

    public TransactionTestResponse(String codUniqueClick,
            String date,
            String code,
            String status,
            String grossAmount)
    {
        super();
        this.codUniqueClick = codUniqueClick;
        this.date = date;
        this.code = code;
        this.status = status;
        this.grossAmount = grossAmount;

    }

    @Override
    public String getEntityRootUrl()
    {
        return ENTITY_ROOT_PS;
    }

    public String getCodUniqueClick()
    {
        return codUniqueClick;
    }

    public void setCodUniqueClick(String codUniqueClick)
    {
        this.codUniqueClick = codUniqueClick;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getGrossAmount()
    {
        return grossAmount;
    }

    public void setGrossAmount(String grossAmount)
    {
        this.grossAmount = grossAmount;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( codUniqueClick == null ) ? 0 : codUniqueClick.hashCode() );
        result = prime * result + ( ( code == null ) ? 0 : code.hashCode() );
        result = prime * result + ( ( date == null ) ? 0 : date.hashCode() );
        result = prime * result + ( ( grossAmount == null ) ? 0 : grossAmount.hashCode() );
        result = prime * result + ( ( status == null ) ? 0 : status.hashCode() );
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
        TransactionTestResponse other = (TransactionTestResponse) obj;
        if ( codUniqueClick == null )
        {
            if ( other.codUniqueClick != null )
                return false;
        } else if ( !codUniqueClick.equals( other.codUniqueClick ) )
            return false;
        if ( code == null )
        {
            if ( other.code != null )
                return false;
        } else if ( !code.equals( other.code ) )
            return false;
        if ( date == null )
        {
            if ( other.date != null )
                return false;
        } else if ( !date.equals( other.date ) )
            return false;
        if ( grossAmount == null )
        {
            if ( other.grossAmount != null )
                return false;
        } else if ( !grossAmount.equals( other.grossAmount ) )
            return false;
        if ( status == null )
        {
            if ( other.status != null )
                return false;
        } else if ( !status.equals( other.status ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "TransactionTestResponse [codUniqueClick=" + codUniqueClick + ", date=" + date + ", code=" + code + ", status=" + status + ", grossAmount=" + grossAmount + "]";
    }

    public static TransactionTestResponse parseToXmlFormat(TransactionTestResponse trasaction)
    {
        if ( trasaction != null )
        {
            try
            {
                TransactionTestResponse newTrasaction = trasaction;
                Date date = DateTimeUtil.parseFromHibernateFormat( trasaction.getDate() );
                newTrasaction.setDate( DateTimeUtil.formatDateToStringXML( date ) );
                return newTrasaction;
            } catch ( Exception ex )
            {
            }
        }
        return null;
    }

    // O conceito de "id" para a transacao aqui nao eh o mesmo, o teste ira mockar a partir dos dados completos
    @Override
    @Transient
    public String getId()
    {
        return String.format( "?status=%s&codUniqueClick=%s&date=%s&transactionId=%s&grossAmount=%s", this.status, this.codUniqueClick, this.date, this.code, this.grossAmount );
    }

    @Override
    @Transient
    public void setId(String id)
    {
    }

}

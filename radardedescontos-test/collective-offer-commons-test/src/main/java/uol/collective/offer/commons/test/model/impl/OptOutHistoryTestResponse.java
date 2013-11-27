package uol.collective.offer.commons.test.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import uol.collective.offer.commons.test.annotation.SequenceName;
import uol.collective.offer.commons.test.annotation.XmlTypeDateTime;
import uol.collective.offer.commons.test.model.TestResponse;

@Entity
@Table(name = "opt_out_history")
@XmlRootElement(name = "optOutHistory")
@XmlAccessorType(XmlAccessType.FIELD)
@SequenceName(name = "sq_opt_out_history_idt", schema = "radar_adm")
public class OptOutHistoryTestResponse implements TestResponse
{

    @XmlElement
    @Id
    @Column(name = "idt_opt_out_history")
    private String id;

    @XmlTypeDateTime 
    @Column(name = "dat_opt_out")
    private String optOutDate;

    @Column(name = "des_email")
    private String email;

    @Column(name = "ind_reason")
    private String optOutReason;

    @Column(name = "des_other_reason")
    private String otherReason;

    public OptOutHistoryTestResponse()
    {
        super();
    }

    public OptOutHistoryTestResponse(String id)
    {
        super();
        this.id = id;
    }

    public OptOutHistoryTestResponse(String optOutDate,
            String email,
            String optOutReason,
            String otherReason)
    {
        super();
        this.optOutDate = optOutDate;
        this.email = email;
        this.optOutReason = optOutReason;
        this.otherReason = otherReason;
    }

    public OptOutHistoryTestResponse(String id,
            String optOutDate,
            String email,
            String optOutReason,
            String otherReason)
    {
        super();
        this.id = id;
        this.optOutDate = optOutDate;
        this.email = email;
        this.optOutReason = optOutReason;
        this.otherReason = otherReason;
    }

    @Override
    public String getEntityRootUrl()
    {
        return ENTITY_ROOT_RADAR + "/optOutHistory";
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getOptOutDate()
    {
        return optOutDate;
    }

    public void setOptOutDate(String optOutDate)
    {
        this.optOutDate = optOutDate;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getOptOutReason()
    {
        return optOutReason;
    }

    public void setOptOutReason(String optOutReason)
    {
        this.optOutReason = optOutReason;
    }

    public String getOtherReason()
    {
        return otherReason;
    }

    public void setOtherReason(String otherReason)
    {
        this.otherReason = otherReason;
    }

    @Override
    public String toString()
    {
        return "OptOutHistoryTestResponse [id=" + id + ", optOutDate=" + optOutDate + ", email=" + email + ", optOutReason=" + optOutReason + ", otherReason=" + otherReason + "]";
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
        OptOutHistoryTestResponse other = (OptOutHistoryTestResponse) obj;
        if ( id == null )
        {
            if ( other.id != null )
                return false;
        } else if ( !id.equals( other.id ) )
            return false;
        return true;
    }
}
package uol.collective.offer.commons.test.model.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.time.DateUtils;

import uol.collective.offer.commons.test.annotation.SequenceName;
import uol.collective.offer.commons.test.annotation.XmlTypeDateTime;
import uol.collective.offer.commons.test.model.TestResponse;
import uol.collective.offer.commons.test.util.DateTimeUtil;
import uol.collective.offer.commons.test.xml.adapter.BooleanAdapter;


@Entity
@Table(name = "sale")
@XmlRootElement(name = "sale")
@XmlAccessorType(XmlAccessType.FIELD)
@SequenceName( name = "sq_sale_idt", schema = "radar_adm")
public class SaleTestResponse implements TestResponse
{

    @XmlElement
    @Id
    @Column(name = "idt_sale")
    private String id;

    @XmlElement(name = "proposal")
    @ManyToOne
    @JoinColumn(name = "idt_proposal")
    private ProposalTestResponse proposal;

    @Transient
    private String proposalId;

    @XmlTypeDateTime
    @Column(name = "dat_start")
    private String startDate;

    @XmlTypeDateTime
    @Column(name = "dat_end")
    private String endDate;

    @XmlTypeDateTime
    @Column(name = "dat_creation")
    private String creationDate;

    @XmlElement(name = "slot")
    @ManyToOne
    @JoinColumn(name = "idt_slot")
    private SlotTestResponse slot;

    @Transient
    private String slotId;

    @Column(name = "ind_status")
    private String status;

    @XmlElement(defaultValue = "false")
    @XmlJavaTypeAdapter(value = BooleanAdapter.class)
    @Column(name = "flg_bonus")
    private String bonus;

    @XmlTypeDateTime
    @Column(name = "dat_cancel")
    private String cancelDate;

    @XmlTransient
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "sale")
    private List<ScheduleTestResponse> schedules;

    public SaleTestResponse()
    {
        super();
    }

    public SaleTestResponse( String id )
    {
        super();
        this.id = id;
    }

    public SaleTestResponse(String id,
            ProposalTestResponse proposal,
            String startDate,
            String endDate,
            SlotTestResponse slot,
            String status)
    {
        super();
        this.id = id;
        this.proposal = proposal;
        this.proposalId = ( proposal == null ? null : proposal.getId() );
        this.startDate = startDate;
        this.endDate = endDate;
        this.slot = slot;
        this.slotId = ( slot == null ? null : slot.getId() );
        this.status = status;
        Date creationDate = DateUtils.truncate( new Date(), Calendar.DATE );
        this.creationDate = DateTimeUtil.formatDateToStringHibernate( creationDate );
        this.bonus = "0";

    }    
    
    public SaleTestResponse(String id,
            ProposalTestResponse proposal,
            String startDate,
            String endDate,
            SlotTestResponse slot,
            String status,
            String bonus)
    {
        super();
        this.id = id;
        this.proposal = proposal;
        this.proposalId = ( proposal == null ? null : proposal.getId() );
        this.startDate = startDate;
        this.endDate = endDate;
        this.slot = slot;
        this.slotId = ( slot == null ? null : slot.getId() );
        this.status = status;
        Date creationDate = DateUtils.truncate( new Date(), Calendar.DATE );
        this.creationDate = DateTimeUtil.formatDateToStringHibernate( creationDate );
        this.bonus = bonus;

    }

    @Override
    public String getEntityRootUrl()
    {
        return ENTITY_ROOT_RADAR + "/sale";
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public ProposalTestResponse getProposal()
    {
        return proposal;
    }

    public void setProposal(ProposalTestResponse proposal)
    {
        this.proposalId = ( proposal == null ? null : proposal.getId() );
        this.proposal = proposal;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public String getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(String creationDate)
    {
        this.creationDate = creationDate;
    }

    public SlotTestResponse getSlot()
    {
        return slot;
    }

    public void setSlot(SlotTestResponse slot)
    {
        this.slotId = ( slot == null ? null : slot.getId() );
        this.slot = slot;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getBonus()
    {
        return bonus;
    }

    public void setBonus(String bonus)
    {
        this.bonus = bonus;
    }

    public String getCancelDate()
    {
        return cancelDate;
    }

    public void setCancelDate(String cancelDate)
    {
        this.cancelDate = cancelDate;
    }

    public String getProposalId()
    {
        return proposalId;
    }

    public void setProposalId(String proposalId)
    {
        this.proposalId = proposalId;
    }

    public String getSlotId()
    {
        return slotId;
    }

    public void setSlotId(String slotId)
    {
        this.slotId = slotId;
    }

    public List<ScheduleTestResponse> getSchedules()
    {
        return schedules;
    }

    public void setSchedules(List<ScheduleTestResponse> schedules)
    {
        this.schedules = schedules;
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
        SaleTestResponse other = (SaleTestResponse) obj;
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
        return "SaleTestResponse [id=" + id + ", proposal=" + proposal + ", startDate=" + startDate + ", endDate=" + endDate + ", creationDate=" + creationDate + ", slot=" + slot + ", status=" + status + ", bonus=" + bonus + ", cancelDate=" + cancelDate + "]";
    }

    public static SaleTestResponse parseToXmlFormat(SaleTestResponse sale)
    {
        if ( sale != null )
        {
            try
            {
                SaleTestResponse newSale = sale;
                Date sDate = DateTimeUtil.parseFromHibernateFormat( sale.getStartDate() );
                Date eDate = DateTimeUtil.parseFromHibernateFormat( sale.getEndDate() );
                newSale.setStartDate( DateTimeUtil.formatDateToStringXML( sDate ) );
                newSale.setEndDate( DateTimeUtil.formatDateToStringXML( eDate ) );
                Date creationDate = DateUtils.truncate( new Date(), Calendar.DATE );
                newSale.setCreationDate( DateTimeUtil.formatDateToStringXML( creationDate ) );
                newSale.setBonus( "false" );
                return newSale;
            } catch ( Exception ex )
            {
            }
        }
        return null;
    }

}

package uol.collective.offer.commons.test.model.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import uol.collective.offer.commons.test.annotation.SequenceName;
import uol.collective.offer.commons.test.model.TestResponse;
import uol.collective.offer.commons.test.util.DateTimeUtil;


@Entity
@Table(name = "schedule")
@XmlRootElement(name = "schedule")
@XmlAccessorType(XmlAccessType.FIELD)
@SequenceName(name = "sq_schedule_idt", schema = "radar_adm")
public class ScheduleTestResponse implements TestResponse
{

    @XmlElement
    @Id
    @Column(name = "idt_schedule")
    private String id;

    @XmlElement(name = "sale")
    @ManyToOne
    @JoinColumn(name = "idt_sale")
    private SaleTestResponse sale;

    @Transient
    private String saleId;

    @Column(name = "dat_display")
    private String displayDate;

    @Column(name = "dat_creation")
    private String creationDate;

    @Column(name = "ind_status")
    private String status;

    @Column(name = "des_comment")
    private String comment;

    @Column(name = "dat_cancel")
    private String cancelDate;

    public ScheduleTestResponse()
    {
        super();
    }

    public ScheduleTestResponse(String id, 
            SaleTestResponse sale,
            String displayDate,
            String status)
    {
        this( sale, displayDate, status );
        this.id = id;
    }
    
    public ScheduleTestResponse(SaleTestResponse sale,
            String displayDate,
            String status)
    {
        super();
        this.sale = sale;
        this.saleId = ( sale != null && sale.getId() != null ? sale.getId()
            .toString() : null );
        this.displayDate = displayDate;
        this.status = status;
        this.creationDate = DateTimeUtil.formatDateToStringHibernate( new Date() );
    }

    @Override
    public String getEntityRootUrl()
    {
        return ENTITY_ROOT_RADAR + "/schedule";
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getDisplayDate()
    {
        return displayDate;
    }

    public void setDisplayDate(String displayDate)
    {
        this.displayDate = displayDate;
    }

    public String getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(String creationDate)
    {
        this.creationDate = creationDate;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public String getCancelDate()
    {
        return cancelDate;
    }

    public void setCancelDate(String cancelDate)
    {
        this.cancelDate = cancelDate;
    }

    public SaleTestResponse getSale()
    {
        return sale;
    }

    public void setSale(SaleTestResponse sale)
    {
        this.sale = sale;
    }
    
    public String getSaleId()
    {
        return saleId;
    }

    public void setSaleId(String saleId)
    {
        this.saleId = saleId;
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
        ScheduleTestResponse other = (ScheduleTestResponse) obj;
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
        return "ScheduleTestResponse [id=" + id + ", sale=" + sale + ", displayDate=" + displayDate + ", creationDate=" + creationDate + ", status=" + status + ", comment=" + comment + ", cancelDate=" + cancelDate + "]";
    }

    public static List<ScheduleTestResponse> valueOf(SaleTestResponse sale, String status) throws Exception
    {
        if ( sale != null )
        {
            List<ScheduleTestResponse> schedules = new ArrayList<ScheduleTestResponse>();
            ScheduleTestResponse sc = null;
            int days = DateTimeUtil.getDifferenceBetweenTwoDatesInclusive( DateTimeUtil.parseFromHibernateFormat( sale.getStartDate() ), DateTimeUtil.parseFromHibernateFormat( sale.getEndDate() ) );
            Calendar currentDate = Calendar.getInstance();
            currentDate.setTime( DateTimeUtil.parseFromHibernateFormat( sale.getStartDate() ) );

            for ( int i = 1; i <= days; i++ )
            {
                sc = new ScheduleTestResponse( sale, DateTimeUtil.formatDateToStringHibernate( currentDate.getTime() ), status );
                schedules.add( sc );
                currentDate.add( Calendar.DATE, +1 );
            }
            return schedules;
        }
        return null;
    }

}

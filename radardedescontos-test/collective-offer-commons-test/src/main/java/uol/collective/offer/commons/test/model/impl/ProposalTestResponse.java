package uol.collective.offer.commons.test.model.impl;

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
import uol.collective.offer.commons.test.annotation.XmlTypeBigDecimal;
import uol.collective.offer.commons.test.annotation.XmlTypeDateTime;
import uol.collective.offer.commons.test.model.TestResponse;

@SuppressWarnings("unused")
@Entity
@Table(name = "proposal")
@XmlRootElement(name = "proposal")
@XmlAccessorType(XmlAccessType.FIELD)
@SequenceName(name = "sq_proposal_idt", schema = "radar_adm")
public class ProposalTestResponse implements TestResponse
{

    @XmlElement
    @Id
    @Column(name = "idt_proposal")
    private String id;

    @XmlElement(name = "site")
    @ManyToOne
    @JoinColumn(name = "idt_site")
    private SiteTestResponse site;

    @Transient
    @XmlElement(name = "siteId")
    private String siteId;

    @XmlElement
    @XmlTypeBigDecimal
    @Column(name = "num_total_value")
    private String value;

    @XmlElement
    @XmlTypeDateTime
    @Column(name = "dat_expiration")
    private String expirationDate;

    @XmlElement
    @XmlTypeDateTime
    @Column(name = "dat_creation")
    private String creationDate;

    @Column(name = "des_comment")
    private String comment;

    // TODO ATUALIZAR OS TESTES DE PROPOSAL PARA OS NOVOS CAMPOS

    public ProposalTestResponse()
    {
        super();
    }

    public ProposalTestResponse(String id,
            SiteTestResponse siteTestResponse,
            String value,
            String expirationDate,
            String creationDate)
    {
        super();
        this.id = id;
        this.setSite( siteTestResponse );
        this.value = value;
        this.expirationDate = expirationDate;
        this.creationDate = creationDate;
    }

    public ProposalTestResponse(String id)
    {
        super();
        this.id = id;
    }

    @Override
    public String getEntityRootUrl()
    {
        return ENTITY_ROOT_RADAR + "/proposal";
    }

    @Override
    public String getId()
    {
        return id;
    }

    @Override
    public void setId(String id)
    {
        this.id = id;
    }

    public SiteTestResponse getSite()
    {
        this.setSiteId( site == null ? null : site.getId() );
        return site;
    }

    public void setSite(SiteTestResponse site)
    {
        this.setSiteId( site == null ? null : site.getId() );
        this.site = site;
    }

    public String getSiteId()
    {
        return siteId;
    }

    public void setSiteId(String siteId)
    {
        this.siteId = siteId;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public String getExpirationDate()
    {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate)
    {
        this.expirationDate = expirationDate;
    }

    public String getCreationDate()
    {
        return creationDate;
    }

    public void setCreationDate(String creationDate)
    {
        this.creationDate = creationDate;
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
        ProposalTestResponse other = (ProposalTestResponse) obj;
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
        return "ProposalTestResponse [id=" + id + ", siteTestResponse=" + site + ", siteId=" + siteId + ", value=" + value + ", expirationDate=" + expirationDate + ", creationDate=" + creationDate + "]";
    }

}

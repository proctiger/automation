package uol.collective.offer.commons.test.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import uol.collective.offer.commons.test.annotation.SequenceName;
import uol.collective.offer.commons.test.model.TestResponse;

@Entity
@Table(name = "offer")
@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
@SequenceName(name = "sq_offer_idt")
public class OfferTestResponse implements TestResponse
{

    @Id
    @Column(name = "idt_offer")
    private String id;

    @Column(name = "nam_offer")
    private String name;

    @Column(name = "ind_status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "idt_site")
    private SiteTestResponse site;

    @Column(name = "des_offer_url")
    private String url;

    @Column(name = "num_regular_price")
    private String regularPrice;

    @Column(name = "num_effective_price")
    private String effectivePrice;

    @Column(name = "dat_subscription")
    private String subscriptionDate;

    @Column(name = "dat_expiration")
    private String expirationDate;

    @Column(name = "flg_premium")
    private String premium;

    @Column(name = "flg_vip")
    private String vip;

    @Column(name = "dat_creation")
    private String creationDate;

    public OfferTestResponse()
    {
        super();
    }

    public OfferTestResponse(String id)
    {
        super();
        this.id = id;
    }

    public OfferTestResponse(String name,
            String status,
            SiteTestResponse site,
            String url,
            String regularPrice,
            String effectivePrice,
            String subscriptionDate,
            String expirationDate,
            String premium,
            String vip,
            String creationDate)
    {
        super();
        this.name = name;
        this.status = status;
        this.site = site;
        this.url = url;
        this.regularPrice = regularPrice;
        this.effectivePrice = effectivePrice;
        this.subscriptionDate = subscriptionDate;
        this.expirationDate = expirationDate;
        this.premium = premium;
        this.vip = vip;
        this.creationDate = creationDate;
    }

    @Override
    public String getEntityRootUrl()
    {
        return ENTITY_ROOT_RADAR + "/offer";
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

    public SiteTestResponse getSite()
    {
        return site;
    }

    public void setSite(SiteTestResponse site)
    {
        this.site = site;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getRegularPrice()
    {
        return regularPrice;
    }

    public void setRegularPrice(String regularPrice)
    {
        this.regularPrice = regularPrice;
    }

    public String getEffectivePrice()
    {
        return effectivePrice;
    }

    public void setEffectivePrice(String effectivePrice)
    {
        this.effectivePrice = effectivePrice;
    }

    public String getSubscriptionDate()
    {
        return subscriptionDate;
    }

    public void setSubscriptionDate(String subscriptionDate)
    {
        this.subscriptionDate = subscriptionDate;
    }

    public String getExpirationDate()
    {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate)
    {
        this.expirationDate = expirationDate;
    }

    public String getPremium()
    {
        return premium;
    }

    public void setPremium(String premium)
    {
        this.premium = premium;
    }

    public String getVip()
    {
        return vip;
    }

    public void setVip(String vip)
    {
        this.vip = vip;
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
    public String toString()
    {
        return "OfferTestResponse [id=" + id + ", name=" + name + ", status=" + status + ", site=" + site + ", url=" + url + ", regularPrice=" + regularPrice + ", effectivePrice=" + effectivePrice + ", subscriptionDate=" + subscriptionDate + ", expirationDate=" + expirationDate + ", premium=" + premium + ", vip=" + vip + ", creationDate=" + creationDate + "]";
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
        OfferTestResponse other = (OfferTestResponse) obj;
        if ( id == null )
        {
            if ( other.id != null )
                return false;
        } else if ( !id.equals( other.id ) )
            return false;
        return true;
    }
}
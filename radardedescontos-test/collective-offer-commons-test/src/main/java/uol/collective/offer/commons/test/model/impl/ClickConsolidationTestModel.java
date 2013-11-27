package uol.collective.offer.commons.test.model.impl;

import java.util.Date;

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
import uol.collective.offer.commons.test.annotation.XmlTypeDateTime;
import uol.collective.offer.commons.test.model.TestEntity;
import uol.collective.offer.commons.test.util.DateTimeUtil;

@Entity
@Table(name = "click_consolidation")
@XmlRootElement(name = "clickConsolidation")
@XmlAccessorType(XmlAccessType.FIELD)
@SequenceName(name = "sq_click_consolidation_idt", schema = "radar_adm")
public class ClickConsolidationTestModel implements TestEntity
{

    @Id
    @Column(name = "idt_click_consolidation")
    private String id;

    @ManyToOne
    @JoinColumn(name = "idt_offer")
    private OfferTestResponse offer;

    @Column(name = "dat_consolidation")
    @XmlTypeDateTime
    private String consolidationDate;

    @ManyToOne
    @JoinColumn(name = "idt_site")
    private SiteTestResponse site;

    @Column(name = "num_clicks")
    private String clickCount;

    @Column(name = "num_payment_value")
    private String transactionValueAmount;

    @Column(name = "num_transaction_total")
    private String numTransactions;

    public ClickConsolidationTestModel(OfferTestResponse offer,
            SiteTestResponse site,
            String clickCount)
    {
        super();
        this.offer = offer;
        this.site = site;
        this.clickCount = clickCount;
        this.consolidationDate = DateTimeUtil.formatDateToStringHibernate( new Date() );
        this.numTransactions = "0";
        this.transactionValueAmount = "0";
    }

    public ClickConsolidationTestModel()
    {
        super();
    }

    public ClickConsolidationTestModel(String id)
    {
        super();
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getConsolidationDate()
    {
        return consolidationDate;
    }

    public void setConsolidationDate(String consolidationDate)
    {
        this.consolidationDate = consolidationDate;
    }

    public String getClickCount()
    {
        return clickCount;
    }

    public void setClickCount(String clickCount)
    {
        this.clickCount = clickCount;
    }

    public OfferTestResponse getOffer()
    {
        return offer;
    }

    public void setOffer(OfferTestResponse offer)
    {
        this.offer = offer;
    }

    public SiteTestResponse getSite()
    {
        return site;
    }

    public void setSite(SiteTestResponse site)
    {
        this.site = site;
    }

    public String getTransactionValueAmount()
    {
        return transactionValueAmount;
    }

    public void setTransactionValueAmount(String transactionValueAmount)
    {
        this.transactionValueAmount = transactionValueAmount;
    }

    public String getNumTransactions()
    {
        return numTransactions;
    }

    public void setNumTransactions(String numTransactions)
    {
        this.numTransactions = numTransactions;
    }

    @Override
    public String toString()
    {
        return "ClickConsolidationTestModel [id=" + id + ", offer=" + offer + ", consolidationDate=" + consolidationDate + ", site=" + site + ", clickCount=" + clickCount + ", transactionValueAmount=" + transactionValueAmount + ", numTransactions=" + numTransactions + "]";
    }
}
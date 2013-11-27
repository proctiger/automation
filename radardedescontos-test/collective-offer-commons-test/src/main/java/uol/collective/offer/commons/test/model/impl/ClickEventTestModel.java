package uol.collective.offer.commons.test.model.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import uol.collective.offer.commons.test.annotation.SequenceName;
import uol.collective.offer.commons.test.annotation.XmlTypeBigDecimal;
import uol.collective.offer.commons.test.annotation.XmlTypeDateTime;
import uol.collective.offer.commons.test.model.TestEntity;
import uol.collective.offer.commons.test.util.DateTimeUtil;

@Entity
@Table(name = "click_event")
@XmlRootElement(name = "clickEvent")
@XmlAccessorType(XmlAccessType.FIELD)
@SequenceName(name = "sq_clickevent_idt", schema = "radar_adm")
public class ClickEventTestModel implements TestEntity
{

    @Id
    @Column(name = "idt_click_event")
    private String id;

    @ManyToOne
    @JoinColumn(name = "idt_offer")
    private OfferTestResponse offer;

    @Column(name = "num_offer_position")
    private String position;

    @ManyToOne
    @JoinColumn(name = "idt_city")
    private CityTestResponse city;

    @Column(name = "des_ip_origem")
    private String ip;

    @Column(name = "dat_click_event")
    @XmlTypeDateTime
    private String clickDate;

    @Column(name = "cod_status")
    private String status;

    @Column(name = "dat_update")
    @XmlTypeDateTime
    private String updateDate;

    @Column(name = "num_click_cost_value")
    @XmlTypeBigDecimal
    private String cpc;

    @Column(name = "num_balance_charge_value")
    @XmlTypeBigDecimal
    private String balanceChargeValue;

    @Column(name = "num_bonus_charge_value")
    @XmlTypeBigDecimal
    private String bonusChargeValue;

    @Column(name = "num_loss_value")
    @XmlTypeBigDecimal
    private String lossValue;

    @Column(name = "cod_platform")
    private String platform;

    @Column(name = "cod_unique_click")
    private String uniqueClick;

    @Column(name = "ind_fraud_type")
    private String fraudType;

    @Transient
    private String impressionId;

    public ClickEventTestModel()
    {
        super();
    }

    public ClickEventTestModel(String id)
    {
        super();
        this.id = id;
    }

    public ClickEventTestModel(OfferTestResponse offer,
            CityTestResponse city,
            String uniqueClick)
    {
        super();
        this.offer = offer;
        this.city = city;
        this.uniqueClick = uniqueClick;
        this.position = "0";
        this.ip = "127.0.0.1";
        this.clickDate = DateTimeUtil.formatDateToStringHibernate( new Date() );
        this.status = "P";
        this.updateDate = DateTimeUtil.formatDateToStringHibernate( new Date() );
        this.cpc = "0";
        this.balanceChargeValue = "0";
        this.bonusChargeValue = "0";
        this.lossValue = "0";
        this.platform = "W";
        this.fraudType = "0";
    }

    public ClickEventTestModel(OfferTestResponse offer,
            String position,
            CityTestResponse city,
            String ip,
            String clickDate,
            String status,
            String updateDate,
            String cpc,
            String balanceChargeValue,
            String bonusChargeValue,
            String lossValue,
            String platform,
            String impressionId,
            String fraudType)
    {
        super();
        this.offer = offer;
        this.position = position;
        this.city = city;
        this.ip = ip;
        this.clickDate = clickDate;
        this.status = status;
        this.updateDate = updateDate;
        this.cpc = cpc;
        this.balanceChargeValue = balanceChargeValue;
        this.bonusChargeValue = bonusChargeValue;
        this.lossValue = lossValue;
        this.platform = platform;
        this.impressionId = impressionId;
        this.fraudType = fraudType;
    }

    public ClickEventTestModel(OfferTestResponse offer,
            String position,
            CityTestResponse city,
            String status,
            String ip,
            String cpc,
            String balanceChargeValue,
            String bonusChargeValue,
            String lossValue,
            String platform,
            String impressionId,
            String fraudType)
    {
        super();
        this.offer = offer;
        this.position = position;
        this.city = city;
        this.status = status;
        this.ip = ip;
        this.cpc = cpc;
        this.balanceChargeValue = balanceChargeValue;
        this.bonusChargeValue = bonusChargeValue;
        this.lossValue = lossValue;
        this.platform = platform;
        this.impressionId = impressionId;
        this.fraudType = fraudType;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }

    public String getClickDate()
    {
        return clickDate;
    }

    public void setClickDate(String clickDate)
    {
        this.clickDate = clickDate;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getUpdateDate()
    {
        return updateDate;
    }

    public void setUpdateDate(String updateDate)
    {
        this.updateDate = updateDate;
    }

    public String getCpc()
    {
        return cpc;
    }

    public void setCpc(String cpc)
    {
        this.cpc = cpc;
    }

    public String getBalanceChargeValue()
    {
        return balanceChargeValue;
    }

    public void setBalanceChargeValue(String balanceChargeValue)
    {
        this.balanceChargeValue = balanceChargeValue;
    }

    public String getBonusChargeValue()
    {
        return bonusChargeValue;
    }

    public void setBonusChargeValue(String bonusChargeValue)
    {
        this.bonusChargeValue = bonusChargeValue;
    }

    public String getLossValue()
    {
        return lossValue;
    }

    public void setLossValue(String lossValue)
    {
        this.lossValue = lossValue;
    }

    public String getPlatform()
    {
        return platform;
    }

    public void setPlatform(String platform)
    {
        this.platform = platform;
    }

    public OfferTestResponse getOffer()
    {
        return offer;
    }

    public void setOffer(OfferTestResponse offer)
    {
        this.offer = offer;
    }

    public CityTestResponse getCity()
    {
        return city;
    }

    public void setCity(CityTestResponse city)
    {
        this.city = city;
    }

    public String getUniqueClick()
    {
        return uniqueClick;
    }

    public void setUniqueClick(String uniqueClick)
    {
        this.uniqueClick = uniqueClick;
    }

    public String getImpressionId()
    {
        return impressionId;
    }

    public void setImpressionId(String impressionId)
    {
        this.impressionId = impressionId;
    }

    public String getFraudType()
    {
        return fraudType;
    }

    public void setFraudType(String fraudType)
    {
        this.fraudType = fraudType;
    }

    @Override
    public String toString()
    {
        return "ClickEventTestModel [id=" + id + ", offer=" + offer + ", position=" + position + ", city=" + city + ", ip=" + ip + ", clickDate=" + clickDate + ", status=" + status + ", updateDate=" + updateDate + ", cpc=" + cpc + ", balanceChargeValue=" + balanceChargeValue + ", bonusChargeValue=" + bonusChargeValue + ", lossValue=" + lossValue + ", platform=" + platform + ", uniqueClick=" + uniqueClick + ", fraudType=" + fraudType + ", impressionId=" + impressionId + "]";
    }
}
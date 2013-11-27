package uol.collective.offer.commons.test.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import uol.collective.offer.commons.test.model.TestResponse;

@Entity
@Table(name = "transaction_event")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TransactionEventTestModel implements TestResponse
{

    @Id
    @Column(name = "cod_transaction_safepay")
    private String id;

    @Column(name = "num_payment_value")
    private String paymentValue;

    @Column(name = "num_commission_value")
    private String commissionValue;

    @Column(name = "idt_click_event")
    private String clickEvent;

    @Column(name = "num_sold_item")
    private String soldItems;

    @Column(name = "dat_transaction")
    private String date;

    @Column(name = "ind_payment_method")
    private String paymentMethod;

    public TransactionEventTestModel()
    {
        super();
    }

    public TransactionEventTestModel(String paymentValue,
            String commissionValue,
            String clickEvent,
            String soldItems,
            String date,
            String paymentMethod)
    {
        super();
        this.paymentValue = paymentValue;
        this.commissionValue = commissionValue;
        this.clickEvent = clickEvent;
        this.soldItems = soldItems;
        this.date = date;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String getEntityRootUrl()
    {
        return null;
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

    public String getPaymentValue()
    {
        return paymentValue;
    }

    public void setPaymentValue(String paymentValue)
    {
        this.paymentValue = paymentValue;
    }

    public String getCommissionValue()
    {
        return commissionValue;
    }

    public void setCommissionValue(String commissionValue)
    {
        this.commissionValue = commissionValue;
    }

    public String getClickEvent()
    {
        return commissionValue;
    }

    public void setClickEvent(String clickEvent)
    {
        this.clickEvent = clickEvent;
    }

    public String getSoldItems()
    {
        return soldItems;
    }

    public void setSoldItems(String soldItems)
    {
        this.soldItems = soldItems;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getPaymentMethod()
    {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod)
    {
        this.paymentMethod = paymentMethod;
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
        TransactionEventTestModel other = (TransactionEventTestModel) obj;
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
        return "TransactionEventTestResponse [id=" + id + ", paymentValue=" + paymentValue + ", commissionValue=" + commissionValue + ", clickEvent=" + clickEvent + ", soldItems=" + soldItems + ", date=" + date + ", paymentMethod=" + paymentMethod + "]";
    }

}

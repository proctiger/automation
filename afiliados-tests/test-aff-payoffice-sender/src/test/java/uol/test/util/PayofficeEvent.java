package uol.test.util;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import uol.ecommerce.commons.xml.Serializer;

import com.google.common.base.Charsets;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author rsilva
 * @version 1.0
 * Atualizado: Priscila Henriques
 */
@XmlRootElement(name = "event")
public class PayofficeEvent 
{

	private Long idtPerson;

	private Long idtEventLog;

	private Long idtInscriptionAccount;

	private String codCenterCost;

	private Date datEvent;

	private BigDecimal numValue;


	private PayofficeEvent() {}

	public static PayofficeEvent from(String payload) throws JAXBException
	{
		System.out.println("================ Valores de saída =================" + payload);

		PayofficeEvent subscriptionEvent = new GsonBuilder().setDateFormat("yyyy-MM-dd").create().fromJson(payload, PayofficeEvent.class);

		return subscriptionEvent;
	}

	public Long getIdtPerson() 
	{
		return idtPerson;
	}

	public void setIdtPerson(Long idtPerson) {
		this.idtPerson = idtPerson;
	}

	public Long getIdtInscriptionAccount() {
		return idtInscriptionAccount;
	}

	public void setIdtInscriptionAccount(Long idtInscriptionAccount)
	{
		this.idtInscriptionAccount = idtInscriptionAccount;
	}  
	
	@XmlElement(name = "idtEvent")
	public Long getIdtEventLog() {
		return idtEventLog;
	}

	public void setIdtEventLog(Long idtEventLog) {
		this.idtEventLog = idtEventLog;
	}

	public String getCodCenterCost() {
		return codCenterCost;
	}

	public void setCodCenterCost(String codCenterCost) {
		this.codCenterCost = codCenterCost;
	}

	public Date getDatEvent() {
		return datEvent;
	}

	@XmlElement(name = "datEvent")
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
	public void setDatEvent(Date datEvent) {
		this.datEvent = datEvent;
	}

	public BigDecimal getNumValue() {
		return numValue;
	}

	public void setNumValue(BigDecimal numValue) {
		this.numValue = numValue;
	}

	@XmlJavaTypeAdapter(EventTypeAdapter.class)
	public EventType getEventType() 
	{
		return EventType.PAYMENT;
	}

	public String toXml() throws JAXBException 
	{

		return Serializer.toXml(this, Charsets.UTF_8.displayName());
	}

	@Override
	public String toString() 
	{
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("idtEvent", getIdtEventLog())
				.append("idtPerson", getIdtPerson())
				.append("idtInscriptionAccount", getIdtInscriptionAccount())
				.append("numvalue", getNumValue())
				.append("eventType", getEventType())
				.append("datEvent", getDatEvent())
				.append("codCenterCost", getCodCenterCost())
				.toString();
	}
}
package uol.affiliated.commons.test.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author tsantos
 * 
 */

public class AeEventLog {
	
	private long idtEventLog;
    private String codTransaction;
    private String codAffiliated;
    private String desName;
    private long idtProductSource;
    private Date datEvent;
    private Date datInsert;
    private long numEventAmount;
    private BigDecimal numValue;
    private String groupings;
    private long eventStatus;
    private BigDecimal numCommissionValue;
    
    
    public long getIdtEventLog() {
		return idtEventLog;
	}
	public void setIdtEventLog(long idtEventLog) {
		this.idtEventLog = idtEventLog;
	}
	
	public String getCodTransaction() {
		return codTransaction;
	}
	public void setCodTransaction(String codTransaction) {
		this.codTransaction = codTransaction;
	}
	public String getCodAffiliated() {
		return codAffiliated;
	}
	public void setCodAffiliated(String codAffiliated) {
		this.codAffiliated = codAffiliated;
	}
	public String getDesName() {
		return desName;
	}
	public void setDesName(String desName) {
		this.desName = desName;
	}
	public long getIdtProductSource() {
		return idtProductSource;
	}
	public void setIdtProductSource(long idtProductSource) {
		this.idtProductSource = idtProductSource;
	}
	public Date getDatEvent() {
		return datEvent;
	}
	public void setDatEvent(Date datEvent) {
		this.datEvent = datEvent;
	}
	public Date getDatInsert() {
		return datInsert;
	}
	public void setDatInsert(Date datInsert) {
		this.datInsert = datInsert;
	}
	public long getNumEventAmount() {
		return numEventAmount;
	}
	public void setNumEventAmount(long numEventAmount) {
		this.numEventAmount = numEventAmount;
	}
	public BigDecimal getNumValue() {
		return numValue;
	}
	public void setNumValue(BigDecimal numValue) {
		this.numValue = numValue;
	}
	public String getGroupings() {
		return groupings;
	}
	public void setGroupings(String groupings) {
		this.groupings = groupings;
	}
	public long getEventStatus() {
		return eventStatus;
	}
	public void setEventStatus(long eventStatus) {
		this.eventStatus = eventStatus;
	}
	public BigDecimal getNumCommissionValue() {
		return numCommissionValue;
	}
	public void setNumCommissionValue(BigDecimal numCommissionValue) {
		this.numCommissionValue = numCommissionValue;
	}
	

}

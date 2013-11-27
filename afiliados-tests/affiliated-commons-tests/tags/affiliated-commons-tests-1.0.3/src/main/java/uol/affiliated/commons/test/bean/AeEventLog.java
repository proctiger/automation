package uol.affiliated.commons.test.bean;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author tsantos
 * 
 */

public class AeEventLog {
	
    private Long idtEventLog;
    private String codTransaction;
    private String codAffiliated;
    private String desName;
    private Long idtProductSource;
    private Date datEvent;
    private Date datInsert;
    private Long numEventAmount;
    private BigDecimal numValue;
    private String groupings;
    private Long eventStatus;
    private BigDecimal numCommissionValue;
    
    public AeEventLog() {};
    
    public AeEventLog(Long idtProductSource,String codAffiliated,String desName,Long numEventAmount,BigDecimal numValue,String groupings) {
        this.idtProductSource = idtProductSource;
        this.codAffiliated = codAffiliated;
        this.desName = desName;
        this.numEventAmount = numEventAmount;
        this.numValue = numValue;
        this.groupings = groupings;
        this.datEvent = new Date();
        this.codTransaction = UUID.randomUUID().toString().replace("-", "");
    }
    
    
    public Long getIdtEventLog() {
		return idtEventLog;
	}
	public void setIdtEventLog(Long idtEventLog) {
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
	public Long getIdtProductSource() {
		return idtProductSource;
	}
	public void setIdtProductSource(Long idtProductSource) {
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
	public Long getNumEventAmount() {
		return numEventAmount;
	}
	public void setNumEventAmount(Long numEventAmount) {
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
	public Long getEventStatus() {
		return eventStatus;
	}
	public void setEventStatus(Long eventStatus) {
		this.eventStatus = eventStatus;
	}
	public BigDecimal getNumCommissionValue() {
		return numCommissionValue;
	}
	public void setNumCommissionValue(BigDecimal numCommissionValue) {
		this.numCommissionValue = numCommissionValue;
	}
	

}

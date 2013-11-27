package uol.admanager.entity;


public class ClickLog {

    private String idtAdClickLog;
    private String desUrl;
    private String dateStart;
    private String dateEnd;
    private String numLines;
	public String getIdtAdClickLog() {
		return idtAdClickLog;
	}
	public void setIdtAdClickLog(String idtAdClickLog) {
		this.idtAdClickLog = idtAdClickLog;
	}
	public String getDesUrl() {
		return desUrl;
	}
	public void setDesUrl(String desUrl) {
		this.desUrl = desUrl;
	}
	public String getDateStart() {
		return dateStart;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getNumLines() {
		return numLines;
	}
	public void setNumLines(String numLines) {
		this.numLines = numLines;
	}
	@Override
	public String toString() {
		return "ClickLog [idtAdClickLog=" + idtAdClickLog + ", desUrl="
				+ desUrl + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd
				+ ", numLines=" + numLines + "]";
	}

}

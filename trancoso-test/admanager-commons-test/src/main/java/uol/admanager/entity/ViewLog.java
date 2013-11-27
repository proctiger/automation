package uol.admanager.entity;


public class ViewLog {

    private String idtAdViewLog;
    private String desUrl;
    private String dateStart;
    private String dateEnd;
    private String numLines;

    public String getIdtAdViewLog() {
		return idtAdViewLog;
	}
	public void setIdtAdViewLog(String idtAdViewLog) {
		this.idtAdViewLog = idtAdViewLog;
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
		return "ViewLog [idtAdViewLog=" + idtAdViewLog + ", desUrl=" + desUrl
				+ ", datStart=" + dateStart + ", datEnd=" + dateEnd
				+ ", numLines=" + numLines + "]";
	}
}

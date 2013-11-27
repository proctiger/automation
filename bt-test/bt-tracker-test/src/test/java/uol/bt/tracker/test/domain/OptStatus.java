package uol.bt.tracker.test.domain;


/**
 * 
 * @author dvrocha
 */
public class OptStatus {

    private String status;
    private String reason;

    public OptStatus() {
    }

    public OptStatus(String status, String reason) {
        this.status = status;
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

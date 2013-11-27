package uol.adserversap.ws.test.domain;

/**
 *
 * @author dvrocha
 */
public class ErrorMessageDomain {

    private String code;
    private String message;

    public ErrorMessageDomain() {
    }

    public ErrorMessageDomain(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ErrorMessageDomain(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

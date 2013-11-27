package uol.adserversap.ws.test.domain;

public class OrderWarning {

	private String code;
    private String message;

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("OrderWarning{code=%s, message=%s}", code, message);
    }
}

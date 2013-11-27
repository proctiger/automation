package uol.bt.cruncher.test.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.annotations.SerializedName;


public abstract class BtEvent {

    @SerializedName("event_id")
    private String id;

    @SerializedName("timestamp")
    private String timestamp;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("page_id")
    private String pageId;

    @SerializedName("source_id")
    private String sourceId;

    private transient boolean valid;

    public BtEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public void setTimestamp(Date date) {
        this.timestamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SS").format(date);
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPageId() {
        return pageId;
    }
    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getSourceId() {
        return sourceId;
    }
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public boolean isValid() {
        return valid;
    }
    public void setValid(boolean valid) {
        this.valid = valid;
    }
}

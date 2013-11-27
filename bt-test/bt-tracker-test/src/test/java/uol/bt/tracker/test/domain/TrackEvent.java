package uol.bt.tracker.test.domain;

import com.google.gson.annotations.SerializedName;

public class TrackEvent {

	public TrackEvent() {};

	public TrackEvent(String eventId, String sourceId) {
		super();
		this.eventId = eventId;
		this.sourceId = sourceId;
	}

	@SerializedName("event_id")
	private String eventId;

	@SerializedName("timestamp")
	private String timestamp;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("page_id")
	private String pageId;

	@SerializedName("source_id")
	private String sourceId;

	@SerializedName("cookie_value")
	private String cookieValue;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
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

	public String getCookieValue() {
		return cookieValue;
	}

	public void setCookieValue(String cookieValue) {
		this.cookieValue = cookieValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
		result = prime * result + ((pageId == null) ? 0 : pageId.hashCode());
		result = prime * result + ((sourceId == null) ? 0 : sourceId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrackEvent other = (TrackEvent) obj;
		if (eventId == null) {
			if (other.eventId != null)
				return false;
		} else if (!eventId.equals(other.eventId))
			return false;
		if (pageId == null) {
			if (other.pageId != null)
				return false;
		} else if (!pageId.equals(other.pageId))
			return false;
		if (sourceId == null) {
			if (other.sourceId != null)
				return false;
		} else if (!sourceId.equals(other.sourceId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
    public String toString() {
	    return "TrackEvent [eventId=" + eventId + ", timestamp=" + timestamp + ", userId=" + userId + ", pageId="
	            + pageId + ", sourceId=" + sourceId + ", cookieValue=" + cookieValue + "]";
    }
}

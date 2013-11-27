package test.utils;

import org.apache.commons.lang.StringUtils;

import test.domain.Event;

public class ClickUriUtil {

    private String source;

    private String idtUrl;

    private String caf;

    private Event event;

    private String redirUrl;

    private String idtLabel;

    private String type;
    
    public void setSource(String source) {
        this.source = source;
    }

    public void setIdtUrl(String idtUrl) {
        this.idtUrl = idtUrl;
    }

    public void setCaf(String caf) {
        this.caf = caf;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setRedirUrl(String redirUrl) {
        this.redirUrl = redirUrl;
    }

    public void setIdtLabel(String idtLabel) {
        this.idtLabel = idtLabel;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public String getIdtUrl() {
        return idtUrl;
    }

    public String getCaf() {
        return caf;
    }

    public Event getEvent() {
        return event;
    }
    
    public String getEventName() {
        return event.getName();
    }

    public String getRedirUrl() {
        if (StringUtils.isEmpty(redirUrl)) {
            redirUrl = ClickUriGenericsParamsUtil.genericRedirUrl;
        }
        return redirUrl;
    }

    public String getIdtLabel() {
        return idtLabel;
    }

    public String getType() {
        if (StringUtils.isEmpty(type)) {
            type = ClickUriGenericsParamsUtil.genericType;
        }
        return type;
    }

    public String getEventClickFullUri() {
        StringBuilder str = new StringBuilder(ClickUriGenericsParamsUtil.genericClickDomain);
        str.append("/").append(getEventName());
        str.append("?source=").append(getSource());
        str.append("&type=").append(getType());
        if (!StringUtils.isEmpty(getIdtUrl())) {
            str.append("&idtUrl=").append(getIdtUrl());
        } else {
            str.append("&caf=").append(getCaf());
        }
        if (!StringUtils.isEmpty(getIdtLabel())) {
            str.append("&idtLabel=").append(getIdtLabel());
        }
        str.append("&affRedir=").append(getRedirUrl());
        return str.toString();
    }
}

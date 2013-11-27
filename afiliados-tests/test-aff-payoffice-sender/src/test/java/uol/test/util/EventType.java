package uol.test.util;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author rsilva
 * 
 */

public enum EventType {
    
    PAYMENT("P");

    private final String type;

    private EventType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static EventType fromType(String arg) {
        for (EventType type : values()) {
            if (StringUtils.equalsIgnoreCase(type.type, arg)) {
                return type;
            }
        }
        return null;
    }
}
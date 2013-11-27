package uol.test.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.commons.lang.StringUtils;

import uol.test.util.EventType;

/**
 * @author rsilva
 * @author mzp_pribeiro
 * @version 1.01
 * 
 */
public class EventTypeAdapter extends XmlAdapter<String, EventType> {

    @Override
    public String marshal(EventType arg) throws Exception {
        if (arg == null) {
            return StringUtils.EMPTY;
        }
        return arg.getType();
    }

    @Override
    public EventType unmarshal(String arg) throws Exception {
        if (StringUtils.isBlank(arg)) {
            return null;
        }
        return EventType.fromType(arg);
    }
}

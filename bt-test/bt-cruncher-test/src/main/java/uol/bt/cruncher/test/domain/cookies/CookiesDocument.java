package uol.bt.cruncher.test.domain.cookies;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import uol.bt.cruncher.test.domain.MongoDocument;
import uol.bt.cruncher.test.util.MapUtils;

import com.mongodb.DBObject;

public class CookiesDocument extends MongoDocument {

    private Date updated;
    private Map<String, Set<String>> partnerSegments;

    public Date getUpdated() {
        return updated;
    }
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Map<String, Set<String>> getPartnerSegments() {
        return partnerSegments;
    }
    public Set<String> getPartnerSegments(String partner) {
        if (MapUtils.isEmpty(partnerSegments)) {
            return null;
        }

        return partnerSegments.get(partner);
    }
    public void setPartnerSegments(Map<String, Set<String>> partnerSegments) {
        this.partnerSegments = partnerSegments;
    }
    public void addPartnerSegment(String partner, String segment) {
        if (partnerSegments == null) {
            partnerSegments = new TreeMap<>();
        }

        final Set<String> segments = (partnerSegments.containsKey(partner))
                ? partnerSegments.get(partner)
                : new TreeSet<String>();

        segments.add(segment);
        partnerSegments.put(partner, segments);
    }
    public void addPartnerSegments(String partner, Collection<String> segments) {
        if (partnerSegments == null) {
            partnerSegments = new TreeMap<>();
        }

        final Set<String> currSegments = (partnerSegments.containsKey(partner))
                ? partnerSegments.get(partner)
                : new TreeSet<String>();

        currSegments.addAll(segments);
        partnerSegments.put(partner, currSegments);
    }
    public void addPartnerSegments(Map<String, Set<String>> partnerSegments) {
        if (MapUtils.isNotEmpty(partnerSegments)) {
            for (Entry<String, Set<String>> entry : partnerSegments.entrySet()) {
                addPartnerSegments(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public DBObject toMongoObject() throws Exception {
        return mapToMongoObj(partnerSegments).append("_id", _id).append("updated", updated);
    }

    public static CookiesDocument fromMongoObject(DBObject mongoObject) throws ParseException {
        final CookiesDocument cookiesDocument = new CookiesDocument();

        if (mongoObject != null) {
            final Set<Entry<String, ?>> entries = mongoObject.toMap().entrySet();
            for (Entry<String, ?> entry : entries) {
                if (entry.getValue() instanceof Collection<?>) {
                    final String partner = entry.getKey();
                    for (String segment : (Collection<String>) entry.getValue()) {
                        cookiesDocument.addPartnerSegment(partner, segment);
                    }
                } else if (Objects.equals("_id", entry.getKey())) {
                    cookiesDocument.setId((String) entry.getValue());
                } else if (Objects.equals("updated", entry.getKey())) {
                    cookiesDocument.setUpdated((Date) mongoObject.get(entry.getKey()));
                }
            }
        }

        return cookiesDocument;
    }
}

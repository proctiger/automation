package uol.bt.cruncher.test.domain.segments;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import uol.bt.cruncher.test.domain.MongoDocument;

import com.mongodb.DBObject;

public class SegmentsDocument extends MongoDocument {

    private Map<String, Map<String, Set<String>>> partnerSegmentsPerDay;

    public Map<String, Map<String, Set<String>>> getPartnerSegmentsPerDay() {
        return partnerSegmentsPerDay;
    }
    public void setPartnerSegmentsPerDay(Map<String, Map<String, Set<String>>> partnerSegmentsPerDay) {
        this.partnerSegmentsPerDay = partnerSegmentsPerDay;
    }

    public void addPartnerSegment(int day, String partner, String segment) {
        if (partnerSegmentsPerDay == null) {
            partnerSegmentsPerDay = new TreeMap<>();
        }

        final String dayAsString = new DecimalFormat("00").format(day);

        final Map<String, Set<String>> partnerSegments = (partnerSegmentsPerDay.containsKey(dayAsString))
                ? partnerSegmentsPerDay.get(dayAsString)
                : new TreeMap<String, Set<String>>();

        final Set<String> segments = (partnerSegments.containsKey(partner))
                ? partnerSegments.get(partner)
                : new TreeSet<String>();

       partnerSegmentsPerDay.put(dayAsString, partnerSegments);
       partnerSegments.put(partner, segments);
       segments.add(segment);
    }

    public void addPartnerSegments(int day, String partner, Set<String> newSegments) {
        if (partnerSegmentsPerDay == null) {
            partnerSegmentsPerDay = new TreeMap<>();
        }

        final String dayAsString = new DecimalFormat("00").format(day);

        final Map<String, Set<String>> partnerSegments = (partnerSegmentsPerDay.containsKey(dayAsString))
                ? partnerSegmentsPerDay.get(dayAsString)
                : new TreeMap<String, Set<String>>();

        final Set<String> segments = (partnerSegments.containsKey(partner))
                ? partnerSegments.get(partner)
                : new TreeSet<String>();

       partnerSegmentsPerDay.put(dayAsString, partnerSegments);
       partnerSegments.put(partner, segments);
       segments.addAll(newSegments);
    }

    public void addPartnerSegmentsPerDay(Map<String, Map<String, Set<String>>> partnerSegmentsPerDay) {
        for (Entry<String, Map<String, Set<String>>> dayEntry : partnerSegmentsPerDay.entrySet()) {
            for (Entry<String, Set<String>> partnerEntry : dayEntry.getValue().entrySet()) {
                addPartnerSegments(Integer.valueOf(dayEntry.getKey()), partnerEntry.getKey(), partnerEntry.getValue());
            }
        }
    }

    @Override
    public DBObject toMongoObject() {
        return mapToMongoObj(partnerSegmentsPerDay).append("_id", _id);
    }

    public static SegmentsDocument fromMongoObject(DBObject mongoObject) throws ParseException {
        final SegmentsDocument segmentsDocument = new SegmentsDocument();

        if (mongoObject != null) {
            for (String key : mongoObject.keySet()) {
                try {
                    int day = Integer.parseInt(key);
                    final Map<String, Set<String>> partnersSegments = (Map<String, Set<String>>) mongoObject.get(key);
                    for (Entry<String, Set<String>> partnerSegments : partnersSegments.entrySet()) {
                        segmentsDocument.addPartnerSegments(day, partnerSegments.getKey(), partnerSegments.getValue());
                    }
                } catch (NumberFormatException e) {
                    if (Objects.equals("_id", key)) {
                        segmentsDocument.setId((String) mongoObject.get(key));
                    }
                }
            }
        }

        return segmentsDocument;
    }
}

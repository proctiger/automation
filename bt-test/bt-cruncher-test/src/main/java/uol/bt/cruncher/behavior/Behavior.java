package uol.bt.cruncher.behavior;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.mongodb.DBObject;

public class Behavior {

    private Map<Integer, Map<String, Integer>> summaryViews;
    private Map<Integer, Map<String, Set<String>>> summarySegments;
    private Map<String, Set<String>> segments = new HashMap<>();
    private Map<String, Set<String>> sticky = new HashMap<>();
    private Set<String> profiles = new TreeSet<>();

    private final BtCollectionFactory collectionFactory;

    public Behavior(BtCollectionFactory collectionFactory) {
        this.collectionFactory = collectionFactory;
    }

    public void summarizeCollection(String collectionName, DBObject data) {
        if (StringUtils.startsWith(collectionName, "views")) {
            summarizeViews(collectionName, data);
        } else if (StringUtils.startsWith(collectionName, "segments")) {
            summarizeSegments(collectionName, data);
        } else if (Objects.equals(collectionName, "profile")) {
            summarizeProfile(data);
        }
    }

    public void summarizeViews(String collectionName, DBObject viewsData) {
        if (summaryViews == null) {
            summaryViews = collectionFactory.newBlankViews();
        }

        final Map<String, Integer> collectionDayToWindow = collectionFactory.dayToWindowMap(collectionName);

        for (String day : viewsData.keySet()) {
            if (Objects.equals("_id", day)) {
                continue;
            }

            for (String group : ((DBObject) viewsData.get(day)).keySet()) {
                final Integer dataCount = (Integer) ((DBObject) viewsData.get(day)).get(group);

                for (Integer window = collectionDayToWindow.get(day); window <= collectionFactory.getWindowSize(); window++) {
                    final Map<String, Integer> summaryView = summaryViews.get(window);
                    final Integer summaryCount = summaryView.get(group) + dataCount;

                    summaryView.put(group, summaryCount);
                }
            }
        }
    }

    public void summarizeSegments(String collectionName, DBObject segmentsData) {
        if (summarySegments == null) {
            summarySegments = collectionFactory.newBlankSegments();
        }

        final Map<String, Integer> collectionDayToWindow = collectionFactory.dayToWindowMap(collectionName);

        for (String day : segmentsData.keySet()) {
            if (Objects.equals("_id", day)) {
                continue;
            }

            for (String partner : ((DBObject) segmentsData.get(day)).keySet()) {
                final Collection<String> dataSegments = (Collection<String>) ((DBObject) segmentsData.get(day)).get(partner);

                for (Integer window = collectionDayToWindow.get(day); window <= collectionFactory.getWindowSize(); window++) {
                    final Map<String, Set<String>> summarySegment = summarySegments.get(window);

                    summarySegment.get(partner).addAll(dataSegments);
                }
            }
        }
    }

    private void summarizeProfile(DBObject profileData) {
        final Collection<String> dataTraits = (Collection<String>) profileData.get("traits");
        profiles.addAll(dataTraits);
    }

    public int traitCountOf(String code, int windowSize) {
        try {
            return summaryViews.get(windowSize).get(code);
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public boolean containsStickySegment(String partner, String code, int windowSize) {
        try {
            return summarySegments.get(windowSize).get(partner).contains(code);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public Map<String, Set<String>> getSegments() {
        return segments;
    }

    public void addSegment(String partner, String segment) {
        if (segments.containsKey(partner)) {
            segments.get(partner).add(segment);
        } else {
            final Set<String> partnerSegments = new TreeSet<>();
            partnerSegments.add(segment);
            segments.put(partner, partnerSegments);
        }
    }

    public void addSegments(String partner, Collection<String> segments) {
        if (this.segments.containsKey(partner)) {
            this.segments.get(partner).addAll(segments);
        } else {
            this.segments.put(partner, new TreeSet<>(segments));
        }
    }

    public void addStickySegment(String partner, String segment) {
        if (sticky.containsKey(partner)) {
            sticky.get(partner).add(segment);
        } else {
            final Set<String> partnerSegments = new TreeSet<>();
            partnerSegments.add(segment);
            sticky.put(partner, partnerSegments);
        }
    }

    public Map<String, Set<String>> getAllCurrentAndStickySegments() {
        final Map<String, Set<String>> all = new HashMap<>();

        for (String partner : collectionFactory.getPartners()) {
            final Set<String> currentSegs = segments.containsKey(partner) ? segments.get(partner) : Collections.EMPTY_SET;
            final Set<String> stickySegs = sticky.containsKey(partner) ? sticky.get(partner) : Collections.EMPTY_SET;
            final Set<String> currentAndStickSegs = new TreeSet<String>(CollectionUtils.union(currentSegs, stickySegs));

            if (CollectionUtils.isNotEmpty(currentAndStickSegs)) {
                all.put(partner, currentAndStickSegs);
            }
        }

        return all;
    }

    public Set<String> getProfiles() {
        return profiles;
    }

    public Collection<String> getStickySegments(String partner) {
        return sticky.get(partner);
    }
}

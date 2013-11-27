package uol.bt.cruncher.behavior;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class BtCollectionFactory {

    private HashMap<String, Integer> zeroGroups;

    private int windowSize = 0;
    private Set<String> partners;
    private Set<String> groups;
    private Map<String, Map<String, Integer>> collections;

    @SuppressWarnings("serial")
    public BtCollectionFactory(RemoteConfiguration config) {
        partners = config.getPartnersCookieParameters().keySet();
        groups = config.getUrlGroups().keySet();

        for (String partner : partners) {
            windowSize = Math.max(windowSize, Integer.valueOf(config.getPartnersCookieParameters().get(partner).get("sessionWindowDays")));
        }

        zeroGroups = new HashMap<String, Integer>(){{
            for (String group : groups) {
                put(group, 0);
            }
        }};

        collections = new HashMap<>();
        final SimpleDateFormat collFormater = new SimpleDateFormat("MMyyyy");
        final DecimalFormat dayFormater = new DecimalFormat("00");
        final Calendar currData = Calendar.getInstance();

        for (Integer window = 0; window <= windowSize; window++) {
            final String viewsName = "views_" + collFormater.format(currData.getTime());
            final String segmentsName = "segments_" + collFormater.format(currData.getTime());

            final Map<String, Integer> viewsDays = collections.containsKey(viewsName)
                    ? collections.get(viewsName)
                    : new HashMap<String, Integer>();
            final Map<String, Integer> segmentsDays = collections.containsKey(segmentsName)
                    ? collections.get(segmentsName)
                    : new HashMap<String, Integer>();

            final String day = dayFormater.format(currData.get(Calendar.DAY_OF_MONTH));
            viewsDays.put(day, window);
            segmentsDays.put(day, window);

            collections.put(viewsName, viewsDays);
            collections.put(segmentsName, segmentsDays);

            currData.add(Calendar.DAY_OF_MONTH, -1);
        }
        collections.put("profile", Collections.EMPTY_MAP);
    }

    private Map<String, Integer> newZeroGroups() {
        return (Map<String, Integer>) zeroGroups.clone();
    }

    @SuppressWarnings("serial")
    public Map<Integer, Map<String, Integer>> newBlankViews() {
        return new HashMap<Integer, Map<String, Integer>>(){{
            for (Integer window = 0; window <= windowSize; window++) {
                put(window, newZeroGroups());
            }
        }};
    }

    @SuppressWarnings("serial")
    private Map<String, Set<String>> newEmptyPartnerSegments() {
        return new HashMap<String, Set<String>>(){{
            for (String partner : partners) {
                put(partner, new TreeSet<String>());
            }
        }};
    }

    @SuppressWarnings("serial")
    public Map<Integer, Map<String, Set<String>>> newBlankSegments() {
        return new HashMap<Integer, Map<String, Set<String>>>(){{
            for (Integer window = 0; window <= windowSize; window++) {
                put(window, newEmptyPartnerSegments());
            }
        }};
    }

    public Map<String, Integer> dayToWindowMap(String collectionName) {
        return collections.get(collectionName);
    }

    public Set<String> getCollections() {
        return collections.keySet();
    }

    public int getWindowSize() {
        return windowSize;
    }

    public Set<String> getPartners() {
        return partners;
    }
}

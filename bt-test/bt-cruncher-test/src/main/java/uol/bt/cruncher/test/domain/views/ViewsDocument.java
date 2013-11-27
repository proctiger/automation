package uol.bt.cruncher.test.domain.views;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.TreeMap;

import uol.bt.cruncher.test.domain.MongoDocument;

import com.mongodb.DBObject;

public class ViewsDocument extends MongoDocument {

    private Map<String, Map<String, Integer>> groupsPerDayCounter;

    public Map<String, Map<String, Integer>> getGroupsPerDayCounter() {
        return groupsPerDayCounter;
    }
    public void setGroupsPerDayCounter(Map<String, Map<String, Integer>> groupsPerDayCounter) {
        this.groupsPerDayCounter = groupsPerDayCounter;
    }

    public void addGroupViews(int day, String group, int views) {
        final String dayFormated = new DecimalFormat("00").format(day);

        if (groupsPerDayCounter == null) {
            groupsPerDayCounter = new TreeMap<>();
        }

        final Map<String, Integer> groupsCounter = (groupsPerDayCounter.containsKey(dayFormated))
                ? groupsPerDayCounter.get(dayFormated)
                : new TreeMap<String, Integer>();

        final Integer counter = (groupsCounter.containsKey(group))
                ? groupsCounter.get(group)
                : 0;

        groupsPerDayCounter.put(dayFormated, groupsCounter);
        groupsCounter.put(group, counter + views);
    }

    public void addGroupViews(Map<String, Map<String, Integer>> groupViews) {
        for (Entry<String, Map<String, Integer>> dayEntry : groupViews.entrySet()) {
            final int day = Integer.valueOf(dayEntry.getKey());
            for (Entry<String, Integer> groupEntry : dayEntry.getValue().entrySet()) {
                addGroupViews(day, groupEntry.getKey(), groupEntry.getValue());
            }
        }
    }

    @Override
    public DBObject toMongoObject() {
        return mapToMongoObj(groupsPerDayCounter).append("_id", _id);
    }

    public static ViewsDocument fromMongoObject(DBObject mongoObject) throws ParseException {
        final ViewsDocument viewsObject = new ViewsDocument();

        if (mongoObject != null) {
            for (String key : mongoObject.keySet()) {
                try {
                    int day = Integer.parseInt(key);
                    final Map<String, Integer> groups = (Map<String, Integer>) mongoObject.get(key);
                    for (Entry<String, Integer> group : groups.entrySet()) {
                        viewsObject.addGroupViews(day, group.getKey(), group.getValue());
                    }
                } catch (NumberFormatException e) {
                    if (Objects.equals("_id", key)) {
                        viewsObject.setId((String) mongoObject.get(key));
                    }
                }
            }
        }

        return viewsObject;
    }
}

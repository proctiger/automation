package uol.bt.cruncher.test.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;


public class MapUtils extends org.apache.commons.collections.MapUtils {

    public static boolean isEqualMaps(Map<String, ?> m1, Map<String, ?> m2) {
        if (isEmpty(m1) || isEmpty(m2)) {
            return isEmpty(m1) && isEmpty(m2);
        }

        if (m1.size() != m2.size()) {
            return false;
        }

        final Comparator<Entry<String, ?>> entryComparator = new Comparator<Entry<String,?>>() {
            @Override
            public int compare(Entry<String, ?> e1, Entry<String, ?> e2) {
                return e1.getKey().compareTo(e2.getKey());
            }
        };
        final TreeSet<Entry<String, ?>> t1 = new TreeSet<>(entryComparator);
        final TreeSet<Entry<String, ?>> t2 = new TreeSet<>(entryComparator);
        t1.addAll(m1.entrySet());
        t2.addAll(m2.entrySet());

        final Iterator<Entry<String, ?>> i1 = t1.iterator();
        final Iterator<Entry<String, ?>> i2 = t2.iterator();

        while (i1.hasNext() && i2.hasNext()) {
            Entry<?, ?> e1 = i1.next();
            Entry<?, ?> e2 = i2.next();

            if (!ObjectUtils.deepEquals(e1.getKey(), e2.getKey())) {
                return false;
            }

            if (!ObjectUtils.deepEquals(e1.getValue(), e2.getValue())) {
                return false;
            }
        }

        return true;
    }
}

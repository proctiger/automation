package uol.admanager.utils;

import java.util.Collection;
import java.util.Random;

public class CollectionUtils extends org.apache.commons.collections.CollectionUtils {

    public static <E> E randomElement(Collection<E> elements) {
        if (isEmpty(elements)) {
            return null;
        }

        return (E) elements.toArray()[randomIndex(elements)];
    }

    public static int randomIndex(Collection<?> collection) {
        return new Random().nextInt(Math.max(1, collection.size()));
    }
}

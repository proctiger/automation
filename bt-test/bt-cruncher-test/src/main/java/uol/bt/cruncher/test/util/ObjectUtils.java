package uol.bt.cruncher.test.util;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

import com.mongodb.DBObject;

public class ObjectUtils extends org.apache.commons.lang.ObjectUtils {

    public static boolean deepEquals(Object o1, Object o2) {
        if (Objects.deepEquals(o1, o2)){
            return true;
        }

        if (o1 instanceof Collection<?> && o2 instanceof Collection<?> &&
                CollectionUtils.isEqualCollection((Collection<?>) o1, (Collection<?>) o2)) {
            return true;
        }

        if (o1 instanceof Map<?, ?> && o2 instanceof Map<?, ?> &&
                MapUtils.isEqualMaps((Map<String, ?>) o1, (Map<String, ?>) o2)) {
            return true;
        }

        if (o1 instanceof Date && o2 instanceof Date &&
                ((Date) o1).getTime() - ((Date) o2).getTime() <= 60000) {
            return true;
        }

        if (o1 instanceof DBObject && o2 instanceof DBObject &&
                MapUtils.isEqualMaps(((DBObject) o1).toMap(), ((DBObject) o2).toMap())) {
            return true;
        }

        return false;
    }
}

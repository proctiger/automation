package uol.bt.cruncher.test.domain;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;

import uol.bt.cruncher.test.util.CollectionUtils;
import uol.bt.cruncher.test.util.ObjectUtils;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public abstract class MongoDocument {
    protected String _id;

    public String getId() {
        return _id;
    }
    public void setId(String id) {
        this._id = id;
    }

    public DBObject toMongoObject() throws Exception {
        final BasicDBObject mongoObj = new BasicDBObject();
        final Field[] allFields = (Field[]) ArrayUtils.addAll(getClass().getDeclaredFields(), MongoDocument.class.getDeclaredFields());

        for (Field field : allFields) {
            field.setAccessible(true);
            final Object fieldValue = field.get(this);

            if (fieldValue == null) {
                continue;
            }

            if (fieldValue instanceof Map<?, ?>) {
                mongoObj.put(field.getName(), mapToMongoObj((Map<String, Object>) fieldValue));
            } else {
                mongoObj.put(field.getName(), fieldValue);
            }
        }

        return mongoObj;
    }

    public static BasicDBObject mapToMongoObj(Map<String, ?> map) {
        final BasicDBObject mongoObj = new BasicDBObject();

        if (MapUtils.isNotEmpty(map)) {
            for (Entry<String, ?> entry : map.entrySet()) {
                final String fieldName = entry.getKey();
                final Object fieldValue = (entry.getValue() instanceof Map<?, ?>)
                        ? mapToMongoObj((Map<String, Object>) entry.getValue())
                        : entry.getValue();
                mongoObj.put(fieldName, fieldValue);
            }
        }

        return mongoObj;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            if (obj == null) {
                final Field[] allFields = (Field[]) ArrayUtils.addAll(getClass().getDeclaredFields(), MongoDocument.class.getDeclaredFields());

                for (Field field : allFields) {
                    field.setAccessible(true);
                    final boolean isFieldValueNotNull = field.get(this) != null && !Objects.equals("_id", field.getName());

                    if (isFieldValueNotNull) {
                        return false;
                    }
                }

                return true;
            }

            if (obj instanceof DBObject) {
                final DBObject otherMongoObj = (DBObject) obj;
                final DBObject thisMongoObj = toMongoObject();

                final boolean notMongoObjsHaveSameFields = !CollectionUtils.isEqualCollection(thisMongoObj.keySet(), otherMongoObj.keySet());
                if (notMongoObjsHaveSameFields) {
                    return false;
                }

                for (String thisMongoObjField : thisMongoObj.keySet()) {
                    final Object thisMongoObjValue = thisMongoObj.get(thisMongoObjField);
                    final Object otherMongoObjValue = otherMongoObj.get(thisMongoObjField);

                    if (!ObjectUtils.deepEquals(thisMongoObjValue, otherMongoObjValue)) {
                        return false;
                    }
                }

                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return super.equals(obj);
    }

    @Override
    public String toString() {
        try {
            return toMongoObject().toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

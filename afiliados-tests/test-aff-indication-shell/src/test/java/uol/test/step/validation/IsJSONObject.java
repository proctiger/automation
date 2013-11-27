package uol.test.step.validation;

import java.util.Iterator;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

/**
 * 
 * @author cad_rpereira
 * 
 */
public class IsJSONObject extends TypeSafeMatcher<JSONObject> {

    private final JSONObject expected;

    private IsJSONObject(JSONObject expected) {
        this.expected = expected;
    }

    public JSONObject getExpected() {
        return expected;
    }

    @Override
    public void describeTo(Description arg) {
        arg.appendText("JSONObject result should be ").appendValue(expected);
    }

    @Factory
    public static <T> Matcher<JSONObject> equalTo(JSONObject expected) {
          return new IsJSONObject(expected);
    }

	@Override
	@SuppressWarnings("rawtypes")
    protected boolean matchesSafely(JSONObject arg) {
        if(expected == null && expected == arg) {
        	return true;
        }
		Iterator keys = expected.keys();
        while(keys.hasNext()) {
        	Object key = keys.next();
        	
        	try {
				String k = key.toString();
				if((arg.isNull(k) && !expected.isNull(k)) || (!arg.isNull(k) && !arg.get(k).equals(expected.get(k)))) {
					return false;
				}
			} catch (JSONException e) {
				Assert.fail(String.format("Falha no teste devido ao seguinte erro: [%s]", e.getLocalizedMessage()));
			}
        }
    	return true;
    }
}
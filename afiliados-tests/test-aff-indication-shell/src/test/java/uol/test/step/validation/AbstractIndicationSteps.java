package uol.test.step.validation;

import org.json.JSONObject;

import uol.affiliated.commons.rest.client.RestServiceCaller;
import uol.affiliated.commons.test.DBUtil;

public abstract class AbstractIndicationSteps {
	protected static RestServiceCaller request;
	protected static String requestURI;
	protected static JSONObject json;
	protected static boolean jsonp;
	protected static String callback;
	protected static int status;
	protected static String redir;
	protected static String cookie;
	protected static DBUtil dbUtil = new DBUtil();
}
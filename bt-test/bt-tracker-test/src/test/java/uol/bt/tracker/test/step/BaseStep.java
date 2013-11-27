package uol.bt.tracker.test.step;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import reconf.client.proxy.ConfigurationRepositoryFactory;
import uol.bt.tracker.test.domain.RequestParams;
import uol.bt.tracker.test.domain.TrackEvent;
import uol.bt.tracker.test.reconf.ConfigBtTrackerTest;
import uol.bt.tracker.test.selenium.BasicWebTest;
import uol.bt.tracker.test.selenium.UserAuthWebPage;
import uol.simple.httpclient.SimpleHttpResponse;

/**
 *
 * @author dvrocha
 */
public class BaseStep {

	protected static SimpleHttpResponse trackerResponse;
	protected static boolean self;
	protected static RequestParams params;
	protected static String userId;
	protected static List<TrackEvent> expectedEvents;
	protected static List<TrackEvent> oldEvents;
	protected static ConfigBtTrackerTest config = ConfigurationRepositoryFactory.get(ConfigBtTrackerTest.class);

	protected static void authAndtrack(String action, String uuid) throws Exception {
		UserAuthWebPage page = new UserAuthWebPage(BasicWebTest.firefoxDriver);
		if (!StringUtils.isEmpty(uuid)) {
			page.openAuthPage(action, uuid);
		} else {
			page.openAuthPage(action);
		}
		page.login();
		page.waitTrack();
	}
}

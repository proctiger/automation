package uol.bt.tracker.test.step;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

import reconf.client.proxy.ConfigurationRepositoryFactory;
import uol.bt.tracker.test.domain.CookieContent;
import uol.bt.tracker.test.domain.RequestParams;
import uol.bt.tracker.test.domain.TrackEvent;
import uol.bt.tracker.test.http.RemoteAdminTestHttp;
import uol.bt.tracker.test.http.TrackerHttp;
import uol.bt.tracker.test.reconf.ConfigBtTracker;

import com.google.gson.Gson;

public class TestPrepare {

	private static final ConfigBtTracker config = ConfigurationRepositoryFactory.get(ConfigBtTracker.class);

	public static TrackEvent getEvent(String type, String action, String uuid) throws Exception {
		TrackEvent event = new TrackEvent(type, "UOL");

		if (!StringUtils.isEmpty(uuid)) {
			event.setUserId(uuid);
		}

		String pageId = "http://tracker.bt.uol.com.br/user-track.html?action=" + action;
		pageId = URLEncoder.encode(pageId, "UTF-8");

		event.setPageId(pageId);
		return event;
	}

	public static CookieContent getInvalidCookieBttrk() {
		return new CookieContent("bt.uol.com.br", "BTTRK", "invalid", "/", null);
	}

	public static CookieContent getCookieBttrk() throws Exception {
		return getCookieBttrk(getBttrkDate());
	}

	public static CookieContent getCookieBttrk(String date) {
		String cookieValue = getRamdomCookieValue();
		if (date != null) {
			cookieValue += "|" + date;
		}
		return new CookieContent("bt.uol.com.br", "BTTRK", cookieValue, "/", null);
	}

	public static String getBttrkDate() throws Exception {
		return getBttrkDate(new Date());
	}

	public static String getBttrkDate(Date date) throws Exception {
		return new SimpleDateFormat("HHmmssddMMyy").format(date);
	}

	private static String getRamdomCookieValue() {
		String cookieValue = "";
		String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		for (int i = 0; i < 32; i++) {
			cookieValue += getRandomObject(Arrays.asList(chars));
		}
		return cookieValue;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getRandomObject(List<? extends Object> list) {
		Random rand = new Random();
		return (T) list.get(rand.nextInt(list.size()));
	}

	public static void removeEvents() throws Exception {
		RemoteAdminTestHttp.shell("sudo rm -rf /export/bt/cruncher-events/uuid*.json").getBodyAsString();
	}

	public static void restartTracker() throws Exception {
		RemoteAdminTestHttp.shell("sudo /etc/init.d/jetty restart bt-tracker").getBodyAsString();
	}

	public static void waitTrackerStart() throws Exception {
		int count = 1;
		while (true) {
			if (TrackerHttp.probe("monitor-probe").getStatusCode() == 200) {
				break;
			}
			if (count >= 60) {
				throw new Exception("BT-TRACKER não foi iniciado");
			}
			Thread.sleep(1000);
			count++;
		}
	}

	public static RequestParams getDefaultparams() {
		RequestParams params = new RequestParams();
		params.setReferer(getRandomReferer());
		return params;
	}

	private static String getRandomReferer() {
		String referer = (Math.random() * 10) >= 7 ? "http://" : "https://";
		referer += getRandomNormalizedText(3 + (int) (Math.random() * 20));
		referer += "." + getRandomNormalizedText(1 + (int) (Math.random() * 2));
		referer += (Math.random() * 10) >= 5 ? "" : "?" + getRandomNormalizedText(1 + (int) (Math.random() * 20)) + "="
		        + getRandomNormalizedText(1 + (int) (Math.random() * 20));
		return referer;
	}

	private static String getRandomNormalizedText(int textSize) {
		String[] charList = { "q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g", "h", "j", "k",
		        "l", "z", "x", "c", "v", "b", "n", "m", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		String text = "";
		Random random = new Random();
		for (int i = 0; i < textSize; i++) {
			text += charList[random.nextInt(charList.length)];
		}
		return text;
	}

	public static List<TrackEvent> getExpectedEvents(RequestParams params) throws Exception {
		List<TrackEvent> events = new ArrayList<>();

		if (params != null && params.getReferer() != null) {
			if (!params.getReferer().toLowerCase().contains("googlebot")) {
				boolean validReferer = true;

				List<String> blackList = config.getReferersBlacklist();
				for (String ref : blackList) {
					if (ref.equals(params.getReferer())) {
						validReferer = false;
						break;
					}
				}

				if (validReferer) {
					TrackEvent event = new TrackEvent();
					event.setEventId("page_view");
					event.setPageId(URLEncoder.encode(params.getReferer(), "UTF-8"));
					event.setSourceId("UOL");
					events.add(event);
				}
			}
		}

		return events;
	}

	private static TrackEvent parseJsonEvent(String json) throws Exception {
		TrackEvent trakedEvent = new TrackEvent();
		if (!StringUtils.isEmpty(json)) {
			try {
				Gson gson = new Gson();
				trakedEvent = gson.fromJson(json, TrackEvent.class);
			} catch (Exception e) {
				throw new Exception("JSON inválido: " + json + " - " + e.getMessage());
			}
		}
		return trakedEvent;
	}

	public static String getUuid(CookieContent bttrk) {
		return bttrk.getValue().substring(0, 32);
	}

	public static List<TrackEvent> getCurrEvents() throws Exception {
		return getCurrEvents(false);
	}

	public static List<TrackEvent> getCurrEvents(boolean wait) throws Exception {
		return getEvents("cat /export/bt/cruncher-events/uuid*.json", wait);
	}

	public static List<TrackEvent> getEventsByUuid(String uuid) throws Exception {
		Integer uuidFistCharHexa = Integer.valueOf(uuid.substring(0, 1), 16);
		String fileNamePref = "uuid" + StringUtils.leftPad(uuidFistCharHexa.toString(), 2, '0');

		return getEvents("cat /export/bt/cruncher-events/" + fileNamePref + "*.json | grep " + uuid, true);
	}

	public static List<TrackEvent> getEvents(String command, boolean wait) throws Exception {
		if (wait) {
			Thread.sleep(1000);
		}

		String fileContent = RemoteAdminTestHttp.shell(command).getBodyAsString();

		List<TrackEvent> events = new ArrayList<>();
		for (String row : fileContent.split("\\n")) {
			if (row.contains("}{")) {
				throw new Exception("Linha do arquivo está corrompida: " + row);
			}
			events.add(parseJsonEvent(row));
		}
		return events;
	}
}

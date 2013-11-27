package uol.bt.tracker.test.step;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.Header;
import org.junit.Assert;

import uol.bt.tracker.test.domain.CookieContent;
import uol.bt.tracker.test.domain.TrackEvent;
import uol.simple.httpclient.SimpleHttpResponse;

public class TestValidate {
	
	public static void validateEventDate(TrackEvent event) throws Exception{
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date eventDate = df.parse(event.getTimestamp().substring(0, 19));
		long eventDateInterval = (new Date().getTime() / 1000) - (eventDate.getTime() / 1000);
		Assert.assertTrue("Data do evento é inválida: " + eventDateInterval, eventDateInterval >= -60
		        && eventDateInterval <= 60);
	}

	public static void validateCookieBttrkExpiration(CookieContent cookie) {
		Assert.assertTrue((cookie.getExpiryDate().getTime() - new Date().getTime()) > 315532798000L);
	}

	private static void validateCookieBttrkValue(String cookieValue) throws Exception {
		Assert.assertEquals("Quantidade de caracteres do valor do cookie esta incorreta", 45, cookieValue.length());
		Assert.assertEquals("Valor do cookie nao possui pipe na posicao correta: " + cookieValue, "|",
		        cookieValue.substring(32, 33));

		String[] cookieSlice = cookieValue.split("\\|");

		String uuid = cookieSlice[0];
		Assert.assertTrue("Valor do UUID é inválido: " + uuid, uuid.matches("^[a-f0-9]{32}$"));

		Date bttrkValDate = transformBttrkValDate(cookieSlice[1]);
		long timeDiff = (bttrkValDate.getTime() / 1000) - (new Date().getTime() / 1000);
		Assert.assertTrue("Data do cookie esta invalida. Data no cookie: " + cookieSlice[1] + ". Diferença: "
		        + timeDiff, timeDiff >= -60 && timeDiff <= 60);
	}

	private static Date transformBttrkValDate(String bttrkValDate) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("HHmmssddMMyy");
		return df.parse(bttrkValDate);
	}

	public static void validateCookieBttrk(CookieContent cookie) throws Exception {
		Assert.assertEquals("bt.uol.com.br", cookie.getDomain());
		validateCookieBttrkValue(cookie.getValue());
		validateCookieBttrkExpiration(cookie);
	}

	public static void validateCookieBttrk(CookieContent oldCookie, CookieContent newCookie) throws Exception {
		validateCookieBttrk(newCookie);
		Assert.assertEquals("Sistema não manteve o ID do usuário", oldCookie.getValue().substring(0, 32), newCookie
		        .getValue().substring(0, 32));
	}
	
	public static CookieContent extractCookieFrom(String cookieName, SimpleHttpResponse response) throws Exception {
		final Header[] headers = response.getHeaders("Set-Cookie");
		if (headers != null) {
			for (Header header : headers) {
				final String value = header.getValue();
				if (value != null && value.startsWith(cookieName)) {
					return parseCookieHeader(value);
				}
			}
		}

		return null;
	}

	public static CookieContent extractBttrkFrom(SimpleHttpResponse response) throws Exception {
		return extractCookieFrom("BTTRK", response);
	}

	private static CookieContent parseCookieHeader(String headerValue) throws Exception {
		CookieContent cookie = new CookieContent();
		String[] values = headerValue.split(";");
		for (int i = 0; i < values.length; i++) {

			if (i == 0) {
				cookie.setName(values[0].substring(0, values[0].indexOf("=")));
				cookie.setValue(values[0].substring(values[0].indexOf("=") + 1));
				continue;
			}

			if (values[i].startsWith("Path=")) {
				cookie.setPath(values[i].replace("Path=", ""));
				continue;
			}

			if (values[i].startsWith("Domain=")) {
				cookie.setDomain(values[i].replace("Domain=", ""));
				continue;
			}

			if (values[i].startsWith("Expires=")) {
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
				cookie.setExpiryDate(df.parse(replaceDateMonth(values[3].replace("Expires=", "").substring(5, 29))));
				continue;
			}
		}

		return cookie;
	}

	private static String replaceDateMonth(String date) {
		date = date.replace("Jan", "01");
		date = date.replace("Feb", "02");
		date = date.replace("Mar", "03");
		date = date.replace("Apr", "04");
		date = date.replace("May", "05");
		date = date.replace("Jun", "06");
		date = date.replace("Jul", "07");
		date = date.replace("Aug", "08");
		date = date.replace("Sep", "09");
		date = date.replace("Oct", "10");
		date = date.replace("Nov", "11");
		date = date.replace("Dec", "12");
		return date;
	}
}

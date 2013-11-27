package sandbox;

import java.util.HashMap;
import java.util.Map;

import uol.tm.stc.test.domain.SigaTag;
import uol.tm.stc.test.page.TestPage;
import uol.tm.stc.test.suite.TestSuite;
import uol.tm.stc.test.web.BasicWebTest;

public class Main {

	public static void main(String... args) throws Exception {
		makeHtmlFile();
		// SimpleHttpResponse response = RemoteAdminTesterHttp.shell("pwd");
		// System.out.println(response.getBodyAsString());
	}

	private static void makeHtmlFile() throws Exception {
		TestSuite.startWebDrive();
		countFormats();
		TestSuite.stopWebDrive();
	}

	private static void countFormats() throws Exception {
		TestPage page = new TestPage(BasicWebTest.firefoxDriver);
		Map<String, Integer> map = new HashMap<String, Integer>();

		for (int i = 0; i < 1000; i++) {
			page.load();
			SigaTag tag = page.getUolCliquesAdById("teste");

			int indexTo = tag.getAdclientSrc().indexOf(".html?");
			String format = tag.getAdclientSrc().substring(32, indexTo);

			if (map.containsKey(format)) {
				map.put(format, map.get(format) + 1);
			} else {
				map.put(format, 1);
			}
		}
		
		System.out.print(map);
	}
}

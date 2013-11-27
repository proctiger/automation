package uol.tm.stc.test.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import uol.tm.stc.test.domain.SigaTag;
import uol.tm.stc.test.web.BasicWebPage;

/**
 * 
 * @author dvrocha
 */
public class TestPage extends BasicWebPage {

	public TestPage(WebDriver driver) {
		super(driver);
	}

	public void load() throws Exception {
		driver.get("http://test.tm.uol.com.br/");
	}

	public List<String> getLogList() throws Exception {
		String logListItem = "//ul[@id='logs']/li[%s]";
		List<String> list = new ArrayList<>();
		int i = 1;
		while (true) {
			By listItem = By.xpath(String.format(logListItem, i));
			if (!isElementPresent(listItem)) {
				break;
			}
			list.add(driver.findElement(listItem).getText());
			i++;
		}
		return list;
	}
	
	public List<String> getSigaPropertiesFromTagmanager() throws Exception {
		String lpPropertyListItem = "//div[@id='uolCliquesConfig']/ul/li[%s]";
		List<String> list = new ArrayList<>();
		int i = 1;
		while (true) {
			By listItem = By.xpath(String.format(lpPropertyListItem, i));
			if (!isElementPresent(listItem)) {
				break;
			}
			list.add(driver.findElement(listItem).getText());
			i++;
		}
		return list;
	}

	public SigaTag getUolCliquesAdById(String id) throws Exception {
		By elementBy = By.xpath("//div[@id='" + id + "']/iframe");

		if (waitDinamicElements(elementBy)) {
			WebElement elem = driver.findElement(elementBy);

			Map<String, String> properties = new HashMap<String, String>();

			String width = elem.getAttribute("width");
			String height = elem.getAttribute("height");
			if (width != null && height != null) {
				properties.put("size", width + "x" + height);
			}

			SigaTag ad = new SigaTag();
			ad.setAdclientSrc(elem.getAttribute("src"));			

			if (ad.getAdclientSrc() != null && ad.getAdclientSrc().contains("?") && ad.getAdclientSrc().contains("&")) {
				String query = ad.getAdclientSrc().substring(ad.getAdclientSrc().indexOf("?") + 1);
				String[] queryProperties = query.split("&");
				for (String prop : queryProperties) {
					String key = prop.substring(0, prop.indexOf("="));
					String value = prop.substring(prop.indexOf("=") + 1);
					properties.put(key, value);
				}
			}
			ad.setProperties(properties);

			return ad;
		}

		return null;
	}

	public boolean waitDinamicElements(By by) throws Exception {
		int maxSecoundsToWait = 2;
		for (int i = 0; i < (maxSecoundsToWait * 2); i++) {
			if (isElementPresent(by)) {
				return true;
			}
			Thread.sleep(500);
		}
		return false;
	}

	public Map<String, String> getSigaProperties() throws Exception {
		Map<String, String> propMap = new HashMap<String, String>();
		for(String prop : getSigaPropertiesFromTagmanager()){
			if(prop != null && prop.contains("=")){
				String key = prop.substring(0, prop.indexOf("="));
				String value = prop.substring(prop.indexOf("=") + 1);
				propMap.put(key, value);
			}
		}
		return propMap;
	}

	public boolean findLogVersion() throws Exception {
		int numMaxTentatives = 4;
		for (int i = 0; i < numMaxTentatives; i++) {
			List<String> logs = getLogList();
			for (String log : logs) {
				if (log.matches("^\\[UOL Tag Manager\\] VERSION: [0-9]+\\.[0-9]+\\.[0-9]+(-SNAPSHOT)?$")) {
					return true;
				}
			}
			Thread.sleep(200);
		}

		return false;
	}

	public boolean findLogWarn(String message) throws Exception {
		int numMaxTentatives = 4;
		for (int i = 0; i < numMaxTentatives; i++) {
			Thread.sleep(200);
			List<String> logs = getLogList();
			for (String log : logs) {
				if (log.matches("^\\[UOL Tag Manager\\] WARN: .+$") && log.contains(message)) {
					return true;
				}
			}
		}
		return false;
	}
}

package uol.tm.stc.test.utils;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import uol.tm.stc.test.domain.PageContent;
import uol.tm.stc.test.domain.TmScript;
import uol.tm.stc.test.domain.SigaTag;
import uol.tm.stc.test.http.RemoteAdminTesterHttp;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

/**
 * 
 * @author dvrocha
 * 
 */
public class TestHelper {

	public static String getTmBody() throws Exception {
		return readResourceFile("tm-body.html");
	}

	public static String getTmHead() throws Exception {
		return readResourceFile("tm-head.html");
	}

	public static String readResourceFile(String fileName) throws Exception {
		URL file = Resources.getResource("tm-test/" + fileName);
		String fileContent = Resources.toString(file, Charsets.UTF_8).replace("\r\n", "");
		return fileContent;
	}

	public static void writePage(String template, TmScript tm, PageContent content) throws Exception {
		String pageContent = template.replace("#TM#", tm.toString());
		pageContent = pageContent.replace("#CONTENT#", content.toString());

		String command = "sudo echo \"" + pageContent.replace("\"", "\\\"") + "\" > /tmp/index.html;";
		command += "sudo mv /tmp/index.html /export/htdocs/test.tm.uol.com.br/;";

		RemoteAdminTesterHttp.shell(command);
	}

	public static void addUolCliquesTagInPageContent(PageContent page, SigaTag tag) {
		if (page.getUolcliquesTags() == null) {
			List<SigaTag> tags = new ArrayList<>();
			tags.add(tag);
			page.setUolcliquesTags(tags);
		} else {
			page.getUolcliquesTags().add(tag);
		}
	}
	
	public static void addUolCliquesConfig(TmScript tmScript, String key, String value) {
		Map<String, String> config = tmScript.getSigaConfig();
		tmScript.setSigaConfig(setMapEntry(config, key, value));
	}
	
	public static Map<String, String> setMapEntry(Map<String, String> map, String key, String value){
		if(map == null){
			map = new HashMap<String, String>();
			map.put(key, value);
		} else {
			map.put(key, value);
		}
		return map;
	}

	public static SigaTag getDefaultLpTag() {
		SigaTag tag = new SigaTag();
		tag.setId(getRandomId());
		tag.setClasses("tm-ads");

		Map<String, String> map = new HashMap<>();
		map.put("size", getRandomUolCliquesSize());
		tag.setProperties(map);

		return tag;
	}

	public static String getRandomId() {
		String[] charList = { "u", "o", "l", "t", "m", "2", "0", "1", "3" };
		String id = "";
		Random random = new Random();
		for (int i = 0; i < 7; i++) {
			id += charList[random.nextInt(charList.length)];
		}
		return id;
	}

	public static String getRandomUolCliquesSize() {
		Random random = new Random();
		return Constants.UOL_CLIQUES_SIZE_LIST[random.nextInt(Constants.UOL_CLIQUES_SIZE_LIST.length)];
	}
}

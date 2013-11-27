package uol.tm.stc.test.domain;

import java.util.Map;

/**
 * 
 * @author dvrocha
 * 
 */
public class TmScript {

	private final String domain = "//tm.uol.com.br";
	private final String config = "TM = {}; TM.Config = {};";

	private String group = "test";
	private String name = "default";
	
	private Map<String, String> sigaConfig;

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getSigaConfig() {
		return sigaConfig;
	}

	public void setSigaConfig(Map<String, String> sigaConfig) {
		this.sigaConfig = sigaConfig;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<script type=\"application/javascript\">");
		sb.append(config);

		if (sigaConfig != null) {
			sb.append("TM.Config.SIGA = {};");
			for (Map.Entry<String, String> entry : sigaConfig.entrySet()) {
				sb.append("TM.Config.SIGA.");
				sb.append(entry.getKey()).append("=");
				sb.append("'").append(entry.getValue()).append("'").append(";");
			}
		}

		sb.append("</script>");
		sb.append(getTmScriptTag());
		return sb.toString();
	}

	private String getTmScriptTag() {
		return String.format("<script type=\"application/javascript\" src=\"%s/%s/%s.js\"></script>", domain, group,
		        name);
	}
}

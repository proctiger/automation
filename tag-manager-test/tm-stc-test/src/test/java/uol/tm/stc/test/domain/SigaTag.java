package uol.tm.stc.test.domain;

import java.util.Map;

/**
 * 
 * @author dvrocha
 * 
 */
public class SigaTag {
	private String id;
	private String classes;
	private String adclientSrc;
	private Map<String, String> properties;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((classes == null) ? 0 : classes.hashCode());
	    result = prime * result + ((id == null) ? 0 : id.hashCode());
	    result = prime * result + ((properties == null) ? 0 : properties.hashCode());
	    return result;
    }

	@Override
    public boolean equals(Object obj) {
	    if (this == obj)
		    return true;
	    if (obj == null)
		    return false;
	    if (getClass() != obj.getClass())
		    return false;
	    SigaTag other = (SigaTag) obj;
	    if (classes == null) {
		    if (other.classes != null)
			    return false;
	    } else if (!classes.equals(other.classes))
		    return false;
	    if (id == null) {
		    if (other.id != null)
			    return false;
	    } else if (!id.equals(other.id))
		    return false;
	    if (properties == null) {
		    if (other.properties != null)
			    return false;
	    } else if (!properties.equals(other.properties))
		    return false;
	    return true;
    }

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("<div");

		if (id != null) {
			sb.append(" id=\"").append(id).append("\"");
		}

		if (classes != null) {
			sb.append(" class=\"").append(classes).append("\"");
		}

		if (properties != null) {
			sb.append(" siga=\"");
			for (Map.Entry<String, String> entry : properties.entrySet()) {
				sb.append(entry.getKey()).append(":");
				sb.append(entry.getValue()).append(";");
			}
			sb.append("\"");
		}

		sb.append("></div>");
		return sb.toString();
	}

	public String getAdclientSrc() {
	    return adclientSrc;
    }

	public void setAdclientSrc(String adclientSrc) {
	    this.adclientSrc = adclientSrc;
    }

}

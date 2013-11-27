package uol.tm.stc.test.domain;

import java.util.List;

/**
 * 
 * @author dvrocha
 *
 */
public class PageContent {

	private List<SigaTag> uolCliquesTags;

	public List<SigaTag> getUolcliquesTags() {
		return uolCliquesTags;
	}

	public void setUolcliquesTags(List<SigaTag> uolcliquesTags) {
		this.uolCliquesTags = uolcliquesTags;
	}

	@Override
    public String toString() {
		String pageContent = "";
		if(uolCliquesTags != null && uolCliquesTags.size() > 0){
			for(SigaTag uolCliquesTag : uolCliquesTags){
				pageContent += uolCliquesTag.toString();
			}
		}
	    return pageContent;
    }
}

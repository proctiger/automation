package uol.adserversap.ws.test.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include=Inclusion.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown=true)
public class AdvertiserDomain {

    private SapAdvertiserDomain sapAdvertiser;
    private DfpAdvertiserDomain dfpAdvertiser;

    public SapAdvertiserDomain getSapAdvertiser() {
        return sapAdvertiser;
    }
    public void setSapAdvertiser(SapAdvertiserDomain sapAdvertiser) {
        this.sapAdvertiser = sapAdvertiser;
    }

    public DfpAdvertiserDomain getDfpAdvertiser() {
        return dfpAdvertiser;
    }
    public void setDfpAdvertiser(DfpAdvertiserDomain dfpAdvertiser) {
        this.dfpAdvertiser = dfpAdvertiser;
    }
}

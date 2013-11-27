package uol.adserversap.ws.test.domain;

public class AgencyDomain {

    private SapAgencyDomain sapAgency;
    private DfpAgencyDomain dfpAgency;

    public SapAgencyDomain getSapAgency() {
        return sapAgency;
    }
    public void setSapAgency(SapAgencyDomain sapAgency) {
        this.sapAgency = sapAgency;
    }

    public DfpAgencyDomain getDfpAgency() {
        return dfpAgency;
    }
    public void setDfpAgency(DfpAgencyDomain dfpAgency) {
        this.dfpAgency = dfpAgency;
    }
}

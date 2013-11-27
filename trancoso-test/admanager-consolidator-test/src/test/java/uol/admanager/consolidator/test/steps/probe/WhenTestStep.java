package uol.admanager.consolidator.test.steps.probe;

import uol.admanager.consolidator.test.http.AdmanagerConsolidatorHttp;
import uol.admanager.consolidator.test.steps.view.TestStep;
import cucumber.api.java.pt.Quando;

public class WhenTestStep extends TestStep {

    @Quando("^for solicitada a URL do probe SLB$")
    public void slbProbe() throws Exception {
        probeResponse = AdmanagerConsolidatorHttp.probe("slb-probe");
    }

    @Quando("^for solicitada a URL do probe CHECK$")
    public void checkProbe() throws Exception {
        probeResponse = AdmanagerConsolidatorHttp.probe("check-probe");
    }

    @Quando("^for solicitada a URL do probe MONITOR$")
    public void monitorProbe() throws Exception {
        probeResponse = AdmanagerConsolidatorHttp.probe("monitor-probe");
    }
}

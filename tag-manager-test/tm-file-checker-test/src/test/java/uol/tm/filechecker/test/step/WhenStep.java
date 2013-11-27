package uol.tm.filechecker.test.step;

import cucumber.api.java.pt.Quando;
import uol.tm.filechecker.test.http.FileCheckerHttp;

/**
 *
 * @author dvrocha
 */
public class WhenStep extends BaseStep {

    @Quando("^for solicitada a requição da URL do <(.+)>$")
    public void senProbeRequest(String probeType) throws Exception {
        serviceResponse = FileCheckerHttp.getProbe(probeType);
    }
}

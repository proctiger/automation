package uol.bt.tracker.test.step.probe;

import uol.bt.tracker.test.http.TrackerHttp;
import uol.bt.tracker.test.step.BaseStep;
import cucumber.api.java.pt.Quando;

/**
 * 
 * @author dvrocha
 */
public class ProbeWhenStep extends BaseStep {

	@Quando("for requisitada a url de <(.+)>")
	public void requestProbe(String probeType) throws Exception {
		trackerResponse = TrackerHttp.probe(probeType);
	}
	
	@Quando("for requisitada a url de <(.+)> via ssl")
	public void requestProbeSsl(String probeType) throws Exception {
		trackerResponse = TrackerHttp.probe(probeType, true);
	}
}

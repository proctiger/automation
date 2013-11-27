package uol.bt.tracker.test.step.optout;

import uol.bt.tracker.test.http.TrackerHttp;
import uol.bt.tracker.test.step.BaseStep;
import cucumber.api.java.pt.Quando;

/**
 * 
 * @author dvrocha
 */
public class OptOutWhenStep extends BaseStep {

	@Quando("^for requisitada a url de opt-in$")
	public void requestOptIn() throws Exception {
		trackerResponse = TrackerHttp.optin(params);
	}

	@Quando("^for requisitada a url de opt-status$")
	public void requestOptStatus() throws Exception {
		trackerResponse = TrackerHttp.optstatus(params);
	}

	@Quando("^for requisitada a url de opt-out$")
	public void requestOptOut() throws Exception {
		trackerResponse = TrackerHttp.optout(params);
	}
	
	@Quando("^for requisitada a url de opt-in via ssl$")
	public void requestOptInSsl() throws Exception {
		trackerResponse = TrackerHttp.optin(params, true);
	}

	@Quando("^for requisitada a url de opt-status via ssl$")
	public void requestOptStatusSsl() throws Exception {
		trackerResponse = TrackerHttp.optstatus(params, true);
	}

	@Quando("^for requisitada a url de opt-out via ssl$")
	public void requestOptOutSsl() throws Exception {
		trackerResponse = TrackerHttp.optout(params, true);
	}
}

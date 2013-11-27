package uol.bt.tracker.test.step.partner;

import uol.bt.tracker.test.http.TrackerHttp;
import uol.bt.tracker.test.step.BaseStep;
import uol.bt.tracker.test.step.TestPrepare;
import cucumber.api.java.pt.Quando;

public class PartnerWhenStep extends BaseStep {
	
	@Quando("^for solicitada a requisição de trackeamento do usuário para parceiros$")
	public void sendTrackRequest() throws Exception {
		expectedEvents = TestPrepare.getExpectedEvents(params);
		trackerResponse = TrackerHttp.partner(params);
	}
	
	@Quando("^for solicitada a requisição de trackeamento do usuário para parceiros via ssl$")
	public void sendTrackRequestSsl() throws Exception {
		trackerResponse = TrackerHttp.partner(params, true);
	}
	
	@Quando("^for solicitada a requisição de trackeamento do usuário para parceiros para um usuário autenticado$")
	public void sendTrackRequestFotAuthUser() throws Exception {
		String action = "partner";
		expectedEvents.add(TestPrepare.getEvent("page_view", action, userId));
		expectedEvents.add(TestPrepare.getEvent("uol_session", action, userId));
		authAndtrack(action, userId);
	}	
}

package uol.bt.tracker.test.step.track;

import uol.bt.tracker.test.http.TrackerHttp;
import uol.bt.tracker.test.step.BaseStep;
import uol.bt.tracker.test.step.TestPrepare;
import cucumber.api.java.pt.Quando;

public class TrackWhenStep extends BaseStep {

	@Quando("^for solicitada a requisição de trackeamento do usuário$")
	public void sendTrackRequest() throws Exception {
		expectedEvents = TestPrepare.getExpectedEvents(params);
		trackerResponse = TrackerHttp.track(params);
	}

	@Quando("^for solicitada a requisição de trackeamento do usuário via ssl$")
	public void sendTrackRequestSsl() throws Exception {
		trackerResponse = TrackerHttp.track(params, true);
	}

	@Quando("^for solicitada a requisição de trackeamento para um usuário autenticado$")
	public void sendTrackRequestFotAuthUser() throws Exception {
		String action = "track";
		expectedEvents.add(TestPrepare.getEvent("page_view", action, userId));
		expectedEvents.add(TestPrepare.getEvent("uol_session", action, userId));
		authAndtrack(action, userId);
	}
}

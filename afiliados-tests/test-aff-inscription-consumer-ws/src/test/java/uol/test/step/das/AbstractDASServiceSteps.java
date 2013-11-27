package uol.test.step.das;

import com.uolinc.das.ws.client.service.SignupWS;

public abstract class AbstractDASServiceSteps {
	protected static final SignupWS subscriptionService;
	protected static final String INFO_TYPE = "namInscAdditInfoType";
	
	static {
		subscriptionService = SignupWS.getInstance("43", "43", 
				"http://das-webservice.sys.srv.intranet:8090/das-ws.html");
	}
}

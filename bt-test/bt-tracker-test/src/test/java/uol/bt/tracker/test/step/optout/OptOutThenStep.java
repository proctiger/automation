package uol.bt.tracker.test.step.optout;

import gherkin.deps.com.google.gson.Gson;

import org.junit.Assert;

import uol.bt.tracker.test.domain.CookieContent;
import uol.bt.tracker.test.domain.OptStatus;
import uol.bt.tracker.test.step.BaseStep;
import uol.bt.tracker.test.step.TestValidate;
import cucumber.api.java.pt.Entao;

/**
 * 
 * @author dvrocha
 * 
 */
public class OptOutThenStep extends BaseStep {

	@Entao("^o sistema retorna o status de opt-out igual a <(.+)>$")
	public void checkOptStatus(String status) throws Exception {
		OptStatus optStatus = parseResponse(trackerResponse.getBodyAsString());
		Assert.assertEquals(status, optStatus.getStatus());
	}

	@Entao("^não retorna nenhum motivo de opt-out$")
	public void checkOptStatusReasonNull() throws Exception {
		OptStatus optStatus = parseResponse(trackerResponse.getBodyAsString());
		Assert.assertNull(optStatus.getReason());
	}

	@Entao("^o sistema retorna o motivo de opt-out igual a <(.+)>$")
	public void checkOptStatusReason(String reason) throws Exception {
		OptStatus optStatus = parseResponse(trackerResponse.getBodyAsString());
		Assert.assertEquals(reason, optStatus.getReason());
	}

	@Entao("^o sistema planta o cookie de OPT-OUT$")
	public void checkOutOutCookie() throws Exception {
		final CookieContent bttrk = TestValidate.extractBttrkFrom(trackerResponse);
		Assert.assertNotNull("Cookie DNT não foi plantado", bttrk);
		Assert.assertEquals("bt.uol.com.br", bttrk.getDomain());
		Assert.assertEquals("DNT", bttrk.getValue());
		TestValidate.validateCookieBttrkExpiration(bttrk);
	}

	private OptStatus parseResponse(String json) throws Exception {
		OptStatus optstatus = new OptStatus();
		Gson gson = new Gson();
		optstatus = gson.fromJson(json, OptStatus.class);
		return optstatus;
	}
}

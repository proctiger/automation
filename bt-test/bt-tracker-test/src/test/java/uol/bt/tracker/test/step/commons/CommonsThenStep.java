package uol.bt.tracker.test.step.commons;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import uol.bt.tracker.test.domain.CookieContent;
import uol.bt.tracker.test.domain.TrackEvent;
import uol.bt.tracker.test.step.BaseStep;
import uol.bt.tracker.test.step.TestPrepare;
import uol.bt.tracker.test.step.TestValidate;
import cucumber.api.java.pt.Entao;

/**
 * 
 * @author dvrocha
 */
public class CommonsThenStep extends BaseStep {

	@Entao("o sistema retorna o código http <(.*)>")
	public void checkHttpCode(int status) {
		Assert.assertEquals("Codigo HTTP retornado esta incorreto", status, trackerResponse.getStatusCode());
	}

	@Entao("^o sistema planta um novo cookie BTTRK para o usuário$")
	public void checkNewBttrk() throws Exception {
		final CookieContent bttrk = TestValidate.extractBttrkFrom(trackerResponse);
		Assert.assertNotNull("Cookie BTTRK não foi plantado", bttrk);
		TestValidate.validateCookieBttrk(bttrk);
	}

	@Entao("^o sistema planta o cookie BTTRK mantendo o ID do usuário$")
	public void checkModifiedBttrk() throws Exception {
		final CookieContent bttrk = TestValidate.extractBttrkFrom(trackerResponse);
		Assert.assertNotNull("Cookie BTTRK não foi plantado", bttrk);
		TestValidate.validateCookieBttrk(params.getBttrk(), bttrk);
	}

	@Entao("^o sistema não planta o cookie BTTRK$")
	public void checkNullBttrk() throws Exception {
		final CookieContent bttrk = TestValidate.extractBttrkFrom(trackerResponse);
		Assert.assertNull("Cookie BTTRK foi plantado indevidamente", bttrk);
	}

	@Entao("^o sistema não registra em log os eventos da requisição$")
	public void checkNotLoggedEvents() throws Exception {
		Assert.assertEquals(oldEvents, TestPrepare.getCurrEvents(true));
	}

	@Entao("^o sistema registra em log os eventos da requisição$")
	public void checkLoggedEvents() throws Exception {
		String uuid = null;
		if (params != null && params.getBttrk() != null) {
			uuid = TestPrepare.getUuid(params.getBttrk());
		} else {
			CookieContent bttrk = TestValidate.extractBttrkFrom(trackerResponse);
			Assert.assertNotNull("Cookie BTTRK não foi plantado", bttrk);
			uuid = TestPrepare.getUuid(bttrk);
		}
		
		List<TrackEvent> newEvents = TestPrepare.getEventsByUuid(uuid);
		Assert.assertEquals("Quantidade de eventos está incorreta", 1, newEvents.size());

		for (TrackEvent event : expectedEvents) {
			event.setUserId(uuid);
		}
		Assert.assertEquals("Evento gerado está incorreto", expectedEvents, newEvents);

		for (TrackEvent event : newEvents) {
			TestValidate.validateEventDate(event);
		}
	}

	@Entao("^o sistema registra em log os eventos do usuário autenticado$")
	public void checkLoggedEventsFromAuthUser() throws Exception {
		List<TrackEvent> newEvents = TestPrepare.getCurrEvents(true);
		newEvents.removeAll(oldEvents);
		Assert.assertEquals("Quantidade de eventos está incorreta", 2, newEvents.size());

		String uuid = null;
		if (!StringUtils.isEmpty(userId)) {
			uuid = userId;
		} else {
			List<TrackEvent> events = new ArrayList<>(newEvents);
			uuid = events.get(0).getUserId();
		}

		for (TrackEvent event : expectedEvents) {
			event.setUserId(uuid);
			boolean found = false;
			for(TrackEvent newEvent : newEvents){
				if(newEvent.getEventId().equals(event.getEventId())){
					found = true;
					Assert.assertEquals("Evento registrado está incorreto", event, newEvent);
					
					TestValidate.validateEventDate(newEvent);
					
					if (newEvent.getEventId().equals("uol_session")) {
						Assert.assertNotNull("Evento de perfil não possui valor do cookie", newEvent.getCookieValue());
						Assert.assertTrue("Valor do cookie está incorreto: " + newEvent.getCookieValue(), newEvent.getCookieValue()
						        .matches("^[a-f0-9]+$"));
					}
				}
			}
			Assert.assertTrue("Evento não foi encontrado: " + event, found);
		}
	}
	
	@Entao("^o sistema não registra em log os eventos do usuário autenticado$")
	public void checkNotLoggedEventsFromAuthUser() throws Exception {
		checkNotLoggedEvents();
	}
}

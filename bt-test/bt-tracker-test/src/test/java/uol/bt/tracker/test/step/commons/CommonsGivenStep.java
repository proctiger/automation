package uol.bt.tracker.test.step.commons;

import java.util.ArrayList;
import java.util.Calendar;

import reconf.client.proxy.ConfigurationRepositoryFactory;
import uol.bt.tracker.test.domain.CookieContent;
import uol.bt.tracker.test.domain.TrackEvent;
import uol.bt.tracker.test.reconf.ConfigBtTracker;
import uol.bt.tracker.test.step.BaseStep;
import uol.bt.tracker.test.step.TestPrepare;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;

/**
 *
 * @author dvrocha
 *
 */
public class CommonsGivenStep extends BaseStep {

	private static final ConfigBtTracker config = ConfigurationRepositoryFactory.get(ConfigBtTracker.class);

	@Before
	public void before() throws Exception {
		params = TestPrepare.getDefaultparams();
		expectedEvents = new ArrayList<TrackEvent>();
		if(!self){
			oldEvents = TestPrepare.getCurrEvents();
		}
		userId = null;
	}

	@Dado("^que o usuário possui o cookie de OPT-OUT$")
	public void setOptOutCookie() {
		params.setBttrk(new CookieContent("bt.uol.com.br", "BTTRK", "DNT", "/", null));
	}

	@Dado("^que o usuário possui o browser com DNT ativo$")
	public void setDntOn() {
		params.setDnt("1");
	}

	@Dado("^que o usuário possui o browser com DNT inativo$")
	public void setDntOff() {
		params.setDnt("0");
	}

	@Dado("^que o usuário já possui o cookie BTTRK$")
	public void setCookieBttrk() throws Exception {
		params.setBttrk(TestPrepare.getCookieBttrk());
	}

	@Dado("^que o usuário possui um cookie BTTRK inválido$")
	public void setCookieBttrkInvalid() throws Exception {
		params.setBttrk(TestPrepare.getInvalidCookieBttrk());
	}

	@Dado("^que o usuário possui um cookie BTTRK mais recente do que o necessário para ser atualizado$")
	public void setCookieBttrkWithRecentDate() throws Exception{
		String cookieDate = TestPrepare.getBttrkDate();
		params.setBttrk(TestPrepare.getCookieBttrk(cookieDate));
	}

	@Dado("^que o usuário possui um cookie BTTRK que atinja a data necessária para ser atualizado$")
	public void setCookieBttrkWithDateToUpdate() throws Exception{
		int secoundsToSubstracting = config.getMinimumSecoundsFromLastVisit() + 60;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, secoundsToSubstracting * -1);
		String cookieDate = TestPrepare.getBttrkDate(calendar.getTime());
		params.setBttrk(TestPrepare.getCookieBttrk(cookieDate));
	}

	@Dado("^que o usuário possui um cookie BTTRK sem data definida$")
	public void setCookieBttrkWithoutDate() throws Exception{
		params.setBttrk(TestPrepare.getCookieBttrk(null));
	}

	@Dado("^que o usuário possui um cookie BTTRK cuja a data refere-se ao ano anterior$")
	public void setCookieBttrkWithDateFromLastYear() throws Exception{
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -1);
		String cookieDate = TestPrepare.getBttrkDate(calendar.getTime());
		params.setBttrk(TestPrepare.getCookieBttrk(cookieDate));
	}

	@Dado("^que o usuário possui um cookie BTTRK cuja a data refere-se ao mês anterior$")
	public void setCookieBttrkWithDateFromLastMonth() throws Exception{
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		String cookieDate = TestPrepare.getBttrkDate(calendar.getTime());
		params.setBttrk(TestPrepare.getCookieBttrk(cookieDate));
	}

	@Dado("^que o usuário possui um cookie BTTRK cuja a data refere-se ao dia anterior$")
	public void setCookieBttrkWithDateFromLastDay() throws Exception{
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		String cookieDate = TestPrepare.getBttrkDate(calendar.getTime());
		params.setBttrk(TestPrepare.getCookieBttrk(cookieDate));
	}

	@Dado("^que o usuário possui um cookie BTTRK cuja a data refere-se à hora anterior$")
	public void setCookieBttrkWithDateFromLastHour() throws Exception{
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, -1);
		String cookieDate = TestPrepare.getBttrkDate(calendar.getTime());
		params.setBttrk(TestPrepare.getCookieBttrk(cookieDate));
	}

	@Dado("^que o usuário possui um cookie BTTRK com data no futuro$")
	public void setCookieBttrkWithFutureDate() throws Exception{
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 5);
		String cookieDate = TestPrepare.getBttrkDate(calendar.getTime());
		params.setBttrk(TestPrepare.getCookieBttrk(cookieDate));
	}

	@Dado("^que o usuário possui um cookie BTTRK com data inválida$")
	public void setCookieBttrkWithInvalidDate() throws Exception{
		params.setBttrk(TestPrepare.getCookieBttrk("55115"));
	}

	@Dado("^que a requisição ao sistema é proveniente de um robô$")
	public void setRobotUserAgent() throws Exception{
		params.setUserAgent("Googlebot 2.1");
	}

	@Dado("^que a requisição ao sistema não possui um referer$")
	public void setNullReferer() throws Exception{
		params.setReferer(null);
	}

	@Dado("^que a requisição ao sistema possui um referer existente na blacklist$")
	public void setBlacklistReferer() throws Exception{
		params.setReferer(String.valueOf(TestPrepare.getRandomObject(config.getReferersBlacklist())));
	}

	@Dado("^que o usuário autenticado possui o cookie de OPT-OUT$")
	public void setUserIdToDnt() throws Exception{
		userId = "DNT";
	}

	@Dado("^que o usuário autenticado já possui o cookie BTTRK$")
	public void setUserId() throws Exception{
		userId = TestPrepare.getCookieBttrk().getValue().substring(0,32);
	}
}

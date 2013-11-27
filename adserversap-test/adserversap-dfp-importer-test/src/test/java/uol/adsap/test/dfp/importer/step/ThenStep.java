package uol.adsap.test.dfp.importer.step;

import java.util.*;
import org.apache.commons.lang.*;
import junit.framework.*;
import uol.adsap.test.dfp.importer.dao.*;
import uol.adsap.test.dfp.importer.http.*;
import uol.simple.httpclient.*;
import cucumber.api.java.pt.*;

/**
 *
 * @author dvrocha
 *
 */
public class ThenStep extends BaseStep {

	@Então("^o sistema registra no log da aplicação o seguinte erro: <(.+)>$")
	public void checkErrorMessage(String message) throws Exception {
		SimpleHttpResponse logFile = RemoteTestHttp.cat("/export/logs/adserversap-dfp-importer/adserversap-dfp-importer.log");
		Assert.assertTrue(logFile.getBodyAsString().contains(message));
	}

	@Então("^o sistema registra no log de alarme o seguinte erro: <(.+)>$")
	public void checkAlarmMessage(String message) throws Exception {
		SimpleHttpResponse logFile = RemoteTestHttp.cat("/export/logs/adserversap-dfp-importer/adserversap-dfp-importer-alarm.log");
		Assert.assertTrue(logFile.getBodyAsString().contains(message));
	}

	@Então("^o sistema importa <(.+)> registros do dfp$")
	public void checkStats(String message) throws Exception {
	    List<String> lines = LineItemDao.selectCsvFromLineItemConsolidate();
	    Assert.assertEquals(Integer.parseInt(message), lines.size());

	    for (int i = 0; i < csv.size(); i++) {
	        Assert.assertTrue(StringUtils.equalsIgnoreCase(lines.get(i), csv.get(i)));
	    }
	}

	@Então("^o sistema registra a execução na tabela de histórico$")
	public void checkExecution() throws Exception {
	    List<String> lines = JobControlDao.selectStatusFromJobControl();
	    Assert.assertEquals(2, lines.size());
	    Assert.assertTrue(lines.contains("I"));
	    Assert.assertTrue(lines.contains("F"));
	}
}

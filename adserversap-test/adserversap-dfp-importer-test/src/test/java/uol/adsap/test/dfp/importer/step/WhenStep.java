package uol.adsap.test.dfp.importer.step;

import uol.adsap.test.dfp.importer.http.RemoteTestHttp;
import uol.simple.httpclient.SimpleHttpResponse;
import cucumber.api.java.pt.Quando;

/**
 *
 * @author dvrocha
 *
 */
public class WhenStep extends BaseStep {

	@Quando("^o dfp-importer for iniciado$")
	public void startJob() throws Exception {
		logger.debug("Executando comando para executar job");
		if(isJobRunning()){
			waitJobStop();
		}
		SimpleHttpResponse response = RemoteTestHttp.shell("sh /opt/adserversap-dfp-importer/scripts/adserversap-dfp-importer.sh start -mock");
		if(response.getStatusCode() != 200) {
			String message = "Nao foi possivel iniciar o job";
			logger.warn(message);
			throw new Exception(message);
		}
		waitJobStop();
	}

	private void waitJobStop() throws Exception {
		int maxSecoundsToWait = 300;
		for(int i = 1;i <= maxSecoundsToWait;i++){
			logger.debug("Aguardando termino do job");
			if(!isJobRunning()){
				Thread.sleep(5000);
				break;
			}
			if(i == maxSecoundsToWait) {
				String message = "O job nao terminou no tempo maximo esperado";
				logger.warn(message);
				throw new Exception(message);
			}
			Thread.sleep(1000);
		}
	}

	private boolean isJobRunning() throws Exception {
		SimpleHttpResponse response = RemoteTestHttp.shell("ps -ef | grep java | grep adserversap-dfp-importer | grep -v grep");
		return response.getBodyAsString().contains("/opt/adserversap-dfp-importer/");
	}
}

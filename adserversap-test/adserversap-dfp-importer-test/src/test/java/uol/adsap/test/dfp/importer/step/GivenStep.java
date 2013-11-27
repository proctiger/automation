package uol.adsap.test.dfp.importer.step;

import java.security.*;
import java.text.*;
import java.util.*;
import org.apache.commons.lang.*;
import org.joda.time.*;
import uol.adsap.test.dfp.importer.dao.*;
import uol.adsap.test.dfp.importer.http.*;
import uol.simple.httpclient.SimpleHttpResponse;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.deps.difflib.*;

/**
 *
 * @author dvrocha
 *
 */
public class GivenStep extends BaseStep {

    private static final SecureRandom random = new SecureRandom();
    private static final String HEADER = "Dimension.LINE_ITEM_ID,Dimension.DATE,Column.AD_SERVER_IMPRESSIONS,Column.AD_SERVER_CLICKS\n";

    @Before
    public void before() throws Exception {
        RemoteTestHttp.shell("rm /export/logs/adserversap-dfp-importer/*.log");
        JobControlDao.deleteFromJobControl();
        setHourToExecution();
        ConfigWsHttp.removeProperty("start.minus.days");
        ConfigWsHttp.removeProperty("end.minus.days");
        DfpMockerHttp.deleteReportDownload();
        DfpMockerHttp.deleteGetReportService();
        DfpMockerHttp.deleteRunReportService();
    }

    @Dado("^que não está na hora de execução do sistema$")
    public void setDifferentHourToExecution() throws Exception {
        int currHour = getCurrHour();
        if (currHour == 12 || currHour == 11) {
            setHourToExecution(10);
        } else {
            setHourToExecution(12);
        }
    }

    @Dado("^que existe um processo de execução (iniciado|finalizado) a (mais|menos) de 2 horas$")
    public void insertExecutionProcess(String status, String moreOrLess)
                    throws Exception {
        String date = moreOrLess.equals("mais") ? "sysdate-(3/24)" : "sysdate-(1/24)";
        status = status.equals("iniciado") ? "I" : "F";
        JobControlDao.insertIntoJobControl(date, "AdServerSAP-DFP-Importer", "a1-adserversap-s-prt2", status);
    }

    @Dado("^que a data de (início|fim) do relatório esteja configurada (com um número negativo|como zero)$")
    public void setPeriodToSearch(String startOrEnd, String number) throws Exception {
        String property = startOrEnd.equals("inicio") ? "start.minus.days" : "end.minus.days";
        String value = number.equals("como zero") ? "0" : "-1";

        updateConfiguration(property, value);
    }

    @Dado("^que a data de fim do relatório seja maior que a data de início$")
    public void setPeriodToSearch() throws Exception {
        updateConfiguration("start.minus.days", 1);
        updateConfiguration("end.minus.days", 2);
    }

    @Dado("^que o intervalo de datas considerado pelo sistema comece em <D(.+)> e termine em <D(.+)>$")
    public void setPeriodToSearch(int start, int end) throws Exception {
        updateConfiguration("start.minus.days", Math.abs(start));
        updateConfiguration("end.minus.days", Math.abs(end));
    }

    @Dado("^existem dados para o intervalo que comeca em <D(.+)> e termina em <D(.+)>$")
    public void setRecordsToReturn(int start, int end) throws Exception {
        csv.clear();
        LineItemDao.deleteFromLineItemConsolidate();
        DateTime now = new DateTime();
        String tpl = "%s,%s,%s,%s,%s";

        if (start == 0 && end == 0) {
            DfpMockerHttp.putReportDownload(HEADER);
            return;
        }

        List<String> lines = new ArrayList<>();
        for (int i = Math.abs(end); i <= Math.abs(start); i++) {
            //ID, Data, Impression, Click
            lines.add(String.format(tpl, random.nextInt(10000000), random.nextInt(10000000), new SimpleDateFormat("yyyy-MM-dd").format(now.minusDays(i).getMillis()), random.nextInt(10000000), random.nextInt(10000000)));
        }
        csv.addAll(lines);
        DfpMockerHttp.putReportDownload(HEADER + StringUtills.join(csv, "\n"));
    }

    @Dado("^o dfp nao retorne o relatorio dentro da quantidade de retentativas configuradas$")
    public void setRetryError() throws Exception {
        updateConfiguration("dfp.max.try", "0");
    }

    @Dado("^o dfp demore <(.+)> segundos para retornar o status do relatorio$")
    public void setReportStatusDelay(String seconds) throws Exception {
        updateConfiguration("dfp.max.try", "1");
        DfpMockerHttp.putSlowReportDownload(Integer.valueOf(seconds) * 1000);
    }

    @Dado("^o dfp retorne erro quando requisitar o relatorio$")
    public void setReportStatusError() throws Exception {
        updateConfiguration("dfp.max.try", "1");
        DfpMockerHttp.putReportService("getReportJob_500");
    }

    @Dado("^o sistema faz <(.+)> tentativas para obter o status do relatorio$")
    public void setMaxTry(int attempts) throws Exception {
        updateConfiguration("dfp.max.try", attempts);
    }

    @Dado("^o dfp devolve o status do relatorio como <(.+)> por <(.+)> vezes$")
    public void addGetReportService(String status, int times) throws Exception {
        if (StringUtils.equalsIgnoreCase(status, "inProgress")) {
            for (int i = 0; i < times; i++) {
                DfpMockerHttp.putReportServiceInProgress();
            }
        }

        if (StringUtils.equalsIgnoreCase(status, "completed")) {
            for (int i = 0; i < times; i++) {
                DfpMockerHttp.putReportServiceComplete();
            }
        }
    }

    private void updateConfiguration(String name, Object value) {
        SimpleHttpResponse response = null;
        try {
            response = ConfigWsHttp.putProperty(name, String.format("'%s'", value.toString()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (response != null && response.getStatusCode() != 204) {
            throw new RuntimeException("Configuração não foi atualizada");
        }
    }

    private void waitHour() throws Exception {
        while (true) {
            SimpleHttpResponse response = RemoteTestHttp.shell("date +%M");
            int currMinute = Integer.valueOf(response.getBodyAsString());
            if (currMinute != 59) {
                break;
            }
            Thread.sleep(10000);
        }
    }

    private int getCurrHour() throws Exception {
        SimpleHttpResponse response = RemoteTestHttp.shell("date +%H");
        return Integer.valueOf(response.getBodyAsString());
    }

    private void setHourToExecution() throws Exception {
        waitHour();
        setHourToExecution(getCurrHour());
    }

    private void setHourToExecution(int hour) throws Exception {
        SimpleHttpResponse response = ConfigWsHttp.putProperty(
                        "hours.execution", "['" + hour + "']");
        if (response.getStatusCode() != 204) {
            throw new Exception("Configuração não foi atualizada");
        }
    }
}
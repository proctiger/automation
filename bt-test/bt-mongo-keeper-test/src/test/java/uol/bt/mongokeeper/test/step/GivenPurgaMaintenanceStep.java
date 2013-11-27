package uol.bt.mongokeeper.test.step;

import java.util.Calendar;
import java.util.Locale;

import uol.bt.mongokeeper.test.util.ConfigWsHttp;
import cucumber.api.java.pt.Dado;

/**
 * 
 * @author wrodrigues
 * 
 */
public class GivenPurgaMaintenanceStep extends BaseStep {
	
	@Dado("^mongo desativado no Remote Config$")
	public void setMongoActiveFalse() throws Exception {
		ConfigWsHttp.putProperty("mongo.active", "'false'");
	}
	
	@Dado("^mongo ativado no Remote Config$")
	public void setMongoActiveTrue() throws Exception {
		ConfigWsHttp.putProperty("mongo.active", "'true'");
	}
	
	@Dado("^horario atual fora da tabela de manutencao do mongo$")
	public void setMongoMaintenanceTableDateTomorrow() throws Exception {
		final Calendar now = Calendar.getInstance(new Locale("pt", "BR"));
		now.add(Calendar.DATE, +1);
        final String dayOfWeek = now.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US).toUpperCase();
        final int hourOfDay = now.get(Calendar.HOUR_OF_DAY);
        
        String property = "mongo.maintenance.table";
        String value = String.format("['%s':['%s','%s','%s']]", dayOfWeek, hourOfDay, hourOfDay+1, hourOfDay+2);
        
		ConfigWsHttp.putProperty(property, value);
	}

	@Dado("^horario atual dentro da tabela de manutencao do mongo$")
	public void setMongoMaintenanceTableDate() throws Exception {
		final Calendar now = Calendar.getInstance(new Locale("pt", "BR"));
		
        final String dayOfWeek = now.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US).toUpperCase();
        final int hourOfDay = now.get(Calendar.HOUR_OF_DAY);
        
        String property = "mongo.maintenance.table";
        String value = String.format("['%s':['%s','%s','%s']]", dayOfWeek, hourOfDay, hourOfDay+1, hourOfDay+2);
        
		ConfigWsHttp.putProperty(property, value);
	}
}

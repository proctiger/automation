package uol.test.step.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;

import uol.test.step.AbstractTestStep;
import uol.test.util.PayofficeEvent;

/* 
 * @author tsantos
 */

public abstract class AbstractLocalServiceSteps extends AbstractTestStep {

protected static float  actualNumBalance;
protected static PayofficeEvent payofficeEvent;
protected static long priorSize = -1;

	public void setActualNumBalance(float actualNumBalance){
		this.actualNumBalance = actualNumBalance;
	}
	


	protected long sqNextVal(String sqName) {
		PreparedStatement statement = null;
		ResultSet rs = null;
		Connection conn;
		try {
			conn = getConnectionUol3();
			statement = conn.prepareStatement("SELECT " + sqName
					+ ".nextval FROM DUAL");
			rs = statement.executeQuery();
			if (rs.next()) {
				return rs.getLong("NEXTVAL");
			}
		} catch (SQLException e) {
			Assert.fail(String.format(
					"\n Erro ao encontrar a sequence da tabela %s a: %s",
					sqName, e.getLocalizedMessage()));
		}
		return 0;
	}
	
	protected String selectCafByIdtPerson(String idtPerson){
		PreparedStatement statement = null;
		ResultSet rs = null;
    	Connection conn;
    	 try {
         	conn = getConnectionUol3();
         	statement = conn.prepareStatement("SELECT COD_DISPLAY_SUPPLIER FROM AFFILIATED_ADM.USER_SUPPLIER WHERE IDT_PERSON = ?");
         	statement.setString(1,idtPerson);
         	rs = statement.executeQuery();
         	if(rs.next()){
         		return rs.getString("COD_DISPLAY_SUPPLIER");
         	}else{
 				Assert.fail(String.format("\n Não foi possível encontrar o caf do afiliado %s na tabela supplier", idtPerson));
         	}
         	
 			} catch (SQLException e) {
 				Assert.fail(String.format("\n Erro ao tentar apagar o evento de indicacao da tabela de extrato devido a: %s", e.getLocalizedMessage()));
 				}
		return null;	    	 
	}


	public void startJobNow() {//////////////////////
		startJob("affiliated-event-processor.sh", "startwait",
				"affiliated-event-processor", "a1-zumbi-afil-s-prt1");
	}

	public void stopComponentNow(String applicationName, String container,
			String host, String pwd) {
		stopApplication(applicationName, container, host, pwd);

	}



	public void startComponentNow(String applicationName, String container,//////////////////////
			String host, String pwd) {
		startAndWaitApplication(applicationName, container, host, pwd);

	}

}
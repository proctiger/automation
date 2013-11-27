package uol.test.step;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Assert;

import uol.test.util.BaseFactory;
import uol.test.util.SshUtil;



public abstract class AbstractTestStep {

	//BaseFactory factory = new BaseFactory();
	
    public void closeResources(PreparedStatement statement, ResultSet rs) {
    	try {
    		if(statement != null){
			statement.close();
    		}
    		if(rs != null){
			rs.close();
    		}
		} catch (SQLException e) {
			System.out.println(" Erro ao fechar conexão");
		}
	}
    
    protected Connection getConnectionUol3(){
       return BaseFactory.getConnectionUol3();
    }
    
    protected Connection getConnectionUol7(){
    	  return BaseFactory.getConnectionUol7();
    }
    
    protected void stopApplication(String applicationName, String container, String host)  throws Exception {
        System.out.println("Stoping " + applicationName + " ....");
        SshUtil.exec(host, applicationName, container, "stop");
        System.out.println("Stopped " + applicationName + ".");
    }
    
    protected void startAndWaitApplication(String applicationName, String container, String host)  throws Exception {
        System.out.println("Starting " + applicationName + " ....");
        SshUtil.exec(host, applicationName, container, "start");
        System.out.println("Started " + applicationName + ".");
    }
    
    
}

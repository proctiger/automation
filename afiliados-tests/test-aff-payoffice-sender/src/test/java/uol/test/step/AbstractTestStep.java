package uol.test.step;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;

import uol.test.util.BaseFactory;
import uol.test.util.SshUtil;




public abstract class AbstractTestStep {
	
	BaseFactory factory = new BaseFactory();

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
    
    protected void stopApplication(String applicationName, String container, String host,String pwd){
        System.out.println("Stoping " + applicationName + " ....");
        try {
			SshUtil.exec(host, applicationName, container, "stop",pwd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Assert.fail(String.format("\n Erro ao tentar parar o " +applicationName, e.getLocalizedMessage()));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			Assert.fail(String.format("\n Erro ao tentar parar o " +applicationName, e.getLocalizedMessage()));		
			}
        System.out.println("Stopped " + applicationName + ".");
    }
    
    protected void startAndWaitApplication(String applicationName, String container, String host,String pwd){
        System.out.println("Starting " + applicationName + " ....");
        try {
        SshUtil.exec(host, applicationName, container, "start",pwd);
        } catch (IOException e) {
        	Assert.fail(String.format("\n Erro ao tentar parar o " +applicationName, e.getLocalizedMessage()));
		} catch (InterruptedException e) {
        Assert.fail(String.format("\n Erro ao tentar parar o " +applicationName, e.getLocalizedMessage()));		
	}
        System.out.println("Started " + applicationName + ".");
    }
    
    protected void stopJob(String jobFileName, String action, String application, String host)  throws Exception {
        System.out.println("Stopping " + application + " ....");
        SshUtil.execJob(host, application, action, jobFileName);
        System.out.println("Stopped " + application + ".");
    }
    
    protected void startJob(String jobFileName, String action, String application, String host){
        System.out.println("Starting " + application + " ....");
        try {
			SshUtil.execJob(host, application, action, jobFileName);
		} catch (IOException e) {
			Assert.fail(String.format("\n Erro ao tentar executar o " +application, e.getLocalizedMessage()));
		} catch (InterruptedException e) {
			Assert.fail(String.format("\n Erro ao tentar executar o " +application, e.getLocalizedMessage()));
		}
        System.out.println("Started " + application + ".");
    }
  
    
    
}

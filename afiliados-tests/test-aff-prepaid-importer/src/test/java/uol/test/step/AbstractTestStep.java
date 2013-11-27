package uol.test.step;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Assert;

import uol.test.util.SshUtil;



public abstract class AbstractTestStep {
    private BasicDataSource dataSourceUol3;

    {
        BasicDataSource dataSourceUol3 = new BasicDataSource();
        dataSourceUol3.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSourceUol3.setUrl("jdbc:oracle:thin:@uol3.bd.sys.intranet:1521:uol3");
        dataSourceUol3.setUsername("desenvolvimento");
        dataSourceUol3.setPassword("desenvolvimento");
        dataSourceUol3.setMaxActive(20);
        dataSourceUol3.setMaxIdle(20);
        dataSourceUol3.setInitialSize(10);
        dataSourceUol3.setMaxWait(5000);
        dataSourceUol3.setValidationQuery("select 1 from dual");
        dataSourceUol3.setTimeBetweenEvictionRunsMillis(5000);
        this.dataSourceUol3 = dataSourceUol3;
    }
    
    private BasicDataSource dataSourceUol7;

    {
        BasicDataSource dataSourceUol7 = new BasicDataSource();
        dataSourceUol7.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSourceUol7.setUrl("jdbc:oracle:thin:@uol7.bd.sys.intranet:1521/cadastro.app");
        dataSourceUol7.setUsername("desenvolvimento");
        dataSourceUol7.setPassword("desenvolvimento");
        dataSourceUol7.setMaxActive(20);
        dataSourceUol7.setMaxIdle(20);
        dataSourceUol7.setInitialSize(10);
        dataSourceUol7.setMaxWait(5000);
        dataSourceUol7.setValidationQuery("select 1 from dual");
        dataSourceUol7.setTimeBetweenEvictionRunsMillis(5000);
        this.dataSourceUol7 = dataSourceUol7;
    }
    
    protected Connection getConnectionUol3(){
        try {
            return this.dataSourceUol3.getConnection();
        } catch (SQLException e) {
            Assert.fail("Falha ao tentar obter a conexao com o banco de dados. Stack > " + e );
            return null;
        }
    }
    
    protected Connection getConnectionUol7(){
        try {
            return this.dataSourceUol7.getConnection();
        } catch (SQLException e) {
            Assert.fail("Falha ao tentar obter a conexao com o banco de dados.");
            return null;
        }
    }
    
    public void stopApplication(String applicationName, String container, String host)  throws Exception {
        System.out.println("Stoping " + applicationName + " ....");
        SshUtil.exec(host, applicationName, container, "stop");
        System.out.println("Stopped " + applicationName + ".");
    }
    
    public void startAndWaitApplication(String applicationName, String container, String host)  throws Exception {
        System.out.println("Starting " + applicationName + " ....");
        SshUtil.exec(host, applicationName, container, "start");
        System.out.println("Started " + applicationName + ".");
    }

}

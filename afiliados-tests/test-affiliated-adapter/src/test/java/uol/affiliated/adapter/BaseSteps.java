package uol.affiliated.adapter;

import java.sql.Connection;
import java.sql.SQLException;
import junit.framework.Assert;

import org.apache.commons.dbcp.BasicDataSource;

public abstract  class BaseSteps {
	
	protected static final String SELECT_COUNT_JMS_MESSAGES = "select count(*) as total from JMS_MESSAGES";
	
	protected static final String SELECT_COUNT_FLG_QUEUE = "select count(*) as total from COMMISSION_EVENT_LINKS where FLG_AFF_QUEUE = 0";
	
	protected static final String UPDATE_FLG_QUEUE_ZERO = "update COMMISSION_EVENT_LINKS set FLG_AFF_QUEUE = 1 where IDT_COMMISSION_EVENT > 0";
	
	protected static final int ATTEMPS = 5;
	
	protected Connection conn;
	
	protected java.sql.Statement stmt;
	
	protected java.sql.ResultSet rs;
		
	protected int result = 0;
	
	protected int result2 = 0;
	
	protected int cont = 0;
	
	protected int last_result = 0;
	
	protected int at_queue = 0;
	
	private BasicDataSource dataSource;

    {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@a1-oasis-s-prt4:1521:uol3");
        dataSource.setUsername("AFF_ADAPTER_JBOSSUBR");
        dataSource.setPassword("ed76c7b1a10370d39f30");
        dataSource.setMaxActive(20);
        dataSource.setMaxIdle(20);
        dataSource.setInitialSize(10);
        dataSource.setMaxWait(5000);
        dataSource.setValidationQuery("select 1 from dual");
        dataSource.setTimeBetweenEvictionRunsMillis(5000);
        this.dataSource = dataSource;
    }
    
    protected Connection getConnection(){
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
        	e.printStackTrace();
            Assert.fail("Falha ao tentar obter a conexao com o banco de dados.");
            return null;
        }
    }

}

package uol.affiliated.commons.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Assert;

public class DBUtil {

	private BasicDataSource dataSourceUol3;
	private BasicDataSource dataSourceUol7;
	private Connection connectionUol3 = null;
	private Connection connectionUol7 = null;

	public DBUtil() {
		createDataSourceUol3();
		createDataSourceUol7();
	}

	public DBUtil createNew() {
		return new DBUtil();
	}
	
	public void closeResources(PreparedStatement statement, ResultSet rs) {
    	try {
    		if(statement != null){
			statement.close();
    		}
    		if(rs != null){
			rs.close();
    		}
		} catch (SQLException e) {
			System.out.println(" Erro ao fechar conexÃ£o");
		}
	}

	public void closeConnectionUol3(){
		try {
			if(connectionUol3 != null && !connectionUol3.isClosed()){
				connectionUol3.close();
				System.out.println("UOL3 Fechado.");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao Tentar Fechar a conexão com o banco!");
		}
	}

	public void closeConnectionUol7(){
		try {
			if(connectionUol7 != null && !connectionUol7.isClosed()){
				connectionUol7.close();
				System.out.println("UOL7 Fechado.");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao Tentar Fechar a conexão com o banco!");
		}

	}

	private void createDataSourceUol7() {
		dataSourceUol7 = new BasicDataSource();
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
	}

	private void createDataSourceUol3() {
		dataSourceUol3 = new BasicDataSource();
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
	}

	public Connection getConnectionUol3() {
		try {
			if(dataSourceUol3 == null){
				createDataSourceUol3();
			}
			if (connectionUol3 != null) {
				if(connectionUol3.isClosed()){
					System.out.println("A Conexão foi fechada, restabelecendo conexão com banco de dados uol3");
					connectionUol3 = dataSourceUol3.getConnection();
				}
				System.out.println("Retornando Conexão com banco de dados uol3");
				return connectionUol3;
			} else {
				System.out.println("Estabelecendo Conexão com banco de dados uol3");
				connectionUol3 = dataSourceUol3.getConnection();
				return connectionUol3;
			}
		} catch (SQLException e) {
			Assert.fail("Falha ao tentar obter a conexao com o banco de dados. Stack > "+ e);
			return null;
		}
	}

	public Connection getConnectionUol7() {
		try {
			if(dataSourceUol7 == null){
				createDataSourceUol3();
			}
			if (connectionUol7 != null) {
				if(connectionUol7.isClosed()){
					System.out.println("A Conexão foi fechada, restabelecendo conexão com banco de dados uol7");
					connectionUol7 = dataSourceUol7.getConnection();
				}
				System.out.println("Retornando Conexão com banco de dados uol7");
				return connectionUol7;
			} else {
				System.out.println("Estabelecendo Conexão com banco de dados uol7");
				connectionUol7 = dataSourceUol7.getConnection();
				return connectionUol7;
			}
		} catch (SQLException e) {
			Assert.fail("Falha ao tentar obter a conexao com o banco de dados. Stack > "+ e);
			return null;
		}
	}
}
package uol.adserversap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import reconf.client.proxy.ConfigurationRepositoryFactory;
import uol.adserversap.config.RemoteConfig;

/**
 *
 * @author dvrocha
 */
public class BaseDaoSingleton {

    private BasicDataSource ds;
    private static final Map<String, BaseDaoSingleton> instances = new HashMap<>();
    private static final RemoteConfig config = ConfigurationRepositoryFactory.get(RemoteConfig.class);
    
    public synchronized static BaseDaoSingleton getEtlInstance() {
        if (!instances.containsKey("etl")) {
            BaseDaoSingleton instance = new BaseDaoSingleton();
            instance.ds = new BasicDataSource();
            instance.ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
            instance.ds.setUrl("jdbc:oracle:thin:@10.133.4.23:1521:pin");
            instance.ds.setUsername("desenvolvimento");
            instance.ds.setPassword("desenvolvimento");
            instance.ds.setMaxActive(-1);
            instance.ds.setDefaultAutoCommit(true);
            instances.put("etl", instance);
        }
        return instances.get("etl");
    }

    public synchronized static BaseDaoSingleton getDartInstance() {
        if (!instances.containsKey("dart")) {
            BaseDaoSingleton instance = new BaseDaoSingleton();
            instance.ds = new BasicDataSource();
            instance.ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
            instance.ds.setUrl("jdbc:oracle:thin:@10.133.9.8:1521:pub4");
            instance.ds.setUsername("PLOCUBR");
            instance.ds.setPassword("PLOCUBR");
            instance.ds.setMaxActive(-1);
            instance.ds.setDefaultAutoCommit(true);
            instances.put("dart", instance);
        }
        return instances.get("dart");
    }

    public synchronized static BaseDaoSingleton getPlocInstance() {
        if (!instances.containsKey("ploc")) {
            BaseDaoSingleton instance = new BaseDaoSingleton();
            instance.ds = new BasicDataSource();
            instance.ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
            instance.ds.setUrl("jdbc:oracle:thin:@10.133.4.23:1521:pin");
            instance.ds.setUsername("desenvolvimento");
            instance.ds.setPassword("desenvolvimento");
            instance.ds.setMaxActive(-1);
            instance.ds.setDefaultAutoCommit(true);
            instances.put("ploc", instance);
        }
        return instances.get("ploc");
    }


    public synchronized static BaseDaoSingleton getSapInstance() {
        if (!instances.containsKey("sap")) {
            BaseDaoSingleton instance = new BaseDaoSingleton();
            instance.ds = new BasicDataSource();
            instance.ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
            instance.ds.setUrl(config.getSapUrlConnection());
            instance.ds.setUsername("adserversapubr");
            instance.ds.setPassword("adserversapubr");
            instance.ds.setMaxActive(-1);
            instance.ds.setDefaultAutoCommit(true);
            instances.put("sap", instance);
        }
        return instances.get("sap");
    }

    public Resource selectStatement(String query) throws Exception {
        final Connection conn = ds.getConnection();
        final Statement stmt = conn.createStatement();
        final ResultSet rs = stmt.executeQuery(query);

        return new Resource(conn, stmt, rs);
    }

    public Resource updateStatement(String query) throws Exception {
        final Connection conn = ds.getConnection();
        final Statement stmt = conn.createStatement();
        final int res = stmt.executeUpdate(query);

        return new Resource(conn, stmt, res);
    }

    public Resource updatePreparedStatement(String query, String... values) throws Exception {
        final Connection conn = ds.getConnection();
        final PreparedStatement stmt = conn.prepareStatement(query);
        for (int i = 0; i < values.length; i++) {
            stmt.setString(i + 1, values[i]);
        }
        final int res = stmt.executeUpdate();

        return new Resource(conn, stmt, res);
    }

    public void closeResources(Resource... resources) throws Exception {
        if (resources != null) {
            for (Resource resource : resources) {
                if (resource != null) {
                    resource.close();
                }
            }
        }
    }
}

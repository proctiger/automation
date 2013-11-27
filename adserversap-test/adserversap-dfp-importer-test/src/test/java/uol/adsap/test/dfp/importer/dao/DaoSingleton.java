package uol.adsap.test.dfp.importer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author dvrocha
 */
public class DaoSingleton {

    private BasicDataSource ds;
    private static final Map<String, DaoSingleton> instances = new HashMap<>();

    public synchronized static DaoSingleton getPinInstance() {
        if (!instances.containsKey("pin")) {
            DaoSingleton instance = new DaoSingleton();
            instance.ds = new BasicDataSource();
            instance.ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
            instance.ds.setUrl("jdbc:oracle:thin:@a1-omegaf-q-pla3:1521:pin");
            instance.ds.setUsername("MANUT_ADSERVSAP");
            instance.ds.setPassword("ploc123");
            instance.ds.setMaxActive(-1);
            instance.ds.setDefaultAutoCommit(true);
            instances.put("pin", instance);
        }
        return instances.get("pin");
    }

    public DaoResource selectStatement(String query) throws Exception {
        final Connection conn = ds.getConnection();
        final Statement stmt = conn.createStatement();
        final ResultSet rs = stmt.executeQuery(query);

        return new DaoResource(conn, stmt, rs);
    }

    public DaoResource updateStatement(String query) throws Exception {
        final Connection conn = ds.getConnection();
        final Statement stmt = conn.createStatement();
        final int res = stmt.executeUpdate(query);

        return new DaoResource(conn, stmt, res);
    }

    public DaoResource updatePreparedStatement(String query, String... values) throws Exception {
        final Connection conn = ds.getConnection();
        final PreparedStatement stmt = conn.prepareStatement(query);
        for (int i = 0; i < values.length; i++) {
            stmt.setString(i + 1, values[i]);
        }
        final int res = stmt.executeUpdate();

        return new DaoResource(conn, stmt, res);
    }

    public void closeResources(DaoResource... resources) throws Exception {
        if (resources != null) {
            for (DaoResource resource : resources) {
                if (resource != null) {
                    resource.close();
                }
            }
        }
    }
}

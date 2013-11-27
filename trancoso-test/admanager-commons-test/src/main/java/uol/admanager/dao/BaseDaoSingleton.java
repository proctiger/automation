package uol.admanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;

/**
 *
 * @author cin_wrodrigues
 */
public class BaseDaoSingleton {

    private BasicDataSource ds;
    private static final Map<String, BaseDaoSingleton> instances = new HashMap<String, BaseDaoSingleton>();

    public synchronized static BaseDaoSingleton getConsolidatorInstance() {
        if (!instances.containsKey("admgr")) {
            BaseDaoSingleton instance = new BaseDaoSingleton();
            instance.ds = new BasicDataSource();
            instance.ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
            instance.ds.setUrl("jdbc:oracle:thin:@a1-oates-q-prt1.host.intranet:1521:ev01");
            instance.ds.setUsername("qa");
            instance.ds.setPassword("mudar123");
            instance.ds.setMaxActive(-1);
            instance.ds.setDefaultAutoCommit(true);
            instances.put("admgr", instance);
        }

        return instances.get("admgr");
    }

    public synchronized static BaseDaoSingleton getAdmanagerAdmInstance() {
        if (!instances.containsKey("ad_manager_adm")) {
            BaseDaoSingleton instance = new BaseDaoSingleton();
            instance.ds = new BasicDataSource();
            instance.ds.setDriverClassName("com.mysql.jdbc.Driver");
            instance.ds.setUrl("jdbc:mysql://a1-oderzo-q-prt1.host.intranet:3306/ad_manager_adm");
            instance.ds.setUsername("qa");
            instance.ds.setPassword("mudar123");
            instance.ds.setMaxActive(-1);
            instance.ds.setDefaultAutoCommit(true);
            instances.put("ad_manager_adm", instance);
        }

        return instances.get("ad_manager_adm");
    }

    public Resource executeStatement(String query) throws Exception {
        final Connection conn = ds.getConnection();
        final Statement stmt = conn.createStatement();
        final ResultSet rs = stmt.executeQuery(query);

        return new Resource(conn, stmt, rs);
    }

    public Resource selectPreparedStatement(String query, Object... values) throws Exception {
        final Connection conn = ds.getConnection();
        final PreparedStatement stmt = conn.prepareStatement(query);

        for (int i = 0; i < values.length; i++) {
            final Object obj = values[i];
            final int index = i + 1;

            if (obj instanceof String) {
                stmt.setString(index, (String) obj);
            } else if (obj instanceof Date) {
                stmt.setDate(index, new java.sql.Date(((Date) obj).getTime()));
            } else if (obj instanceof Boolean) {
                stmt.setBoolean(index, (boolean) obj);
            } else if (obj instanceof Integer) {
                stmt.setInt(index, (int) obj);
            } else if (obj instanceof Float) {
                stmt.setFloat(index, (float) obj);
            } else if (obj instanceof Long) {
                stmt.setLong(index, (long) obj);
            } else if (obj instanceof Double) {
                stmt.setDouble(index, (double) obj);
            } else {
                stmt.setObject(index, obj);
            }
        }

        return new Resource(conn, stmt, stmt.executeQuery());
    }

    public void insertStatement(String query) throws Exception {
        Connection conn = ds.getConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.execute();

    }

    public Resource updateStatement(String query) throws Exception {
        final Connection conn = ds.getConnection();
        final Statement stmt = conn.createStatement();
        final int res = stmt.executeUpdate(query);

        return new Resource(conn, stmt, res);
    }

    public Resource updatePreparedStatement(String query, Object... values) throws Exception {
        final Connection conn = ds.getConnection();
        final PreparedStatement stmt = conn.prepareStatement(query);

        for (int i = 0; i < values.length; i++) {
            final Object obj = values[i];
            final int index = i + 1;

            if (obj instanceof String) {
                stmt.setString(index, (String) obj);
            } else if (obj instanceof Date) {
                stmt.setDate(index, new java.sql.Date(((Date) obj).getTime()));
            } else if (obj instanceof Boolean) {
                stmt.setBoolean(index, (boolean) obj);
            } else if (obj instanceof Integer) {
                stmt.setInt(index, (int) obj);
            } else if (obj instanceof Float) {
                stmt.setFloat(index, (float) obj);
            } else if (obj instanceof Long) {
                stmt.setLong(index, (long) obj);
            } else if (obj instanceof Double) {
                stmt.setDouble(index, (double) obj);
            } else {
                stmt.setObject(index, obj);
            }
        }

        return new Resource(conn, stmt, stmt.executeUpdate());
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

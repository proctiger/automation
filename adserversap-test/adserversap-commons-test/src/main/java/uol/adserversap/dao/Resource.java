package uol.adserversap.dao;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author dvrocha
 */
public class Resource implements Closeable {

    private final Connection connection;
    private final Statement statement;
    private final ResultSet resultSet;
    private final int result;

    public Resource(Connection connection, Statement statement, int result) {
        this.connection = connection;
        this.statement = statement;
        this.resultSet = null;
        this.result = result;
    }

    public Resource(Connection connection, Statement statement, ResultSet resultSet) {
        this.connection = connection;
        this.statement = statement;
        this.resultSet = resultSet;
        this.result = 0;
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public int getResult() {
        return result;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (Exception e) {
            //-- ignore
        }

        try {
            statement.close();
        } catch (Exception e) {
            //-- ignore
        }

        try {
            resultSet.close();
        } catch (Exception e) {
            //-- ignore
        }
    }
}

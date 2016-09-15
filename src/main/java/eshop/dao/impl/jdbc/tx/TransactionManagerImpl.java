package eshop.dao.impl.jdbc.tx;

import eshop.dao.impl.jdbc.JdbcUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class TransactionManagerImpl extends BaseDataSource implements TransactionManager{
    public static final String JDBC_URL =
            "jdbc:mysql://127.0.0.1:3306/eshop?user=gamst&password=bati09";
    private static ThreadLocal<Connection> connectionHolder =
            new ThreadLocal<>();

    @Override
    public Connection getConnection() throws SQLException {
        return connectionHolder.get();
    }

    @Override
    public <T, E extends Exception> T doInTransaction(UnitOfWork<T, E> unitOfWork) throws E {
        Connection conn = DriverManager.getConnection(JDBC_URL);
        conn.setAutoCommit(false);
        connectionHolder.set(conn);
        try {
            T result = unitOfWork.doInTx();
            conn.commit();
            return result;
        } catch (Exception e){
            conn.rollback();
            throw e;
        } finally {
            JdbcUtils.closeQuietly(conn);
            connectionHolder.remove();
        }
    }
}

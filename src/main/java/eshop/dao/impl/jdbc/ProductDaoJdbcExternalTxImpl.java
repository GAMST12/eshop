package eshop.dao.impl.jdbc;

import eshop.dao.ProductDao;
import eshop.dao.exception.DaoSystemException;
import eshop.dao.exception.NoSuchEntityException;
import eshop.entity.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class ProductDaoJdbcExternalTxImpl implements ProductDao{

    public static final String JDBC_URL =
            "jdbc:mysql://127.0.0.1:3306/eshop?user=gamst&password=bati09";
    public static final String SELECT_BY_ID_SQL = "SELECT id, caption from Products where id=?";
    public static final String SELECT_ALL_SQL = "SELECT id, caption from Products";

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Product selectById(int id) throws DaoSystemException, NoSuchEntityException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            Connection conn = dataSource.getConnection();
            stmt = conn.prepareStatement(SELECT_BY_ID_SQL);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                throw new NoSuchEntityException("No product for id = " + id);
            }
            Product result = new Product(rs.getInt("id"), rs.getString("name"));
            conn.commit();
            return result;
        } catch (SQLException e){
            throw new DaoSystemException("Some exception", e);
        } finally {
            JdbcUtils.closeQuietly(rs, stmt);
        }
    }

    @Override
    public List<Product> selectAll() throws DaoSystemException {
        return null;
    }
}

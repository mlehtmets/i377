package dao;

import java.sql.*;

import org.apache.commons.dbutils.DbUtils;

public abstract class AbstractDao {

    public static final String DB_URL =
            "jdbc:hsqldb:file:${user.home}/data/mlehtmets/db;shutdown=true;";
    protected Connection connection; 
    protected PreparedStatement pst;
    protected Statement st;
    protected ResultSet rs;
    static {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection() {
        try {
            connection = DriverManager.getConnection(DB_URL, "sa", "");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    protected void closeResources() { 
        DbUtils.closeQuietly(rs);
        DbUtils.closeQuietly(pst);
        DbUtils.closeQuietly(st);
        DbUtils.closeQuietly(connection);
    }
}

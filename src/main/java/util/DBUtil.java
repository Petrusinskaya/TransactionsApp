package util;

import model.Transaction;
import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {

    private static final JdbcConnectionPool cp; //an object that stores database connection pool
    /**
     * Static initialization of DBUtil class
     */
    static {
        cp = JdbcConnectionPool.create("jdbc:h2:./myTransactions.db", "sa", ""); // creates database connection pool
    }

    /**
     * Method for getting connection from this pool
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return cp.getConnection();
    }

    /**
     * Method for getting the data from the table of the database
     * @throws Exception
     */
    public static List<Transaction> getAllTransactions() throws Exception {
        List<Transaction> list = new ArrayList<Transaction>();
        Connection conn = getConnection(); // conn - an object containing the current connection to database
        String sql = "SELECT * FROM \"PUBLIC\".\"TRANSACTIONS\"";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        while(rs.next()) {
            Transaction tr = new Transaction();
            // Retrieve by column name
            tr.setDate(rs.getDate("Date"));
            tr.setType(rs.getString("Type"));
            tr.setAmount(rs.getFloat("Amount"));
            tr.setSource(rs.getString("Source"));
            list.add(tr);
        }
        conn.close();
        return list;
    }

    public static Transaction getTransaction(int id) throws Exception {
        Connection conn = getConnection(); // conn - an object containing the current connection to database
        PreparedStatement preparedStatement = null;
        preparedStatement = conn.prepareStatement("SELECT * FROM \"PUBLIC\".\"TRANSACTIONS\" WHERE ID=?");
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        Transaction tr = new Transaction();
        while(rs.next()) {
            // Retrieve by column name
            tr.setDate(rs.getDate("Date"));
            tr.setType(rs.getString("Type"));
            tr.setAmount(rs.getFloat("Amount"));
            tr.setSource(rs.getString("Source"));
        }
        conn.close();
        return tr;
    }

    public static List<Transaction> getLastTransactions() throws Exception {
        List<Transaction> list = new ArrayList<Transaction>();
        Connection conn = getConnection(); // conn - an object containing the current connection to database
        PreparedStatement preparedStatement = null;
        preparedStatement = conn.prepareStatement("SELECT * FROM TRANSACTIONS ORDER BY \"Date\" DESC LIMIT 10");
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
            Transaction tr = new Transaction();
            // Retrieve by column name
            tr.setDate(rs.getDate("Date"));
            tr.setType(rs.getString("Type"));
            tr.setAmount(rs.getFloat("Amount"));
            tr.setSource(rs.getString("Source"));
            list.add(tr);
        }
        conn.close();
        return list;
    }

    /**
     * Method for adding the data to the table of the database
     */
    public static void addTransaction(Transaction tr) throws SQLException {
        Connection conn = getConnection(); // conn - an object containing the current connection to database
        String sql = "INSERT INTO PUBLIC.TRANSACTIONS (\"Date\",\"Type\",AMOUNT,\"Source\") VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = null;
        preparedStatement = conn.prepareStatement(sql);
        java.sql.Date date = new java.sql.Date(tr.getDate().getTime());
        preparedStatement.setDate(1, date);
        preparedStatement.setString(2, tr.getType());
        preparedStatement.setFloat(3, tr.getAmount());
        preparedStatement.setString(4, tr.getSource());
        preparedStatement.executeUpdate();
        conn.close();
    }

    /**
     * Method for deleting the data from the table of the database
     */
    public static void delTransaction(int id) throws SQLException {
        Connection conn = getConnection(); // conn - an object containing the current connection to database
        String sql = "DELETE FROM PUBLIC.TRANSACTIONS WHERE ID=?";
        PreparedStatement preparedStatement = null;
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        conn.close();
    }
}

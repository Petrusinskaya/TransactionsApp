package util;

import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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
    public static void getItem() throws Exception {
        Connection conn = getConnection(); // conn - an object containing the current connection to database
        String sql = "SELECT * FROM \"PUBLIC\".\"TRANSACTIONS\"";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        while(rs.next()) {
            // Retrieve by column name
            Date date  = rs.getDate("Date");
            String type = rs.getString("Type");
            Float amount = rs.getFloat("Amount");
            String source = rs.getString("Source");

            // Display values
            System.out.print("Date: " + date);
            System.out.print(", Type: " + type);
            System.out.print(", Amount: " + amount);
            System.out.println(", Source: " + source);
        }
        conn.close();
    }

    public static void addItem(){

    }
}

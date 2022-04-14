package util;

import model.Profit;
import model.Transaction;
import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBUtil {

    private static final JdbcConnectionPool cp; // cp is the object that stores database connection pool
    /**
     * Static initialization of DBUtil class
     */
    static {
        cp = JdbcConnectionPool.create("jdbc:h2:./myTransactions.db", "sa", ""); // creating database connection pool
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
     * Method for adding the transaction to the transactions table of the database
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

    public static List<Transaction> getLastTransactions() throws Exception {
        List<Transaction> list = new ArrayList<Transaction>();
        Connection conn = getConnection(); // conn - an object containing the current connection to database
        PreparedStatement preparedStatement = null;
        preparedStatement = conn.prepareStatement("SELECT * FROM TRANSACTIONS ORDER BY \"Date\" DESC LIMIT 17");
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
            Transaction tr = new Transaction();
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
     * Method for getting all transactions from the transactions table in the database
     * @throws Exception
     */
    public static List<Transaction> getAllTransactions() throws Exception {
        List<Transaction> list = new ArrayList<Transaction>();
        Connection conn = getConnection(); // conn - an object containing the current connection to database
        String sql = "SELECT * FROM \"PUBLIC\".\"TRANSACTIONS\" ORDER BY \"Date\" DESC";
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

    public static List<Transaction> filterTransactions(String date, String type, String amount, String source) throws Exception {
        List<Transaction> list = new ArrayList<Transaction>();
        Connection conn = getConnection(); // conn - an object containing the current connection to database
        String sql = "SELECT * FROM TRANSACTIONS WHERE 1=1 <<DATE>> <<TYPE>> <<AMOUNT>> <<SOURCE>> ORDER BY \"Date\" DESC";

        int index = 1;
        if (!"Any time".equals(date)){
            sql = sql.replaceAll("<<DATE>>","AND \"Date\">=?");
        }
        else {
            sql = sql.replaceAll("<<DATE>>","");
        }
        if (!"Any type".equals(type)){
            sql = sql.replaceAll("<<TYPE>>","AND \"Type\"=?");
        }
        else{
            sql = sql.replaceAll("<<TYPE>>","");
        }
        if (!"Any amount".equals(amount)){
            sql = sql.replaceAll("<<AMOUNT>>","AND \"AMOUNT\">=?");
        }
        else{
            sql = sql.replaceAll("<<AMOUNT>>","");
        }
        if (!"Any source".equals(source)){
            sql = sql.replaceAll("<<SOURCE>>","AND \"Source\"=?");
        }
        else{
            sql = sql.replaceAll("<<SOURCE>>","");
        }

        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        LocalDate localDate = LocalDate.now();
        switch (date){
            case "Any time":

                break;
            case "Last week":
                localDate = localDate.minusWeeks(1);
                break;
            case "Last month":
                localDate = localDate.minusMonths(1);
                break;
            case "Last year":
                localDate = localDate.minusYears(1);
                break;
        }

        Date dateToString = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(dateToString.getTime());

        if (!"Any time".equals(date)){
            preparedStatement.setDate(index++, sqlDate);
        }
        if (!"Any type".equals(type)){
            preparedStatement.setString(index++, type);
        }
        if (!"Any amount".equals(amount)){
            Float floatAmount = Float.valueOf(amount.substring(1,amount.length()));
            preparedStatement.setFloat(index++, floatAmount);
        }
        if (!"Any source".equals(source)){
            preparedStatement.setString(index++, source);
        }

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

    public static List<Profit> getAllProfits(String period) throws Exception {
        Connection conn = getConnection(); // conn - an object containing the current connection to database
        String sql = "SELECT DATE_TRUNC(MONTH, \"Date\") AS DATECUT, \"Type\", SUM(AMOUNT) AS SUM FROM TRANSACTIONS <<WHERE_PERIOD>> GROUP BY DATECUT, \"Type\" ORDER BY DATECUT";
        if(period!=null && !"".equals(period)){
            sql = sql.replaceAll("<<WHERE_PERIOD>>", " WHERE \"Date\" > ? ");
        }else{
            sql = sql.replaceAll("<<WHERE_PERIOD>>", "");
        }
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        LocalDate whereDate = LocalDate.now();
        if("last year".equals(period)){
            whereDate = whereDate.minusYears(1);
        }else if("quarter".equals(period)){
            whereDate = whereDate.minusMonths(3);
        }
        if(period!=null && !"".equals(period)){
            Date date = Date.from(whereDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            preparedStatement.setDate(1, sqlDate);
        }
        ResultSet rs = preparedStatement.executeQuery();
        Date prevDate = null;
        Float prevTypeIncomeSum = 0f;
        Float prevTypeExpenseSum = 0f;
        ArrayList<Profit> profitList = new ArrayList<Profit>();
        while(rs.next()){
            Date date = rs.getDate("DATECUT");
            if (!date.equals(prevDate) && prevDate!=null) {
                Profit profit = new Profit();
                profit.setPeriod(prevDate);
                profit.setProfit(prevTypeIncomeSum-prevTypeExpenseSum);
                profitList.add(profit);
                prevTypeIncomeSum=0f;
                prevTypeExpenseSum=0f;
            }
            String type = rs.getString("Type");
            if ("Income".equals(type)){
                prevTypeIncomeSum = rs.getFloat("SUM");
            }
            else{
                prevTypeExpenseSum = rs.getFloat("SUM");
            }
            prevDate = date;
        }
        if(prevDate!=null) {
            Profit profit = new Profit();
            profit.setPeriod(prevDate);
            profit.setProfit(prevTypeIncomeSum - prevTypeExpenseSum);
            profitList.add(profit);
        }

        conn.close();
        return profitList;
    }

    public static List<Profit> getLastProfits() throws Exception {
        Connection conn = getConnection(); // conn - an object containing the current connection to database
        String sql = "SELECT DATE_TRUNC(MONTH, \"Date\") AS DATECUT, \"Type\", SUM(AMOUNT) AS SUM FROM TRANSACTIONS GROUP BY DATECUT, \"Type\" ORDER BY DATECUT DESC";
        ResultSet rs = conn.createStatement().executeQuery(sql);

        Date prevDate = null;
        Float prevTypeIncomeSum = 0f;
        Float prevTypeExpenseSum = 0f;
        ArrayList<Profit> profitList = new ArrayList<Profit>();
        while(rs.next()){
            Date date = rs.getDate("DATECUT");
            if (!date.equals(prevDate) && prevDate!=null) {
                Profit profit = new Profit();
                profit.setPeriod(prevDate);
                profit.setProfit(prevTypeIncomeSum-prevTypeExpenseSum);
                profitList.add(profit);
                prevTypeIncomeSum=0f;
                prevTypeExpenseSum=0f;
            }
            String type = rs.getString("Type");
            if ("Income".equals(type)){
                prevTypeIncomeSum = rs.getFloat("SUM");
            }
            else{
                prevTypeExpenseSum = rs.getFloat("SUM");
            }
            prevDate = date;
        }

        Profit profit = new Profit();
        profit.setPeriod(prevDate);
        profit.setProfit(prevTypeIncomeSum-prevTypeExpenseSum);
        profitList.add(profit);

        conn.close();
        return profitList;
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

//    public static List<Transaction> getLastWeekTransactions(String type, String amount, String source) throws Exception {
//        List<Transaction> list = new ArrayList<Transaction>();
//        Connection conn = getConnection(); // conn - an object containing the current connection to database
//        PreparedStatement preparedStatement = null;
//        preparedStatement = conn.prepareStatement("SELECT * FROM TRANSACTIONS WHERE \"Date\">=? AND \"Type\"==? AND \"Amount\"==? AND \"Source\"==? order by \"Date\" DESC");
//        LocalDate lastWeek = LocalDate.now().minusWeeks(1);
//        Date date = Date.from(lastWeek.atStartOfDay(ZoneId.systemDefault()).toInstant());
//        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//        preparedStatement.setDate(1, sqlDate);
//        preparedStatement.setString(2, type);
//        preparedStatement.setString(3, amount);
//        preparedStatement.setString(4, source);
//        ResultSet rs = preparedStatement.executeQuery();
//        while(rs.next()) {
//            Transaction tr = new Transaction();
//            // Retrieve by column name
//            tr.setDate(rs.getDate("Date"));
//            tr.setType(rs.getString("Type"));
//            tr.setAmount(rs.getFloat("Amount"));
//            tr.setSource(rs.getString("Source"));
//            list.add(tr);
//        }
//        conn.close();
//        return list;
//    }

//    public static List<Transaction> getLastMonthTransactions() throws Exception {
//        List<Transaction> list = new ArrayList<Transaction>();
//        Connection conn = getConnection(); // conn - an object containing the current connection to database
//        PreparedStatement preparedStatement = null;
//        preparedStatement = conn.prepareStatement("SELECT * FROM TRANSACTIONS WHERE \"Date\" >= ? order by \"Date\" DESC");
//        LocalDate lastMonth = LocalDate.now().minusMonths(1);
//        Date date = Date.from(lastMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
//        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//        preparedStatement.setDate(1, sqlDate);
//        ResultSet rs = preparedStatement.executeQuery();
//        while(rs.next()) {
//            Transaction tr = new Transaction();
//            // Retrieve by column name
//            tr.setDate(rs.getDate("Date"));
//            tr.setType(rs.getString("Type"));
//            tr.setAmount(rs.getFloat("Amount"));
//            tr.setSource(rs.getString("Source"));
//            list.add(tr);
//        }
//        conn.close();
//        return list;
//    }

//    public static List<Transaction> getLastYearTransactions() throws Exception {
//        List<Transaction> list = new ArrayList<Transaction>();
//        Connection conn = getConnection(); // conn - an object containing the current connection to database
//        PreparedStatement preparedStatement = null;
//        preparedStatement = conn.prepareStatement("SELECT * FROM TRANSACTIONS WHERE \"Date\" >= ? order by \"Date\" DESC");
//        LocalDate lastWeek = LocalDate.now().minusYears(1);
//        Date date = Date.from(lastWeek.atStartOfDay(ZoneId.systemDefault()).toInstant());
//        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//        preparedStatement.setDate(1, sqlDate);
//        ResultSet rs = preparedStatement.executeQuery();
//        while(rs.next()) {
//            Transaction tr = new Transaction();
//            // Retrieve by column name
//            tr.setDate(rs.getDate("Date"));
//            tr.setType(rs.getString("Type"));
//            tr.setAmount(rs.getFloat("Amount"));
//            tr.setSource(rs.getString("Source"));
//            list.add(tr);
//        }
//        conn.close();
//        return list;
//    }

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

    public static List<Profit> getProfits(String period) throws Exception {
        Connection conn = getConnection(); // conn - an object containing the current connection to database
        LocalDate periodDiff;
        if("QUARTER".equals(period)){
            periodDiff = LocalDate.now().minusMonths(3);
        }
        else {
            periodDiff = LocalDate.now().minusYears(1);
        }

        String sql = "SELECT DATE_TRUNC(MONTH, \"Date\") AS DATECUT, \"Type\", SUM(AMOUNT) AS SUM FROM TRANSACTIONS GROUP BY DATECUT, \"Type\" HAVING DATECUT>? ORDER BY DATECUT";
        PreparedStatement ps = conn.prepareStatement(sql);
        Date transferDate = Date.from(periodDiff.atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date sqlDate = new java.sql.Date(transferDate.getTime());
        ps.setDate(1,sqlDate);
        ResultSet rs = ps.executeQuery();

        Date prevDate = null;
        Float prevTypeIncomeSum = 0f;
        Float prevTypeExpenseSum = 0f;
        String prevSum = null;

        ArrayList<Profit> profitList = new ArrayList<Profit>();
        while(rs.next()){
            Date date = rs.getDate("DATECUT");
            if (!date.equals(prevDate) && prevDate!=null) {
                Profit profit = new Profit();
                profit.setPeriod(prevDate);
                profit.setProfit(prevTypeIncomeSum-prevTypeExpenseSum);
                profitList.add(profit);
                prevTypeIncomeSum=0f;
                prevTypeExpenseSum=0f;
            }
            String type = rs.getString("Type");
            if ("Income".equals(type)){
                prevTypeIncomeSum = rs.getFloat("SUM");
            }
            else{
                prevTypeExpenseSum = rs.getFloat("SUM");
            }
            prevDate = date;
        }

        Profit profit = new Profit();
        profit.setPeriod(prevDate);
        profit.setProfit(prevTypeIncomeSum-prevTypeExpenseSum);
        profitList.add(profit);

        conn.close();
        return profitList;
    }

}

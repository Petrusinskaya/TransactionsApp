import model.Transaction;
import util.DBUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

public class Transactions extends JFrame{

    private JPanel transactions;
    private JComboBox timeComboBox;
    private JComboBox typeComboBox;
    private JComboBox amountComboBox;
    private JComboBox sourceComboBox;
    private JButton sortButton;
    private JButton unsortButton;
    private JLabel dateLabel;
    private JLabel typeLabel;
    private JLabel amountLabel;
    private JLabel sourceLabel;
    private JTable allTransactionsTable;

    public Transactions(String title) throws Exception {
        super(title);
        setContentPane(transactions);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(720,420);
        setLocationRelativeTo(null);
        setVisible(true);

        //Adding the options to timeComboBox
        timeComboBox.addItem("Any time");
        timeComboBox.addItem("Last week");
        timeComboBox.addItem("Last month");
        timeComboBox.addItem("Last year");

        //Adding the options to typeComboBox
        typeComboBox.addItem("Any type");
        typeComboBox.addItem("Expense");
        typeComboBox.addItem("Income");

        //Adding the options to amountComboBox
        amountComboBox.addItem("Any amount");
        amountComboBox.addItem(">5000");
        amountComboBox.addItem(">10000");
        amountComboBox.addItem(">20000");

        //Adding the options to sourceComboBox
        sourceComboBox.addItem("Equipment");
        sourceComboBox.addItem("Feed");
        sourceComboBox.addItem("Medicines");
        sourceComboBox.addItem("Horse work");
        sourceComboBox.addItem("Groom's salary");
        sourceComboBox.addItem("Photo-shoot");
        sourceComboBox.addItem("Performance");
        sourceComboBox.addItem("Manure sale");
        sourceComboBox.addItem("Other");

        //Adding the table of last transactions to the window
        Vector<String> columnNames = new Vector<String>();
        columnNames.add("Date");
        columnNames.add("Type");
        columnNames.add("Amount");
        columnNames.add("Source");
        Vector<Vector> data = new Vector<Vector>();
        List<Transaction> list = DBUtil.getAllTransactions();
        for(Transaction element: list){
            Vector<String> row = new Vector<String>();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
            String date = sdf.format(element.getDate());
            row.add(date);
            row.add(element.getType());
            row.add(String.format("%.1f",element.getAmount()));
            row.add(element.getSource());
            data.add(row);
        }
        allTransactionsTable.setModel(new DefaultTableModel(data, columnNames));

    }

    public static void main(String[] args){
        JFrame transactions = new JFrame("Transactions page");
    }
}

import model.Transaction;
import model.TransactionTableModel;
import util.DBUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Transactions extends JFrame{

    private JPanel transactions;
    private JComboBox periodComboBox;
    private JComboBox typeComboBox;
    private JComboBox amountComboBox;
    private JComboBox sourceComboBox;
    private JButton sortButton;
    private JButton unsortButton;
    private JTable allTransactionsTable;

    public Transactions(String title) throws Exception {
        super(title);
        setContentPane(transactions);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(720,420);
        setLocationRelativeTo(null);
        setVisible(true);

        //Adding the options to timeComboBox
        periodComboBox.addItem("Any time");
        periodComboBox.addItem("Last week");
        periodComboBox.addItem("Last month");
        periodComboBox.addItem("Last year");

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
        sourceComboBox.addItem("Any source");
        sourceComboBox.addItem("Equipment");
        sourceComboBox.addItem("Feed");
        sourceComboBox.addItem("Medicines");
        sourceComboBox.addItem("Horse work");
        sourceComboBox.addItem("Groom's salary");
        sourceComboBox.addItem("Photo-shoot");
        sourceComboBox.addItem("Performance");
        sourceComboBox.addItem("Manure sale");

        //Adding the table of last transactions to the window
        List<Transaction> list = DBUtil.getAllTransactions();
        TransactionTableModel allTransactionsTableModel = new TransactionTableModel();
        allTransactionsTableModel.setData(list);
        allTransactionsTable.setModel(allTransactionsTableModel);

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==sortButton){
                    List<Transaction> list = null;
                    String date = (String) periodComboBox.getSelectedItem();
                    String type = (String) typeComboBox.getSelectedItem();
                    String amount = (String) amountComboBox.getSelectedItem();
                    String source = (String) sourceComboBox.getSelectedItem();
                    try {
                        //Adding the table of filtered transactions to the window
                        list = DBUtil.filterTransactions(date, type, amount, source);
                        TransactionTableModel filteredModel = new TransactionTableModel();
                        filteredModel.setData(list);
                        allTransactionsTable.setModel(filteredModel);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });

        unsortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==unsortButton){
                    List<Transaction> list = null;
                    try {
                        list = DBUtil.getAllTransactions();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    TransactionTableModel allTransactionsTableModel = new TransactionTableModel();
                    allTransactionsTableModel.setData(list);
                    allTransactionsTable.setModel(allTransactionsTableModel);
                }
            }
        });
    }

//    public static void freeSort(JTable table){
//        List<Transaction> list = null;
//        try {
//            list = DBUtil.getAllTransactions();
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//        TransactionTableModel allTransactionsTableModel = new TransactionTableModel();
//        allTransactionsTableModel.setData(list);
//        table.setModel(allTransactionsTableModel);
//    }

    public static void main(String[] args){
        JFrame transactions = new JFrame("Transactions page");
    }
}

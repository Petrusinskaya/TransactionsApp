import model.Transaction;
import model.TransactionTableModel;
import util.DBUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

public class TransactionAppGUI extends JFrame{
    private JPanel startPage;
    private JLabel lastTransactionsLabel;
    private JButton viewMoreTransactionsButton;
    private JLabel yourProfitLabel;
    private JButton viewMoreProfitButton;
    private JLabel equipmentLabel;
    private JButton viewMoreEquipmentButton;
    private JButton addTransactionButton;
    private JTable lastTransactionsTable;

    public TransactionAppGUI(String title) throws Exception {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(startPage);
        this.pack();

        //An algorithm for opening the Transactions page window
        viewMoreTransactionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==viewMoreTransactionsButton){
                    try {
                        new Transactions("Transactions page");
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });

        //An algorithm for opening the Adding Transaction page window
        addTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==addTransactionButton){
                    try {
                        new AddingTransaction("Adding Transaction page");
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                }
            }
        });

        //An algorithm for opening the Profit page window
        viewMoreProfitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==viewMoreProfitButton){
                    new Profit("Profit page");
                }
            }
        });

        //An algorithm for opening the Equipment, Medicines & Feed page window
        viewMoreEquipmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==viewMoreEquipmentButton){
                    new Equipment("Equipment, Medicines & Feed page");
                }
            }
        });

        //Adding the table of last transactions to the window
        List<Transaction> list = DBUtil.getLastTransactions();
        TransactionTableModel lastTransactionsTableModel = new TransactionTableModel();
        lastTransactionsTableModel.setData(list);
        lastTransactionsTable.setModel(lastTransactionsTableModel);

    }

    public static void main(String[] args) throws Exception {

        //Setting the window characteristics
        JFrame transactionAppGUI = new TransactionAppGUI("Horse Stable Diary");
        transactionAppGUI.setSize(720,420);
        transactionAppGUI.setLocationRelativeTo(null);
        transactionAppGUI.setVisible(true);

    }

}

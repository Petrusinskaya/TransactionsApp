import util.DBUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TransactionAppGUI extends JFrame{
    private JPanel startPage;
    private JLabel lastTransactionsLabel;
    private JButton viewMoreTransactionsButton;
    private JLabel yourProfitLabel;
    private JButton viewMoreProfitButton;
    private JLabel equipmentLabel;
    private JButton viewMoreEquipmentButton;
    private JButton addTransactionButton;

    public TransactionAppGUI(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(startPage);
        this.pack();

        //An algorithm for opening the Transactions page window
        viewMoreTransactionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==viewMoreTransactionsButton){
                    new transactions("Transactions page");
                }
            }
        });

        //An algorithm for opening the Adding Transaction page window
        addTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==addTransactionButton){
                    new addingTransaction("Adding Transaction page");
                }
            }
        });

        //An algorithm for opening the Profit page window
        viewMoreProfitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==viewMoreProfitButton){
                    new profit("Profit page");
                }
            }
        });

        //An algorithm for opening the Equipment, Medicines & Feed page window
        viewMoreEquipmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==viewMoreEquipmentButton){
                    new equipment("Equipment, Medicines & Feed page");
                }
            }
        });

    }

    public static void main(String[] args) throws Exception {
        JFrame transactionAppGUI = new TransactionAppGUI("Horse Stable Diary");
        transactionAppGUI.setSize(720,420);
        transactionAppGUI.setLocationRelativeTo(null);
        transactionAppGUI.setVisible(true);

        DBUtil.getItem();
    }
}

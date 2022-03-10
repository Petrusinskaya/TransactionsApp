import model.Transaction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;

import static util.DBUtil.addTransaction;

public class AddingTransaction extends JFrame {

    private JPanel addingTransaction;
    private JLabel dateLabel;
    private JLabel typeLabel;
    private JLabel amountLabel;
    private JLabel sourceLabel;
    private JLabel dayLabel;
    private JTextField dayTextField;
    private JLabel monthLabel;
    private JLabel yearLabel;
    private JRadioButton expenseRadioButton;
    private JRadioButton incomeRadioButton;
    private JTextField amountTextField;
    private JLabel rublesLabel;
    private JComboBox sourceComboBox;
    private JButton addTransactionButton;
    private JComboBox dayComboBox;
    private JComboBox monthComboBox;
    private JComboBox yearComboBox;

    public AddingTransaction(String title) throws ParseException {
        super(title);
        setContentPane(addingTransaction);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(720,420);
        setLocationRelativeTo(null);
        setVisible(true);

        //Combining expense and income radio buttons so that only one can be chosen at a time
        ButtonGroup type = new ButtonGroup();
        type.add(expenseRadioButton);
        type.add(incomeRadioButton);

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

        //Adding the options to dayComboBox
        for (int i=1; i<32; i++){
            dayComboBox.addItem(i);
        }

        //Adding the options to monthComboBox
        monthComboBox.addItem("January");
        monthComboBox.addItem("February");
        monthComboBox.addItem("March");
        monthComboBox.addItem("April");
        monthComboBox.addItem("May");
        monthComboBox.addItem("June");
        monthComboBox.addItem("July");
        monthComboBox.addItem("August");
        monthComboBox.addItem("September");
        monthComboBox.addItem("October");
        monthComboBox.addItem("November");
        monthComboBox.addItem("December");

        //Adding the options to monthComboBox
        for (int i=0; i<2; i++) {
            yearComboBox.addItem(Calendar.getInstance().get(Calendar.YEAR)-i);
        }

        addTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Transaction transaction = new Transaction();

                /*Setting the date
                dayTextField.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String day = dayTextField.getText();
                    }
                });

                monthTextField.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String month = dayTextField.getText();
                    }
                });

                yearTextField.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String year = dayTextField.getText();
                    }
                });*/

                /*try {
                    transaction.setDateFromString(dayTextField.getText() + "/" + monthTextField.getText() + "/" + yearTextField.getText());
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }*/

                expenseRadioButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        transaction.setType("Expense");
                    }
                });

                incomeRadioButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        transaction.setType("Income");
                    }
                });

                transaction.setAmount(Float.valueOf(dayTextField.getText()));
                transaction.setSource((String) sourceComboBox.getSelectedItem());

                /*amountTextField.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Float amount = Float.valueOf(dayTextField.getText());
                        transaction.setAmount(amount);
                    }
                });

                sourceComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String source = (String) sourceComboBox.getSelectedItem();
                        transaction.setSource(source);
                    }
                });*/

                try {
                    addTransaction(transaction);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

    }

    public static void main(String[] args){
        JFrame addingTransaction = new JFrame("Adding Transaction page");
    }
}

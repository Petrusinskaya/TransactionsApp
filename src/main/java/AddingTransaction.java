import model.Transaction;

import javax.swing.*;
import java.awt.*;
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
        setSize(720, 420);
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
        for (int i = 1; i < 32; i++) {
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

        for (int i = 0; i < 2; i++) {
            yearComboBox.addItem(Calendar.getInstance().get(Calendar.YEAR) - i);
        }

        Transaction transaction = new Transaction();

        //Setting the date
        final String[] day = {"1"};
        final String[] month = {"01"};
        final String[] year = {((Integer) Calendar.getInstance().get(Calendar.YEAR)).toString()};
        dayComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer selectedDay = (Integer) dayComboBox.getSelectedItem();
                day[0] = selectedDay.toString();
                if (selectedDay < 10) {
                    day[0] = "0" + day[0];
                }
            }
        });

        monthComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch ((String) monthComboBox.getSelectedItem()) {
                    case "January":
                        month[0] = "01";
                        break;
                    case "February":
                        month[0] = "02";
                        break;
                    case "March":
                        month[0] = "03";
                        break;
                    case "April":
                        month[0] = "04";
                        break;
                    case "May":
                        month[0] = "05";
                        break;
                    case "June":
                        month[0] = "06";
                        break;
                    case "July":
                        month[0] = "07";
                        break;
                    case "August":
                        month[0] = "08";
                        break;
                    case "September":
                        month[0] = "09";
                        break;
                    case "October":
                        month[0] = "10";
                        break;
                    case "November":
                        month[0] = "11";
                        break;
                    case "December":
                        month[0] = "12";
                        break;
                }
            }
        });

        yearComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                year[0] = ((Integer) yearComboBox.getSelectedItem()).toString();
            }
        });

        //Determining the type of transaction
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

        addTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    transaction.setDateFromString(day[0] + "-" + month[0] + "-" + year[0]);
                    transaction.setAmount(Float.valueOf(amountTextField.getText()));
                    transaction.setSource((String) sourceComboBox.getSelectedItem());
                    addTransaction(transaction);
                    dispose();
                } catch (SQLException | ParseException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

    }

    public static void main(String[] args) {
        JFrame addingTransaction = new JFrame("Adding Transaction page");
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        addingTransaction = new JPanel();
        addingTransaction.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 9, new Insets(0, 0, 0, 0), -1, -1));
        dateLabel = new JLabel();
        dateLabel.setText("Date");
        addingTransaction.add(dateLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        typeLabel = new JLabel();
        typeLabel.setText("Type");
        addingTransaction.add(typeLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        amountLabel = new JLabel();
        amountLabel.setText("Amount");
        addingTransaction.add(amountLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sourceLabel = new JLabel();
        sourceLabel.setText("Source");
        addingTransaction.add(sourceLabel, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dayLabel = new JLabel();
        dayLabel.setText("Day");
        addingTransaction.add(dayLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        monthLabel = new JLabel();
        monthLabel.setText("Month");
        addingTransaction.add(monthLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        yearLabel = new JLabel();
        yearLabel.setText("Year");
        addingTransaction.add(yearLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 6, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        expenseRadioButton = new JRadioButton();
        expenseRadioButton.setText("Expense");
        addingTransaction.add(expenseRadioButton, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        incomeRadioButton = new JRadioButton();
        incomeRadioButton.setText("Income");
        addingTransaction.add(incomeRadioButton, new com.intellij.uiDesigner.core.GridConstraints(1, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        amountTextField = new JTextField();
        addingTransaction.add(amountTextField, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        rublesLabel = new JLabel();
        rublesLabel.setText("rubles");
        addingTransaction.add(rublesLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        sourceComboBox = new JComboBox();
        addingTransaction.add(sourceComboBox, new com.intellij.uiDesigner.core.GridConstraints(3, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addTransactionButton = new JButton();
        addTransactionButton.setText("Add transaction");
        addingTransaction.add(addTransactionButton, new com.intellij.uiDesigner.core.GridConstraints(4, 7, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        addingTransaction.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        addingTransaction.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(2, 8, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        dayComboBox = new JComboBox();
        addingTransaction.add(dayComboBox, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        monthComboBox = new JComboBox();
        addingTransaction.add(monthComboBox, new com.intellij.uiDesigner.core.GridConstraints(0, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        yearComboBox = new JComboBox();
        addingTransaction.add(yearComboBox, new com.intellij.uiDesigner.core.GridConstraints(0, 7, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return addingTransaction;
    }
}

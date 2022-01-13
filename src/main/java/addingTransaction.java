import javax.swing.*;

public class addingTransaction extends JFrame {

    private JPanel addingTransaction;
    private JLabel dateLabel;
    private JLabel typeLabel;
    private JLabel amountLabel;
    private JLabel sourceLabel;
    private JLabel dayLabel;
    private JTextField dayTextField;
    private JLabel monthLabel;
    private JTextField monthTextField;
    private JLabel yearLabel;
    private JTextField yearTextField;
    private JRadioButton expenseRadioButton;
    private JRadioButton incomeRadioButton;
    private JTextField amountTextField;
    private JLabel rublesLabel;
    private JComboBox sourceComboBox;
    private JButton addTransactionButton;

    public addingTransaction(String title){
        super(title);
        setContentPane(addingTransaction);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    }

    public static void main(String[] args){
        JFrame addingTransaction = new JFrame("Adding Transaction page");
    }
}

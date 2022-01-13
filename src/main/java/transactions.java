import javax.swing.*;

public class transactions extends JFrame{

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

    public transactions(String title){
        super(title);
        setContentPane(transactions);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    }

    public static void main(String[] args){
        JFrame transactions = new JFrame("Transactions page");
    }
}

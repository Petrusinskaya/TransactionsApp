import javax.swing.*;

public class profit extends JFrame {
    private JPanel profit;
    private JLabel monthLabel;
    private JLabel amountLabel;
    private JLabel profitDiagramLabel;
    private JLabel mainSourcesOfIncomeLabel;
    private JComboBox showProfitForComboBox;
    private JLabel showProfitForLabel;

    public profit(String title){
        super(title);
        setContentPane(profit);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(720,420);
        setLocationRelativeTo(null);
        setVisible(true);

        //Adding the options to showProfitForComboBox
        showProfitForComboBox.addItem("the last month");
        showProfitForComboBox.addItem("the last half a year");
        showProfitForComboBox.addItem("the last year");
    }

    public static void main(String[] args){}
}

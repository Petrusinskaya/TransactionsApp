import model.ProfitTableModel;
import util.DBUtil;

import javax.swing.*;
import java.util.List;


public class Profit extends JFrame {
    private JPanel profit;
    private JLabel monthLabel;
    private JLabel mainSourcesOfIncomeLabel;
    private JComboBox showProfitForComboBox;
    private JLabel showProfitForLabel;
    private JTable profitTable;
    private JLabel amountLabel;
    private JLabel profitDiagramLabel;

    public Profit(String title) throws Exception {
        super(title);
        setContentPane(profit);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(720,420);
        setLocationRelativeTo(null);
        setVisible(true);

        //Adding the options to showProfitForComboBox
        showProfitForComboBox.addItem("the last month");
        showProfitForComboBox.addItem("the last half a year");
        showProfitForComboBox.addItem("the last year");

        //Adding the profit table to the window
        List<model.Profit> list = DBUtil.getAllProfits();
        ProfitTableModel allProfits = new ProfitTableModel();
        allProfits.setData(list);
        profitTable.setModel(allProfits);

    }

    public static void main(String[] args) throws Exception {

    }

}
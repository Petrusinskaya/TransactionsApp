import model.ProfitTableModel;
import util.DBUtil;
import util.MyPanel;

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
    private MyPanel myPanel;
    private JTable incomeSourcesTable;
    List<model.Profit> profitList;

    public Profit(String title) throws Exception {
        super(title);
        setContentPane(profit);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(720,420);
        setLocationRelativeTo(null);
        setVisible(true);

        //Adding the options to showProfitForComboBox
        showProfitForComboBox.addItem("all the time");
        showProfitForComboBox.addItem("quarter");
        showProfitForComboBox.addItem("last year");

        //Adding the profit table to the window
        profitList = DBUtil.getAllProfits();
        ProfitTableModel allProfits = new ProfitTableModel();
        allProfits.setData(profitList);
        profitTable.setModel(allProfits);

//        showProfitForComboBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if(e.getSource()==showProfitForComboBox){
//                    List<model.Profit> profits = null;
//                    switch((String) showProfitForComboBox.getSelectedItem()){
//                        case ("all the time"):
//                            try {
//                                profits = DBUtil.getAllProfits();
//                            } catch (Exception exception) {
//                                exception.printStackTrace();
//                            }
//                            break;
//                        case ("quarter"):
//                            try {
//                                profits = DBUtil.getProfits("QUARTER");
//                            } catch (Exception exception) {
//                                exception.printStackTrace();
//                            }
//                            break;
//                        case ("last year"):
//                            try {
//                                profits = DBUtil.getProfits("YEAR");
//                            } catch (Exception exception) {
//                                exception.printStackTrace();
//                            }
//                            break;
//                    }
//                    try {
//                        myPanel = new MyPanel(profits);
//                    } catch (Exception exception) {
//                        exception.printStackTrace();
//                    }
//                    myPanel.repaint();
//                    myPanel.revalidate();
//                }
//            }
//        });
    }

    public static void main(String[] args) throws Exception {

    }

    private void createUIComponents() {
        try {
            myPanel = new MyPanel(DBUtil.getAllProfits());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
import model.ProfitTableModel;
import util.DBUtil;
import util.ProfitPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private ProfitPanel myPanel;
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
        //showProfitForComboBox.addItem("quarter");
        showProfitForComboBox.addItem("last year");

        ProfitTableModel profitModel = new ProfitTableModel(DBUtil.getAllProfits(""));
        profitTable.setModel(profitModel);

        showProfitForComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==showProfitForComboBox){

                    switch((String) showProfitForComboBox.getSelectedItem()){
                        case ("all the time"):
                            try {
                                profitList = DBUtil.getAllProfits("");
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                            break;
                        case ("quarter"):
                            try {
                                profitList = DBUtil.getAllProfits("quarter");
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                            break;
                        case ("last year"):
                            try {
                                profitList = DBUtil.getAllProfits("last year");
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                            break;
                    }
                    setProfit(profitList);
                    repaint();
                }
            }
        });
    }
    private void setProfit(List<model.Profit> profitList){
        if(profitList == null){
            try {
                profitList = DBUtil.getAllProfits("");
                myPanel = new ProfitPanel();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(profitTable != null) {
            ProfitTableModel profitModel = new ProfitTableModel(profitList);
            profitTable.setModel(profitModel);
        }
        myPanel.setProfitList(profitList);
    }

    private void createUIComponents() {
        try {
            setProfit(profitList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
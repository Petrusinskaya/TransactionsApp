import model.Profit;
import model.ProfitTableModel;
import util.DBUtil;
import util.ProfitPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Profits extends JFrame {
    private JPanel profit;
    private JLabel monthLabel;
    private JComboBox showProfitForComboBox;
    private JLabel showProfitForLabel;
    private JTable profitTable;
    private JLabel amountLabel;
    private JLabel profitDiagramLabel;
    private ProfitPanel myPanel;
    List<Profit> profitList;

    public Profits(String title) throws Exception {
        super(title);

        $$$setupUI$$$();
        setContentPane(profit);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(720, 420);
        setLocationRelativeTo(null);
        setVisible(true);

        //Adding the options to showProfitForComboBox
        showProfitForComboBox.addItem("all the time");
        showProfitForComboBox.addItem("quarter");
        showProfitForComboBox.addItem("last year");

        //Filling the profits table
        ProfitTableModel profitModel = new ProfitTableModel(DBUtil.getAllProfits(""));
        profitTable.setModel(profitModel);

        showProfitForComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == showProfitForComboBox) {
                    switch ((String) showProfitForComboBox.getSelectedItem()) {
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

    //Adding the profit diagram to the window
    private void setProfit(List<Profit> profitList) {
        if (profitList == null) {
            try {
                profitList = DBUtil.getAllProfits("");
                myPanel = new ProfitPanel();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (profitTable != null) {
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

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        profit = new JPanel();
        profit.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 6, new Insets(0, 0, 0, 0), -1, -1));
        monthLabel = new JLabel();
        monthLabel.setText("Month");
        profit.add(monthLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        profitTable = new JTable();
        profitTable.setAutoCreateRowSorter(true);
        profit.add(profitTable, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        amountLabel = new JLabel();
        amountLabel.setText("Profit");
        profit.add(amountLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(46, 16), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        profit.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        profitDiagramLabel = new JLabel();
        profitDiagramLabel.setText("Profit Diagram");
        profit.add(profitDiagramLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        profit.add(myPanel, new com.intellij.uiDesigner.core.GridConstraints(2, 3, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(500, 240), new Dimension(500, 240), new Dimension(500, 240), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        profit.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        profit.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(5, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(46, 14), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        profit.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(4, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        profit.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(2, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        showProfitForLabel = new JLabel();
        showProfitForLabel.setText("Show profit for");
        profit.add(showProfitForLabel, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(68, 16), null, 0, false));
        showProfitForComboBox = new JComboBox();
        profit.add(showProfitForComboBox, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(488, 30), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        profit.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return profit;
    }
}
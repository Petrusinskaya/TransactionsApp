package model;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

public class ProfitTableModel extends DefaultTableModel {
    public ProfitTableModel() {
        Vector<String> columnNames = new Vector<String>();
        columnNames.add("Month");
        columnNames.add("Profit");
        setColumnIdentifiers(columnNames);
    }

    public ProfitTableModel(List<Profit> list) {
        this();
        setData(list);
    }
    public void setData(List<Profit> list) {
        for(Profit element: list){
            Vector<String> row = new Vector<String>();
            row.add(element.getPeriodAsString());
            row.add((element.getProfit()).toString());
            addRow(row);
        }
    }

}

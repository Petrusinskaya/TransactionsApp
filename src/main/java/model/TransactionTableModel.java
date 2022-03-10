package model;

import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

public class TransactionTableModel extends DefaultTableModel {
    public TransactionTableModel() {
        Vector<String> columnNames = new Vector<String>();
        columnNames.add("Date");
        columnNames.add("Type");
        columnNames.add("Amount");
        columnNames.add("Source");
        setColumnIdentifiers(columnNames);
    }

    public void setData(List<Transaction> list) {
        for(Transaction element: list){
            Vector<String> row = new Vector<String>();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
            String date = sdf.format(element.getDate());
            row.add(date);
            row.add(element.getType());
            row.add(String.format("%.1f",element.getAmount()));
            row.add(element.getSource());
            addRow(row);
        }
    }
}

package cpsc304.UI;

import javax.swing.*;
import java.util.Vector;

public class TableWindow {
    private Vector<Vector<?>> rowData;
    private Vector<?> columnNames;
    private JTable dataTable;
    private JFrame dataFrame;
    private JScrollPane sp;

    public TableWindow(){
        dataFrame = new JFrame("new");
    }

    public void updateTable(Vector<Vector<String>> rowData, Vector<String> columnNames, String title){
        dataFrame.setVisible(false);
        dataFrame = new JFrame(title);
        dataTable = new JTable(rowData, columnNames);
        sp = new JScrollPane(dataTable);
        dataFrame.add(sp);
        dataFrame.setSize(1200,300);
        dataFrame.setVisible(true);
    }

    public void closeTable(){
        dataFrame.setVisible(false);
    }
}

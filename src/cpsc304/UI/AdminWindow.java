package cpsc304.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import java.util.ArrayList;

import cpsc304.delegates.AdminWindowDelegate;
import cpsc304.model.entities.Driver;
import cpsc304.model.entities.VehicleFee;

public class AdminWindow extends JPanel{

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int TEXTFIELD = 15;

    private int textInput;

    private JTextField textField;
    private AdminWindowDelegate delegate;
    private JFrame frame;
    private JScrollPane scrollPane;

    private boolean[] colBitmap = new boolean[2];

    public void launch(AdminWindowDelegate delegate) {
        this.delegate = delegate;

        frame = new JFrame("Administrator");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        this.init();
        frame.add(this);
        frame.setVisible(true);
    }

    private void init() {
        JPanel panel = new JPanel(new GridBagLayout());

        JPanel inputPanel = new JPanel(new GridLayout(8,1));
        JCheckBox cb1 = new JCheckBox("name");
        cb1.addActionListener((e)->{colBitmap[0]= cb1.isSelected();});
        JCheckBox cb2 = new JCheckBox("phone");
        cb2.addActionListener((e)->{colBitmap[1]= cb2.isSelected();});
        inputPanel.add(cb1);
        inputPanel.add(cb2);
        inputPanel.add(new JLabel());

        inputPanel.add(new JLabel("Passenger's SIN: "));
        textField = new JTextField(TEXTFIELD);
        inputPanel.add(textField);

        GridBagConstraints inputConstraint = new GridBagConstraints();
        inputConstraint.gridx = 0;
        inputConstraint.gridy = 0;
        panel.add(inputPanel, inputConstraint);


        JPanel buttonsPanel = new JPanel(new GridLayout(8, 1));
        JButton button1 = new JButton("GET ALL DRIVERS");
        button1.addActionListener((e)->performGetDriver());
        buttonsPanel.add(button1);
        buttonsPanel.add(new JLabel());
        JButton button2 = new JButton("SUM VEHICLE FEES");
        button2.addActionListener((e)->delegate.sumVehicleFee());
        buttonsPanel.add(button2);
        buttonsPanel.add(new JLabel());
        JButton deleteButton = new JButton("DELETE PASSENGER");
        deleteButton.addActionListener((e)->performDelete());
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(new JLabel());
        JButton button3 = new JButton(" GET GOOD PASSENGER");
        button3.addActionListener((e)->delegate.getGoodPassengers());
        buttonsPanel.add(button3);


        GridBagConstraints buttonsConstraint = new GridBagConstraints();
        buttonsConstraint.gridx = 1;
        buttonsConstraint.gridy = 0;
        panel.add(buttonsPanel, buttonsConstraint);


        JTextArea textArea = new JTextArea(5, 20);
        //textArea.setFont(new Font("Monaco", Font.PLAIN, 20)); NOT WORKING
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);
        GridBagConstraints scrollConstraint = new GridBagConstraints();
        scrollConstraint.fill = GridBagConstraints.BOTH;
        scrollConstraint.gridwidth = GridBagConstraints.REMAINDER;
        scrollConstraint.gridx = 0;
        scrollConstraint.gridy= 8;
        panel.add(scrollPane, scrollConstraint);


        add(panel);
    }

    private void performGetDriver() {
        String [] cols = {"name", "phone"};
        String field = "";
        int numFields = 0;

        for (int i = 0; i < cols.length; i++) {
            if (numFields > 0 && colBitmap[i]) field = field.concat(", ");
            if (colBitmap[i]) {
                field = field.concat(cols[i]);
                numFields ++;
            }
        }
        if (numFields == 0) {
            field = "name, phone";
            numFields = 2;
        }
        //System.out.println(field);
        delegate.getAllDrivers(field, numFields);
    }
    public void displayDriverList(String[][] drivers, String field) {
        String [] colName = field.split(",");
        JTable table = new JTable(drivers, colName);
        setDisplay(table);


        //final JList<String> list = new JList<String>(strList.toArray(new String[strList.size()]));

        //setDisplay(list);
    }

    public void displayVehicleFee(VehicleFee[] vehicles) {
        ArrayList <String> strList = new ArrayList<>(vehicles.length);
        String[][] rowdata = new String[vehicles.length][2];
        String [] colName = {"vehicle_id", "fee"};
        for (int i = 0; i < vehicles.length; i++) {
            rowdata[i][0] = Integer.toString(vehicles[i].vehicle_id);
            rowdata[i][1] = Double.toString(vehicles[i].fee);
        }
        //final JList<String> list = new JList<String>(strList.toArray(new String[strList.size()]));
        JTable table = new JTable(rowdata, colName);
        setDisplay(table);
    }

    private void performDelete (){
        try {
            textInput = Integer.valueOf(textField.getText());
        } catch (Exception e) {
            textInput = -1;
        }
        delegate.deletePassenger(textInput);

    }

    public void displayDeleteResult(String msg) {
        String[] strArray = {msg};
        final JList<String> list = new JList<String>(strArray);
        setDisplay(list);
    }

    public void displayGoodPassengers (String[] passengers) {
        final JList<String> list = new JList<String>(passengers);
        String [] colName = {"user_id"};
        String [][] rowData = new String[passengers.length][1];
        for (int i = 0; i < passengers.length; i++) {
            rowData[i][0] = passengers[i];
        }

        setDisplay(new JTable(rowData, colName));
    }

    private void setDisplay (Component c) {
        scrollPane.setViewportView(c);
        scrollPane.doLayout();
        frame.setVisible(true);
    }

}

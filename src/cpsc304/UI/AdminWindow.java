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

    public void launch(AdminWindowDelegate delegate) {
        this.delegate = delegate;

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        this.init();
        frame.add(this);
        frame.setVisible(true);
    }

    private void init() {
        JPanel panel = new JPanel(new GridBagLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.add(new JLabel("Passenger's SIN: "));
        textField = new JTextField(TEXTFIELD);
        inputPanel.add(textField);
        GridBagConstraints inputConstraint = new GridBagConstraints();
        inputConstraint.gridx = 0;
        inputConstraint.gridy = 0;
        panel.add(inputPanel, inputConstraint);


        JPanel buttonsPanel = new JPanel(new GridLayout(8, 1));
        JButton button1 = new JButton("GET ALL DRIVERS");
        button1.addActionListener((e)->delegate.getAllDrivers());
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

    public void displayDriverList(Driver[] drivers) {
        ArrayList <String> strList = new ArrayList<>(drivers.length);
        for (int i = 0; i < drivers.length; i++) {
            strList.add(String.format("%-15s %-15s", drivers[i].name, drivers[i].phone));
        }
        final JList<String> list = new JList<String>(strList.toArray(new String[strList.size()]));

        setDisplay(list);
    }

    public void displayVehicleFee(VehicleFee[] vehicles) {
        ArrayList <String> strList = new ArrayList<>(vehicles.length);
        for (int i = 0; i < vehicles.length; i++) {
            strList.add(String.format("%-15s %-15s", vehicles[i].vehicle_id , vehicles[i].fee));
        }
        final JList<String> list = new JList<String>(strList.toArray(new String[strList.size()]));

        setDisplay(list);
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
        setDisplay(list);
    }

    private void setDisplay (JList <String> list) {
        scrollPane.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        scrollPane.doLayout();
        frame.setVisible(true);
    }

}

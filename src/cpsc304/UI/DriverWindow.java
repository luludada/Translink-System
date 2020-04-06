package cpsc304.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import java.util.ArrayList;

import cpsc304.delegates.DriverWindowDelegate;
import cpsc304.model.entities.Driver;

public class DriverWindow extends JPanel{

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JTextField textField1;
    private JTextField textField2;
    private DriverWindowDelegate delegate;
    private JFrame frame;
    private JScrollPane scrollPane;

    public void launch(DriverWindowDelegate delegate) {
        this.delegate = delegate;
        frame = new JFrame("Driver");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        this.init();
        frame.add(this);
        frame.setVisible(true);
    }

    private void init() {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        String[] attributes = {"name","sin","vehicle_follow_drive1.phone","license_id"};
        final JComboBox<String> cb1 = new JComboBox<String>(attributes);
        cb1.setVisible(true);
        cb1.setEditable(true);
        panel.add(cb1);
        c.gridx = 1;
        String[] relations = {"=","<>",">","<",">=","<="};
        final JComboBox<String> cb2 = new JComboBox<String>(relations);
        cb2.setVisible(true);
        cb2.setEditable(true);
        panel.add(cb2);
        c.gridx = 2;
        textField1 = new JTextField(15);
        panel.add(textField1, c);
        c.gridx = 0;
        //panel.add(new JLabel("Format (replace x by input) : name='x', sin=x, "), c);
        //c.gridy = 2;
        //panel.add(new JLabel("vehicle_follow_drive1.phone='x', license_id='x'"), c);
        //c.gridy = 3;
        //panel.add(new JLabel(" "), c);
        c.gridy = 1;
        panel.add(new JLabel("name"), c);


        textField2 = new JTextField(15);
        c.gridx = 1;
        c.gridy = 1;
        panel.add(textField2, c);


        //JPanel buttonsPanel = new JPanel(new GridLayout(4, 1));
        JButton button1 = new JButton("Get Vehicle id and Route id");
        button1.addActionListener((e)->delegate.getVehicleRoute(cb1.getSelectedItem().toString()+
                cb2.getSelectedItem().toString()+textField1.getText()));
        c.gridx = 3;
        c.gridy = 0;
        panel.add(button1, c);
        //panel.add(new JLabel());

        JButton button2 = new JButton("Get passenger number");
        button2.addActionListener((e)->delegate.getPassengernum(textField2.getText()));
        c.gridy = 1;
        panel.add(button2, c);
        //buttonsPanel.add(new JLabel());

        /*GridBagConstraints buttonsConstraint = new GridBagConstraints();
        buttonsConstraint.gridx = 0;
        buttonsConstraint.gridy = 1;
        panel.add(buttonsPanel, buttonsConstraint);*/

        JTextArea textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);
        GridBagConstraints scrollConstraint = new GridBagConstraints();
        scrollConstraint.fill = GridBagConstraints.BOTH;
        scrollConstraint.gridwidth = GridBagConstraints.REMAINDER;
        scrollConstraint.gridx = 0;
        scrollConstraint.gridy= 5;
        panel.add(scrollPane, scrollConstraint);


        add(panel);
    }

    public void displayTwoids(Driver[] driver, String name) {
        ArrayList<String> strList = new ArrayList<>(driver.length);
        for (int i = 0; i < driver.length; i++) {
            strList.add(" vehicle id: " + driver[i].vehicle_id + " route id: " + driver[i].route_id);
        }
        final JList<String> list = new JList<String>(strList.toArray(new String[strList.size()]));
        scrollPane.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        scrollPane.doLayout();
        frame.setVisible(true);
    }

    public void displayPnum(String name, int num) {
        ArrayList <String> strList = new ArrayList<>(1);
        strList.add(name+" passenger number:"+num);
        final JList<String> list = new JList<String>(strList.toArray(new String[strList.size()]));

        scrollPane.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        scrollPane.doLayout();
        frame.setVisible(true);
    }
}


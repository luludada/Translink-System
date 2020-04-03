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

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        this.init();
        frame.add(this);
        frame.setVisible(true);
    }

    private void init() {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        panel.add(new JLabel("name"), c);
        c.gridy = 2;
        panel.add(new JLabel("name"), c);

        textField1 = new JTextField(15);
        textField2 = new JTextField(15);
        c.gridx = 1;
        c.gridy = 1;
        panel.add(textField1, c);
        c.gridy = 2;
        panel.add(textField2, c);


        //JPanel buttonsPanel = new JPanel(new GridLayout(4, 1));
        JButton button1 = new JButton("Get Vehicle id and Route id");
        button1.addActionListener((e)->delegate.getVehicleRoute(textField1.getText()));
        c.gridx = 2;
        c.gridy = 1;
        panel.add(button1, c);
        //panel.add(new JLabel());

        JButton button2 = new JButton("Get passenger number");
        button2.addActionListener((e)->delegate.getPassengernum(textField2.getText()));
        c.gridy = 2;
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
        scrollConstraint.gridy= 3;
        panel.add(scrollPane, scrollConstraint);


        add(panel);
    }

    public void displayTwoids(Driver driver, String name) {
        ArrayList <String> strList = new ArrayList<>(1);
        strList.add(name+" vehicle id: "+driver.vehicle_id+" route id: "+driver.route_id);
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


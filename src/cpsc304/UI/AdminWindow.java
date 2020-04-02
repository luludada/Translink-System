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

public class AdminWindow extends JPanel{

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private AdminWindowDelegate delegate;
    private JFrame frame;
    private JScrollPane scrollPane;

    public void launch(AdminWindowDelegate delegate) {
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

        JPanel buttonsPanel = new JPanel(new GridLayout(4, 1));
        JButton button1 = new JButton("GET ALL DRIVERS");
        button1.addActionListener((e)->delegate.getAllDrivers());
        buttonsPanel.add(button1);
        buttonsPanel.add(new JLabel());
        GridBagConstraints buttonsConstraint = new GridBagConstraints();
        buttonsConstraint.gridx = 0;
        buttonsConstraint.gridy = 1;
        panel.add(buttonsPanel, buttonsConstraint);

        scrollPane = new JScrollPane();
        GridBagConstraints scrollConstraint = new GridBagConstraints();
        scrollConstraint.gridx = 1;
        scrollConstraint.gridy = 1;
        panel.add(scrollPane, scrollConstraint);


        add(panel);
    }

    public void displayDriverList(Driver[] drivers) {
        ArrayList <String> strList = new ArrayList<>(drivers.length);
        for (int i = 0; i < drivers.length; i++) {
            strList.add(drivers[i].name + " " + drivers[i].phone);
        }
        final JList<String> list = new JList<String>(strList.toArray(new String[strList.size()]));

        scrollPane.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        scrollPane.doLayout();
        frame.setVisible(true);
    }
}

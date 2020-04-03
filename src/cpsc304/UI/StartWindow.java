package cpsc304.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import java.util.ArrayList;

import cpsc304.model.entities.Driver;

public class StartWindow extends JPanel {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JTextField textField1;
    private JTextField textField2;
    //private DriverWindowDelegate delegate;
    private JFrame frame;
    private JScrollPane scrollPane;

    public void launch() {
        //this.delegate = delegate;

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


        //JPanel buttonsPanel = new JPanel(new GridLayout(4, 1));
        JButton button1 = new JButton("Admin");
        //button1.addActionListener((e) -> delegate.getVehicleRoute(textField1.getText()));
        c.gridx = 1;
        c.gridy = 1;
        panel.add(button1, c);
        //panel.add(new JLabel());

        JButton button2 = new JButton("Driver");
        //button2.addActionListener((e) -> delegate.getPassengernum(textField2.getText()));
        c.gridy = 2;
        panel.add(button2, c);
        //buttonsPanel.add(new JLabel());

        JButton button3 = new JButton("Passenger");
        //button3.addActionListener((e) -> delegate.getPassengernum(textField2.getText()));
        c.gridy = 3;
        panel.add(button3, c);

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
        scrollConstraint.gridy = 4;
        panel.add(scrollPane, scrollConstraint);


        add(panel);
    }
}

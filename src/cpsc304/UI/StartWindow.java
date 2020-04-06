package cpsc304.UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import java.util.ArrayList;

import cpsc304.controller.Admin;
import cpsc304.controller.Drive;
import cpsc304.controller.Passenger;

public class StartWindow extends JPanel {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 200;

    private Admin admin;
    private Drive drive;
    private Passenger passenger;

    private JFrame frame;

    public void launch(Admin admin, Drive drive, Passenger passenger) {

        this.admin= admin;
        this.drive = drive;
        this.passenger = passenger;

        frame = new JFrame("Translink-System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        this.init();

        // center the frame
        Dimension d = this.getToolkit().getScreenSize();
        Rectangle r = frame.getBounds();
        frame.setLocation( (d.width - r.width)/2, (d.height - r.height)/2 );

        frame.add(this);
        frame.setVisible(true);
    }

    private void init() {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();


        JPanel buttonsPanel = new JPanel(new GridLayout(5, 1));
        JButton button1 = new JButton("Admin");
        button1.addActionListener((e) -> admin.start());
        c.gridx = 1;
        c.gridy = 1;
        buttonsPanel.add(button1, c);
        buttonsPanel.add(new Label());


        JButton button2 = new JButton("Driver");
        button2.addActionListener((e) -> drive.start());
        c.gridy = 3;
        buttonsPanel.add(button2, c);
        buttonsPanel.add(new JLabel());

        JButton button3 = new JButton("Passenger");
        button3.addActionListener((e) -> passenger.start());
        c.gridy = 5;
        buttonsPanel.add(button3, c);

        panel.add(buttonsPanel);
        add(panel);
    }
}

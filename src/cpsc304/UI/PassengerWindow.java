package cpsc304.UI;

import cpsc304.delegates.AdminWindowDelegate;
import cpsc304.delegates.PassengerDelegate;

import javax.swing.*;
import java.awt.*;

public class PassengerWindow extends JPanel {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private PassengerDelegate delegate;
    private JFrame frame;
    private JScrollPane scrollPane;

    public void launch(PassengerDelegate delegate) {
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
        JButton button1 = new JButton("View Your Account");
        button1.addActionListener((e)->delegate.getPassengerCard());
        buttonsPanel.add(button1);
        buttonsPanel.add(new JLabel());

        JButton button2 = new JButton("Add Money to Account");
        button2.addActionListener((e)->delegate.updateBalance(1));
        buttonsPanel.add(button2);
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

}

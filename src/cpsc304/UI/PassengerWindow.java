package cpsc304.UI;

import cpsc304.delegates.PassengerDelegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PassengerWindow extends JPanel implements ActionListener {


    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    JTextField user_id;


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

        user_id = new JTextField(30);
        JLabel welcome = new JLabel("Please enter your user_id", JLabel.CENTER);
        add(welcome);
        user_id.setBounds(200,150, 200,30);
        add(user_id);

        JButton enter = new JButton("Enter");
        add(enter);
        enter.setActionCommand("Enter");
        enter.addActionListener(this);

        JPanel buttonsPanel = new JPanel(new GridLayout(4, 1));

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Enter")) {
            String id = user_id.getText();
            Boolean isVerified = delegate.verifyPassenger(id);
            if(isVerified) {
                frame.dispose();
                new CardAccountWindow(id);
            } else {
                JOptionPane.showMessageDialog(null,"Invalid user id");
            }
        }
    }
}

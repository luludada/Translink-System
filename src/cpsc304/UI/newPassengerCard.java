package cpsc304.UI;

import cpsc304.delegates.AdminWindowDelegate;
import cpsc304.delegates.PassengerDelegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class newPassengerCard extends JFrame implements ActionListener {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private PassengerDelegate delegate;
    private JFrame frame;
    private JScrollPane scrollPane;

    JTextField textField;
    JTextField SIN;
    JTextField card_num;
    JTextField PIN;
    JTextField user_id;
    JTextField phone;
    JTextField name;
    JTextField email;

    public newPassengerCard() {
        JPanel panel = new JPanel(new GridBagLayout());
        JPanel inputPanel = new JPanel(new GridBagLayout());
        SIN = new JTextField(10);
        add(SIN);
        card_num = new JTextField(10);
        add(card_num);
        PIN = new JTextField(10);
        add(PIN);
        user_id = new JTextField(10);
        add(user_id);
        phone = new JTextField(10);
        add(phone);
        name = new JTextField(10);
        add(name);
        email = new JTextField(10);
        add(email);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

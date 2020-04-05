package cpsc304.UI;

import cpsc304.controller.Passenger;
import cpsc304.delegates.DriverWindowDelegate;
import cpsc304.delegates.PassengerDelegate;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class newPassengerCard extends JFrame implements ActionListener {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private PassengerDelegate delegate;
    private JFrame frame;
    private JButton submit;
    private JButton back;

    JTextField SIN;
    JTextField user_id;
    JTextField Phone;
    JTextField Name;
    JTextField Email;


    public newPassengerCard() {
        //card_num and PIN are random generated
        super("Insert new passenger Information");

        JPanel panel = new JPanel(new GridBagLayout());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(15, 15, 15, 15));

        SIN = new JTextField(10);
        SIN.setUI(new TransparentText("  Enter your SIN", Color.gray));
        SIN.setBounds(300,50, 200,30);
        add(SIN);

        user_id = new JTextField(10);
        user_id.setUI(new TransparentText("  Create user ID", Color.gray));
        user_id.setBounds(300,90, 200,30);
        add(user_id);

        Phone = new JTextField(10);
        Phone.setUI(new TransparentText("  Enter you phone", Color.gray));
        Phone.setBounds(300,130, 200,30);
        add(Phone);

        Name = new JTextField(10);
        Name.setUI(new TransparentText("  Enter your Name", Color.gray));
        Name.setBounds(300,170, 200,30);
        add(Name);


        Email = new JTextField(10);
        Email.setUI(new TransparentText("  Enter your email", Color.gray));
        Email.setBounds(300,210, 200,30);
        add(Email);

        submit = new JButton("Submit");
        submit.setBounds(260,320, 100,30);
        submit.addActionListener(this);
        add(submit);
        back = new JButton("Back");
        back.addActionListener(this);
        back.setBounds(410,320, 100,30);
        add(back);

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Submit")) {
            int sin = Integer.parseInt(SIN.getText());
            String userID = user_id.getText();
            String phone = Phone.getText();
            String name = Name.getText();
            String email = Email.getText();
            Random random = new Random();
            String cardNo = String.format("%04d", random.nextInt(10000));
            int pin = random.nextInt(999);
            //delegate.insertPassengerCard(sin, userID, phone, name, email, cardNo, pin);
            JOptionPane.showMessageDialog(null, "Add successfully, and Your cardNo is" + cardNo);
            dispose();
        }
        if (e.getActionCommand().equals("Back")) {
            dispose();
        }
    }
}

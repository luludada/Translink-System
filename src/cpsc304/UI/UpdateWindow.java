package cpsc304.UI;

import cpsc304.database.DatabaseConnectionHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateWindow extends JFrame implements ActionListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int TEXT_FIELD_WIDTH = 10;
    private DatabaseConnectionHandler dbhandler;
    private TableWindow tb = new TableWindow();

    JTextField testField1;
    JTextField balance;
    JTextField card_num;
    JTextField user_id;
    String[] P1attributes = {"sin", "email", "age"};
    final JComboBox<String> cb1 = new JComboBox<String>(P1attributes);

    public UpdateWindow(DatabaseConnectionHandler dbhandler) {
        super("Update Window");
        this.dbhandler = dbhandler;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);

        JLabel titleLabel = new JLabel("Update Passenger Info");
        titleLabel.setBounds(200, 50, 200, 30);
        add(titleLabel);

        JLabel passengerCard1 = new JLabel("Update PassengerCard1");
        passengerCard1.setBounds(300, 100, 100, 30);
        add(passengerCard1);

        //Not need for passenger_card2 since you only change card 2 when you update phone, so combine at same time


        cb1.setBounds(80, 200, 100, 30);
        contentPane.add(cb1);
        cb1.setVisible(true);
        cb1.setEditable(true);

        JLabel equal = new JLabel("=");
        equal.setBounds(200,200, 10,30);
        add(equal);


        testField1 = new JTextField(TEXT_FIELD_WIDTH);
        testField1.setUI(new TransparentText("  Update your PassengerCard_1 Data", Color.gray));
        testField1.setBounds(220,200, 200,30);
        add(testField1);

        user_id = new JTextField(TEXT_FIELD_WIDTH);
        user_id.setUI(new TransparentText("  Enter User ID", Color.gray));
        user_id.setBounds(220,200, 100,30);
        add(user_id);

        JButton updatePassengerBtn = new JButton("Update PassengerCard");
        updatePassengerBtn.setBounds(260,250, 100,30);
        updatePassengerBtn.addActionListener(this);
        add(updatePassengerBtn);

        JLabel Card = new JLabel("Update Card Info");
        Card.setBounds(200, 400, 100, 30);
        contentPane.add(Card);

        card_num = new JTextField(TEXT_FIELD_WIDTH);
        card_num.setUI(new TransparentText("  Enter Card Num", Color.gray));
        card_num.setBounds(100,450, 100,30);
        add(card_num);

        balance = new JTextField(TEXT_FIELD_WIDTH);
        balance.setUI(new TransparentText("  Update Card Balance", Color.gray));
        balance.setBounds(240,450, 200,30);
        add(balance);


        JButton updateCardBtn = new JButton("Update Card");
        updateCardBtn.setBounds(260,450, 100,30);
        updateCardBtn.addActionListener(this);
        add(updateCardBtn);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Update PassengerCard")) {
            String attribute = cb1.getSelectedItem().toString();
            if(attribute.equals("sin")) {
                dbhandler.updatePassengerInt(attribute, Integer.parseInt(testField1.getText()), user_id.getText());
                tb.updateTable(dbhandler.getAllPassenger(), dbhandler.getPassengerCardColumn(), "Passenger Card");
            } else if (attribute.equals("email")) {
                dbhandler.updatePassengerStr(attribute, testField1.getText(), user_id.getText());
                tb.updateTable(dbhandler.getAllPassenger(), dbhandler.getPassengerCardColumn(), "Passenger Card");
            } else {
                //age
                dbhandler.updatePassengerInt(attribute, Integer.parseInt(testField1.getText()), user_id.getText());
                tb.updateTable(dbhandler.getAllPassenger(), dbhandler.getPassengerCardColumn(), "Passenger Card");
            }
        }

        if (e.getActionCommand().equals("Update Card")) {
            double value = Double.parseDouble(balance.getText());
            dbhandler.updatePassengerCardBalance(value, card_num.getText());
            tb.updateTable(dbhandler.getAllCard(), dbhandler.getCardColumn(), "Card");
        }
    }
}
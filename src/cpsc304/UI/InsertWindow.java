package cpsc304.UI;

import cpsc304.database.DatabaseConnectionHandler;
import cpsc304.model.entities.Card;
import cpsc304.model.entities.PassengerCard1;
import cpsc304.model.entities.PassengerCard2;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class InsertWindow extends JFrame implements ActionListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int TEXT_FIELD_WIDTH = 10;
    private DatabaseConnectionHandler dbhandler;
    private TableWindow tb = new TableWindow();
    JTextField SIN;
    JTextField user_id;
    JTextField Phone ;
    JTextField Name;
    JTextField Email;
    JTextField Age;
    private JButton showPassenger;
    private JButton insertPassenger;
    private JButton back;

    public InsertWindow(DatabaseConnectionHandler dbhandler) {

        super("Insert Window");
        this.dbhandler = dbhandler;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        // INSERT Passenger Card //
        SIN = new JTextField(TEXT_FIELD_WIDTH);
        SIN.setUI(new TransparentText("  Enter your SIN", Color.gray));
        SIN.setBounds(100,50, 120,30);
        add(SIN);

        user_id = new JTextField(TEXT_FIELD_WIDTH);
        user_id.setUI(new TransparentText("  Create user ID", Color.gray));
        user_id.setBounds(200,50, 120,30);
        add(user_id);

        Phone = new JTextField(TEXT_FIELD_WIDTH);
        Phone.setUI(new TransparentText("  Enter you phone", Color.gray));
        Phone.setBounds(300,50, 120,30);
        add(Phone);

        Name = new JTextField(TEXT_FIELD_WIDTH);
        Name.setUI(new TransparentText("  Enter your Name", Color.gray));
        Name.setBounds(400,50, 120,30);
        add(Name);


        Email = new JTextField(TEXT_FIELD_WIDTH);
        Email.setUI(new TransparentText("  Enter your email", Color.gray));
        Email.setBounds(500,50, 120,30);
        add(Email);

        Age = new JTextField(TEXT_FIELD_WIDTH);
        Age.setUI(new TransparentText("  Enter your age", Color.gray));
        Age.setBounds(600,50, 120,30);
        add(Age);


        insertPassenger = new JButton("Insert Passenger");
        insertPassenger.setBounds(160,320, 100,30);
        insertPassenger.addActionListener(this);
        add(insertPassenger);

        showPassenger = new JButton("Show Passenger");
        showPassenger.setBounds(360,320, 100,30);
        showPassenger.addActionListener(this);
        add(showPassenger);


        JButton backBtn = new JButton("Back");



        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Insert Passenger")) {
            Random random = new Random();
                String cardNo = String.format("%04d", random.nextInt(10000));
                int pin = random.nextInt(999);
                int sin = Integer.parseInt(SIN.getText());
                int age = Integer.parseInt(Age.getText());
                double balance  = 0;
                int cvn = random.nextInt(999);
                PassengerCard1 p1 = new PassengerCard1(sin, cardNo, Phone.getText(), user_id.getText(), Email.getText(), pin, age);
                PassengerCard2 p2 = new PassengerCard2(Phone.getText(), Name.getText());
                Card card = new Card(cardNo, balance, cvn);
                dbhandler.insertPassengerCard(p1, p2, card);
                tb.updateTable(dbhandler.getAllPassenger(), dbhandler.getPassengerCardColumn(), "Passenger");
                tb.updateTable(dbhandler.getAllCard(), dbhandler.getCardColumn(), "Card");
        }

    }
}

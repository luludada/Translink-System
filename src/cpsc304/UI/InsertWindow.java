package cpsc304.UI;

import cpsc304.database.DatabaseConnectionHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton showPassenger;
    private JButton back;

    public InsertWindow(DatabaseConnectionHandler dbhandler) {
        super("Insert Window");
        this.dbhandler = dbhandler;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
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
        user_id.setBounds(300,50, 120,30);
        add(user_id);

        Phone = new JTextField(TEXT_FIELD_WIDTH);
        Phone.setUI(new TransparentText("  Enter you phone", Color.gray));
        Phone.setBounds(300,50, 120,30);
        add(Phone);

        Name = new JTextField(TEXT_FIELD_WIDTH);
        Name.setUI(new TransparentText("  Enter your Name", Color.gray));
        Name.setBounds(300,50, 120,30);
        add(Name);


        Email = new JTextField(TEXT_FIELD_WIDTH);
        Email.setUI(new TransparentText("  Enter your email", Color.gray));
        Email.setBounds(300,50, 120,30);
        add(Email);

        showPassenger = new JButton("Show Passenger");
        showPassenger.setBounds(260,320, 100,30);
        showPassenger.addActionListener(this);
        add(showPassenger);





        // Insert Driver

        // Insert Station

        // Insert Bus

        // Insert Ferry







        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        String option = e.getActionCommand();
        switch(option){
            case "show Passenger":
                tb.updateTable(dbhandler.getAllPassenger(), dbhandler.getPassengerCardColumn(), "Passenger");
                break;
        }



    }
}

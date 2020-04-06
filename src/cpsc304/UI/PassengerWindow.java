package cpsc304.UI;

import cpsc304.database.DatabaseConnectionHandler;
import cpsc304.delegates.PassengerDelegate;

import javax.sql.rowset.Joinable;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PassengerWindow extends JPanel implements ActionListener {


    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    JTextField user_id;
    private DatabaseConnectionHandler dbhandler;

    private PassengerDelegate delegate;
    private JFrame frame;



    public void launch(PassengerDelegate delegate, DatabaseConnectionHandler dbHandler) {
        this.delegate = delegate;
        frame = new JFrame("Passenger");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        this.init(dbhandler);
        frame.add(this);
        frame.setVisible(true);
    }
    private void init(DatabaseConnectionHandler dbhandler) {

        JPanel panel = new JPanel(new GridBagLayout());


        JButton insertBtn = new JButton("Insert Passenger");
        insertBtn.setBounds(100,100, 200,30);
        insertBtn.addActionListener(this);
        insertBtn.setActionCommand("insert");
        add(insertBtn);

        JButton updateBtn = new JButton("Update Passenger");
        updateBtn.setBounds(100, 200, 200,30);
        updateBtn.addActionListener(this);
        updateBtn.setActionCommand("update");
        add(updateBtn);

        JButton joinBtn = new JButton("Join Passenger");
        joinBtn.setBounds(100,300, 200,30);
        joinBtn.addActionListener(this);
        joinBtn.setActionCommand("join");
        add(joinBtn);

        add(panel);
        this.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String option = e.getActionCommand();
        switch (option) {
            case "insert":
                new InsertWindow(dbhandler);
                break;
            case "update":
                new UpdateWindow(dbhandler);
                break;
            case "join":
                new JoinWindow(dbhandler);
                break;
        }
    }

}

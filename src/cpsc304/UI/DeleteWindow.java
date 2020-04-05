package cpsc304.UI;

import cpsc304.database.DatabaseConnectionHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteWindow extends JFrame implements ActionListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int TEXT_FIELD_WIDTH = 10;
    private DatabaseConnectionHandler dbhandler;
    private TableWindow tb = new TableWindow();

    private JButton deletePassenger;
    private JButton showPassenger;
    private JButton back;

    JTextField user_id;

    public DeleteWindow(DatabaseConnectionHandler dbhandler) {
        super("Delete Window");
        this.dbhandler = dbhandler;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        user_id = new JTextField(TEXT_FIELD_WIDTH);
        user_id.setUI(new TransparentText("  Enter to be deleted user ID", Color.gray));
        user_id.setBounds(190,50, 120,30);
        add(user_id);



        deletePassenger = new JButton("Delete PassengerCard");
        deletePassenger.setBounds(260,100, 100,30);
        deletePassenger.addActionListener(this);
        add(deletePassenger);

        showPassenger = new JButton("Show PassengerCard");
        showPassenger.setBounds(260,100, 100,30);
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
        String option = e.getActionCommand();
        switch(option) {

            case "Delete PassengerCard":
                dbhandler.deletePassengerCard(user_id.getText());
                break;
            case "Show PassengerCard":
                tb.updateTable(dbhandler.getAllPassenger(), dbhandler.getPassengerCardColumn(), "Passenger");
                break;
        }

    }
}

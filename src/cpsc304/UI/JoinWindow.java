package cpsc304.UI;

import cpsc304.database.DatabaseConnectionHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class JoinWindow extends JFrame implements ActionListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int TEXT_FIELD_WIDTH = 10;
    private DatabaseConnectionHandler dbhandler;
    private TableWindow tb = new TableWindow();


    public JoinWindow(DatabaseConnectionHandler dbhandler) {
        super("Join Window");
        this.dbhandler = dbhandler;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JButton joinBtn = new JButton("Find ALl Passenger Name");
        joinBtn.setBounds(100,200, 100,30);

        joinBtn.addActionListener(this);
        joinBtn.setActionCommand("findName");
        add(joinBtn);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("findName")) {
            //dbhandler.FindALLPassengerName();
            Vector<String> col = new Vector<String>();
            col.add("Name");
            tb.updateTable(dbhandler.FindALLPassengerName(), col, "InnerJoin Find Name");
        }
    }
}

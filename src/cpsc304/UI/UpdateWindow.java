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

    JTextField user_id;
    JTextField card_num;

    public UpdateWindow(DatabaseConnectionHandler dbhandler) {
        super("Update Window");
        this.dbhandler = dbhandler;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
